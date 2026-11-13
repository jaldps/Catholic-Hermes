# Catholic Hermes — CatholicGPT

Versión actual: **1.6**, con Lumen y Sanctuary, correcciones editoriales y un Estudio más desarrollado: cuatro cursos y veinte lecciones, con un nuevo recorrido completo para aprender a leer la Biblia.

- [Descargar el APK actual para Android](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-quality-preview.apk).
- [Instalación y actualización](dist/README.md).
- [Cambios, verificaciones y límites de la versión 1.6](docs/quality/DELIVERY-1.6.md).
- [Plan escalonado de contenido](docs/quality/CONTENT-DELIVERY-PLAN.md).
- [Contenido y experiencia de Estudio](docs/quality/STUDY-DELIVERY.md).
- [Revisión pastoral y prioridades de contenido](docs/quality/PASTORAL-REVIEW-2.md).
- [Integridad y procedencia del Catecismo](docs/content/CONTENT-INTEGRITY.md).

El siguiente briefing describe una iteración anterior de noviembre de 2026. Sus nombres de temas, paquetes, pantallas y resultados de pruebas son históricos; la documentación enlazada arriba describe la entrega actual.

## Briefing histórico (rama `material-aaa-design`)

Guía exhaustiva de **cada cosa** que hace la app y **cómo funciona por dentro**: qué datos usa, de dónde vienen, cómo se calculan, qué se guarda y cómo se ve. Última actualización: 2026-11-07.

---

## 1. Identidades visuales (temas)

La app tiene 3 identidades, seleccionables en Ajustes → Apariencia:

- **Cupertino** (defecto): sistema neutro, superficies opacas, radio 12dp en todo.
- **Ive** (referencia iOS): papel cálido `#F7F3ED`, tarjetas frosted translúcidas (alpha ~90%) con sombra 6dp + borde 1dp `hairline` y radio 20dp; títulos serenos peso 400 (`screenTitle`), CTA negra pill, acento verde litúrgico; heroes fotográficos licuados (fade foto→papel + scrim 160dp) con título `BottomStart` y gutters 20dp. Todo botón en 12dp recto-ligeramente-redondeado. La barra inferior flotante propia se mantiene en todas las identidades (nunca se cambia).
- **Translucency**: superficies frosted iOS7+ (heredó el tema Translucency tras eliminar Material Design).

Todos los heroes fotográficos de todas las pantallas miden **220dp**, con el **mismo fade** (banda completa transparente→canvas + scrim inferior 160dp) y título en `BottomStart` con gutter horizontal + 24dp vertical, peso 400. Los títulos de sección comienzan a la **misma altura** en Inicio, Biblia, Rosario, Oraciones, Hábitos, Explorar, Doctrina, Catecismo, etc.

---

## 2. Inicio (Hoy)

- **Hero con fecha**: foto de la cúpula de San Pedro con fade; título "Hoy" + fecha localizada (ES/EN/PT) en la zona de fade para legibilidad. Barra de estado y navbar flotante respetados (scaffold global +96dp, pantallas 16dp de respiro — sin doble inset).
- **Versículo del día dinámico**: ya no es estático. `DailyVerseStore` rota por día del año entre 36 referencias canónicas, fetchea el texto de bible-api.com con caché por fecha en DataStore, y resuelve pasajes de la Biblia bundleada trilingüe si no hay red.
- **Banner litúrgico**: foto del día (Sol naciente) recortada al centro, comillas normalizadas a « » en ES/PT (inmaculadas en EN vía `normalizeExcerptQuotes()`).
- **Misa de hoy**: tarjeta con primera lectura (Fil 3) y Evangelio (Lc 16) + botón "Leer Misa" que abre el Misal completo; "Seguir leyendo" reanuda el libro donde lo dejaste.
- **Pregunta del día**: card con una de las 101 preguntas; "Leer respuesta" abre **esa pregunta específica** (deep link por índice `questionsInitialIndex`, no la lista general).
- **Oración diaria por hora** (mantiene el estilo de lista, cambia el contenido según la hora): 05h Oraciones de la mañana, 06h Laudes, 12h Ángelus, 15h Coronilla, 18h Ángelus, 20h+ Oraciones tradicionales de la noche; muestra la oración actual + la siguiente.
- **Laudes / Ángelus tiles**: clic en "Laudes" abre la oración de Laudes (salmo 95, Benedictus, etc.); clic en "El Ángelus" abre el Ángelus. Recordatorios: Ángelus 12:00 (activo por defecto), Ángelus 18:00 (apagado por defecto), Coronilla 15:00 (activa por defecto).
- **Fidelidad de hoy**: anillo con X/12 hábitos del día; **se resetea cada día** (se lee por fecha `yyyy-MM-dd`); calendario mensual de fidelidad en la tarjeta (`fidelity_calendar`, total del mes que se reinicia al cambiar de mes).
- **Santo del día**: `DailySaintsFetcher` con parser robusto (`NAV_HEADINGS` multilingüe ES/EN/PT, fuente Vatican News trilingüe + `daily-saints` API 537 santos con foto Wikipedia). Cita/foto por fecha; no repite texto.

---

## 3. Biblia

- **Hero 220dp** uniforme con título "Sagrada Biblia" en la zona de fade.
- Tabs **Biblia / Misa / Estudio** con chips estilo Biblia; búsqueda con foco visible.
- **Continuar leyendo** (Deuteronomio 1…) con flecha; filtros "Todos los libros" y "Versiones".
- Lista por testamento (Pentateuco: Génesis 50 caps, Éxodo 40…), filas grouped con chevron `textSecondary` (contraste ≥4.5:1).
- **Lector**: serif (EB Garamond vía `styles.scripture`), progreso guardado, Prev/Next por capítulo.
- Deep links desde las 101 Preguntas: las referencias (`FoundationRefParser`, ES/EN/PT, acentos-insensibles, rangos CCC) abren el capítulo exacto.

---

## 4. Rosario / Coronilla (Divina Misericordia)

- **Layout compacto sin scroll** (como la Coronilla): todo visible en una pantalla — hero 180dp compacto, tabs, rail, controles, texto.
- **Hero arriba estilo Home** (fuera de los Misterios): foto de perlas, título "Santo Rosario"/"Coronilla" + "Paso 1 de 85" + **sin CTA en el hero** (la CTA de voz vive en la guía, no duplicada).
- **Misterios dropdown** reubicado **debajo del texto de oración e Instrucciones**, estilo picker "Voces" (`MenuPickerRow` en `AppListGroup`): nombre del misterio + días recomendados + "· hoy".
- **Rail de cuentas grande**: beads proporcionales (radio loop 64dp compacto, bead 3.0–6.5dp, cruz 22dp), bead actual con halo/acento, gaps 16–20dp sin solape.
- **Controles integrados una sola vez** en la tarjeta de guía (`TransportCluster`: Anterior / ▶ / Siguiente + dots de progreso). **Botones duplicados debajo del texto ELIMINADOS**.
- **"Rezar en voz alta" persistente**: flag `narrationActive` que no se pierde al avanzar cuentas/misterios/pestañas; solo se para con Pausa. El botón vive integrado bajo el rosario/coronilla a ancho completo; Anterior/Siguiente van en la misma fila 50/50 encima.
- Texto de oración serif; meditación y "Inicio" con contraste correcto (`textSecondary` 7.02:1 en Ive).

---

## 5. Oraciones y Novenas

- **Header foto de velas 220dp** (centrada, focal correcta) con fade uniforme; título sobre scrim 0.92 sin ghosting.
- **Grid de categorías 3 columnas** (`PrayerCategoryTiles`): Básicas, Intenciones, Familia, Sanación, Acción de gracias, Intercesión — tiles frosted 18dp/104dp. Tiles seleccionados en mint con tinta verde oscura (contraste correcto).
- **"Padre Nuestro" y "Cómo orar"** con affordance unificada: chevron 22dp `textSecondary` en ambas (sin "Ver más" verde desparejo); pesos de título iguales.
- **Novenas — diseño original restaurado**: **2 tarjetas por línea** (`chunked(2)`, 312dp, placa 108dp), cada novena con **su propio arte** (`AsyncImage` sobre `imageUrl` de cada novena, fallback foto bundle solo si falta), título + patrón + "9 días"/progreso + barra; al tocarla abre la novena. Sin foto genérica de velas.
- **Intenciones**: un solo dropdown "Intenciones" (`MenuPickerRow`, patrón Misterios) con **icono por tipo** (imposibles, bendiciones, amor, dinero, consuelo, salud, familia, paz…) en vez de botones individuales.
- **Detalle de novena**: undo del día ("Deshacer día"), "Reiniciar novena" borra progreso, placas 108dp, tarjeta 288→312dp.

---

## 6. Hábitos y Virtudes

- **Hero vela 220dp arriba** en ambas pestañas (estándar Home) + título sobre scrim 160dp. Sin doble título.
- **12 hábitos diarios exactos** en 3 grupos:
  - *Mañana*: Ofrecimiento del día (click → Cómo orar), Orações matutinas (Pai Nosso/Ave/Glória), Oração mental e silêncio 15–30 min, Leitura espiritual (click → lectura del día de la Misa).
  - *Durante el día*: Ângelus 6h/12h/18h (click → Ángelus), Coronilha 15:00 (click → Coronilla), Jaculatórias (click → verlas), Santa Missa/Misa del día (click → lecturas), Bênção das refeições.
  - *Noche*: Exame de consciência (click → Examen), Terço (click → Rosario), Orações tradicionais (click → tab Tradicional).
  Cada hábito: círculo = check del día (Room `HabitCompletionLogEntity`), texto = navega al destino. Gramática corregida ("1 día"/"N días"), hora única (sin duplicar `12:00`).
- **Control de cumplimiento**: anillo X/12 hoy + tarjeta mensual (hoy X/Y, mes X/Z, días activos, puntitos por día) con datos por fecha (reset diario, acumulativo mensual).
- **Recordatorios de oración**: botón en Hábitos → pantalla de Recordatorios con banner de estado (permisos `POST_NOTIFICATIONS` + `canScheduleExactAlarms()`, accesos directos a ajustes). **FC corregido** (causa raíz: key de LazyColumn duplicada `reminders_header` → `reminders_list_header`; `ReminderStore` ahora cae a defaults ante cualquier fallo de DataStore en vez de relanzar).
- **Victoria sobre la tentación**: diálogo **520dp** ancho (antes 460dp estrecho), texto bodyLarge, botones full-width; stats **X hoy / X esta semana / X este mes** tras cada victoria; botón **"Caí en una tentación"** con contador separado. Cada victoria/c caída se escribe en `TemptationStore` (DataStore por fecha `yyyy-MM-dd`); **la cuenta diaria se resetea cada día** (día nuevo = 0) y el calendario mensual muestra el acumulativo por día; al cambiar de mes el total se reinicia.
- **Calendario de tentaciones**: grid mensual con `dayCount·cumulative`, días de victoria en `accentMuted`, hoy en dorado, flechas prev/next de mes, tabs Vencí/Caí. Trilingüe.

---

## 7. Oraciones de la noche

- **Tab Tradicional** (tercera, junto a Completas y Examen ignaciano): 9 pasos — 1 Persignarse, 2 Padre Nuestro, 3 Ave María, 4 Gloria, 5 Ángel de la Guarda (texto líder completo; el `responseText` bundleado era un fragmento suelto), 6 Bendita sea tu pureza, 7 Signarse, 8 "Oh María sin pecado concebida, ruega por nosotros que recurrimos a ti", 9 "Gracias te doy buen Señor y alabo tu gran poder…" (inline con EN/PT). Navegación Prev/Next 50/50 48dp, progreso, TTS por paso, Done/Undo. Todo trilingüe.
- **Banner de montaña eliminado**: el header de velas + las **dos filas de tabs apiladas** (selector de tipo + selector de oficina nocturna Completas/Examen/Tradicional), sin banner intermedio que ocupaba espacio.
- Completas y Examen ignaciano con su TTS y progreso existentes.

---

## 8. Recordatorios (sistema completo)

- **Catálogo** (`ReminderCatalog`): Ángelus mañana/mediodía/noche (6h/12h/18h), Coronilla 15:00, Oración de la mañana, Oración de la noche, Lectura diaria. **Defaults activos**: Ángelus 12:00 + Coronilla 15:00; Ángelus 18:00 apagado por defecto.
- **Programación**: `AlarmManager` alarmas exactas + `ReminderReceiver` (con `EXTRA_LANG` para el idioma correcto — antes leía SharedPreferences inexistente y siempre sonaba en ES) + `ReminderBootReceiver` (reprograma al reiniciar leyendo `UserPreferencesStore` en coroutine IO). Permisos: `POST_NOTIFICATIONS`, `SCHEDULE_EXACT_ALARM`, `RECEIVE_BOOT_COMPLETED`, `READ/WRITE_CALENDAR`.
- **Google Calendar Sync** (`GoogleCalendarSync`, CalendarContract sin API key): crea eventos para novenas/recordatorios en el calendario de Google.
- **Pantalla**: switches por recordatorio (`reminder_switch_<id>`), hora (`reminder_time_<id>`), sección de novenas (`novena_section/start/finish`), sincronización (`gcal_<id>`), banner de estado de permisos (`reminders_status`).

---

## 9. Doctrina e Historia

- **Subsecciones separadas en Más → Estudio** (ya no tabs apilados): Catecismo, Doctrina, María y Jesús, Historia, 101 Preguntas, Órdenes y Oficios, Noticias.
- **Cada subsección con hero fotográfico distintivo 220dp**: Doctrina → cúpula de catedral, María y Jesús → estatua de María, Historia → vitral, 101 Preguntas → amanecer, Órdenes → velas (título en `BottomStart` con fade canónico; sin título duplicado sobre la foto).
- **María y Jesús**: devoción (Nuestra Señora / Cristo) y región consolidados en **dropdowns con iconos** (Favorite/LightMode; iconos por región) en vez de botones individuales, mismo lenguaje que Intenciones/Misterios.
- **Didaché**: 16 capítulos + intro ES/EN/PT (fuente clerus.org ES `es/fcn.htm`, EN `en/du1.htm`), reader por capítulo con progreso Prev/Next, testTags `didache_chapter_N`.
- **Noticias**: carga centrada (48dp, `Arrangement.Center`), eyebrow `textSecondary`, sin wells saturados.
- **Órdenes y Oficios — 37 emblemas oficiales**: escudos/logos oficiales de cada orden religiosa (Benedictinos, Franciscanos, Dominicos, Jesuitas, Carmelitas, etc.) descargados de Wikipedia/Wikimedia (29) y sitios oficiales (8), en `app/src/main/assets/orders/<id>.png` (≤256px, verificados PNG + revisión visual; se rechazaron retratos/fotos por error). Se muestran **40dp en cada fila de la lista** y **56dp en el título del detalle** (`order_emblem_detail`), con `ContentScale.Fit` (logotipos anchos nunca recortados). Captions trilingües intactos.

---

## 10. 101 Preguntas

- **101 preguntas** (`CatholicQuestionsData`, batches Q1–34/35–67/68–101) con respuestas reescritas: estructura directa + ejemplo cotidiano + objeción protestante/ateísta más fuerte con respuesta honesta. Fixes aplicados: Q12 (sin ref. Lucas irrelevante), Q42 (+Gal 2,11-14), Q46, Q94 (+Rom 16 + Hech 18).
- **Deep links de fundamentos** (`FoundationRefParser` ES/EN/PT, acentos-insensibles): chips/filas "Referencias — toca para profundizar" que abren Biblia/Catecismo en el pasaje o §CCC exacto.
- **Pregunta del día** abre la respuesta específica (ver §2).

---

## 11. Catecismo

- **Hero vitral 220dp** con título en fade canónico.
- **Búsqueda** estilo API: localiza artículos por texto (ES/EN/PT) y abre el §CCC exacto (deep links desde Preguntas).
- Filas sin iconos saturados (se eliminaron wells `MenuBook` de color en filas fuente); `InBriefNote` en `textSecondary`.
- **Compendio** (`CatechismCompendiumData` + 21 archivos `CatechismFullDataXX`) con contenido del Compendio del Catecismo.

---

## 12. Explorar (Más)

- **Un solo título** ("Explorar", `AppScreenTitle`) — eliminado el texto "Explorar" duplicado en negro sobre la foto y el strip de foto-ventana duplicado (la parte de arriba ya identifica la sección).
- **Iconos monocromáticos distintos por sección** (fuera wells rainbow de AppleKit y el glifo templo repetido): Catecismo→MenuBook, Doctrina→School, María y Jesús→Favorite, Historia→History, Preguntas→Help, Órdenes→Groups, Noticias→Newspaper, Hábitos→Checklist, Vida interior→Spa, Parroquias→Church, Peregrinación→Place — todos Outline 20dp en un solo tinte `textSecondary`, chevron outline.
- **Strips fotográficos por sección con título único** (`AppSectionHeader`), sin título duplicado sobre la foto.

---

## 13. Datos y contenidos

- **40 oraciones** (`PrayerAndNovenaData`): Padre Nuestro, Ave María, Gloria, Ángel de la Guarda, Laudes (nueva: versículo inicial, himno, Salmo 95, Lc 1:68-70, Benedictus, colecta), Bendita sea tu pureza, Persignarse/Signarse, Inmaculada Concepción, etc.
- **104 preguntas quiz** (`DoctrineAndSpiritualData`).
- **30 advocaciones Marianas + 20 de Cristo** (`DevotionsAndHistoryData`).
- **Timeline de 10 épocas** de historia de la Iglesia.

---

## 14. Build, tests y repo

- **Rama**: `material-aaa-design` (desde `CatholicGPT` `19546d9`). Último commit verificado: `e2c4449` (11 cambios). APK: `dist/Catholic-Hermes-preview.apk` en la rama.
- **Build 4GB sin OOM**: heap Gradle 1.5GB, `--max-workers=2`, `--no-daemon`, sin configuration-cache; tests con fork 1GB vía `lowmem.init.gradle` en scratch; builds secuenciales.
- **Tests históricos**: 120/121 (único fallo pre-existente `ExampleRobolectricTest`: Java 17 vs SDK 36 requiere Java 21 — de entorno, no de código). Suites: `RemindersTest`, `AppNavigationTest`, `DesignContrastTest`, `FontTypographyTest`, `ReaderFontChoiceTest`, `DidacheDataTest`, `CatholicQuestionsDataTest`, `FoundationRefParserTest`, `FullAppScreenshotTest`.
- **Package real en dispositivo**: `com.aistudio.sanctafides.qvmrpx` (no el de build `com.example.catholicgpt`).
- **Emulador**: AVD `Clearplay_API_35` (WHPX `HypervisorPlatform` habilitado + reboot).
- **Botones uniformes**: 12dp recto-ligeramente redondeado en ambas identidades (M3 `materialShapes()` alineado a `shapes.button`).
- **Accesibilidad**: targets 48dp, `minimumInteractiveComponentSize()` en beads, etiquetas/testTags preservados en todos los rediseños.

---

## 15. Flujo de datos clave

- **Recordatorios**: `ReminderStore` (DataStore) → `ReminderScheduler` (AlarmManager exactas con `EXTRA_LANG`) → `ReminderReceiver` (notificación en idioma correcto) + `ReminderBootReceiver` (reprograma tras reboot) + `GoogleCalendarSync` (CalendarContract, sin API key).
- **Tentaciones/Fidelidad**: `TemptationStore`/`FidelityStore` (DataStore por fecha `yyyy-MM-dd`) → calendario mensual (acumulativo por día, reset mensual) + contadores hoy/semana/mes en el diálogo.
- **Versículo**: `DailyVerseStore` (rotación day-of-year + bible-api.com + caché DataStore) → `CatholicDataRepository` (resuelve pasaje bundleado trilingüe) → Home.
- **Preguntas**: `CatholicQuestionsData` (101) + `FoundationRefParser` (ES/EN/PT → `ScriptureReference` + rangos CCC) → deep links a Biblia/Catecismo.
- **Santo del día**: `DailySaintsFetcher` (Vatican News ES/EN/PT + `daily-saints` API, `NAV_HEADINGS` multilingüe) → Home.
