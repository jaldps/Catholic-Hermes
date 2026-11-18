# T-041 · Insignia de origen en la lista de novenas

- **Rama:** `CatholicGPT` (sin commit ni push, según lo pedido).
- **No se ha tocado:** `assets/`, versiones, firma, `tools/hermes-preview.gradle`, `.github/`, `dist/`, el esquema de Room, el catálogo, los hashes ni el número de novenas (262).

## Qué hace ahora la app

1. **Insignia en cada novena de la lista** (Orar → Novenas, y también «En curso» / «Completadas»). Cada tarjeta muestra, junto a su subtítulo («9 días», «Completada»…), una cápsula discreta con el origen de la novena. Si no cabe, baja a una segunda línea bajo el subtítulo; nunca estrecha el título. Tag: `novena_badge_<id>`.
2. **Filtro «Origen»** en la zona de filtros desplegables, junto a «A quién» e «Intención». Chip `novena_origin_picker`, elementos `novena_origin_all | novena_origin_traditional | novena_origin_modern | novena_origin_pastoral_review`. Se combina con «A quién», «Intención» y la búsqueda; «Ver todas» del estado vacío también lo reinicia; el contador de la cabecera («19 de 262») lo cuenta.

## Etiquetas

| `NovenaStatus` (provenance) | ES | EN | PT |
|---|---|---|---|
| `HISTORICAL`, `TRADITIONAL` | Tradicional | Traditional | Tradicional |
| `MODERN` | Moderna | Modern | Moderna |
| `PASTORAL_REVIEW` | En revisión pastoral | Under pastoral review | Em revisão pastoral |
| sin `provenance` | (sin insignia) | | |

Filtro «Origen» (prefijo ES/EN/PT: Origen / Origin / Origem): Todas · Tradicionales · Modernas · En revisión / All · Traditional · Modern · Under review / Todas · Tradicionais · Modernas · Em revisão.

**Decisiones a tener en cuenta**
- El enum real tiene **cuatro** estados (`HISTORICAL`, `TRADITIONAL`, `MODERN`, `PASTORAL_REVIEW`), no tres. Datos: 4 históricas, 108 tradicionales, 131 modernas, 19 en revisión (262 en total, todas con procedencia). Se agrupan `HISTORICAL` + `TRADITIONAL` bajo «Tradicional», como pedía el encargo.
- **La pantalla de detalle no muestra el estado** (solo «Historia», «Promesa» con `PromiseCard`, aviso comercial y nota pastoral). No había palabras previas que reutilizar para el estado, así que las etiquetas son las del encargo. La insignia no repite el tipo de promesa (`promise.kind`), que sigue solo en el detalle con sus textos («Promesa con fuente», «Concesión de la Iglesia», «Tradición popular», «Sin promesa asociada»).
- **Componente:** los chips de `AppComponents.kt` (`AppChip`, `AppMenuChip`) son interactivos (48 dp, seleccionables) y no existe una etiqueta no interactiva en los wrappers. La insignia es un pequeño `Text` en cápsula (`NovenaOriginBadge`, privado en `PrayersAndNovenasScreen.kt`), dibujado con el idioma blanco-sobre-degradado de la propia tarjeta (`RemoteArtCard`), de modo que se lee igual en las cuatro identidades y en claro/oscuro. El filtro sí usa `AppMenuChip`/`AppMenuItem`.
- **Accesibilidad:** la insignia es texto legible; la tarjeta ya fusionaba su semántica y su `contentDescription` pasa a «título, progreso, origen» (p. ej. «Novena a Cristo Rey, 9 días, Moderna»). Como la tarjeta fusiona descendientes, los tests encuentran `novena_badge_<id>` con `useUnmergedTree = true`.
- **Diseño a 320 dp / 130 %:** la tarjeta tenía relación fija 0,8; ahora esa relación es un **mínimo** y la tarjeta crece con el contenido, y las dos de cada fila miden lo mismo (`NovenaCardRow` con `IntrinsicSize.Max`; antes `AppGrid`/`Row`). Los otros usos de `RemoteArtCard` conservan su relación al tamaño normal. Con letra grande el título sigue limitado a 3 líneas (como antes; p. ej. «Beata Chiara Luce Ba…»).

## Dos cambios colaterales (en la misma pantalla)

- La búsqueda de texto de novenas ahora evalúa **primero** los filtros baratos (intención, «A quién», origen) y solo después el texto (que carga cuerpos de novena). Mismo resultado, menos cargas.
- La coincidencia de texto con los cuerpos va envuelta en `runCatching`: un cuerpo ilegible solo queda fuera de la búsqueda de texto. **Defecto preexistente descubierto:** `assets/novenas/270.json` (Nuestra Señora de Suyapa) y `272.json` (Divino Niño Jesús del 20 de julio) traen `scripture` como objeto `{es,en,pt}` en lugar de cadena, y el modelo espera una cadena (`JsonDataException`). Sin el `runCatching`, buscar texto en las novenas lanzaba esa excepción dentro de la búsqueda. **Abrir esas dos novenas probablemente falla igual**; no se ha tocado (`assets/` queda fuera) y conviene arreglarlo en una tarea aparte (los datos o el modelo/`NovenaCatalog`).

## Archivos

Modificado:
- `app/src/main/java/com/example/ui/screens/PrayersAndNovenasScreen.kt` (`NovenaOrigin` + funciones de etiquetas, filtro, `NovenaOriginBadge`, `RemoteArtCard` con insignia y altura mínima, `NovenaCardRow`).

Nuevos:
- `app/src/test/java/com/example/NovenaOriginBadgeTest.kt` (unitaria, 7 pruebas: estado → etiqueta en ES/EN/PT, sin procedencia, agrupación del filtro, etiquetas del filtro).
- `app/src/test/java/com/example/NovenaOriginBadgeUiTest.kt` (5 pruebas, Robolectric + `MainApp`: Orar → Novenas; histórica `novena-a-la-divina-misericordia` y tradicional `novena-a-la-inmaculada-concepcion` → «Tradicional»; moderna `novena-a-cristo-rey` → «Moderna»; en revisión `novena-a-la-virgen-del-pilar` → «En revisión pastoral»; `contentDescription` de la tarjeta; el filtro estrecha la lista con contadores calculados desde el catálogo; se combina con la búsqueda «Novena a Cristo Rey», con «A quién: Populares» y «Ver todas» lo reinicia).
- `app/src/test/java/com/example/screenshots/NovenaBadgesScreenshotTest.kt` (8 capturas por identidad/modo; comprueba además que hay insignias).
- `docs/tasks/T-041-shots/*.png` (16 capturas) y este informe.

## Capturas (`docs/tasks/T-041-shots/`)

Lumen claro, Sanctuary oscuro, Cristal (`glass`) claro y Tradicional (`classic`) oscuro, cada una con `list` y `origin_menu` (menú abierto, ventana emergente incluida), más `320_130_list` y `320_130_origin_menu` (320 dp, letra 130 %, PT):
`lumen_light_*`, `sanctuary_dark_*`, `glass_light_*`, `classic_dark_*` (`_list`, `_origin_menu`, `_320_130_list`, `_320_130_origin_menu`).
Revisadas a mano: sin recortes ni solapamientos; a 320/130 % la insignia baja bajo «9 dias» y las dos tarjetas de la fila miden lo mismo. El gutter lo aplica `AppListGroup`/la propia fila; no se ha duplicado el relleno.
Nota de captura: sin red, una tarjeta con imagen remota puede quedarse con el pintor vacío si se compuso antes de decodificar el arte de respaldo (preexistente en `AppRemoteImage`); el test de capturas desplaza la lista fuera y de vuelta antes de capturar.

## Pruebas

- `NovenaOriginBadgeTest` 7/7, `NovenaOriginBadgeUiTest` 5/5, `NovenaBadgesScreenshotTest` 8/8.
- **Suite unitaria completa** (`:app:testDebugUnitTest`, 39 min): 850 pruebas, 3 omitidas, 2 fallos:
  - `ExampleRobolectricTest > read string from context`: fallo conocido por el script de preview (ignorado).
  - `MyPathUiTest > todaySummaryShowsHabitsFidelityPlanAndNovenaAndEachRowOpensItsPlace`: falló en la suite completa y en una repetición inmediata, pero **pasa** (10/10) en tres ejecuciones posteriores con mis cambios, y también pasa con mis cambios retirados. Lo trato como inestable (carga de la máquina o dependencia de la fecha), no causado por esta tarea; conviene vigilarlo.
  - Dentro de esa ejecución pasaron todas las de `Novena*`, `EditorialReliabilityTest`, las de oraciones (`Prayer*`), `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest` y `SearchUiTest`.
- `:app:assembleDebug`: correcto.
- Tras la suite completa se revirtieron con `git checkout` los PNG regenerados de otras tareas bajo `docs/tasks/*-shots`.

## Limitaciones

- Verificado solo con Robolectric (capturas nativas); no hay comprobación en dispositivo real.
- La insignia no se ha probado con lector de pantalla real; la accesibilidad se comprueba por semántica (`contentDescription` y texto).
- «Tradicional» agrupa los estados `HISTORICAL` y `TRADITIONAL`; si se quisiera distinguir «Histórica» de «Tradicional», hay que ampliar etiquetas y filtro.
- Con letra grande el título sigue truncándose a 3 líneas en títulos muy largos.
- Los textos de las novenas 270 y 272 (ver arriba) siguen sin poder leerse por el defecto de datos preexistente.
