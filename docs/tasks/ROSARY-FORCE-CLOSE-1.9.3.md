# Force close al abrir Rosario / Coronilla / Otras coronillas — informe para el coordinador

## Versión afectada

- 1.9.2 (código 42, `73e998e9`). El propio commit de 1.9.2 documentaba el force close como **no reproducible bajo Robolectric** y añadía un grabador de crashes; no se pudo confirmar en teléfono (`known_limitations`).

## Causa identificada en el código

No se dispone del informe de error del teléfono (no hay dispositivo conectado ni archivo `error-report-*.txt` compartido), así que se hizo una auditoría estática de la ruta común: `MainApp → RosaryScreen → rememberAssetBitmap → HeroCache.bitmap`.

El defecto está en el cálculo de `inSampleSize` de `LumenFoundation.kt`:

```kotlin
var sample = 1
while (bounds.outWidth / (sample * 2) >= maxWidthPx) sample *= 2
```

El operador `>=` y la comparación contra `outWidth / (sample*2)` **dejan la imagen decodificada por encima del ancho solicitado**:

- `outWidth=1080`, `maxWidthPx=720` → `sample=1` → decode real **1080 px** (50 % por encima).
- `outWidth=900`, `maxWidthPx=240` → `sample=2` → decode real **450 px** (87 % por encima).

Abrir el rosario (hero a 1080) y después desplazar el catálogo de 55 coronillas (miniaturas) mantiene simultáneamente varias imágenes sobredimensionadas en un `LruCache` de 48 MB. En un teléfono con heap pequeño esto agota memoria y el proceso se cierra; en Robolectric/JVM no reproduce porque el heap es mucho mayor. Es coherente con que 1.9.2 ya había intentado mitigar el problema limitando la paralelidad y bajando las miniaturas a 144 px, pero sin corregir el muestreo.

## Corrección en 1.9.3

1. `assetSampleSize(outWidth, maxWidthPx)` usa `while (outWidth / sample > maxWidthPx) sample *= 2`: la imagen **nunca** se decodifica por encima del ancho pedido.
2. `rememberAssetBitmap` limita todo decode a `MaxAssetDecodeWidth = 720` px, con clave de caché coherente.
3. `HeroCache` baja de 48 MB a 24 MB.
4. `RosaryScreen` sustituye dos `!!` por fallbacks (`chapletGuide?.guide ?: mercyGuide`, `chaplet?.id ?: "other"`), para que una ficha inesperadamente ausente no tumbe la pantalla.

## Verificación

- `./gradlew :app:testDebugUnitTest --tests ... --max-workers=4` → **BUILD SUCCESSFUL in 3m 56s**, **14 tests, 0 fallos**:
  - `AssetDecodeBudgetTest` 2/2;
  - `ChapletGuideAuditTest` 1/1 (55 coronillas reales);
  - `OtherChapletsEntryCrashTest` 2/2;
  - `OtherChapletsEntryMatrixTest` 8/8;
  - `CrashRecorderFormatTest` 1/1.
- APK 1.9.3 firmado con el certificado Hermes Preview (`b634b6b8…`), `versionCode=43`, `application-label='Catholic Hermes 1.9.3'`, SHA-256 `5a47fd433dd5fd5ea3804489b442d13f0ec921f26c5216ca9b34bf3bc4c0331e`, 110721828 bytes.

## Limitación

Sin el informe de error del teléfono no se puede afirmar que el stack sea exactamente un `OutOfMemoryError`. La corrección ataca el defecto demostrable encontrado en el código y la sobrecarga de memoria que explica el síntoma. Si el force close continúa en 1.9.3, necesito el archivo «Enviar informe de errores» de Ajustes para afinar la causa.
