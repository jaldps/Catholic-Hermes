# Ediciones católicas descargables

Las 7 ediciones se ofrecen en el selector de Biblia, agrupadas por idioma. La Nova Vulgata está disponible con la app en cualquier idioma.

## Procedencia de los datos

- **Texto.** Sale de [catholicbibletools/cbt](https://github.com/catholicbibletools/cbt), commit `0568883103855235b4667c8958c5c082db60a420`. Lo convierte `tools/bibles/convert_cbt.py`.
- **Alojamiento.** Los archivos están en la rama `bibles-data`, commit `a4895797797ae11a9d32b005e03774349420e2df`. La app los descarga libro a libro desde esa URL fija y verifica cada archivo con sha256 y número de versos (`app/src/main/assets/bible/download_manifest.json`).
- **Referencias cruzadas.** Vienen de OpenBible.info (CC BY 4.0). Para cada edición se generó un `xref.bin` en su propia numeración, con `tools/xref` (`build_assets.py --editions`). En cada edición la auditoría de 52 referencias conocidas dio 52/52, y ninguna referencia apunta a un verso inexistente.

## Ediciones

| Id | Edición | Libros | Versos (≈ último n.º por capítulo) | Tamaño de xref |
|---|---|---|---|---|
| `en.ccb` | Christian Community Bible | 73 | 35532 | 2090 KB |
| `en.drbcr` | Douay-Rheims (Challoner Revision) | 73 | 35804 | 2090 KB |
| `es.eldpdd` | El Libro del Pueblo de Dios | 73 | 35558 | 2090 KB |
| `es.lbl` | La Biblia Latinoamericana | 73 | 35540 | 2090 KB |
| `es.lsb` | La Santa Biblia | 73 | 35566 | 2091 KB |
| `pt.bs` | Bíblia Sagrada | 73 | 35436 | 2091 KB |
| `la.nvbse` | Nova Vulgata | 73 | 35638 | 2091 KB |

## Derechos

El dueño del proyecto declara (15‑11‑2026) que estos textos son de uso libre y que cuenta con permiso para usarlos en la app. La identificación detallada de cada edición (editor, año y atribución) se documenta en T-010, que es documental y no bloquea la publicación.

## Correcciones aplicadas a la fuente

Las anota el conversor en su informe:

- **en.ccb:** los versos 19–24 aparecían repetidos al final de Juan 5; se movieron a Juan 7.
- **en.drbcr:** se renumeraron las claves de los Salmos 115 y 147.
- **pt.bs:** en Ester 10, la clave «3l» se corrigió a «31».
- **pt.bs, la.nvbse:** los subversos de las adiciones de Ester se fusionaron en su verso base.
