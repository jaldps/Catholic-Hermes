# T-034 · Revisión del coordinador

- **Entrega:** `media/saint-images` (fork) @ `d95e897` (tercera ronda: 215/215)
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

## Comprobado

- **Cobertura:** Hermes entregó las 215. El coordinador revisó las 31 de la tercera ronda e **integra 214**.
- **Excluida:** `bede_the_venerable_priest`. «Venerable Bede, Bramley» es la foto de una iglesia dedicada a san Beda, no del santo, así que la ficha mantiene el arte de sección. Si aparece una obra que represente al santo, se puede sustituir.
- **Aceptada:** «Primeros mártires de la Santa Iglesia Romana» usa el cuadro *Las antorchas de Nerón* de Siemiradzki, que representa precisamente ese martirio, sin primer plano sangriento.
- **Credenciales:** las claves coinciden con `T-034-data/saints.tsv`, con un crédito por imagen y una imagen por crédito.
- **Licencias:**

  | Licencia | Imágenes |
  |---|---|
  | Dominio público | 137 |
  | CC BY-SA | 35 |
  | CC0 | 33 |
  | CC BY | 10 (9 tras excluir la de san Beda) |

  No hay ninguna NC ni ND, ni ninguna imagen referencial.
- **Auditoría propia:** las hojas de contactos retiraron 61 imágenes dudosas o equivocadas. Además, una muestra al azar de 12 que revisé es correcta.

## Integración

- **Archivos:** las imágenes se recodificaron a un máximo de 600×720 px en JPEG calidad 68 (unos 11,5 MB en total) y están en `app/src/main/assets/saints/images/<key>.jpg`, junto con `credits.json` y `manifest.json` (foco y tono).
- **Código:** `DevotionImageCredits.saints` reutiliza el catálogo de créditos de las devociones.
- **Ficha del santo:** usa la imagen en el cabezal único y muestra debajo la línea de crédito (`saint_image_credit`), que enlaza a la página del archivo en Commons. Si no hay imagen, mantiene el arte de sección.
- **Pruebas:** `SaintsScreenTest` comprueba el crédito, y `SaintImageScreenshotTest` genera las capturas de las 4 identidades y a 320 dp / 130 %, guardadas en `docs/tasks/T-034-shots/`.
