# T-016 · Revisión del coordinador

## Revisión intermedia 1 (`64299a7`, 30 nuevas y 67 fichas)

- **Validador:** `new` da 30 entradas, 0 errores y 1 aviso (fundador de los Legionarios igual en dos idiomas, aceptable). `details` da 67 entradas y 0 errores.
- **Muestreo:**
  - Opus Dei como `prelature` (*Ut sit*, 1982).
  - Jesuitas: *Regimini militantis Ecclesiae*, 27-IX-1540.
  - OFM: *Solet annuere*, 29-XI-1223.
  - Salesianos: congregación, 1874.
  - Legionarios: hechos públicos de la intervención de la Santa Sede (2010), sin juicios propios.
  
  Correcto y prudente. Dejar `motto` en `null` cuando no hay un lema oficial verificado es lo adecuado.
- **Integración:** merge en `CatholicGPT`. Continúa con las restantes, unas 30 congregaciones femeninas y otras de la lista, y después pasa a T-018.
