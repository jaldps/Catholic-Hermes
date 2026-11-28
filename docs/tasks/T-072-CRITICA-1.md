# Crítico · T-072 (Catholic1 lote B) y T-069 (capturas del teléfono)

Revisé `fork/ext/T-072` (6 commits sobre 7f4b78c), el brief, el REPORT, las 40 capturas del lote y las 26 de T-069, y apliqué CATHOLIC1.md §1, §5, §6 y §7. Rutas de capturas relativas a `scratchpad/t072/docs/tasks/T-072-shots/`, salvo que diga otra cosa. Las líneas de código son de la rama `fork/ext/T-072`.

## Hallazgos (por gravedad)

### H · bloquean la integración

**H1. Explorar en vertical hace inalcanzables varios destinos.**
- **Qué:** `MoreScreen.kt:251-285` ya solo pinta Buscar, Comienza aquí, Descubre (`study.take(6)`) y Ajustes. `churchToday`, `innerLife` y `leftovers` se calculan (:153-159) pero en vertical no se usan. El comentario de :105-107 («nothing is ever dropped») ya es falso. Inicio, además, quitó la rejilla «Explorar la fe» (Iglesias, Santuarios, Trivia, Vida interior).
- **Destino por destino:**

| Destino | ¿Dónde más se llega? | Estado |
|---|---|---|
| `nav_parishes` · Parroquias | Solo en Primeros pasos, en el botón del paso «Encontrar una parroquia» (`BeginnerJourneyScreen.kt:239`). | **Perdido** en la práctica |
| `nav_churches_news` · Noticias | En ningún sitio. | **Perdido** |
| `nav_plans` · Planes de lectura (catálogo) | Solo un plan ya empezado en Mi camino, o un plan concreto en la Búsqueda. Leer no tiene planes. | **Perdido** |
| `preferences_recovery_settings` · Revisar cambios no guardados | No está en `SettingsTags`, así que cae en `leftovers`. Solo queda el diálogo automático al arrancar (`MainApp.kt:431`). | **Perdido** |
| `nav_pilgrimage` · Calendario y santuarios | Al tocar la tarjeta del tiempo litúrgico en Inicio (`HomeScreen.kt:484`). Funciona, pero no se ve que se pueda tocar. | Escondido |
| `nav_scripture_study` · Cursos bíblicos | Leer › Estudio. | OK |
| `nav_saints` · Santos | Mosaico de Inicio, Búsqueda y Santo del día. | OK |
| `nav_confession` · Confesión | Mosaico de Inicio y Mi camino. | OK |
| Trivia / Vida interior (antes en Inicio) | Mi camino › Prácticas. | OK |

- **Arreglo:**
  1. Después de «Descubre», pinta `churchToday` como grupo de filas «Iglesia cerca de ti»: Parroquias, Calendario y santuarios y Noticias.
  2. Después pinta `leftovers` como grupos de filas: Planes de lectura y Cursos bíblicos. Santos y Confesión se pueden quitar porque ya están en Inicio.
  3. Añade `"preferences_recovery_settings"` a `SettingsTags`.
  4. Corrige el KDoc.

**H2. La semana litúrgica de Inicio es falsa en portugués y no cambia nada en español ni en inglés.**
- **Qué:** `HomeScreen.kt:470-472` busca `(\d{1,2})\.?[ªa]?\s+Semana` y siempre escribe «del Tiempo Ordinario» / «do Tempo Comum».
  - El título portugués es `"$dayPt da N.ª Semana do/da <tiempo>"` (`LiturgicalCalendar.kt:411-415`). Por eso, desde el **lunes 30-nov-2026** saldrá «Semana 1 do Tempo Comum» en Adviento, y lo mismo pasará en Cuaresma y Pascua. Cuando la regex encaja, también se pierde `fastingRule`: los viernes de Cuaresma desaparece la abstinencia.
  - En español el título es «Sábado de la semana XXXIV…» (romano, y «semana» va antes del número), y en inglés va con la palabra escrita. Ninguno encaja, así que **sigue saliendo el día de la semana duplicado**: el brief no se cumplió.
  - Además, la tarjeta repite el tiempo litúrgico: «Tempo Comum» / «Semana 34 do Tempo Comum» (`home/despues/home-dia.png`, tarjeta y≈1095-1230).
- **Arreglo:** construir el detalle con los datos y no con una regex sobre texto:
  - en días feriales, «Semana XXXIV» / «Week 34» / «34.ª Semana», sin el nombre del tiempo, que ya es el título;
  - en fiestas, `celebrationTitle`;
  - siempre `+ " · " + fastingRule` cuando exista.
  - Si se prefiere texto, quitar el prefijo `^(Lunes|…|Domingo|Monday|…|Segunda-feira|…)\s+(de la|del|of the|da|do)\s+` y después el tiempo, como ya hace `seasonDetail`.

**H3. Se borraron o se cambiaron etiquetas de test estables (AGENTS.md), y unos 15 tests romperán.**
- **Desaparecen:**
  - `home_gate_*` (MyPathUiTest:210-216);
  - `nav_plans` (ReadingPlansUiTest:62, CrossReferencesUiTest:129/151);
  - `nav_saints` (SaintsScreenTest:60, TabletLayoutTest:148-197, porque en tableta la vista no es horizontal de teléfono);
  - `nav_scripture_study` (GroupedPickersTest:67, StudyJourneyTest:60);
  - `nav_parishes` (AppNavigationTest:169);
  - `nav_churches_news` (ExploreScreenshotTest:97);
  - `my_path_today` en vertical.
- **Cambian de nombre:** `saint_image_credit`, `devotion_image_credit` y `order_emblem_credit` (SaintsScreenTest:80, DevotionsScreenTest:111-119, OrdersScreenTest:92, SaintImageScreenshotTest:66).
- **Cambia de significado:** `home_open_temptations` ahora abre Hábitos (`HomeScreen.kt:1062-1070`; JournalTemptationInteractionTest:224, TemptationGuideInteractionTest:74).
- **Arreglo:**
  - mantener los tags antiguos en los nodos nuevos: el crédito nuevo lleva el tag viejo y la tarjeta de Mi camino lleva `my_path_today`;
  - con H1 vuelven los `nav_*`;
  - la fila «Abrir Mi camino» lleva `home_fidelity_open_habits` en la propia fila, no en un `Box` sin clic;
  - `home_open_temptations` no debe apuntar a otro destino: se retira y se actualizan sus tests, o la fila vuelve a abrir Tentaciones.

**H4. Hay capturas falsas, y una expone el teléfono personal del dueño.**
- **Qué:**
  - `details/orden/orden-detalle-dia.png` y `-dia-2.png` son el **escritorio y el cajón de apps del dueño**, con apps bancarias, mensajería y más. Están confirmadas en el commit de79ca5.
  - `details/devocion/devocion-detalle-dia.png` y `-dia-2.png` son idénticas (227 513 bytes) y muestran Explorar › Ajustes.
  - Las de santo de día no enseñan la imagen 4:5.
  - No hay ninguna captura de «Minha lista», de «Terminar e apagar» ni del diálogo de borrado.
  - El REPORT afirma que todo está verificado.
- **Arreglo:** reescribir de79ca5 sin esos PNG para que nunca lleguen a `origin`, y volver a capturar de Día: devoción, orden, santo con la tarjeta 4:5, y Confesión › lista › borrar › diálogo.

**H5. La tarjeta «Hoje no meu caminho» va sin margen lateral, de borde a borde.**
- **Evidencia:** `mypath/despues/mypath-dia.png` y `-noche.png`: la tarjeta va de x=0 a 732, y los grupos de abajo van de 35 a 697. Viene de `MyPathScreen.kt:204` `AppCard(Modifier.fillMaxWidth()…)`.
- **Arreglo:** `.padding(horizontal = design.spacing.gutter)`.

### M · corregir antes de publicar

**M1. «Comienza aquí» está duplicado y nunca desaparece.**
- **Qué:**
  - Sale con el mismo oro y el mismo destino en Inicio (`HomeScreen.kt:450-466`) y en Explorar (`MoreScreen.kt:251-259`; `more/despues/more-dia.png` y-630-1035). §7 Explorar 3 lo prohíbe.
  - En Inicio no hay ninguna condición de «completado» (§7 Inicio 4).
  - En español se llama «Comienza aquí» en Inicio y «Empieza aquí» en Explorar.
- **Arreglo:**
  - En Inicio, mostrarlo solo mientras `BeginnerJourneyStore` no esté completo. Hace falta un `completed`: hoy solo existe `step` 0..6.
  - En Explorar, solo cuando Inicio no lo muestra, que en la práctica es quitarlo.
  - Un único texto: «Comienza aquí».

**M2. El color litúrgico se cuela fuera del dock (§2.3).**
- **Evidencia:**
  - «Escolha seu ritmo» / «Agora não» en verde: `home/despues/home-dia.png` y≈880-930 y `home-noche-2.png`.
  - El cursor del buscador en verde: `search/search-dia.png`, campo.
- **Arreglo:**
  - `HomeScreen.kt:460-461` usa `material3.TextButton` crudo: hay que cambiarlo por un wrapper terciario con `link` (#8A5E14 / #E3B65A).
  - En la capa compartida, `colorScheme.primary` no debe ser el color litúrgico. Es una petición al coordinador.

**M3. El orden de Inicio no sigue §7.**
- **Qué:**
  - «Liturgia de hoy» (`daily_mass_card`) sigue siendo una tarjeta grande **después** de «Mis prácticas». §7 la pone como fila dentro de «Hoy en la Iglesia».
  - «Oración del momento» muestra la oración de ahora y la siguiente, no Rosario + Coronilla + la coronilla sugerida.
- **Arreglo:**
  - poner una fila «Liturgia del día» bajo la tarjeta del tiempo y llevar el bloque del Evangelio a Leer, o dejar la tarjeta justo después de «Hoy en la Iglesia»;
  - para la Oración del momento, decisión del dueño si se acepta la variante.

**M4. La búsqueda sale dos veces en Inicio.**
- **Evidencia:** el botón del banner (`global_search_button`, `HomeScreen.kt:406-411`) y la fila `home_search_row` (:423-433); se ven juntas en `home-noche.png`.
- §5.1 y §7 piden las dos cosas, pero el principio 3 dice «cada cosa una vez».
- **Puerta para el dueño.** Por defecto: quitar la fila, porque ocupa unos 100 dp al 130 % y el banner ya ofrece la lupa.

**M5. La tarjeta de Mi camino no es la tarjeta destacada del plano.**
- **Qué:**
  - «Hábitos» aparece dos veces, como título de la tarjeta y como fila (`mypath-dia.png` y 560-800).
  - No lleva el baño `ivory.100` al 60 % (§5.3). `AppCard` no tiene esa variante y el REPORT dice «Peticiones: ninguna».
  - Hay un hueco doble antes de «Seções» (:234 + :257 `padding(top = sectionGap-8)`).
  - Se rompe el plural: «1 días seguidos» (:228).
  - Se pierde progreso: solo se muestran el primer plan y la primera novena (:237, :244), y se quitó el «· N %».
- **Arreglo:**
  - toda la tarjeta clicable hacia Hábitos, sin la fila repetida;
  - racha con plural en el subtítulo;
  - todos los planes y 2 novenas, con «· N %»;
  - pedir `AppCard(featured = true)` a la capa compartida.

**M6. Iconos fuera de §6: uno por concepto, sin repetir.**
- **Evidencia:**
  - Descubra tiene 5 mosaicos con el mismo `HistoryEdu` (`more/despues/more-dia-2.png`).
  - En Inicio: Novenas = `AutoStories` (el libro de Leer en el dock); Confissão = `Spa` (Prácticas); «Abrir Meu caminho» también `Spa` (`HomeScreen.kt:441-442`, :1066).
  - En Mi camino: Hábitos y Tentações comparten escudo, que es justo el error que §6 corrige; Diário usa el libro de Leer.
  - Confesión es `Spa` en Inicio y `Church` en Mi camino.
  - En Búsqueda (`SearchScreen.kt:272-278`): el Catecismo lleva el icono de la Biblia, Planes y Cursos el del Catecismo, los Santos el de Doctrina, y el resto el de Explorar.
- **Arreglo, según la tabla de §6:** Catecismo `school`, Novenas `DevotionalGlyphs.rosaryOutlined`, Confesión `church`, Santos `Person`, Hábitos `check_circle`, Diario `edit_note`.
  - Pedir al coordinador un glifo propio por destino de estudio, por ejemplo: Doctrina `library_books`, Historia `history_edu`, 101 preguntas `quiz` y Órdenes `groups`.

**M7. Las fichas de santo, devoción y orden se presentan de forma distinta y pierden el enlace del crédito.**
- **Qué:**
  - **Orden:** el emblema sale dos veces, la placa pequeña y otra vez a 200 dp dentro de una tarjeta 4:5 casi vacía (`details/orden/orden-detalle-noche.png` y 620-790 y 960-1410).
  - **Tres tratamientos distintos:** la devoción es un `Box` recortado sin tarjeta; el santo es un `AppCard` con la imagen metida dentro y radios que no son concéntricos; la orden, `AppCard` con el emblema.
  - **Créditos:** perdieron el `clickable { openUri }`, que es la atribución CC-BY con enlace.
  - **Copia:** en PT pone «Imagem: Public domain…», sin traducir (`devocion-detalle-noche.png` y≈1410).
  - **Recorte:** la cabeza de la Guadalupe toca el borde superior.
- **Arreglo:**
  - un solo componente `ContentImageCard(model, credit, tag)` o una petición `AppContentImage`: imagen 4:5 a sangre dentro de la tarjeta y `Alignment.TopCenter`;
  - crédito clicable debajo, con el tag antiguo;
  - en órdenes, sin 4:5: la placa existente y el crédito debajo.

**M8. Ficha de santo: dos «volver» y una cabecera rara de noche.**
- **Qué:**
  - Muestra la flecha del banner y además «Todos os santos» (`santo-detalle-noche.png` y 555-640), con el mismo destino.
  - De noche aparece una franja opaca de unos 165 px sobre el banner, que queda más bajo (+27 px), más corto y sin logotipo (y 0-320). No ocurre en devoción ni en orden. Hay que verificar si es un doble header (lista de Santos + `AppDetailHeader`).
- **Arreglo:** quitar «Todos os santos» cuando hay flecha y revisar el contenedor.

**M9. Confesión.**
- **Qué:**
  - El segmentado se corta al 130 %: «O sacramer» (`confession/confesion-dia.png` y 485-575).
  - Hay dos botones de contorno a ancho completo seguidos y con el mismo peso («Entendi» y «Escolher perfil»).
  - El aviso de privacidad está montado a mano con `background()` (`ConfessionScreen.kt:140-147`), sin wrapper.
  - «Terminar y borrar» no es rojo.
- **Arreglo:**
  - segmentado que admita 2 líneas, o etiqueta corta («Sacramento»);
  - «Entendido» como texto/terciario;
  - wrapper `AppNotice(sunken)`;
  - apoyo la petición de `AppDestructiveButton`.

**M10. Capa compartida (para el coordinador): barra compacta translúcida y sin velo bajo la barra de estado.**
- **Evidencia:**
  - El contenido se transparenta bajo el título y los iconos de estado: `home-dia-2.png`, `mypath-dia-2.png` y `confesion-noche-2.png` (y 0-160).
  - En algunos puntos del desplazamiento no hay barra y el texto choca con la hora y la batería: `home/despues/home-dia.png` y 0-100, y la captura de Explorar dentro de `devocion-detalle-dia.png` («101 perguntas» y «Ordens e ofícios» bajo los iconos).
- **Arreglo:** un velo opaco `canvas` del alto de `WindowInsets.statusBars`, siempre por encima de la lista cuando el banner sale, y una barra compacta opaca.

**M11. Capa compartida: de noche, la píldora activa del dock y el segmento activo son oro** (`home-noche.png`, dock; `confesion-noche.png`, segmentado). Con el CTA son tres rellenos de oro en una sola vista. §5.2 y §5.5 dicen `brand` con `onBrand`. Es una decisión del coordinador o del dueño.

**M12. Capa compartida: el banner de detalle no es igual en todas las fichas.**
- **Evidencia:** en Confesión y Búsqueda el botón volver tapa el símbolo, que asoma por la derecha (`confesion-dia.png` x 25-105, y 85-160). Devoción y orden no tienen logotipo.
- **Arreglo:** según §5.1, en el detalle va solo la flecha, sin símbolo ni logotipo.

### L · pulido

- **L1. REPORT:**
  - token chino suelto: «con默认值 compatibles» (§1);
  - en §2 dice que la fila es «Abrir Mi camino», y el código dice «Hábitos / Abrir mis hábitos»;
  - «Sin defectos abiertos» y «Peticiones: ninguna» no son ciertos;
  - el crítico fue «autocrítica».
- **L2. Inicio:**
  - la barra compacta dice «Hoje» y el título grande «Bem-vindo!»;
  - en inglés el patrón es «Ordinary Time · Week n», distinto del español y el portugués;
  - los iconos de los mosaicos van en marino, cuando §5.3 los pide en acento;
  - quedan `onOpenTemptations` y `largeLabels` sin usar en `FidelityCard` (:1026-1035) e imports muertos `Church`, `GridView`, `Place` y `StarOutline` (:33-45).
- **L3. Explorar:**
  - los valores «Claro / Escuro / Automático» no siguen «Día / Noche / Sistema»;
  - las filas van en Regular, mientras en Inicio van en SemiBold (`more-dia.png` «Buscar» frente a `home-noche.png`);
  - `height = 232.dp` / `120.dp` sobran por cada pantalla (:228);
  - el KDoc (:120-133) describe el diseño antiguo.
- **L4. Búsqueda:**
  - «55 resultados» igual con «Deus» que con «Deus de» (`search-dia.png` / `-dia-2.png`): o se capturó durante el retardo, o el contador no se actualiza;
  - en las capturas sale la burbuja de otra app («Flow Bubble»);
  - con el teclado abierto y el banner de 176 dp solo se ven unos 1,3 resultados. Es una puerta para el dueño: plegar el banner con el IME.
- **L5. Diálogo de borrado:** añadir «No se puede deshacer.».
- **L6. Imagen del santo:** `contentDescription` es el mismo título, así que TalkBack lo lee dos veces. Mejor «Imagen de …».
- **L7. De noche, los pozos de icono de Ajustes no se distinguen** (`more-noche-2.png`): `sunken` y `surface` están demasiado cerca. Es de la capa compartida.
- **L8. Anillo de Mi camino:** mide 76 dp con cifra en `title2`; §5.9 pide 72 dp, cifra en `title1` y unidad en `footnote`.

## Veredicto T-072: **CAMBIOS**

Antes de integrar:
1. **H1:** recuperar Parroquias, Noticias, Planes y Revisar cambios, y hacer visible Calendario.
2. **H2:** semana litúrgica con los datos, sin la regex.
3. **H3:** recuperar los tags.
4. **H4:** reescribir de79ca5 sin las capturas personales y repetir las que faltan.
5. **H5:** margen de la tarjeta de Mi camino.
6. **M1, M2 (lado pantalla), M5, M6, M7 y M8.**

M3, M4 y M11 necesitan decisión del dueño. M10 y M12 son para la capa compartida.

## Lo que revelan las capturas del teléfono (T-069)

La APK es de 5aa37a2 y es anterior a bd0f034. El banner de T-069 es el antiguo; el de las capturas de T-072 es el nuevo.

- **Iconos de estado sobre el banner:**
  - **Antiguo:** la wifi, la cobertura y el «5G», en blanco sobre el velo crema de la Virgen, casi no se leen (`hoy-dia.png` y `leer-dia.png`, x 420-570, y 20-45).
  - **Nuevo:** la Virgen está más baja y los iconos van sobre azul, así que se leen bien (`T-072 home-noche.png`).
- **Botones sobre el rostro:**
  - **Antiguo:** la lupa y la campana tapan la mejilla y la barbilla (`hoy-dia.png` x 445-585, y 85-150), y en Leer el botón de traducir tapa la boca (`leer-dia.png` x 520-585).
  - **Nuevo:** no tapa, pero la onda corta a la Virgen por los ojos y solo se ve la frente (`T-072 home-noche.png` x 530-660, y 155-240): ya no se reconoce.
  - **Propuesta:** reducir la Virgen un 15-20 % y subirla para que la cara entera, con la barbilla, quede entre el borde inferior de los botones (unos 160 px) y la onda, a la derecha de los botones.
- **Tras desplazar sobre marfil:** los iconos pasan a oscuros correctamente, pero no hay velo, y el texto pasa bajo la hora y la batería (`T-072 home-dia.png`). En `santo-noche.png`, «Todos os santos» se desliza bajo la flecha flotante. Es M10.
- **Gesto del sistema:**
  - El dock queda unos 22 dp por encima del asa: correcto.
  - Entre el dock y el asa se ve texto pasando (`santo-dia.png` y 1310-1356). Hace falta un velo bajo el dock (L).
  - En la hoja de Apariencia, el último botón («Baixar vozes melhores») queda bajo el asa (`ajustes-dia.png` y 1310-1356): falta `navigationBars` en el relleno final (M).
- **Recorte de cámara (cutout):** no hay choques; la hora queda a la izquierda, libre.
- **Texto e imágenes:**
  - Inter se ve nítida.
  - Las pinturas (Rubens del Rosario, miniaturas de coronillas) se ven nítidas y sin bandas.
  - La fuente elegida (Inter) se aplica también a la oración, así que la selección funciona.
- **130 % en el dispositivo:** «Meu caminho» parte en 2 líneas en el dock y las demás etiquetas van en una, con lo que las líneas base no coinciden (L).
- **Del ámbito de T-071, visto en el teléfono:**
  - En el Rosario la flecha de volver pisa el segmento «Terço» (`rosario-2-noche.png` x 25-85, y 85-160).
  - El segmentado es de cristal sobre la pintura, cuando el plano dice superficies opacas.
  - V. y R. van en gris y no en acento.
  - El oro grande es «Ouvir» y no «Siguiente» (§7).
