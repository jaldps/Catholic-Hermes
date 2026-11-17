# T-034 · Revisión del coordinador

- **Entrega:** `media/saint-images` (fork) @ `ed583d1`
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

## Comprobado

- **Cobertura:** 184 de las 215 fichas tienen imagen. Las 31 sin imagen están listadas en el REPORT, con motivo: no hay obra fiable del santo concreto.
- **Credenciales:** las claves coinciden con `T-034-data/saints.tsv`, con un crédito por imagen y una imagen por crédito.
- **Licencias:**

  | Licencia | Imágenes |
  |---|---|
  | Dominio público | 120 |
  | CC0 | 28 |
  | CC BY-SA | 27 |
  | CC BY | 9 |

  No hay ninguna NC ni ND, ni ninguna imagen referencial.
- **Auditoría propia:** las hojas de contactos retiraron 61 imágenes dudosas o equivocadas. Además, una muestra al azar de 12 que revisé es correcta.

## Integración

- **Archivos:** las imágenes se recodificaron a un máximo de 720×860 px en JPEG calidad 74 (unos 15 MB en total) y están en `app/src/main/assets/saints/images/<key>.jpg`, junto con `credits.json` y `manifest.json` (foco y tono).
- **Código:** `DevotionImageCredits.saints` reutiliza el catálogo de créditos de las devociones.
- **Ficha del santo:** usa la imagen en el cabezal único y muestra debajo la línea de crédito (`saint_image_credit`), que enlaza a la página del archivo en Commons. Si no hay imagen, mantiene el arte de sección.
- **Pruebas:** `SaintsScreenTest` comprueba el crédito, y `SaintImageScreenshotTest` genera las capturas de las 4 identidades y a 320 dp / 130 %, guardadas en `docs/tasks/T-034-shots/`.
