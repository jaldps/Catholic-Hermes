# Catholic Hermes Preview 1.7

[Descargar APK](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-quality-preview.apk?v=1.7)

Versión 1.7-quality-preview, versionCode8. Conserva el paquete y certificado de la preview1.6 (code7), para actualizar esa instalación. Las migraciones Room3/4→5 se comprobaron por separado. No se probó instalación física.

## Cambios

- Guardado de apuntes, progreso e intenciones mediante operaciones recuperables; migración atómica de novenas e índice único de hábitos.
- Citas bíblicas completas, correcciones de calendario/lecturas, fecha coherente y traslados configurables.
- Las246novenas se cargan a demanda; los3.068días y todos los cuerpos de oración se conservaron.
- Recordatorios con reconciliación de permisos y errores visibles; exportación con elección explícita del calendario y prevención de duplicados.
- Narración con estados de disponibilidad; navegador interno con política HTTPS; lectura/búsqueda bíblica en segundo plano y continuación después de20resultados.
- Metadatos devocionales más precisos, contexto pastoral donde corresponde, citas de santos con fuente y9repasos vinculados a lo estudiado.
- Ventanas adaptadas a320dp/130% y controles de al menos48dp en los casos comprobados.

## Validación y límites

Compilación assembleDebug completada. Firma verificada, mismo paquete/certificado que la APK1.6 y los246cuerpos comprobados dentro del propio instalador. Los resultados dirigidos están en [audit-remediation](audit-remediation/README.md).

La batería completa se detuvo por petición del usuario para conservar cuota: se habían observado 49 casos aprobados y 0 fallos en el registro hasta ese momento. **No se certifica una suite completa aprobada.** No hubo más cambios funcionales después de la compilación.

Se comprobaron por etapas persistencia, migraciones, calendario, referencias, recordatorios, selección de cuenta, voz, navegador, búsqueda, contenido y UI. Las capturas son de Compose en Android simulado; no acreditan audio, Doze ni funcionamiento en todos los teléfonos.

El leccionario offline no es completo: faltan ciclos ferialesI/II, servicios completos de Vigilia, Liturgia de las Horas y propios territoriales. El texto marcado para revisión pastoral no está aprobado por esta entrega. No se atribuye aprobación eclesiástica.

SHA-256: 5072d9636eed0a47c0a14c166ae07f6d3e45cdeb83357b6da54763bb8290c1e6
Tamaño: 62769415bytes. [Metadatos del APK](audit-remediation/release-1.7/APK.json).
