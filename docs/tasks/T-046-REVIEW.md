# T-046 · Revisión del coordinador

- **Entrega:** fork `joselanzd-hash/Catholic-Hermes`, rama `ext/T-046` @ `99ca7df` (4 commits).
- **Veredicto:** **APROBADO · CERRADO.** Integrado en `CatholicGPT` y publicado en la 1.8.29.

## Alcance

Solo toca los archivos permitidos:
- `MainApp.kt`, `AppIconSet.kt` y `MyPathScreen.kt`;
- los tests afectados, más el test de capturas nuevo;
- el informe y las capturas.

Los commits son pequeños y en el orden pedido.

## Código

- **Fila única «Hábitos».**
  - Reutiliza literalmente los textos de progreso y racha, unidos por « · ».
  - Si `habits == null` muestra solo la racha.
  - Conserva el tag `my_path_today_habits`.
  - Las filas `my_path_today_fidelity` y `my_path_section_habits` desaparecen, con sus tests adaptados y sin quitar aserciones.
- **Quinta pestaña.**
  - `tabs = listOf(HOME, PRAYERS, BIBLE, MY_PATH, MORE)`.
  - Volver a tocarla devuelve al hub.
  - Los deep links, la búsqueda, los avisos y la bienvenida dejan seleccionada «Mi camino», y «atrás» lleva al hub.
  - La pila inicial de un destino absorbido es `MY_PATH`, no `MORE`.
- **Icono `AppIconSlot.PATH`:** `Route` en los cuatro estilos.
- **Explorar:** ya no repite «Mi camino». Confesión sigue ahí.

## Decisiones de Hermes que se aceptan

1. **Etiqueta corta «Ruta» / "Path" / «Rota»** en vez de «Camino».
   - Está medida: «Camino» y «Caminho» se partían a mitad de palabra en la columna de 48 dp al 130 %.
   - El diseño horizontal (§3.5) adopta la misma etiqueta para el riel.
2. **Los atajos de «Hoy»** conservan su vuelta a «Hoy». Es razonable: el encargo no los mencionaba.

## Tests

Las fallas por `AppNotIdleException` del informe venían del entorno de Windows de Hermes; también fallaban en su commit base. Aquí se repitieron todos con el lock de Gradle:

| Clase | Resultado |
|---|---|
| `MyPathUiTest` | 13/13 |
| `MyPathSummaryTest` | 3/3 |
| `DesignNavigationTest` | 8/8 |
| `TabletLayoutTest` | 44/44 |
| `NarrowLargeTextAuditTest` | 8/8 (a 320 dp y 130 %, sin hallazgos nuevos) |
| `SearchUiTest` | 8/8 |
| `AppNavigationTest` | 5/5 **tras un ajuste del coordinador** |
| `WelcomePersonalizationTest` | 16/16 **tras un ajuste del coordinador** |

**Dos tests que Hermes no pudo correr en su entorno:**
- **`AppNavigationTest`** seguía buscando «Mi camino» en Explorar. Ahora:
  - comprueba que ya **no** está ahí;
  - lo abre desde la pestaña `nav_my_path`;
  - entra en Hábitos, vuelve al hub y regresa a Explorar por su pestaña.

  No se quitó ninguna aserción.
- **`WelcomePersonalizationTest`** fallaba en 3 de 4 identidades, las del dock flotante:
  - al quitar la fila «Mi camino», las filas de Explorar subieron;
  - `performScrollTo` dejaba «Perfil» en el borde inferior (825–891 dp), debajo del dock (820–876), y el toque caía en el dock. Se midió con un diagnóstico.
  - Ahora el test desplaza 160 dp más antes de tocar. Si la lista no pudiera moverse, el toque seguiría fallando, así que el test sigue vigilando un solapamiento real.
  - En la app no hay defecto: Explorar se puede seguir desplazando.

## Capturas

Revisadas `_docks.png` y los hubs de las cuatro identidades, también a 320 dp con letra al 130 %:
- 5 pestañas completas, sin etiquetas partidas ni iconos montados;
- una sola fila «Hábitos» con el subtítulo combinado.

## Nota menor (no bloquea)

«Tentaciones y victorias» usa el mismo escudo que «Hábitos», como ya pasaba antes. Si se quiere distinguir, se resuelve en L6.
