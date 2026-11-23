# T-055 · Revisión del coordinador

- **Entrega:** fork `joselanzd-hash/Catholic-Hermes`, `ext/T-055` @ `90ff84b`.
- **Veredicto:** **APROBADO · CERRADO.** Integrado en `CatholicGPT`.
- **Una petición menor**, que no bloquea: rehacer unas capturas.

## Código

- Solo toca los archivos permitidos. Las decisiones son razonables y están documentadas:
  - una sobrecarga de `ContentLoadNotice`, para no romper `BibleScreen.kt:843`;
  - el Ángelus del mediodía, que es el que viene activo por defecto;
  - se conserva `novena_clear_filters`;
  - Órdenes sin cambios, porque su segmentado no puede vaciar la lista.
- **Los cinco estados existen con sus etiquetas:** `content_load_notice`, `daily_missal_error`, `novena_filter_empty`, `list_empty` y `reminders_empty`. No se creó `news_offline`, como se pidió.
- **Sanctuary a 411 dp:** el `Spacer` de 64 dp bajo «Quitar filtros» es una solución correcta para que el botón se pueda desplazar por encima de la barra. La altura de `AppEmptyState` en Sanctuary se revisará en L4 (Orar horizontal).

## Tests (aquí, con el lock)

| Clase | Resultado |
|---|---|
| `EmptyStatesTest` | 5/5 |
| `SaintsScreenTest` | 2/2 |
| `HomeTextTest` | 7/7 |
| `HomePrayerPrioritiesTest` | **8/8** (los 4 `AppNotIdleException` eran de tu entorno) |
| `NovenaOriginBadgeUiTest` | 5/5 |
| `AppNavigationTest` | 5/5 |
| `DesignNavigationTest` | 8/8 |

## Petición menor (puedes hacerla junto con los cambios de T-056)

En `_contact.png`, las capturas de **Lumen claro y Cristal claro** salen con títulos y textos lavados, casi ilegibles («Hoy», «Santos», «Nada coincide», «Aún no tienes recordatorios»). Sanctuary y Tradicional se ven bien, así que parece que la captura atrapa la animación de entrada a medias, no un defecto de la app.

- En `EmptyStatesScreenshotTest`, pasa `reduceMotion = true` a `SanctaFidesTheme` y espera a que la composición esté en reposo antes de capturar. AGENTS.md lo pide: las capturas congelan el movimiento decorativo.
- Rehaz las capturas y la lámina. Si después de eso siguen lavadas, avisa: entonces sí sería un defecto.

## Revisión 2 (23-nov) · Capturas

`ext/T-055` @ `fa5b364`, integrado.

Tenías razón en que los PNG apenas cambiaron, pero el defecto no era la lámina. En `daily_missal_error`, `list_empty` y `reminders_empty` de **Lumen y Cristal claros**, el fondo es oscuro opaco (`#191C1E`) con texto de modo claro.

**Causa:** en esos casos, `EmptyStatesScreenshotTest.theme { … }` dibuja la pantalla **sin** el lienzo de la app. `MainApp` lo pinta con `lumenCanvas()`, y por eso `novena_filter_empty`, que pasa por `MainApp`, se ve bien. En la app real no ocurre: todas las pantallas van dentro de `MainApp`.

**Arreglo, cuando puedas:** en `theme()`, envuelve `content()` en `Box(Modifier.fillMaxSize().lumenCanvas())` y rehaz esas capturas. No bloquea.
