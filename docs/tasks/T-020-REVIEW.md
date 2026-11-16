# T-020 · Revisión del coordinador

## Revisión final (`c38bf3c`) · CERRADO

**Decisión del dueño (16-XI-2026):**
- se integra todo, incluida la identidad **Cristal**, así que ahora hay tres identidades seleccionables;
- se mantiene la **luz litúrgica** como acento con el tinte «Dinámico Litúrgico».

`AGENTS.md` está actualizado con las tres identidades y la regla del cabezal único.

### Comprobaciones

- **Merge limpio** sobre `CatholicGPT`, con T-018 incluido.
- **`assembleDebug`:** OK.
- **Tests:** pasan `AppNavigationTest`, `DesignNavigationTest`, `DesignContrastTest`, `LiquidGlassTest`, `Font*`, `SaintsScreenTest`, `OrdersScreenTest`, `Devotions*`, `VoiceSettingsUiTest`, `RosaryInteractionTest`, `Novena*` y `EditorialReliabilityTest`.
  - `LiquidGlassTest` necesitó, en este entorno, el artefacto de Robolectric para SDK 28.
- **Capturas:** Hoy en Lumen, Cristal claro y Cristal oscuro, Santos y una devoción mariana. Se ven coherentes.
  - El dock de seis pestañas con «Oracione s» partido que sale en `devotion_marian_*` es el dock falso del arnés `DoctrineAreaScreenshotTest`, no el de la app.
- **Pinturas nuevas:** Durero, Holman Hunt y Velázquez, de dominio público, con su procedencia en `manifest.json` e `IMAGES.md`.

### Respuesta a la pregunta

El pulido pasa a **T-021** (`ext/T-021`), partiendo de `CatholicGPT` con este rediseño ya fusionado. Así no hay dos ramas sobre `ui/design/`.

### Pendiente (sin verificar en dispositivo)

- el desenfoque en vivo (Android 12 o superior) y la lente AGSL (Android 13 o superior);
- la respuesta háptica;
- el rendimiento al hacer scroll.

El dueño lo probará con el APK 1.8.13.

### Auditoría `docs/AUDIT-2026-11.md`

Se integra como documento. Sus urgencias quedan propuestas al dueño como meta futura, sin aplicarse en esta revisión:
- copia y exportación de datos;
- foco de audio y servicio en segundo plano;
- deduplicar novenas;
- R8 y dependencias sin uso.

La versión del build de producción (D7) no se toca: la preview la gestiona `tools/hermes-preview.gradle`.
