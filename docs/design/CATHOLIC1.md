# Catholic1 · identidad visual única

> **Estado:** **APROBADO v2** (28-nov), con los ajustes del dueño: banner del mismo alto, onda recortada, color litúrgico en el dock y Plus Jakarta Sans.
> **Maquetas:** https://claude.ai/artifact/PNffuMwHmzZtrgFCmXYKAi (Inicio, Orar, Rosario, lector y Mi camino de noche).

## 0. Decisión y alcance

- **Una sola identidad.** Catholic1 sustituye a Lumen, Tradicional, Cristal y Sanctuary. Solo hay dos esquemas: **Día** y **Noche**, y por defecto se sigue el del sistema.
- **Lo que no cambia:**
  - la arquitectura: un solo kit, `LumenKit`, detrás de `AppKit`, y las pantallas solo llaman a los wrappers de `AppComponents.kt`;
  - los contenidos y los tags de test;
  - los ajustes de accesibilidad: tamaño de letra, alto contraste, reducir movimiento y fuente de lectura.
- **Lo que desaparece:**
  - el selector de identidad;
  - los presets de acento y la rueda de color;
  - la pintura distinta en cada cabecera;
  - las ramas de código por identidad (`isSanctuary`, `liquid`, `opaqueMaterial`), al final del trabajo.
- **Ids guardados:** cualquier valor antiguo de `designLanguage` se lee como `CATHOLIC1`.
- **Fuentes del diseño:** los renders del dueño (navegación y composición) y el **símbolo y el logotipo** que él entregó (colores exactos). Ningún valor se inventa: los colores se miden en el logo y el resto viene de los tokens que la app ya tiene. Las desviaciones se marcan como **puerta**, es decir, una decisión del dueño, con su opción por defecto.

Skills usadas: `build-design-system` (capas y nombres), `brand-identity` y `better-colors` (paleta medida), `better-typography`, `better-layout`, `apple-design` (contención y materiales), `dxe-android-native-ui` y `impeccable-native` (Android).

---

## 1. Principios

1. **Sagrado y sereno.** Mucho marfil, el marino da estructura y el oro es el único acento. Nada chillón: sin degradados de colores ni brillos.
2. **Un solo oro de acción por vista.** El botón dorado relleno es la acción principal. Todo lo demás es marino, con contorno o en texto.
3. **Cada cosa una vez.** Ningún destino se repite en la misma pantalla. Los renders repetían Confesión tres veces en Inicio, y Parroquias y Calendario dos veces en Explorar.
4. **El banner es la marca; el contenido es el protagonista.** El banner es sólido, con el logo y la Virgen del símbolo. Las imágenes de contenido (santos, devociones, misterios, coronillas) se quedan en su sitio, dentro de las fichas.
5. **Lectura primero.** La oración y la Escritura van en superficie opaca, con serif, en la medida justa y con interlineado amplio.

---

## 2. Color

### 2.1 Primitivos (medidos en el símbolo del dueño, `catholic1_splash.png`)

| Primitivo | Hex | Dónde aparece en el logo |
|---|---|---|
| `navy.950` | `#0C2848` | Sombra del libro |
| `navy.900` | `#0C2C50` | Manto (sombra) |
| `navy.800` | `#10345C` | Manto y libro (cuerpo) |
| `navy.600` | `#184878` | Pliegue del velo |
| `gold.500` | `#DCA83C` | Estrellas, cantos y rosario |
| `gold.600` | `#D09828` | Canto del libro |
| `gold.700` | `#B47C20` | Cuello (sombra) |
| `ivory.100` | `#FCF4E0` | Rostro y páginas |
| `ivory.200` | `#F8E8BC` | Velo interior |

Neutros: vienen del lienzo y las tarjetas de los renders, a la espera de confirmarlos en las maquetas.

| Primitivo | Hex | Uso |
|---|---|---|
| `paper.50` | `#FCFAF6` | Lienzo marfil (render: `#FCFAF8`) |
| `paper.0` | `#FFFFFF` | Tarjetas |
| `slate.600` | `#5B6475` | Texto secundario |
| `line.200` | `#E9E4DA` | Filetes |

**Exactos del SVG del dueño:** el logotipo usa marino `#0D2E50` y el oro del símbolo va de `#D49C2C` a `#E7B445`. En la construcción, `navy.900` pasa a `#0D2E50` y `gold.500` y `gold.600` toman los valores exactos del SVG. Todos están a menos de ΔE 2 de los medidos.

### 2.2 Roles (semánticos) y contraste medido

| Rol | Día | Noche | Pareja y contraste (día / noche) |
|---|---|---|---|
| `canvas` | `paper.50` | `#0E1A2B` | — |
| `surface` (tarjeta) | `paper.0` | `#162438` | — |
| `surface.sunken` (pozo de icono, campo) | `#F4EFE4` | `#1D2D44` | — |
| `text.primary` | `navy.950` | `#EDE6D8` | sobre `canvas`: **14,3 / 14,1** |
| `text.secondary` | `slate.600` | `#A9B4C6` | sobre `canvas` y `surface`: **5,7 / 7,5** |
| `brand` (banner, píldora activa del dock) | `navy.800` | `navy.950` | `text.onBrand`: **12,6** |
| `text.onBrand` | `#FFFFFF` | `#F2EBDC` | — |
| `accent` (oro: estrellas, onda, iconos de acento) | `gold.500` | `#E3B65A` | Sobre marino: **5,8** (decorativo y grande) |
| `action` (botón principal) | `gold.500` | `#E3B65A` | `text.onAction` = `navy.950`: **6,9 / 7,9** |
| `link` (texto dorado sobre claro: «Ver calendario») | `#8A5E14` | `#E3B65A` | **5,5 / 9,2** |
| `line` (filete) | `line.200` | `#2A3A52` | — |
| `focus` | `navy.600` | `#E3B65A` | Anillo de 2 dp: ≥ 3:1 |

**Puerta P1 · texto de los botones dorados.** En los renders el texto del botón dorado es blanco: blanco sobre `#B68025` da **3,6:1** y no llega al mínimo de 4,5:1.
- **Por defecto:** oro `gold.500` con texto marino `navy.950`, que da 6,9:1.
- **Alternativa:** oro oscuro `#9A6A1A` con texto blanco, que da 4,7:1 pero es menos luminoso.

### 2.3 El color litúrgico (decisión del dueño, 28-nov)

El color del día va **solo en los iconos de la barra inferior** (el dock): en los iconos de los destinos no activos. El destino activo conserva su píldora `brand`. No tiñe nada más: ni fechas, ni banner, ni botones.

**Ajuste nuevo** en Apariencia: «Color de los iconos de la barra», con dos opciones, **Litúrgico** (por defecto) o **Del tema** (marino de día, marfil de noche).

| Color del día | Icono de día (sobre `#FFFFFF`) | Icono de noche (sobre `#162438`) |
|---|---|---|
| Verde | `#2E7D32` · 5,1 | `#7CCB8A` · 8,0 |
| Morado | `#6A3D9A` · 7,6 | `#B9A0E6` · 6,9 |
| Rojo | `#B3261E` · 6,5 | `#F28B82` · 6,5 |
| Rosa | `#B0306A` · 6,0 | `#F2A0C4` · 7,9 |
| Blanco o dorado | `#9A6A1A` (oro) · 4,7 | `#E3B65A` · 8,3 |
| Del tema | `navy.950` · 14,9 | `#EDE6D8` · 12,6 |

Todos los tonos superan 3:1 frente al dock; las etiquetas de texto siguen en `text.primary`. El color no lleva información por sí solo, porque los iconos son los mismos en cualquier tiempo litúrgico.

### 2.4 Noche

- **El marino de día se vuelve lienzo:** `#0E1A2B`, un poco más cálido y profundo que `navy.950`.
- **Superficies:** tarjetas un paso más claras. El oro se aclara un poco y los textos pasan a marfil frío.
- **Banner:** usa `navy.950`, con la Virgen al 85 % para no deslumbrar.
- **Sin blanco puro:** ningún blanco puro sobre negro.

---

## 3. Tipografía

**Fuente de títulos (decisión del dueño, 28-nov): Plus Jakarta Sans**, con licencia OFL y en peso 700. Comparada con el logotipo SVG del dueño frente a otras 11 sans, es la más parecida: la «a» de dos pisos, la «t» con el corte inclinado, el «1» con bandera y la geometría redonda. Se incluye en la app (`res/font`).
- Se usa en `largeTitle`, `title1` y `title2`, y en los títulos de tarjeta grandes.
- La interfaz y el cuerpo siguen en **Inter**, y la lectura en **EB Garamond**.
- En el banner, el logotipo no es texto: es el **SVG del dueño** en versión clara (blanco con el 1 dorado).

| Rol | Tamaño / línea (sp) | Peso | Uso |
|---|---|---|---|
| `largeTitle` | 34/40 | Bold, en Plus Jakarta Sans | Título de cada sección raíz («Meu caminho») |
| `title1` | 28/34 | SemiBold | Título de una página de detalle |
| `title2` | 22/28 | SemiBold | Encabezado de sección («Hoy en la Iglesia») |
| `headline` | 17/22 | SemiBold | Título de fila o de tarjeta |
| `body` | 17/24 | Regular | Texto de interfaz |
| `subhead` | 15/20 | Regular | Subtítulo de fila |
| `footnote` | 13/18 | Regular | Metadatos y créditos |
| `caption` | 12/16 | Medium | Etiquetas del dock |
| `reading` | 19/30, en EB Garamond | Regular | Oración, Escritura, Catecismo |

- Es la escala que ya tiene la app (`AppTypography.lumen`), con un solo cambio: `largeTitle` pasa de SemiBold a **Bold**, como en los renders.
- **Medida de lectura:** 60–75 caracteres; nunca un ancho fijo de 640 dp.
- **Sin mayúsculas sostenidas en las etiquetas.** El antetítulo («Orai sem cessar») va en minúscula, con el tamaño de `subhead`.

---

## 4. Forma, espacio, profundidad y movimiento

Se mantienen los tokens actuales de `LumenShapes`, `LumenSpacing` y `LumenMotion`:
- **Forma:** tarjeta de 28 dp, lista agrupada de 24, botones y campos en píldora, hoja de 34.
- **Espacio:** margen lateral de 20, hueco entre secciones de 28, hueco entre tarjetas de 14, fila de 52 como mínimo.

**Puerta P3 · sombra.** Los renders usan una sombra suave bajo las tarjetas, y la app hoy usa un filete sin sombra.
- **Por defecto:** sombra suave, `y` 4 dp, desenfoque 16 dp, `navy.950` al 8 %, más un filete de 1 dp en `line`.
- **En noche:** sin sombra, solo el filete.

**Movimiento:**
- presión: escala 0,98, 120 ms;
- entrada de hoja: 300 ms con aceleración enfatizada;
- con «reducir movimiento»: sin desplazamientos, solo fundido.

---

## 5. Componentes

### 5.1 Banner (cabecera única)

Todas las cabeceras actuales lo usan: `AppHeroHeader`, `AppDetailHeader` y `AppLargeTitleHeader`.

```
┌─────────────────────────────────────────┐ ← barra de estado (texto claro)
│ ✦  Catholic1                  (🔍) (🔔) │  logo (símbolo 40 dp + logotipo 22 sp)
│                         ✦ ✦  ╭──────╮   │
│                        ✦    │ Virgen│   │  la Virgen del símbolo, en vector,
│                         ✦   │ (vector)  │  a la derecha, recortada por la onda
│                              ╰──────╯   │
╰~~~~~~~~~~~ onda dorada (2 dp, accent) ~~╯ ← borde inferior en onda
  Meu caminho                               ← largeTitle sobre el lienzo marfil
  Acompanhe sua jornada espiritual.         ← subhead secundario
```

- **Fondo:** el **fondo azul atmosférico** del dueño (`source/Fundo-Azul-Marianista-Atmosferico-1-.png`), recortado en *cover*. Debajo va `brand` sólido, que se ve mientras carga la imagen.
- **Virgen:** la **Virgen recortada** del dueño (`source/Virgem-Maria-em-Manto-Azul-Celestial-1-.png`), alineada a la derecha y arriba; la onda la corta por abajo. Es la misma en toda la app, así que es coherente con el logo y se reconoce al instante.
- **Logo:** el símbolo (`Catholic1-logo.svg`, convertido a `VectorDrawable`) de 44 dp más el logotipo «Catholic1», en blanco con el 1 dorado.
- **Noche:** el fondo al 70 % sobre `navy.950` y la Virgen al 85 %.
- **Altura: la misma en toda la app**, en raíz y en detalle (decisión del dueño): **176 dp** contando la barra de estado.
  - La raíz lleva el logo y el logotipo; el detalle, la flecha de volver.
  - El título nunca va dentro del banner.
- **Borde en onda real:** el banner se **recorta** con la forma de la onda, mediante un `Shape` de Compose con `clip`. No se pinta un rectángulo y se tapa con un parche del color del lienzo, porque eso deja un filete recto visible bajo la curva. El trazo dorado de 2,5 dp va por encima, en la misma curva.
  - horizontal: banda compacta de 64 dp (la retomará el rediseño del horizontal).
- **Título:** **fuera** del banner, en el lienzo, a 16 dp de la onda. Así el título siempre es legible y no compite con la imagen. Los renders 7–9 lo ponían dentro; los 5–6, fuera.

**Puerta P4 · sitio del título:**
- **por defecto:** fuera del banner, como en los renders 5 y 6;
- **opción:** dentro, en blanco.

### 5.2 Dock (barra inferior)

- **Forma:** cápsula flotante en `surface`, con la sombra de P3, a 12 dp de los bordes y por encima del gesto del sistema.
- **Contenido:** 5 destinos (Inicio, Orar, Leer, Mi camino, Explorar), cada uno con icono de 24 dp y etiqueta en `caption`.
- **Activo:** píldora `brand` con icono y etiqueta en `text.onBrand`. **Inactivo:** `text.primary`, que tiene contraste suficiente.
- El dock ya no cambia por identidad, así que `FolioDock` y `GlassDock` se funden en uno.

### 5.3 Tarjetas y filas

- **Tarjeta:** `surface`, 28 dp, sombra P3, relleno de 20.
- **Grupo de filas:** `surface` con radio de 24, filetes `line` sangrados desde el texto.
- **Fila:**
  - pozo de icono de 48 dp en `surface.sunken`, con el icono de 24 dp en `brand` (o `accent` si la fila es destacada);
  - título en `headline`, subtítulo en `subhead` y chevron en `text.secondary`.
- **Tarjeta destacada** («Hoy en mi camino»): `surface` con un baño `ivory.100` al 60 % y el mismo radio. Es la única tarjeta tintada de la vista.
- **Accesos rápidos** (Inicio): 4 mosaicos de 1 por 1, con icono de acento y etiqueta. **Sin repetir** lo que ya está en el dock.

### 5.4 Botones

| Tipo | Aspecto | Cuándo |
|---|---|---|
| Principal | Píldora en `action` con texto `text.onAction` y flecha › | **Uno por vista**: «Dar un primer paso» |
| Secundario | Píldora con contorno `brand` de 1,5 dp y texto `brand` | Acciones al lado del principal |
| Terciario | Texto `link` con › | «Ver calendario», «Ver todas» |
| Icono | Círculo de 44 dp con contorno `text.onBrand` al 60 % en el banner (buscar, avisos) | Barra del banner |

Alto mínimo de 48 dp. Estado pulsado: escala 0,98 más un velo de 8 %.

### 5.5 Selector segmentado (Oraciones | Novenas | Noche)

Pista `surface` con sombra P3. El segmento activo es una píldora `brand` con texto `text.onBrand`; los inactivos llevan texto `text.primary` en peso Medium.

### 5.6 Hojas y diálogos

- **Hoja:** `surface`, radio de 34 arriba, asa de 40×5 en `line` y título `headline` centrado; el cierre es un icono de 44 dp.
- **Diálogo:** radio de 32, con un solo principal. La acción destructiva va en texto rojo de iglesia `#9B2C2C` (6,9:1 sobre blanco) y nunca en dorado.

### 5.7 Campos y búsqueda

Píldora `surface` con sombra P3, lupa en `text.secondary` y marcador en `text.secondary`. Con foco: anillo `focus`.

### 5.8 Estados

- **Vacío y error:** ilustración lineal de 96 dp en `accent` y `brand` (P6), título `headline`, una línea de ayuda y **una** acción secundaria.
- **Cargando:** silueta de la tarjeta en `surface.sunken`, con pulso al 40 % (sin pulso si se reduce el movimiento).

### 5.9 Progreso

Anillo de 72 dp con pista `line` y arco `accent`. La cifra en `title1` y la unidad en `footnote`, como «12 días» en los renders.

---

## 6. Iconos

Un solo juego: los **Material Symbols Rounded** de contorno que la app ya usa. Uno distinto por concepto, sin repetir:

| Concepto | Icono | Concepto | Icono |
|---|---|---|---|
| Inicio | `home` | Hábitos | `check_circle` |
| Orar | `folded_hands` (personalizado) | Tentaciones y victorias | `shield` |
| Leer | `menu_book` | Prácticas | `spa` |
| Mi camino | `route` | Diario | `edit_note` |
| Explorar | `grid_view` | Confesión | `church` |
| Catecismo | `school` | Santos | `person_heart`, o halo personalizado |
| Novenas | `rosary` (personalizado, del logo) | Parroquias | `location_on` |
| Calendario | `calendar_month` | Noticias | `newspaper` |

Corrige un error de los renders: allí Hábitos y Tentaciones compartían el escudo, y Parroquias usaba el icono de Confesión.

Iconos personalizados (vector, 24 dp, trazo de 1,75 dp): `folded_hands`, `rosary` (las cuentas y la cruz del logo) y `halo`.

---

## 7. Plano de las pantallas

Cada pantalla, con el banner de §5.1 y lo de debajo.

**Inicio**
1. Saludo (`largeTitle` «¡Bienvenido!» más el subtítulo).
2. Búsqueda.
3. Accesos rápidos: Catecismo, Santos, Novenas y Confesión. Son 4, sin repetir el dock.
4. «Comienza aquí»: la tarjeta con el único botón principal. Desaparece cuando la persona completa los pasos.
5. «Hoy en la Iglesia»: fecha con el punto litúrgico, celebración y «Liturgia del día» como fila.
6. «Oración del momento»: Rosario y Coronilla, más la coronilla sugerida para hoy.
7. «Mis prácticas»: anillo de racha y una fila a Mi camino.

**Orar**
1. Antetítulo, título y segmentado Oraciones | Novenas | Noche.
2. Dos tarjetas grandes con su imagen: Rosario y Coronilla de la Divina Misericordia. Son imágenes de contenido.
3. Filas «Otras coronillas» y «Ayúdame a orar».
4. Mosaicos de categorías.

**Leer**
1. Continuar la lectura (tarjeta destacada).
2. Lecturas de la Misa.
3. Biblia: libros.
4. Catecismo y planes.

**Mi camino**
1. «Hoy en mi camino»: tarjeta destacada de Hábitos.
2. Grupo de secciones: Diario, Tentaciones, Prácticas.
3. Confesión, aparte.

**Explorar**
1. Búsqueda.
2. «Descubre», una rejilla de 2×3 sin repetir nada.
3. «Comienza aquí», solo si no está ya en Inicio.
4. Ajustes.

**Rosario y coronillas**
- Fondo `canvas`. El banner detalle lleva el título del misterio y la imagen del misterio en una tarjeta de 16:9, debajo.
- **Hilo de cuentas** horizontal: cuentas `line`, la actual en `accent` con un halo de 4 dp y las rezadas en `brand`. Las medallas son en forma de rombo.
- **Tarjeta de oración:** opaca, en `surface` y en serif `reading`. Las marcas V. y R. van en `accent` y en negrita; la rúbrica, en `footnote`.
- **Transporte:** Anterior (secundario), «Siguiente» (círculo de 64 dp en `action`) y Escuchar. Es el único oro relleno de la vista.
- **Selector:** Rosario | Coronilla | Otras ▾, con el estilo de §5.5.

**Lectores** (Biblia, Misa, oración, día de novena, Catecismo)
- Banner detalle y título `title1`.
- Hoja de lectura en `surface`, en serif `reading` y con la medida de §3. Los versículos llevan el número en `accent`.
- Barra inferior de lectura: Escuchar, Aa (tamaño y fuente) y Marcar.

**Fichas** (santo, devoción, orden)
- Banner detalle.
- Imagen de contenido en una tarjeta de 4:5, con el crédito debajo en `footnote`.
- Secciones en tarjetas.

**Confesión**
- Banner detalle con aviso de privacidad (tarjeta `surface.sunken`).
- Examen por secciones y lista privada.
- «Terminar y borrar» como acción destructiva (§5.6).

**Ajustes**
- Apariencia: Día / Noche / Sistema, tamaño de letra, fuente de lectura, alto contraste y reducir movimiento.
- Sin identidades ni acentos.

---

## 8. Imágenes

| Necesidad | Fuente | Quién |
|---|---|---|
| Fondo y Virgen del banner | Los **originales del dueño** (`docs/design/catholic1/source/`), en WebP a 2× y 3× | Coordinador |
| Logotipo «Catholic1» | Logotipo SVG del dueño | Coordinador |
| Santos, devociones, misterios, coronillas, órdenes | Las imágenes actuales, que no cambian | — |
| **P6:** ilustraciones lineales de los estados vacíos (vela, libro abierto, rosario, cruz sencilla, paloma) | **Chaty**, si el dueño lo aprueba: genéricas, de trazo en marino y oro sobre transparente | Chaty |

Las cabeceras pintadas por sección (`heroes/lumen/*`) dejan de usarse en la interfaz, pero se conservan en `assets` hasta la limpieza.

---

## 9. Puertas para el dueño

| | Decisión | Por defecto |
|---|---|---|
| P1 | Texto del botón dorado | **Aprobado:** marino sobre oro (6,9) |
| P2 | Fuente de los títulos | **Decidido:** Plus Jakarta Sans |
| P3 | Sombra de las tarjetas | **Aprobado:** sombra suave de día, filete de noche |
| P4 | Título fuera o dentro del banner | **Decidido:** fuera; banner de 176 dp en todas las pantallas |
| P5 | Color litúrgico | **Decidido:** iconos del dock, con un ajuste «Litúrgico / Del tema» |
| P6 | Ilustraciones de los estados vacíos | **Aprobado:** Chaty, con las indicaciones de §8 |

---

## 10. Construcción (tras la aprobación)

1. **Tokens:**
   - `DesignLanguage.CATHOLIC1` y su paleta de día y noche en `DesignTokens.of`;
   - `selectable = [CATHOLIC1]`;
   - `fromId` y `canonicalDesign` llevan todo id antiguo a CATHOLIC1;
   - fuera los presets de acento; el color litúrgico solo en los iconos del dock, con el ajuste «Litúrgico / Del tema»;
   - Plus Jakarta Sans en `res/font`, con su licencia OFL.
2. **Componentes**, en `LumenKit`, `LumenComponents` y `LumenExtras`: banner, dock único, tarjeta con sombra, fila con pozo, botones, segmentado, hoja y estados.
3. **Pantallas**, por lotes (Chaty y Hermes con encargos detallados): quitar los duplicados y aplicar el plano de §7.
4. **Limpieza:** las ramas por identidad y las paletas muertas.
5. **Verificación:**
   - `assembleDebug`;
   - **solo capturas** de las pantallas, en día y noche, al 100 % y al 130 %;
   - el dueño la prueba en su teléfono.
6. **AGENTS.md** pasa a describir una sola identidad. Se publica como **versión 3.0.0**.
