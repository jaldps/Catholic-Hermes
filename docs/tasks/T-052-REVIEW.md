# T-052 · Revisión del coordinador

## Ronda 1 · CAMBIOS (`560149c`, 7/7)

- **Alcance:** correcto. Siete pantallas en dos paneles, sin tocar `ui/design` ni `MainApp.kt`, y con un test L5 por pantalla.
- **No se integra todavía.** Fusionado aquí sobre `CatholicGPT`, **10 tests que antes pasaban fallan** (tests del coordinador del 24-nov).
- La versión 1.9.1 sale sin T-052. Puedes ver tu rama fusionada en `wip/t057` (`124cb81`) del repositorio principal.

### Tests en rojo

| Test | Fallo | Causa probable |
|---|---|---|
| `SaintsScreenTest.exploreFilterAndDetailInLumen` | `Expected exactly '1' node … TestTag = 'saints_month_1'` | Los filtros de mes se movieron a la hoja y **en vertical ya no están en pantalla**. El encargo pedía conservar todas las etiquetas y no cambiar el vertical: en vertical los filtros siguen como antes. Solo en `LandscapePhone` van a la hoja. |
| `SearchUiTest.typingShowsGroupsAndTappingASaintOpensItsProfileThenBackKeepsTheResults` | `ComposeTimeoutException` tras 40 s | Seguramente la misma causa: al abrir un santo desde la Búsqueda, la ficha o «atrás» cambió en vertical. Comprueba que «atrás» vuelve a los resultados con el texto escrito. |
| `TabletLayoutTest.justBelowTheTwoPaneBreakpointPushesDetails` (4 identidades) | `assertDoesNotExist … TestTag = 'two_pane'`, encontrado a 1360 px de ancho | Justo **por debajo** del punto de corte de tableta, la ficha debe apilarse como página. Tu condición activa los dos paneles también ahí. Los dos paneles nuevos son **solo para `LandscapePhone`** (alto < 480); en tableta manda el corte que ya existía. |
| `TabletLayoutTest.theRailStartsAt600` (4 identidades) | Falla en la misma fase | Probablemente la misma condición de ventana. Revisa que no cambiaste la clase de ventana ni el contenedor de Santos en anchos ≥ 600 con alto ≥ 480. |

### Qué hacer

1. Limita la disposición nueva a `appWindowClass() == LandscapePhone`. En vertical y en tableta, todo debe quedar **idéntico** a `CatholicGPT`.
2. Ejecuta **estos cuatro tests** además de los tuyos: `SaintsScreenTest`, `SearchUiTest`, `TabletLayoutTest` y `NarrowLargeTextAuditTest`, clase por clase. Es la regla 10: son de tu área, porque tocas sus pantallas.
3. Push a `ext/T-052` con «T-052 r2: …». Después sigue con **T-053**, que ya está abierto.
