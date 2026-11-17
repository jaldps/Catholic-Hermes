# T-037 · Bienvenida personalizada: informe de integración

Rama `CatholicGPT`, sin commit ni push (según lo pedido). El contenido sale de `app/src/main/assets/onboarding/profiles.json` (T-033), que no se ha tocado.

## Qué hace ahora

- **Catálogo** (`OnboardingCatalog`): lee y valida los 3 perfiles (ids, índices 0..2, duraciones 2/5/10, ES/EN/PT no vacíos, ids de sugerencia únicos). Si falla, registra el evento fijo `LogEvent.ONBOARDING_CATALOG`, devuelve `null` y no inventa contenido. Un fallo no se guarda en caché, así que se puede reintentar; un éxito sí.
- **Diálogo de bienvenida**: al elegir un perfil aparece su línea `welcome` justo debajo de la lista de perfiles. Antes de elegir, el diálogo no cambia de tamaño. Se conservan todas las etiquetas (`welcome_dialog`, `welcome_profile_N`, `welcome_minutes_N`, `welcome_language`, `welcome_begin`, `welcome_skip`, `welcome_personalize`).
- **Home, «Para ti hoy»** (`home_for_you`): visible cuando la persona ya pasó por la bienvenida (incluido «Ahora no», que guarda exploring + 5 min). Muestra hasta 3 filas `for_you_<id>` con título, `why` y los minutos. «Ver todo» (`for_you_all`) abre un `AppSheet` (`for_you_sheet`) con todas las sugerencias del perfil, filas `for_you_sheet_<id>`.
- **Ajustes → «Tu camino» / "Your path" / «Seu caminho»** (`settings_welcome_profile`): abre una hoja (`welcome_profile_sheet`) con las mismas piezas que el diálogo (`JourneyChoicePicker`). Guarda al instante cada cambio y «Listo» (`welcome_profile_done`) cierra. La fila muestra el perfil actual como valor («Regresando», …).
- Home se actualiza al instante: `rememberJourney()` escucha los cambios de `SharedPreferences`, así que ya no depende de un `remember` que se leía una sola vez.

## Orden de las sugerencias

Función pura `OnboardingOrder.order(sugerencias, minutos)`:

1. las que coinciden con el tiempo elegido;
2. las más cortas, la más larga primero (la que mejor se acerca);
3. las más largas, la más corta primero;
4. dentro de una misma duración se respeta el orden revisado del JSON.

No se descarta ninguna. Ejemplo, «Estoy regresando» + 10 min: `returning_home`, `returning_saint`, `returning_confession`, `returning_rosary`, `returning_gospel`, `returning_our_father`.

## Decisiones que conviene conocer

1. **Home no lista las sugerencias con destino `HOME`.** `returning_home` («Empezar por la pantalla de Hoy») sería una fila que no hace nada dentro de Hoy. Home muestra las 3 primeras *sin* ese destino (con regresando + 10 min: santo, confesión, Rosario). La hoja «Ver todo» sí la incluye; tocarla cierra la hoja y vuelve al principio de Hoy. Si se prefiere mostrarla también en Home, basta quitar el filtro de `forYouOnHome`.
2. **`BIBLE` abre la liturgia del día.** Las tres sugerencias `BIBLE` son «El Evangelio de hoy», que vive en la pestaña de la Misa de Leer, no en el lector de la Biblia. `openSuggestion` en `MainApp` usa `bibleInitialTab = 1` (igual que «Ver la liturgia de hoy»). El resto de destinos entra por `openSection` (el mismo camino que la búsqueda), que reinicia los deep links.
3. **`HABITS_VIRTUES` y `SPIRITUAL_LIFE`** se resuelven por `AppDestination.myPathSectionFor`: abren «Mi camino» en Hábitos y en Diario, respectivamente. Hay test para ambos casos (`practicing_spiritual` → `spiritual_confession_entry`).
4. **El paso de «Primeros pasos» (`step`)** ya no se reinicia siempre al guardar: solo se fija al del perfil (regresando 4, practicando 6) cuando la respuesta es nueva o cambia el perfil; cambiar solo el tiempo conserva el avance.
5. **Entrada de Ajustes en `MoreScreen`:** esa pantalla agrupa los ajustes con la lista `SettingsTags`; una etiqueta que no esté allí se pinta como una segunda sección «Ajustes». Se añadió `settings_welcome_profile` entre Fuente y Copia de seguridad.
6. **Textos con letra muy grande (320 dp / 130 %):** la fila de Ajustes (`EntryRow` de `MoreScreen`) pasa el valor a la segunda línea cuando el texto es muy grande (antes «Fuente» y «Apariencia» se partían letra a letra); los chips muestran solo el número («10») con la unidad en el rótulo («Tiempo para hoy, en minutos») y descripción accesible «10 min»; en las filas de Home los minutos pasan al principio del subtítulo. El texto «10 min» se recortaba en el chip. Además, la fila «Idioma» del diálogo usa subtítulo en vez de texto final (se partía letra a letra a 320/130 %).

## Copia de seguridad

**Ya estaba incluido, no hizo falta cambiar el código.** `BackupPreferences.SHARED` contiene `beginner_journey`, y `BackupValidation` valida `welcomed` (BOOLEAN) y `step`, `profile`, `minutes` (INT). Al restaurar se sustituyen o se combinan (en combinar, `step` conserva el mayor y `profile`/`minutes` toman los del archivo).
Es el cambio más pequeño y seguro: mover las claves a DataStore habría exigido una migración sin ganar nada. Se añadió el test `theWelcomeProfileAndTimeTravelInTheBackupAndMergeKeepsTheFurthestStep` en `BackupCoreTest` (ida y vuelta REPLACE y MERGE, y lectura real con `BeginnerJourneyStore`).

## Dónde queda en Home

Después de la tarjeta «Liturgia de hoy» (`daily_mass_card`) y antes del botón «Más para hoy» (`today_more_toggle`). Orden de la primera pantalla: cabecera, día litúrgico, oración del momento, mis prácticas, liturgia de hoy, **Para ti hoy**, «Más para hoy». El día litúrgico, las lecturas y el santo no se mueven (el santo sigue dentro de «Más para hoy»).

## Archivos

Nuevos:
- `app/src/main/java/com/example/data/repository/OnboardingCatalog.kt` (catálogo, modelos y `OnboardingOrder`)
- `app/src/main/java/com/example/ui/screens/ForYouToday.kt` («Para ti hoy», hoja «Ver todo», carga de perfiles)
- `app/src/test/java/com/example/OnboardingCatalogTest.kt`
- `app/src/test/java/com/example/WelcomePersonalizationTest.kt`
- `app/src/test/java/com/example/screenshots/WelcomePersonalizationScreenshotTest.kt`
- `docs/tasks/T-037-shots/` (32 PNG) y este informe

Modificados:
- `app/build.gradle.kts` (recurso de test `src/main/assets/onboarding`)
- `data/diagnostics/AppLog.kt` (`ONBOARDING_CATALOG`)
- `SanctaFidesApplication.kt` (`OnboardingCatalog.install`)
- `ui/screens/BeginnerJourneyScreen.kt` (store con `profile`, `rememberJourney`, `JourneyChoicePicker`, `WelcomeChoices`, `JourneyChoiceSheet`)
- `ui/screens/HomeScreen.kt` (bloque, hoja, `onOpenSuggestion`, estado reactivo)
- `ui/screens/MoreScreen.kt` (`SettingsTags`)
- `ui/MainApp.kt` (`openSuggestion`, entrada de Ajustes y hoja)
- `app/src/test/java/com/example/BackupCoreTest.kt`

Nota: en el mismo checkout hay cambios de otra tarea en curso (avisos y widgets: `data/notices`, `widget`, `MainActivity`, `RemindersScreen`, etc.). No son de T-037 y no se han tocado.

## Pruebas

Resultados (Gradle con el candado compartido):

- Suite completa `:app:testDebugUnitTest`: 761 pruebas, 1 fallo, el conocido `ExampleRobolectricTest` «read string from context» (init script de la vista previa). Incluye `AppNavigationTest` (5), `DesignNavigationTest` (8), `DesignContrastTest` (1), `FontPreferencesTest` (4), `HomePrayerPrioritiesTest` (8), `MyPathUiTest` (10), `SearchUiTest` (8), `BackupCoreTest` (13) y las nuevas, todas en verde.
- `:app:assembleDebug`: BUILD SUCCESSFUL.

Cobertura añadida:

- `OnboardingCatalogTest` (8): 3 perfiles, 6–7 sugerencias, las tres duraciones, destinos válidos como `AppDestination`, destinos absorbidos por «Mi camino», contenido mal formado o ausente (`null`), reintento tras fallo, orden por minutos (pura), todas las sugerencias se conservan, caso «regresando + 10 min», Home ≤ 3 sin `HOME`.
- `WelcomePersonalizationTest` (4 casos × 4 identidades = 16): bienvenida → «Estoy regresando» + 10 min → `home_for_you` con el orden correcto, línea de bienvenida solo tras elegir, tocar una fila navega (Confesión); Ajustes cambia perfil y tiempo → Home se actualiza → «Ver todo» abre la hoja con todas las sugerencias y una fila abre «Mi camino»; el Evangelio abre la liturgia; «Ahora no» personaliza con los valores por defecto.
- `BackupCoreTest`: copia y combinación del perfil.

## Capturas

`docs/tasks/T-037-shots/` (60 % del tamaño real). Nombre: `<identidad>_<claro|oscuro>_<escenario>_<vista>.png`.

- Lumen claro, Sanctuary oscuro, Cristal claro, Tradicional oscuro (`lumen_light`, `sanctuary_dark`, `glass_light`, `classic_dark`).
- Escenarios: `es_*` (411 dp, 100 %, español) y `320_130_*` (320 dp, 130 %, portugués).
- Vistas: `home` (bloque «Para ti hoy»), `settings` (Explorar → Ajustes), `sheet` (hoja «Tu camino»), `welcome` (diálogo con la línea del perfil).

Revisadas a mano: sin recortes ni solapes; el único bloque que se cortaba (chips y fila «Idioma» a 320/130 %) se corrigió. Los márgenes no se duplican (`AppListGroup` ya aplica la gutter).

## Limitaciones

- La bienvenida (`welcome_personalize` / «Ahora no») sigue dentro de «Más para hoy», que arranca plegado; es el diseño previo y no se tocó. «Tu camino» en Ajustes da una entrada siempre visible.
- Capturas hechas con Robolectric, no en dispositivo real; no se ha comparado con ninguna web.
- «Para ti hoy» no aparece mientras el catálogo carga (breve, fuera del hilo principal) ni si el asset fallara (se registra el evento y no se muestra nada).
- El orden entre duraciones más cortas («la más larga primero») es una interpretación mía de «luego las más cortas»; está documentada en `OnboardingOrder` y cubierta por tests.
