# Buzón de Chaty

Cuando Jose te diga **«continúa»**, haz esto:
1. `git fetch origin`.
2. Lee **este archivo** en `origin/CatholicGPT`. El mensaje más reciente va arriba, en «Mensaje actual».
3. Cumple el «Mensaje actual» en orden.

Si el mensaje actual ya está hecho y no hay otro, sigue con el siguiente encargo `ABIERTO` para ti en `docs/tasks/README.md`. Si tampoco hay ninguno, avisa a Jose.

Al terminar cada entrega, actualiza el `T-0NN-REPORT.md` de tu rama (regla 12). El coordinador lo lee ahí.

---

## Mensaje actual · 28-nov, ~21:40 UTC

**T-070 APROBADO y CERRADO.** El crítico aprobó las nueve medallas en la ronda 3. Ya están integradas en `CatholicGPT`, en `docs/design/catholic1/illustrations/v2/`. Buen trabajo.

**1. Ahora, T-073 completo** (`docs/tasks/T-073.md`), **puntos 1 a 6.**
- El **punto 5 ya está desbloqueado**: convierte las nueve medallas v2 a `VectorDrawable`.
  - Día en `res/drawable/`, Noche en `res/drawable-night/`.
  - Usa los grupos por rol de tus SVG.
  - Añade el parámetro `illustration` a `AppEmptyState` y úsalo en favoritos, diario, búsqueda sin resultados y error sin conexión.
- **Pulido opcional del crítico** en el punto 5: alarga unos 10 px el extremo superior derecho de la barra de «sin conexión», para que cruce la nube.
- Un push por punto a `ext/T-073`.
- Capturas al 60 % en `docs/tasks/T-073-shots/`, sin datos personales.
- `T-073-REPORT.md` en tu rama, con un apartado por punto.

**2. Después, T-071** (`docs/tasks/T-071.md`): Rosario, coronillas, Orar, Leer y los lectores.

**Reglas:**
- sin tests largos: basta con que compile;
- un directorio de trabajo propio;
- haz push cada vez que algo compile.

## Mensajes anteriores
- **28-nov, ~21:10.** T-070, ronda 2: paloma, cruz, «sin conexión» y rosario en oro limpio. Hecho en `448814d` y aprobado.

- **28-nov, ~20:50.** Correcciones de T-070, ronda 1, con la Noche invertida (opción b). Hecho en `4c91c97`.
