# T-042 — Narración por el servicio de audio en más pantallas

Estado: implementado y verificado con pruebas Robolectric y capturas. No se ha verificado en un teléfono real (ver el último apartado).

## Resumen

Todas las pantallas que leen en voz alta ya pasaban por `speakSingle` / `speakDialogue` de `LiturgicalTtsManager`, que es lo que inicia `NarrationService` (notificación en primer plano, foco de audio, temporizador de sueño). La auditoría encontró cuatro huecos reales, no una ruta paralela:

1. **Ninguna pantalla ponía título.** `narrationTitle` solo lo asignaba un test, así que la notificación y la pantalla de bloqueo mostraban siempre el genérico «Narración».
2. **Los lectores de doctrina creaban un segundo reproductor.** `ReaderVoice` hacía `LiturgicalTtsManager(context)` propio (segundo motor TTS). Además, cada manager se registra como `active` al crearse y se desregistra al liberarse: al salir de un lector se ponía `active = null` aunque el reproductor principal siguiera narrando, y los botones de la notificación dejaban de funcionar (el servicio lo tomaba por un arranque obsoleto y se cerraba). Con dos managers vivos, los botones también podían apuntar al equivocado.
3. **Cinco pantallas no paraban al salir:** Oración de la noche, Oraciones/Novenas, Vida espiritual, Evangelio de Inicio y Estudio de la Escritura. Con el servicio en primer plano, la voz seguía sonando fuera de la pantalla.
4. **Faltaba «Escuchar»** en el detalle del santo y en el cuerpo del detalle de devoción. En las 101 preguntas ya existía (`catholic_qa_listen_button`).

## Punto de entrada único

No se ha creado un reproductor nuevo. Los dos métodos existentes ganan un parámetro opcional `title`:

```kotlin
fun speakSingle(text: String, isFemaleVoice: Boolean = false, title: String? = null)
fun speakDialogue(leaderText: String, responseText: String, title: String? = null)
```

- Fijan `narrationTitle` en cada llamada (y la limpian si no se pasa), de modo que un título nunca sobrevive al contenido que nombra. `stop()` también lo borra. `resume()` repite la petición con su mismo título.
- `beginSession` marca a este manager como `active`: los botones de la notificación hablan siempre con quien está narrando.
- `NarrationService` publica también `MediaMetadata` (título y fragmento) en la `MediaSession`, que es lo que leen la pantalla de bloqueo, Bluetooth y el coche. El título de reserva se unifica en una función.
- `ReaderVoice` usa ahora el manager compartido (nuevo parámetro `ttsManager` en `DoctrineAndHistoryScreen`, que `MainApp` rellena en las 7 rutas). Solo si no se le da uno (previews y tests aislados) crea el suyo, como antes. Con el compartido, al salir de la pantalla hace `stop()` en vez de `release()`.
- `ReaderVoice.isReading(texto)` compara con `currentlySpeaking`, de modo que cada botón muestra «Detener» solo para lo que él inició (necesario ahora que el detalle de devoción tiene dos botones).

## Auditoría (pantalla → antes → después)

Para todas: antes = servicio, foco y temporizador ya funcionaban vía el manager; no se anotan de nuevo salvo si cambian.

| Pantalla | Antes | Después |
|---|---|---|
| Biblia (capítulo, versículo, lecturas de la Misa; `SpeechQueue`) | Servicio sí. Sin título. Paraba al salir (`DisposableEffect`) | Título «Génesis 1», cita del versículo («Lecturas de la Misa» para el misal). Se añade `title` a `SpeechQueue.play`. Sin otros cambios |
| Rosario y Coronilla (`RosaryScreen`) | Servicio sí. Sin título. Paraba al salir | Título «Santo Rosario · {sección}» / «Coronilla de la Divina Misericordia · {sección}». Voces líder/respuesta y velocidad intactas |
| Oración de la noche (Completas, Examen, Tradicional, antífona mariana) | Servicio sí. Sin título. **No paraba** al cambiar de oficio ni al salir | Título «Completas», «{oficio} · {paso}», título de la antífona. Para al cambiar de oficio y al salir |
| Oraciones (lista y detalle) | Servicio sí. Sin título. **No paraba** al abrir otra página ni al salir | Título = nombre de la oración. Para al cambiar de ruta y al salir |
| Novenas (día) | Servicio sí. Sin título. **No paraba** | Título «Día N · {novena}». Para al cambiar de día/ruta y al salir |
| Coronilla de la Misericordia (en Oraciones) | Servicio sí. Sin título. **No paraba** | Título de la coronilla. Para al salir |
| Vida espiritual (Vía Crucis, canto gregoriano) | Servicio sí. Sin título. **No paraba** al cambiar de página | Título «Vía Crucis · {estación}» / título del canto. Para al cambiar de página y al salir |
| Hábitos / En la tentación (Ave María) | Servicio sí. Sin título. Paraba al salir | Título «Ave María». Sin otros cambios |
| Inicio (Evangelio del día) | Servicio sí. Sin título. **No paraba** al salir | Título «Evangelio de hoy». Para al salir si lo inició Inicio |
| Estudio de la Escritura (lección) | Servicio sí. Sin título. **No paraba** | Título = lección. Para al salir de la lección |
| Lectores de doctrina: Didajé, órdenes, oficios, 101 preguntas (`ReaderActionRow`) | **Segundo manager y segundo motor TTS** (`ReaderVoice`). Podía robar y dejar sin efecto los botones de la notificación. Sin título | Manager compartido. Título = capítulo / orden / oficio / «Pregunta N». Para al salir (`stop`). Botón «Detener» solo en el que se pulsó |
| Detalle de devoción — oración (`devotion_prayer_listen`) | Igual que los lectores (segundo manager) | Manager compartido, título = título de la oración |
| Detalle de devoción — historia | **Sin «Escuchar»** | Nuevo `devotion_listen_button`: lee el título, la historia y el significado |
| Detalle del santo (`SaintsScreen`) | **Sin «Escuchar»** | Nuevo `saint_listen_button`: lee el nombre y luego la biografía. Para al volver o salir |
| Respuesta de las 101 preguntas | Ya tenía `catholic_qa_listen_button` (con segundo manager) | Se reutiliza ese tag (no se añade `question_listen_button`, por cubrirlo ya el existente). Manager compartido y título «Pregunta N» |
| Vista previa de voces (`GuiCustomizerModal`) | Sin servicio a propósito (`withService = false`) | Sin cambios; ahora limpia el título |

## Archivos modificados

Código principal:
- `data/audio/LiturgicalTtsManager.kt`, `data/audio/NarrationService.kt`
- `ui/MainApp.kt`
- `ui/screens/`: `BibleScreen`, `RosaryScreen`, `NightPrayersScreen`, `PrayersAndNovenasScreen`, `SpiritualLifeScreen`, `HabitsAndVirtuesScreen`, `HomeScreen`, `ScriptureStudyScreen`, `SaintsScreen`, `DevotionsExplorerScreen`, `DoctrineParts`, `DoctrineAndHistoryScreen`, `DoctrineQuestions`, `DoctrineOrders`, `DoctrineMoral`

Pruebas:
- `NarrationPlaybackTest` (ampliado; el test del título usa ahora `speakSingle(..., title = ...)`)
- `NarrationEntryPointsTest` (nuevo)
- `NarrationTestSupport` (nuevo)
- `screenshots/NarrationScreenshotTest` (nuevo)

Capturas: `docs/tasks/T-042-shots/`. No se tocaron `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `.github/`, `dist/` ni el esquema Room.

## Textos de interfaz

«Escuchar» / «Listen» / «Ouvir» y «Detener» / «Stop» / «Parar» salen de los wrappers existentes (`AppSecondaryCapsule`). Los títulos nuevos se resuelven en ES/EN/PT en cada pantalla. No se han añadido recursos nuevos.

## Pruebas

Nuevas o ampliadas:
- `NarrationEntryPointsTest` (6):
  - el santo inicia `NarrationService`, el título de la notificación es el del santo y el texto empieza por el nombre y termina con la biografía;
  - salir del santo para la voz y el servicio;
  - la respuesta de una pregunta, una orden y el detalle de devoción conmutan Escuchar/Detener y arrancan y paran el servicio;
  - la devoción conserva el control de la notificación en el manager compartido;
  - el Rosario inicia el servicio con título «Holy Rosary · …» y lo para.
- `NarrationPlaybackTest` (10, 4 nuevas):
  - título ligado al contenido (no se hereda);
  - pérdida de foco (también transitoria con ducking): pausa, marca `interrupted`, la notificación pasa a «Continuar» con el mismo título y `resume()` lo recupera;
  - pausa desde pantalla de bloqueo seguida de parada: se detiene el servicio;
  - el último manager que narra conserva `active`.

Resultados: ver el apartado siguiente.

## Cómo reproducir

```sh
scratchpad/gw.sh :app:testDebugUnitTest --tests 'com.example.NarrationEntryPointsTest' --tests 'com.example.NarrationPlaybackTest'
scratchpad/gw.sh :app:testDebugUnitTest --tests 'com.example.screenshots.NarrationScreenshotTest'
```

## Capturas (`docs/tasks/T-042-shots/`)

Detalle de San Francisco de Sales con el botón Escuchar, en reposo y narrando:
- Lumen claro: `lumen_light_saint_idle.png`, `lumen_light_saint_playing.png`
- Sanctuary oscuro: `sanctuary_dark_saint_idle.png`, `sanctuary_dark_saint_playing.png`
- Cristal claro, 320 dp y 130 % de fuente (PT): `glass_light_saint_320_130_idle.png`, `glass_light_saint_320_130_playing.png`
- Tradicional oscuro, 320 dp y 130 % de fuente (PT): `classic_dark_saint_320_130_idle.png`, `classic_dark_saint_320_130_playing.png`

Revisadas a ojo: el botón queda bajo «Todos los santos», no tapa texto ni ilustración, cabe entero a 320 dp con 130 % y cambia a «Detener» / «Parar».

## Lo que no se puede verificar sin un teléfono real

- Pantalla de bloqueo real y su aspecto (Android 13+ muestra la sesión multimedia; aquí solo se comprueba la notificación y el estado de la sesión en Robolectric).
- Botones Bluetooth, auriculares y coche (AVRCP): llegan por `MediaSession`, que Robolectric no simula.
- Llamadas entrantes y otras apps con foco real: se simula con `handleFocusChange`, no con el `AudioManager` del sistema.
- Motores TTS de fabricantes (Samsung, Google, Xiaomi…): Robolectric usa un motor de pruebas; la latencia, la lista de voces y el comportamiento de `stop()` pueden variar.
- Políticas de ahorro de batería de fabricantes y el permiso de notificaciones en Android 13+.
- Que la voz siga sonando con la pantalla apagada y el temporizador de sueño con tiempos reales (en pruebas solo se avanza el reloj de Robolectric).
