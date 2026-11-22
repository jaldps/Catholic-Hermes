# Rosarios, coronas y coronillas: informe de configuración para una app guía

> **Destinatario:** un LLM que configurará una aplicación de guía espiritual «paso a paso».  
> **Método:** lectura íntegra de las tres webs indicadas: 59 páginas de detalle enlazadas desde el índice de Catholic Doors, 17 paneles desplegables del índice de IVE Arabic y 9 páginas de Hozana (que contienen 11 devociones, porque dos páginas describen cada una dos formas distintas).  
> **Regla de oro:** el texto de las oraciones debe tomarse literalmente de la URL de cada ficha. No generar, parafrasear ni completar oraciones devocionales cuando la fuente no las da.

## 1. Alcance y fuentes

| Índice | URL | Devociones |
|---|---|---|
| Catholic Doors Ministry, *A Treasure of 59 Chaplet Devotions* | https://www.catholicdoors.com/prayers/chaplets.htm | 59 (58 coronillas + 1 rosario josefino) |
| Instituto del Verbo Encarnado (IVE), *Rosarios y Coronas* | https://iveinarabic.org/es/rosarios-y-coronas/ | 17 |
| Hozana, sección *Santo Rosario* (9 páginas) | https://hozana.org/es/oracion/santo-rosario/ | 11 |

Total: **87 devociones** con ficha individual. Los textos de Catholic Doors están en inglés; los de IVE, en español (con algunas erratas de transcripción que se señalan).

## 2. Modelo conceptual para la app

Cada devoción puede describirse con siete campos. Esa es la unidad mínima de configuración:

1. **`instrumento`** — rosario mariano de 59 cuentas, coronilla propia (con colores, medallas, números distintos), chotki de 33 nudos, o ninguno.
2. **`cuentas` / `grupos`** — número total y patrón repetitivo (`5x10`, `7x7`, `13x3`, `3x4`, `1x9`...).
3. **`secuencia`** — lista ordenada de pasos: qué se reza en cruz, medalla, cuenta grande, cuenta pequeña, separador y cierre.
4. **`sustituciones`** — qué plegaria estándar se sustituye por qué texto propio (muchas coronillas no usan ni Padre Nuestro ni Avemaría).
5. **`temporización`** — día de la semana, franja horaria, temporada litúrgica o «a demanda».
6. **`variables`** — datos que el usuario debe introducir (nombre del difunto, favor, intención, color de cuenta).
7. **`meditación`** — misterios, dolores, virtudes o intenciones que se muestran antes de cada grupo.

### 2.1 Tipología de formatos

| Formato | Cuentas | Devociones que lo usan | Implicación para la app |
|---|---|---|---|
| Rosario mariano completo | 59 | CD: Holy Souls, Praise of the Virgin Mary, all Souls, Conversion, Divine Mercy 1 y 2, Faith, Mercy and Forgiveness, Perpetual Help 1 y 2, Our Lady's Tears, Gertrude, Joseph, Sacred Face, Sacred Heart 1 y 3, Trinity, Tears, Great Confidence, Mother of the Most Holy Eucharist, Holy Wounds, Immaculate Heart, Franciscan Family, Josephian. IVE: los cuatro misterios, Divine Mercy, Purgatorio, dos Corazones, Sangre Preciosa, Pasión, desagravio. | Patrón estándar 1-3-1 + 5x(1+10); basta un contador de decenas y un mapa de sustituciones. |
| Coronilla de 33 cuentas | 33 | CD: Blessed Sacrament, Holy Name, Sacred Heart #2. IVE: Corona de la Sangre Preciosa (33 Padres Nuestros). | 3 decenas o 33 cuentas corridas; en Holy Name admite chotki de nudos. |
| Coronillas de 7 en 7 | 49-59 | CD: Chaplet of Tears (7x7), Seven Dolors (7x7+3). IVE: Corona de los Siete Dolores (7x7). | El grupo de 7 sustituye a la decena; necesita 7 pantallas de meditación. |
| Grupos de 3 | 9-39 | CD: Mount Carmel (3x3), St Rita (3x3), Padre Pio (3x3), St Anthony (13x3), St Dymphna (2+15), St Kateri (8+8+8), St Philomena (3+13), St Gabriel (3+11x3), Queen of Light (3x6), Two Hearts (5x4). IVE: San Charbel (5x3), San Antonio (13x3), desagravio (5x3). | Grupo de tres = Padre/Ave/Gloria, salvo excepciones; conviene un componente «tríada». |
| Grupos de 5 | 20-25 | CD: Cabrini (5x5), Five Wounds (5x5), Immaculate Conception (3x4+1), St Anne (3x6). IVE: Sangre Preciosa (5 Padres Nuestros por misterio). | El grupo de 4 o 5 requiere contador propio. |
| Cuentas sueltas / especiales | 9-21 | CD: Czestochowa (9), St Jude (9), St Raphael (12), St Patrick (12), Holy Infant (15), Infant Prague (15), Maximilian (15), St Dymphna (17), Queen of Light (18), Guadalupe (21), Star of the Sea (15), Holy Ghost (50), Way of the Cross (51), Holy Face (39). | Cada una es un caso único: conviene declarar `pattern` como lista explícita. |

### 2.2 Días y temporadas (reglas automáticas)

| Devoción | Regla | Fuente |
|---|---|---|
| Misterios de Gloria | Miércoles y domingo | IVE |
| Misterios de Gozo | Lunes y sábado; domingos de Adviento; domingos de la Epifanía a Cuaresma | IVE |
| Misterios de Dolores | Martes y viernes; diario del Miércoles de Ceniza al Sábado de Gloria | IVE |
| Misterios de Luz | Todos los jueves | IVE |
| Rosario del Espíritu Santo | Sugerido en domingo (Pascua y envío del Espíritu), o cuando se sienta el llamado | IVE |
| Corona de la Divina Misericordia | Preferentemente a las 15:00; también junto a agonizantes | Ambas fuentes |
| Corona de la Sangre Preciosa | Diaria; plenaria una vez al mes con confesión y comunión | IVE |
| Coronilla de desagravio a María | Todos los días; los cinco misterios pueden rezarse separados | IVE |
| Coronilla al Sagrado Corazón (IVE) | «A cada instante y todos los días» | IVE |
| Chaplet of the Immaculate Conception | Se recomienda recitarla a diario | Catholic Doors |
| Chaplet of Saint Michael | Devoción diaria recomendada por la fuente | Catholic Doors |

### 2.3 Sustituciones frecuentes

| Devoción | En vez de... | Se reza... |
|---|---|---|
| Divine Mercy (ambas fuentes) | Avemarías | «For the sake of His sorrowful Passion...» / «Por su dolorosa Pasión...» |
| Faith | Padre Nuestro (cuentas grandes) | Credo de los Apóstoles |
| Praise of the Virgin Mary | Credo y Padre Nuestro iniciales | Oración de reparación a María |
| Praise of the Virgin Mary | Avemarías | Diez alabanzas numeradas |
| Our Lady's Tears | Credo, Padres Nuestros y Avemarías | Tres textos responsoriales V/R distintos |
| Mercy and Forgiveness | Padres Nuestros, Avemarías y Glorias | Tres oraciones propias + intercesión mariana |
| Most Holy Trinity | Padres Nuestros y Avemarías | Alabanzas a la Trinidad |
| Josephian Rosary | Avemarías | «Hail Joseph...» |
| Sacred Heart #2 (CD) | Avemarías | «Sweet Heart of Jesus, be my love» |
| Rosario de la Pasión (IVE) | Todas | Dos invocaciones a la Pasión + Santo Dios al final |

### 2.4 Datos que la app debe pedir al usuario

| Variable | Devociones que la requieren |
|---|---|
| Nombre / pronombre del difunto | Chaplet for the Dead, Chaplet of all Souls (menciona nombre), Coronilla a las almas del Purgatorio |
| Intención o favor concreto | St Anne, St Jude, Padre Pio, Two Hearts, Purgatorio (CD), Star of the Sea, San Charbel, Czestochowa, Holy Name |
| Color de la cuenta | St Kateri (marrón/rojo/transparente), St Dymphna (rojo/blanco/verde) |
| Tipo de cuenta (grande / pequeña / separadora) | St Gabriel, Guadalupe, Chaplet of Tears, San Charbel, St Anthony |
| Temporada litúrgica | Los cuatro misterios IVE |

## 3. Tabla maestra de las 87 devociones

| ID | Devoción | Fuente | Instrumento y cuentas | Grupos | Día / frecuencia | Rasgo diferencial |
|---|---|---|---|---|---|---|
| CD-01 | Chaplet for the Dead | Catholic Doors | Coronilla propia: cruz + 5 cuentas introductorias + 4 tandas de 10 (45 cuentas). Sin medalla. (45 (5+40)) | 4x10 | Uso libre; pensada para difuntos | Estructura sin medalla; sustituye el Avemaría por una jaculatoria; exige personalizar género. |
| CD-02 | Chaplet for the Holy Souls | Catholic Doors | Rosario mariano común de 59 cuentas (59) | 5x10 | Uso libre; sufragio por las almas | Sustituye tanto el Padre Nuestro de la cuenta grande como el Avemaría de las pequeñas. |
| CD-03 | Chaplet in Praise of the Virgin Mary | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | Uso libre; reparación por blasfemias | No usa Avemarías: las sustituye por 10 alabanzas numeradas; cierre ×5. |
| CD-04 | Chaplet of all Souls | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | Uso libre; sufragio | Ojo: el texto de la fuente dice «How to pray the Chaplet of Our Lady's Tears» por error de cabecera, pero el contenido es el de All Souls. |
| CD-05 | Chaplet of Conversion | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | Uso libre; intención conversionista | Invierte el uso normal: en cuenta grande va Avemaría y jaculatoria; usa Salve dos veces. |
| CD-06 | Chaplet of Divine Mercy # 1 | Catholic Doors | Rosario de 5 decenas (59 cuentas); también posible en cuentas sueltas (59) | 5x10 | Tradicionalmente a las 15:00; también junto a agonizantes | Versión con dos oraciones de apertura del Santuario de Stockbridge; la fuente da otra versión (#2) con variante de inicio en la medalla. |
| CD-07 | Chaplet of Divine Mercy # 2 | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | A las 15:00 / junto a moribundos | Difiere de #1 en el punto de arranque (medalla en vez de cruz) y en el orden de las oraciones iniciales. |
| CD-08 | Chaplet of Faith | Catholic Doors | Rosario de 5 decenas (59) | 5x10 | Uso libre | La fuente indica indulgencia de 5 años (texto histórico; verificar normativa actual). Sustituye Padre Nuestro por Credo. |
| CD-09 | Chaplet of Mercy and Forgiveness | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | Uso libre | Todas las oraciones estándar se sustituyen; conserva un Gloria final. |
| CD-10 | Chaplet of Our Lady of Czestochowa | Catholic Doors | Coronilla propia: cruz + 9 cuentas + medalla de Czestochowa (9) | 1x9 | Uso libre | Formato muy corto 3+3+3; añade dos oraciones de intercesión a Polonia. |
| CD-11 | Chaplet of Our Lady of Guadalupe | Catholic Doors | Coronilla propia: cruz + 21 cuentas + medalla (21) | 1+4+1 + 3+1+3+1+3+1+3 = 21 | Uso libre | Estructura narrativa: cada grupo de 3 cuenta una aparición distinta; ideal para app con pantallas por episodio. |
| CD-12 | Chaplet of Our Lady of Mount Carmel | Catholic Doors | Coronilla: medalla del Carmen + 3 tandas de 3 cuentas (9) | 3x3 | Uso libre | Una de las formas más simples: 9 cuentas, sin meditación. |
| CD-13 | Chaplet of Our Lady of Perpetual Help # 1 | Catholic Doors | Rosario de María (59 cuentas) (59) | 5x10 | Uso libre | Dos formas distintas para el mismo título; la #2 es más breve y usa Salve al inicio. |
| CD-14 | Chaplet of Our Lady of Perpetual Help # 2 | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | No parece indicar Credo inicial; arranca con Salve en la cruz. |
| CD-15 | Chaplet of Our Lady, Star of the Sea | Catholic Doors | Coronilla: medalla + 3 cuentas separadas + tanda de 12 (15) | 3 + 12 | Uso libre | Cada cuenta de la segunda tanda lleva dos jaculatorias; no hay Glorias finales. |
| CD-16 | Chaplet of Our Lady's Tears | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | Todas las oraciones son versículos responsoriales V/R: la app debe alternar dos voces. |
| CD-17 | Chaplet of Saint Anne | Catholic Doors | Sin instrumento especificado (3 bloques de 1 Padre Nuestro + 5 Avemarías) (18 oraciones) | 3x6 | Uso libre; favores espirituales y temporales | No describe cuentas: la app debe contar 18 oraciones, no perlas. |
| CD-18 | Chaplet of Saint Anthony | Catholic Doors | Coronilla: medalla de San Antonio y el Niño + 13 tandas de 3 cuentas (39) | 13x3 | Uso libre | La fuente da las 13 intenciones; requiere pantalla de meditación por tanda. |
| CD-19 | Chaplet of Saint Dymphna | Catholic Doors | Coronilla: medalla + 2 cuentas + 15 cuentas de colores (5 rojas, 5 blancas, 5 verdes) (17) | 2 + 15 | Uso libre; especial para trastornos emocionales | Coronilla de colores: la app debe pedir el color de cada cuenta. |
| CD-20 | Chaplet of Saint Frances Xavier Cabrini | Catholic Doors | Coronilla: 25 cuentas, 5 tandas de 5 + medalla de Mother Cabrini (25) | 5x5 | Uso libre | Formato inusual 5x5; una sola jaculatoria de cierre por tanda. |
| CD-21 | Chaplet of Saint Gabriel | Catholic Doors | Coronilla: medalla grande + 3 cuentas + medalla pequeña en la unión + 3 tandas de 11 separadas por 1 cuenta (38) | 3 + 3x11 + 2 | Uso libre | Estructura en Y; exige distinguir entre cuentas de tanda y separadoras. |
| CD-22 | Chaplet of Saint Gertrude | Catholic Doors | Rosario de 59 cuentas (cruz+1+3+1+medalla/10+1...) (59) | 5x10 | Uso libre | La oración de las cuentas pequeñas es larguísima; conviene mostrarla en pantalla completa. |
| CD-23 | Chaplet of Saint Joseph | Catholic Doors | Rosario (cuentas grandes y pequeñas) (No se especifica en la fuente) | 5x10 | Uso libre; protección familiar | La fuente no especifica Credo ni cuenta inicial; solo grandes/pequeñas. |
| CD-24 | Chaplet of Saint Jude | Catholic Doors | Coronilla: cruz + 9 cuentas + medalla de San Judas (9) | 1x9 | Uso libre; causas desesperadas | Muy corta; requiere campo de intención en la cruz. |
| CD-25 | Chaplet of Saint Kateri Tekakwitha | Catholic Doors | Coronilla de 24 cuentas por colores: 8 marrones, 8 rojas, 8 transparentes (24) | 3x8 | Uso libre | Se conoce también como Kateri Indian Rosary; la app debe reconocer color de cuenta. |
| CD-26 | Chaplet of Saint Maximillian | Catholic Doors | Coronilla: medalla de San Maximiliano + 1 cuenta + medalla de la Virgen + 14 cuentas (15) | 1 + 14 | Uso libre | Dos medallas; la cuenta intermedia cambia completamente de oración. |
| CD-27 | Chaplet of Saint Michael (Archangel) | Catholic Doors | Rosario o coronilla; 9 salutaciones (1 Padre Nuestro + 3 Avemarías cada una) más 4 cuentas finales (9x(1+3) + 4 = 40 oraciones (más la oración inicial)) | 9x(1+3) + 4 | Devoción diaria recomendada por la fuente | La fuente incluye promesas (escuadra de 9 ángeles, auxilio diario, liberación del Purgatorio) — texto histórico. |
| CD-28 | Chaplet of Saint Padre Pio | Catholic Doors | Coronilla: medalla + crucifijo + 3 tandas de 3 cuentas marrones (9) (9) | 3x3 | Uso libre | La oración de cuenta es larga y personalizable; el cierre es triple 3-3-3. |
| CD-29 | Chaplet of Saint Patrick | Catholic Doors | Coronilla: medalla + 12 cuentas verdes (12) | 1x12 | Uso libre; fortalecer la fe | No usa Avemarías: solo Gloria ×12 y la Coraza final. |
| CD-30 | Chaplet of Saint Philomena | Catholic Doors | Coronilla: medalla + 3 cuentas blancas + 13 rojas (16) | 3 + 13 | Uso libre | Dos colores con funciones distintas. |
| CD-31 | Chaplet of Saint Raphael (Archangel) | Catholic Doors | Coronilla: medalla + 3 cuentas finales + 9 cuentas (12) (12) | 3 + 9 | Uso libre | Estructura similar a San Miguel pero con Santísima Trinidad en vez de Padre+Avemaría. |
| CD-32 | Chaplet of Saint Rita | Catholic Doors | Coronilla: medalla + 3 tandas de 3 cuentas azules + cruz (9) | 3x3 | Uso libre; patrona de lo imposible | Cuentas azules; una de las coronillas más cortas. |
| CD-33 | Chaplet of Saint Theresa (the Little Flower) | Catholic Doors | Rosario de Santa Teresa: 24 cuentas + 1 adicional (25) | 25 | Uso libre | Casi monótono: 24 Glorias; la app solo cambia la primera cuenta. |
| CD-34 | Chaplet of Tears | Catholic Doors | Coronilla de 59 cuentas: medalla grande + 3 + 1 + medalla de Lourdes + 7 tandas de 7 separadas por 6 cuentas (59) | 7x7 + 6 | Uso libre | Texto responsorial V/R; distinta de la Chaplet of Our Lady's Tears (ésta usa rosario de 59 y 5 decenas). |
| CD-35 | Chaplet of the Blessed Sacrament | Catholic Doors | Coronilla: medalla del Santísimo + 33 cuentas (33) | 1x33 | Uso libre | La fuente menciona aprobación de Pío X (1911) e indulgencia de 300 días — verificar normativa vigente. |
| CD-36 | Chaplet of the Five Wounds | Catholic Doors | Coronilla: 5 grupos de 5 cuentas (grande + 4 pequeñas) (25) | 5x5 | Uso libre | Origen pasionista; aprobada por Pío VII en 1822 según la fuente. |
| CD-37 | Chaplet of the Holy Face | Catholic Doors | Coronilla: medalla + 39 cuentas (6 grandes, 33 pequeñas) (39) | 6 + 33 | Uso libre | No confundir con Chaplet of the Sacred Face (#51), que usa rosario de 59. |
| CD-38 | Chaplet of the Holy Ghost | Catholic Doors | Coronilla de 50 cuentas: 3 cuentas iniciales + 5 tandas de 7 separadas por 2 grandes (50) | 5x7 + 3 + 10 | Uso libre | Obra de un capuchino en 1892, aprobada por León XIII en 1902. Requiere 5 textos de meditación bíblica. Ojo: la fuente afirma 50 cuentas pero describe 3 iniciales + 5x7 + 2 separadoras por grupo, cifras que no cuadran (46 o 48); verificar antes de fijar el contador. |
| CD-39 | Chaplet of the Holy Infant | Catholic Doors | Coronilla: medalla + 3 cuentas + 12 cuentas (15) | 3 + 12 | Uso libre | Muy similar a la del Niño Jesús de Praga, pero con 3+12 y frase antes de cada oración. |
| CD-40 | Chaplet of the Holy Name | Catholic Doors | Coronilla de 33 cuentas (3 decenas de 1 grande + 10 pequeñas); admite chotki de 33 nudos (33) | 3x(1+10) | Uso libre | Usa 33 cuentas tipo oriental; la jaculatoria se repite 30 veces. |
| CD-41 | Chaplet Of the Holy Wounds | Catholic Doors | Rosario mariano de 59 cuentas (59) | 5x10 | Uso libre | Muy parecido al de las Cinco Llagas, pero con inicio especial de 4 oraciones y sin meditación de 5 llagas. |
| CD-42 | Chaplet of the Immaculate Conception | Catholic Doors | Coronilla de 15 cuentas: 3 tandas de 4 + 1 cuenta final + Medalla Milagrosa (15) | 3x4 + 1 | Se recomienda diaria | La fuente da el propósito: nunca pecar contra la pureza. Ojo: afirma 15 cuentas pero describe 3x4 + 1 = 13; verificar el cómputo. |
| CD-43 | Chaplet of the Immaculate Heart of Mary | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | La señal de la Cruz ×5 es un rasgo único; conviene contarla como paso explícito. |
| CD-44 | Chaplet of the Infant Jesus of Prague | Catholic Doors | Coronilla de 15 cuentas (15) | 3 + 12 | Uso libre | Muy similar a Chaplet of the Holy Infant; aquí 15 cuentas y frase antes de cada oración. |
| CD-45 | Chaplet of the Most Holy Trinity | Catholic Doors | Rosario normal de 59 cuentas (59) | 5x10 | Uso libre; reparación por los pecados de los pecadores | Sustituye Avemarías y Padres Nuestros por alabanzas trinitarias. |
| CD-46 | Chaplet of the Precious Blood | Catholic Doors | Coronilla de 7 grupos de 33 cuentas (231 cuentas teóricas) (7x33) | 7x33 | Uso libre | La más larga de la colección; requiere 7 bloques de 33 y control de 33 Padres Nuestros. |
| CD-47 | Chaplet of the Queen of Light | Catholic Doors | Coronilla: medalla del Sagrado Corazón + 18 cuentas en 3 tandas de 6 (18) | 3x6 | Uso libre | Un solo Padre Nuestro por tanda; las meditaciones son textos cortos. |
| CD-48 | Chaplet of the Sacred Face | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | A diferencia del Holy Face (#07), aquí las 5 decenas tienen texto propio. |
| CD-49 | Chaplet of the Sacred Heart # 1 | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | Tres variantes del Sagrado Corazón en la fuente; la #1 es la más completa. |
| CD-50 | Chaplet of the Sacred Heart # 2 | Catholic Doors | Coronilla propia: 33 cuentas pequeñas + crucifijo + medalla del Sagrado Corazón (33) | 3x10 + 3 | Uso libre | 33 cuentas, medalla reversible (Sagrado Corazón / Inmaculado Corazón). |
| CD-51 | Chaplet of the Sacred Heart # 3 | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | Arranque 1-3-1 distinto del rosario estándar. |
| CD-52 | Chaplet of the Seven Dolors of Our Lady | Catholic Doors | Coronilla de 58 cuentas + medalla del primer dolor + cruz (58) | 7x7 + 3 + 6 | Uso libre | La fuente añade indulgencias históricas (confirmadas 1747) — verificar. |
| CD-53 | Chaplet of the Two Hearts | Catholic Doors | Coronilla: medalla de los Dos Corazones + 20 cuentas en 5 tandas de 4 (20) | 5x4 | Uso libre | Formato 5x4 con oración final personalizable. |
| CD-54 | Chaplet of the Way of the Cross | Catholic Doors | Coronilla: 15 tandas de 3 cuentas entre medallas de las estaciones + 6 cuentas + crucifijo (51) | 15x3 + 6 | Uso libre; Vía Crucis | Ojo con la incoherencia de la fuente: indica 15 grupos pero lista 14 estaciones. La app debe decidir 14 o preguntar. |
| CD-55 | Chaplet of Unity | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre; unidad | Las 4 invocaciones finales funcionan como bloque de cierre. |
| CD-56 | Chaplet to Obtain a Great Confidence | Catholic Doors | Rosario de 59 cuentas, pero se usan solo 55 (55 de 59) | 5x(1+10) | Uso libre | La fuente dice explícitamente que se omiten 4 cuentas: la app debe ocultarlas. |
| CD-57 | Chaplet to the Mother of the Most Holy Eucharist | Catholic Doors | Rosario de 59 cuentas (59) | 5x10 | Uso libre | Incluye una profesión de fe larguísima al final. |
| CD-58 | Franciscan Family Chaplet | Catholic Doors | Rosario / cruz de San Damián (No se especifica; 3 decenas de 10 Avemarías + 3 Avemarías iniciales) | 3x10 + 3 | Uso libre | Autor Nicholas H. Kovacs, OFS; usa la cruz de San Damián como punto de inicio. |
| CD-59 | Josephian Rosary | Catholic Doors | Rosario mariano de 59 cuentas (no hace falta uno especial) (59) | 5x10 | Uso privado (la fuente lo indica explícitamente) | Sustituye el Avemaría por «Hail Joseph»; el más largo de la colección y con misterios elaborados por la propia web. La fuente ofrece dos modos: meditar los misterios 1-5 en las cinco decenas del rosario, o bien los misterios 6-10. |
| IVE-01 | Misterios de Gloria (Rosario glorioso) | IVE Arabic | Rosario mariano completo de 59 cuentas (59) | 5x10 | Miércoles y domingo | Versión IVE del rosario: añade acto de contrición, ofrecimiento largo, jaculatorias y Salve final. |
| IVE-02 | Misterios de Gozo (Rosario gozoso) | IVE Arabic | Rosario mariano completo de 59 cuentas (59) | 5x10 | Lunes y sábado; también domingos de Adviento y domingos desde la Epifanía hasta Cuaresma | La regla de días es más rica que la estándar: incluye dos temporadas litúrgicas. |
| IVE-03 | Misterios de Dolores (Rosario doloroso) | IVE Arabic | Rosario mariano completo de 59 cuentas (59) | 5x10 | Martes y viernes; además, diariamente desde el Miércoles de Ceniza hasta el Sábado de Gloria | Regla de Cuaresma diaria: la app debe cambiar el modo según la temporada litúrgica. |
| IVE-04 | Misterios de Luz (Rosario luminoso) | IVE Arabic | Rosario mariano completo de 59 cuentas (59) | 5x10 | Todos los jueves | Cuarto bloque semanal; comparte esqueleto con los otros tres. |
| IVE-05 | Corona a la Divina Misericordia | IVE Arabic | Rosario común de 5 decenas (59) | 5x10 | Preferentemente a las 15:00; también junto a un agonizante o recién fallecido | La página insiste en rezarla junto al moribundo y luego el rosario; incluye texto de promesas. |
| IVE-06 | Coronilla a las almas del Purgatorio | IVE Arabic | Cuentas de un rosario tradicional (59) | 5x10 | Cada día (la fuente habla de unirnos «cada día») | Texto en español; difiere del chaplet inglés de Holy Souls de Catholic Doors. |
| IVE-07 | Coronilla al Sagrado Corazón de Jesús | IVE Arabic | Rosario común (cuentas grandes y pequeñas) (59) | 5x10 | A cada instante y todos los días | Una de las más cortas: solo dos textos alternos + cierre. |
| IVE-08 | Coronilla al Inmaculado Corazón de María | IVE Arabic | Rosario común de 5 decenas (59) | 5x10 | Uso libre | Gemela en estructura a la del Sagrado Corazón; la app puede compartir plantilla. |
| IVE-09 | Corona a la Sangre Preciosa de Jesús | IVE Arabic | Corona de 7 misterios con 33 Padres Nuestros en total (33 Padres Nuestros + 6 Glorias + jaculatorias) | 7x(5 Padre Nuestro + Gloria), último 3 Padre Nuestro + Gloria | Diaria (indulgencia diaria); indulgencia plenaria una vez al mes con confesión y comunión | Indulgencias de Pío VII (1809 y 1815) según la fuente; verificar normativa actual. Texto paralelo al chaplet inglés de Precious Blood. |
| IVE-10 | El Rosario del Espíritu Santo | IVE Arabic | Rosario de 5 misterios (5 decenas) | 5x10 | Se sugiere el domingo (Pascua del Señor y envío del Espíritu), o cuando se sienta el llamado | Requiere mostrar texto bíblico en pantalla antes de cada decena; six dones del Espíritu en la introducción. |
| IVE-11 | Corona de los Siete Dolores | IVE Arabic | Corona de 7 misterios; 7 Ave Marías por dolor (7x7 = 49 Ave Marías + 7 Padres Nuestros + 7 Glorias) | 7x(1+7+1) | Uso libre | Cita de Newman: «El camino para penetrar en los sufrimientos del Hijo es penetrar en los sufrimientos de la Madre». No confundir con el Chaplet of the Seven Dolors inglés (58 cuentas). |
| IVE-12 | Coronilla de Alabanzas a María | IVE Arabic | Coronilla de 3 coronas (Excelencia, Poder, Bondad) (3x(1 Padre Nuestro + 5 Avemarías + Gloria) aprox.) | 3x5 | Uso libre | Texto casi íntegramente en español; cada alabanza lleva Avemaría y el responsorio fijo. |
| IVE-13 | Coronilla de San Charbel | IVE Arabic | 5 grupos de 3 cuentas + 4 cuentas separadoras + cuenta grande inicial (5x3 + 4 separadoras + 1 inicial = 20) | 5x3 | Uso libre | Rito maronita; la plegaria inicial es larga y debe mostrarse íntegra. |
| IVE-14 | Coronilla de San Antonio de Padua | IVE Arabic | Coronilla de 39 cuentas: 13 grupos de 3 (39) | 13x3 | Uso libre | Equivale a la Chaplet of Saint Anthony de Catholic Doors, pero con texto español propio. |
| IVE-15 | Rosario de la Pasión de Jesús | IVE Arabic | Rosario común (59) | 5x10 | Uso libre | Texto en español, estructura muy parecida a Divine Mercy; arranca con una cita de Cristo crucificado. |
| IVE-16 | Coronilla simple de desagravio a María Santísima | IVE Arabic | 5 misterios independientes de 3 Avemarías cada uno (15 Avemarías) | 5x3 | Para todos los días; los misterios pueden rezarse juntos o separados a lo largo del día | Diseñada para memorizar y rezar «caminando por la calle»: la app debe permitir iniciar un solo misterio. |
| IVE-17 | Corona de confianza en grandes tribulaciones | IVE Arabic | Corona de 10 misterios con 9 repeticiones cada uno (10x9 + cierre = 91) | 10x9 | En grandes tribulaciones o presagios de peligro | La fuente indica que se completan 91 repeticiones; verificar el cómputo en la app. |
| HZ-01 | Rosario de la Virgen de Lourdes | Hozana | Rosario mariano tradicional de 59 cuentas (59) | 5x10 | Diario a las 15:30 en el santuario de Lourdes; uso libre en comunión con los enfermos | La fuente no aporta textos nuevos ni misterios: es el rosario estándar con intención lourdiana. No inventar oraciones. |
| HZ-02 | Coronilla de liberación | Hozana | Rosario especial o coronilla similar al rosario tradicional (59) | 5x10 | Uso libre; se recomienda confiar una intención precisa antes de empezar | La página añade oraciones del Ritual Romano como material complementario, no como parte de la coronilla. |
| HZ-03 | Coronilla de las llagas de Cristo | Hozana | Rosario tradicional de 5 decenas (59) | 5x10 | Uso libre | También llamada Rosario a la preciosa sangre de Cristo. La página añade una consagración a la Faz y una oración a la llaga del hombro como textos complementarios. |
| HZ-04 | Coronilla para meditar y adorar al Santísimo Sacramento | Hozana | Rosario tradicional (59) | 5x10 | Uso libre | Primera de las dos formas que contiene la misma página; es distinta de la coronilla de 33 cuentas. |
| HZ-05 | Coronilla de la Sagrada Eucaristía | Hozana | Coronilla de 33 cuentas (o rosario tradicional si no se tiene) (33) | 1x33 | Cuando no se puede recibir la comunión en la Misa | Segunda forma de la página; equivale a la Chaplet of the Blessed Sacrament de Catholic Doors. |
| HZ-06 | Coronilla a San Miguel Arcángel (Hozana) | Hozana | Rosario especial de 39 cuentas: 9 grupos de 3 pequeñas separados por una grande, con 4 granos antes de la medalla; admite rosario tradicional (39) | 9x3 + 4 | Protección y liberación en luchas espirituales; también antes de comulgar | Origen: aparición a Antónia de Astónaco, monja carmelita portuguesa, siglo XVIII; conocida como «corona angélica». La lista de coros en esta página difiere del orden de Catholic Doors. |
| HZ-07 | Coronilla a San José | Hozana | Coronilla de 60 cuentas pequeñas violeta agrupadas en tríadas y separadas por cuentas grandes blancas; medalla y cruz (60 pequeñas + separadoras) | tríadas | Ayuda y protección del hogar; gracia de una buena muerte | La fuente la distingue expresamente del Rosario a San José, que sí medita misterios de la vida de Cristo. |
| HZ-08 | 24 Glorias a Santa Teresita | Hozana | Rosario de 25 cuentas: 24 cuentas y una adicional separada por una medalla de Santa Teresa (25) | 24 + 1 | Uso libre | OJO: el título dice «24 Glorias», pero la fuente manda oraciones propias largas en cada cuenta, no Glorias. Coincide con CD solo en el número de cuentas. |
| HZ-09 | Rosario por las benditas almas del purgatorio | Hozana | Rosario tradicional (59) | 5x10 | Noviembre o cualquier momento del año; puede ser oración diaria | Equivale en lo esencial al Chaplet for the Holy Souls de Catholic Doors. |
| HZ-10 | Rosario por los difuntos | Hozana | Rosario tradicional (59) | 5x10 | Noviembre o cualquier momento | Segunda forma de la misma página; se parece al Chaplet for the Dead de Catholic Doors, pero aquí usa 59 cuentas y Credo en vez de 45 cuentas y De Profundis. |
| HZ-11 | El rosario de San Miguel | Hozana | Rosario especial de 39 cuentas en 9 grupos de 3 pequeñas; medalla (39) | 9x3 + 4 | Por la Iglesia en sus luchas y por las almas del purgatorio | Pío IX promulgó un decreto en 1851. Es la misma devoción que la coronilla anterior pero con textos propios y Gloria explícita en cada grupo. |

## 4. Fichas detalladas

Cada ficha incluye un bloque JSON listo para cargar como configuración de la devoción.

### CD-01 · Chaplet for the Dead

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap22.htm
- **Instrumento:** Coronilla propia: cruz + 5 cuentas introductorias + 4 tandas de 10 (45 cuentas). Sin medalla.
- **Cuentas:** 45 (5+40) · **Grupos:** 4x10
- **Secuencia:** Inicio y final: De Profundis o Padre Nuestro + Avemaría → En cuentas grandes: actos de Fe, Esperanza y Caridad → En cada cuenta pequeña: «Sweet Heart of Mary, be my salvation» → Tras cada tanda de 10: «Eternal Rest Grant unto (him/her)...» → Cierre: versículos «Eternal rest grant unto...», «May his/her soul...» y bendición final
- **Día / frecuencia:** Uso libre; pensada para difuntos
- **Variables del usuario:** nombre y pronombre del difunto (him/her, he/she)
- **Meditación / intenciones:** 40 cuentas = las 40 horas que Jesús estuvo en el Limbo
- **Características y advertencias:** Estructura sin medalla; sustituye el Avemaría por una jaculatoria; exige personalizar género.

```json
{
  "id": "CD-01",
  "nombre": "Chaplet for the Dead",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap22.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla propia: cruz + 5 cuentas introductorias + 4 tandas de 10 (45 cuentas). Sin medalla.",
  "cuentas": "45 (5+40)",
  "grupos": "4x10",
  "pasos": [
    "Inicio y final: De Profundis o Padre Nuestro + Avemaría",
    "En cuentas grandes: actos de Fe, Esperanza y Caridad",
    "En cada cuenta pequeña: «Sweet Heart of Mary, be my salvation»",
    "Tras cada tanda de 10: «Eternal Rest Grant unto (him/her)...»",
    "Cierre: versículos «Eternal rest grant unto...», «May his/her soul...» y bendición final"
  ],
  "dia_o_frecuencia": "Uso libre; pensada para difuntos",
  "variables_usuario": "nombre y pronombre del difunto (him/her, he/she)",
  "meditacion_o_intenciones": "40 cuentas = las 40 horas que Jesús estuvo en el Limbo",
  "notas": "Estructura sin medalla; sustituye el Avemaría por una jaculatoria; exige personalizar género."
}
```

### CD-02 · Chaplet for the Holy Souls

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap15.htm
- **Instrumento:** Rosario mariano común de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz en la cruz → Credo → Cuentas entre cruz y medalla: 1 Padre Nuestro, 3 Avemarías, 1 Gloria → Cuenta grande previa a cada decena: «O Holy Souls, draw the fire of God's love into my soul...» → Cuentas pequeñas: «Crucified Lord Jesus, have mercy on the souls in Purgatory» → Tras 5 decenas: 3 Glorias
- **Día / frecuencia:** Uso libre; sufragio por las almas
- **Variables del usuario:** opcional: intención particular
- **Meditación / intenciones:** Cada decena se ofrece por las almas del Purgatorio
- **Características y advertencias:** Sustituye tanto el Padre Nuestro de la cuenta grande como el Avemaría de las pequeñas.

```json
{
  "id": "CD-02",
  "nombre": "Chaplet for the Holy Souls",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap15.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano común de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz en la cruz",
    "Credo",
    "Cuentas entre cruz y medalla: 1 Padre Nuestro, 3 Avemarías, 1 Gloria",
    "Cuenta grande previa a cada decena: «O Holy Souls, draw the fire of God's love into my soul...»",
    "Cuentas pequeñas: «Crucified Lord Jesus, have mercy on the souls in Purgatory»",
    "Tras 5 decenas: 3 Glorias"
  ],
  "dia_o_frecuencia": "Uso libre; sufragio por las almas",
  "variables_usuario": "opcional: intención particular",
  "meditacion_o_intenciones": "Cada decena se ofrece por las almas del Purgatorio",
  "notas": "Sustituye tanto el Padre Nuestro de la cuenta grande como el Avemaría de las pequeñas."
}
```

### CD-03 · Chaplet in Praise of the Virgin Mary

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap30.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** En lugar del Credo y del Padre Nuestro iniciales: oración de reparación «Mary, my Immaculate Mother...» → Cuenta grande: «Grant that I may praise thee, O Holy Virgin! Give me strength against thy enemies!» → 10 cuentas pequeñas: 10 alabanzas distintas (Blessed be the Great Mother of God... etc.) → Tras cada decena: «Glory be to Mary, Daughter of the Father...» → Cierre: oración final repetida 5 veces
- **Día / frecuencia:** Uso libre; reparación por blasfemias
- **Variables del usuario:** —
- **Meditación / intenciones:** 10 alabanzas fijas, una por cuenta pequeña
- **Características y advertencias:** No usa Avemarías: las sustituye por 10 alabanzas numeradas; cierre ×5.

```json
{
  "id": "CD-03",
  "nombre": "Chaplet in Praise of the Virgin Mary",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap30.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "En lugar del Credo y del Padre Nuestro iniciales: oración de reparación «Mary, my Immaculate Mother...»",
    "Cuenta grande: «Grant that I may praise thee, O Holy Virgin! Give me strength against thy enemies!»",
    "10 cuentas pequeñas: 10 alabanzas distintas (Blessed be the Great Mother of God... etc.)",
    "Tras cada decena: «Glory be to Mary, Daughter of the Father...»",
    "Cierre: oración final repetida 5 veces"
  ],
  "dia_o_frecuencia": "Uso libre; reparación por blasfemias",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "10 alabanzas fijas, una por cuenta pequeña",
  "notas": "No usa Avemarías: las sustituye por 10 alabanzas numeradas; cierre ×5."
}
```

### CD-04 · Chaplet of all Souls

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap53.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** En la cruz: salmo «Out of the Depths» → Cuentas grandes: Padre Nuestro → Cuentas pequeñas: «O good Jesus, have mercy on the soul in purgatory...» → Cada decena comienza con una ofrenda propia (1. familiares, 2. bienhechores, 3. ofendidos, 4. amigos, 5. padres y allegados) → Cierre con la invocación final
- **Día / frecuencia:** Uso libre; sufragio
- **Variables del usuario:** nombre del difunto; 5 destinatarios de intención
- **Meditación / intenciones:** Cada decena: una Preciosa Sangre distinta (Huerto, Flagelación, Cruz, etc.)
- **Características y advertencias:** Ojo: el texto de la fuente dice «How to pray the Chaplet of Our Lady's Tears» por error de cabecera, pero el contenido es el de All Souls.

```json
{
  "id": "CD-04",
  "nombre": "Chaplet of all Souls",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap53.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "En la cruz: salmo «Out of the Depths»",
    "Cuentas grandes: Padre Nuestro",
    "Cuentas pequeñas: «O good Jesus, have mercy on the soul in purgatory...»",
    "Cada decena comienza con una ofrenda propia (1. familiares, 2. bienhechores, 3. ofendidos, 4. amigos, 5. padres y allegados)",
    "Cierre con la invocación final"
  ],
  "dia_o_frecuencia": "Uso libre; sufragio",
  "variables_usuario": "nombre del difunto; 5 destinatarios de intención",
  "meditacion_o_intenciones": "Cada decena: una Preciosa Sangre distinta (Huerto, Flagelación, Cruz, etc.)",
  "notas": "Ojo: el texto de la fuente dice «How to pray the Chaplet of Our Lady's Tears» por error de cabecera, pero el contenido es el de All Souls."
}
```

### CD-05 · Chaplet of Conversion

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap16.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Credo por los incrédulos → Cuenta 1: Padre Nuestro por la conversión del mundo → Cuenta 2: Avemaría en honor del Padre → Cuenta 3: Salve → Medalla: oración «O Holy Mother, I join you at the foot of your Son's Cross...» → Cuentas pequeñas: invocación larga de conversión → Cuentas grandes: Avemaría por las lágrimas de María + «Holy Mother Mary, Mediatrix of all graces...» → 3 Glorias → Salve final
- **Día / frecuencia:** Uso libre; intención conversionista
- **Variables del usuario:** —
- **Meditación / intenciones:** Conversión del mundo
- **Características y advertencias:** Invierte el uso normal: en cuenta grande va Avemaría y jaculatoria; usa Salve dos veces.

```json
{
  "id": "CD-05",
  "nombre": "Chaplet of Conversion",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap16.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Credo por los incrédulos",
    "Cuenta 1: Padre Nuestro por la conversión del mundo",
    "Cuenta 2: Avemaría en honor del Padre",
    "Cuenta 3: Salve",
    "Medalla: oración «O Holy Mother, I join you at the foot of your Son's Cross...»",
    "Cuentas pequeñas: invocación larga de conversión",
    "Cuentas grandes: Avemaría por las lágrimas de María + «Holy Mother Mary, Mediatrix of all graces...»",
    "3 Glorias",
    "Salve final"
  ],
  "dia_o_frecuencia": "Uso libre; intención conversionista",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Conversión del mundo",
  "notas": "Invierte el uso normal: en cuenta grande va Avemaría y jaculatoria; usa Salve dos veces."
}
```

### CD-06 · Chaplet of Divine Mercy # 1

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap01.htm
- **Instrumento:** Rosario de 5 decenas (59 cuentas); también posible en cuentas sueltas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Dos oraciones de apertura opcionales («You expired, Jesus...», «O Blood and Water...») → Padre Nuestro, Avemaría y Credo → Cuenta grande: «Eternal Father, I offer You the Body and Blood...» → Cada una de las 10 pequeñas: «For the sake of His sorrowful Passion, have mercy on us and on the whole world» → Tras 5 decenas: «Holy God, Holy Mighty One...» ×3 → Oración de cierre opcional
- **Día / frecuencia:** Tradicionalmente a las 15:00; también junto a agonizantes
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 decenas sin misterios; repetición fija
- **Características y advertencias:** Versión con dos oraciones de apertura del Santuario de Stockbridge; la fuente da otra versión (#2) con variante de inicio en la medalla.

```json
{
  "id": "CD-06",
  "nombre": "Chaplet of Divine Mercy # 1",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap01.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 5 decenas (59 cuentas); también posible en cuentas sueltas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Dos oraciones de apertura opcionales («You expired, Jesus...», «O Blood and Water...»)",
    "Padre Nuestro, Avemaría y Credo",
    "Cuenta grande: «Eternal Father, I offer You the Body and Blood...»",
    "Cada una de las 10 pequeñas: «For the sake of His sorrowful Passion, have mercy on us and on the whole world»",
    "Tras 5 decenas: «Holy God, Holy Mighty One...» ×3",
    "Oración de cierre opcional"
  ],
  "dia_o_frecuencia": "Tradicionalmente a las 15:00; también junto a agonizantes",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 decenas sin misterios; repetición fija",
  "notas": "Versión con dos oraciones de apertura del Santuario de Stockbridge; la fuente da otra versión (#2) con variante de inicio en la medalla."
}
```

### CD-07 · Chaplet of Divine Mercy # 2

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap21.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz, 1 Padre Nuestro, 1 Avemaría, Credo → Desde la medalla: «Eternal Father, I offer You...» → 10 cuentas: «For the sake of His sorrowful Passion...» → En cada cuenta separadora repetition de «Eternal Father...» → Cierre: «Holy God...» ×3 + oración opcional
- **Día / frecuencia:** A las 15:00 / junto a moribundos
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Difiere de #1 en el punto de arranque (medalla en vez de cruz) y en el orden de las oraciones iniciales.

```json
{
  "id": "CD-07",
  "nombre": "Chaplet of Divine Mercy # 2",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap21.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz, 1 Padre Nuestro, 1 Avemaría, Credo",
    "Desde la medalla: «Eternal Father, I offer You...»",
    "10 cuentas: «For the sake of His sorrowful Passion...»",
    "En cada cuenta separadora repetition de «Eternal Father...»",
    "Cierre: «Holy God...» ×3 + oración opcional"
  ],
  "dia_o_frecuencia": "A las 15:00 / junto a moribundos",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Difiere de #1 en el punto de arranque (medalla en vez de cruz) y en el orden de las oraciones iniciales."
}
```

### CD-08 · Chaplet of Faith

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap03.htm
- **Instrumento:** Rosario de 5 decenas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cuentas grandes: Credo de los Apóstoles → Cuentas pequeñas: «Jesus, Mary, I love you, save souls, save the consecrated» → Final: Salve repetida 5 veces
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** La fuente indica indulgencia de 5 años (texto histórico; verificar normativa actual). Sustituye Padre Nuestro por Credo.

```json
{
  "id": "CD-08",
  "nombre": "Chaplet of Faith",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap03.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 5 decenas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cuentas grandes: Credo de los Apóstoles",
    "Cuentas pequeñas: «Jesus, Mary, I love you, save souls, save the consecrated»",
    "Final: Salve repetida 5 veces"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "La fuente indica indulgencia de 5 años (texto histórico; verificar normativa actual). Sustituye Padre Nuestro por Credo."
}
```

### CD-09 · Chaplet of Mercy and Forgiveness

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap27.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz y beso al crucifijo; oración de fe mirando la cruz → Cuentas de Padre Nuestro: plegaria «Merciful Jesus, our Redeemer...» → Cuentas de Avemaría: «Holy God, Holy Mighty One, Holy Immortal One, have mercy on us» → Final de cada decena: «Most Blessed Virgin Mary, Mother of God, intercede for us» → Cierre: oración larga + Gloria
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Origen: Chorbishop Joseph Saidi (Worcester, EE.UU.)
- **Características y advertencias:** Todas las oraciones estándar se sustituyen; conserva un Gloria final.

```json
{
  "id": "CD-09",
  "nombre": "Chaplet of Mercy and Forgiveness",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap27.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz y beso al crucifijo; oración de fe mirando la cruz",
    "Cuentas de Padre Nuestro: plegaria «Merciful Jesus, our Redeemer...»",
    "Cuentas de Avemaría: «Holy God, Holy Mighty One, Holy Immortal One, have mercy on us»",
    "Final de cada decena: «Most Blessed Virgin Mary, Mother of God, intercede for us»",
    "Cierre: oración larga + Gloria"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Origen: Chorbishop Joseph Saidi (Worcester, EE.UU.)",
  "notas": "Todas las oraciones estándar se sustituyen; conserva un Gloria final."
}
```

### CD-10 · Chaplet of Our Lady of Czestochowa

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap45.htm
- **Instrumento:** Coronilla propia: cruz + 9 cuentas + medalla de Czestochowa
- **Cuentas:** 9 · **Grupos:** 1x9
- **Secuencia:** En las 9 cuentas: 3 Padres Nuestros, 3 Avemarías y 3 Glorias → Después: oración «O Almighty and merciful God...» y «Lady of Jasna Gora...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** intención particular en «State your intention here»
- **Meditación / intenciones:** —
- **Características y advertencias:** Formato muy corto 3+3+3; añade dos oraciones de intercesión a Polonia.

```json
{
  "id": "CD-10",
  "nombre": "Chaplet of Our Lady of Czestochowa",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap45.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla propia: cruz + 9 cuentas + medalla de Czestochowa",
  "cuentas": "9",
  "grupos": "1x9",
  "pasos": [
    "En las 9 cuentas: 3 Padres Nuestros, 3 Avemarías y 3 Glorias",
    "Después: oración «O Almighty and merciful God...» y «Lady of Jasna Gora...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "intención particular en «State your intention here»",
  "meditacion_o_intenciones": "—",
  "notas": "Formato muy corto 3+3+3; añade dos oraciones de intercesión a Polonia."
}
```

### CD-11 · Chaplet of Our Lady of Guadalupe

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap28.htm
- **Instrumento:** Coronilla propia: cruz + 21 cuentas + medalla
- **Cuentas:** 21 · **Grupos:** 1+4+1 + 3+1+3+1+3+1+3 = 21
- **Secuencia:** Oración inicial a Guadalupe → 1 Padre Nuestro → 4 Avemarías por las 4 apariciones → 1 Gloria → Cada una de las 4 tandas: intro + 1 Padre Nuestro + acción de gracias específica + 3 Avemarías + Gloria → Cierre: oración a Guadalupe + opcional «Our Lady of Guadalupe, my mother...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Las 4 apariciones a Juan Diego, con texto propio para cada una
- **Características y advertencias:** Estructura narrativa: cada grupo de 3 cuenta una aparición distinta; ideal para app con pantallas por episodio.

```json
{
  "id": "CD-11",
  "nombre": "Chaplet of Our Lady of Guadalupe",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap28.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla propia: cruz + 21 cuentas + medalla",
  "cuentas": "21",
  "grupos": "1+4+1 + 3+1+3+1+3+1+3 = 21",
  "pasos": [
    "Oración inicial a Guadalupe",
    "1 Padre Nuestro",
    "4 Avemarías por las 4 apariciones",
    "1 Gloria",
    "Cada una de las 4 tandas: intro + 1 Padre Nuestro + acción de gracias específica + 3 Avemarías + Gloria",
    "Cierre: oración a Guadalupe + opcional «Our Lady of Guadalupe, my mother...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Las 4 apariciones a Juan Diego, con texto propio para cada una",
  "notas": "Estructura narrativa: cada grupo de 3 cuenta una aparición distinta; ideal para app con pantallas por episodio."
}
```

### CD-12 · Chaplet of Our Lady of Mount Carmel

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap44.htm
- **Instrumento:** Coronilla: medalla del Carmen + 3 tandas de 3 cuentas
- **Cuentas:** 9 · **Grupos:** 3x3
- **Secuencia:** Dos oraciones iniciales («O most beautiful flower of Mount Carmel...», «O Holy Mary, Mother of God...») → En cada tanda de 3: 1 Padre Nuestro + 1 Avemaría + 1 Gloria (3 veces)
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Una de las formas más simples: 9 cuentas, sin meditación.

```json
{
  "id": "CD-12",
  "nombre": "Chaplet of Our Lady of Mount Carmel",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap44.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla del Carmen + 3 tandas de 3 cuentas",
  "cuentas": "9",
  "grupos": "3x3",
  "pasos": [
    "Dos oraciones iniciales («O most beautiful flower of Mount Carmel...», «O Holy Mary, Mother of God...»)",
    "En cada tanda de 3: 1 Padre Nuestro + 1 Avemaría + 1 Gloria (3 veces)"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Una de las formas más simples: 9 cuentas, sin meditación."
}
```

### CD-13 · Chaplet of Our Lady of Perpetual Help # 1

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap10.htm
- **Instrumento:** Rosario de María (59 cuentas)
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cuenta grande: «O Mother of Perpetual Help, listen to our pleading souls...» → Cuentas pequeñas (×10): «O Mary, help us!» → Tras cada decena: «Mary has helped us, Mary wants to help us, Mary can help us, Mary will help us» → Tras 5 decenas: 3 Avemarías en las cuentas pequeñas finales + Gloria → Cierre: «We fly to your patronage...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Dos formas distintas para el mismo título; la #2 es más breve y usa Salve al inicio.

```json
{
  "id": "CD-13",
  "nombre": "Chaplet of Our Lady of Perpetual Help # 1",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap10.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de María (59 cuentas)",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cuenta grande: «O Mother of Perpetual Help, listen to our pleading souls...»",
    "Cuentas pequeñas (×10): «O Mary, help us!»",
    "Tras cada decena: «Mary has helped us, Mary wants to help us, Mary can help us, Mary will help us»",
    "Tras 5 decenas: 3 Avemarías en las cuentas pequeñas finales + Gloria",
    "Cierre: «We fly to your patronage...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Dos formas distintas para el mismo título; la #2 es más breve y usa Salve al inicio."
}
```

### CD-14 · Chaplet of Our Lady of Perpetual Help # 2

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap54.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** En la cruz: Salve → Cuentas grandes: «Mary can help us, Mary will help us, Mary wants to help us, Mary must help us» → Cuentas pequeñas: «Mother of Perpetual Help, pray for us» → Cierre: Memorare de San Bernardo
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** No parece indicar Credo inicial; arranca con Salve en la cruz.

```json
{
  "id": "CD-14",
  "nombre": "Chaplet of Our Lady of Perpetual Help # 2",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap54.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "En la cruz: Salve",
    "Cuentas grandes: «Mary can help us, Mary will help us, Mary wants to help us, Mary must help us»",
    "Cuentas pequeñas: «Mother of Perpetual Help, pray for us»",
    "Cierre: Memorare de San Bernardo"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "No parece indicar Credo inicial; arranca con Salve en la cruz."
}
```

### CD-15 · Chaplet of Our Lady, Star of the Sea

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap47.htm
- **Instrumento:** Coronilla: medalla + 3 cuentas separadas + tanda de 12
- **Cuentas:** 15 · **Grupos:** 3 + 12
- **Secuencia:** Dos oraciones iniciales → 3 cuentas: 1 Padre Nuestro, 1 Avemaría, 1 Gloria (por los ministros de la Iglesia, Papa, obispo, párroco) → 12 cuentas: 1 Avemaría + «Our Lady, Star of the Sea, help and protect us!» + «Sweet Mother, I place this cause in your hand»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** intención personal en «this cause»
- **Meditación / intenciones:** 12 cuentas = las 12 estrellas de la corona de María
- **Características y advertencias:** Cada cuenta de la segunda tanda lleva dos jaculatorias; no hay Glorias finales.

```json
{
  "id": "CD-15",
  "nombre": "Chaplet of Our Lady, Star of the Sea",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap47.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 3 cuentas separadas + tanda de 12",
  "cuentas": "15",
  "grupos": "3 + 12",
  "pasos": [
    "Dos oraciones iniciales",
    "3 cuentas: 1 Padre Nuestro, 1 Avemaría, 1 Gloria (por los ministros de la Iglesia, Papa, obispo, párroco)",
    "12 cuentas: 1 Avemaría + «Our Lady, Star of the Sea, help and protect us!» + «Sweet Mother, I place this cause in your hand»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "intención personal en «this cause»",
  "meditacion_o_intenciones": "12 cuentas = las 12 estrellas de la corona de María",
  "notas": "Cada cuenta de la segunda tanda lleva dos jaculatorias; no hay Glorias finales."
}
```

### CD-16 · Chaplet of Our Lady's Tears

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap52.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Sustituye el Credo por «Crucified Jesus! We fall at Your feet...» → Sustituye Padres Nuestros por versículo «O Jesus, look upon the tears of her who loved You most...» → Sustituye Avemarías por «O Jesus, hear our prayers, for the sake of Your most holy Mother's tears» → Cierre: el versículo ×3 + oración final «O Mary, Mother of love, sorrow and mercy...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Lágrimas de María
- **Características y advertencias:** Todas las oraciones son versículos responsoriales V/R: la app debe alternar dos voces.

```json
{
  "id": "CD-16",
  "nombre": "Chaplet of Our Lady's Tears",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap52.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Sustituye el Credo por «Crucified Jesus! We fall at Your feet...»",
    "Sustituye Padres Nuestros por versículo «O Jesus, look upon the tears of her who loved You most...»",
    "Sustituye Avemarías por «O Jesus, hear our prayers, for the sake of Your most holy Mother's tears»",
    "Cierre: el versículo ×3 + oración final «O Mary, Mother of love, sorrow and mercy...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Lágrimas de María",
  "notas": "Todas las oraciones son versículos responsoriales V/R: la app debe alternar dos voces."
}
```

### CD-17 · Chaplet of Saint Anne

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap12.htm
- **Instrumento:** Sin instrumento especificado (3 bloques de 1 Padre Nuestro + 5 Avemarías)
- **Cuentas:** 18 oraciones · **Grupos:** 3x6
- **Secuencia:** 1. En honor de Jesús: 1 Padre Nuestro + 5 Avemarías + «Jesus, Mary and St. Anne, grant the favour I ask» → 2. En honor de María: igual → 3. En honor de Santa Ana: 1 Padre Nuestro + 5 Avemarías + «Jesus, and St. Anne, grant the favour I ask»
- **Día / frecuencia:** Uso libre; favores espirituales y temporales
- **Variables del usuario:** favor concreto a pedir
- **Meditación / intenciones:** Tres intenciones: Jesús, María, Santa Ana
- **Características y advertencias:** No describe cuentas: la app debe contar 18 oraciones, no perlas.

```json
{
  "id": "CD-17",
  "nombre": "Chaplet of Saint Anne",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap12.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Sin instrumento especificado (3 bloques de 1 Padre Nuestro + 5 Avemarías)",
  "cuentas": "18 oraciones",
  "grupos": "3x6",
  "pasos": [
    "1. En honor de Jesús: 1 Padre Nuestro + 5 Avemarías + «Jesus, Mary and St. Anne, grant the favour I ask»",
    "2. En honor de María: igual",
    "3. En honor de Santa Ana: 1 Padre Nuestro + 5 Avemarías + «Jesus, and St. Anne, grant the favour I ask»"
  ],
  "dia_o_frecuencia": "Uso libre; favores espirituales y temporales",
  "variables_usuario": "favor concreto a pedir",
  "meditacion_o_intenciones": "Tres intenciones: Jesús, María, Santa Ana",
  "notas": "No describe cuentas: la app debe contar 18 oraciones, no perlas."
}
```

### CD-18 · Chaplet of Saint Anthony

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap29.htm
- **Instrumento:** Coronilla: medalla de San Antonio y el Niño + 13 tandas de 3 cuentas
- **Cuentas:** 39 · **Grupos:** 13x3
- **Secuencia:** Cada tanda: cuenta 1 Padre Nuestro, cuenta 2 Avemaría, cuenta 3 Gloria → Tras las 13 tandas: Responsorio «If miracles thou fain would see...» + Gloria + versículo + oración final
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 13 meditaciones sobre los milagros y virtudes de San Antonio (resucitados, predicación, objetos perdidos, pobres...)
- **Características y advertencias:** La fuente da las 13 intenciones; requiere pantalla de meditación por tanda.

```json
{
  "id": "CD-18",
  "nombre": "Chaplet of Saint Anthony",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap29.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla de San Antonio y el Niño + 13 tandas de 3 cuentas",
  "cuentas": "39",
  "grupos": "13x3",
  "pasos": [
    "Cada tanda: cuenta 1 Padre Nuestro, cuenta 2 Avemaría, cuenta 3 Gloria",
    "Tras las 13 tandas: Responsorio «If miracles thou fain would see...» + Gloria + versículo + oración final"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "13 meditaciones sobre los milagros y virtudes de San Antonio (resucitados, predicación, objetos perdidos, pobres...)",
  "notas": "La fuente da las 13 intenciones; requiere pantalla de meditación por tanda."
}
```

### CD-19 · Chaplet of Saint Dymphna

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap48.htm
- **Instrumento:** Coronilla: medalla + 2 cuentas + 15 cuentas de colores (5 rojas, 5 blancas, 5 verdes)
- **Cuentas:** 17 · **Grupos:** 2 + 15
- **Secuencia:** Intenciones antes o después → Cuenta 1: Padre Nuestro → Cuenta 2: Avemaría por las intenciones del Papa → 15 cuentas: 15 Glorias por los 15 años de vida de Santa Dymphna
- **Día / frecuencia:** Uso libre; especial para trastornos emocionales
- **Variables del usuario:** intenciones personales
- **Meditación / intenciones:** Rojo=martirio, blanco=virginidad, verde=esperanza de alivio en trastornos emocionales
- **Características y advertencias:** Coronilla de colores: la app debe pedir el color de cada cuenta.

```json
{
  "id": "CD-19",
  "nombre": "Chaplet of Saint Dymphna",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap48.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 2 cuentas + 15 cuentas de colores (5 rojas, 5 blancas, 5 verdes)",
  "cuentas": "17",
  "grupos": "2 + 15",
  "pasos": [
    "Intenciones antes o después",
    "Cuenta 1: Padre Nuestro",
    "Cuenta 2: Avemaría por las intenciones del Papa",
    "15 cuentas: 15 Glorias por los 15 años de vida de Santa Dymphna"
  ],
  "dia_o_frecuencia": "Uso libre; especial para trastornos emocionales",
  "variables_usuario": "intenciones personales",
  "meditacion_o_intenciones": "Rojo=martirio, blanco=virginidad, verde=esperanza de alivio en trastornos emocionales",
  "notas": "Coronilla de colores: la app debe pedir el color de cada cuenta."
}
```

### CD-20 · Chaplet of Saint Frances Xavier Cabrini

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap34.htm
- **Instrumento:** Coronilla: 25 cuentas, 5 tandas de 5 + medalla de Mother Cabrini
- **Cuentas:** 25 · **Grupos:** 5x5
- **Secuencia:** En cada cuenta: Avemaría → Tras cada tanda de 5: «O My Saviour, hear Saint Frances Xavier Cabrini plead for me»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Formato inusual 5x5; una sola jaculatoria de cierre por tanda.

```json
{
  "id": "CD-20",
  "nombre": "Chaplet of Saint Frances Xavier Cabrini",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap34.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: 25 cuentas, 5 tandas de 5 + medalla de Mother Cabrini",
  "cuentas": "25",
  "grupos": "5x5",
  "pasos": [
    "En cada cuenta: Avemaría",
    "Tras cada tanda de 5: «O My Saviour, hear Saint Frances Xavier Cabrini plead for me»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Formato inusual 5x5; una sola jaculatoria de cierre por tanda."
}
```

### CD-21 · Chaplet of Saint Gabriel

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap38.htm
- **Instrumento:** Coronilla: medalla grande + 3 cuentas + medalla pequeña en la unión + 3 tandas de 11 separadas por 1 cuenta
- **Cuentas:** 38 · **Grupos:** 3 + 3x11 + 2
- **Secuencia:** 3 primeras cuentas: tres oraciones propias (al Padre, a María, a Gabriel) → Cada cuenta de las tandas de 11: «Hail, full of grace, the Lord is with Thee...» → Las 2 cuentas separadoras: «Behold thou shalt conceive in thy womb...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 3x11 = 33 años de la vida del Salvador; 2 cuentas = divinidad y humanidad
- **Características y advertencias:** Estructura en Y; exige distinguir entre cuentas de tanda y separadoras.

```json
{
  "id": "CD-21",
  "nombre": "Chaplet of Saint Gabriel",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap38.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla grande + 3 cuentas + medalla pequeña en la unión + 3 tandas de 11 separadas por 1 cuenta",
  "cuentas": "38",
  "grupos": "3 + 3x11 + 2",
  "pasos": [
    "3 primeras cuentas: tres oraciones propias (al Padre, a María, a Gabriel)",
    "Cada cuenta de las tandas de 11: «Hail, full of grace, the Lord is with Thee...»",
    "Las 2 cuentas separadoras: «Behold thou shalt conceive in thy womb...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "3x11 = 33 años de la vida del Salvador; 2 cuentas = divinidad y humanidad",
  "notas": "Estructura en Y; exige distinguir entre cuentas de tanda y separadoras."
}
```

### CD-22 · Chaplet of Saint Gertrude

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap40.htm
- **Instrumento:** Rosario de 59 cuentas (cruz+1+3+1+medalla/10+1...)
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** En la cruz: Credo → 1 Padre Nuestro, 3 Avemarías, 1 Gloria → Medalla y 4 cuentas grandes: Padre Nuestro → Cuentas pequeñas: ofrenda de la Preciosa Sangre «Eternal Father, I offer Thee... in union with the Masses said throughout the world today...» → Tras cada decena: dos oraciones (Sagrado Corazón, Inmaculado Corazón) + Gloria
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Ofrenda por almas del Purgatorio, pecadores, Iglesia, familia
- **Características y advertencias:** La oración de las cuentas pequeñas es larguísima; conviene mostrarla en pantalla completa.

```json
{
  "id": "CD-22",
  "nombre": "Chaplet of Saint Gertrude",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap40.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas (cruz+1+3+1+medalla/10+1...)",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "En la cruz: Credo",
    "1 Padre Nuestro, 3 Avemarías, 1 Gloria",
    "Medalla y 4 cuentas grandes: Padre Nuestro",
    "Cuentas pequeñas: ofrenda de la Preciosa Sangre «Eternal Father, I offer Thee... in union with the Masses said throughout the world today...»",
    "Tras cada decena: dos oraciones (Sagrado Corazón, Inmaculado Corazón) + Gloria"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Ofrenda por almas del Purgatorio, pecadores, Iglesia, familia",
  "notas": "La oración de las cuentas pequeñas es larguísima; conviene mostrarla en pantalla completa."
}
```

### CD-23 · Chaplet of Saint Joseph

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap11.htm
- **Instrumento:** Rosario (cuentas grandes y pequeñas)
- **Cuentas:** No se especifica en la fuente · **Grupos:** 5x10
- **Secuencia:** Cuentas grandes: «St. Joseph guardian of the Holy Family, bless our families» → Cuentas pequeñas: «St. Joseph pray for us» → Cierre: «Jesus, Mary and Joseph, I give You my heart and my soul...»
- **Día / frecuencia:** Uso libre; protección familiar
- **Variables del usuario:** —
- **Meditación / intenciones:** Protección de la familia
- **Características y advertencias:** La fuente no especifica Credo ni cuenta inicial; solo grandes/pequeñas.

```json
{
  "id": "CD-23",
  "nombre": "Chaplet of Saint Joseph",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap11.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario (cuentas grandes y pequeñas)",
  "cuentas": "No se especifica en la fuente",
  "grupos": "5x10",
  "pasos": [
    "Cuentas grandes: «St. Joseph guardian of the Holy Family, bless our families»",
    "Cuentas pequeñas: «St. Joseph pray for us»",
    "Cierre: «Jesus, Mary and Joseph, I give You my heart and my soul...»"
  ],
  "dia_o_frecuencia": "Uso libre; protección familiar",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Protección de la familia",
  "notas": "La fuente no especifica Credo ni cuenta inicial; solo grandes/pequeñas."
}
```

### CD-24 · Chaplet of Saint Jude

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap46.htm
- **Instrumento:** Coronilla: cruz + 9 cuentas + medalla de San Judas
- **Cuentas:** 9 · **Grupos:** 1x9
- **Secuencia:** En la cruz: oración larga a San Judas (con intención) → En cada cuenta: 1 Avemaría + «Saint Jude, Apostle martyr and relative of Our Lord...» → Cierre: 4 oraciones de alabanza al Sagrado Corazón y a San Judas
- **Día / frecuencia:** Uso libre; causas desesperadas
- **Variables del usuario:** intención en «particularly...»
- **Meditación / intenciones:** Patrocinio de San Judas en lo desesperado
- **Características y advertencias:** Muy corta; requiere campo de intención en la cruz.

```json
{
  "id": "CD-24",
  "nombre": "Chaplet of Saint Jude",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap46.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: cruz + 9 cuentas + medalla de San Judas",
  "cuentas": "9",
  "grupos": "1x9",
  "pasos": [
    "En la cruz: oración larga a San Judas (con intención)",
    "En cada cuenta: 1 Avemaría + «Saint Jude, Apostle martyr and relative of Our Lord...»",
    "Cierre: 4 oraciones de alabanza al Sagrado Corazón y a San Judas"
  ],
  "dia_o_frecuencia": "Uso libre; causas desesperadas",
  "variables_usuario": "intención en «particularly...»",
  "meditacion_o_intenciones": "Patrocinio de San Judas en lo desesperado",
  "notas": "Muy corta; requiere campo de intención en la cruz."
}
```

### CD-25 · Chaplet of Saint Kateri Tekakwitha

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap33.htm
- **Instrumento:** Coronilla de 24 cuentas por colores: 8 marrones, 8 rojas, 8 transparentes
- **Cuentas:** 24 · **Grupos:** 3x8
- **Secuencia:** Cuentas marrones: Padre Nuestro → Rojas: Avemaría → Transparentes: Gloria
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 24 cuentas = 24 años de vida de Santa Kateri; colores: tierra, sangre, agua
- **Características y advertencias:** Se conoce también como Kateri Indian Rosary; la app debe reconocer color de cuenta.

```json
{
  "id": "CD-25",
  "nombre": "Chaplet of Saint Kateri Tekakwitha",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap33.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 24 cuentas por colores: 8 marrones, 8 rojas, 8 transparentes",
  "cuentas": "24",
  "grupos": "3x8",
  "pasos": [
    "Cuentas marrones: Padre Nuestro",
    "Rojas: Avemaría",
    "Transparentes: Gloria"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "24 cuentas = 24 años de vida de Santa Kateri; colores: tierra, sangre, agua",
  "notas": "Se conoce también como Kateri Indian Rosary; la app debe reconocer color de cuenta."
}
```

### CD-26 · Chaplet of Saint Maximillian

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap42.htm
- **Instrumento:** Coronilla: medalla de San Maximiliano + 1 cuenta + medalla de la Virgen + 14 cuentas
- **Cuentas:** 15 · **Grupos:** 1 + 14
- **Secuencia:** Cuenta entre medallas: Memorare → Cada una de las 14 cuentas: oración de la Medalla Milagrosa «O Mary, conceived without sin...» + «St. Maximillian, pray for us!»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Dos medallas; la cuenta intermedia cambia completamente de oración.

```json
{
  "id": "CD-26",
  "nombre": "Chaplet of Saint Maximillian",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap42.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla de San Maximiliano + 1 cuenta + medalla de la Virgen + 14 cuentas",
  "cuentas": "15",
  "grupos": "1 + 14",
  "pasos": [
    "Cuenta entre medallas: Memorare",
    "Cada una de las 14 cuentas: oración de la Medalla Milagrosa «O Mary, conceived without sin...» + «St. Maximillian, pray for us!»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Dos medallas; la cuenta intermedia cambia completamente de oración."
}
```

### CD-27 · Chaplet of Saint Michael (Archangel)

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap08.htm
- **Instrumento:** Rosario o coronilla; 9 salutaciones (1 Padre Nuestro + 3 Avemarías cada una) más 4 cuentas finales
- **Cuentas:** 9x(1+3) + 4 = 40 oraciones (más la oración inicial) · **Grupos:** 9x(1+3) + 4
- **Secuencia:** En la medalla: invocación «O God, come to my assistance...» + Gloria → Nueve salutaciones a los 9 coros; tras cada una, 1 Padre Nuestro + 3 Avemarías → Al final: 1 Padre Nuestro en cada una de las 4 cuentas (San Miguel, San Gabriel, San Rafael y Ángel Guardián) → Cierre: «O glorious Prince St. Michael...», versículo y oración final
- **Día / frecuencia:** Devoción diaria recomendada por la fuente
- **Variables del usuario:** —
- **Meditación / intenciones:** 9 coros: Serafines, Querubines, Tronos, Dominaciones, Virtudes, Principados, Potestades, Arcángeles, Ángeles
- **Características y advertencias:** La fuente incluye promesas (escuadra de 9 ángeles, auxilio diario, liberación del Purgatorio) — texto histórico.

```json
{
  "id": "CD-27",
  "nombre": "Chaplet of Saint Michael (Archangel)",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap08.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario o coronilla; 9 salutaciones (1 Padre Nuestro + 3 Avemarías cada una) más 4 cuentas finales",
  "cuentas": "9x(1+3) + 4 = 40 oraciones (más la oración inicial)",
  "grupos": "9x(1+3) + 4",
  "pasos": [
    "En la medalla: invocación «O God, come to my assistance...» + Gloria",
    "Nueve salutaciones a los 9 coros; tras cada una, 1 Padre Nuestro + 3 Avemarías",
    "Al final: 1 Padre Nuestro en cada una de las 4 cuentas (San Miguel, San Gabriel, San Rafael y Ángel Guardián)",
    "Cierre: «O glorious Prince St. Michael...», versículo y oración final"
  ],
  "dia_o_frecuencia": "Devoción diaria recomendada por la fuente",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "9 coros: Serafines, Querubines, Tronos, Dominaciones, Virtudes, Principados, Potestades, Arcángeles, Ángeles",
  "notas": "La fuente incluye promesas (escuadra de 9 ángeles, auxilio diario, liberación del Purgatorio) — texto histórico."
}
```

### CD-28 · Chaplet of Saint Padre Pio

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap25.htm
- **Instrumento:** Coronilla: medalla + crucifijo + 3 tandas de 3 cuentas marrones (9)
- **Cuentas:** 9 · **Grupos:** 3x3
- **Secuencia:** En cada una de las 9 cuentas: oración larga «Oh Jesus, full of grace and charity... glorify ... Padre Pio...» con petición personal → Cierre: 3 Padres Nuestros, 3 Avemarías, 3 Glorias
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** petición concreta en «Mention your request here»
- **Meditación / intenciones:** —
- **Características y advertencias:** La oración de cuenta es larga y personalizable; el cierre es triple 3-3-3.

```json
{
  "id": "CD-28",
  "nombre": "Chaplet of Saint Padre Pio",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap25.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + crucifijo + 3 tandas de 3 cuentas marrones (9)",
  "cuentas": "9",
  "grupos": "3x3",
  "pasos": [
    "En cada una de las 9 cuentas: oración larga «Oh Jesus, full of grace and charity... glorify ... Padre Pio...» con petición personal",
    "Cierre: 3 Padres Nuestros, 3 Avemarías, 3 Glorias"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "petición concreta en «Mention your request here»",
  "meditacion_o_intenciones": "—",
  "notas": "La oración de cuenta es larga y personalizable; el cierre es triple 3-3-3."
}
```

### CD-29 · Chaplet of Saint Patrick

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap26.htm
- **Instrumento:** Coronilla: medalla + 12 cuentas verdes
- **Cuentas:** 12 · **Grupos:** 1x12
- **Secuencia:** Inicio: Credo de los Apóstoles → Cada cuenta verde: Gloria → Cierre: Coraza de San Patrick («Christ as a light...»)
- **Día / frecuencia:** Uso libre; fortalecer la fe
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** No usa Avemarías: solo Gloria ×12 y la Coraza final.

```json
{
  "id": "CD-29",
  "nombre": "Chaplet of Saint Patrick",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap26.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 12 cuentas verdes",
  "cuentas": "12",
  "grupos": "1x12",
  "pasos": [
    "Inicio: Credo de los Apóstoles",
    "Cada cuenta verde: Gloria",
    "Cierre: Coraza de San Patrick («Christ as a light...»)"
  ],
  "dia_o_frecuencia": "Uso libre; fortalecer la fe",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "No usa Avemarías: solo Gloria ×12 y la Coraza final."
}
```

### CD-30 · Chaplet of Saint Philomena

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap32.htm
- **Instrumento:** Coronilla: medalla + 3 cuentas blancas + 13 rojas
- **Cuentas:** 16 · **Grupos:** 3 + 13
- **Secuencia:** Medalla: Credo → 3 cuentas blancas: Padre Nuestro (Trinidad) → 13 cuentas rojas: «Hail, O holy St. Philomena...» → Cierre: «Hail, O illustrious St. Philomena...» + intención
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** intención final
- **Meditación / intenciones:** 13 años que Santa Filomena estuvo en la tierra
- **Características y advertencias:** Dos colores con funciones distintas.

```json
{
  "id": "CD-30",
  "nombre": "Chaplet of Saint Philomena",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap32.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 3 cuentas blancas + 13 rojas",
  "cuentas": "16",
  "grupos": "3 + 13",
  "pasos": [
    "Medalla: Credo",
    "3 cuentas blancas: Padre Nuestro (Trinidad)",
    "13 cuentas rojas: «Hail, O holy St. Philomena...»",
    "Cierre: «Hail, O illustrious St. Philomena...» + intención"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "intención final",
  "meditacion_o_intenciones": "13 años que Santa Filomena estuvo en la tierra",
  "notas": "Dos colores con funciones distintas."
}
```

### CD-31 · Chaplet of Saint Raphael (Archangel)

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap13.htm
- **Instrumento:** Coronilla: medalla + 3 cuentas finales + 9 cuentas (12)
- **Cuentas:** 12 · **Grupos:** 3 + 9
- **Secuencia:** Medalla: «You are Raphael the Healer...» → 3 cuentas pequeñas: Avemarías a María, reina de los ángeles → 9 cuentas: «Holy, holy, holy Lord, God of Hosts...» + Glorias trinitarias → Cierre: «Saint Raphael, angel of health, of love, of joy and light, pray for us»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 9 cuentas = los 9 coros angélicos (ángeles, arcángeles, tronos, dominaciones, virtudes, principados, potestades, querubines, serafines)
- **Características y advertencias:** Estructura similar a San Miguel pero con Santísima Trinidad en vez de Padre+Avemaría.

```json
{
  "id": "CD-31",
  "nombre": "Chaplet of Saint Raphael (Archangel)",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap13.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 3 cuentas finales + 9 cuentas (12)",
  "cuentas": "12",
  "grupos": "3 + 9",
  "pasos": [
    "Medalla: «You are Raphael the Healer...»",
    "3 cuentas pequeñas: Avemarías a María, reina de los ángeles",
    "9 cuentas: «Holy, holy, holy Lord, God of Hosts...» + Glorias trinitarias",
    "Cierre: «Saint Raphael, angel of health, of love, of joy and light, pray for us»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "9 cuentas = los 9 coros angélicos (ángeles, arcángeles, tronos, dominaciones, virtudes, principados, potestades, querubines, serafines)",
  "notas": "Estructura similar a San Miguel pero con Santísima Trinidad en vez de Padre+Avemaría."
}
```

### CD-32 · Chaplet of Saint Rita

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap41.htm
- **Instrumento:** Coronilla: medalla + 3 tandas de 3 cuentas azules + cruz
- **Cuentas:** 9 · **Grupos:** 3x3
- **Secuencia:** Oración inicial a Santa Rita → 3 Padres Nuestros, 3 Avemarías, 3 Glorias
- **Día / frecuencia:** Uso libre; patrona de lo imposible
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Cuentas azules; una de las coronillas más cortas.

```json
{
  "id": "CD-32",
  "nombre": "Chaplet of Saint Rita",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap41.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 3 tandas de 3 cuentas azules + cruz",
  "cuentas": "9",
  "grupos": "3x3",
  "pasos": [
    "Oración inicial a Santa Rita",
    "3 Padres Nuestros, 3 Avemarías, 3 Glorias"
  ],
  "dia_o_frecuencia": "Uso libre; patrona de lo imposible",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Cuentas azules; una de las coronillas más cortas."
}
```

### CD-33 · Chaplet of Saint Theresa (the Little Flower)

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap02.htm
- **Instrumento:** Rosario de Santa Teresa: 24 cuentas + 1 adicional
- **Cuentas:** 25 · **Grupos:** 25
- **Secuencia:** Cuenta 1: «Saint Therese of the Infant Jesus, Patroness of Missions, pray for us» → Otras 24: Gloria al Padre
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 24 cuentas = 24 años de vida de Santa Teresita
- **Características y advertencias:** Casi monótono: 24 Glorias; la app solo cambia la primera cuenta.

```json
{
  "id": "CD-33",
  "nombre": "Chaplet of Saint Theresa (the Little Flower)",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap02.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de Santa Teresa: 24 cuentas + 1 adicional",
  "cuentas": "25",
  "grupos": "25",
  "pasos": [
    "Cuenta 1: «Saint Therese of the Infant Jesus, Patroness of Missions, pray for us»",
    "Otras 24: Gloria al Padre"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "24 cuentas = 24 años de vida de Santa Teresita",
  "notas": "Casi monótono: 24 Glorias; la app solo cambia la primera cuenta."
}
```

### CD-34 · Chaplet of Tears

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap37.htm
- **Instrumento:** Coronilla de 59 cuentas: medalla grande + 3 + 1 + medalla de Lourdes + 7 tandas de 7 separadas por 6 cuentas
- **Cuentas:** 59 · **Grupos:** 7x7 + 6
- **Secuencia:** Señal de la Cruz → Medalla grande: oración a Cristo crucificado → 3 cuentas: «O Jesus! Hear our petitions...» → Cuenta siguiente: «O Jesus! Look upon the tears...» → Cada tanda: versículo previo + 7 veces «O Jesus, hear our prayers, for the sake of Your most holy Mother's tears» → Tras 7 tandas: versículo ×3
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 7 grupos de lágrimas: infancia, huida a Egipto, búsqueda de Jesús, Vía Crucis, agonía, descendimiento, sepulcro
- **Características y advertencias:** Texto responsorial V/R; distinta de la Chaplet of Our Lady's Tears (ésta usa rosario de 59 y 5 decenas).

```json
{
  "id": "CD-34",
  "nombre": "Chaplet of Tears",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap37.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 59 cuentas: medalla grande + 3 + 1 + medalla de Lourdes + 7 tandas de 7 separadas por 6 cuentas",
  "cuentas": "59",
  "grupos": "7x7 + 6",
  "pasos": [
    "Señal de la Cruz",
    "Medalla grande: oración a Cristo crucificado",
    "3 cuentas: «O Jesus! Hear our petitions...»",
    "Cuenta siguiente: «O Jesus! Look upon the tears...»",
    "Cada tanda: versículo previo + 7 veces «O Jesus, hear our prayers, for the sake of Your most holy Mother's tears»",
    "Tras 7 tandas: versículo ×3"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "7 grupos de lágrimas: infancia, huida a Egipto, búsqueda de Jesús, Vía Crucis, agonía, descendimiento, sepulcro",
  "notas": "Texto responsorial V/R; distinta de la Chaplet of Our Lady's Tears (ésta usa rosario de 59 y 5 decenas)."
}
```

### CD-35 · Chaplet of the Blessed Sacrament

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap31.htm
- **Instrumento:** Coronilla: medalla del Santísimo + 33 cuentas
- **Cuentas:** 33 · **Grupos:** 1x33
- **Secuencia:** Medalla: comunión espiritual «As I cannot now receive Thee, my Jesus...» → Cada cuenta: «Jesus in the Blessed Sacrament on the altar have mercy on us!»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 33 cuentas = 33 años de la vida de Cristo
- **Características y advertencias:** La fuente menciona aprobación de Pío X (1911) e indulgencia de 300 días — verificar normativa vigente.

```json
{
  "id": "CD-35",
  "nombre": "Chaplet of the Blessed Sacrament",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap31.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla del Santísimo + 33 cuentas",
  "cuentas": "33",
  "grupos": "1x33",
  "pasos": [
    "Medalla: comunión espiritual «As I cannot now receive Thee, my Jesus...»",
    "Cada cuenta: «Jesus in the Blessed Sacrament on the altar have mercy on us!»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "33 cuentas = 33 años de la vida de Cristo",
  "notas": "La fuente menciona aprobación de Pío X (1911) e indulgencia de 300 días — verificar normativa vigente."
}
```

### CD-36 · Chaplet of the Five Wounds

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap05.htm
- **Instrumento:** Coronilla: 5 grupos de 5 cuentas (grande + 4 pequeñas)
- **Cuentas:** 25 · **Grupos:** 5x5
- **Secuencia:** Cada cuenta: 1 Gloria + 1 Avemaría por la Virgen → Cuenta grande: «Eternal Father, I offer Thee the Wounds of Our Lord Jesus Christ...» → Cuentas pequeñas: «My Jesus, pardon and mercy through the merits of Thy Sacred Wounds»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 llagas: pie izquierdo, pie derecho, mano izquierda, mano derecha, costado
- **Características y advertencias:** Origen pasionista; aprobada por Pío VII en 1822 según la fuente.

```json
{
  "id": "CD-36",
  "nombre": "Chaplet of the Five Wounds",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap05.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: 5 grupos de 5 cuentas (grande + 4 pequeñas)",
  "cuentas": "25",
  "grupos": "5x5",
  "pasos": [
    "Cada cuenta: 1 Gloria + 1 Avemaría por la Virgen",
    "Cuenta grande: «Eternal Father, I offer Thee the Wounds of Our Lord Jesus Christ...»",
    "Cuentas pequeñas: «My Jesus, pardon and mercy through the merits of Thy Sacred Wounds»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 llagas: pie izquierdo, pie derecho, mano izquierda, mano derecha, costado",
  "notas": "Origen pasionista; aprobada por Pío VII en 1822 según la fuente."
}
```

### CD-37 · Chaplet of the Holy Face

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap07.htm
- **Instrumento:** Coronilla: medalla + 39 cuentas (6 grandes, 33 pequeñas)
- **Cuentas:** 39 · **Grupos:** 6 + 33
- **Secuencia:** 6 cuentas grandes: 1 Gloria + «My Jesus, mercy» (meditando un sentido/herida) → 33 pequeñas: «Arise, O Lord, and let Thy enemies be scattered...» → Cierre: Gloria ×7 + «O God, our Protector...» en la medalla
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 30 cuentas = vida oculta (5 sentidos); 3 = vida pública; cierre = 7 últimas palabras y 7 dolores
- **Características y advertencias:** No confundir con Chaplet of the Sacred Face (#51), que usa rosario de 59.

```json
{
  "id": "CD-37",
  "nombre": "Chaplet of the Holy Face",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap07.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 39 cuentas (6 grandes, 33 pequeñas)",
  "cuentas": "39",
  "grupos": "6 + 33",
  "pasos": [
    "6 cuentas grandes: 1 Gloria + «My Jesus, mercy» (meditando un sentido/herida)",
    "33 pequeñas: «Arise, O Lord, and let Thy enemies be scattered...»",
    "Cierre: Gloria ×7 + «O God, our Protector...» en la medalla"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "30 cuentas = vida oculta (5 sentidos); 3 = vida pública; cierre = 7 últimas palabras y 7 dolores",
  "notas": "No confundir con Chaplet of the Sacred Face (#51), que usa rosario de 59."
}
```

### CD-38 · Chaplet of the Holy Ghost

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap23.htm
- **Instrumento:** Coronilla de 50 cuentas: 3 cuentas iniciales + 5 tandas de 7 separadas por 2 grandes
- **Cuentas:** 50 · **Grupos:** 5x7 + 3 + 10
- **Secuencia:** 3 cuentas iniciales: Señal de la Cruz, Acto de Contrición e himno «Come, Holy Ghost» → Cada tanda de 7: 7 Glorias → Las 2 grandes: 1 Padre Nuestro y 1 Avemaría → Cierre: Credo + Padre Nuestro, Avemaría y Gloria por el Papa
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 misterios con cita bíblica: 1) Jesús concebido por el Espíritu en María (Lc 1,35); 2) el Espíritu sobre Jesús en el Bautismo (Mt 3,16); 3) Jesús llevado al desierto y tentado (Lc 4,1-2); 4) el Espíritu en la Iglesia (Hch 2); 5) el Espíritu en el alma del justo (1 Cor 6,19)
- **Características y advertencias:** Obra de un capuchino en 1892, aprobada por León XIII en 1902. Requiere 5 textos de meditación bíblica. Ojo: la fuente afirma 50 cuentas pero describe 3 iniciales + 5x7 + 2 separadoras por grupo, cifras que no cuadran (46 o 48); verificar antes de fijar el contador.

```json
{
  "id": "CD-38",
  "nombre": "Chaplet of the Holy Ghost",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap23.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 50 cuentas: 3 cuentas iniciales + 5 tandas de 7 separadas por 2 grandes",
  "cuentas": "50",
  "grupos": "5x7 + 3 + 10",
  "pasos": [
    "3 cuentas iniciales: Señal de la Cruz, Acto de Contrición e himno «Come, Holy Ghost»",
    "Cada tanda de 7: 7 Glorias",
    "Las 2 grandes: 1 Padre Nuestro y 1 Avemaría",
    "Cierre: Credo + Padre Nuestro, Avemaría y Gloria por el Papa"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 misterios con cita bíblica: 1) Jesús concebido por el Espíritu en María (Lc 1,35); 2) el Espíritu sobre Jesús en el Bautismo (Mt 3,16); 3) Jesús llevado al desierto y tentado (Lc 4,1-2); 4) el Espíritu en la Iglesia (Hch 2); 5) el Espíritu en el alma del justo (1 Cor 6,19)",
  "notas": "Obra de un capuchino en 1892, aprobada por León XIII en 1902. Requiere 5 textos de meditación bíblica. Ojo: la fuente afirma 50 cuentas pero describe 3 iniciales + 5x7 + 2 separadoras por grupo, cifras que no cuadran (46 o 48); verificar antes de fijar el contador."
}
```

### CD-39 · Chaplet of the Holy Infant

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap39.htm
- **Instrumento:** Coronilla: medalla + 3 cuentas + 12 cuentas
- **Cuentas:** 15 · **Grupos:** 3 + 12
- **Secuencia:** Medalla: «Divine Infant Jesus, I adore Thy Cross...» → 3 cuentas: «And the Word was made flesh...» + Padre Nuestro (×3) → Cuenta 1 de las 12: misma frase + Avemaría → Resto (11): Avemarías → Cierre: «Holy Infant Jesus, bless and protect us»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Muy similar a la del Niño Jesús de Praga, pero con 3+12 y frase antes de cada oración.

```json
{
  "id": "CD-39",
  "nombre": "Chaplet of the Holy Infant",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap39.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla + 3 cuentas + 12 cuentas",
  "cuentas": "15",
  "grupos": "3 + 12",
  "pasos": [
    "Medalla: «Divine Infant Jesus, I adore Thy Cross...»",
    "3 cuentas: «And the Word was made flesh...» + Padre Nuestro (×3)",
    "Cuenta 1 de las 12: misma frase + Avemaría",
    "Resto (11): Avemarías",
    "Cierre: «Holy Infant Jesus, bless and protect us»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Muy similar a la del Niño Jesús de Praga, pero con 3+12 y frase antes de cada oración."
}
```

### CD-40 · Chaplet of the Holy Name

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/english6/p03527.htm
- **Instrumento:** Coronilla de 33 cuentas (3 decenas de 1 grande + 10 pequeñas); admite chotki de 33 nudos
- **Cuentas:** 33 · **Grupos:** 3x(1+10)
- **Secuencia:** Acto de Contrición → «Incline unto my aid, O God...» → Decena 1, cuenta grande: «Lord, Thou hast said: Ask and ye shall receive...» (con intención) → 10 pequeñas: «Incline unto my aid, O God...» → Decena 2: «Amen, I say unto you, if ye ask the Father anything in My Name...» → Decena 3: «Heaven and earth shall pass away, but My Word shall not pass away...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** intención en la primera decena
- **Meditación / intenciones:** Tres promesas de Cristo sobre la oración
- **Características y advertencias:** Usa 33 cuentas tipo oriental; la jaculatoria se repite 30 veces.

```json
{
  "id": "CD-40",
  "nombre": "Chaplet of the Holy Name",
  "fuente": "https://www.catholicdoors.com/prayers/english6/p03527.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 33 cuentas (3 decenas de 1 grande + 10 pequeñas); admite chotki de 33 nudos",
  "cuentas": "33",
  "grupos": "3x(1+10)",
  "pasos": [
    "Acto de Contrición",
    "«Incline unto my aid, O God...»",
    "Decena 1, cuenta grande: «Lord, Thou hast said: Ask and ye shall receive...» (con intención)",
    "10 pequeñas: «Incline unto my aid, O God...»",
    "Decena 2: «Amen, I say unto you, if ye ask the Father anything in My Name...»",
    "Decena 3: «Heaven and earth shall pass away, but My Word shall not pass away...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "intención en la primera decena",
  "meditacion_o_intenciones": "Tres promesas de Cristo sobre la oración",
  "notas": "Usa 33 cuentas tipo oriental; la jaculatoria se repite 30 veces."
}
```

### CD-41 · Chaplet Of the Holy Wounds

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap57.htm
- **Instrumento:** Rosario mariano de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Crucifijo y 3 primeras cuentas: cuatro oraciones (Divine Redeemer; Strong God; Grace and Mercy; Eternal Father) → Cuentas grandes: «Eternal Father, I offer You the Wounds of Our Lord Jesus Christ...» → Cuentas pequeñas: «My Jesus, pardon and mercy, through the merits of Your Holy Wounds»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Revelado a Sor Mary Martha Chambon (1841-1907)
- **Características y advertencias:** Muy parecido al de las Cinco Llagas, pero con inicio especial de 4 oraciones y sin meditación de 5 llagas.

```json
{
  "id": "CD-41",
  "nombre": "Chaplet Of the Holy Wounds",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap57.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Crucifijo y 3 primeras cuentas: cuatro oraciones (Divine Redeemer; Strong God; Grace and Mercy; Eternal Father)",
    "Cuentas grandes: «Eternal Father, I offer You the Wounds of Our Lord Jesus Christ...»",
    "Cuentas pequeñas: «My Jesus, pardon and mercy, through the merits of Your Holy Wounds»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Revelado a Sor Mary Martha Chambon (1841-1907)",
  "notas": "Muy parecido al de las Cinco Llagas, pero con inicio especial de 4 oraciones y sin meditación de 5 llagas."
}
```

### CD-42 · Chaplet of the Immaculate Conception

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap36.htm
- **Instrumento:** Coronilla de 15 cuentas: 3 tandas de 4 + 1 cuenta final + Medalla Milagrosa
- **Cuentas:** 15 · **Grupos:** 3x4 + 1
- **Secuencia:** Señal de la Cruz → Cada tanda: 1 Padre Nuestro, 4 Avemarías, 1 Gloria, más «Blessed be the Holy and Immaculate Conception...»
- **Día / frecuencia:** Se recomienda diaria
- **Variables del usuario:** —
- **Meditación / intenciones:** Pureza; compuesta por San Juan Berchmans S.J.
- **Características y advertencias:** La fuente da el propósito: nunca pecar contra la pureza. Ojo: afirma 15 cuentas pero describe 3x4 + 1 = 13; verificar el cómputo.

```json
{
  "id": "CD-42",
  "nombre": "Chaplet of the Immaculate Conception",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap36.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 15 cuentas: 3 tandas de 4 + 1 cuenta final + Medalla Milagrosa",
  "cuentas": "15",
  "grupos": "3x4 + 1",
  "pasos": [
    "Señal de la Cruz",
    "Cada tanda: 1 Padre Nuestro, 4 Avemarías, 1 Gloria, más «Blessed be the Holy and Immaculate Conception...»"
  ],
  "dia_o_frecuencia": "Se recomienda diaria",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Pureza; compuesta por San Juan Berchmans S.J.",
  "notas": "La fuente da el propósito: nunca pecar contra la pureza. Ojo: afirma 15 cuentas pero describe 3x4 + 1 = 13; verificar el cómputo."
}
```

### CD-43 · Chaplet of the Immaculate Heart of Mary

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap50.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz repetida 5 veces (por las 5 llagas) → Cuentas grandes: «Sorrowful and Immaculate Heart of Mary, pray for those who seek refuge in you» → Cuentas pequeñas: «Holy Mother, save us through your Immaculate Heart's flame of love» → Cierre: 3 Glorias
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** La señal de la Cruz ×5 es un rasgo único; conviene contarla como paso explícito.

```json
{
  "id": "CD-43",
  "nombre": "Chaplet of the Immaculate Heart of Mary",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap50.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz repetida 5 veces (por las 5 llagas)",
    "Cuentas grandes: «Sorrowful and Immaculate Heart of Mary, pray for those who seek refuge in you»",
    "Cuentas pequeñas: «Holy Mother, save us through your Immaculate Heart's flame of love»",
    "Cierre: 3 Glorias"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "La señal de la Cruz ×5 es un rasgo único; conviene contarla como paso explícito."
}
```

### CD-44 · Chaplet of the Infant Jesus of Prague

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap14.htm
- **Instrumento:** Coronilla de 15 cuentas
- **Cuentas:** 15 · **Grupos:** 3 + 12
- **Secuencia:** Medalla: «Divine Infant Jesus, I adore Thy Cross...» → Antes de cada oración: «And the Word was made flesh...» → 3 Padres Nuestros (Sagrada Familia) + 12 Avemarías (años de la infancia) → Cierre: «Holy Infant Jesus, bless and protect us»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 12 años de la infancia de Jesús
- **Características y advertencias:** Muy similar a Chaplet of the Holy Infant; aquí 15 cuentas y frase antes de cada oración.

```json
{
  "id": "CD-44",
  "nombre": "Chaplet of the Infant Jesus of Prague",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap14.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 15 cuentas",
  "cuentas": "15",
  "grupos": "3 + 12",
  "pasos": [
    "Medalla: «Divine Infant Jesus, I adore Thy Cross...»",
    "Antes de cada oración: «And the Word was made flesh...»",
    "3 Padres Nuestros (Sagrada Familia) + 12 Avemarías (años de la infancia)",
    "Cierre: «Holy Infant Jesus, bless and protect us»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "12 años de la infancia de Jesús",
  "notas": "Muy similar a Chaplet of the Holy Infant; aquí 15 cuentas y frase antes de cada oración."
}
```

### CD-45 · Chaplet of the Most Holy Trinity

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap04.htm
- **Instrumento:** Rosario normal de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Crucifijo: «Incline unto mine aid, O God...» → 3 pequeñas + 1 grande: «Glory be to Thee, O most sweet, most noble... Trinity» → Cuentas pequeñas de cada decena: «Glory, honor and praise be to the great Eternal God. Amen» → Tras cada decena: Gloria → Cierre: 3 Glorias + Alabanzas Divinas
- **Día / frecuencia:** Uso libre; reparación por los pecados de los pecadores
- **Variables del usuario:** —
- **Meditación / intenciones:** Ofrecimiento por las cinco llagas
- **Características y advertencias:** Sustituye Avemarías y Padres Nuestros por alabanzas trinitarias.

```json
{
  "id": "CD-45",
  "nombre": "Chaplet of the Most Holy Trinity",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap04.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario normal de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Crucifijo: «Incline unto mine aid, O God...»",
    "3 pequeñas + 1 grande: «Glory be to Thee, O most sweet, most noble... Trinity»",
    "Cuentas pequeñas de cada decena: «Glory, honor and praise be to the great Eternal God. Amen»",
    "Tras cada decena: Gloria",
    "Cierre: 3 Glorias + Alabanzas Divinas"
  ],
  "dia_o_frecuencia": "Uso libre; reparación por los pecados de los pecadores",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Ofrecimiento por las cinco llagas",
  "notas": "Sustituye Avemarías y Padres Nuestros por alabanzas trinitarias."
}
```

### CD-46 · Chaplet of the Precious Blood

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap09.htm
- **Instrumento:** Coronilla de 7 grupos de 33 cuentas (231 cuentas teóricas)
- **Cuentas:** 7x33 · **Grupos:** 7x33
- **Secuencia:** Cada misterio: 5 Padres Nuestros (3 en el último) + «Incline unto my aid...» + Gloria + versículos «We beseech You, Lord, help Your servants...» → Cierre: ofrenda de la Preciosa Sangre
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 7 efusiones de sangre: circuncisión, huerto, flagelación, coronación de espinas, cruz, llaga del costado, etc.; 33 Padres Nuestros en total
- **Características y advertencias:** La más larga de la colección; requiere 7 bloques de 33 y control de 33 Padres Nuestros.

```json
{
  "id": "CD-46",
  "nombre": "Chaplet of the Precious Blood",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap09.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 7 grupos de 33 cuentas (231 cuentas teóricas)",
  "cuentas": "7x33",
  "grupos": "7x33",
  "pasos": [
    "Cada misterio: 5 Padres Nuestros (3 en el último) + «Incline unto my aid...» + Gloria + versículos «We beseech You, Lord, help Your servants...»",
    "Cierre: ofrenda de la Preciosa Sangre"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "7 efusiones de sangre: circuncisión, huerto, flagelación, coronación de espinas, cruz, llaga del costado, etc.; 33 Padres Nuestros en total",
  "notas": "La más larga de la colección; requiere 7 bloques de 33 y control de 33 Padres Nuestros."
}
```

### CD-47 · Chaplet of the Queen of Light

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap19.htm
- **Instrumento:** Coronilla: medalla del Sagrado Corazón + 18 cuentas en 3 tandas de 6
- **Cuentas:** 18 · **Grupos:** 3x6
- **Secuencia:** Cada tanda: cuenta 1 = meditación + Padre Nuestro; las otras 5 = Avemarías
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 3 meditaciones breves (oración significativa, unirse a Cristo en el Sacramento, Madre de los sin esperanza)
- **Características y advertencias:** Un solo Padre Nuestro por tanda; las meditaciones son textos cortos.

```json
{
  "id": "CD-47",
  "nombre": "Chaplet of the Queen of Light",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap19.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla del Sagrado Corazón + 18 cuentas en 3 tandas de 6",
  "cuentas": "18",
  "grupos": "3x6",
  "pasos": [
    "Cada tanda: cuenta 1 = meditación + Padre Nuestro; las otras 5 = Avemarías"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "3 meditaciones breves (oración significativa, unirse a Cristo en el Sacramento, Madre de los sin esperanza)",
  "notas": "Un solo Padre Nuestro por tanda; las meditaciones son textos cortos."
}
```

### CD-48 · Chaplet of the Sacred Face

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap51.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Oración inicial «Most Sacred Face, we call on You...» → Cuentas grandes: ofrenda de los méritos de la Santa Faz → Cuentas pequeñas: invocación diferente en cada decena (heridas, sangre, lágrimas, desprecio, dolor silencioso) → Cierre: oración de adoración
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 aspectos de la Santa Faz, uno por decena
- **Características y advertencias:** A diferencia del Holy Face (#07), aquí las 5 decenas tienen texto propio.

```json
{
  "id": "CD-48",
  "nombre": "Chaplet of the Sacred Face",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap51.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Oración inicial «Most Sacred Face, we call on You...»",
    "Cuentas grandes: ofrenda de los méritos de la Santa Faz",
    "Cuentas pequeñas: invocación diferente en cada decena (heridas, sangre, lágrimas, desprecio, dolor silencioso)",
    "Cierre: oración de adoración"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 aspectos de la Santa Faz, uno por decena",
  "notas": "A diferencia del Holy Face (#07), aquí las 5 decenas tienen texto propio."
}
```

### CD-49 · Chaplet of the Sacred Heart # 1

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap24.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Ángel de la Guarda → Cuenta 1: ofrenda al Padre Eterno → 3 cuentas: acto de fe, esperanza y caridad → Cuentas grandes: «Jesus, meek and humble of heart...» + «O Mary, conceived without sin...» → Cuentas pequeñas: «Sweetest Heart of Jesus... Sweet Heart of Mary...» → Cierre: «Jesus, Mary, and Joseph, I give you my heart and soul...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Tres variantes del Sagrado Corazón en la fuente; la #1 es la más completa.

```json
{
  "id": "CD-49",
  "nombre": "Chaplet of the Sacred Heart # 1",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap24.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Ángel de la Guarda",
    "Cuenta 1: ofrenda al Padre Eterno",
    "3 cuentas: acto de fe, esperanza y caridad",
    "Cuentas grandes: «Jesus, meek and humble of heart...» + «O Mary, conceived without sin...»",
    "Cuentas pequeñas: «Sweetest Heart of Jesus... Sweet Heart of Mary...»",
    "Cierre: «Jesus, Mary, and Joseph, I give you my heart and soul...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Tres variantes del Sagrado Corazón en la fuente; la #1 es la más completa."
}
```

### CD-50 · Chaplet of the Sacred Heart # 2

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap55.htm
- **Instrumento:** Coronilla propia: 33 cuentas pequeñas + crucifijo + medalla del Sagrado Corazón
- **Cuentas:** 33 · **Grupos:** 3x10 + 3
- **Secuencia:** Crucifijo: Anima Christi → Cuentas grandes: «O Sweetest Heart of Jesus, I implore...» → Cuentas pequeñas: «Sweet Heart of Jesus, be my love» → Tras cada decena: «Sweet Heart of Mary, be my salvation» → Cierre: «May the Heart of Jesus in the Most Blessed Sacrament...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** 33 cuentas, medalla reversible (Sagrado Corazón / Inmaculado Corazón).

```json
{
  "id": "CD-50",
  "nombre": "Chaplet of the Sacred Heart # 2",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap55.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla propia: 33 cuentas pequeñas + crucifijo + medalla del Sagrado Corazón",
  "cuentas": "33",
  "grupos": "3x10 + 3",
  "pasos": [
    "Crucifijo: Anima Christi",
    "Cuentas grandes: «O Sweetest Heart of Jesus, I implore...»",
    "Cuentas pequeñas: «Sweet Heart of Jesus, be my love»",
    "Tras cada decena: «Sweet Heart of Mary, be my salvation»",
    "Cierre: «May the Heart of Jesus in the Most Blessed Sacrament...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "33 cuentas, medalla reversible (Sagrado Corazón / Inmaculado Corazón)."
}
```

### CD-51 · Chaplet of the Sacred Heart # 3

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap56.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: «O Jesus give us Thy Heart as a pledge of Thy love...» → Primeras 5 cuentas: 1 Padre Nuestro, 3 Avemarías, 1 Credo → Cuentas grandes restantes: oración de reparación al Sagrado Corazón → Cuentas pequeñas: «O Sacred Heart of Jesus, burning with love of us, inflame our hearts...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Reparación por el Santísimo Sacramento
- **Características y advertencias:** Arranque 1-3-1 distinto del rosario estándar.

```json
{
  "id": "CD-51",
  "nombre": "Chaplet of the Sacred Heart # 3",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap56.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: «O Jesus give us Thy Heart as a pledge of Thy love...»",
    "Primeras 5 cuentas: 1 Padre Nuestro, 3 Avemarías, 1 Credo",
    "Cuentas grandes restantes: oración de reparación al Sagrado Corazón",
    "Cuentas pequeñas: «O Sacred Heart of Jesus, burning with love of us, inflame our hearts...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Reparación por el Santísimo Sacramento",
  "notas": "Arranque 1-3-1 distinto del rosario estándar."
}
```

### CD-52 · Chaplet of the Seven Dolors of Our Lady

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap18.htm
- **Instrumento:** Coronilla de 58 cuentas + medalla del primer dolor + cruz
- **Cuentas:** 58 · **Grupos:** 7x7 + 3 + 6
- **Secuencia:** 7 tandas de 7: Avemarías en las cuentas; Padre Nuestro en las separadoras → 3 cuentas extra: Avemarías por las lágrimas de María → Tras cada tanda: «Pray for us, O most sorrowful Virgin...» → Cierre: oración «Lord Jesus, we now implore...»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 7 dolores: profecía de Simeón, huida a Egipto, pérdida del Niño, encuentro con la cruz, crucifixión, descendimiento, sepultura
- **Características y advertencias:** La fuente añade indulgencias históricas (confirmadas 1747) — verificar.

```json
{
  "id": "CD-52",
  "nombre": "Chaplet of the Seven Dolors of Our Lady",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap18.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla de 58 cuentas + medalla del primer dolor + cruz",
  "cuentas": "58",
  "grupos": "7x7 + 3 + 6",
  "pasos": [
    "7 tandas de 7: Avemarías en las cuentas; Padre Nuestro en las separadoras",
    "3 cuentas extra: Avemarías por las lágrimas de María",
    "Tras cada tanda: «Pray for us, O most sorrowful Virgin...»",
    "Cierre: oración «Lord Jesus, we now implore...»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "7 dolores: profecía de Simeón, huida a Egipto, pérdida del Niño, encuentro con la cruz, crucifixión, descendimiento, sepultura",
  "notas": "La fuente añade indulgencias históricas (confirmadas 1747) — verificar."
}
```

### CD-53 · Chaplet of the Two Hearts

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap20.htm
- **Instrumento:** Coronilla: medalla de los Dos Corazones + 20 cuentas en 5 tandas de 4
- **Cuentas:** 20 · **Grupos:** 5x4
- **Secuencia:** Cada tanda: 1 Padre Nuestro + 3 Avemarías + una meditación → Cierre en la medalla: «O United Hearts of Jesus and Mary...» con intención concreta
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** necesidad particular a presentar
- **Meditación / intenciones:** 5 temas: Sagrado Corazón, Inmaculado Corazón, Pasión, Dolores de María, reparación
- **Características y advertencias:** Formato 5x4 con oración final personalizable.

```json
{
  "id": "CD-53",
  "nombre": "Chaplet of the Two Hearts",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap20.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: medalla de los Dos Corazones + 20 cuentas en 5 tandas de 4",
  "cuentas": "20",
  "grupos": "5x4",
  "pasos": [
    "Cada tanda: 1 Padre Nuestro + 3 Avemarías + una meditación",
    "Cierre en la medalla: «O United Hearts of Jesus and Mary...» con intención concreta"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "necesidad particular a presentar",
  "meditacion_o_intenciones": "5 temas: Sagrado Corazón, Inmaculado Corazón, Pasión, Dolores de María, reparación",
  "notas": "Formato 5x4 con oración final personalizable."
}
```

### CD-54 · Chaplet of the Way of the Cross

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap06.htm
- **Instrumento:** Coronilla: 15 tandas de 3 cuentas entre medallas de las estaciones + 6 cuentas + crucifijo
- **Cuentas:** 51 · **Grupos:** 15x3 + 6
- **Secuencia:** Cruz: señal de la Cruz → Antes de cada tanda: «We adore You, O Christ...» → Cada tanda: 1 Padre Nuestro, 1 Avemaría, 1 Gloria → Cierre de cada estación: meditación
- **Día / frecuencia:** Uso libre; Vía Crucis
- **Variables del usuario:** —
- **Meditación / intenciones:** 14 estaciones (la fuente enumera 14 aunque dice 15 grupos)
- **Características y advertencias:** Ojo con la incoherencia de la fuente: indica 15 grupos pero lista 14 estaciones. La app debe decidir 14 o preguntar.

```json
{
  "id": "CD-54",
  "nombre": "Chaplet of the Way of the Cross",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap06.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Coronilla: 15 tandas de 3 cuentas entre medallas de las estaciones + 6 cuentas + crucifijo",
  "cuentas": "51",
  "grupos": "15x3 + 6",
  "pasos": [
    "Cruz: señal de la Cruz",
    "Antes de cada tanda: «We adore You, O Christ...»",
    "Cada tanda: 1 Padre Nuestro, 1 Avemaría, 1 Gloria",
    "Cierre de cada estación: meditación"
  ],
  "dia_o_frecuencia": "Uso libre; Vía Crucis",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "14 estaciones (la fuente enumera 14 aunque dice 15 grupos)",
  "notas": "Ojo con la incoherencia de la fuente: indica 15 grupos pero lista 14 estaciones. La app debe decidir 14 o preguntar."
}
```

### CD-55 · Chaplet of Unity

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap43.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Medalla y 4 cuentas grandes: oración larga «God our Heavenly Father, through Your Son Jesus...» → Cuentas pequeñas: «In Your great mercy, forgive our sinfulness, heal our brokenness...» → Cierre: 4 invocaciones (Shema, Jesús rey, María, San Miguel)
- **Día / frecuencia:** Uso libre; unidad
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Las 4 invocaciones finales funcionan como bloque de cierre.

```json
{
  "id": "CD-55",
  "nombre": "Chaplet of Unity",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap43.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Medalla y 4 cuentas grandes: oración larga «God our Heavenly Father, through Your Son Jesus...»",
    "Cuentas pequeñas: «In Your great mercy, forgive our sinfulness, heal our brokenness...»",
    "Cierre: 4 invocaciones (Shema, Jesús rey, María, San Miguel)"
  ],
  "dia_o_frecuencia": "Uso libre; unidad",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Las 4 invocaciones finales funcionan como bloque de cierre."
}
```

### CD-56 · Chaplet to Obtain a Great Confidence

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap49.htm
- **Instrumento:** Rosario de 59 cuentas, pero se usan solo 55
- **Cuentas:** 55 de 59 · **Grupos:** 5x(1+10)
- **Secuencia:** 5 cuentas grandes: oración larga «See where Thy boundless Love has reached...» → 50 cuentas pequeñas: «Sacred Heart of Jesus, I trust in Thee!» → Se ignoran las 4 cuentas restantes
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** La fuente dice explícitamente que se omiten 4 cuentas: la app debe ocultarlas.

```json
{
  "id": "CD-56",
  "nombre": "Chaplet to Obtain a Great Confidence",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap49.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas, pero se usan solo 55",
  "cuentas": "55 de 59",
  "grupos": "5x(1+10)",
  "pasos": [
    "5 cuentas grandes: oración larga «See where Thy boundless Love has reached...»",
    "50 cuentas pequeñas: «Sacred Heart of Jesus, I trust in Thee!»",
    "Se ignoran las 4 cuentas restantes"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "La fuente dice explícitamente que se omiten 4 cuentas: la app debe ocultarlas."
}
```

### CD-57 · Chaplet to the Mother of the Most Holy Eucharist

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/chaplets/chap35.htm
- **Instrumento:** Rosario de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz → Primera cuenta grande: Credo → 3 pequeñas: Avemarías → Siguiente cuenta grande: acto de amor «My God, I love you...» → Antes de cada decena: «Hail Mary, O Mother of the Most Holy Eucharist...» → 10 pequeñas: «O Mother help me!» + Memorare → Cierre: profesión de fe eucarística larga + «O Mother of the Most Holy Eucharist, pray for us» ×3
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Fe eucarística
- **Características y advertencias:** Incluye una profesión de fe larguísima al final.

```json
{
  "id": "CD-57",
  "nombre": "Chaplet to the Mother of the Most Holy Eucharist",
  "fuente": "https://www.catholicdoors.com/prayers/chaplets/chap35.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz",
    "Primera cuenta grande: Credo",
    "3 pequeñas: Avemarías",
    "Siguiente cuenta grande: acto de amor «My God, I love you...»",
    "Antes de cada decena: «Hail Mary, O Mother of the Most Holy Eucharist...»",
    "10 pequeñas: «O Mother help me!» + Memorare",
    "Cierre: profesión de fe eucarística larga + «O Mother of the Most Holy Eucharist, pray for us» ×3"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Fe eucarística",
  "notas": "Incluye una profesión de fe larguísima al final."
}
```

### CD-58 · Franciscan Family Chaplet

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/prayers/english6/p03528.htm
- **Instrumento:** Rosario / cruz de San Damián
- **Cuentas:** No se especifica; 3 decenas de 10 Avemarías + 3 Avemarías iniciales · **Grupos:** 3x10 + 3
- **Secuencia:** Señal de la Cruz + Credo en la cruz de San Damián + 3 Avemarías → Cada decena: intención por una Orden franciscana (1ª, 2ª, 3ª regular y secular) + Padre Nuestro + «My God and my all!» ×10 → Cierre: «We adore You, O Lord Jesus Christ...» + 3 Glorias por el Papa + señal de la Cruz
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Tres ramas franciscanas
- **Características y advertencias:** Autor Nicholas H. Kovacs, OFS; usa la cruz de San Damián como punto de inicio.

```json
{
  "id": "CD-58",
  "nombre": "Franciscan Family Chaplet",
  "fuente": "https://www.catholicdoors.com/prayers/english6/p03528.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario / cruz de San Damián",
  "cuentas": "No se especifica; 3 decenas de 10 Avemarías + 3 Avemarías iniciales",
  "grupos": "3x10 + 3",
  "pasos": [
    "Señal de la Cruz + Credo en la cruz de San Damián + 3 Avemarías",
    "Cada decena: intención por una Orden franciscana (1ª, 2ª, 3ª regular y secular) + Padre Nuestro + «My God and my all!» ×10",
    "Cierre: «We adore You, O Lord Jesus Christ...» + 3 Glorias por el Papa + señal de la Cruz"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Tres ramas franciscanas",
  "notas": "Autor Nicholas H. Kovacs, OFS; usa la cruz de San Damián como punto de inicio."
}
```

### CD-59 · Josephian Rosary

- **Fuente:** Catholic Doors — https://www.catholicdoors.com/misc/josephian.htm
- **Instrumento:** Rosario mariano de 59 cuentas (no hace falta uno especial)
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Credo → Cuentas iniciales: Padre Nuestro, 3 Avemarías, Gloria → Medalla y cada cuenta grande: Padre Nuestro + 10 «Hail Joseph...» + Gloria → Repetir 5 decenas → Cierre con oraciones propias
- **Día / frecuencia:** Uso privado (la fuente lo indica explícitamente)
- **Variables del usuario:** —
- **Meditación / intenciones:** 10 misterios josefinos: vocación divina, protector de los inocentes, hallazgo de alojamiento, guardián del Niño, obediencia a la Ley/circuncisión, sumisión a la guía divina, gran angustia, realeza sin corona, restauración del reino, proclamación de San José como patrono de la Iglesia
- **Características y advertencias:** Sustituye el Avemaría por «Hail Joseph»; el más largo de la colección y con misterios elaborados por la propia web. La fuente ofrece dos modos: meditar los misterios 1-5 en las cinco decenas del rosario, o bien los misterios 6-10.

```json
{
  "id": "CD-59",
  "nombre": "Josephian Rosary",
  "fuente": "https://www.catholicdoors.com/misc/josephian.htm",
  "sitio": "Catholic Doors",
  "instrumento": "Rosario mariano de 59 cuentas (no hace falta uno especial)",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Credo",
    "Cuentas iniciales: Padre Nuestro, 3 Avemarías, Gloria",
    "Medalla y cada cuenta grande: Padre Nuestro + 10 «Hail Joseph...» + Gloria",
    "Repetir 5 decenas",
    "Cierre con oraciones propias"
  ],
  "dia_o_frecuencia": "Uso privado (la fuente lo indica explícitamente)",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "10 misterios josefinos: vocación divina, protector de los inocentes, hallazgo de alojamiento, guardián del Niño, obediencia a la Ley/circuncisión, sumisión a la guía divina, gran angustia, realeza sin corona, restauración del reino, proclamación de San José como patrono de la Iglesia",
  "notas": "Sustituye el Avemaría por «Hail Joseph»; el más largo de la colección y con misterios elaborados por la propia web. La fuente ofrece dos modos: meditar los misterios 1-5 en las cinco decenas del rosario, o bien los misterios 6-10."
}
```

### IVE-01 · Misterios de Gloria (Rosario glorioso)

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario mariano completo de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Señal de la Cruz → Acto de Contrición → Ofrecimiento del Rosario (reparación a los Corazones de Jesús, María y José; intenciones por la conversión, paz, familias, almas, Papa, etc.) → 5 decenas con Padre Nuestro + 10 Avemarías + Gloria → Jaculatorias opcionales entre misterios → 3 últimas cuentas: tres V/R «Virgen Purísima y Castísima...» + Avemaría → Salve final (de pie o de rodillas)
- **Día / frecuencia:** Miércoles y domingo
- **Variables del usuario:** intenciones generales ya fijadas en el ofrecimiento
- **Meditación / intenciones:** 1. Resurrección; 2. Ascensión; 3. Pentecostés; 4. Asunción; 5. Coronación de María
- **Características y advertencias:** Versión IVE del rosario: añade acto de contrición, ofrecimiento largo, jaculatorias y Salve final.

```json
{
  "id": "IVE-01",
  "nombre": "Misterios de Gloria (Rosario glorioso)",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario mariano completo de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Señal de la Cruz",
    "Acto de Contrición",
    "Ofrecimiento del Rosario (reparación a los Corazones de Jesús, María y José; intenciones por la conversión, paz, familias, almas, Papa, etc.)",
    "5 decenas con Padre Nuestro + 10 Avemarías + Gloria",
    "Jaculatorias opcionales entre misterios",
    "3 últimas cuentas: tres V/R «Virgen Purísima y Castísima...» + Avemaría",
    "Salve final (de pie o de rodillas)"
  ],
  "dia_o_frecuencia": "Miércoles y domingo",
  "variables_usuario": "intenciones generales ya fijadas en el ofrecimiento",
  "meditacion_o_intenciones": "1. Resurrección; 2. Ascensión; 3. Pentecostés; 4. Asunción; 5. Coronación de María",
  "notas": "Versión IVE del rosario: añade acto de contrición, ofrecimiento largo, jaculatorias y Salve final."
}
```

### IVE-02 · Misterios de Gozo (Rosario gozoso)

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario mariano completo de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Igual esquema que el glorioso: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve
- **Día / frecuencia:** Lunes y sábado; también domingos de Adviento y domingos desde la Epifanía hasta Cuaresma
- **Variables del usuario:** —
- **Meditación / intenciones:** 1. Encarnación del Hijo de Dios; 2. Visita de la Virgen a Santa Isabel; 3. Nacimiento en el portal de Belén; 4. Presentación de Jesús en el Templo; 5. Niño Jesús perdido y hallado en el Templo
- **Características y advertencias:** La regla de días es más rica que la estándar: incluye dos temporadas litúrgicas.

```json
{
  "id": "IVE-02",
  "nombre": "Misterios de Gozo (Rosario gozoso)",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario mariano completo de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Igual esquema que el glorioso: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve"
  ],
  "dia_o_frecuencia": "Lunes y sábado; también domingos de Adviento y domingos desde la Epifanía hasta Cuaresma",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "1. Encarnación del Hijo de Dios; 2. Visita de la Virgen a Santa Isabel; 3. Nacimiento en el portal de Belén; 4. Presentación de Jesús en el Templo; 5. Niño Jesús perdido y hallado en el Templo",
  "notas": "La regla de días es más rica que la estándar: incluye dos temporadas litúrgicas."
}
```

### IVE-03 · Misterios de Dolores (Rosario doloroso)

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario mariano completo de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Igual esquema: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve
- **Día / frecuencia:** Martes y viernes; además, diariamente desde el Miércoles de Ceniza hasta el Sábado de Gloria
- **Variables del usuario:** —
- **Meditación / intenciones:** 1. Oración de Jesús en el Huerto; 2. Azotes atado a la columna; 3. Corona de espinas; 4. Jesús con la Cruz a cuestas; 5. Crucifixión y muerte del Señor
- **Características y advertencias:** Regla de Cuaresma diaria: la app debe cambiar el modo según la temporada litúrgica.

```json
{
  "id": "IVE-03",
  "nombre": "Misterios de Dolores (Rosario doloroso)",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario mariano completo de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Igual esquema: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve"
  ],
  "dia_o_frecuencia": "Martes y viernes; además, diariamente desde el Miércoles de Ceniza hasta el Sábado de Gloria",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "1. Oración de Jesús en el Huerto; 2. Azotes atado a la columna; 3. Corona de espinas; 4. Jesús con la Cruz a cuestas; 5. Crucifixión y muerte del Señor",
  "notas": "Regla de Cuaresma diaria: la app debe cambiar el modo según la temporada litúrgica."
}
```

### IVE-04 · Misterios de Luz (Rosario luminoso)

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario mariano completo de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Igual esquema: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve
- **Día / frecuencia:** Todos los jueves
- **Variables del usuario:** —
- **Meditación / intenciones:** 1. Bautismo en el Jordán; 2. Autorrevelación en las bodas de Caná; 3. Anuncio del Reino invitando a la conversión; 4. Transfiguración; 5. Institución de la Eucaristía, expresión sacramental del Misterio Pascual
- **Características y advertencias:** Cuarto bloque semanal; comparte esqueleto con los otros tres.

```json
{
  "id": "IVE-04",
  "nombre": "Misterios de Luz (Rosario luminoso)",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario mariano completo de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Igual esquema: Señal de la Cruz, Acto de Contrición, Ofrecimiento, 5 decenas, jaculatorias, 3 cuentas finales y Salve"
  ],
  "dia_o_frecuencia": "Todos los jueves",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "1. Bautismo en el Jordán; 2. Autorrevelación en las bodas de Caná; 3. Anuncio del Reino invitando a la conversión; 4. Transfiguración; 5. Institución de la Eucaristía, expresión sacramental del Misterio Pascual",
  "notas": "Cuarto bloque semanal; comparte esqueleto con los otros tres."
}
```

### IVE-05 · Corona a la Divina Misericordia

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario común de 5 decenas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Dos oraciones de apertura del diario de Santa Faustina («Expiraste, Jesús...», «Oh Sangre y Agua...») → Padre Nuestro, Avemaría y Credo → En las cuentas grandes: «Padre Eterno, te ofrezco el Cuerpo y la Sangre...» → En las 10 pequeñas: «Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero» → Cierre: «Santo Dios, Santo Fuerte, Santo Inmortal...» ×3 + oración final opcional
- **Día / frecuencia:** Preferentemente a las 15:00; también junto a un agonizante o recién fallecido
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 decenas sin misterios
- **Características y advertencias:** La página insiste en rezarla junto al moribundo y luego el rosario; incluye texto de promesas.

```json
{
  "id": "IVE-05",
  "nombre": "Corona a la Divina Misericordia",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario común de 5 decenas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Dos oraciones de apertura del diario de Santa Faustina («Expiraste, Jesús...», «Oh Sangre y Agua...»)",
    "Padre Nuestro, Avemaría y Credo",
    "En las cuentas grandes: «Padre Eterno, te ofrezco el Cuerpo y la Sangre...»",
    "En las 10 pequeñas: «Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero»",
    "Cierre: «Santo Dios, Santo Fuerte, Santo Inmortal...» ×3 + oración final opcional"
  ],
  "dia_o_frecuencia": "Preferentemente a las 15:00; también junto a un agonizante o recién fallecido",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 decenas sin misterios",
  "notas": "La página insiste en rezarla junto al moribundo y luego el rosario; incluye texto de promesas."
}
```

### IVE-06 · Coronilla a las almas del Purgatorio

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Cuentas de un rosario tradicional
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Introducción: «Oh Sangre y Agua...» ×3 → Padre Nuestro, Avemaría y Credo → Jaculatoria antes de las cuentas pequeñas: «Padre Eterno, te ofrezco el Preciosísimo Cuerpo, Sangre, Alma y Divinidad... en unión con las Misas de hoy» → En cada una de las 10 cuentas de cada decena: «Jesús, María os amo, salvad las almas» → Jaculatoria entre decenas → En las 3 últimas cuentas antes de la Cruz: «Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero» → Oración final por los difuntos (dictada a María Valtorta)
- **Día / frecuencia:** Cada día (la fuente habla de unirnos «cada día»)
- **Variables del usuario:** —
- **Meditación / intenciones:** Conjugación de revelaciones a Santa Gertrudis, Santa Faustina y Sor María Consolata Betrone
- **Características y advertencias:** Texto en español; difiere del chaplet inglés de Holy Souls de Catholic Doors.

```json
{
  "id": "IVE-06",
  "nombre": "Coronilla a las almas del Purgatorio",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Cuentas de un rosario tradicional",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Introducción: «Oh Sangre y Agua...» ×3",
    "Padre Nuestro, Avemaría y Credo",
    "Jaculatoria antes de las cuentas pequeñas: «Padre Eterno, te ofrezco el Preciosísimo Cuerpo, Sangre, Alma y Divinidad... en unión con las Misas de hoy»",
    "En cada una de las 10 cuentas de cada decena: «Jesús, María os amo, salvad las almas»",
    "Jaculatoria entre decenas",
    "En las 3 últimas cuentas antes de la Cruz: «Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero»",
    "Oración final por los difuntos (dictada a María Valtorta)"
  ],
  "dia_o_frecuencia": "Cada día (la fuente habla de unirnos «cada día»)",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Conjugación de revelaciones a Santa Gertrudis, Santa Faustina y Sor María Consolata Betrone",
  "notas": "Texto en español; difiere del chaplet inglés de Holy Souls de Catholic Doors."
}
```

### IVE-07 · Coronilla al Sagrado Corazón de Jesús

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario común (cuentas grandes y pequeñas)
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cuentas grandes: «Oh Sacratísimo, Divino y Adorado Corazón de Jesús, a vos me doy y consagro todo y sin reserva» → Cuentas pequeñas ×10: V/R «Divino Corazón de Jesús, fuente inagotable de amor y bondad / Sed nuestro refugio y amparo...» → Cierre: «Divino Corazón de Jesús, tened piedad de nosotros» ×3 → Repetir a cada instante: «Ven, Señor Jesús» ×3
- **Día / frecuencia:** A cada instante y todos los días
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Una de las más cortas: solo dos textos alternos + cierre.

```json
{
  "id": "IVE-07",
  "nombre": "Coronilla al Sagrado Corazón de Jesús",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario común (cuentas grandes y pequeñas)",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cuentas grandes: «Oh Sacratísimo, Divino y Adorado Corazón de Jesús, a vos me doy y consagro todo y sin reserva»",
    "Cuentas pequeñas ×10: V/R «Divino Corazón de Jesús, fuente inagotable de amor y bondad / Sed nuestro refugio y amparo...»",
    "Cierre: «Divino Corazón de Jesús, tened piedad de nosotros» ×3",
    "Repetir a cada instante: «Ven, Señor Jesús» ×3"
  ],
  "dia_o_frecuencia": "A cada instante y todos los días",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Una de las más cortas: solo dos textos alternos + cierre."
}
```

### IVE-08 · Coronilla al Inmaculado Corazón de María

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario común de 5 decenas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cuentas grandes: «Corazón ardiente, Corazón herido en la cruz, Corazón que sangra en la Hostia...» → Cuentas pequeñas ×10: V/R «Corazón Inmaculado de María, holocausto perfecto del Divino Amor / Haz que me hieran las heridas de tu Hijo...» → Cierre ×3: «Madre del Corazón doloroso, termina en mi cuerpo lo que falta a la pasión de tu Hijo»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** —
- **Características y advertencias:** Gemela en estructura a la del Sagrado Corazón; la app puede compartir plantilla.

```json
{
  "id": "IVE-08",
  "nombre": "Coronilla al Inmaculado Corazón de María",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario común de 5 decenas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cuentas grandes: «Corazón ardiente, Corazón herido en la cruz, Corazón que sangra en la Hostia...»",
    "Cuentas pequeñas ×10: V/R «Corazón Inmaculado de María, holocausto perfecto del Divino Amor / Haz que me hieran las heridas de tu Hijo...»",
    "Cierre ×3: «Madre del Corazón doloroso, termina en mi cuerpo lo que falta a la pasión de tu Hijo»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "—",
  "notas": "Gemela en estructura a la del Sagrado Corazón; la app puede compartir plantilla."
}
```

### IVE-09 · Corona a la Sangre Preciosa de Jesús

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Corona de 7 misterios con 33 Padres Nuestros en total
- **Cuentas:** 33 Padres Nuestros + 6 Glorias + jaculatorias · **Grupos:** 7x(5 Padre Nuestro + Gloria), último 3 Padre Nuestro + Gloria
- **Secuencia:** Cada misterio: 5 Padres Nuestros + 1 Gloria; en el séptimo solo 3 Padres Nuestros + 1 Gloria (completan 33) + jaculatoria «Te rogamos, pues, que vengas en ayuda de tus siervos...» «Te rogamos, Señor, ayuda a tus siervos / a quienes redimiste con tu Preciosa Sangre» → Cierre: ofrenda al Padre Eterno + aspiraciones con indulgencia
- **Día / frecuencia:** Diaria (indulgencia diaria); indulgencia plenaria una vez al mes con confesión y comunión
- **Variables del usuario:** —
- **Meditación / intenciones:** 7 efusiones de sangre: circuncisión, huerto de Olivos, flagelación, coronación de espinas, Vía Crucis, crucifixión, llaga del costado
- **Características y advertencias:** Indulgencias de Pío VII (1809 y 1815) según la fuente; verificar normativa actual. Texto paralelo al chaplet inglés de Precious Blood.

```json
{
  "id": "IVE-09",
  "nombre": "Corona a la Sangre Preciosa de Jesús",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Corona de 7 misterios con 33 Padres Nuestros en total",
  "cuentas": "33 Padres Nuestros + 6 Glorias + jaculatorias",
  "grupos": "7x(5 Padre Nuestro + Gloria), último 3 Padre Nuestro + Gloria",
  "pasos": [
    "Cada misterio: 5 Padres Nuestros + 1 Gloria; en el séptimo solo 3 Padres Nuestros + 1 Gloria (completan 33) + jaculatoria «Te rogamos, pues, que vengas en ayuda de tus siervos...» «Te rogamos, Señor, ayuda a tus siervos / a quienes redimiste con tu Preciosa Sangre»",
    "Cierre: ofrenda al Padre Eterno + aspiraciones con indulgencia"
  ],
  "dia_o_frecuencia": "Diaria (indulgencia diaria); indulgencia plenaria una vez al mes con confesión y comunión",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "7 efusiones de sangre: circuncisión, huerto de Olivos, flagelación, coronación de espinas, Vía Crucis, crucifixión, llaga del costado",
  "notas": "Indulgencias de Pío VII (1809 y 1815) según la fuente; verificar normativa actual. Texto paralelo al chaplet inglés de Precious Blood."
}
```

### IVE-10 · El Rosario del Espíritu Santo

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario de 5 misterios
- **Cuentas:** 5 decenas · **Grupos:** 5x10
- **Secuencia:** En cada misterio: 1. enunciarlo, 2. leer la cita bíblica, 3. Padre Nuestro + 10 Avemarías + Gloria → Cierre: «Ven, Espíritu Santo, a renovar la faz de la tierra»
- **Día / frecuencia:** Se sugiere el domingo (Pascua del Señor y envío del Espíritu), o cuando se sienta el llamado
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 misterios con cita bíblica: Encarnación (Lc 1,26-28), Bautismo (Lc 3,21-22), Muerte en cruz (Jn 19,30), Aparición a los discípulos (Jn 20,22), Pentecostés (Hch 2,1-13)
- **Características y advertencias:** Requiere mostrar texto bíblico en pantalla antes de cada decena; six dones del Espíritu en la introducción.

```json
{
  "id": "IVE-10",
  "nombre": "El Rosario del Espíritu Santo",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario de 5 misterios",
  "cuentas": "5 decenas",
  "grupos": "5x10",
  "pasos": [
    "En cada misterio: 1. enunciarlo, 2. leer la cita bíblica, 3. Padre Nuestro + 10 Avemarías + Gloria",
    "Cierre: «Ven, Espíritu Santo, a renovar la faz de la tierra»"
  ],
  "dia_o_frecuencia": "Se sugiere el domingo (Pascua del Señor y envío del Espíritu), o cuando se sienta el llamado",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 misterios con cita bíblica: Encarnación (Lc 1,26-28), Bautismo (Lc 3,21-22), Muerte en cruz (Jn 19,30), Aparición a los discípulos (Jn 20,22), Pentecostés (Hch 2,1-13)",
  "notas": "Requiere mostrar texto bíblico en pantalla antes de cada decena; six dones del Espíritu en la introducción."
}
```

### IVE-11 · Corona de los Siete Dolores

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Corona de 7 misterios; 7 Ave Marías por dolor
- **Cuentas:** 7x7 = 49 Ave Marías + 7 Padres Nuestros + 7 Glorias · **Grupos:** 7x(1+7+1)
- **Secuencia:** Rezar despacio: en cada dolor, meditación larga dirigida a María → Padrenuestro + 7 Ave Marías + Gloria al Padre → Cierre con las promesas de María a quienes propaguen la devoción a sus lágrimas y dolores
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 7 dolores: profecía de Simeón, huida a Egipto, pérdida de Jesús, encuentro con la cruz, crucifixión, descendimiento, sepultura
- **Características y advertencias:** Cita de Newman: «El camino para penetrar en los sufrimientos del Hijo es penetrar en los sufrimientos de la Madre». No confundir con el Chaplet of the Seven Dolors inglés (58 cuentas).

```json
{
  "id": "IVE-11",
  "nombre": "Corona de los Siete Dolores",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Corona de 7 misterios; 7 Ave Marías por dolor",
  "cuentas": "7x7 = 49 Ave Marías + 7 Padres Nuestros + 7 Glorias",
  "grupos": "7x(1+7+1)",
  "pasos": [
    "Rezar despacio: en cada dolor, meditación larga dirigida a María",
    "Padrenuestro + 7 Ave Marías + Gloria al Padre",
    "Cierre con las promesas de María a quienes propaguen la devoción a sus lágrimas y dolores"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "7 dolores: profecía de Simeón, huida a Egipto, pérdida de Jesús, encuentro con la cruz, crucifixión, descendimiento, sepultura",
  "notas": "Cita de Newman: «El camino para penetrar en los sufrimientos del Hijo es penetrar en los sufrimientos de la Madre». No confundir con el Chaplet of the Seven Dolors inglés (58 cuentas)."
}
```

### IVE-12 · Coronilla de Alabanzas a María

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Coronilla de 3 coronas (Excelencia, Poder, Bondad)
- **Cuentas:** 3x(1 Padre Nuestro + 5 Avemarías + Gloria) aprox. · **Grupos:** 3x5
- **Secuencia:** Invocación inicial V/R «Dígnate aceptar mis alabanzas...» → Credo → Corona 1 de Excelencia: Padre Nuestro + Avemaría + 4 alabanzas con V/R «Regocíjate, Virgen María / Regocíjate mil veces» + Avemaría entre cada una + Gloria → Corona 2 de Poder: mismo esquema → Corona 3 de Bondad: mismo esquema → Oremos final
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Tres series de alabanzas: excelencia, poder, bondad
- **Características y advertencias:** Texto casi íntegramente en español; cada alabanza lleva Avemaría y el responsorio fijo.

```json
{
  "id": "IVE-12",
  "nombre": "Coronilla de Alabanzas a María",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Coronilla de 3 coronas (Excelencia, Poder, Bondad)",
  "cuentas": "3x(1 Padre Nuestro + 5 Avemarías + Gloria) aprox.",
  "grupos": "3x5",
  "pasos": [
    "Invocación inicial V/R «Dígnate aceptar mis alabanzas...»",
    "Credo",
    "Corona 1 de Excelencia: Padre Nuestro + Avemaría + 4 alabanzas con V/R «Regocíjate, Virgen María / Regocíjate mil veces» + Avemaría entre cada una + Gloria",
    "Corona 2 de Poder: mismo esquema",
    "Corona 3 de Bondad: mismo esquema",
    "Oremos final"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Tres series de alabanzas: excelencia, poder, bondad",
  "notas": "Texto casi íntegramente en español; cada alabanza lleva Avemaría y el responsorio fijo."
}
```

### IVE-13 · Coronilla de San Charbel

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** 5 grupos de 3 cuentas + 4 cuentas separadoras + cuenta grande inicial
- **Cuentas:** 5x3 + 4 separadoras + 1 inicial = 20 · **Grupos:** 5x3
- **Secuencia:** Cuenta grande inicial: plegaria «Al Padre de la verdad» (poema de San Jacobo de Sarug, siglo V, de la Misa maronita) → Cuentas separadoras: Padre Nuestro → Cada grupo de 3: tres Avemarías en honor a pobreza, castidad y obediencia → Cierre: oración «Dios infinitamente santo...» con petición de gracia especial
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** favor concreto a pedir en la oración final
- **Meditación / intenciones:** Los tres votos religiosos; la fuente enumera intenciones por grupo (fe, castidad, conversión, etc.)
- **Características y advertencias:** Rito maronita; la plegaria inicial es larga y debe mostrarse íntegra.

```json
{
  "id": "IVE-13",
  "nombre": "Coronilla de San Charbel",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "5 grupos de 3 cuentas + 4 cuentas separadoras + cuenta grande inicial",
  "cuentas": "5x3 + 4 separadoras + 1 inicial = 20",
  "grupos": "5x3",
  "pasos": [
    "Cuenta grande inicial: plegaria «Al Padre de la verdad» (poema de San Jacobo de Sarug, siglo V, de la Misa maronita)",
    "Cuentas separadoras: Padre Nuestro",
    "Cada grupo de 3: tres Avemarías en honor a pobreza, castidad y obediencia",
    "Cierre: oración «Dios infinitamente santo...» con petición de gracia especial"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "favor concreto a pedir en la oración final",
  "meditacion_o_intenciones": "Los tres votos religiosos; la fuente enumera intenciones por grupo (fe, castidad, conversión, etc.)",
  "notas": "Rito maronita; la plegaria inicial es larga y debe mostrarse íntegra."
}
```

### IVE-14 · Coronilla de San Antonio de Padua

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Coronilla de 39 cuentas: 13 grupos de 3
- **Cuentas:** 39 · **Grupos:** 13x3
- **Secuencia:** Preparatoria: Señal de la Cruz, Acto de Contrición y «Abrid, Señor, mis labios...» → Cada grupo: invocación propia + Padrenuestro + Avemaría + Gloria → Tras 13 grupos: Responsorio «Si quieres ver milagros...» + Gloria + versículo → Oración final
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 13 invocaciones: apóstol por la fe, patriarca por la esperanza, serafín por la caridad, mansedumbre, castidad, penitencia, obediencia, martirio, etc.
- **Características y advertencias:** Equivale a la Chaplet of Saint Anthony de Catholic Doors, pero con texto español propio.

```json
{
  "id": "IVE-14",
  "nombre": "Coronilla de San Antonio de Padua",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Coronilla de 39 cuentas: 13 grupos de 3",
  "cuentas": "39",
  "grupos": "13x3",
  "pasos": [
    "Preparatoria: Señal de la Cruz, Acto de Contrición y «Abrid, Señor, mis labios...»",
    "Cada grupo: invocación propia + Padrenuestro + Avemaría + Gloria",
    "Tras 13 grupos: Responsorio «Si quieres ver milagros...» + Gloria + versículo",
    "Oración final"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "13 invocaciones: apóstol por la fe, patriarca por la esperanza, serafín por la caridad, mansedumbre, castidad, penitencia, obediencia, martirio, etc.",
  "notas": "Equivale a la Chaplet of Saint Anthony de Catholic Doors, pero con texto español propio."
}
```

### IVE-15 · Rosario de la Pasión de Jesús

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Rosario común
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Inicio: Padrenuestro, Avemaría y Gloria → Cuentas grandes: «¡Te adoramos y te alabamos, Divino Jesús, porque por Vuestra Pasión y Muerte redimiste al mundo!» → Cuentas pequeñas: «¡Por los merecimientos de Vuestra Pasión y Muerte, perdónanos, Jesús!» → Final de cada misterio: «¡Ten piedad de nosotros, Señor!» → Tres últimas cuentas: «Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero»
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** Pasión y Muerte de Cristo
- **Características y advertencias:** Texto en español, estructura muy parecida a Divine Mercy; arranca con una cita de Cristo crucificado.

```json
{
  "id": "IVE-15",
  "nombre": "Rosario de la Pasión de Jesús",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Rosario común",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Inicio: Padrenuestro, Avemaría y Gloria",
    "Cuentas grandes: «¡Te adoramos y te alabamos, Divino Jesús, porque por Vuestra Pasión y Muerte redimiste al mundo!»",
    "Cuentas pequeñas: «¡Por los merecimientos de Vuestra Pasión y Muerte, perdónanos, Jesús!»",
    "Final de cada misterio: «¡Ten piedad de nosotros, Señor!»",
    "Tres últimas cuentas: «Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero»"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Pasión y Muerte de Cristo",
  "notas": "Texto en español, estructura muy parecida a Divine Mercy; arranca con una cita de Cristo crucificado."
}
```

### IVE-16 · Coronilla simple de desagravio a María Santísima

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** 5 misterios independientes de 3 Avemarías cada uno
- **Cuentas:** 15 Avemarías · **Grupos:** 5x3
- **Secuencia:** Por cada misterio: 3 Avemarías + «Ave María Purísima, sin pecado concebida» → Oremos final: consagración al Inmaculado Corazón
- **Día / frecuencia:** Para todos los días; los misterios pueden rezarse juntos o separados a lo largo del día
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 ofensas: contra la Inmaculada Concepción, la Perpetua Virginidad, la Divina Maternidad, el desprecio en los niños, las blasfemias en medios de comunicación
- **Características y advertencias:** Diseñada para memorizar y rezar «caminando por la calle»: la app debe permitir iniciar un solo misterio.

```json
{
  "id": "IVE-16",
  "nombre": "Coronilla simple de desagravio a María Santísima",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "5 misterios independientes de 3 Avemarías cada uno",
  "cuentas": "15 Avemarías",
  "grupos": "5x3",
  "pasos": [
    "Por cada misterio: 3 Avemarías + «Ave María Purísima, sin pecado concebida»",
    "Oremos final: consagración al Inmaculado Corazón"
  ],
  "dia_o_frecuencia": "Para todos los días; los misterios pueden rezarse juntos o separados a lo largo del día",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 ofensas: contra la Inmaculada Concepción, la Perpetua Virginidad, la Divina Maternidad, el desprecio en los niños, las blasfemias en medios de comunicación",
  "notas": "Diseñada para memorizar y rezar «caminando por la calle»: la app debe permitir iniciar un solo misterio."
}
```

### IVE-17 · Corona de confianza en grandes tribulaciones

- **Fuente:** IVE Arabic — https://iveinarabic.org/es/rosarios-y-coronas/
- **Instrumento:** Corona de 10 misterios con 9 repeticiones cada uno
- **Cuentas:** 10x9 + cierre = 91 · **Grupos:** 10x9
- **Secuencia:** Credo una sola vez al comenzar → Cada misterio: Padrenuestro + 9 veces «Eres mi fortaleza y mi refugio, eres mi Dios en quien confío» + Gloria → Cierre: 1 Padrenuestro + la jaculatoria ×1 + 1 Gloria + «Amén, Amén, Amén»
- **Día / frecuencia:** En grandes tribulaciones o presagios de peligro
- **Variables del usuario:** —
- **Meditación / intenciones:** 10 versos (Salmo 91): red del cazador, peste mortal, cobijo bajo sus alas, terror nocturno, caída de miles, desgracia, ángeles guardadores, unión con Dios, león y dragón, respuesta y larga vida
- **Características y advertencias:** La fuente indica que se completan 91 repeticiones; verificar el cómputo en la app.

```json
{
  "id": "IVE-17",
  "nombre": "Corona de confianza en grandes tribulaciones",
  "fuente": "https://iveinarabic.org/es/rosarios-y-coronas/",
  "sitio": "IVE Arabic",
  "instrumento": "Corona de 10 misterios con 9 repeticiones cada uno",
  "cuentas": "10x9 + cierre = 91",
  "grupos": "10x9",
  "pasos": [
    "Credo una sola vez al comenzar",
    "Cada misterio: Padrenuestro + 9 veces «Eres mi fortaleza y mi refugio, eres mi Dios en quien confío» + Gloria",
    "Cierre: 1 Padrenuestro + la jaculatoria ×1 + 1 Gloria + «Amén, Amén, Amén»"
  ],
  "dia_o_frecuencia": "En grandes tribulaciones o presagios de peligro",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "10 versos (Salmo 91): red del cazador, peste mortal, cobijo bajo sus alas, terror nocturno, caída de miles, desgracia, ángeles guardadores, unión con Dios, león y dragón, respuesta y larga vida",
  "notas": "La fuente indica que se completan 91 repeticiones; verificar el cómputo en la app."
}
```

### HZ-01 · Rosario de la Virgen de Lourdes

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/lourdes
- **Instrumento:** Rosario mariano tradicional de 59 cuentas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Sosteniendo la cruz: 1 señal de la cruz + 1 Credo → En las 5 cuentas finales: 1 Padre Nuestro, 3 Avemarías, 1 Gloria al Padre → En cada decena: 1 Padre Nuestro, 10 Avemarías, 1 Gloria al Padre
- **Día / frecuencia:** Diario a las 15:30 en el santuario de Lourdes; uso libre en comunión con los enfermos
- **Variables del usuario:** intención por los enfermos o peregrinos
- **Meditación / intenciones:** Intención de Lourdes: sufrimientos físicos, morales y espirituales; sin misterios propios
- **Características y advertencias:** La fuente no aporta textos nuevos ni misterios: es el rosario estándar con intención lourdiana. No inventar oraciones.

```json
{
  "id": "HZ-01",
  "nombre": "Rosario de la Virgen de Lourdes",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/lourdes",
  "sitio": "Hozana",
  "instrumento": "Rosario mariano tradicional de 59 cuentas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Sosteniendo la cruz: 1 señal de la cruz + 1 Credo",
    "En las 5 cuentas finales: 1 Padre Nuestro, 3 Avemarías, 1 Gloria al Padre",
    "En cada decena: 1 Padre Nuestro, 10 Avemarías, 1 Gloria al Padre"
  ],
  "dia_o_frecuencia": "Diario a las 15:30 en el santuario de Lourdes; uso libre en comunión con los enfermos",
  "variables_usuario": "intención por los enfermos o peregrinos",
  "meditacion_o_intenciones": "Intención de Lourdes: sufrimientos físicos, morales y espirituales; sin misterios propios",
  "notas": "La fuente no aporta textos nuevos ni misterios: es el rosario estándar con intención lourdiana. No inventar oraciones."
}
```

### HZ-02 · Coronilla de liberación

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/de-liberacion
- **Instrumento:** Rosario especial o coronilla similar al rosario tradicional
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Credo → Cuentas grandes: «si el Hijo los libera, ustedes serán realmente libres» (Jn 8:36) → Cuentas pequeñas: «¡Jesús, ten piedad de mí! ¡Jesús, sáname! ¡Jesús, sálvame! ¡Jesús, libérame!» → Cierre: Salve Regina
- **Día / frecuencia:** Uso libre; se recomienda confiar una intención precisa antes de empezar
- **Variables del usuario:** intención concreta (personal o de un ser querido)
- **Meditación / intenciones:** Basada en Jn 8:36; miedos, adicciones, ira, heridas, falsas creencias
- **Características y advertencias:** La página añade oraciones del Ritual Romano como material complementario, no como parte de la coronilla.

```json
{
  "id": "HZ-02",
  "nombre": "Coronilla de liberación",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/de-liberacion",
  "sitio": "Hozana",
  "instrumento": "Rosario especial o coronilla similar al rosario tradicional",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Credo",
    "Cuentas grandes: «si el Hijo los libera, ustedes serán realmente libres» (Jn 8:36)",
    "Cuentas pequeñas: «¡Jesús, ten piedad de mí! ¡Jesús, sáname! ¡Jesús, sálvame! ¡Jesús, libérame!»",
    "Cierre: Salve Regina"
  ],
  "dia_o_frecuencia": "Uso libre; se recomienda confiar una intención precisa antes de empezar",
  "variables_usuario": "intención concreta (personal o de un ser querido)",
  "meditacion_o_intenciones": "Basada en Jn 8:36; miedos, adicciones, ira, heridas, falsas creencias",
  "notas": "La página añade oraciones del Ritual Romano como material complementario, no como parte de la coronilla."
}
```

### HZ-03 · Coronilla de las llagas de Cristo

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/de-las-llagas-de-cristo
- **Instrumento:** Rosario tradicional de 5 decenas
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: tres oraciones («Oh Jesús, divino Redentor...», «Dios santo, Dios fuerte, Dios inmortal...», «Oh Padre Eterno, trátanos con misericordia...») → Cuentas grandes: «Padre Eterno, te ofrezco las heridas de nuestro Señor Jesucristo para sanar las heridas de nuestras almas» → Cuentas pequeñas: «Jesús mío, perdón y misericordia, por los méritos de tus santas llagas» → Cierre: el «Padre Eterno...» ×3 → Al inicio de cada decena: intención y nombrar una llaga (manos, pies, espalda, cabeza, corazón)
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** intención por decena; llaga a meditar
- **Meditación / intenciones:** Sor María Martha Chambon; retoma los misterios dolorosos
- **Características y advertencias:** También llamada Rosario a la preciosa sangre de Cristo. La página añade una consagración a la Faz y una oración a la llaga del hombro como textos complementarios.

```json
{
  "id": "HZ-03",
  "nombre": "Coronilla de las llagas de Cristo",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/de-las-llagas-de-cristo",
  "sitio": "Hozana",
  "instrumento": "Rosario tradicional de 5 decenas",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: tres oraciones («Oh Jesús, divino Redentor...», «Dios santo, Dios fuerte, Dios inmortal...», «Oh Padre Eterno, trátanos con misericordia...»)",
    "Cuentas grandes: «Padre Eterno, te ofrezco las heridas de nuestro Señor Jesucristo para sanar las heridas de nuestras almas»",
    "Cuentas pequeñas: «Jesús mío, perdón y misericordia, por los méritos de tus santas llagas»",
    "Cierre: el «Padre Eterno...» ×3",
    "Al inicio de cada decena: intención y nombrar una llaga (manos, pies, espalda, cabeza, corazón)"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "intención por decena; llaga a meditar",
  "meditacion_o_intenciones": "Sor María Martha Chambon; retoma los misterios dolorosos",
  "notas": "También llamada Rosario a la preciosa sangre de Cristo. La página añade una consagración a la Faz y una oración a la llaga del hombro como textos complementarios."
}
```

### HZ-04 · Coronilla para meditar y adorar al Santísimo Sacramento

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/santisimo-sacramento
- **Instrumento:** Rosario tradicional
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: señal de la cruz + Padre Nuestro → 3 Avemarías + 1 Gloria → Cada decena: cuenta grande Padre Nuestro + 10 cuentas pequeñas con «Jesús, alabado seas en todo momento, en el Santísimo Sacramento, hoy y siempre. Jesús, alabado seas en el Santísimo Sacramento» → Cada decena lleva una meditación
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 5 misterios: 1) institución en memoria de la pasión y muerte; 2) para vivir en nosotros cada día; 3) perpetuar el sacrificio en todos los altares; 4) pan y vino del alma; 5) visitarnos al morir y llevarnos al cielo
- **Características y advertencias:** Primera de las dos formas que contiene la misma página; es distinta de la coronilla de 33 cuentas.

```json
{
  "id": "HZ-04",
  "nombre": "Coronilla para meditar y adorar al Santísimo Sacramento",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/santisimo-sacramento",
  "sitio": "Hozana",
  "instrumento": "Rosario tradicional",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: señal de la cruz + Padre Nuestro",
    "3 Avemarías + 1 Gloria",
    "Cada decena: cuenta grande Padre Nuestro + 10 cuentas pequeñas con «Jesús, alabado seas en todo momento, en el Santísimo Sacramento, hoy y siempre. Jesús, alabado seas en el Santísimo Sacramento»",
    "Cada decena lleva una meditación"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "5 misterios: 1) institución en memoria de la pasión y muerte; 2) para vivir en nosotros cada día; 3) perpetuar el sacrificio en todos los altares; 4) pan y vino del alma; 5) visitarnos al morir y llevarnos al cielo",
  "notas": "Primera de las dos formas que contiene la misma página; es distinta de la coronilla de 33 cuentas."
}
```

### HZ-05 · Coronilla de la Sagrada Eucaristía

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/santisimo-sacramento
- **Instrumento:** Coronilla de 33 cuentas (o rosario tradicional si no se tiene)
- **Cuentas:** 33 · **Grupos:** 1x33
- **Secuencia:** Medalla o cruz: «Como no puedo recibirte, Jesús mío, en la Santa Comunión, ven espiritualmente a mi corazón, y hazlo siempre tuyo» → Cada una de las 33 cuentas: «Jesús, presente en el Santísimo Sacramento del altar, ten piedad de nosotros»
- **Día / frecuencia:** Cuando no se puede recibir la comunión en la Misa
- **Variables del usuario:** —
- **Meditación / intenciones:** 33 cuentas = 33 años de Jesús en la Tierra
- **Características y advertencias:** Segunda forma de la página; equivale a la Chaplet of the Blessed Sacrament de Catholic Doors.

```json
{
  "id": "HZ-05",
  "nombre": "Coronilla de la Sagrada Eucaristía",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/santisimo-sacramento",
  "sitio": "Hozana",
  "instrumento": "Coronilla de 33 cuentas (o rosario tradicional si no se tiene)",
  "cuentas": "33",
  "grupos": "1x33",
  "pasos": [
    "Medalla o cruz: «Como no puedo recibirte, Jesús mío, en la Santa Comunión, ven espiritualmente a mi corazón, y hazlo siempre tuyo»",
    "Cada una de las 33 cuentas: «Jesús, presente en el Santísimo Sacramento del altar, ten piedad de nosotros»"
  ],
  "dia_o_frecuencia": "Cuando no se puede recibir la comunión en la Misa",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "33 cuentas = 33 años de Jesús en la Tierra",
  "notas": "Segunda forma de la página; equivale a la Chaplet of the Blessed Sacrament de Catholic Doors."
}
```

### HZ-06 · Coronilla a San Miguel Arcángel (Hozana)

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/coronilla-san-miguel-arcangel
- **Instrumento:** Rosario especial de 39 cuentas: 9 grupos de 3 pequeñas separados por una grande, con 4 granos antes de la medalla; admite rosario tradicional
- **Cuentas:** 39 · **Grupos:** 9x3 + 4
- **Secuencia:** Señal de la cruz + «Dios, ayúdame. ¡Señor, ayúdanos! Gloria al Padre...» → 4 Padres Nuestros iniciales (San Miguel, San Gabriel, San Rafael y ángel guardián) → Por cada coro: saludo específico + Padre Nuestro + 3 Avemarías → Cierre: antífona «Gloriosísimo San Miguel Arcángel...» + V/R y oración final
- **Día / frecuencia:** Protección y liberación en luchas espirituales; también antes de comulgar
- **Variables del usuario:** —
- **Meditación / intenciones:** 9 coros: Serafines, Querubines, Tronos, Dominaciones, Potestades, Virtudes, Principados, Arcángeles, Ángeles
- **Características y advertencias:** Origen: aparición a Antónia de Astónaco, monja carmelita portuguesa, siglo XVIII; conocida como «corona angélica». La lista de coros en esta página difiere del orden de Catholic Doors.

```json
{
  "id": "HZ-06",
  "nombre": "Coronilla a San Miguel Arcángel (Hozana)",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/coronilla-san-miguel-arcangel",
  "sitio": "Hozana",
  "instrumento": "Rosario especial de 39 cuentas: 9 grupos de 3 pequeñas separados por una grande, con 4 granos antes de la medalla; admite rosario tradicional",
  "cuentas": "39",
  "grupos": "9x3 + 4",
  "pasos": [
    "Señal de la cruz + «Dios, ayúdame. ¡Señor, ayúdanos! Gloria al Padre...»",
    "4 Padres Nuestros iniciales (San Miguel, San Gabriel, San Rafael y ángel guardián)",
    "Por cada coro: saludo específico + Padre Nuestro + 3 Avemarías",
    "Cierre: antífona «Gloriosísimo San Miguel Arcángel...» + V/R y oración final"
  ],
  "dia_o_frecuencia": "Protección y liberación en luchas espirituales; también antes de comulgar",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "9 coros: Serafines, Querubines, Tronos, Dominaciones, Potestades, Virtudes, Principados, Arcángeles, Ángeles",
  "notas": "Origen: aparición a Antónia de Astónaco, monja carmelita portuguesa, siglo XVIII; conocida como «corona angélica». La lista de coros en esta página difiere del orden de Catholic Doors."
}
```

### HZ-07 · Coronilla a San José

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/coronilla-san-jose
- **Instrumento:** Coronilla de 60 cuentas pequeñas violeta agrupadas en tríadas y separadas por cuentas grandes blancas; medalla y cruz
- **Cuentas:** 60 pequeñas + separadoras · **Grupos:** tríadas
- **Secuencia:** Cuentas grandes: «San José, guardián de la sagrada familia, bendice nuestras familias» → Cuentas pequeñas: «San José, ruega por nosotros» → Final, en medalla y cruz: «Jesús, José y María, os doy mi corazón y el alma mía... Amén» → Opcional: «Dios te salve, José...»
- **Día / frecuencia:** Ayuda y protección del hogar; gracia de una buena muerte
- **Variables del usuario:** —
- **Meditación / intenciones:** Violeta=piedad de San José; blanco=pureza
- **Características y advertencias:** La fuente la distingue expresamente del Rosario a San José, que sí medita misterios de la vida de Cristo.

```json
{
  "id": "HZ-07",
  "nombre": "Coronilla a San José",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/coronilla-san-jose",
  "sitio": "Hozana",
  "instrumento": "Coronilla de 60 cuentas pequeñas violeta agrupadas en tríadas y separadas por cuentas grandes blancas; medalla y cruz",
  "cuentas": "60 pequeñas + separadoras",
  "grupos": "tríadas",
  "pasos": [
    "Cuentas grandes: «San José, guardián de la sagrada familia, bendice nuestras familias»",
    "Cuentas pequeñas: «San José, ruega por nosotros»",
    "Final, en medalla y cruz: «Jesús, José y María, os doy mi corazón y el alma mía... Amén»",
    "Opcional: «Dios te salve, José...»"
  ],
  "dia_o_frecuencia": "Ayuda y protección del hogar; gracia de una buena muerte",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Violeta=piedad de San José; blanco=pureza",
  "notas": "La fuente la distingue expresamente del Rosario a San José, que sí medita misterios de la vida de Cristo."
}
```

### HZ-08 · 24 Glorias a Santa Teresita

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/santa-teresita
- **Instrumento:** Rosario de 25 cuentas: 24 cuentas y una adicional separada por una medalla de Santa Teresa
- **Cuentas:** 25 · **Grupos:** 24 + 1
- **Secuencia:** Cada una de las 24 cuentas: «Santa Teresa del Niño Jesús y de la Santa Faz, recuerda tu promesa de hacer el bien en la tierra, derrama tu lluvia de rosas...» → Medalla: «Oh Padre nuestro celestial...» → Última cuenta: «Oh Santa Teresa... Patrona de las misiones católicas...» + Padre Nuestro → Opcional: oración propuesta por François Lugan
- **Día / frecuencia:** Uso libre
- **Variables del usuario:** —
- **Meditación / intenciones:** 24 cuentas = 24 años de vida de Santa Teresita
- **Características y advertencias:** OJO: el título dice «24 Glorias», pero la fuente manda oraciones propias largas en cada cuenta, no Glorias. Coincide con CD solo en el número de cuentas.

```json
{
  "id": "HZ-08",
  "nombre": "24 Glorias a Santa Teresita",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/santa-teresita",
  "sitio": "Hozana",
  "instrumento": "Rosario de 25 cuentas: 24 cuentas y una adicional separada por una medalla de Santa Teresa",
  "cuentas": "25",
  "grupos": "24 + 1",
  "pasos": [
    "Cada una de las 24 cuentas: «Santa Teresa del Niño Jesús y de la Santa Faz, recuerda tu promesa de hacer el bien en la tierra, derrama tu lluvia de rosas...»",
    "Medalla: «Oh Padre nuestro celestial...»",
    "Última cuenta: «Oh Santa Teresa... Patrona de las misiones católicas...» + Padre Nuestro",
    "Opcional: oración propuesta por François Lugan"
  ],
  "dia_o_frecuencia": "Uso libre",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "24 cuentas = 24 años de vida de Santa Teresita",
  "notas": "OJO: el título dice «24 Glorias», pero la fuente manda oraciones propias largas en cada cuenta, no Glorias. Coincide con CD solo en el número de cuentas."
}
```

### HZ-09 · Rosario por las benditas almas del purgatorio

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/almas-purgatorio
- **Instrumento:** Rosario tradicional
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Credo → Cuenta grande: Padre Nuestro → 3 cuentas pequeñas: 3 Avemarías → Cuenta grande: Gloria → Cada decena: cuenta grande «Oh Almas Santas, llevad a mi alma el fuego del amor de Dios...» → Cada cuenta de la decena: «Señor Jesús Crucificado, ten piedad de las almas del purgatorio»
- **Día / frecuencia:** Noviembre o cualquier momento del año; puede ser oración diaria
- **Variables del usuario:** —
- **Meditación / intenciones:** También se sugiere rezar el rosario meditando los Misterios Dolorosos
- **Características y advertencias:** Equivale en lo esencial al Chaplet for the Holy Souls de Catholic Doors.

```json
{
  "id": "HZ-09",
  "nombre": "Rosario por las benditas almas del purgatorio",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/almas-purgatorio",
  "sitio": "Hozana",
  "instrumento": "Rosario tradicional",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Credo",
    "Cuenta grande: Padre Nuestro",
    "3 cuentas pequeñas: 3 Avemarías",
    "Cuenta grande: Gloria",
    "Cada decena: cuenta grande «Oh Almas Santas, llevad a mi alma el fuego del amor de Dios...»",
    "Cada cuenta de la decena: «Señor Jesús Crucificado, ten piedad de las almas del purgatorio»"
  ],
  "dia_o_frecuencia": "Noviembre o cualquier momento del año; puede ser oración diaria",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "También se sugiere rezar el rosario meditando los Misterios Dolorosos",
  "notas": "Equivale en lo esencial al Chaplet for the Holy Souls de Catholic Doors."
}
```

### HZ-10 · Rosario por los difuntos

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/almas-purgatorio
- **Instrumento:** Rosario tradicional
- **Cuentas:** 59 · **Grupos:** 5x10
- **Secuencia:** Cruz: Credo → Cuenta grande: Padre Nuestro → 3 cuentas pequeñas: 3 Avemarías → Cuenta grande: Gloria → Cada decena: cuenta grande «Dios mío, creo en Ti porque eres la verdad misma... Que las almas de los fieles difuntos descansen en paz...» → Cada cuenta: «Dulce Corazón de María, sé mi salvación»
- **Día / frecuencia:** Noviembre o cualquier momento
- **Variables del usuario:** —
- **Meditación / intenciones:** Acto de fe, esperanza y caridad por los difuntos
- **Características y advertencias:** Segunda forma de la misma página; se parece al Chaplet for the Dead de Catholic Doors, pero aquí usa 59 cuentas y Credo en vez de 45 cuentas y De Profundis.

```json
{
  "id": "HZ-10",
  "nombre": "Rosario por los difuntos",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/almas-purgatorio",
  "sitio": "Hozana",
  "instrumento": "Rosario tradicional",
  "cuentas": "59",
  "grupos": "5x10",
  "pasos": [
    "Cruz: Credo",
    "Cuenta grande: Padre Nuestro",
    "3 cuentas pequeñas: 3 Avemarías",
    "Cuenta grande: Gloria",
    "Cada decena: cuenta grande «Dios mío, creo en Ti porque eres la verdad misma... Que las almas de los fieles difuntos descansen en paz...»",
    "Cada cuenta: «Dulce Corazón de María, sé mi salvación»"
  ],
  "dia_o_frecuencia": "Noviembre o cualquier momento",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "Acto de fe, esperanza y caridad por los difuntos",
  "notas": "Segunda forma de la misma página; se parece al Chaplet for the Dead de Catholic Doors, pero aquí usa 59 cuentas y Credo en vez de 45 cuentas y De Profundis."
}
```

### HZ-11 · El rosario de San Miguel

- **Fuente:** Hozana — https://hozana.org/es/oracion/santo-rosario/san-miguel
- **Instrumento:** Rosario especial de 39 cuentas en 9 grupos de 3 pequeñas; medalla
- **Cuentas:** 39 · **Grupos:** 9x3 + 4
- **Secuencia:** Medalla: señal de la cruz + «Dios ayúdame, Señor socórrenos. Gloria al Padre...» → 4 primeras cuentas: 4 Padrenuestros por San Miguel, San Gabriel, San Rafael y el ángel de la guarda → Cada grupo: saludo a un coro + Padre Nuestro (cuenta grande) + 3 Avemarías + Gloria → Cierre: antífona «Gloriosísimo San Miguel Arcángel...» + V/R y oración final
- **Día / frecuencia:** Por la Iglesia en sus luchas y por las almas del purgatorio
- **Variables del usuario:** —
- **Meditación / intenciones:** 9 coros celestiales, con saludos de texto distinto al de la otra página de Hozana
- **Características y advertencias:** Pío IX promulgó un decreto en 1851. Es la misma devoción que la coronilla anterior pero con textos propios y Gloria explícita en cada grupo.

```json
{
  "id": "HZ-11",
  "nombre": "El rosario de San Miguel",
  "fuente": "https://hozana.org/es/oracion/santo-rosario/san-miguel",
  "sitio": "Hozana",
  "instrumento": "Rosario especial de 39 cuentas en 9 grupos de 3 pequeñas; medalla",
  "cuentas": "39",
  "grupos": "9x3 + 4",
  "pasos": [
    "Medalla: señal de la cruz + «Dios ayúdame, Señor socórrenos. Gloria al Padre...»",
    "4 primeras cuentas: 4 Padrenuestros por San Miguel, San Gabriel, San Rafael y el ángel de la guarda",
    "Cada grupo: saludo a un coro + Padre Nuestro (cuenta grande) + 3 Avemarías + Gloria",
    "Cierre: antífona «Gloriosísimo San Miguel Arcángel...» + V/R y oración final"
  ],
  "dia_o_frecuencia": "Por la Iglesia en sus luchas y por las almas del purgatorio",
  "variables_usuario": "—",
  "meditacion_o_intenciones": "9 coros celestiales, con saludos de texto distinto al de la otra página de Hozana",
  "notas": "Pío IX promulgó un decreto en 1851. Es la misma devoción que la coronilla anterior pero con textos propios y Gloria explícita en cada grupo."
}
```

## 5. Duplicados, variantes y solapamientos entre las tres webs

| Devoción | Variantes encontradas | Cómo tratarlas en la app |
|---|---|---|
| Divina Misericordia | CD #1 (Stockbridge, dos oraciones de apertura), CD #2 (arranca en la medalla) e IVE (español, a las 15:00 y junto a moribundos) | Tres `variants` de un mismo `devotion_id`; dejar elegir idioma y punto de inicio. |
| Almas del Purgatorio | CD *Chaplet for the Holy Souls* (59 cuentas, invocación larga), CD *Chaplet of all Souls* (5 decenas con intenciones por allegados) e IVE *Coronilla a las almas del Purgatorio* (revelaciones a tres santas) | Tres configuraciones distintas: no fusionar. |
| Siete Dolores | CD *Chaplet of the Seven Dolors* (58 cuentas, 7x7+3) e IVE *Corona de los Siete Dolores* (7x7 con meditaciones largas y promesas) | Compartir los 7 dolores; separar los textos. |
| Preciosa Sangre | CD *Chaplet of the Precious Blood* (7x33 cuentas) e IVE *Corona a la Sangre Preciosa* (33 Padres Nuestros e indulgencias de Pío VII) | Mismos siete misterios, distinto cómputo: no unificar contadores. |
| San Antonio de Padua | CD *Chaplet of Saint Anthony* (13x3, responsorio latino en inglés) e IVE *Coronilla de San Antonio* (13x3, invocaciones en español) | Misma estructura; ofrecer dos paquetes de idioma. |
| Sagrado Corazón | CD #1 (rosario 59), CD #2 (33 cuentas), CD #3 (59, reparación eucarística) e IVE (rosario, V/R) | Cuatro variantes independientes con un mismo `topic`. |
| Niño Jesús | CD *Holy Infant* (3+12), CD *Infant Jesus of Prague* (3+12 con frase previa), IVE no lo repite | Dos variantes casi idénticas; diferenciar por la frase «And the Word was made flesh». |
| Lágrimas / Santa Faz | CD *Our Lady's Tears* (59, 5 decenas V/R), CD *Chaplet of Tears* (59, 7x7), CD *Holy Face* (39 cuentas) y CD *Sacred Face* (59, 5 textos) | Cuatro configuraciones; no confundir *Holy Face* con *Sacred Face*. |


**Aportación de Hozana al mapa de duplicados:**

- *El rosario de San Miguel* y *Coronilla a San Miguel Arcángel* son la misma devoción con dos textos distintos para los saludos a los coros; solo la primera versión incluye Gloria explícita en cada grupo.
- *Coronilla de la Sagrada Eucaristía* (33 cuentas) es textualmente equivalente a *Chaplet of the Blessed Sacrament* de Catholic Doors.
- *Rosario por las benditas almas del purgatorio* equivale a *Chaplet for the Holy Souls*; *Rosario por los difuntos* se parece a *Chaplet for the Dead*, pero usa 59 cuentas y Credo en lugar de 45 cuentas y De Profundis.
- *24 Glorias a Santa Teresita* comparte con *Chaplet of Saint Theresa* el número de cuentas (24+1), pero no el texto: Hozana manda oraciones propias largas, no Glorias.

## 6. Auditoría de exactitud (double check)

- Se ha contrastado cada una de las 87 fichas con el texto íntegro de su página fuente. Cambios introducidos en esta revisión:
  1. **Chaplet of Saint Michael (CD)** —añadidas las 4 cuentas finales con un Padre Nuestro por San Miguel, San Gabriel, San Rafael y el Ángel Guardián, más la antífona de cierre; el cómputo correcto es 9×(1+3)+4.
  2. **Chaplet of the Holy Ghost (CD)** —corregidos los cinco misterios según la fuente: Encarnación, Bautismo, desierto y tentación, Espíritu en la Iglesia y Espíritu en el alma del justo. Se eliminó la conjetura «Transfiguración/Cruz».
  3. **Chaplet of Our Lady of Guadalupe (CD)** —corregido el patrón: 1+4+1 y después 3+1+3+1+3+1+3 = 21 cuentas (la versión anterior daba 22).
  4. **Chaplet of the Immaculate Conception (CD)** —se documenta que la fuente dice 15 cuentas pero describe 3×4+1 = 13.
  5. **IVE, Misterios de Gozo / Dolores / Luz** —sustituidas las listas genéricas de misterios por los títulos literales de la fuente.
  6. **IVE, Corona de la Sangre Preciosa** —corregido el último misterio: lleva 3 Padres Nuestros **y** Gloria, no Gloria omitida.
  7. **IVE, Coronilla a las almas del Purgatorio** —secuencia corregida con la jaculatoria «Jesús, María os amo, salvad las almas» en cada cuenta, repetición entre decenas y las tres cuentas finales.
  8. **Josephian Rosary (CD)** —añadida la doble modalidad de la fuente: meditar los misterios 1-5 en las cinco decenas, o los misterios 6-10.
  9. **Hozana, Santa Teresita** —el título «24 Glorias» no coincide con el cuerpo, que prescribe oraciones propias; queda advertido en su ficha.

- No se han «arreglado» las erratas de las páginas originales: se conservan y se señalan para que la app no herede un cómputo imposible como si fuera correcto.


## 7. Advertencias de calidad de los datos

- La fuente *Chaplet of all Souls* lleva por error el encabezado «How to pray the Chaplet of Our Lady's Tears»; el cuerpo corresponde a All Souls [1].
- *Chaplet of the Way of the Cross* anuncia 15 grupos de 3 cuentas pero enumera solo 14 estaciones [1]. La app debería usar 14 o preguntar.
- El índice IVE contiene erratas de título: «Misiricordia», «Rosariod del Espíritu Santo», «San Antonios de Padua», «Coronillade San Charbel» [2]. Se han corregido en las fichas, manteniendo el título original entre paréntesis donde era útil.
- *Chaplet to Obtain a Great Confidence* indica expresamente que se ignoran 4 de las 59 cuentas [1].
- *Josephian Rosary* está marcado por la fuente como «For Private Use Only» y sus misterios son obra de Catholic Doors Ministry [1].
- Las indulgencias citadas (Faith, Blessed Sacrament, Seven Dolors, Precious Blood) corresponden al texto histórico de las fuentes; deben verificarse contra la normativa vigente antes de mostrarse como vigentes [1][2].
## 8. Esquema de ejecución recomendado para el LLM

```
start(devotion_id)
  → cargar spec JSON de la ficha
  → pedir las `variables_usuario` (si las hay)
  → mostrar `instrumento` y configurar contador = 0
loop:
  → paso = pasos[cursor]
  → si el paso tiene `meditacion`, mostrarla ANTES de la oración
  → si el paso tiene `variable`, sustituir el marcador por el dato del usuario
  → mostrar el texto literal de la fuente (nunca generado)
  → esperar confirmación del usuario → avanzar el contador
  → al cerrar un `grupo`, comprobar el número de repeticiones y cambiar de texto
  → al terminar, mostrar el cierre y la opción de repetir
```

**Buenas prácticas:** una sola oración por pantalla; contador visible «decena 3 de 5, cuenta 7 de 10»; botón «repetir última»; modo «sin instrumento» que sustituye el avance de cuentas por un contador en pantalla; y un `source_url` siempre accesible para que el usuario verifique el texto.

## Sources

1. Catholic Doors Ministry — *A Treasure of 59 Chaplet Devotions* (índice y 59 fichas enlazadas): https://www.catholicdoors.com/prayers/chaplets.htm
2. Instituto del Verbo Encarnado — *Rosarios y Coronas*: https://iveinarabic.org/es/rosarios-y-coronas/
3. Hozana — *Santo Rosario* (9 páginas consultadas): https://hozana.org/es/oracion/santo-rosario/ — lourdes, de-liberacion, de-las-llagas-de-cristo, santisimo-sacramento, coronilla-san-miguel-arcangel, coronilla-san-jose, santa-teresita, almas-purgatorio, san-miguel

Las URLs individuales de cada devoción figuran en su ficha y en el campo `fuente` de cada bloque JSON.
