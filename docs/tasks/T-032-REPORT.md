# T-032 · Avisos litúrgicos y widgets (M12, paso 4)

- **Asignado:** coordinador (agente Opus local) · **Rama:** `CatholicGPT` (sobre `0649437`), sin commit.
- **Estado:** ENTREGADO para revisión.
- **Convivencia:** T-037 trabajaba a la vez en el mismo árbol. Todo lo nuevo va en archivos propios; en los
  compartidos solo hay ediciones pequeñas y localizadas (ver la lista). No se tocaron `HomeScreen.kt`,
  `BeginnerJourneyScreen.kt`, `assets/`, versiones, firma, `tools/hermes-preview.gradle` ni `dist/`.

## Qué se hizo

### 1. `NoticesCatalog` (`data/notices/NoticesCatalog.kt`)

- Patrón de catálogo: `install` (en `SanctaFidesApplication`), `default`, `override` para pruebas.
- Valida al cargar: los 7 tiempos con `start` y de 4 a 6 `weekly`; las 48 celebraciones con `eve` y `day`;
  los 7 recordatorios; ES/EN/PT no vacíos; `action` dentro de la lista de T-035; marcadores `{{…}}` en
  `novena_day` y `plan_day`; ids semanales únicos.
- Si falla, `AppLog.failure(LogEvent.NOTICE_CATALOG, …)` y no hay avisos (nunca medio catálogo). Se vuelve a
  intentar en la siguiente llamada.

### 2. Selección pura (`data/notices/NoticeSelector.kt`)

`NoticeSelector.select(tipo, fecha, textos, contexto, idioma)`, sin reloj ni almacenamiento: la usan el
programador, el receptor, la vista previa y las pruebas.

| Tipo (`NoticeType`) | Grupo | Hora por defecto | Cuándo |
|---|---|---|---|
| `season_start` | Tiempo litúrgico | 8:00 | El primer día de cada tiempo (cambio de `seasonOf`, con las reglas del usuario). |
| `season_weekly` | Tiempo litúrgico | 9:00 | Los domingos del tiempo, rotando por semana. Semana Santa y Triduo duran menos de una semana: allí avanza cada día para que cada texto tenga su día (Triduo: jueves w1, viernes w2, sábado w3; `triduum_w4` no se usa). |
| `feast_eve` | Fiestas | 19:00 | La víspera de cualquiera de las 48 celebraciones. |
| `feast_day` | Fiestas | 8:00 | El día de la celebración. |
| `sunday_mass` | Recordatorios | 18:00 | Los sábados. |
| `holy_day_of_obligation` | Recordatorios | 19:30 | La víspera de los días de precepto **que la persona marque**. |
| `novena_day` | Recordatorios | 9:00 | Cada día, si hay una novena en curso. |
| `plan_day` | Recordatorios | 8:30 | Cada día, si hay un plan en curso y el día no está leído. |
| `rosary_evening` | Recordatorios | 20:00 | Cada día. |
| `examen_night` | Recordatorios | 22:00 | Cada día. |
| `confession_gentle` | Recordatorios | 10:00 | Una vez al mes, el primer sábado. |

- **Celebraciones:** se resuelven con `LiturgicalCalendar.dayInfo(fecha, reglas)`, que ya aplica la precedencia
  y los traslados (José y Anunciación en Semana Santa, Inmaculada en domingo, Epifanía/Ascensión/Corpus según
  las reglas del usuario). Domingo de Ramos y Jueves Santo se reconocen por fecha. Si la liturgia impide la
  fiesta ese día (domingo de Adviento, Semana Santa, octava de Pascua, fiesta de santo en domingo), el motor no
  la devuelve y no hay aviso, ni víspera ni día. Una tabla traduce las 13 claves del motor que se llaman
  distinto (`st_joseph` → `joseph_spouse_of_mary`, etc.).
- **Precepto:** la app no decide qué días lo son. La persona activa el interruptor y marca en una hoja los días
  del canon 1246 que se guardan en su país, con la nota de que lo decide su conferencia episcopal. Sin días
  marcados no hay alarma. Los domingos quedan para `sunday_mass`. Una solemnidad fija solo cuenta en su fecha
  propia: si un traslado rubrical la mueve, no se anuncia como precepto, porque eso también lo decide la
  conferencia.
- **`novena_day`** (nota de T-035-REVIEW): se rellena con el **destinatario**, no con el título.
  `NovenaRecipient` lo extrae del título del catálogo en los tres idiomas. Por ejemplo, «Novena a San José»
  da «la novena de San José»; «Novena al Sagrado Corazón» da «la novena del Sagrado Corazón», y en PT
  «Novena à Imaculada» da «da novena da Imaculada». Además:
  - corta la intención (« para solteros que buscan esposo», " for Expectant Mothers");
  - si el nombre mismo es una intención sensible (fertilidad, maldiciones, elecciones…), el aviso sale sin
    nombre: «Hoy es el día 5 de la novena.»;
  - la novena escrita a mano en Recordatorios nunca muestra su texto libre.

  Comprobado sobre las 262 novenas del catálogo: más del 90 % recibe nombre, y ninguno contiene «novena».

### 3. Programación (`data/notices/NoticeScheduler.kt`, integrada en `ReminderScheduler`)

- Se reutiliza todo lo de los recordatorios:
  - el mismo `ReminderReceiver` (nueva acción `NOTICE_FIRE`);
  - el mismo canal `devotion_reminders`;
  - la misma alarma exacta con alternativa inexacta (`ReminderScheduler.setAlarm`, extraída de `schedule`);
  - el mismo `contentIntent` hacia `MainActivity`;
  - el mismo `ReminderBootReceiver`.
- **Como máximo una alarma pendiente por tipo:** cada `NoticeType` tiene un único `PendingIntent` (mismo
  `requestCode` y acción); volver a programar lo reemplaza. Al apagar un tipo se cancela.
- La alarma solo lleva el tipo y el día previsto. El texto se elige **al sonar**, con el idioma de la app, las
  reglas de traslado y el progreso de ese momento. Por eso un cambio de idioma dentro de la app no necesita
  reprogramar nada.
- **Al sonar:**
  1. Primero encadena la siguiente alarma.
  2. Si la alarma llegó otro día (reposo largo, cambio de reloj), solo reprograma: nada de «Mañana es
     Pentecostés» después de medianoche.
  3. Si el tipo está apagado, o no hay novena ni plan en curso, no publica nada.
- **Reprogramación:**
  - al arrancar la app y en `onResume` (`rescheduleAll` ahora incluye los avisos);
  - tras `BOOT_COMPLETED`, `TIME_SET`, `TIMEZONE_CHANGED` y `MY_PACKAGE_REPLACED`;
  - con el cambio de permiso de alarmas exactas;
  - con `LOCALE_CHANGED`, que se añadió al filtro;
  - y cada vez que se cambia un interruptor, una hora o los días de precepto.
- **Progreso** (`NoticeProgress`): lee lo mismo y con las mismas reglas que «Mi camino». Es decir, Room vía el
  DAO (`novenaProgressOnce`, `novenaDaySnapshot`, `NovenaExtraDaysStore` y `novenasInProgress`),
  `UserPreferencesStore.planProgress` con `plansInProgress`, y los hábitos de hoy. No guarda nada y no hay
  cambio de esquema.
- **Privacidad:** los textos salen de `notices.json` revisado. Confesión dice «Un gesto de vuelta». Nunca se
  muestran intenciones ni texto libre del usuario.

### 4. Al tocar (avisos y widgets)

`contentIntent` lleva el id `notice:<origen>:<DESTINO>`. `MainActivity` lo pasa como `initialReminderId`, igual
que los recordatorios de siempre, y `MainApp` lo enruta con la navegación existente:

- `HOME` usa `go(HOME)`.
- `BIBLE` abre las lecturas del día (pestaña 1 de Leer).
- Los demás usan `openSection`: `ROSARY`, `PRAYERS`, `CONFESSION`, `PLANS`, `SAINTS`, `CATECHISM`, y también
  `HABITS_VIRTUES`, `SPIRITUAL_LIFE` y `MY_PATH` como secciones de «Mi camino».

La víspera abre lo mismo que el día.

### 5. Ajustes (`ui/screens/NoticeSettings.kt`, insertado en `RemindersScreen`)

- **Tres secciones:** Tiempo litúrgico, Fiestas y Recordatorios.
  - Cada fila tiene un interruptor (`AppSwitch`), la hora y la cadencia.
  - Cada grupo es un `AppListGroup`, que ya pone el margen lateral; las filas no añaden otro.
  - Debajo de cada grupo hay una nota breve.
- **Al tocar una fila** se abre una hoja (`AppSheet`) con la rueda de hora existente (`TimeWheel`, ahora
  `internal`) y una vista previa real del próximo aviso (`NotificationPreview`).
- **Todo empieza APAGADO.** Al activar cualquiera se pide `POST_NOTIFICATIONS` (Android 13+), con el mismo
  lanzador de la pantalla.
- **Días de precepto:** al activarlo sin días se abre la lista de días con la nota sobre la conferencia
  episcopal. Con días elegidos, la fila lo resume («2 días elegidos · depende de tu conferencia episcopal»).
- **Preferencias:** van al DataStore de recordatorios. Las claves son `notice_enabled_<id>`,
  `notice_time_<id>` y `notice_obligation_days`, así que viajan solas en el grupo «reminders» de la copia de
  seguridad (`NoticeBackupTest`).

### 6. Widgets (`widget/AppWidgets.kt`, `widget/WidgetContent.kt`)

Son `AppWidgetProvider` clásicos con `RemoteViews`, **sin dependencias nuevas** (no hizo falta Glance).

| Widget | Muestra | Al tocar |
|---|---|---|
| **Día litúrgico** | Fecha, celebración, «tiempo · color» con una barra y un punto del color litúrgico (`setColorFilter`), y «Evangelio: …» con la referencia del leccionario incluido, sin red. | Hoy |
| **Mi camino** | «Hábitos de hoy: 3 de 7» (o «todo hecho»), la novena en curso (con el mismo nombre seguro que los avisos) y el plan en curso; si no hay ninguno, «Sin novena ni plan en curso». | `MY_PATH` |

- **Tema:** colores en `values/` y `values-night/`, así que siguen el modo claro u oscuro del sistema. El texto
  de cuerpo supera 4,5:1.
- **Idioma:** los textos de los widgets siguen el idioma de la app. La etiqueta en el selector de widgets
  sigue el idioma del sistema (`values`, `values-es` y `values-pt`).
- **Actualización:**
  - justo después de medianoche, con una alarma **inexacta** que solo existe mientras hay un widget puesto;
  - con hora, zona o idioma nuevos (receptor de arranque);
  - cada 6 h como respaldo (`updatePeriodMillis`);
  - y cuando cambian los datos, mientras la app está abierta: `AppWidgets.observe` en `MainActivity` escucha
    hábitos, días de novena, planes, novena libre, idioma y reglas, con 1 s de espera. Si no hay widgets, no
    hace nada.
- **Diseño:** el título de la celebración ocupa el espacio que queda, de modo que en un widget bajo se acorta
  el título y no se corta la línea del Evangelio.

## Archivos

- **Nuevos:**
  - `app/src/main/java/com/example/data/notices/`: `NoticesCatalog.kt`, `NoticeSelector.kt`,
    `NovenaRecipient.kt`, `NoticeStore.kt`, `NoticeProgress.kt` y `NoticeScheduler.kt`.
  - `app/src/main/java/com/example/widget/`: `AppWidgets.kt` y `WidgetContent.kt`.
  - `app/src/main/java/com/example/ui/screens/NoticeSettings.kt`.
  - Recursos:
    - `res/layout/widget_liturgical_day.xml` y `widget_my_path.xml`;
    - `res/xml/widget_*_info.xml`;
    - `res/drawable/widget_background.xml`, `widget_color_bar.xml` y `widget_color_dot.xml`;
    - `res/values/widget_colors.xml` y `widget_strings.xml`;
    - `res/values-night/widget_colors.xml`;
    - `res/values-es/widget_strings.xml` y `res/values-pt/widget_strings.xml`.
  - Pruebas: `NoticeSelectorTest`, `NoticeSchedulingTest`, `NoticeUiTest`, `WidgetRemoteViewsTest`,
    `NoticeBackupTest` y `screenshots/NoticesScreenshotTest`.
- **Editados, con cambios pequeños:**
  - `AndroidManifest.xml`: los 3 receptores de widgets y `LOCALE_CHANGED`.
  - `ReminderScheduler.kt`: `setAlarm`, la rama `NOTICE_FIRE`, los avisos en `rescheduleAll` y en el arranque,
    y el refresco de widgets.
  - `RemindersScreen.kt`: un `item` nuevo, y `TimeWheel` y `NotificationPreview` pasan a `internal`.
  - `MainApp.kt`: una rama `notice:` en el `LaunchedEffect(initialReminderId)`.
  - `MainActivity.kt`: el observador de widgets.
  - `SanctaFidesApplication.kt`: una línea para `install`.
  - `AppLog.kt`: `NOTICE_CATALOG`.
- **Copia de seguridad:** la prueba de ida y vuelta va en `NoticeBackupTest`, con el mismo arnés que
  `BackupCoreTest`. Se dejó aparte porque T-037 estaba editando `BackupCoreTest` a la vez.

## Pruebas

- **`NoticeSelectorTest`** (función pura, 10 casos):
  - el primer domingo de Adviento da `ADVENT.start` y `advent_w1`;
  - la víspera de Pentecostés da `pentecost.eve`, que abre `SPIRITUAL_LIFE`;
  - las fijas trasladadas (Anunciación 2024 el 8 de abril, Inmaculada 2024 el 9 de diciembre) y la Epifanía
    en domingo por regla del usuario;
  - San Andrés en el primer domingo de Adviento de 2025 queda en silencio;
  - las 48 celebraciones aparecen en 2026-2030;
  - `novena_day` con nombre en ES/EN/PT, con contracción y sin nombre cuando es sensible;
  - las 262 novenas;
  - sin precepto si no está activado, y nunca en traslados ni domingos;
  - sábados, primer sábado y plan ya leído.
- **`NoticeSchedulingTest`** (Robolectric con `ShadowAlarmManager`):
  - todo apagado por defecto;
  - una alarma por tipo, que no se acumula al reprogramar 3 veces;
  - horas correctas: sábado a las 18:00, Adviento el 29 de noviembre, víspera de la Inmaculada el 7 de
    diciembre;
  - apagar cancela;
  - `BOOT_COMPLETED` reprograma;
  - al sonar se publica el aviso con su `contentIntent` (`notice:sunday_mass:HOME`) y se encadena el sábado
    siguiente;
  - una alarma de otro día o de un tipo apagado no publica nada.
- **`NoticeUiTest`:**
  - activar «Misa del domingo» pide `POST_NOTIFICATIONS` y programa la alarma;
  - «Días de precepto» abre la nota y guarda el día marcado;
  - un aviso tocado (`notice:…:ROSARY`) abre el Rosario, y el widget «Mi camino» abre `my_path_screen`.
- **`WidgetRemoteViewsTest`:**
  - los `RemoteViews` inflados contienen la celebración, «Blanco», el Evangelio y el `PorterDuffColorFilter`
    del color del día (blanco o verde);
  - la descripción accesible;
  - «Mi camino» sin la intención del título;
  - el estado vacío.
- **`NoticeBackupTest`:** interruptores, hora y días de precepto vuelven tras exportar y restaurar.
- **`NoticesScreenshotTest`:** ver Capturas.
- **Resultados:**
  - **Suite completa** (`:app:testDebugUnitTest`): 761 pruebas, 3 omitidas y 1 fallo, que es el conocido
    `ExampleRobolectricTest > read string from context` del script de vista previa. Incluye
    `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `MyPathUiTest`, `SearchUiTest`,
    `BackupCoreTest` y todas las pruebas nuevas.
  - **Después de la suite** solo cambió la vista previa de la hoja, que ahora se calcula sin hilo aparte.
    Se volvieron a pasar `NoticesScreenshotTest` y `NoticeUiTest`, con 0 fallos.
  - **Bloque enfocado**, 73 pruebas: `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`,
    `MyPathUiTest`, `SearchUiTest`, `BackupCoreTest`, `NoticeBackupTest`, `NoticeSelectorTest`,
    `NoticeSchedulingTest`, `WidgetRemoteViewsTest`, `ReminderSchedulingTest`,
    `ReminderReliabilityRegressionTest` y `AppLogTest`. Hubo 1 fallo intermitente:
    `AppNavigationTest[APPLE]` dio «connection pool has been closed». El colector es
    `HomeScreen`/`dao.getAllCompletions().collectAsState`, que ya existía, y la base se cerró durante el
    desmontaje. La clase sola pasa en verde, y también pasó en la suite completa.
  - `:app:assembleDebug`: correcto (`app/build/outputs/apk/debug/app-debug.apk`).

## Capturas (`docs/tasks/T-032-shots/`)

- **Recordatorios en las 4 identidades:**
  - `lumen_light_*`, `sanctuary_dark_*`, `glass_light_*` y `classic_dark_*`;
  - en cada una, `notices_season_feasts` y `notices_reminders` en ES;
  - y `320_130_notices_season` y `320_130_notices_reminders` en PT, a 320 dp y 130 %.
- **Hojas:** `lumen_light_notice_sheet` (hora y vista previa) y `classic_dark_320_130_obligation_sheet`
  (días de precepto con la nota).
- **Widgets:** `widgets_light_es`, `widgets_light_en`, `widgets_dark_es` y `widgets_dark_en`. Cada una muestra
  el día litúrgico de Cristo Rey (blanco, con el Evangelio), una memoria opcional en verde, «Mi camino» con
  novena y plan, y «Mi camino» vacío.
- **Revisión visual:** sin doble margen (los grupos usan el margen de `AppListGroup`), textos completos a
  320 dp y 130 %, e interruptores alineados en las cuatro identidades.

## Lo que no se puede verificar sin un teléfono real

- **Alarmas exactas:** `canScheduleExactAlarms` y el permiso de Android 12+ (`SCHEDULE_EXACT_ALARM`). Si el
  usuario lo niega, se usa `setAndAllowWhileIdle` y el aviso puede retrasarse; Robolectric no simula ese
  retraso.
- **Fabricantes:** las restricciones de batería de cada marca (Xiaomi, Huawei, Samsung…) pueden retrasar o
  suprimir alarmas y la recepción de `BOOT_COMPLETED`.
- **El lanzador real:** cómo dibuja los widgets (radio, recorte, tamaño de celdas, `previewLayout` en el
  selector) y si entrega `APPWIDGET_UPDATE` tras reiniciar. Las capturas de widgets son `RemoteViews` inflados
  en Robolectric sobre un fondo neutro, no una pantalla de inicio.
- **El sistema:** el diálogo de permiso de notificaciones y el aspecto de la notificación.
- **Robolectric** no reproduce el reposo (Doze) ni el retraso de la alarma inexacta de medianoche.
