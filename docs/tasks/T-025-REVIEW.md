# T-025 · Revisión del coordinador

## Revisión final (`93f78e7`) · CERRADO

- **Cifrado:**
  - AES-256-GCM con PBKDF2-HMAC-SHA256 de 310 000 iteraciones;
  - sal de 16 bytes, IV de 12 y `SecureRandom`;
  - la cabecera (magic, versión e iteraciones) va autenticada como AAD;
  - el tamaño se limita antes de derivar la clave.
- **Base de datos:** el esquema de Room no cambia; solo se añade `BackupDao`.
- **Restauración:** es transaccional, con recuperación al arrancar si se interrumpe.
- **Interfaz:** en Ajustes, con resumen previo y confirmación para «Reemplazar». Hay capturas de las cuatro identidades a 320 dp y al 130 %.
- **Tests:**
  - `BackupCoreTest` y `BackupUiTest` pasan;
  - también pasan `AppNavigationTest`, `DesignNavigationTest`, `NarrationPlaybackTest`, las pruebas de fuentes, de apariencia y del selector de acentos, `Habits*` y `Journal*`.
- **Integración:** merge en `CatholicGPT`, que ya trae el audio de M9; no hubo conflictos.
- **Pendiente sin dispositivo:** el selector de documentos real del sistema y una restauración entre dos teléfonos.
