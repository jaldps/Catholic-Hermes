# Crítico T-070 · Ronda 3 (ext/T-070 @ 448814d)

**Veredicto: APROBADO.** Queda una nota de pulido opcional (L), que no bloquea.

Método:
- Comparé con t070b byte a byte.
- Hice zoom de las 4 ilustraciones corregidas y filas de las nueve a 96 y 64 px sobre `#FFFFFF` y `#162438`.
- Medí la geometría del rosario sobre el SVG.
- No hay prueba en dispositivo.

## Los cuatro puntos

1. **Paloma de Día: ✅.**
   - Cuerpo marino `#10345C` en el grupo `navy` (11,49:1 frente al medallón), ala en `warm`, y ojo y línea del ala marfil en `dove-details`.
   - Se lee como silueta maciza a 96 y a 64 dp, igual que el resto de la serie.
   - De Noche solo cambian los `id` (el PNG es idéntico a la ronda 2): cuerpo marfil y ojo marino, correcto.
2. **Cruz de Noche: ✅.**
   - Cuerpo `#10345C` y sombra `#0C2848` sobre el nimbo `#F8E8BC` (10,35:1).
   - Se lee igual que de Día.
   - `details-cross` se ha quitado en los dos temas.
3. **«Sin conexión» de Noche: ✅.**
   - Barra `#10345C` con filete oro de 12 px (10,14:1 frente a la nube).
   - El saliente es de unos 10 px, solo abajo a la izquierda (≤ 16).
4. **Rosario: ✅.**
   - Los extremos pasan a y167, con las cuentas en (168,167) y (344,167).
   - Aire con la estrella inferior: **18,6 px** (antes 4,5).
   - Aire entre cuentas: entre **6,8 y 8,3 px** (todas ≥ 6).
   - El oro limpio de Día se acepta por decisión del dueño.

## Las otras cinco

Libro, corazón, diario, lupa y vela: SVG y PNG **idénticos byte a byte** a la ronda 2. No hay regresiones.

## L · Pulido (opcional)

- **Sin conexión, Día y Noche:** al recortarla, la barra ya no **cruza** la nube. Ahora sale solo por abajo a la izquierda; arriba a la derecha termina dentro de la cúpula. A 64 dp se lee más como una franja que como un tachado.
- **Propuesta:** alargar el extremo superior derecho hasta que salga de la nube unos 10 px, igual que el inferior. Así la barra sería simétrica y cruzaría la nube, como pide el encargo.

## Veredicto por ilustración

| Ilustración | Veredicto |
|---|---|
| `empty-book` | APROBADO |
| `empty-heart` | APROBADO |
| `empty-journal` | APROBADO |
| `empty-search` | APROBADO |
| `empty-candle` | APROBADO |
| `empty-dove` | APROBADO |
| `empty-cross` | APROBADO |
| `empty-rosary` | APROBADO |
| `error-offline` | APROBADO; la nota L es opcional |

**Global: APROBADO para integrar.**
