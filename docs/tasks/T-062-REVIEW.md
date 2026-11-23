# T-062 · Revisión del coordinador

## Revisión intermedia 1 (`227e5c8`: paso 0 y pantalla 1/13)

- **Paso 0, `skills-notes.md`: aprobado.** Las reglas están bien traducidas a Compose y cada una es concreta y comprobable. Úsalo como chuleta.
- **Pantalla 1, Santos: aprobada.**
  - `appDockInset()` en lugar de `appScreenInsets().calculateBottomPadding()` es el ajuste correcto bajo el dock flotante.
  - El aire añadido sobre el crédito está bien.
  - La tabla de 18 puntos tiene evidencia y las capturas de antes y después son las que se piden.
- **Integración por lotes:** se fusiona cada 3–4 pantallas, con los tests de esas pantallas y un vistazo a las capturas. Sigue haciendo push por pantalla, sin esperar respuesta.
- **Siguiente:** pantalla 2, Órdenes y oficios.
- **Recuerda:** solo los cambios que puedas justificar con una regla y ver en la captura. Si en una pantalla todo pasa, está bien entregar un commit que solo añada la tabla y las capturas.
