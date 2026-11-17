# T-026 · Revisión del coordinador

## Revisión final (`9c13de9`) · CERRADO

- **Registro de errores:**
  - `AppLog` solo admite eventos de un enum, sin texto libre, y no serializa `message` ni `toString`. Escribe fuera del hilo principal y rota a 256 KiB.
  - 37 sitios instrumentados.
  - «Enviar informe de errores» solo se envía si el usuario lo decide, y el `FileProvider` queda limitado a `cacheDir/error-reports`.
  - La privacidad está bien resuelta.
- **Higiene:**
  - 5.875 líneas de kits muertos y `ThemePalettes` eliminadas, con verificación por grep.
  - `IVE` se conserva, porque tiene usos vivos.
  - Fuera Navigation, Retrofit, Firebase AI, App Check y los plugins Secrets y Google Services: 86 módulos menos, sin cambios de versión en los que quedan.
- **Validación del coordinador tras el merge:**
  - `assembleDebug` correcto.
  - Pasan las pruebas de navegación, diseño, fuentes, copia de seguridad, narración, `ErrorReport*`, `AppLog`, devociones, `LiquidGlassTest` y el selector de acentos.
  - `HomePrayerPrioritiesTest[SANCTUARY-DARK]` falló, igual que en tu informe. Es el D4 de la auditoría. Su diagnóstico y corrección se hacen aparte, sin relajar la aserción.
