# T-035 · Revisión del coordinador

- **Entrega:** `ext/T-035` (fork) @ `d2eda7a`
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

## Comprobado

- `validate_notices.py`: 0 errores y 0 avisos.
- **Contenido:** 7 tiempos con 32 avisos semanales, las 48 celebraciones (víspera y día) y 7 recordatorios con sus marcadores en ES, EN y PT.
- **Muestreo correcto:**
  - Viernes Santo sin misa;
  - Letrán, «cabeza de las iglesias de Roma y del mundo»;
  - la Inmaculada, preservada desde el primer instante;
  - la Divina Misericordia, sin promesas ni indulgencias;
  - el aviso de precepto no dice qué días lo son.
- El tono invita sin culpa y nombra las prácticas sin componer oraciones.

## Nota para la integración (T-032)

`novena_day` dice «la novena de {{novena}}». Al rellenarlo, el código debe pasar el nombre del destinatario («San José»), no el título completo de la novena, para no producir «la novena de Novena a San José».
