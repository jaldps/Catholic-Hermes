# T-072 · Revisión 1 (crítico independiente y coordinador, 28-nov)

**Veredicto: CAMBIOS.** No se integra todavía.

Revisado: `ext/T-072` @ `de79ca5`, 6 commits sobre `7f4b78c`, con las 40 capturas y el REPORT.

El trabajo va en buena dirección: el orden de Inicio, la Búsqueda con pozos de icono y Confesión con diálogo de confirmación. Pero hay cinco defectos que bloquean.

## 0. Urgente: capturas con datos personales

`docs/tasks/T-072-shots/details/orden/orden-detalle-dia.png` y `orden-detalle-dia-2.png` muestran el **escritorio y el cajón de apps del teléfono del dueño** (apps bancarias, mensajería).

1. **Reescribe el commit `de79ca5`** sin esos dos PNG: `git rebase -i` o `git commit --amend` en el último commit, y después `git push --force-with-lease` a tu rama `ext/T-072`. Así no quedan en el historial del fork.
2. Antes de cada captura, comprueba que la app está en primer plano en la pantalla correcta. Si aparece una burbuja de otra app, ciérrala.

## Bloqueos (H)

### H1. Explorar en vertical deja destinos sin acceso

`MoreScreen.kt:251-285` ya no pinta `churchToday`, `innerLife` ni `leftovers`, aunque los sigue calculando.

| Destino | Estado |
|---|---|
| Parroquias (`nav_parishes`) | Solo desde un paso de Primeros pasos |
| Noticias de la Iglesia (`nav_churches_news`) | **Sin acceso** |
| Catálogo de Planes de lectura (`nav_plans`) | Sin acceso, salvo un plan ya empezado |
| `preferences_recovery_settings` | No está en `SettingsTags` |
| Calendario y santuarios (`nav_pilgrimage`) | Escondido |

La regla de AGENTS.md: se puede quitar lo redundante, pero **ningún destino puede quedar sin acceso**.

**Arreglo:**
1. Después de «Descubre», un grupo de filas **«Iglesia cerca de ti»** con Parroquias, Calendario y santuarios, y Noticias.
2. Un grupo **«Leer más»** con Planes de lectura y Cursos bíblicos (`nav_plans`, `nav_scripture_study`).
3. `preferences_recovery_settings` en `SettingsTags`.
4. Corregir el KDoc («nothing is ever dropped»).

### H2. La semana litúrgica de Inicio está mal

`HomeScreen.kt:470-472` busca el número con una regex y escribe siempre «Tiempo Ordinario» / «Tempo Comum».

- **Desde el lunes 30-nov (Adviento)**, en portugués saldrá «Semana 1 do Tempo Comum». Lo mismo pasará en Cuaresma y Pascua.
- Cuando la regex encaja, **se pierde la regla de ayuno**, como la abstinencia de los viernes de Cuaresma.
- **En español e inglés** el formato no encaja nunca, así que el brief no se cumple.
- La tarjeta repite el tiempo: «Tempo Comum» y debajo «Semana 34 do Tempo Comum».

**Arreglo:** construir el texto **con los datos del calendario**, sin regex:
- en los días feriales, «Semana XXXIV» / «Week 34» / «34.ª Semana»;
- en las fiestas, el título de la celebración;
- en los dos casos, « · » más la regla de ayuno si la hay.

### H3. Se perdieron etiquetas de test estables (AGENTS.md: se conservan)

- **Desaparecen:**
  - `home_gate_*`;
  - `nav_plans`, `nav_saints`, `nav_scripture_study`, `nav_parishes` y `nav_churches_news`; los `nav_*` vuelven con H1;
  - `my_path_today` en vertical.
- **Cambian de nombre:** `saint_image_credit`, `devotion_image_credit` y `order_emblem_credit`. Vuelve a ponerles su nombre antiguo, en el nodo nuevo.
- **Cambia de destino:** `home_open_temptations` ahora abre Hábitos. O vuelve a abrir Tentaciones, o se quita junto con su fila; nunca un tag con otro destino.
- **Mal colocado:** `home_fidelity_open_habits` está en un `Box` que no se puede pulsar. Ponlo en la propia fila.

Antes de entregar, haz `git diff 7f4b78c -- app | grep -E '^-.*testTag|^-.*tag = '` y justifica cada línea que quede.

### H4. Capturas que no muestran lo que dicen

- Las dos de `orden-detalle-dia*` están en el punto 0.
- `devocion-detalle-dia.png` y `-dia-2.png` son el mismo archivo y muestran Explorar › Ajustes.
- Las del santo de día no enseñan la imagen 4:5.
- **Falta** la secuencia de Confesión: lista › «Terminar y borrar» › diálogo.

Vuelve a capturar y míralas antes de subirlas.

### H5. La tarjeta «Hoy en mi camino» va de borde a borde

`MyPathScreen.kt:204`: le falta `.padding(horizontal = design.spacing.gutter)`.

## Antes de publicar (M)

- **M1. «Comienza aquí»** sale dos veces, en Inicio y en Explorar, y nunca desaparece.
  - Quítalo de Explorar.
  - En Inicio, ocúltalo cuando se completen los pasos. Si `BeginnerJourneyStore` no tiene el dato de completado, pídelo en el REPORT.
  - Un solo texto: «Comienza aquí».
- **M2.** «Elige tu ritmo» y «Ahora no» usan `TextButton` de Material directamente (`HomeScreen.kt:460-461`). Usa el botón de texto del kit.
  - La parte compartida, que `colorScheme.primary` salía verde litúrgico, ya la corrige el coordinador.
- **M5. Mi camino:**
  - «Hábitos» aparece dos veces: haz clicable la tarjeta entera, sin la fila repetida;
  - el plural sale mal («1 días seguidos»);
  - el hueco antes de «Secciones» está duplicado (:234 + :257);
  - se perdieron los planes en curso y el «· N %» de las novenas: vuelven todos los planes y dos novenas con su porcentaje.
- **M6. Iconos fuera de §6 (uno por concepto):**
  - Descubre: 5 mosaicos con el mismo icono `HistoryEdu`;
  - Inicio: Novenas lleva el libro de Leer, y Confesión y «Abrir Mi camino» comparten `Spa`;
  - Mi camino: Hábitos y Tentaciones comparten el escudo, y Diario lleva el libro de Leer;
  - Confesión tiene un icono distinto en Inicio y en Mi camino;
  - Búsqueda (`SearchScreen.kt:272-278`): el Catecismo lleva el icono de la Biblia y los Planes el del Catecismo.
  - Aplica la tabla de §6.
- **M7. Fichas de santo, devoción y orden:**
  - en la orden, el emblema sale dos veces (placa pequeña y 200 dp en una tarjeta 4:5 casi vacía): en las órdenes, sin tarjeta 4:5;
  - un solo modo de presentar la imagen de contenido, alineada arriba (la cabeza de la Guadalupe toca el borde);
  - **el crédito vuelve a ser clicable**, porque CC BY exige el enlace;
  - «Public domain» se traduce: «Dominio público» / «Domínio público».
- **M8. Ficha de santo:**
  - hay dos «volver»: quita «Todos los santos» si repite la flecha del banner;
  - de noche hay una franja opaca de unos 165 px sobre el banner (`santo-detalle-noche.png`): comprueba que no haya una cabecera doble.
- **M9. Confesión:**
  - el selector de secciones se corta al 130 % («O sacramer»);
  - «Entendido» y «Elegir perfil» son dos botones de contorno iguales seguidos: el primero pasa a botón de texto;
  - el aviso de privacidad debe usar los wrappers.

## Pendiente de decisión del dueño (no lo cambies todavía)

- **M3.** El orden de Inicio frente a §7: la Liturgia del día como fila y «Oración del momento» (oración actual y siguiente, o Rosario y Coronilla).
- **M4.** La búsqueda aparece dos veces en Inicio: la lupa del banner y la fila `home_search_row`.

## Para la capa compartida (no es tuyo)

Lo resuelven el coordinador o Chaty en T-073:
- el velo bajo la barra de estado (M10);
- el banner de detalle igual en todas partes (M12);
- el botón destructivo;
- `AppCard(featured = true)`.

## Pulido (L)

- **REPORT:**
  - quita el token «默认值»;
  - describe lo que hay de verdad en el código, por ejemplo que la fila dice «Hábitos»;
  - no pongas «sin defectos» sin el crítico.
- **Explorar:**
  - los valores de Apariencia, «Día / Noche / Sistema»;
  - las filas en SemiBold, como en Inicio;
  - quita `height = 232.dp`.
- **Confesión:** el diálogo de borrado añade «No se puede deshacer.».
- **Ficha de santo:** TalkBack lee dos veces el nombre del santo.
- **Mi camino:** el anillo, 72 dp con la cifra en `title1` (§5.9).
- **Inicio:** quita los parámetros sin usar de `FidelityCard` y los imports muertos (:33-45).

## Entrega

En la misma rama `ext/T-072`:
1. primero, el punto 0 (push forzado);
2. después, un commit por bloque: H1+H3, H2, H5+M5, M1+M2, M6, M7+M8, M9.

Capturas nuevas al 60 % y REPORT actualizado. El crítico revisa otra vez **solo** estos puntos.
