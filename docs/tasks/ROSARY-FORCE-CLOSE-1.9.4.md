# Force close al abrir Rosario / Coronilla / Otras coronillas — informe para el coordinador

## Versión afectada y causa exacta

- 1.9.2 y 1.9.3.
- El dueño compartió `error-report-a39db8c8-331e-434e-929b-d7bbb4cdc5ac.txt`. El stack es inequívoco:

```
java.lang.ExceptionInInitializerError
caused by java.util.regex.PatternSyntaxException
  at java.util.regex.Pattern.compile (Android ICU)
  at com.example.ui.screens.ChapletVariables.<clinit>(ChapletEngine.kt:141)
  at com.example.ui.screens.RosaryScreenKt.RosaryScreen(RosaryScreen.kt:1134)
  at com.example.ui.MainAppKt.MainApp(MainApp.kt:805)
```

- `ChapletVariables` compilaba estáticamente `Regex("""\{\{([^}]+)}}""")`. Los corchetes de apertura están escapados pero los dos `}` finales **no**. El `Pattern` de JVM lo acepta (por eso pasaban todos los tests en Robolectric), pero el `PatternNative` de Android ICU lo rechaza con `PatternSyntaxException`. Al ser un field estático, la primera vez que se entra en `RosaryScreen` se lanza `ExceptionInInitializerError` y la app se cierra. Afecta al Rosario, a la Coronilla de la Divina Misericordia y a «Otras coronillas» porque las tres pasan por `RosaryScreen` y tocan `ChapletVariables`.

## Corrección real (1.9.4)

- Se elimina el `Regex` estático. `ChapletVariables.apply` ahora parsea los marcadores con `indexOf("{{")` / `indexOf("}}")`, sin compilar ninguna expresión regular. Es inmune a las diferencias JVM/ICU.
- El comportamiento se conserva: `{{nombre}}`, `{{intencion}}`, `{{él|ella}}` y sus variantes; un marcador desconocido o malformado se deja literal.
- `ChapletVariablesMarkerTest` cubre sustituciones, fallbacks, marcadores malformados y —como regresión estructural— que la clase no contenga ningún campo `Regex`.

## Hardening previo (1.9.3, se conserva)

Antes de recibir el informe se corrigió un defecto distinto y real del decoder de imágenes: `inSampleSize` usaba `>=` y podía decodificar hasta el doble del ancho pedido. Ahora `assetSampleSize` nunca excede el presupuesto, los decodes se limitan a 720 px y la caché bajó de 48 a 24 MB. No era la causa de este force close, pero reduce la presión de memoria al abrir el catálogo de 55 coronillas.

## Verificación 1.9.4

- `./gradlew :app:testDebugUnitTest --tests ... --max-workers=4` → **BUILD SUCCESSFUL in 5m 13s**, **18 tests, 0 fallos**:
  - `ChapletVariablesMarkerTest` 4/4;
  - `AssetDecodeBudgetTest` 2/2;
  - `ChapletGuideAuditTest` 1/1 (55 coronillas);
  - `OtherChapletsEntryCrashTest` 2/2;
  - `OtherChapletsEntryMatrixTest` 8/8;
  - `CrashRecorderFormatTest` 1/1.
- APK 1.9.4 firmado con el certificado Hermes Preview (`b634b6b8…`), `versionCode=44`, `application-label='Catholic Hermes 1.9.4'`, SHA-256 `1b17f4aba87c6432a4104800d0d836f379280107c3ea643d06ab135071d52405`, 110787368 bytes.

## Nota para el coordinador

La causa no era de memoria: era un `PatternSyntaxException` en un `Regex` estático que solo falla en el runtime de Android. Robolectric no podía detectarlo. Gracias al grabador de crashes de 1.9.2 el informe del teléfono dio la clase, el método y la línea exactos.
