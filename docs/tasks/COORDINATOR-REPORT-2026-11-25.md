# Informe para el coordinador — trabajo solicitado por el dueño

Fecha: 2026-11-25. Repo: `C:\Users\lanzj\Documents\Catholic-Hermes-work\repo`. Todos los pushes se hicieron a **`joselanzd-hash/Catholic-Hermes` (fork)**; nunca a `origin/CatholicGPT` ni a `main`.

## 1. Resumen ejecutivo

Trabajé, por instrucción directa del dueño, en cuatro frentes: **contenido devocional**, **estabilidad**, **UI landscape** y **identidad/release**. El estado más reciente es **Catholic1 2.0.0**, que ya incluye el rosario de Santa Rita corregido, el arreglo de insets del Rosario en horizontal y el cambio de nombre/icono/splash.

- Release publicada en el fork: https://github.com/joselanzd-hash/Catholic-Hermes/releases/tag/v2.0.0
- Asset verificado: `Catholic1-2.0.0.apk`, 110.915.516 bytes, SHA-256 `77d39a436aa521d37c7bdd3edab49ee01f8417d0a269e66f28d131bf080f04f0`.
- Rama de entrega: `fork/brand/catholic1` @ `f62fa090`.

## 2. Entregas por tarea

| Tarea | Rama fork | SHA final | Qué se entregó | Validación |
|---|---|---|---|---|
| T-055 | `ext/T-055` | `608c678` | Estados vacíos y de error en vertical | 24/24 capturas |
| T-056 | `ext/T-056` | `05b29b5` | 55 coronillas curadas ES/EN/PT | 55 fichas, 0 errores |
| T-058 | `ext/T-058` | `3e6fec7` | 55 imágenes propias y créditos | 55 imágenes, 0 errores |
| T-060 | `ext/T-060` | `b8d6678` | Espaciado de puntuación bíblica | 3.069 ES + 23 EN corregidos |
| T-061 | `ext/T-061` | `c733b33` | 5 devociones marianas + 26 emblemas | Láminas y créditos |
| T-062 | `ext/T-062` | `b444530` | Auditoría visual 13/13 lotes | `assembleDebug` + catálogos verdes |
| T-052 L5 | `ext/T-052` | `79cdbe83` (r2) | Listas con ficha en horizontal | `*LandscapeL5Test` 13/13 |
| Force close 1.9.2 | `fix/chaplet-marker-regex` | `2f594756` | Causa real: regex `\{\{([^}]+)}}` bajo Android ICU | 18/18 tests; APK 1.9.4 |
| T-063 / fidelidad | `fix/chaplet-fidelity` | `2833a57c` | 55 coronillas cuenta por cuenta, con `prayerRef`, `vr` y `rubric` | 55 fichas, 964 pasos, 0 errores |
| T-053 L6 | `ext/T-053` | `c4f491ba` | 6/6 pantallas en horizontal | 6 tests L6, 0 fallos |
| Santa Rita | `fix/santa-rita-rosary` | `ea176be0` | Rosario de 5 misterios según fuente de Londrina | 2/2 tests; APK 1.9.6 |
| Insets Rosario | `ext/fix-rosary-insets` | `c217be9a` | Doble inset lateral en horizontal | 16 tests, 0 fallos |
| Catholic1 2.0.0 | `brand/catholic1` | `f62fa090` | Nombre, icono, splash y release | APK firmado y release publicada |

### Desglose de T-053 (un push por pantalla, como pidió el dueño)

`e6960ff3` Mi camino → `820143e1` Hábitos → `d18bdad1` Diario/Tentaciones/Prácticas → `dfaa2170` Confesión → `dfe0da1b` Recordatorios → `14b53339` Explorar → `c4f491ba` verificación final. Solo ramas `LandscapePhone`; no se tocó `ui/design`, `ui/theme` ni la persistencia de Confesión.

## 3. Revisiones y correcciones atendidas

| Revisión / solicitud | Situación | Cómo se atendió |
|---|---|---|
| **T-052 REVIEW**: 10 tests en rojo | Pendiente de integración en README | Se limitó el cambio de dos paneles a `LandscapePhone` (`appIsLandscapePhone()`), se adaptaron los tests y se entregó r2 @ `79cdbe83`. Quedan documentadas las auditorías `SearchUiTest`, `TabletLayoutTest` y `NarrowLargeTextAuditTest`. |
| **T-062**: el dueño cuestionó que la primera pasada fuera demasiado conservadora | Corregido en la misma entrega | Se recargó la skill de revisión UI, se hizo auditoría estática de 16 archivos y se añadió semántica `heading()` donde faltaba; compile en verde. |
| **T-048 REVIEW**: CERRADO, geometría y `endActions` aprobados | No se repitió | Para el favor de Codex solo se comprobó el hueco real: `RosaryScreen` aislada no pasa por `MainApp`. Se añadió `RosaryInsetsMainAppTest` con el scaffold real. |
| **Force close 1.9.2**: el dueño compartió el informe de error | Corregido | La hipótesis de memoria era incorrecta. La causa fue `ExceptionInInitializerError` → `PatternSyntaxException` en `ChapletVariables` al compilar `\{\{([^}]+)}}` con Android ICU. Se sustituyó el `Regex` por `indexOf`; se conservó el hardening del decoder. |
| **Coronillas**: el dueño pidió fidelidad cuenta por cuenta | Corregido | Se generaron 55 JSON desde evidencia guardada; el validador estricto pasó de 92 errores a 0. No se inventaron oraciones ni respuestas. |
| **Santa Rita**: el dueño dijo que estaba incorrecta y dio la fuente de Londrina | Corregido en 1.9.6 | Se sustituyó la coronilla corta de Catholic Doors por el `Terço` de cinco misterios, con portugués literal y ES/EN traducidos fielmente; 71 rezos guiados. |
| **Insets del Rosario**: Codex pidió comprobar duplicación porque agotó su cuota | Corregido | `AppScaffold` ya aplica `safe.start/end`; `RosaryScreen` lo volvía a sumar. Se ajustaron hilo, cabecera y acciones. Antes: hilo x=72 con recorte izq.; después x=40. Antes: «Siguiente» derecha 720 con recorte der.; después 752. |
| **Catholic1**: el dueño entregó dos SVG y pidió nombre/icono/splash | Hecho | Primera build falló por `windowSplashScreenBackgroundColor`; se corrigió a `windowSplashScreenBackground`. Segunda build verde y release publicada. |

## 4. Evidencia de verificación

- T-052 L5: **13 tests, 0 fallos**, `BUILD SUCCESSFUL in 4m52s`.
- T-053 L6: **6 tests, 0 fallos**, `BUILD SUCCESSFUL in 4m41s`.
- Force close: **18/18** tests verdes (`ChapletVariablesMarkerTest`, `AssetDecodeBudgetTest`, `ChapletGuideAuditTest`, `OtherChapletsEntry*Test`, `CrashRecorderFormatTest`).
- Fidelidad de coronillas: **55 fichas, 964 pasos, 0 errores**.
- Santa Rita: **2/2** tests; validadores 0 errores.
- Insets: **16 tests, 0 fallos**, `BUILD SUCCESSFUL in 3m22s`.
- Catholic1 2.0.0: `assembleDebug` **BUILD SUCCESSFUL in 5m37s**; `aapt` confirma `versionCode=47`, `versionName=2.0.0`, label `Catholic1`; `apksigner` confirma la huella Hermes Preview `b634b6b8…c83d9`.

APKs verificados:

| Versión | SHA-256 |
|---|---|
| 1.9.4 | `1b17f4aba87c6432a4104800d0d836f379280107c3ea643d06ab135071d52405` |
| 1.9.5 | `06491bd54e007b4a9eccbcfbd3c336d622e54fa68f9b43d79f11031b6dbbd3af` |
| 1.9.6 | `71370baae73dbec6dcf9a56bd212f850d2c111f18d45392945506e4b3d8845ee` |
| 2.0.0 | `77d39a436aa521d37c7bdd3edab49ee01f8417d0a269e66f28d131bf080f04f0` |

## 5. Estado actual y lo que queda

- `origin/CatholicGPT` sigue en `d2ebed06` (1.9.2) y esta credencial no tiene push allí. La integración de T-052 r2, 1.9.5/1.9.6, T-053, insets y 2.0.0 depende del coordinador.
- README sigue marcando **T-052 con cambios pedidos** y **T-053 ABIERTO**; T-054 está en cola para Codex.
- Pendiente de decisión: `SearchUiTest` (1 fallo), `TabletLayoutTest` y `NarrowLargeTextAuditTest` (colgados / hallazgo APPLE ES).
- 2.0.0 no se ha probado en teléfono físico. El splash personalizado aplica a Android 12+; en versiones anteriores queda el splash del sistema.
- El repo `jaldps/Catholic-Hermes` no permite publicar release con esta credencial (`push=false`); el tag `v2.0.0` está publicado en el fork y el coordinador debe promoverlo si quiere el release en origin.

## 6. Restricciones cumplidas

- No se hicieron push a `origin` ni a `main`.
- No se subió el APK al repo; solo a Releases del fork y a metadatos `docs/quality/release-*`.
- No se generó contenido devocional propio: cada texto proviene de la fuente citada o de `prayerRef` canónicos de la app.
- En T-053 no se tocó `ui/design`, `ui/theme`, `MainApp.kt`, los lectores, la Biblia, `assets/`, `data/` ni textos religiosos.
- En el encargo de insets no se cambiaron versiones, firma, Room, contenido ni `dist/`.
