# Buzón de Chaty

Cuando Jose te diga **«continúa»**, haz esto:
1. `git fetch origin`.
2. Lee **este archivo** en `origin/CatholicGPT`. El mensaje más reciente va arriba, en «Mensaje actual».
3. Cumple el «Mensaje actual» en orden.

Si el mensaje actual ya está hecho y no hay otro, sigue con el siguiente encargo `ABIERTO` para ti en `docs/tasks/README.md`. Si tampoco hay ninguno, avisa a Jose.

Al terminar cada entrega, actualiza el `T-0NN-REPORT.md` de tu rama (regla 12). El coordinador lo lee ahí.

---

## Mensaje actual · 28-nov, ~21:10 UTC

**1. T-070, ronda 2.** Ya hay 6 de 9 aprobadas: libro, corazón, diario, lupa, vela y el rosario de Noche.
- Lee el apartado «Revisión 2» de `docs/tasks/T-070-REVIEW.md`; el detalle está en `T-070-CRITICA-2.md`.
- Faltan cuatro cosas:
  - la **paloma de Día**, con cuerpo marino (grupo `navy`);
  - la **cruz de Noche**, en marino `#10345C` con sombra `#0C2848`, y quita `details-cross`;
  - la **barra de «sin conexión» de Noche**, en marino con filete oro, que sobresalga 16 px como máximo;
  - en el **rosario**, baja 12 px las cuentas de los extremos.
- **Rosario de Día: Jose decidió (a).** Las cuentas van en **oro limpio** `#DCA83C`, como en el símbolo.
- Haz push a `ext/T-070`, con las cuatro láminas nuevas y el REPORT con un apartado «Revisión 2» que conteste a cada punto.

**2. Después, T-073** (`docs/tasks/T-073.md`): los puntos 1, 2, 3, 4 y 6.
- Un push por punto a `ext/T-073`.
- Capturas al 60 % en `docs/tasks/T-073-shots/`, sin datos personales.
- El punto 5 se hace cuando T-070 quede aprobado.

**3. Luego, T-071.**

**Reglas:**
- sin tests largos: basta con que compile;
- un directorio de trabajo propio (regla 13);
- haz push cada vez que algo compile.

---

## Mensajes anteriores

- **28-nov, ~20:50.** Correcciones de T-070, ronda 1, con la Noche invertida (opción b). Hecho en `4c91c97`.
