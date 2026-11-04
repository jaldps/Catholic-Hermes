package com.example.data.repository

import com.example.data.models.ApologeticsQuestion
import com.example.data.models.LocalizedText

/**
 * 101 Catholic Questions: apologetics Q&A (Respuesta / Fundamento / Aclaración),
 * researched against Scripture + CCC (honest limits kept in each clarification).
 * ES verbatim from the project source text; EN/PT faithful translations.
 */
object CatholicQuestionsData {
    val questions: List<ApologeticsQuestion> = listOf(
    ApologeticsQuestion(
        number = 1,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios es bueno y todopoderoso, ¿por qué permite el sufrimiento de personas inocentes?""", """If God is good and almighty, why does He allow the suffering of innocent people?""", """Se Deus é bom e onipotente, por que permite o sofrimento de pessoas inocentes?"""),
        answer = LocalizedText("""La fe católica no enseña que todo sufrimiento sea querido directamente por Dios ni que una persona sufra necesariamente porque ha pecado.

Jesús rechaza esa asociación simplista cuando habla del hombre ciego de nacimiento (Juan 9,1-3). Y ante la muerte de Lázaro, Jesús mismo llora (Juan 11,35).

La libertad humana explica una parte del mal, como las guerras, los asesinatos y los abusos. Pero no explica por sí sola los terremotos, el cáncer infantil o las enfermedades hereditarias.

La respuesta cristiana más profunda es que Dios no permanece indiferente ante el sufrimiento: en Jesucristo entra en nuestra condición humana, sufre y vence la muerte mediante la resurrección.""", """Catholic faith does not teach that all suffering is directly willed by God, nor that a person suffers necessarily because they have sinned.

Jesus rejects that simplistic association when He speaks of the man born blind (John 9:1-3). And before the death of Lazarus, Jesus Himself weeps (John 11:35).

Human freedom explains part of evil, such as wars, murders and abuse. But it does not by itself explain earthquakes, childhood cancer or hereditary diseases.

The deepest Christian answer is that God is not indifferent to suffering: in Jesus Christ He enters our human condition, suffers and conquers death through the resurrection.""", """A fé católica não ensina que todo sofrimento seja querido diretamente por Deus, nem que uma pessoa sofra necessariamente porque pecou.

Jesus rejeita essa associação simplista quando fala do homem cego de nascença (João 9,1-3). E diante da morte de Lázaro, o próprio Jesus chora (João 11,35).

A liberdade humana explica uma parte do mal, como as guerras, os assassinatos e os abusos. Mas não explica por si só os terremotos, o câncer infantil ou as doenças hereditárias.

A resposta cristã mais profunda é que Deus não permanece indiferente ao sofrimento: em Jesus Cristo entra em nossa condição humana, sofre e vence a morte mediante a ressurreição."""),
        foundation = LocalizedText("""Romanos 8,18-23; Juan 9,1-3; Apocalipsis 21,4. Catecismo, 309-314.""", """Romans 8:18-23; John 9:1-3; Revelation 21:4. Catechism, 309-314.""", """Romanos 8,18-23; João 9,1-3; Apocalipse 21,4. Catecismo, 309-314."""),
        clarification = LocalizedText(""": Esto no demuestra por qué Dios permite cada tragedia. El problema del sufrimiento inocente sigue siendo una de las dificultades filosóficas más fuertes para el teísmo. La esperanza cristiana ofrece una respuesta sobre el destino del sufrimiento, pero no una explicación exhaustiva de cada caso.""", """This does not demonstrate why God allows every tragedy. The problem of innocent suffering remains one of the strongest philosophical difficulties for theism. Christian hope offers an answer about the destiny of suffering, but not an exhaustive explanation of every case.""", """Isso não demonstra por que Deus permite cada tragédia. O problema do sofrimento inocente continua sendo uma das dificuldades filosóficas mais fortes para o teísmo. A esperança cristã oferece uma resposta sobre o destino do sofrimento, mas não uma explicação exaustiva de cada caso.""")
    ),
    ApologeticsQuestion(
        number = 2,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios conoce nuestro futuro, ¿cómo podemos ser realmente libres?""", """If God knows our future, how can we truly be free?""", """Se Deus conhece o nosso futuro, como podemos ser realmente livres?"""),
        answer = LocalizedText("""Conocer una decisión no es lo mismo que causarla.

Imaginemos que alguien observa una acción que otra persona realiza libremente. Su conocimiento no convierte esa acción en obligatoria. La explicación clásica sostiene que Dios, al ser eterno, conoce nuestras decisiones sin estar limitado a nuestro tiempo.

La Biblia afirma tanto el conocimiento divino como la responsabilidad humana.""", """Knowing a decision is not the same as causing it.

Imagine someone observing an action that another person performs freely. Their knowledge does not make that action obligatory. The classical explanation holds that God, being eternal, knows our decisions without being limited to our time.

The Bible affirms both divine knowledge and human responsibility.""", """Conhecer uma decisão não é o mesmo que causá-la.

Imaginemos alguém que observa uma ação que outra pessoa realiza livremente. Seu conhecimento não torna essa ação obrigatória. A explicação clássica sustenta que Deus, por ser eterno, conhece nossas decisões sem estar limitado ao nosso tempo.

A Bíblia afirma tanto o conhecimento divino quanto a responsabilidade humana."""),
        foundation = LocalizedText("""Salmo 139,1-16; Deuteronomio 30,19; Eclesiástico 15,11-20; Catecismo, 1730-1742.""", """Psalm 139:1-16; Deuteronomy 30:19; Sirach 15:11-20; Catechism, 1730-1742.""", """Salmo 139,1-16; Deuteronômio 30,19; Eclesiástico 15,11-20; Catecismo, 1730-1742."""),
        clarification = LocalizedText(""": La comparación con un observador humano ayuda, pero no resuelve por completo el problema filosófico. Existen diversas explicaciones católicas sobre cómo se relacionan la presciencia, la gracia y la libertad.

La Iglesia sostiene las dos afirmaciones fundamentales: Dios conoce todas las cosas y el ser humano es realmente responsable de sus elecciones.""", """The comparison with a human observer helps, but does not fully resolve the philosophical problem. There are various Catholic explanations of how foreknowledge, grace and freedom relate.

The Church holds both fundamental affirmations: God knows all things, and the human being is truly responsible for their choices.""", """A comparação com um observador humano ajuda, mas não resolve por completo o problema filosófico. Existem diversas explicações católicas sobre como se relacionam presciência, graça e liberdade.

A Igreja sustenta as duas afirmações fundamentais: Deus conhece todas as coisas e o ser humano é realmente responsável por suas escolhas.""")
    ),
    ApologeticsQuestion(
        number = 3,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios es amor, ¿cómo puede existir un infierno eterno?""", """If God is love, how can an eternal hell exist?""", """Se Deus é amor, como pode existir um inferno eterno?"""),
        answer = LocalizedText("""La explicación católica es que el infierno no representa la falta de amor de Dios, sino la posibilidad de que una criatura libre rechace definitivamente su comunión con Él.

El Catecismo define su realidad esencial como separación definitiva de Dios.

Jesús habla seriamente del juicio y de la posibilidad de perderse. Por eso, la Iglesia no puede eliminar el infierno de su enseñanza.""", """The Catholic explanation is that hell does not represent the absence of God's love, but the possibility that a free creature definitively rejects communion with Him.

The Catechism defines its essential reality as definitive separation from God.

Jesus speaks seriously of judgment and of the possibility of being lost. That is why the Church cannot remove hell from its teaching.""", """A explicação católica é que o inferno não representa a falta de amor de Deus, mas a possibilidade de uma criatura livre rejeitar definitivamente a comunhão com Ele.

O Catecismo define sua realidade essencial como separação definitiva de Deus.

Jesus fala seriamente do juízo e da possibilidade de se perder. Por isso, a Igreja não pode eliminar o inferno de sua doutrina."""),
        foundation = LocalizedText("""Mateo 25,31-46; Marcos 9,43-48; 1 Timoteo 2,4; Catecismo, 1033-1037.""", """Matthew 25:31-46; Mark 9:43-48; 1 Timothy 2:4; Catechism, 1033-1037.""", """Mateus 25,31-46; Marcos 9,43-48; 1 Timóteo 2,4; Catecismo, 1033-1037."""),
        clarification = LocalizedText(""": La objeción más difícil permanece: ¿por qué un Dios infinitamente misericordioso permitiría que ese rechazo fuese definitivo?

Apelar a la libertad constituye una respuesta parcial, no una demostración concluyente. La Iglesia no enseña que debamos considerar condenada a una persona concreta y anima a rezar por la salvación de todos.""", """The most difficult objection remains: why would an infinitely merciful God allow that rejection to be definitive?

Appealing to freedom is a partial answer, not a conclusive demonstration. The Church does not teach that we should consider a concrete person condemned, and it encourages prayer for the salvation of all.""", """A objeção mais difícil permanece: por que um Deus infinitamente misericordioso permitiria que essa rejeição fosse definitiva?

Apelar à liberdade constitui uma resposta parcial, não uma demonstração conclusiva. A Igreja não ensina que devamos considerar condenada uma pessoa concreta e anima a rezar pela salvação de todos.""")
    ),
    ApologeticsQuestion(
        number = 4,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué Dios no se manifiesta de una forma absolutamente evidente?""", """Why does God not reveal Himself in an absolutely evident way?""", """Por que Deus não se manifesta de forma absolutamente evidente?"""),
        answer = LocalizedText("""La tradición católica sostiene que Dios puede ser conocido parcialmente mediante la razón, la creación y la conciencia, pero que conocer su existencia no equivale a entrar en una relación de amor con Él.

La Biblia presenta la creación como un signo de Dios y a Jesucristo como su revelación decisiva.""", """Catholic tradition holds that God can be partially known through reason, creation and conscience, but that knowing of His existence is not the same as entering into a loving relationship with Him.

The Bible presents creation as a sign of God and Jesus Christ as His decisive revelation.""", """A tradição católica sustenta que Deus pode ser conhecido parcialmente mediante a razão, a criação e a consciência, mas que conhecer sua existência não equivale a entrar em uma relação de amor com Ele.

A Bíblia apresenta a criação como sinal de Deus e a Jesus Cristo como sua revelação decisiva."""),
        foundation = LocalizedText("""Romanos 1,19-20; Salmo 19,2-5; Juan 1,18; Hebreos 1,1-2; Catecismo, 31-35.""", """Romans 1:19-20; Psalm 19:2-5; John 1:18; Hebrews 1:1-2; Catechism, 31-35.""", """Romanos 1,19-20; Salmo 19,2-5; João 1,18; Hebreus 1,1-2; Catecismo, 31-35."""),
        clarification = LocalizedText(""": Es importante no responder simplemente que Dios se oculta para proteger nuestra libertad. Conocer que alguien existe no elimina nuestra capacidad de amarlo o rechazarlo.

La ocultación divina sigue siendo un problema filosófico serio, especialmente cuando hablamos de personas sinceramente abiertas a creer que no encuentran razones suficientes.

La fe católica afirma que Dios busca a todos; no presume conocer por qué cada persona experimenta su presencia o su silencio de manera diferente.""", """It is important not to simply answer that God hides to protect our freedom. Knowing that someone exists does not remove our capacity to love or reject them.

Divine hiddenness remains a serious philosophical problem, especially when we speak of people sincerely open to believing who do not find sufficient reasons.

Catholic faith affirms that God seeks everyone; it does not presume to know why each person experiences His presence or His silence differently.""", """É importante não responder simplesmente que Deus se oculta para proteger nossa liberdade. Conhecer que alguém existe não elimina nossa capacidade de amá-lo ou rejeitá-lo.

A ocultação divina continua sendo um problema filosófico sério, especialmente quando falamos de pessoas sinceramente abertas a crer que não encontram razões suficientes.

A fé católica afirma que Deus busca a todos; não presume conhecer por que cada pessoa experimenta sua presença ou seu silêncio de maneira diferente.""")
    ),
    ApologeticsQuestion(
        number = 5,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Cómo sabemos que el Dios cristiano es verdadero y no una invención humana?""", """How do we know that the Christian God is true and not a human invention?""", """Como sabemos que o Deus cristão é verdadeiro e não uma invenção humana?"""),
        answer = LocalizedText("""Podemos abordar esta pregunta mediante dos investigaciones distintas.

La primera es filosófica: argumentos como la contingencia del universo, la existencia de leyes inteligibles y la fundamentación de la moral intentan mostrar que creer en Dios puede ser racional.

La segunda es histórica: el cristianismo presenta afirmaciones concretas sobre Jesús de Nazaret, su crucifixión y el nacimiento de la fe en su resurrección.

No basta demostrar que es razonable creer en un Creador para demostrar que el catolicismo es verdadero. Se necesita un razonamiento adicional sobre Jesucristo, la revelación y la Iglesia.""", """We can approach this question through two distinct inquiries.

The first is philosophical: arguments such as the contingency of the universe, the existence of intelligible laws and the grounding of morality attempt to show that believing in God can be rational.

The second is historical: Christianity presents concrete affirmations about Jesus of Nazareth, His crucifixion and the birth of faith in His resurrection.

It is not enough to show that it is reasonable to believe in a Creator in order to show that Catholicism is true. Further reasoning is needed about Jesus Christ, revelation and the Church.""", """Podemos abordar essa pergunta por meio de duas investigações distintas.

A primeira é filosófica: argumentos como a contingência do universo, a existência de leis inteligíveis e o fundamento da moral tentam mostrar que crer em Deus pode ser racional.

A segunda é histórica: o cristianismo apresenta afirmações concretas sobre Jesus de Nazaré, sua crucifixão e o nascimento da fé em sua ressurreição.

Não basta demonstrar que é razoável crer em um Criador para demonstrar que o catolicismo é verdadeiro. É necessário um raciocínio adicional sobre Jesus Cristo, a revelação e a Igreja."""),
        foundation = LocalizedText("""Sabiduría 13,1-9; Romanos 1,20; 1 Corintios 15,3-8; Catecismo, 31-38.""", """Wisdom 13:1-9; Romans 1:20; 1 Corinthians 15:3-8; Catechism, 31-38.""", """Sabedoria 13,1-9; Romanos 1,20; 1 Coríntios 15,3-8; Catecismo, 31-38."""),
        clarification = LocalizedText(""": Ningún argumento filosófico universalmente aceptado demuestra todos los dogmas cristianos. La historicidad de Jesús y de su crucifixión está ampliamente respaldada; la resurrección, como intervención divina, requiere una valoración histórica y filosófica adicional.""", """No universally accepted philosophical argument demonstrates all Christian dogmas. The historicity of Jesus and His crucifixion is widely supported; the resurrection, as a divine intervention, requires additional historical and philosophical assessment.""", """Nenhum argumento filosófico universalmente aceito demonstra todos os dogmas cristãos. A historicidade de Jesus e de sua crucifixão é amplamente respaldada; a ressurreição, como intervenção divina, requer uma avaliação histórica e filosófica adicional.""")
    ),
    ApologeticsQuestion(
        number = 6,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios no necesita nada, ¿por qué nos creó y desea que lo adoremos?""", """If God needs nothing, why did He create us and desire us to adore Him?""", """Se Deus não precisa de nada, por que nos criou e deseja que o adoremos?"""),
        answer = LocalizedText("""La Iglesia enseña que Dios creó libremente por amor, no para satisfacer una necesidad.

La adoración cristiana no añade perfección a Dios. Su finalidad es que la criatura reconozca la verdad sobre su origen y aprenda a orientar su vida hacia el bien.

Un ejemplo imperfecto: un padre amoroso puede desear que su hijo reconozca la verdad y lo ame, sin necesitar ese reconocimiento para existir.""", """The Church teaches that God created freely out of love, not to satisfy a need.

Christian worship does not add perfection to God. Its purpose is that the creature recognize the truth about its origin and learn to orient its life toward the good.

An imperfect example: a loving father may desire his child to recognize the truth and love him, without needing that recognition in order to exist.""", """A Igreja ensina que Deus criou livremente por amor, não para satisfazer uma necessidade.

A adoração cristã não acrescenta perfeição a Deus. Sua finalidade é que a criatura reconheça a verdade sobre sua origem e aprenda a orientar sua vida para o bem.

Um exemplo imperfeito: um pai amoroso pode desejar que seu filho reconheça a verdade e o ame, sem precisar desse reconhecimento para existir."""),
        foundation = LocalizedText("""Hechos 17,24-25; Salmo 50,9-15; Efesios 1,3-12; Catecismo, 293-294 y 2096-2097.""", """Acts 17:24-25; Psalm 50:9-15; Ephesians 1:3-12; Catechism, 293-294 and 2096-2097.""", """Atos 17,24-25; Salmo 50,9-15; Efésios 1,3-12; Catecismo, 293-294 e 2096-2097."""),
        clarification = LocalizedText(""": Decir que Dios crea por amor pertenece a la interpretación teológica de la creación. No es una conclusión que podamos comprobar experimentalmente.

La adoración auténtica tampoco consiste solamente en palabras: implica justicia, amor y transformación de vida.""", """To say that God created out of love belongs to the theological interpretation of creation. It is not a conclusion we can verify experimentally.

Authentic worship also does not consist only in words: it implies justice, love and transformation of life.""", """Dizer que Deus criou por amor pertence à interpretação teológica da criação. Não é uma conclusão que possamos comprovar experimentalmente.

A adoração autêntica também não consiste somente em palavras: implica justiça, amor e transformação de vida.""")
    ),
    ApologeticsQuestion(
        number = 7,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué Dios parece responder algunas oraciones y no otras?""", """Why does God seem to answer some prayers and not others?""", """Por que Deus parece responder algumas orações e não outras?"""),
        answer = LocalizedText("""Jesús enseña a pedir con confianza, pero nunca promete que recibiremos exactamente lo solicitado.

En Getsemaní, Jesús pide que pase de Él el cáliz del sufrimiento y acepta la voluntad del Padre. San Pablo relata que pidió ser liberado de una dificultad y no recibió esa liberación del modo esperado.

La oración cristiana no funciona como una máquina en la que introducir suficientes peticiones garantiza un resultado.""", """Jesus teaches us to ask with confidence, but He never promises that we will receive exactly what we ask for.

In Gethsemane, Jesus asks that the cup of suffering pass from Him and accepts the Father's will. Saint Paul relates that he asked to be freed from a difficulty and did not receive that liberation in the expected way.

Christian prayer does not work like a machine into which inserting enough petitions guarantees a result.""", """Jesus ensina a pedir com confiança, mas nunca promete que receberemos exatamente o que pedimos.

No Getsêmani, Jesus pede que o cálice do sofrimento se afaste dele e aceita a vontade do Pai. São Paulo relata que pediu para ser libertado de uma dificuldade e não recebeu essa libertação do modo esperado.

A oração cristã não funciona como uma máquina na qual introduzir pedidos suficientes garanta um resultado."""),
        foundation = LocalizedText("""Mateo 26,39; 2 Corintios 12,7-10; 1 Juan 5,14-15; Catecismo, 2734-2737.""", """Matthew 26:39; 2 Corinthians 12:7-10; 1 John 5:14-15; Catechism, 2734-2737.""", """Mateus 26,39; 2 Coríntios 12,7-10; 1 João 5,14-15; Catecismo, 2734-2737."""),
        clarification = LocalizedText(""": No debemos afirmar que una persona no sanó porque le faltó fe, ni que la muerte de un niño fue necesaria para enseñar una lección.

La explicación católica reconoce que Dios puede actuar, pero no permite identificar con certeza sus motivos en acontecimientos particulares.""", """We must not say that a person was not healed because they lacked faith, nor that a child's death was necessary to teach a lesson.

The Catholic explanation acknowledges that God can act, but does not allow us to identify with certainty His motives in particular events.""", """Não devemos afirmar que uma pessoa não se curou porque lhe faltou fé, nem que a morte de uma criança foi necessária para ensinar uma lição.

A explicação católica reconhece que Deus pode agir, mas não permite identificar com certeza seus motivos em acontecimentos particulares.""")
    ),
    ApologeticsQuestion(
        number = 8,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios tiene un plan, ¿también forman parte de él las guerras y los abusos?""", """If God has a plan, are wars and abuse also part of it?""", """Se Deus tem um plano, as guerras e os abusos também fazem parte dele?"""),
        answer = LocalizedText("""Es necesario distinguir entre lo que Dios quiere moralmente y lo que permite.

La Biblia condena el asesinato, la injusticia y el abuso del inocente. Por tanto, sería contradictorio afirmar que Dios manda cometer esas acciones y al mismo tiempo las prohíbe.

La providencia significa que el mal no puede frustrar definitivamente el propósito salvador de Dios; no significa que cada atrocidad sea una orden divina.""", """We must distinguish between what God morally wills and what He permits.

The Bible condemns murder, injustice and abuse of the innocent. It would therefore be contradictory to affirm that God commands those actions while at the same time prohibiting them.

Providence means that evil cannot definitively frustrate God's saving purpose; it does not mean that every atrocity is a divine command.""", """É necessário distinguir entre o que Deus quer moralmente e o que permite.

A Bíblia condena o assassinato, a injustiça e o abuso do inocente. Portanto, seria contraditório afirmar que Deus manda cometer essas ações e ao mesmo tempo as proíbe.

A providência significa que o mal não pode frustrar definitivamente o propósito salvador de Deus; não significa que cada atrocidade seja uma ordem divina."""),
        foundation = LocalizedText("""Santiago 1,13; Génesis 50,20; Romanos 8,28; Catecismo, 311-314.""", """James 1:13; Genesis 50:20; Romans 8:28; Catechism, 311-314.""", """Tiago 1,13; Gênesis 50,20; Romanos 8,28; Catecismo, 311-314."""),
        clarification = LocalizedText(""": Que Dios pueda obtener un bien después de una tragedia no convierte la tragedia en buena.

Y nunca debe utilizarse la providencia para excusar al agresor, impedir una denuncia o convencer a una víctima de que debe aceptar el mal.""", """That God can bring good after a tragedy does not make the tragedy good.

And providence must never be used to excuse the aggressor, prevent a complaint, or convince a victim that they must accept evil.""", """Que Deus possa obter um bem depois de uma tragédia não torna a tragédia boa.

E nunca se deve usar a providência para desculpar o agressor, impedir uma denúncia ou convencer uma vítima de que deve aceitar o mal.""")
    ),
    ApologeticsQuestion(
        number = 9,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué el Dios del Antiguo Testamento parece violento y Jesús enseña el amor?""", """Why does the God of the Old Testament seem violent, while Jesus teaches love?""", """Por que o Deus do Antigo Testamento parece violento e Jesus ensina o amor?"""),
        answer = LocalizedText("""Para el cristianismo existe un solo Dios y una historia progresiva de revelación.

La Biblia contiene textos de épocas diferentes, expresados mediante géneros literarios y mentalidades históricas distintas.

Jesús no presenta a otro Dios, sino que lleva la revelación a su plenitud. Por eso, al interpretar las Escrituras, el cristiano debe considerar la persona y la enseñanza de Cristo.""", """For Christianity there is one God and a progressive history of revelation.

The Bible contains texts from different periods, expressed through different literary genres and historical mentalities.

Jesus does not present another God, but brings revelation to its fullness. Therefore, when interpreting the Scriptures, the Christian must consider the person and teaching of Christ.""", """Para o cristianismo existe um só Deus e uma história progressiva de revelação.

A Bíblia contém textos de épocas diferentes, expressados mediante gêneros literários e mentalidades históricas distintas.

Jesus não apresenta outro Deus, mas leva a revelação à sua plenitude. Por isso, ao interpretar as Escrituras, o cristão deve considerar a pessoa e o ensino de Cristo."""),
        foundation = LocalizedText("""Mateo 5,17-48; Lucas 9,54-56; Hebreos 1,1-3; Catecismo, 121-123.""", """Matthew 5:17-48; Luke 9:54-56; Hebrews 1:1-3; Catechism, 121-123.""", """Mateus 5,17-48; Lucas 9,54-56; Hebreus 1,1-3; Catecismo, 121-123."""),
        clarification = LocalizedText(""": Algunos relatos del Antiguo Testamento atribuyen explícitamente a Dios órdenes de violencia. No resulta suficiente negar que esos textos existan o declararlos automáticamente simbólicos.

La interpretación católica debe estudiar su género, contexto, finalidad y relación con Cristo. Sigue existiendo un debate teológico importante sobre cómo entender determinados pasajes.""", """Some Old Testament accounts explicitly attribute commands of violence to God. It is not enough to deny that those texts exist or to declare them automatically symbolic.

Catholic interpretation must study their genre, context, purpose and relation to Christ. An important theological debate continues on how to understand certain passages.""", """Alguns relatos do Antigo Testamento atribuem explicitamente a Deus ordens de violência. Não basta negar que esses textos existam ou declará-los automaticamente simbólicos.

A interpretação católica deve estudar seu gênero, contexto, finalidade e relação com Cristo. Continua um debate teológico importante sobre como entender determinados trechos.""")
    ),
    ApologeticsQuestion(
        number = 10,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Puede salvarse una persona buena que no cree en Dios?""", """Can a good person who does not believe in God be saved?""", """Uma pessoa boa que não crê em Deus pode ser salva?"""),
        answer = LocalizedText("""Sí, la doctrina católica reconoce la posibilidad de salvación de quienes, sin culpa propia, no conocen a Cristo o a la Iglesia, pero buscan sinceramente la verdad y procuran hacer el bien según su conciencia, bajo la acción de la gracia.

No significa que las buenas obras compren automáticamente el cielo. La Iglesia enseña que toda salvación procede de la gracia de Dios mediante Jesucristo.""", """Yes, Catholic doctrine recognizes the possibility of salvation for those who, without fault of their own, do not know Christ or the Church, but sincerely seek the truth and strive to do good according to their conscience, under the action of grace.

This does not mean that good works automatically buy heaven. The Church teaches that all salvation proceeds from God's grace through Jesus Christ.""", """Sim, a doutrina católica reconhece a possibilidade de salvação daqueles que, sem culpa própria, não conhecem a Cristo ou a Igreja, mas buscam sinceramente a verdade e procuram fazer o bem segundo sua consciência, sob a ação da graça.

Isso não significa que as boas obras comprem automaticamente o céu. A Igreja ensina que toda salvação procede da graça de Deus mediante Jesus Cristo."""),
        foundation = LocalizedText("""Romanos 2,14-16; 1 Timoteo 2,4-6; Mateo 25,31-46; Catecismo, 846-848.""", """Romans 2:14-16; 1 Timothy 2:4-6; Matthew 25:31-46; Catechism, 846-848.""", """Romanos 2,14-16; 1 Timóteo 2,4-6; Mateus 25,31-46; Catecismo, 846-848."""),
        clarification = LocalizedText(""": La Iglesia no afirma conocer el destino eterno de cada persona ni sostiene que cualquier creencia sea igualmente verdadera.

Tampoco enseña que una persona deba declarar explícitamente su fe católica para que Dios pueda actuar en ella cuando existe ignorancia no culpable.""", """The Church does not claim to know the eternal destiny of each person, nor does it hold that every belief is equally true.

Nor does it teach that a person must explicitly declare their Catholic faith for God to act in them when there is non-culpable ignorance.""", """A Igreja não afirma conhecer o destino eterno de cada pessoa nem sustenta que qualquer crença seja igualmente verdadeira.

Tampouco ensina que uma pessoa deva declarar explicitamente sua fé católica para que Deus possa atuar nela quando existe ignorância não culpável.""")
    ),
    ApologeticsQuestion(
        number = 11,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué aceptar la Tradición y el Magisterio si ya tenemos la Biblia?""", """Why accept Tradition and the Magisterium if we already have the Bible?""", """Por que aceitar a Tradição e o Magistério se já temos a Bíblia?"""),
        answer = LocalizedText("""Porque el Nuevo Testamento no presenta la enseñanza cristiana como algo transmitido exclusivamente mediante libros.

San Pablo pide a los cristianos conservar las enseñanzas recibidas tanto oralmente como por carta. También encomienda a Timoteo transmitir su enseñanza a personas capaces de enseñar a otros.

La argumentación católica es sencilla: la predicación apostólica precedió a la redacción completa y al reconocimiento del canon del Nuevo Testamento.

Esto demuestra que la comunidad cristiana primitiva no vivía exclusivamente de una colección cerrada de libros.""", """Because the New Testament does not present Christian teaching as something transmitted exclusively through books.

Saint Paul asks Christians to keep the teachings they received both orally and by letter. He also entrusts Timothy to transmit his teaching to people capable of teaching others.

The Catholic argument is simple: apostolic preaching preceded the complete writing and recognition of the New Testament canon.

This shows that the primitive Christian community did not live exclusively from a closed collection of books.""", """Porque o Novo Testamento não apresenta o ensino cristão como algo transmitido exclusivamente mediante livros.

São Paulo pede aos cristãos que conservem os ensinamentos recebidos tanto oralmente como por carta. Também encomenda a Timóteo transmitir seu ensino a pessoas capazes de ensinar a outros.

A argumentação católica é simples: a pregação apostólica precedeu a redação completa e o reconhecimento do cânon do Novo Testamento.

Isso demonstra que a comunidade cristã primitiva não vivia exclusivamente de uma coleção fechada de livros."""),
        foundation = LocalizedText("""2 Tesalonicenses 2,15; 2 Timoteo 2,2; 1 Timoteo 3,15; Juan 21,25; Catecismo, 74-87.""", """2 Thessalonians 2:15; 2 Timothy 2:2; 1 Timothy 3:15; John 21:25; Catechism, 74-87.""", """2 Tessalonicenses 2,15; 2 Timóteo 2,2; 1 Timóteo 3,15; João 21,25; Catecismo, 74-87."""),
        clarification = LocalizedText(""": Estos versículos no prueban automáticamente que toda tradición católica posterior sea apostólica. Para sostener una enseñanza concreta hay que mostrar su continuidad con la revelación, no simplemente llamarla tradición.

La discusión con sola Scriptura consiste en determinar qué autoridad normativa corresponde a la transmisión apostólica y cómo reconocerla.""", """These verses do not automatically prove that every later Catholic tradition is apostolic. To sustain a concrete teaching one must show its continuity with revelation, not simply call it tradition.

The discussion with sola Scriptura concerns determining what normative authority belongs to apostolic transmission and how to recognize it.""", """Esses versículos não provam automaticamente que toda tradição católica posterior seja apostólica. Para sustentar um ensino concreto é preciso mostrar sua continuidade com a revelação, não simplesmente chamá-lo de tradição.

A discussão com a sola Scriptura consiste em determinar que autoridade normativa corresponde à transmissão apostólica e como reconhecê-la.""")
    ),
    ApologeticsQuestion(
        number = 12,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Quién decidió qué libros pertenecen a la Biblia?""", """Who decided which books belong to the Bible?""", """Quem decidiu quais livros pertencem à Bíblia?"""),
        answer = LocalizedText("""El canon fue reconocido gradualmente en la vida de las comunidades cristianas, mediante el uso litúrgico, la recepción eclesial, la relación con la tradición apostólica y el discernimiento de la Iglesia.

No fue una lista que Jesús dejara escrita antes de ascender al cielo.

La distinción católica fundamental es que la Iglesia reconoce los libros inspirados; no los vuelve inspirados mediante una votación.""", """The canon was gradually recognized in the life of Christian communities, through liturgical use, ecclesial reception, relation to apostolic tradition and the discernment of the Church.

It was not a list that Jesus left written before ascending to heaven.

The fundamental Catholic distinction is that the Church recognizes the inspired books; it does not make them inspired by a vote.""", """O cânon foi reconhecido gradualmente na vida das comunidades cristãs, mediante o uso litúrgico, a recepção eclesial, a relação com a tradição apostólica e o discernimento da Igreja.

Não foi uma lista que Jesus deixou escrita antes de subir ao céu.

A distinção católica fundamental é que a Igreja reconhece os livros inspirados; não os torna inspirados mediante um voto."""),
        foundation = LocalizedText("""Lucas 24,44; 2 Pedro 3,15-16; 1 Timoteo 3,15; Catecismo, 120.""", """Luke 24:44; 2 Peter 3:15-16; 1 Timothy 3:15; Catechism, 120.""", """Lucas 24,44; 2 Pedro 3,15-16; 1 Timóteo 3,15; Catecismo, 120."""),
        clarification = LocalizedText(""": La formación histórica del canon es más compleja que afirmar que un único concilio decidió todo. Hubo debates y variaciones regionales.

El razonamiento católico destaca la necesidad de una tradición eclesial para identificar el canon. Pero esto, por sí solo, no demuestra todas las afirmaciones posteriores sobre la autoridad del Magisterio.""", """The historical formation of the canon is more complex than claiming that a single council decided everything. There were debates and regional variations.

Catholic reasoning emphasizes the need for an ecclesial tradition to identify the canon. But this by itself does not demonstrate all later claims about the authority of the Magisterium.""", """A formação histórica do cânon é mais complexa do que afirmar que um único concílio decidiu tudo. Houve debates e variações regionais.

O raciocínio católico destaca a necessidade de uma tradição eclesial para identificar o cânon. Mas isso, por si só, não demonstra todas as afirmações posteriores sobre a autoridade do Magistério.""")
    ),
    ApologeticsQuestion(
        number = 13,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué los católicos tienen 73 libros bíblicos y muchos protestantes 66?""", """Why do Catholics have 73 biblical books and many Protestants 66?""", """Por que os católicos têm 73 livros bíblicos e muitos protestantes 66?"""),
        answer = LocalizedText("""La diferencia está en siete libros del Antiguo Testamento —Tobías, Judit, Sabiduría, Eclesiástico, Baruc y los dos Macabeos—, junto con determinados pasajes de Ester y Daniel.

La tradición católica reconoce estos textos como deuterocanónicos. Formaban parte de tradiciones bíblicas utilizadas por numerosos cristianos antiguos y fueron recibidos en el canon católico.""", """The difference lies in seven Old Testament books — Tobit, Judith, Wisdom, Sirach, Baruch and the two books of Maccabees — together with certain passages in Esther and Daniel.

Catholic tradition recognizes these texts as deuterocanonical. They were part of biblical traditions used by many ancient Christians and were received into the Catholic canon.""", """A diferença está em sete livros do Antigo Testamento — Tobias, Judite, Sabedoria, Eclesiástico, Baruque e os dois Macabeus —, junto com trechos determinados de Ester e Daniel.

A tradição católica reconhece esses textos como deuterocanônicos. Faziam parte de tradições bíblicas utilizadas por numerosos cristãos antigos e foram recebidos no cânon católico."""),
        foundation = LocalizedText("""Catecismo, 120; referencias históricas a los concilios de Hipona y Cartago, y a las definiciones de Florencia y Trento.""", """Catechism, 120; historical references to the councils of Hippo and Carthage, and to the definitions of Florence and Trent.""", """Catecismo, 120; referências históricas aos concílios de Hipona e Cartago e às definições de Florença e Trento."""),
        clarification = LocalizedText(""": No es históricamente preciso decir que todos los judíos del siglo I utilizaban exactamente el mismo canon, ni que todos los primeros cristianos aceptaban sin discusión los mismos libros.

La Reforma favoreció un Antiguo Testamento basado en el canon hebreo rabínico. La controversia requiere estudiar la recepción histórica de los libros, no solamente contar cuántos aparecen en cada Biblia.""", """It is not historically accurate to say that all Jews of the first century used exactly the same canon, nor that all early Christians accepted the same books without discussion.

The Reformation favored an Old Testament based on the rabbinic Hebrew canon. The controversy requires studying the historical reception of the books, not only counting how many appear in each Bible.""", """Não é historicamente preciso dizer que todos os judeus do século I utilizavam exatamente o mesmo cânon, nem que todos os primeiros cristãos aceitavam sem discussão os mesmos livros.

A Reforma favoreceu um Antigo Testamento baseado no cânon hebraico rabínico. A controvérsia requer estudar a recepção histórica dos livros, não somente contar quantos aparecem em cada Bíblia.""")
    ),
    ApologeticsQuestion(
        number = 14,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""Si la Biblia es inspirada, ¿por qué contiene relatos aparentemente contradictorios?""", """If the Bible is inspired, why does it contain apparently contradictory accounts?""", """Se a Bíblia é inspirada, por que contém relatos aparentemente contraditórios?"""),
        answer = LocalizedText("""La inspiración bíblica no significa que todos sus libros utilicen los métodos de una crónica moderna.

La Escritura incluye poesía, legislación, genealogía, narración histórica, parábolas, profecía y literatura apocalíptica.

Para comprender lo que un autor afirma, primero debemos determinar qué pretendía comunicar y mediante qué género literario.

La Iglesia enseña que la Escritura comunica sin error la verdad que Dios quiso consignar para nuestra salvación.""", """Biblical inspiration does not mean that all its books use the methods of a modern chronicle.

Scripture includes poetry, legislation, genealogy, historical narration, parables, prophecy and apocalyptic literature.

To understand what an author affirms, we must first determine what he intended to communicate and through what literary genre.

The Church teaches that Scripture communicates without error the truth God intended to consign for our salvation.""", """A inspiração bíblica não significa que todos os seus livros utilizem os métodos de uma crônica moderna.

A Escritura inclui poesia, legislação, genealogia, narração histórica, parábolas, profecia e literatura apocalíptica.

Para compreender o que um autor afirma, primeiro devemos determinar o que pretendia comunicar e mediante que gênero literário.

A Igreja ensina que a Escritura comunica sem erro a verdade que Deus quis consignar para nossa salvação."""),
        foundation = LocalizedText("""Lucas 1,1-4; 2 Timoteo 3,16; Catecismo, 105-119.""", """Luke 1:1-4; 2 Timothy 3:16; Catechism, 105-119.""", """Lucas 1,1-4; 2 Timóteo 3,16; Catecismo, 105-119."""),
        clarification = LocalizedText(""": Algunas discrepancias entre relatos bíblicos son difíciles de armonizar. No debemos inventar explicaciones para hacer desaparecer cada diferencia.

La Pontificia Comisión Bíblica insiste en estudiar los textos según sus géneros y contextos históricos.""", """Some discrepancies between biblical accounts are difficult to harmonize. We must not invent explanations to make every difference disappear.

The Pontifical Biblical Commission insists on studying texts according to their genres and historical contexts.""", """Algumas discrepâncias entre relatos bíblicos são difíceis de harmonizar. Não devemos inventar explicações para fazer desaparecer cada diferença.

A Pontifícia Comissão Bíblica insiste em estudar os textos segundo seus gêneros e contextos históricos.""")
    ),
    ApologeticsQuestion(
        number = 15,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Cómo encajan Adán y Eva con la evolución humana?""", """How do Adam and Eve fit with human evolution?""", """Como se encaixam Adão e Eva com a evolução humana?"""),
        answer = LocalizedText("""La evolución biológica y la creación divina responden a preguntas diferentes.

La ciencia investiga los procesos naturales mediante los cuales aparecieron y se diversificaron los seres vivos. La teología pregunta por el fundamento último de la existencia, la dignidad humana y nuestra relación con Dios.

El relato del Génesis enseña, entre otras cosas, que el ser humano depende de Dios, posee dignidad y está llamado a vivir en comunión con Él.""", """Biological evolution and divine creation answer different questions.

Science investigates the natural processes by which living beings appeared and diversified. Theology asks about the ultimate foundation of existence, human dignity and our relationship with God.

The Genesis account teaches, among other things, that the human being depends on God, possesses dignity and is called to live in communion with Him.""", """A evolução biológica e a criação divina respondem a perguntas diferentes.

A ciência investiga os processos naturais mediante os quais surgiram e se diversificaram os seres vivos. A teologia pergunta pelo fundamento último da existência, pela dignidade humana e nossa relação com Deus.

O relato do Gênesis ensina, entre outras coisas, que o ser humano depende de Deus, possui dignidade e é chamado a viver em comunhão com Ele."""),
        foundation = LocalizedText("""Génesis 1-3; Sabiduría 2,23-24; Romanos 5,12-21; Catecismo, 283-289 y 390.""", """Genesis 1-3; Wisdom 2:23-24; Romans 5:12-21; Catechism, 283-289 and 390.""", """Gênesis 1-3; Sabedoria 2,23-24; Romanos 5,12-21; Catecismo, 283-289 e 390."""),
        clarification = LocalizedText(""": La cuestión de los primeros seres humanos, el pecado original y el origen histórico de la humanidad sigue requiriendo una reflexión teológica cuidadosa.

La Iglesia no exige interpretar cada detalle narrativo del Génesis como un informe científico, pero tampoco reduce el pecado original a una simple metáfora sin relación con la condición humana.""", """The question of the first human beings, original sin and the historical origin of humanity still requires careful theological reflection.

The Church does not require interpreting every narrative detail of Genesis as a scientific report, but neither does it reduce original sin to a mere metaphor unrelated to the human condition.""", """A questão dos primeiros seres humanos, do pecado original e da origem histórica da humanidade continua requerendo uma reflexão teológica cuidadosa.

A Igreja não exige interpretar cada detalhe narrativo do Gênesis como um relatório científico, mas tampouco reduz o pecado original a uma simples metáfora sem relação com a condição humana.""")
    ),
    ApologeticsQuestion(
        number = 16,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué la Biblia contiene leyes sobre esclavitud, guerra y desigualdad?""", """Why does the Bible contain laws about slavery, war and inequality?""", """Por que a Bíblia contém leis sobre escravidão, guerra e desigualdade?"""),
        answer = LocalizedText("""Porque la revelación bíblica fue transmitida dentro de sociedades históricas que tenían instituciones y comportamientos que hoy reconocemos como gravemente injustos.

La existencia de una norma reguladora dentro de un texto antiguo no significa necesariamente que esa práctica represente el ideal moral definitivo.

Jesús mismo explica que determinadas disposiciones de la Ley de Moisés respondían a la dureza del corazón humano.

La lectura cristiana interpreta las normas antiguas a la luz de Cristo, de la dignidad de toda persona y del mandamiento del amor.""", """Because biblical revelation was transmitted within historical societies that had institutions and behaviors we today recognize as gravely unjust.

The existence of a regulatory norm within an ancient text does not necessarily mean that the practice represents the definitive moral ideal.

Jesus Himself explains that certain provisions of the Law of Moses responded to the hardness of the human heart.

Christian reading interprets ancient norms in the light of Christ, of the dignity of every person and of the commandment of love.""", """Porque a revelação bíblica foi transmitida dentro de sociedades históricas que tinham instituições e comportamentos que hoje reconhecemos como gravemente injustos.

A existência de uma norma reguladora dentro de um texto antigo não significa necessariamente que essa prática represente o ideal moral definitivo.

Jesus mesmo explica que determinadas disposições da Lei de Moisés respondiam à dureza do coração humano.

A leitura cristã interpreta as normas antigas à luz de Cristo, da dignidade de toda pessoa e do mandamento do amor."""),
        foundation = LocalizedText("""Mateo 19,8; Gálatas 3,28; Filemón 15-16; Mateo 22,37-40.""", """Matthew 19:8; Galatians 3:28; Philemon 15-16; Matthew 22:37-40.""", """Mateus 19,8; Gálatas 3,28; Filemon 15-16; Mateus 22,37-40."""),
        clarification = LocalizedText(""": Esto no elimina el problema de por qué los textos permitieron determinadas prácticas ni justifica que los cristianos históricos las perpetuaran.

Las injusticias bíblicas y eclesiales merecen ser examinadas, no encubiertas mediante una interpretación conveniente.""", """This does not remove the problem of why the texts allowed certain practices, nor does it justify historical Christians perpetuating them.

Biblical and ecclesial injustices deserve to be examined, not covered up by a convenient interpretation.""", """Isso não elimina o problema de por que os textos permitiram determinadas práticas nem justifica que os cristãos históricos as perpetuassem.

As injustiças bíblicas e eclesiais merecem ser examinadas, não encobertas mediante uma interpretação conveniente.""")
    ),
    ApologeticsQuestion(
        number = 17,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Quién decide cuándo la Biblia se interpreta literalmente y cuándo simbólicamente?""", """Who decides when the Bible is interpreted literally and when symbolically?""", """Quem decide quando a Bíblia se interpreta literalmente e quando simbolicamente?"""),
        answer = LocalizedText("""No debería decidirse por conveniencia doctrinal, sino atendiendo al género literario, al contexto histórico, a la intención del autor y a la unidad del conjunto bíblico.

Por ejemplo, cuando Jesús dice que es la puerta, el contexto indica una imagen. Cuando los Evangelios describen su crucifixión, presentan un acontecimiento histórico.

La tradición católica utiliza la investigación bíblica y también considera la interpretación de la Iglesia.""", """It should not be decided by doctrinal convenience, but by attending to literary genre, historical context, authorial intention and the unity of the whole Bible.

For example, when Jesus says He is the door, the context indicates an image. When the Gospels describe His crucifixion, they present a historical event.

Catholic tradition uses biblical research and also considers the interpretation of the Church.""", """Não deveria decidir-se por conveniência doutrinal, mas atendendo ao gênero literário, ao contexto histórico, à intenção do autor e à unidade do conjunto bíblico.

Por exemplo, quando Jesus diz que é a porta, o contexto indica uma imagem. Quando os Evangelhos descrevem sua crucifixão, apresentam um acontecimento histórico.

A tradição católica utiliza a pesquisa bíblica e também considera a interpretação da Igreja."""),
        foundation = LocalizedText("""Lucas 24,27; Nehemías 8,8; 2 Pedro 3,15-16; Catecismo, 109-119.""", """Luke 24:27; Nehemiah 8:8; 2 Peter 3:15-16; Catechism, 109-119.""", """Lucas 24,27; Neemias 8,8; 2 Pedro 3,15-16; Catecismo, 109-119."""),
        clarification = LocalizedText(""": No siempre es fácil decidir qué elementos son históricos o figurados. Los católicos y los protestantes pueden compartir métodos rigurosos de exégesis y llegar a conclusiones distintas.

Por eso es necesario razonar cada interpretación y no resolver las controversias simplemente afirmando que la propia lectura es evidente.""", """It is not always easy to decide which elements are historical or figurative. Catholics and Protestants can share rigorous exegetical methods and reach different conclusions.

Therefore each interpretation must be reasoned, and controversies must not be resolved by simply affirming that one's own reading is evident.""", """Nem sempre é fácil decidir quais elementos são históricos ou figurados. Católicos e protestantes podem compartilhar métodos rigorosos de exegese e chegar a conclusões distintas.

Por isso é necessário raciocinar cada interpretação e não resolver as controvérsias simplesmente afirmando que a própria leitura é evidente.""")
    ),
    ApologeticsQuestion(
        number = 18,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Podemos confiar en los Evangelios si fueron escritos décadas después de Jesús?""", """Can we trust the Gospels if they were written decades after Jesus?""", """Podemos confiar nos Evangelhos se foram escritos décadas depois de Jesus?"""),
        answer = LocalizedText("""La distancia temporal, por sí sola, no invalida un testimonio histórico antiguo.

Los Evangelios fueron compuestos dentro del movimiento cristiano primitivo, que conservaba tradiciones sobre Jesús y proclamaba su muerte y resurrección.

Además, algunas formulaciones cristianas muy tempranas aparecen en las cartas de Pablo, anteriores a los Evangelios canónicos.

El investigador debe estudiar la transmisión de esas tradiciones, la relación entre las fuentes, sus características literarias y el contexto histórico del siglo I.""", """Temporal distance by itself does not invalidate an ancient historical testimony.

The Gospels were composed within the primitive Christian movement, which preserved traditions about Jesus and proclaimed His death and resurrection.

Moreover, some very early Christian formulations appear in Paul's letters, which predate the canonical Gospels.

The researcher must study the transmission of those traditions, the relation between sources, their literary characteristics and the first-century historical context.""", """A distância temporal, por si só, não invalida um testemunho histórico antigo.

Os Evangelhos foram compostos dentro do movimento cristão primitivo, que conservava tradições sobre Jesus e proclamava sua morte e ressurreição.

Além disso, algumas formulações cristãs muito antigas aparecem nas cartas de Paulo, anteriores aos Evangelhos canônicos.

O pesquisador deve estudar a transmissão dessas tradições, a relação entre as fontes, suas características literárias e o contexto histórico do século I."""),
        foundation = LocalizedText("""Lucas 1,1-4; 1 Corintios 15,3-8; Gálatas 1,18-19.""", """Luke 1:1-4; 1 Corinthians 15:3-8; Galatians 1:18-19.""", """Lucas 1,1-4; 1 Coríntios 15,3-8; Gálatas 1,18-19."""),
        clarification = LocalizedText(""": Los Evangelios no son transcripciones neutrales. Sus autores escribieron desde la fe y con propósitos teológicos.

Eso no los descalifica automáticamente como fuentes históricas, pero exige analizarlos críticamente. La fiabilidad debe argumentarse pasaje por pasaje cuando corresponda.""", """The Gospels are not neutral transcripts. Their authors wrote from faith and with theological purposes.

That does not automatically disqualify them as historical sources, but it requires analyzing them critically. Reliability must be argued passage by passage when appropriate.""", """Os Evangelhos não são transcrições neutras. Seus autores escreveram a partir da fé e com propósitos teológicos.

Isso não os desqualifica automaticamente como fontes históricas, mas exige analisá-los criticamente. A confiabilidade deve ser argumentada trecho por trecho quando corresponder.""")
    ),
    ApologeticsQuestion(
        number = 19,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué fueron excluidos evangelios como el de Tomás o el de María Magdalena?""", """Why were gospels such as Thomas or Mary Magdalene excluded?""", """Por que foram excluídos evangelhos como o de Tomás ou o de Maria Madalena?"""),
        answer = LocalizedText("""Porque no todos los textos antiguos que llevan el nombre de un apóstol poseen el mismo origen, antigüedad, contenido o recepción en las comunidades cristianas.

El reconocimiento del canon consideró la vinculación apostólica, el uso eclesial y la conformidad con la fe recibida.

Los evangelios no canónicos son importantes para investigar la diversidad del cristianismo antiguo. Sin embargo, varios fueron compuestos después de los Evangelios canónicos, y sus atribuciones apostólicas no pueden aceptarse sin análisis.""", """Because not all ancient texts bearing an apostle's name possess the same origin, antiquity, content or reception in Christian communities.

Recognition of the canon considered apostolic connection, ecclesial use and conformity with the faith received.

Non-canonical gospels are important for investigating the diversity of ancient Christianity. However, several were composed after the canonical Gospels, and their apostolic attributions cannot be accepted without analysis.""", """Porque nem todos os textos antigos que levam o nome de um apóstolo possuem a mesma origem, antiguidade, conteúdo ou recepção nas comunidades cristãs.

O reconhecimento do cânon considerou a vinculação apostólica, o uso eclesial e a conformidade com a fé recebida.

Os evangelhos não canônicos são importantes para investigar a diversidade do cristianismo antigo. Contudo, vários foram compostos depois dos Evangelhos canônicos, e suas atribuições apostólicas não podem ser aceitas sem análise."""),
        foundation = LocalizedText("""Lucas 1,1-4; 2 Tesalonicenses 2,15; 1 Juan 4,1; Catecismo, 120.""", """Luke 1:1-4; 2 Thessalonians 2:15; 1 John 4:1; Catechism, 120.""", """Lucas 1,1-4; 2 Tessalonicenses 2,15; 1 João 4,1; Catecismo, 120."""),
        clarification = LocalizedText(""": No es correcto afirmar que todos los apócrifos son falsificaciones sin valor histórico. Tampoco hay pruebas de una conspiración única destinada a eliminar todos los textos que cuestionaban una supuesta versión oficial.

Cada escrito debe investigarse según su fecha, fuentes, transmisión y contenido.""", """It is incorrect to claim that all apocrypha are worthless forgeries. Nor is there evidence of a single conspiracy aimed at eliminating all texts that questioned a supposed official version.

Each writing must be investigated according to its date, sources, transmission and content.""", """Não é correto afirmar que todos os apócrifos são falsificações sem valor histórico. Tampouco há provas de uma conspiração única destinada a eliminar todos os textos que questionavam uma suposta versão oficial.

Cada escrito deve ser investigado segundo sua data, fontes, transmissão e conteúdo.""")
    ),
    ApologeticsQuestion(
        number = 20,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""Si la revelación no cambia, ¿por qué cambian enseñanzas y prácticas eclesiales?""", """If revelation does not change, why do ecclesial teachings and practices change?""", """Se a revelação não muda, por que mudam ensinamentos e práticas eclesiais?"""),
        answer = LocalizedText("""Es necesario distinguir entre el contenido esencial de la fe, su comprensión progresiva y las normas disciplinares que pueden modificarse.

La Iglesia católica considera que la revelación pública quedó completada en Cristo y la época apostólica, pero reconoce que la comprensión de esa revelación puede profundizarse.

El concilio de Jerusalén, narrado en Hechos 15, muestra a la primera Iglesia afrontando una cuestión nueva mediante discernimiento comunitario.""", """We must distinguish between the essential content of faith, its progressive understanding and disciplinary norms that can be changed.

The Catholic Church considers that public revelation was completed in Christ and the apostolic age, but acknowledges that understanding of that revelation can deepen.

The council of Jerusalem, narrated in Acts 15, shows the primitive Church facing a new question through communal discernment.""", """É necessário distinguir entre o conteúdo essencial da fé, sua compreensão progressiva e as normas disciplinares que podem modificar-se.

A Igreja católica considera que a revelação pública ficou completada em Cristo e na época apostólica, mas reconhece que a compreensão dessa revelação pode aprofundar-se.

O concílio de Jerusalém, narrado em Atos 15, mostra a primeira Igreja enfrentando uma questão nova mediante discernimento comunitário."""),
        foundation = LocalizedText("""Juan 16,13; Judas 3; Hechos 15,1-29; Catecismo, 66-67 y 94.""", """John 16:13; Jude 3; Acts 15:1-29; Catechism, 66-67 and 94.""", """João 16,13; Judas 3; Atos 15,1-29; Catecismo, 66-67 e 94."""),
        clarification = LocalizedText(""": La idea de desarrollo doctrinal no permite justificar cualquier cambio. Una enseñanza nueva debe guardar continuidad sustancial con la fe apostólica.

Determinar si un desarrollo conserva esa continuidad es precisamente uno de los grandes asuntos discutidos entre católicos, ortodoxos y protestantes.""", """The idea of doctrinal development does not allow justifying any change. A new teaching must preserve substantial continuity with apostolic faith.

Determining whether a development preserves that continuity is precisely one of the great matters discussed between Catholics, Orthodox and Protestants.""", """A ideia de desenvolvimento doutrinal não permite justificar qualquer mudança. Um ensino novo deve guardar continuidade substancial com a fé apostólica.

Determinar se um desenvolvimento conserva essa continuidade é precisamente um dos grandes assuntos discutidos entre católicos, ortodoxos e protestantes.""")
    ),
    ApologeticsQuestion(
        number = 21,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Dónde dice la Biblia que Dios es Trinidad? ¿No son tres dioses?""", """Where does the Bible say that God is Trinity? Are they not three gods?""", """Onde diz a Bíblia que Deus é Trindade? Não são três deuses?"""),
        answer = LocalizedText("""La palabra Trinidad no aparece en la Biblia, pero la doctrina busca explicar conjuntamente tres afirmaciones bíblicas: hay un solo Dios; el Padre es Dios; y el Hijo y el Espíritu Santo participan de la identidad divina.

Jesús ordena bautizar en el nombre —en singular— del Padre, del Hijo y del Espíritu Santo.

La fórmula doctrinal distingue entre una sola naturaleza divina y tres personas distintas. No afirma tres dioses ni que una persona sea una tercera parte de Dios.""", """The word Trinity does not appear in the Bible, but the doctrine seeks to explain together three biblical affirmations: there is one God; the Father is God; and the Son and the Holy Spirit share the divine identity.

Jesus commands baptism in the name — singular — of the Father, of the Son and of the Holy Spirit.

The doctrinal formula distinguishes between one divine nature and three distinct persons. It does not affirm three gods, nor that one person is a third part of God.""", """A palavra Trindade não aparece na Bíblia, mas a doutrina busca explicar conjuntamente três afirmações bíblicas: há um só Deus; o Pai é Deus; e o Filho e o Espírito Santo participam da identidade divina.

Jesus ordena batizar em nome — no singular — do Pai, do Filho e do Espírito Santo.

A fórmula doutrinal distingue entre uma única natureza divina e três pessoas distintas. Não afirma três deuses nem que uma pessoa seja uma terceira parte de Deus."""),
        foundation = LocalizedText("""Deuteronomio 6,4; Mateo 28,19; Juan 1,1-3; Juan 20,28; Hechos 5,3-4; 2 Corintios 13,13; Catecismo, 232-267.""", """Deuteronomy 6:4; Matthew 28:19; John 1:1-3; John 20:28; Acts 5:3-4; 2 Corinthians 13:13; Catechism, 232-267.""", """Deuteronômio 6,4; Mateus 28,19; João 1,1-3; João 20,28; Atos 5,3-4; 2 Coríntios 13,13; Catecismo, 232-267."""),
        clarification = LocalizedText(""": Ningún versículo contiene por sí solo toda la formulación posterior del dogma. Esta surge de la interpretación conjunta de las Escrituras y del desarrollo teológico de los primeros siglos.

La Trinidad tampoco puede demostrarse mediante una analogía matemática sencilla. Su coherencia depende de distinguir los conceptos de naturaleza y persona.""", """No verse alone contains the whole later formulation of the dogma. This emerges from joint interpretation of the Scriptures and theological development in the first centuries.

The Trinity also cannot be demonstrated by a simple mathematical analogy. Its coherence depends on distinguishing the concepts of nature and person.""", """Nenhum versículo contém por si só toda a formulação posterior do dogma. Esta surge da interpretação conjunta das Escrituras e do desenvolvimento teológico dos primeiros séculos.

A Trindade tampouco pode ser demonstrada por uma analogia matemática simples. Sua coerência depende de distinguir os conceitos de natureza e pessoa.""")
    ),
    ApologeticsQuestion(
        number = 22,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús es Dios, ¿a quién rezaba y por qué decía que el Padre era mayor?""", """If Jesus is God, to whom did He pray and why did He say the Father was greater?""", """Se Jesus é Deus, a quem rezava e por que dizia que o Pai era maior?"""),
        answer = LocalizedText("""La doctrina católica afirma que Jesucristo es una persona divina que posee verdaderamente naturaleza divina y naturaleza humana.

Como hombre, Jesús vive una relación filial de obediencia, confianza y oración con el Padre. Su oración no significa que esté hablando consigo mismo: el Padre y el Hijo son personas distintas.

Cuando Jesús afirma que el Padre es mayor, la interpretación católica considera el contexto de su misión y de su condición humana.""", """Catholic doctrine affirms that Jesus Christ is a divine person who truly possesses a divine nature and a human nature.

As man, Jesus lives a filial relationship of obedience, trust and prayer with the Father. His prayer does not mean that He is speaking to Himself: the Father and the Son are distinct persons.""", """A doutrina católica afirma que Jesus Cristo é uma pessoa divina que possui verdadeiramente natureza divina e natureza humana.

Como homem, Jesus vive uma relação filial de obediência, confiança e oração com o Pai. Sua oração não significa que esteja falando consigo mesmo: o Pai e o Filho são pessoas distintas."""),
        foundation = LocalizedText("""Juan 1,1.14; Juan 14,28; Filipenses 2,5-11; Hebreos 5,7-9; Catecismo, 464-469.""", """John 1:1.14; John 14:28; Philippians 2:5-11; Hebrews 5:7-9; Catechism, 464-469.""", """João 1,1.14; João 14,28; Filipenses 2,5-11; Hebreus 5,7-9; Catecismo, 464-469."""),
        clarification = LocalizedText(""": Este texto ha sido interpretado de distintas maneras por los cristianos. La explicación trinitaria no nace de ignorarlo, sino de leerlo junto con los pasajes que atribuyen divinidad a Jesucristo.""", """This text has been interpreted in different ways by Christians. The Trinitarian explanation does not arise from ignoring it, but from reading it together with passages attributing divinity to Jesus Christ.""", """Este texto tem sido interpretado de maneiras diferentes pelos cristãos. A explicação trinitária não nasce de ignorá-lo, mas de lê-lo junto com os trechos que atribuem divindade a Jesus Cristo.""")
    ),
    ApologeticsQuestion(
        number = 23,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús es Dios, ¿cómo pudo sufrir y morir?""", """If Jesus is God, how could He suffer and die?""", """Se Jesus é Deus, como pôde sofrer e morrer?"""),
        answer = LocalizedText("""La Encarnación no significa que Dios dejara de ser Dios, sino que el Hijo eterno asumió una naturaleza humana completa.

Por eso Jesús experimentó hambre, dolor, cansancio y muerte reales.

La naturaleza divina no dejó de existir ni perdió su inmortalidad. La muerte afectó a la humanidad asumida por el Hijo: su alma humana se separó de su cuerpo, mientras su persona divina permaneció unida a ambos.""", """The Incarnation does not mean that God ceased to be God, but that the eternal Son assumed a complete human nature.

Therefore Jesus experienced real hunger, pain, tiredness and death.

The divine nature did not cease to exist nor lose its immortality. Death affected the humanity assumed by the Son: His human soul separated from His body, while His divine person remained united to both.""", """A Encarnação não significa que Deus deixou de ser Deus, mas que o Filho eterno assumiu uma natureza humana completa.

Por isso Jesus experimentou fome, dor, cansaço e morte reais.

A natureza divina não deixou de existir nem perdeu sua imortalidade. A morte afetou a humanidade assumida pelo Filho: sua alma humana se separou de seu corpo, enquanto sua pessoa divina permaneceu unida a ambos."""),
        foundation = LocalizedText("""Juan 1,14; Filipenses 2,6-8; Hebreos 2,14-18; 1 Pedro 3,18; Catecismo, 470-478 y 624-626.""", """John 1:14; Philippians 2:6-8; Hebrews 2:14-18; 1 Peter 3:18; Catechism, 470-478 and 624-626.""", """João 1,14; Filipenses 2,6-8; Hebreus 2,14-18; 1 Pedro 3,18; Catecismo, 470-478 e 624-626."""),
        clarification = LocalizedText(""": La expresión «Dios murió por nosotros» es correcta en el lenguaje cristológico porque quien murió es la persona divina del Hijo, según su naturaleza humana.

No significa que la divinidad haya dejado de existir.""", """The expression “God died for us” is correct in Christological language because the one who died is the divine person of the Son, according to His human nature.

It does not mean that divinity ceased to exist.""", """A expressão «Deus morreu por nós» é correta na linguagem cristológica porque quem morreu é a pessoa divina do Filho, segundo sua natureza humana.

Não significa que a divindade tenha deixado de existir.""")
    ),
    ApologeticsQuestion(
        number = 24,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús era omnisciente, ¿por qué parecía ignorar algunas cosas?""", """If Jesus was omniscient, why did He seem to ignore some things?""", """Se Jesus era onisciente, por que parecia ignorar algumas coisas?"""),
        answer = LocalizedText("""Porque la fe católica reconoce que Jesús tuvo una inteligencia verdaderamente humana, que se desarrolló dentro de una vida humana real.

Lucas afirma que Jesús crecía en sabiduría. El Evangelio también presenta su conocimiento singular del Padre y de su misión.

La Encarnación no fue una apariencia: Jesucristo asumió auténticamente nuestra condición.""", """Because Catholic faith recognizes that Jesus had a truly human intelligence, which developed within a real human life.

Luke affirms that Jesus grew in wisdom. The Gospel also presents His singular knowledge of the Father and His mission.

The Incarnation was not an appearance: Jesus Christ truly assumed our condition.""", """Porque a fé católica reconhece que Jesus teve uma inteligência verdadeiramente humana, que se desenvolveu dentro de uma vida humana real.

Lucas afirma que Jesus crescia em sabedoria. O Evangelho também apresenta seu conhecimento singular do Pai e de sua missão.

A Encarnação não foi uma aparência: Jesus Cristo assumiu autenticamente nossa condição."""),
        foundation = LocalizedText("""Lucas 2,52; Marcos 13,32; Juan 16,30; Colosenses 2,9; Catecismo, 471-474.""", """Luke 2:52; Mark 13:32; John 16:30; Colossians 2:9; Catechism, 471-474.""", """Lucas 2,52; Marcos 13,32; João 16,30; Colossenses 2,9; Catecismo, 471-474."""),
        clarification = LocalizedText(""": Cómo se relacionan exactamente el conocimiento humano de Jesús, su conciencia mesiánica y su divinidad es una cuestión teológica compleja.

No resulta riguroso afirmar que Marcos 13,32 es irrelevante o que Jesús simplemente fingía ignorancia. El texto debe tomarse en serio dentro de la doctrina de sus dos naturalezas.""", """How exactly Jesus' human knowledge, His messianic consciousness and His divinity relate is a complex theological question.

It is not rigorous to affirm that Mark 13:32 is irrelevant or that Jesus simply feigned ignorance. The text must be taken seriously within the doctrine of His two natures.""", """Como se relacionam exatamente o conhecimento humano de Jesus, sua consciência messiânica e sua divindade é uma questão teológica complexa.

Não é rigoroso afirmar que Marcos 13,32 é irrelevante ou que Jesus simplesmente fingia ignorância. O texto deve ser levado a sério dentro da doutrina de suas duas naturezas.""")
    ),
    ApologeticsQuestion(
        number = 25,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué Jesús tuvo que morir si Dios podía perdonarnos directamente?""", """Why did Jesus have to die if God could forgive us directly?""", """Por que Jesus teve que morrer se Deus podia nos perdoar diretamente?"""),
        answer = LocalizedText("""La explicación cristiana no es que el Padre necesitara descargar su ira sobre una víctima inocente para poder amar a la humanidad.

La cruz manifiesta la gravedad del mal y la profundidad del amor de Dios: Cristo se entrega voluntariamente para reconciliar a la humanidad con Él.

Jesús enseña que nadie le quita su vida, sino que la entrega libremente (Juan 10,18).""", """The Christian explanation is not that the Father needed to discharge His wrath on an innocent victim in order to be able to love humanity.

The cross manifests the gravity of evil and the depth of God's love: Christ gives Himself voluntarily to reconcile humanity with Him.

Jesus teaches that no one takes His life from Him, but that He gives it freely (John 10:18).""", """A explicação cristã não é que o Pai precisava descarregar sua ira sobre uma vítima inocente para poder amar a humanidade.

A cruz manifesta a gravidade do mal e a profundidade do amor de Deus: Cristo se entrega voluntariamente para reconciliar a humanidade com Ele.

Jesus ensina que ninguém lhe tira a vida, mas que a entrega livremente (João 10,18)."""),
        foundation = LocalizedText("""Juan 3,16-17; Romanos 5,6-11; 2 Corintios 5,18-21; Hebreos 9,11-15; Catecismo, 599-618.""", """John 3:16-17; Romans 5:6-11; 2 Corinthians 5:18-21; Hebrews 9:11-15; Catechism, 599-618.""", """João 3,16-17; Romanos 5,6-11; 2 Coríntios 5,18-21; Hebreus 9,11-15; Catecismo, 599-618."""),
        clarification = LocalizedText(""": Hay distintas teorías cristianas sobre cómo la muerte de Cristo realiza la redención: sacrificio, victoria sobre el mal, satisfacción, reconciliación y transformación del ser humano.

La cruz es central para la fe cristiana, pero no podemos demostrar filosóficamente que Dios estuviera absolutamente obligado a salvarnos de esta única manera.""", """There are different Christian theories about how Christ's death effects redemption: sacrifice, victory over evil, satisfaction, reconciliation and transformation of the human being.

The cross is central to Christian faith, but we cannot philosophically demonstrate that God was absolutely bound to save us only in this way.""", """Há diferentes teorias cristãs sobre como a morte de Cristo realiza a redenção: sacrifício, vitória sobre o mal, satisfação, reconciliação e transformação do ser humano.

A cruz é central para a fé cristã, mas não podemos demonstrar filosoficamente que Deus estivesse absolutamente obrigado a nos salvar somente desta maneira.""")
    ),
    ApologeticsQuestion(
        number = 26,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Qué pruebas históricas existen de la resurrección de Jesús?""", """What historical evidence exists for the resurrection of Jesus?""", """Que provas históricas existem da ressurreição de Jesus?"""),
        answer = LocalizedText("""La investigación histórica puede establecer que Jesús fue crucificado y que sus primeros seguidores proclamaron muy pronto que había resucitado y se les había aparecido.

Pablo transmite en 1 Corintios 15 una tradición cristiana anterior a su carta.

Además, el surgimiento y la expansión del movimiento cristiano requieren una explicación histórica.

La interpretación católica sostiene que la resurrección real de Jesús explica adecuadamente estos testimonios.""", """Historical research can establish that Jesus was crucified and that His first followers proclaimed very early that He had risen and appeared to them.

Paul transmits in 1 Corinthians 15 a Christian tradition earlier than his letter.

Moreover, the emergence and expansion of the Christian movement require a historical explanation.

Catholic interpretation holds that the real resurrection of Jesus adequately explains these testimonies.""", """A pesquisa histórica pode estabelecer que Jesus foi crucificado e que seus primeiros seguidores proclamaram muito cedo que ele havia ressuscitado e se lhes tinha aparecido.

Paulo transmite em 1 Coríntios 15 uma tradição cristã anterior à sua carta.

Além disso, o surgimento e a expansão do movimento cristão requerem uma explicação histórica.

A interpretação católica sustenta que a ressurreição real de Jesus explica adequadamente esses testemunhos."""),
        foundation = LocalizedText("""1 Corintios 15,3-8; Hechos 2,22-36; Lucas 24; Juan 20.""", """1 Corinthians 15:3-8; Acts 2:22-36; Luke 24; John 20.""", """1 Coríntios 15,3-8; Atos 2,22-36; Lucas 24; João 20."""),
        clarification = LocalizedText(""": La historia puede estudiar testimonios, creencias y acontecimientos accesibles a sus métodos. Demostrar una intervención sobrenatural como tal es más problemático.

Las explicaciones alternativas también deben ser examinadas, no descartadas automáticamente. La resurrección es una afirmación histórica central del cristianismo y, al mismo tiempo, un acontecimiento reconocido mediante la fe.""", """History can study testimonies, beliefs and events accessible to its methods. Demonstrating a supernatural intervention as such is more problematic.

Alternative explanations must also be examined, not automatically dismissed. The resurrection is a central historical affirmation of Christianity and, at the same time, an event recognized through faith.""", """A história pode estudar testemunhos, crenças e acontecimentos acessíveis aos seus métodos. Demonstrar uma intervenção sobrenatural como tal é mais problemático.

As explicações alternativas também devem ser examinadas, não descartadas automaticamente. A ressurreição é uma afirmação histórica central do cristianismo e, ao mesmo tempo, um acontecimento reconhecido mediante a fé.""")
    ),
    ApologeticsQuestion(
        number = 27,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús fundó una sola Iglesia, ¿por qué existen tantas denominaciones?""", """If Jesus founded one Church, why do so many denominations exist?""", """Se Jesus fundou uma única Igreja, por que existem tantas denominações?"""),
        answer = LocalizedText("""Jesús pidió la unidad de sus discípulos y los apóstoles denunciaron las divisiones.

La Iglesia católica sostiene que Cristo quiso una comunidad visible, unida en la fe, los sacramentos y la sucesión apostólica.

La división entre cristianos no demuestra que Jesús quisiera varias iglesias contradictorias, pero tampoco prueba automáticamente todas las pretensiones históricas de una denominación.""", """Jesus asked for the unity of His disciples, and the apostles denounced divisions.

The Catholic Church holds that Christ willed a visible community, united in faith, the sacraments and apostolic succession.

Division among Christians does not show that Jesus willed several contradictory churches, but neither does it automatically prove all the historical claims of a denomination.""", """Jesus pediu a unidade de seus discípulos e os apóstolos denunciaram as divisões.

A Igreja católica sustenta que Cristo quis uma comunidade visível, unida na fé, nos sacramentos e na sucessão apostólica.

A divisão entre cristãos não demonstra que Jesus quisesse várias igrejas contraditórias, mas tampouco prova automaticamente todas as pretensões históricas de uma denominação."""),
        foundation = LocalizedText("""Juan 17,20-23; Mateo 16,18; Efesios 4,4-6; 1 Corintios 1,10-13; Catecismo, 811-822.""", """John 17:20-23; Matthew 16:18; Ephesians 4:4-6; 1 Corinthians 1:10-13; Catechism, 811-822.""", """João 17,20-23; Mateus 16,18; Efésios 4,4-6; 1 Coríntios 1,10-13; Catecismo, 811-822."""),
        clarification = LocalizedText(""": La Iglesia católica reconoce elementos auténticos de santificación y verdad en otras comunidades cristianas, y considera a los cristianos bautizados no católicos como hermanos en Cristo.

La discusión sobre la Iglesia verdadera requiere investigar la continuidad apostólica, la doctrina, la historia y la autoridad, no limitarse a contar denominaciones.""", """The Catholic Church recognizes authentic elements of sanctification and truth in other Christian communities, and considers non-Catholic baptized Christians as brothers and sisters in Christ.

The discussion about the true Church requires investigating apostolic continuity, doctrine, history and authority, not merely counting denominations.""", """A Igreja católica reconhece elementos autênticos de santificação e verdade em outras comunidades cristãs e considera os cristãos batizados não católicos como irmãos em Cristo.

A discussão sobre a Igreja verdadeira requer investigar a continuidade apostólica, a doutrina, a história e a autoridade, não limitar-se a contar denominações.""")
    ),
    ApologeticsQuestion(
        number = 28,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué el Espíritu Santo es una persona y no simplemente una fuerza?""", """Why is the Holy Spirit a person and not simply a force?""", """Por que o Espírito Santo é uma pessoa e não simplesmente uma força?"""),
        answer = LocalizedText("""Porque el Nuevo Testamento le atribuye acciones personales: enseña, guía, habla, distribuye dones y puede ser entristecido.

Jesús lo presenta como otro Paráclito, enviado para acompañar a sus discípulos.

En Hechos 5,3-4, mentir al Espíritu Santo aparece relacionado con mentir a Dios.

El razonamiento católico es que estos textos, considerados conjuntamente, presentan al Espíritu como más que una energía impersonal.""", """Because the New Testament attributes personal actions to Him: He teaches, guides, speaks, distributes gifts and can be grieved.

Jesus presents Him as another Advocate, sent to accompany His disciples.

In Acts 5:3-4, lying to the Holy Spirit appears related to lying to God.

Catholic reasoning is that these texts, considered together, present the Spirit as more than an impersonal energy.""", """Porque o Novo Testamento lhe atribui ações pessoais: ensina, guia, fala, distribui dons e pode ser entristecido.

Jesus o apresenta como outro Paráclito, enviado para acompanhar seus discípulos.

Em Atos 5,3-4, mentir ao Espírito Santo aparece relacionado a mentir a Deus.

O raciocínio católico é que esses textos, considerados conjuntamente, apresentam o Espírito como mais que uma energia impessoal."""),
        foundation = LocalizedText("""Juan 14,16-17.26; Juan 16,13; Hechos 13,2; 1 Corintios 12,11; Efesios 4,30; Catecismo, 683-747.""", """John 14:16-17.26; John 16:13; Acts 13:2; 1 Corinthians 12:11; Ephesians 4:30; Catechism, 683-747.""", """João 14,16-17.26; João 16,13; Atos 13,2; 1 Coríntios 12,11; Efésios 4,30; Catecismo, 683-747."""),
        clarification = LocalizedText(""": Algunas expresiones podrían interpretarse figuradamente si estuvieran aisladas. La afirmación doctrinal procede de la lectura conjunta de las Escrituras, no de un solo verbo.""", """Some expressions could be interpreted figuratively if isolated. The doctrinal affirmation comes from joint reading of the Scriptures, not from a single verb.""", """Algumas expressões poderiam interpretar-se figuradamente se estivessem isoladas. A afirmação doutrinal procede da leitura conjunta das Escrituras, não de um único verbo.""")
    ),
    ApologeticsQuestion(
        number = 29,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Judas estaba destinado a traicionar a Jesús?""", """Was Judas destined to betray Jesus?""", """Judas estava destinado a trair Jesus?"""),
        answer = LocalizedText("""Que Jesús conociera la traición no implica necesariamente que la causara o que obligara a Judas a realizarla.

Los Evangelios presentan la acción de Judas como moralmente responsable.

La tradición cristiana distingue entre el conocimiento providente de Dios y la libertad de las decisiones humanas.""", """That Jesus knew the betrayal does not necessarily mean He caused it or forced Judas to carry it out.

The Gospels present Judas' action as morally responsible.

Christian tradition distinguishes between God's provident knowledge and the freedom of human decisions.""", """Que Jesus conhecesse a traição não implica necessariamente que a tenha causado ou que tenha obrigado Judas a realizá-la.

Os Evangelhos apresentam a ação de Judas como moralmente responsável.

A tradição cristã distingue entre o conhecimento providente de Deus e a liberdade das decisões humanas."""),
        foundation = LocalizedText("""Mateo 26,14-25; Lucas 22,3-6; Juan 13,21-30; Hechos 1,16-20.""", """Matthew 26:14-25; Luke 22:3-6; John 13:21-30; Acts 1:16-20.""", """Mateus 26,14-25; Lucas 22,3-6; João 13,21-30; Atos 1,16-20."""),
        clarification = LocalizedText(""": Los textos también relacionan la traición con el cumplimiento de las Escrituras. Cómo se armonizan exactamente profecía, providencia y libertad constituye una cuestión filosófica compleja.

Además, la Iglesia no ha definido que Judas se encuentre en el infierno. Su destino eterno no debe afirmarse como si lo conociéramos.""", """The texts also relate the betrayal to the fulfillment of the Scriptures. How exactly prophecy, providence and freedom are harmonized is a complex philosophical question.

Moreover, the Church has not defined that Judas is in hell. His eternal destiny must not be stated as if we knew it.""", """Os textos também relacionam a traição com o cumprimento das Escrituras. Como se harmonizam exatamente profecia, providência e liberdade constitui uma questão filosófica complexa.

Além disso, a Igreja não definiu que Judas se encontre no inferno. Seu destino eterno não deve ser afirmado como se o conhecêssemos.""")
    ),
    ApologeticsQuestion(
        number = 30,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué Jesús no ha regresado después de dos mil años?""", """Why has Jesus not returned after two thousand years?""", """Por que Jesus não voltou depois de dois mil anos?"""),
        answer = LocalizedText("""El Nuevo Testamento contiene una fuerte expectativa del retorno de Cristo, pero también advierte que no corresponde a los discípulos conocer los tiempos.

La esperanza cristiana no depende de que podamos calcular una fecha.

La Iglesia interpreta la historia como el tiempo de misión, conversión y espera de la consumación definitiva.""", """The New Testament contains a strong expectation of Christ's return, but it also warns that it is not for the disciples to know the times.

Christian hope does not depend on our being able to calculate a date.

The Church interprets history as the time of mission, conversion and expectation of the final consummation.""", """O Novo Testamento contém uma forte expectativa do retorno de Cristo, mas também adverte que não corresponde aos discípulos conhecer os tempos.

A esperança cristã não depende de que possamos calcular uma data.

A Igreja interpreta a história como o tempo de missão, conversão e espera da consumação definitiva."""),
        foundation = LocalizedText("""Mateo 24,36; Hechos 1,6-11; 2 Pedro 3,8-9; Catecismo, 668-682.""", """Matthew 24:36; Acts 1:6-11; 2 Peter 3:8-9; Catechism, 668-682.""", """Mateus 24,36; Atos 1,6-11; 2 Pedro 3,8-9; Catecismo, 668-682."""),
        clarification = LocalizedText(""": Es legítimo investigar las expectativas escatológicas de las primeras comunidades cristianas. Algunos textos reflejan la impresión de que el cumplimiento estaba próximo.

La respuesta católica distingue entre esa expectativa histórica y la afirmación doctrinal de que Cristo volverá. No existe una demostración histórica de que el acontecimiento futuro ocurrirá.""", """It is legitimate to investigate the eschatological expectations of the first Christian communities. Some texts reflect the impression that fulfillment was near.

The Catholic answer distinguishes between that historical expectation and the doctrinal affirmation that Christ will return. There is no historical demonstration that the future event will occur.""", """É legítimo investigar as expectativas escatológicas das primeiras comunidades cristãs. Alguns textos refletem a impressão de que o cumprimento estava próximo.

A resposta católica distingue entre essa expectativa histórica e a afirmação doutrinal de que Cristo voltará. Não existe uma demonstração histórica de que o acontecimento futuro ocorrerá.""")
    ),
    ApologeticsQuestion(
        number = 31,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué rezar a María y a los santos si podemos dirigirnos directamente a Dios?""", """Why pray to Mary and the saints if we can address God directly?""", """Por que rezar a Maria e aos santos se podemos nos dirigir diretamente a Deus?"""),
        answer = LocalizedText("""Los católicos podemos y debemos dirigirnos directamente a Dios.

Pedir la intercesión de un santo no significa necesitar su autorización para acercarnos a Cristo. Significa solicitar su oración, de manera semejante a cuando pedimos a otro cristiano que rece por nosotros.

San Pablo pide a otros cristianos que oren por él, a pesar de reconocer a Cristo como único Mediador.

La interpretación católica considera que la comunión de los creyentes en Cristo no termina con la muerte.""", """Catholics can and must address God directly.

Asking a saint's intercession does not mean needing their authorization to approach Christ. It means asking for their prayer, similarly to when we ask another Christian to pray for us.

Saint Paul asks other Christians to pray for him, despite recognizing Christ as the sole Mediator.

Catholic interpretation considers that the communion of believers in Christ does not end with death.""", """Os católicos podemos e devemos nos dirigir diretamente a Deus.

Pedir a intercessão de um santo não significa precisar de sua autorização para nos aproximarmos de Cristo. Significa solicitar sua oração, de maneira semelhantes a quando pedimos a outro cristão que reze por nós.

São Paulo pede a outros cristãos que orem por ele, apesar de reconhecer a Cristo como único Mediador.

A interpretação católica considera que a comunhão dos crentes em Cristo não termina com a morte."""),
        foundation = LocalizedText("""1 Timoteo 2,1-5; Santiago 5,16; Apocalipsis 5,8; Hebreos 12,1; Catecismo, 954-959.""", """1 Timothy 2:1-5; James 5:16; Revelation 5:8; Hebrews 12:1; Catechism, 954-959.""", """1 Timóteo 2,1-5; Tiago 5,16; Apocalipse 5,8; Hebreus 12,1; Catecismo, 954-959."""),
        clarification = LocalizedText(""": Ningún texto bíblico ordena explícitamente dirigirse a los santos fallecidos por su nombre. La práctica se fundamenta en una interpretación de la comunión de los santos y en la Tradición.

Por eso debemos presentarla como una doctrina católica con fundamentos, no como una orden bíblica textual indiscutible.""", """No biblical text explicitly commands addressing deceased saints by name. The practice is founded on an interpretation of the communion of saints and on Tradition.

Therefore we must present it as a Catholic doctrine with foundations, not as an indisputable textual biblical command.""", """Nenhum texto bíblico ordena explicitamente dirigir-se aos santos falecidos pelo nome. A prática se fundamenta em uma interpretação da comunhão dos santos e na Tradição.

Por isso devemos apresentá-la como uma doutrina católica com fundamentos, não como uma ordem bíblica textual indiscutível.""")
    ),
    ApologeticsQuestion(
        number = 32,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿No es idolatría rezar delante de una estatua?""", """Is praying before a statue idolatry?""", """Não é idolatria rezar diante de uma estátua?"""),
        answer = LocalizedText("""La idolatría consiste en tratar como Dios a algo que no es Dios.

La Biblia condena expresamente los ídolos, pero también describe imágenes religiosas realizadas por mandato divino, como los querubines del Arca de la Alianza.

Por tanto, la prohibición bíblica no puede interpretarse como rechazo absoluto de toda representación religiosa. La cuestión decisiva es qué culto se le ofrece.

La teología católica distingue entre adoración, reservada exclusivamente a Dios, y veneración de los santos.""", """Idolatry consists in treating as God something that is not God.

The Bible expressly condemns idols, but it also describes religious images made by divine command, such as the cherubim of the Ark of the Covenant.

Therefore the biblical prohibition cannot be interpreted as an absolute rejection of every religious representation. The decisive question is what worship is offered to it.

Catholic theology distinguishes between adoration, reserved exclusively to God, and veneration of the saints.""", """A idolatria consiste em tratar como Deus algo que não é Deus.

A Bíblia condena expressamente os ídolos, mas também descreve imagens religiosas feitas por mandato divino, como os querubins da Arca da Aliança.

Portanto, a proibição bíblica não pode interpretar-se como rejeição absoluta de toda representação religiosa. A questão decisiva é que culto é oferecido a ela.

A teologia católica distingue entre adoração, reservada exclusivamente a Deus, e veneração dos santos."""),
        foundation = LocalizedText("""Éxodo 20,3-5; Éxodo 25,18-22; Números 21,8-9; 1 Reyes 6,23-29; Catecismo, 2112-2114 y 2131-2132.""", """Exodus 20:3-5; Exodus 25:18-22; Numbers 21:8-9; 1 Kings 6:23-29; Catechism, 2112-2114 and 2131-2132.""", """Êxodo 20,3-5; Êxodo 25,18-22; Números 21,8-9; 1 Reis 6,23-29; Catecismo, 2112-2114 e 2131-2132."""),
        clarification = LocalizedText(""": Una persona sí puede caer en idolatría si atribuye a una imagen poder divino autónomo o la trata como un dios.

No basta tener una estatua católica para que cualquier comportamiento ante ella sea automáticamente correcto.""", """A person can indeed fall into idolatry if they attribute autonomous divine power to an image or treat it as a god.

It is not enough to have a Catholic statue for any behavior before it to be automatically correct.""", """Uma pessoa pode, sim, cair em idolatria se atribuir a uma imagem poder divino autônomo ou tratá-la como um deus.

Não basta ter uma estátua católica para que qualquer comportamento diante dela seja automaticamente correto.""")
    ),
    ApologeticsQuestion(
        number = 33,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué existen tantas vírgenes?""", """Why are there so many Virgin Marys?""", """Por que existem tantas virgens?"""),
        answer = LocalizedText("""Porque no existen múltiples vírgenes María.

Nuestra Señora de Guadalupe, de Fátima, de Lourdes, del Carmen o de Aparecida son advocaciones de la misma María, madre de Jesús.

Las advocaciones reciben nombres relacionados con lugares, acontecimientos, títulos espirituales o tradiciones devocionales.

Podemos compararlo con los distintos títulos que una misma persona recibe según la relación o el contexto en que se la recuerda.""", """Because there are not multiple Virgin Marys.

Our Lady of Guadalupe, of Fatima, of Lourdes, of Mount Carmel or of Aparecida are titles of the same Mary, mother of Jesus.

Titles receive names related to places, events, spiritual titles or devotional traditions.

We can compare it with the different titles the same person receives according to the relationship or context in which they are remembered.""", """Porque não existem múltiplas virgens Maria.

Nossa Senhora de Guadalupe, de Fátima, de Lourdes, do Carmo ou de Aparecida são invocações da mesma Maria, mãe de Jesus.

As invocações recebem nomes relacionados com lugares, acontecimentos, títulos espirituais ou tradições devocionais.

Podemos compará-lo com os diferentes títulos que uma mesma pessoa recebe segundo a relação ou o contexto em que é lembrada."""),
        foundation = LocalizedText("""Lucas 1,26-56; Juan 19,25-27; Catecismo, 963-975.""", """Luke 1:26-56; John 19:25-27; Catechism, 963-975.""", """Lucas 1,26-56; João 19,25-27; Catecismo, 963-975."""),
        clarification = LocalizedText(""": No todas las advocaciones implican apariciones sobrenaturales reconocidas.

El hecho de que una devoción esté permitida o sea culturalmente importante no convierte automáticamente todos los relatos asociados a ella en hechos históricos demostrados.""", """Not all titles imply recognized supernatural apparitions.

The fact that a devotion is permitted or culturally important does not automatically turn every account associated with it into demonstrated historical fact.""", """Nem todas as invocações implicam aparições sobrenaturais reconhecidas.

O fato de uma devoção ser permitida ou culturalmente importante não converte automaticamente todos os relatos associados a ela em fatos históricos demonstrados.""")
    ),
    ApologeticsQuestion(
        number = 34,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿María es divina? ¿Por qué se la llama Madre de Dios o Reina del Cielo?""", """Is Mary divine? Why is she called Mother of God or Queen of Heaven?""", """Maria é divina? Por que se a chama Mãe de Deus ou Rainha do Céu?"""),
        answer = LocalizedText("""María no es Dios. Es una criatura humana.

Se la llama Madre de Dios porque el hijo que concibió y dio a luz es Jesucristo, una persona divina verdaderamente encarnada.

El título no significa que María haya creado la divinidad ni que existiera antes de Dios.

El título de Reina se interpreta en relación con el reinado mesiánico de Cristo y con las imágenes bíblicas de la madre del rey.""", """Mary is not God. She is a human creature.

She is called Mother of God because the child she conceived and bore is Jesus Christ, a truly incarnate divine person.

The title does not mean that Mary created divinity or existed before God.

The title of Queen is interpreted in relation to Christ's messianic reign and to biblical images of the king's mother.""", """Maria não é Deus. É uma criatura humana.

Chama-se-lhe Mãe de Deus porque o filho que concebeu e deu à luz é Jesus Cristo, uma pessoa divina verdadeiramente encarnada.

O título não significa que Maria tenha criado a divindade nem que existisse antes de Deus.

O título de Rainha interpreta-se em relação com o reinado messiânico de Cristo e com as imagens bíblicas da mãe do rei."""),
        foundation = LocalizedText("""Lucas 1,43; Juan 1,1.14; Gálatas 4,4; Apocalipsis 12; Catecismo, 495 y 963-975.""", """Luke 1:43; John 1:1.14; Galatians 4:4; Revelation 12; Catechism, 495 and 963-975.""", """Lucas 1,43; João 1,1.14; Gálatas 4,4; Apocalipse 12; Catecismo, 495 e 963-975."""),
        clarification = LocalizedText(""": Los fundamentos de los títulos marianos no poseen todos la misma fuerza textual. Por ejemplo, Lucas 1,43 es especialmente importante para Madre de Dios, mientras que la interpretación de Apocalipsis 12 es más discutida.

La adoración de María sería contraria a la doctrina católica.""", """The foundations of Marian titles do not all have the same textual force. For example, Luke 1:43 is especially important for Mother of God, while the interpretation of Revelation 12 is more debated.

Adoration of Mary would be contrary to Catholic doctrine.""", """Os fundamentos dos títulos marianos não possuem todos a mesma força textual. Por exemplo, Lucas 1,43 é especialmente importante para Mãe de Deus, enquanto a interpretação de Apocalipse 12 é mais discutida.

A adoração de Maria seria contrária à doutrina católica.""")
    ),
    ApologeticsQuestion(
        number = 35,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Dónde dice la Biblia que María fue concebida sin pecado original?""", """Where does the Bible say that Mary was conceived without original sin?""", """Onde a Bíblia diz que Maria foi concebida sem pecado original?"""),
        answer = LocalizedText("""La Biblia no contiene una frase que afirme literalmente la Inmaculada Concepción.

La doctrina católica se fundamenta principalmente en la comprensión de la gracia singular concedida a María y en su papel en la historia de la salvación.

El argumento teológico sostiene que Dios pudo aplicar anticipadamente a María los frutos de la redención de Cristo.

Esto no significa que María no necesitara ser salvada. Según el dogma, fue redimida de manera singular por los méritos de Jesucristo.""", """The Bible does not contain a phrase that literally affirms the Immaculate Conception.

Catholic doctrine is founded mainly on the understanding of the singular grace granted to Mary and her role in salvation history.

The theological argument holds that God could have applied the fruits of Christ's redemption to Mary in anticipation.

This does not mean that Mary did not need to be saved. According to the dogma, she was redeemed in a singular way through the merits of Jesus Christ.""", """A Bíblia não contém uma frase que afirme literalmente a Imaculada Conceição.

A doutrina católica funda-se principalmente na compreensão da graça singular concedida a Maria e em seu papel na história da salvação.

O argumento teológico sustenta que Deus pôde aplicar antecipadamente a Maria os frutos da redenção de Cristo.

Isso não significa que Maria não precisasse ser salva. Segundo o dogma, ela foi redimida de maneira singular pelos méritos de Jesus Cristo."""),
        foundation = LocalizedText("""Lucas 1,28; Génesis 3,15; Efesios 1,4; Catecismo, 490-493.""", """Luke 1:28; Genesis 3:15; Ephesians 1:4; Catechism, 490-493.""", """Lucas 1,28; Gênesis 3,15; Efésios 1,4; Catecismo, 490-493."""),
        clarification = LocalizedText(""": Lucas 1,28 no demuestra por sí solo toda la doctrina de la concepción sin pecado original.

Es un dogma definido en 1854, sostenido desde la Escritura interpretada a la luz de la Tradición, no una enseñanza que pueda probarse mediante una cita bíblica inequívoca.""", """Luke 1:28 does not by itself demonstrate the whole doctrine of conception without original sin.

It is a dogma defined in 1854, sustained from Scripture interpreted in the light of Tradition, not a teaching that can be proved through an unequivocal biblical citation.""", """Lucas 1,28 não demonstra por si só toda a doutrina da concepção sem pecado original.

É um dogma definido em 1854, sustentado pela Escritura interpretada à luz da Tradição, e não um ensino que possa ser provado por meio de uma citação bíblica inequívoca.""")
    ),
    ApologeticsQuestion(
        number = 36,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""Si Jesús tenía hermanos, ¿cómo pudo María ser siempre virgen?""", """If Jesus had brothers, how could Mary remain a virgin?""", """Se Jesus tinha irmãos, como Maria pôde ser sempre virgem?"""),
        answer = LocalizedText("""Los términos utilizados para hablar de los hermanos de Jesús pueden designar relaciones familiares cuya extensión depende del contexto lingüístico y cultural.

La interpretación católica entiende que esas referencias no obligan a concluir que María tuvo otros hijos biológicos.

Además, la entrega de María al discípulo amado en Juan 19 ha sido interpretada tradicionalmente como coherente con la ausencia de otros hijos suyos.""", """The terms used to speak of Jesus' brothers may designate family relationships whose extent depends on the linguistic and cultural context.

The Catholic interpretation understands that such references do not compel concluding that Mary had other biological children.

Moreover, the entrusting of Mary to the beloved disciple in John 19 has traditionally been interpreted as consistent with the absence of other children of hers.""", """Os termos utilizados para falar dos irmãos de Jesus podem designar relações familiares cuja extensão depende do contexto linguístico e cultural.

A interpretação católica entende que essas referências não obrigam a concluir que Maria teve outros filhos biológicos.

Além disso, a entrega de Maria ao discípulo amado em João 19 foi interpretada tradicionalmente como coerente com a ausência de outros filhos seus."""),
        foundation = LocalizedText("""Marcos 6,3; Mateo 13,55-56; Juan 19,26-27; Catecismo, 499-501.""", """Mark 6:3; Matthew 13:55-56; John 19:26-27; Catechism, 499-501.""", """Marcos 6,3; Mateus 13,55-56; João 19,26-27; Catecismo, 499-501."""),
        clarification = LocalizedText(""": Este último argumento no es concluyente por sí solo. Y el término griego adelphoi normalmente significa hermanos, aunque su uso y los contextos de parentesco permiten un debate interpretativo.

La virginidad perpetua pertenece a la enseñanza católica sostenida también por la Tradición antigua. No debe presentarse como una conclusión lingüística que todos los especialistas necesariamente aceptan.""", """That last argument is not conclusive by itself. And the Greek term adelphoi normally means brothers, although its use and the contexts of kinship allow an interpretive debate.

Perpetual virginity belongs to Catholic teaching also sustained by ancient Tradition. It must not be presented as a linguistic conclusion that all specialists necessarily accept.""", """Esse último argumento não é concluyente por si só. E o termo grego adelphoi normalmente significa irmãos, embora seu uso e os contextos de parentesco permitam um debate interpretativo.

A virgindade perpétua pertence ao ensino católico sustentado também pela antiga Tradição. Não deve ser apresentada como uma conclusão linguística que todos os especialistas necessariamente aceitam.""")
    ),
    ApologeticsQuestion(
        number = 37,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Dónde aparece en la Biblia la Asunción de María?""", """Where does the Assumption of Mary appear in the Bible?""", """Onde aparece na Bíblia a Assunção de Maria?"""),
        answer = LocalizedText("""Ningún libro bíblico describe explícitamente el acontecimiento de la Asunción.

La Iglesia lo comprende como la participación singular de María en la victoria de Cristo sobre la muerte y como anticipación de la resurrección prometida a los creyentes.

La doctrina fue definida solemnemente en 1950, aunque tiene antecedentes devocionales y litúrgicos mucho más antiguos.""", """No biblical book explicitly describes the event of the Assumption.

The Church understands it as Mary's singular participation in Christ's victory over death and as an anticipation of the resurrection promised to believers.

The doctrine was solemnly defined in 1950, although it has much older devotional and liturgical antecedents.""", """Nenhum livro bíblico descreve explicitamente o acontecimento da Assunção.

A Igreja a compreende como a participação singular de Maria na vitória de Cristo sobre a morte e como antecipação da ressurreição prometida aos crentes.

A doutrina foi definida solenemente em 1950, embora tenha antecedentes devocionais e litúrgicos muito mais antigos."""),
        foundation = LocalizedText("""1 Corintios 15,20-23; Apocalipsis 12,1; Lucas 1,28; Catecismo, 966.""", """1 Corinthians 15:20-23; Revelation 12:1; Luke 1:28; Catechism, 966.""", """1 Coríntios 15,20-23; Apocalipse 12,1; Lucas 1,28; Catecismo, 966."""),
        clarification = LocalizedText(""": Apocalipsis 12 admite distintas interpretaciones, entre ellas las relacionadas con Israel, la Iglesia y María.

No constituye por sí solo una narración histórica de la Asunción.

Este dogma depende de la recepción de la Tradición y de la autoridad doctrinal de la Iglesia; un cristiano que acepte exclusivamente afirmaciones explícitas de la Escritura probablemente no considerará suficiente esta argumentación.""", """Revelation 12 admits different interpretations, including those related to Israel, the Church and Mary.

It does not by itself constitute a historical narrative of the Assumption.

This dogma depends on receiving Tradition and on the doctrinal authority of the Church; a Christian who accepts only explicit affirmations of Scripture will probably not consider this argument sufficient.""", """Apocalipse 12 admite distintas interpretações, entre elas as relacionadas com Israel, a Igreja e Maria.

Não constitui por si só uma narração histórica da Assunção.

Este dogma depende da recepção da Tradição e da autoridade doutrinal da Igreja; um cristão que aceita exclusivamente afirmações explícitas da Escritura provavelmente não considerará suficiente essa argumentação.""")
    ),
    ApologeticsQuestion(
        number = 38,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Cómo pueden los santos escuchar millones de oraciones?""", """How can the saints hear millions of prayers?""", """Como os santos podem escutar milhões de orações?"""),
        answer = LocalizedText("""La doctrina católica no enseña que los santos sean omniscientes ni que posean naturalmente las capacidades de Dios.

La explicación teológica sostiene que quienes participan de la gloria divina pueden conocer aquello que Dios les permite conocer.

La clave es distinguir entre poseer conocimiento infinito por naturaleza y recibir un conocimiento como don divino.""", """Catholic doctrine does not teach that the saints are omniscient or possess God's capacities by nature.

The theological explanation holds that those who share in divine glory can know what God permits them to know.

The key is to distinguish between possessing infinite knowledge by nature and receiving knowledge as a divine gift.""", """A doutrina católica não ensina que os santos sejam oniscientes nem que possuam naturalmente as capacidades de Deus.

A explicação teológica sustenta que quem participa da glória divina pode conhecer aquilo que Deus lhe permite conhecer.

A chave é distinguir entre possuir conhecimento infinito por natureza e receber um conhecimento como dom divino."""),
        foundation = LocalizedText("""Lucas 15,7; Apocalipsis 5,8; Apocalipsis 8,3-4; 1 Corintios 13,12; Catecismo, 954-956.""", """Luke 15:7; Revelation 5:8; Revelation 8:3-4; 1 Corinthians 13:12; Catechism, 954-956.""", """Lucas 15,7; Apocalipse 5,8; Apocalipse 8,3-4; 1 Coríntios 13,12; Catecismo, 954-956."""),
        clarification = LocalizedText(""": La Biblia no explica el mecanismo mediante el cual un santo conocería peticiones particulares formuladas desde distintos lugares.

Por tanto, no debemos describir ese mecanismo como un hecho demostrado.

La Iglesia afirma la intercesión de los santos, pero no ofrece una explicación física de cómo se transmite cada petición.""", """The Bible does not explain the mechanism by which a saint would know particular petitions made from different places.

Therefore we must not describe that mechanism as a demonstrated fact.

The Church affirms the intercession of the saints, but it offers no physical explanation of how each petition is transmitted.""", """A Bíblia não explica o mecanismo mediante o qual um santo conheceria petições particolari formuladas de lugares distintos.

Portanto, não devemos descrever esse mecanismo como um fato demonstrado.

A Igreja afirma a intercessão dos santos, mas não oferece uma explicação física de como se transmite cada pedido.""")
    ),
    ApologeticsQuestion(
        number = 39,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué venerar reliquias y objetos de los santos? ¿No es superstición?""", """Why venerate relics and objects of the saints? Is it not superstition?""", """Por que venerar relíquias e objetos dos santos? Não é superstição?"""),
        answer = LocalizedText("""La Biblia contiene relatos donde Dios obra mediante elementos materiales relacionados con sus servidores.

Por ejemplo, se relata una curación vinculada a los huesos de Eliseo y otras relacionadas con objetos que habían tocado a Pablo.

Estos relatos muestran que la intervención divina no queda necesariamente excluida por el uso de signos materiales.

La veneración católica reconoce en las reliquias un vínculo con personas cuya vida estuvo dedicada a Dios.""", """The Bible contains accounts where God acts through material elements related to His servants.

For example, a healing linked to the bones of Elisha is related, and others related to objects that had touched Paul.

These accounts show that divine intervention is not necessarily excluded by the use of material signs.

Catholic veneration recognizes in relics a bond with people whose lives were dedicated to God.""", """A Bíblia contém relatos em que Deus atua mediante elementos materiais relacionados com seus servidores.

Por exemplo, relata-se uma cura vinculada aos ossos de Eliseu e outras relacionadas com objetos que haviam tocado Paulo.

Esses relatos mostram que a intervenção divina não fica necessariamente excluída pelo uso de sinais materiais.

A veneração católica reconhece nas relíquias um vínculo com pessoas cuja vida esteve dedicada a Deus."""),
        foundation = LocalizedText("""2 Reyes 13,20-21; Hechos 19,11-12; Marcos 5,27-34; Catecismo, 1674-1676 y 2110-2111.""", """2 Kings 13:20-21; Acts 19:11-12; Mark 5:27-34; Catechism, 1674-1676 and 2110-2111.""", """2 Reis 13,20-21; Atos 19,11-12; Marcos 5,27-34; Catecismo, 1674-1676 e 2110-2111."""),
        clarification = LocalizedText(""": Un objeto no posee poder mágico por ser una reliquia.

La autenticidad histórica de cada reliquia también debe examinarse. Vender falsas reliquias, atribuirles propiedades garantizadas o explotarlas económicamente constituye un abuso, no una consecuencia legítima de la fe.""", """An object does not possess magical power because it is a relic.

The historical authenticity of each relic must also be examined. Selling false relics, attributing guaranteed properties to them or exploiting them economically constitutes abuse, not a legitimate consequence of faith.""", """Um objeto não possui poder mágico por ser uma relíquia.

A autenticidade histórica de cada relíquia também deve ser examinada. Vender falsas relíquias, atribuir-lhes propriedades garantidas ou explorá-las economicamente constitui abuso, não uma consequência legítima da fé.""")
    ),
    ApologeticsQuestion(
        number = 40,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué creer en las apariciones de Fátima, Lourdes o Guadalupe?""", """Why believe in the apparitions of Fatima, Lourdes or Guadalupe?""", """Por que acreditar nas aparições de Fátima, Lourdes ou Guadalupe?"""),
        answer = LocalizedText("""Un católico no está obligado a creer en todas las apariciones marianas, incluso cuando una autoridad eclesiástica permite o reconoce una devoción asociada a ellas.

La revelación pública, cuyo centro es Jesucristo, no necesita nuevas apariciones para ser completada.

La Iglesia examina los testimonios, la conformidad doctrinal y otros elementos antes de emitir juicios pastorales o doctrinales.""", """A Catholic is not obliged to believe in all Marian apparitions, even when an ecclesiastical authority permits or recognizes a devotion associated with them.

Public revelation, whose center is Jesus Christ, needs no new apparitions to be completed.

The Church examines the testimonies, doctrinal conformity and other elements before issuing pastoral or doctrinal judgments.""", """Um católico não é obrigado a crer em todas as aparições marianas, mesmo quando uma autoridade eclesiástica permite ou reconhece uma devoção a elas associada.

A revelação pública, cujo centro é Jesus Cristo, não precisa de novas aparições para ser completada.

A Igreja examina os testemunhos, a conformidade doutrinal e outros elementos antes de emitir juízos pastorais ou doutrinais."""),
        foundation = LocalizedText("""Hebreos 1,1-2; Gálatas 1,8; Catecismo, 66-67.""", """Hebrews 1:1-2; Galatians 1:8; Catechism, 66-67.""", """Hebreus 1,1-2; Gálatas 1,8; Catecismo, 66-67."""),
        clarification = LocalizedText(""": La autorización de una devoción no equivale necesariamente a una demostración histórica o científica de que ocurrió una aparición sobrenatural.

Una experiencia religiosa puede ser importante para muchos creyentes sin convertirse en obligación de fe para todos.

La actitud más sólida combina apertura, prudencia, investigación y libertad.""", """Authorizing a devotion does not necessarily amount to a historical or scientific demonstration that a supernatural apparition occurred.

A religious experience may be important for many believers without becoming an obligation of faith for all.

The soundest attitude combines openness, prudence, investigation and freedom.""", """A autorização de uma devoção não equivale necessariamente a uma demonstração histórica ou científica de que ocorreu uma aparição sobrenatural.

Uma experiência religiosa pode ser importante para muitos crentes sem se tornar obrigação de fé para todos.

A atitude mais sólida combina abertura, prudência, investigação e liberdade.""")
    ),
    ApologeticsQuestion(
        number = 41,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Dónde dice Jesús que Pedro fue el primer Papa?""", """Where does Jesus say that Peter was the first Pope?""", """Onde Jesus diz que Pedro foi o primeiro Papa?"""),
        answer = LocalizedText("""La palabra Papa, en el sentido institucional actual, no aparece en los Evangelios.

La argumentación católica comienza con el papel especial que Jesús confía a Pedro.

En Mateo 16,18-19 le habla de la roca y las llaves del Reino. En Lucas 22,31-32 le encomienda fortalecer a sus hermanos. En Juan 21,15-17 le confía el cuidado de sus ovejas.

La Iglesia interpreta conjuntamente estos textos como fundamento bíblico del ministerio petrino.""", """The word Pope, in the current institutional sense, does not appear in the Gospels.

The Catholic argument begins with the special role Jesus entrusts to Peter.

In Matthew 16:18-19 He speaks to him of the rock and the keys of the Kingdom. In Luke 22:31-32 He entrusts him with strengthening his brothers. In John 21:15-17 He entrusts him with caring for His sheep.

The Church interprets these texts together as the biblical foundation of the Petrine ministry.""", """A palavra Papa, no sentido institucional atual, não aparece nos Evangelhos.

A argumentação católica começa com o papel especial que Jesus confia a Pedro.

Em Mateus 16,18-19 fala-lhe da rocha e das chaves do Reino. Em Lucas 22,31-32 encomenda-lhe fortalecer seus irmãos. Em João 21,15-17 confia-lhe o cuidado de suas ovelhas.

A Igreja interpreta esses textos conjuntamente como fundamento bíblico do ministério petrino."""),
        foundation = LocalizedText("""Mateo 16,18-19; Lucas 22,31-32; Juan 21,15-17; Isaías 22,20-22; Catecismo, 880-882.""", """Matthew 16:18-19; Luke 22:31-32; John 21:15-17; Isaiah 22:20-22; Catechism, 880-882.""", """Mateus 16,18-19; Lucas 22,31-32; João 21,15-17; Isaías 22,20-22; Catecismo, 880-882."""),
        clarification = LocalizedText(""": Los pasajes muestran la importancia de Pedro, pero no describen explícitamente toda la estructura del papado moderno ni establecen con detalle cómo se transmitiría su ministerio a los obispos de Roma.

La doctrina del primado romano también depende de argumentos históricos y de la sucesión apostólica. Esta es una de las discusiones centrales con protestantes y ortodoxos.""", """These passages show Peter's importance, but they do not explicitly describe the whole structure of the modern papacy nor establish in detail how his ministry would be transmitted to the bishops of Rome.

The doctrine of Roman primacy also depends on historical arguments and on apostolic succession. This is one of the central discussions with Protestants and Orthodox.""", """As passagens mostram a importância de Pedro, mas não descrevem explicitamente toda a estrutura do papado moderno nem estabelecem em detalhe como seu ministério se transmitiria aos bispos de Roma.

A doutrina do primado romano também depende de argumentos históricos e da sucessão apostólica. Esta é uma das discussões centrais com protestantes e ortodoxos.""")
    ),
    ApologeticsQuestion(
        number = 42,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""Si hubo Papas corruptos, ¿cómo puede ser infalible el Papa?""", """If there were corrupt Popes, how can the Pope be infallible?""", """Se houve Papas corruptos, como pode o Papa ser infalível?"""),
        answer = LocalizedText("""Porque infalibilidad no significa impecabilidad.

La doctrina católica no afirma que el Papa nunca peque, se equivoque políticamente, tome malas decisiones o tenga opiniones personales erróneas.

La infalibilidad se refiere a circunstancias precisas en las que, como pastor supremo, define definitivamente una doctrina de fe o moral para toda la Iglesia.

Pedro mismo aparece cometiendo errores graves y siendo corregido por Pablo.

Fundamento adicional: Gálatas 2,11-14.""", """Because infallibility does not mean impeccability.

Catholic doctrine does not affirm that the Pope never sins, makes political mistakes, takes bad decisions or holds erroneous personal opinions.

Infallibility refers to precise circumstances in which, as supreme pastor, he definitively defines a doctrine of faith or morals for the whole Church.

Peter himself appears committing grave errors and being corrected by Paul.

Addendum: Galatians 2:11-14.""", """Porque infalibilidade não significa impecabilidade.

A doutrina católica não afirma que o Papa nunca peque, não se equivoque politicamente, não tome más decisões nem tenha opiniões pessoais errôneas.

A infalibilidade refere-se a circunstâncias precisas em que, como pastor supremo, define definitivamente uma doutrina de fé ou moral para toda a Igreja.

O próprio Pedro aparece cometendo erros graves e sendo corrigido por Paulo.

Adicional: Gálatas 2,11-14."""),
        foundation = LocalizedText("""Lucas 22,31-32; Mateo 16,18; Juan 16,13; Catecismo, 889-892.""", """Luke 22:31-32; Matthew 16:18; John 16:13; Catechism, 889-892.""", """Lucas 22,31-32; Mateus 16,18; João 16,13; Catecismo, 889-892."""),
        clarification = LocalizedText(""": Los versículos citados no enuncian literalmente la definición de infalibilidad del Concilio Vaticano I.

La argumentación católica es que el don de preservar a la Iglesia del error definitivo puede coexistir con los pecados personales de sus ministros.

No obstante, esa afirmación requiere aceptar una interpretación particular de las promesas de Cristo a su Iglesia.""", """The verses cited do not literally state the definition of infallibility of the First Vatican Council.

The Catholic argument is that the gift of preserving the Church from definitive error can coexist with the personal sins of its ministers.

Nevertheless, that affirmation requires accepting a particular interpretation of Christ's promises to His Church.""", """Os versículos citados não enunciam literalmente a definição de infalibilidade do Concílio Vaticano I.

A argumentação católica é que o dom de preservar a Igreja do erro definitivo pode coexistir com os pecados pessoais de seus ministros.

Contudo, essa afirmação requer aceitar uma interpretação particular das promessas de Cristo à sua Igreja.""")
    ),
    ApologeticsQuestion(
        number = 43,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Por qué la Iglesia tiene autoridad para interpretar la Biblia?""", """Why does the Church have authority to interpret the Bible?""", """Por que a Igreja tem autoridade para interpretar a Bíblia?"""),
        answer = LocalizedText("""Porque Jesús no entregó solamente textos a sus discípulos: también les encargó enseñar, bautizar y formar comunidades.

El Nuevo Testamento muestra a los apóstoles resolviendo disputas doctrinales y prácticas.

La Iglesia católica entiende que el Magisterio sirve a la Palabra de Dios, no que está por encima de ella.

Un cristiano puede leer la Biblia personalmente. La existencia de una autoridad doctrinal no elimina esa responsabilidad.""", """Because Jesus did not entrust only texts to His disciples: He also charged them to teach, baptize and form communities.

The New Testament shows the apostles resolving doctrinal and practical disputes.

The Catholic Church understands that the Magisterium serves the Word of God, not that it stands above it.

A Christian can read the Bible personally. The existence of a doctrinal authority does not eliminate that responsibility.""", """Porque Jesus não entregou somente textos a seus discípulos: também lhes encarregou ensinar, batizar e formar comunidades.

O Novo Testamento mostra os apóstolos resolvendo disputas doutrinais e práticas.

A Igreja católica entende que o Magistério serve à Palavra de Deus, e não que está acima dela.

Um cristão pode ler a Bíblia pessoalmente. A existência de uma autoridade doutrinal não elimina essa responsabilidade."""),
        foundation = LocalizedText("""Mateo 28,18-20; Hechos 15,1-29; 1 Timoteo 3,15; 2 Pedro 1,20-21; Catecismo, 85-87.""", """Matthew 28:18-20; Acts 15:1-29; 1 Timothy 3:15; 2 Peter 1:20-21; Catechism, 85-87.""", """Mateus 28,18-20; Atos 15,1-29; 1 Timóteo 3,15; 2 Pedro 1,20-21; Catecismo, 85-87."""),
        clarification = LocalizedText(""": El argumento de que existió autoridad apostólica no demuestra automáticamente todos los alcances que hoy se atribuyen al Magisterio católico.

Es necesario justificar también su continuidad histórica y los límites de su ejercicio.""", """The argument that there was apostolic authority does not automatically demonstrate all the scopes now attributed to the Catholic Magisterium.

Its historical continuity and the limits of its exercise must also be justified.""", """O argumento de que existiu autoridade apostólica não demonstra automaticamente todos os alcances hoje atribuídos ao Magistério católico.

É necessário justificar também sua continuidade histórica e os limites de seu exercício.""")
    ),
    ApologeticsQuestion(
        number = 44,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""Si Jesús enseñó a servir, ¿por qué la Iglesia tiene una jerarquía?""", """If Jesus taught to serve, why does the Church have a hierarchy?""", """Se Jesus ensinou a servir, por que a Igreja tem uma hierarquia?"""),
        answer = LocalizedText("""Porque la igualdad de dignidad no implica que todos desempeñen exactamente la misma función.

Jesús escogió a los Doce y les confió responsabilidades particulares. Las primeras comunidades reconocieron ministerios de enseñanza, supervisión y servicio.

La explicación católica es que el ministerio ordenado debe existir para servir a la comunidad.

Jesús enseña que el mayor debe comportarse como el servidor de todos.""", """Because equality of dignity does not imply that everyone performs exactly the same function.

Jesus chose the Twelve and entrusted them with particular responsibilities. The first communities recognized ministries of teaching, supervision and service.

The Catholic explanation is that ordained ministry must exist to serve the community.

Jesus teaches that the greatest must act as the servant of all.""", """Porque a igualdade de dignidade não implica que todos desempenhem exatamente a mesma função.

Jesus escolheu os Doze e lhes confiou responsabilidades particulares. As primeiras comunidades reconheceram ministérios de ensino, supervisão e serviço.

A explicação católica é que o ministério ordenado deve existir para servir à comunidade.

Jesus ensina que o maior deve comportar-se como o servidor de todos."""),
        foundation = LocalizedText("""Marcos 3,13-19; Lucas 22,24-27; Hechos 6,1-6; 1 Corintios 12,27-30; 1 Timoteo 3,1-13.""", """Mark 3:13-19; Luke 22:24-27; Acts 6:1-6; 1 Corinthians 12:27-30; 1 Timothy 3:1-13.""", """Marcos 3,13-19; Lucas 22,24-27; Atos 6,1-6; 1 Coríntios 12,27-30; 1 Timóteo 3,1-13."""),
        clarification = LocalizedText(""": La jerarquía puede convertirse en clericalismo cuando sus miembros se consideran superiores en dignidad, exigen obediencia abusiva o utilizan su posición para obtener privilegios.

La estructura ministerial tiene fundamentos bíblicos; las formas concretas de ejercerla deben ser examinadas críticamente a la luz del Evangelio.""", """Hierarchy can become clericalism when its members regard themselves as superior in dignity, demand abusive obedience or use their position to obtain privileges.

Ministerial structure has biblical foundations; the concrete forms of exercising it must be examined critically in the light of the Gospel.""", """A hierarquia pode se converter em clericalismo quando seus membros se consideram superiores em dignidade, exigem obediência abusiva ou utilizam sua posição para obter privilégios.

A estrutura ministerial tem fundamentos bíblicos; as formas concretas de exercê-la devem ser examinadas criticamente à luz do Evangelho.""")
    ),
    ApologeticsQuestion(
        number = 45,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿La Iglesia católica considera inferiores a los demás cristianos?""", """Does the Catholic Church consider other Christians inferior?""", """A Igreja católica considera inferiores os demais cristãos?"""),
        answer = LocalizedText("""La Iglesia católica considera que la plenitud de los medios de salvación confiados por Cristo subsiste en ella, pero reconoce que fuera de su estructura visible existen auténticos elementos de verdad y santificación.

También reconoce como hermanos en Cristo a los cristianos válidamente bautizados de otras comunidades.

Esto significa que la unidad de los cristianos es una responsabilidad, no una excusa para el desprecio.""", """The Catholic Church considers that the fullness of the means of salvation entrusted by Christ subsists in it, but it recognizes that outside its visible structure there exist authentic elements of truth and sanctification.

It also recognizes Christians validly baptized in other communities as brothers in Christ.

This means that the unity of Christians is a responsibility, not an excuse for contempt.""", """A Igreja católica considera que a plenitude dos meios de salvação confiados por Cristo subsiste nela, mas reconhece que fora de sua estrutura visível existem autênticos elementos de verdade e santificação.

Também reconhece como irmãos em Cristo os cristãos validamente batizados de outras comunidades.

Isso significa que a unidade dos cristãos é uma responsabilidade, não uma desculpa para o desprezo."""),
        foundation = LocalizedText("""Juan 17,21; Efesios 4,4-6; 1 Corintios 12,13; Catecismo, 817-819 y 838.""", """John 17:21; Ephesians 4:4-6; 1 Corinthians 12:13; Catechism, 817-819 and 838.""", """João 17,21; Efésios 4,4-6; 1 Coríntios 12,13; Catecismo, 817-819 e 838."""),
        clarification = LocalizedText(""": Hay diferencias doctrinales reales sobre sacramentos, ministerios, autoridad y sucesión apostólica.

No sería honesto fingir que esas diferencias carecen de importancia. Pero tampoco sería católico negar la acción de Dios en otros cristianos o tratarlos como personas de menor dignidad.""", """There are real doctrinal differences about the sacraments, ministries, authority and apostolic succession.

It would not be honest to pretend those differences are unimportant. But neither would it be Catholic to deny God's action in other Christians or treat them as persons of lesser dignity.""", """Há diferenças doutrinais reais sobre sacramentos, ministérios, autoridade e sucessão apostólica.

Não seria honesto fingir que essas diferenças não têm importância. Mas tampouco seria católico negar a ação de Deus em outros cristãos ou tratá-los como pessoas de menor dignidade.""")
    ),
    ApologeticsQuestion(
        number = 46,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Cómo pudo la Iglesia participar en las Cruzadas?""", """How could the Church take part in the Crusades?""", """Como a Igreja pôde participar das Cruzadas?"""),
        answer = LocalizedText("""Las Cruzadas surgieron en contextos históricos donde se mezclaban motivos religiosos, políticos, territoriales y militares.

La defensa de poblaciones o peregrinos y la recuperación de territorios fueron algunos de los argumentos utilizados, pero también existieron ambiciones y acciones gravemente injustas.

La doctrina moral católica distingue la defensa legítima de la violencia injustificada.""", """The Crusades arose in historical contexts where religious, political, territorial and military motives mingled.

The defense of peoples or pilgrims and the recovery of territories were some of the arguments used, but there were also grave injustices and ambitions.

Catholic moral doctrine distinguishes legitimate defense from unjustified violence.""", """As Cruzadas surgiram em contextos históricos em que se misturavam motivos religiosos, políticos, territoriais e militares.

A defesa de populações ou peregrinos e a recuperação de territórios foram alguns dos argumentos utilizados, mas também existiram ambições e ações gravemente injustas.

A doutrina moral católica distingue a legítima defesa da violência injustificada."""),
        foundation = LocalizedText("""Mateo 5,9.44; Mateo 26,52; Juan 18,36; Catecismo, 2307-2317.""", """Matthew 5:9,44; Matthew 26:52; John 18:36; Catechism, 2307-2317.""", """Mateus 5,9.44; Mateus 26,52; João 18,36; Catecismo, 2307-2317."""),
        clarification = LocalizedText(""": Esa distinción no convierte automáticamente todas las Cruzadas en moralmente legítimas.

Los ataques contra civiles, las matanzas y la imposición violenta de la fe deben juzgarse críticamente.

Reconocer el contexto histórico ayuda a comprender los acontecimientos, pero no basta para justificarlos.

La mejor defensa de la fe no consiste en afirmar que todos los cristianos de la historia actuaron correctamente.""", """That distinction does not automatically make all the Crusades morally legitimate.

Attacks against civilians, massacres and the violent imposition of faith must be judged critically.

Recognizing the historical context helps to understand events, but it is not enough to justify them.

The best defense of faith does not consist in affirming that all Christians in history acted rightly.""", """Essa distinção não torna automaticamente todas as Cruzadas moralmente legítimas.

Os ataques contra civis, as matanças e a imposição violenta da fé devem ser julgados criticamente.

Reconhecer o contexto histórico ajuda a compreender os acontecimentos, mas não basta para justificá-los.

A melhor defesa da fé não consiste em afirmar que todos os cristãos da história agiram corretamente.""")
    ),
    ApologeticsQuestion(
        number = 47,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Cómo se justifican la Inquisición y la persecución de herejes?""", """How are the Inquisition and the persecution of heretics justified?""", """Como se justificam a Inquisição e a perseguição de hereges?"""),
        answer = LocalizedText("""La fe cristiana exige una adhesión libre de la conciencia. Una declaración religiosa obtenida exclusivamente por coerción no constituye una auténtica respuesta de amor a Dios.

Históricamente, instituciones inquisitoriales funcionaron en sociedades donde religión, poder político y orden jurídico estaban estrechamente vinculados.

Su historia no puede reducirse a una sola práctica ni a una única época.""", """Christian faith requires a free adherence of conscience. A religious profession obtained exclusively by coercion does not constitute an authentic response of love to God.

Historically, inquisitorial institutions functioned in societies where religion, political power and the legal order were closely linked.

Their history cannot be reduced to a single practice or a single era.""", """A fé cristã exige uma adesão livre da consciência. Uma profissão religiosa obtida exclusivamente por coerção não constitui uma autêntica resposta de amor a Deus.

Historicamente, instituições inquisitoriais funcionaram em sociedades onde religião, poder político e ordem jurídica estavam estreitamente vinculados.

Sua história não pode ser reduzida a uma só prática nem a uma única época."""),
        foundation = LocalizedText("""Juan 18,36; Mateo 13,24-30; 2 Corintios 3,17; Catecismo, 2104-2109.""", """John 18:36; Matthew 13:24-30; 2 Corinthians 3:17; Catechism, 2104-2109.""", """João 18,36; Mateus 13,24-30; 2 Coríntios 3,17; Catecismo, 2104-2109."""),
        clarification = LocalizedText(""": Hubo procesos injustos y formas de violencia religiosa que contradicen el respeto a la dignidad humana.

Es importante reconocerlos sin caer en exageraciones históricas ni utilizar la existencia de abusos como argumento para negar cualquier cuestión doctrinal.

La defensa católica de la libertad religiosa desarrollada por el Concilio Vaticano II ofrece un criterio importante para evaluar críticamente esas prácticas.""", """There were unjust trials and forms of religious violence that contradict respect for human dignity.

It is important to recognize them without falling into historical exaggerations or using the existence of abuses as an argument to deny every doctrinal question.

The Catholic defense of religious freedom developed by the Second Vatican Council offers an important criterion for critically evaluating those practices.""", """Houve processos injustos e formas de violência religiosa que contradizem o respeito pela dignidade humana.

É importante reconhecê-los sem cair em exageros históricos nem utilizar a existência de abusos como argumento para negar qualquer questão doutrinal.

A defesa católica da liberdade religiosa desenvolvida pelo Concílio Vaticano II oferece um critério importante para avaliar criticamente essas práticas.""")
    ),
    ApologeticsQuestion(
        number = 48,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Por qué la Iglesia se equivocó en el caso de Galileo?""", """Why did the Church err in the Galileo case?""", """Por que a Igreja errou no caso de Galileu?"""),
        answer = LocalizedText("""El conflicto de Galileo muestra los riesgos de confundir una interpretación bíblica con una afirmación científica que debe investigarse mediante los métodos de la ciencia.

Las Escrituras no fueron escritas como manuales de astronomía.

La doctrina católica sostiene que una verdad científicamente establecida y una verdad de fe correctamente comprendida no pueden contradecirse, porque la verdad es una.""", """The Galileo conflict shows the risks of confusing a biblical interpretation with a scientific affirmation that must be investigated by the methods of science.

Scriptures were not written as astronomy manuals.

Catholic doctrine holds that a scientifically established truth and a correctly understood truth of faith cannot contradict each other, because truth is one.""", """O conflito de Galileu mostra os riscos de confundir uma interpretação bíblica com uma afirmação científica que deve ser investigada mediante os métodos da ciência.

As Escrituras não foram escritas como manuais de astronomia.

A doutrina católica sustenta que uma verdade cientificamente estabelecida e uma verdade de fé corretamente compreendida não podem contradizer-se, porque a verdade é uma."""),
        foundation = LocalizedText("""Salmo 19; Génesis 1; Catecismo, 159.""", """Psalm 19; Genesis 1; Catechism, 159.""", """Salmo 19; Gênesis 1; Catecismo, 159."""),
        clarification = LocalizedText(""": Eso no significa que la Iglesia haya actuado correctamente en el proceso contra Galileo.

Las autoridades eclesiásticas cometieron errores de juicio y de interpretación.

Tampoco sería riguroso afirmar que Galileo ya había demostrado todos los aspectos del sistema heliocéntrico de manera concluyente en el momento de su proceso.

La lección es doble: respetar los métodos científicos y evitar convertir interpretaciones humanas en verdades intocables.""", """That does not mean that the Church acted rightly in the trial against Galileo.

Ecclesiastical authorities committed errors of judgment and interpretation.

Nor would it be rigorous to affirm that Galileo had already conclusively demonstrated all aspects of the heliocentric system at the time of his trial.

The lesson is twofold: respect scientific methods and avoid turning human interpretations into untouchable truths.""", """Isso não significa que a Igreja tenha agido corretamente no processo contra Galileu.

As autoridades eclesiásticas cometeram erros de julgamento e de interpretação.

Tampouco seria rigoroso afirmar que Galileu já havia demonstrado de modo conclusivo todos os aspectos do sistema heliocêntrico no momento de seu processo.

A lição é dupla: respeitar os métodos científicos e evitar converter interpretações humanas em verdades intocáveis.""")
    ),
    ApologeticsQuestion(
        number = 49,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Qué responsabilidad tiene la Iglesia en los abusos de la colonización de América?""", """What responsibility does the Church bear for the abuses of the colonization of America?""", """Que responsabilidade a Igreja tem nos abusos da colonização da América?"""),
        answer = LocalizedText("""La evangelización cristiana debe distinguirse de la conquista y de la imposición religiosa.

En América coexistieron misioneros que defendieron a pueblos indígenas, autoridades que toleraron injusticias y miembros de instituciones eclesiásticas que participaron en sistemas de dominación.

El mandato de anunciar el Evangelio no autoriza a destruir culturas ni a utilizar la fuerza para obtener conversiones.""", """Christian evangelization must be distinguished from conquest and from religious imposition.

In America there coexisted missionaries who defended indigenous peoples, authorities who tolerated injustices, and members of ecclesiastical institutions who took part in systems of domination.

The mandate to proclaim the Gospel does not authorize destroying cultures or using force to obtain conversions.""", """A evangelização cristã deve ser distinguida da conquista e da imposição religiosa.

Na América coexistiram missionários que defenderam povos indígenas, autoridades que toleraram injustiças e membros de instituições eclesiásticas que participaram de sistemas de dominação.

O mandato de anunciar o Evangelho não autoriza destruir culturas nem usar a força para obter conversões."""),
        foundation = LocalizedText("""Mateo 28,19-20; Lucas 4,18-19; Gálatas 3,28; Catecismo, 1934-1938.""", """Matthew 28:19-20; Luke 4:18-19; Galatians 3:28; Catechism, 1934-1938.""", """Mateus 28,19-20; Lucas 4,18-19; Gálatas 3,28; Catecismo, 1934-1938."""),
        clarification = LocalizedText(""": Sería históricamente falso presentar a todos los misioneros como opresores o a toda la Iglesia como defensora de los indígenas.

También sería falso negar los abusos y las complicidades.

Una respuesta católica seria debe incluir investigación histórica, reconocimiento del daño y respeto por la libertad de conciencia de los pueblos.""", """It would be historically false to present all missionaries as oppressors or the whole Church as defenders of the indigenous people.

It would also be false to deny abuses and complicities.

A serious Catholic response must include historical research, recognition of harm and respect for the freedom of conscience of peoples.""", """Seria historicamente falso apresentar todos os missionários como opressores ou toda a Igreja como defensora dos indígenas.

Também seria falso negar os abusos e as cumplicidades.

Uma resposta católica séria deve incluir investigação histórica, reconhecimento do dano e respeito pela liberdade de consciência dos povos.""")
    ),
    ApologeticsQuestion(
        number = 50,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Por qué confiar en una Iglesia cuyos miembros pueden corromperse?""", """Why trust a Church whose members can be corrupted?""", """Por que confiar em uma Igreja cujos membros podem corromper-se?"""),
        answer = LocalizedText("""La credibilidad del cristianismo no puede fundamentarse en que todos sus representantes sean moralmente perfectos.

Jesús mismo advirtió sobre los falsos pastores, los hipócritas y la posibilidad de corrupción religiosa.

La doctrina católica distingue entre la santidad de Cristo, los medios de gracia confiados a la Iglesia y los pecados reales de sus miembros.""", """The credibility of Christianity cannot be founded on all its representatives being morally perfect.

Jesus Himself warned about false shepherds, hypocrites and the possibility of religious corruption.

Catholic doctrine distinguishes between the holiness of Christ, the means of grace entrusted to the Church, and the real sins of its members.""", """A credibilidade do cristianismo não pode fundamentar-se em que todos os seus representantes sejam moralmente perfeitos.

O próprio Jesus advertiu sobre os falsos pastores, os hipócritas e a possibilidade de corrupção religiosa.

A doutrina católica distingue entre a santidade de Cristo, os meios de graça confiados à Igreja e os pecados reais de seus membros."""),
        foundation = LocalizedText("""Mateo 7,15-23; Mateo 23; 2 Corintios 4,7; Juan 15,1-8; Catecismo, 823-827.""", """Matthew 7:15-23; Matthew 23; 2 Corinthians 4:7; John 15:1-8; Catechism, 823-827.""", """Mateus 7,15-23; Mateus 23; 2 Coríntios 4,7; João 15,1-8; Catecismo, 823-827."""),
        clarification = LocalizedText(""": Esa distinción no otorga inmunidad moral o institucional a los dirigentes religiosos.

Cuando hay corrupción, abuso de autoridad o injusticia, la credibilidad exige verdad, responsabilidad, reparación y reforma.

Una Iglesia que predica la conversión debe comenzar por aplicarla a sí misma.""", """That distinction does not grant moral or institutional immunity to religious leaders.

When there is corruption, abuse of authority or injustice, credibility demands truth, responsibility, reparation and reform.

A Church that preaches conversion must begin by applying it to itself.""", """Essa distinção não concede imunidade moral ou institucional aos dirigentes religiosos.

Quando há corrupção, abuso de autoridade ou injustiça, a credibilidade exige verdade, responsabilidade, reparação e reforma.

Uma Igreja que prega a conversão deve começar por aplicá-la a si mesma.""")
    ),
    ApologeticsQuestion(
        number = 51,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Dónde dice que la Comunión es realmente el Cuerpo y la Sangre de Cristo?""", """Where does it say that Communion is really the Body and Blood of Christ?""", """Onde está escrito que a Comunhão é realmente o Corpo e Sangue de Cristo?"""),
        answer = LocalizedText("""Los principales fundamentos son las palabras de Jesús en la Última Cena y su discurso sobre el Pan de Vida.

En Juan 6, Jesús habla de dar su carne para la vida del mundo. En la institución eucarística identifica el pan con su cuerpo y el cáliz con su sangre.

San Pablo advierte que recibir indignamente la Eucaristía implica una responsabilidad respecto del Cuerpo y la Sangre del Señor.

Para la interpretación católica, esto apoya una presencia que supera un mero recordatorio simbólico.""", """The main foundations are Jesus' words at the Last Supper and His discourse on the Bread of Life.

In John 6, Jesus speaks of giving His flesh for the life of the world. At the Eucharistic institution He identifies the bread with His body and the chalice with His blood.

Saint Paul warns that receiving the Eucharist unworthily involves a responsibility regarding the Lord's Body and Blood.

For the Catholic interpretation, this supports a presence surpassing a mere symbolic reminder.""", """Os principais fundamentos são as palavras de Jesus na Última Ceia e seu discurso sobre o Pão da Vida.

Em João 6, Jesus fala de dar sua carne pela vida do mundo. Na instituição eucarística identifica o pão com seu corpo e o cálice com seu sangue.

São Paulo adverte que receber indignamente a Eucaristia implica responsabilidade quanto ao Corpo e Sangue do Senhor.

Para a interpretação católica, isso sustenta uma presença que supera a mera lembrança simbólica."""),
        foundation = LocalizedText("""Juan 6,51-58; Mateo 26,26-28; Lucas 22,19-20; 1 Corintios 10,16; 1 Corintios 11,27-29; Catecismo, 1373-1381.""", """John 6:51-58; Matthew 26:26-28; Luke 22:19-20; 1 Corinthians 10:16; 1 Corinthians 11:27-29; Catechism, 1373-1381.""", """João 6,51-58; Mateus 26,26-28; Lucas 22,19-20; 1 Coríntios 10,16; 1 Coríntios 11,27-29; Catecismo, 1373-1381."""),
        clarification = LocalizedText(""": Algunos protestantes interpretan Juan 6 principalmente como una enseñanza sobre creer en Cristo. Otros aceptan formas de presencia real sin adoptar la transubstanciación.

La defensa católica más fuerte utiliza el conjunto de los textos, la liturgia primitiva y el testimonio de los primeros escritores cristianos, no solamente un versículo aislado.""", """Some Protestants interpret John 6 mainly as teaching about believing in Christ. Others accept forms of real presence without adopting transubstantiation.

The strongest Catholic defense uses the whole of the texts, the primitive liturgy and the testimony of the first Christian writers, not just an isolated verse.""", """Alguns protestantes interpretam João 6 principalmente como ensino sobre crer em Cristo. Outros aceitam formas de presença real sem adotar a transubstanciação.

A defesa católica mais forte utiliza o conjunto dos textos, a liturgia primitiva e o testemunho dos primeiros escritores cristãos, não somente um versículo isolado.""")
    ),
    ApologeticsQuestion(
        number = 52,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""Si Jesús murió una sola vez, ¿por qué la Misa es un sacrificio?""", """If Jesus died only once, why is Mass a sacrifice?""", """Se Jesus morreu uma só vez, por que a Missa é um sacrifício?"""),
        answer = LocalizedText("""Porque la Iglesia no enseña que Jesús vuelva a morir en cada Misa.

El sacrificio de Cristo ocurrió históricamente una sola vez y posee eficacia definitiva.

La Eucaristía hace sacramentalmente presente ese mismo sacrificio, sin repetir la crucifixión.

En la tradición bíblica, un memorial litúrgico no es solamente recordar mentalmente un acontecimiento pasado; implica actualizar ritualmente su significado para la comunidad.""", """Because the Church does not teach that Jesus dies again at each Mass.

Christ's sacrifice occurred historically only once and possesses definitive efficacy.

The Eucharist makes that same sacrifice sacramentally present, without repeating the crucifixion.

In biblical tradition, a liturgical memorial is not merely mentally remembering a past event; it ritually actualizes its meaning for the community.""", """Porque a Igreja não ensina que Jesus morra de novo em cada Missa.

O sacrifício de Cristo ocorreu historicamente uma só vez e possui eficácia definitiva.

A Eucaristia torna sacramentalmente presente esse mesmo sacrifício, sem repetir a crucifixão.

Na tradição bíblica, um memorial litúrgico não é somente lembrar mentalmente um acontecimento passado; implica atualizar ritualmente seu significado para a comunidade."""),
        foundation = LocalizedText("""Hebreos 7,27; Hebreos 9,24-28; Hebreos 10,10-14; Lucas 22,19-20; 1 Corintios 11,26; Catecismo, 1362-1367.""", """Hebrews 7:27; Hebrews 9:24-28; Hebrews 10:10-14; Luke 22:19-20; 1 Corinthians 11:26; Catechism, 1362-1367.""", """Hebreus 7,27; Hebreus 9,24-28; Hebreus 10,10-14; Lucas 22,19-20; 1 Coríntios 11,26; Catecismo, 1362-1367."""),
        clarification = LocalizedText(""": La afirmación de que la Misa hace presente sacramentalmente el sacrificio de Cristo pertenece a la interpretación teológica católica.

Hebreos enseña inequívocamente que Cristo se ofreció una sola vez. Cualquier explicación católica debe preservar esa verdad y evitar sugerir un sacrificio nuevo o una muerte repetida.""", """The affirmation that the Mass makes Christ's sacrifice sacramentally present belongs to Catholic theological interpretation.

Hebrews unequivocally teaches that Christ offered Himself only once. Any Catholic explanation must preserve that truth and avoid suggesting a new sacrifice or a repeated death.""", """A afirmação de que a Missa torna presente sacramentalmente o sacrifício de Cristo pertence à interpretação teológica católica.

Hebreus ensina inequivocamente que Cristo se ofereceu uma só vez. Qualquer explicação católica deve preservar essa verdade e evitar sugerir um sacrifício novo ou uma morte repetida.""")
    ),
    ApologeticsQuestion(
        number = 53,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Cómo puede el pan convertirse en Cristo si sigue pareciendo pan?""", """How can bread become Christ if it still looks like bread?""", """Como pode o pão se converter em Cristo se continua parecendo pão?"""),
        answer = LocalizedText("""La doctrina de la transubstanciación distingue entre la realidad sustancial de lo que algo es y sus propiedades sensibles.

La Iglesia enseña que, mediante la consagración, cambia la sustancia del pan y del vino, mientras permanecen sus apariencias.

La explicación utiliza categorías filosóficas para expresar la fe en la presencia real de Cristo.""", """The doctrine of transubstantiation distinguishes between the substantial reality of what something is and its sensible properties.

The Church teaches that, by the consecration, the substance of the bread and wine changes while their appearances remain.

The explanation uses philosophical categories to express faith in Christ's real presence.""", """A doutrina da transubstanciação distingue entre a realidade substancial daquilo que algo é e suas propriedades sensíveis.

A Igreja ensina que, mediante a consagração, muda a substância do pão e do vinho, enquanto permanecem suas aparências.

A explicação utiliza categorias filosóficas para exprimir a fé na presença real de Cristo."""),
        foundation = LocalizedText("""Mateo 26,26-28; Juan 6,53-56; 1 Corintios 11,23-29; Catecismo, 1376 y 1413.""", """Matthew 26:26-28; John 6:53-56; 1 Corinthians 11:23-29; Catechism, 1376 and 1413.""", """Mateus 26,26-28; João 6,53-56; 1 Coríntios 11,23-29; Catecismo, 1376 e 1413."""),
        clarification = LocalizedText(""": La transubstanciación no es una transformación química.

No se puede verificar mediante un microscopio que las propiedades físicas del pan desaparecieron, porque la doctrina afirma precisamente que esas propiedades permanecen.

Por tanto, no debemos presentarla como una conclusión científica demostrada. Es una explicación metafísica de una afirmación sacramental recibida por la fe.""", """Transubstantiation is not a chemical transformation.

It cannot be verified under a microscope that the physical properties of the bread disappeared, because the doctrine precisely affirms that those properties remain.

Therefore we must not present it as a demonstrated scientific conclusion. It is a metaphysical explanation of a sacramental affirmation received by faith.""", """A transubstanciação não é uma transformação química.

Não se pode verificar mediante um microscópio que as propriedades físicas do pão desapareceram, porque a doutrina afirma precisamente que essas propriedades permanecem.

Portanto, não devemos apresentá-la como uma conclusão científica demonstrada. É uma explicação metafísica de uma afirmação sacramental recebida pela fé.""")
    ),
    ApologeticsQuestion(
        number = 54,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué confesar los pecados ante un sacerdote si Dios puede perdonar directamente?""", """Why confess sins to a priest if God can forgive directly?""", """Por que confessar os pecados diante de um sacerdote se Deus pode perdoar diretamente?"""),
        answer = LocalizedText("""Dios es quien perdona los pecados. El sacerdote no posee un poder de perdón independiente de Dios.

Después de la resurrección, Jesús comunica a sus apóstoles el Espíritu Santo y les confía una misión relacionada explícitamente con perdonar y retener pecados.

La explicación católica es que Cristo quiso que su perdón también llegara por medio de un signo sacramental y comunitario.""", """God is the one who forgives sins. The priest does not possess a power of forgiveness independent of God.

After the resurrection, Jesus communicates the Holy Spirit to His apostles and entrusts them with a mission explicitly related to forgiving and retaining sins.

The Catholic explanation is that Christ willed His forgiveness to come also through a sacramental and communal sign.""", """Deus é quem perdoa os pecados. O sacerdote não possui um poder de perdão independente de Deus.

Depois da ressurreição, Jesus comunica a seus apóstolos o Espírito Santo e lhes confia uma missão relacionada explicitamente com perdoar e reter pecados.

A explicação católica é que Cristo quis que seu perdão também chegasse por meio de um sinal sacramental e comunitário."""),
        foundation = LocalizedText("""Juan 20,21-23; Mateo 16,19; Mateo 18,18; Santiago 5,14-16; 2 Corintios 5,18-20; Catecismo, 1422-1498.""", """John 20:21-23; Matthew 16:19; Matthew 18:18; James 5:14-16; 2 Corinthians 5:18-20; Catechism, 1422-1498.""", """João 20,21-23; Mateus 16,19; Mateus 18,18; Tiago 5,14-16; 2 Coríntios 5,18-20; Catecismo, 1422-1498."""),
        clarification = LocalizedText(""": La Iglesia no enseña que Dios sea incapaz de perdonar fuera del confesionario.

El Catecismo, 1452, reconoce que la contrición perfecta puede obtener el perdón de los pecados graves si incluye el propósito de acudir a la confesión cuando sea posible.

La controversia con los protestantes se centra en el alcance sacramental y ministerial de Juan 20, no en si Dios puede perdonar directamente.""", """The Church does not teach that God is unable to forgive outside the confessional.

The Catechism, 1452, recognizes that perfect contrition can obtain forgiveness of grave sins if it includes the purpose of going to confession when possible.

The controversy with Protestants centers on the sacramental and ministerial scope of John 20, not on whether God can forgive directly.""", """A Igreja não ensina que Deus seja incapaz de perdoar fora do confessionário.

O Catecismo, 1452, reconhece que a contrição perfeita pode obter o perdão dos pecados graves se inclui o propósito de recorrer à confissão quando for possível.

A controvérsia com os protestantes centra-se no alcance sacramental e ministerial de João 20, não em saber se Deus pode perdoar diretamente.""")
    ),
    ApologeticsQuestion(
        number = 55,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Puede un sacerdote pecador administrar sacramentos válidos?""", """Can a sinful priest administer valid sacraments?""", """Um sacerdote pecador pode administrar sacramentos válidos?"""),
        answer = LocalizedText("""Sí, porque la eficacia fundamental del sacramento procede de Cristo, no de la perfección moral del ministro.

San Pablo distingue entre el valor del Evangelio y la fragilidad de quienes lo anuncian.

La tradición católica rechaza la idea de que el estado moral del sacerdote determine automáticamente la validez del sacramento.""", """Yes, because the fundamental efficacy of the sacrament proceeds from Christ, not from the moral perfection of the minister.

Saint Paul distinguishes between the value of the Gospel and the fragility of those who proclaim it.

Catholic tradition rejects the idea that the priest's moral state automatically determines the validity of the sacrament.""", """Sim, porque a eficácia fundamental do sacramento procede de Cristo, não da perfeição moral do ministro.

São Paulo distingue entre o valor do Evangelho e a fragilidade de quem o anuncia.

A tradição católica rejeita a ideia de que o estado moral do sacerdote determine automaticamente a validade do sacramento."""),
        foundation = LocalizedText("""2 Corintios 4,7; 1 Corintios 3,5-7; Juan 20,21-23; Catecismo, 1127-1128 y 1584.""", """2 Corinthians 4:7; 1 Corinthians 3:5-7; John 20:21-23; Catechism, 1127-1128 and 1584.""", """2 Coríntios 4,7; 1 Coríntios 3,5-7; João 20,21-23; Catecismo, 1127-1128 e 1584."""),
        clarification = LocalizedText(""": La validez también requiere condiciones sacramentales concretas, como ministro apto, materia, forma e intención correspondientes.

Además, que un sacramento pueda ser válido no significa que el comportamiento del ministro sea aceptable.

Un sacerdote que comete delitos o abusos debe responder por ellos, y su ministerio puede ser restringido o retirado conforme al derecho aplicable.""", """Validity also requires concrete sacramental conditions, such as a suitable minister, matter, form and corresponding intention.

Moreover, that a sacrament may be valid does not mean that the minister's behavior is acceptable.

A priest who commits crimes or abuses must answer for them, and his ministry may be restricted or withdrawn according to applicable law.""", """A validade também requer condições sacramentais concretas, como ministro apto, matéria, forma e intenção correspondentes.

Além disso, que um sacramento possa ser válido não significa que o comportamento do ministro seja aceitável.

Um sacerdote que comete delitos ou abusos deve responder por eles, e seu ministério pode ser restringido ou retirado conforme o direito aplicável.""")
    ),
    ApologeticsQuestion(
        number = 56,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué bautizar a niños que todavía no pueden decidir?""", """Why baptize children who cannot yet decide?""", """Por que batizar crianças que ainda não podem decidir?"""),
        answer = LocalizedText("""La interpretación católica comprende el bautismo primero como un don de la gracia divina, no simplemente como una declaración pública de una decisión individual.

En el Nuevo Testamento se relaciona con el nuevo nacimiento y la incorporación a Cristo.

Los relatos de bautismos de familias son compatibles con la práctica del bautismo infantil, aunque no demuestran por sí solos que hubiera bebés presentes.

La Iglesia entiende que la fe de la comunidad acompaña al niño hasta que este pueda asumir personalmente su vida cristiana.""", """The Catholic interpretation understands baptism first as a gift of divine grace, not simply as a public declaration of an individual decision.

In the New Testament it is linked to new birth and incorporation into Christ.

Accounts of household baptisms are compatible with the practice of infant baptism, although they do not by themselves prove that babies were present.

The Church understands that the community's faith accompanies the child until he can personally assume his Christian life.""", """A interpretação católica compreende o batismo primeiro como dom da graça divina, não simplesmente como declaração pública de uma decisão individual.

No Novo Testamento, ele é relacionado com o novo nascimento e a incorporação a Cristo.

Os relatos de batismos de famílias são compatíveis com a prática do batismo infantil, embora não demonstrem por si sós que houvesse bebês presentes.

A Igreja entende que a fé da comunidade acompanha a criança até que esta possa assumir pessoalmente sua vida cristã."""),
        foundation = LocalizedText("""Juan 3,5; Hechos 2,38-39; Hechos 16,15.33; Colosenses 2,11-12; Catecismo, 1250-1252.""", """John 3:5; Acts 2:38-39; Acts 16:15,33; Colossians 2:11-12; Catechism, 1250-1252.""", """João 3,5; Atos 2,38-39; Atos 16,15.33; Colossenses 2,11-12; Catecismo, 1250-1252."""),
        clarification = LocalizedText(""": La Biblia no contiene una orden explícita formulada como «bautizad a los recién nacidos».

Por eso, el argumento católico incluye también la Tradición y una determinada comprensión del sacramento.

Cuando el niño crece, su libertad y su conciencia deben ser respetadas.""", """The Bible contains no explicit order formulated as “baptize newborns”.

Therefore the Catholic argument also includes Tradition and a certain understanding of the sacrament.

When the child grows, his freedom and conscience must be respected.""", """A Bíblia não contém uma ordem explícita formulada como “batizai os recém-nascidos”.

Por isso, o argumento católico inclui também a Tradição e uma determinada compreensão do sacramento.

Quando a criança cresce, sua liberdade e sua consciência devem ser respeitadas.""")
    ),
    ApologeticsQuestion(
        number = 57,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Qué sucede con los bebés que mueren sin bautismo?""", """What happens to babies who die without baptism?""", """O que acontece com os bebês que morrem sem batismo?"""),
        answer = LocalizedText("""La Iglesia los confía a la misericordia de Dios y expresa esperanza en su salvación.

El bautismo es el medio ordinario de incorporación sacramental a Cristo, pero Dios no está limitado por sus sacramentos.

Jesús manifiesta un amor especial por los pequeños y afirma que el Reino pertenece a quienes son como ellos.""", """The Church entrusts them to God's mercy and expresses hope for their salvation.

Baptism is the ordinary means of sacramental incorporation into Christ, but God is not limited by His sacraments.

Jesus shows a special love for little children and affirms that the Kingdom belongs to those who are like them.""", """A Igreja os confia à misericórdia de Deus e expressa esperança em sua salvação.

O batismo é o meio ordinário de incorporação sacramental a Cristo, mas Deus não está limitado por seus sacramentos.

Jesus manifesta um amor especial pelos pequenos e afirma que o Reino pertence aos que são como eles."""),
        foundation = LocalizedText("""Marcos 10,13-16; Mateo 18,10-14; 1 Timoteo 2,4; Catecismo, 1261.""", """Mark 10:13-16; Matthew 18:10-14; 1 Timothy 2:4; Catechism, 1261.""", """Marcos 10,13-16; Mateus 18,10-14; 1 Timóteo 2,4; Catecismo, 1261."""),
        clarification = LocalizedText(""": La Iglesia no ha revelado ni definido con certeza el destino individual de todos los niños muertos sin bautismo.

La antigua hipótesis teológica del limbo no es un dogma.

Sería incorrecto enseñar que esos niños están condenados, del mismo modo que sería incorrecto presentar una explicación particular de su destino como conocimiento definitivo.""", """The Church has not revealed or definitively defined the individual destiny of all unbaptized children who die.

The old theological hypothesis of limbo is not a dogma.

It would be incorrect to teach that these children are condemned, just as it would be incorrect to present a particular explanation of their destiny as definitive knowledge.""", """A Igreja não revelou nem definiu com certeza o destino individual de todas as crianças mortas sem batismo.

A antiga hipótese teológica do limbo não é um dogma.

Seria incorreto ensinar que essas crianças estão condenadas, do mesmo modo que seria incorreto apresentar uma explicação particular de seu destino como conhecimento definitivo.""")
    ),
    ApologeticsQuestion(
        number = 58,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué faltar a Misa el domingo puede ser pecado grave?""", """Why can missing Mass on Sunday be a grave sin?""", """Por que faltar à Missa no domingo pode ser pecado grave?"""),
        answer = LocalizedText("""Porque para la Iglesia la Eucaristía no es simplemente una actividad religiosa opcional, sino el centro de la vida cristiana y de la comunión eclesial.

Los primeros cristianos se reunían para partir el pan, especialmente el día del Señor.

La obligación dominical expresa la importancia del culto comunitario, no la idea de que Dios necesite comprobar nuestra asistencia.""", """Because for the Church the Eucharist is not simply an optional religious activity, but the center of Christian life and ecclesial communion.

The first Christians met to break bread, especially on the Lord's Day.

The Sunday obligation expresses the importance of communal worship, not the idea that God needs to check our attendance.""", """Porque para a Igreja a Eucaristia não é simplesmente uma atividade religiosa opcional, mas o centro da vida cristã e da comunhão eclesial.

Os primeiros cristãos se reuniam para partir o pão, especialmente no Dia do Senhor.

A obrigação dominical expressa a importância do culto comunitário, não a ideia de que Deus precise comprovar nossa presença."""),
        foundation = LocalizedText("""Hechos 2,42; Hechos 20,7; Hebreos 10,24-25; 1 Corintios 11,23-26; Catecismo, 2177-2183.""", """Acts 2:42; Acts 20:7; Hebrews 10:24-25; 1 Corinthians 11:23-26; Catechism, 2177-2183.""", """Atos 2,42; Atos 20,7; Hebreus 10,24-25; 1 Coríntios 11,23-26; Catecismo, 2177-2183."""),
        clarification = LocalizedText(""": No cualquier ausencia constituye automáticamente pecado mortal.

Se requiere materia grave, pleno conocimiento y consentimiento deliberado. También existen causas justificadas, como enfermedad, atención necesaria a otras personas o imposibilidad real.

La vida cristiana no puede reducirse a registrar asistencias.""", """Not every absence automatically constitutes mortal sin.

It requires grave matter, full knowledge and deliberate consent. There are also justified causes, such as illness, necessary attention to other people, or real impossibility.

Christian life cannot be reduced to recording attendance.""", """Nem toda ausência constitui automaticamente pecado mortal.

Requer-se matéria grave, pleno conhecimento e consentimento deliberado. Também existem causas justificadas, como doença, atendimento necessário a outras pessoas ou impossibilidade real.

A vida cristã não pode ser reduzida a registrar presenças.""")
    ),
    ApologeticsQuestion(
        number = 59,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué la Iglesia no permite comulgar a cualquier persona que lo desee?""", """Why does the Church not allow just anyone who wishes to receive Communion?""", """Por que a Igreja não permite comungar qualquer pessoa que o deseje?"""),
        answer = LocalizedText("""Porque la Comunión eucarística expresa simultáneamente la unión con Cristo y la comunión en la fe y los sacramentos de la Iglesia.

San Pablo advierte sobre la necesidad de discernir el Cuerpo del Señor antes de comulgar.

La disciplina católica procura proteger la integridad del sacramento y el significado público de recibirlo.""", """Because Eucharistic Communion simultaneously expresses union with Christ and communion in the Church's faith and sacraments.

Saint Paul warns about the need to discern the Lord's Body before communing.

Catholic discipline seeks to protect the integrity of the sacrament and the public meaning of receiving it.""", """Porque a Comunhão eucarística expressa simultaneamente a união com Cristo e a comunhão na fé e nos sacramentos da Igreja.

São Paulo adverte sobre a necessidade de discernir o Corpo do Senhor antes de comungar.

A disciplina católica procura proteger a integridade do sacramento e o significado público de recebê-lo."""),
        foundation = LocalizedText("""1 Corintios 10,16-17; 1 Corintios 11,27-29; Mateo 5,23-24; Catecismo, 1385 y 1395-1396.""", """1 Corinthians 10:16-17; 1 Corinthians 11:27-29; Matthew 5:23-24; Catechism, 1385 and 1395-1396.""", """1 Coríntios 10,16-17; 1 Coríntios 11,27-29; Mateus 5,23-24; Catecismo, 1385 e 1395-1396."""),
        clarification = LocalizedText(""": Las condiciones de acceso deben explicarse con respeto y aplicarse conforme al derecho de la Iglesia.

Existen excepciones y situaciones particulares para cristianos no católicos, reguladas por la disciplina sacramental.

Una persona que no comulga no está excluida de la oración, de escuchar la Palabra o de buscar a Dios.

Tampoco corresponde juzgar públicamente el estado interior de cada fiel.""", """Conditions of access must be explained with respect and applied according to the Church's law.

There are exceptions and particular situations for non-Catholic Christians, regulated by sacramental discipline.

A person who does not commune is not excluded from prayer, listening to the Word, or seeking God.

Nor is it for us to publicly judge the interior state of each believer.""", """As condições de acesso devem ser explicadas com respeito e aplicadas conforme o direito da Igreja.

Existem exceções e situações particulares para cristãos não católicos, reguladas pela disciplina sacramental.

Uma pessoa que não comunga não está excluída da oração, de escutar a Palavra ou de buscar a Deus.

Tampouco corresponde julgar publicamente o estado interior de cada fiel.""")
    ),
    ApologeticsQuestion(
        number = 60,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué usar agua bendita, medallas y escapularios? ¿No son amuletos?""", """Why use holy water, medals and scapulars? Are they not amulets?""", """Por que usar água benta, medalhas e escapulários? Não são amuletos?"""),
        answer = LocalizedText("""Porque la tradición católica admite signos materiales que ayudan a expresar la fe, recordar compromisos y pedir la bendición de Dios.

La Biblia utiliza el agua, el aceite, la imposición de manos y otros elementos materiales en contextos religiosos.

Estos signos son llamados sacramentales. No producen automáticamente la gracia del mismo modo que los sacramentos.""", """Because Catholic tradition admits material signs that help express faith, recall commitments and ask God's blessing.

The Bible uses water, oil, the laying on of hands and other material elements in religious contexts.

These signs are called sacramentals. They do not automatically produce grace in the same way as the sacraments.""", """Porque a tradição católica admite sinais materiais que ajudam a exprimir a fé, lembrar compromissos e pedir a bênção de Deus.

A Bíblia utiliza a água, o óleo, a imposição de mãos e outros elementos materiais em contextos religiosos.

Esses sinais são chamados sacramentais. Eles não produzem automaticamente a graça do mesmo modo que os sacramentos."""),
        foundation = LocalizedText("""Éxodo 30,22-30; Marcos 6,13; Santiago 5,14; Hechos 19,11-12; Catecismo, 1667-1679.""", """Exodus 30:22-30; Mark 6:13; James 5:14; Acts 19:11-12; Catechism, 1667-1679.""", """Êxodo 30,22-30; Marcos 6,13; Tiago 5,14; Atos 19,11-12; Catecismo, 1667-1679."""),
        clarification = LocalizedText(""": Una medalla no garantiza protección física; el agua bendita no es una sustancia mágica; y portar un escapulario no sustituye la conversión, la caridad ni la confianza en Dios.

Si alguien atribuye a un objeto una eficacia automática desligada de Dios y de la fe, puede caer en superstición, que la propia Iglesia condena.""", """A medal does not guarantee physical protection; holy water is not a magical substance; and carrying a scapular does not replace conversion, charity or trust in God.

If someone attributes to an object an automatic efficacy detached from God and from faith, they may fall into superstition, which the Church itself condemns.""", """Uma medalha não garante proteção física; a água benta não é uma substância mágica; e portar um escapulário não substitui a conversão, a caridade nem a confiança em Deus.

Se alguém atribui a um objeto uma eficácia automática desligada de Deus e da fé, pode cair em superstição, que a própria Igreja condena.""")
    ),
    ApologeticsQuestion(
        number = 61,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Dónde dice la Biblia que existe el purgatorio?""", """Where does the Bible say purgatory exists?""", """Onde a Bíblia diz que existe o purgatório?"""),
        answer = LocalizedText("""La Biblia no contiene la palabra purgatorio ni una descripción completa de su funcionamiento.

La doctrina católica se fundamenta en tres ideas: nada impuro entra en la presencia definitiva de Dios; la salvación es obra de Cristo; y es posible que una persona salvada necesite una purificación final.

San Pablo describe a alguien cuya obra es consumida por el fuego, pero que personalmente se salva.

El razonamiento católico es que la purificación final no constituye una segunda oportunidad de salvación: corresponde a quienes mueren en amistad con Dios.""", """The Bible does not contain the word purgatory nor a complete description of how it works.

Catholic doctrine is founded on three ideas: nothing impure enters God's definitive presence; salvation is Christ's work; and it is possible that a saved person needs a final purification.

Saint Paul describes someone whose work is consumed by fire, but who himself is saved.

Catholic reasoning is that final purification does not constitute a second chance of salvation: it corresponds to those who die in friendship with God.""", """A Bíblia não contém a palavra purgatório nem uma descrição completa de seu funcionamento.

A doutrina católica se fundamenta em três ideias: nada impuro entra na presença definitiva de Deus; a salvação é obra de Cristo; e é possível que uma pessoa salva necessite uma purificação final.

São Paulo descreve alguém cuja obra é consumida pelo fogo, mas que pessoalmente se salva.

O raciocínio católico é que a purificação final não constitui uma segunda oportunidade de salvação: corresponde a quem morre em amizade com Deus."""),
        foundation = LocalizedText("""1 Corintios 3,11-15; Apocalipsis 21,27; Hebreos 12,14; 2 Macabeos 12,42-46; Catecismo, 1030-1032.""", """1 Corinthians 3:11-15; Revelation 21:27; Hebrews 12:14; 2 Maccabees 12:42-46; Catechism, 1030-1032.""", """1 Coríntios 3,11-15; Apocalipse 21,27; Hebreus 12,14; 2 Macabeus 12,42-46; Catecismo, 1030-1032."""),
        clarification = LocalizedText(""": 1 Corintios 3 admite interpretaciones distintas; su contexto inmediato es la evaluación de la obra de los ministros cristianos.

Además, muchos protestantes no aceptan 2 Macabeos como Escritura canónica.

Por tanto, el argumento no puede presentarse como una demostración bíblica incontestable. La doctrina se apoya también en la Tradición y en el desarrollo teológico.""", """1 Corinthians 3 admits different interpretations; its immediate context is the evaluation of the work of Christian ministers.

Moreover, many Protestants do not accept 2 Maccabees as canonical Scripture.

Therefore the argument cannot be presented as an unquestionable biblical demonstration. The doctrine also relies on Tradition and theological development.""", """1 Coríntios 3 admite interpretações distintas; seu contexto imediato é a avaliação da obra dos ministros cristãos.

Além disso, muitos protestantes não aceitam 2 Macabeus como Escritura canônica.

Portanto, o argumento não pode ser apresentado como uma demonstração bíblica incontestável. A doutrina se apoia também na Tradição e no desenvolvimento teológico.""")
    ),
    ApologeticsQuestion(
        number = 62,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""Si Cristo murió por nuestros pecados, ¿por qué necesitaríamos el purgatorio?""", """If Christ died for our sins, why would we need purgatory?""", """Se Cristo morreu por nossos pecados, por que precisaríamos do purgatório?"""),
        answer = LocalizedText("""Porque perdonar una culpa y completar la transformación de una persona no son conceptos idénticos.

Una persona puede ser perdonada y conservar todavía apegos, hábitos desordenados o heridas que necesitan sanación.

La doctrina católica comprende la purificación final como una aplicación de la gracia de Cristo, no como una reparación de una supuesta insuficiencia de su sacrificio.

El mismo Cristo que perdona es quien santifica.""", """Because forgiving a fault and completing a person's transformation are not identical concepts.

A person may be forgiven and still retain attachments, disordered habits or wounds needing healing.

Catholic doctrine understands final purification as an application of Christ's grace, not as a repair of a supposed insufficiency of His sacrifice.

The same Christ who forgives is the one who sanctifies.""", """Porque perdoar uma culpa e completar a transformação de uma pessoa não são conceitos idênticos.

Uma pessoa pode ser perdoada e conservar ainda apegos, hábitos desordenados ou feridas que precisam de cura.

A doutrina católica compreende a purificação final como uma aplicação da graça de Cristo, não como uma reparação de uma suposta insuficiência de seu sacrifício.

O mesmo Cristo que perdoa é quem santifica."""),
        foundation = LocalizedText("""Hebreos 10,14; Filipenses 1,6; 1 Corintios 3,15; Catecismo, 1030-1031.""", """Hebrews 10:14; Philippians 1:6; 1 Corinthians 3:15; Catechism, 1030-1031.""", """Hebreus 10,14; Filipenses 1,6; 1 Coríntios 3,15; Catecismo, 1030-1031."""),
        clarification = LocalizedText(""": El argumento no demuestra que esa transformación deba producirse mediante un estado intermedio después de la muerte. Dios podría realizarla de un modo que desconocemos.

La Iglesia afirma la realidad de una purificación final para quienes la necesitan, pero no define su duración ni su naturaleza física.""", """The argument does not demonstrate that such transformation must occur through an intermediate state after death. God could accomplish it in a way we do not know.

The Church affirms the reality of a final purification for those who need it, but it does not define its duration or physical nature.""", """O argumento não demonstra que essa transformação deva ocorrer mediante um estado intermediário depois da morte. Deus poderia realizá-la de um modo que desconhecemos.

A Igreja afirma a realidade de uma purificação final para quem a necessita, mas não define sua duração nem sua natureza física.""")
    ),
    ApologeticsQuestion(
        number = 63,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Basta la fe para salvarse o también son necesarias las obras?""", """Is faith alone enough to be saved, or are works also necessary?""", """Basta a fé para salvar-se ou também são necessárias as obras?"""),
        answer = LocalizedText("""La salvación comienza en la gracia de Dios, no en nuestras obras.

Nadie puede ganar el perdón divino mediante una cantidad suficiente de acciones buenas.

Sin embargo, la fe auténtica no es solamente aceptar intelectualmente que Dios existe. Debe transformar la vida y producir amor.

San Pablo enseña que somos salvados por gracia mediante la fe y creados para las buenas obras.

Santiago afirma que una fe sin obras está muerta.

La lectura católica entiende que ambos describen aspectos complementarios de la vida cristiana.""", """Salvation begins in God's grace, not in our works.

No one can earn divine forgiveness through a sufficient quantity of good actions.

However, authentic faith is not merely intellectually accepting that God exists. It must transform life and produce love.

Saint Paul teaches that we are saved by grace through faith and created for good works.

James affirms that faith without works is dead.

The Catholic reading understands that both describe complementary aspects of Christian life.""", """A salvação começa na graça de Deus, não em nossas obras.

Ninguém pode ganhar o perdão divino mediante uma quantidade suficiente de boas ações.

Entretanto, a fé autêntica não é somente aceitar intelectualmente que Deus existe. Deve transformar a vida e produzir amor.

São Paulo ensina que somos salvos pela graça mediante a fé e criados para as boas obras.

Tiago afirma que uma fé sem obras está morta.

A leitura católica entende que ambos descrevem aspectos complementares da vida cristã."""),
        foundation = LocalizedText("""Efesios 2,8-10; Santiago 2,14-26; Gálatas 5,6; Romanos 3,28; Mateo 25,31-46; Catecismo, 1987-2029.""", """Ephesians 2:8-10; James 2:14-26; Galatians 5:6; Romans 3:28; Matthew 25:31-46; Catechism, 1987-2029.""", """Efésios 2,8-10; Tiago 2,14-26; Gálatas 5,6; Romanos 3,28; Mateus 25,31-46; Catecismo, 1987-2029."""),
        clarification = LocalizedText(""": Muchos protestantes también consideran indispensables las buenas obras como fruto de la fe.

La diferencia doctrinal suele estar en cómo se comprende la justificación y qué relación tienen la gracia, la fe, la transformación interior y las obras.

No debemos caricaturizar la posición protestante como si enseñara que es válido creer en Jesús y vivir deliberadamente haciendo el mal.""", """Many Protestants also consider good works essential as the fruit of faith.

The doctrinal difference usually lies in how justification is understood and what relation grace, faith, interior transformation and works have.

We must not caricature the Protestant position as if it taught that it is valid to believe in Jesus and live deliberately doing evil.""", """Muitos protestantes também consideram indispensáveis as boas obras como fruto da fé.

A diferença doutrinal costuma estar em como se compreende a justificação e que relação têm a graça, a fé, a transformação interior e as obras.

Não devemos caricaturizar a posição protestante como se ensinasse que seja válido crer em Jesus e viver deliberadamente fazendo o mal.""")
    ),
    ApologeticsQuestion(
        number = 64,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Puede salvarse un asesino arrepentido y condenarse alguien que hizo mucho bien?""", """Can a repentant murderer be saved and someone who did much good be condemned?""", """Pode salvar-se um assassino arrependido e condenar-se alguém que fez muito bem?"""),
        answer = LocalizedText("""La misericordia de Dios puede alcanzar incluso al pecador más grave, siempre que exista arrepentimiento verdadero.

El buen ladrón, crucificado junto a Jesús, constituye un ejemplo bíblico de esperanza.

Pero arrepentirse no equivale a pronunciar unas palabras para evitar consecuencias.

Implica reconocer el mal, rechazarlo, querer cambiar y reparar los daños en la medida de lo posible.""", """God's mercy can reach even the gravest sinner, provided there is true repentance.

The good thief, crucified beside Jesus, is a biblical example of hope.

But repenting does not amount to uttering a few words to avoid consequences.

It implies recognizing evil, rejecting it, wanting to change and repairing damage as far as possible.""", """A misericórdia de Deus pode alcançar até o pecador mais grave, sempre que exista arrependimento verdadeiro.

O bom ladrão, crucificado junto a Jesus, constitui um exemplo bíblico de esperança.

Mas arrepender-se não equivale a pronunciar algumas palavras para evitar consequências.

Implica reconhecer o mal, rejeitá-lo, querer mudar e reparar os danos na medida do possível."""),
        foundation = LocalizedText("""Lucas 23,39-43; Lucas 15,11-32; Ezequiel 18,21-32; Mateo 20,1-16.""", """Luke 23:39-43; Luke 15:11-32; Ezekiel 18:21-32; Matthew 20:1-16.""", """Lucas 23,39-43; Lucas 15,11-32; Ezequiel 18,21-32; Mateus 20,1-16."""),
        clarification = LocalizedText(""": La Iglesia no enseña que una persona honrada sea condenada automáticamente por no profesar explícitamente el catolicismo.

Dios conoce la conciencia y la responsabilidad de cada ser humano.

Tampoco significa que un criminal arrepentido deje de estar obligado a enfrentar la justicia humana o a reparar a sus víctimas.""", """The Church does not teach that an honest person is automatically condemned for not explicitly professing Catholicism.

God knows each human being's conscience and responsibility.

Nor does it mean that a repentant criminal ceases to be obliged to face human justice or to repair his victims.""", """A Igreja não ensina que uma pessoa honrada seja condenada automaticamente por não professar explicitamente o catolicismo.

Deus conhece a consciência e a responsabilidade de cada ser humano.

Tampouco significa que um criminoso arrependido deixe de estar obrigado a enfrentar a justiça humana ou a reparar suas vítimas.""")
    ),
    ApologeticsQuestion(
        number = 65,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Por qué sufrimos las consecuencias del pecado de Adán y Eva?""", """Why do we suffer the consequences of Adam and Eve's sin?""", """Por que sofremos as consequências do pecado de Adão e Eva?"""),
        answer = LocalizedText("""La doctrina católica distingue entre cometer personalmente un pecado y nacer dentro de una humanidad cuya relación original con Dios está herida.

El pecado original no significa que cada recién nacido sea personalmente culpable de la decisión de Adán.

San Pablo establece un paralelo entre Adán, con quien entra el pecado en la historia humana, y Cristo, por quien llega la salvación.

La lógica de esta doctrina incluye la solidaridad humana: las decisiones humanas tienen consecuencias que afectan a otros, incluso a generaciones posteriores.""", """Catholic doctrine distinguishes between committing a sin personally and being born into a humanity whose original relation with God is wounded.

Original sin does not mean that each newborn is personally guilty of Adam's decision.

Saint Paul draws a parallel between Adam, with whom sin enters human history, and Christ, through whom salvation comes.

The logic of this doctrine includes human solidarity: human decisions have consequences affecting others, even later generations.""", """A doutrina católica distingue entre cometer pessoalmente um pecado e nascer dentro de uma humanidade cuja relação original com Deus está ferida.

O pecado original não significa que cada recém-nascido seja pessoalmente culpado da decisão de Adão.

São Paulo estabelece um paralelo entre Adão, com quem o pecado entra na história humana, e Cristo, por quem chega a salvação.

A lógica desta doutrina inclui a solidariedade humana: as decisões humanas têm consequências que afetam a outros, inclusive gerações posteriores."""),
        foundation = LocalizedText("""Génesis 3; Romanos 5,12-21; 1 Corintios 15,21-22; Catecismo, 396-409.""", """Genesis 3; Romans 5:12-21; 1 Corinthians 15:21-22; Catechism, 396-409.""", """Gênesis 3; Romanos 5,12-21; 1 Coríntios 15,21-22; Catecismo, 396-409."""),
        clarification = LocalizedText(""": La transmisión del pecado original no puede explicarse simplemente como una herencia genética.

La relación entre el relato bíblico, la historia de la humanidad y la evolución es compleja.

No debemos atribuir a los bebés culpa moral personal por algo que no hicieron.""", """The transmission of original sin cannot be explained simply as genetic inheritance.

The relation between the biblical account, human history and evolution is complex.

We must not attribute personal moral guilt to babies for something they did not do.""", """A transmissão do pecado original não pode ser explicada simplesmente como herança genética.

A relação entre o relato bíblico, a história da humanidade e a evolução é complexa.

Não devemos atribuir aos bebês culpa moral pessoal por algo que não fizeram.""")
    ),
    ApologeticsQuestion(
        number = 66,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Qué diferencia existe entre pecado mortal y venial?""", """What difference exists between mortal and venial sin?""", """Que diferença existe entre pecado mortal e venial?"""),
        answer = LocalizedText("""La Iglesia distingue los pecados según su gravedad y el grado de responsabilidad personal.

Un pecado mortal requiere simultáneamente materia grave, conocimiento suficiente de su gravedad y consentimiento deliberado.

La distinción reconoce que no todos los actos malos tienen la misma importancia moral y que la libertad puede estar afectada por ignorancia, miedo, hábitos, presiones o determinadas circunstancias.""", """The Church distinguishes sins according to their gravity and degree of personal responsibility.

Mortal sin simultaneously requires grave matter, sufficient knowledge of its gravity, and deliberate consent.

The distinction recognizes that not all bad acts have the same moral importance and that freedom can be affected by ignorance, fear, habits, pressures or certain circumstances.""", """A Igreja distingue os pecados segundo sua gravidade e o grau de responsabilidade pessoal.

Um pecado mortal requer simultaneamente matéria grave, conhecimento suficiente de sua gravidade e consentimento deliberado.

A distinção reconhece que nem todos os atos maus têm a mesma importância moral e que a liberdade pode estar afetada por ignorância, medo, hábitos, pressões ou determinadas circunstâncias."""),
        foundation = LocalizedText("""1 Juan 5,16-17; Santiago 1,14-15; Mateo 5,21-22; Catecismo, 1854-1864.""", """1 John 5:16-17; James 1:14-15; Matthew 5:21-22; Catechism, 1854-1864.""", """1 João 5,16-17; Tiago 1,14-15; Mateus 5,21-22; Catecismo, 1854-1864."""),
        clarification = LocalizedText(""": No basta con identificar una conducta objetivamente grave para conocer con certeza la culpabilidad subjetiva de alguien.

Los tres requisitos deben considerarse.

Tampoco corresponde a Catholic Hermes determinar que una persona concreta ha cometido un pecado mortal o afirmar cuál será su destino eterno.""", """It is not enough to identify an objectively grave act to know with certainty someone's subjective culpability.

The three requirements must be considered.

Nor does it belong to Catholic Hermes to determine that a concrete person has committed a mortal sin or to affirm what their eternal destiny will be.""", """Não basta identificar uma conduta objetivamente grave para conhecer com certeza a culpabilidade subjetiva de alguém.

Os três requisitos devem ser considerados.

Tampouco corresponde a Catholic Hermes determinar que uma pessoa concreta cometeu um pecado mortal ou afirmar qual será seu destino eterno.""")
    ),
    ApologeticsQuestion(
        number = 67,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Podría salvarse finalmente toda la humanidad?""", """Could all humanity finally be saved?""", """Poderia salvar-se finalmente toda a humanidade?"""),
        answer = LocalizedText("""La Iglesia enseña que Dios quiere la salvación de todos y que Cristo murió por todos.

Por eso, el cristiano puede y debe desear y pedir la salvación universal.

Sin embargo, también enseña que existe la posibilidad real de condenación definitiva.""", """The Church teaches that God wills the salvation of all and that Christ died for all.

Therefore the Christian can and must desire and ask for universal salvation.

However, it also teaches that there is a real possibility of definitive condemnation.""", """A Igreja ensina que Deus quer a salvação de todos e que Cristo morreu por todos.

Por isso, o cristão pode e deve desejar e pedir a salvação universal.

Entretanto, também ensina que existe a possibilidade real de condenação definitiva."""),
        foundation = LocalizedText("""1 Timoteo 2,3-6; 2 Pedro 3,9; Romanos 11,32; Mateo 25,46; Catecismo, 1033-1037.""", """1 Timothy 2:3-6; 2 Peter 3:9; Romans 11:32; Matthew 25:46; Catechism, 1033-1037.""", """1 Timóteo 2,3-6; 2 Pedro 3,9; Romanos 11,32; Mateus 25,46; Catecismo, 1033-1037."""),
        clarification = LocalizedText(""": No es equivalente esperar que todos se salven y afirmar como doctrina segura que nadie puede condenarse.

La Iglesia no enseña que el infierno esté necesariamente poblado por un número determinado de seres humanos.

La tensión entre el deseo salvífico universal de Dios y el respeto a la libertad humana forma parte del misterio cristiano.

No debemos resolverla inventando certezas que la revelación no ofrece.""", """It is not equivalent to hope that everyone will be saved and to affirm as a secure doctrine that no one can be condemned.

The Church does not teach that hell is necessarily populated by a determined number of human beings.

The tension between God's universal saving will and respect for human freedom is part of the Christian mystery.

We must not resolve it by inventing certainties that revelation does not offer.""", """Não é equivalente esperar que todos se salvem e afirmar como doutrina segura que ninguém pode condenar-se.

A Igreja não ensina que o inferno esteja necessariamente povoado por um número determinado de seres humanos.

A tensão entre o desejo salvífico universal de Deus e o respeito pela liberdade humana faz parte do mistério cristão.

Não devemos resolvê-la inventando certezas que a revelação não oferece.""")
    ),
    ApologeticsQuestion(
        number = 68,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """Salvação, purgatório, pecado e vida eterna"""),
        question = LocalizedText("""¿Qué sucede con quienes nunca conocieron a Jesús?""", """What happens to those who never knew Jesus?""", """O que acontece com quem nunca conheceu Jesus?"""),
        answer = LocalizedText("""Dios no juzga a las personas como si todas hubieran recibido la misma información y las mismas oportunidades.

La enseñanza católica reconoce la posibilidad de salvación de quienes no conocen el Evangelio sin culpa propia, buscan sinceramente la verdad y procuran seguir su conciencia bajo la influencia de la gracia.

Esto no crea una segunda fuente de salvación independiente de Cristo. La Iglesia sostiene que toda salvación procede de Él.""", """God does not judge people as if everyone had received the same information and the same opportunities.

Catholic teaching recognizes the possibility of salvation for those who do not know the Gospel through no fault of their own, sincerely seek the truth, and strive to follow their conscience under the influence of grace.

This does not create a second path of salvation independent of Christ. The Church holds that all salvation comes from Him.""", """Deus não julga as pessoas como se todas tivessem recebido a mesma informação e as mesmas oportunidades.

O ensino católico reconhece a possibilidade de salvação daqueles que não conhecem o Evangelho sem culpa própria, buscam sinceramente a verdade e procuram seguir a consciência sob a influência da graça.

Isso não cria uma segunda fonte de salvação independente de Cristo. A Igreja sustenta que toda salvação procede dele."""),
        foundation = LocalizedText("""Romanos 2,14-16; Hechos 10,34-35; 1 Timoteo 2,4; Catecismo, 846-848.""", """Romans 2:14-16; Acts 10:34-35; 1 Timothy 2:4; Catechism, 846-848.""", """Romanos 2,14-16; Atos 10,34-35; 1 Timóteo 2,4; Catecismo, 846-848."""),
        clarification = LocalizedText("""No significa que cualquier conducta sea automáticamente aceptable porque alguien siga su conciencia.

La conciencia también debe ser formada y puede equivocarse.

Pero tampoco se puede equiparar una ignorancia involuntaria con un rechazo libre y consciente de lo que se reconoce como verdadero.""", """This does not mean that any conduct is automatically acceptable because someone follows their conscience.

Conscience must also be formed and can be mistaken.

But neither can involuntary ignorance be equated with a free and conscious rejection of what one recognizes as true.""", """Isso não significa que qualquer conduta seja automaticamente aceitável porque alguém segue a própria consciência.

A consciência também deve ser formada e pode se equivocar.

Mas também não se pode equiparar uma ignorância involuntária a uma rejeição livre e consciente daquilo que se reconhece como verdadeiro.""")
    ),
    ApologeticsQuestion(
        number = 69,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """Salvação, purgatório, pecado e vida eterna"""),
        question = LocalizedText("""¿Para qué rezar por los muertos si Dios ya conoce su destino?""", """Why pray for the dead if God already knows their destiny?""", """Para que rezar pelos mortos, se Deus já conhece o destino deles?"""),
        answer = LocalizedText("""Porque la oración cristiana no busca informar a Dios de algo que desconoce.

La Iglesia enseña que los creyentes permanecen unidos en Cristo y que la oración puede participar misteriosamente en su obra salvadora.

La oración por los difuntos es coherente con la doctrina católica de la comunión de los santos y la purificación final.""", """Because Christian prayer does not seek to inform God of something He does not know.

The Church teaches that believers remain united in Christ and that prayer can mysteriously participate in His saving work.

Prayer for the dead is coherent with the Catholic doctrine of the communion of saints and final purification.""", """Porque a oração cristã não busca informar a Deus de algo que ele desconheça.

A Igreja ensina que os crentes permanecem unidos em Cristo e que a oração pode participar misteriosamente de sua obra salvífica.

A oração pelos defuntos é coerente com a doutrina católica da comunhão dos santos e da purificação final."""),
        foundation = LocalizedText("""2 Macabeos 12,42-46; 1 Corintios 3,15; 2 Timoteo 1,16-18; Catecismo, 1032 y 958.""", """2 Maccabees 12:42-46; 1 Corinthians 3:15; 2 Timothy 1:16-18; Catechism, 1032 and 958.""", """2 Macabeus 12,42-46; 1 Coríntios 3,15; 2 Timóteo 1,16-18; Catecismo, 1032 e 958."""),
        clarification = LocalizedText("""El pasaje de 2 Timoteo no establece inequívocamente que Onesíforo estuviera muerto cuando Pablo oró por él.

Y 2 Macabeos no es aceptado como canónico por muchos protestantes.

La respuesta más sólida reconoce que esta práctica tiene una base bíblica interpretada dentro de la Tradición, pero no puede presentarse como un mandato explícito del Nuevo Testamento.""", """The passage from 2 Timothy does not unequivocally establish that Onesiphorus was dead when Paul prayed for him.

And 2 Maccabees is not accepted as canonical by many Protestants.

The most solid answer acknowledges that this practice has a biblical basis interpreted within Tradition, but it cannot be presented as an explicit mandate of the New Testament.""", """A passagem de 2 Timóteo não estabelece de maneira inequívoca que Onésíforo estivesse morto quando Paulo orou por ele.

E 2 Macabeus não é aceito como canônico por muitos protestantes.

A resposta mais sólida reconhece que esta prática tem uma base bíblica interpretada dentro da Tradição, mas não pode ser apresentada como um mandato explícito do Novo Testamento.""")
    ),
    ApologeticsQuestion(
        number = 70,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """Salvação, purgatório, pecado e vida eterna"""),
        question = LocalizedText("""¿Qué son las indulgencias y por qué existieron abusos relacionados con ellas?""", """What are indulgences, and why were there abuses connected with them?""", """O que são as indulgências e por que existiram abusos relacionados a elas?"""),
        answer = LocalizedText("""Una indulgencia no es el perdón comprado de un pecado.

La doctrina católica la relaciona con las consecuencias temporales del pecado ya perdonado y con la comunión de los santos.

Para comprenderla hay que distinguir entre la culpa perdonada y los efectos desordenados que el pecado puede dejar.

Por ejemplo, una persona puede ser perdonada por robar y continuar obligada a reparar el daño.""", """An indulgence is not the purchase of forgiveness for a sin.

Catholic doctrine relates it to the temporal consequences of a sin already forgiven and to the communion of saints.

To understand it, one must distinguish between forgiven guilt and the disordered effects that sin can leave behind.

For example, a person can be forgiven for stealing and still be obliged to repair the damage.""", """Uma indulgência não é o perdão comprado de um pecado.

A doutrina católica a relaciona com as consequências temporais do pecado já perdoado e com a comunhão dos santos.

Para compreendê-la, é preciso distinguir entre a culpa perdoada e os efeitos desordenados que o pecado pode deixar.

Por exemplo, uma pessoa pode ser perdoada por roubar e continuar obrigada a reparar o dano."""),
        foundation = LocalizedText("""Mateo 16,19; 1 Corintios 12,26; 2 Samuel 12,13-14; Catecismo, 1471-1479.""", """Matthew 16:19; 1 Corinthians 12:26; 2 Samuel 12:13-14; Catechism, 1471-1479.""", """Mateus 16,19; 1 Coríntios 12,26; 2 Samuel 12,13-14; Catecismo, 1471-1479."""),
        clarification = LocalizedText("""Esa comparación no demuestra por sí misma la doctrina completa de las indulgencias.

Además, históricamente existieron abusos graves relacionados con su predicación y con prácticas económicas.

La crítica a esos abusos fue legítima. Las indulgencias no pueden venderse ni utilizarse como garantía automática de salvación.

Estas preguntas requieren especial cuidado. Para Catholic Hermes, la fidelidad a la enseñanza católica debe ser inseparable del respeto a la dignidad personal.

No sería aceptable utilizar la doctrina para justificar insultos, humillación, rechazo social, violencia o discriminación injusta.""", """That comparison does not by itself prove the full doctrine of indulgences.

Moreover, historically there were grave abuses connected with their preaching and with economic practices.

Criticism of those abuses was legitimate. Indulgences cannot be sold or used as an automatic guarantee of salvation.""", """Essa comparação não demonstra por si mesma a doutrina completa das indulgências.

Além disso, historicamente existiram abusos graves relacionados à sua pregação e a práticas econômicas.

A crítica a esses abusos foi legítima. As indulgências não podem ser vendidas nem utilizadas como garantia automática de salvação.""")
    ),
    ApologeticsQuestion(
        number = 71,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""Si Dios es amor, ¿por qué la Iglesia considera inmorales las relaciones homosexuales?""", """If God is love, why does the Church consider homosexual relationships immoral?""", """Se Deus é amor, por que a Igreja considera imorais as relações homossexuais?"""),
        answer = LocalizedText("""La Iglesia católica no enseña que una persona homosexual tenga menos dignidad o sea menos amada por Dios.

Su juicio moral tradicional se refiere a los actos sexuales, dentro de una comprensión del matrimonio como unión entre hombre y mujer abierta a la vida.

La doctrina moral católica sostiene que la sexualidad tiene una dimensión unitiva y procreativa que debe respetarse.""", """The Catholic Church does not teach that a homosexual person has less dignity or is less loved by God.

Its traditional moral judgement refers to sexual acts, within an understanding of marriage as a union between man and woman open to life.

Catholic moral doctrine holds that sexuality has a unitive and procreative dimension that must be respected.""", """A Igreja católica não ensina que uma pessoa homossexual tenha menos dignidade ou seja menos amada por Deus.

Seu juízo moral tradicional refere-se aos atos sexuais, dentro de uma compreensão do casamento como união entre homem e mulher aberta à vida.

A doutrina moral católica sustenta que a sexualidade tem uma dimensão unitiva e procriativa que deve ser respeitada."""),
        foundation = LocalizedText("""Génesis 1,27-28; Mateo 19,4-6; Romanos 1,26-27; Catecismo, 2357-2359.""", """Genesis 1:27-28; Matthew 19:4-6; Romans 1:26-27; Catechism, 2357-2359.""", """Gênesis 1,27-28; Mateus 19,4-6; Romanos 1,26-27; Catecismo, 2357-2359."""),
        clarification = LocalizedText("""La interpretación de los textos bíblicos sobre relaciones homosexuales es objeto de controversias exegéticas y morales importantes, especialmente en relación con las formas contemporáneas de relaciones afectivas estables entre personas del mismo sexo.

La Iglesia mantiene su enseñanza sobre el matrimonio, pero exige respeto a todas las personas.

La declaración Fiducia supplicans de 2023 abrió la posibilidad de bendiciones pastorales espontáneas de parejas del mismo sexo, sin equipararlas al matrimonio ni cambiar la doctrina sobre este sacramento.""", """The interpretation of the biblical texts on homosexual relationships is the subject of important exegetical and moral controversies, especially in relation to contemporary forms of stable affective relationships between persons of the same sex.

The Church maintains its teaching on marriage, but demands respect for all persons.

The 2023 declaration Fiducia supplicans opened the possibility of spontaneous pastoral blessings of same-sex couples, without equating them with marriage or changing the doctrine on this sacrament.""", """A interpretação dos textos bíblicos sobre relações homossexuais é objeto de importantes controvérsias exegéticas e morais, especialmente em relação às formas contemporâneas de relações afetivas estáveis entre pessoas do mesmo sexo.

A Igreja mantém seu ensino sobre o casamento, mas exige respeito por todas as pessoas.

A declaração Fiducia supplicans de 2023 abriu a possibilidade de bênçãos pastorais espontâneas de casais do mesmo sexo, sem equipará-las ao casamento nem mudar a doutrina sobre este sacramento.""")
    ),
    ApologeticsQuestion(
        number = 72,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué dos personas del mismo sexo que se aman no pueden contraer matrimonio católico?""", """Why cannot two people of the same sex who love each other enter into a Catholic marriage?""", """Por que duas pessoas do mesmo sexo que se amam não podem contrair o casamento católico?"""),
        answer = LocalizedText("""Porque la Iglesia considera que el matrimonio sacramental posee una estructura propia vinculada a la complementariedad sexual del hombre y la mujer y a la apertura a la generación de vida.

Según esta perspectiva, el matrimonio no se define solamente por la intensidad del amor, sino también por una determinada clase de alianza corporal y familiar.""", """Because the Church considers that sacramental marriage has its own structure, linked to the sexual complementarity of man and woman and to openness to the generation of life.

According to this perspective, marriage is not defined only by the intensity of love, but also by a particular kind of bodily and family covenant.""", """Porque a Igreja considera que o casamento sacramental possui uma estrutura própria, ligada à complementaridade sexual do homem e da mulher e à abertura à geração de vida.

Segundo esta perspectiva, o casamento não se define somente pela intensidade do amor, mas também por um determinado tipo de aliança corporal e familiar."""),
        foundation = LocalizedText("""Génesis 2,24; Mateo 19,4-6; Efesios 5,31-32; Catecismo, 1601 y 1652-1654.""", """Genesis 2:24; Matthew 19:4-6; Ephesians 5:31-32; Catechism, 1601 and 1652-1654.""", """Gênesis 2,24; Mateus 19,4-6; Efésios 5,31-32; Catecismo, 1601 e 1652-1654."""),
        clarification = LocalizedText("""Esto no significa que las personas homosexuales sean incapaces de amar sinceramente, ser fieles o cuidar a otros.

Tampoco constituye una demostración de que toda sociedad deba adoptar exactamente la definición sacramental católica en su legislación civil.

Es necesario distinguir el debate teológico sobre el matrimonio sacramental del debate jurídico sobre el matrimonio civil.""", """This does not mean that homosexual persons are incapable of loving sincerely, being faithful, or caring for others.

Nor does it constitute a proof that every society must adopt the Catholic sacramental definition exactly in its civil legislation.

It is necessary to distinguish the theological debate on sacramental marriage from the legal debate on civil marriage.""", """Isso não significa que as pessoas homossexuais sejam incapazes de amar sinceramente, ser fiéis ou cuidar dos outros.

Tampouco constitui uma demonstração de que toda sociedade deva adotar exatamente a definição sacramental católica em sua legislação civil.

É necessário distinguir o debate teológico sobre o casamento sacramental do debate jurídico sobre o casamento civil.""")
    ),
    ApologeticsQuestion(
        number = 73,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué las relaciones sexuales fuera del matrimonio se consideran pecado?""", """Why are sexual relations outside marriage considered a sin?""", """Por que as relações sexuais fora do casamento são consideradas pecado?"""),
        answer = LocalizedText("""La Iglesia entiende la unión sexual como una expresión de entrega personal que debe corresponder a un compromiso estable, exclusivo y público.

El matrimonio busca establecer ese compromiso mediante una alianza de fidelidad y responsabilidad.

La idea central no es que el cuerpo o el deseo sexual sean malos. La sexualidad es considerada un bien que debe integrarse en el amor responsable.""", """The Church understands sexual union as an expression of personal self-giving that must correspond to a stable, exclusive and public commitment.

Marriage seeks to establish that commitment through a covenant of fidelity and responsibility.

The central idea is not that the body or sexual desire is bad. Sexuality is considered a good that must be integrated into responsible love.""", """A Igreja entende a união sexual como uma expressão de entrega pessoal que deve corresponder a um compromisso estável, exclusivo e público.

O casamento busca estabelecer esse compromisso mediante uma aliança de fidelidade e responsabilidade.

A ideia central não é que o corpo ou o desejo sexual sejam maus. A sexualidade é considerada um bem que deve ser integrada ao amor responsável."""),
        foundation = LocalizedText("""Génesis 2,24; Mateo 19,4-6; 1 Corintios 6,18-20; 1 Tesalonicenses 4,3-5; Catecismo, 2337 y 2353.""", """Genesis 2:24; Matthew 19:4-6; 1 Corinthians 6:18-20; 1 Thessalonians 4:3-5; Catechism, 2337 and 2353.""", """Gênesis 2,24; Mateus 19,4-6; 1 Coríntios 6,18-20; 1 Tessalonicenses 4,3-5; Catecismo, 2337 e 2353."""),
        clarification = LocalizedText("""La Biblia no presenta el concepto moderno de noviazgo tal como lo conocemos.

Y el argumento de que las relaciones sexuales requieren matrimonio forma parte de una determinada antropología moral.

La enseñanza católica debe explicarse sin convertir a quienes no la siguen en personas indignas de respeto.""", """The Bible does not present the modern concept of dating as we know it.

And the argument that sexual relations require marriage forms part of a particular moral anthropology.

Catholic teaching must be explained without turning those who do not follow it into persons unworthy of respect.""", """A Bíblia não apresenta o conceito moderno de namoro tal como o conhecemos.

E o argumento de que as relações sexuais requerem o casamento faz parte de uma determinada antropologia moral.

O ensino católico deve ser explicado sem transformar quem não o segue em pessoas indignas de respeito.""")
    ),
    ApologeticsQuestion(
        number = 74,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué rechazar anticonceptivos artificiales y permitir métodos naturales?""", """Why reject artificial contraception and allow natural methods?""", """Por que rejeitar os anticoncepcionais artificiais e permitir métodos naturais?"""),
        answer = LocalizedText("""La doctrina católica sostiene que los actos conyugales deben respetar conjuntamente su significado unitivo y su apertura a la procreación.

Por eso distingue entre evitar deliberadamente la fecundidad de un acto sexual mediante anticoncepción y elegir responsablemente momentos de infertilidad.

La Iglesia reconoce que pueden existir motivos responsables para espaciar los nacimientos.""", """Catholic doctrine holds that conjugal acts must jointly respect their unitive meaning and their openness to procreation.

That is why it distinguishes between deliberately avoiding the fertility of a sexual act through contraception and responsibly choosing times of infertility.

The Church recognizes that there can be responsible reasons for spacing births.""", """A doutrina católica sustenta que os atos conjugais devem respeitar conjuntamente seu significado unitivo e sua abertura à procriação.

Por isso distingue entre evitar deliberadamente a fecundidade de um ato sexual mediante a contracepção e escolher responsavelmente momentos de infertilidade.

A Igreja reconhece que podem existir motivos responsáveis para espaçar os nascimentos."""),
        foundation = LocalizedText("""Génesis 1,28; Génesis 2,24; Catecismo, 2366-2370; encíclica Humanae vitae, 11-16.""", """Genesis 1:28; Genesis 2:24; Catechism, 2366-2370; encyclical Humanae vitae, 11-16.""", """Gênesis 1,28; Gênesis 2,24; Catecismo, 2366-2370; encíclica Humanae vitae, 11-16."""),
        clarification = LocalizedText("""La distinción no constituye una conclusión científica inevitable; es una valoración moral basada en una comprensión específica de la sexualidad.

Tampoco todos los medicamentos que afectan a la fertilidad se utilizan como anticonceptivos: su finalidad terapéutica y sus efectos deben analizarse cuidadosamente.

Esta enseñanza exige una explicación seria y sensible a las dificultades de las familias.""", """The distinction does not constitute an inevitable scientific conclusion; it is a moral assessment based on a specific understanding of sexuality.

Nor are all medications that affect fertility used as contraceptives: their therapeutic purpose and their effects must be carefully analyzed.

This teaching requires a serious explanation sensitive to the difficulties of families.""", """A distinção não constitui uma conclusão científica inevitável; é uma avaliação moral baseada em uma compreensão específica da sexualidade.

Tampouco todos os medicamentos que afetam a fertilidade são usados como anticoncepcionais: sua finalidade terapêutica e seus efeitos devem ser analisados cuidadosamente.

Este ensino exige uma explicação séria e sensível às dificuldades das famílias.""")
    ),
    ApologeticsQuestion(
        number = 75,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué rechazar la fecundación in vitro si ayuda a matrimonios infértiles?""", """Why reject in vitro fertilization if it helps infertile couples?""", """Por que rejeitar a fecundação in vitro, se ajuda casais inférteis?"""),
        answer = LocalizedText("""La Iglesia reconoce que el deseo de tener hijos es legítimo y que la infertilidad puede causar un sufrimiento profundo.

Sin embargo, considera que la generación de una vida humana debe respetar la dignidad del embrión y la unidad entre la procreación y el acto conyugal.

La enseñanza no rechaza la medicina reproductiva por ser artificial. Acepta procedimientos que ayudan a restaurar la fertilidad sin sustituir el acto conyugal.""", """The Church recognizes that the desire to have children is legitimate and that infertility can cause profound suffering.

However, it considers that the generation of a human life must respect the dignity of the embryo and the unity between procreation and the conjugal act.

The teaching does not reject reproductive medicine for being artificial. It accepts procedures that help restore fertility without substituting for the conjugal act.""", """A Igreja reconhece que o desejo de ter filhos é legítimo e que a infertilidade pode causar um sofrimento profundo.

No entanto, considera que a geração de uma vida humana deve respeitar a dignidade do embrião e a unidade entre a procriação e o ato conjugal.

O ensino não rejeita a medicina reprodutiva por ser artificial. Aceita procedimentos que ajudam a restaurar a fertilidade sem substituir o ato conjugal."""),
        foundation = LocalizedText("""Génesis 1,27-28; Salmo 139,13-16; Catecismo, 2373-2379; Dignitas personae, 12-16.""", """Genesis 1:27-28; Psalm 139:13-16; Catechism, 2373-2379; Dignitas personae, 12-16.""", """Gênesis 1,27-28; Salmo 139,13-16; Catecismo, 2373-2379; Dignitas personae, 12-16."""),
        clarification = LocalizedText("""La objeción católica a la fecundación in vitro no depende únicamente de la destrucción de embriones, aunque esta es una preocupación importante.

También cuestiona la separación entre concepción y unión conyugal.

Es una argumentación filosófica y moral debatida. Los niños concebidos mediante estas técnicas tienen exactamente la misma dignidad que cualquier otra persona.""", """The Catholic objection to in vitro fertilization does not depend solely on the destruction of embryos, although this is an important concern.

It also questions the separation between conception and conjugal union.

It is a debated philosophical and moral argument. Children conceived through these techniques have exactly the same dignity as any other person.""", """A objeção católica à fecundação in vitro não depende unicamente da destruição de embriões, embora esta seja uma preocupação importante.

Também questiona a separação entre a concepção e a união conjugal.

É um argumento filosófico e moral debatido. As crianças concebidas por meio dessas técnicas têm exatamente a mesma dignidade que qualquer outra pessoa.""")
    ),
    ApologeticsQuestion(
        number = 76,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia obliga a permanecer en un matrimonio donde existe violencia o sufrimiento?""", """Why does the Church oblige someone to remain in a marriage where there is violence or suffering?""", """Por que a Igreja obriga a permanecer em um casamento onde há violência ou sofrimento?"""),
        answer = LocalizedText("""La Iglesia no obliga a una persona a permanecer físicamente junto a un cónyuge violento.

La separación puede ser legítima, y el divorcio civil puede admitirse cuando sea necesario para proteger derechos, hijos, seguridad o patrimonio.

La indisolubilidad del matrimonio sacramental no significa que una víctima deba soportar agresiones.

La Iglesia también contempla procedimientos para determinar si un matrimonio fue inválido desde su origen.""", """The Church does not oblige a person to remain physically together with a violent spouse.

Separation can be legitimate, and civil divorce can be admitted when necessary to protect rights, children, safety or property.

The indissolubility of sacramental marriage does not mean that a victim must endure aggression.

The Church also contemplates procedures to determine whether a marriage was invalid from its origin.""", """A Igreja não obriga uma pessoa a permanecer fisicamente junto a um cônjuge violento.

A separação pode ser legítima, e o divórcio civil pode ser admitido quando for necessário para proteger direitos, filhos, segurança ou patrimônio.

A indissolubilidade do casamento sacramental não significa que uma vítima deva suportar agressões.

A Igreja também contempla procedimentos para determinar se um casamento foi inválido desde sua origem."""),
        foundation = LocalizedText("""Mateo 19,3-9; 1 Corintios 7,10-11; Catecismo, 2382-2383.""", """Matthew 19:3-9; 1 Corinthians 7:10-11; Catechism, 2382-2383.""", """Mateus 19,3-9; 1 Coríntios 7,10-11; Catecismo, 2382-2383."""),
        clarification = LocalizedText("""La nulidad no es un divorcio católico ni significa que toda relación fracasada fuese inválida.

Puede haber matrimonios válidos que posteriormente se vuelvan profundamente dolorosos.

La cuestión de una nueva unión requiere un discernimiento pastoral y canónico específico.

En una situación de violencia, proteger a la víctima y a los hijos es prioritario.""", """Nullity is not a Catholic divorce, nor does it mean that every failed relationship was invalid.

There can be valid marriages that later become profoundly painful.

The question of a new union requires a specific pastoral and canonical discernment.

In a situation of violence, protecting the victim and the children is a priority.""", """A nulidade não é um divórcio católico, nem significa que toda relação fracassada fosse inválida.

Pode haver casamentos válidos que posteriormente se tornam profundamente dolorosos.

A questão de uma nova união requer um discernimento pastoral e canônico específico.

Em uma situação de violência, proteger a vítima e os filhos é prioritário.""")
    ),
    ApologeticsQuestion(
        number = 77,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué el matrimonio es indisoluble si el amor puede desaparecer?""", """Why is marriage indissoluble if love can disappear?""", """Por que o casamento é indissolúvel, se o amor pode desaparecer?"""),
        answer = LocalizedText("""Porque Jesús presenta el matrimonio como una unión de fidelidad que no depende exclusivamente de los sentimientos cambiantes.

El amor matrimonial incluye decisiones, compromisos y responsabilidades que pueden mantenerse incluso cuando atraviesan dificultades.

La Iglesia considera que el sacramento establece un vínculo estable entre los esposos.""", """Because Jesus presents marriage as a union of fidelity that does not depend exclusively on changing feelings.

Marital love includes decisions, commitments and responsibilities that can be maintained even through difficulties.

The Church considers that the sacrament establishes a stable bond between spouses.""", """Porque Jesus apresenta o casamento como uma união de fidelidade que não depende exclusivamente de sentimentos mutáveis.

O amor matrimonial inclui decisões, compromissos e responsabilidades que podem ser mantidos mesmo quando atravessam dificuldades.

A Igreja considera que o sacramento estabelece um vínculo estável entre os esposos."""),
        foundation = LocalizedText("""Mateo 19,3-9; Marcos 10,2-12; 1 Corintios 7,10-11; Efesios 5,25-33; Catecismo, 1644-1651.""", """Matthew 19:3-9; Mark 10:2-12; 1 Corinthians 7:10-11; Ephesians 5:25-33; Catechism, 1644-1651.""", """Mateus 19,3-9; Marcos 10,2-12; 1 Coríntios 7,10-11; Efésios 5,25-33; Catecismo, 1644-1651."""),
        clarification = LocalizedText("""La permanencia del vínculo no justifica la convivencia obligatoria ante el abuso.

Tampoco significa que todas las crisis matrimoniales tengan solución ni que la persona abandonada sea culpable de la ruptura.

La disciplina sobre nuevas uniones y el acompañamiento de personas divorciadas constituyen cuestiones pastorales delicadas que exigen evitar respuestas automáticas.""", """The permanence of the bond does not justify obligatory cohabitation in face of abuse.

Nor does it mean that all marital crises have a solution, or that the abandoned spouse is guilty of the breakdown.

The discipline on new unions and the accompaniment of divorced persons are delicate pastoral questions that demand avoiding automatic responses.""", """A permanência do vínculo não justifica a convivência obrigatória diante do abuso.

Tampouco significa que todas as crises matrimoniais tenham solução, nem que a pessoa abandonada seja culpada pela ruptura.

A disciplina sobre novas uniões e o acompanhamento de pessoas divorciadas constituem questões pastorais delicadas que exigem evitar respostas automáticas.""")
    ),
    ApologeticsQuestion(
        number = 78,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia cuestiona determinadas concepciones de identidad de género?""", """Why does the Church question certain conceptions of gender identity?""", """Por que a Igreja questiona determinadas concepções de identidade de gênero?"""),
        answer = LocalizedText("""La antropología católica considera que el cuerpo forma parte constitutiva de la persona y que la diferencia sexual posee un significado humano y relacional importante.

No entiende el cuerpo como un objeto completamente separado de la identidad personal.

Desde esta perspectiva, algunas concepciones que desvinculan radicalmente identidad y realidad corporal plantean dificultades teológicas.""", """Catholic anthropology considers that the body is a constitutive part of the person and that sexual difference has an important human and relational significance.

It does not understand the body as an object completely separate from personal identity.

From this perspective, some conceptions that radically disconnect identity and bodily reality raise theological difficulties.""", """A antropologia católica considera que o corpo faz parte constitutiva da pessoa e que a diferença sexual possui um significado humano e relacional importante.

Não entende o corpo como um objeto completamente separado da identidade pessoal.

Desta perspectiva, algumas concepções que desvinculam radicalmente identidade e realidade corporal apresentam dificuldades teológicas."""),
        foundation = LocalizedText("""Génesis 1,27; 1 Corintios 6,19-20; Catecismo, 364-365; Dignitas infinita, 55-60.""", """Genesis 1:27; 1 Corinthians 6:19-20; Catechism, 364-365; Dignitas infinita, 55-60.""", """Gênesis 1,27; 1 Coríntios 6,19-20; Catecismo, 364-365; Dignitas infinita, 55-60."""),
        clarification = LocalizedText("""La vida de las personas transgénero no puede reducirse a una teoría filosófica.

Sus experiencias, necesidades de salud, dignidad y derechos deben tomarse en serio.

La doctrina católica no autoriza el maltrato, la humillación ni la discriminación injusta.

Tampoco todos los casos médicos o psicológicos son idénticos; requieren acompañamiento individual y competencia profesional.""", """The lives of transgender people cannot be reduced to a philosophical theory.

Their experiences, health needs, dignity and rights must be taken seriously.

Catholic doctrine does not authorize mistreatment, humiliation or unjust discrimination.

Nor are all medical or psychological cases identical; they require individual support and professional competence.""", """A vida das pessoas transgênero não pode ser reduzida a uma teoria filosófica.

Suas experiências, necessidades de saúde, dignidade e direitos devem ser levados a sério.

A doutrina católica não autoriza o mau-trato, a humilhação nem a discriminação injusta.

Tampouco todos os casos médicos ou psicológicos são idênticos; requerem acompanhamento individual e competência profissional.""")
    ),
    ApologeticsQuestion(
        number = 79,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia considera moralmente desordenada la masturbación?""", """Why does the Church consider masturbation morally disordered?""", """Por que a Igreja considera moralmente desordenada a masturbação?"""),
        answer = LocalizedText("""La Iglesia comprende la sexualidad dentro de una vocación a la entrega personal, a la integración de los afectos y al amor.

Desde esa antropología, considera que el ejercicio deliberado de la sexualidad separado de su significado conyugal no alcanza el ideal moral que propone.""", """The Church understands sexuality within a vocation to personal self-giving, the integration of the affections, and love.

From that anthropology, it considers that the deliberate exercise of sexuality separated from its conjugal meaning does not reach the moral ideal it proposes.""", """A Igreja compreende a sexualidade dentro de uma vocação à entrega pessoal, à integração dos afetos e ao amor.

Dessa antropologia, considera que o exercício deliberado da sexualidade separado de seu significado conjugal não alcança o ideal moral que propõe."""),
        foundation = LocalizedText("""Mateo 5,27-28; 1 Corintios 6,12-20; Catecismo, 2352.""", """Matthew 5:27-28; 1 Corinthians 6:12-20; Catechism, 2352.""", """Mateus 5,27-28; 1 Coríntios 6,12-20; Catecismo, 2352."""),
        clarification = LocalizedText("""La Biblia no contiene un versículo que nombre explícitamente la masturbación y la prohíba.

Es incorrecto utilizar el relato de Onán como si fuera una prohibición textual directa de esta conducta.

Además, el Catecismo reconoce que factores como inmadurez afectiva, hábitos arraigados, angustia u otras condiciones pueden disminuir la responsabilidad subjetiva.

La enseñanza debe explicarse sin fomentar vergüenza obsesiva, ansiedad religiosa o desprecio por el propio cuerpo.""", """The Bible contains no verse that explicitly names masturbation and prohibits it.

It is incorrect to use the story of Onan as if it were a direct textual prohibition of this conduct.

Moreover, the Catechism acknowledges that factors such as affective immaturity, ingrained habits, anxiety or other conditions can diminish subjective responsibility.

The teaching must be explained without fostering obsessive shame, religious anxiety or contempt for one's own body.""", """A Bíblia não contém um versículo que mencione explicitamente a masturbação e a proíba.

É incorreto utilizar o relato de Onã como se fosse uma proibição textual direta desta conduta.

Além disso, o Catecismo reconhece que fatores como imaturidade afetiva, hábitos arraigados, angústia ou outras condições podem diminuir a responsabilidade subjetiva.

O ensino deve ser explicado sem fomentar vergonha obsessiva, ansiedade religiosa ou desprezo pelo próprio corpo.""")
    ),
    ApologeticsQuestion(
        number = 80,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué se habla tanto de sexualidad y tan poco de pobreza, corrupción e injusticia?""", """Why is there so much talk about sexuality and so little about poverty, corruption and injustice?""", """Por que se fala tanto de sexualidade e tão pouco de pobreza, corrupção e injustiça?"""),
        answer = LocalizedText("""La enseñanza católica no limita la moral al comportamiento sexual.

La Biblia dedica una enorme atención a la justicia, al trato de los pobres, a la honestidad económica y a la condena de la opresión.

La Iglesia enseña que explotar trabajadores, cometer fraudes, enriquecerse injustamente o abandonar a personas vulnerables son problemas morales gravísimos.""", """Catholic teaching does not limit morality to sexual behavior.

The Bible devotes enormous attention to justice, the treatment of the poor, economic honesty, and the condemnation of oppression.

The Church teaches that exploiting workers, committing fraud, unjustly enriching oneself, or abandoning vulnerable persons are most serious moral problems.""", """O ensino católico não limita a moral ao comportamento sexual.

A Bíblia dedica enorme atenção à justiça, ao trato dos pobres, à honestidade econômica e à condenação da opressão.

A Igreja ensina que explorar trabalhadores, cometer fraudes, enriquecer-se injustamente ou abandonar pessoas vulneráveis são problemas morais gravíssimos."""),
        foundation = LocalizedText("""Isaías 1,16-17; Amós 5,21-24; Mateo 25,31-46; Santiago 5,1-6; Catecismo, 2401-2463.""", """Isaiah 1:16-17; Amos 5:21-24; Matthew 25:31-46; James 5:1-6; Catechism, 2401-2463.""", """Isaías 1,16-17; Amós 5,21-24; Mateus 25,31-46; Tiago 5,1-6; Catecismo, 2401-2463."""),
        clarification = LocalizedText("""La crítica a un desequilibrio en determinadas comunidades, predicaciones o medios religiosos puede estar plenamente justificada.

Una catequesis que habla constantemente de pecados sexuales y evita la corrupción, el racismo, el abuso de poder o la miseria no presenta de forma equilibrada el Evangelio.

La fidelidad católica exige considerar toda la moral cristiana, no solamente sus temas más polémicos.""", """Criticism of an imbalance in certain communities, preaching or religious media can be fully justified.

A catechesis that constantly talks about sexual sins and avoids corruption, racism, abuse of power or misery does not present the Gospel in a balanced way.

Catholic fidelity demands considering the whole of Christian morality, not only its most controversial topics.""", """A crítica a um desequilíbrio em determinadas comunidades, pregações ou meios religiosos pode estar plenamente justificada.

Uma catequese que fala constantemente de pecados sexuais e evita a corrupção, o racismo, o abuso de poder ou a miséria não apresenta o Evangelho de forma equilibrada.

A fidelidade católica exige considerar toda a moral cristã, não somente seus temas mais polêmicos.""")
    ),
    ApologeticsQuestion(
        number = 81,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""Si la mujer tiene derecho sobre su cuerpo, ¿por qué la Iglesia condena el aborto?""", """If a woman has a right over her own body, why does the Church condemn abortion?""", """Se a mulher tem direito sobre o próprio corpo, por que a Igreja condena o aborto?"""),
        answer = LocalizedText("""La Iglesia reconoce la dignidad de la mujer y su derecho a la integridad corporal. Su argumento contra el aborto parte de otra consideración: el ser humano en desarrollo dentro del embarazo también merece protección moral.

Desde la concepción comienza el desarrollo de un nuevo organismo humano, aunque existe un debate filosófico sobre cómo relacionar ese hecho biológico con la condición moral y jurídica de persona.

La Iglesia sostiene que la vida humana debe ser respetada desde la concepción y que no es legítimo eliminar directamente una vida inocente.""", """The Church recognizes the dignity of women and their right to bodily integrity. Its argument against abortion starts from another consideration: the human being developing inside pregnancy also deserves moral protection.

From conception begins the development of a new human organism, although there is a philosophical debate about how to relate that biological fact to the moral and legal condition of personhood.

The Church holds that human life must be respected from conception and that it is not legitimate to directly eliminate an innocent life.""", """A Igreja reconhece a dignidade da mulher e seu direito à integridade corporal. Seu argumento contra o aborto parte de outra consideração: o ser humano em desenvolvimento dentro da gravidez também merece proteção moral.

Desde a concepção começa o desenvolvimento de um novo organismo humano, embora exista um debate filosófico sobre como relacionar esse fato biológico com a condição moral e jurídica de pessoa.

A Igreja sustenta que a vida humana deve ser respeitada desde a concepção e que não é legítimo eliminar diretamente uma vida inocente."""),
        foundation = LocalizedText("""Jeremías 1,5; Salmo 139,13-16; Lucas 1,39-44; Catecismo, 2270-2275.""", """Jeremiah 1:5; Psalm 139:13-16; Luke 1:39-44; Catechism, 2270-2275.""", """Jeremias 1,5; Salmo 139,13-16; Lucas 1,39-44; Catecismo, 2270-2275."""),
        clarification = LocalizedText("""Los textos bíblicos citados no constituyen una exposición científica de embriología ni resuelven por sí solos el debate sobre los derechos en conflicto.

Una argumentación completa debe tomar muy en serio la autonomía corporal, la salud, las circunstancias sociales y los riesgos que afrontan las mujeres.

La defensa de la vida prenatal debe acompañarse de apoyo concreto a mujeres embarazadas, madres, niños y familias.""", """The biblical texts cited do not constitute a scientific exposition of embryology, nor do they by themselves resolve the debate on conflicting rights.

A complete argument must take very seriously bodily autonomy, health, social circumstances, and the risks women face.

The defense of prenatal life must be accompanied by concrete support for pregnant women, mothers, children and families.""", """Os textos bíblicos citados não constituem uma exposição científica de embriologia, nem resolvem por si sós o debate sobre os direitos em conflito.

Um argumento completo deve levar muito a sério a autonomia corporal, a saúde, as circunstâncias sociais e os riscos que as mulheres enfrentam.

A defesa da vida prenatal deve ser acompanhada de apoio concreto a mulheres grávidas, mães, crianças e famílias.""")
    ),
    ApologeticsQuestion(
        number = 82,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Por qué la Iglesia se opone al aborto incluso después de una violación?""", """Why does the Church oppose abortion even after a rape?""", """Por que a Igreja se opõe ao aborto mesmo após uma violação?"""),
        answer = LocalizedText("""La Iglesia considera que una violación constituye una injusticia gravísima y que la víctima no tiene ninguna responsabilidad por la agresión sufrida.

Al mismo tiempo, sostiene que la dignidad del ser humano concebido no depende de las circunstancias en las que ocurrió la concepción.

Su razonamiento distingue entre la culpa del agresor y la inocencia del hijo concebido.""", """The Church considers that a rape constitutes a most grave injustice and that the victim bears no responsibility for the aggression she suffered.

At the same time, it holds that the dignity of the conceived human being does not depend on the circumstances in which conception occurred.

Its reasoning distinguishes between the guilt of the aggressor and the innocence of the conceived child.""", """A Igreja considera que uma violação constitui uma injustiça gravíssima e que a vítima não tem nenhuma responsabilidade pela agressão sofrida.

Ao mesmo tempo, sustenta que a dignidade do ser humano concebido não depende das circunstâncias em que ocorreu a concepção.

Seu raciocínio distingue entre a culpa do agressor e a inocência do filho concebido."""),
        foundation = LocalizedText("""Éxodo 20,13; Salmo 139,13-16; Mateo 25,40; Catecismo, 2270-2275.""", """Exodus 20:13; Psalm 139:13-16; Matthew 25:40; Catechism, 2270-2275.""", """Êxodo 20,13; Salmo 139,13-16; Mateus 25,40; Catecismo, 2270-2275."""),
        clarification = LocalizedText("""Este argumento no elimina ni disminuye el sufrimiento de una víctima de violación ni resuelve automáticamente todos los conflictos éticos involucrados.

La respuesta pastoral debe priorizar su seguridad, atención médica, salud mental, protección jurídica y acompañamiento sin culpabilización.

Especialmente cuando se trata de menores, es imprescindible activar los mecanismos de protección correspondientes.

Una respuesta católica no debería convertirse jamás en un reproche contra la víctima.""", """This argument does not eliminate or diminish the suffering of a rape victim, nor does it automatically resolve all the ethical conflicts involved.

The pastoral response must prioritize their safety, medical care, mental health, legal protection, and support without blame.

Especially when minors are involved, it is essential to activate the corresponding protection mechanisms.

A Catholic response should never become a reproach against the victim.""", """Este argumento não elimina nem diminui o sofrimento de uma vítima de violação, nem resolve automaticamente todos os conflitos éticos envolvidos.

A resposta pastoral deve priorizar sua segurança, atendimento médico, saúde mental, proteção jurídica e acompanhamento sem culpabilização.

Especialmente quando se trata de menores, é imprescindível ativar os mecanismos de proteção correspondentes.

Uma resposta católica nunca deveria se converter em um reproche contra a vítima.""")
    ),
    ApologeticsQuestion(
        number = 83,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Cómo distingue la Iglesia entre aborto directo y tratamientos que pueden causar la muerte fetal?""", """How does the Church distinguish between direct abortion and treatments that may cause fetal death?""", """Como a Igreja distingue entre aborto direto e tratamentos que podem causar a morte fetal?"""),
        answer = LocalizedText("""La teología moral católica distingue entre provocar directamente la muerte del hijo como fin o medio y realizar un tratamiento legítimo para salvar la vida de la madre cuando la pérdida fetal no es el objetivo buscado.

Esta distinción se relaciona con el principio del doble efecto.

Por ejemplo, determinados tratamientos para enfermedades graves de la madre pueden ser moralmente evaluados de forma diferente de una intervención cuyo objetivo inmediato sea provocar la muerte fetal.""", """Catholic moral theology distinguishes between directly causing the death of the child as an end or means, and carrying out a legitimate treatment to save the mother's life when fetal loss is not the intended goal.

This distinction is related to the principle of double effect.

For example, certain treatments for serious maternal illnesses can be morally assessed differently from an intervention whose immediate aim is to cause fetal death.""", """A teologia moral católica distingue entre provocar diretamente a morte do filho como fim ou meio e realizar um tratamento legítimo para salvar a vida da mãe quando a perda fetal não é o objetivo buscado.

Esta distinção se relaciona com o princípio do duplo efeito.

Por exemplo, determinados tratamentos para doenças graves da mãe podem ser moralmente avaliados de forma diferente de uma intervenção cujo objetivo imediato seja provocar a morte fetal."""),
        foundation = LocalizedText("""Romanos 3,8; Catecismo, 1750-1756 y 2270-2275.""", """Romans 3:8; Catechism, 1750-1756 and 2270-2275.""", """Romanos 3,8; Catecismo, 1750-1756 e 2270-2275."""),
        clarification = LocalizedText("""Los casos médicos reales son complejos y no todos reciben la misma clasificación entre especialistas católicos.

No debe improvisarse una respuesta clínica a partir de una fórmula moral general.

Ante una emergencia obstétrica, la atención médica inmediata y cualificada es esencial. Catholic Hermes nunca debería sugerir retrasar una intervención necesaria mientras la paciente busca una interpretación teológica.""", """Real medical cases are complex, and not all receive the same classification among Catholic specialists.

A clinical response must not be improvised from a general moral formula.

In an obstetric emergency, immediate and qualified medical care is essential. Catholic Hermes should never suggest delaying a necessary intervention while the patient seeks a theological interpretation.""", """Os casos médicos reais são complexos e nem todos recebem a mesma classificação entre especialistas católicos.

Não se deve improvisar uma resposta clínica a partir de uma fórmula moral geral.

Diante de uma emergência obstétrica, o atendimento médico imediato e qualificado é essencial. O Catholic Hermes nunca deveria sugerir atrasar uma intervenção necessária enquanto a paciente busca uma interpretação teológica.""")
    ),
    ApologeticsQuestion(
        number = 84,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Por qué la Iglesia se opone a la eutanasia voluntaria?""", """Why does the Church oppose voluntary euthanasia?""", """Por que a Igreja se opõe à eutanásia voluntária?"""),
        answer = LocalizedText("""Porque considera que la dignidad humana no depende de la autonomía, la productividad, la ausencia de dolor o la percepción de calidad de vida.

Desde su perspectiva, provocar deliberadamente la muerte de una persona para terminar con su sufrimiento no equivale moralmente a aliviar ese sufrimiento.

La doctrina católica defiende los cuidados paliativos y permite rechazar tratamientos desproporcionados o extraordinarios cuando solamente prolongan de forma gravosa el proceso de morir.

También admite el tratamiento adecuado del dolor, incluso cuando puede tener efectos secundarios graves no buscados.""", """Because it considers that human dignity does not depend on autonomy, productivity, absence of pain, or perceived quality of life.

From its perspective, deliberately causing a person's death to end their suffering is not morally equivalent to relieving that suffering.

Catholic doctrine defends palliative care and allows refusing disproportionate or extraordinary treatments when they only gravely prolong the dying process.

It also admits adequate treatment of pain, even when it may have serious unwanted side effects.""", """Porque considera que a dignidade humana não depende da autonomia, da produtividade, da ausência de dor ou da percepção de qualidade de vida.

De sua perspectiva, provocar deliberadamente a morte de uma pessoa para pôr fim ao seu sofrimento não equivale moralmente a aliviar esse sofrimento.

A doutrina católica defende os cuidados paliativos e permite recusar tratamentos desproporcionados ou extraordinários quando somente prolongam de forma gravosa o processo de morrer.

Também admite o tratamento adequado da dor, mesmo quando pode ter efeitos colaterais graves não buscados."""),
        foundation = LocalizedText("""Éxodo 20,13; Mateo 25,36; Catecismo, 2276-2279.""", """Exodus 20:13; Matthew 25:36; Catechism, 2276-2279.""", """Êxodo 20,13; Mateus 25,36; Catecismo, 2276-2279."""),
        clarification = LocalizedText("""El sufrimiento extremo y el deseo de morir no deben trivializarse.

La respuesta cristiana requiere atención médica, acompañamiento humano, alivio del dolor y respeto a la persona.

Existe un debate ético serio sobre autonomía, dignidad y final de la vida que debe presentarse con fidelidad.""", """Extreme suffering and the desire to die must not be trivialized.

The Christian response requires medical care, human support, relief of pain, and respect for the person.

There is a serious ethical debate on autonomy, dignity and the end of life that must be presented faithfully.""", """O sofrimento extremo e o desejo de morrer não devem ser trivializados.

A resposta cristã requer atenção médica, acompanhamento humano, alívio da dor e respeito à pessoa.

Existe um debate ético sério sobre autonomia, dignidade e fim da vida que deve ser apresentado com fidelidade.""")
    ),
    ApologeticsQuestion(
        number = 85,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Puede un católico ser policía o militar si su trabajo puede exigir matar?""", """Can a Catholic be a policeman or soldier if his work may require killing?""", """Pode um católico ser policial ou militar, se seu trabalho pode exigir matar?"""),
        answer = LocalizedText("""Sí. La Iglesia no considera moralmente ilícitas por naturaleza las profesiones policial o militar.

Distingue entre el homicidio injusto y el uso proporcionado de la fuerza para proteger a personas frente a una agresión grave e injusta.

Juan Bautista no exige a los soldados que abandonen su profesión; les pide que no extorsionen ni abusen de su poder.

La Iglesia enseña que la legítima defensa puede llegar a ser un deber para quien debe proteger otras vidas.""", """Yes. The Church does not consider the police or military professions morally illicit by nature.

It distinguishes between unjust homicide and the proportionate use of force to protect people from grave and unjust aggression.

John the Baptist does not demand that soldiers abandon their profession; he asks them not to extort or abuse their power.

The Church teaches that legitimate defense can be a duty for one who must protect other lives.""", """Sim. A Igreja não considera moralmente ilícitas por natureza as profissões policial ou militar.

Distingue entre o homicídio injusto e o uso proporcionado da força para proteger pessoas contra uma agressão grave e injusta.

João Batista não exige que os soldados abandonem sua profissão; pede-lhes que não extorqam nem abusem de seu poder.

A Igreja ensina que a legítima defesa pode chegar a ser um dever para quem deve proteger outras vidas."""),
        foundation = LocalizedText("""Lucas 3,14; Romanos 13,1-4; Mateo 8,5-13; Catecismo, 2263-2265 y 2310-2313.""", """Luke 3:14; Romans 13:1-4; Matthew 8:5-13; Catechism, 2263-2265 and 2310-2313.""", """Lucas 3,14; Romanos 13,1-4; Mateus 8,5-13; Catecismo, 2263-2265 e 2310-2313."""),
        clarification = LocalizedText("""Esto no significa que cualquier acción realizada bajo uniforme sea legítima.

La fuerza debe ser necesaria y proporcionada. Las ejecuciones arbitrarias, la tortura, los ataques contra inocentes y las órdenes manifiestamente injustas no quedan justificados por la obediencia debida.""", """This does not mean that any action carried out in uniform is legitimate.

Force must be necessary and proportionate. Arbitrary executions, torture, attacks against the innocent, and manifestly unjust orders are not justified by due obedience.""", """Isso não significa que qualquer ação realizada de uniforme seja legítima.

A força deve ser necessária e proporcionada. As execuções arbitrárias, a tortura, os ataques contra inocentes e as ordens manifestamente injustas não ficam justificadas pela obediência devida.""")
    ),
    ApologeticsQuestion(
        number = 86,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""Si Jesús enseñó a poner la otra mejilla, ¿cómo puede justificarse una guerra?""", """If Jesus taught to turn the other cheek, how can a war be justified?""", """Se Jesus ensinou a pôr a outra face, como pode justificar-se uma guerra?"""),
        answer = LocalizedText("""La Iglesia considera que la paz es el objetivo moral y que la guerra constituye una tragedia que debe evitarse.

Sin embargo, distingue la renuncia personal a la venganza de la responsabilidad de proteger a una población contra una agresión injusta.

La tradición de la guerra justa establece condiciones rigurosas: daño grave y cierto, último recurso, posibilidades serias de éxito y proporcionalidad de los medios empleados.""", """The Church considers that peace is the moral goal and that war is a tragedy that must be avoided.

However, it distinguishes personal renunciation of vengeance from the responsibility to protect a population against unjust aggression.

The just war tradition establishes rigorous conditions: grave and certain harm, last resort, serious possibilities of success, and proportionality of the means employed.""", """A Igreja considera que a paz é o objetivo moral e que a guerra constitui uma tragédia que deve ser evitada.

No entanto, distingue a renúncia pessoal à vingança da responsabilidade de proteger uma população contra uma agressão injusta.

A tradição da guerra justa estabelece condições rigorosas: dano grave e certo, último recurso, possibilidades sérias de sucesso e proporcionalidade dos meios empregados."""),
        foundation = LocalizedText("""Mateo 5,38-44; Romanos 12,17-21; Romanos 13,4; Catecismo, 2307-2317.""", """Matthew 5:38-44; Romans 12:17-21; Romans 13:4; Catechism, 2307-2317.""", """Mateus 5,38-44; Romanos 12,17-21; Romanos 13,4; Catecismo, 2307-2317."""),
        clarification = LocalizedText("""No basta que un gobierno declare justa una guerra para que realmente lo sea.

La guerra moderna plantea enormes dificultades de proporcionalidad y protección de civiles.

La enseñanza católica exige evitar las hostilidades, buscar vías diplomáticas y respetar la dignidad humana incluso durante los conflictos armados.""", """It is not enough for a government to declare a war just for it really to be so.

Modern war raises enormous difficulties of proportionality and protection of civilians.

Catholic teaching requires avoiding hostilities, seeking diplomatic ways, and respecting human dignity even during armed conflicts.""", """Não basta que um governo declare justa uma guerra para que realmente o seja.

A guerra moderna apresenta enormes dificuldades de proporcionalidade e proteção de civis.

O ensino católico exige evitar as hostilidades, buscar vias diplomáticas e respeitar a dignidade humana mesmo durante os conflitos armados.""")
    ),
    ApologeticsQuestion(
        number = 87,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Por qué cambió la enseñanza sobre la pena de muerte?""", """Why did the teaching on the death penalty change?""", """Por que mudou o ensino sobre a pena de morte?"""),
        answer = LocalizedText("""Históricamente, autoridades y teólogos católicos consideraron admisible la pena capital bajo determinadas condiciones.

La formulación actual del Catecismo enseña que es inadmisible y promueve su abolición.

La explicación oficial relaciona este desarrollo con una comprensión más profunda de la dignidad humana, de las penas y de la capacidad del Estado para proteger a la sociedad sin quitar definitivamente la vida al condenado.""", """Historically, Catholic authorities and theologians considered the death penalty admissible under certain conditions.

The current formulation of the Catechism teaches that it is inadmissible and promotes its abolition.

The official explanation relates this development to a deeper understanding of human dignity, of punishment, and of the State's capacity to protect society without definitively taking the condemned person's life.""", """Historicamente, autoridades e teólogos católicos consideraram admissível a pena capital sob determinadas condições.

A formulação atual do Catecismo ensina que ela é inadmissível e promove sua abolição.

A explicação oficial relaciona este desenvolvimento com uma compreensão mais profunda da dignidade humana, das penas e da capacidade do Estado de proteger a sociedade sem tirar definitivamente a vida do condenado."""),
        foundation = LocalizedText("""Génesis 9,6; Romanos 13,4; Mateo 5,38-48; Catecismo, 2267.""", """Genesis 9:6; Romans 13:4; Matthew 5:38-48; Catechism, 2267.""", """Gênesis 9,6; Romanos 13,4; Mateus 5,38-48; Catecismo, 2267."""),
        clarification = LocalizedText("""Este es un ejemplo importante para debatir la continuidad y el desarrollo de la doctrina moral.

No sería honesto afirmar que la postura histórica y la actual utilizan exactamente la misma formulación.

La Iglesia presenta la enseñanza vigente como un desarrollo doctrinal a la luz del Evangelio y de una mayor comprensión de la dignidad personal.""", """This is an important example for debating the continuity and development of moral doctrine.

It would not be honest to claim that the historical and current positions use exactly the same formulation.

The Church presents the current teaching as a doctrinal development in light of the Gospel and of a greater understanding of personal dignity.""", """Este é um exemplo importante para debater a continuidade e o desenvolvimento da doutrina moral.

Não seria honesto afirmar que a postura histórica e a atual utilizam exatamente a mesma formulação.

A Igreja apresenta o ensino vigente como um desenvolvimento doutrinal à luz do Evangelho e de uma maior compreensão da dignidade pessoal.""")
    ),
    ApologeticsQuestion(
        number = 88,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Cómo pudieron existir esclavitud y racismo en sociedades católicas?""", """How could slavery and racism exist in Catholic societies?""", """Como puderam existir escravidão e racismo em sociedades católicas?"""),
        answer = LocalizedText("""Porque pertenecer a una religión no garantiza que las personas y las instituciones vivan de acuerdo con sus principios.

La fe cristiana contiene fundamentos muy fuertes para la igualdad de dignidad: todos son creados a imagen de Dios y llamados a la unidad en Cristo.

Estos fundamentos fueron utilizados por cristianos para combatir la esclavitud y defender a pueblos oprimidos.""", """Because belonging to a religion does not guarantee that people and institutions live according to its principles.

The Christian faith contains very strong foundations for the equality of dignity: all are created in the image of God and called to unity in Christ.

These foundations were used by Christians to combat slavery and defend oppressed peoples.""", """Porque pertencer a uma religião não garante que as pessoas e as instituições vivam de acordo com seus princípios.

A fé cristã contém fundamentos muito fortes para a igualdade de dignidade: todos são criados à imagem de Deus e chamados à unidade em Cristo.

Estes fundamentos foram utilizados por cristãos para combater a escravidão e defender povos oprimidos."""),
        foundation = LocalizedText("""Génesis 1,27; Gálatas 3,28; Filemón 15-16; Santiago 2,1-9; Catecismo, 1934-1938.""", """Genesis 1:27; Galatians 3:28; Philemon 15-16; James 2:1-9; Catechism, 1934-1938.""", """Gênesis 1,27; Gálatas 3,28; Filemom 15-16; Tiago 2,1-9; Catecismo, 1934-1938."""),
        clarification = LocalizedText("""También hubo cristianos que justificaron sistemas esclavistas y discriminatorios.

Debemos reconocer esa contradicción y estudiar con precisión las posiciones históricas, incluidas las ambigüedades institucionales.

La existencia de principios igualitarios en la Biblia no demuestra que todos los cristianos los aplicaran correctamente.

La apologética responsable distingue la verdad de una enseñanza de la fidelidad histórica de quienes dicen seguirla.""", """There were also Christians who justified slaveholding and discriminatory systems.

We must acknowledge that contradiction and study historical positions precisely, including institutional ambiguities.

The existence of egalitarian principles in the Bible does not prove that all Christians applied them correctly.

Responsible apologetics distinguishes the truth of a teaching from the historical fidelity of those who claim to follow it.""", """Também houve cristãos que justificaram sistemas escravistas e discriminatórios.

Devemos reconhecer essa contradição e estudar com precisão as posições históricas, incluídas as ambiguidades institucionais.

A existência de princípios igualitários na Bíblia não demonstra que todos os cristãos os aplicaram corretamente.

A apologética responsável distingue a verdade de um ensino da fidelidade histórica de quem diz segui-lo.""")
    ),
    ApologeticsQuestion(
        number = 89,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Puede ser buen católico alguien inmensamente rico?""", """Can someone immensely rich be a good Catholic?""", """Pode um católico ser immensamente rico?"""),
        answer = LocalizedText("""La Iglesia no enseña que poseer bienes sea automáticamente pecado, pero advierte seriamente contra la avaricia, la explotación y la indiferencia hacia los pobres.

Jesús enseña que la riqueza puede convertirse en un obstáculo espiritual cuando ocupa el lugar de Dios o produce insensibilidad ante el sufrimiento ajeno.

El derecho a la propiedad está subordinado a exigencias de justicia y al destino universal de los bienes.""", """The Church does not teach that possessing goods is automatically a sin, but it seriously warns against greed, exploitation, and indifference toward the poor.

Jesus teaches that wealth can become a spiritual obstacle when it occupies the place of God or produces insensitivity to others' suffering.

The right to property is subordinated to the demands of justice and to the universal destination of goods.""", """A Igreja não ensina que possuir bens seja automaticamente pecado, mas adverte seriamente contra a avareza, a exploração e a indiferença para com os pobres.

Jesus ensina que a riqueza pode se tornar um obstáculo espiritual quando ocupa o lugar de Deus ou produz insensibilidade diante do sofrimento alheio.

O direito à propriedade está subordinado às exigências da justiça e ao destino universal dos bens."""),
        foundation = LocalizedText("""Marcos 10,17-27; Lucas 12,15-21; Lucas 19,1-10; Santiago 5,1-6; Catecismo, 2401-2406 y 2443-2449.""", """Mark 10:17-27; Luke 12:15-21; Luke 19:1-10; James 5:1-6; Catechism, 2401-2406 and 2443-2449.""", """Marcos 10,17-27; Lucas 12,15-21; Lucas 19,1-10; Tiago 5,1-6; Catecismo, 2401-2406 e 2443-2449."""),
        clarification = LocalizedText("""No basta donar una pequeña parte de una fortuna para justificar cualquier forma de acumulación.

La moral católica exige examinar cómo se obtiene la riqueza, cómo se administra y qué responsabilidad existe frente a los trabajadores, la comunidad y las personas vulnerables.""", """Donating a small part of a fortune is not enough to justify any form of accumulation.

Catholic morality demands examining how wealth is obtained, how it is administered, and what responsibility exists toward workers, the community, and vulnerable persons.""", """Não basta doar uma pequena parte de uma fortuna para justificar qualquer forma de acumulação.

A moral católica exige examinar como a riqueza é obtida, como é administrada e qual responsabilidade existe frente aos trabalhadores, à comunidade e às pessoas vulneráveis.""")
    ),
    ApologeticsQuestion(
        number = 90,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Hasta dónde obliga la fe a ayudar a pobres, inmigrantes y refugiados?""", """How far does faith oblige us to help the poor, immigrants and refugees?""", """Até onde a fé obriga a ajudar pobres, imigrantes e refugiados?"""),
        answer = LocalizedText("""El cristianismo considera la atención a las personas necesitadas una obligación moral, no una actividad secundaria.

Jesús identifica el servicio prestado a los más vulnerables con el amor dirigido a Él.

La Iglesia reconoce deberes de solidaridad, hospitalidad y protección de la dignidad humana.""", """Christianity considers care for people in need a moral obligation, not a secondary activity.

Jesus identifies the service rendered to the most vulnerable with the love directed to Him.

The Church recognizes duties of solidarity, hospitality, and protection of human dignity.""", """O cristianismo considera a atenção às pessoas necessitadas uma obrigação moral, não uma atividade secundária.

Jesus identifica o serviço prestado aos mais vulneráveis com o amor dirigido a ele.

A Igreja reconhece deveres de solidariedade, hospitalidade e proteção da dignidade humana."""),
        foundation = LocalizedText("""Mateo 25,31-46; Levítico 19,33-34; Hebreos 13,2; Santiago 2,14-17; Catecismo, 2241 y 2443-2449.""", """Matthew 25:31-46; Leviticus 19:33-34; Hebrews 13:2; James 2:14-17; Catechism, 2241 and 2443-2449.""", """Mateus 25,31-46; Levítico 19,33-34; Hebreus 13,2; Tiago 2,14-17; Catecismo, 2241 e 2443-2449."""),
        clarification = LocalizedText("""La doctrina no prescribe una única política migratoria o un modelo económico detallado para todos los países.

Los Estados pueden establecer regulaciones legítimas, siempre que respeten los derechos fundamentales.

Por eso, dos católicos pueden discrepar sobre medidas concretas sin que toda diferencia política implique necesariamente una traición a la fe.

Lo que no puede descartarse es la dignidad del extranjero, del refugiado o de la persona pobre.""", """Doctrine does not prescribe a single migration policy or a detailed economic model for all countries.

States can establish legitimate regulations, as long as they respect fundamental rights.

Therefore, two Catholics can disagree on concrete measures without every political difference necessarily implying a betrayal of the faith.

What cannot be discarded is the dignity of the foreigner, the refugee, or the poor person.""", """A doutrina não prescreve uma única política migratória ou um modelo econômico detalhado para todos os países.

Os Estados podem estabelecer regulamentações legítimas, sempre que respeitem os direitos fundamentais.

Por isso, dois católicos podem discordar sobre medidas concretas sem que toda diferença política implique necessariamente uma traição à fé.

O que não pode ser descartado é a dignidade do estrangeiro, do refugiado ou da pessoa pobre.""")
    ),
    ApologeticsQuestion(
        number = 91,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Por qué los sacerdotes no pueden casarse si Pedro estuvo casado?""", """Why cannot priests marry if Peter was married?""", """Por que os sacerdotes não podem se casar se Pedro era casado?"""),
        answer = LocalizedText("""La Biblia no establece una prohibición universal del matrimonio para todos los ministros cristianos.

Pedro tenía suegra y san Pablo reconoce que algunos apóstoles estaban casados.

La disciplina habitual de la Iglesia latina elige para el presbiterado a hombres que asumen el celibato por el Reino de Dios.

Se considera una forma de disponibilidad especial para el servicio pastoral.""", """The Bible does not establish a universal prohibition of marriage for all Christian ministers.

Peter had a mother-in-law, and Saint Paul acknowledges that some apostles were married.

The usual discipline of the Latin Church chooses for the priesthood men who assume celibacy for the sake of the Kingdom of God.

It is considered a form of special availability for pastoral service.""", """A Bíblia não estabelece uma proibição universal do casamento para todos os ministros cristãos.

Pedro tinha sogra, e são Paulo reconhece que alguns apóstolos eram casados.

A disciplina habitual da Igreja latina escolhe para o presbitério homens que assumem o celibato pelo Reino de Deus.

Considera-se uma forma de disponibilidade especial para o serviço pastoral."""),
        foundation = LocalizedText("""Mateo 8,14; 1 Corintios 9,5; 1 Timoteo 3,2; Mateo 19,12; 1 Corintios 7,32-35.""", """Matthew 8:14; 1 Corinthians 9:5; 1 Timothy 3:2; Matthew 19:12; 1 Corinthians 7:32-35.""", """Mateus 8,14; 1 Coríntios 9,5; 1 Timóteo 3,2; Mateus 19,12; 1 Coríntios 7,32-35."""),
        clarification = LocalizedText("""El celibato sacerdotal latino es fundamentalmente una disciplina eclesiástica, no una prohibición bíblica universal.

Las Iglesias católicas orientales pueden ordenar como presbíteros a hombres casados bajo sus propias normas. También existen excepciones en la Iglesia latina.

Por tanto, sería incorrecto afirmar que Dios prohíbe casarse a todos los sacerdotes.""", """Latin priestly celibacy is fundamentally an ecclesiastical discipline, not a universal biblical prohibition.

Eastern Catholic Churches can ordain married men as priests under their own norms. There are also exceptions in the Latin Church.

Therefore, it would be incorrect to claim that God forbids all priests to marry.""", """O celibato sacerdotal latino é fundamentalmente uma disciplina eclesiástica, não uma proibição bíblica universal.

As Igrejas católicas orientais podem ordenar como presbíteros homens casados, segundo suas próprias normas. Também existem exceções na Igreja latina.

Portanto, seria incorreto afirmar que Deus proíbe todos os sacerdotes de se casarem.""")
    ),
    ApologeticsQuestion(
        number = 92,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Por qué las monjas y los religiosos renuncian al matrimonio?""", """Why do nuns and religious renounce marriage?""", """Por que as monjas e os religiosos renunciam ao casamento?"""),
        answer = LocalizedText("""Porque la vida consagrada propone una forma particular de seguir a Cristo mediante los consejos evangélicos de pobreza, castidad y obediencia.

Jesús reconoce que algunas personas renuncian libremente al matrimonio por el Reino de los Cielos.

San Pablo también valora la posibilidad de una vida célibe dedicada al servicio de Dios.

La Iglesia considera que tanto el matrimonio como la vida consagrada pueden ser caminos de santidad.""", """Because consecrated life proposes a particular way of following Christ through the evangelical counsels of poverty, chastity and obedience.

Jesus acknowledges that some people freely renounce marriage for the sake of the Kingdom of Heaven.

Saint Paul also values the possibility of a celibate life dedicated to the service of God.

The Church considers that both marriage and consecrated life can be paths of holiness.""", """Porque a vida consagrada propõe uma forma particular de seguir a Cristo mediante os conselhos evangélicos de pobreza, castidade e obediência.

Jesus reconhece que algumas pessoas renunciam livremente ao casamento pelo Reino dos Céus.

São Paulo também valoriza a possibilidade de uma vida celibatária dedicada ao serviço de Deus.

A Igreja considera que tanto o casamento quanto a vida consagrada podem ser caminhos de santidade."""),
        foundation = LocalizedText("""Mateo 19,10-12; 1 Corintios 7,7-8.32-35; Catecismo, 915-933.""", """Matthew 19:10-12; 1 Corinthians 7:7-8.32-35; Catechism, 915-933.""", """Mateus 19,10-12; 1 Coríntios 7,7-8.32-35; Catecismo, 915-933."""),
        clarification = LocalizedText("""La vida célibe no demuestra que el matrimonio sea malo o inferior en dignidad humana.

La vocación religiosa debe ser libre y madura. Ninguna persona debería ser presionada para emitir votos ni permanecer sometida a relaciones de obediencia abusiva.

La castidad consagrada es un compromiso espiritual, no un desprecio de la sexualidad.""", """Celibate life does not prove that marriage is bad or inferior in human dignity.

The religious vocation must be free and mature. No person should be pressured to pronounce vows or remain subject to abusive relationships of obedience.

Consecrated chastity is a spiritual commitment, not a contempt for sexuality.""", """A vida celibatária não demonstra que o casamento seja mau ou inferior em dignidade humana.

A vocação religiosa deve ser livre e madura. Nenhuma pessoa deveria ser pressionada a emitir votos nem a permanecer submetida a relações de obediência abusiva.

A castidade consagrada é um compromisso espiritual, não um desprezo pela sexualidade.""")
    ),
    ApologeticsQuestion(
        number = 93,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Por qué las mujeres no pueden recibir la ordenación sacerdotal?""", """Why cannot women receive priestly ordination?""", """Por que as mulheres não podem receber a ordenação sacerdotal?"""),
        answer = LocalizedText("""La Iglesia católica sostiene que no posee autoridad para conferir el sacerdocio ministerial a las mujeres.

La explicación oficial se fundamenta en la elección de los Doce por Jesucristo, la práctica histórica recibida y la comprensión sacramental del ministerio.

La doctrina distingue entre igualdad de dignidad y diversidad de funciones eclesiales.""", """The Catholic Church holds that it does not have authority to confer ministerial priesthood on women.

The official explanation is based on Christ's choice of the Twelve, the historical practice received, and the sacramental understanding of ministry.

Doctrine distinguishes between equality of dignity and diversity of ecclesial functions.""", """A Igreja católica sustenta que não possui autoridade para conferir o sacerdócio ministerial às mulheres.

A explicação oficial se fundamenta na escolha dos Doze por Jesus Cristo, na prática histórica recebida e na compreensão sacramental do ministério.

A doutrina distingue entre igualdade de dignidade e diversidade de funções eclesiais."""),
        foundation = LocalizedText("""Marcos 3,13-19; Lucas 6,12-16; Efesios 5,23-32; Catecismo, 1577; Ordinatio sacerdotalis (1994).""", """Mark 3:13-19; Luke 6:12-16; Ephesians 5:23-32; Catechism, 1577; Ordinatio sacerdotalis (1994).""", """Marcos 3,13-19; Lucas 6,12-16; Efésios 5,23-32; Catecismo, 1577; Ordinatio sacerdotalis (1994)."""),
        clarification = LocalizedText("""La existencia de doce apóstoles varones no constituye por sí sola una demostración lógica de que ninguna mujer pueda ser ordenada.

El argumento católico depende de una interpretación particular de la intención de Cristo y de la autoridad de la Tradición apostólica.

El Magisterio ha enseñado que esta posición debe mantenerse definitivamente. No se trata simplemente de una disciplina modificable como el celibato sacerdotal.""", """The existence of twelve male apostles does not by itself constitute a logical proof that no woman can be ordained.

The Catholic argument depends on a particular interpretation of Christ's intention and on the authority of Apostolic Tradition.

The Magisterium has taught that this position must be held definitively. It is not simply a modifiable discipline like priestly celibacy.""", """A existência de doze apóstolos varões não constitui por si só uma demonstração lógica de que nenhuma mulher possa ser ordenada.

O argumento católico depende de uma interpretação particular da intenção de Cristo e da autoridade da Tradição apostólica.

O Magistério ensinou que esta posição deve ser mantida definitivamente. Não se trata simplesmente de uma disciplina modificável como o celibato sacerdotal.""")
    ),
    ApologeticsQuestion(
        number = 94,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Por qué una jerarquía mayoritariamente masculina decide sobre matrimonio y familia?""", """Why does a mostly male hierarchy decide on marriage and family?""", """Por que uma hierarquia majoritariamente masculina decide sobre casamento e família?"""),
        answer = LocalizedText("""La autoridad eclesial no significa que un obispo o sacerdote conozca por experiencia personal todas las situaciones sobre las que debe enseñar.

Una persona puede comprender razonablemente una realidad mediante el estudio, el diálogo y la escucha, aunque no haya vivido personalmente cada aspecto.

Sin embargo, la Biblia también presenta a mujeres desempeñando funciones importantes en la misión cristiana, como Priscila, Febe y otras colaboradoras de Pablo.

Fundamento adicional: Romanos 16,1-7; Hechos 18,24-26.""", """Ecclesial authority does not mean that a bishop or priest knows by personal experience every situation he must teach about.

A person can reasonably understand a reality through study, dialogue and listening, even without having personally lived every aspect.

However, the Bible also presents women performing important functions in the Christian mission, such as Priscilla, Phoebe, and other collaborators of Paul.

Additional foundation: Romans 16:1-7; Acts 18:24-26.""", """A autoridade eclesial não significa que um bispo ou sacerdote conheça por experiência pessoal todas as situações sobre as quais deve ensinar.

Uma pessoa pode compreender razoavelmente uma realidade mediante o estudo, o diálogo e a escuta, embora não tenha vivido pessoalmente cada aspecto.

No entanto, a Bíblia também apresenta mulheres desempenhando funções importantes na missão cristã, como Priscila, Febe e outras colaboradoras de Paulo.

Fundamento adicional: Romanos 16,1-7; Atos 18,24-26."""),
        foundation = LocalizedText("""1 Corintios 12,4-27; Romanos 12,4-8; Gálatas 3,28.""", """1 Corinthians 12:4-27; Romans 12:4-8; Galatians 3:28.""", """1 Coríntios 12,4-27; Romanos 12,4-8; Gálatas 3,28."""),
        clarification = LocalizedText("""La observación crítica es legítima: la ausencia de suficiente participación de mujeres y laicos puede empobrecer la comprensión pastoral.

La reserva del sacerdocio ministerial a los varones no implica que todos los cargos de responsabilidad, administración, enseñanza o asesoramiento deban estar reservados a hombres.

La Iglesia necesita escuchar las experiencias reales de las familias y ofrecer espacios efectivos de participación.""", """The critical observation is legitimate: the absence of sufficient participation by women and laypeople can impoverish pastoral understanding.

Reserving ministerial priesthood to men does not imply that all positions of responsibility, administration, teaching or advice must be reserved to men.

The Church needs to hear the real experiences of families and offer effective spaces of participation.""", """A observação crítica é legítima: a ausência de suficiente participação de mulheres e leigos pode empobrecer a compreensão pastoral.

A reserva do sacerdócio ministerial aos varões não implica que todos os cargos de responsabilidade, administração, ensino ou assessoramento devam estar reservados aos homens.

A Igreja precisa ouvir as experiências reais das famílias e oferecer espaços efetivos de participação.""")
    ),
    ApologeticsQuestion(
        number = 95,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""Si Jesús fue pobre, ¿por qué el Vaticano posee tantas riquezas?""", """If Jesus was poor, why does the Vatican possess so much wealth?""", """Se Jesus foi pobre, por que o Vaticano possui tantas riquezas?"""),
        answer = LocalizedText("""La Iglesia distingue entre poseer bienes para fines religiosos, culturales y sociales, y utilizarlos para el lujo personal o la acumulación egoísta.

Una catedral, una biblioteca o una obra de arte religiosa pueden tener valor espiritual y cultural, además de económico.

Tampoco todo el patrimonio relacionado con la Iglesia pertenece al Papa ni puede ser vendido libremente. Existen propietarios, finalidades y restricciones distintos.""", """The Church distinguishes between holding goods for religious, cultural and social purposes and using them for personal luxury or selfish accumulation.

A cathedral, a library or a work of religious art can have spiritual and cultural value, as well as economic value.

Nor does all the patrimony related to the Church belong to the Pope or can be freely sold. There are different owners, purposes and restrictions.""", """A Igreja distingue entre possuir bens para fins religiosos, culturais e sociais e utilizá-los para o luxo pessoal ou a acumulação egoísta.

Uma catedral, uma biblioteca ou uma obra de arte religiosa podem ter valor espiritual e cultural, além de econômico.

Tampouco todo o patrimônio relacionado à Igreja pertence ao Papa nem pode ser vendido livremente. Existem proprietários, finalidades e restrições distintos."""),
        foundation = LocalizedText("""Lucas 12,15; Hechos 2,44-45; Hechos 4,32-35; Mateo 25,35-40; Catecismo, 2401-2406 y 2443-2449.""", """Luke 12:15; Acts 2:44-45; Acts 4:32-35; Matthew 25:35-40; Catechism, 2401-2406 and 2443-2449.""", """Lucas 12,15; Atos 2,44-45; Atos 4,32-35; Mateus 25,35-40; Catecismo, 2401-2406 e 2443-2449."""),
        clarification = LocalizedText("""Esto no demuestra que todas las decisiones patrimoniales del Vaticano hayan sido correctas.

La pregunta sobre cuánto debe conservarse, venderse, invertirse o destinarse a los pobres es legítima.

La Iglesia debe explicar públicamente cómo administra sus recursos y cómo se relacionan sus gastos con su misión evangélica.

El argumento de preservar el patrimonio cultural no puede convertirse en una excusa universal para ignorar necesidades humanas urgentes.""", """This does not prove that all the Vatican's patrimonial decisions have been correct.

The question of how much should be kept, sold, invested or destined to the poor is a legitimate one.

The Church must publicly explain how it administers its resources and how its expenses relate to its evangelical mission.

The argument of preserving cultural patrimony cannot become a universal excuse for ignoring urgent human needs.""", """Isso não demonstra que todas as decisões patrimoniais do Vaticano tenham sido corretas.

A pergunta sobre quanto deve ser conservado, vendido, investido ou destinado aos pobres é legítima.

A Igreja deve explicar publicamente como administra seus recursos e como seus gastos se relacionam com sua missão evangélica.

O argumento de preservar o patrimônio cultural não pode se converter em uma desculpa universal para ignorar necessidades humanas urgentes.""")
    ),
    ApologeticsQuestion(
        number = 96,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Cómo puede haber corrupción financiera dentro de la Iglesia?""", """How can there be financial corruption inside the Church?""", """Como pode haver corrupção financeira dentro da Igreja?"""),
        answer = LocalizedText("""Porque los miembros de la Iglesia pueden cometer los mismos pecados de codicia, engaño y abuso de poder que cualquier otra persona.

El cristianismo no enseña que recibir la ordenación sacerdotal vuelva a alguien incapaz de pecar.

La Biblia denuncia duramente la corrupción religiosa y la utilización de lo sagrado para obtener beneficios personales.""", """Because members of the Church can commit the same sins of greed, deceit and abuse of power as any other person.

Christianity does not teach that receiving priestly ordination makes someone incapable of sinning.

The Bible harshly denounces religious corruption and the use of sacred things to obtain personal benefits.""", """Porque os membros da Igreja podem cometer os mesmos pecados de cobiça, engano e abuso de poder que qualquer outra pessoa.

O cristianismo não ensina que receber a ordenação sacerdotal torne alguém incapaz de pecar.

A Bíblia denuncia duramente a corrupção religiosa e a utilização do sagrado para obter benefícios pessoais."""),
        foundation = LocalizedText("""Mateo 21,12-13; Hechos 5,1-11; 1 Timoteo 6,9-10; Santiago 5,1-6; Catecismo, 2409 y 2454.""", """Matthew 21:12-13; Acts 5:1-11; 1 Timothy 6:9-10; James 5:1-6; Catechism, 2409 and 2454.""", """Mateus 21,12-13; Atos 5,1-11; 1 Timóteo 6,9-10; Tiago 5,1-6; Catecismo, 2409 e 2454."""),
        clarification = LocalizedText("""La corrupción no se justifica diciendo que todos somos pecadores.

Las instituciones eclesiásticas tienen una responsabilidad especialmente alta de transparencia, controles independientes y rendición de cuentas.

Los recursos donados para fines religiosos o caritativos deben utilizarse de acuerdo con esos fines.

La necesidad de reforma administrativa puede reconocerse sin concluir automáticamente que todas las personas vinculadas a la Iglesia son corruptas.""", """Corruption is not justified by saying that we are all sinners.

Ecclesiastical institutions bear a particularly high responsibility for transparency, independent controls, and accountability.

Resources donated for religious or charitable purposes must be used in accordance with those purposes.

The need for administrative reform can be acknowledged without automatically concluding that all people linked to the Church are corrupt.""", """A corrupção não se justifica dizendo que todos somos pecadores.

As instituições eclesiásticas têm uma responsabilidade especialmente alta de transparência, controles independentes e prestação de contas.

Os recursos doados para fins religiosos ou caritativos devem ser utilizados de acordo com esses fins.

A necessidade de reforma administrativa pode ser reconhecida sem concluir automaticamente que todas as pessoas vinculadas à Igreja são corruptas.""")
    ),
    ApologeticsQuestion(
        number = 97,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""Si Jesús prohibió llamar «padre» a otros hombres, ¿por qué llamamos «padre» a los sacerdotes?""", """If Jesus forbade calling other men 'father', why do we call priests 'father'?""", """Se Jesus proibiu chamar de «pai» outros homens, por que chamamos «pai» aos sacerdotes?"""),
        answer = LocalizedText("""Mateo 23,9 debe leerse en el contexto de la crítica de Jesús a la búsqueda de honores y superioridad religiosa.

La misma Biblia utiliza el lenguaje de paternidad espiritual.

San Pablo se presenta como padre espiritual de los cristianos de Corinto porque los ha engendrado en Cristo mediante el Evangelio.

Si la prohibición fuera absoluta respecto de cualquier uso humano de la palabra padre, también resultaría problemático emplearla para el padre biológico o para personajes bíblicos.""", """Matthew 23:9 must be read in the context of Jesus' criticism of the search for honors and religious superiority.

The Bible itself uses the language of spiritual paternity.

Saint Paul presents himself as the spiritual father of the Christians of Corinth because he fathered them in Christ through the Gospel.

If the prohibition were absolute regarding any human use of the word father, it would also be problematic to employ it for the biological father or for biblical characters.""", """Mateus 23,9 deve ser lido no contexto da crítica de Jesus à busca de honras e à superioridade religiosa.

A própria Bíblia utiliza a linguagem de paternidade espiritual.

São Paulo se apresenta como pai espiritual dos cristãos de Corinto porque os gerou em Cristo mediante o Evangelho.

Se a proibição fosse absoluta quanto a qualquer uso humano da palavra pai, também resultaria problemático empregá-la para o pai biológico ou para personagens bíblicos."""),
        foundation = LocalizedText("""Mateo 23,8-12; 1 Corintios 4,14-15; Filemón 10; Hechos 7,2; Efesios 6,1-4.""", """Matthew 23:8-12; 1 Corinthians 4:14-15; Philemon 10; Acts 7:2; Ephesians 6:1-4.""", """Mateus 23,8-12; 1 Coríntios 4,14-15; Filemom 10; Atos 7,2; Efésios 6,1-4."""),
        clarification = LocalizedText("""El título no debe utilizarse para atribuir a un sacerdote una autoridad divina independiente.

El razonamiento católico es que Jesús condena la vanidad y la pretensión de superioridad espiritual, no toda aplicación respetuosa del término.

Un sacerdote que utiliza su título para exigir obediencia abusiva contradice precisamente el espíritu del pasaje.""", """The title must not be used to attribute to a priest an independent divine authority.

The Catholic reasoning is that Jesus condemns vanity and pretension to spiritual superiority, not every respectful application of the term.

A priest who uses his title to demand abusive obedience contradicts precisely the spirit of the passage.""", """O título não deve ser utilizado para atribuir a um sacerdote uma autoridade divina independente.

O raciocínio católico é que Jesus condena a vaidade e a pretensão de superioridade espiritual, não toda aplicação respeitosa do termo.

Um sacerdote que utiliza seu título para exigir obediência abusiva contradiz precisamente o espírito do passagem.""")
    ),
    ApologeticsQuestion(
        number = 98,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""Si Jesús está presente donde dos o tres se reúnen, ¿para qué necesitamos iglesias y sacerdotes?""", """If Jesus is present where two or three gather, why do we need churches and priests?""", """Se Jesus está presente onde dois ou três se reúnem, para que precisamos de igrejas e sacerdotes?"""),
        answer = LocalizedText("""Mateo 18,20 afirma la presencia de Cristo en la reunión de sus discípulos. No dice que esa sea la única manera en que Cristo se hace presente ni que toda forma de ministerio o culto comunitario sea innecesaria.

El Nuevo Testamento también describe comunidades organizadas que celebran la fracción del pan, reciben enseñanza apostólica y reconocen responsables pastorales.

La Iglesia enseña que Dios puede ser encontrado en cualquier lugar. Los templos son espacios dedicados al culto comunitario, no edificios que contengan o limiten a Dios.""", """Matthew 18:20 affirms Christ's presence in the gathering of His disciples. It does not say that this is the only way in which Christ makes Himself present, nor that every form of communal worship ministry is unnecessary.

The New Testament also describes organized communities that celebrate the breaking of bread, receive apostolic teaching, and recognize pastoral leaders.

The Church teaches that God can be found anywhere. Temples are spaces dedicated to communal worship, not buildings that contain or limit God.""", """Mateus 18,20 afirma a presença de Cristo na reunião de seus discípulos. Não diz que essa seja a única maneira em que Cristo se faz presente, nem que toda forma de ministério de culto comunitário seja desnecessária.

O Novo Testamento também descreve comunidades organizadas que celebram a fração do pão, recebem ensino apostólico e reconhecem responsáveis pastorais.

A Igreja ensina que Deus pode ser encontrado em qualquer lugar. Os templos são espaços dedicados ao culto comunitário, não edifícios que contenham ou limitem a Deus."""),
        foundation = LocalizedText("""Mateo 18,20; Hechos 2,42; Hechos 14,23; 1 Corintios 11,23-29; 1 Timoteo 3,1-13.""", """Matthew 18:20; Acts 2:42; Acts 14:23; 1 Corinthians 11:23-29; 1 Timothy 3:1-13.""", """Mateus 18,20; Atos 2,42; Atos 14,23; 1 Coríntios 11,23-29; 1 Timóteo 3,1-13."""),
        clarification = LocalizedText("""Un edificio religioso no es indispensable para toda oración ni para toda relación con Cristo.

Y un sacerdote no sustituye la relación personal con Dios.

La argumentación católica es que Cristo quiso también una comunidad sacramental y ministerial. Esa afirmación requiere considerar el conjunto del Nuevo Testamento y la historia de la Iglesia primitiva.""", """A religious building is not indispensable for every prayer or for every relationship with Christ.

And a priest does not substitute for the personal relationship with God.

The Catholic argument is that Christ also willed a sacramental and ministerial community. That affirmation requires considering the whole of the New Testament and the history of the early Church.""", """Um edifício religioso não é indispensável para toda oração nem para toda relação com Cristo.

E um sacerdote não substitui a relação pessoal com Deus.

O argumento católico é que Cristo quis também uma comunidade sacramental e ministerial. Essa afirmação requer considerar o conjunto do Novo Testamento e a história da Igreja primitiva.""")
    ),
    ApologeticsQuestion(
        number = 99,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""¿Por qué aceptar la autoridad moral de sacerdotes que no viven lo que predican?""", """Why accept the moral authority of priests who do not live what they preach?""", """Por que aceitar a autoridade moral de sacerdotes que não vivem o que pregam?"""),
        answer = LocalizedText("""Porque debemos distinguir entre la verdad de una enseñanza y la conducta de quien la comunica.

Una persona puede enseñar correctamente que robar es injusto y, sin embargo, ser ladrona.

Su hipocresía destruye su credibilidad personal, pero no convierte automáticamente en falsa la norma que estaba enseñando.

Jesús mismo advierte contra quienes enseñan y no practican lo que predican.""", """Because we must distinguish between the truth of a teaching and the conduct of the person communicating it.

A person can correctly teach that stealing is unjust and yet be a thief.

Their hypocrisy destroys their personal credibility, but it does not automatically make the norm they were teaching false.

Jesus Himself warns against those who teach and do not practice what they preach.""", """Porque devemos distinguir entre a verdade de um ensino e a conduta de quem o comunica.

Uma pessoa pode ensinar corretamente que roubar é injusto e, no entanto, ser ladra.

Sua hipocrisia destrói sua credibilidade pessoal, mas não torna automaticamente falsa a norma que estava ensinando.

O próprio Jesus adverte contra aqueles que ensinam e não praticam o que pregam."""),
        foundation = LocalizedText("""Mateo 23,1-4; Romanos 2,17-24; 1 Pedro 5,2-3.""", """Matthew 23:1-4; Romans 2:17-24; 1 Peter 5:2-3.""", """Mateus 23,1-4; Romanos 2,17-24; 1 Pedro 5,2-3."""),
        clarification = LocalizedText("""Distinguir enseñanza y conducta no significa exigir obediencia ciega.

La autoridad religiosa debe estar limitada por la verdad, la justicia, la conciencia y las normas legítimas.

Una persona no tiene obligación moral de colaborar con un abuso o aceptar una orden injusta.

La Iglesia debe formar ministros que sean testigos creíbles de lo que anuncian.""", """Distinguishing teaching from conduct does not mean demanding blind obedience.

Religious authority must be limited by truth, justice, conscience and legitimate norms.

A person has no moral obligation to cooperate with an abuse or accept an unjust order.

The Church must form ministers who are credible witnesses to what they proclaim.""", """Distinguir ensino e conduta não significa exigir obediência cega.

A autoridade religiosa deve estar limitada pela verdade, pela justiça, pela consciência e pelas normas legítimas.

Uma pessoa não tem obrigação moral de colaborar com um abuso ou aceitar uma ordem injusta.

A Igreja deve formar ministros que sejam testemunhas críveis do que anunciam.""")
    ),
    ApologeticsQuestion(
        number = 100,
        section = LocalizedText("""Sacerdotes, religiosas, mujeres, riqueza y autoridad""", """Priests, religious, women, wealth and authority""", """Sacerdotes, religiosos, mulheres, riqueza e autoridade"""),
        question = LocalizedText("""Si Jesús regresara hoy, ¿reconocería a la Iglesia católica?""", """If Jesus returned today, would He recognize the Catholic Church?""", """Se Jesus voltasse hoje, reconheceria a Igreja católica?"""),
        answer = LocalizedText("""Ninguno de nosotros puede afirmar con certeza qué juicio concreto emitiría Jesús sobre cada institución, persona o práctica actual.

Pero sí tenemos los Evangelios para identificar algunos criterios centrales de su enseñanza.

Jesús valora la misericordia, la verdad, la fidelidad a Dios, el servicio humilde y la atención a los pobres. También condena la hipocresía, la explotación religiosa y el uso del poder para oprimir.

La Iglesia católica sostiene que Cristo permanece fiel a su Iglesia y actúa en ella mediante su Espíritu, a pesar de los pecados de sus miembros.""", """None of us can state with certainty what concrete judgement Jesus would make on each current institution, person or practice.

But we do have the Gospels to identify some central criteria of His teaching.

Jesus values mercy, truth, fidelity to God, humble service and attention to the poor. He also condemns hypocrisy, religious exploitation and the use of power to oppress.

The Catholic Church holds that Christ remains faithful to His Church and acts in it through His Spirit, despite the sins of its members.""", """Nenhum de nós pode afirmar com certeza que juízo concreto Jesus emitiria sobre cada instituição, pessoa ou prática atual.

Mas temos os Evangelhos para identificar alguns critérios centrais de seu ensino.

Jesus valoriza a misericórdia, a verdade, a fidelidade a Deus, o serviço humilde e a atenção aos pobres. Também condena a hipocrisia, a exploração religiosa e o uso do poder para oprimir.

A Igreja católica sustenta que Cristo permanece fiel à sua Igreja e atua nela mediante seu Espírito, apesar dos pecados de seus membros."""),
        foundation = LocalizedText("""Mateo 23; Mateo 25,31-46; Marcos 10,42-45; Juan 13,12-17; Apocalipsis 2-3.""", """Matthew 23; Matthew 25:31-46; Mark 10:42-45; John 13:12-17; Revelation 2-3.""", """Mateus 23; Mateus 25,31-46; Marcos 10,42-45; João 13,12-17; Apocalipse 2-3."""),
        clarification = LocalizedText("""Esto no significa que Cristo apruebe toda decisión histórica o contemporánea de las autoridades eclesiásticas.

La pregunta más importante no es únicamente si reconocería las estructuras actuales, sino si nuestras comunidades viven realmente como discípulos suyos.""", """This does not mean that Christ approves every historical or contemporary decision of ecclesiastical authorities.

The most important question is not only whether He would recognize the current structures, but whether our communities really live as His disciples.""", """Isso não significa que Cristo aprove toda decisão histórica ou contemporânea das autoridades eclesiásticas.

A pergunta mais importante não é unicamente se reconheceria as estruturas atuais, mas se nossas comunidades vivem realmente como seus discípulos.""")
    ),
    ApologeticsQuestion(
        number = 101,
        section = LocalizedText("""La oración""", """Prayer""", """A oração"""),
        question = LocalizedText("""Si Jesús condenó las repeticiones vacías al orar, ¿por qué los católicos repiten una y otra vez el Padrenuestro, el Avemaría y las oraciones del Rosario? ¿No deberíamos hablar espontáneamente con Dios, desde el corazón, en lugar de repetir fórmulas aprendidas de memoria? ¿Dónde enseña la Biblia que debemos rezar de esa manera?""", """If Jesus condemned empty repetitions when praying, why do Catholics repeatedly recite the Our Father, the Hail Mary, and the prayers of the Rosary? Should we not speak spontaneously with God, from the heart, instead of repeating formulas learned by memory? Where does the Bible teach that we should pray in this way?""", """Se Jesus condenou as repetições vazias ao orar, por que os católicos repetem uma e outra vez o Pai-nosso, a Ave-Maria e as orações do Rosário? Não deveríamos falar espontaneamente com Deus, desde o coração, em lugar de repetir fórmulas aprendidas de memória? Onde ensina a Bíblia que devemos rezar dessa maneira?"""),
        answer = LocalizedText("""La Biblia no condena repetir oraciones. Lo que Jesús condena es la repetición vacía, es decir, creer que Dios nos escuchará por acumular palabras, sin una verdadera disposición del corazón.

De hecho, Jesús enseñó una oración con palabras concretas: el Padrenuestro.

En Lucas 11,2, Jesús dice a sus discípulos: «Cuando oréis, decid…», y les enseña una fórmula de oración.

Además, Jesús mismo repitió palabras durante su oración en Getsemaní.

¿Repetir «te amo» a una persona hace que esas palabras pierdan su valor?

No necesariamente. Depende de si las pronunciamos mecánicamente o si expresan realmente nuestro amor.

Lo mismo sucede con la oración.

Un católico puede repetir cincuenta avemarías con atención, amor y meditación, mientras otra persona puede improvisar una oración de diez minutos sin prestar verdadera atención a lo que está diciendo.

Y también puede ocurrir exactamente lo contrario.

La cuestión no es si repetimos palabras, sino si oramos sinceramente.

¿Entonces cuál es la diferencia entre rezar y orar?

En español, rezar suele referirse a pronunciar oraciones establecidas, mientras que orar puede utilizarse de manera más amplia para cualquier forma de comunicación con Dios.

Sin embargo, esta distinción no es absoluta: rezar también es orar. Una oración espontánea no es necesariamente superior a una oración aprendida, ni viceversa.

El Rosario, por ejemplo, no consiste únicamente en repetir avemarías: su finalidad es meditar los misterios de la vida de Jesucristo mientras se recitan las oraciones.""", """The Bible does not condemn repeating prayers. What Jesus condemns is empty repetition, that is, believing that God will hear us by accumulating words, without a true disposition of the heart.

In fact, Jesus taught a prayer with concrete words: the Our Father.

In Luke 11:2, Jesus says to His disciples: “When you pray, say…”, and teaches them a formula of prayer.

Moreover, Jesus himself repeated words during his prayer in Gethsemane.

Does repeating “I love you” to a person make those words lose their value?

Not necessarily. It depends on whether we pronounce them mechanically or whether they truly express our love.

The same happens with prayer.

A Catholic can repeat fifty Hail Marys with attention, love and meditation, while another person can improvise a ten-minute prayer without truly paying attention to what they are saying.

And the exactly opposite can also happen.

The issue is not whether we repeat words, but whether we pray sincerely.

So what is the difference between praying [rezar] and praying [orar]?

In Spanish, rezar usually refers to pronouncing established prayers, while orar can be used more broadly for any form of communication with God.

However, this distinction is not absolute: rezar is also orar. A spontaneous prayer is not necessarily superior to a learned prayer, nor vice versa.

The Rosary, for example, does not consist only in repeating Hail Marys: its purpose is to meditate on the mysteries of the life of Jesus Christ while the prayers are recited.""", """A Bíblia não condena repetir orações. O que Jesus condena é a repetição vazia, isto é, crer que Deus nos ouvirá por acumular palavras, sem uma verdadeira disposição do coração.

De fato, Jesus ensinou uma oração com palavras concretas: o Pai-nosso.

Em Lucas 11,2, Jesus diz a seus discípulos: «Quando orardes, dizei…», e lhes ensina uma fórmula de oração.

Além disso, o próprio Jesus repetiu palavras durante sua oração no Getsêmani.

Repetir «eu te amo» a uma pessoa faz com que essas palavras percam seu valor?

Não necessariamente. Depende se as pronunciamos mecanicamente ou se expressam realmente nosso amor.

O mesmo acontece com a oração.

Um católico pode repetir cinquenta ave-marias com atenção, amor e meditação, enquanto outra pessoa pode improvisar uma oração de dez minutos sem prestar verdadeira atenção ao que está dizendo.

E também pode ocorrer exatamente o contrário.

A questão não é se repetimos palavras, mas se oramos sinceramente.

Então, qual é a diferença entre rezar e orar?

Em espanhol, rezar costuma referir-se a pronunciar orações estabelecidas, enquanto orar pode ser usado de maneira mais ampla para qualquer forma de comunicação com Deus.

No entanto, esta distinção não é absoluta: rezar também é orar. Uma oração espontânea não é necessariamente superior a uma oração aprendida, nem vice-versa.

O Rosário, por exemplo, não consiste unicamente em repetir ave-marias: sua finalidade é meditar os mistérios da vida de Jesus Cristo enquanto se recitam as orações."""),
        foundation = LocalizedText("""Mateo 6,7-13: Jesús condena la palabrería vacía y enseña el Padrenuestro.

Lucas 11,1-4: Los discípulos piden a Jesús que les enseñe a orar, y Él les da una oración concreta.

Mateo 26,44: Jesús vuelve a orar por tercera vez, repitiendo las mismas palabras.

Salmo 136: La expresión «porque es eterna su misericordia» se repite 26 veces.

Apocalipsis 4,8: Los seres celestiales proclaman continuamente «Santo, santo, santo».

1 Tesalonicenses 5,17: San Pablo invita a orar sin cesar.

Catecismo de la Iglesia Católica, 2700-2724: Explica la oración vocal, la meditación y la oración contemplativa.""", """Matthew 6:7-13: Jesus condemns empty talk and teaches the Our Father.

Luke 11:1-4: The disciples ask Jesus to teach them to pray, and He gives them a concrete prayer.

Matthew 26:44: Jesus prays a third time, repeating the same words.

Psalm 136: The expression “for His steadfast love endures forever” is repeated 26 times.

Revelation 4:8: The heavenly beings continually proclaim “Holy, holy, holy”.

1 Thessalonians 5:17: Saint Paul invites to pray without ceasing.

Catechism of the Catholic Church, 2700-2724: Explains vocal prayer, meditation, and contemplative prayer.""", """Mateus 6,7-13: Jesus condena a tagarelice vazia e ensina o Pai-nosso.

Lucas 11,1-4: Os discípulos pedem a Jesus que lhes ensine a orar, e ele lhes dá uma oração concreta.

Mateus 26,44: Jesus volta a orar pela terceira vez, repetindo as mesmas palavras.

Salmo 136: A expressão «porque é eterna sua misericórdia» se repete 26 vezes.

Apocalipse 4,8: Os seres celestiais proclamam continuamente «Santo, santo, santo».

1 Tessalonicenses 5,17: São Paulo convida a orar sem cessar.

Catecismo da Igreja Católica, 2700-2724: Explica a oração vocal, a meditação e a oração contemplativa."""),
        clarification = LocalizedText("""Jesús nunca ordenó rezar el Rosario. Es una devoción desarrollada históricamente por la Iglesia, no un mandato bíblico. Tampoco enseñó que debamos repetir un número determinado de oraciones para que Dios nos escuche.

Por eso, un católico puede dirigirse a Dios con sus propias palabras, en silencio, mediante los salmos, con el Padrenuestro o utilizando otras oraciones tradicionales.

La Biblia no enseña que repetir una oración sea malo. Jesús enseñó una oración concreta y Él mismo repitió palabras al orar.

Lo que sí rechazó fue la idea de que Dios nos escucha por la cantidad de palabras que pronunciamos.

Dios no necesita que inventemos palabras nuevas cada vez que hablamos con Él. Quiere que lo hagamos con sinceridad.

Y una oración aprendida de memoria puede convertirse en una expresión profunda de fe cuando la pronunciamos con el corazón.""", """Jesus never ordered praying the Rosary. It is a devotion historically developed by the Church, not a biblical mandate. Nor did He teach that we must repeat a determined number of prayers for God to hear us.

Therefore, a Catholic can address God with his own words, in silence, through the Psalms, with the Our Father, or using other traditional prayers.

The Bible does not teach that repeating a prayer is bad. Jesus taught a concrete prayer and He himself repeated words when praying.

What He did reject was the idea that God hears us by the number of words we pronounce.

God does not need us to invent new words every time we speak with Him. He wants us to do so sincerely.

And a prayer learned by memory can become a deep expression of faith when we pronounce it with the heart.""", """Jesus nunca ordenou rezar o Rosário. É uma devoção desenvolvida historicamente pela Igreja, não um mandato bíblico. Tampouco ensinou que devemos repetir um número determinado de orações para que Deus nos ouça.

Por isso, um católico pode dirigir-se a Deus com suas próprias palavras, em silêncio, mediante os salmos, com o Pai-nosso ou utilizando outras orações tradicionais.

A Bíblia não ensina que repetir uma oração seja mau. Jesus ensinou uma oração concreta e ele mesmo repetiu palavras ao orar.

O que ele rejeitou foi a ideia de que Deus nos ouve pela quantidade de palavras que pronunciamos.

Deus não precisa que inventemos palavras novas cada vez que falamos com ele. Quer que o façamos com sinceridade.

E uma oração aprendida de memória pode se tornar uma expressão profunda de fé quando a pronunciamos com o coração.""")
    )
    )
}
