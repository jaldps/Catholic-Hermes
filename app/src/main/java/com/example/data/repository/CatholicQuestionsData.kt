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
        answer = LocalizedText("""La respuesta directa es esta: la fe católica no enseña que Dios quiera el sufrimiento inocente ni que una persona sufra necesariamente porque pecó. Dios lo permite sin aprobarlo, y no permanece indiferente: en Jesucristo entra en nuestra condición, sufre y vence la muerte en la resurrección. Jesús rechaza la idea de que la ceguera de un hombre fuese castigo de un pecado concreto (Juan 9,1-3) y llora ante la muerte de Lázaro (Juan 11,35).

Piensa en una familia que espera en la sala de un hospital: puede acompañar a su hijo enfermo, sostenerle la mano y buscar su curación, pero no puede eliminar por sí sola la enfermedad. La analogía no explica el dolor; solo ayuda a distinguir estar presente de causar el mal.

La objeción más fuerte dice: «Si Dios puede impedirlo y no lo hace, es cómplice o carece de poder». La Iglesia responde que la libertad explica parte del mal —guerras, asesinatos, abusos— pero no los terremotos ni el cáncer infantil. No posee una explicación exhaustiva de cada caso, y el misterio permanece. Lo que ofrece es esperanza: el sufrimiento tendrá fin (Apocalipsis 21,4), no una demostración de por qué ocurrió cada tragedia concreta.""", """The direct answer is this: Catholic faith does not teach that God wills innocent suffering or that a person necessarily suffers because they have sinned. God permits it without approving it, and He does not remain indifferent: in Jesus Christ He enters our condition, suffers and conquers death in the resurrection. Jesus rejects the idea that a man's blindness was punishment for a specific sin (John 9:1-3) and weeps before Lazarus's death (John 11:35).

Think of a family waiting in a hospital room: they can accompany their sick child, hold his hand and seek his healing, but they cannot by themselves eliminate the illness. The analogy does not explain the pain; it only helps distinguish being present from causing evil.

The strongest objection says: "If God can prevent it and does not, He is complicit or lacks power." The Church answers that freedom explains part of evil — wars, murders, abuse — but not earthquakes or childhood cancer. It does not have an exhaustive explanation for every case, and the mystery remains. What it offers is hope: suffering will have an end (Revelation 21:4), not a demonstration of why each concrete tragedy occurred.""", """A resposta direta é esta: a fé católica não ensina que Deus queira o sofrimento inocente nem que uma pessoa sofra necessariamente porque pecou. Deus o permite sem aprová-lo e não permanece indiferente: em Jesus Cristo entra em nossa condição, sofre e vence a morte na ressurreição. Jesus rejeita a ideia de que a cegueira de um homem fosse castigo por um pecado concreto (João 9,1-3) e chora diante da morte de Lázaro (João 11,35).

Pense em uma família que espera na sala de um hospital: pode acompanhar seu filho doente, segurar sua mão e buscar sua cura, mas não pode eliminar por si mesma a doença. A analogia não explica a dor; ajuda apenas a distinguir estar presente de causar o mal.

A objeção mais forte diz: "Se Deus pode impedi-lo e não o faz, é cúmplice ou não tem poder". A Igreja responde que a liberdade explica parte do mal — guerras, assassinatos, abusos —, mas não os terremotos nem o câncer infantil. Não possui uma explicação exaustiva para cada caso, e o mistério permanece. O que oferece é esperança: o sofrimento terá fim (Apocalipse 21,4), não uma demonstração de por que ocorreu cada tragédia concreta."""),
        foundation = LocalizedText("""Romanos 8,18-23; Juan 9,1-3; Apocalipsis 21,4. Catecismo, 309-314.""", """Romans 8:18-23; John 9:1-3; Revelation 21:4. Catechism, 309-314.""", """Romanos 8,18-23; João 9,1-3; Apocalipse 21,4. Catecismo, 309-314."""),
        clarification = LocalizedText("""Esto no demuestra por qué Dios permite cada tragedia. El problema del sufrimiento inocente sigue siendo una de las dificultades filosóficas más fuertes para el teísmo. La esperanza cristiana ofrece una respuesta sobre el destino del sufrimiento, pero no una explicación exhaustiva de cada caso.""", """This does not demonstrate why God allows every tragedy. The problem of innocent suffering remains one of the strongest philosophical difficulties for theism. Christian hope offers an answer about the destiny of suffering, but not an exhaustive explanation of every case.""", """Isso não demonstra por que Deus permite cada tragédia. O problema do sofrimento inocente continua sendo uma das dificuldades filosóficas mais fortes para o teísmo. A esperança cristã oferece uma resposta sobre o destino do sofrimento, mas não uma explicação exaustiva de cada caso.""")
    ),
    ApologeticsQuestion(
        number = 2,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios conoce nuestro futuro, ¿cómo podemos ser realmente libres?""", """If God knows our future, how can we truly be free?""", """Se Deus conhece o nosso futuro, como podemos ser realmente livres?"""),
        answer = LocalizedText("""La respuesta directa es que conocer una decisión no es lo mismo que causarla. Dios conoce lo que libremente elegiremos sin obligarnos a elegirlo. La explicación clásica añade que Dios es eterno y conoce nuestras decisiones con un conocimiento perfecto, sin estar dentro de nuestro tiempo ni empujarnos desde el pasado hacia el futuro. La Biblia mantiene las dos afirmaciones juntas: Dios conoce todo (Salmo 139,1-16) y cada persona es responsable de elegir la vida (Deuteronomio 30,19; Eclesiástico 15,11-20).

Imagina a un padre que conoce tan bien a su hijo adolescente que sabe que, ante una salida con malas compañías, rechazará acompañarlos. Su conocimiento no le obliga: el hijo podría aceptar. La analogía ayuda a ver la diferencia entre saber y coaccionar, aunque Dios conoce más perfectamente que cualquier padre.

La objeción más fuerte afirma: «Si Dios ya sabe que elegiré el mal y no puede sorprenderse, mi elección ya está fijada». La respuesta honesta es que los filósofos discuten cómo se reconcilian presciencia, gracia y libertad, y ninguna analogía lo resuelve del todo. La Iglesia sostiene, sin embargo, que Dios no anula nuestra responsabilidad ni nuestra capacidad real de elegir.""", """The direct answer is that knowing a decision is not the same as causing it. God knows what we will freely choose without obliging us to choose it. The classical explanation adds that God is eternal and knows our decisions with perfect knowledge, without being inside our time or pushing us from the past toward the future. The Bible holds the two affirmations together: God knows everything (Psalm 139:1-16) and each person is responsible for choosing life (Deuteronomy 30:19; Sirach 15:11-20).

Imagine a father who knows his teenage son so well that he knows that, faced with going out with bad company, he will refuse to join them. His knowledge does not compel him: the son could accept. The analogy helps to see the difference between knowing and coercing, although God knows more perfectly than any father.

The strongest objection states: "If God already knows that I will choose evil and cannot be surprised, my choice is already fixed." The honest answer is that philosophers debate how foreknowledge, grace and freedom are reconciled, and no analogy fully resolves it. The Church maintains, however, that God does not annul our responsibility or our real capacity to choose.""", """A resposta direta é que conhecer uma decisão não é o mesmo que causá-la. Deus conhece o que livremente escolheremos sem nos obrigar a escolhê-lo. A explicação clássica acrescenta que Deus é eterno e conhece nossas decisões com conhecimento perfeito, sem estar dentro do nosso tempo nem nos empurrar do passado para o futuro. A Bíblia mantém as duas afirmações juntas: Deus conhece tudo (Salmo 139,1-16) e cada pessoa é responsável por escolher a vida (Deuteronômio 30,19; Eclesiástico 15,11-20).

Imagine um pai que conhece tão bem seu filho adolescente que sabe que, diante de uma saída com más companhias, ele recusará acompanhá-los. Seu conhecimento não o obriga: o filho poderia aceitar. A analogia ajuda a ver a diferença entre saber e coagir, embora Deus conheça mais perfeitamente que qualquer pai.

A objeção mais forte afirma: "Se Deus já sabe que escolherei o mal e não pode se surpreender, minha escolha já está fixada". A resposta honesta é que os filósofos discutem como se reconciliam presciência, graça e liberdade, e nenhuma analogia o resolve totalmente. A Igreja sustenta, contudo, que Deus não anula nossa responsabilidade nem nossa capacidade real de escolher."""),
        foundation = LocalizedText("""Salmo 139,1-16; Deuteronomio 30,19; Eclesiástico 15,11-20; Catecismo, 1730-1742.""", """Psalm 139:1-16; Deuteronomy 30:19; Sirach 15:11-20; Catechism, 1730-1742.""", """Salmo 139,1-16; Deuteronômio 30,19; Eclesiástico 15,11-20; Catecismo, 1730-1742."""),
        clarification = LocalizedText("""La comparación con un observador humano ayuda, pero no resuelve por completo el problema filosófico. Existen diversas explicaciones católicas sobre cómo se relacionan la presciencia, la gracia y la libertad.

La Iglesia sostiene las dos afirmaciones fundamentales: Dios conoce todas las cosas y el ser humano es realmente responsable de sus elecciones.""", """The comparison with a human observer helps, but does not fully resolve the philosophical problem. There are various Catholic explanations of how foreknowledge, grace and freedom relate.

The Church holds both fundamental affirmations: God knows all things, and the human being is truly responsible for their choices.""", """A comparação com um observador humano ajuda, mas não resolve por completo o problema filosófico. Existem diversas explicações católicas sobre como se relacionam presciência, graça e liberdade.

A Igreja sustenta as duas afirmações fundamentais: Deus conhece todas as coisas e o ser humano é realmente responsável por suas escolhas.""")
    ),
    ApologeticsQuestion(
        number = 3,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios es amor, ¿cómo puede existir un infierno eterno?""", """If God is love, how can an eternal hell exist?""", """Se Deus é amor, como pode existir um inferno eterno?"""),
        answer = LocalizedText("""La respuesta directa es que el infierno no significa falta de amor de Dios, sino la posibilidad real de que una criatura libre rechace definitivamente la comunión con Él. El Catecismo lo define como separación definitiva de Dios (1033-1037) y Jesús habla con total seriedad del juicio y de la posibilidad de perderse (Mateo 25,31-46; Marcos 9,43-48). Si Dios quiere que todos se salven (1 Timoteo 2,4), la condena no es fruto de preferencias divinas, sino de un rechazo sostenido.

Piensa en un padre que escribe a su hijo adulto cartas de reconciliación y el hijo decide no abrirlas y no volver jamás. La puerta está cerrada desde dentro por la decisión del hijo, no porque el padre haya dejado de amarlo. La imagen es limitada —ninguna persona es un personaje de ficción sin salida—, pero muestra la diferencia entre amar y obligar.

La objeción más fuerte dice: «Un castigo eterno por pecados finitos es desproporcionado y contrario a la misericordia». La respuesta honesta reconoce que la libertad ofrece una explicación parcial, no una demostración concluyente. La Iglesia no enseña que sepamos el destino eterno de personas concretas y anima a rezar por la salvación de todos.""", """The direct answer is that hell does not mean a lack of love from God, but the real possibility that a free creature definitively rejects communion with Him. The Catechism defines it as definitive separation from God (1033-1037), and Jesus speaks with complete seriousness about judgment and the possibility of being lost (Matthew 25:31-46; Mark 9:43-48). If God wants all to be saved (1 Timothy 2:4), condemnation is not the fruit of divine preferences, but of a sustained rejection.

Think of a father who writes letters of reconciliation to his adult son and the son decides never to open them and never to return. The door is closed from within by the son's decision, not because the father has stopped loving him. The image is limited — no person is a fictional character without an exit — but it shows the difference between loving and compelling.

The strongest objection says: "An eternal punishment for finite sins is disproportionate and contrary to mercy." The honest answer acknowledges that freedom offers a partial explanation, not a conclusive demonstration. The Church does not teach that we know the eternal destiny of concrete persons and encourages prayer for the salvation of all.""", """A resposta direta é que o inferno não significa falta de amor de Deus, mas a possibilidade real de uma criatura livre rejeitar definitivamente a comunhão com Ele. O Catecismo o define como separação definitiva de Deus (1033-1037) e Jesus fala com total seriedade do juízo e da possibilidade de se perder (Mateus 25,31-46; Marcos 9,43-48). Se Deus quer que todos se salvem (1 Timóteo 2,4), a condenação não é fruto de preferências divinas, mas de uma rejeição sustentada.

Pense em um pai que escreve cartas de reconciliação ao filho adulto e o filho decide não abri-las e nunca voltar. A porta está fechada por dentro pela decisão do filho, não porque o pai tenha deixado de amá-lo. A imagem é limitada — nenhuma pessoa é um personagem de ficção sem saída —, mas mostra a diferença entre amar e obrigar.

A objeção mais forte diz: "Um castigo eterno por pecados finitos é desproporcional e contrário à misericórdia". A resposta honesta reconhece que a liberdade oferece uma explicação parcial, não uma demonstração conclusiva. A Igreja não ensina que conhecemos o destino eterno de pessoas concretas e anima a rezar pela salvação de todos."""),
        foundation = LocalizedText("""Mateo 25,31-46; Marcos 9,43-48; 1 Timoteo 2,4; Catecismo, 1033-1037.""", """Matthew 25:31-46; Mark 9:43-48; 1 Timothy 2:4; Catechism, 1033-1037.""", """Mateus 25,31-46; Marcos 9,43-48; 1 Timóteo 2,4; Catecismo, 1033-1037."""),
        clarification = LocalizedText("""La objeción más difícil permanece: ¿por qué un Dios infinitamente misericordioso permitiría que ese rechazo fuese definitivo?

Apelar a la libertad constituye una respuesta parcial, no una demostración concluyente. La Iglesia no enseña que debamos considerar condenada a una persona concreta y anima a rezar por la salvación de todos.""", """The most difficult objection remains: why would an infinitely merciful God allow that rejection to be definitive?

Appealing to freedom is a partial answer, not a conclusive demonstration. The Church does not teach that we should consider a concrete person condemned, and it encourages prayer for the salvation of all.""", """A objeção mais difícil permanece: por que um Deus infinitamente misericordioso permitiria que essa rejeição fosse definitiva?

Apelar à liberdade constitui uma resposta parcial, não uma demonstração conclusiva. A Igreja não ensina que devamos considerar condenada uma pessoa concreta e anima a rezar pela salvação de todos.""")
    ),
    ApologeticsQuestion(
        number = 4,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué Dios no se manifiesta de una forma absolutamente evidente?""", """Why does God not reveal Himself in an absolutely evident way?""", """Por que Deus não se manifesta de forma absolutamente evidente?"""),
        answer = LocalizedText("""La respuesta directa es que Dios sí ha dado signos accesibles a la razón: la creación, la conciencia y, para los cristianos, la revelación decisiva en Jesucristo. Pero conocer que alguien existe no equivale a entrar en una relación de amor con esa persona. Saber que un autor existe por su libro no es lo mismo que conocerlo y amarlo. La fe no pretende coaccionar con una prueba irresistible; busca una respuesta libre y personal.

Imagina a dos amigos: uno podrá decir que conoce la vida del otro por sus cartas y fotografías, pero solo hay amistad real si hay trato mutuo. La analogía no prueba a Dios, pero distingue información sobre alguien de relación con esa persona.

La objeción atea más fuerte es la del ocultamiento divino: «Si Dios existe y quiere relacionarse, debería dar evidencia suficiente a todos, también a quienes lo buscan sinceramente». La respuesta católica no puede resolverlo con la frase fácil de que Dios se oculta para proteger nuestra libertad, porque conocer a alguien no elimina nuestra capacidad de amarlo o rechazarlo. La fe afirma que Dios busca a todos, pero reconoce que el silencio divino sigue siendo un problema filosófico serio.""", """The direct answer is that God has indeed given signs accessible to reason: creation, conscience and, for Christians, the decisive revelation in Jesus Christ. But knowing that someone exists is not the same as entering into a loving relationship with that person. Knowing that an author exists through his book is not the same as knowing and loving him. Faith does not seek to coerce with an irresistible proof; it seeks a free and personal response.

Imagine two friends: one may say he knows the other's life through his letters and photographs, but there is real friendship only if there is mutual relationship. The analogy does not prove God, but it distinguishes information about someone from relationship with that person.

The strongest atheist objection is that of divine hiddenness: "If God exists and wants a relationship, He should give sufficient evidence to all, including those who sincerely seek Him." The Catholic answer cannot resolve this with the easy phrase that God hides to protect our freedom, because knowing someone does not remove our capacity to love or reject them. Faith affirms that God seeks everyone, but acknowledges that divine silence remains a serious philosophical problem.""", """A resposta direta é que Deus deu sinais acessíveis à razão: a criação, a consciência e, para os cristãos, a revelação decisiva em Jesus Cristo. Mas conhecer que alguém existe não equivale a entrar em uma relação de amor com essa pessoa. Saber que um autor existe por seu livro não é o mesmo que conhecê-lo e amá-lo. A fé não busca coagir com uma prova irresistível; busca uma resposta livre e pessoal.

Imagine dois amigos: um poderá dizer que conhece a vida do outro por suas cartas e fotografias, mas só há amizade real se houver trato mútuo. A analogia não prova a Deus, mas distingue informação sobre alguém de relação com essa pessoa.

A objeção ateia mais forte é a do ocultamento divino: "Se Deus existe e quer uma relação, deveria dar evidência suficiente a todos, inclusive a quem o busca sinceramente". A resposta católica não pode resolvê-la com a frase fácil de que Deus se oculta para proteger nossa liberdade, porque conhecer alguém não elimina nossa capacidade de amá-lo ou rejeitá-lo. A fé afirma que Deus busca a todos, mas reconhece que o silêncio divino continua sendo um problema filosófico sério."""),
        foundation = LocalizedText("""Romanos 1,19-20; Salmo 19,2-5; Juan 1,18; Hebreos 1,1-2; Catecismo, 31-35.""", """Romans 1:19-20; Psalm 19:2-5; John 1:18; Hebrews 1:1-2; Catechism, 31-35.""", """Romanos 1,19-20; Salmo 19,2-5; João 1,18; Hebreus 1,1-2; Catecismo, 31-35."""),
        clarification = LocalizedText("""Es importante no responder simplemente que Dios se oculta para proteger nuestra libertad. Conocer que alguien existe no elimina nuestra capacidad de amarlo o rechazarlo.

La ocultación divina sigue siendo un problema filosófico serio, especialmente cuando hablamos de personas sinceramente abiertas a creer que no encuentran razones suficientes.

La fe católica afirma que Dios busca a todos; no presume conocer por qué cada persona experimenta su presencia o su silencio de manera diferente.""", """It is important not to simply answer that God hides to protect our freedom. Knowing that someone exists does not remove our capacity to love or reject them.

Divine hiddenness remains a serious philosophical problem, especially when we speak of people sincerely open to believing who do not find sufficient reasons.

Catholic faith affirms that God seeks everyone; it does not presume to know why each person experiences His presence or His silence differently.""", """É importante não responder simplesmente que Deus se oculta para proteger nossa liberdade. Conhecer que alguém existe não elimina nossa capacidade de amá-lo ou rejeitá-lo.

O ocultamento divino continua sendo um problema filosófico sério, especialmente quando falamos de pessoas sinceramente abertas a crer que não encontram razões suficientes.

A fé católica afirma que Deus busca a todos; não presume conhecer por que cada pessoa experimenta sua presença ou seu silêncio de maneira diferente.""")
    ),
    ApologeticsQuestion(
        number = 5,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Cómo sabemos que el Dios cristiano es verdadero y no una invención humana?""", """How do we know that the Christian God is true and not a human invention?""", """Como sabemos que o Deus cristão é verdadeiro e não uma invenção humana?"""),
        answer = LocalizedText("""La respuesta directa distingue dos caminos. El primero es filosófico: argumentos como la contingencia del universo, las leyes inteligibles de la naturaleza y el fundamento de la moral intentan mostrar que creer en Dios puede ser racional (Sabiduría 13,1-9; Romanos 1,20). El segundo es histórico: el cristianismo hace afirmaciones concretas sobre Jesús de Nazaret, su crucifixión y la proclamación muy temprana de su resurrección (1 Corintios 15,3-8). Probar que es razonable creer en un Creador no basta para probar todo el catolicismo; hace falta además razonar sobre Cristo, la revelación y la Iglesia.

Imagina a un detective que recibe una carta atribuida a un abuelo desaparecido: compara el papel, la letra y los datos internos, y luego comprueba si coincide con otros documentos familiares. La analogía es imperfecta, pero muestra la diferencia entre juzgar un documento por indicios y aceptar cada frase sin examen.

La objeción más fuerte dice: «Los argumentos filosóficos prueban demasiado o demasiado poco, y la resurrección no es un hecho histórico neutral». La respuesta honesta es que ningún argumento universalmente aceptado demuestra por sí solo todos los dogmas. La existencia histórica de Jesús y su crucifixión están ampliamente respaldadas; valorar la resurrección como intervención divina exige además un juicio filosófico y de fe.""", """The direct answer distinguishes two paths. The first is philosophical: arguments such as the contingency of the universe, the intelligible laws of nature and the grounding of morality attempt to show that believing in God can be rational (Wisdom 13:1-9; Romans 1:20). The second is historical: Christianity makes concrete assertions about Jesus of Nazareth, His crucifixion and the very early proclamation of His resurrection (1 Corinthians 15:3-8). Proving that it is reasonable to believe in a Creator is not enough to prove all of Catholicism; further reasoning about Christ, revelation and the Church is also needed.

Imagine a detective who receives a letter attributed to a missing grandfather: he compares the paper, the handwriting and the internal details, and then checks whether it matches other family documents. The analogy is imperfect, but it shows the difference between judging a document by evidence and accepting every sentence without examination.

The strongest objection says: "The philosophical arguments prove too much or too little, and the resurrection is not a neutral historical fact." The honest answer is that no universally accepted argument demonstrates all dogmas by itself. The historical existence of Jesus and His crucifixion are widely supported; assessing the resurrection as a divine intervention also requires a philosophical and faith judgment.""", """A resposta direta distingue dois caminhos. O primeiro é filosófico: argumentos como a contingência do universo, as leis inteligíveis da natureza e o fundamento da moral tentam mostrar que crer em Deus pode ser racional (Sabedoria 13,1-9; Romanos 1,20). O segundo é histórico: o cristianismo faz afirmações concretas sobre Jesus de Nazaré, sua crucifixão e a proclamação muito antiga de sua ressurreição (1 Coríntios 15,3-8). Provar que é razoável crer em um Criador não basta para provar todo o catolicismo; é preciso também raciocinar sobre Cristo, a revelação e a Igreja.

Imagine um detetive que recebe uma carta atribuída a um avô desaparecido: compara o papel, a letra e os dados internos e depois verifica se coincide com outros documentos da família. A analogia é imperfeita, mas mostra a diferença entre julgar um documento por indícios e aceitar cada frase sem exame.

A objeção mais forte diz: "Os argumentos filosóficos provam demais ou de menos, e a ressurreição não é um fato histórico neutro". A resposta honesta é que nenhum argumento universalmente aceito demonstra por si só todos os dogmas. A existência histórica de Jesus e sua crucifixão estão amplamente respaldadas; avaliar a ressurreição como intervenção divina exige também um juízo filosófico e de fé."""),
        foundation = LocalizedText("""Sabiduría 13,1-9; Romanos 1,20; 1 Corintios 15,3-8; Catecismo, 31-38.""", """Wisdom 13:1-9; Romans 1:20; 1 Corinthians 15:3-8; Catechism, 31-38.""", """Sabedoria 13,1-9; Romanos 1,20; 1 Coríntios 15,3-8; Catecismo, 31-38."""),
        clarification = LocalizedText("""Ningún argumento filosófico universalmente aceptado demuestra todos los dogmas cristianos. La historicidad de Jesús y de su crucifixión está ampliamente respaldada; la resurrección, como intervención divina, requiere una valoración histórica y filosófica adicional.""", """No universally accepted philosophical argument demonstrates all Christian dogmas. The historicity of Jesus and His crucifixion is widely supported; the resurrection, as a divine intervention, requires additional historical and philosophical assessment.""", """Nenhum argumento filosófico universalmente aceito demonstra todos os dogmas cristãos. A historicidade de Jesus e de sua crucifixão é amplamente respaldada; a ressurreição, como intervenção divina, requer uma avaliação histórica e filosófica adicional.""")
    ),
    ApologeticsQuestion(
        number = 6,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios no necesita nada, ¿por qué nos creó y desea que lo adoremos?""", """If God needs nothing, why did He create us and desire us to adore Him?""", """Se Deus não precisa de nada, por que nos criou e deseja que o adoremos?"""),
        answer = LocalizedText("""La respuesta directa es que Dios creó libremente por amor, no para satisfacer una necesidad. Dios es perfectamente autosuficiente (Hechos 17,24-25) y la creación no le añade nada. La adoración no completa a Dios; pone a la criatura en la verdad sobre su origen y la orienta hacia el bien y hacia los demás (Salmo 50,9-15). Por eso la Iglesia la enseña como el primer mandamiento: no un pago a Dios, sino reconocimiento de que dependemos de Él para ser y para vivir bien (Catecismo, 2096-2097).

Piensa en un profesor que enseña a escribir a niños pequeños: no necesita sus notas para saber escribir ni para ser profesor, pero les enseña porque su bien lo requiere. La analogía no explica el amor divino, pero distingue entre necesitar algo y desear el bien de otro.

La objeción más fuerte dice: «Si Dios no necesita adoración, pedirla es vanidad o narcisismo». La respuesta es que la adoración cristiana no es un cumplido para un Dios inseguro, sino disciplina del corazón: implica justicia, amor y transformación de vida, no solo palabras. Aun así, «Dios creó por amor» es una afirmación teológica, no una conclusión comprobable en un laboratorio.""", """The direct answer is that God created freely out of love, not to satisfy a need. God is perfectly self-sufficient (Acts 17:24-25) and creation adds nothing to Him. Worship does not complete God; it places the creature in the truth about its origin and directs it toward the good and toward others (Psalm 50:9-15). Therefore the Church teaches it as the first commandment: not a payment to God, but recognition that we depend on Him to be and to live well (Catechism, 2096-2097).

Think of a teacher who teaches small children to write: he does not need their grades in order to know how to write or to be a teacher, but he teaches them because their good requires it. The analogy does not explain divine love, but it distinguishes between needing something and desiring another's good.

The strongest objection says: "If God needs no worship, asking for it is vanity or narcissism." The answer is that Christian worship is not a compliment for an insecure God, but discipline of the heart: it implies justice, love and transformation of life, not just words. Even so, "God created out of love" is a theological affirmation, not a conclusion verifiable in a laboratory.""", """A resposta direta é que Deus criou livremente por amor, não para satisfazer uma necessidade. Deus é perfeitamente autossuficiente (Atos 17,24-25) e a criação não lhe acrescenta nada. A adoração não completa Deus; coloca a criatura na verdade sobre sua origem e a orienta para o bem e para os outros (Salmo 50,9-15). Por isso a Igreja a ensina como o primeiro mandamento: não um pagamento a Deus, mas reconhecimento de que dependemos dEle para ser e para viver bem (Catecismo, 2096-2097).

Pense em um professor que ensina crianças pequenas a escrever: não precisa de suas notas para saber escrever nem para ser professor, mas as ensina porque o bem delas o requer. A analogia não explica o amor divino, mas distingue entre precisar de algo e desejar o bem do outro.

A objeção mais forte diz: "Se Deus não precisa de adoração, pedi-la é vaidade ou narcisismo". A resposta é que a adoração cristã não é um elogio para um Deus inseguro, mas disciplina do coração: implica justiça, amor e transformação de vida, não apenas palavras. Ainda assim, "Deus criou por amor" é uma afirmação teológica, não uma conclusão verificável em um laboratório."""),
        foundation = LocalizedText("""Hechos 17,24-25; Salmo 50,9-15; Efesios 1,3-12; Catecismo, 293-294 y 2096-2097.""", """Acts 17:24-25; Psalm 50:9-15; Ephesians 1:3-12; Catechism, 293-294 and 2096-2097.""", """Atos 17,24-25; Salmo 50,9-15; Efésios 1,3-12; Catecismo, 293-294 e 2096-2097."""),
        clarification = LocalizedText("""Decir que Dios crea por amor pertenece a la interpretación teológica de la creación. No es una conclusión que podamos comprobar experimentalmente.

La adoración auténtica tampoco consiste solamente en palabras: implica justicia, amor y transformación de vida.""", """To say that God created out of love belongs to the theological interpretation of creation. It is not a conclusion we can verify experimentally.

Authentic worship also does not consist only in words: it implies justice, love and transformation of life.""", """Dizer que Deus criou por amor pertence à interpretação teológica da criação. Não é uma conclusão que possamos comprovar experimentalmente.

A adoração autêntica também não consiste somente em palavras: implica justiça, amor e transformação de vida.""")
    ),
    ApologeticsQuestion(
        number = 7,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué Dios parece responder algunas oraciones y no otras?""", """Why does God seem to answer some prayers and not others?""", """Por que Deus parece responder algumas orações e não outras?"""),
        answer = LocalizedText("""La respuesta directa es que Dios escucha siempre, pero no siempre concede exactamente lo pedido. Jesús enseña a pedir con confianza sin prometer que recibiremos al pie de la letra lo solicitado. En Getsemaní pide que pase de Él el cáliz y acepta la voluntad del Padre (Mateo 26,39), y Pablo acepta que no fue liberado como esperaba de su dificultad (2 Corintios 12,7-10). La oración cristiana es relación y confianza, no una técnica que obligue a Dios.

Imagina a un niño que antes de almorzar pide un helado y su padre, que lo ama, le da fruta y le promete el helado para después. El padre no ignora la petición; la responde pensando en el bien del hijo. La analogía no vale para todo —hay oraciones por enfermos graves—, pero muestra por qué un «no ahora» puede ser respuesta y no silencio.

La objeción más fuerte dice: «Si Dios no concede lo pedido, la oración es inútil o placebo». La respuesta honesta es que la fe no puede identificar con certeza los motivos de Dios en cada caso particular, ni afirmar que alguien no sanó por falta de fe. Recocer el misterio forma parte de la oración adulta.""", """The direct answer is that God always hears, but does not always grant exactly what is asked. Jesus teaches us to ask with confidence without promising that we will receive literally what we ask for. In Gethsemane He asks that the cup pass from Him and accepts the Father's will (Matthew 26:39), and Paul accepts that he was not freed as he expected from his difficulty (2 Corinthians 12:7-10). Christian prayer is relationship and trust, not a technique that compels God.

Imagine a child who before lunch asks for an ice cream, and his father, who loves him, gives him fruit and promises the ice cream for later. The father is not ignoring the request; he is answering it with the child's good in mind. The analogy does not cover everything — there are prayers for the seriously ill — but it shows why a "not now" can be an answer and not silence.

The strongest objection says: "If God does not grant what is asked, prayer is useless or a placebo." The honest answer is that faith cannot identify with certainty God's motives in each particular case, nor claim that someone was not healed for lack of faith. Acknowledging the mystery is part of adult prayer.""", """A resposta direta é que Deus escuta sempre, mas não concede exatamente o que é pedido. Jesus ensina a pedir com confiança, sem prometer que receberemos literalmente o que pedimos. No Getsêmani pede que o cálice se afaste dele e aceita a vontade do Pai (Mateus 26,39), e Paulo aceita que não foi libertado como esperava de sua dificuldade (2 Coríntios 12,7-10). A oração cristã é relação e confiança, não uma técnica que obrigue Deus.

Imagine uma criança que antes do almoço pede um sorvete e seu pai, que a ama, lhe dá fruta e promete o sorvete para depois. O pai não ignora o pedido; responde pensando no bem do filho. A analogia não vale para tudo — há orações por doentes graves —, mas mostra por que um "não agora" pode ser resposta e não silêncio.

A objeção mais forte diz: "Se Deus não concede o que é pedido, a oração é inútil ou placebo". A resposta honesta é que a fé não pode identificar com certeza os motivos de Deus em cada caso particular nem afirmar que alguém não se curou por falta de fé. Reconhecer o mistério faz parte da oração adulta."""),
        foundation = LocalizedText("""Mateo 26,39; 2 Corintios 12,7-10; 1 Juan 5,14-15; Catecismo, 2734-2737.""", """Matthew 26:39; 2 Corinthians 12:7-10; 1 John 5:14-15; Catechism, 2734-2737.""", """Mateus 26,39; 2 Coríntios 12,7-10; 1 João 5,14-15; Catecismo, 2734-2737."""),
        clarification = LocalizedText("""No debemos afirmar que una persona no sanó porque le faltó fe, ni que la muerte de un niño fue necesaria para enseñar una lección.

La explicación católica reconoce que Dios puede actuar, pero no permite identificar con certeza sus motivos en acontecimientos particulares.""", """We must not say that a person was not healed because they lacked faith, nor that a child's death was necessary to teach a lesson.

The Catholic explanation acknowledges that God can act, but does not allow us to identify with certainty His motives in particular events.""", """Não devemos afirmar que uma pessoa não se curou porque lhe faltou fé, nem que a morte de uma criança foi necessária para ensinar uma lição.

A explicação católica reconhece que Deus pode agir, mas não permite identificar com certeza seus motivos em acontecimentos particulares.""")
    ),
    ApologeticsQuestion(
        number = 8,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""Si Dios tiene un plan, ¿también forman parte de él las guerras y los abusos?""", """If God has a plan, are wars and abuse also part of it?""", """Se Deus tem um plano, as guerras e os abusos também fazem parte dele?"""),
        answer = LocalizedText("""La respuesta directa es que no: Dios no manda cometer guerras ni abusos. La Biblia los condena, por lo que sería contradictorio que Dios ordenase lo que prohíbe. Hay que distinguir entre lo que Dios quiere moralmente y lo que permite a seres libres. Su providencia significa que el mal no puede frustrar definitivamente su propósito salvador —del mal puede salir bien, como en José vendido por sus hermanos (Génesis 50,20; Romanos 8,28)—, no que cada atrocidad sea una orden divina ni un bien oculto.

Imagina a un árbitro que permite que un jugador cometa una falta táctica porque no puede detener el partido sin destruir su libertad, pero no ordena la falta y la sanciona. La imagen es imperfecta —Dios no es un simple árbitro externo—, pero distingue permitir de querer moralmente.

La objeción más fuerte dice: «Si Dios pudo impedirlo y lo permitió sabiéndolo, es cómplice». La respuesta honesta es que la providencia no convierte la tragedia en buena ni excusa al agresor, ni debe usarse para impedir denuncias. El mal sigue siendo mal; la esperanza cristiana es que tampoco tendrá la última palabra.""", """The direct answer is no: God does not command wars or abuse. The Bible condemns them, so it would be contradictory for God to command what He prohibits. We must distinguish between what God morally wills and what He permits free beings. His providence means that evil cannot definitively frustrate His saving purpose — good can come out of evil, as with Joseph sold by his brothers (Genesis 50:20; Romans 8:28) — not that every atrocity is a divine command or a hidden good.

Imagine a referee who allows a player to commit a tactical foul because he cannot stop the match without destroying his freedom, but does not order the foul and sanctions it. The image is imperfect — God is not a mere external referee — but it distinguishes permitting from morally willing.

The strongest objection says: "If God could have prevented it and permitted it knowingly, He is an accomplice." The honest answer is that providence does not turn tragedy into good nor excuse the aggressor, nor should it be used to prevent reports. Evil remains evil; Christian hope is that it will not have the last word either.""", """A resposta direta é não: Deus não manda cometer guerras nem abusos. A Bíblia os condena, pelo que seria contraditório que Deus ordenasse o que proíbe. É preciso distinguir entre o que Deus quer moralmente e o que permite a seres livres. Sua providência significa que o mal não pode frustrar definitivamente seu propósito salvador — do mal pode sair bem, como em José vendido pelos irmãos (Gênesis 50,20; Romanos 8,28) —, não que cada atrocidade seja uma ordem divina nem um bem oculto.

Imagine um árbitro que permite que um jogador cometa uma falta tática porque não pode deter o jogo sem destruir sua liberdade, mas não ordena a falta e a sanciona. A imagem é imperfeita — Deus não é um simples árbitro externo —, mas distingue permitir de querer moralmente.

A objeção mais forte diz: "Se Deus podia impedi-lo e o permitiu sabendo, é cúmplice". A resposta honesta é que a providência não torna a tragédia boa nem desculpa o agressor, nem deve ser usada para impedir denúncias. O mal continua sendo mal; a esperança cristã é que ele também não terá a última palavra."""),
        foundation = LocalizedText("""Santiago 1,13; Génesis 50,20; Romanos 8,28; Catecismo, 311-314.""", """James 1:13; Genesis 50:20; Romans 8:28; Catechism, 311-314.""", """Tiago 1,13; Gênesis 50,20; Romanos 8,28; Catecismo, 311-314."""),
        clarification = LocalizedText("""Que Dios pueda obtener un bien después de una tragedia no convierte la tragedia en buena.

Y nunca debe utilizarse la providencia para excusar al agresor, impedir una denuncia o convencer a una víctima de que debe aceptar el mal.""", """That God can bring good after a tragedy does not make the tragedy good.

And providence must never be used to excuse the aggressor, prevent a complaint, or convince a victim that they must accept evil.""", """Que Deus possa obter um bem depois de uma tragédia não torna a tragédia boa.

E nunca se deve usar a providência para desculpar o agressor, impedir uma denúncia ou convencer uma vítima de que deve aceitar o mal.""")
    ),
    ApologeticsQuestion(
        number = 9,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Por qué el Dios del Antiguo Testamento parece violento y Jesús enseña el amor?""", """Why does the God of the Old Testament seem violent, while Jesus teaches love?""", """Por que o Deus do Antigo Testamento parece violento e Jesus ensina o amor?"""),
        answer = LocalizedText("""La respuesta directa es que el cristianismo afirma un solo Dios en toda la Biblia, no dos dioses distintos. El Antiguo Testamento recoge siglos de revelación progresiva, escritos en géneros y mentalidades diferentes; Jesús no presenta a otro Dios, sino que lleva esa revelación a su plenitud (Hebreos 1,1-3). Cuando Lc 9,54-56 los discípulos piden fuego contra una aldea, Jesús los corrige: el cristiano interpreta las Escrituras a la luz de Cristo.

Piensa en el código de circulación de un pueblo hace cien años: permitía circular a velocidad de carruaje y no exigía cinturón. No significa que la norma fuera el bien ideal para hoy; significa que regulaba una sociedad de entonces. La analogía no justifica ningún texto concreto, pero ayuda a separar regular una práctica antigua de aprobarla como ideal eterno.

La objeción más fuerte dice: «Hay órdenes explícitas de violencia y exterminio atribuidas a Dios; no basta decir que es género literario». La respuesta honesta es que no basta negar esos textos ni declararlos automáticamente simbólicos. La interpretación católica debe atender a género, contexto e finalidad y reconoce que sobre algunos pasajes sigue habiendo debate teológico serio.""", """The direct answer is that Christianity affirms one God throughout the Bible, not two distinct gods. The Old Testament gathers centuries of progressive revelation, written in different genres and mentalities; Jesus does not present another God, but brings that revelation to its fullness (Hebrews 1:1-3). When in Luke 9:54-56 the disciples ask for fire against a village, Jesus rebukes them: the Christian interprets the Scriptures in the light of Christ.

Think of a village's traffic code a hundred years ago: it allowed driving at carriage speed and did not require seatbelts. It does not mean the rule was the ideal good for today; it means it regulated a society of that time. The analogy does not justify any concrete text, but it helps to separate regulating an ancient practice from approving it as an eternal ideal.

The strongest objection says: "There are explicit orders of violence and extermination attributed to God; it is not enough to say it is a literary genre." The honest answer is that it is not enough to deny those texts or automatically declare them symbolic. Catholic interpretation must attend to genre, context and purpose and acknowledges that on some passages serious theological debate continues.""", """A resposta direta é que o cristianismo afirma um só Deus em toda a Bíblia, não dois deuses distintos. O Antigo Testamento reúne séculos de revelação progressiva, escritos em gêneros e mentalidades diferentes; Jesus não apresenta outro Deus, mas leva essa revelação à plenitude (Hebreus 1,1-3). Quando em Lc 9,54-56 os discípulos pedem fogo contra uma aldeia, Jesus os corrige: o cristão interpreta as Escrituras à luz de Cristo.

Pense no código de trânsito de uma aldeia há cem anos: permitia circular à velocidade de carruagem e não exigia cinto de segurança. Não significa que a norma fosse o bem ideal para hoje; significa que regulava uma sociedade de então. A analogia não justifica nenhum texto concreto, mas ajuda a separar regular uma prática antiga de aprová-la como ideal eterno.

A objeção mais forte diz: "Há ordens explícitas de violência e extermínio atribuídas a Deus; não basta dizer que é um gênero literário". A resposta honesta é que não basta negar esses textos nem declará-los automaticamente simbólicos. A interpretação católica deve atender ao gênero, ao contexto e à finalidade e reconhece que sobre alguns trechos continua um debate teológico sério."""),
        foundation = LocalizedText("""Mateo 5,17-48; Lucas 9,54-56; Hebreos 1,1-3; Catecismo, 121-123.""", """Matthew 5:17-48; Luke 9:54-56; Hebrews 1:1-3; Catechism, 121-123.""", """Mateus 5,17-48; Lucas 9,54-56; Hebreus 1,1-3; Catecismo, 121-123."""),
        clarification = LocalizedText("""Algunos relatos del Antiguo Testamento atribuyen explícitamente a Dios órdenes de violencia. No resulta suficiente negar que esos textos existan o declararlos automáticamente simbólicos.

La interpretación católica debe estudiar su género, contexto, finalidad y relación con Cristo. Sigue existiendo un debate teológico importante sobre cómo entender determinados pasajes.""", """Some Old Testament accounts explicitly attribute commands of violence to God. It is not enough to deny that those texts exist or to declare them automatically symbolic.

Catholic interpretation must study their genre, context, purpose and relation to Christ. An important theological debate continues on how to understand certain passages.""", """Alguns relatos do Antigo Testamento atribuem explicitamente a Deus ordens de violência. Não basta negar que esses textos existam ou declará-los automaticamente simbólicos.

A interpretação católica deve estudar seu gênero, contexto, finalidade e relação com Cristo. Continua um debate teológico importante sobre como entender determinados trechos.""")
    ),
    ApologeticsQuestion(
        number = 10,
        section = LocalizedText("""Dios, su existencia, el sufrimiento y la libertad humana""", """God, His existence, suffering and human freedom""", """Deus, Sua existência, o sofrimento e a liberdade humana"""),
        question = LocalizedText("""¿Puede salvarse una persona buena que no cree en Dios?""", """Can a good person who does not believe in God be saved?""", """Uma pessoa boa que não crê em Deus pode ser salva?"""),
        answer = LocalizedText("""La respuesta directa es: sí, es posible, según la doctrina católica. Quien sin culpa propia no conoce a Cristo ni a la Iglesia, pero busca sinceramente la verdad y hace el bien según su conciencia, puede alcanzar la salvación por la gracia de Dios (Catecismo, 846-848). Pablo afirma que los que no tienen la Ley pueden hacer por naturaleza lo que la Ley pide y que Dios juzga los secretos de los corazones (Romanos 2,14-16). Eso no significa que todas las religiones sean igualmente verdaderas ni que las buenas obras compren automáticamente el cielo: toda salvación procede de Dios por Jesucristo.

Imagina a un paciente que recibe una medicina que le cura sin saber su nombre ni su composición: la curación es real aunque desconozca cómo llegó a él. La analogía no explica la gracia, pero distingue entre beneficiarse algo y conocer su fuente.

La objeción protestante más fuerte dice: «Si los buenos sin fe pueden salvarse, Cristo es innecesario y la fe no importa». La respuesta es que la Iglesia no dice que Cristo sea innecesario, sino que su gracia puede actuar también donde no hay conocimiento explícito de Él. No conocemos el destino eterno de personas concretas ni podemos declarar que alguien esté condenado.""", """The direct answer is: yes, it is possible, according to Catholic doctrine. Whoever, without fault of their own, does not know Christ or the Church, but sincerely seeks the truth and does good according to their conscience, can attain salvation by God's grace (Catechism, 846-848). Paul affirms that those who do not have the Law can by nature do what the Law requires and that God judges the secrets of hearts (Romans 2:14-16). That does not mean all religions are equally true or that good works automatically buy heaven: all salvation comes from God through Jesus Christ.

Imagine a patient who receives a medicine that cures him without knowing its name or composition: the healing is real even though he does not know how it reached him. The analogy does not explain grace, but it distinguishes between benefiting from something and knowing its source.

The strongest Protestant objection says: "If good people without faith can be saved, Christ is unnecessary and faith does not matter." The answer is that the Church does not say Christ is unnecessary, but that His grace can also act where there is no explicit knowledge of Him. We do not know the eternal destiny of concrete persons nor can we declare that someone is condemned.""", """A resposta direta é: sim, é possível, segundo a doutrina católica. Quem, sem culpa própria, não conhece Cristo nem a Igreja, mas busca sinceramente a verdade e faz o bem segundo sua consciência, pode alcançar a salvação pela graça de Deus (Catecismo, 846-848). Paulo afirma que os que não têm a Lei podem, por natureza, fazer o que a Lei pede e que Deus julga os segredos dos corações (Romanos 2,14-16). Isso não significa que todas as religiões sejam igualmente verdadeiras nem que as boas obras comprem automaticamente o céu: toda salvação procede de Deus por Jesus Cristo.

Imagine um paciente que recebe um remédio que o cura sem saber seu nome nem sua composição: a cura é real, embora desconheça como chegou até ele. A analogia não explica a graça, mas distingue entre beneficiar-se de algo e conhecer sua fonte.

A objeção protestante mais forte diz: "Se os bons sem fé podem se salvar, Cristo é desnecessário e a fé não importa". A resposta é que a Igreja não diz que Cristo seja desnecessário, mas que sua graça pode atuar também onde não há conhecimento explícito dEle. Não conhecemos o destino eterno de pessoas concretas nem podemos declarar que alguém esteja condenado."""),
        foundation = LocalizedText("""Romanos 2,14-16; 1 Timoteo 2,4-6; Mateo 25,31-46; Catecismo, 846-848.""", """Romans 2:14-16; 1 Timothy 2:4-6; Matthew 25:31-46; Catechism, 846-848.""", """Romanos 2,14-16; 1 Timóteo 2,4-6; Mateus 25,31-46; Catecismo, 846-848."""),
        clarification = LocalizedText("""La Iglesia no afirma conocer el destino eterno de cada persona ni sostiene que cualquier creencia sea igualmente verdadera.

Tampoco enseña que una persona deba declarar explícitamente su fe católica para que Dios pueda actuar en ella cuando existe ignorancia no culpable.""", """The Church does not claim to know the eternal destiny of each person, nor does it hold that every belief is equally true.

Nor does it teach that a person must explicitly declare their Catholic faith for God to act in them when there is non-culpable ignorance.""", """A Igreja não afirma conhecer o destino eterno de cada pessoa nem sustenta que qualquer crença seja igualmente verdadeira.

Tampouco ensina que uma pessoa deva declarar explicitamente sua fé católica para que Deus possa atuar nela quando existe ignorância não culpável.""")
    ),
    ApologeticsQuestion(
        number = 11,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué aceptar la Tradición y el Magisterio si ya tenemos la Biblia?""", """Why accept Tradition and the Magisterium if we already have the Bible?""", """Por que aceitar a Tradição e o Magistério se já temos a Bíblia?"""),
        answer = LocalizedText("""La respuesta directa es que el Nuevo Testamento no presenta la enseñanza cristiana como transmitida solo por libros. Pablo manda conservar las tradiciones recibidas «oralmente o por carta» (2 Tesalonicenses 2,15) y encarga a Timoteo transmitirlas a personas capaces de enseñar a otros (2 Timoteo 2,2). La predicación apostólica precedió a la redacción completa y al reconocimiento del canon: la primera comunidad vivió de la tradición viva y luego escribió libros para sostenerla (Juan 21,25). De ahí que la Iglesia hable de Escritura y Tradición juntas y de un Magisterio que sirve para custodiar la interpretación (Catecismo, 74-87).

Piensa en una receta familiar de la abuela: la familia cocinó el plato durante décadas transmitiéndolo de palabra y luego alguien lo escribió en un cuaderno. Quien solo lee el cuaderno sin conocer la cocina familiar puede copiar mal. La analogía no prueba documentos concretos, pero muestra por qué el libro nace dentro de una práctica viva.

La objeción protestante más fuerte dice: «Esos versículos prueban que hubo tradición oral, no que el Magisterio actual tenga autoridad». La respuesta honesta es que ningún versículo aislado prueba automáticamente que cada tradición posterior sea apostólica: hay que demostrar la continuidad de cada enseñanza con la revelación.""", """The direct answer is that the New Testament does not present Christian teaching as transmitted only through books. Paul asks Christians to keep the traditions received "orally or by letter" (2 Thessalonians 2:15) and entrusts Timothy to pass them on to people capable of teaching others (2 Timothy 2:2). Apostolic preaching preceded the complete writing and recognition of the canon: the first community lived from living tradition and then wrote books to sustain it (John 21:25). Hence the Church speaks of Scripture and Tradition together and of a Magisterium that serves to safeguard interpretation (Catechism, 74-87).

Think of a family recipe from a grandmother: the family cooked the dish for decades, passing it on orally, and later someone wrote it in a notebook. Whoever only reads the notebook without knowing the family kitchen may copy it badly. The analogy does not prove specific documents, but it shows why the book is born within a living practice.

The strongest Protestant objection says: "Those verses prove there was oral tradition, not that the present Magisterium has authority." The honest answer is that no isolated verse automatically proves that every later tradition is apostolic: the continuity of each teaching with revelation must be demonstrated.""", """A resposta direta é que o Novo Testamento não apresenta o ensino cristão como transmitido apenas por livros. Paulo manda conservar as tradições recebidas "oralmente ou por carta" (2 Tessalonicenses 2,15) e encarrega Timóteo de transmiti-las a pessoas capazes de ensinar a outros (2 Timóteo 2,2). A pregação apostólica precedeu a redação completa e o reconhecimento do cânon: a primeira comunidade viveu da tradição viva e depois escreveu livros para sustentá-la (João 21,25). Daí que a Igreja fale de Escritura e Tradição juntas e de um Magistério que serve para custodiar a interpretação (Catecismo, 74-87).

Pense em uma receita de família da avó: a família cozinhou o prato durante décadas, transmitindo-o oralmente, e depois alguém o escreveu em um caderno. Quem só lê o caderno sem conhecer a cozinha da família pode copiá-lo mal. A analogia não prova documentos concretos, mas mostra por que o livro nasce dentro de uma prática viva.

A objeção protestante mais forte diz: "Esses versículos provam que houve tradição oral, não que o Magistério atual tenha autoridade". A resposta honesta é que nenhum versículo isolado prova automaticamente que cada tradição posterior seja apostólica: é preciso demonstrar a continuidade de cada ensino com a revelação."""),
        foundation = LocalizedText("""2 Tesalonicenses 2,15; 2 Timoteo 2,2; 1 Timoteo 3,15; Juan 21,25; Catecismo, 74-87.""", """2 Thessalonians 2:15; 2 Timothy 2:2; 1 Timothy 3:15; John 21:25; Catechism, 74-87.""", """2 Tessalonicenses 2,15; 2 Timóteo 2,2; 1 Timóteo 3,15; João 21,25; Catecismo, 74-87."""),
        clarification = LocalizedText("""Estos versículos no prueban automáticamente que toda tradición católica posterior sea apostólica. Para sostener una enseñanza concreta hay que mostrar su continuidad con la revelación, no simplemente llamarla tradición.

La discusión con sola Scriptura consiste en determinar qué autoridad normativa corresponde a la transmisión apostólica y cómo reconocerla.""", """These verses do not automatically prove that every later Catholic tradition is apostolic. To sustain a concrete teaching one must show its continuity with revelation, not simply call it tradition.

The discussion with sola Scriptura concerns determining what normative authority belongs to apostolic transmission and how to recognize it.""", """Esses versículos não provam automaticamente que toda tradição católica posterior seja apostólica. Para sustentar um ensino concreto é preciso mostrar sua continuidade com a revelação, não simplesmente chamá-lo de tradição.

A discussão com a sola Scriptura consiste em determinar que autoridade normativa corresponde à transmissão apostólica e como reconhecê-la.""")
    ),
    ApologeticsQuestion(
        number = 12,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Quién decidió qué libros pertenecen a la Biblia?""", """Who decided which books belong to the Bible?""", """Quem decidiu quais livros pertencem à Bíblia?"""),
        answer = LocalizedText("""La respuesta directa es que no hubo un único momento en el que Jesús dejara una lista escrita. El canon se reconoció gradualmente en la vida de las comunidades cristianas: por el uso en la liturgia, la recepción en diversas Iglesias, la cercanía a la tradición apostólica y el discernimiento eclesial. La distinción católica es que la Iglesia reconoce los libros inspirados; no los hace inspirados con una votación. No es exacto decir que un solo concilio decidiera todo de golpe: hubo debates y variaciones regionales (Catecismo, 120).

Imagina la biblioteca de una escuela que durante años usa los mismos libros en clase, los presta entre alumnos y los conserva en el estante de referencia. Al final cataloga como «clásicos de la escuela» los que ya venían usándose, no inventa su valor en el catálogo. La imagen es limitada, pero muestra la diferencia entre reconocer un uso y crearlo desde cero.

La objeción más fuerte dice: «Si la Iglesia eligió los libros, pudo inventarlos». La respuesta es que elegir o reconocer no equivale a inventar, aunque esto por sí solo no demuestra todas las afirmaciones posteriores sobre la autoridad del Magisterio. Por eso el fundamento se apoya en 2 Pedro 3,15-16 y 1 Timoteo 3,15, no en una lista entregada en un día concreto.""", """The direct answer is that there was no single moment when Jesus left a written list. The canon was gradually recognized in the life of Christian communities: through liturgical use, reception in various Churches, closeness to apostolic tradition and ecclesial discernment. The Catholic distinction is that the Church recognizes the inspired books; it does not make them inspired by a vote. It is not accurate to say that a single council decided everything at once: there were debates and regional variations (Catechism, 120).

Imagine a school library that for years uses the same books in class, lends them between students and keeps them on the reference shelf. In the end it catalogues as "school classics" those already in use; it does not invent their value in the catalogue. The image is limited, but it shows the difference between recognizing a use and creating it from scratch.

The strongest objection says: "If the Church chose the books, it could have invented them." The answer is that choosing or recognizing is not the same as inventing, although this by itself does not demonstrate all later claims about the authority of the Magisterium. That is why the foundation rests on 2 Peter 3:15-16 and 1 Timothy 3:15, not on a list delivered on a specific day.""", """A resposta direta é que não houve um único momento em que Jesus deixou uma lista escrita. O cânon foi reconhecido gradualmente na vida das comunidades cristãs: pelo uso na liturgia, pela recepção em diversas Igrejas, pela proximidade à tradição apostólica e pelo discernimento eclesial. A distinção católica é que a Igreja reconhece os livros inspirados; não os torna inspirados com um voto. Não é exato dizer que um único concílio decidiu tudo de uma vez: houve debates e variações regionais (Catecismo, 120).

Imagine a biblioteca de uma escola que durante anos usa os mesmos livros em aula, os empresta entre alunos e os guarda na estante de referência. No fim cataloga como "clássicos da escola" os que já vinham sendo usados; não inventa seu valor no catálogo. A imagem é limitada, mas mostra a diferença entre reconhecer um uso e criá-lo do zero.

A objeção mais forte diz: "Se a Igreja escolheu os livros, pôde inventá-los". A resposta é que escolher ou reconhecer não equivale a inventar, embora isso por si só não demonstre todas as afirmações posteriores sobre a autoridade do Magistério. Por isso o fundamento se apoia em 2 Pedro 3,15-16 e 1 Timóteo 3,15, não em uma lista entregue em um dia concreto."""),
        foundation = LocalizedText("""2 Pedro 3,15-16; 1 Timoteo 3,15; Catecismo, 120.""", """2 Peter 3:15-16; 1 Timothy 3:15; Catechism, 120.""", """2 Pedro 3,15-16; 1 Timóteo 3,15; Catecismo, 120."""),
        clarification = LocalizedText("""La formación histórica del canon es más compleja que afirmar que un único concilio decidió todo. Hubo debates y variaciones regionales.

El razonamiento católico destaca la necesidad de una tradición eclesial para identificar el canon. Pero esto, por sí solo, no demuestra todas las afirmaciones posteriores sobre la autoridad del Magisterio.""", """The historical formation of the canon is more complex than claiming that a single council decided everything. There were debates and regional variations.

Catholic reasoning emphasizes the need for an ecclesial tradition to identify the canon. But this by itself does not demonstrate all later claims about the authority of the Magisterium.""", """A formação histórica do cânon é mais complexa do que afirmar que um único concílio decidiu tudo. Houve debates e variações regionais.

O raciocínio católico destaca a necessidade de uma tradição eclesial para identificar o cânon. Mas isso, por si só, não demonstra todas as afirmações posteriores sobre a autoridade do Magistério.""")
    ),
    ApologeticsQuestion(
        number = 13,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué los católicos tienen 73 libros bíblicos y muchos protestantes 66?""", """Why do Catholics have 73 biblical books and many Protestants 66?""", """Por que os católicos têm 73 livros bíblicos e muitos protestantes 66?"""),
        answer = LocalizedText("""La respuesta directa es que la diferencia está en siete libros del Antiguo Testamento —Tobías, Judit, Sabiduría, Eclesiástico, Baruc y los dos Macabeos— y en algunos pasajes añadidos de Ester y Daniel. La Iglesia católica los reconoce como canónicos, deuterocanónicos, y las iglesias ortodoxas también los incluyen, a veces con pequeñas diferencias. La Reforma protestante, en cambio, prefirió para el Antiguo Testamento el canon hebreo rabínico de 24 libros, por lo que muchas Biblias protestantes tienen 66 en total.

Imagina dos bibliotecas escolares: una conserva 66 tomos y otra conserva 73 porque además guarda unos anexos antiguos que la primera aparta como útiles pero no esenciales. Contar los tomos no dice por sí solo cuáles son los correctos; hay que estudiar su recepción histórica.

La objeción protestante más fuerte dice: «Esos libros no estaban en el canon hebreo y no aparecen citados por Jesús, luego no son inspirados». La respuesta honesta es que tampoco es exacto afirmar que todos los judíos del siglo I usaban exactamente el mismo canon, ni que todos los primeros cristianos aceptaban los mismos libros sin discusión. La controversia exige historia de recepción, no solo contar libros.""", """The direct answer is that the difference lies in seven Old Testament books — Tobit, Judith, Wisdom, Sirach, Baruch and the two books of Maccabees — and in some added passages in Esther and Daniel. The Catholic Church recognizes them as canonical, deuterocanonical, and the Orthodox churches also include them, sometimes with small differences. The Protestant Reformation, however, preferred for the Old Testament the rabbinic Hebrew canon of 24 books, so many Protestant Bibles have 66 in total.

Imagine two school libraries: one keeps 66 volumes and the other keeps 73 because it also preserves some ancient appendices that the first sets aside as useful but not essential. Counting the volumes does not by itself tell which are correct; their historical reception must be studied.

The strongest Protestant objection says: "Those books were not in the Hebrew canon and are not quoted by Jesus, so they are not inspired." The honest answer is that it is also not accurate to claim that all Jews of the first century used exactly the same canon, nor that all early Christians accepted the same books without discussion. The controversy requires a history of reception, not only counting books.""", """A resposta direta é que a diferença está em sete livros do Antigo Testamento — Tobias, Judite, Sabedoria, Eclesiástico, Baruque e os dois Macabeus — e em alguns trechos acrescentados de Ester e Daniel. A Igreja católica os reconhece como canônicos, deuterocanônicos, e as igrejas ortodoxas também os incluem, às vezes com pequenas diferenças. A Reforma protestante, em troca, preferiu para o Antigo Testamento o cânon hebraico rabínico de 24 livros, por isso muitas Bíblias protestantes têm 66 no total.

Imagine duas bibliotecas escolares: uma guarda 66 volumes e outra guarda 73 porque também conserva uns anexos antigos que a primeira separa como úteis, mas não essenciais. Contar os volumes não diz por si só quais são os corretos; é preciso estudar sua recepção histórica.

A objeção protestante mais forte diz: "Esses livros não estavam no cânon hebraico e não são citados por Jesus, logo não são inspirados". A resposta honesta é que tampouco é exato afirmar que todos os judeus do século I usavam exatamente o mesmo cânon, nem que todos os primeiros cristãos aceitavam os mesmos livros sem discussão. A controvérsia exige história da recepção, não apenas contar livros."""),
        foundation = LocalizedText("""Catecismo, 120; concilios históricos de Hipona y Cartago y definiciones de Florencia y Trento.""", """Catechism, 120; historical councils of Hippo and Carthage and the definitions of Florence and Trent.""", """Catecismo, 120; concílios históricos de Hipona e Cartago e definições de Florença e Trento."""),
        clarification = LocalizedText("""No es históricamente preciso decir que todos los judíos del siglo I utilizaban exactamente el mismo canon, ni que todos los primeros cristianos aceptaban sin discusión los mismos libros.

La Reforma favoreció un Antiguo Testamento basado en el canon hebreo rabínico. La controversia requiere estudiar la recepción histórica de los libros, no solamente contar cuántos aparecen en cada Biblia.""", """It is not historically accurate to say that all Jews of the first century used exactly the same canon, nor that all early Christians accepted the same books without discussion.

The Reformation favored an Old Testament based on the rabbinic Hebrew canon. The controversy requires studying the historical reception of the books, not only counting how many appear in each Bible.""", """Não é historicamente preciso dizer que todos os judeus do século I utilizavam exatamente o mesmo cânon, nem que todos os primeiros cristãos aceitavam sem discussão os mesmos livros.

A Reforma favoreceu um Antigo Testamento baseado no cânon hebraico rabínico. A controvérsia requer estudar a recepção histórica dos livros, não apenas contar quantos aparecem em cada Bíblia.""")
    ),
    ApologeticsQuestion(
        number = 14,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""Si la Biblia es inspirada, ¿por qué contiene relatos aparentemente contradictorios?""", """If the Bible is inspired, why does it contain apparently contradictory accounts?""", """Se a Bíblia é inspirada, por que contém relatos aparentemente contraditórios?"""),
        answer = LocalizedText("""La respuesta directa es que la inspiración no significa que todos los libros deban leerse como una crónica moderna de laboratorio. La Escritura incluye poesía, leyes, genealogías, narración histórica, parábolas, profecía y literatura apocalíptica (Catecismo, 105-119). Para saber lo que afirma un autor hay que preguntar primero qué quiso comunicar y en qué género. La Iglesia enseña que la Escritura comunica sin error la verdad que Dios quiso consignar para nuestra salvación, no que cada frase sea ciencia moderna (2 Timoteo 3,16).

Piensa en un chat familiar: alguien escribe «ya llegué» y dos minutos después «estoy en la puerta». Sin contexto parecen contradictorios; con el contexto, son dos momentos de un mismo trayecto. La analogía es sencilla, pero muestra por qué el contexto cambia el sentido.

La objeción más fuerte dice: «Algunas contradicciones reales no desaparecen con el género y la inerrancia es falsa». La respuesta honesta es que algunas discrepancias entre relatos son difíciles de armonizar y no deben inventarse explicaciones para hacerlas desaparecer. La Pontificia Comisión Bíblica insiste en estudiar género y contexto, no en forzar armonías.""", """The direct answer is that inspiration does not mean that all books must be read as a modern laboratory chronicle. Scripture includes poetry, law, genealogies, historical narration, parables, prophecy and apocalyptic literature (Catechism, 105-119). To know what an author affirms, we must first ask what he wanted to communicate and in which genre. The Church teaches that Scripture communicates without error the truth God intended to consign for our salvation, not that every sentence is modern science (2 Timothy 3:16).

Think of a family chat: someone writes "I have arrived" and two minutes later "I am at the door." Without context they seem contradictory; with context, they are two moments of the same journey. The analogy is simple, but it shows why context changes meaning.

The strongest objection says: "Some real contradictions do not disappear with genre, and inerrancy is false." The honest answer is that some discrepancies between accounts are difficult to harmonize and explanations should not be invented to make them disappear. The Pontifical Biblical Commission insists on studying genre and context, not on forcing harmonies.""", """A resposta direta é que a inspiração não significa que todos os livros devam ser lidos como uma crônica moderna de laboratório. A Escritura inclui poesia, leis, genealogias, narração histórica, parábolas, profecia e literatura apocalíptica (Catecismo, 105-119). Para saber o que afirma um autor é preciso perguntar primeiro o que quis comunicar e em que gênero. A Igreja ensina que a Escritura comunica sem erro a verdade que Deus quis consignar para nossa salvação, não que cada frase seja ciência moderna (2 Timóteo 3,16).

Pense em um chat de família: alguém escreve "já cheguei" e dois minutos depois "estou na porta". Sem contexto parecem contraditórios; com o contexto, são dois momentos de um mesmo trajeto. A analogia é simples, mas mostra por que o contexto muda o sentido.

A objeção mais forte diz: "Algumas contradições reais não desaparecem com o gênero e a inerrância é falsa". A resposta honesta é que algumas discrepâncias entre relatos são difíceis de harmonizar e não se devem inventar explicações para fazê-las desaparecer. A Pontifícia Comissão Bíblica insiste em estudar gênero e contexto, não em forçar harmonias."""),
        foundation = LocalizedText("""Lucas 1,1-4; 2 Timoteo 3,16; Catecismo, 105-119.""", """Luke 1:1-4; 2 Timothy 3:16; Catechism, 105-119.""", """Lucas 1,1-4; 2 Timóteo 3,16; Catecismo, 105-119."""),
        clarification = LocalizedText("""Algunas discrepancias entre relatos bíblicos son difíciles de armonizar. No debemos inventar explicaciones para hacer desaparecer cada diferencia.

La Pontificia Comisión Bíblica insiste en estudiar los textos según sus géneros y contextos históricos.""", """Some discrepancies between biblical accounts are difficult to harmonize. We must not invent explanations to make every difference disappear.

The Pontifical Biblical Commission insists on studying texts according to their genres and historical contexts.""", """Algumas discrepâncias entre relatos bíblicos são difíceis de harmonizar. Não devemos inventar explicações para fazer desaparecer cada diferença.

A Pontifícia Comissão Bíblica insiste em estudar os textos segundo seus gêneros e contextos históricos.""")
    ),
    ApologeticsQuestion(
        number = 15,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Cómo encajan Adán y Eva con la evolución humana?""", """How do Adam and Eve fit with human evolution?""", """Como se encaixam Adão e Eva com a evolução humana?"""),
        answer = LocalizedText("""La respuesta directa es que evolución biológica y creación divina preguntan cosas distintas. La ciencia estudia los procesos naturales por los que aparecieron y se diversificaron los seres vivos; la teología pregunta por el fundamento último de la existencia, la dignidad humana y nuestra relación con Dios (Catecismo, 283-289). Génesis enseña, entre otras cosas, que el ser humano depende de Dios, tiene dignidad y está llamado a la comunión (Génesis 1-3). La Iglesia no exige leer cada detalle del relato como un informe científico, pero tampoco reduce el pecado original a una metáfora sin relación con nosotros (Romanos 5,12-21).

Imagina a unos albañiles que explican cómo colocan los ladrillos y a la familia que explica para quién y por qué construye la casa. Uno describe el método y la otra el propósito; no se contradicen porque no responden lo mismo. La analogía ayuda a no mezclar niveles.

La objeción atea más fuerte dice: «La ciencia muestra poblaciones, no una sola pareja, y sin Adán histórico se cae el pecado original». La respuesta honesta es que esta armonía exige una reflexión teológica cuidadosa y sigue abierta en puntos concretos, incluida la pregunta por los primeros seres humanos.""", """The direct answer is that biological evolution and divine creation ask different questions. Science studies the natural processes by which living beings appeared and diversified; theology asks about the ultimate foundation of existence, human dignity and our relationship with God (Catechism, 283-289). Genesis teaches, among other things, that the human being depends on God, has dignity and is called to communion (Genesis 1-3). The Church does not require reading every detail of the account as a scientific report, but neither does it reduce original sin to a metaphor unrelated to us (Romans 5:12-21).

Imagine builders who explain how they lay bricks and the family that explains for whom and why it builds the house. One describes the method and the other the purpose; they do not contradict each other because they are not answering the same thing. The analogy helps not to mix levels.

The strongest atheist objection says: "Science shows populations, not a single couple, and without a historical Adam original sin collapses." The honest answer is that this harmony requires careful theological reflection and remains open on concrete points, including the question of the first human beings.""", """A resposta direta é que evolução biológica e criação divina perguntam coisas distintas. A ciência estuda os processos naturais pelos quais surgiram e se diversificaram os seres vivos; a teologia pergunta pelo fundamento último da existência, pela dignidade humana e nossa relação com Deus (Catecismo, 283-289). O Gênesis ensina, entre outras coisas, que o ser humano depende de Deus, tem dignidade e é chamado à comunhão (Gênesis 1-3). A Igreja não exige ler cada detalhe do relato como um relatório científico, mas tampouco reduz o pecado original a uma metáfora sem relação conosco (Romanos 5,12-21).

Imagine pedreiros que explicam como colocam os tijolos e a família que explica para quem e por que constrói a casa. Um descreve o método e a outra o propósito; não se contradizem porque não respondem a mesma coisa. A analogia ajuda a não misturar níveis.

A objeção ateia mais forte diz: "A ciência mostra populações, não um único casal, e sem Adão histórico o pecado original desmorona". A resposta honesta é que essa harmonia exige uma reflexão teológica cuidadosa e continua aberta em pontos concretos, incluindo a pergunta pelos primeiros seres humanos."""),
        foundation = LocalizedText("""Génesis 1-3; Sabiduría 2,23-24; Romanos 5,12-21; Catecismo, 283-289 y 390.""", """Genesis 1-3; Wisdom 2:23-24; Romans 5:12-21; Catechism, 283-289 and 390.""", """Gênesis 1-3; Sabedoria 2,23-24; Romanos 5,12-21; Catecismo, 283-289 e 390."""),
        clarification = LocalizedText("""La cuestión de los primeros seres humanos, el pecado original y el origen histórico de la humanidad sigue requiriendo una reflexión teológica cuidadosa.

La Iglesia no exige interpretar cada detalle narrativo del Génesis como un informe científico, pero tampoco reduce el pecado original a una simple metáfora sin relación con la condición humana.""", """The question of the first human beings, original sin and the historical origin of humanity still requires careful theological reflection.

The Church does not require interpreting every narrative detail of Genesis as a scientific report, but neither does it reduce original sin to a mere metaphor unrelated to the human condition.""", """A questão dos primeiros seres humanos, do pecado original e da origem histórica da humanidade continua requerendo uma reflexão teológica cuidadosa.

A Igreja não exige interpretar cada detalhe narrativo do Gênesis como um relatório científico, mas tampouco reduz o pecado original a uma simples metáfora sem relação com a condição humana.""")
    ),
    ApologeticsQuestion(
        number = 16,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué la Biblia contiene leyes sobre esclavitud, guerra y desigualdad?""", """Why does the Bible contain laws about slavery, war and inequality?""", """Por que a Bíblia contém leis sobre escravidão, guerra e desigualdade?"""),
        answer = LocalizedText("""La respuesta directa es que la revelación bíblica se transmitió dentro de sociedades antiguas con instituciones injustas que hoy reconocemos como gravemente inaceptables. Que un texto antiguo regule una práctica no significa necesariamente que esa práctica sea el ideal moral definitivo. Jesús mismo explica que algunas disposiciones de la Ley de Moisés respondían a la dureza del corazón humano (Mateo 19,8), y el cristiano lee las normas antiguas a la luz de Cristo, de la dignidad de toda persona (Gálatas 3,28) y del mandamiento del amor (Mateo 22,37-40).

Piensa en un reglamento de una fábrica de 1800 que fija una jornada de catorce horas: regulaba el trabajo real de entonces, pero nadie hoy debería presentarlo como el horario justo. La analogía no absuelve ningún texto, pero distingue entre regular una realidad histórica y aprobarla moralmente.

La objeción más fuerte dice: «Si Dios inspiró leyes injustas, ¿cómo es bueno?». La respuesta honesta es que esto no elimina el problema ni justifica que cristianos de siglos posteriores perpetuaran esas prácticas. Las injusticias bíblicas y eclesiales merecen examinarse, no encubrirse con una interpretación cómoda.""", """The direct answer is that biblical revelation was transmitted within ancient societies with unjust institutions that we today recognize as gravely unacceptable. That an ancient text regulates a practice does not necessarily mean that practice is the definitive moral ideal. Jesus Himself explains that some provisions of the Law of Moses responded to the hardness of the human heart (Matthew 19:8), and the Christian reads ancient norms in the light of Christ, of the dignity of every person (Galatians 3:28) and of the commandment of love (Matthew 22:37-40).

Think of a factory regulation from 1800 that sets a fourteen-hour day: it regulated the real work of that time, but no one today should present it as the just schedule. The analogy does not absolve any text, but it distinguishes between regulating a historical reality and morally approving it.

The strongest objection says: "If God inspired unjust laws, how is He good?" The honest answer is that this does not remove the problem nor justify later Christians perpetuating those practices. Biblical and ecclesial injustices deserve to be examined, not covered up with a convenient interpretation.""", """A resposta direta é que a revelação bíblica foi transmitida dentro de sociedades antigas com instituições injustas que hoje reconhecemos como gravemente inaceitáveis. Que um texto antigo regule uma prática não significa necessariamente que essa prática seja o ideal moral definitivo. Jesus mesmo explica que algumas disposições da Lei de Moisés respondiam à dureza do coração humano (Mateus 19,8), e o cristão lê as normas antigas à luz de Cristo, da dignidade de toda pessoa (Gálatas 3,28) e do mandamento do amor (Mateus 22,37-40).

Pense em um regulamento de fábrica de 1800 que fixa uma jornada de catorze horas: regulava o trabalho real de então, mas ninguém hoje deveria apresentá-lo como o horário justo. A analogia não absolve nenhum texto, mas distingue entre regular uma realidade histórica e aprová-la moralmente.

A objeção mais forte diz: "Se Deus inspirou leis injustas, como é bom?". A resposta honesta é que isso não elimina o problema nem justifica que cristãos de séculos posteriores perpetuassem essas práticas. As injustiças bíblicas e eclesiais merecem ser examinadas, não encobertas com uma interpretação cômoda."""),
        foundation = LocalizedText("""Mateo 19,8; Gálatas 3,28; Filemón 15-16; Mateo 22,37-40.""", """Matthew 19:8; Galatians 3:28; Philemon 15-16; Matthew 22:37-40.""", """Mateus 19,8; Gálatas 3,28; Filemom 15-16; Mateus 22,37-40."""),
        clarification = LocalizedText("""Esto no elimina el problema de por qué los textos permitieron determinadas prácticas ni justifica que los cristianos históricos las perpetuaran.

Las injusticias bíblicas y eclesiales merecen ser examinadas, no encubiertas mediante una interpretación conveniente.""", """This does not remove the problem of why the texts allowed certain practices, nor does it justify historical Christians perpetuating them.

Biblical and ecclesial injustices deserve to be examined, not covered up by a convenient interpretation.""", """Isso não elimina o problema de por que os textos permitiram determinadas práticas nem justifica que cristãos históricos as perpetuassem.

As injustiças bíblicas e eclesiais merecem ser examinadas, não encobertas mediante uma interpretação conveniente.""")
    ),
    ApologeticsQuestion(
        number = 17,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Quién decide cuándo la Biblia se interpreta literalmente y cuándo simbólicamente?""", """Who decides when the Bible is interpreted literally and when symbolically?""", """Quem decide quando a Bíblia se interpreta literalmente e quando simbolicamente?"""),
        answer = LocalizedText("""La respuesta directa es que no debería decidirlo la conveniencia doctrinal, sino el género literario, el contexto histórico, la intención del autor y la unidad de toda la Escritura (Catecismo, 109-119). Cuando Jesús dice «soy la puerta» (Juan 10,9) el contexto indica una imagen; cuando los Evangelios narran su crucifixión presentan un acontecimiento histórico. Nehemías 8,8 muestra la lectura pública explicada, y 2 Pedro 3,15-16 admite que algunas cartas son «difíciles de entender». La tradición católica usa la investigación bíblica y también la interpretación eclesial.

Piensa en un amigo que dice «me estoy muriendo» con la gripe en el sofá: no llama a urgencias, pero si lo dice en urgencias por un infarto, sí actúas. La frase es la misma, el contexto cambia el sentido. La analogía no decide textos concretos, pero muestra por qué el contexto importa.

La objeción más fuerte dice: «Los católicos eligen literal o simbólico según convenga». La respuesta honesta es que no siempre es fácil decidir qué es histórico y qué es figurado, y católicos y protestantes pueden usar métodos rigurosos y llegar a conclusiones distintas. Hay que razonar cada interpretación, no declarar evidente la propia.""", """The direct answer is that doctrinal convenience should not decide it, but literary genre, historical context, authorial intention and the unity of all Scripture should (Catechism, 109-119). When Jesus says "I am the door" (John 10:9) the context indicates an image; when the Gospels narrate His crucifixion they present a historical event. Nehemiah 8:8 shows public reading explained, and 2 Peter 3:15-16 admits that some letters are "hard to understand." Catholic tradition uses biblical research and also ecclesial interpretation.

Think of a friend who says "I'm dying" with the flu on the sofa: you do not call emergency services, but if he says it in emergency with a heart attack, you do act. The sentence is the same, the context changes the meaning. The analogy does not decide concrete texts, but it shows why context matters.

The strongest objection says: "Catholics choose literal or symbolic as convenient." The honest answer is that it is not always easy to decide what is historical and what is figurative, and Catholics and Protestants can use rigorous methods and reach different conclusions. Each interpretation must be reasoned, not declared evident.""", """A resposta direta é que a conveniência doutrinal não deveria decidir, mas sim o gênero literário, o contexto histórico, a intenção do autor e a unidade de toda a Escritura (Catecismo, 109-119). Quando Jesus diz "eu sou a porta" (João 10,9) o contexto indica uma imagem; quando os Evangelhos narram sua crucifixão apresentam um acontecimento histórico. Neemias 8,8 mostra a leitura pública explicada, e 2 Pedro 3,15-16 admite que algumas cartas são "difíceis de entender". A tradição católica usa a pesquisa bíblica e também a interpretação eclesial.

Pense em um amigo que diz "estou morrendo" com gripe no sofá: você não chama a emergência, mas se ele disser isso no pronto-socorro com um infarto, você age. A frase é a mesma, o contexto muda o sentido. A analogia não decide textos concretos, mas mostra por que o contexto importa.

A objeção mais forte diz: "Os católicos escolhem literal ou simbólico conforme convém". A resposta honesta é que nem sempre é fácil decidir o que é histórico e o que é figurado, e católicos e protestantes podem usar métodos rigorosos e chegar a conclusões distintas. É preciso raciocinar cada interpretação, não declarar evidente a própria."""),
        foundation = LocalizedText("""Lucas 24,27; Nehemías 8,8; 2 Pedro 3,15-16; Catecismo, 109-119.""", """Luke 24:27; Nehemiah 8:8; 2 Peter 3:15-16; Catechism, 109-119.""", """Lucas 24,27; Neemias 8,8; 2 Pedro 3,15-16; Catecismo, 109-119."""),
        clarification = LocalizedText("""No siempre es fácil decidir qué elementos son históricos o figurados. Los católicos y los protestantes pueden compartir métodos rigurosos de exégesis y llegar a conclusiones distintas.

Por eso es necesario razonar cada interpretación y no resolver las controversias simplemente afirmando que la propia lectura es evidente.""", """It is not always easy to decide which elements are historical or figurative. Catholics and Protestants can share rigorous exegetical methods and reach different conclusions.

Therefore each interpretation must be reasoned, and controversies must not be resolved by simply affirming that one's own reading is evident.""", """Nem sempre é fácil decidir quais elementos são históricos ou figurados. Católicos e protestantes podem compartilhar métodos rigorosos de exegese e chegar a conclusões distintas.

Por isso é necessário raciocinar cada interpretação e não resolver as controvérsias simplesmente afirmando que a própria leitura é evidente.""")
    ),
    ApologeticsQuestion(
        number = 18,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Podemos confiar en los Evangelios si fueron escritos décadas después de Jesús?""", """Can we trust the Gospels if they were written decades after Jesus?""", """Podemos confiar nos Evangelhos se foram escritos décadas depois de Jesus?"""),
        answer = LocalizedText("""La respuesta directa es que la distancia temporal por sí sola no invalida un testimonio antiguo. Los Evangelios se compusieron dentro del movimiento cristiano primitivo, que ya proclamaba la muerte y resurrección de Jesús: Pablo transmite en 1 Corintios 15,3-8 una fórmula anterior a su carta, y en Gálatas 1,18-19 cuenta que conoció en Jerusalén a testigos como Cefas y Santiago. Lucas afirma haber investigado con orden los relatos recibidos (Lucas 1,1-4). El historiador debe estudiar transmisión, relación entre fuentes, género literario y contexto del siglo I.

Imagina una biografía de un abuelo escrita treinta años después de su muerte por nietos que escucharon a sus hijos y conservaron sus cartas. Que no sea una transcripción literal no la hace inútil; obliga a comparar fuentes. La analogía no prueba ningún pasaje concreto, pero muestra por qué «treinta años después» no equivale a «inventado».

La objeción más fuerte dice: «Son escritos de fe y propaganda, no historia neutral». La respuesta honesta es que no son transcripciones neutrales: sus autores escriben desde la fe y con propósito teológico. Eso no los descalifica como fuentes históricas, pero exige analizar críticamente cada pasaje.""", """The direct answer is that temporal distance by itself does not invalidate an ancient testimony. The Gospels were composed within the primitive Christian movement, which already proclaimed the death and resurrection of Jesus: Paul transmits in 1 Corinthians 15:3-8 a formula earlier than his letter, and in Galatians 1:18-19 he recounts meeting in Jerusalem witnesses such as Cephas and James. Luke affirms that he investigated the received accounts in an orderly way (Luke 1:1-4). The historian must study transmission, the relation between sources, literary genre and first-century context.

Imagine a biography of a grandfather written thirty years after his death by grandchildren who heard his children and preserved his letters. That it is not a literal transcript does not make it useless; it requires comparing sources. The analogy does not prove any concrete passage, but it shows why "thirty years later" does not equal "invented."

The strongest objection says: "They are writings of faith and propaganda, not neutral history." The honest answer is that they are not neutral transcripts: their authors write from faith and with theological purpose. That does not disqualify them as historical sources, but it requires critically analyzing each passage.""", """A resposta direta é que a distância temporal por si só não invalida um testemunho antigo. Os Evangelhos foram compostos dentro do movimento cristão primitivo, que já proclamava a morte e ressurreição de Jesus: Paulo transmite em 1 Coríntios 15,3-8 uma fórmula anterior a sua carta e em Gálatas 1,18-19 conta que conheceu em Jerusalém testemunhas como Cefas e Tiago. Lucas afirma ter investigado com ordem os relatos recebidos (Lucas 1,1-4). O historiador deve estudar transmissão, relação entre fontes, gênero literário e contexto do século I.

Imagine uma biografia de um avô escrita trinta anos depois de sua morte por netos que ouviram seus filhos e conservaram suas cartas. Que não seja uma transcrição literal não a torna inútil; obriga a comparar fontes. A analogia não prova nenhum trecho concreto, mas mostra por que "trinta anos depois" não equivale a "inventado".

A objeção mais forte diz: "São escritos de fé e propaganda, não história neutra". A resposta honesta é que não são transcrições neutras: seus autores escrevem a partir da fé e com propósito teológico. Isso não os desqualifica como fontes históricas, mas exige analisar criticamente cada trecho."""),
        foundation = LocalizedText("""Lucas 1,1-4; 1 Corintios 15,3-8; Gálatas 1,18-19.""", """Luke 1:1-4; 1 Corinthians 15:3-8; Galatians 1:18-19.""", """Lucas 1,1-4; 1 Coríntios 15,3-8; Gálatas 1,18-19."""),
        clarification = LocalizedText("""Los Evangelios no son transcripciones neutrales. Sus autores escribieron desde la fe y con propósitos teológicos.

Eso no los descalifica automáticamente como fuentes históricas, pero exige analizarlos críticamente. La fiabilidad debe argumentarse pasaje por pasaje cuando corresponda.""", """The Gospels are not neutral transcripts. Their authors wrote from faith and with theological purposes.

That does not automatically disqualify them as historical sources, but it requires analyzing them critically. Reliability must be argued passage by passage when appropriate.""", """Os Evangelhos não são transcrições neutras. Seus autores escreveram a partir da fé e com propósitos teológicos.

Isso não os desqualifica automaticamente como fontes históricas, mas exige analisá-los criticamente. A confiabilidade deve ser argumentada trecho por trecho quando corresponder.""")
    ),
    ApologeticsQuestion(
        number = 19,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""¿Por qué fueron excluidos evangelios como el de Tomás o el de María Magdalena?""", """Why were gospels such as Thomas or Mary Magdalene excluded?""", """Por que foram excluídos evangelhos como o de Tomás ou o de Maria Madalena?"""),
        answer = LocalizedText("""La respuesta directa es que no todos los textos antiguos con nombre de apóstol tienen el mismo origen, antigüedad ni recepción. Al reconocer el canon la Iglesia atendió a la vinculación apostólica, al uso en las comunidades y a la conformidad con la fe recibida (Catecismo, 120). Varios textos apócrifos se compusieron después de los Evangelios canónicos, y sus atribuciones apostólicas no pueden aceptarse sin análisis. Con todo, son valiosos para estudiar la diversidad del cristianismo antiguo y deben examinarse según fecha, fuentes y transmisión (1 Juan 4,1).

Imagina una carta escolar firmada como si la hubiera escrito el director, pero en un papel moderno, con letra distinta y con datos imposibles. No basta el nombre de la firma: hay que comprobar origen y recepción. La analogía no juzga ningún texto concreto, pero muestra por qué una atribución debe verificarse.

La objeción más fuerte sostiene que hubo una conspiración para borrar textos incómodos. La respuesta honesta es que no hay pruebas de una única conspiración de ese tipo, ni es correcto declarar todos los apócrifos falsificaciones sin valor histórico. Cada escrito debe investigarse por sí mismo.""", """The direct answer is that not all ancient texts bearing an apostle's name have the same origin, antiquity or reception. When recognizing the canon the Church attended to apostolic connection, use in the communities and conformity with the received faith (Catechism, 120). Several apocryphal texts were composed after the canonical Gospels, and their apostolic attributions cannot be accepted without analysis. Nevertheless, they are valuable for studying the diversity of ancient Christianity and must be examined according to date, sources and transmission (1 John 4:1).

Imagine a school letter signed as if written by the principal, but on modern paper, with different handwriting and impossible details. The signature's name is not enough: origin and reception must be checked. The analogy does not judge any concrete text, but it shows why an attribution must be verified.

The strongest objection maintains that there was a conspiracy to erase uncomfortable texts. The honest answer is that there is no evidence of a single conspiracy of that kind, nor is it correct to declare all apocrypha worthless forgeries. Each writing must be investigated on its own.""", """A resposta direta é que nem todos os textos antigos com nome de apóstolo têm a mesma origem, antiguidade nem recepção. Ao reconhecer o cânon, a Igreja atendeu à vinculação apostólica, ao uso nas comunidades e à conformidade com a fé recebida (Catecismo, 120). Vários textos apócrifos foram compostos depois dos Evangelhos canônicos, e suas atribuições apostólicas não podem ser aceitas sem análise. Contudo, são valiosos para estudar a diversidade do cristianismo antigo e devem ser examinados segundo data, fontes e transmissão (1 João 4,1).

Imagine uma carta escolar assinada como se a tivesse escrito o diretor, mas em papel moderno, com letra distinta e dados impossíveis. Não basta o nome da assinatura: é preciso comprobar origem e recepção. A analogia não julga nenhum texto concreto, mas mostra por que uma atribuição deve ser verificada.

A objeção mais forte sustenta que houve uma conspiração para apagar textos incômodos. A resposta honesta é que não há provas de uma única conspiração desse tipo, nem é correto declarar todos os apócrifos falsificações sem valor histórico. Cada escrito deve ser investigado por si mesmo."""),
        foundation = LocalizedText("""Lucas 1,1-4; 2 Tesalonicenses 2,15; 1 Juan 4,1; Catecismo, 120.""", """Luke 1:1-4; 2 Thessalonians 2:15; 1 John 4:1; Catechism, 120.""", """Lucas 1,1-4; 2 Tessalonicenses 2,15; 1 João 4,1; Catecismo, 120."""),
        clarification = LocalizedText("""No es correcto afirmar que todos los apócrifos son falsificaciones sin valor histórico. Tampoco hay pruebas de una conspiración única destinada a eliminar todos los textos que cuestionaban una supuesta versión oficial.

Cada escrito debe investigarse según su fecha, fuentes, transmisión y contenido.""", """It is incorrect to claim that all apocrypha are worthless forgeries. Nor is there evidence of a single conspiracy aimed at eliminating all texts that questioned a supposed official version.

Each writing must be investigated according to its date, sources, transmission and content.""", """Não é correto afirmar que todos os apócrifos são falsificações sem valor histórico. Tampouco há provas de uma conspiração única destinada a eliminar todos os textos que questionavam uma suposta versão oficial.

Cada escrito deve ser investigado segundo sua data, fontes, transmissão e conteúdo.""")
    ),
    ApologeticsQuestion(
        number = 20,
        section = LocalizedText("""La Biblia, su autoridad y contradicciones""", """The Bible, its authority and apparent contradictions""", """A Bíblia, sua autoridade e aparentes contradições"""),
        question = LocalizedText("""Si la revelación no cambia, ¿por qué cambian enseñanzas y prácticas eclesiales?""", """If revelation does not change, why do ecclesial teachings and practices change?""", """Se a revelação não muda, por que mudam ensinamentos e práticas eclesiais?"""),
        answer = LocalizedText("""La respuesta directa es que hay que distinguir tres planos. Primero, el contenido esencial de la fe, que la Iglesia considera completo con Cristo y la época apostólica (Judas 3). Segundo, la comprensión progresiva de ese contenido: la misma verdad puede entenderse más profundamente con el tiempo (Juan 16,13). Tercero, normas disciplinares y prácticas que pueden modificarse según la época. El concilio de Jerusalén (Hechos 15,1-29) muestra a la primera Iglesia discerniendo una cuestión nueva en comunidad. El Catecismo (66-67 y 94) habla de depósito de la fe y de crecimiento en la comprensión (Catecismo, 94).

Piensa en el reglamento de seguridad de una empresa: el principio «proteger a las personas» no cambia, pero las normas de cascos, extintores y evacuación sí cambian con la tecnología. La analogía no demuestra ninguna doctrina concreta, pero distingue entre principio permanente y aplicación cambiante.

La objeción más fuerte dice: «El desarrollo doctrinal justifica cualquier cambio». La respuesta honesta es que no: cada enseñanza nueva debe guardar continuidad sustancial con la fe apostólica. Determinar si un desarrollo conserva esa continuidad es precisamente uno de los grandes debates entre católicos, ortodoxos y protestantes.""", """The direct answer is that three levels must be distinguished. First, the essential content of faith, which the Church considers complete with Christ and the apostolic age (Jude 3). Second, the progressive understanding of that content: the same truth can be understood more deeply over time (John 16:13). Third, disciplinary norms and practices that can change according to the age. The council of Jerusalem (Acts 15:1-29) shows the primitive Church discerning a new question in community. The Catechism (66-67 and 94) speaks of the deposit of faith and growth in understanding (Catechism, 94).

Think of a company's safety regulations: the principle "protect people" does not change, but the rules about helmets, extinguishers and evacuation do change with technology. The analogy does not demonstrate any concrete doctrine, but it distinguishes between a permanent principle and a changing application.

The strongest objection says: "Doctrinal development justifies any change." The honest answer is no: each new teaching must preserve substantial continuity with apostolic faith. Determining whether a development preserves that continuity is precisely one of the great debates between Catholics, Orthodox and Protestants.""", """A resposta direta é que é preciso distinguir três planos. Primeiro, o conteúdo essencial da fé, que a Igreja considera completo com Cristo e a época apostólica (Judas 3). Segundo, a compreensão progressiva desse conteúdo: a mesma verdade pode ser entendida mais profundamente com o tempo (João 16,13). Terceiro, normas disciplinares e práticas que podem mudar segundo a época. O concílio de Jerusalém (Atos 15,1-29) mostra a primeira Igreja discernindo uma questão nova em comunidade. O Catecismo (66-67 e 94) fala de depósito da fé e crescimento na compreensão (Catecismo, 94).

Pense no regulamento de segurança de uma empresa: o princípio "proteger as pessoas" não muda, mas as normas de capacetes, extintores e evacuação mudam com a tecnologia. A analogia não demonstra nenhuma doutrina concreta, mas distingue entre princípio permanente e aplicação mutável.

A objeção mais forte diz: "O desenvolvimento doutrinal justifica qualquer mudança". A resposta honesta é não: cada ensino novo deve guardar continuidade substancial com a fé apostólica. Determinar se um desenvolvimento conserva essa continuidade é precisamente um dos grandes debates entre católicos, ortodoxos e protestantes."""),
        foundation = LocalizedText("""Juan 16,13; Judas 3; Hechos 15,1-29; Catecismo, 66-67 y 94.""", """John 16:13; Jude 3; Acts 15:1-29; Catechism, 66-67 and 94.""", """João 16,13; Judas 3; Atos 15,1-29; Catecismo, 66-67 e 94."""),
        clarification = LocalizedText("""La idea de desarrollo doctrinal no permite justificar cualquier cambio. Una enseñanza nueva debe guardar continuidad sustancial con la fe apostólica.

Determinar si un desarrollo conserva esa continuidad es precisamente uno de los grandes asuntos discutidos entre católicos, ortodoxos y protestantes.""", """The idea of doctrinal development does not allow justifying any change. A new teaching must preserve substantial continuity with apostolic faith.

Determining whether a development preserves that continuity is precisely one of the great matters discussed between Catholics, Orthodox and Protestants.""", """A ideia de desenvolvimento doutrinal não permite justificar qualquer mudança. Um ensino novo deve guardar continuidade substancial com a fé apostólica.

Determinar se um desenvolvimento conserva essa continuidade é precisamente um dos grandes assuntos discutidos entre católicos, ortodoxos e protestantes.""")
    ),
    ApologeticsQuestion(
        number = 21,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Dónde dice la Biblia que Dios es Trinidad? ¿No son tres dioses?""", """Where does the Bible say that God is Trinity? Are they not three gods?""", """Onde diz a Bíblia que Deus é Trindade? Não são três deuses?"""),
        answer = LocalizedText("""La respuesta directa es que la palabra «Trinidad» no aparece en la Biblia, pero la doctrina intenta explicar conjuntamente tres afirmaciones bíblicas: hay un solo Dios (Deuteronomio 6,4); el Padre es Dios; y el Hijo y el Espíritu Santo comparten esa única identidad divina. Jesús ordena bautizar en el nombre —en singular— del Padre, del Hijo y del Espíritu Santo (Mateo 28,19), y Pablo habla de Dios Padre, del Señor Jesucristo y de la comunión del Espíritu (2 Corintios 13,13).

Piensa en una familia formada por padre, madre e hijo: son tres personas, pero no una sola naturaleza humana compartida como en Dios. La analogía sirve solo para distinguir personas de naturaleza: en la Trinidad hay una sola naturaleza divina y tres personas distintas, sin que ninguna sea una tercera parte de Dios ni tres dioses.

La objeción más fuerte dice que la fórmula es tardía, no está en un solo versículo y suena contradictoria: «uno y tres a la vez». La respuesta honesta es que ningún versículo contiene por sí solo toda la formulación posterior, que se desarrolló en los primeros siglos, y que no hay una analogía matemática que la demuestre. La Iglesia la presenta como un misterio coherente con el conjunto bíblico, no como una ecuación resuelta.""", """The direct answer is that the word "Trinity" does not appear in the Bible, but the doctrine seeks to explain together three biblical affirmations: there is one God (Deuteronomy 6:4); the Father is God; and the Son and the Holy Spirit share that single divine identity. Jesus commands baptism in the name —in the singular— of the Father, of the Son and of the Holy Spirit (Matthew 28:19), and Paul speaks of God the Father, of the Lord Jesus Christ and of the fellowship of the Spirit (2 Corinthians 13:13).

Think of a family made up of father, mother and child: they are three persons, but not one single human nature shared as in God. The analogy serves only to distinguish persons from nature: in the Trinity there is one divine nature and three distinct persons, without any one being a third part of God or three gods.

The strongest objection says that the formula is late, is not found in a single verse, and sounds contradictory: "one and three at once." The honest answer is that no verse by itself contains the whole later formulation, which developed in the first centuries, and that there is no mathematical analogy that proves it. The Church presents it as a mystery coherent with the whole of the Bible, not as a solved equation.""", """A resposta direta é que a palavra "Trindade" não aparece na Bíblia, mas a doutrina tenta explicar conjuntamente três afirmações bíblicas: há um só Deus (Deuteronômio 6,4); o Pai é Deus; e o Filho e o Espírito Santo compartilham essa única identidade divina. Jesus ordena batizar em nome —no singular— do Pai, do Filho e do Espírito Santo (Mateus 28,19), e Paulo fala de Deus Pai, do Senhor Jesus Cristo e da comunhão do Espírito (2 Coríntios 13,13).

Pense em uma família formada por pai, mãe e filho: são três pessoas, mas não uma só natureza humana compartilhada como em Deus. A analogia serve apenas para distinguir pessoas de natureza: na Trindade há uma só natureza divina e três pessoas distintas, sem que nenhuma seja uma terceira parte de Deus nem três deuses.

A objeção mais forte diz que a fórmula é tardia, não está em um só versículo e soa contraditória: "um e três ao mesmo tempo". A resposta honesta é que nenhum versículo contém por si só toda a formulação posterior, que se desenvolveu nos primeiros séculos, e que não há uma analogia matemática que a demonstre. A Igreja a apresenta como um mistério coerente com o conjunto bíblico, não como uma equação resolvida."""),
        foundation = LocalizedText("""Deuteronomio 6,4; Mateo 28,19; Juan 1,1-3; Juan 20,28; Hechos 5,3-4; 2 Corintios 13,13; Catecismo, 232-267.""", """Deuteronomy 6:4; Matthew 28:19; John 1:1-3; John 20:28; Acts 5:3-4; 2 Corinthians 13:13; Catechism, 232-267.""", """Deuteronômio 6,4; Mateus 28,19; João 1,1-3; João 20,28; Atos 5,3-4; 2 Coríntios 13,13; Catecismo, 232-267."""),
        clarification = LocalizedText("""Ningún versículo contiene por sí solo toda la formulación posterior del dogma. Esta surge de la interpretación conjunta de las Escrituras y del desarrollo teológico de los primeros siglos.

La Trinidad tampoco puede demostrarse mediante una analogía matemática sencilla. Su coherencia depende de distinguir los conceptos de naturaleza y persona.""", """No verse alone contains the whole later formulation of the dogma. This emerges from joint interpretation of the Scriptures and theological development in the first centuries.

The Trinity also cannot be demonstrated by a simple mathematical analogy. Its coherence depends on distinguishing the concepts of nature and person.""", """Nenhum versículo contém por si só toda a formulação posterior do dogma. Esta surge da interpretação conjunta das Escrituras e do desenvolvimento teológico dos primeiros séculos.

A Trindade tampouco pode ser demonstrada por uma analogia matemática simples. Sua coerência depende de distinguir os conceitos de natureza e pessoa.""")
    ),
    ApologeticsQuestion(
        number = 22,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús es Dios, ¿a quién rezaba y por qué decía que el Padre era mayor?""", """If Jesus is God, to whom did He pray and why did He say the Father was greater?""", """Se Jesus é Deus, a quem rezava e por que dizia que o Pai era maior?"""),
        answer = LocalizedText("""La respuesta directa es que Jesús, según la fe católica, es una sola persona divina con dos naturalezas verdaderas: divina y humana. Por eso puede orar como hombre: vive una relación filial de obediencia y confianza con el Padre, una persona distinta de Él. Cuando dice «el Padre es mayor que yo» (Juan 14,28), la interpretación católica lo lee en el contexto de su misión y de su condición humana, sin negar textos que atribuyen divinidad a Jesús (Juan 1,1.14; Filipenses 2,5-11).

Imagina a una misma persona que es madre en casa y médica en el hospital: en cada lugar actúa según la relación que vive, sin dejar de ser la misma persona. De modo semejante, en Jesús hay dos modos de actuar —divino y humano— sin que sean dos personas distintas ni que Él se hable a Sí mismo.

La objeción más fuerte afirma que «el Padre es mayor» prueba que Jesús es inferior y que su oración lo muestra. La respuesta honesta es que este texto ha sido interpretado de diversas maneras por los cristianos, y que la lectura católica depende de leerlo junto con los pasajes que hablan de su divinidad. No hay una prueba aislada que obligue a una sola interpretación.""", """The direct answer is that Jesus, according to Catholic faith, is a single divine person with two true natures: divine and human. That is why he can pray as man: he lives a filial relationship of obedience and trust with the Father, a person distinct from him. When he says "the Father is greater than I" (John 14:28), the Catholic interpretation reads it in the context of his mission and his human condition, without denying texts that attribute divinity to Jesus (John 1:1,14; Philippians 2:5-11).

Imagine the same person who is a mother at home and a doctor in the hospital: in each place she acts according to the relationship she lives, without ceasing to be the same person. In the same way, in Jesus there are two modes of acting —divine and human— without his being two distinct persons or his speaking to himself.

The strongest objection claims that "the Father is greater" proves that Jesus is inferior and that his prayer shows it. The honest answer is that this text has been interpreted in various ways by Christians, and that the Catholic reading depends on reading it together with the passages that speak of his divinity. There is no isolated proof that compels a single interpretation.""", """A resposta direta é que Jesus, segundo a fé católica, é uma só pessoa divina com duas naturezas verdadeiras: divina e humana. Por isso pode orar como homem: vive uma relação filial de obediência e confiança com o Pai, uma pessoa distinta dele. Quando diz "o Pai é maior do que eu" (João 14,28), a interpretação católica o lê no contexto de sua missão e de sua condição humana, sem negar os textos que atribuem divindade a Jesus (João 1,1.14; Filipenses 2,5-11).

Imagine a mesma pessoa que é mãe em casa e médica no hospital: em cada lugar age conforme a relação que vive, sem deixar de ser a mesma pessoa. Da mesma forma, em Jesus há dois modos de agir —divino e humano— sem que sejam duas pessoas distintas nem que ele fale consigo mesmo.

A objeção mais forte afirma que "o Pai é maior" prova que Jesus é inferior e que sua oração mostra isso. A resposta honesta é que este texto foi interpretado de diversas maneiras pelos cristãos, e que a leitura católica depende de lê-lo junto com os trechos que falam de sua divindade. Não há uma prova isolada que obrigue a uma única interpretação."""),
        foundation = LocalizedText("""Juan 1,1.14; Juan 14,28; Filipenses 2,5-11; Hebreos 5,7-9; Catecismo, 464-469.""", """John 1:1.14; John 14:28; Philippians 2:5-11; Hebrews 5:7-9; Catechism, 464-469.""", """João 1,1.14; João 14,28; Filipenses 2,5-11; Hebreus 5,7-9; Catecismo, 464-469."""),
        clarification = LocalizedText("""Este texto ha sido interpretado de distintas maneras por los cristianos. La explicación trinitaria no nace de ignorarlo, sino de leerlo junto con los pasajes que atribuyen divinidad a Jesucristo.""", """This text has been interpreted in different ways by Christians. The Trinitarian explanation does not arise from ignoring it, but from reading it together with passages attributing divinity to Jesus Christ.""", """Este texto tem sido interpretado de maneiras diferentes pelos cristãos. A explicação trinitária não nasce de ignorá-lo, mas de lê-lo junto com os trechos que atribuem divindade a Jesus Cristo.""")
    ),
    ApologeticsQuestion(
        number = 23,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús es Dios, ¿cómo pudo sufrir y morir?""", """If Jesus is God, how could He suffer and die?""", """Se Jesus é Deus, como pôde sofrer e morrer?"""),
        answer = LocalizedText("""La respuesta directa es que la Encarnación no significa que Dios dejara de ser Dios, sino que el Hijo eterno asumió una naturaleza humana completa. Por eso Jesús experimentó hambre, dolor, cansancio y muerte reales: su alma humana se separó de su cuerpo, mientras su persona divina permaneció unida a ambos. La naturaleza divina no dejó de existir ni perdió su inmortalidad. Por eso se dice que «Dios murió por nosotros», porque quien murió es la persona divina del Hijo según su naturaleza humana (Catecismo, 470-478 y 624-626).

Piensa en un autor que entra en su propia novela como un personaje: sigue siendo el autor, pero dentro del relato vive como personaje, con cuerpo y límites. La analogía tiene límites, pero ayuda a distinguir: la persona es divina; lo que sufre y muere es su humanidad asumida.

La objeción más fuerte dice que esto es contradictorio: si Dios no puede morir, no murió; si murió, dejó de ser Dios. La respuesta honesta es que la unión de dos naturalezas en una persona es un misterio que la Iglesia afirma sin poder explicarlo del todo con categorías ordinarias. No significa que la divinidad haya dejado de existir.""", """The direct answer is that the Incarnation does not mean that God ceased to be God, but that the eternal Son assumed a complete human nature. That is why Jesus experienced real hunger, pain, weariness and death: his human soul was separated from his body, while his divine person remained united to both. The divine nature did not cease to exist nor lose its immortality. That is why it is said that "God died for us," because the one who died is the divine person of the Son according to his human nature (Catechism, 470-478 and 624-626).

Think of an author who enters his own novel as a character: he remains the author, but inside the story he lives as a character, with a body and limits. The analogy has limits, but it helps to distinguish: the person is divine; what suffers and dies is his assumed humanity.

The strongest objection says this is contradictory: if God cannot die, he did not die; if he died, he ceased to be God. The honest answer is that the union of two natures in one person is a mystery that the Church affirms without being able to explain it fully with ordinary categories. It does not mean that divinity ceased to exist.""", """A resposta direta é que a Encarnação não significa que Deus deixou de ser Deus, mas que o Filho eterno assumiu uma natureza humana completa. Por isso Jesus experimentou fome, dor, cansaço e morte reais: sua alma humana se separou de seu corpo, enquanto sua pessoa divina permaneceu unida a ambos. A natureza divina não deixou de existir nem perdeu sua imortalidade. Por isso se diz que "Deus morreu por nós", porque quem morreu é a pessoa divina do Filho segundo sua natureza humana (Catecismo, 470-478 e 624-626).

Pense em um autor que entra em seu próprio romance como personagem: continua sendo o autor, mas dentro da história vive como personagem, com corpo e limites. A analogia tem limites, mas ajuda a distinguir: a pessoa é divina; o que sofre e morre é sua humanidade assumida.

A objeção mais forte diz que isso é contraditório: se Deus não pode morrer, não morreu; se morreu, deixou de ser Deus. A resposta honesta é que a união de duas naturezas em uma pessoa é um mistério que a Igreja afirma sem poder explicá-lo por completo com categorias ordinárias. Não significa que a divindade deixou de existir."""),
        foundation = LocalizedText("""Juan 1,14; Filipenses 2,6-8; Hebreos 2,14-18; 1 Pedro 3,18; Catecismo, 470-478 y 624-626.""", """John 1:14; Philippians 2:6-8; Hebrews 2:14-18; 1 Peter 3:18; Catechism, 470-478 and 624-626.""", """João 1,14; Filipenses 2,6-8; Hebreus 2,14-18; 1 Pedro 3,18; Catecismo, 470-478 e 624-626."""),
        clarification = LocalizedText("""La expresión «Dios murió por nosotros» es correcta en el lenguaje cristológico porque quien murió es la persona divina del Hijo, según su naturaleza humana.

No significa que la divinidad haya dejado de existir.""", """The expression “God died for us” is correct in Christological language because the one who died is the divine person of the Son, according to His human nature.

It does not mean that divinity ceased to exist.""", """A expressão «Deus morreu por nós» é correta na linguagem cristológica porque quem morreu é a pessoa divina do Filho, segundo sua natureza humana.

Não significa que a divindade tenha deixado de existir.""")
    ),
    ApologeticsQuestion(
        number = 24,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús era omnisciente, ¿por qué parecía ignorar algunas cosas?""", """If Jesus was omniscient, why did He seem to ignore some things?""", """Se Jesus era onisciente, por que parecia ignorar algumas coisas?"""),
        answer = LocalizedText("""La respuesta directa es que la fe católica reconoce que Jesús tuvo una inteligencia verdaderamente humana, que se desarrolló en una vida humana real. Lucas afirma que Jesús «crecía en sabiduría» (Lucas 2,52), y el Evangelio también presenta su conocimiento singular del Padre y de su misión (Juan 16,30). La Encarnación no fue una apariencia: Jesucristo asumió auténticamente nuestra condición, también en el conocer humano (Catecismo, 471-474).

Piensa en un niño que de adulto será un gran matemático: en la escuela primaria no sabe cálculo, aunque su capacidad se irá desarrollando. De modo semejante, en Jesús el conocimiento humano se fue desplegando en su vida, sin que eso niegue su identidad divina.

La objeción más fuerte es Marcos 13,32: «nadie conoce el día ni la hora, ni el Hijo». Para muchos, esto contradice la omnisciencia. La respuesta honesta es que cómo se relacionan exactamente el conocimiento humano de Jesús, su conciencia mesiánica y su divinidad es una cuestión teológica compleja. No es riguroso decir que Jesús simplemente fingía ignorancia ni que el texto sea irrelevante.""", """The direct answer is that Catholic faith recognizes that Jesus had a truly human intelligence, which developed within a real human life. Luke states that Jesus "grew in wisdom" (Luke 2:52), and the Gospel also presents his singular knowledge of the Father and of his mission (John 16:30). The Incarnation was not an appearance: Jesus Christ truly assumed our condition, also in human knowing (Catechism, 471-474).

Think of a child who as an adult will be a great mathematician: in primary school he does not know calculus, although his ability will develop. In the same way, in Jesus human knowledge unfolded in his life, without that denying his divine identity.

The strongest objection is Mark 13:32: "no one knows the day or the hour, not even the Son." For many, this contradicts omniscience. The honest answer is that how exactly Jesus' human knowledge, his messianic consciousness and his divinity relate is a complex theological question. It is not rigorous to say that Jesus was simply feigning ignorance or that the text is irrelevant.""", """A resposta direta é que a fé católica reconhece que Jesus teve uma inteligência verdadeiramente humana, que se desenvolveu em uma vida humana real. Lucas afirma que Jesus "crescia em sabedoria" (Lucas 2,52), e o Evangelho também apresenta seu conhecimento singular do Pai e de sua missão (João 16,30). A Encarnação não foi uma aparência: Jesus Cristo assumiu autenticamente nossa condição, também no conhecer humano (Catecismo, 471-474).

Pense em uma criança que, adulta, será um grande matemático: na escola primária não sabe cálculo, embora sua capacidade vá se desenvolvendo. Da mesma forma, em Jesus o conhecimento humano foi se desplegando em sua vida, sem que isso negue sua identidade divina.

A objeção mais forte é Marcos 13,32: "ninguém conhece o dia nem a hora, nem o Filho". Para muitos, isso contradiz a onisciência. A resposta honesta é que como se relacionam exatamente o conhecimento humano de Jesus, sua consciência messiânica e sua divindade é uma questão teológica complexa. Não é rigoroso dizer que Jesus simplesmente fingia ignorância nem que o texto seja irrelevante."""),
        foundation = LocalizedText("""Lucas 2,52; Marcos 13,32; Juan 16,30; Colosenses 2,9; Catecismo, 471-474.""", """Luke 2:52; Mark 13:32; John 16:30; Colossians 2:9; Catechism, 471-474.""", """Lucas 2,52; Marcos 13,32; João 16,30; Colossenses 2,9; Catecismo, 471-474."""),
        clarification = LocalizedText("""Cómo se relacionan exactamente el conocimiento humano de Jesús, su conciencia mesiánica y su divinidad es una cuestión teológica compleja.

No resulta riguroso afirmar que Marcos 13,32 es irrelevante o que Jesús simplemente fingía ignorancia. El texto debe tomarse en serio dentro de la doctrina de sus dos naturalezas.""", """How exactly Jesus' human knowledge, His messianic consciousness and His divinity relate is a complex theological question.

It is not rigorous to affirm that Mark 13:32 is irrelevant or that Jesus simply feigned ignorance. The text must be taken seriously within the doctrine of His two natures.""", """Como se relacionam exatamente o conhecimento humano de Jesus, sua consciência messiânica e sua divindade é uma questão teológica complexa.

Não é rigoroso afirmar que Marcos 13,32 é irrelevante ou que Jesus simplesmente fingia ignorância. O texto deve ser levado a sério dentro da doutrina de suas duas naturezas.""")
    ),
    ApologeticsQuestion(
        number = 25,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué Jesús tuvo que morir si Dios podía perdonarnos directamente?""", """Why did Jesus have to die if God could forgive us directly?""", """Por que Jesus teve que morrer se Deus podia nos perdoar diretamente?"""),
        answer = LocalizedText("""La respuesta directa es que la explicación cristiana no es que el Padre necesitara descargar su ira sobre una víctima inocente para poder amar a la humanidad. La cruz manifiesta la gravedad del mal y la profundidad del amor de Dios: Cristo se entrega voluntariamente para reconciliar a la humanidad con Él. Jesús enseña que «nadie le quita su vida, sino que la entrega libremente» (Juan 10,18), y Pablo habla de Dios reconciliándonos por medio de Cristo (2 Corintios 5,18-21).

Piensa en un amigo que paga la deuda de otro para liberarlo y enseñarle a vivir de otro modo: no porque el acreedor lo exija con crueldad, sino porque el pago abre una relación nueva. La analogía es limitada, pero ayuda a ver la cruz como entrega que reconcilia, no como un castigo necesario para aplacar a Dios.

La objeción más fuerte dice que es injusto castigar a un inocente y que un Dios todopoderoso podría perdonar sin muerte. La respuesta honesta es que hay distintas teorías cristianas sobre cómo la muerte de Cristo realiza la redención —sacrificio, victoria, reconciliación— y que no puede demostrarse filosóficamente que Dios estuviera absolutamente obligado a hacerlo así.""", """The direct answer is that the Christian explanation is not that the Father needed to vent his wrath on an innocent victim in order to love humanity. The cross reveals the gravity of evil and the depth of God's love: Christ gives himself voluntarily to reconcile humanity with him. Jesus teaches that "no one takes my life from me, but I give it freely" (John 10:18), and Paul speaks of God reconciling us through Christ (2 Corinthians 5:18-21).

Think of a friend who pays another's debt to free him and teach him to live differently: not because the creditor cruelly demands it, but because the payment opens a new relationship. The analogy is limited, but it helps to see the cross as a gift that reconciles, not as a punishment necessary to appease God.

The strongest objection says it is unjust to punish an innocent and that an almighty God could forgive without death. The honest answer is that there are various Christian theories about how Christ's death effects redemption —sacrifice, victory, reconciliation— and that it cannot be philosophically demonstrated that God was absolutely bound to do it this way.""", """A resposta direta é que a explicação cristã não é que o Pai precisasse descarregar sua ira sobre uma vítima inocente para poder amar a humanidade. A cruz manifesta a gravidade do mal e a profundidade do amor de Deus: Cristo se entrega voluntariamente para reconciliar a humanidade com ele. Jesus ensina que "ninguém tira a minha vida de mim, mas eu a entrego livremente" (João 10,18), e Paulo fala de Deus nos reconciliando por meio de Cristo (2 Coríntios 5,18-21).

Pense em um amigo que paga a dívida de outro para libertá-lo e ensiná-lo a viver de outro modo: não porque o credor o exija cruelmente, mas porque o pagamento abre uma relação nova. A analogia é limitada, mas ajuda a ver a cruz como entrega que reconcilia, não como um castigo necessário para aplacar a Deus.

A objeção mais forte diz que é injusto punir um inocente e que um Deus onipotente poderia perdoar sem morte. A resposta honesta é que há diversas teorias cristãs sobre como a morte de Cristo realiza a redenção —sacrifício, vitória, reconciliação— e que não se pode demonstrar filosoficamente que Deus estivesse absolutamente obrigado a fazê-lo assim."""),
        foundation = LocalizedText("""Juan 3,16-17; Romanos 5,6-11; 2 Corintios 5,18-21; Hebreos 9,11-15; Catecismo, 599-618.""", """John 3:16-17; Romans 5:6-11; 2 Corinthians 5:18-21; Hebrews 9:11-15; Catechism, 599-618.""", """João 3,16-17; Romanos 5,6-11; 2 Coríntios 5,18-21; Hebreus 9,11-15; Catecismo, 599-618."""),
        clarification = LocalizedText("""Hay distintas teorías cristianas sobre cómo la muerte de Cristo realiza la redención: sacrificio, victoria sobre el mal, satisfacción, reconciliación y transformación del ser humano.

La cruz es central para la fe cristiana, pero no podemos demostrar filosóficamente que Dios estuviera absolutamente obligado a salvarnos de esta única manera.""", """There are different Christian theories about how Christ's death effects redemption: sacrifice, victory over evil, satisfaction, reconciliation and transformation of the human being.

The cross is central to Christian faith, but we cannot philosophically demonstrate that God was absolutely bound to save us only in this way.""", """Há diferentes teorias cristãs sobre como a morte de Cristo realiza a redenção: sacrifício, vitória sobre o mal, satisfação, reconciliação e transformação do ser humano.

A cruz é central para a fé cristã, mas não podemos demonstrar filosoficamente que Deus estivesse absolutamente obrigado a nos salvar somente desta maneira.""")
    ),
    ApologeticsQuestion(
        number = 26,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Qué pruebas históricas existen de la resurrección de Jesús?""", """What historical evidence exists for the resurrection of Jesus?""", """Que provas históricas existem da ressurreição de Jesus?"""),
        answer = LocalizedText("""La respuesta directa es que la investigación histórica puede establecer, con los métodos propios de la historia, que Jesús fue crucificado y que sus primeros seguidores proclamaron muy pronto que había resucitado y que se les había aparecido. Pablo transmite en 1 Corintios 15 una tradición cristiana anterior a su carta, que enumera apariciones a Pedro, a los Doce y a muchos otros. Además, el surgimiento y la expansión del movimiento cristiano requieren una explicación histórica. La interpretación católica sostiene que la resurrección real de Jesús explica adecuadamente estos testimonios (1 Corintios 15,3-8; Hechos 2,22-36).

Imagina investigar un accidente ocurrido hace décadas solo con informes cercanos, cartas y testimonios de testigos: el historiador puede reconstruir qué ocurrió y qué creyó la gente, aunque no pueda repetir el hecho. De modo semejante, la historia estudia los testimonios de la resurrección, pero demostrar una intervención sobrenatural como tal excede sus métodos.

La objeción más fuerte dice que la historia no puede probar milagros y que hay explicaciones alternativas, como el duelo o la leyenda. La respuesta honesta es que esas explicaciones también deben examinarse, no descartarse automáticamente. La resurrección es una afirmación histórica y de fe: los testimonios son antiguos y cercanos, pero aceptarla como intervención divina requiere una valoración que va más allá del mero dato.""", """The direct answer is that historical research can establish, with the methods proper to history, that Jesus was crucified and that his first followers proclaimed very soon that he had risen and that he had appeared to them. Paul transmits in 1 Corinthians 15 a Christian tradition earlier than his letter, which lists appearances to Peter, to the Twelve, and to many others. Moreover, the emergence and expansion of the Christian movement require a historical explanation. The Catholic interpretation holds that the real resurrection of Jesus adequately explains these testimonies (1 Corinthians 15:3-8; Acts 2:22-36).

Imagine investigating an accident that happened decades ago using only nearby reports, letters, and witness testimonies: the historian can reconstruct what happened and what people believed, although he cannot repeat the event. In the same way, history studies the testimonies of the resurrection, but demonstrating a supernatural intervention as such exceeds its methods.

The strongest objection says that history cannot prove miracles and that there are alternative explanations, such as grief or legend. The honest answer is that those explanations must also be examined, not automatically discarded. The resurrection is both a historical and a faith claim: the testimonies are ancient and close, but accepting it as a divine intervention requires an assessment that goes beyond the mere fact.""", """A resposta direta é que a pesquisa histórica pode estabelecer, com os métodos próprios da história, que Jesus foi crucificado e que seus primeiros seguidores proclamaram muito cedo que ele havia ressuscitado e que lhes tinha aparecido. Paulo transmite em 1 Coríntios 15 uma tradição cristã anterior à sua carta, que enumera aparições a Pedro, aos Doze e a muitos outros. Além disso, o surgimento e a expansão do movimento cristão exigem uma explicação histórica. A interpretação católica sustenta que a ressurreição real de Jesus explica adequadamente estes testemunhos (1 Coríntios 15,3-8; Atos 2,22-36).

Imagine investigar um acidente ocorrido décadas atrás apenas com relatórios próximos, cartas e testemunhos de testemunhas: o historiador pode reconstruir o que aconteceu e o que as pessoas creram, embora não possa repetir o fato. Da mesma forma, a história estuda os testemunhos da ressurreição, mas demonstrar uma intervenção sobrenatural como tal excede seus métodos.

A objeção mais forte diz que a história não pode provar milagres e que há explicações alternativas, como o luto ou a lenda. A resposta honesta é que essas explicações também devem ser examinadas, não descartadas automaticamente. A ressurreição é uma afirmação histórica e de fé: os testemunhos são antigos e próximos, mas aceitá-la como intervenção divina exige uma avaliação que vai além do mero fato."""),
        foundation = LocalizedText("""1 Corintios 15,3-8; Hechos 2,22-36; Lucas 24; Juan 20.""", """1 Corinthians 15:3-8; Acts 2:22-36; Luke 24; John 20.""", """1 Coríntios 15,3-8; Atos 2,22-36; Lucas 24; João 20."""),
        clarification = LocalizedText("""La historia puede estudiar testimonios, creencias y acontecimientos accesibles a sus métodos. Demostrar una intervención sobrenatural como tal es más problemático.

Las explicaciones alternativas también deben ser examinadas, no descartadas automáticamente. La resurrección es una afirmación histórica central del cristianismo y, al mismo tiempo, un acontecimiento reconocido mediante la fe.""", """History can study testimonies, beliefs and events accessible to its methods. Demonstrating a supernatural intervention as such is more problematic.

Alternative explanations must also be examined, not automatically dismissed. The resurrection is a central historical affirmation of Christianity and, at the same time, an event recognized through faith.""", """A história pode estudar testemunhos, crenças e acontecimentos acessíveis aos seus métodos. Demonstrar uma intervenção sobrenatural como tal é mais problemático.

As explicações alternativas também devem ser examinadas, não descartadas automaticamente. A ressurreição é uma afirmação histórica central do cristianismo e, ao mesmo tempo, um acontecimento reconhecido mediante a fé.""")
    ),
    ApologeticsQuestion(
        number = 27,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""Si Jesús fundó una sola Iglesia, ¿por qué existen tantas denominaciones?""", """If Jesus founded one Church, why do so many denominations exist?""", """Se Jesus fundou uma única Igreja, por que existem tantas denominações?"""),
        answer = LocalizedText("""La respuesta directa es que Jesús pidió la unidad de sus discípulos (Juan 17,20-23) y los apóstoles denunciaron las divisiones (1 Corintios 1,10-13). La Iglesia católica sostiene que Cristo quiso una comunidad visible, unida en la fe, los sacramentos y la sucesión apostólica, y que la división entre cristianos es una herida histórica, no el plan original. Al mismo tiempo, reconoce elementos auténticos de santificación y verdad en otras comunidades y considera a los cristianos bautizados no católicos como hermanos en Cristo (Catecismo, 811-822).

Piensa en una gran familia reunida para un aniversario: todos comparten el mismo apellido y la misma historia, pero discuten con fuerza por una herencia. Que haya discusiones no significa que existan varias familias distintas, aunque tampoco prueba por sí solo quién tiene razón en cada detalle. La analogía ayuda a distinguir unidad de origen y división posterior.

La objeción más fuerte dice que si Jesús fundó una sola Iglesia, la existencia de tantas denominaciones demuestra que nadie puede reclamar con certeza esa continuidad. La respuesta honesta es que la división tiene causas históricas, humanas y doctrinales, y que discutir la Iglesia verdadera requiere investigar la continuidad apostólica, la doctrina y la historia, no resolverlo con un solo versículo.""", """The direct answer is that Jesus asked for the unity of his disciples (John 17:20-23) and the apostles denounced divisions (1 Corinthians 1:10-13). The Catholic Church holds that Christ willed a visible community, united in faith, the sacraments and apostolic succession, and that division among Christians is a historical wound, not the original plan. At the same time, it recognizes authentic elements of sanctification and truth in other communities and considers baptized non-Catholic Christians as brothers and sisters in Christ (Catechism, 811-822).

Think of a large family gathered for an anniversary: they all share the same surname and the same history, but they argue fiercely over an inheritance. That there are arguments does not mean there are several distinct families, although it also does not by itself prove who is right on each detail. The analogy helps to distinguish unity of origin from later division.

The strongest objection says that if Jesus founded one Church, the existence of so many denominations proves that no one can claim that continuity with certainty. The honest answer is that division has historical, human, and doctrinal causes, and that discussing the true Church requires investigating apostolic continuity, doctrine, and history, not settling it with a single verse.""", """A resposta direta é que Jesus pediu a unidade de seus discípulos (João 17,20-23) e os apóstolos denunciaram as divisões (1 Coríntios 1,10-13). A Igreja católica sustenta que Cristo quis uma comunidade visível, unida na fé, nos sacramentos e na sucessão apostólica, e que a divisão entre cristãos é uma ferida histórica, não o plano original. Ao mesmo tempo, reconhece elementos autênticos de santificação e verdade em outras comunidades e considera os cristãos batizados não católicos como irmãos em Cristo (Catecismo, 811-822).

Pense em uma grande família reunida para um aniversário: todos compartilham o mesmo sobrenome e a mesma história, mas discutem com força por uma herança. Que haja discussões não significa que existam várias famílias distintas, embora também não prove por si só quem tem razão em cada detalhe. A analogia ajuda a distinguir unidade de origem e divisão posterior.

A objeção mais forte diz que, se Jesus fundou uma só Igreja, a existência de tantas denominações demonstra que ninguém pode reivindicar com certeza essa continuidade. A resposta honesta é que a divisão tem causas históricas, humanas e doutrinais, e que discutir a Igreja verdadeira exige investigar a continuidade apostólica, a doutrina e a história, não resolvê-lo com um só versículo."""),
        foundation = LocalizedText("""Juan 17,20-23; Mateo 16,18; Efesios 4,4-6; 1 Corintios 1,10-13; Catecismo, 811-822.""", """John 17:20-23; Matthew 16:18; Ephesians 4:4-6; 1 Corinthians 1:10-13; Catechism, 811-822.""", """João 17,20-23; Mateus 16,18; Efésios 4,4-6; 1 Coríntios 1,10-13; Catecismo, 811-822."""),
        clarification = LocalizedText("""La Iglesia católica reconoce elementos auténticos de santificación y verdad en otras comunidades cristianas, y considera a los cristianos bautizados no católicos como hermanos en Cristo.

La discusión sobre la Iglesia verdadera requiere investigar la continuidad apostólica, la doctrina, la historia y la autoridad, no limitarse a contar denominaciones.""", """The Catholic Church recognizes authentic elements of sanctification and truth in other Christian communities, and considers non-Catholic baptized Christians as brothers and sisters in Christ.

The discussion about the true Church requires investigating apostolic continuity, doctrine, history and authority, not merely counting denominations.""", """A Igreja católica reconhece elementos autênticos de santificação e verdade em outras comunidades cristãs e considera os cristãos batizados não católicos como irmãos em Cristo.

A discussão sobre a Igreja verdadeira requer investigar a continuidade apostólica, a doutrina, a história e a autoridade, não limitar-se a contar denominações.""")
    ),
    ApologeticsQuestion(
        number = 28,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué el Espíritu Santo es una persona y no simplemente una fuerza?""", """Why is the Holy Spirit a person and not simply a force?""", """Por que o Espírito Santo é uma pessoa e não simplesmente uma força?"""),
        answer = LocalizedText("""La respuesta directa es que el Nuevo Testamento le atribuye al Espíritu Santo acciones personales: enseña, guía, habla, distribuye dones y puede ser entristecido (Juan 14,17.26; Juan 16,13; Efesios 4,30). Jesús lo presenta como «otro Paráclito», enviado para acompañar a sus discípulos, y en Hechos 5,3-4 mentirle al Espíritu aparece relacionado con mentir a Dios. El razonamiento católico es que estos textos, considerados conjuntamente, presentan al Espíritu como más que una energía impersonal (Catecismo, 683-747).

Piensa en el viento que mueve las hojas de un árbol: tiene fuerza, pero no habla, no consuela ni puede entristecerse. Si alguien te viene a hablar, te guía y te acompaña en un momento difícil, ya no lo describes como «una fuerza», sino como una persona. La analogía ayuda a ver por qué los verbos bíblicos importan.

La objeción más fuerte dice que esas expresiones son metáforas poéticas y que no prueban una persona. La respuesta honesta es que algunas expresiones, aisladas, podrían interpretarse figuradamente. La afirmación doctrinal no nace de un solo verbo, sino de la lectura conjunta de las Escrituras, y por eso sostiene que el Espíritu es una persona divina, no una simple fuerza.""", """The direct answer is that the New Testament attributes personal actions to the Holy Spirit: he teaches, guides, speaks, distributes gifts, and can be grieved (John 14:17,26; John 16:13; Ephesians 4:30). Jesus presents him as "another Advocate," sent to accompany his disciples, and in Acts 5:3-4 lying to the Holy Spirit is related to lying to God. The Catholic reasoning is that these texts, considered together, present the Spirit as more than an impersonal energy (Catechism, 683-747).

Think of the wind moving the leaves of a tree: it has force, but it does not speak, it does not console, and it cannot be grieved. If someone comes to speak to you, guide you, and accompany you in a difficult moment, you no longer describe him as "a force" but as a person. The analogy helps to see why the biblical verbs matter.

The strongest objection says those expressions are poetic metaphors and do not prove a person. The honest answer is that some expressions, in isolation, could be interpreted figuratively. The doctrinal affirmation does not come from a single verb, but from reading the Scriptures together, and therefore holds that the Spirit is a divine person, not a mere force.""", """A resposta direta é que o Novo Testamento atribui ao Espírito Santo ações pessoais: ensina, guia, fala, distribui dons e pode ser entristecido (João 14,17.26; João 16,13; Efésios 4,30). Jesus o apresenta como "outro Paráclito", enviado para acompanhar seus discípulos, e em Atos 5,3-4 mentir ao Espírito Santo aparece relacionado a mentir a Deus. O raciocínio católico é que estes textos, considerados conjuntamente, apresentam o Espírito como mais do que uma energia impessoal (Catecismo, 683-747).

Pense no vento que move as folhas de uma árvore: tem força, mas não fala, não consola nem pode entristecer-se. Se alguém vem falar com você, guiá-lo e acompanhá-lo em um momento difícil, você já não o descreve como "uma força", mas como uma pessoa. A analogia ajuda a ver por que os verbos bíblicos importam.

A objeção mais forte diz que essas expressões são metáforas poéticas e não provam uma pessoa. A resposta honesta é que algumas expressões, isoladas, poderiam ser interpretadas figurativamente. A afirmação doutrinal não nasce de um só verbo, mas da leitura conjunta das Escrituras, e por isso sustenta que o Espírito é uma pessoa divina, não uma simples força."""),
        foundation = LocalizedText("""Juan 14,16-17.26; Juan 16,13; Hechos 13,2; 1 Corintios 12,11; Efesios 4,30; Catecismo, 683-747.""", """John 14:16-17.26; John 16:13; Acts 13:2; 1 Corinthians 12:11; Ephesians 4:30; Catechism, 683-747.""", """João 14,16-17.26; João 16,13; Atos 13,2; 1 Coríntios 12,11; Efésios 4,30; Catecismo, 683-747."""),
        clarification = LocalizedText("""Algunas expresiones podrían interpretarse figuradamente si estuvieran aisladas. La afirmación doctrinal procede de la lectura conjunta de las Escrituras, no de un solo verbo.""", """Some expressions could be interpreted figuratively if isolated. The doctrinal affirmation comes from joint reading of the Scriptures, not from a single verb.""", """Algumas expressões poderiam interpretar-se figuradamente se estivessem isoladas. A afirmação doutrinal procede da leitura conjunta das Escrituras, não de um único verbo.""")
    ),
    ApologeticsQuestion(
        number = 29,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Judas estaba destinado a traicionar a Jesús?""", """Was Judas destined to betray Jesus?""", """Judas estava destinado a trair Jesus?"""),
        answer = LocalizedText("""La respuesta directa es que el conocimiento previo no implica causar ni obligar. Que Jesús supiera la traición no significa que la causara o que obligara a Judas a realizarla. Los Evangelios presentan la acción de Judas como moralmente responsable: aceptó dinero, entró en la conspiración y luego se arrepintió de su acto (Mateo 26,14-25; Juan 13,21-30). La tradición cristiana distingue entre el conocimiento providente de Dios y la libertad de las decisiones humanas.

Piensa en un profesor que, por conocer a su alumno, sabe que copiará en el examen si tiene ocasión y le advierte: su conocimiento no le obliga a copiar. Podría haberle tendido una trampa o impedírselo, pero no lo hace. De modo semejante, saber de antemano no equivale a forzar la voluntad.

La objeción más fuerte recuerda que los textos relacionan la traición con el cumplimiento de las Escrituras y que Jesús eligió a Judas sabiendo lo que haría. La respuesta honesta es que cómo se armonizan profecía, providencia y libertad es una cuestión filosófica compleja y misteriosa. Además, la Iglesia no ha definido que Judas esté en el infierno, y su destino eterno no debe afirmarse con certeza.""", """The direct answer is that prior knowledge does not imply causing or compelling. That Jesus knew of the betrayal does not mean that he caused it or forced Judas to do it. The Gospels present Judas's action as morally responsible: he accepted money, entered into the conspiracy, and then repented of his act (Matthew 26:14-25; John 13:21-30). Christian tradition distinguishes between God's providential knowledge and the freedom of human decisions.

Think of a teacher who, knowing his student, knows that he will cheat on the exam if he has the chance, and warns him: his knowledge does not compel him to cheat. He could have set a trap for him or prevented it, but he does not. In the same way, knowing beforehand is not the same as forcing the will.

The strongest objection recalls that the texts relate the betrayal to the fulfillment of the Scriptures and that Jesus chose Judas knowing what he would do. The honest answer is that how prophecy, providence, and freedom are harmonized is a complex and mysterious philosophical question. Moreover, the Church has not defined that Judas is in hell, and his eternal destiny must not be affirmed with certainty.""", """A resposta direta é que o conhecimento prévio não implica causar nem obrigar. Que Jesus soubesse da traição não significa que a tenha causado ou que tenha obrigado Judas a cometê-la. Os Evangelhos apresentam a ação de Judas como moralmente responsável: aceitou dinheiro, entrou na conspiração e depois se arrependeu de seu ato (Mateus 26,14-25; João 13,21-30). A tradição cristã distingue entre o conhecimento providente de Deus e a liberdade das decisões humanas.

Pense em um professor que, por conhecer seu aluno, sabe que ele copiará na prova se tiver ocasião e o adverte: seu conhecimento não o obriga a copiar. Poderia ter-lhe tendido uma armadilha ou impedi-lo, mas não o faz. Da mesma forma, saber de antemão não equivale a forçar a vontade.

A objeção mais forte lembra que os textos relacionam a traição com o cumprimento das Escrituras e que Jesus escolheu Judas sabendo o que ele faria. A resposta honesta é que como se harmonizam profecia, providência e liberdade é uma questão filosófica complexa e misteriosa. Além disso, a Igreja não definiu que Judas esteja no inferno, e seu destino eterno não deve ser afirmado com certeza."""),
        foundation = LocalizedText("""Mateo 26,14-25; Lucas 22,3-6; Juan 13,21-30; Hechos 1,16-20.""", """Matthew 26:14-25; Luke 22:3-6; John 13:21-30; Acts 1:16-20.""", """Mateus 26,14-25; Lucas 22,3-6; João 13,21-30; Atos 1,16-20."""),
        clarification = LocalizedText("""Los textos también relacionan la traición con el cumplimiento de las Escrituras. Cómo se armonizan exactamente profecía, providencia y libertad constituye una cuestión filosófica compleja.

Además, la Iglesia no ha definido que Judas se encuentre en el infierno. Su destino eterno no debe afirmarse como si lo conociéramos.""", """The texts also relate the betrayal to the fulfillment of the Scriptures. How exactly prophecy, providence and freedom are harmonized is a complex philosophical question.

Moreover, the Church has not defined that Judas is in hell. His eternal destiny must not be stated as if we knew it.""", """Os textos também relacionam a traição com o cumprimento das Escrituras. Como se harmonizam exatamente profecia, providência e liberdade constitui uma questão filosófica complexa.

Além disso, a Igreja não definiu que Judas se encontre no inferno. Seu destino eterno não deve ser afirmado como se o conhecêssemos.""")
    ),
    ApologeticsQuestion(
        number = 30,
        section = LocalizedText("""Jesucristo, la Trinidad y el Espíritu Santo""", """Jesus Christ, the Trinity and the Holy Spirit""", """Jesus Cristo, a Trindade e o Espírito Santo"""),
        question = LocalizedText("""¿Por qué Jesús no ha regresado después de dos mil años?""", """Why has Jesus not returned after two thousand years?""", """Por que Jesus não voltou depois de dois mil anos?"""),
        answer = LocalizedText("""La respuesta directa es que el Nuevo Testamento contiene una fuerte expectativa del retorno de Cristo, pero también advierte que no corresponde a los discípulos conocer los tiempos: «no os toca a vosotros conocer los tiempos» (Hechos 1,6-11; Mateo 24,36). La esperanza cristiana no depende de calcular una fecha, sino de vivir la historia como tiempo de misión, conversión y espera de la consumación definitiva (Catecismo, 668-682). Pedro recuerda que, para Dios, mil años son como un día, y que Él es paciente (2 Pedro 3,8-9).

Piensa en una familia que espera a un hijo que viaja por trabajo: sabe que volverá, pero no el día ni la hora, así que mantiene la casa preparada y sigue con su vida. La espera no es pasividad: es vigilancia activa sin calendario. La analogía ayuda a distinguir esperanza de cálculo.

La objeción más fuerte dice que varios textos dan la impresión de que el fin estaba muy próximo —«esta generación no pasará»— y que dos mil años de espera debilitan la credibilidad. La respuesta honesta es que algunas expectativas de las primeras comunidades reflejan esa cercanía, y la fe católica distingue entre esa expectativa histórica y la afirmación doctrinal de que Cristo volverá. No existe una fecha revelada.""", """The direct answer is that the New Testament contains a strong expectation of Christ's return, but also warns that it does not belong to the disciples to know the times: "it is not for you to know the times" (Acts 1:6-11; Matthew 24:36). Christian hope does not depend on calculating a date, but on living history as a time of mission, conversion, and waiting for the definitive consummation (Catechism, 668-682). Peter recalls that, for God, a thousand years are like a day, and that he is patient (2 Peter 3:8-9).

Think of a family waiting for a child who travels for work: they know he will return, but not the day or the hour, so they keep the house ready and continue with their lives. Waiting is not passivity: it is active watchfulness without a calendar. The analogy helps to distinguish hope from calculation.

The strongest objection says that several texts give the impression that the end was very near —"this generation will not pass away"— and that two thousand years of waiting weaken credibility. The honest answer is that some expectations of the early communities reflect that nearness, and Catholic faith distinguishes between that historical expectation and the doctrinal affirmation that Christ will return. There is no revealed date.""", """A resposta direta é que o Novo Testamento contém uma forte expectativa do retorno de Cristo, mas também adverte que não cabe aos discípulos conhecer os tempos: "não vos toca conhecer os tempos" (Atos 1,6-11; Mateus 24,36). A esperança cristã não depende de calcular uma data, mas de viver a história como tempo de missão, conversão e espera da consumação definitiva (Catecismo, 668-682). Pedro lembra que, para Deus, mil anos são como um dia, e que ele é paciente (2 Pedro 3,8-9).

Pense em uma família que espera um filho que viaja por trabalho: sabe que ele voltará, mas não o dia nem a hora, então mantém a casa preparada e continua com sua vida. A espera não é passividade: é vigilância ativa sem calendário. A analogia ajuda a distinguir esperança de cálculo.

A objeção mais forte diz que vários textos dão a impressão de que o fim estava muito próximo —"esta geração não passará"— e que dois mil anos de espera enfraquecem a credibilidade. A resposta honesta é que algumas expectativas das primeiras comunidades refletem essa proximidade, e a fé católica distingue entre essa expectativa histórica e a afirmação doutrinal de que Cristo voltará. Não existe uma data revelada."""),
        foundation = LocalizedText("""Mateo 24,36; Hechos 1,6-11; 2 Pedro 3,8-9; Catecismo, 668-682.""", """Matthew 24:36; Acts 1:6-11; 2 Peter 3:8-9; Catechism, 668-682.""", """Mateus 24,36; Atos 1,6-11; 2 Pedro 3,8-9; Catecismo, 668-682."""),
        clarification = LocalizedText("""Es legítimo investigar las expectativas escatológicas de las primeras comunidades cristianas. Algunos textos reflejan la impresión de que el cumplimiento estaba próximo.

La respuesta católica distingue entre esa expectativa histórica y la afirmación doctrinal de que Cristo volverá. No existe una demostración histórica de que el acontecimiento futuro ocurrirá.""", """It is legitimate to investigate the eschatological expectations of the first Christian communities. Some texts reflect the impression that fulfillment was near.

The Catholic answer distinguishes between that historical expectation and the doctrinal affirmation that Christ will return. There is no historical demonstration that the future event will occur.""", """É legítimo investigar as expectativas escatológicas das primeiras comunidades cristãs. Alguns textos refletem a impressão de que o cumprimento estava próximo.

A resposta católica distingue entre essa expectativa histórica e a afirmação doutrinal de que Cristo voltará. Não existe uma demonstração histórica de que o acontecimento futuro ocorrerá.""")
    ),
    ApologeticsQuestion(
        number = 31,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué rezar a María y a los santos si podemos dirigirnos directamente a Dios?""", """Why pray to Mary and the saints if we can address God directly?""", """Por que rezar a Maria e aos santos se podemos nos dirigir diretamente a Deus?"""),
        answer = LocalizedText("""La respuesta directa es que los católicos pueden y deben dirigirse directamente a Dios, y que pedir la intercesión de un santo no significa necesitar su autorización para acercarse a Cristo. Significa solicitar su oración, de manera semejante a cuando pedimos a otro cristiano que rece por nosotros. San Pablo pide a otros cristianos que oren por él (Romanos 15,30-32; Efesios 6,18-19), a pesar de reconocer a Cristo como único Mediador (1 Timoteo 2,5-6). La interpretación católica considera que la comunión de los creyentes en Cristo no termina con la muerte (Catecismo, 954-959).

Piensa en alguien enfermo en el hospital que pide a su familia que lo acompañe y rece por él: no necesita permiso de su familia para hablar con el médico, pero valora su compañía. De modo semejante, pedir a un santo que interceda no sustituye la oración directa a Dios, sino que la acompaña.

La objeción más fuerte dice que Cristo es el único mediador y que ningún texto bíblico ordena dirigirse a los santos fallecidos por su nombre. La respuesta honesta es que la práctica no se fundamenta en una orden textual explícita, sino en una interpretación de la comunión de los santos y en la Tradición. Por eso debe presentarse como doctrina católica con fundamentos, no como un mandato bíblico literal.""", """The direct answer is that Catholics can and must address God directly, and that asking a saint for intercession does not mean needing their authorization to approach Christ. It means requesting their prayer, in a similar way to when we ask another Christian to pray for us. Saint Paul asks other Christians to pray for him (Romans 15:30-32; Ephesians 6:18-19), while recognizing Christ as the one Mediator (1 Timothy 2:5-6). The Catholic interpretation holds that the communion of believers in Christ does not end with death (Catechism, 954-959).

Think of someone ill in the hospital who asks family to accompany him and pray for him: he does not need his family's permission to speak to the doctor, but he values their company. In the same way, asking a saint to intercede does not replace direct prayer to God, but accompanies it.

The strongest objection says that Christ is the only mediator and that no biblical text commands addressing deceased saints by name. The honest answer is that the practice is not based on an explicit textual command, but on an interpretation of the communion of saints and on Tradition. Therefore it should be presented as a Catholic doctrine with foundations, not as a literal biblical mandate.""", """A resposta direta é que os católicos podem e devem se dirigir diretamente a Deus, e que pedir a intercessão de um santo não significa precisar de sua autorização para se aproximar de Cristo. Significa solicitar sua oração, de maneira semelhante a quando pedimos a outro cristão que ore por nós. São Paulo pede a outros cristãos que orem por ele (Romanos 15,30-32; Efésios 6,18-19), apesar de reconhecer Cristo como único Mediador (1 Timóteo 2,5-6). A interpretação católica considera que a comunhão dos crentes em Cristo não termina com a morte (Catecismo, 954-959).

Pense em alguém enfermo no hospital que pede à família que o acompanhe e ore por ele: não precisa da permissão da família para falar com o médico, mas valoriza sua companhia. Da mesma forma, pedir a um santo que interceda não substitui a oração direta a Deus, mas a acompanha.

A objeção mais forte diz que Cristo é o único mediador e que nenhum texto bíblico ordena dirigir-se aos santos falecidos pelo nome. A resposta honesta é que a prática não se fundamenta em um mandamento textual explícito, mas em uma interpretação da comunhão dos santos e na Tradição. Por isso deve ser apresentada como doutrina católica com fundamentos, não como um mandato bíblico literal."""),
        foundation = LocalizedText("""1 Timoteo 2,1-5; Santiago 5,16; Apocalipsis 5,8; Hebreos 12,1; Catecismo, 954-959.""", """1 Timothy 2:1-5; James 5:16; Revelation 5:8; Hebrews 12:1; Catechism, 954-959.""", """1 Timóteo 2,1-5; Tiago 5,16; Apocalipse 5,8; Hebreus 12,1; Catecismo, 954-959."""),
        clarification = LocalizedText("""Ningún texto bíblico ordena explícitamente dirigirse a los santos fallecidos por su nombre. La práctica se fundamenta en una interpretación de la comunión de los santos y en la Tradición.

Por eso debemos presentarla como una doctrina católica con fundamentos, no como una orden bíblica textual indiscutible.""", """No biblical text explicitly commands addressing deceased saints by name. The practice is founded on an interpretation of the communion of saints and on Tradition.

Therefore we must present it as a Catholic doctrine with foundations, not as an indisputable textual biblical command.""", """Nenhum texto bíblico ordena explicitamente dirigir-se aos santos falecidos pelo nome. A prática se fundamenta em uma interpretação da comunhão dos santos e na Tradição.

Por isso devemos apresentá-la como uma doutrina católica com fundamentos, não como uma ordem bíblica textual indiscutível.""")
    ),
    ApologeticsQuestion(
        number = 32,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿No es idolatría rezar delante de una estatua?""", """Is praying before a statue idolatry?""", """Não é idolatria rezar diante de uma estátua?"""),
        answer = LocalizedText("""La respuesta directa es que la idolatría consiste en tratar como Dios a algo que no es Dios. La Biblia condena expresamente los ídolos (Éxodo 20,3-5), pero también describe imágenes religiosas realizadas por mandato divino, como los querubines del Arca de la Alianza (Éxodo 25,18-22) y la serpiente de bronce (Números 21,8-9). Por tanto, la prohibición bíblica no puede interpretarse como rechazo absoluto de toda representación religiosa. La cuestión decisiva es qué culto se le ofrece. La teología católica distingue entre adoración, reservada exclusivamente a Dios, y veneración de los santos (Catecismo, 2112-2114 y 2131-2132).

Piensa en la foto de tu madre que llevas en la billetera: puedes besarla y guardarla con cariño sin creer que la foto sea tu madre. Sería idolatría si la trataras como una diosa capaz de ayudarte por sí misma. De modo semejante, una estatua católica es un recordatorio; el culto absoluto se reserva a Dios.

La objeción más fuerte dice que, en la práctica, muchos creyentes atribuyen poderes a las imágenes y que la distinción entre adoración y veneración se derrumba. La respuesta honesta es que una persona puede caer en idolatría si atribuye a una imagen poder divino autónomo, y que tener una estatua católica no hace automáticamente correcto cualquier comportamiento ante ella. La doctrina condena ese abuso.""", """The direct answer is that idolatry consists in treating as God something that is not God. The Bible expressly condemns idols (Exodus 20:3-5), but it also describes religious images made by divine command, such as the cherubim on the Ark of the Covenant (Exodus 25:18-22) and the bronze serpent (Numbers 21:8-9). Therefore the biblical prohibition cannot be interpreted as an absolute rejection of all religious representation. The decisive question is what worship is offered to it. Catholic theology distinguishes between adoration, reserved exclusively to God, and veneration of the saints (Catechism, 2112-2114 and 2131-2132).

Think of the photo of your mother that you carry in your wallet: you can kiss it and keep it with affection without believing that the photo is your mother. It would be idolatry if you treated it as a goddess able to help you by herself. In the same way, a Catholic statue is a reminder; absolute worship is reserved to God.

The strongest objection says that, in practice, many believers attribute powers to images and that the distinction between adoration and veneration collapses. The honest answer is that a person can fall into idolatry if he attributes autonomous divine power to an image, and that having a Catholic statue does not automatically make any behavior before it correct. The doctrine condemns that abuse.""", """A resposta direta é que a idolatria consiste em tratar como Deus algo que não é Deus. A Bíblia condena expressamente os ídolos (Êxodo 20,3-5), mas também descreve imagens religiosas feitas por mandamento divino, como os querubins da Arca da Aliança (Êxodo 25,18-22) e a serpente de bronze (Números 21,8-9). Portanto, a proibição bíblica não pode ser interpretada como rejeição absoluta de toda representação religiosa. A questão decisiva é que culto é oferecido a ela. A teologia católica distingue entre adoração, reservada exclusivamente a Deus, e veneração dos santos (Catecismo, 2112-2114 e 2131-2132).

Pense na foto da sua mãe que você leva na carteira: pode beijá-la e guardá-la com carinho sem acreditar que a foto seja sua mãe. Seria idolatria se você a tratasse como uma deusa capaz de ajudá-lo por si mesma. Da mesma forma, uma estátua católica é um lembrete; o culto absoluto é reservado a Deus.

A objeção mais forte diz que, na prática, muitos crentes atribuem poderes às imagens e que a distinção entre adoração e veneração desmorona. A resposta honesta é que uma pessoa pode cair em idolatria se atribuir a uma imagem poder divino autônomo, e que ter uma estátua católica não torna automaticamente correto qualquer comportamento diante dela. A doutrina condena esse abuso."""),
        foundation = LocalizedText("""Éxodo 20,3-5; Éxodo 25,18-22; Números 21,8-9; 1 Reyes 6,23-29; Catecismo, 2112-2114 y 2131-2132.""", """Exodus 20:3-5; Exodus 25:18-22; Numbers 21:8-9; 1 Kings 6:23-29; Catechism, 2112-2114 and 2131-2132.""", """Êxodo 20,3-5; Êxodo 25,18-22; Números 21,8-9; 1 Reis 6,23-29; Catecismo, 2112-2114 e 2131-2132."""),
        clarification = LocalizedText("""Una persona sí puede caer en idolatría si atribuye a una imagen poder divino autónomo o la trata como un dios.

No basta tener una estatua católica para que cualquier comportamiento ante ella sea automáticamente correcto.""", """A person can indeed fall into idolatry if they attribute autonomous divine power to an image or treat it as a god.

It is not enough to have a Catholic statue for any behavior before it to be automatically correct.""", """Uma pessoa pode, sim, cair em idolatria se atribuir a uma imagem poder divino autônomo ou tratá-la como um deus.

Não basta ter uma estátua católica para que qualquer comportamento diante dela seja automaticamente correto.""")
    ),
    ApologeticsQuestion(
        number = 33,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué existen tantas vírgenes?""", """Why are there so many Virgin Marys?""", """Por que existem tantas virgens?"""),
        answer = LocalizedText("""La respuesta directa es que no existen múltiples vírgenes María, sino una sola: la madre de Jesús. Nuestra Señora de Guadalupe, de Fátima, de Lourdes, del Carmen o de Aparecida son advocaciones de la misma María. Las advocaciones reciben nombres relacionados con lugares, acontecimientos, títulos espirituales o tradiciones devocionales, y expresan aspectos concretos de su presencia en la historia de la fe (Catecismo, 963-975).

Piensa en una misma persona llamada de formas distintas según el contexto: para sus alumnos es «la profesora Ana», para sus vecinos «doña Ana» y para su familia «mamá». No son tres mujeres, sino una sola conocida con títulos diferentes. De modo semejante, las advocaciones marianas son títulos de la única María según el lugar y el acontecimiento.

La objeción más fuerte dice que la proliferación de vírgenes parece indicar varias diosas o una confusión pagana. La respuesta honesta es que la teología católica insiste en que es la misma María, pero que no todas las advocaciones implican apariciones sobrenaturales reconocidas, y que una devoción culturalmente importante no convierte automáticamente todos sus relatos en hechos históricos demostrados.""", """The direct answer is that there are not multiple Marys, but only one: the mother of Jesus. Our Lady of Guadalupe, of Fatima, of Lourdes, of Mount Carmel, or of Aparecida are titles of the same Mary. These titles receive names related to places, events, spiritual titles, or devotional traditions, and express concrete aspects of her presence in the history of faith (Catechism, 963-975).

Think of the same person called by different names depending on the context: for her students she is "Miss Ana," for her neighbors "Mrs. Ana," and for her family "Mom." They are not three women, but one person known by different titles. In the same way, Marian titles are titles of the one Mary according to place and event.

The strongest objection says that the proliferation of Virgins seems to indicate several goddesses or a pagan confusion. The honest answer is that Catholic theology insists it is the same Mary, but that not all titles imply recognized supernatural apparitions, and that a culturally important devotion does not automatically turn all its accounts into proven historical facts.""", """A resposta direta é que não existem múltiplas virgens Maria, mas sim uma só: a mãe de Jesus. Nossa Senhora de Guadalupe, de Fátima, de Lourdes, do Carmo ou de Aparecida são invocações da mesma Maria. As invocações recebem nomes relacionados a lugares, acontecimentos, títulos espirituais ou tradições devocionais, e expressam aspectos concretos de sua presença na história da fé (Catecismo, 963-975).

Pense em uma mesma pessoa chamada de formas distintas conforme o contexto: para seus alunos é "a professora Ana", para seus vizinhos "dona Ana" e para sua família "mamãe". Não são três mulheres, mas uma só conhecida por títulos diferentes. Da mesma forma, as invocações marianas são títulos da única Maria conforme o lugar e o acontecimento.

A objeção mais forte diz que a proliferação de virgens parece indicar várias deusas ou uma confusão pagã. A resposta honesta é que a teologia católica insiste que é a mesma Maria, mas que nem todas as invocações implicam aparições sobrenaturais reconhecidas, e que uma devoção culturalmente importante não converte automaticamente todos os seus relatos em fatos históricos demonstrados."""),
        foundation = LocalizedText("""Lucas 1,26-56; Juan 19,25-27; Catecismo, 963-975.""", """Luke 1:26-56; John 19:25-27; Catechism, 963-975.""", """Lucas 1,26-56; João 19,25-27; Catecismo, 963-975."""),
        clarification = LocalizedText("""No todas las advocaciones implican apariciones sobrenaturales reconocidas.

El hecho de que una devoción esté permitida o sea culturalmente importante no convierte automáticamente todos los relatos asociados a ella en hechos históricos demostrados.""", """Not all titles imply recognized supernatural apparitions.

The fact that a devotion is permitted or culturally important does not automatically turn every account associated with it into demonstrated historical fact.""", """Nem todas as invocações implicam aparições sobrenaturais reconhecidas.

O fato de uma devoção ser permitida ou culturalmente importante não converte automaticamente todos os relatos associados a ela em fatos históricos demonstrados.""")
    ),
    ApologeticsQuestion(
        number = 34,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿María es divina? ¿Por qué se la llama Madre de Dios o Reina del Cielo?""", """Is Mary divine? Why is she called Mother of God or Queen of Heaven?""", """Maria é divina? Por que se a chama Mãe de Deus ou Rainha do Céu?"""),
        answer = LocalizedText("""La respuesta directa es que María no es Dios: es una criatura humana. Se la llama Madre de Dios porque el hijo que concibió y dio a luz es Jesucristo, una persona divina verdaderamente encarnada: «¿de dónde a mí que la madre de mi Señor venga a mí?» (Lucas 1,43). El título no significa que María haya creado la divinidad ni que existiera antes de Dios, sino que su hijo es Dios. El título de Reina se interpreta en relación con el reinado mesiánico de Cristo y con las imágenes bíblicas de la madre del rey (Apocalipsis 12; Catecismo, 495 y 963-975).

Piensa en la madre de un rey: es llamada reina madre por ser la madre del rey, no porque haya conquistado el trono por sí misma ni porque sea la fuente del poder real. Su título depende de su hijo. De modo semejante, María es Madre de Dios porque su hijo es Dios, sin dejar de ser criatura.

La objeción más fuerte dice que «Madre de Dios» suena a que María creó a Dios y que «Reina del Cielo» recuerda a diosas paganas. La respuesta honesta es que los títulos deben interpretarse en su sentido teológico, y que no todos tienen la misma fuerza textual: Lucas 1,43 es especialmente importante para Madre de Dios, mientras que la interpretación de Apocalipsis 12 es más discutida. La adoración de María sería contraria a la doctrina católica.""", """The direct answer is that Mary is not God: she is a human creature. She is called Mother of God because the child she conceived and bore is Jesus Christ, a truly incarnate divine person: "why is this granted to me, that the mother of my Lord should come to me?" (Luke 1:43). The title does not mean that Mary created divinity or existed before God, but that her son is God. The title of Queen is interpreted in relation to Christ's messianic reign and biblical images of the king's mother (Revelation 12; Catechism, 495 and 963-975).

Think of the mother of a king: she is called queen mother because she is the king's mother, not because she conquered the throne by herself or is the source of royal power. Her title depends on her son. In the same way, Mary is Mother of God because her son is God, without ceasing to be a creature.

The strongest objection says that "Mother of God" sounds as if Mary created God and that "Queen of Heaven" recalls pagan goddesses. The honest answer is that the titles must be interpreted in their theological sense, and that they do not all have the same textual force: Luke 1:43 is especially important for Mother of God, while the interpretation of Revelation 12 is more debated. Worship of Mary would be contrary to Catholic doctrine.""", """A resposta direta é que Maria não é Deus: é uma criatura humana. É chamada Mãe de Deus porque o filho que concebeu e deu à luz é Jesus Cristo, uma pessoa divina verdadeiramente encarnada: "de onde me vem que a mãe do meu Senhor venha a mim?" (Lucas 1,43). O título não significa que Maria tenha criado a divindade nem que existisse antes de Deus, mas que seu filho é Deus. O título de Rainha é interpretado em relação ao reinado messiânico de Cristo e às imagens bíblicas da mãe do rei (Apocalipse 12; Catecismo, 495 e 963-975).

Pense na mãe de um rei: é chamada rainha-mãe por ser a mãe do rei, não porque tenha conquistado o trono por si mesma nem porque seja a fonte do poder real. Seu título depende de seu filho. Da mesma forma, Maria é Mãe de Deus porque seu filho é Deus, sem deixar de ser criatura.

A objeção mais forte diz que "Mãe de Deus" soa como se Maria tivesse criado Deus e que "Rainha do Céu" lembra deusas pagãs. A resposta honesta é que os títulos devem ser interpretados em seu sentido teológico, e que nem todos têm a mesma força textual: Lucas 1,43 é especialmente importante para Mãe de Deus, enquanto a interpretação de Apocalipse 12 é mais discutida. A adoração de Maria seria contrária à doutrina católica."""),
        foundation = LocalizedText("""Lucas 1,43; Juan 1,1.14; Gálatas 4,4; Apocalipsis 12; Catecismo, 495 y 963-975.""", """Luke 1:43; John 1:1.14; Galatians 4:4; Revelation 12; Catechism, 495 and 963-975.""", """Lucas 1,43; João 1,1.14; Gálatas 4,4; Apocalipse 12; Catecismo, 495 e 963-975."""),
        clarification = LocalizedText("""Los fundamentos de los títulos marianos no poseen todos la misma fuerza textual. Por ejemplo, Lucas 1,43 es especialmente importante para Madre de Dios, mientras que la interpretación de Apocalipsis 12 es más discutida.

La adoración de María sería contraria a la doctrina católica.""", """The foundations of Marian titles do not all have the same textual force. For example, Luke 1:43 is especially important for Mother of God, while the interpretation of Revelation 12 is more debated.

Adoration of Mary would be contrary to Catholic doctrine.""", """Os fundamentos dos títulos marianos não possuem todos a mesma força textual. Por exemplo, Lucas 1,43 é especialmente importante para Mãe de Deus, enquanto a interpretação de Apocalipse 12 é mais discutida.

A adoração de Maria seria contrária à doutrina católica.""")
    ),
    ApologeticsQuestion(
        number = 35,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Dónde dice la Biblia que María fue concebida sin pecado original?""", """Where does the Bible say that Mary was conceived without original sin?""", """Onde a Bíblia diz que Maria foi concebida sem pecado original?"""),
        answer = LocalizedText("""La respuesta directa es que la Biblia no contiene una frase que afirme literalmente la Inmaculada Concepción. La doctrina católica se fundamenta principalmente en la comprensión de la gracia singular concedida a María y en su papel en la historia de la salvación: saludada como «llena de gracia» (Lucas 1,28) y asociada a la descendencia que aplastará a la serpiente (Génesis 3,15). El argumento teológico sostiene que Dios pudo aplicar anticipadamente a María los frutos de la redención de Cristo. Esto no significa que María no necesitara ser salvada: según el dogma, fue redimida de manera singular por los méritos de Jesucristo (Catecismo, 490-493).

Piensa en dos personas rescatadas de un incendio: una es sacada después de caer, la otra es sacada antes de caer. Ambas deben su rescate a los bomberos, aunque de modos distintos. De modo semejante, ser preservada del pecado original y ser librada de él son dos formas de ser redimida.

La objeción más fuerte dice que Lucas 1,28 no demuestra por sí solo toda la doctrina y que un dogma definido en 1854 llega demasiado tarde. La respuesta honesta es que se trata de un dogma sostenido desde la Escritura interpretada a la luz de la Tradición, no de una enseñanza que pueda probarse con una cita bíblica inequívoca. Quien no comparta esa lectura de la Tradición no estará obligado por la sola cita.""", """The direct answer is that the Bible contains no sentence that literally affirms the Immaculate Conception. Catholic doctrine is based mainly on understanding the singular grace granted to Mary and her role in the history of salvation: greeted as "full of grace" (Luke 1:28) and associated with the offspring who will crush the serpent (Genesis 3:15). The theological argument holds that God could apply to Mary in advance the fruits of Christ's redemption. This does not mean that Mary did not need to be saved: according to the dogma, she was redeemed in a singular way through the merits of Jesus Christ (Catechism, 490-493).

Think of two people rescued from a fire: one is taken out after falling, the other is taken out before falling. Both owe their rescue to the firefighters, although in different ways. In the same way, being preserved from original sin and being delivered from it are two forms of being redeemed.

The strongest objection says that Luke 1:28 by itself does not prove the whole doctrine and that a dogma defined in 1854 comes too late. The honest answer is that it is a dogma held on the basis of Scripture interpreted in the light of Tradition, not a teaching that can be proved by an unequivocal biblical quote. Those who do not share that reading of Tradition will not be bound by the quote alone.""", """A resposta direta é que a Bíblia não contém uma frase que afirme literalmente a Imaculada Conceição. A doutrina católica se fundamenta principalmente na compreensão da graça singular concedida a Maria e em seu papel na história da salvação: saudada como "cheia de graça" (Lucas 1,28) e associada à descendência que esmagará a serpente (Gênesis 3,15). O argumento teológico sustenta que Deus pôde aplicar antecipadamente a Maria os frutos da redenção de Cristo. Isso não significa que Maria não precisasse ser salva: segundo o dogma, foi redimida de maneira singular pelos méritos de Jesus Cristo (Catecismo, 490-493).

Pense em duas pessoas resgatadas de um incêndio: uma é retirada depois de cair, a outra é retirada antes de cair. Ambas devem seu resgate aos bombeiros, embora de modos distintos. Da mesma forma, ser preservada do pecado original e ser livrada dele são duas formas de ser redimida.

A objeção mais forte diz que Lucas 1,28 não demonstra por si só toda a doutrina e que um dogma definido em 1854 chega tarde demais. A resposta honesta é que se trata de um dogma sustentado desde a Escritura interpretada à luz da Tradição, não de um ensinamento que possa ser provado com uma citação bíblica inequívoca. Quem não compartilha essa leitura da Tradição não estará obrigado pela só citação."""),
        foundation = LocalizedText("""Lucas 1,28; Génesis 3,15; Efesios 1,4; Catecismo, 490-493.""", """Luke 1:28; Genesis 3:15; Ephesians 1:4; Catechism, 490-493.""", """Lucas 1,28; Gênesis 3,15; Efésios 1,4; Catecismo, 490-493."""),
        clarification = LocalizedText("""Lucas 1,28 no demuestra por sí solo toda la doctrina de la concepción sin pecado original.

Es un dogma definido en 1854, sostenido desde la Escritura interpretada a la luz de la Tradición, no una enseñanza que pueda probarse mediante una cita bíblica inequívoca.""", """Luke 1:28 does not by itself demonstrate the whole doctrine of conception without original sin.

It is a dogma defined in 1854, sustained from Scripture interpreted in the light of Tradition, not a teaching that can be proved through an unequivocal biblical citation.""", """Lucas 1,28 não demonstra por si só toda a doutrina da concepção sem pecado original.

É um dogma definido em 1854, sustentado pela Escritura interpretada à luz da Tradição, e não um ensino que possa ser provado por meio de uma citação bíblica inequívoca.""")
    ),
    ApologeticsQuestion(
        number = 36,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""Si Jesús tenía hermanos, ¿cómo pudo María ser siempre virgen?""", """If Jesus had brothers, how could Mary remain a virgin?""", """Se Jesus tinha irmãos, como Maria pôde ser sempre virgem?"""),
        answer = LocalizedText("""La respuesta directa es que los términos usados para hablar de los «hermanos» de Jesús pueden designar relaciones familiares cuya extensión depende del contexto lingüístico y cultural. La interpretación católica entiende que esas referencias no obligan a concluir que María tuvo otros hijos biológicos, y sostiene además la virginidad perpetua de María desde la antigüedad cristiana (Catecismo, 499-501). La entrega de María al discípulo amado en Juan 19,26-27 ha sido interpretada tradicionalmente como coherente con la ausencia de otros hijos suyos, pues Jesús la confía a un discípulo y no a un hermano.

Piensa en una comunidad religiosa donde dos monjes se llaman «hermanos» y «padre» sin ser hijos de la misma madre: el parentesco es de fe y de vida común, no de sangre. El lenguaje familiar de la Biblia también es amplio. La analogía ayuda a ver que «hermano de Jesús» no significa necesariamente hijo biológico de María.

La objeción más fuerte dice que el griego adelphoi normalmente significa hermanos carnales y que la lectura más sencilla es que María tuvo más hijos. La respuesta honesta es que ese es el uso habitual, aunque el contexto de parentesco permite debate, y que el argumento de Juan 19 no es concluyente por sí solo. La virginidad perpetua pertenece a la enseñanza católica sostenida también por la Tradición antigua.""", """The direct answer is that the terms used to speak of Jesus' "brothers" can denote family relationships whose extent depends on the linguistic and cultural context. The Catholic interpretation understands that those references do not compel the conclusion that Mary had other biological children, and also upholds Mary's perpetual virginity since Christian antiquity (Catechism, 499-501). Jesus' entrusting of Mary to the beloved disciple in John 19:26-27 has traditionally been interpreted as coherent with the absence of other children of hers, since Jesus entrusts her to a disciple and not to a brother.

Think of a religious community where two monks call each other "brothers" and "father" without being children of the same mother: the kinship is one of faith and common life, not of blood. Biblical family language is also broad. The analogy helps to see that "brother of Jesus" does not necessarily mean a biological son of Mary.

The strongest objection says that the Greek adelphoi normally means blood brothers and that the simplest reading is that Mary had more children. The honest answer is that this is the usual usage, although the kinship context allows debate, and that the argument from John 19 is not conclusive by itself. Perpetual virginity belongs to Catholic teaching also held by ancient Tradition.""", """A resposta direta é que os termos usados para falar dos "irmãos" de Jesus podem designar relações familiares cuja extensão depende do contexto linguístico e cultural. A interpretação católica entende que essas referências não obrigam a concluir que Maria teve outros filhos biológicos, e sustenta ainda a virgindade perpétua de Maria desde a antiguidade cristã (Catecismo, 499-501). A entrega de Maria ao discípulo amado em João 19,26-27 foi tradicionalmente interpretada como coerente com a ausência de outros filhos seus, pois Jesus a confia a um discípulo e não a um irmão.

Pense em uma comunidade religiosa onde dois monges se chamam "irmãos" e "pai" sem serem filhos da mesma mãe: o parentesco é de fé e de vida comum, não de sangue. A linguagem familiar da Bíblia também é ampla. A analogia ajuda a ver que "irmão de Jesus" não significa necessariamente filho biológico de Maria.

A objeção mais forte diz que o grego adelphoi normalmente significa irmãos carnais e que a leitura mais simples é que Maria teve mais filhos. A resposta honesta é que esse é o uso habitual, embora o contexto de parentesco permita debate, e que o argumento de João 19 não é conclusivo por si só. A virgindade perpétua pertence ao ensinamento católico sustentado também pela Tradição antiga."""),
        foundation = LocalizedText("""Marcos 6,3; Mateo 13,55-56; Juan 19,26-27; Catecismo, 499-501.""", """Mark 6:3; Matthew 13:55-56; John 19:26-27; Catechism, 499-501.""", """Marcos 6,3; Mateus 13,55-56; João 19,26-27; Catecismo, 499-501."""),
        clarification = LocalizedText("""Este último argumento no es concluyente por sí solo. Y el término griego adelphoi normalmente significa hermanos, aunque su uso y los contextos de parentesco permiten un debate interpretativo.

La virginidad perpetua pertenece a la enseñanza católica sostenida también por la Tradición antigua. No debe presentarse como una conclusión lingüística que todos los especialistas necesariamente aceptan.""", """That last argument is not conclusive by itself. And the Greek term adelphoi normally means brothers, although its use and the contexts of kinship allow an interpretive debate.

Perpetual virginity belongs to Catholic teaching also sustained by ancient Tradition. It must not be presented as a linguistic conclusion that all specialists necessarily accept.""", """Esse último argumento não é concluyente por si só. E o termo grego adelphoi normalmente significa irmãos, embora seu uso e os contextos de parentesco permitam um debate interpretativo.

A virgindade perpétua pertence ao ensino católico sustentado também pela antiga Tradição. Não deve ser apresentada como uma conclusão linguística que todos os especialistas necessariamente aceitam.""")
    ),
    ApologeticsQuestion(
        number = 37,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Dónde aparece en la Biblia la Asunción de María?""", """Where does the Assumption of Mary appear in the Bible?""", """Onde aparece na Bíblia a Assunção de Maria?"""),
        answer = LocalizedText("""La respuesta directa es que ningún libro bíblico describe explícitamente el acontecimiento de la Asunción. La Iglesia lo comprende como la participación singular de María en la victoria de Cristo sobre la muerte y como anticipación de la resurrección prometida a los creyentes (1 Corintios 15,20-23). La doctrina fue definida solemnemente en 1950, aunque tiene antecedentes devocionales y litúrgicos mucho más antiguos (Catecismo, 966).

Piensa en la familia de un general victorioso, invitada a presidir el desfile del triunfo: no luchó en la batalla, pero participa de honores por su vínculo con el vencedor. De modo semejante, la Asunción se entiende como participación de María en la victoria de Cristo, sin que ello la convierta en protagonista independiente de la resurrección.

La objeción más fuerte dice que el silencio de la Biblia y la fecha tardía de la definición muestran que es una invención. La respuesta honesta es que Apocalipsis 12,1 admite distintas interpretaciones —Israel, la Iglesia, María— y no constituye por sí solo una narración histórica de la Asunción. Este dogma depende de la recepción de la Tradición y de la autoridad doctrinal de la Iglesia; un cristiano que no comparta esa tradición no lo aceptará por un solo versículo.""", """The direct answer is that no biblical book explicitly describes the event of the Assumption. The Church understands it as Mary's singular participation in Christ's victory over death and as an anticipation of the resurrection promised to believers (1 Corinthians 15:20-23). The doctrine was solemnly defined in 1950, although it has much older devotional and liturgical antecedents (Catechism, 966).

Think of the family of a victorious general, invited to preside over the triumph parade: they did not fight in the battle, but they share in the honors because of their bond with the victor. In the same way, the Assumption is understood as Mary's participation in Christ's victory, without making her an independent protagonist of the resurrection.

The strongest objection says that the silence of the Bible and the late date of the definition show that it is an invention. The honest answer is that Revelation 12:1 admits different interpretations —Israel, the Church, Mary— and does not by itself constitute a historical narrative of the Assumption. This dogma depends on the reception of Tradition and on the Church's doctrinal authority; a Christian who does not share that tradition will not accept it on the basis of a single verse.""", """A resposta direta é que nenhum livro bíblico descreve explicitamente o acontecimento da Assunção. A Igreja o compreende como a participação singular de Maria na vitória de Cristo sobre a morte e como antecipação da ressurreição prometida aos crentes (1 Coríntios 15,20-23). A doutrina foi definida solenemente em 1950, embora tenha antecedentes devocionais e litúrgicos muito mais antigos (Catecismo, 966).

Pense na família de um general vitorioso, convidada a presidir o desfile do triunfo: não lutou na batalha, mas participa das honras por seu vínculo com o vencedor. Da mesma forma, a Assunção é entendida como participação de Maria na vitória de Cristo, sem convertê-la em protagonista independente da ressurreição.

A objeção mais forte diz que o silêncio da Bíblia e a data tardia da definição mostram que é uma invenção. A resposta honesta é que Apocalipse 12,1 admite diferentes interpretações —Israel, a Igreja, Maria— e não constitui por si só uma narrativa histórica da Assunção. Este dogma depende da recepção da Tradição e da autoridade doutrinal da Igreja; um cristão que não compartilha essa tradição não o aceitará por um só versículo."""),
        foundation = LocalizedText("""1 Corintios 15,20-23; Apocalipsis 12,1; Lucas 1,28; Catecismo, 966.""", """1 Corinthians 15:20-23; Revelation 12:1; Luke 1:28; Catechism, 966.""", """1 Coríntios 15,20-23; Apocalipse 12,1; Lucas 1,28; Catecismo, 966."""),
        clarification = LocalizedText("""Apocalipsis 12 admite distintas interpretaciones, entre ellas las relacionadas con Israel, la Iglesia y María.

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
        answer = LocalizedText("""La respuesta directa es que la doctrina católica no enseña que los santos sean omniscientes ni que posean naturalmente las capacidades de Dios. La explicación teológica sostiene que quienes participan de la gloria divina pueden conocer aquello que Dios les permite conocer, y que las oraciones de los santos son presentadas a Dios (Apocalipsis 5,8; Apocalipsis 8,3-4). La clave es distinguir entre poseer conocimiento infinito por naturaleza y recibir un conocimiento como don divino (Catecismo, 954-956).

Piensa en una gran empresa donde el director no lee cada correo personalmente, sino que su equipo le filtra lo importante: no conoce todo por naturaleza, sino por el trabajo organizado de otros. De modo semejante, el conocimiento de los santos no sería infinito por sí mismo, sino participado y limitado por lo que Dios les permita conocer.

La objeción más fuerte dice que no hay explicación de cómo un santo conocería millones de peticiones concretas en distintas lenguas y lugares. La respuesta honesta es que la Biblia no explica el mecanismo, y la Iglesia no ofrece una explicación física de cómo ocurre. Afirma la intercesión de los santos, pero no presenta ese mecanismo como un hecho demostrado.""", """The direct answer is that Catholic doctrine does not teach that saints are omniscient or that they naturally possess God's capacities. The theological explanation holds that those who share in divine glory can know what God allows them to know, and that the prayers of the saints are presented to God (Revelation 5:8; Revelation 8:3-4). The key is to distinguish between possessing infinite knowledge by nature and receiving knowledge as a divine gift (Catechism, 954-956).

Think of a large company where the director does not personally read every email, but his team filters what is important to him: he does not know everything by nature, but through the organized work of others. In the same way, the saints' knowledge would not be infinite in itself, but shared and limited by what God allows them to know.

The strongest objection says there is no explanation of how a saint could know millions of concrete petitions in different languages and places. The honest answer is that the Bible does not explain the mechanism, and the Church offers no physical explanation of how it happens. It affirms the intercession of the saints, but does not present that mechanism as a proven fact.""", """A resposta direta é que a doutrina católica não ensina que os santos sejam oniscientes nem que possuam naturalmente as capacidades de Deus. A explicação teológica sustenta que os que participam da glória divina podem conhecer aquilo que Deus lhes permite conhecer, e que as orações dos santos são apresentadas a Deus (Apocalipse 5,8; Apocalipse 8,3-4). A chave é distinguir entre possuir conhecimento infinito por natureza e receber um conhecimento como dom divino (Catecismo, 954-956).

Pense em uma grande empresa onde o diretor não lê pessoalmente cada e-mail, mas sua equipe filtra o que é importante: não conhece tudo por natureza, mas pelo trabalho organizado dos outros. Da mesma forma, o conhecimento dos santos não seria infinito por si mesmo, mas participado e limitado pelo que Deus lhes permite conhecer.

A objeção mais forte diz que não há explicação de como um santo conheceria milhões de pedidos concretos em diferentes línguas e lugares. A resposta honesta é que a Bíblia não explica o mecanismo, e a Igreja não oferece uma explicação física de como isso acontece. Afirma a intercessão dos santos, mas não apresenta esse mecanismo como um fato demonstrado."""),
        foundation = LocalizedText("""Lucas 15,7; Apocalipsis 5,8; Apocalipsis 8,3-4; 1 Corintios 13,12; Catecismo, 954-956.""", """Luke 15:7; Revelation 5:8; Revelation 8:3-4; 1 Corinthians 13:12; Catechism, 954-956.""", """Lucas 15,7; Apocalipse 5,8; Apocalipse 8,3-4; 1 Coríntios 13,12; Catecismo, 954-956."""),
        clarification = LocalizedText("""La Biblia no explica el mecanismo mediante el cual un santo conocería peticiones particulares formuladas desde distintos lugares.

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
        answer = LocalizedText("""La respuesta directa es que la Biblia contiene relatos donde Dios obra mediante elementos materiales relacionados con sus servidores: una curación vinculada a los huesos de Eliseo (2 Reyes 13,20-21) y curaciones relacionadas con objetos que habían tocado a Pablo (Hechos 19,11-12). Estos relatos muestran que la intervención divina no queda necesariamente excluida por el uso de signos materiales. La veneración católica reconoce en las reliquias un vínculo con personas cuya vida estuvo dedicada a Dios, sin atribuir al objeto un poder mágico propio (Catecismo, 1674-1676 y 2110-2111).

Piensa en la medalla de un abuelo guardada por su familia: no tiene poderes, pero se valora porque recuerda quién fue y lo que vivió. Si alguien la vendiera como amuleto milagroso, estaría abusando de ese recuerdo. De modo semejante, una reliquia se venera por su vínculo con el santo, no por una magia inherente.

La objeción más fuerte dice que venerar reliquias parece superstición, que hay falsificaciones y que en la práctica se comercia con ellas. La respuesta honesta es que un objeto no posee poder mágico por ser una reliquia, que la autenticidad histórica de cada una debe examinarse, y que vender falsas reliquias o explotarlas económicamente es un abuso, no una consecuencia legítima de la fe.""", """The direct answer is that the Bible contains accounts where God acts through material elements related to his servants: a healing linked to Elisha's bones (2 Kings 13:20-21) and healings related to objects that had touched Paul (Acts 19:11-12). These accounts show that divine intervention is not necessarily excluded by the use of material signs. Catholic veneration recognizes in relics a bond with people whose lives were dedicated to God, without attributing any magical power to the object itself (Catechism, 1674-1676 and 2110-2111).

Think of a grandfather's medal kept by his family: it has no powers, but it is treasured because it recalls who he was and what he lived. If someone sold it as a miraculous amulet, they would be abusing that memory. In the same way, a relic is venerated because of its bond with the saint, not because of any inherent magic.

The strongest objection says that venerating relics looks like superstition, that there are forgeries, and that in practice they are traded. The honest answer is that an object has no magical power because it is a relic, that the historical authenticity of each one must be examined, and that selling false relics or exploiting them economically is an abuse, not a legitimate consequence of faith.""", """A resposta direta é que a Bíblia contém relatos em que Deus age por meio de elementos materiais relacionados a seus servidores: uma cura vinculada aos ossos de Eliseu (2 Reis 13,20-21) e curas relacionadas a objetos que haviam tocado Paulo (Atos 19,11-12). Estes relatos mostram que a intervenção divina não fica necessariamente excluída pelo uso de sinais materiais. A veneração católica reconhece nas relíquias um vínculo com pessoas cuja vida foi dedicada a Deus, sem atribuir ao objeto um poder mágico próprio (Catecismo, 1674-1676 e 2110-2111).

Pense na medalha de um avô guardada por sua família: não tem poderes, mas é valorizada porque lembra quem ele foi e o que viveu. Se alguém a vendesse como amuleto milagroso, estaria abusando dessa lembrança. Da mesma forma, uma relíquia é venerada por seu vínculo com o santo, não por uma magia inerente.

A objeção mais forte diz que venerar relíquias parece superstição, que há falsificações e que na prática se comercia com elas. A resposta honesta é que um objeto não possui poder mágico por ser uma relíquia, que a autenticidade histórica de cada uma deve ser examinada, e que vender falsas relíquias ou explorá-las economicamente é um abuso, não uma consequência legítima da fé."""),
        foundation = LocalizedText("""2 Reyes 13,20-21; Hechos 19,11-12; Marcos 5,27-34; Catecismo, 1674-1676 y 2110-2111.""", """2 Kings 13:20-21; Acts 19:11-12; Mark 5:27-34; Catechism, 1674-1676 and 2110-2111.""", """2 Reis 13,20-21; Atos 19,11-12; Marcos 5,27-34; Catecismo, 1674-1676 e 2110-2111."""),
        clarification = LocalizedText("""Un objeto no posee poder mágico por ser una reliquia.

La autenticidad histórica de cada reliquia también debe examinarse. Vender falsas reliquias, atribuirles propiedades garantizadas o explotarlas económicamente constituye un abuso, no una consecuencia legítima de la fe.""", """An object does not possess magical power because it is a relic.

The historical authenticity of each relic must also be examined. Selling false relics, attributing guaranteed properties to them or exploiting them economically constitutes abuse, not a legitimate consequence of faith.""", """Um objeto não possui poder mágico por ser uma relíquia.

A autenticidade histórica de cada relíquia também deve ser examinada. Vender falsas relíquias, atribuir-lhes propriedades garantidas ou explorá-las economicamente constitui abuso, não uma consequência legítima da fé.""")
    ),
    ApologeticsQuestion(
        number = 40,
        section = LocalizedText("""María, los santos, las imágenes y las devociones""", """Mary, the saints, images and devotions""", """Maria, os santos, as imagens e as devoções"""),
        question = LocalizedText("""¿Por qué creer en las apariciones de Fátima, Lourdes o Guadalupe?""", """Why believe in the apparitions of Fatima, Lourdes or Guadalupe?""", """Por que acreditar nas aparições de Fátima, Lourdes ou Guadalupe?"""),
        answer = LocalizedText("""La respuesta directa es que un católico no está obligado a creer en todas las apariciones marianas, incluso cuando una autoridad eclesiástica permite o reconoce una devoción asociada a ellas. La revelación pública, cuyo centro es Jesucristo, no necesita nuevas apariciones para ser completada (Hebreos 1,1-2; Catecismo, 66-67). La Iglesia examina los testimonios, la conformidad doctrinal y otros elementos antes de emitir juicios pastorales o doctrinales, y la autorización de una devoción no equivale a una demostración histórica o científica de que ocurrió una aparición sobrenatural.

Piensa en un amigo que te cuenta una experiencia personal muy importante para él: puedes escucharlo con respeto, tomártela en serio para tu vida y, sin embargo, no sentirte obligado a creer cada detalle. De modo semejante, una aparición puede ser importante para muchos creyentes sin convertirse en obligación de fe para todos.

La objeción más fuerte dice que si la Iglesia reconoce algunas apariciones, debería exigir creerlas, y que el discernimiento es demasiado subjetivo. La respuesta honesta es que la Iglesia propone esas devociones con prudencia, distingue entre fe central y experiencias particulares, y sabe que una experiencia religiosa puede ser valiosa sin ser una obligación universal. La actitud más sólida combina apertura, examen crítico y libertad de conciencia.""", """The direct answer is that a Catholic is not bound to believe in all Marian apparitions, even when an ecclesiastical authority permits or recognizes a devotion associated with them. Public revelation, whose center is Jesus Christ, needs no new apparitions to be completed (Hebrews 1:1-2; Catechism, 66-67). The Church examines testimonies, doctrinal conformity, and other elements before issuing pastoral or doctrinal judgments, and authorizing a devotion is not the same as a historical or scientific demonstration that a supernatural apparition occurred.

Think of a friend who tells you about a personal experience very important to him: you can listen respectfully, take it seriously for your life, and yet not feel bound to believe every detail. In the same way, an apparition can be important for many believers without becoming an obligation of faith for all.

The strongest objection says that if the Church recognizes some apparitions, it should require belief in them, and that discernment is too subjective. The honest answer is that the Church proposes those devotions prudently, distinguishes between central faith and particular experiences, and knows that a religious experience can be valuable without being a universal obligation. The soundest attitude combines openness, critical examination, and freedom of conscience.""", """A resposta direta é que um católico não é obrigado a acreditar em todas as aparições marianas, mesmo quando uma autoridade eclesiástica permite ou reconhece uma devoção associada a elas. A revelação pública, cujo centro é Jesus Cristo, não precisa de novas aparições para ser completada (Hebreus 1,1-2; Catecismo, 66-67). A Igreja examina os testemunhos, a conformidade doutrinal e outros elementos antes de emitir juízos pastorais ou doutrinais, e a autorização de uma devoção não equivale a uma demonstração histórica ou científica de que ocorreu uma aparição sobrenatural.

Pense em um amigo que lhe conta uma experiência pessoal muito importante para ele: você pode ouvi-lo com respeito, levá-la a sério para sua vida e, ainda assim, não se sentir obrigado a acreditar em cada detalhe. Da mesma forma, uma aparição pode ser importante para muitos crentes sem se tornar obrigação de fé para todos.

A objeção mais forte diz que, se a Igreja reconhece algumas aparições, deveria exigir que se acredite nelas, e que o discernimento é subjetivo demais. A resposta honesta é que a Igreja propõe essas devoções com prudência, distingue entre fé central e experiências particulares, e sabe que uma experiência religiosa pode ser valiosa sem ser uma obrigação universal. A atitude mais sólida combina abertura, exame crítico e liberdade de consciência."""),
        foundation = LocalizedText("""Hebreos 1,1-2; Gálatas 1,8; Catecismo, 66-67.""", """Hebrews 1:1-2; Galatians 1:8; Catechism, 66-67.""", """Hebreus 1,1-2; Gálatas 1,8; Catecismo, 66-67."""),
        clarification = LocalizedText("""La autorización de una devoción no equivale necesariamente a una demostración histórica o científica de que ocurrió una aparición sobrenatural.

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
        answer = LocalizedText("""La respuesta directa es que Jesús no usa la palabra «Papa» ni dice literalmente que Pedro sea el «primer Papa» en el sentido institucional actual. El argumento católico es otro: Jesús confiere a Pedro un papel especial que la Iglesia interpreta como fundamento del ministerio petrino. En Mateo 16,18-19 le habla de la roca y de las llaves del Reino, en Lucas 22,31-32 le encarga fortalecer a sus hermanos y en Juan 21,15-17 le confía el cuidado de sus ovejas.

Piensa en el mayordomo de un palacio en Isaías 22: recibe la llave de la casa de su señor para abrir y cerrar en su nombre. No es el rey, pero ejerce una autoridad real por delegación. La analogía ayuda a entender las «llaves» de Mateo 16, aunque no demuestra por sí sola cómo se transmitió ese ministerio a los obispos de Roma.

La objeción más fuerte dice: «Esos textos prueban la importancia de Pedro, no la estructura del papado moderno ni su sucesión en Roma». Es correcto pedir más: la doctrina del primado romano depende también de la historia y de la sucesión apostólica. Jesús no deja una descripción completa de la institución posterior, y esa es una de las discusiones centrales con protestantes y ortodoxos.""", """The direct answer is that Jesus does not use the word “Pope” or literally say that Peter was the “first Pope” in the current institutional sense. The Catholic argument is different: Jesus entrusts Peter with a special role that the Church interprets as the foundation of the Petrine ministry. In Matthew 16:18-19 He speaks to him of the rock and the keys of the Kingdom, in Luke 22:31-32 He charges him with strengthening his brothers, and in John 21:15-17 He entrusts him with the care of His sheep.

Think of the steward of a palace in Isaiah 22: he receives the key of his master's house to open and close in his name. He is not the king, but he exercises real delegated authority. The analogy helps to understand the “keys” of Matthew 16, although it does not by itself demonstrate how that ministry was transmitted to the bishops of Rome.

The strongest objection says: “Those texts prove Peter's importance, not the structure of the modern papacy or its succession in Rome.” It is fair to ask for more: the doctrine of Roman primacy also depends on history and apostolic succession. Jesus does not leave a complete description of the later institution, and that is one of the central discussions with Protestants and Orthodox.""", """A resposta direta é que Jesus não usa a palavra “Papa” nem diz literalmente que Pedro foi o “primeiro Papa” no sentido institucional atual. O argumento católico é outro: Jesus confia a Pedro um papel especial que a Igreja interpreta como fundamento do ministério petrino. Em Mateus 16,18-19 fala-lhe da rocha e das chaves do Reino, em Lucas 22,31-32 encarrega-o de fortalecer seus irmãos e em João 21,15-17 confia-lhe o cuidado de suas ovelhas.

Pense no mordomo de um palácio em Isaías 22: ele recebe a chave da casa de seu senhor para abrir e fechar em seu nome. Não é o rei, mas exerce uma autoridade real por delegação. A analogia ajuda a compreender as “chaves” de Mateus 16, embora não demonstre por si só como esse ministério foi transmitido aos bispos de Roma.

A objeção mais forte diz: “Esses textos provam a importância de Pedro, não a estrutura do papado moderno nem sua sucessão em Roma”. É justo pedir mais: a doutrina do primado romano depende também da história e da sucessão apostólica. Jesus não deixa uma descrição completa da instituição posterior, e essa é uma das discussões centrais com protestantes e ortodoxos."""),
        foundation = LocalizedText("""Mateo 16,18-19; Lucas 22,31-32; Juan 21,15-17; Isaías 22,20-22; Catecismo, 880-882.""", """Matthew 16:18-19; Luke 22:31-32; John 21:15-17; Isaiah 22:20-22; Catechism, 880-882.""", """Mateus 16,18-19; Lucas 22,31-32; João 21,15-17; Isaías 22,20-22; Catecismo, 880-882."""),
        clarification = LocalizedText("""Los pasajes muestran la importancia de Pedro, pero no describen explícitamente toda la estructura del papado moderno ni establecen con detalle cómo se transmitiría su ministerio a los obispos de Roma.

La doctrina del primado romano también depende de argumentos históricos y de la sucesión apostólica. Esta es una de las discusiones centrales con protestantes y ortodoxos.""", """These passages show Peter's importance, but they do not explicitly describe the whole structure of the modern papacy nor establish in detail how his ministry would be transmitted to the bishops of Rome.

The doctrine of Roman primacy also depends on historical arguments and on apostolic succession. This is one of the central discussions with Protestants and Orthodox.""", """As passagens mostram a importância de Pedro, mas não descrevem explicitamente toda a estrutura do papado moderno nem estabelecem em detalhe como seu ministério seria transmitido aos bispos de Roma.

A doutrina do primado romano também depende de argumentos históricos e da sucessão apostólica. Essa é uma das discussões centrais com protestantes e ortodoxos.""")
    ),
    ApologeticsQuestion(
        number = 42,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""Si hubo Papas corruptos, ¿cómo puede ser infalible el Papa?""", """If there were corrupt Popes, how can the Pope be infallible?""", """Se houve Papas corruptos, como pode o Papa ser infalível?"""),
        answer = LocalizedText("""La respuesta directa es que infalibilidad no significa impecabilidad. La doctrina católica no afirma que el Papa nunca peque, nunca se equivoque en política, nunca tome malas decisiones ni tenga opiniones personales erróneas. La infalibilidad se refiere solo a circunstancias precisas en las que, como pastor supremo, define definitivamente una doctrina de fe o de moral para toda la Iglesia. Pedro mismo cometió errores graves y fue corregido por Pablo (Gálatas 2,11-14).

Piensa en un embajador que firma un tratado en nombre de su gobierno: sus deudas o vicios personales pueden ser reales, pero no se sigue de ahí que cada firma oficial sea inválida. La analogía distingue la persona de la función, aunque no demuestra por qué Dios asistiría a la definición solemne.

La objeción más fuerte dice: «Si Dios protege la definición, ¿por qué no protege a la persona y evita un Papa corrupto?». La respuesta honesta es que los versículos no enuncian literalmente la definición del Vaticano I: hace falta aceptar una interpretación particular de las promesas de Cristo a su Iglesia. La distinción entre pecado personal y error definitivo en la doctrina puede sostenerse, pero no es una prueba neutral.""", """The direct answer is that infallibility does not mean impeccability. Catholic doctrine does not affirm that the Pope never sins, never makes political mistakes, never takes bad decisions, or never holds erroneous personal opinions. Infallibility refers only to precise circumstances in which, as supreme pastor, he definitively defines a doctrine of faith or morals for the whole Church. Peter himself committed grave errors and was corrected by Paul (Galatians 2:11-14).

Think of an ambassador who signs a treaty on behalf of his government: his personal debts or vices may be real, but it does not follow that every official signature is invalid. The analogy distinguishes the person from the function, although it does not demonstrate why God would assist the solemn definition.

The strongest objection says: “If God protects the definition, why does He not protect the person and prevent a corrupt Pope?” The honest answer is that the verses do not literally state the definition of Vatican I: one must accept a particular interpretation of Christ's promises to His Church. The distinction between personal sin and definitive error in doctrine can be maintained, but it is not a neutral proof.""", """A resposta direta é que infalibilidade não significa impecabilidade. A doutrina católica não afirma que o Papa nunca peque, nunca se equivoque em política, nunca tome más decisões nem tenha opiniões pessoais errôneas. A infalibilidade refere-se apenas a circunstâncias precisas em que, como pastor supremo, define definitivamente uma doutrina de fé ou de moral para toda a Igreja. O próprio Pedro cometeu erros graves e foi corrigido por Paulo (Gálatas 2,11-14).

Pense em um embaixador que assina um tratado em nome de seu governo: suas dívidas ou vícios pessoais podem ser reais, mas daí não se segue que cada assinatura oficial seja inválida. A analogia distingue a pessoa da função, embora não demonstre por que Deus assistiria à definição solene.

A objeção mais forte diz: “Se Deus protege a definição, por que não protege a pessoa e evita um Papa corrupto?”. A resposta honesta é que os versículos não enunciam literalmente a definição do Vaticano I: é preciso aceitar uma interpretação particular das promessas de Cristo à sua Igreja. A distinção entre pecado pessoal e erro definitivo na doutrina pode ser sustentada, mas não é uma prova neutra."""),
        foundation = LocalizedText("""Lucas 22,31-32; Mateo 16,18; Juan 16,13; Gálatas 2,11-14; Catecismo, 889-892.""", """Luke 22:31-32; Matthew 16:18; John 16:13; Galatians 2:11-14; Catechism, 889-892.""", """Lucas 22,31-32; Mateus 16,18; João 16,13; Gálatas 2,11-14; Catecismo, 889-892."""),
        clarification = LocalizedText("""Los versículos citados no enuncian literalmente la definición de infalibilidad del Concilio Vaticano I.

La argumentación católica es que el don de preservar a la Iglesia del error definitivo puede coexistir con los pecados personales de sus ministros.

No obstante, esa afirmación requiere aceptar una interpretación particular de las promesas de Cristo a su Iglesia.""", """The verses cited do not literally state the definition of infallibility of the First Vatican Council.

The Catholic argument is that the gift of preserving the Church from definitive error can coexist with the personal sins of its ministers.

Nevertheless, that affirmation requires accepting a particular interpretation of Christ's promises to His Church.""", """Os versículos citados não enunciam literalmente a definição de infalibilidade do Concílio Vaticano I.

O argumento católico é que o dom de preservar a Igreja do erro definitivo pode coexistir com os pecados pessoais de seus ministros.

Contudo, essa afirmação requer aceitar uma interpretação particular das promessas de Cristo à sua Igreja.""")
    ),
    ApologeticsQuestion(
        number = 43,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Por qué la Iglesia tiene autoridad para interpretar la Biblia?""", """Why does the Church have authority to interpret the Bible?""", """Por que a Igreja tem autoridade para interpretar a Bíblia?"""),
        answer = LocalizedText("""La respuesta directa es que Jesús no entregó solamente textos a sus discípulos: también les encargó enseñar, bautizar y formar comunidades (Mateo 28,18-20). El Nuevo Testamento muestra a los apóstoles resolviendo disputas doctrinales y prácticas, como en Hechos 15, y llama a la Iglesia columna y fundamento de la verdad (1 Timoteo 3,15). Por eso la Iglesia católica entiende que el Magisterio sirve a la Palabra de Dios, no que está por encima de ella.

Piensa en una familia numerosa que conserva las cartas de su fundador: todos pueden leerlas, pero la familia necesita representantes que las interpreten de forma coherente para no dividirse en mil lecturas opuestas. La analogía ayuda a ver la función de unidad, aunque no demuestra por sí sola el alcance concreto del Magisterio.

La objeción más fuerte dice: «Es circular: la Iglesia usa la Biblia para probar su autoridad y su autoridad para interpretar la Biblia». Es una crítica seria. Que existiera autoridad apostólica no demuestra automáticamente todos los alcances que hoy se atribuyen al Magisterio: hay que justificar también su continuidad histórica y los límites de su ejercicio. Un cristiano, además, puede y debe leer la Biblia personalmente.""", """The direct answer is that Jesus did not entrust only texts to his disciples: he also charged them to teach, baptize and form communities (Matthew 28:18-20). The New Testament shows the apostles resolving doctrinal and practical disputes, as in Acts 15, and calls the Church the pillar and foundation of the truth (1 Timothy 3:15). That is why the Catholic Church understands that the Magisterium serves the Word of God, not that it stands above it.

Think of a large family that preserves the letters of its founder: everyone can read them, but the family needs representatives who interpret them coherently so as not to split into a thousand opposing readings. The analogy helps to see the function of unity, although it does not by itself demonstrate the concrete scope of the Magisterium.

The strongest objection says: “It is circular: the Church uses the Bible to prove its authority and its authority to interpret the Bible.” That is a serious criticism. The fact that there was apostolic authority does not automatically demonstrate all the scopes now attributed to the Magisterium: its historical continuity and the limits of its exercise must also be justified. A Christian, moreover, can and must read the Bible personally.""", """A resposta direta é que Jesus não entregou apenas textos aos seus discípulos: também lhes encarregou ensinar, batizar e formar comunidades (Mateus 28,18-20). O Novo Testamento mostra os apóstolos resolvendo disputas doutrinárias e práticas, como em Atos 15, e chama a Igreja de coluna e fundamento da verdade (1 Timóteo 3,15). Por isso a Igreja católica entende que o Magistério serve à Palavra de Deus, e não que está acima dela.

Pense em uma família numerosa que conserva as cartas de seu fundador: todos podem lê-las, mas a família precisa de representantes que as interpretem de forma coerente para não se dividir em mil leituras opostas. A analogia ajuda a ver a função de unidade, embora não demonstre por si só o alcance concreto do Magistério.

A objeção mais forte diz: “É circular: a Igreja usa a Bíblia para provar sua autoridade e sua autoridade para interpretar a Bíblia”. É uma crítica séria. Que tenha existido autoridade apostólica não demonstra automaticamente todos os alcances hoje atribuídos ao Magistério: é preciso justificar também sua continuidade histórica e os limites de seu exercício. Um cristão, além disso, pode e deve ler a Bíblia pessoalmente."""),
        foundation = LocalizedText("""Mateo 28,18-20; Hechos 15,1-29; 1 Timoteo 3,15; 2 Pedro 1,20-21; Catecismo, 85-87.""", """Matthew 28:18-20; Acts 15:1-29; 1 Timothy 3:15; 2 Peter 1:20-21; Catechism, 85-87.""", """Mateus 28,18-20; Atos 15,1-29; 1 Timóteo 3,15; 2 Pedro 1,20-21; Catecismo, 85-87."""),
        clarification = LocalizedText("""El argumento de que existió autoridad apostólica no demuestra automáticamente todos los alcances que hoy se atribuyen al Magisterio católico.

Es necesario justificar también su continuidad histórica y los límites de su ejercicio.""", """The argument that there was apostolic authority does not automatically demonstrate all the scopes now attributed to the Catholic Magisterium.

Its historical continuity and the limits of its exercise must also be justified.""", """O argumento de que existiu autoridade apostólica não demonstra automaticamente todos os alcances hoje atribuídos ao Magistério católico.

É necessário justificar também sua continuidade histórica e os limites de seu exercício.""")
    ),
    ApologeticsQuestion(
        number = 44,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""Si Jesús enseñó a servir, ¿por qué la Iglesia tiene una jerarquía?""", """If Jesus taught to serve, why does the Church have a hierarchy?""", """Se Jesus ensinou a servir, por que a Igreja tem uma hierarquia?"""),
        answer = LocalizedText("""La respuesta directa es que la igualdad de dignidad no implica que todos desempeñen exactamente la misma función. Jesús escogió a los Doce y les confió responsabilidades particulares (Marcos 3,13-19), y las primeras comunidades reconocieron ministerios de enseñanza, supervisión y servicio (Hechos 6,1-6; 1 Corintios 12,27-30). La explicación católica es que el ministerio ordenado existe para servir a la comunidad, no para dominarla: el mayor ha de comportarse como el servidor de todos (Lucas 22,24-27).

Piensa en un hospital: médicos, enfermeros y administrativos tienen la misma dignidad como personas, pero no hacen lo mismo. El director coordina y responde por el conjunto, y su autoridad vale por el servicio al paciente, no por un privilegio personal. La analogía ayuda a distinguir jerarquía de superioridad de valor.

La objeción más fuerte dice: «En la práctica la jerarquía se vuelve clericalismo, obediencia abusiva y privilegio». Es una denuncia con fundamento histórico. Que la estructura ministerial tenga fundamentos bíblicos no convierte en aceptables sus malas formas de ejercicio: deben examinarse críticamente a la luz del Evangelio.""", """The direct answer is that equality of dignity does not imply that everyone performs exactly the same function. Jesus chose the Twelve and entrusted them with particular responsibilities (Mark 3:13-19), and the first communities recognized ministries of teaching, supervision and service (Acts 6:1-6; 1 Corinthians 12:27-30). The Catholic explanation is that ordained ministry exists to serve the community, not to dominate it: the greatest must act as the servant of all (Luke 22:24-27).

Think of a hospital: doctors, nurses and administrators have the same dignity as persons, but they do not do the same work. The director coordinates and answers for the whole, and his authority is valid because of service to the patient, not because of a personal privilege. The analogy helps to distinguish hierarchy from superiority of worth.

The strongest objection says: “In practice hierarchy becomes clericalism, abusive obedience and privilege.” That is a historically grounded complaint. The fact that ministerial structure has biblical foundations does not make its bad forms of exercise acceptable: they must be critically examined in the light of the Gospel.""", """A resposta direta é que a igualdade de dignidade não implica que todos desempenhem exatamente a mesma função. Jesus escolheu os Doze e lhes confiou responsabilidades particulares (Marcos 3,13-19), e as primeiras comunidades reconheceram ministérios de ensino, supervisão e serviço (Atos 6,1-6; 1 Coríntios 12,27-30). A explicação católica é que o ministério ordenado existe para servir à comunidade, e não para dominá-la: o maior deve comportar-se como o servidor de todos (Lucas 22,24-27).

Pense em um hospital: médicos, enfermeiros e administrativos têm a mesma dignidade como pessoas, mas não fazem o mesmo trabalho. O diretor coordina e responde pelo conjunto, e sua autoridade vale pelo serviço ao paciente, e não por um privilégio pessoal. A analogia ajuda a distinguir hierarquia de superioridade de valor.

A objeção mais forte diz: “Na prática, a hierarquia se torna clericalismo, obediência abusiva e privilégio”. É uma denúncia com fundamento histórico. Que a estrutura ministerial tenha fundamentos bíblicos não torna aceitáveis suas más formas de exercício: devem ser examinadas criticamente à luz do Evangelho."""),
        foundation = LocalizedText("""Marcos 3,13-19; Lucas 22,24-27; Hechos 6,1-6; 1 Corintios 12,27-30; 1 Timoteo 3,1-13.""", """Mark 3:13-19; Luke 22:24-27; Acts 6:1-6; 1 Corinthians 12:27-30; 1 Timothy 3:1-13.""", """Marcos 3,13-19; Lucas 22,24-27; Atos 6,1-6; 1 Coríntios 12,27-30; 1 Timóteo 3,1-13."""),
        clarification = LocalizedText("""La jerarquía puede convertirse en clericalismo cuando sus miembros se consideran superiores en dignidad, exigen obediencia abusiva o utilizan su posición para obtener privilegios.

La estructura ministerial tiene fundamentos bíblicos; las formas concretas de ejercerla deben ser examinadas críticamente a la luz del Evangelio.""", """Hierarchy can become clericalism when its members regard themselves as superior in dignity, demand abusive obedience or use their position to obtain privileges.

Ministerial structure has biblical foundations; the concrete forms of exercising it must be examined critically in the light of the Gospel.""", """A hierarquia pode se converter em clericalismo quando seus membros se consideram superiores em dignidade, exigem obediência abusiva ou utilizam sua posição para obter privilégios.

A estrutura ministerial tem fundamentos bíblicos; as formas concretas de exercê-la devem ser examinadas criticamente à luz do Evangelho.""")
    ),
    ApologeticsQuestion(
        number = 45,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿La Iglesia católica considera inferiores a los demás cristianos?""", """Does the Catholic Church consider other Christians inferior?""", """A Igreja católica considera inferiores os demais cristãos?"""),
        answer = LocalizedText("""La respuesta directa es no. La Iglesia católica considera que la plenitud de los medios de salvación confiados por Cristo subsiste en ella, pero reconoce que fuera de su estructura visible existen auténticos elementos de verdad y de santificación. También reconoce como hermanos en Cristo a los cristianos válidamente bautizados de otras comunidades (Catecismo, 838). Considerar que algo es pleno no equivale a tratar a las personas como inferiores en dignidad.

Piensa en una familia extensa con una casa principal y otras viviendas donde también se vive en familia: la casa principal no convierte a los parientes de fuera en extraños. La analogía ayuda a distinguir plenitud de medios y desprecio hacia las personas, aunque no resuelve las diferencias doctrinales.

La objeción más fuerte dice: «Si hay diferencias reales en sacramentos, ministerios y sucesión, hablar de plena verdad puede sonar a exclusivismo». Es cierto: no sería honesto fingir que esas diferencias carecen de importancia. Pero tampoco sería católico negar la acción de Dios en otros cristianos. La unidad es una responsabilidad, no una excusa para el desprecio.""", """The direct answer is no. The Catholic Church considers that the fullness of the means of salvation entrusted by Christ subsists in it, but it recognizes that outside its visible structure there exist authentic elements of truth and sanctification. It also recognizes Christians validly baptized in other communities as brothers in Christ (Catechism, 838). Considering something to be full is not the same as treating people as inferior in dignity.

Think of an extended family with a main house and other homes where family life also exists: the main house does not turn relatives outside into strangers. The analogy helps to distinguish fullness of means from contempt for people, although it does not resolve doctrinal differences.

The strongest objection says: “If there are real differences in sacraments, ministries and succession, speaking of full truth can sound exclusivist.” That is true: it would not be honest to pretend those differences are unimportant. But neither would it be Catholic to deny God's action in other Christians. Unity is a responsibility, not an excuse for contempt.""", """A resposta direta é não. A Igreja católica considera que a plenitude dos meios de salvação confiados por Cristo subsiste nela, mas reconhece que, fora de sua estrutura visível, existem autênticos elementos de verdade e de santificação. Também reconhece como irmãos em Cristo os cristãos validamente batizados de outras comunidades (Catecismo, 838). Considerar que algo é pleno não equivale a tratar as pessoas como inferiores em dignidade.

Pense em uma família extensa com uma casa principal e outras moradias onde também se vive em família: a casa principal não transforma os parentes de fora em estranhos. A analogia ajuda a distinguir plenitude de meios e desprezo pelas pessoas, embora não resolva as diferenças doutrinárias.

A objeção mais forte diz: “Se há diferenças reais em sacramentos, ministérios e sucessão, falar de plena verdade pode soar a exclusivismo”. É verdade: não seria honesto fingir que essas diferenças não têm importância. Mas tampouco seria católico negar a ação de Deus em outros cristãos. A unidade é uma responsabilidade, não uma desculpa para o desprezo."""),
        foundation = LocalizedText("""Juan 17,21; Efesios 4,4-6; 1 Corintios 12,13; Catecismo, 817-819 y 838.""", """John 17:21; Ephesians 4:4-6; 1 Corinthians 12:13; Catechism, 817-819 and 838.""", """João 17,21; Efésios 4,4-6; 1 Coríntios 12,13; Catecismo, 817-819 e 838."""),
        clarification = LocalizedText("""Hay diferencias doctrinales reales sobre sacramentos, ministerios, autoridad y sucesión apostólica.

No sería honesto fingir que esas diferencias carecen de importancia. Pero tampoco sería católico negar la acción de Dios en otros cristianos o tratarlos como personas de menor dignidad.""", """There are real doctrinal differences about the sacraments, ministries, authority and apostolic succession.

It would not be honest to pretend those differences are unimportant. But neither would it be Catholic to deny God's action in other Christians or treat them as persons of lesser dignity.""", """Há diferenças doutrinárias reais sobre sacramentos, ministérios, autoridade e sucessão apostólica.

Não seria honesto fingir que essas diferenças não têm importância. Mas tampouco seria católico negar a ação de Deus em outros cristãos ou tratá-los como pessoas de menor dignidade.""")
    ),
    ApologeticsQuestion(
        number = 46,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Cómo pudo la Iglesia participar en las Cruzadas?""", """How could the Church take part in the Crusades?""", """Como a Igreja pôde participar das Cruzadas?"""),
        answer = LocalizedText("""La respuesta directa es que las Cruzadas no pueden justificarse como un todo. Surgieron en contextos donde se mezclaban motivos religiosos, políticos, territoriales y militares: defensa de poblaciones o peregrinos, recuperación de territorios, ambiciones y acciones gravemente injustas. La doctrina moral católica distingue la defensa legítima de la violencia injustificada (Catecismo, 2307-2317), y los ataques contra civiles, las matanzas y la imposición violenta de la fe deben juzgarse críticamente.

Piensa en la toma de Jerusalén en 1099: comprender la Rivalidad política y militar de la época ayuda a explicar cómo ocurrió, pero no convierte en justa la matanza de sus habitantes. La analogía distingue explicar de justificar.

La objeción más fuerte dice: «Si la Iglesia predicó y bendijo campañas violentas, ¿qué queda de la enseñanza de Jesús sobre la paz y de no empuñar la espada (Mateo 26,52)?». Es una tensión real. Reconocer el contexto histórico no basta para justificar los hechos, y la mejor defensa de la fe no consiste en afirmar que todos los cristianos de la historia actuaron correctamente.""", """The direct answer is that the Crusades cannot be justified as a whole. They arose in contexts where religious, political, territorial and military motives mingled: defense of peoples or pilgrims, recovery of territories, ambitions and gravely unjust actions. Catholic moral doctrine distinguishes legitimate defense from unjustified violence (Catechism, 2307-2317), and attacks against civilians, massacres and the violent imposition of faith must be judged critically.

Think of the capture of Jerusalem in 1099: understanding the political and military rivalry of the time helps to explain how it happened, but it does not make the massacre of its inhabitants just. The analogy distinguishes explaining from justifying.

The strongest objection says: “If the Church preached and blessed violent campaigns, what remains of Jesus' teaching on peace and on not taking up the sword (Matthew 26:52)?” That is a real tension. Recognizing the historical context is not enough to justify the facts, and the best defense of faith does not consist in affirming that all Christians in history acted rightly.""", """A resposta direta é que as Cruzadas não podem ser justificadas como um todo. Elas surgiram em contextos em que se misturavam motivos religiosos, políticos, territoriais e militares: defesa de populações ou peregrinos, recuperação de territórios, ambições e ações gravemente injustas. A doutrina moral católica distingue a legítima defesa da violência injustificada (Catecismo, 2307-2317), e os ataques contra civis, as matanças e a imposição violenta da fé devem ser julgados criticamente.

Pense na tomada de Jerusalém em 1099: compreender a rivalidade política e militar da época ajuda a explicar como aconteceu, mas não torna justa a matança de seus habitantes. A analogia distingue explicar de justificar.

A objeção mais forte diz: “Se a Igreja pregou e abençoou campanhas violentas, o que resta do ensino de Jesus sobre a paz e sobre não empunhar a espada (Mateus 26,52)?”. É uma tensão real. Reconhecer o contexto histórico não basta para justificar os fatos, e a melhor defesa da fé não consiste em afirmar que todos os cristãos da história agiram corretamente."""),
        foundation = LocalizedText("""Mateo 5,9; 5,44; Mateo 26,52; Juan 18,36; Catecismo, 2307-2317.""", """Matthew 5:9,44; Matthew 26:52; John 18:36; Catechism, 2307-2317.""", """Mateus 5,9; 5,44; Mateus 26,52; João 18,36; Catecismo, 2307-2317."""),
        clarification = LocalizedText("""Esa distinción no convierte automáticamente todas las Cruzadas en moralmente legítimas.

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
        answer = LocalizedText("""La respuesta directa es que hoy no pueden justificarse. La fe cristiana exige una adhesión libre de la conciencia: una declaración religiosa obtenida exclusivamente por coerción no constituye una auténtica respuesta de amor a Dios. Históricamente, las instituciones inquisitoriales funcionaron en sociedades donde religión, poder político y orden jurídico estaban estrechamente vinculados, y su historia no puede reducirse a una sola práctica ni a una única época.

Piensa en una firma obtenida bajo amenaza en un contrato: el papel puede estar firmado, pero la voluntad no es libre. La analogía ayuda a ver por qué una profesión de fe coaccionada no equivale a fe auténtica, aunque no resume la complejidad histórica de esos tribunales.

La objeción más fuerte dice: «Si la Iglesia hoy condena la coerción religiosa, ¿por qué la toleró o participó en ella durante siglos?». Es una pregunta legítima. Hubo procesos injustos y formas de violencia religiosa que contradicen la dignidad humana, y la defensa católica de la libertad religiosa del Vaticano II ofrece un criterio para juzgarlas críticamente, sin caer en exageraciones históricas.""", """The direct answer is that today they cannot be justified. Christian faith requires a free adherence of conscience: a religious profession obtained exclusively by coercion does not constitute an authentic response of love to God. Historically, inquisitorial institutions functioned in societies where religion, political power and the legal order were closely linked, and their history cannot be reduced to a single practice or a single era.

Think of a signature obtained under threat in a contract: the paper may be signed, but the will is not free. The analogy helps to see why a coerced profession of faith is not the same as authentic faith, although it does not summarize the historical complexity of those tribunals.

The strongest objection says: “If the Church today condemns religious coercion, why did it tolerate or participate in it for centuries?” That is a legitimate question. There were unjust trials and forms of religious violence that contradict human dignity, and the Catholic defense of religious freedom of Vatican II offers a criterion for judging them critically, without falling into historical exaggerations.""", """A resposta direta é que hoje não podem ser justificadas. A fé cristã exige uma adesão livre da consciência: uma profissão religiosa obtida exclusivamente por coerção não constitui uma autêntica resposta de amor a Deus. Historicamente, as instituições inquisitoriais funcionaram em sociedades onde religião, poder político e ordem jurídica estavam estreitamente vinculados, e sua história não pode ser reduzida a uma só prática nem a uma única época.

Pense em uma assinatura obtida sob ameaça em um contrato: o papel pode estar assinado, mas a vontade não é livre. A analogia ajuda a ver por que uma profissão de fé coagida não equivale à fé autêntica, embora não resuma a complexidade histórica desses tribunais.

A objeção mais forte diz: “Se a Igreja hoje condena a coerção religiosa, por que a tolerou ou dela participou durante séculos?”. É uma pergunta legítima. Houve processos injustos e formas de violência religiosa que contradizem a dignidade humana, e a defesa católica da liberdade religiosa do Vaticano II oferece um critério para julgá-las criticamente, sem cair em exageros históricos."""),
        foundation = LocalizedText("""Juan 18,36; Mateo 13,24-30; 2 Corintios 3,17; Catecismo, 2104-2109.""", """John 18:36; Matthew 13:24-30; 2 Corinthians 3:17; Catechism, 2104-2109.""", """João 18,36; Mateus 13,24-30; 2 Coríntios 3,17; Catecismo, 2104-2109."""),
        clarification = LocalizedText("""Hubo procesos injustos y formas de violencia religiosa que contradicen el respeto a la dignidad humana.

Es importante reconocerlos sin caer en exageraciones históricas ni utilizar la existencia de abusos como argumento para negar cualquier cuestión doctrinal.

La defensa católica de la libertad religiosa desarrollada por el Concilio Vaticano II ofrece un criterio importante para evaluar críticamente esas prácticas.""", """There were unjust trials and forms of religious violence that contradict respect for human dignity.

It is important to recognize them without falling into historical exaggerations or using the existence of abuses as an argument to deny every doctrinal question.

The Catholic defense of religious freedom developed by the Second Vatican Council offers an important criterion for critically evaluating those practices.""", """Houve processos injustos e formas de violência religiosa que contradizem o respeito pela dignidade humana.

É importante reconhecê-los sem cair em exageros históricos nem utilizar a existência de abusos como argumento para negar qualquer questão doutrinária.

A defesa católica da liberdade religiosa desenvolvida pelo Concílio Vaticano II oferece um critério importante para avaliar criticamente essas práticas.""")
    ),
    ApologeticsQuestion(
        number = 48,
        section = LocalizedText("""La Iglesia, el Papa, la autoridad y la historia""", """The Church, the Pope, authority and history""", """A Igreja, o Papa, a autoridade e a história"""),
        question = LocalizedText("""¿Por qué la Iglesia se equivocó en el caso de Galileo?""", """Why did the Church err in the Galileo case?""", """Por que a Igreja errou no caso de Galileu?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia se equivocó en su juicio y en su interpretación, no en una definición dogmática. El conflicto muestra el riesgo de confundir una interpretación bíblica con una afirmación científica que debe investigarse con los métodos de la ciencia. Las Escrituras no fueron escritas como manuales de astronomía, y la doctrina católica sostiene que una verdad científicamente establecida y una verdad de fe correctamente comprendida no pueden contradecirse, porque la verdad es una (Catecismo, 159).

Piensa en un juez que prohíbe un mapa nuevo usando un libro de historia como si fuera un manual de física: el error no está en el libro de historia, sino en usarlo para un fin que no pretendía. La analogía ayuda a ver la confusión de campos, aunque no resume el caso histórico completo.

La objeción más fuerte dice: «Si las autoridades pudieron equivocarse tan gravemente interpretando la Biblia, ¿por qué confiar en otras interpretaciones?». Es una advertencia válida: las interpretaciones humanas pueden fallar. Tampoco sería riguroso afirmar que Galileo ya había demostrado concluyentemente todos los aspectos del heliocentrismo en el momento del proceso. La lección es doble: respetar los métodos científicos y no convertir interpretaciones humanas en verdades intocables.""", """The direct answer is that the Church erred in its judgment and interpretation, not in a dogmatic definition. The conflict shows the risk of confusing a biblical interpretation with a scientific affirmation that must be investigated with the methods of science. Scripture was not written as a manual of astronomy, and Catholic doctrine holds that a scientifically established truth and a correctly understood truth of faith cannot contradict each other, because truth is one (Catechism, 159).

Think of a judge who forbids a new map by using a history book as if it were a physics manual: the error is not in the history book, but in using it for a purpose it did not intend. The analogy helps to see the confusion of fields, although it does not summarize the complete historical case.

The strongest objection says: “If the authorities could err so gravely in interpreting the Bible, why trust other interpretations?” That is a valid warning: human interpretations can fail. Nor would it be rigorous to affirm that Galileo had already conclusively demonstrated all aspects of heliocentrism at the time of the trial. The lesson is twofold: respect scientific methods and do not turn human interpretations into untouchable truths.""", """A resposta direta é que a Igreja errou em seu julgamento e em sua interpretação, e não em uma definição dogmática. O conflito mostra o risco de confundir uma interpretação bíblica com uma afirmação científica que deve ser investigada com os métodos da ciência. As Escrituras não foram escritas como manuais de astronomia, e a doutrina católica sustenta que uma verdade cientificamente estabelecida e uma verdade de fé corretamente compreendida não podem contradizer-se, porque a verdade é uma (Catecismo, 159).

Pense em um juiz que proíbe um mapa novo usando um livro de história como se fosse um manual de física: o erro não está no livro de história, mas em usá-lo para um fim que ele não pretendia. A analogia ajuda a ver a confusão de campos, embora não resuma o caso histórico completo.

A objeção mais forte diz: “Se as autoridades puderam errar tão gravemente ao interpretar a Bíblia, por que confiar em outras interpretações?”. É um alerta válido: interpretações humanas podem falhar. Tampouco seria rigoroso afirmar que Galileu já havia demonstrado de modo conclusivo todos os aspectos do heliocentrismo no momento do processo. A lição é dupla: respeitar os métodos científicos e não converter interpretações humanas em verdades intocáveis."""),
        foundation = LocalizedText("""Salmo 19; Génesis 1; Catecismo, 159.""", """Psalm 19; Genesis 1; Catechism, 159.""", """Salmo 19; Gênesis 1; Catecismo, 159."""),
        clarification = LocalizedText("""Eso no significa que la Iglesia haya actuado correctamente en el proceso contra Galileo.

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
        answer = LocalizedText("""La respuesta directa es que la evangelización cristiana debe distinguirse de la conquista y de la imposición religiosa. El mandato de anunciar el Evangelio (Mateo 28,19-20) no autoriza a destruir culturas ni a utilizar la fuerza para obtener conversiones. En América coexistieron misioneros que defendieron a los pueblos indígenas, autoridades que toleraron injusticias y miembros de instituciones eclesiásticas que participaron en sistemas de dominación.

Piensa en Bartolomé de las Casas defendiendo a los indígenas ante la corona mientras otros encomenderos los explotaban: la misma época y el mismo cristianismo produjeron posturas opuestas. El ejemplo concreto rompe tanto la idea de que todos fueron opresores como la de que todos fueron defensores.

La objeción más fuerte dice: «Si instituciones eclesiásticas se beneficiaron de la encomienda y del patronato, distinguir evangelización y conquista puede ser una excusa». Es una sospecha fundamentada. Sería históricamente falso negar los abusos y las complicidades, y una respuesta católica seria debe incluir investigación histórica, reconocimiento del daño y respeto por la libertad de conciencia de los pueblos.""", """The direct answer is that Christian evangelization must be distinguished from conquest and from religious imposition. The mandate to proclaim the Gospel (Matthew 28:19-20) does not authorize destroying cultures or using force to obtain conversions. In America there coexisted missionaries who defended indigenous peoples, authorities who tolerated injustices, and members of ecclesiastical institutions who took part in systems of domination.

Think of Bartolomé de las Casas defending indigenous people before the crown while other encomenderos exploited them: the same era and the same Christianity produced opposing postures. The concrete example breaks both the idea that all were oppressors and the idea that all were defenders.

The strongest objection says: “If ecclesiastical institutions benefited from the encomienda and the patronato, distinguishing evangelization from conquest may be an excuse.” That is a well-founded suspicion. It would be historically false to deny abuses and complicities, and a serious Catholic response must include historical research, recognition of harm and respect for the freedom of conscience of peoples.""", """A resposta direta é que a evangelização cristã deve ser distinguida da conquista e da imposição religiosa. O mandato de anunciar o Evangelho (Mateus 28,19-20) não autoriza destruir culturas nem usar a força para obter conversões. Na América coexistiram missionários que defenderam os povos indígenas, autoridades que toleraram injustiças e membros de instituições eclesiásticas que participaram de sistemas de dominação.

Pense em Bartolomé de las Casas defendendo os indígenas diante da coroa enquanto outros encomenderos os exploravam: a mesma época e o mesmo cristianismo produziram posturas opostas. O exemplo concreto quebra tanto a ideia de que todos foram opressores quanto a de que todos foram defensores.

A objeção mais forte diz: “Se instituições eclesiásticas se beneficiaram da encomienda e do padroado, distinguir evangelização e conquista pode ser uma desculpa”. É uma suspeita fundamentada. Seria historicamente falso negar os abusos e as cumplicidades, e uma resposta católica séria deve incluir investigação histórica, reconhecimento do dano e respeito pela liberdade de consciência dos povos."""),
        foundation = LocalizedText("""Mateo 28,19-20; Lucas 4,18-19; Gálatas 3,28; Catecismo, 1934-1938.""", """Matthew 28:19-20; Luke 4:18-19; Galatians 3:28; Catechism, 1934-1938.""", """Mateus 28,19-20; Lucas 4,18-19; Gálatas 3,28; Catecismo, 1934-1938."""),
        clarification = LocalizedText("""Sería históricamente falso presentar a todos los misioneros como opresores o a toda la Iglesia como defensora de los indígenas.

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
        answer = LocalizedText("""La respuesta directa es que la credibilidad del cristianismo no puede fundamentarse en que todos sus representantes sean moralmente perfectos. Jesús mismo advirtió sobre los falsos pastores, los hipócritas y la posibilidad de corrupción religiosa (Mateo 7,15-23; Mateo 23). La doctrina católica distingue entre la santidad de Cristo, los medios de gracia confiados a la Iglesia y los pecados reales de sus miembros.

Piensa en un tesoro en vasos de barro (2 Corintios 4,7): el valor del tesoro no depende de que el recipiente sea perfecto, pero tampoco se puede negar que el recipiente puede romperse. La analogía ayuda a distinguir el don confiado de la fragilidad de quien lo transmite, sin convertir esa fragilidad en excusa.

La objeción más fuerte dice: «Esa distinción puede usarse como inmunidad moral para no rendir cuentas». Es correcto señalarlo: la distinción no otorga inmunidad a los dirigentes religiosos. Cuando hay corrupción, abuso de autoridad o injusticia, la credibilidad exige verdad, responsabilidad, reparación y reforma. Una Iglesia que predica la conversión debe comenzar por aplicarla a sí misma.""", """The direct answer is that the credibility of Christianity cannot be founded on all its representatives being morally perfect. Jesus Himself warned about false shepherds, hypocrites and the possibility of religious corruption (Matthew 7:15-23; Matthew 23). Catholic doctrine distinguishes between the holiness of Christ, the means of grace entrusted to the Church, and the real sins of its members.

Think of treasure in jars of clay (2 Corinthians 4:7): the value of the treasure does not depend on the vessel being perfect, but neither can it be denied that the vessel can break. The analogy helps to distinguish the entrusted gift from the fragility of the one who transmits it, without turning that fragility into an excuse.

The strongest objection says: “That distinction can be used as moral immunity to avoid accountability.” It is right to point this out: the distinction does not grant immunity to religious leaders. When there is corruption, abuse of authority or injustice, credibility demands truth, responsibility, reparation and reform. A Church that preaches conversion must begin by applying it to itself.""", """A resposta direta é que a credibilidade do cristianismo não pode fundamentar-se em que todos os seus representantes sejam moralmente perfeitos. O próprio Jesus advertiu sobre os falsos pastores, os hipócritas e a possibilidade de corrupção religiosa (Mateus 7,15-23; Mateus 23). A doutrina católica distingue entre a santidade de Cristo, os meios de graça confiados à Igreja e os pecados reais de seus membros.

Pense em um tesouro em vasos de barro (2 Coríntios 4,7): o valor do tesouro não depende de o recipiente ser perfeito, mas tampouco se pode negar que o recipiente pode quebrar-se. A analogia ajuda a distinguir o dom confiado da fragilidade de quem o transmite, sem converter essa fragilidade em desculpa.

A objeção mais forte diz: “Essa distinção pode ser usada como imunidade moral para não prestar contas”. É correto apontá-lo: a distinção não concede imunidade aos dirigentes religiosos. Quando há corrupção, abuso de autoridade ou injustiça, a credibilidade exige verdade, responsabilidade, reparação e reforma. Uma Igreja que prega a conversão deve começar por aplicá-la a si mesma."""),
        foundation = LocalizedText("""Mateo 7,15-23; Mateo 23; 2 Corintios 4,7; Juan 15,1-8; Catecismo, 823-827.""", """Matthew 7:15-23; Matthew 23; 2 Corinthians 4:7; John 15:1-8; Catechism, 823-827.""", """Mateus 7,15-23; Mateus 23; 2 Coríntios 4,7; João 15,1-8; Catecismo, 823-827."""),
        clarification = LocalizedText("""Esa distinción no otorga inmunidad moral o institucional a los dirigentes religiosos.

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
        answer = LocalizedText("""La respuesta directa es que los fundamentos principales son las palabras de Jesús en la Última Cena y su discurso sobre el Pan de Vida. En Juan 6,51-58 Jesús habla de dar su carne para la vida del mundo, en la institución eucarística identifica el pan con su cuerpo y el cáliz con su sangre (Mateo 26,26-28; Lucas 22,19-20), y San Pablo advierte que recibir indignamente la Eucaristía implica una responsabilidad respecto del Cuerpo y la Sangre del Señor (1 Corintios 11,27-29).

Piensa en cristianos de los primeros siglos que se reunían en secreto para partir el pan, arriesgando su vida: no actuaban como si se tratara de un recordatorio sin importancia. El ejemplo ayuda a ver el peso que la primera comunidad daba a esa comida, aunque no sustituye el análisis de los textos.

La objeción más fuerte dice: «Juan 6 habla de creer en Cristo, no de pan convertido». Algunos protestantes lo interpretan así, y otros aceptan formas de presencia real sin adoptar la transubstanciación. La defensa católica más fuerte utiliza el conjunto de los textos, la liturgia primitiva y el testimonio de los primeros escritores cristianos, no solamente un versículo aislado.""", """The direct answer is that the main foundations are Jesus' words at the Last Supper and His discourse on the Bread of Life. In John 6:51-58 Jesus speaks of giving His flesh for the life of the world, at the Eucharistic institution He identifies the bread with His body and the chalice with His blood (Matthew 26:26-28; Luke 22:19-20), and Saint Paul warns that receiving the Eucharist unworthily involves a responsibility regarding the Lord's Body and Blood (1 Corinthians 11:27-29).

Think of Christians of the first centuries who met in secret to break bread, risking their lives: they did not act as if it were an unimportant reminder. The example helps to see the weight the first community gave to that meal, although it does not replace the analysis of the texts.

The strongest objection says: “John 6 is about believing in Christ, not about transformed bread.” Some Protestants interpret it that way, and others accept forms of real presence without adopting transubstantiation. The strongest Catholic defense uses the whole of the texts, the primitive liturgy and the testimony of the first Christian writers, not just an isolated verse.""", """A resposta direta é que os fundamentos principais são as palavras de Jesus na Última Ceia e seu discurso sobre o Pão da Vida. Em João 6,51-58, Jesus fala de dar sua carne pela vida do mundo, na instituição eucarística identifica o pão com seu corpo e o cálice com seu sangue (Mateus 26,26-28; Lucas 22,19-20), e São Paulo adverte que receber indignamente a Eucaristia implica responsabilidade quanto ao Corpo e ao Sangue do Senhor (1 Coríntios 11,27-29).

Pense em cristãos dos primeiros séculos que se reuniam em segredo para partir o pão, arriscando a vida: não agiam como se se tratasse de uma lembrança sem importância. O exemplo ajuda a ver o peso que a primeira comunidade dava a essa refeição, embora não substitua a análise dos textos.

A objeção mais forte diz: “João 6 fala de crer em Cristo, não de pão transformado”. Alguns protestantes o interpretam assim, e outros aceitam formas de presença real sem adotar a transubstanciação. A defesa católica mais forte utiliza o conjunto dos textos, a liturgia primitiva e o testemunho dos primeiros escritores cristãos, e não apenas um versículo isolado."""),
        foundation = LocalizedText("""Juan 6,51-58; Mateo 26,26-28; Lucas 22,19-20; 1 Corintios 10,16; 1 Corintios 11,27-29; Catecismo, 1373-1381.""", """John 6:51-58; Matthew 26:26-28; Luke 22:19-20; 1 Corinthians 10:16; 1 Corinthians 11:27-29; Catechism, 1373-1381.""", """João 6,51-58; Mateus 26,26-28; Lucas 22,19-20; 1 Coríntios 10,16; 1 Coríntios 11,27-29; Catecismo, 1373-1381."""),
        clarification = LocalizedText("""Algunos protestantes interpretan Juan 6 principalmente como una enseñanza sobre creer en Cristo. Otros aceptan formas de presencia real sin adoptar la transubstanciación.

La defensa católica más fuerte utiliza el conjunto de los textos, la liturgia primitiva y el testimonio de los primeros escritores cristianos, no solamente un versículo aislado.""", """Some Protestants interpret John 6 mainly as teaching about believing in Christ. Others accept forms of real presence without adopting transubstantiation.

The strongest Catholic defense uses the whole of the texts, the primitive liturgy and the testimony of the first Christian writers, not just an isolated verse.""", """Alguns protestantes interpretam João 6 principalmente como ensino sobre crer em Cristo. Outros aceitam formas de presença real sem adotar a transubstanciação.

A defesa católica mais forte utiliza o conjunto dos textos, a liturgia primitiva e o testemunho dos primeiros escritores cristãos, e não apenas um versículo isolado.""")
    ),
    ApologeticsQuestion(
        number = 52,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""Si Jesús murió una sola vez, ¿por qué la Misa es un sacrificio?""", """If Jesus died only once, why is Mass a sacrifice?""", """Se Jesus morreu uma só vez, por que a Missa é um sacrifício?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia no enseña que Jesús vuelva a morir en cada Misa. El sacrificio de Cristo ocurrió históricamente una sola vez y posee eficacia definitiva (Hebreos 7,27; 9,24-28; 10,10-14). La Eucaristía hace sacramentalmente presente ese mismo sacrificio, sin repetir la crucifixión: en la tradición bíblica, un memorial litúrgico no es solamente recordar mentalmente el pasado, sino actualizar ritualmente su significado para la comunidad (Lucas 22,19-20).

Piensa en una pareja que celebra cada aniversario renovando sus votos: no se casan de nuevo, pero actualizan el compromiso original. La analogía ayuda a distinguir repetición y actualización, aunque no demuestra por sí sola la presencia sacramental.

La objeción más fuerte dice: «Hebreos enseña que Cristo se ofreció una sola vez; hablar de sacrificio en la Misa sugiere otro sacrificio». Es una advertencia que toda explicación católica debe respetar: no hay muerte repetida ni sacrificio nuevo. La afirmación de la presencia sacramental pertenece a la interpretación teológica católica y debe preservar la verdad del «una sola vez».""", """The direct answer is that the Church does not teach that Jesus dies again at each Mass. Christ's sacrifice occurred historically only once and possesses definitive efficacy (Hebrews 7:27; 9:24-28; 10:10-14). The Eucharist makes that same sacrifice sacramentally present, without repeating the crucifixion: in biblical tradition, a liturgical memorial is not merely mentally remembering the past, but ritually actualizing its meaning for the community (Luke 22:19-20).

Think of a couple celebrating each anniversary by renewing their vows: they are not married again, but they actualize the original commitment. The analogy helps to distinguish repetition from actualization, although it does not by itself demonstrate sacramental presence.

The strongest objection says: “Hebrews teaches that Christ offered Himself only once; speaking of sacrifice at Mass suggests another sacrifice.” That is a warning every Catholic explanation must respect: there is no repeated death or new sacrifice. The affirmation of sacramental presence belongs to Catholic theological interpretation and must preserve the truth of “once for all.”""", """A resposta direta é que a Igreja não ensina que Jesus morra de novo em cada Missa. O sacrifício de Cristo ocorreu historicamente uma só vez e possui eficácia definitiva (Hebreus 7,27; 9,24-28; 10,10-14). A Eucaristia torna sacramentalmente presente esse mesmo sacrifício, sem repetir a crucifixão: na tradição bíblica, um memorial litúrgico não é apenas lembrar mentalmente o passado, mas atualizar ritualmente seu significado para a comunidade (Lucas 22,19-20).

Pense em um casal que celebra cada aniversário renovando seus votos: não se casam de novo, mas atualizam o compromisso original. A analogia ajuda a distinguir repetição e atualização, embora não demonstre por si só a presença sacramental.

A objeção mais forte diz: “Hebreus ensina que Cristo se ofereceu uma só vez; falar de sacrifício na Missa sugere outro sacrifício”. É um alerta que toda explicação católica deve respeitar: não há morte repetida nem sacrifício novo. A afirmação da presença sacramental pertence à interpretação teológica católica e deve preservar a verdade do “uma só vez”."""),
        foundation = LocalizedText("""Hebreos 7,27; Hebreos 9,24-28; Hebreos 10,10-14; Lucas 22,19-20; 1 Corintios 11,26; Catecismo, 1362-1367.""", """Hebrews 7:27; Hebrews 9:24-28; Hebrews 10:10-14; Luke 22:19-20; 1 Corinthians 11:26; Catechism, 1362-1367.""", """Hebreus 7,27; Hebreus 9,24-28; Hebreus 10,10-14; Lucas 22,19-20; 1 Coríntios 11,26; Catecismo, 1362-1367."""),
        clarification = LocalizedText("""La afirmación de que la Misa hace presente sacramentalmente el sacrificio de Cristo pertenece a la interpretación teológica católica.

Hebreos enseña inequívocamente que Cristo se ofreció una sola vez. Cualquier explicación católica debe preservar esa verdad y evitar sugerir un sacrificio nuevo o una muerte repetida.""", """The affirmation that the Mass makes Christ's sacrifice sacramentally present belongs to Catholic theological interpretation.

Hebrews unequivocally teaches that Christ offered Himself only once. Any Catholic explanation must preserve that truth and avoid suggesting a new sacrifice or a repeated death.""", """A afirmação de que a Missa torna presente sacramentalmente o sacrifício de Cristo pertence à interpretação teológica católica.

Hebreus ensina inequivocamente que Cristo se ofereceu uma só vez. Qualquer explicação católica deve preservar essa verdade e evitar sugerir um sacrifício novo ou uma morte repetida.""")
    ),
    ApologeticsQuestion(
        number = 53,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Cómo puede el pan convertirse en Cristo si sigue pareciendo pan?""", """How can bread become Christ if it still looks like bread?""", """Como pode o pão se converter em Cristo se continua parecendo pão?"""),
        answer = LocalizedText("""La respuesta directa es que la doctrina de la transubstanciación distingue entre la realidad sustancial de lo que algo es y sus propiedades sensibles. La Iglesia enseña que, mediante la consagración, cambia la sustancia del pan y del vino, mientras permanecen sus apariencias (Catecismo, 1376). La explicación utiliza categorías filosóficas para expresar la fe en la presencia real de Cristo, no para describir un cambio químico.

Piensa en un embajador: exteriormente es un hombre como cualquier otro, pero jurídicamente representa a su país y su firma compromete a un Estado. La analogía ayuda a distinguir niveles —lo que se ve y lo que algo es en un orden distinto—, aunque no demuestra el misterio eucarístico.

La objeción más fuerte dice: «Si nada cambia bajo el microscopio, no hay conversión verificable». Es correcto: la transubstanciación no es una transformación química y no debe presentarse como una conclusión científica demostrada. Es una explicación metafísica de una afirmación sacramental recibida por la fe.""", """The direct answer is that the doctrine of transubstantiation distinguishes between the substantial reality of what something is and its sensible properties. The Church teaches that, by the consecration, the substance of the bread and wine changes while their appearances remain (Catechism, 1376). The explanation uses philosophical categories to express faith in Christ's real presence, not to describe a chemical change.

Think of an ambassador: externally he is a man like any other, but legally he represents his country and his signature binds a state. The analogy helps to distinguish levels — what is seen and what something is in a different order — although it does not demonstrate the Eucharistic mystery.

The strongest objection says: “If nothing changes under a microscope, there is no verifiable conversion.” That is correct: transubstantiation is not a chemical transformation and must not be presented as a demonstrated scientific conclusion. It is a metaphysical explanation of a sacramental affirmation received by faith.""", """A resposta direta é que a doutrina da transubstanciação distingue entre a realidade substancial daquilo que algo é e suas propriedades sensíveis. A Igreja ensina que, mediante a consagração, muda a substância do pão e do vinho, enquanto permanecem suas aparências (Catecismo, 1376). A explicação utiliza categorias filosóficas para exprimir a fé na presença real de Cristo, e não para descrever uma mudança química.

Pense em um embaixador: exteriormente é um homem como qualquer outro, mas juridicamente representa seu país e sua assinatura compromete um Estado. A analogia ajuda a distinguir níveis — o que se vê e o que algo é em uma ordem distinta —, embora não demonstre o mistério eucarístico.

A objeção mais forte diz: “Se nada muda sob o microscópio, não há conversão verificável”. É correto: a transubstanciação não é uma transformação química e não deve ser apresentada como uma conclusão científica demonstrada. É uma explicação metafísica de uma afirmação sacramental recebida pela fé."""),
        foundation = LocalizedText("""Mateo 26,26-28; Juan 6,53-56; 1 Corintios 11,23-29; Catecismo, 1376 y 1413.""", """Matthew 26:26-28; John 6:53-56; 1 Corinthians 11:23-29; Catechism, 1376 and 1413.""", """Mateus 26,26-28; João 6,53-56; 1 Coríntios 11,23-29; Catecismo, 1376 e 1413."""),
        clarification = LocalizedText("""La transubstanciación no es una transformación química.

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
        answer = LocalizedText("""La respuesta directa es que Dios es quien perdona los pecados; el sacerdote no posee un poder de perdón independiente de Dios. Después de la resurrección, Jesús comunica a sus apóstoles el Espíritu Santo y les confía una misión relacionada explícitamente con perdonar y retener pecados (Juan 20,21-23). La explicación católica es que Cristo quiso que su perdón también llegara por medio de un signo sacramental y comunitario.

Piensa en un embajador que otorga visas en nombre de su gobierno: el visado es real, pero su autoridad viene de quien lo envió, no de su poder personal. La analogía ayuda a ver un perdón mediado sin convertir al ministro en dueño del perdón, aunque no demuestra por sí sola el alcance sacramental de Juan 20.

La objeción más fuerte dice: «Si Dios perdona directamente, el sacerdote sobra». La Iglesia responde que Dios no es incapaz de perdonar fuera del confesionario: la contrición perfecta puede obtener el perdón de los pecados graves si incluye el propósito de confesarse cuando sea posible (Catecismo, 1452). La controversia se centra en el alcance ministerial de Juan 20, no en si Dios puede perdonar.""", """The direct answer is that God is the one who forgives sins; the priest does not possess a power of forgiveness independent of God. After the resurrection, Jesus communicates the Holy Spirit to His apostles and entrusts them with a mission explicitly related to forgiving and retaining sins (John 20:21-23). The Catholic explanation is that Christ willed His forgiveness to come also through a sacramental and communal sign.

Think of an ambassador who issues visas on behalf of his government: the visa is real, but his authority comes from the one who sent him, not from his personal power. The analogy helps to see a mediated forgiveness without making the minister the owner of forgiveness, although it does not by itself demonstrate the sacramental scope of John 20.

The strongest objection says: “If God forgives directly, the priest is unnecessary.” The Church answers that God is not unable to forgive outside the confessional: perfect contrition can obtain forgiveness of grave sins if it includes the purpose of going to confession when possible (Catechism, 1452). The controversy centers on the ministerial scope of John 20, not on whether God can forgive.""", """A resposta direta é que Deus é quem perdoa os pecados; o sacerdote não possui um poder de perdão independente de Deus. Depois da ressurreição, Jesus comunica aos seus apóstolos o Espírito Santo e lhes confia uma missão relacionada explicitamente com perdoar e reter pecados (João 20,21-23). A explicação católica é que Cristo quis que seu perdão também chegasse por meio de um sinal sacramental e comunitário.

Pense em um embaixador que concede vistos em nome de seu governo: o visto é real, mas sua autoridade vem de quem o enviou, e não de seu poder pessoal. A analogia ajuda a ver um perdão mediado sem converter o ministro em dono do perdão, embora não demonstre por si só o alcance sacramental de João 20.

A objeção mais forte diz: “Se Deus perdoa diretamente, o sacerdote é desnecessário”. A Igreja responde que Deus não é incapaz de perdoar fora do confessionário: a contrição perfeita pode obter o perdão dos pecados graves se incluir o propósito de confessar-se quando for possível (Catecismo, 1452). A controvérsia centra-se no alcance ministerial de João 20, não em saber se Deus pode perdoar."""),
        foundation = LocalizedText("""Juan 20,21-23; Mateo 16,19; Mateo 18,18; Santiago 5,14-16; 2 Corintios 5,18-20; Catecismo, 1422-1498.""", """John 20:21-23; Matthew 16:19; Matthew 18:18; James 5:14-16; 2 Corinthians 5:18-20; Catechism, 1422-1498.""", """João 20,21-23; Mateus 16,19; Mateus 18,18; Tiago 5,14-16; 2 Coríntios 5,18-20; Catecismo, 1422-1498."""),
        clarification = LocalizedText("""La Iglesia no enseña que Dios sea incapaz de perdonar fuera del confesionario.

El Catecismo, 1452, reconoce que la contrición perfecta puede obtener el perdón de los pecados graves si incluye el propósito de acudir a la confesión cuando sea posible.

La controversia con los protestantes se centra en el alcance sacramental y ministerial de Juan 20, no en si Dios puede perdonar directamente.""", """The Church does not teach that God is unable to forgive outside the confessional.

The Catechism, 1452, recognizes that perfect contrition can obtain forgiveness of grave sins if it includes the purpose of going to confession when possible.

The controversy with Protestants centers on the sacramental and ministerial scope of John 20, not on whether God can forgive directly.""", """A Igreja não ensina que Deus seja incapaz de perdoar fora do confessionário.

O Catecismo, 1452, reconhece que a contrição perfeita pode obter o perdão dos pecados graves se incluir o propósito de acudir à confissão quando for possível.

A controvérsia com os protestantes centra-se no alcance sacramental e ministerial de João 20, não em saber se Deus pode perdoar diretamente.""")
    ),
    ApologeticsQuestion(
        number = 55,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Puede un sacerdote pecador administrar sacramentos válidos?""", """Can a sinful priest administer valid sacraments?""", """Um sacerdote pecador pode administrar sacramentos válidos?"""),
        answer = LocalizedText("""La respuesta directa es sí. La eficacia fundamental del sacramento procede de Cristo, no de la perfección moral del ministro. San Pablo distingue entre el valor del Evangelio y la fragilidad de quienes lo anuncian (1 Corintios 3,5-7; 2 Corintios 4,7), y la tradición católica rechaza la idea de que el estado moral del sacerdote determine automáticamente la validez del sacramento.

Piensa en un cartero que entrega una carta importante: el valor de la carta viene de quien la escribió, no de la vida personal del cartero. La analogía ayuda a distinguir el mensaje de su mensajero, aunque no sustituye las condiciones concretas de cada sacramento.

La objeción más fuerte dice: «Si un sacerdote abusador puede bautizar o consagrar válidamente, el sistema parece proteger al ministro». Es una preocupación legítima: que un sacramento pueda ser válido no significa que el comportamiento del ministro sea aceptable. La validez también requiere ministro apto, materia, forma e intención correspondientes, y un sacerdote que comete delitos o abusos debe responder por ellos; su ministerio puede ser restringido o retirado conforme al derecho.""", """The direct answer is yes. The fundamental efficacy of the sacrament proceeds from Christ, not from the moral perfection of the minister. Saint Paul distinguishes between the value of the Gospel and the fragility of those who proclaim it (1 Corinthians 3:5-7; 2 Corinthians 4:7), and Catholic tradition rejects the idea that the priest's moral state automatically determines the validity of the sacrament.

Think of a postman delivering an important letter: the letter's value comes from who wrote it, not from the postman's personal life. The analogy helps to distinguish the message from its messenger, although it does not replace the concrete conditions of each sacrament.

The strongest objection says: “If an abusive priest can validly baptize or consecrate, the system seems to protect the minister.” That is a legitimate concern: that a sacrament may be valid does not mean that the minister's behavior is acceptable. Validity also requires a suitable minister, matter, form and corresponding intention, and a priest who commits crimes or abuses must answer for them; his ministry may be restricted or withdrawn according to the law.""", """A resposta direta é sim. A eficácia fundamental do sacramento procede de Cristo, e não da perfeição moral do ministro. São Paulo distingue entre o valor do Evangelho e a fragilidade de quem o anuncia (1 Coríntios 3,5-7; 2 Coríntios 4,7), e a tradição católica rejeita a ideia de que o estado moral do sacerdote determine automaticamente a validade do sacramento.

Pense em um carteiro que entrega uma carta importante: o valor da carta vem de quem a escreveu, e não da vida pessoal do carteiro. A analogia ajuda a distinguir a mensagem de seu mensageiro, embora não substitua as condições concretas de cada sacramento.

A objeção mais forte diz: “Se um sacerdote abusador pode batizar ou consagrar validamente, o sistema parece proteger o ministro”. É uma preocupação legítima: que um sacramento possa ser válido não significa que o comportamento do ministro seja aceitável. A validade também requer ministro apto, matéria, forma e intenção correspondentes, e um sacerdote que comete crimes ou abusos deve responder por eles; seu ministério pode ser restringido ou retirado conforme o direito."""),
        foundation = LocalizedText("""2 Corintios 4,7; 1 Corintios 3,5-7; Juan 20,21-23; Catecismo, 1127-1128 y 1584.""", """2 Corinthians 4:7; 1 Corinthians 3:5-7; John 20:21-23; Catechism, 1127-1128 and 1584.""", """2 Coríntios 4,7; 1 Coríntios 3,5-7; João 20,21-23; Catecismo, 1127-1128 e 1584."""),
        clarification = LocalizedText("""La validez también requiere condiciones sacramentales concretas, como ministro apto, materia, forma e intención correspondientes.

Además, que un sacramento pueda ser válido no significa que el comportamiento del ministro sea aceptable.

Un sacerdote que comete delitos o abusos debe responder por ellos, y su ministerio puede ser restringido o retirado conforme al derecho aplicable.""", """Validity also requires concrete sacramental conditions, such as a suitable minister, matter, form and corresponding intention.

Moreover, that a sacrament may be valid does not mean that the minister's behavior is acceptable.

A priest who commits crimes or abuses must answer for them, and his ministry may be restricted or withdrawn according to applicable law.""", """A validade também requer condições sacramentais concretas, como ministro apto, matéria, forma e intenção correspondentes.

Além disso, que um sacramento possa ser válido não significa que o comportamento do ministro seja aceitável.

Um sacerdote que comete crimes ou abusos deve responder por eles, e seu ministério pode ser restringido ou retirado conforme o direito aplicável.""")
    ),
    ApologeticsQuestion(
        number = 56,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué bautizar a niños que todavía no pueden decidir?""", """Why baptize children who cannot yet decide?""", """Por que batizar crianças que ainda não podem decidir?"""),
        answer = LocalizedText("""La respuesta directa es que la interpretación católica comprende el bautismo primero como un don de la gracia divina, no simplemente como una declaración pública de una decisión individual. En el Nuevo Testamento se relaciona con el nuevo nacimiento y la incorporación a Cristo (Juan 3,5; Colosenses 2,11-12), y la Iglesia entiende que la fe de la comunidad acompaña al niño hasta que este pueda asumir personalmente su vida cristiana.

Piensa en un bebé inscrito en el registro civil por sus padres: no elige su nacionalidad ni su nombre, pero entra en una familia y en una historia que lo preceden. La analogía ayuda a entender un don recibido antes de la decisión, aunque no demuestra por qué el bautismo debería funcionar igual.

La objeción más fuerte dice: «La Biblia no ordena explícitamente bautizar recién nacidos». Es correcto: los relatos de bautismos de familias (Hechos 16,15.33) son compatibles con el bautismo infantil, pero no demuestran por sí solos que hubiera bebés presentes. Por eso el argumento católico incluye también la Tradición y una determinada comprensión del sacramento, y al crecer el niño su libertad y su conciencia deben ser respetadas.""", """The direct answer is that the Catholic interpretation understands baptism first as a gift of divine grace, not simply as a public declaration of an individual decision. In the New Testament it is linked to new birth and incorporation into Christ (John 3:5; Colossians 2:11-12), and the Church understands that the community's faith accompanies the child until he can personally assume his Christian life.

Think of a baby registered in the civil registry by his parents: he does not choose his nationality or his name, but he enters a family and a history that precede him. The analogy helps to understand a gift received before decision, although it does not demonstrate why baptism should work the same way.

The strongest objection says: “The Bible does not explicitly command baptizing newborns.” That is correct: the accounts of household baptisms (Acts 16:15,33) are compatible with infant baptism, but they do not by themselves prove that babies were present. That is why the Catholic argument also includes Tradition and a certain understanding of the sacrament, and as the child grows his freedom and conscience must be respected.""", """A resposta direta é que a interpretação católica compreende o batismo primeiro como dom da graça divina, e não simplesmente como declaração pública de uma decisão individual. No Novo Testamento, ele é relacionado com o novo nascimento e a incorporação a Cristo (João 3,5; Colossenses 2,11-12), e a Igreja entende que a fé da comunidade acompanha a criança até que esta possa assumir pessoalmente sua vida cristã.

Pense em um bebê inscrito no registro civil por seus pais: ele não escolhe sua nacionalidade nem seu nome, mas entra em uma família e em uma história que o precedem. A analogia ajuda a compreender um dom recebido antes da decisão, embora não demonstre por que o batismo deveria funcionar da mesma forma.

A objeção mais forte diz: “A Bíblia não ordena explicitamente batizar recém-nascidos”. É correto: os relatos de batismos de famílias (Atos 16,15.33) são compatíveis com o batismo infantil, mas não demonstram por si sós que houvesse bebês presentes. Por isso o argumento católico inclui também a Tradição e uma determinada compreensão do sacramento, e, ao crescer a criança, sua liberdade e sua consciência devem ser respeitadas."""),
        foundation = LocalizedText("""Juan 3,5; Hechos 2,38-39; Hechos 16,15.33; Colosenses 2,11-12; Catecismo, 1250-1252.""", """John 3:5; Acts 2:38-39; Acts 16:15,33; Colossians 2:11-12; Catechism, 1250-1252.""", """João 3,5; Atos 2,38-39; Atos 16,15.33; Colossenses 2,11-12; Catecismo, 1250-1252."""),
        clarification = LocalizedText("""La Biblia no contiene una orden explícita formulada como «bautizad a los recién nacidos».

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
        answer = LocalizedText("""La respuesta directa es que la Iglesia no define con certeza el destino individual de esos niños, sino que los confía a la misericordia de Dios y expresa esperanza en su salvación (Catecismo, 1261). El bautismo es el medio ordinario de incorporación sacramental a Cristo, pero Dios no está limitado por sus sacramentos. Jesús manifiesta un amor especial por los pequeños y afirma que el Reino pertenece a quienes son como ellos (Marcos 10,13-16).

Piensa en unos padres que entierran a su bebé y rezan por él: la Iglesia acompaña ese duelo con esperanza, sin presentar una explicación particular como conocimiento definitivo. El ejemplo ayuda a ver la actitud pastoral, aunque no resuelve teológicamente cada caso.

La objeción más fuerte dice: «Si Dios quiere que todos se salven (1 Timoteo 2,4) y el bautismo es el medio ordinario, ¿no están condenados?». La Iglesia responde que no: la antigua hipótesis teológica del limbo no es un dogma, y sería incorrecto enseñar que esos niños están condenados, del mismo modo que sería incorrecto presentar una explicación particular de su destino como conocimiento definitivo.""", """The direct answer is that the Church does not definitively define the individual destiny of those children, but entrusts them to God's mercy and expresses hope for their salvation (Catechism, 1261). Baptism is the ordinary means of sacramental incorporation into Christ, but God is not limited by His sacraments. Jesus shows a special love for little children and affirms that the Kingdom belongs to those who are like them (Mark 10:13-16).

Think of parents who bury their baby and pray for him: the Church accompanies that grief with hope, without presenting a particular explanation as definitive knowledge. The example helps to see the pastoral attitude, although it does not theologically resolve every case.

The strongest objection says: “If God wants all to be saved (1 Timothy 2:4) and baptism is the ordinary means, are they not condemned?” The Church answers no: the old theological hypothesis of limbo is not a dogma, and it would be incorrect to teach that these children are condemned, just as it would be incorrect to present a particular explanation of their destiny as definitive knowledge.""", """A resposta direta é que a Igreja não define com certeza o destino individual dessas crianças, mas as confia à misericórdia de Deus e expressa esperança em sua salvação (Catecismo, 1261). O batismo é o meio ordinário de incorporação sacramental a Cristo, mas Deus não está limitado por seus sacramentos. Jesus manifesta um amor especial pelos pequenos e afirma que o Reino pertence aos que são como eles (Marcos 10,13-16).

Pense em pais que enterram seu bebê e rezam por ele: a Igreja acompanha esse luto com esperança, sem apresentar uma explicação particular como conhecimento definitivo. O exemplo ajuda a ver a atitude pastoral, embora não resolva teologicamente cada caso.

A objeção mais forte diz: “Se Deus quer que todos se salvem (1 Timóteo 2,4) e o batismo é o meio ordinário, eles não estão condenados?”. A Igreja responde que não: a antiga hipótese teológica do limbo não é dogma, e seria incorreto ensinar que essas crianças estão condenadas, do mesmo modo que seria incorreto apresentar uma explicação particular de seu destino como conhecimento definitivo."""),
        foundation = LocalizedText("""Marcos 10,13-16; Mateo 18,10-14; 1 Timoteo 2,4; Catecismo, 1261.""", """Mark 10:13-16; Matthew 18:10-14; 1 Timothy 2:4; Catechism, 1261.""", """Marcos 10,13-16; Mateus 18,10-14; 1 Timóteo 2,4; Catecismo, 1261."""),
        clarification = LocalizedText("""La Iglesia no ha revelado ni definido con certeza el destino individual de todos los niños muertos sin bautismo.

La antigua hipótesis teológica del limbo no es un dogma.

Sería incorrecto enseñar que esos niños están condenados, del mismo modo que sería incorrecto presentar una explicación particular de su destino como conocimiento definitivo.""", """The Church has not revealed or definitively defined the individual destiny of all unbaptized children who die.

The old theological hypothesis of limbo is not a dogma.

It would be incorrect to teach that these children are condemned, just as it would be incorrect to present a particular explanation of their destiny as definitive knowledge.""", """A Igreja não revelou nem definiu com certeza o destino individual de todas as crianças mortas sem batismo.

A antiga hipótese teológica do limbo não é dogma.

Seria incorreto ensinar que essas crianças estão condenadas, do mesmo modo que seria incorreto apresentar uma explicação particular de seu destino como conhecimento definitivo.""")
    ),
    ApologeticsQuestion(
        number = 58,
        section = LocalizedText("""La Eucaristía, la confesión y los sacramentos""", """The Eucharist, confession and the sacraments""", """A Eucaristia, a confissão e os sacramentos"""),
        question = LocalizedText("""¿Por qué faltar a Misa el domingo puede ser pecado grave?""", """Why can missing Mass on Sunday be a grave sin?""", """Por que faltar à Missa no domingo pode ser pecado grave?"""),
        answer = LocalizedText("""La respuesta directa es que para la Iglesia la Eucaristía no es simplemente una actividad religiosa opcional, sino el centro de la vida cristiana y de la comunión eclesial. Los primeros cristianos se reunían para partir el pan, especialmente el día del Señor (Hechos 2,42; 20,7). La obligación dominical expresa la importancia del culto comunitario, no la idea de que Dios necesite comprobar nuestra asistencia.

Piensa en una familia que se reúne cada domingo: quien falta sin motivo no solo pierde una comida, rompe un vínculo que se alimenta de la presencia repetida. La analogía ayuda a entender la obligación como cuidado de la comunión, no como control de asistencia.

La objeción más fuerte dice: «Convertir la ausencia en pecado grave parece legalista». La Iglesia responde que no cualquier ausencia constituye automáticamente pecado mortal: se requiere materia grave, pleno conocimiento y consentimiento deliberado. También existen causas justificadas, como enfermedad, atención necesaria a otras personas o imposibilidad real. La vida cristiana no puede reducirse a registrar asistencias.""", """The direct answer is that for the Church the Eucharist is not simply an optional religious activity, but the center of Christian life and ecclesial communion. The first Christians met to break bread, especially on the Lord's Day (Acts 2:42; 20:7). The Sunday obligation expresses the importance of communal worship, not the idea that God needs to check our attendance.

Think of a family that meets every Sunday: whoever is absent without reason does not merely miss a meal, he breaks a bond that is nourished by repeated presence. The analogy helps to understand the obligation as care for communion, not as attendance control.

The strongest objection says: “Turning absence into grave sin seems legalistic.” The Church answers that not every absence automatically constitutes mortal sin: it requires grave matter, full knowledge and deliberate consent. There are also justified causes, such as illness, necessary attention to other people, or real impossibility. Christian life cannot be reduced to recording attendance.""", """A resposta direta é que para a Igreja a Eucaristia não é simplesmente uma atividade religiosa opcional, mas o centro da vida cristã e da comunhão eclesial. Os primeiros cristãos se reuniam para partir o pão, especialmente no Dia do Senhor (Atos 2,42; 20,7). A obrigação dominical expressa a importância do culto comunitário, e não a ideia de que Deus precise comprovar nossa presença.

Pense em uma família que se reúne a cada domingo: quem falta sem motivo não apenas perde uma refeição, quebra um vínculo que se alimenta da presença repetida. A analogia ajuda a compreender a obrigação como cuidado da comunhão, e não como controle de presença.

A objeção mais forte diz: “Converter a ausência em pecado grave parece legalista”. A Igreja responde que nem toda ausência constitui automaticamente pecado mortal: requer-se matéria grave, pleno conhecimento e consentimento deliberado. Também existem causas justificadas, como doença, atendimento necessário a outras pessoas ou impossibilidade real. A vida cristã não pode ser reduzida a registrar presenças."""),
        foundation = LocalizedText("""Hechos 2,42; Hechos 20,7; Hebreos 10,24-25; 1 Corintios 11,23-26; Catecismo, 2177-2183.""", """Acts 2:42; Acts 20:7; Hebrews 10:24-25; 1 Corinthians 11:23-26; Catechism, 2177-2183.""", """Atos 2,42; Atos 20,7; Hebreus 10,24-25; 1 Coríntios 11,23-26; Catecismo, 2177-2183."""),
        clarification = LocalizedText("""No cualquier ausencia constituye automáticamente pecado mortal.

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
        answer = LocalizedText("""La respuesta directa es que la Comunión eucarística expresa simultáneamente la unión con Cristo y la comunión en la fe y los sacramentos de la Iglesia. San Pablo advierte sobre la necesidad de discernir el Cuerpo del Señor antes de comulgar (1 Corintios 11,27-29), y la disciplina católica procura proteger la integridad del sacramento y el significado público de recibirlo.

Piensa en una ceremonia de graduación: recibir el diploma expresa públicamente que se completaron los estudios. La analogía ayuda a entender que comulgar es también un signo público, aunque no reduce la Eucaristía a un examen académico ni a un premio por méritos.

La objeción más fuerte dice: «Si Jesús acogía a pecadores y excluidos, ¿por qué la Iglesia pone condiciones?». Es una tensión que debe explicarse con respeto y aplicarse conforme al derecho de la Iglesia. Existen excepciones y situaciones particulares para cristianos no católicos, reguladas por la disciplina sacramental, y una persona que no comulga no está excluida de la oración, de escuchar la Palabra o de buscar a Dios. Tampoco corresponde juzgar públicamente el estado interior de cada fiel.""", """The direct answer is that Eucharistic Communion simultaneously expresses union with Christ and communion in the Church's faith and sacraments. Saint Paul warns about the need to discern the Lord's Body before communing (1 Corinthians 11:27-29), and Catholic discipline seeks to protect the integrity of the sacrament and the public meaning of receiving it.

Think of a graduation ceremony: receiving the diploma publicly expresses that the studies were completed. The analogy helps to understand that communing is also a public sign, although it does not reduce the Eucharist to an academic exam or a prize for merits.

The strongest objection says: “If Jesus welcomed sinners and the excluded, why does the Church impose conditions?” That is a tension that must be explained with respect and applied according to the Church's law. There are exceptions and particular situations for non-Catholic Christians, regulated by sacramental discipline, and a person who does not commune is not excluded from prayer, listening to the Word, or seeking God. Nor is it for us to publicly judge the interior state of each believer.""", """A resposta direta é que a Comunhão eucarística expressa simultaneamente a união com Cristo e a comunhão na fé e nos sacramentos da Igreja. São Paulo adverte sobre a necessidade de discernir o Corpo do Senhor antes de comungar (1 Coríntios 11,27-29), e a disciplina católica procura proteger a integridade do sacramento e o significado público de recebê-lo.

Pense em uma cerimônia de formatura: receber o diploma expressa publicamente que os estudos foram concluídos. A analogia ajuda a compreender que comungar é também um sinal público, embora não reduza a Eucaristia a um exame acadêmico nem a um prêmio por méritos.

A objeção mais forte diz: “Se Jesus acolhia pecadores e excluídos, por que a Igreja impõe condições?”. É uma tensão que deve ser explicada com respeito e aplicada conforme o direito da Igreja. Existem exceções e situações particulares para cristãos não católicos, reguladas pela disciplina sacramental, e uma pessoa que não comunga não está excluída da oração, de escutar a Palavra ou de buscar a Deus. Tampouco corresponde julgar publicamente o estado interior de cada fiel."""),
        foundation = LocalizedText("""1 Corintios 10,16-17; 1 Corintios 11,27-29; Mateo 5,23-24; Catecismo, 1385 y 1395-1396.""", """1 Corinthians 10:16-17; 1 Corinthians 11:27-29; Matthew 5:23-24; Catechism, 1385 and 1395-1396.""", """1 Coríntios 10,16-17; 1 Coríntios 11,27-29; Mateus 5,23-24; Catecismo, 1385 e 1395-1396."""),
        clarification = LocalizedText("""Las condiciones de acceso deben explicarse con respeto y aplicarse conforme al derecho de la Iglesia.

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
        answer = LocalizedText("""La respuesta directa es que la tradición católica admite signos materiales que ayudan a expresar la fe, recordar compromisos y pedir la bendición de Dios. La Biblia utiliza el agua, el aceite, la imposición de manos y otros elementos materiales en contextos religiosos. Estos signos son llamados sacramentales: no producen automáticamente la gracia del mismo modo que los sacramentos (Catecismo, 1667-1679).

Piensa en un anillo de compromiso o en una foto en la cartera: no protege por magia, pero recuerda una promesa y una relación que importan. La analogía ayuda a distinguir un signo que recuerda de un amuleto que supuestamente actúa solo, aunque no elimina el riesgo de superstición.

La objeción más fuerte dice: «En la práctica mucha gente los usa como amuletos». Es cierto que puede ocurrir, y la propia Iglesia lo condena: una medalla no garantiza protección física, el agua bendita no es una sustancia mágica y portar un escapulario no sustituye la conversión, la caridad ni la confianza en Dios. Si alguien atribuye a un objeto una eficacia automática desligada de Dios y de la fe, puede caer en superstición.""", """The direct answer is that Catholic tradition admits material signs that help express faith, recall commitments and ask God's blessing. The Bible uses water, oil, the laying on of hands and other material elements in religious contexts. These signs are called sacramentals: they do not automatically produce grace in the same way as the sacraments (Catechism, 1667-1679).

Think of an engagement ring or a photo in a wallet: it does not protect by magic, but it recalls a promise and a relationship that matter. The analogy helps to distinguish a sign that recalls from an amulet that supposedly acts alone, although it does not eliminate the risk of superstition.

The strongest objection says: “In practice many people use them as amulets.” That is true, and the Church itself condemns it: a medal does not guarantee physical protection, holy water is not a magical substance, and carrying a scapular does not replace conversion, charity or trust in God. If someone attributes to an object an automatic efficacy detached from God and from faith, they may fall into superstition.""", """A resposta direta é que a tradição católica admite sinais materiais que ajudam a exprimir a fé, lembrar compromissos e pedir a bênção de Deus. A Bíblia utiliza a água, o óleo, a imposição de mãos e outros elementos materiais em contextos religiosos. Esses sinais são chamados sacramentais: não produzem automaticamente a graça do mesmo modo que os sacramentos (Catecismo, 1667-1679).

Pense em um anel de compromisso ou em uma foto na carteira: não protege por magia, mas lembra uma promessa e uma relação que importam. A analogia ajuda a distinguir um sinal que lembra de um amuleto que supostamente atua sozinho, embora não elimine o risco de superstição.

A objeção mais forte diz: “Na prática, muita gente os usa como amuletos”. É verdade, e a própria Igreja o condena: uma medalha não garante proteção física, a água benta não é uma substância mágica e portar um escapulário não substitui a conversão, a caridade nem a confiança em Deus. Se alguém atribui a um objeto uma eficácia automática desligada de Deus e da fé, pode cair em superstição."""),
        foundation = LocalizedText("""Éxodo 30,22-30; Marcos 6,13; Santiago 5,14; Hechos 19,11-12; Catecismo, 1667-1679.""", """Exodus 30:22-30; Mark 6:13; James 5:14; Acts 19:11-12; Catechism, 1667-1679.""", """Êxodo 30,22-30; Marcos 6,13; Tiago 5,14; Atos 19,11-12; Catecismo, 1667-1679."""),
        clarification = LocalizedText("""Una medalla no garantiza protección física; el agua bendita no es una sustancia mágica; y portar un escapulario no sustituye la conversión, la caridad ni la confianza en Dios.

Si alguien atribuye a un objeto una eficacia automática desligada de Dios y de la fe, puede caer en superstición, que la propia Iglesia condena.""", """A medal does not guarantee physical protection; holy water is not a magical substance; and carrying a scapular does not replace conversion, charity or trust in God.

If someone attributes to an object an automatic efficacy detached from God and from faith, they may fall into superstition, which the Church itself condemns.""", """Uma medalha não garante proteção física; a água benta não é uma substância mágica; e portar um escapulário não substitui a conversão, a caridade nem a confiança em Deus.

Se alguém atribui a um objeto uma eficácia automática desligada de Deus e da fé, pode cair em superstição, que a própria Igreja condena.""")
    ),
    ApologeticsQuestion(
        number = 61,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Dónde dice la Biblia que existe el purgatorio?""", """Where does the Bible say purgatory exists?""", """Onde a Bíblia diz que existe o purgatório?"""),
        answer = LocalizedText("""La respuesta directa es no: la Biblia no contiene la palabra purgatorio ni una descripción completa de su funcionamiento, y conviene decirlo con claridad desde el principio.

La doctrina católica se apoya en tres ideas: nada impuro entra en la presencia definitiva de Dios, la salvación es obra de Cristo y es posible que una persona ya salvada necesite una purificación final.

Por ejemplo, San Pablo describe a alguien cuya obra es consumida por el fuego, pero que personalmente se salva (1 Cor 3,15). La imagen no describe una segunda salvación, sino a una persona salvada cuya obra imperfecta queda probada.

El razonamiento católico concluye que la purificación final no es una segunda oportunidad tras la muerte: corresponde solo a quienes mueren en amistad con Dios, con la culpa ya perdonada.""", """Direct answer: the Bible contains neither the word purgatory nor a full description of how it works, and that should be said clearly at the start.

Catholic doctrine rests on three ideas: nothing impure enters God's definitive presence, salvation is Christ's work, and a person already saved may still need a final purification.

For example, Saint Paul describes someone whose work is burned away by the fire, yet who is himself saved (1 Cor 3:15). The image does not depict a second chance at salvation, but a saved person whose imperfect work is tested.

Catholic reasoning therefore holds that final purification is not a second opportunity after death: it concerns only those who die in friendship with God, their guilt already forgiven.""", """Resposta direta: a Bíblia não contém a palavra purgatório nem uma descrição completa de seu funcionamento, e isso deve ser dito com clareza desde o início.

A doutrina católica apoia-se em três ideias: nada impuro entra na presença definitiva de Deus, a salvação é obra de Cristo e é possível que uma pessoa já salva ainda necessite de uma purificação final.

Por exemplo, São Paulo descreve alguém cuja obra é consumida pelo fogo, mas que pessoalmente se salva (1Cor 3,15). A imagem não retrata uma segunda salvação, mas uma pessoa salva cuja obra imperfeita é provada.

O raciocínio católico conclui, portanto, que a purificação final não é uma segunda oportunidade após a morte: corresponde apenas a quem morre em amizade com Deus, com a culpa já perdoada."""),
        foundation = LocalizedText("""1 Corintios 3,11-15; Apocalipsis 21,27; Hebreos 12,14; 2 Macabeos 12,42-46; Catecismo, 1030-1032.""", """1 Corinthians 3:11-15; Revelation 21:27; Hebrews 12:14; 2 Maccabees 12:42-46; Catechism, 1030-1032.""", """1 Coríntios 3,11-15; Apocalipse 21,27; Hebreus 12,14; 2 Macabeus 12,42-46; Catecismo, 1030-1032."""),
        clarification = LocalizedText("""La objeción más fuerte es que 1 Corintios 3 admite otras interpretaciones: su contexto inmediato es la evaluación de la obra de los ministros cristianos, no un tratado sobre el purgatorio. Además, muchos protestantes no aceptan 2 Macabeos como Escritura canónica, por lo que el argumento no puede presentarse como una demostración bíblica incontestable. La doctrina se apoya también en la Tradición y en el desarrollo teológico.""", """The strongest objection is that 1 Corinthians 3 admits other interpretations: its immediate context is the assessment of Christian ministers' work, not a treatise on purgatory. Moreover, many Protestants do not accept 2 Maccabees as canonical Scripture, so the argument cannot be presented as an unquestionable biblical proof. The doctrine also rests on Tradition and theological development.""", """A objeção mais forte é que 1Coríntios 3 admite outras interpretações: seu contexto imediato é a avaliação da obra dos ministros cristãos, não um tratado sobre o purgatório. Além disso, muitos protestantes não aceitam 2Macabeus como Escritura canônica, de modo que o argumento não pode ser apresentado como uma prova bíblica incontestável. A doutrina apoia-se também na Tradição e no desenvolvimento teológico.""")
    ),
    ApologeticsQuestion(
        number = 62,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""Si Cristo murió por nuestros pecados, ¿por qué necesitaríamos el purgatorio?""", """If Christ died for our sins, why would we need purgatory?""", """Se Cristo morreu por nossos pecados, por que precisaríamos do purgatório?"""),
        answer = LocalizedText("""Perdonar una culpa y completar la transformación de una persona no son la misma cosa, y esa es la respuesta directa de la doctrina católica.

Una persona puede ser perdonada y conservar todavía apegos desordenados, hábitos arraigados o heridas que necesitan sanación. Por ejemplo, alguien que abandona una adicción puede ser perdonado y aun así necesitar tiempo, acompañamiento y nuevos hábitos para recuperarse por completo.

La purificación final se entiende como una aplicación de la gracia de Cristo, no como una reparación de una supuesta insuficiencia de su sacrificio. El mismo Cristo que perdona es quien santifica.""", """Forgiving a fault and completing a person's transformation are not the same thing, and that is the Catholic doctrine's direct answer.

A person can be forgiven while still retaining disordered attachments, ingrained habits or wounds that need healing. For example, someone who leaves an addiction behind can be forgiven yet still need time, support and new habits to recover fully.

Final purification is understood as an application of Christ's grace, not as a repair of a supposed insufficiency of His sacrifice. The same Christ who forgives is the one who sanctifies.""", """Perdoar uma culpa e completar a transformação de uma pessoa não são a mesma coisa, e essa é a resposta direta da doutrina católica.

Uma pessoa pode ser perdoada e ainda conservar apegos desordenados, hábitos arraigados ou feridas que precisam de cura. Por exemplo, alguém que deixa um vício para trás pode ser perdoado e ainda assim precisar de tempo, apoio e novos hábitos para se recuperar por completo.

A purificação final é entendida como uma aplicação da graça de Cristo, não como uma reparação de uma suposta insuficiência de seu sacrifício. O mesmo Cristo que perdoa é quem santifica."""),
        foundation = LocalizedText("""Hebreos 10,14; Filipenses 1,6; 1 Corintios 3,15; Catecismo, 1030-1031.""", """Hebrews 10:14; Philippians 1:6; 1 Corinthians 3:15; Catechism, 1030-1031.""", """Hebreus 10,14; Filipenses 1,6; 1 Coríntios 3,15; Catecismo, 1030-1031."""),
        clarification = LocalizedText("""La objeción más fuerte es que nada en esto demuestra que esa transformación deba ocurrir en un estado intermedio después de la muerte; Dios podría realizarla de un modo que desconocemos. La Iglesia afirma la realidad de una purificación final para quien la necesite, pero no define su duración ni su naturaleza física.""", """The strongest objection is that none of this proves the transformation must occur in an intermediate state after death; God could accomplish it in a way we do not know. The Church affirms the reality of a final purification for those who need it, but does not define its duration or physical nature.""", """A objeção mais forte é que nada disso demonstra que essa transformação deva ocorrer em um estado intermediário depois da morte; Deus poderia realizá-la de um modo que desconhecemos. A Igreja afirma a realidade de uma purificação final para quem a necessite, mas não define sua duração nem sua natureza física.""")
    ),
    ApologeticsQuestion(
        number = 63,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Basta la fe para salvarse o también son necesarias las obras?""", """Is faith alone enough to be saved, or are works also necessary?""", """Basta a fé para salvar-se ou também são necessárias as obras?"""),
        answer = LocalizedText("""La respuesta directa es que la salvación no se compra con obras: comienza en la gracia de Dios, no en nuestras obras.

Nadie puede ganar el perdón divino acumulando una cantidad suficiente de acciones buenas. Sin embargo, la fe auténtica no es solo aceptar intelectualmente que Dios existe; es una fe viva que transforma la vida y produce amor.

Por ejemplo, una persona puede afirmar que cree en Dios y tratar con dureza a su familia, lo que muestra una fe muerta. Otra persona, en cambio, perdona, sirve y actúa con justicia, lo que muestra una fe que obra por el amor.

San Pablo enseña que somos salvados por gracia mediante la fe y creados para las buenas obras (Ef 2,8-10), mientras Santiago afirma que una fe sin obras está muerta. La lectura católica entiende ambos textos como complementarios.""", """The direct answer is that salvation cannot be bought with works: it begins in God's grace, not in our works.

No one can earn divine forgiveness by accumulating a sufficient number of good deeds. However, authentic faith is not merely intellectually accepting that God exists; it is a living faith that transforms life and produces love.

For example, a person may claim to believe in God while treating their family harshly, which shows a dead faith. Another person, by contrast, forgives, serves and acts justly, which shows a faith working through love.

Saint Paul teaches that we are saved by grace through faith and created for good works (Eph 2:8-10), while James affirms that faith without works is dead. The Catholic reading understands both texts as complementary.""", """A resposta direta é que a salvação não se compra com obras: ela começa na graça de Deus, não em nossas obras.

Ninguém pode ganhar o perdão divino acumulando uma quantidade suficiente de boas ações. Contudo, a fé autêntica não é apenas aceitar intelectualmente que Deus existe; é uma fé viva que transforma a vida e produz amor.

Por exemplo, uma pessoa pode afirmar que crê em Deus e tratar a família com dureza, o que mostra uma fé morta. Outra pessoa, ao contrário, perdoa, serve e age com justiça, o que mostra uma fé que opera pelo amor.

São Paulo ensina que somos salvos pela graça mediante a fé e criados para as boas obras (Ef 2,8-10), enquanto Tiago afirma que a fé sem obras está morta. A leitura católica entende ambos os textos como complementares."""),
        foundation = LocalizedText("""Efesios 2,8-10; Santiago 2,14-26; Gálatas 5,6; Romanos 3,28; Mateo 25,31-46; Catecismo, 1987-2029.""", """Ephesians 2:8-10; James 2:14-26; Galatians 5:6; Romans 3:28; Matthew 25:31-46; Catechism, 1987-2029.""", """Efésios 2,8-10; Tiago 2,14-26; Gálatas 5,6; Romanos 3,28; Mateus 25,31-46; Catecismo, 1987-2029."""),
        clarification = LocalizedText("""La objeción más fuerte es que muchos protestantes también consideran las buenas obras indispensables como fruto de la fe, por lo que la diferencia real está en cómo se entiende la justificación. No debemos caricaturizar la posición protestante como si enseñara que es válido creer en Jesús y vivir deliberadamente haciendo el mal.""", """The strongest objection is that many Protestants also consider good works essential as the fruit of faith, so the real difference lies in how justification itself is understood. We must not caricature the Protestant position as if it taught that it is valid to believe in Jesus while deliberately living by doing evil.""", """A objeção mais forte é que muitos protestantes também consideram as boas obras indispensáveis como fruto da fé, de modo que a diferença real está em como se entende a própria justificação. Não devemos caricaturar a posição protestante como se ensinasse que é válido crer em Jesus e viver deliberadamente fazendo o mal.""")
    ),
    ApologeticsQuestion(
        number = 64,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Puede salvarse un asesino arrepentido y condenarse alguien que hizo mucho bien?""", """Can a repentant murderer be saved and someone who did much good be condemned?""", """Pode salvar-se um assassino arrependido e condenar-se alguém que fez muito bem?"""),
        answer = LocalizedText("""La respuesta directa es que sí: la misericordia de Dios puede alcanzar incluso al pecador más grave, siempre que exista un arrepentimiento verdadero.

El buen ladrón, crucificado junto a Jesús, es el ejemplo bíblico de esa esperanza (Lc 23,39-43). Pero arrepentirse no equivale a pronunciar unas palabras para evitar consecuencias.

Por ejemplo, quien reconoce el mal que causó, decide no repetirlo, busca reparar el daño en la medida de lo posible y asume las consecuencias muestra un arrepentimiento real. Quien solo dice tener remordimientos para salir de un castigo, sin cambiar su conducta, no muestra lo mismo.""", """The direct answer is yes: God's mercy can reach even the gravest sinner, provided there is true repentance.

The good thief, crucified beside Jesus, is the biblical example of that hope (Lk 23:39-43). But repenting does not amount to uttering a few words in order to avoid consequences.

For example, someone who recognizes the harm they caused, decides not to repeat it, tries to repair the damage as far as possible and accepts the consequences shows genuine repentance. Someone who merely professes remorse to escape punishment, without changing their behaviour, shows something different.""", """A resposta direta é que sim: a misericórdia de Deus pode alcançar até o pecador mais grave, sempre que exista um arrependimento verdadeiro.

O bom ladrão, crucificado junto a Jesus, é o exemplo bíblico dessa esperança (Lc 23,39-43). Mas arrepender-se não equivale a pronunciar algumas palavras para evitar consequências.

Por exemplo, quem reconhece o mal que causou, decide não repeti-lo, procura reparar o dano na medida do possível e assume as consequências mostra um arrependimento real. Quem apenas alega remorso para escapar de um castigo, sem mudar sua conduta, mostra algo diferente."""),
        foundation = LocalizedText("""Lucas 23,39-43; Lucas 15,11-32; Ezequiel 18,21-32; Mateo 20,1-16.""", """Luke 23:39-43; Luke 15:11-32; Ezekiel 18:21-32; Matthew 20:1-16.""", """Lucas 23,39-43; Lucas 15,11-32; Ezequiel 18,21-32; Mateus 20,1-16."""),
        clarification = LocalizedText("""La objeción más fuerte es que esto podría parecer una gracia demasiado fácil para quien causó un daño enorme. La respuesta católica precisa que ello no exime de enfrentar la justicia humana ni de reparar a las víctimas, y que la Iglesia no enseña que una persona honrada sea condenada automáticamente por no profesar explícitamente el catolicismo; Dios conoce la conciencia de cada uno.""", """The strongest objection is that this might seem like grace that comes too easily for someone who caused enormous harm. The Catholic answer clarifies that this does not exempt anyone from facing human justice or repairing the victims, and that the Church does not teach that an honest person is automatically condemned for not explicitly professing Catholicism; God knows each person's conscience.""", """A objeção mais forte é que isso poderia parecer uma graça fácil demais para quem causou um dano enorme. A resposta católica esclarece que isso não isenta de enfrentar a justiça humana nem de reparar as vítimas, e que a Igreja não ensina que uma pessoa honrada seja condenada automaticamente por não professar explicitamente o catolicismo; Deus conhece a consciência de cada um.""")
    ),
    ApologeticsQuestion(
        number = 65,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Por qué sufrimos las consecuencias del pecado de Adán y Eva?""", """Why do we suffer the consequences of Adam and Eve's sin?""", """Por que sofremos as consequências do pecado de Adão e Eva?"""),
        answer = LocalizedText("""La respuesta directa es que la doctrina católica distingue entre cometer personalmente un pecado y nacer dentro de una humanidad cuya relación original con Dios está herida.

El pecado original no significa que cada recién nacido sea personalmente culpable de la decisión de Adán. Por ejemplo, un niño que nace en una ciudad devastada por la guerra sufre sus consecuencias sin haberlas elegido: de modo análogo, nacemos en una humanidad marcada por la desobediencia de los primeros padres.

San Pablo establece un paralelo entre Adán, con quien entra el pecado en la historia humana, y Cristo, por quien llega la salvación (Rom 5,12-21).""", """The direct answer is that Catholic doctrine distinguishes between committing a sin personally and being born into a humanity whose original relationship with God is wounded.

Original sin does not mean that each newborn is personally guilty of Adam's decision. For example, a child born in a city devastated by war suffers its consequences without having chosen them: in a similar way, we are born into a humanity marked by the first parents' disobedience.

Saint Paul draws a parallel between Adam, with whom sin enters human history, and Christ, through whom salvation comes (Rom 5:12-21).""", """A resposta direta é que a doutrina católica distingue entre cometer pessoalmente um pecado e nascer dentro de uma humanidade cuja relação original com Deus está ferida.

O pecado original não significa que cada recém-nascido seja pessoalmente culpado da decisão de Adão. Por exemplo, uma criança que nasce em uma cidade devastada pela guerra sofre suas consequências sem tê-las escolhido: de modo análogo, nascemos em uma humanidade marcada pela desobediência dos primeiros pais.

São Paulo estabelece um paralelo entre Adão, com quem o pecado entra na história humana, e Cristo, por quem chega a salvação (Rm 5,12-21)."""),
        foundation = LocalizedText("""Génesis 3; Romanos 5,12-21; 1 Corintios 15,21-22; Catecismo, 396-409.""", """Genesis 3; Romans 5:12-21; 1 Corinthians 15:21-22; Catechism, 396-409.""", """Gênesis 3; Romanos 5,12-21; 1 Coríntios 15,21-22; Catecismo, 396-409."""),
        clarification = LocalizedText("""La objeción más fuerte es la de la justicia: por qué un inocente carga con consecuencias de algo que no hizo. La doctrina responde con la solidaridad humana, según la cual las decisiones de unos afectan a otros incluso durante generaciones, pero insiste en que no debe atribuirse a los bebés culpa moral personal, y la transmisión no puede explicarse como una simple herencia genética.""", """The strongest objection is one of justice: why should an innocent person bear the consequences of something they did not do? The doctrine answers with human solidarity, since the decisions of some affect others even across generations, but it insists that personal moral guilt must not be attributed to babies, and transmission cannot be explained as simple genetic inheritance.""", """A objeção mais forte é a da justiça: por que um inocente carrega as consequências de algo que não fez? A doutrina responde com a solidariedade humana, segundo a qual as decisões de uns afetam outros até através de gerações, mas insiste que não se deve atribuir culpa moral pessoal aos bebês, e a transmissão não pode ser explicada como uma simples herança genética.""")
    ),
    ApologeticsQuestion(
        number = 66,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Qué diferencia existe entre pecado mortal y venial?""", """What difference exists between mortal and venial sin?""", """Que diferença existe entre pecado mortal e venial?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia distingue los pecados según su gravedad y el grado de responsabilidad personal, no solo por el acto externo.

Un pecado mortal requiere simultáneamente tres condiciones: materia grave, conocimiento suficiente de su gravedad y consentimiento deliberado. Si falta una, la culpabilidad subjetiva no es la misma.

Por ejemplo, una persona que realiza un acto objetivamente grave estando privada de sueño, presionada o sin comprender plenamente su gravedad no está en la misma situación que quien busca ese acto con plena conciencia y libertad. Esa distinción protege tanto la seriedad moral como la justicia al juzgar a cada persona.""", """The direct answer is that the Church distinguishes sins by their gravity and by the degree of personal responsibility, not by outward act alone.

A mortal sin simultaneously requires three conditions: grave matter, sufficient knowledge of its gravity, and deliberate consent. If one is missing, subjective culpability is not the same.

For example, a person who performs an objectively grave act while deprived of sleep, under pressure, or without fully understanding its gravity is not in the same situation as someone who chooses that act with full conscience and freedom. That distinction protects both moral seriousness and fairness in judging each person.""", """A resposta direta é que a Igreja distingue os pecados segundo sua gravidade e o grau de responsabilidade pessoal, não apenas pelo ato externo.

Um pecado mortal requer simultaneamente três condições: matéria grave, conhecimento suficiente de sua gravidade e consentimento deliberado. Se falta uma delas, a culpabilidade subjetiva não é a mesma.

Por exemplo, uma pessoa que realiza um ato objetivamente grave, privada de sono, pressionada ou sem compreender plenamente sua gravidade não está na mesma situação de quem escolhe esse ato com plena consciência e liberdade. Essa distinção protege tanto a seriedade moral quanto a justiça ao julgar cada pessoa."""),
        foundation = LocalizedText("""1 Juan 5,16-17; Santiago 1,14-15; Mateo 5,21-22; Catecismo, 1854-1864.""", """1 John 5:16-17; James 1:14-15; Matthew 5:21-22; Catechism, 1854-1864.""", """1 João 5,16-17; Tiago 1,14-15; Mateus 5,21-22; Catecismo, 1854-1864."""),
        clarification = LocalizedText("""La objeción más fuerte es que esta distinción parece demasiado subjetiva y deja espacio para minimizar pecados graves. La respuesta es que la gravedad objetiva del acto sigue contando, pero no basta para conocer con certeza la culpabilidad subjetiva de alguien: deben considerarse los tres requisitos. Tampoco corresponde a Catholic Hermes determinar que una persona concreta cometió pecado mortal o afirmar cuál será su destino eterno.""", """The strongest objection is that this distinction seems too subjective and leaves room to minimize grave sins. The answer is that the objective gravity of the act still matters, but it is not enough to know someone's subjective culpability with certainty: all three requirements must be considered. Nor does it belong to Catholic Hermes to determine that a concrete person committed a mortal sin or to affirm what their eternal destiny will be.""", """A objeção mais forte é que essa distinção parece subjetiva demais e abre espaço para minimizar pecados graves. A resposta é que a gravidade objetiva do ato continua contando, mas não basta para conhecer com certeza a culpabilidade subjetiva de alguém: devem ser considerados os três requisitos. Tampouco corresponde a Catholic Hermes determinar que uma pessoa concreta cometeu pecado mortal ou afirmar qual será seu destino eterno.""")
    ),
    ApologeticsQuestion(
        number = 67,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Podría salvarse finalmente toda la humanidad?""", """Could all humanity finally be saved?""", """Poderia salvar-se finalmente toda a humanidade?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia enseña ambas cosas a la vez: Dios quiere la salvación de todos y, al mismo tiempo, existe la posibilidad real de condenación definitiva.

Por eso, el cristiano puede y debe desear y pedir la salvación universal, sin afirmar como doctrina segura que nadie puede condenarse. Por ejemplo, rezar por la conversión de un familiar o de un pueblo entero expresa ese deseo, sin atribuir definitivamente a otra persona su destino.

Esa tensión entre el deseo salvífico universal de Dios y el respeto a la libertad humana forma parte del misterio cristiano.""", """The direct answer is that the Church teaches both at once: God wills the salvation of all, and at the same time there is a real possibility of definitive condemnation.

Therefore the Christian can and must desire and ask for universal salvation, without affirming as a secure doctrine that no one can be condemned. For example, praying for the conversion of a relative or an entire people expresses that desire, without definitively attributing their destiny to another person.

That tension between God's universal saving will and respect for human freedom is part of the Christian mystery.""", """A resposta direta é que a Igreja ensina as duas coisas ao mesmo tempo: Deus quer a salvação de todos e, ao mesmo tempo, existe a possibilidade real de condenação definitiva.

Por isso, o cristão pode e deve desejar e pedir a salvação universal, sem afirmar como doutrina segura que ninguém pode ser condenado. Por exemplo, rezar pela conversão de um familiar ou de um povo inteiro expressa esse desejo, sem atribuir definitivamente a outra pessoa o seu destino.

Essa tensão entre o desejo salvífico universal de Deus e o respeito pela liberdade humana faz parte do mistério cristão."""),
        foundation = LocalizedText("""1 Timoteo 2,3-6; 2 Pedro 3,9; Romanos 11,32; Mateo 25,46; Catecismo, 1033-1037.""", """1 Timothy 2:3-6; 2 Peter 3:9; Romans 11:32; Matthew 25:46; Catechism, 1033-1037.""", """1 Timóteo 2,3-6; 2 Pedro 3,9; Romanos 11,32; Mateus 25,46; Catecismo, 1033-1037."""),
        clarification = LocalizedText("""La objeción más fuerte es que si Dios quiere salvar a todos, la libertad humana parece una objeción secundaria frente a su misericordia infinita. La respuesta católica no resuelve esa tensión inventando certezas que la revelación no ofrece: esperar que todos se salven no equivale a afirmar como seguro que nadie puede condenarse, ni la Iglesia enseña que el infierno esté necesariamente poblado por un número determinado de personas.""", """The strongest objection is that if God wills to save everyone, human freedom seems a secondary objection beside His infinite mercy. The Catholic answer does not resolve that tension by inventing certainties revelation does not offer: hoping that all may be saved is not the same as affirming as certain that no one can be condemned, nor does the Church teach that hell is necessarily populated by a determined number of people.""", """A objeção mais forte é que, se Deus quer salvar todos, a liberdade humana parece uma objeção secundária diante de sua misericórdia infinita. A resposta católica não resolve essa tensão inventando certezas que a revelação não oferece: esperar que todos se salvem não equivale a afirmar como certo que ninguém pode ser condenado, nem a Igreja ensina que o inferno esteja necessariamente povoado por um número determinado de pessoas.""")
    ),
    ApologeticsQuestion(
        number = 68,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Qué sucede con quienes nunca conocieron a Jesús?""", """What happens to those who never knew Jesus?""", """O que acontece com quem nunca conheceu Jesus?"""),
        answer = LocalizedText("""La respuesta directa es que Dios no juzga a las personas como si todas hubieran recibido la misma información y las mismas oportunidades.

La enseñanza católica reconoce la posibilidad de salvación de quienes no conocen el Evangelio sin culpa propia, buscan sinceramente la verdad y procuran seguir su conciencia bajo la influencia de la gracia. Por ejemplo, una persona que vive en una cultura rural sin acceso al Evangelio, que actúa con honestidad y busca el bien, no puede equipararse a alguien que conoce a Cristo y lo rechaza libremente.

Esto no crea una segunda fuente de salvación independiente de Cristo: la Iglesia sostiene que toda salvación procede de Él.""", """The direct answer is that God does not judge people as if everyone had received the same information and the same opportunities.

Catholic teaching recognizes the possibility of salvation for those who do not know the Gospel through no fault of their own, sincerely seek the truth, and strive to follow their conscience under the influence of grace. For example, a person living in a rural culture without access to the Gospel, who acts honestly and seeks the good, cannot be equated with someone who knows Christ and freely rejects Him.

This does not create a second path of salvation independent of Christ: the Church holds that all salvation comes from Him.""", """A resposta direta é que Deus não julga as pessoas como se todas tivessem recebido a mesma informação e as mesmas oportunidades.

O ensino católico reconhece a possibilidade de salvação daqueles que não conhecem o Evangelho sem culpa própria, buscam sinceramente a verdade e procuram seguir a consciência sob a influência da graça. Por exemplo, uma pessoa que vive em uma cultura rural sem acesso ao Evangelho, que age com honestidade e busca o bem, não pode ser equiparada a alguém que conhece a Cristo e o rejeita livremente.

Isso não cria uma segunda fonte de salvação independente de Cristo: a Igreja sustenta que toda salvação procede dele."""),
        foundation = LocalizedText("""Romanos 2,14-16; Hechos 10,34-35; 1 Timoteo 2,4; Catecismo, 846-848.""", """Romans 2:14-16; Acts 10:34-35; 1 Timothy 2:4; Catechism, 846-848.""", """Romanos 2,14-16; Atos 10,34-35; 1 Timóteo 2,4; Catecismo, 846-848."""),
        clarification = LocalizedText("""La objeción más fuerte es que seguir la conciencia podría justificar cualquier conducta. La respuesta es que la conciencia también debe ser formada y puede equivocarse, de modo que seguirla no hace automáticamente aceptable cualquier acto. Pero tampoco se puede equiparar una ignorancia involuntaria con un rechazo libre y consciente de lo que se reconoce como verdadero.""", """The strongest objection is that following one's conscience could justify any conduct. The answer is that conscience must also be formed and can be mistaken, so following it does not automatically make any act acceptable. But involuntary ignorance cannot be equated with a free and conscious rejection of what one recognizes as true.""", """A objeção mais forte é que seguir a própria consciência poderia justificar qualquer conduta. A resposta é que a consciência também deve ser formada e pode se equivocar, de modo que segui-la não torna automaticamente aceitável qualquer ato. Mas tampouco se pode equiparar uma ignorância involuntária a uma rejeição livre e consciente daquilo que se reconhece como verdadeiro.""")
    ),
    ApologeticsQuestion(
        number = 69,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Para qué rezar por los muertos si Dios ya conoce su destino?""", """Why pray for the dead if God already knows their destiny?""", """Para que rezar pelos mortos, se Deus já conhece o destino deles?"""),
        answer = LocalizedText("""La respuesta directa es que la oración cristiana no busca informar a Dios de algo que desconoce, como si Él necesitara un aviso.

La Iglesia enseña que los creyentes permanecen unidos en Cristo y que la oración puede participar misteriosamente en su obra salvadora. Por ejemplo, rezar por un amigo enfermo no le enseña nada nuevo a Dios, pero expresa comunión y confianza en su ayuda; de modo análogo, rezar por los difuntos expresa esa comunión más allá de la muerte.

La oración por los difuntos es coherente con la doctrina católica de la comunión de los santos y la purificación final.""", """The direct answer is that Christian prayer does not seek to inform God of something He does not know, as if He needed a warning.

The Church teaches that believers remain united in Christ and that prayer can mysteriously participate in His saving work. For example, praying for a sick friend teaches God nothing new, but expresses communion and trust in His help; in a similar way, praying for the dead expresses that communion beyond death.

Prayer for the dead is coherent with the Catholic doctrine of the communion of saints and final purification.""", """A resposta direta é que a oração cristã não busca informar a Deus de algo que ele desconheça, como se ele precisasse de um aviso.

A Igreja ensina que os crentes permanecem unidos em Cristo e que a oração pode participar misteriosamente de sua obra salvífica. Por exemplo, rezar por um amigo enfermo não ensina nada de novo a Deus, mas expressa comunhão e confiança em sua ajuda; de modo análogo, rezar pelos defuntos expressa essa comunhão além da morte.

A oração pelos defuntos é coerente com a doutrina católica da comunhão dos santos e da purificação final."""),
        foundation = LocalizedText("""2 Macabeos 12,42-46; 1 Corintios 3,15; 2 Timoteo 1,16-18; Catecismo, 1032 y 958.""", """2 Maccabees 12:42-46; 1 Corinthians 3:15; 2 Timothy 1:16-18; Catechism, 1032 and 958.""", """2 Macabeus 12,42-46; 1 Coríntios 3,15; 2 Timóteo 1,16-18; Catecismo, 1032 e 958."""),
        clarification = LocalizedText("""La objeción más fuerte es que, si Dios ya conoce el destino de cada persona, rezar parece inútil. La respuesta más sólida reconoce que esta práctica tiene una base bíblica interpretada dentro de la Tradición, pero no puede presentarse como un mandato explícito del Nuevo Testamento: el pasaje de 2 Timoteo no establece inequívocamente que Onesíforo estuviera muerto, y 2 Macabeos no es aceptado como canónico por muchos protestantes.""", """The strongest objection is that, if God already knows each person's destiny, praying seems pointless. The most solid answer recognizes that this practice has a biblical basis interpreted within Tradition, but it cannot be presented as an explicit mandate of the New Testament: the passage in 2 Timothy does not unequivocally establish that Onesiphorus was dead, and 2 Maccabees is not accepted as canonical by many Protestants.""", """A objeção mais forte é que, se Deus já conhece o destino de cada pessoa, rezar parece inútil. A resposta mais sólida reconhece que esta prática tem uma base bíblica interpretada dentro da Tradição, mas não pode ser apresentada como um mandato explícito do Novo Testamento: a passagem de 2Timóteo não estabelece de maneira inequívoca que Onésíforo estivesse morto, e 2Macabeus não é aceito como canônico por muitos protestantes.""")
    ),
    ApologeticsQuestion(
        number = 70,
        section = LocalizedText("""La salvación, el purgatorio, el pecado y la vida eterna""", """Salvation, purgatory, sin and eternal life""", """A salvação, o purgatório, o pecado e a vida eterna"""),
        question = LocalizedText("""¿Qué son las indulgencias y por qué existieron abusos relacionados con ellas?""", """What are indulgences, and why were there abuses connected with them?""", """O que são as indulgências e por que existiram abusos relacionados a elas?"""),
        answer = LocalizedText("""La respuesta directa es que una indulgencia no es el perdón comprado de un pecado.

La doctrina católica la relaciona con las consecuencias temporales del pecado ya perdonado y con la comunión de los santos: hay que distinguir entre la culpa perdonada y los efectos desordenados que el pecado puede dejar.

Por ejemplo, una persona puede ser perdonada por robar y continuar obligada a reparar el daño a la víctima. Del mismo modo, la indulgencia se refiere a la sanación de esas consecuencias, no a la venta de la culpa.""", """The direct answer is that an indulgence is not the purchased forgiveness of a sin.

Catholic doctrine relates it to the temporal consequences of a sin already forgiven and to the communion of saints: one must distinguish between forgiven guilt and the disordered effects that sin can leave behind.

For example, a person can be forgiven for stealing while still being obliged to repair the harm to the victim. In a similar way, an indulgence concerns the healing of those consequences, not the sale of guilt.""", """A resposta direta é que uma indulgência não é o perdão comprado de um pecado.

A doutrina católica a relaciona com as consequências temporais do pecado já perdoado e com a comunhão dos santos: é preciso distinguir entre a culpa perdoada e os efeitos desordenados que o pecado pode deixar.

Por exemplo, uma pessoa pode ser perdoada por roubar e continuar obrigada a reparar o dano à vítima. De modo semelhante, a indulgência diz respeito à cura dessas consequências, não à venda da culpa."""),
        foundation = LocalizedText("""Mateo 16,19; 1 Corintios 12,26; 2 Samuel 12,13-14; Catecismo, 1471-1479.""", """Matthew 16:19; 1 Corinthians 12:26; 2 Samuel 12:13-14; Catechism, 1471-1479.""", """Mateus 16,19; 1 Coríntios 12,26; 2 Samuel 12,13-14; Catecismo, 1471-1479."""),
        clarification = LocalizedText("""La objeción más fuerte es histórica: existieron abusos graves relacionados con la predicación de las indulgencias y con prácticas económicas, y la crítica a esos abusos fue legítima. Esa comparación no demuestra por sí misma la doctrina completa, y las indulgencias no pueden venderse ni utilizarse como garantía automática de salvación. La doctrina debe explicarse sin insultar ni humillar a nadie.""", """The strongest objection is historical: there were grave abuses connected with the preaching of indulgences and with economic practices, and criticism of those abuses was legitimate. That comparison does not by itself prove the full doctrine, and indulgences cannot be sold or used as an automatic guarantee of salvation. The doctrine must be explained without insulting or humiliating anyone.""", """A objeção mais forte é histórica: existiram abusos graves ligados à pregação das indulgências e a práticas econômicas, e a crítica a esses abusos foi legítima. Essa comparação, por si só, não demonstra a doutrina completa, e as indulgências não podem ser vendidas nem utilizadas como garantia automática de salvação. A doutrina deve ser explicada sem insultar nem humilhar ninguém.""")
    ),
    ApologeticsQuestion(
        number = 71,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""Si Dios es amor, ¿por qué la Iglesia considera inmorales las relaciones homosexuales?""", """If God is love, why does the Church consider homosexual relationships immoral?""", """Se Deus é amor, por que a Igreja considera imorais as relações homossexuais?"""),
        answer = LocalizedText("""La respuesta directa debe partir del respeto: la Iglesia católica no enseña que una persona homosexual tenga menos dignidad o sea menos amada por Dios. Su juicio moral tradicional se refiere a los actos sexuales, dentro de una comprensión del matrimonio como unión entre hombre y mujer abierta a la vida.

La doctrina moral católica sostiene que la sexualidad tiene una dimensión unitiva y procreativa que debe respetarse. Por ejemplo, acompañar con respeto y cercanía a una persona homosexual, escuchándola y sin humillarla, es exigible en todo caso, porque la dignidad de cada persona es inviolable.""", """The direct answer must begin with respect: the Catholic Church does not teach that a homosexual person has less dignity or is less loved by God. Its traditional moral judgement refers to sexual acts, within an understanding of marriage as a union between man and woman open to life.

Catholic moral doctrine holds that sexuality has a unitive and procreative dimension that must be respected. For example, accompanying a homosexual person with respect and closeness, listening to them without humiliating them, is required in every case, because the dignity of every person is inviolable.""", """A resposta direta deve partir do respeito: a Igreja católica não ensina que uma pessoa homossexual tenha menos dignidade ou seja menos amada por Deus. Seu juízo moral tradicional refere-se aos atos sexuais, dentro de uma compreensão do casamento como união entre homem e mulher aberta à vida.

A doutrina moral católica sustenta que a sexualidade tem uma dimensão unitiva e procriativa que deve ser respeitada. Por exemplo, acompanhar uma pessoa homossexual com respeito e proximidade, ouvindo-a sem humilhá-la, é exigível em todos os casos, porque a dignidade de cada pessoa é inviolável."""),
        foundation = LocalizedText("""Génesis 1,27-28; Mateo 19,4-6; Romanos 1,26-27; Catecismo, 2357-2359.""", """Genesis 1:27-28; Matthew 19:4-6; Romans 1:26-27; Catechism, 2357-2359.""", """Gênesis 1,27-28; Mateus 19,4-6; Romanos 1,26-27; Catecismo, 2357-2359."""),
        clarification = LocalizedText("""La objeción más fuerte es exegética: la interpretación de los textos bíblicos sobre relaciones homosexuales es objeto de controversias importantes, especialmente ante las formas contemporáneas de relaciones afectivas estables entre personas del mismo sexo. La Iglesia mantiene su enseñanza sobre el matrimonio, pero exige respeto a todas las personas, y la declaración Fiducia supplicans de 2023 abrió la posibilidad de bendiciones pastorales espontáneas de parejas del mismo sexo, sin equipararlas al matrimonio ni cambiar la doctrina sobre este sacramento.""", """The strongest objection is exegetical: the interpretation of the biblical texts on homosexual relationships is the subject of important controversies, especially in the light of contemporary forms of stable affective relationships between persons of the same sex. The Church maintains its teaching on marriage, but demands respect for all persons, and the 2023 declaration Fiducia supplicans opened the possibility of spontaneous pastoral blessings of same-sex couples, without equating them with marriage or changing the doctrine on this sacrament.""", """A objeção mais forte é exegética: a interpretação dos textos bíblicos sobre relações homossexuais é objeto de controvérsias importantes, especialmente diante das formas contemporâneas de relações afetivas estáveis entre pessoas do mesmo sexo. A Igreja mantém seu ensino sobre o casamento, mas exige respeito por todas as pessoas, e a declaração Fiducia supplicans de 2023 abriu a possibilidade de bênçãos pastorais espontâneas de casais do mesmo sexo, sem equipará-las ao casamento nem mudar a doutrina sobre este sacramento.""")
    ),
    ApologeticsQuestion(
        number = 72,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué dos personas del mismo sexo que se aman no pueden contraer matrimonio católico?""", """Why cannot two people of the same sex who love each other enter into a Catholic marriage?""", """Por que duas pessoas do mesmo sexo que se amam não podem contrair o casamento católico?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia considera que el matrimonio sacramental posee una estructura propia, vinculada a la complementariedad sexual del hombre y la mujer y a la apertura a la generación de vida.

Según esta perspectiva, el matrimonio no se define solamente por la intensidad del amor, sino también por una determinada clase de alianza corporal y familiar. Por ejemplo, un novio católico que cuida a su pareja en la enfermedad muestra amor verdadero, pero la Iglesia distingue ese amor de la forma sacramental que entiende reservada a la alianza entre hombre y mujer, sin negar la capacidad de amar de ninguna persona.""", """The direct answer is that the Church considers sacramental marriage to have its own structure, linked to the sexual complementarity of man and woman and to openness to the generation of life.

According to this perspective, marriage is not defined only by the intensity of love, but also by a particular kind of bodily and family covenant. For example, a Catholic partner who cares for their spouse in illness shows true love, but the Church distinguishes that love from the sacramental form it understands as reserved to the covenant between man and woman, without denying anyone's capacity to love.""", """A resposta direta é que a Igreja considera que o casamento sacramental possui uma estrutura própria, ligada à complementaridade sexual do homem e da mulher e à abertura à geração de vida.

Segundo essa perspectiva, o casamento não se define somente pela intensidade do amor, mas também por um determinado tipo de aliança corporal e familiar. Por exemplo, um parceiro católico que cuida do cônjuge na doença mostra amor verdadeiro, mas a Igreja distingue esse amor da forma sacramental que entende reservada à aliança entre homem e mulher, sem negar a capacidade de amar de nenhuma pessoa."""),
        foundation = LocalizedText("""Génesis 2,24; Mateo 19,4-6; Efesios 5,31-32; Catecismo, 1601 y 1652-1654.""", """Genesis 2:24; Matthew 19:4-6; Ephesians 5:31-32; Catechism, 1601 and 1652-1654.""", """Gênesis 2,24; Mateus 19,4-6; Efésios 5,31-32; Catecismo, 1601 e 1652-1654."""),
        clarification = LocalizedText("""La objeción más fuerte es que la intensidad y la fidelidad del amor parecen bastar para hablar de matrimonio, y que la mujer y el hombre aportan a la sociedad por igual. La respuesta católica distingue el debate teológico sobre el matrimonio sacramental del debate jurídico sobre el matrimonio civil: ello no significa que las personas homosexuales sean incapaces de amar sinceramente, ser fieles o cuidar a otros.""", """The strongest objection is that the intensity and fidelity of love seem enough to speak of marriage, and that women and men contribute equally to society. The Catholic answer distinguishes the theological debate on sacramental marriage from the legal debate on civil marriage: this does not mean that homosexual persons are incapable of loving sincerely, being faithful, or caring for others.""", """A objeção mais forte é que a intensidade e a fidelidade do amor parecem bastar para falar de casamento, e que mulheres e homens contribuem igualmente para a sociedade. A resposta católica distingue o debate teológico sobre o casamento sacramental do debate jurídico sobre o casamento civil: isso não significa que as pessoas homossexuais sejam incapazes de amar sinceramente, ser fiéis ou cuidar dos outros.""")
    ),
    ApologeticsQuestion(
        number = 73,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué las relaciones sexuales fuera del matrimonio se consideran pecado?""", """Why are sexual relations outside marriage considered a sin?""", """Por que as relações sexuais fora do casamento são consideradas pecado?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia entiende la unión sexual como una expresión de entrega personal que debe corresponder a un compromiso estable, exclusivo y público.

El matrimonio busca establecer ese compromiso mediante una alianza de fidelidad y responsabilidad. Por ejemplo, un novio que protege la exclusividad mutua, actúa con honestidad y cuida el futuro de la otra persona se acerca a ese ideal de entrega; una relación movida por la manipulación, la ocultación o el uso del otro la contradice.

La idea central no es que el cuerpo o el deseo sexual sean malos: la sexualidad es un bien que debe integrarse en el amor responsable.""", """The direct answer is that the Church understands sexual union as an expression of personal self-giving that must correspond to a stable, exclusive and public commitment.

Marriage seeks to establish that commitment through a covenant of fidelity and responsibility. For example, a partner who protects mutual exclusivity, acts honestly and cares for the other's future approaches that ideal of self-giving; a relationship driven by manipulation, concealment or the use of the other contradicts it.

The central idea is not that the body or sexual desire is bad: sexuality is a good that must be integrated into responsible love.""", """A resposta direta é que a Igreja entende a união sexual como uma expressão de entrega pessoal que deve corresponder a um compromisso estável, exclusivo e público.

O casamento busca estabelecer esse compromisso mediante uma aliança de fidelidade e responsabilidade. Por exemplo, um parceiro que protege a exclusividade mútua, age com honestidade e cuida do futuro da outra pessoa se aproxima desse ideal de entrega; um relacionamento movido por manipulação, ocultação ou uso do outro o contradiz.

A ideia central não é que o corpo ou o desejo sexual sejam maus: a sexualidade é um bem que deve ser integrada ao amor responsável."""),
        foundation = LocalizedText("""Génesis 2,24; Mateo 19,4-6; 1 Corintios 6,18-20; 1 Tesalonicenses 4,3-5; Catecismo, 2337 y 2353.""", """Genesis 2:24; Matthew 19:4-6; 1 Corinthians 6:18-20; 1 Thessalonians 4:3-5; Catechism, 2337 and 2353.""", """Gênesis 2,24; Mateus 19,4-6; 1 Coríntios 6,18-20; 1 Tessalonicenses 4,3-5; Catecismo, 2337 e 2353."""),
        clarification = LocalizedText("""La objeción más fuerte es histórica y antropológica: la Biblia no presenta el concepto moderno de noviazgo tal como lo conocemos, y el argumento de que la sexualidad requiere matrimonio forma parte de una determinada antropología moral. La enseñanza católica debe explicarse sin convertir a quienes no la siguen en personas indignas de respeto.""", """The strongest objection is historical and anthropological: the Bible does not present the modern concept of dating as we know it, and the argument that sexuality requires marriage forms part of a particular moral anthropology. Catholic teaching must be explained without turning those who do not follow it into persons unworthy of respect.""", """A objeção mais forte é histórica e antropológica: a Bíblia não apresenta o conceito moderno de namoro tal como o conhecemos, e o argumento de que a sexualidade requer o casamento faz parte de uma determinada antropologia moral. O ensino católico deve ser explicado sem transformar quem não o segue em pessoas indignas de respeito.""")
    ),
    ApologeticsQuestion(
        number = 74,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué rechazar anticonceptivos artificiales y permitir métodos naturales?""", """Why reject artificial contraception and allow natural methods?""", """Por que rejeitar os anticoncepcionais artificiais e permitir métodos naturais?"""),
        answer = LocalizedText("""La respuesta directa es que la doctrina católica sostiene que los actos conyugales deben respetar conjuntamente su significado unitivo y su apertura a la procreación.

Por eso distingue entre evitar deliberadamente la fecundidad de un acto sexual mediante anticoncepción y elegir responsablemente momentos de infertilidad. Por ejemplo, un matrimonio que, por salud o por razones económicas, decide espaciar los nacimientos mediante métodos naturales sigue reconociendo la apertura a la vida, mientras que la anticoncepción artificial, según esta doctrina, cierra deliberadamente cada acto a la fecundidad.

La Iglesia reconoce que pueden existir motivos responsables para espaciar los nacimientos.""", """The direct answer is that Catholic doctrine holds that conjugal acts must jointly respect their unitive meaning and their openness to procreation.

That is why it distinguishes between deliberately avoiding the fertility of a sexual act through contraception and responsibly choosing times of infertility. For example, a couple that, for health or financial reasons, decides to space births through natural methods still recognizes openness to life, while artificial contraception, according to this doctrine, deliberately closes each act to fertility.

The Church recognizes that there can be responsible reasons for spacing births.""", """A resposta direta é que a doutrina católica sustenta que os atos conjugais devem respeitar conjuntamente seu significado unitivo e sua abertura à procriação.

Por isso distingue entre evitar deliberadamente a fecundidade de um ato sexual mediante a contracepção e escolher responsavelmente momentos de infertilidade. Por exemplo, um casal que, por saúde ou por razões econômicas, decide espaçar os nascimentos mediante métodos naturais continua a reconhecer a abertura à vida, enquanto a contracepção artificial, segundo esta doutrina, fecha deliberadamente cada ato à fecundidade.

A Igreja reconhece que podem existir motivos responsáveis para espaçar os nascimentos."""),
        foundation = LocalizedText("""Génesis 1,28; Génesis 2,24; Catecismo, 2366-2370; encíclica Humanae vitae, 11-16.""", """Genesis 1:28; Genesis 2:24; Catechism, 2366-2370; encyclical Humanae vitae, 11-16.""", """Gênesis 1,28; Gênesis 2,24; Catecismo, 2366-2370; encíclica Humanae vitae, 11-16."""),
        clarification = LocalizedText("""La objeción más fuerte es que la distinción entre método y acto parece artificial y que la intención de la pareja debería ser lo decisivo. La respuesta católica precisa que la distinción no es una conclusión científica inevitable, sino una valoración moral basada en una comprensión específica de la sexualidad, y que los medicamentos con finalidad terapéutica deben analizarse por su propósito y sus efectos, no por una etiqueta automática.""", """The strongest objection is that the distinction between method and act seems artificial, and that the couple's intention should be decisive. The Catholic answer clarifies that the distinction is not an inevitable scientific conclusion, but a moral judgement based on a specific understanding of sexuality, and that medicines with a therapeutic purpose must be assessed by their purpose and effects, not by an automatic label.""", """A objeção mais forte é que a distinção entre método e ato parece artificial e que a intenção do casal deveria ser decisiva. A resposta católica esclarece que a distinção não é uma conclusão científica inevitável, mas uma avaliação moral baseada em uma compreensão específica da sexualidade, e que os medicamentos com finalidade terapêutica devem ser avaliados por seu propósito e seus efeitos, não por um rótulo automático.""")
    ),
    ApologeticsQuestion(
        number = 75,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué rechazar la fecundación in vitro si ayuda a matrimonios infértiles?""", """Why reject in vitro fertilization if it helps infertile couples?""", """Por que rejeitar a fecundação in vitro, se ajuda casais inférteis?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia reconoce que el deseo de tener hijos es legítimo y que la infertilidad puede causar un sufrimiento profundo, pero considera que la generación de una vida humana debe respetar la dignidad del embrión y la unidad entre la procreación y el acto conyugal.

La enseñanza no rechaza la medicina reproductiva por ser artificial: acepta procedimientos que ayudan a restaurar la fertilidad sin sustituir el acto conyugal. Por ejemplo, un tratamiento hormonal que restablece la ovulación es aceptado, mientras que la fecundación fuera del cuerpo es considerada problemática por separar la concepción de la unión conyugal y por los riesgos de pérdida embrionaria.""", """The direct answer is that the Church recognizes that the desire to have children is legitimate and that infertility can cause profound suffering, but it considers that the generation of a human life must respect the dignity of the embryo and the unity between procreation and the conjugal act.

The teaching does not reject reproductive medicine for being artificial: it accepts procedures that help restore fertility without substituting for the conjugal act. For example, hormonal treatment that restores ovulation is accepted, while conception outside the body is considered problematic because it separates conception from conjugal union and carries risks of embryonic loss.""", """A resposta direta é que a Igreja reconhece que o desejo de ter filhos é legítimo e que a infertilidade pode causar um sofrimento profundo, mas considera que a geração de uma vida humana deve respeitar a dignidade do embrião e a unidade entre a procriação e o ato conjugal.

O ensino não rejeita a medicina reprodutiva por ser artificial: aceita procedimentos que ajudam a restaurar a fertilidade sem substituir o ato conjugal. Por exemplo, um tratamento hormonal que restaura a ovulação é aceito, enquanto a fecundação fora do corpo é considerada problemática porque separa a concepção da união conjugal e envolve riscos de perda embrionária."""),
        foundation = LocalizedText("""Génesis 1,27-28; Salmo 139,13-16; Catecismo, 2373-2379; Dignitas personae, 12-16.""", """Genesis 1:27-28; Psalm 139:13-16; Catechism, 2373-2379; Dignitas personae, 12-16.""", """Gênesis 1,27-28; Salmo 139,13-16; Catecismo, 2373-2379; Dignitas personae, 12-16."""),
        clarification = LocalizedText("""La objeción más fuerte es pastoral: parejas que sufren años en silencio perciben esta enseñanza como falta de compasión ante un deseo legítimo. La respuesta católica precisa que la objeción no depende únicamente de la destrucción de embriones, aunque esta es una preocupación importante, sino también de la separación entre concepción y unión conyugal, y que los niños concebidos mediante estas técnicas tienen exactamente la misma dignidad que cualquier otra persona.""", """The strongest objection is pastoral: couples who suffer for years in silence perceive this teaching as a lack of compassion toward a legitimate desire. The Catholic answer clarifies that the objection does not depend solely on the destruction of embryos, although this is an important concern, but also on the separation between conception and conjugal union, and that children conceived through these techniques have exactly the same dignity as any other person.""", """A objeção mais forte é pastoral: casais que sofrem anos em silêncio percebem esse ensino como falta de compaixão diante de um desejo legítimo. A resposta católica esclarece que a objeção não depende unicamente da destruição de embriões, embora seja uma preocupação importante, mas também da separação entre concepção e união conjugal, e que as crianças concebidas por meio dessas técnicas têm exatamente a mesma dignidade que qualquer outra pessoa.""")
    ),
    ApologeticsQuestion(
        number = 76,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia obliga a permanecer en un matrimonio donde existe violencia o sufrimiento?""", """Why does the Church oblige someone to remain in a marriage where there is violence or suffering?""", """Por que a Igreja obriga a permanecer em um casamento onde há violência ou sofrimento?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia no obliga a una persona a permanecer físicamente junto a un cónyuge violento: esa formulación de la pregunta no corresponde a la doctrina católica.

La separación puede ser legítima, y el divorcio civil puede admitirse cuando sea necesario para proteger derechos, hijos, seguridad o patrimonio. La indisolubilidad del matrimonio sacramental no significa que una víctima deba soportar agresiones.

Por ejemplo, una mujer que se aleja de un hogar violento, pone a salvo a sus hijos y busca protección legal actúa conforme a la enseñanza católica, sin que ello implique rebelión contra la Iglesia.""", """The direct answer is that the Church does not oblige a person to remain physically alongside a violent spouse: that way of putting the question does not match Catholic doctrine.

Separation can be legitimate, and civil divorce can be admitted when necessary to protect rights, children, safety or property. The indissolubility of sacramental marriage does not mean that a victim must endure aggression.

For example, a woman who leaves a violent home, keeps her children safe and seeks legal protection acts in accordance with Catholic teaching, without this implying any rebellion against the Church.""", """A resposta direta é que a Igreja não obriga uma pessoa a permanecer fisicamente junto a um cônjuge violento: essa formulação da pergunta não corresponde à doutrina católica.

A separação pode ser legítima, e o divórcio civil pode ser admitido quando for necessário para proteger direitos, filhos, segurança ou patrimônio. A indissolubilidade do casamento sacramental não significa que uma vítima deva suportar agressões.

Por exemplo, uma mulher que sai de um lar violento, coloca os filhos em segurança e busca proteção legal age de acordo com o ensino católico, sem que isso implique rebelião contra a Igreja."""),
        foundation = LocalizedText("""Mateo 19,3-9; 1 Corintios 7,10-11; Catecismo, 2382-2383.""", """Matthew 19:3-9; 1 Corinthians 7:10-11; Catechism, 2382-2383.""", """Mateus 19,3-9; 1 Coríntios 7,10-11; Catecismo, 2382-2383."""),
        clarification = LocalizedText("""La objeción más fuerte es que, aun con la separación permitida, la persona sigue vinculada y no puede contraer una nueva unión católica. La respuesta precisa que la nulidad no es un divorcio católico ni significa que toda relación fracasada fuese inválida; puede haber matrimonios válidos que luego se vuelven profundamente dolorosos. En situación de violencia, proteger a la víctima y a los hijos es prioritario, y una nueva unión requiere discernimiento pastoral y canónico específico.""", """The strongest objection is that, even with separation allowed, the person remains bound and cannot enter a new Catholic union. The answer clarifies that nullity is not a Catholic divorce, nor does it mean that every failed relationship was invalid; there can be valid marriages that later become profoundly painful. In a situation of violence, protecting the victim and the children is a priority, and a new union requires specific pastoral and canonical discernment.""", """A objeção mais forte é que, mesmo com a separação permitida, a pessoa continua vinculada e não pode contrair uma nova união católica. A resposta esclarece que a nulidade não é um divórcio católico nem significa que toda relação fracassada fosse inválida; pode haver casamentos válidos que depois se tornam profundamente dolorosos. Em situação de violência, proteger a vítima e os filhos é prioritário, e uma nova união requer discernimento pastoral e canônico específico.""")
    ),
    ApologeticsQuestion(
        number = 77,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué el matrimonio es indisoluble si el amor puede desaparecer?""", """Why is marriage indissoluble if love can disappear?""", """Por que o casamento é indissolúvel, se o amor pode desaparecer?"""),
        answer = LocalizedText("""La respuesta directa es que Jesús presenta el matrimonio como una unión de fidelidad que no depende exclusivamente de los sentimientos cambiantes.

El amor matrimonial incluye decisiones, compromisos y responsabilidades que pueden mantenerse incluso cuando atraviesan dificultades. Por ejemplo, una pareja que, tras el nacimiento de un hijo enfermo o en una etapa de sequedad afectiva, mantiene el respeto mutuo, busca ayuda professional y cumple sus promesas muestra ese amor que perdura más allá de la euforia inicial.

La Iglesia considera que el sacramento establece un vínculo estable entre los esposos.""", """The direct answer is that Jesus presents marriage as a union of fidelity that does not depend exclusively on changing feelings.

Marital love includes decisions, commitments and responsibilities that can be maintained even through difficulties. For example, a couple that, after the birth of a sick child or in a period of emotional dryness, maintains mutual respect, seeks professional help and keeps its promises shows that love which endures beyond the initial excitement.

The Church considers that the sacrament establishes a stable bond between spouses.""", """A resposta direta é que Jesus apresenta o casamento como uma união de fidelidade que não depende exclusivamente de sentimentos mutáveis.

O amor matrimonial inclui decisões, compromissos e responsabilidades que podem ser mantidos mesmo em meio a dificuldades. Por exemplo, um casal que, após o nascimento de um filho doente ou em uma fase de aridez afetiva, mantém o respeito mútuo, busca ajuda profissional e cumpre suas promessas mostra esse amor que perdura além da euforia inicial.

A Igreja considera que o sacramento estabelece um vínculo estável entre os cônjuges."""),
        foundation = LocalizedText("""Mateo 19,3-9; Marcos 10,2-12; 1 Corintios 7,10-11; Efesios 5,25-33; Catecismo, 1644-1651.""", """Matthew 19:3-9; Mark 10:2-12; 1 Corinthians 7:10-11; Ephesians 5:25-33; Catechism, 1644-1651.""", """Mateus 19,3-9; Marcos 10,2-12; 1 Coríntios 7,10-11; Efésios 5,25-33; Catecismo, 1644-1651."""),
        clarification = LocalizedText("""La objeción más fuerte es que atar a dos personas cuando el amor se ha ido parece contrario a la compasión y puede aprisionar a víctimas en relaciones vacías o dañinas. La respuesta precisa que la permanencia del vínculo no justifica la convivencia obligatoria ante el abuso, que no todas las crisis tienen solución ni que la persona abandonada sea culpable de la ruptura, y que el acompañamiento de personas divorciadas exige evitar respuestas automáticas.""", """The strongest objection is that binding two people together when love is gone seems contrary to compassion and can trap victims in empty or harmful relationships. The answer clarifies that the permanence of the bond does not justify obligatory cohabitation in face of abuse, that not all crises have a solution nor is the abandoned spouse guilty of the breakdown, and that the accompaniment of divorced persons requires avoiding automatic responses.""", """A objeção mais forte é que vincular duas pessoas quando o amor se foi parece contrário à compaixão e pode aprisionar vítimas em relações vazias ou prejudiciais. A resposta esclarece que a permanência do vínculo não justifica a convivência obrigatória diante do abuso, que nem todas as crises têm solução nem a pessoa abandonada é culpada pela ruptura, e que o acompanhamento de pessoas divorciadas exige evitar respostas automáticas.""")
    ),
    ApologeticsQuestion(
        number = 78,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia cuestiona determinadas concepciones de identidad de género?""", """Why does the Church question certain conceptions of gender identity?""", """Por que a Igreja questiona determinadas concepções de identidade de gênero?"""),
        answer = LocalizedText("""La respuesta directa es que la antropología católica considera que el cuerpo forma parte constitutiva de la persona y que la diferencia sexual posee un significado humano y relacional importante, sin entender el cuerpo como un objeto completamente separado de la identidad personal.

Desde esta perspectiva, algunas concepciones que desvinculan radicalmente identidad y realidad corporal plantean dificultades teológicas. Por ejemplo, si una persona transgénero busca acompañamiento psicológico desde un respeto que no la humille, la doctrina exige tratarla con dignidad, escuchar su experiencia y evitar todo maltrato, aunque persistan discrepancias antropológicas.""", """The direct answer is that Catholic anthropology considers that the body is a constitutive part of the person and that sexual difference has an important human and relational significance, without understanding the body as an object completely separate from personal identity.

From this perspective, some conceptions that radically disconnect identity and bodily reality raise theological difficulties. For example, if a transgender person seeks psychological support with respect that does not humiliate them, the doctrine demands treating them with dignity, listening to their experience and avoiding all mistreatment, even though anthropological disagreements remain.""", """A resposta direta é que a antropologia católica considera que o corpo faz parte constitutiva da pessoa e que a diferença sexual possui um significado humano e relacional importante, sem entender o corpo como um objeto completamente separado da identidade pessoal.

Dessa perspectiva, algumas concepções que desvinculam radicalmente identidade e realidade corporal apresentam dificuldades teológicas. Por exemplo, se uma pessoa transgênero busca acompanhamento psicológico com um respeito que não a humilhe, a doutrina exige tratá-la com dignidade, ouvir sua experiência e evitar qualquer mau-trato, mesmo com discrepâncias antropológicas persistentes."""),
        foundation = LocalizedText("""Génesis 1,27; 1 Corintios 6,19-20; Catecismo, 364-365; Dignitas infinita, 55-60.""", """Genesis 1:27; 1 Corinthians 6:19-20; Catechism, 364-365; Dignitas infinita, 55-60.""", """Gênesis 1,27; 1 Coríntios 6,19-20; Catecismo, 364-365; Dignitas infinita, 55-60."""),
        clarification = LocalizedText("""La objeción más fuerte es que la vida de las personas transgénero no puede reducirse a una teoría filosófica: sus experiencias, necesidades de salud, dignidad y derechos deben tomarse en serio. La respuesta católica precisa que la doctrina no autoriza el maltrato, la humillación ni la discriminación injusta, que no todos los casos médicos o psicológicos son idénticos, y que requieren acompañamiento individual y competencia profesional.""", """The strongest objection is that the lives of transgender people cannot be reduced to a philosophical theory: their experiences, health needs, dignity and rights must be taken seriously. The Catholic answer clarifies that the doctrine does not authorize mistreatment, humiliation or unjust discrimination, that not all medical or psychological cases are identical, and that they require individual support and professional competence.""", """A objeção mais forte é que a vida das pessoas transgênero não pode ser reduzida a uma teoria filosófica: suas experiências, necessidades de saúde, dignidade e direitos devem ser levados a sério. A resposta católica esclarece que a doutrina não autoriza o mau-trato, a humilhação nem a discriminação injusta, que nem todos os casos médicos ou psicológicos são idênticos e que requerem acompanhamento individual e competência profissional.""")
    ),
    ApologeticsQuestion(
        number = 79,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué la Iglesia considera moralmente desordenada la masturbación?""", """Why does the Church consider masturbation morally disordered?""", """Por que a Igreja considera moralmente desordenada a masturbação?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia comprende la sexualidad dentro de una vocación a la entrega personal, a la integración de los afectos y al amor.

Desde esa antropología, considera que el ejercicio deliberado de la sexualidad separado de su significado conyugal no alcanza el ideal moral que propone. Por ejemplo, un adolescente que utiliza el cuerpo de forma impulsiva, sin vínculo de entrega ni integración afectiva, ilustra esa separación del ideal propuesto, sin que ello autorice a tratarlo con desdén ni vergüenza.""", """The direct answer is that the Church understands sexuality within a vocation to personal self-giving, the integration of the affections, and love.

From that anthropology, it considers that the deliberate exercise of sexuality separated from its conjugal meaning does not reach the moral ideal it proposes. For example, an adolescent who uses the body impulsively, without a bond of self-giving or affective integration, illustrates that separation from the proposed ideal, without this authorizing contempt or shame toward them.""", """A resposta direta é que a Igreja compreende a sexualidade dentro de uma vocação à entrega pessoal, à integração dos afetos e ao amor.

Dessa antropologia, considera que o exercício deliberado da sexualidade separado de seu significado conjugal não alcança o ideal moral que propõe. Por exemplo, um adolescente que usa o corpo de forma impulsiva, sem vínculo de entrega nem integração afetiva, ilustra essa separação do ideal proposto, sem que isso autorize o desdém ou a vergonha em relação a ele."""),
        foundation = LocalizedText("""Mateo 5,27-28; 1 Corintios 6,12-20; Catecismo, 2352.""", """Matthew 5:27-28; 1 Corinthians 6:12-20; Catechism, 2352.""", """Mateus 5,27-28; 1 Coríntios 6,12-20; Catecismo, 2352."""),
        clarification = LocalizedText("""La objeción más fuerte es textual: la Biblia no contiene un versículo que nombre explícitamente la masturbación y la prohíba, y es incorrecto usar el relato de Onán como si fuera una prohibición textual directa. La respuesta católica lo reconoce y añade que el Catecismo admite que factores como inmadurez afectiva, hábitos arraigados, angustia u otras condiciones pueden disminuir la responsabilidad subjetiva, y que el tema debe explicarse sin fomentar vergüenza obsesiva, ansiedad religiosa o desprecio por el propio cuerpo.""", """The strongest objection is textual: the Bible contains no verse that explicitly names masturbation and prohibits it, and it is incorrect to use the story of Onan as if it were a direct textual prohibition. The Catholic answer recognizes this and adds that the Catechism admits that factors such as affective immaturity, ingrained habits, anxiety or other conditions can diminish subjective responsibility, and that the topic must be explained without fostering obsessive shame, religious anxiety or contempt for one's own body.""", """A objeção mais forte é textual: a Bíblia não contém um versículo que mencione explicitamente a masturbação e a proíba, e é incorreto usar o relato de Onã como se fosse uma proibição textual direta. A resposta católica reconhece isso e acrescenta que o Catecismo admite que fatores como imaturidade afetiva, hábitos arraigados, angústia ou outras condições podem diminuir a responsabilidade subjetiva, e que o tema deve ser explicado sem fomentar vergonha obsessiva, ansiedade religiosa ou desprezo pelo próprio corpo.""")
    ),
    ApologeticsQuestion(
        number = 80,
        section = LocalizedText("""Sexualidad, matrimonio y diversidad humana""", """Sexuality, marriage and human diversity""", """Sexualidade, casamento e diversidade humana"""),
        question = LocalizedText("""¿Por qué se habla tanto de sexualidad y tan poco de pobreza, corrupción e injusticia?""", """Why is there so much talk about sexuality and so little about poverty, corruption and injustice?""", """Por que se fala tanto de sexualidade e tão pouco de pobreza, corrupção e injustiça?"""),
        answer = LocalizedText("""La respuesta directa es que la enseñanza católica no limita la moral al comportamiento sexual.

La Biblia dedica una enorme atención a la justicia, al trato de los pobres, a la honestidad económica y a la condena de la opresión, y la Iglesia enseña que explotar trabajadores, cometer fraudes, enriquecerse injustamente o abandonar a personas vulnerables son problemas morales gravísimos.

Por ejemplo, un empresario que paga salarios injustos o un responsable que acepta sobornos contradice la fe tanto como quien vive sin respeto en su vida afectiva: la moral cristiana abarca toda la persona.""", """The direct answer is that Catholic teaching does not limit morality to sexual behaviour.

The Bible devotes enormous attention to justice, the treatment of the poor, economic honesty, and the condemnation of oppression, and the Church teaches that exploiting workers, committing fraud, unjustly enriching oneself, or abandoning vulnerable persons are most serious moral problems.

For example, a businessman who pays unfair wages or an official who accepts bribes contradicts faith as much as someone who lives without respect in their affective life: Christian morality embraces the whole person.""", """A resposta direta é que o ensino católico não limita a moral ao comportamento sexual.

A Bíblia dedica enorme atenção à justiça, ao trato dos pobres, à honestidade econômica e à condenação da opressão, e a Igreja ensina que explorar trabalhadores, cometer fraudes, enriquecer-se injustamente ou abandonar pessoas vulneráveis são problemas morais gravíssimos.

Por exemplo, um empresário que paga salários injustos ou um funcionário que aceita subornos contradiz a fé tanto quanto quem vive sem respeito em sua vida afetiva: a moral cristã abrange a pessoa inteira."""),
        foundation = LocalizedText("""Isaías 1,16-17; Amós 5,21-24; Mateo 25,31-46; Santiago 5,1-6; Catecismo, 2401-2463.""", """Isaiah 1:16-17; Amos 5:21-24; Matthew 25:31-46; James 5:1-6; Catechism, 2401-2463.""", """Isaías 1,16-17; Amós 5,21-24; Mateus 25,31-46; Tiago 5,1-6; Catecismo, 2401-2463."""),
        clarification = LocalizedText("""La objeción más fuerte es que, en la práctica de determinadas comunidades, predicaciones o medios religiosos, se habla constantemente de pecados sexuales y se evita la corrupción, el racismo, el abuso de poder o la miseria. Esa crítica a un desequilibrio puede estar plenamente justificada: una catequesis que no presenta de forma equilibrada el Evangelio no es fiel, y la fidelidad católica exige considerar toda la moral cristiana, no solamente sus temas más polémicos.""", """The strongest objection is that, in practice, certain communities, preaching or religious media talk constantly about sexual sins while avoiding corruption, racism, abuse of power or misery. That criticism of an imbalance can be fully justified: a catechesis that does not present the Gospel in a balanced way is not faithful, and Catholic fidelity demands considering the whole of Christian morality, not only its most controversial topics.""", """A objeção mais forte é que, na prática, determinadas comunidades, pregações ou meios religiosos falam constantemente de pecados sexuais e evitam a corrupção, o racismo, o abuso de poder ou a miséria. Essa crítica a um desequilíbrio pode estar plenamente justificada: uma catequese que não apresenta o Evangelho de forma equilibrada não é fiel, e a fidelidade católica exige considerar toda a moral cristã, não apenas seus temas mais polêmicos.""")
    ),
    ApologeticsQuestion(
        number = 81,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""Si la mujer tiene derecho sobre su cuerpo, ¿por qué la Iglesia condena el aborto?""", """If a woman has a right over her own body, why does the Church condemn abortion?""", """Se a mulher tem direito sobre o próprio corpo, por que a Igreja condena o aborto?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia sí reconoce la dignidad de la mujer y su derecho a la integridad corporal, pero también sostiene que el ser humano en desarrollo en el embarazo merece protección moral. Desde la concepción comienza, según la biología, un organismo humano nuevo, y la doctrina católica concluye que no es legítimo eliminarlo directamente (Catecismo, 2270-2275).

Piensa en una intervención médica compleja en la que hay dos pacientes: la madre, que puede expresar su consentimiento y sufrimiento, y un hijo aún no nacido, que no puede hablar por sí mismo. La analogía no resuelve el conflicto, pero ayuda a ver por qué la Iglesia habla de dos vidas vinculadas y no de una sola decisión privada sin consecuencias.

La objeción más fuerte dice: «Si el cuerpo es de la mujer, obligarla a continuar el embarazo es una coacción injusta». La respuesta honesta reconoce que la autonomía corporal, la salud y las circunstancias sociales deben tomarse muy en serio, y que la filosofía discute cómo pasar del hecho biológico a la condición de persona. La Iglesia pide, junto a su postura, apoyo concreto a mujeres embarazadas y familias.""", """The direct answer is that the Church does recognize the dignity of women and their right to bodily integrity, but it also holds that the developing human being in pregnancy deserves moral protection. From conception begins, according to biology, a new human organism, and Catholic doctrine concludes that it is not legitimate to directly eliminate it (Catechism, 2270-2275).

Think of a complex medical intervention in which there are two patients: the mother, who can express her consent and suffering, and a child still unborn, who cannot speak for himself. The analogy does not resolve the conflict, but it helps to see why the Church speaks of two linked lives and not of a single private decision without consequences.

The strongest objection says: "If the body belongs to the woman, forcing her to continue the pregnancy is unjust coercion." The honest answer acknowledges that bodily autonomy, health and social circumstances must be taken very seriously, and that philosophy debates how to move from the biological fact to the condition of personhood. The Church asks, together with its position, for concrete support for pregnant women and families.""", """A resposta direta é que a Igreja sim reconhece a dignidade da mulher e seu direito à integridade corporal, mas também sustenta que o ser humano em desenvolvimento na gravidez merece proteção moral. Desde a concepção começa, segundo a biologia, um novo organismo humano, e a doutrina católica conclui que não é legítimo eliminá-lo diretamente (Catecismo, 2270-2275).

Pense em uma intervenção médica complexa em que há dois pacientes: a mãe, que pode expressar seu consentimento e sofrimento, e um filho ainda não nascido, que não pode falar por si mesmo. A analogia não resolve o conflito, mas ajuda a ver por que a Igreja fala de duas vidas vinculadas e não de uma única decisão privada sem consequências.

A objeção mais forte diz: «Se o corpo é da mulher, obrigá-la a continuar a gravidez é uma coação injusta». A resposta honesta reconhece que a autonomia corporal, a saúde e as circunstâncias sociais devem ser levadas muito a sério, e que a filosofia debate como passar do fato biológico à condição de pessoa. A Igreja pede, junto a sua posição, apoio concreto a mulheres grávidas e famílias."""),
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
        answer = LocalizedText("""La respuesta directa es que para la Iglesia una violación es una injusticia gravísima y la víctima no tiene ninguna culpa por la agresión. Al mismo tiempo, sostiene que la dignidad del hijo concebido no depende de cómo ocurrió la concepción, y distingue la culpa del agresor de la inocencia del niño por nacer (Catecismo, 2270-2275).

Imagina una sentencia judicial que condena al agresor con toda severidad, protege a la víctima y, a la vez, afirma que el niño nacido de ese delito tiene derechos propios. La comparación ayuda a ver la lógica: castigar el mal no exige eliminar a quien no eligió nacer.

La objeción más fuerte dice: «Obligar a continuar un embarazo tras una violencia añade un segundo daño a la víctima». La Iglesia reconoce la gravedad del sufrimiento y exige seguridad, atención médica y psicológica, protección jurídica y acompañamiento sin culpabilización, especialmente si la víctima es menor. Admite que este argumento no elimina el conflicto ni la tragedia, y que ninguna respuesta católica debe convertirse en reproche contra la víctima.""", """The direct answer is that for the Church a rape is a most grave injustice and the victim bears no blame for the aggression. At the same time, it holds that the dignity of the conceived child does not depend on how the conception occurred, and it distinguishes the guilt of the aggressor from the innocence of the child to be born (Catechism, 2270-2275).

Imagine a court judgment that severely punishes the aggressor, protects the victim and, at the same time, affirms that a child born from that crime has his or her own rights. The comparison helps to see the logic: punishing evil does not require eliminating someone who did not choose to be born.

The strongest objection says: "Forcing a pregnancy to continue after violence adds a second harm to the victim." The Church acknowledges the seriousness of the suffering and demands safety, medical and psychological care, legal protection and support without blame, especially if the victim is a minor. It admits that this argument does not eliminate the conflict or the tragedy, and that no Catholic response should ever become a reproach against the victim.""", """A resposta direta é que para a Igreja uma violação é uma injustiça gravíssima e a vítima não tem nenhuma culpa pela agressão. Ao mesmo tempo, sustenta que a dignidade do filho concebido não depende de como ocorreu a concepção, e distingue a culpa do agressor da inocência da criança por nascer (Catecismo, 2270-2275).

Imagine uma sentença judicial que condena o agressor com toda a severidade, protege a vítima e, ao mesmo tempo, afirma que uma criança nascida desse crime tem direitos próprios. A comparação ajuda a ver a lógica: punir o mal não exige eliminar quem não escolheu nascer.

A objeção mais forte diz: «Obrigar a continuar uma gravidez após uma violência acrescenta um segundo dano à vítima». A Igreja reconhece a gravidade do sofrimento e exige segurança, atenção médica e psicológica, proteção jurídica e acompanhamento sem culpabilização, especialmente se a vítima é menor. Admite que este argumento não elimina o conflito nem a tragédia, e que nenhuma resposta católica deve jamais se converter em reproche contra a vítima."""),
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
        answer = LocalizedText("""La respuesta directa es que la teología moral católica distingue entre causar directamente la muerte del hijo como medio o fin, y actuar para salvar la vida de la madre cuando la pérdida fetal ocurre como efecto no buscado. Es el llamado principio del doble efecto: el daño no debe ser querido, aunque pueda ser previsto (Catecismo, 1750-1756).

Piensa en un cirujano que opera un órgano enfermo de la madre para evitar su muerte, sabiendo que el embarazo no podrá continuar. La imagen no convierte cada caso en idéntico —cada diagnóstico tiene sus matices—, pero muestra la diferencia que la Iglesia marca entre provocar la muerte y intentar salvar a quien puede salvarse sin buscar ese resultado.

La objeción más fuerte dice: «Esa distinción entre intención directa y efecto previsto es artificial y no debería guiar decisiones médicas reales». La respuesta honesta admite que los casos son complejos, que los especialistas discrepan en la clasificación y que ninguna fórmula general sustituye el juicio clínico. Ante una emergencia obstétrica, la atención médica inmediata y cualificada es esencial; nunca debe retrasarse una intervención necesaria por una interpretación teológica improvisada.""", """The direct answer is that Catholic moral theology distinguishes between directly causing the child's death as a means or end, and acting to save the mother's life when the fetal loss occurs as an unintended effect. This is the so-called principle of double effect: the harm must not be willed, even if it can be foreseen (Catechism, 1750-1756).

Think of a surgeon operating on the mother's diseased organ to prevent her death, knowing that the pregnancy cannot continue. The image does not make every case identical — each diagnosis has its nuances — but it shows the difference the Church draws between causing death and trying to save who can be saved without seeking that result.

The strongest objection says: "That distinction between direct intention and foreseen effect is artificial and should not guide real medical decisions." The honest answer admits that cases are complex, that specialists disagree on classification and that no general formula replaces clinical judgment. In an obstetric emergency, immediate and qualified medical care is essential; a necessary intervention must never be delayed for an improvised theological interpretation.""", """A resposta direta é que a teologia moral católica distingue entre causar diretamente a morte do filho como meio ou fim e agir para salvar a vida da mãe quando a perda fetal ocorre como efeito não buscado. É o chamado princípio do duplo efeito: o dano não deve ser querido, ainda que possa ser previsto (Catecismo, 1750-1756).

Pense em um cirurgião que opera um órgão enfermo da mãe para evitar sua morte, sabendo que a gravidez não poderá continuar. A imagem não torna cada caso idêntico —cada diagnóstico tem seus matizes—, mas mostra a diferença que a Igreja marca entre provocar a morte e tentar salvar quem pode ser salvo sem buscar esse resultado.

A objeção mais forte diz: «Essa distinção entre intenção direta e efeito previsto é artificial e não deveria guiar decisões médicas reais». A resposta honesta admite que os casos são complexos, que os especialistas discordam na classificação e que nenhuma fórmula geral substitui o julgamento clínico. Diante de uma emergência obstétrica, a atenção médica imediata e qualificada é essencial; nunca se deve atrasar uma intervenção necessária por uma interpretação teológica improvisada."""),
        foundation = LocalizedText("""Romanos 3,8; Catecismo, 1750-1756 y 2270-2275.""", """Romans 3:8; Catechism, 1750-1756 and 2270-2275.""", """Romanos 3,8; Catecismo, 1750-1756 e 2270-2275."""),
        clarification = LocalizedText("""Los casos médicos reales son complejos y no todos reciben la misma clasificación entre especialistas católicos.

No debe improvisarse una respuesta clínica a partir de una fórmula moral general.

Ante una emergencia obstétrica, la atención médica inmediata y cualificada es esencial; nunca debe retrasarse una intervención necesaria por una interpretación teológica improvisada.""", """Real medical cases are complex, and not all receive the same classification among Catholic specialists.

A clinical response must not be improvised from a general moral formula.

In an obstetric emergency, immediate and qualified medical care is essential; a necessary intervention must never be delayed for an improvised theological interpretation.""", """Os casos médicos reais são complexos e nem todos recebem a mesma classificação entre especialistas católicos.

Não se deve improvisar uma resposta clínica a partir de uma fórmula moral geral.

Diante de uma emergência obstétrica, o atendimento médico imediato e qualificado é essencial; nunca se deve atrasar uma intervenção necessária por uma interpretação teológica improvisada.""")
    ),
    ApologeticsQuestion(
        number = 84,
        section = LocalizedText("""La vida humana, la justicia, la guerra y la moral social""", """Human life, justice, war and social morality""", """A vida humana, a justiça, a guerra e a moral social"""),
        question = LocalizedText("""¿Por qué la Iglesia se opone a la eutanasia voluntaria?""", """Why does the Church oppose voluntary euthanasia?""", """Por que a Igreja se opõe à eutanásia voluntária?"""),
        answer = LocalizedText("""La respuesta directa es que la Iglesia considera que la dignidad humana no depende de la autonomía, la productividad, la ausencia de dolor o la percepción de calidad de vida. Por eso rechaza provocar deliberadamente la muerte de una persona para terminar con su sufrimiento, y defiende en cambio los cuidados paliativos, el alivio del dolor y la posibilidad de rechazar tratamientos desproporcionados que solo prolongan con gravedad el proceso de morir (Catecismo, 2276-2279).

Piensa en un equipo de hospital que, ante un paciente con cáncer avanzado, intensifica los analgésicos y acompaña a la familia, en lugar de buscar su muerte anticipada. La escena ayuda a distinguir dos objetivos: aliviar el sufrimiento sin convertir la muerte en el tratamiento.

La objeción más fuerte dice: «Respetar la autonomía debería permitir decidir el momento de la propia muerte, sobre todo ante sufrimiento extremo». La respuesta honesta reconoce que el deseo de morir ante un dolor insoportable no debe trivializarse y que el debate sobre autonomía y dignidad es éticamente serio. La Iglesia pide atención médica, acompañamiento humano y respeto a la persona, con fidelidad a su convicción de que toda vida tiene valor.""", """The direct answer is that the Church considers that human dignity does not depend on autonomy, productivity, absence of pain or perceived quality of life. That is why it rejects deliberately causing a person's death to end their suffering, and instead defends palliative care, pain relief and the possibility of refusing disproportionate treatments that only gravely prolong the dying process (Catechism, 2276-2279).

Think of a hospital team that, faced with a patient with advanced cancer, intensifies painkillers and accompanies the family, instead of seeking anticipated death. The scene helps to distinguish two aims: relieving suffering without making death the treatment.

The strongest objection says: "Respecting autonomy should allow deciding the moment of one's own death, especially in the face of extreme suffering." The honest answer acknowledges that the desire to die in the face of unbearable pain must not be trivialized and that the debate on autonomy and dignity is ethically serious. The Church asks for medical care, human accompaniment and respect for the person, with fidelity to its conviction that every life has value.""", """A resposta direta é que a Igreja considera que a dignidade humana não depende da autonomia, da produtividade, da ausência de dor ou da percepção de qualidade de vida. Por isso, rejeita provocar deliberadamente a morte de uma pessoa para pôr fim ao seu sofrimento e defende, em vez disso, os cuidados paliativos, o alívio da dor e a possibilidade de recusar tratamentos desproporcionados que só prolongam gravosamente o processo de morrer (Catecismo, 2276-2279).

Pense em uma equipe hospitalar que, diante de um paciente com câncer avançado, intensifica os analgésicos e acompanha a família, em vez de buscar sua morte antecipada. A cena ajuda a distinguir dois objetivos: aliviar o sofrimento sem converter a morte no tratamento.

A objeção mais forte diz: «Respeitar a autonomia deveria permitir decidir o momento da própria morte, sobretudo diante de sofrimento extremo». A resposta honesta reconhece que o desejo de morrer diante de uma dor insuportável não deve ser banalizado e que o debate sobre autonomia e dignidade é eticamente sério. A Igreja pede atenção médica, acompanhamento humano e respeito à pessoa, com fidelidade à sua convicção de que toda vida tem valor."""),
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
        answer = LocalizedText("""La respuesta directa es sí: un católico puede ser policía o militar, porque la Iglesia no considera ilícitas por naturaleza esas profesiones. Distingue entre el homicidio injusto y el uso necesario y proporcionado de la fuerza para proteger a personas frente a una agresión grave e injusta, y enseña que la legítima defensa puede ser incluso un deber (Catecismo, 2263-2265).

Piensa en un agente que se interpone entre un agresor armado y un grupo de escolares, y emplea la fuerza mínima para detener el ataque. La imagen muestra la lógica: el peligro no está en el uniforme, sino en si la fuerza es justa, necesaria y proporcionada. Juan Bautista no exige a los soldados que abandonen su oficio, sino que no extorsionen ni abusen de su poder (Lucas 3,14).

La objeción más fuerte dice: «Quien está entrenado para matar siempre terminará justificando la violencia». La respuesta honesta reconoce que la obediencia no justifica ejecuciones arbitrarias, tortura, ataques a inocentes ni órdenes manifiestamente injustas. La vocación policial o militar queda condicionada a respetar la dignidad humana y los límites morales.""", """The direct answer is yes: a Catholic can be a policeman or soldier, because the Church does not consider those professions illicit by nature. It distinguishes between unjust homicide and the necessary and proportionate use of force to protect people from grave and unjust aggression, and teaches that legitimate defense can even be a duty (Catechism, 2263-2265).

Think of an officer who interposes himself between an armed aggressor and a group of schoolchildren, and uses the minimum force to stop the attack. The image shows the logic: the danger is not in the uniform, but in whether the force is just, necessary and proportionate. John the Baptist does not demand that soldiers abandon their profession, but that they not extort or abuse their power (Luke 3:14).

The strongest objection says: "Anyone trained to kill will always end up justifying violence." The honest answer acknowledges that obedience does not justify arbitrary executions, torture, attacks on the innocent or manifestly unjust orders. The police or military vocation remains conditioned on respecting human dignity and moral limits.""", """A resposta direta é sim: um católico pode ser policial ou militar, porque a Igreja não considera ilícitas por natureza essas profissões. Distingue entre o homicídio injusto e o uso necessário e proporcionado da força para proteger pessoas contra uma agressão grave e injusta, e ensina que a legítima defesa pode chegar a ser um dever (Catecismo, 2263-2265).

Pense em um agente que se interpõe entre um agressor armado e um grupo de escolares e emprega a força mínima para deter o ataque. A imagem mostra a lógica: o perigo não está no uniforme, mas em se a força é justa, necessária e proporcionada. João Batista não exige que os soldados abandonem seu ofício, mas que não extorqam nem abusem de seu poder (Lucas 3,14).

A objeção mais forte diz: «Quem é treinado para matar sempre acabará justificando a violência». A resposta honesta reconhece que a obediência não justifica execuções arbitrárias, tortura, ataques a inocentes nem ordens manifestamente injustas. A vocação policial ou militar fica condicionada a respeitar a dignidade humana e os limites morais."""),
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
        answer = LocalizedText("""La respuesta directa es que la Iglesia considera que la paz es el objetivo moral y que la guerra es una tragedia que debe evitarse. Sin embargo, distingue entre la renuncia personal a la venganza, que Jesús enseña en el sermón del monte, y la responsabilidad de una autoridad de proteger a una población frente a una agresión injusta (Mateo 5,38-44; Romanos 13,4).

Imagina a una ciudad que intenta todos los frentes diplomáticos y, tras ser atacada, valora defenderse solo si hay daño grave y cierto, como último recurso, con posibilidades serias de éxito y con medios proporcionados. Ese contraste ayuda a entender por qué la tradición católica habla de guerra justa con condiciones rigurosas (Catecismo, 2307-2317), y por qué la mayoría de las guerras reales no las cumplen.

La objeción más fuerte dice: «La enseñanza de la otra mejilla hace cualquier guerra injustificable». La respuesta honesta admite que la guerra moderna plantea enormes dificultades de proporcionalidad y protección de civiles, y que no basta con que un gobierno la declare justa. La Iglesia exige agotar las vías diplomáticas y respetar la dignidad humana incluso durante el conflicto.""", """The direct answer is that the Church considers peace the moral goal and war a tragedy that must be avoided. However, it distinguishes between the personal renunciation of vengeance, which Jesus teaches in the Sermon on the Mount, and the responsibility of an authority to protect a population from unjust aggression (Matthew 5:38-44; Romans 13:4).

Imagine a city that tries every diplomatic front and, after being attacked, considers defending itself only if there is grave and certain harm, as a last resort, with serious chances of success and with proportionate means. That contrast helps to understand why the Catholic tradition speaks of just war with rigorous conditions (Catechism, 2307-2317), and why most real wars do not meet them.

The strongest objection says: "The teaching of the other cheek makes any war unjustifiable." The honest answer admits that modern war raises enormous difficulties of proportionality and protection of civilians, and that it is not enough for a government to declare it just. The Church requires exhausting diplomatic ways and respecting human dignity even during conflict.""", """A resposta direta é que a Igreja considera que a paz é o objetivo moral e que a guerra é uma tragédia que deve ser evitada. No entanto, distingue entre a renúncia pessoal à vingança, ensinada por Jesus no sermão do monte, e a responsabilidade de uma autoridade de proteger uma população contra uma agressão injusta (Mateus 5,38-44; Romanos 13,4).

Imagine uma cidade que tenta todas as frentes diplomáticas e, após ser atacada, avalia defender-se só se houver dano grave e certo, como último recurso, com sérias possibilidades de êxito e com meios proporcionados. Esse contraste ajuda a entender por que a tradição católica fala de guerra justa com condições rigorosas (Catecismo, 2307-2317) e por que a maioria das guerras reais não as cumpre.

A objeção mais forte diz: «O ensino da outra face torna qualquer guerra injustificável». A resposta honesta admite que a guerra moderna apresenta enormes dificuldades de proporcionalidade e proteção de civis, e que não basta que um governo a declare justa. A Igreja exige esgotar as vias diplomáticas e respeitar a dignidade humana mesmo durante o conflito."""),
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
        answer = LocalizedText("""La respuesta directa es que la Iglesia reconoce un cambio real en la formulación: antes, autoridades y teólogos católicos consideraron admisible la pena capital bajo ciertas condiciones, y hoy el Catecismo la enseña como inadmisible y promueve su abolición (Catecismo, 2267). La explicación oficial habla de un desarrollo doctrinal a la luz de una comprensión más profunda de la dignidad humana y de la capacidad del Estado para proteger sin matar.

Piensa en una regla escolar que antes permitía un castigo severo y que, tras décadas de reflexión y tras comprobar que existían alternativas capaces de mantener el orden, la comunidad decide abolirlo. La comparación no agota el tema, pero muestra cómo una norma puede cambiar sin que la Iglesia afirme que siempre enseñó lo mismo.

La objeción más fuerte dice: «Si la doctrina puede cambiar en algo tan grave, ninguna enseñanza moral es segura». La respuesta honesta admite que no sería honesto presentar la postura antigua y la actual como idénticas, y que este caso sirve para debatir la continuidad y el desarrollo de la doctrina. La Iglesia presenta el cambio como un desarrollo doctrinal, no como si nunca hubiera admitido la pena de muerte.""", """The direct answer is that the Church recognizes a real change in formulation: in the past, Catholic authorities and theologians considered the death penalty admissible under certain conditions, and today the Catechism teaches it as inadmissible and promotes its abolition (Catechism, 2267). The official explanation speaks of doctrinal development in light of a deeper understanding of human dignity and of the State's capacity to protect without killing.

Think of a school rule that once allowed a severe punishment and that, after decades of reflection and after confirming that alternatives existed capable of maintaining order, the community decides to abolish it. The comparison does not exhaust the subject, but it shows how a norm can change without the Church affirming that it always taught the same thing.

The strongest objection says: "If doctrine can change on something so grave, no moral teaching is secure." The honest answer admits that it would not be honest to present the old and current positions as identical, and that this case serves to debate the continuity and development of doctrine. The Church presents the change as doctrinal development, not as if it had never admitted the death penalty.""", """A resposta direta é que a Igreja reconhece uma mudança real na formulação: antes, autoridades e teólogos católicos consideraram admissível a pena capital sob certas condições, e hoje o Catecismo a ensina como inadmissível e promove sua abolição (Catecismo, 2267). A explicação oficial fala de desenvolvimento doutrinário à luz de uma compreensão mais profunda da dignidade humana e da capacidade do Estado de proteger sem matar.

Pense em uma regra escolar que antes permitia um castigo severo e que, após décadas de reflexão e após comprobar que existiam alternativas capazes de manter a ordem, a comunidade decide aboli-lo. A comparação não esgota o tema, mas mostra como uma norma pode mudar sem que a Igreja afirme que sempre ensinou a mesma coisa.

A objeção mais forte diz: «Se a doutrina pode mudar em algo tão grave, nenhum ensino moral é seguro». A resposta honesta admite que não seria honesto apresentar a postura antiga e a atual como idênticas, e que este caso serve para debater a continuidade e o desenvolvimento da doutrina. A Igreja apresenta a mudança como desenvolvimento doutrinário, e não como se nunca tivesse admitido a pena de morte."""),
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
        answer = LocalizedText("""La respuesta directa es que pertenecer a una religión no garantiza vivir según sus principios. La fe cristiana contiene fundamentos fuertes de igualdad —todos creados a imagen de Dios (Génesis 1,27), unidad en Cristo (Gálatas 3,28)— y fueron usados por cristianos para combatir la esclavitud, pero también hubo cristianos que justificaron sistemas esclavistas y discriminatorios. La contradicción debe reconocerse sin retoques.

Piensa en una escuela cuyas normas prohíben el acoso y, aun así, algunos alumnos acosaron y otros defendieron a los agredidos. La imagen ayuda a distinguir dos planos: la enseñanza que proclama la dignidad y la fidelidad histórica, a veces infiel, de quienes dicen seguirla.

La objeción más fuerte dice: «Si la Iglesia acompañó la esclavitud y el racismo, ¿qué credibilidad tiene su palabra sobre la dignidad?». La respuesta honesta admite las ambigüedades institucionales y exige estudiar con precisión las posiciones históricas. La apologética responsable no deduce la verdad de una doctrina de la conducta perfecta de sus seguidores, ni oculta sus fracasos.""", """The direct answer is that belonging to a religion does not guarantee living according to its principles. Christian faith contains strong foundations of equality — all created in God's image (Genesis 1:27), unity in Christ (Galatians 3:28) — and these were used by Christians to fight slavery, but there were also Christians who justified slave and discriminatory systems. The contradiction must be acknowledged without retouching.

Think of a school whose rules forbid bullying and, even so, some students bullied and others defended the victims. The image helps to distinguish two levels: the teaching that proclaims dignity and the historical fidelity, at times unfaithful, of those who claim to follow it.

The strongest objection says: "If the Church accompanied slavery and racism, what credibility does its word on dignity have?" The honest answer admits the institutional ambiguities and demands precise study of historical positions. Responsible apologetics does not deduce the truth of a doctrine from the perfect conduct of its followers, nor does it hide their failures.""", """A resposta direta é que pertencer a uma religião não garante viver de acordo com seus princípios. A fé cristã contém fundamentos fortes de igualdade —todos criados à imagem de Deus (Gênesis 1,27), unidade em Cristo (Gálatas 3,28)— e foram usados por cristãos para combater a escravidão, mas também houve cristãos que justificaram sistemas escravistas e discriminatórios. A contradição deve ser reconhecida sem retoques.

Pense em uma escola cujas normas proíbem o assédio e, ainda assim, alguns alunos assediaram e outros defenderam os agredidos. A imagem ajuda a distinguir dois planos: o ensino que proclama a dignidade e a fidelidade histórica, por vezes infiel, de quem diz segui-lo.

A objeção mais forte diz: «Se a Igreja acompanhou a escravidão e o racismo, que credibilidade tem sua palavra sobre a dignidade?». A resposta honesta admite as ambiguidades institucionais e exige estudar com precisão as posições históricas. A apologética responsável não deduz a verdade de uma doutrina da conduta perfeita de seus seguidores, nem oculta seus fracassos."""),
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
        answer = LocalizedText("""La respuesta directa es que la Iglesia no enseña que poseer bienes sea por sí mismo pecado, pero advierte seriamente contra la avaricia, la explotación y la indiferencia ante los pobres. La riqueza puede convertirse en obstáculo espiritual cuando ocupa el lugar de Dios o produce insensibilidad ante el sufrimiento ajeno (Marcos 10,17-27; Santiago 5,1-6).

Piensa en dos personas con la misma fortuna: una la administra cuidando los salarios justos y compartiendo con su comunidad, y otra acumula sin mirar a sus trabajadores ni a los pobres. La comparación muestra por qué la moral católica examina cómo se obtiene, cómo se administra y qué responsabilidad existe frente a la comunidad y a las personas vulnerables (Catecismo, 2401-2406).

La objeción más fuerte dice: «Con donar una pequeña parte de la fortuna basta para legitimar cualquier acumulación». La respuesta honesta reconoce que no basta con una limosna ocasional: el derecho a la propiedad está subordinado a la justicia y al destino universal de los bienes, y el destino de una fortuna debe juzgarse por su origen, su administración y sus efectos.""", """The direct answer is that the Church does not teach that possessing goods is by itself a sin, but it seriously warns against greed, exploitation and indifference toward the poor. Wealth can become a spiritual obstacle when it takes God's place or produces insensitivity toward others' suffering (Mark 10:17-27; James 5:1-6).

Think of two people with the same fortune: one administers it caring for just wages and sharing with the community, and another accumulates without regard for workers or the poor. The comparison shows why Catholic morality examines how wealth is obtained, how it is administered and what responsibility exists toward the community and vulnerable people (Catechism, 2401-2406).

The strongest objection says: "Donating a small part of a fortune is enough to justify any accumulation." The honest answer acknowledges that an occasional alms is not enough: the right to property is subordinated to justice and to the universal destination of goods, and the destiny of a fortune must be judged by its origin, its administration and its effects.""", """A resposta direta é que a Igreja não ensina que possuir bens seja por si mesmo pecado, mas adverte seriamente contra a avareza, a exploração e a indiferença diante dos pobres. A riqueza pode se converter em obstáculo espiritual quando ocupa o lugar de Deus ou produz insensibilidade ante o sofrimento alheio (Marcos 10,17-27; Tiago 5,1-6).

Pense em duas pessoas com a mesma fortuna: uma a administra cuidando dos salários justos e compartilhando com sua comunidade, e outra acumula sem olhar para seus trabalhadores nem para os pobres. A comparação mostra por que a moral católica examina como se obtém a riqueza, como se administra e qual responsabilidade existe diante da comunidade e das pessoas vulneráveis (Catecismo, 2401-2406).

A objeção mais forte diz: «Basta doar uma pequena parte da fortuna para legitimar qualquer acumulação». A resposta honesta reconhece que não basta uma esmola ocasional: o direito à propriedade está subordinado à justiça e ao destino universal dos bens, e o destino de uma fortuna deve ser julgado por sua origem, sua administração e seus efeitos."""),
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
        answer = LocalizedText("""La respuesta directa es que el cristianismo considera la atención a las personas necesitadas una obligación moral, no una actividad secundaria. Jesús identifica el servicio a los más vulnerables con el amor dirigido a Él (Mateo 25,31-46), y la Iglesia reconoce deberes de solidaridad, hospitalidad y protección de la dignidad, también del extranjero y del refugiado (Catecismo, 2241).

Piensa en una familia que recibe en su casa a alguien que acaba de llegar sin nada, le ofrece comida y lo ayuda a regularizar su situación. La escena no resuelve el diseño de las políticas, pero muestra el núcleo de la enseñanza: el pobre y el migrante no son un problema abstracto, sino personas a las que se debe dignidad.

La objeción más fuerte dice: «Si la Iglesia no ofrece una política migratoria concreta, su enseñanza social es inútil en la práctica». La respuesta honesta admite que la doctrina no prescribe un modelo económico único para todos los países y que los Estados pueden establecer regulaciones legítimas. Dos católicos pueden discrepar sobre medidas concretas, pero ninguno puede descartar la dignidad del extranjero, del refugiado o de la persona pobre.""", """The direct answer is that Christianity considers care for people in need a moral obligation, not a secondary activity. Jesus identifies service to the most vulnerable with love directed toward Him (Matthew 25:31-46), and the Church recognizes duties of solidarity, hospitality and protection of dignity, also of the foreigner and the refugee (Catechism, 2241).

Think of a family that receives in their home someone who has just arrived with nothing, offers food and helps him regularize his situation. The scene does not resolve the design of policies, but it shows the core of the teaching: the poor and the migrant are not an abstract problem, but persons to whom dignity is owed.

The strongest objection says: "If the Church offers no concrete migration policy, its social teaching is useless in practice." The honest answer admits that doctrine prescribes no single economic model for all countries and that States may establish legitimate regulations. Two Catholics may disagree on concrete measures, but neither can disregard the dignity of the foreigner, the refugee or the poor person.""", """A resposta direta é que o cristianismo considera a atenção às pessoas necessitadas uma obrigação moral, não uma atividade secundária. Jesus identifica o serviço prestado aos mais vulneráveis com o amor dirigido a Ele (Mateus 25,31-46), e a Igreja reconhece deveres de solidariedade, hospitalidade e proteção da dignidade, também do estrangeiro e do refugiado (Catecismo, 2241).

Pense em uma família que recebe em sua casa alguém que acaba de chegar sem nada, lhe oferece comida e o ajuda a regularizar sua situação. A cena não resolve o desenho das políticas, mas mostra o núcleo do ensino: o pobre e o migrante não são um problema abstrato, mas pessoas às quais se deve dignidade.

A objeção mais forte diz: «Se a Igreja não oferece uma política migratória concreta, sua doutrina social é inútil na prática». A resposta honesta admite que a doutrina não prescreve um único modelo econômico para todos os países e que os Estados podem estabelecer regulações legítimas. Dois católicos podem discordar sobre medidas concretas, mas nenhum pode descartar a dignidade do estrangeiro, do refugiado ou da pessoa pobre."""),
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
        answer = LocalizedText("""La respuesta directa es que la Biblia no establece una prohibición universal del matrimonio para todos los ministros. Pedro tenía suegra (Mateo 8,14) y san Pablo reconoce que algunos apóstoles estaban casados (1 Corintios 9,5). La disciplina habitual de la Iglesia latina, que elige para el presbiterado a hombres célibes, es una disciplina eclesiástica, no un mandato bíblico universal.

Piensa en dos equipos de un mismo proyecto: uno con todos sus miembros dedicados a tiempo completo y otro con algunos casados que compatibilizan familia y servicio. La imagen ayuda a ver la lógica de la Iglesia latina —disponibilidad especial para el ministerio pastoral—, sin convertir esa disciplina en una prohibición válida para todos los cristianos.

La objeción más fuerte dice: «Si Pedro estaba casado, el celibato sacerdotal es una invención injusta e inferior». La respuesta honesta admite que sería incorrecto afirmar que Dios prohíbe casarse a todo sacerdote: las Iglesias católicas orientales ordenan presbíteros casados y la Iglesia latina admite excepciones. Se trata de una disciplina modificable, no de una prohibición bíblica universal.""", """The direct answer is that the Bible establishes no universal prohibition of marriage for all ministers. Peter had a mother-in-law (Matthew 8:14) and Saint Paul recognizes that some apostles were married (1 Corinthians 9:5). The usual discipline of the Latin Church, which selects celibate men for the presbyterate, is an ecclesiastical discipline, not a universal biblical mandate.

Think of two teams in the same project: one with all its members dedicated full-time and another with some married people who combine family and service. The image helps to see the logic of the Latin Church — special availability for pastoral ministry — without turning that discipline into a prohibition valid for all Christians.

The strongest objection says: "If Peter was married, priestly celibacy is an unjust and inferior invention." The honest answer admits that it would be incorrect to affirm that God forbids every priest to marry: Eastern Catholic Churches ordain married priests and the Latin Church admits exceptions. It is a modifiable discipline, not a universal biblical prohibition.""", """A resposta direta é que a Bíblia não estabelece uma proibição universal do casamento para todos os ministros. Pedro tinha sogra (Mateus 8,14) e São Paulo reconhece que alguns apóstolos eram casados (1 Coríntios 9,5). A disciplina habitual da Igreja latina, que escolhe para o presbiterado homens celibatários, é uma disciplina eclesiástica, não um mandato bíblico universal.

Pense em duas equipes de um mesmo projeto: uma com todos os seus membros dedicados em tempo integral e outra com alguns casados que compatibilizam família e serviço. A imagem ajuda a ver a lógica da Igreja latina —disponibilidade especial para o ministério pastoral—, sem converter essa disciplina em uma proibição válida para todos os cristãos.

A objeção mais forte diz: «Se Pedro era casado, o celibato sacerdotal é uma invenção injusta e inferior». A resposta honesta admite que seria incorreto afirmar que Deus proíbe todo sacerdote de casar-se: as Igrejas católicas orientais ordenam presbíteros casados e a Igreja latina admite exceções. Trata-se de uma disciplina modificável, não de uma proibição bíblica universal."""),
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
        answer = LocalizedText("""La respuesta directa es que la vida consagrada propone una forma particular de seguir a Cristo mediante los consejos evangélicos de pobreza, castidad y obediencia. Jesús reconoce que algunas personas renuncian libremente al matrimonio por el Reino (Mateo 19,10-12), y san Pablo valora una vida célibe dedicada a Dios. La Iglesia considera que tanto el matrimonio como la vida consagrada pueden ser caminos de santidad (Catecismo, 915-933).

Piensa en dos amigas que eligen caminos distintos: una forma una familia y otra dedica su vida a una comunidad religiosa y educativa. La comparación muestra que la Iglesia no presenta el celibato como superioridad de dignidad, sino como vocaciones diferentes, ambas libres, maduras y orientadas al servicio.

La objeción más fuerte dice: «Renunciar al matrimonio implica que la Iglesia desprecia la sexualidad». La respuesta honesta admite que la castidad consagrada debe ser libre y madura, sin presiones ni obediencias abusivas, y que no demuestra que el matrimonio sea malo o inferior en dignidad humana. Es un compromiso espiritual, no un desprecio de la sexualidad.""", """The direct answer is that consecrated life proposes a particular way of following Christ through the evangelical counsels of poverty, chastity and obedience. Jesus acknowledges that some people freely renounce marriage for the Kingdom (Matthew 19:10-12), and Saint Paul values a celibate life dedicated to God. The Church considers that both marriage and consecrated life can be paths of holiness (Catechism, 915-933).

Think of two friends who choose different paths: one forms a family and the other dedicates her life to a religious and educational community. The comparison shows that the Church does not present celibacy as superiority of dignity, but as different vocations, both free, mature and oriented to service.

The strongest objection says: "Renouncing marriage implies that the Church despises sexuality." The honest answer admits that consecrated chastity must be free and mature, without pressures or abusive obedience, and that it does not prove that marriage is bad or inferior in human dignity. It is a spiritual commitment, not a contempt for sexuality.""", """A resposta direta é que a vida consagrada propõe uma forma particular de seguir a Cristo mediante os conselhos evangélicos de pobreza, castidade e obediência. Jesus reconhece que algumas pessoas renunciam livremente ao casamento pelo Reino (Mateus 19,10-12), e São Paulo valoriza uma vida celibatária dedicada a Deus. A Igreja considera que tanto o casamento quanto a vida consagrada podem ser caminhos de santidade (Catecismo, 915-933).

Pense em duas amigas que escolhem caminhos distintos: uma forma uma família e outra dedica sua vida a uma comunidade religiosa e educativa. A comparação mostra que a Igreja não apresenta o celibato como superioridade de dignidade, mas como vocações diferentes, ambas livres, maduras e orientadas ao serviço.

A objeção mais forte diz: «Renunciar ao casamento implica que a Igreja despreza a sexualidade». A resposta honesta admite que a castidade consagrada deve ser livre e madura, sem pressões nem obediência abusiva, e que não demonstra que o casamento seja mau ou inferior em dignidade humana. É um compromisso espiritual, não um desprezo pela sexualidade."""),
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
        answer = LocalizedText("""La respuesta directa es que la Iglesia católica sostiene que no posee autoridad para conferir el sacerdocio ministerial a las mujeres. La explicación oficial se fundamenta en la elección de los Doce por Jesucristo, la práctica histórica recibida y la comprensión sacramental del ministerio, y distingue entre igualdad de dignidad y diversidad de funciones eclesiales (Catecismo, 1577; Ordinatio sacerdotalis, 1994).

Piensa en un equipo con tareas distintas dentro de una misma misión: todos comparten la dignidad del proyecto, pero no todos reciben el mismo oficio. La imagen ayuda a entender la distinción católica entre dignidad y función, aunque no demuestra por sí sola qué funciones corresponden a cada persona.

La objeción más fuerte dice: «Si hay igualdad de dignidad, la exclusión de las mujeres del sacerdocio es discriminación». La respuesta honesta reconoce que la existencia de doce apóstoles varones no constituye por sí sola una demostración lógica de esa exclusión, y que el argumento católico depende de una interpretación de la intención de Cristo y de la autoridad de la Tradición apostólica. El Magisterio enseña que esta posición debe mantenerse definitivamente.""", """The direct answer is that the Catholic Church holds that it does not have authority to confer ministerial priesthood on women. The official explanation is based on Christ's choice of the Twelve, the historical practice received and the sacramental understanding of ministry, and it distinguishes between equality of dignity and diversity of ecclesial functions (Catechism, 1577; Ordinatio sacerdotalis, 1994).

Think of a team with different tasks within the same mission: all share the dignity of the project, but not all receive the same office. The image helps to understand the Catholic distinction between dignity and function, although it does not by itself prove which functions correspond to each person.

The strongest objection says: "If there is equality of dignity, excluding women from the priesthood is discrimination." The honest answer acknowledges that the existence of twelve male apostles does not by itself constitute a logical proof of that exclusion, and that the Catholic argument depends on an interpretation of Christ's intention and on the authority of Apostolic Tradition. The Magisterium teaches that this position must be held definitively.""", """A resposta direta é que a Igreja católica sustenta que não possui autoridade para conferir o sacerdócio ministerial às mulheres. A explicação oficial se fundamenta na escolha dos Doze por Jesus Cristo, na prática histórica recebida e na compreensão sacramental do ministério, e distingue entre igualdade de dignidade e diversidade de funções eclesiais (Catecismo, 1577; Ordinatio sacerdotalis, 1994).

Pense em uma equipe com tarefas distintas dentro de uma mesma missão: todos compartilham a dignidade do projeto, mas nem todos recebem o mesmo ofício. A imagem ajuda a entender a distinção católica entre dignidade e função, embora não demonstre por si só quais funções correspondem a cada pessoa.

A objeção mais forte diz: «Se há igualdade de dignidade, a exclusão das mulheres do sacerdócio é discriminação». A resposta honesta reconhece que a existência de doze apóstolos varões não constitui por si só uma demonstração lógica dessa exclusão, e que o argumento católico depende de uma interpretação da intenção de Cristo e da autoridade da Tradição apostólica. O Magistério ensina que esta posição deve ser mantida definitivamente."""),
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
        answer = LocalizedText("""La respuesta directa es que la autoridad eclesial no significa conocer por experiencia personal todas las situaciones sobre las que se debe enseñar. Una persona puede comprender razonablemente una realidad mediante el estudio, el diálogo y la escucha, aunque no haya vivido cada aspecto, y la Biblia presenta a mujeres con funciones importantes en la misión, como Priscila y Febe (Romanos 16,1-7; Hechos 18,24-26).

Piensa en un comité que asesora a una familia sobre sus dificultades: quienes no han vivido esa situación exacta pueden aportar comprensión valiosa si escuchan con atención. La imagen ayuda a ver que la falta de experiencia personal no impide pensar con seriedad, aunque la experiencia directa aporta un conocimiento que el estudio no sustituye.

La objeción más fuerte dice: «Una jerarquía mayoritariamente masculina no puede comprender ni juzgar las realidades del matrimonio y la familia». La respuesta honesta reconoce que la observación es legítima: la falta de participación suficiente de mujeres y laicos puede empobrecer la comprensión pastoral. La reserva del sacerdocio a los varones no implica que todos los cargos de responsabilidad, administración o asesoramiento deban ser masculinos, y la Iglesia debe escuchar realmente a las familias.""", """The direct answer is that ecclesial authority does not mean knowing by personal experience every situation one must teach about. A person can reasonably understand a reality through study, dialogue and listening, even without having lived every aspect, and the Bible presents women with important functions in the mission, such as Priscilla and Phoebe (Romans 16:1-7; Acts 18:24-26).

Think of a committee advising a family about its difficulties: those who have not lived that exact situation can contribute valuable understanding if they listen attentively. The image helps to see that lack of personal experience does not prevent serious thinking, although direct experience contributes knowledge that study does not replace.

The strongest objection says: "A predominantly male hierarchy cannot understand or judge the realities of marriage and family." The honest answer acknowledges that the observation is legitimate: the lack of sufficient participation by women and laypeople can impoverish pastoral understanding. Reserving the priesthood to men does not imply that all positions of responsibility, administration or advisory must be male, and the Church must truly listen to families.""", """A resposta direta é que a autoridade eclesial não significa conhecer por experiência pessoal todas as situações sobre as quais é preciso ensinar. Uma pessoa pode compreender razoavelmente uma realidade mediante o estudo, o diálogo e a escuta, embora não tenha vivido cada aspecto, e a Bíblia apresenta mulheres com funções importantes na missão, como Priscila e Febe (Romanos 16,1-7; Atos 18,24-26).

Pense em um comitê que assessora uma família sobre suas dificuldades: quem não viveu exatamente essa situação pode aportar compreensão valiosa se escutar com atenção. A imagem ajuda a ver que a falta de experiência pessoal não impede pensar com seriedade, embora a experiência direta aporte um conhecimento que o estudo não substitui.

A objeção mais forte diz: «Uma hierarquia majoritariamente masculina não pode compreender nem julgar as realidades do casamento e da família». A resposta honesta reconhece que a observação é legítima: a falta de participação suficiente de mulheres e leigos pode empobrecer a compreensão pastoral. A reserva do sacerdócio aos varões não implica que todos os cargos de responsabilidade, administração ou assessoramento devam ser masculinos, e a Igreja deve escutar realmente as famílias."""),
        foundation = LocalizedText("""1 Corintios 12,4-27; Romanos 12,4-8; Gálatas 3,28; Romanos 16,1-7; Hechos 18,24-26.""", """1 Corinthians 12:4-27; Romans 12:4-8; Galatians 3:28; Romans 16:1-7; Acts 18:24-26.""", """1 Coríntios 12,4-27; Romanos 12,4-8; Gálatas 3,28; Romanos 16,1-7; Atos 18,24-26."""),
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
        answer = LocalizedText("""La respuesta directa es que la Iglesia distingue entre poseer bienes para fines religiosos, culturales y sociales, y usarlos para el lujo personal o la acumulación egoísta. Una catedral, una biblioteca o una obra de arte pueden tener valor espiritual y cultural además de económico, y no todo el patrimonio relacionado con la Iglesia pertenece al Papa ni puede venderse libremente: existen propietarios, finalidades y restricciones distintos (Catecismo, 2401-2406).

Piensa en una biblioteca municipal que conserva libros antiguos y los pone al servicio de estudiantes sin regalarlos. La imagen ayuda a entender que conservar bienes con finalidad pública no equivale a enriquecimiento privado, aunque conserva abierta la pregunta de si los recursos se administran con transparencia.

La objeción más fuerte dice: «Si Jesús fue pobre, cualquier riqueza vaticana contradice su mensaje». La respuesta honesta reconoce que esto no demuestra que todas las decisiones patrimoniales hayan sido correctas, y que la pregunta por cuánto conservar, vender o dedicar a los pobres es legítima. La Iglesia debe explicar públicamente cómo administra sus recursos, y preservar el patrimonio no puede ser una excusa universal para ignorar necesidades humanas urgentes.""", """The direct answer is that the Church distinguishes between holding goods for religious, cultural and social purposes and using them for personal luxury or selfish accumulation. A cathedral, a library or a work of art can have spiritual and cultural value as well as economic value, and not all patrimony related to the Church belongs to the Pope or can be freely sold: there are different owners, purposes and restrictions (Catechism, 2401-2406).

Think of a municipal library that preserves old books and puts them at the service of students without giving them away. The image helps to understand that preserving goods with a public purpose is not the same as private enrichment, although it leaves open the question of whether resources are administered transparently.

The strongest objection says: "If Jesus was poor, any Vatican wealth contradicts his message." The honest answer acknowledges that this does not prove that all patrimonial decisions have been correct, and that the question of how much to keep, sell or dedicate to the poor is legitimate. The Church must publicly explain how it administers its resources, and preserving patrimony cannot be a universal excuse to ignore urgent human needs.""", """A resposta direta é que a Igreja distingue entre possuir bens para fins religiosos, culturais e sociais e utilizá-los para o luxo pessoal ou a acumulação egoísta. Uma catedral, uma biblioteca ou uma obra de arte podem ter valor espiritual e cultural além do econômico, e nem todo o patrimônio relacionado à Igreja pertence ao Papa nem pode ser vendido livremente: existem proprietários, finalidades e restrições distintos (Catecismo, 2401-2406).

Pense em uma biblioteca municipal que conserva livros antigos e os coloca ao serviço de estudantes sem regalá-los. A imagem ajuda a entender que conservar bens com finalidade pública não equivale a enriquecimento privado, embora deixe aberta a pergunta de se os recursos são administrados com transparência.

A objeção mais forte diz: «Se Jesus foi pobre, qualquer riqueza vaticana contradiz sua mensagem». A resposta honesta reconhece que isso não demonstra que todas as decisões patrimoniais tenham sido corretas, e que a pergunta sobre quanto conservar, vender ou dedicar aos pobres é legítima. A Igreja deve explicar publicamente como administra seus recursos, e preservar o patrimônio não pode ser uma desculpa universal para ignorar necessidades humanas urgentes."""),
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
        answer = LocalizedText("""La respuesta directa es que los miembros de la Iglesia pueden cometer los mismos pecados de codicia, engaño y abuso de poder que cualquier otra persona. Recibir la ordenación sacerdotal no vuelve a nadie incapaz de pecar, y la Biblia denuncia con dureza la corrupción religiosa y el uso de lo sagrado para obtener beneficios personales (Mateo 21,12-13; 1 Timoteo 6,9-10).

Piensa en un administrador de una fundación benéfica que desvía donaciones a su provecho. La escena no sorprende por el hecho de que ocurra en un entorno religioso, sino que muestra por qué los recursos donados para fines caritativos exigen transparencia, controles independientes y rendición de cuentas.

La objeción más fuerte dice: «Si la Iglesia es santa, no debería haber corrupción financiera en su interior». La respuesta honesta reconoce que la corrupción no se justifica diciendo que todos somos pecadores, y que las instituciones eclesiásticas tienen una responsabilidad especialmente alta de transparencia. La necesidad de reforma administrativa puede admitirse sin concluir que todas las personas vinculadas a la Iglesia sean corruptas.""", """The direct answer is that members of the Church can commit the same sins of greed, deceit and abuse of power as any other person. Receiving priestly ordination does not make anyone incapable of sinning, and the Bible harshly denounces religious corruption and the use of sacred things to obtain personal benefits (Matthew 21:12-13; 1 Timothy 6:9-10).

Think of an administrator of a charitable foundation who diverts donations to his own benefit. The scene is not surprising because it occurs in a religious setting, but it shows why resources donated for charitable purposes require transparency, independent controls and accountability.

The strongest objection says: "If the Church is holy, there should be no financial corruption inside it." The honest answer acknowledges that corruption is not justified by saying that we are all sinners, and that ecclesiastical institutions bear a particularly high responsibility for transparency. The need for administrative reform can be admitted without concluding that all people linked to the Church are corrupt.""", """A resposta direta é que os membros da Igreja podem cometer os mesmos pecados de cobiça, engano e abuso de poder que qualquer outra pessoa. Receber a ordenação sacerdotal não torna ninguém incapaz de pecar, e a Bíblia denuncia duramente a corrupção religiosa e a utilização do sagrado para obter benefícios pessoais (Mateus 21,12-13; 1 Timóteo 6,9-10).

Pense em um administrador de uma fundação beneficente que desvia doações para seu próprio proveito. A cena não surpreende por ocorrer em um ambiente religioso, mas mostra por que os recursos doados para fins caritativos exigem transparência, controles independentes e prestação de contas.

A objeção mais forte diz: «Se a Igreja é santa, não deveria haver corrupção financeira em seu interior». A resposta honesta reconhece que a corrupção não se justifica dizendo que todos somos pecadores, e que as instituições eclesiásticas têm uma responsabilidade especialmente alta de transparência. A necessidade de reforma administrativa pode ser admitida sem concluir que todas as pessoas vinculadas à Igreja sejam corruptas."""),
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
        answer = LocalizedText("""La respuesta directa es que Mateo 23,9 debe leerse en el contexto de la crítica de Jesús a la búsqueda de honores y superioridad religiosa, no como una prohibición absoluta de toda paternidad espiritual. La misma Biblia usa ese lenguaje: san Pablo se presenta como padre espiritual de los creyentes de Corinto porque los ha engendrado en Cristo mediante el Evangelio (1 Corintios 4,14-15).

Piensa en un profesor que los antiguos alumnos llaman padre en sentido figurado porque los ha ayudado a formar su criterio, sin que nadie suponga que sea su padre biológico. La comparación ayuda a ver cómo una palabra puede usarse en sentido espiritual y respetuoso sin pretender una autoridad divina independiente.

La objeción más fuerte dice: «Jesús dijo que no llamáramos padre a nadie; llamar padre a un sacerdote desobedece ese mandato». La respuesta honesta admite que, si la prohibición fuese absoluta, también resultaría problemático usar la palabra para el padre biológico o para personajes bíblicos. El razonamiento católico entiende que Jesús condena la vanidad y la pretensión de superioridad, no toda aplicación respetuosa del término, y que usar el título para exigir obediencia abusiva contradice el espíritu del pasaje.""", """The direct answer is that Matthew 23:9 must be read in the context of Jesus' criticism of the search for honors and religious superiority, not as an absolute prohibition of all spiritual paternity. The Bible itself uses that language: Saint Paul presents himself as the spiritual father of the believers of Corinth because he fathered them in Christ through the Gospel (1 Corinthians 4:14-15).

Think of a teacher whom former students call father in a figurative sense because he helped them form their judgment, without anyone supposing that he is their biological father. The comparison helps to see how a word can be used in a spiritual and respectful sense without claiming independent divine authority.

The strongest objection says: "Jesus said to call no one father; calling priests father disobeys that command." The honest answer admits that, if the prohibition were absolute, it would also be problematic to use the word for the biological father or for biblical characters. The Catholic reasoning understands that Jesus condemns vanity and pretension to superiority, not every respectful application of the term, and that using the title to demand abusive obedience contradicts the spirit of the passage.""", """A resposta direta é que Mateus 23,9 deve ser lido no contexto da crítica de Jesus à busca de honras e superioridade religiosa, não como uma proibição absoluta de toda paternidade espiritual. A própria Bíblia usa essa linguagem: São Paulo se apresenta como pai espiritual dos crentes de Corinto porque os gerou em Cristo mediante o Evangelho (1 Coríntios 4,14-15).

Pense em um professor a quem antigos alunos chamam de pai em sentido figurado porque os ajudou a formar seu critério, sem que ninguém suponha que seja seu pai biológico. A comparação ajuda a ver como uma palavra pode ser usada em sentido espiritual e respeitoso sem pretender uma autoridade divina independente.

A objeção mais forte diz: «Jesus disse que não chamássemos pai a ninguém; chamar pai aos sacerdotes desobedece esse mandato». A resposta honesta admite que, se a proibição fosse absoluta, também resultaria problemático empregar a palavra para o pai biológico ou para personagens bíblicos. O raciocínio católico entende que Jesus condena a vaidade e a pretensão de superioridade, não toda aplicação respeitosa do termo, e que usar o título para exigir obediência abusiva contradiz o espírito da passagem."""),
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
        answer = LocalizedText("""La respuesta directa es que Mateo 18,20 afirma la presencia de Cristo cuando sus discípulos se reúnen en su nombre, pero no dice que esa sea la única forma de su presencia ni que todo ministerio y culto comunitario sean innecesarios. El Nuevo Testamento describe comunidades organizadas que celebran la fracción del pan, reciben enseñanza apostólica y reconocen responsables pastorales (Hechos 2,42; 1 Timoteo 3,1-13).

Piensa en una casa donde un amigo puede visitar a la vez que existe un centro de encuentro con horarios, responsables y celebraciones compartidas. La imagen ayuda a ver la diferencia entre la presencia personal de Cristo y la conveniencia de templos y sacerdotes: Dios puede ser encontrado en cualquier lugar, y los templos son espacios dedicados al culto comunitario, no edificios que contengan a Dios.

La objeción más fuerte dice: «Si Cristo está donde dos o tres se reúnen, iglesias y sacerdotes sobran». La respuesta honesta admite que un edificio religioso no es indispensable para toda oración y que el sacerdote no sustituye la relación personal con Dios. La Iglesia sostiene que Cristo quiso también una comunidad sacramental y ministerial, afirmación que requiere considerar el conjunto del Nuevo Testamento y la historia de la Iglesia primitiva.""", """The direct answer is that Matthew 18:20 affirms Christ's presence when his disciples gather in his name, but it does not say that this is the only way of his presence or that every ministry and communal worship is unnecessary. The New Testament describes organized communities that celebrate the breaking of bread, receive apostolic teaching and recognize pastoral leaders (Acts 2:42; 1 Timothy 3:1-13).

Think of a house where a friend can also visit, together with a meeting center with schedules, leaders and shared celebrations. The image helps to see the difference between Christ's personal presence and the usefulness of churches and priests: God can be found anywhere, and churches are spaces dedicated to communal worship, not buildings that contain God.

The strongest objection says: "If Christ is where two or three gather, churches and priests are superfluous." The honest answer admits that a religious building is not indispensable for every prayer and that the priest does not replace the personal relationship with God. The Church holds that Christ also willed a sacramental and ministerial community, an affirmation that requires considering the whole New Testament and the history of the primitive Church.""", """A resposta direta é que Mateus 18,20 afirma a presença de Cristo quando seus discípulos se reúnem em seu nome, mas não diz que essa seja a única forma de sua presença nem que todo ministério e culto comunitário sejam desnecessários. O Novo Testamento descreve comunidades organizadas que celebram a fração do pão, recebem ensino apostólico e reconhecem responsáveis pastorais (Atos 2,42; 1 Timóteo 3,1-13).

Pense em uma casa onde um amigo pode visitar ao mesmo tempo em que existe um centro de encontro com horários, responsáveis e celebrações compartilhadas. A imagem ajuda a ver a diferença entre a presença pessoal de Cristo e a conveniência de templos e sacerdotes: Deus pode ser encontrado em qualquer lugar, e os templos são espaços dedicados ao culto comunitário, não edifícios que contêm Deus.

A objeção mais forte diz: «Se Cristo está onde dois ou três se reúnem, igrejas e sacerdotes sobram». A resposta honesta admite que um edifício religioso não é indispensável para toda oração e que o sacerdote não substitui a relação pessoal com Deus. A Igreja sustenta que Cristo quis também uma comunidade sacramental e ministerial, afirmação que requer considerar o conjunto do Novo Testamento e a história da Igreja primitiva."""),
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
        answer = LocalizedText("""La respuesta directa es que debemos distinguir entre la verdad de una enseñanza y la conducta de quien la comunica. Una persona puede enseñar correctamente que robar es injusto y, sin embargo, ser ladrona; su hipocresía destruye su credibilidad personal, pero no convierte automáticamente en falsa la norma que estaba enseñando. Jesús mismo advierte contra quienes enseñan y no practican lo que predican (Mateo 23,1-4).

Piensa en un médico fumador que explica correctamente los daños del tabaco. Su conducta no hace falsa su explicación médica, aunque debilite su ejemplo y despierte desconfianza. La comparación ayuda a entender por qué la Iglesia sostiene que la enseñanza puede ser verdadera aunque el ministro sea infiel, sin premiar esa infidelidad.

La objeción más fuerte dice: «Si un sacerdote no vive lo que predica, nadie debería aceptar su autoridad moral». La respuesta honesta reconoce que distinguir enseñanza y conducta no significa exigir obediencia ciega: la autoridad religiosa debe estar limitada por la verdad, la justicia, la conciencia y las normas legítimas, y nadie tiene obligación moral de colaborar con un abuso o aceptar una orden injusta. La Iglesia debe formar ministros testigos creíbles de lo que anuncian.""", """The direct answer is that we must distinguish between the truth of a teaching and the conduct of the person who communicates it. A person can correctly teach that stealing is unjust and yet be a thief; their hypocrisy destroys their personal credibility, but it does not automatically make the norm they were teaching false. Jesus himself warns against those who teach and do not practice what they preach (Matthew 23:1-4).

Think of a doctor who smokes and correctly explains the harms of tobacco. His conduct does not make his medical explanation false, although it weakens his example and arouses distrust. The comparison helps to understand why the Church holds that teaching can be true although the minister is unfaithful, without rewarding that unfaithfulness.

The strongest objection says: "If a priest does not live what he preaches, no one should accept his moral authority." The honest answer acknowledges that distinguishing teaching from conduct does not mean demanding blind obedience: religious authority must be limited by truth, justice, conscience and legitimate norms, and no one has a moral obligation to cooperate with abuse or accept an unjust order. The Church must form ministers who are credible witnesses to what they proclaim.""", """A resposta direta é que devemos distinguir entre a verdade de um ensino e a conduta de quem o comunica. Uma pessoa pode ensinar corretamente que roubar é injusto e, no entanto, ser ladra; sua hipocrisia destrói sua credibilidade pessoal, mas não torna automaticamente falsa a norma que estava ensinando. O próprio Jesus adverte contra aqueles que ensinam e não praticam o que pregam (Mateus 23,1-4).

Pense em um médico fumante que explica corretamente os danos do tabaco. Sua conduta não torna falsa sua explicação médica, embora enfraqueça seu exemplo e desperte desconfiança. A comparação ajuda a entender por que a Igreja sustenta que o ensino pode ser verdadeiro ainda que o ministro seja infiel, sem premiar essa infidelidade.

A objeção mais forte diz: «Se um sacerdote não vive o que prega, ninguém deveria aceitar sua autoridade moral». A resposta honesta reconhece que distinguir ensino e conduta não significa exigir obediência cega: a autoridade religiosa deve estar limitada pela verdade, pela justiça, pela consciência e pelas normas legítimas, e ninguém tem obrigação moral de colaborar com um abuso ou aceitar uma ordem injusta. A Igreja deve formar ministros que sejam testemunhas críveis do que anunciam."""),
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
        answer = LocalizedText("""La respuesta directa es que ninguno puede afirmar con certeza qué juicio concreto emitiría Jesús sobre cada institución, persona o práctica actual. Pero los Evangelios ofrecen criterios centrales: misericordia, verdad, fidelidad a Dios, servicio humilde y atención a los pobres, frente a la hipocresía, la explotación religiosa y el uso del poder para oprimir (Mateo 23; Mateo 25,31-46; Marcos 10,42-45).

Imagina una auditoría que evalúa una empresa no por su nombre comercial, sino por su contenido: cómo trata a sus trabajadores, reparte beneficios y sirve a sus clientes. La comparación ayuda a entender la pregunta: más que clasificar estructuras desde fuera, hay que preguntarse si las comunidades viven como discípulas de Jesús.

La objeción más fuerte dice: «Si Jesús hoy denunciaría la riqueza y el poder eclesiástico, nadie debería reconocerse en la Iglesia católica». La respuesta honesta admite que esto no significa que Cristo apruebe toda decisión histórica o contemporánea de las autoridades, y que la pregunta decisiva es si las comunidades viven realmente como discípulos suyos. La Iglesia sostiene que Cristo permanece fiel a su Iglesia y actúa en ella mediante su Espíritu, a pesar de los pecados de sus miembros.""", """The direct answer is that no one can affirm with certainty what concrete judgment Jesus would make on each current institution, person or practice. But the Gospels offer central criteria: mercy, truth, fidelity to God, humble service and attention to the poor, as opposed to hypocrisy, religious exploitation and the use of power to oppress (Matthew 23; Matthew 25:31-46; Mark 10:42-45).

Imagine an audit that evaluates a company not by its commercial name, but by its content: how it treats workers, shares profits and serves clients. The comparison helps to understand the question: more than classifying structures from outside, one must ask whether communities live as disciples of Jesus.

The strongest objection says: "If today Jesus would denounce ecclesiastical wealth and power, no one should recognize themselves in the Catholic Church." The honest answer admits that this does not mean that Christ approves every historical or contemporary decision of the authorities, and that the decisive question is whether communities truly live as his disciples. The Church holds that Christ remains faithful to his Church and acts in it through his Spirit, despite the sins of its members.""", """A resposta direta é que ninguém pode afirmar com certeza que juízo concreto Jesus emitiria sobre cada instituição, pessoa ou prática atual. Mas os Evangelhos oferecem critérios centrais: misericórdia, verdade, fidelidade a Deus, serviço humilde e atenção aos pobres, diante da hipocrisia, da exploração religiosa e do uso do poder para oprimir (Mateus 23; Mateus 25,31-46; Marcos 10,42-45).

Imagine uma auditoria que avalia uma empresa não por seu nome comercial, mas por seu conteúdo: como trata seus trabalhadores, reparte benefícios e serve seus clientes. A comparação ajuda a entender a pergunta: mais que classificar estruturas desde fora, é preciso perguntar-se se as comunidades vivem como discípulas de Jesus.

A objeção mais forte diz: «Se Jesus hoje denunciaria a riqueza e o poder eclesiásticos, ninguém deveria reconhecer-se na Igreja católica». A resposta honesta admite que isso não significa que Cristo aprove toda decisão histórica ou contemporânea das autoridades, e que a pergunta decisiva é se as comunidades vivem realmente como discípulas suas. A Igreja sustenta que Cristo permanece fiel à sua Igreja e atua nela mediante seu Espírito, apesar dos pecados de seus membros."""),
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
