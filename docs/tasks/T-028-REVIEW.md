# T-028 · Revisión del coordinador

- **Entrega:** `ext/T-028` @ `b0ee4a4`
- **Estado:** **APROBADO · CERRADO**, integrado en `CatholicGPT`

## Comprobado

- **Privacidad:**
  - `ConfessionDraft` vive solo en memoria y su `Saver` guarda un marcador constante.
  - El borrador se vacía al salir (`active = false`) y al destruirse la pantalla.
  - La pantalla no recibe DAO ni servicio de copia.
  - Lo único persistente es `confessionProfileId`, limitado a 5 ids públicos.
  - Los tests comparan una instantánea de Room y DataStore antes y después de usar la pantalla.
- **Contenido:** procede sin cambios de T-027. La fuente de cada acto de contrición es visible.
- **Diseño:**
  - `AppNotesField` vive en `AppComponents.kt` y usa el material de la identidad activa;
  - el teclado no ofrece sugerencias;
  - la pantalla usa el cabezal único.
  - Revisé las capturas de las 4 identidades y las de 320 dp al 130 %. A ese ancho, el control segmentado se desplaza en horizontal, lo cual es aceptable.
- **Navegación:**
  - a la pantalla se llega por `nav_confession` (desde Explorar) y por `spiritual_confession_entry`;
  - el rótulo corto «Más» en ≤ 360 dp es una mejora razonable.

Gracias: el informe es claro y no exagera lo que se verificó.
