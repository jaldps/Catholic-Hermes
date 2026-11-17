# T-028 · Pantalla de Confesión

Rama `ext/T-028`, desde `origin/CatholicGPT` (`5e7c564`). Implementación terminada y validada para revisión del coordinador.

## Recorrido

- Nuevo destino `AppDestination.CONFESSION`, entrada `nav_confession` en Explorar → Vida interior y acceso `spiritual_confession_entry` en Vida espiritual.
- Tres secciones con `AppSegmentedTabs`: Prepararme, Mi lista y El sacramento. Cabezal compartido con el arte existente de Vida espiritual; sin otro kit ni navegación paralela.
- Prepararme permite elegir los cinco perfiles, abre una sección del examen a la vez y muestra las preguntas con casillas y su base del Catecismo. Solo el identificador del perfil se recuerda en preferencias.
- Mi lista reúne las preguntas marcadas, con su perfil y referencia, permite desmarcarlas y escribir notas opcionales. «Terminar y borrar» permanece fijo en un área propia sobre la navegación, sin cubrir el editor, y vacía preguntas y notas; salir de la pantalla también las vacía. No registra una confesión recibida ni incrementa estadísticas.
- En anchos de 360 dp o menos, Explorar reutiliza su rótulo corto existente «Más / More / Mais», para evitar partir la palabra en la navegación. Se conservan sus destinos y tags.
- El sacramento presenta los ocho pasos, los tres actos de contrición con fuente visible y las nueve preguntas frecuentes. Los textos proceden exclusivamente de los JSON revisados de T-027, sin alterarlos. Las referencias inglesas se presentan como CCC.

## Privacidad del borrador

La pantalla no recibe un DAO ni un servicio de copia. El único método persistente es `setConfessionProfile`, limitado a cinco identificadores conocidos. No se envían preguntas, notas ni recuentos a preferencias, Room, registros ni copias.

`rememberSaveable` normalmente puede serializar su valor en el estado de Android. Para cumplir el requisito de lista solo en memoria, `ConfessionDraft.Saver` guarda **solo un marcador constante** y restaura un borrador vacío. Preguntas y notas se mantienen en memoria observable; se eliminan al salir y al destruir la composición. La restauración de la pantalla, incluida su recreación al rotarla, recupera las opciones públicas de interfaz, pero no el borrador. El aviso inicial explica esta duración y se puede cerrar una vez.

El nuevo campo multilínea compartido usa la tarjeta, tipografía y paleta de la identidad elegida; crece de cuatro a ocho líneas y después permite desplazar notas largas. Solicita un teclado sin sugerencias de escritura; la aplicación no controla los teclados de terceros.

## Catálogo

`ConfessionCatalog`: `install(context)`, `default`, `override` para tests; lectura desde assets y classpath, en IO desde la pantalla. Puede reintentarse tras un error y no inventa contenido de sustitución. Un evento fijo de AppLog registra los fallos técnicos sin serializar mensajes de excepción.

La carpeta `confession` se añade a recursos de test. Se mantienen intactos assets revisados, versiones, firmas, esquema Room y `dist/`.

## Validación

Contenido sin modificar:

```text
examen.json: 5 perfiles · 60 secciones · 244 preguntas
guide.json: 8 pasos · 3 actos de contrición · 9 preguntas
0 errors · 0 warnings
```

Pruebas incluidas:

- Catálogo completo en ES/EN/PT, fallos de lectura y guardado del borrador sin contenido privado.
- Navegación desde Explorar y Vida espiritual, elección de perfil, casillas, notas, borrado y salida/reentrada.
- Comparación real de preferencias y de la instantánea de todas las tablas personales y preferencias exportables antes/después de marcar y escribir.
- Inspección directa del estado que Android guardaría: sin texto privado ni identificadores de preguntas marcadas; restauración vacía y fuente visible del acto de contrición.
- `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`.
- Capturas de las cuatro identidades en claro/oscuro y 320 dp al 130 %, con botones alcanzables y etiquetas sin recorte.

**Resultado final: 36 pruebas aprobadas, 0 fallos, 0 omitidas; `assembleDebug` correcto. `BUILD SUCCESSFUL in 8m 3s`.** [Resultado por caso y comprobación de alcance](T-028-shots/validation.json). Las iteraciones detectaron y corrigieron márgenes duplicados y la acción de borrado inaccesible al editar en 320 dp. La prueba de tipografía mide los límites del párrafo que pinta el texto centrado, además de comprobar altura y ausencia de elipsis; no confunde su caja intrínseca más estrecha con un recorte.

Comando final (Java 21, Gradle 9.3.1, Android SDK del entorno; el init script temporal ajusta el `user.home` de los tests para Robolectric y su salida):

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=2 --no-configuration-cache --no-build-cache \
  -I /tmp/t026-runner.gradle :app:assembleDebug :app:testDebugUnitTest \
  --tests com.example.ConfessionCatalogTest --tests com.example.ConfessionInteractionTest \
  --tests com.example.AppNavigationTest --tests com.example.DesignNavigationTest \
  --tests com.example.DesignContrastTest --tests com.example.screenshots.ConfessionScreenshotTest
```

La ejecución cubre las clases anteriores; no se ha repetido la suite completa de la app.

## Capturas

Se generaron 56 capturas de la app y se conservan 16 en esta entrega. Se revisaron los renders de las cuatro identidades y los estados de lista a 320 dp/130 %. Son Canvas nativo de Robolectric, no maquetas.

| Identidad | Claro | Oscuro |
|---|---|---|
| Lumen | [Prepararme](T-028-shots/lumen_light_overview.png) | [Prepararme](T-028-shots/lumen_dark_overview.png) |
| Tradicional | [Prepararme](T-028-shots/classic_light_overview.png) | [Prepararme](T-028-shots/classic_dark_overview.png) |
| Cristal | [Prepararme](T-028-shots/glass_light_overview.png) | [Prepararme](T-028-shots/glass_dark_overview.png) |
| Sanctuary | [Prepararme](T-028-shots/sanctuary_light_overview.png) | [Prepararme](T-028-shots/sanctuary_dark_overview.png) |

- Lumen claro: [Mi lista](T-028-shots/lumen_light_list.png), [El sacramento](T-028-shots/lumen_light_guide.png).
- Sanctuary oscuro: [Mi lista](T-028-shots/sanctuary_dark_list.png), [El sacramento](T-028-shots/sanctuary_dark_guide.png).
- 320 dp al 130 %, PT: [Lumen](T-028-shots/lumen_light_320_130_list.png), [Tradicional](T-028-shots/classic_dark_320_130_list.png), [Cristal](T-028-shots/glass_light_320_130_list.png), [Sanctuary](T-028-shots/sanctuary_dark_320_130_list.png).

La matriz y las dimensiones de las capturas quedan registradas en `T-028-shots/validation.json`.

## Fuentes y límites

Fuentes: `docs/tasks/T-028.md`, `docs/tasks/T-027-REPORT.md`, `AGENTS.md`, `docs/content/EDITORIAL-RULES-1.8.md`; contratos y componentes existentes del repositorio. No se redacta contenido doctrinal ni ninguna oración.

La verificación es automatizada con Robolectric/Canvas nativo; no se ha probado en un teléfono real ni con un teclado físico o de terceros. No se afirma una verificación de dispositivo.

Preguntas para el coordinador: ninguna; se explicita arriba la restauración vacía del borrador para evitar que Android lo guarde fuera de memoria.
