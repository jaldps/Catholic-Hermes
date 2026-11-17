# T-026 · Informe de Codex

Rama `ext/T-026`, desde `origin/CatholicGPT` (`3172bed`). **Partes A y B implementadas. A entregada en `8af52f3`; esta segunda entrega completa la higiene. La validación conserva un fallo intermitente del lector, detallado abajo.**

## Registro local y privacidad

Los fallos recuperables instrumentados dejan ahora un rastro técnico local, sin modificar sus retornos, fallbacks, estados de error ni reintentos.

- `AppLog` escribe en `filesDir/diagnostics/current.log`: máximo 256 KiB, más un único `previous.log` del mismo límite. Rota antes de superar el tamaño. Cada línea lleva instante ISO UTC, nivel, etiqueta fija y mensaje técnico.
- La API segura solo acepta un evento de un enum, una excepción opcional y un nivel. No acepta mensajes de texto libres. Guarda la clase de la excepción y hasta cuatro frames con nombres técnicos saneados; **el formateador no serializa `message`, `localizedMessage`, `toString`, cadenas de causas ni excepciones suprimidas**. Los nombres de fuente que parecen rutas o texto libre se sustituyen.
- No se registran diario, notas, contraseñas, contenido de copias, coordenadas, URL de petición ni voces/posiciones elegidas. Las cancelaciones normales se omiten. No hay telemetría ni servicio de subida.
- Escritura en un único trabajador fuera del hilo principal, con cola acotada a 128 tareas. Los problemas del propio registro se absorben y no producen recursión ni cambios en la operación original.
- La inicialización ocurre antes de instalar los catálogos. Compartir espera a las escrituras aceptadas y crea una instantánea separada; no expone los archivos activos. Se conservan hasta tres instantáneas en caché.

## Compartir, siempre por decisión del usuario

Explorar → Ajustes → **Enviar informe de errores** (`error_report_entry`). La hoja explica en ES/EN/PT qué contiene y qué excluye. Solo el botón Compartir prepara el archivo y abre el selector de Android (`ACTION_SEND`, MIME `text/plain`). El usuario elige la aplicación y decide si enviarlo.

`FileProvider` no exportado, con permiso URI de lectura y un único directorio permitido: `cacheDir/error-reports/`. No permite acceder a `filesDir`, a los archivos del diario ni a las copias. La URI también viaja en `ClipData`. La pantalla usa los wrappers existentes (`AppSheet`, botones y avisos).

## Selección de puntos de fallo

**37 sitios de las rutas existentes**, más el fallo de compartir de la propia pantalla. Se priorizan I/O/parseo de Biblias y catálogos, feeds y caché, escrituras personales/preferencias, recuperación de copias, recordatorios y audio. No se añaden registros a cada validación de entrada prevista ni a cada cancelación.

| Archivo | Sitios |
|---|---:|
| [MainActivity.kt](../../app/src/main/java/com/example/MainActivity.kt#L101) | 1 |
| [LiturgicalTtsManager.kt](../../app/src/main/java/com/example/data/audio/LiturgicalTtsManager.kt#L214) | 9 |
| [BackupService.kt](../../app/src/main/java/com/example/data/backup/BackupService.kt#L142) | 2 |
| [HttpText.kt](../../app/src/main/java/com/example/data/network/HttpText.kt#L22) | 2 |
| [VaticanGospelFetcher.kt](../../app/src/main/java/com/example/data/news/VaticanGospelFetcher.kt#L186) | 2 |
| [VaticanNewsFetcher.kt](../../app/src/main/java/com/example/data/news/VaticanNewsFetcher.kt#L77) | 1 |
| [VaticanReadingsCache.kt](../../app/src/main/java/com/example/data/news/VaticanReadingsCache.kt#L33) | 2 |
| [PersonalDataCommands.kt](../../app/src/main/java/com/example/data/persistence/PersonalDataCommands.kt#L61) | 1 |
| [UserPreferencesStore.kt](../../app/src/main/java/com/example/data/prefs/UserPreferencesStore.kt#L86) | 1 |
| [ReminderScheduler.kt](../../app/src/main/java/com/example/data/reminders/ReminderScheduler.kt#L96) | 2 |
| [BibleEditions.kt](../../app/src/main/java/com/example/data/repository/BibleEditions.kt#L45) | 3 |
| [BibleTextProvider.kt](../../app/src/main/java/com/example/data/repository/BibleTextProvider.kt#L62) | 1 |
| [CatholicDataRepository.kt](../../app/src/main/java/com/example/data/repository/CatholicDataRepository.kt#L347) | 1 |
| [DevotionsCatalog.kt](../../app/src/main/java/com/example/data/repository/DevotionsCatalog.kt#L66) | 1 |
| [NovenaCatalog.kt](../../app/src/main/java/com/example/data/repository/NovenaCatalog.kt#L64) | 1 |
| [OrdersCatalog.kt](../../app/src/main/java/com/example/data/repository/OrdersCatalog.kt#L60) | 1 |
| [PrayerDetailsCatalog.kt](../../app/src/main/java/com/example/data/repository/PrayerDetailsCatalog.kt#L74) | 1 |
| [QuestionDeeperCatalog.kt](../../app/src/main/java/com/example/data/repository/QuestionDeeperCatalog.kt#L47) | 1 |
| [SaintsCatalog.kt](../../app/src/main/java/com/example/data/repository/SaintsCatalog.kt#L54) | 1 |
| [StudyCoursesCatalog.kt](../../app/src/main/java/com/example/data/repository/StudyCoursesCatalog.kt#L103) | 1 |
| [DailyVerseStore.kt](../../app/src/main/java/com/example/data/verse/DailyVerseStore.kt#L162) | 2 |

## Verificación

- Suite completa sobre la base sin modificar: **584 pruebas, 0 fallos, 0 omitidas**, `BUILD SUCCESSFUL` (54 m 41 s). No se reprodujeron en esta pasada los inestables descritos por la auditoría antigua. [Resultado completo](T-026-validation/baseline.json).
- Parte A: **35 pruebas, 0 fallos, 0 omitidas**, más `assembleDebug`: `BUILD SUCCESSFUL` (6 m 28 s). Incluye los tests nuevos de rotación, formato, privacidad, instantánea, URI/permisos, rechazo de archivos personales y entrada/confirmación manual en la UI; también copia, preferencias, narración y navegación. [Resultado por caso](T-026-validation/part-a.json).
- Comprobación de UI: abrir la hoja no lanza una actividad externa; el selector solo aparece después de pulsar Compartir.
- Final completo: **592 pruebas, 591 aprobadas, 1 fallida, 0 omitidas**. `assembleDebug` terminó; el comando combinado acabó con `BUILD FAILED in 1h 21s` por el test indicado abajo. [Resultado completo, sin ocultar el fallo](T-026-validation/final.json).
- Comprobación final de compilación en `ext/T-026`: `assembleDebug`, **BUILD SUCCESSFUL in 14s**.
- Comparación por `(clase, nombre)`: **los 584 casos de la base están presentes**, más **8 nuevos aprobados**. [Comparación](T-026-validation/comparison.json).

### Inestabilidad conocida y repetición

Falló `HomePrayerPrioritiesTest.prayerFollowsSeasonAndCounterPrecedesLiturgyWithExactVerseReader[SANCTUARY-LIGHT]`, línea 145: el selector de capítulo no aparecía visible al comprobarlo inmediatamente después de abrir el versículo. Es el caso documentado como intermitente en `docs/AUDIT-2026-11.md`, D4. En la base de esta entrega sus ocho casos habían pasado; en el final completo falló una variante.

`HomeScreen.kt`, `BibleScreen.kt` y el test permanecen idénticos a la base. El árbol runtime conserva las versiones de sus bibliotecas. Se repite **la clase completa (8 casos)**, sin editar código ni aserciones. Primera repetición: **8 casos, 7 aprobados y 1 fallido**, `BUILD FAILED in 2m 26s`. Sanctuary claro pasó y falló Sanctuary oscuro, en la misma línea 145. [Resultado por caso](T-026-validation/reader-retry.json). Los 592 casos han pasado al menos una vez entre la ejecución completa y esta repetición; esto **no** convierte la ejecución completa en una pasada sin fallos. La repetición de la clase sobre la **base original** dio **8 aprobados, 0 fallos**, `BUILD SUCCESSFUL in 2m 37s`; [casos](T-026-validation/baseline-reader.json). Tras restaurar T-026, la comprobación final de la clase dio **8 casos, 7 aprobados y 1 fallido**, otra vez Sanctuary oscuro en la línea 145; `BUILD FAILED in 4m 40s`. [Resultado](T-026-validation/final-reader-check.json). La prueba intermitente no se excluye ni se altera.

**La suite no está completamente verde.** La auditoría anterior documenta el mismo síntoma como intermitente, pero la base pasó en nuestras comparaciones y T-026 falló en las ejecuciones descritas; estas muestras no permiten atribuir la diferencia con certeza ni descartar una influencia de tiempos. Queda pendiente diagnosticar D4 en el lector/test y reproducirlo en dispositivo. El código de navegación y del lector permanece intacto dentro de este encargo de registro e higiene.

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=2 --no-configuration-cache --no-build-cache \
  -I /tmp/t026-runner.gradle :app:assembleDebug :app:testDebugUnitTest
```

La base usó cuatro trabajadores y la ejecución final limita `--max-workers=2` por el espacio del contenedor; no se filtran ni omiten casos. El init script temporal solo ajusta `Test.systemProperty('user.home', '/tmp/catholic-toolchain/user')` para Robolectric y habilita salida de resultados. Está fuera del repositorio. La suite completa posterior a la higiene contiene todos los casos de la base y los ocho nuevos, sin modificar pruebas existentes ni omitir casos.

## Parte B · Código sin consumidores

Se buscó cada símbolo en producción, pruebas y recursos antes de retirarlo. Solo aparecían las cuatro declaraciones; ninguna llamada externa. Se conservan el contrato `AppKit`, los wrappers y el kit actual.

| Archivo / declaración | Líneas eliminadas | Bytes eliminados |
|---|---:|---:|
| `ui/design/AppleKit.kt` | 1.892 | 86.731 |
| `ui/design/MaterialKit.kt` | 1.653 | 77.217 |
| `ui/design/VaticanKit.kt` | 2.085 | 96.611 |
| `ThemePalettes` e imports exclusivos en `ThemePresets.kt` | 245 | 9.371 |
| **Total Kotlin** | **5.875** | **269.930 (263,6 KiB)** |

El enum `AppThemePreset` permanece completo. La compilación inmediatamente posterior a esta retirada terminó con `BUILD SUCCESSFUL in 2m 3s`.

Se mantienen **`DesignLanguage.APPLE` (Lumen) e `IVE`**: `IVE` tiene cinco referencias vivas de producción, además de referencias de prueba. Eliminarlo excedería la limpieza segura y arriesgaría las preferencias antiguas. No cambia el selector de identidades.

Pruebas de búsqueda, con sus coincidencias antes y después: [hygiene.json](T-026-validation/hygiene.json). Comandos equivalentes:

```sh
git grep -n -E '\b(AppleKit|MaterialKit|VaticanKit|ThemePalettes)\b' 8af52f3 -- app/src
rg -n '\b(AppleKit|MaterialKit|VaticanKit|ThemePalettes)\b' app/src
rg -n 'DesignLanguage\.IVE' app/src/main
rg -n 'androidx\.navigation|retrofit2|com\.google\.firebase|BuildConfig\.' app/src/main
```

Los dos primeros comandos arrojan cuatro declaraciones antes y cero coincidencias después. El cuarto no encuentra consumidores de las dependencias retiradas. La única coincidencia de `BuildConfig` es `VERSION_NAME`, generada por Android, no por Secrets.

## Parte B · Dependencias y plugins

Grep de imports y usos en Kotlin/XML y configuración no mostró consumidores. Se retiraron declaraciones, alias/versiones exclusivos y bloques de configuración ya sin uso. No existía `google-services.json` en la base. No se borran claves ni archivos locales de configuración.

Se compiló después de cada familia retirada, en este orden:

| Retirada | Resultado de `assembleDebug` |
|---|---|
| Navigation Compose | `BUILD SUCCESSFUL in 4m 41s` |
| Retrofit y conversor Moshi | `BUILD SUCCESSFUL in 5m 6s` |
| Firebase AI | `BUILD SUCCESSFUL in 5m 7s` |
| Firebase App Check (reCAPTCHA y debug), BOM sin consumidores activos | `BUILD SUCCESSFUL in 4m 46s` |
| Plugin Secrets y su bloque | `BUILD SUCCESSFUL in 4m 52s` |
| Plugin Google Services, import y su bloque | `BUILD SUCCESSFUL in 4m 39s` |

No se elimina Moshi ni OkHttp, que sí tienen uso. La base resolvía **OkHttp 4.12.0 y Okio 3.9.1** por dependencias transitivas aunque declaraba OkHttp 4.10.0. Para conservar el comportamiento efectivo, la declaración de OkHttp pasa a 4.12.0 y una restricción mantiene Okio 3.9.1. También se conservan las versiones previamente resueltas de Compose (1.7.2), Collection (1.4.4) y Concurrent Futures (1.2.0), que llegaban por Navigation. Se usan restricciones, sin añadir bibliotecas. Moshi Kotlin sigue en 1.15.2. La comparación de todo el árbol `debugRuntimeClasspath` confirma **86 módulos menos, cero módulos nuevos y cero cambios de versión en los módulos que permanecen**, además de la ausencia de Navigation, Retrofit, Firebase AI y App Check. [Comparación por módulo](T-026-validation/dependency-comparison.json).

[Compilaciones por etapa](T-026-validation/dependency-stages.json).

| APK debug con la misma configuración | Bytes |
|---|---:|
| Base `3172bed` | 100.143.113 |
| Parte A | 100.144.862 |
| Después de B | 93.033.268 |

Reducción frente a la base: **7.109.845 bytes (6,78 MiB; 7,10 %)**. Es una medida de debug, sin R8; no se publica un APK en este encargo.

No se cambia la versión de producción, R8, reducción de recursos, formato de novenas, `tools/hermes-preview.gradle`, `dist/`, esquema Room, pestañas ni estructura de ViewModels. [Comprobación de alcance](T-026-validation/scope.json).

## Incidencias del entorno de validación

Algunos intentos intermedios agotaron el disco del contenedor al regenerar transformaciones de Gradle. Un intento adicional de la base tampoco pudo cargar las fuentes nativas de Robolectric por falta de espacio; se descartó como comprobación funcional y se repitió. Se liberaron únicamente cachés y salidas regenerables de `app/build`, y se repitieron las tareas afectadas hasta obtener las compilaciones satisfactorias de la tabla. El fallo de `checkDebugDuplicateClasses` en esos intentos era de escritura de caché por falta de espacio, no un conflicto de clases de la app. Los APK y capturas versionados permanecen intactos.

## Fuentes y límites

Fuentes: `docs/tasks/T-026.md`, `AGENTS.md`, `docs/AUDIT-2026-11.md`, las búsquedas de referencias en código/recursos/tests, el árbol Gradle y las APIs Java/Android ya disponibles en el proyecto. No hay nuevo contenido doctrinal ni dependencias añadidas en A.

No se ha probado el envío a una aplicación destinataria en un teléfono real. Tampoco se ha reproducido D4 en un teléfono; sigue siendo una limitación de la validación automatizada. La URI y el selector se verifican con Robolectric; no se ha enviado ningún informe a terceros. El registro cubre los sitios instrumentados; no captura mensajes arbitrarios ni instala un manejador global de excepciones no controladas.
