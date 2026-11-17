# T-026 · Informe de Codex

Rama `ext/T-026`, desde `origin/CatholicGPT` (`3172bed`). **Parte A terminada y validada; Parte B pendiente de esta segunda entrega.**

## Registro local y privacidad

Los fallos recuperables instrumentados dejan ahora un rastro técnico local, sin modificar sus retornos, fallbacks, estados de error ni reintentos.

- `AppLog` escribe en `filesDir/diagnostics/current.log`: máximo 256 KiB, más un único `previous.log` del mismo límite. Rota antes de superar el tamaño. Cada línea lleva instante ISO UTC, nivel, etiqueta fija y mensaje técnico.
- La API segura solo acepta un evento de un enum, una excepción opcional y un nivel. No acepta mensajes de texto libres. Guarda la clase de la excepción y hasta cuatro frames con nombres técnicos saneados; **no lee `message`, `localizedMessage`, `toString`, causas ni excepciones suprimidas**. Los nombres de fuente que parecen rutas o texto libre se sustituyen.
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

## Verificación hasta esta entrega

- Suite completa sobre la base sin modificar: **584 pruebas, 0 fallos, 0 omitidas**, `BUILD SUCCESSFUL` (54 m 41 s). No se reprodujeron en esta pasada los inestables descritos por la auditoría antigua. [Resultado completo](T-026-validation/baseline.json).
- Parte A: **35 pruebas, 0 fallos, 0 omitidas**, más `assembleDebug`: `BUILD SUCCESSFUL` (6 m 28 s). Incluye los tests nuevos de rotación, formato, privacidad, instantánea, URI/permisos, rechazo de archivos personales y entrada/confirmación manual en la UI; también copia, preferencias, narración y navegación. [Resultado por caso](T-026-validation/part-a.json).
- Comprobación de UI: abrir la hoja no lanza una actividad externa; el selector solo aparece después de pulsar Compartir.

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache --no-build-cache \
  -I /tmp/t026-runner.gradle :app:assembleDebug :app:testDebugUnitTest
```

El init script temporal solo ajusta `Test.systemProperty('user.home', '/tmp/catholic-toolchain/user')` para Robolectric y habilita salida de resultados. Está fuera del repositorio. La suite completa se repetirá tras la higiene, con los mismos casos de base y los nuevos.

## Parte B

Pendiente. Ya se comprobó que los tres kits antiguos y `ThemePalettes` carecen de consumidores externos. Se conservarán `DesignLanguage.APPLE` (Lumen) y `IVE`, que todavía tiene referencias vivas y pruebas de compatibilidad. Antes de eliminar dependencias se ha guardado su árbol resuelto para evitar degradaciones transitivas involuntarias: la base ya usa OkHttp 4.12.0.

No se cambia la versión de producción, R8, reducción de recursos, formato de novenas, `tools/hermes-preview.gradle`, `dist/`, pestañas ni estructura de ViewModels.

## Fuentes y límites

Fuentes: `docs/tasks/T-026.md`, `AGENTS.md`, `docs/AUDIT-2026-11.md`, las búsquedas de referencias en código/recursos/tests, el árbol Gradle y las APIs Java/Android ya disponibles en el proyecto. No hay nuevo contenido doctrinal ni dependencias añadidas en A.

No se ha probado el envío a una aplicación destinataria en un teléfono real. La URI y el selector se verifican con Robolectric; no se ha enviado ningún informe a terceros. El registro cubre los sitios instrumentados; no captura mensajes arbitrarios ni instala un manejador global de excepciones no controladas.
