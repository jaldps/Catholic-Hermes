# Encargos para agentes externos (Hermes, Codex)

El coordinador (Claude, en la nube) publica aquí encargos autocontenidos en la rama `CatholicGPT`. Los agentes externos los ejecutan en el PC de Jose y entregan su trabajo en una rama propia. El coordinador revisa e integra; **nadie más escribe en `CatholicGPT`**.

## Protocolo

1. **Elegir un encargo.** Busca en `docs/tasks/T-*.md` uno con `Estado: ABIERTO` y `Asignado: <tu nombre>`.
2. **Partir de lo último.** Ejecuta `git fetch origin && git switch -c ext/T-00N origin/CatholicGPT`. Si la rama ya existe, haz `git switch ext/T-00N` y luego `git merge origin/CatholicGPT`.
3. **Trabajar.** Haz solo lo que pide el encargo y toca solo los archivos que nombra. Lee siempre antes `docs/content/EDITORIAL-RULES-1.8.md` y la especificación que cite el encargo.
4. **Validar.** (Entregas parciales: añade `--partial` al validador; acepta un prefijo ordenado de la lista.) Ejecuta el validador del encargo hasta que salga con `0 errors`. Si tienes Android SDK y Java 21, puedes compilar con `./gradlew :app:testDebugUnitTest --tests <clase>`. No es obligatorio: el coordinador compila aquí.
5. **Informar.** Escribe `docs/tasks/T-00N-REPORT.md` con:
   - qué hiciste;
   - el resultado del validador (las últimas líneas);
   - las fuentes que consultaste;
   - los datos que **no** pudiste verificar;
   - las preguntas para el coordinador.
6. **Entregar.** Haz commit con `T-00N: <resumen>` y `git push -u origin ext/T-00N`. Si no tienes permiso de push en `jaldps/Catholic-Hermes`, haz push de la misma rama a tu fork: el coordinador lo lee desde ahí. Puedes subir entregas parciales: cada push es un punto de revisión.
7. **Correcciones.** El coordinador responde en `docs/tasks/T-00N-REVIEW.md`, dentro de tu rama o en `CatholicGPT`. Si pide cambios, corrígelos en la misma rama y vuelve a hacer push.

## Reglas fijas

- No hagas push a `CatholicGPT` ni a `main`. No hagas force-push sobre ramas ajenas.
- Nunca subas claves (`*.keystore`, `*.jks`), APK, `local.properties`, tokens ni contraseñas.
- Oraciones y novenas **reales**: su texto debe existir en una fuente comprobada y se reproduce fielmente; nunca se componen textos nuevos (traducir fielmente sí está permitido).
- Derechos: que un texto aparezca en un santuario, diócesis, editorial o web **no implica copyright**. Solo se trata como protegido si hay una **reserva expresa verificada**; en ese caso se indica «Derechos: <titular>».
- No inventes nada: citas, fechas, aprobaciones, indulgencias, números de concesión, promesas ni milagros. Si no puedes verificar un dato, omítelo o di «según la tradición» y anótalo en el informe.
- Textos con derechos de autor (traducciones litúrgicas modernas, libros, webs): no se copian. Usa textos de dominio público o redacción y traducción propias, y márcalo en `textNote`.
- Tres idiomas completos y naturales. Español; inglés; portugués de Brasil.
- Mantén los ids, claves y formatos exactos del contrato. El validador es la referencia.

## Encargos

| Encargo | Asignado | Estado | Tema |
|---|---|---|---|
| [T-001](T-001.md) | Hermes | CERRADO ([revisión](T-001-REVIEW-2.md)) | M3 · detalles de 34 oraciones básicas → `assets/prayers/details-a.json` |
| [T-002](T-002.md) | Codex | CERRADO ([revisión](T-002-REVIEW.md)) | M3 · detalles de 34 oraciones especializadas → `assets/prayers/details-b.json` |
| [T-003](T-003.md) | Codex | CERRADO ([revisión](T-003-REVIEW.md)) | M3 · ~30 oraciones nuevas, reales y documentadas → `assets/prayers/extra.json` |
| [T-004](T-004.md) | Codex | CERRADO ([revisión](T-004-REVIEW.md)) | Textos auténticos (no redactados) para las 16 novenas nuevas de 1.8.2 |
| [T-007](T-007.md) | Codex | ABIERTO (prioritario) | Textos auténticos para oraciones alteradas o sin procedencia → `assets/prayers/text-overrides.json` |
| [T-008](T-008.md) | Codex | ABIERTO (prioritario) | Texto real y verificado para 15 novenas retiradas (Aparecida, Luján, Pilar…) |
| [T-005](T-005.md) | Codex | ABIERTO | M4 · «Profundizar» de las preguntas 1–51 → `assets/questions/deeper-a.json` |
| [T-006](T-006.md) | Codex | ABIERTO | M4 · «Profundizar» de las preguntas 52–101 → `assets/questions/deeper-b.json` |
