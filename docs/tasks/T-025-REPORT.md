# T-025 · Informe de Codex

Rama `ext/T-025`, desde `origin/CatholicGPT` (`697eb6d`). Entrega escalonada: primero núcleo y pruebas; después interfaz, navegación y capturas. Este informe se actualiza con cada entrega.

## Formato y cifrado

- Un archivo `.chbackup`, binario, sin SQLite, ZIP ni archivos temporales de texto claro externos.
- Cabecera de 44 bytes: ASCII `CHBACKUP` (8), versión de formato (entero de 32 bits, big endian), iteraciones (32 bits), sal aleatoria (16 bytes), IV aleatorio (12 bytes). Sigue el JSON cifrado y su etiqueta GCM de 16 bytes.
- `formatVersion: 1` también dentro del JSON autenticado; `databaseVersion` identifica el esquema de origen. La versión actual exporta esquema 5 después de las migraciones normales. El formato no copia SQL ni cambia el esquema; los bitmasks antiguos de novena se convierten en días completos, incluidos días mayores de 31.
- AES-256-GCM, etiqueta de 128 bits. La cabecera completa se autentica como AAD. Clave PBKDF2-HMAC-SHA256, **310 000 iteraciones**, sal e IV nuevos por copia. Se rechazan factores fuera de 210 000–1 000 000 antes de derivar, así como versiones desconocidas o archivos mayores de 32 MiB de contenido cifrado más cabecera/etiqueta.
- Se usa JCA; para proveedores de Android 24–25 sin `PBKDF2WithHmacSHA256` se incluye la construcción estándar con `HmacSHA256`, contrastada con vector publicado y JCA, también con contraseña Unicode. No se añade una dependencia criptográfica.
- La contraseña no se persiste ni se registra. Los `CharArray` de la API se borran al terminar. Se borran también los buffers de clave y texto claro; las cadenas que Kotlin/JSON crea en memoria no ofrecen borrado garantizado. La interfaz debe limpiar sus campos al iniciar/cancelar; no se promete protección frente a un dispositivo comprometido.
- Autenticación y validación terminan antes de cualquier cambio. Contraseña incorrecta y etiqueta dañada producen un mismo error comprensible; una versión no compatible o una estructura inválida se rechazan por separado. No se informa éxito antes de terminar las escrituras.

## Datos incluidos

Las nueve tablas solicitadas: `journal_entries`, `novena_progress`, `novena_day_completions`, `memorized_verses`, `user_prayer_stats`, `spiritual_habits`, `habit_completion_logs`, `sin_overcome_logs`, `scripture_study_progress`.

Todos los valores persistidos de UserPreferencesStore, incluidos idioma, identidad, accesibilidad, fuente, escala, voz, calendario, ediciones bíblicas y progreso/última pregunta. Se conservan tipos primitivos explícitos (sin pasar un Long por Double).

Además se detectaron datos personales portátiles en otros almacenes y se incluyen: conteos de tentaciones y caídas; horarios y activación de recordatorios; posición de lectura; ajustes del lector bíblico; último párrafo del Catecismo; itinerario inicial; Rosario/Coronilla pendiente. Los días largos de novenas todavía no migrados se leen sin alterar el origen y se incorporan a las filas canónicas.

Se excluyen `liturgical_days`, `bible_downloads`, caché del versículo, archivos bíblicos y marcadores internos de migración. `app_preferences` es la tabla heredada: la fuente de ajustes exportada es DataStore. Los IDs de eventos de Google Calendar pertenecen al teléfono/cuenta de origen y no se trasladan ni se sobrescriben en el destino. No se añaden permisos de almacenamiento.

## Reemplazar y combinar

- **Reemplazar:** borra y repone solo las nueve tablas personales en una transacción; restaura los ajustes presentes en la copia. Las cachés y los demás marcadores locales se conservan.
- **Combinar:** unión de días por novena/día y prácticas por hábito/fecha; conserva los IDs locales. Las entradas nuevas con IDs numéricos coincidentes reciben un ID local nuevo. El diario se compara por fecha y contenido, reuniendo el estado de respuesta y las notas; los registros distintos se conservan. Las notas de estudio e intenciones distintas se conservan juntas, sin volver a añadirlas al repetir la misma importación.
- Las estadísticas acumuladas usan máximos, no sumas de una copia consigo misma; las rachas se toman del registro más reciente y se conserva el mejor histórico. Los hábitos personales de igual ID pero contenido diferente se remapean de forma determinista con sus registros.
- Se unen las preguntas visitadas y los máximos del itinerario. Los ajustes de la copia se aplican también en Combinar; una sesión de oración ya pendiente en el destino se conserva. Esta elección debe indicarse en el resumen de la interfaz.

## Recuperación y límites entre almacenes

Room y DataStore no comparten un commit. Antes de escribir se guarda con `AtomicFile` un pequeño registro privado de recuperación con los ajustes anteriores y un token aleatorio; no contiene textos de diario ni contraseñas y nunca se exporta. La transacción Room restaura filas, escribe ajustes y confirma ese token en la tabla interna ya existente. Si hay un fallo, Room revierte y se reponen los ajustes anteriores. Si el proceso se interrumpe, el registro permite comprobar al inicio si Room confirmó y reponer los ajustes solo cuando corresponde. La conexión al arranque se entrega con la interfaz; no debe permitirse una nueva restauración antes de terminar esa recuperación.

Las operaciones se coordinan con `PersonalDataCommands`; los errores de la copia no dejan una tarea con el contenido descifrado retenida en el registro de reintentos de escrituras. El esquema, las entidades y la versión de Room no cambian; se añade únicamente un DAO de consultas.

## Pruebas del núcleo

Robolectric API 34, Room en memoria y DataStores temporales separados. Cobertura: ida y vuelta de las nueve tablas y preferencias; contraseña errónea; archivo manipulado/truncado; reemplazo; combinación repetida; colisiones de IDs; hábitos personalizados; notas y respuestas actualizadas; rollback si falla una preferencia; recuperación simulada entre commits; días de novena superiores a 31; cabecera autenticada; límites de trabajo criptográfico y compatibilidad de PBKDF2.

La compilación de Kotlin pasa. **BackupCoreTest: 11 pruebas pasan, 0 fallos, 0 errores**. Incluye actualización de una entrada contestada y rechazo de tipos de preferencias incompatibles. Resultado: BUILD SUCCESSFUL.

Para este contenedor, Robolectric necesita un directorio `user.home` escribible. Se usa un init script temporal, fuera del repositorio:

```groovy
allprojects {
    tasks.withType(Test).configureEach {
        systemProperty 'user.home', '/tmp/catholic-toolchain/user'
        testLogging { events 'passed', 'failed', 'skipped'; exceptionFormat 'full' }
    }
}
```

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache --no-build-cache \
  -I /tmp/t025-runner.gradle :app:testDebugUnitTest --tests com.example.BackupCoreTest
```

La primera ejecución sin esa configuración falló al crear el bloqueo de descarga de Robolectric en `/home/agent`; no fue un fallo de importación. El ajuste temporal evita escribir fuera de `/tmp` y `/workspace`.

## Interfaz y capturas

Pendientes de la segunda entrega: entrada en Ajustes; selectores SAF; contraseña oculta; resumen previo; elección Combinar/Reemplazar y confirmación; mensajes ES/EN/PT; tags; recuperación al arranque; pruebas de navegación y capturas en `docs/tasks/T-025-shots/`.

## Pruebas en teléfono

No se ha probado todavía en un teléfono real, ni con proveedores documentales externos o Google Drive, ni con un apagado físico durante el commit. La recuperación se simula con los registros privados y los marcadores reales en Room. La disponibilidad criptográfica en Android 24–25 se cubre por vectores del fallback, no por una prueba física en esos sistemas.
