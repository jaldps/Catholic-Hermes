# Catholic1 2.0.0 — identidad visual

## Alcance

A petición del dueño, la app pasa a llamarse **Catholic1** y usa los dos SVG entregados en `Downloads/Catholic1/`:

- `Catholic1_estrellas_simetricas.svg`: símbolo (Virgen, doce estrellas, Biblia con el 1 y rosario).
- `Catholic1_tipografia.svg`: logotipo tipográfico «Catholic1» en azul marino con el 1 dorado.

## Cambios

| Superficie | Antes | Ahora |
|---|---|---|
| Nombre de app | `Sancta Fides` / `Catholic Hermes <versión>` en el overlay | `Catholic1` en `strings.xml`, en el overlay de firma y en el pie de «Más» |
| Icono de lanzador | Icono Sancta Fides | Símbolo Catholic1 sobre fondo `#0D2E50`, generado a 48/72/96/144/192 px y adaptive foreground |
| Splash | Icono del sistema sin personalizar | `values-v31/themes.xml` usa `windowSplashScreenAnimatedIcon` con el lockup símbolo + tipografía sobre blanco |
| Versión | 1.9.6 (code 46) | **2.0.0** (code 47) |

La rama `brand/catholic1` nace de `fix/santa-rita-rosary` (1.9.6) e incorpora por merge `ext/fix-rosary-insets`, así que esta APK conserva el rosario de Santa Rita corregido y el arreglo de insets del Rosario en horizontal.

## Verificación

- `assembleDebug` con el overlay Hermes Preview: **BUILD SUCCESSFUL in 5m 37s**.
- `aapt dump badging`: `versionCode=47`, `versionName=2.0.0`, `application-label:'Catholic1'`.
- `apksigner verify --print-certs`: certificado Hermes Preview `b634b6b8…c83d9`.
- SHA-256: `77d39a436aa521d37c7bdd3edab49ee01f8417d0a269e66f28d131bf080f04f0`.
- Tamaño: 110.915.516 bytes.

## Limitación

El lockup personalizado del splash se aplica en **Android 12+** (`values-v31`); en versiones anteriores queda el splash del sistema. No se ha probado en teléfono físico.
