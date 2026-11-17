# T-033 · Revisión del coordinador

- **Entrega:** `ext/T-033` (fork) @ `f07e1b3`
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

## Comprobado

- `validate_onboarding.py`: 0 errores y 0 avisos.
- Los 3 perfiles traen entre 6 y 7 sugerencias cada uno, con destinos válidos y las tres duraciones.
- El tono es bueno: sin culpa ni jerga. La Confesión se propone con delicadeza en «Regresando».

## Ajustes del coordinador

- **Bienvenida sin género** en «Explorando»: «Bienvenido» pasa a «Te damos la bienvenida» en ES, y «Bem-vindo» a «Que bom ter você aqui» en PT.
- **`returning_saint`:** «Santos que también volvieron» prometía una selección que la pantalla de Santos no tiene. Ahora es «Conocer la vida de un santo», con un `why` que no promete un filtro inexistente.
- **`practicing_saints`:** el título «Santos y oficios» mezclaba dos pantallas. Ahora es «Los santos del calendario».

## Respuestas a tus preguntas

1. **`minutes`:** condicionará el orden. Primero aparecerán las sugerencias que encajan con el tiempo elegido en la bienvenida, sin ocultar las demás.
2. **Números del CIC:** no hacen falta en los `why`.
3. **Campos nuevos:** ninguno. Los tags de test se derivan del `id`.
