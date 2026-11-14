package com.example.data.repository

import com.example.data.models.GuideCitation
import com.example.data.models.GuidePhase
import com.example.data.models.GuideScripture
import com.example.data.models.LocalizedText
import com.example.data.models.TemptationGuide

/** Long-form guides for ENVY, PRIDE and GREED (editorial rules 1.8). */
object TemptationGuidesPartB {
    val guides: List<TemptationGuide> = listOf(envy, pride, TemptationGuidesPartB2.greed)
}

private val envy = TemptationGuide(
    sinKey = "ENVY",
    summary = LocalizedText(
        "La envidia es la tristeza por el bien del prójimo, vivido como una ofensa a mi propio valor. Hiere la caridad y nos encierra en la comparación. Se sana con la caridad fraterna y la gratitud: alegrarse con el que se alegra y reconocer lo que Dios ya nos ha dado.",
        "Envy is sadness at a neighbour's good, felt as if it were an offence against my own worth. It wounds charity and locks us into comparison. It is healed by fraternal charity and gratitude: rejoicing with those who rejoice and recognising what God has already given us.",
        "A inveja é a tristeza diante do bem do próximo, sentido como se fosse uma ofensa ao meu próprio valor. Fere a caridade e nos prende na comparação. Cura-se com a caridade fraterna e a gratidão: alegrar-se com quem se alegra e reconhecer o que Deus já nos deu."
    ),
    howItWorks = LocalizedText(
        "Santo Tomás la define como tristeza por el bien ajeno, en cuanto lo sentimos como disminución de nuestra propia gloria (Suma Teológica II-II, q. 36, a. 1). No es la sana emulación de un bien (Suma Teológica II-II, q. 36, a. 2), sino dolerse de que el otro lo tenga y, a veces, desearlo desordenadamente para sí (CIC 2539). El Catecismo la describe como un pecado capital que puede llegar a desear al prójimo un mal grave, y recuerda que el décimo mandamiento pide un corazón limpio de ella (CIC 2538-2540). Las ocasiones son muy comunes: el éxito de un compañero, la boda o los hijos de una amiga, el talento de un hermano, y hoy sobre todo las redes sociales, que nos muestran la mejor cara de la vida de todos y nos invitan a medirnos sin descanso.\n\n" +
            "Como toda tentación, avanza por etapas: una sugerencia (un pensamiento de comparación que llega sin pedirlo), la complacencia (darle vueltas, imaginar ventajas y agravios) y el consentimiento (hablar mal, callar un elogio, negar ayuda, alegrarse del tropiezo ajeno). Los Padres del desierto, Evagrio y Casiano, llamaban «pensamientos» (logismoi) a esas sugerencias iniciales: no son pecado mientras no los acogemos, y por eso conviene reconocerlos y nombrarlos pronto, cuando son aún pequeños.\n\n" +
            "San Gregorio Magno enumera las hijas de la envidia: el odio, la murmuración, la detracción, la alegría por la desgracia del prójimo y la aflicción por su prosperidad (Moralia in Job XXXI, 45). La envidia se disfraza de justicia («es que no se lo merece»), de realismo («yo solo digo la verdad») o de humildad herida («yo nunca tengo suerte»). Quien envidia rara vez lo admite; suele hablar de injusticias o de «preocupación» por el otro.",
        "St Thomas defines it as sadness at another's good, insofar as we experience it as a diminishing of our own glory (Summa Theologiae II-II, q. 36, a. 1). It is not the healthy emulation of a good (Summa Theologiae II-II, q. 36, a. 2), but grieving that another has it and, at times, desiring it for oneself in a disordered way (CCC 2539). The Catechism calls it a capital sin that can go as far as wishing grave harm on a neighbour, and notes that the tenth commandment asks for a heart free of it (CCC 2538-2540). Occasions are everyday: a colleague's success, a friend's wedding or children, a sibling's talent, and today above all social media, which shows everyone's best side and invites us to measure ourselves without rest.\n\n" +
            "Like every temptation it advances in stages: a suggestion (a comparing thought that comes unasked), delight or complacency (turning it over, imagining advantages and grievances) and consent (speaking ill, withholding praise, refusing help, rejoicing at another's stumble). The Desert Fathers, Evagrius and Cassian, called those first suggestions logismoi, \"thoughts\": they are not sin while we do not welcome them, which is why it helps to recognise and name them early, while they are still small.\n\n" +
            "St Gregory the Great lists the daughters of envy: hatred, whispering, detraction, joy at a neighbour's misfortune and affliction at his prosperity (Moralia on Job XXXI, 45). Envy disguises itself as justice (\"he doesn't deserve it\"), as realism (\"I'm only telling the truth\") or as wounded humility (\"I never get lucky\"). The envious person rarely admits it; he usually speaks of injustice or of \"concern\" for the other.",
        "Santo Tomás a define como tristeza pelo bem alheio, enquanto o sentimos como diminuição da nossa própria glória (Suma Teológica II-II, q. 36, a. 1). Não é a saudável emulação de um bem (Suma Teológica II-II, q. 36, a. 2), mas entristecer-se por o outro tê-lo e, às vezes, desejá-lo desordenadamente para si (CIC 2539). O Catecismo a descreve como pecado capital que pode chegar a desejar ao próximo um mal grave e lembra que o décimo mandamento pede um coração livre dela (CIC 2538-2540). As ocasiões são comuns: o sucesso de um colega, o casamento ou os filhos de uma amiga, o talento de um irmão e, hoje sobretudo, as redes sociais, que mostram o melhor lado da vida de todos e nos convidam a nos medir sem descanso.\n\n" +
            "Como toda tentação, avança por etapas: a sugestão (um pensamento de comparação que chega sem ser chamado), a complacência (remoê-lo, imaginar vantagens e injustiças) e o consentimento (falar mal, calar um elogio, negar ajuda, alegrar-se com o tropeço alheio). Os Padres do deserto, Evágrio e Cassiano, chamavam essas primeiras sugestões de logismoi, \"pensamentos\": não são pecado enquanto não os acolhemos, por isso convém reconhecê-los e nomeá-los cedo, quando ainda são pequenos.\n\n" +
            "São Gregório Magno enumera as filhas da inveja: o ódio, a murmuração, a detração, a alegria com a desgraça do próximo e a aflição com a sua prosperidade (Moralia in Job XXXI, 45). A inveja se disfarça de justiça (\"ele não merece\"), de realismo (\"só estou dizendo a verdade\") ou de humildade ferida (\"eu nunca tenho sorte\"). Quem inveja raramente admite; costuma falar de injustiça ou de \"preocupação\" com o outro."
    ),
    whyItIsHard = LocalizedText(
        "Es difícil porque se esconde. A diferencia de otros vicios, no promete placer, y por eso nos da vergüenza; lo normal es negarla incluso ante uno mismo. Además crece sobre una herida real: la necesidad de ser valorado y amado, y el miedo a que no haya suficiente para mí. La concupiscencia que queda en nosotros tras el bautismo nos inclina a ello (CIC 405, 2515), y la cultura de la imagen y de la comparación constante alimenta el hábito a diario.\n\n" +
            "Por eso no se vence a fuerza de voluntad sola ni con reproches duros contra uno mismo. Se vence descubriendo de qué necesidad nace (reconocimiento, seguridad, afecto), llevándola a Dios y aprendiendo, poco a poco, a ver al otro como un don y no como un rival.",
        "It is hard because it hides. Unlike other vices it promises no pleasure, so it shames us, and the usual response is to deny it even to ourselves. It also grows on a real wound: the need to be valued and loved, and the fear that there is not enough for me. The concupiscence that remains after baptism inclines us to it (CCC 405, 2515), and a culture of image and constant comparison feeds the habit daily.\n\n" +
            "That is why it is not beaten by willpower alone or by harsh self-reproach. It is beaten by discovering the need it springs from (recognition, security, affection), bringing that need to God, and learning little by little to see the other as a gift rather than a rival.",
        "É difícil porque se esconde. Diferentemente de outros vícios, não promete prazer e por isso nos envergonha; o normal é negá-la até diante de nós mesmos. Além disso cresce sobre uma ferida real: a necessidade de ser valorizado e amado, e o medo de que não haja o bastante para mim. A concupiscência que permanece em nós após o batismo nos inclina a ela (CIC 405, 2515), e a cultura da imagem e da comparação constante alimenta o hábito todos os dias.\n\n" +
            "Por isso ela não se vence só com força de vontade nem com reprovações duras contra si mesmo. Vence-se descobrindo de que necessidade nasce (reconhecimento, segurança, afeto), levando-a a Deus e aprendendo, pouco a pouco, a ver o outro como dom e não como rival."
    ),
    warningSigns = listOf(
        LocalizedText(
            "Sientes un pinchazo o un nudo al oír una buena noticia de alguien concreto, y finges alegría.",
            "You feel a pang or a knot when you hear someone's good news, and you pretend to be glad.",
            "Você sente uma pontada ou um nó ao ouvir a boa notícia de alguém e finge alegria."
        ),
        LocalizedText(
            "Revisas con frecuencia los perfiles o los logros de otra persona y terminas peor que al empezar.",
            "You often check someone's profiles or achievements and end up feeling worse than when you began.",
            "Você confere com frequência os perfis ou conquistas de alguém e termina pior do que começou."
        ),
        LocalizedText(
            "Te cuesta felicitar, o felicitas con un «pero» o con un comentario que le quita brillo.",
            "You find it hard to congratulate, or you congratulate with a \"but\" or a remark that dims the shine.",
            "Você tem dificuldade em felicitar, ou felicita com um \"mas\" ou um comentário que tira o brilho."
        ),
        LocalizedText(
            "Notas un alivio secreto cuando a alguien que te hace sombra le va mal.",
            "You feel a secret relief when someone who outshines you does badly.",
            "Você sente um alívio secreto quando alguém que lhe faz sombra vai mal."
        ),
        LocalizedText(
            "Hablas de los defectos de esa persona con otros o buscas «lo que hay detrás» de su éxito.",
            "You talk about that person's faults with others or look for \"what's behind\" their success.",
            "Você fala dos defeitos dessa pessoa com outros ou procura \"o que há por trás\" do sucesso dela."
        ),
        LocalizedText(
            "Tus oraciones se llenan de quejas («¿y yo, Señor?») y apenas de agradecimiento.",
            "Your prayers fill with complaints (\"and what about me, Lord?\") and hardly any thanks.",
            "Suas orações se enchem de queixas (\"e eu, Senhor?\") e quase nada de gratidão."
        )
    ),
    inTheMoment = listOf(
        LocalizedText(
            "Nómbrala: «esto es envidia». Decirlo con sencillez le quita la fuerza del disfraz.",
            "Name it: \"this is envy.\" Saying it plainly strips it of its disguise.",
            "Dê-lhe nome: \"isto é inveja\". Dizê-lo com simplicidade tira a força do disfarce."
        ),
        LocalizedText(
            "Bendice a la persona en voz baja y pide para ella exactamente aquello que envidias.",
            "Bless the person quietly and ask for them exactly what you envy.",
            "Abençoe a pessoa em voz baixa e peça para ela exatamente aquilo que você inveja."
        ),
        LocalizedText(
            "Da gracias a Dios por tres dones concretos que tú has recibido hoy.",
            "Thank God for three concrete gifts you have received today.",
            "Agradeça a Deus por três dons concretos que você recebeu hoje."
        ),
        LocalizedText(
            "Si puedes, felicita de verdad (un mensaje breve, sin «peros»). El acto precede al sentimiento.",
            "If you can, congratulate sincerely (a short message, no \"buts\"). The act comes before the feeling.",
            "Se puder, felicite de verdade (uma mensagem curta, sem \"mas\"). O ato precede o sentimento."
        ),
        LocalizedText(
            "Cierra la pantalla o cambia de lugar; no sigas comparando.",
            "Close the screen or change place; do not keep comparing.",
            "Feche a tela ou mude de lugar; não continue comparando."
        ),
        LocalizedText(
            "Si estabas a punto de criticar, calla o cambia de tema. Dicho con caridad o no dicho.",
            "If you were about to criticise, stay silent or change the subject. Said with charity or not said.",
            "Se ia criticar, cale ou mude de assunto. Dito com caridade ou não dito."
        )
    ),
    dailyRoutine = listOf(
            LocalizedText(
                "Mañana: ofrecimiento del día y petición de un corazón agradecido. Una frase basta: «Señor, que me alegre con lo que das a cada uno».",
                "Morning: offer the day and ask for a grateful heart. One sentence is enough: \"Lord, let me rejoice in what you give to each one.\"",
                "Manhã: oferecimento do dia e pedido de um coração agradecido. Uma frase basta: \"Senhor, que eu me alegre com o que dás a cada um\"."
            ),
            LocalizedText(
                "Custodia del corazón: decide hoy en qué momento y en qué lugares limitarás las redes o los espacios donde más te comparas.",
                "Guarding the heart: decide today when and where you will limit social media or the places where you compare most.",
                "Guarda do coração: decida hoje em que momentos e lugares você limitará as redes ou os espaços onde mais se compara."
            ),
            LocalizedText(
                "Mediodía: pausa de un minuto. ¿He sentido tristeza por el bien de alguien? Bendice a esa persona por su nombre.",
                "Midday: a one-minute pause. Have I felt sadness at anyone's good? Bless them by name.",
                "Meio-dia: pausa de um minuto. Senti tristeza pelo bem de alguém? Abençoe-o pelo nome."
            ),
            LocalizedText(
                "Un acto diario de alabanza: elogia sinceramente a alguien, de palabra o por escrito.",
                "One daily act of praise: sincerely commend someone, in words or in writing.",
                "Um ato diário de louvor: elogie sinceramente alguém, de palavra ou por escrito."
            ),
            LocalizedText(
                "Examen de la noche (San Ignacio): gracias por los dones del día; repaso de las veces que me comparé o hablé mal; petición de perdón; propósito para mañana.",
                "Evening examen (St Ignatius): thanks for the day's gifts; review the times I compared myself or spoke ill; ask pardon; resolve for tomorrow.",
                "Exame da noite (Santo Inácio): ação de graças pelos dons do dia; revisão das vezes em que me comparei ou falei mal; pedido de perdão; propósito para amanhã."
            ),
            LocalizedText(
                "Semanal: confesión o dirección espiritual con ritmo estable (por ejemplo cada dos semanas o una vez al mes); lleva la envidia por su nombre.",
                "Weekly rhythm: confession or spiritual direction at a steady pace (for example every two weeks or monthly); bring envy by its name.",
                "Ritmo semanal: confissão ou direção espiritual com ritmo estável (por exemplo a cada duas semanas ou uma vez por mês); leve a inveja pelo nome."
            ),
            LocalizedText(
                "Cuaderno de gratitud: anota cada noche tres bienes recibidos, uno de ellos relacionado con otra persona.",
                "Gratitude notebook: each night write three goods received, one of them connected with another person.",
                "Caderno de gratidão: anote cada noite três bens recebidos, um deles ligado a outra pessoa."
            )
    ),
    plan = listOf(
        GuidePhase(
            title = LocalizedText("Ver con claridad", "Seeing clearly", "Ver com clareza"),
            days = "1–7",
            focus = LocalizedText(
                "Reconocer cuándo, con quién y por qué aparece la envidia, sin dramatizar.",
                "Recognise when, with whom and why envy appears, without drama.",
                "Reconhecer quando, com quem e por que a inveja aparece, sem dramatizar."
            ),
            practices = listOf(
                LocalizedText(
                    "Anota cada noche las situaciones del día en que sentiste una punzada de comparación.",
                    "Each night note the situations that day when you felt a pang of comparison.",
                    "Anote cada noite as situações do dia em que sentiu uma pontada de comparação."
                ),
                LocalizedText(
                    "Pregúntate qué necesidad hay detrás (reconocimiento, seguridad, afecto) y preséntasela al Señor.",
                    "Ask what need lies behind it (recognition, security, affection) and present it to the Lord.",
                    "Pergunte-se que necessidade há por trás (reconhecimento, segurança, afeto) e apresente-a ao Senhor."
                ),
                LocalizedText(
                    "Reza cada día un Padre Nuestro por la persona que más te cuesta alegrarte.",
                    "Pray an Our Father each day for the person whose good you find hardest to rejoice in.",
                    "Reze cada dia um Pai-Nosso pela pessoa cujo bem você tem mais dificuldade de celebrar."
                ),
                LocalizedText(
                    "Confiésate y nombra la envidia.",
                    "Go to confession and name the envy.",
                    "Confesse-se e dê nome à inveja."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Quitar ocasiones", "Removing occasions", "Retirar as ocasiões"),
            days = "8–14",
            focus = LocalizedText(
                "Reducir lo que alimenta la comparación y buscar apoyo.",
                "Reduce whatever feeds comparison and seek support.",
                "Reduzir o que alimenta a comparação e buscar apoio."
            ),
            practices = listOf(
                LocalizedText(
                    "Silencia o deja de seguir las cuentas que te hacen sentir menos; fija un horario para las redes.",
                    "Mute or unfollow accounts that make you feel less; set a schedule for social media.",
                    "Silencie ou deixe de seguir as contas que o fazem sentir menos; defina um horário para as redes."
                ),
                LocalizedText(
                    "Cuéntale tu lucha a un confesor, director o amigo de confianza que te pregunte cada semana.",
                    "Tell your struggle to a confessor, director or trusted friend who will ask you each week.",
                    "Conte sua luta a um confessor, diretor ou amigo de confiança que lhe pergunte toda semana."
                ),
                LocalizedText(
                    "Haz una pequeña mortificación diaria (por ejemplo, no mirar el móvil en la primera hora) ofrecida por esa persona.",
                    "Make one small daily mortification (for instance, no phone in the first hour) offered for that person.",
                    "Faça uma pequena mortificação diária (por exemplo, não olhar o celular na primeira hora) oferecida por essa pessoa."
                ),
                LocalizedText(
                    "Confiésate de las faltas concretas (murmuración, alegría por el mal) con sencillez.",
                    "Confess concrete faults (gossip, joy at harm) simply.",
                    "Confesse as faltas concretas (murmuração, alegria com o mal) com simplicidade."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Cultivar la gratitud", "Cultivating gratitude", "Cultivar a gratidão"),
            days = "15–21",
            focus = LocalizedText(
                "Plantar la virtud contraria: agradecer lo propio y alegrarse con lo ajeno.",
                "Plant the opposite virtue: be thankful for what is yours and glad for what is another's.",
                "Plantar a virtude contrária: agradecer o que é seu e alegrar-se com o alheio."
            ),
            practices = listOf(
                LocalizedText(
                    "Escribe una lista de tus dones (talentos, personas, historia) y agradécela en la oración.",
                    "Write a list of your gifts (talents, people, history) and give thanks for it in prayer.",
                    "Escreva uma lista dos seus dons (talentos, pessoas, história) e agradeça-a na oração."
                ),
                LocalizedText(
                    "Elogia cada día a alguien en voz alta, sobre todo a quien envidias.",
                    "Praise someone aloud every day, especially the one you envy.",
                    "Elogie alguém em voz alta todos os dias, sobretudo quem você inveja."
                ),
                LocalizedText(
                    "Medita Rm 12,15 y 1 Co 12,12-26: un solo cuerpo, donde el don de uno es riqueza de todos.",
                    "Meditate on Rom 12:15 and 1 Cor 12:12-26: one body, where one's gift enriches all.",
                    "Medite Rm 12,15 e 1Cor 12,12-26: um só corpo, onde o dom de um é riqueza de todos."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Servir y dar", "Serving and giving", "Servir e doar"),
            days = "22–30",
            focus = LocalizedText(
                "Pasar de los sentimientos a obras concretas de caridad hacia el envidiado.",
                "Move from feelings to concrete works of charity toward the envied person.",
                "Passar dos sentimentos a obras concretas de caridade para com o invejado."
            ),
            practices = listOf(
                LocalizedText(
                    "Haz un favor discreto a esa persona o ayúdala en algo que le importe, sin buscar reconocimiento.",
                    "Do that person a discreet favour or help with something that matters to them, seeking no credit.",
                    "Faça um favor discreto a essa pessoa ou ajude-a em algo que lhe importe, sem buscar reconhecimento."
                ),
                LocalizedText(
                    "Sirve a alguien que lo necesita, en un lugar donde nadie te vea (una visita, una tarea, una limosna).",
                    "Serve someone in need in a place where no one sees you (a visit, a chore, an alms).",
                    "Sirva alguém que precisa, num lugar onde ninguém o veja (uma visita, uma tarefa, uma esmola)."
                ),
                LocalizedText(
                    "Habla bien de ella o de él en público siempre que sea verdad.",
                    "Speak well of that person in public whenever it is true.",
                    "Fale bem dessa pessoa em público sempre que for verdade."
                ),
                LocalizedText(
                    "Participa en la Eucaristía con una intención concreta de gratitud por los dones ajenos.",
                    "Attend Mass with a concrete intention of thanks for others' gifts.",
                    "Participe da Eucaristia com uma intenção concreta de gratidão pelos dons alheios."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Consolidar y proteger", "Consolidating and protecting", "Consolidar e proteger"),
            days = "31–40",
            focus = LocalizedText(
                "Afianzar la libertad con una regla de vida y un protocolo ante las recaídas.",
                "Secure the freedom gained with a rule of life and a plan for relapses.",
                "Firmar a liberdade com uma regra de vida e um protocolo diante das recaídas."
            ),
            practices = listOf(
                LocalizedText(
                    "Si recaes, no te desesperes: nombra, pide perdón, vuelve a la rutina del día siguiente. La recaída no cancela lo avanzado.",
                    "If you relapse, do not despair: name it, ask pardon, return to the next day's routine. A relapse does not cancel the progress made.",
                    "Se recair, não se desespere: nomeie, peça perdão, volte à rotina do dia seguinte. A recaída não cancela o que foi avançado."
                ),
                LocalizedText(
                    "Escribe tu regla de vida: dos o tres prácticas que mantendrás (gratitud, límite en redes, confesión periódica).",
                    "Write your rule of life: two or three practices you will keep (gratitude, social-media limit, regular confession).",
                    "Escreva sua regra de vida: duas ou três práticas que manterá (gratidão, limite nas redes, confissão periódica)."
                ),
                LocalizedText(
                    "Revisa con tu director o confesor qué ha cambiado y qué ocasiones siguen siendo frágiles.",
                    "Review with your director or confessor what has changed and which occasions remain fragile.",
                    "Reveja com seu diretor ou confessor o que mudou e quais ocasiões continuam frágeis."
                ),
                LocalizedText(
                    "Agradece a Dios en una Eucaristía el camino recorrido y encomienda a quienes envidiabas.",
                    "Give thanks to God at Mass for the road travelled and entrust those you used to envy.",
                    "Agradeça a Deus numa Eucaristia o caminho percorrido e encomende os que você invejava."
                )
            )
        )
    ),
    scripture = listOf(
        GuideScripture(
            LocalizedText("Rm 12,15", "Rom 12:15", "Rm 12,15"),
            LocalizedText(
                "Pablo propone el antídoto concreto: alegrarse con los que se alegran. La caridad empieza por compartir la alegría ajena.",
                "Paul offers the concrete antidote: rejoice with those who rejoice. Charity begins by sharing another's joy.",
                "Paulo propõe o antídoto concreto: alegrar-se com os que se alegram. A caridade começa por partilhar a alegria alheia."
            )
        ),
        GuideScripture(
            LocalizedText("Gn 4,3-8", "Gen 4:3-8", "Gn 4,3-8"),
            LocalizedText(
                "Caín mira a su hermano con tristeza y su rostro decae. Dios le advierte que el pecado acecha, pero que puede dominarlo: la envidia no es destino.",
                "Cain looks at his brother with sadness and his face falls. God warns him that sin crouches at the door but can be mastered: envy is not destiny.",
                "Caim olha o irmão com tristeza e o rosto se abate. Deus o adverte de que o pecado espreita, mas pode ser dominado: a inveja não é destino."
            )
        ),
        GuideScripture(
            LocalizedText("Mt 20,1-16", "Matt 20:1-16", "Mt 20,1-16"),
            LocalizedText(
                "En la parábola de los obreros, la queja nace de mirar la bondad del dueño con ojos de comparación. Nos invita a alegrarnos de la generosidad de Dios con todos.",
                "In the parable of the labourers the grumbling comes from looking at the owner's goodness through comparison. It invites us to rejoice in God's generosity to all.",
                "Na parábola dos trabalhadores, a queixa nasce de olhar a bondade do patrão com olhos de comparação. Convida-nos a nos alegrar com a generosidade de Deus para com todos."
            )
        ),
        GuideScripture(
            LocalizedText("St 3,14-16", "Jas 3:14-16", "Tg 3,14-16"),
            LocalizedText(
                "Santiago une la envidia amarga y el espíritu de rivalidad con el desorden y toda mala acción: dañan la paz de la comunidad.",
                "James links bitter envy and rivalry with disorder and every evil practice: they damage the peace of the community.",
                "Tiago une a inveja amarga e a rivalidade com a desordem e toda má ação: prejudicam a paz da comunidade."
            )
        ),
        GuideScripture(
            LocalizedText("1 Co 12,12-26", "1 Cor 12:12-26", "1Cor 12,12-26"),
            LocalizedText(
                "Un solo cuerpo con muchos miembros: cuando uno es honrado, todos se alegran con él. Mi lugar no compite con el tuyo.",
                "One body with many members: when one is honoured, all rejoice with it. My place does not compete with yours.",
                "Um só corpo com muitos membros: quando um é honrado, todos se alegram com ele. Meu lugar não compete com o seu."
            )
        )
    ),
    saintsWisdom = listOf(
        GuideCitation(
            LocalizedText("Santo Tomás de Aquino", "St Thomas Aquinas", "Santo Tomás de Aquino"),
            LocalizedText(
                "La envidia es tristeza por el bien ajeno que se vive como disminución del propio; por eso va contra la caridad, que se alegra del bien del prójimo.",
                "Envy is sadness at another's good experienced as a lessening of one's own; hence it opposes charity, which rejoices in the neighbour's good.",
                "A inveja é tristeza pelo bem alheio vivida como diminuição do próprio; por isso se opõe à caridade, que se alegra com o bem do próximo."
            ),
            LocalizedText("Suma Teológica II-II, q. 36, a. 1", "Summa Theologiae II-II, q. 36, a. 1", "Suma Teológica II-II, q. 36, a. 1")
        ),
        GuideCitation(
            LocalizedText("San Gregorio Magno", "St Gregory the Great", "São Gregório Magno"),
            LocalizedText(
                "De la envidia nacen el odio, la murmuración, la detracción, la alegría por la desgracia del prójimo y la aflicción por su prosperidad.",
                "From envy spring hatred, whispering, detraction, joy at a neighbour's misfortune and affliction at his prosperity.",
                "Da inveja nascem o ódio, a murmuração, a detração, a alegria com a desgraça do próximo e a aflição com a sua prosperidade."
            ),
            LocalizedText("Moralia in Job XXXI, 45", "Moralia on Job XXXI, 45", "Moralia in Job XXXI, 45")
        ),
        GuideCitation(
            LocalizedText("San Basilio Magno", "St Basil the Great", "São Basílio Magno"),
            LocalizedText(
                "En su homilía sobre la envidia advierte que este mal consume primero a quien lo alberga, antes que a quien es envidiado.",
                "In his homily on envy he warns that this evil consumes first the one who harbours it, before the one envied.",
                "Em sua homilia sobre a inveja adverte que esse mal consome primeiro quem o abriga, antes de quem é invejado."
            ),
            LocalizedText("Homilía 11, Sobre la envidia (De invidia), PG 31, 372-385", "Homily 11, On Envy (De invidia), PG 31, 372–385", "Homilia 11, Sobre a inveja (De invidia), PG 31, 372-385")
        )
    ),
    sacramentalHelps = LocalizedText(
        "La confesión frecuente, con la humildad de nombrar la envidia y sus hijas (murmuración, juicio, alegría del mal ajeno), es el gran remedio: en ella recibimos perdón y fuerza. La Eucaristía nos une a Cristo y a todos los miembros de su Cuerpo; comulgar es aprender a alegrarse de los dones que el Espíritu reparte en la Iglesia. Ayuda también la dirección espiritual, la oración de bendición por quien nos cuesta, y el Magníficat, canto de quien se alegra de lo que Dios hace en otros.",
        "Frequent confession, with the humility to name envy and its daughters (gossip, judgement, joy at another's harm), is the great remedy: there we receive pardon and strength. The Eucharist unites us to Christ and to every member of his Body; receiving Communion is learning to rejoice in the gifts the Spirit distributes in the Church. Spiritual direction, praying a blessing for those we find hard, and the Magnificat, the song of one who rejoices in what God does in others, also help.",
        "A confissão frequente, com a humildade de nomear a inveja e suas filhas (murmuração, julgamento, alegria com o mal alheio), é o grande remédio: nela recebemos perdão e força. A Eucaristia nos une a Cristo e a todos os membros do seu Corpo; comungar é aprender a se alegrar com os dons que o Espírito distribui na Igreja. Ajudam também a direção espiritual, a oração de bênção por quem nos custa e o Magnificat, canto de quem se alegra com o que Deus faz nos outros."
    ),
    whenToSeekHelp = LocalizedText(
        "Habla con tu confesor o director espiritual si la envidia te acompaña desde hace tiempo, si te hace hablar mal con frecuencia o te aparta de personas. No la lleves solo ni te juzgues con dureza: la envidia es una herida común, no una condena. Si la comparación constante te hunde en la tristeza, la ansiedad o la sensación de no valer nada, o si el deseo de dañar a alguien se vuelve obsesivo, consulta también a un profesional cualificado en salud mental; la fe y la ayuda profesional se complementan.",
        "Speak to your confessor or spiritual director if envy has accompanied you for a long time, makes you speak ill often or pulls you away from people. Do not carry it alone or judge yourself harshly: envy is a common wound, not a verdict. If constant comparison sinks you into sadness, anxiety or a sense of worthlessness, or if the wish to harm someone becomes obsessive, also consult a qualified mental-health professional; faith and professional help complement each other.",
        "Fale com seu confessor ou diretor espiritual se a inveja o acompanha há muito tempo, se o faz falar mal com frequência ou o afasta das pessoas. Não a carregue sozinho nem se julgue com dureza: a inveja é uma ferida comum, não uma condenação. Se a comparação constante o afunda na tristeza, na ansiedade ou na sensação de não valer nada, ou se o desejo de prejudicar alguém se torna obsessivo, consulte também um profissional qualificado em saúde mental; a fé e a ajuda profissional se complementam."
    ),
    sources = listOf(
        LocalizedText("Catecismo de la Iglesia Católica (CIC) 2538-2540 (décimo mandamiento y envidia), 1866 (pecados capitales), 405 y 2515 (concupiscencia).", "Catechism of the Catholic Church (CCC) 2538-2540 (tenth commandment and envy), 1866 (capital sins), 405 and 2515 (concupiscence).", "Catecismo da Igreja Católica (CIC) 2538-2540 (décimo mandamento e inveja), 1866 (pecados capitais), 405 e 2515 (concupiscência)."),
        LocalizedText("Santo Tomás de Aquino, Suma Teológica II-II, q. 36 (la envidia).", "St Thomas Aquinas, Summa Theologiae II-II, q. 36 (envy).", "Santo Tomás de Aquino, Suma Teológica II-II, q. 36 (a inveja)."),
        LocalizedText("San Gregorio Magno, Moralia in Job XXXI, 45.", "St Gregory the Great, Moralia on Job XXXI, 45.", "São Gregório Magno, Moralia in Job XXXI, 45."),
        LocalizedText("San Basilio Magno, Homilía 11, Sobre la envidia (De invidia), PG 31, 372-385.", "St Basil the Great, Homily 11, On Envy (De invidia), PG 31, 372–385.", "São Basílio Magno, Homilia 11, Sobre a inveja (De invidia), PG 31, 372-385."),
        LocalizedText("Evagrio Póntico y San Juan Casiano: doctrina de los «pensamientos» (logismoi) y los vicios capitales.", "Evagrius Ponticus and St John Cassian: teaching on the \"thoughts\" (logismoi) and the capital vices.", "Evágrio Pôntico e São João Cassiano: doutrina dos \"pensamentos\" (logismoi) e dos vícios capitais.")
    )
)

private val pride = TemptationGuide(
    sinKey = "PRIDE",
    summary = LocalizedText(
        "La soberbia es el amor desordenado a la propia excelencia: querer ser el origen de todo bien y el centro de la mirada. Es la raíz de los demás pecados porque nos aparta de Dios. Se sana con la humildad, que es vivir en la verdad de lo que somos y de lo que hemos recibido.",
        "Pride is the disordered love of one's own excellence: wanting to be the source of all good and the centre of every gaze. It is the root of the other sins because it turns us from God. It is healed by humility, which is living in the truth of what we are and of what we have received.",
        "A soberba é o amor desordenado pela própria excelência: querer ser a origem de todo bem e o centro de todos os olhares. É a raiz dos demais pecados porque nos afasta de Deus. Cura-se com a humildade, que é viver na verdade do que somos e do que recebemos."
    ),
    howItWorks = LocalizedText(
        "Las Escrituras la llaman principio de todo pecado (Si 10,12-13; la Vulgata: «initium omnis peccati superbia», citada por san Agustín y santo Tomás), y San Gregorio Magno la llama reina de los vicios, de la que nacen los demás (Moralia in Job XXXI, 45). El Catecismo la sitúa entre los pecados capitales (CIC 1866) y señala que el odio a Dios tiene su fuente en la soberbia (CIC 2094). Conviene distinguir la vanagloria (buscar la admiración y el aplauso) de la soberbia propiamente dicha (atribuirse el bien como propio, despreciar la dependencia de Dios y de los demás). Santo Tomás las trata en II-II, q. 162 y q. 132.\n\n" +
            "Sus ocasiones son tanto el éxito como el fracaso: el elogio que nos infla, la crítica que nos duele más de lo debido, la corrección que no soportamos, la necesidad de tener razón, de quedar bien o de ser «el que más sirve». Sigue las etapas clásicas: una sugerencia («eres mejor que…», «nadie lo hace como tú»), la complacencia (saborear la imagen propia) y el consentimiento (hablar de uno mismo con astucia, menospreciar, no pedir perdón, no aceptar ayuda). Evagrio y Casiano describían la vanagloria y la soberbia como los últimos y más sutiles pensamientos, que atacan incluso al que ha vencido a los otros vicios (Casiano, Instituciones XI-XII).\n\n" +
            "Tiene muchos rostros ocultos: la hipersensibilidad ante cualquier crítica, la incapacidad de pedir perdón o de decir «me equivoqué», el desprecio silencioso de los demás, la falsa modestia que busca ser alabada, la soberbia espiritual de quien se siente más devoto o mejor católico que los demás. Santa Teresa enseña que la humildad es andar en verdad (Castillo interior, Moradas sextas 10,7): ni rebajarse ni engrandecerse, sino reconocer lo que Dios ha hecho en nosotros y lo que nosotros hemos dejado de hacer.",
        "Scripture calls it the beginning of all sin (Sir 10:12–13; the Vulgate: \"initium omnis peccati superbia\", quoted by St Augustine and St Thomas), and St Gregory the Great calls it the queen of the vices, from which the rest are born (Moralia on Job XXXI, 45). The Catechism lists it among the capital sins (CCC 1866) and notes that hatred of God has its source in pride (CCC 2094). It helps to distinguish vainglory (seeking admiration and applause) from pride proper (claiming the good as one's own, despising dependence on God and others). St Thomas treats them in II-II, q. 162 and q. 132.\n\n" +
            "Its occasions are success and failure alike: praise that puffs us up, criticism that hurts more than it should, correction we cannot bear, the need to be right, to look good, or to be \"the one who serves most.\" It follows the classic stages: a suggestion (\"you are better than...\", \"nobody does it like you\"), delight (savouring one's own image) and consent (speaking of oneself cleverly, belittling, not asking pardon, refusing help). Evagrius and Cassian described vainglory and pride as the last and subtlest thoughts, which attack even the one who has overcome the other vices (Cassian, Institutes XI–XII).\n\n" +
            "It has many hidden faces: hypersensitivity to any criticism, inability to ask forgiveness or to say \"I was wrong,\" silent contempt for others, false modesty that fishes for praise, the spiritual pride of one who feels more devout or a better Catholic than others. St Teresa teaches that humility is walking in truth (Interior Castle, Sixth Mansions 10:7): neither belittling ourselves nor exalting ourselves, but acknowledging what God has done in us and what we have failed to do.",
        "A Escritura a chama princípio de todo pecado (Eclo 10,12-13; a Vulgata: \"initium omnis peccati superbia\", citada por Santo Agostinho e Santo Tomás), e São Gregório Magno a chama rainha dos vícios, da qual nascem os demais (Moralia in Job XXXI, 45). O Catecismo a situa entre os pecados capitais (CIC 1866) e assinala que o ódio a Deus tem sua fonte na soberba (CIC 2094). Convém distinguir a vanglória (buscar admiração e aplauso) da soberba propriamente dita (atribuir-se o bem como próprio, desprezar a dependência de Deus e dos outros). Santo Tomás trata delas em II-II, q. 162 e q. 132.\n\n" +
            "Suas ocasiões são tanto o sucesso quanto o fracasso: o elogio que nos infla, a crítica que dói mais do que deveria, a correção que não suportamos, a necessidade de ter razão, de ficar bem ou de ser \"quem mais serve\". Segue as etapas clássicas: a sugestão (\"você é melhor que...\", \"ninguém faz como você\"), a complacência (saborear a imagem de si) e o consentimento (falar de si com astúcia, menosprezar, não pedir perdão, não aceitar ajuda). Evágrio e Cassiano descreviam a vanglória e a soberba como os últimos e mais sutis pensamentos, que atacam até quem venceu os outros vícios (Cassiano, Instituições XI-XII).\n\n" +
            "Tem muitos rostos escondidos: a hipersensibilidade a qualquer crítica, a incapacidade de pedir perdão ou de dizer \"eu errei\", o desprezo silencioso pelos outros, a falsa modéstia que pesca elogios, a soberba espiritual de quem se sente mais devoto ou melhor católico que os outros. Santa Teresa ensina que a humildade é andar na verdade (Castelo Interior, Sextas Moradas 10,7): nem se rebaixar nem se engrandecer, mas reconhecer o que Deus fez em nós e o que deixamos de fazer."
    ),
    whyItIsHard = LocalizedText(
        "La soberbia es difícil de vencer porque casi nunca se reconoce a sí misma: quien la tiene suele ver soberbia en los demás. Se alimenta de lo bueno que hay en nosotros (talentos, éxitos, incluso virtudes) y se apoya en el miedo a ser poca cosa. La concupiscencia nos inclina a ponernos en el centro (CIC 405, 2515), y la cultura del éxito, de la imagen y de la autopromoción premia justo lo contrario de la humildad.\n\n" +
            "Además, cada victoria puede volverse una trampa: «qué humilde soy». Por eso la humildad no se alcanza buscándola directamente, sino mirando a Cristo, que siendo Dios se humilló (Flp 2,6-8), y aceptando con paz las pequeñas humillaciones y correcciones del día a día, que son el mejor maestro.",
        "Pride is hard to defeat because it almost never recognises itself: those who have it usually see pride in others. It feeds on what is good in us (talents, successes, even virtues) and leans on the fear of being small. Concupiscence inclines us to put ourselves at the centre (CCC 405, 2515), and a culture of success, image and self-promotion rewards precisely the opposite of humility.\n\n" +
            "Moreover, each victory can become a trap: \"how humble I am.\" So humility is not attained by chasing it directly but by looking at Christ, who, being God, humbled himself (Phil 2:6-8), and by accepting with peace the small humiliations and corrections of daily life, which are the best teacher.",
        "A soberba é difícil de vencer porque quase nunca se reconhece a si mesma: quem a tem costuma ver soberba nos outros. Alimenta-se do que há de bom em nós (talentos, sucessos, até virtudes) e se apoia no medo de ser pouca coisa. A concupiscência nos inclina a nos pôr no centro (CIC 405, 2515), e a cultura do sucesso, da imagem e da autopromoção premia justamente o contrário da humildade.\n\n" +
            "Além disso, cada vitória pode se tornar uma armadilha: \"como sou humilde\". Por isso a humildade não se alcança buscando-a diretamente, mas olhando para Cristo, que, sendo Deus, se humilhou (Fl 2,6-8), e aceitando com paz as pequenas humilhações e correções do dia a dia, que são o melhor mestre."
    ),
    warningSigns = listOf(
        LocalizedText(
            "Te cuesta decir «me equivoqué» o «perdón», y te justificas casi por reflejo.",
            "You find it hard to say \"I was wrong\" or \"I'm sorry,\" and you justify yourself almost by reflex.",
            "Você tem dificuldade em dizer \"errei\" ou \"desculpe\" e se justifica quase por reflexo."
        ),
        LocalizedText(
            "Una crítica o una corrección te deja herido o rumiando durante horas o días.",
            "A criticism or correction leaves you hurt or brooding for hours or days.",
            "Uma crítica ou correção o deixa ferido ou remoendo por horas ou dias."
        ),
        LocalizedText(
            "Llevas la conversación hacia tus logros, o necesitas que se sepa lo que haces bien.",
            "You steer conversations toward your achievements, or need it known what you do well.",
            "Você leva a conversa para suas conquistas, ou precisa que se saiba o que faz bem."
        ),
        LocalizedText(
            "Juzgas con rapidez a los demás y sientes un desprecio silencioso por su torpeza o su fe.",
            "You judge others quickly and feel a quiet contempt for their clumsiness or their faith.",
            "Você julga os outros rapidamente e sente um desprezo silencioso por sua inabilidade ou sua fé."
        ),
        LocalizedText(
            "Te cuesta pedir ayuda o consejo, o aceptar que otros decidan.",
            "You find it hard to ask for help or advice, or to let others decide.",
            "Você tem dificuldade em pedir ajuda ou conselho, ou em aceitar que outros decidam."
        ),
        LocalizedText(
            "Tu oración se vuelve una lista de méritos o de quejas por no ser reconocido.",
            "Your prayer becomes a list of merits or complaints at not being recognised.",
            "Sua oração se torna uma lista de méritos ou de queixas por não ser reconhecido."
        )
    ),
    inTheMoment = listOf(
        LocalizedText(
            "Antes de defenderte, haz una pausa y respira. El silencio es la primera humildad.",
            "Before defending yourself, pause and breathe. Silence is the first humility.",
            "Antes de se defender, faça uma pausa e respire. O silêncio é a primeira humildade."
        ),
        LocalizedText(
            "Pregúntate: ¿hay algo de verdad en lo que se me dice? Reconócelo en voz alta si lo hay.",
            "Ask yourself: is there some truth in what is being said? Acknowledge it aloud if there is.",
            "Pergunte-se: há alguma verdade no que me dizem? Reconheça-o em voz alta se houver."
        ),
        LocalizedText(
            "Reza brevemente: «Jesús, manso y humilde de corazón, haz mi corazón semejante al tuyo» (cf. Mt 11,29).",
            "Pray briefly: \"Jesus, meek and humble of heart, make my heart like yours\" (cf. Matt 11:29).",
            "Reze brevemente: \"Jesus, manso e humilde de coração, faz meu coração semelhante ao teu\" (cf. Mt 11,29)."
        ),
        LocalizedText(
            "Renuncia a la última palabra o a mencionar tu mérito; cede el primer lugar.",
            "Give up the last word or the mention of your merit; yield the first place.",
            "Renuncie à última palavra ou a mencionar seu mérito; ceda o primeiro lugar."
        ),
        LocalizedText(
            "Pide consejo a alguien de confianza antes de decidir, aunque creas saber la respuesta.",
            "Ask a trusted person for counsel before deciding, even if you think you know the answer.",
            "Peça conselho a alguém de confiança antes de decidir, mesmo que ache que sabe a resposta."
        ),
        LocalizedText(
            "Haz un acto de servicio escondido, que nadie sepa, y ofrécelo a Dios.",
            "Do a hidden act of service that nobody knows about, and offer it to God.",
            "Faça um ato de serviço escondido, que ninguém saiba, e ofereça-o a Deus."
        ),
        LocalizedText(
            "Agradece a Dios el bien que se te elogia y devuélvele la gloria: «no a nosotros, Señor» (Sal 115,1).",
            "Thank God for the good for which you are praised and return the glory: \"not to us, O Lord\" (Ps 115:1).",
            "Agradeça a Deus o bem pelo qual o elogiam e devolva-lhe a glória: \"não a nós, Senhor\" (Sl 115,1)."
        )
    ),
    dailyRoutine = listOf(
        LocalizedText(
            "Mañana: ofrecimiento del día y petición de humildad. Un acto de adoración: Dios es Dios, yo soy criatura amada.",
            "Morning: offer the day and ask for humility. An act of adoration: God is God, I am a beloved creature.",
            "Manhã: oferecimento do dia e pedido de humildade. Um ato de adoração: Deus é Deus, eu sou criatura amada."
        ),
        LocalizedText(
            "Custodia del corazón: elige una ocasión del día en que cederás, escucharás o servirás sin que se note.",
            "Guarding the heart: choose one occasion in the day when you will yield, listen or serve unnoticed.",
            "Guarda do coração: escolha uma ocasião do dia em que cederá, escutará ou servirá sem que se perceba."
        ),
        LocalizedText(
            "Mediodía: ¿he hablado demasiado de mí? ¿He interrumpido, corregido sin pedirlo, menospreciado? Rectifica ahora.",
            "Midday: have I talked too much about myself? Interrupted, corrected unasked, belittled? Make amends now.",
            "Meio-dia: falei demais de mim? Interrompi, corrigi sem ser pedido, menosprezei? Corrija agora."
        ),
        LocalizedText(
            "Letanía de la humildad (atribuida al cardenal Rafael Merry del Val): reza algunas peticiones lentamente, sin forzar los sentimientos.",
            "Litany of Humility (attributed to Cardinal Rafael Merry del Val): pray a few petitions slowly, without forcing feeling.",
            "Ladainha da humildade (atribuída ao cardeal Rafael Merry del Val): reze algumas petições lentamente, sem forçar sentimentos."
        ),
        LocalizedText(
            "Examen de la noche (San Ignacio): gracias; repaso de situaciones de justificación, vanidad o desprecio; perdón; propósito.",
            "Evening examen (St Ignatius): thanks; review moments of self-justification, vanity or contempt; pardon; resolve.",
            "Exame da noite (Santo Inácio): ação de graças; revisão de situações de justificação, vaidade ou desprezo; perdão; propósito."
        ),
        LocalizedText(
            "Semanal: confesión o dirección con ritmo estable; deja que el confesor te corrija y pide su consejo.",
            "Weekly rhythm: confession or direction at a steady pace; let the confessor correct you and seek his counsel.",
            "Ritmo semanal: confissão ou direção com ritmo estável; deixe o confessor corrigi-lo e peça o conselho dele."
        ),
        LocalizedText(
            "Lectura breve del Evangelio (p. ej. el lavatorio de los pies, Jn 13,1-17) y una tarea humilde de la casa o la comunidad.",
            "A short Gospel reading (e.g. the washing of the feet, John 13:1-17) and one humble household or community task.",
            "Leitura breve do Evangelho (p. ex. o lava-pés, Jo 13,1-17) e uma tarefa humilde da casa ou da comunidade."
        )
    ),
    plan = listOf(
        GuidePhase(
            title = LocalizedText("Conocerse en verdad", "Knowing yourself in truth", "Conhecer-se na verdade"),
            days = "1–7",
            focus = LocalizedText(
                "Descubrir cómo se manifiesta en ti la soberbia, sin condenarte.",
                "Discover how pride shows itself in you, without condemning yourself.",
                "Descobrir como a soberba se manifesta em você, sem se condenar."
            ),
            practices = listOf(
                LocalizedText(
                    "Cada noche anota situaciones en que te justificaste, buscaste aplauso o juzgaste con dureza.",
                    "Each night note situations when you justified yourself, sought applause or judged harshly.",
                    "Cada noite anote situações em que se justificou, buscou aplauso ou julgou com dureza."
                ),
                LocalizedText(
                    "Pregunta a alguien que te quiera y sea sincero qué rasgo de tu carácter más pesa a los demás.",
                    "Ask someone who loves you and is honest which trait of yours weighs most on others.",
                    "Pergunte a alguém que o ama e seja sincero qual traço seu mais pesa nos outros."
                ),
                LocalizedText(
                    "Medita Flp 2,5-11 y 1 Co 4,7: ¿qué tienes que no hayas recibido?",
                    "Meditate on Phil 2:5-11 and 1 Cor 4:7: what do you have that you have not received?",
                    "Medite Fl 2,5-11 e 1Cor 4,7: o que você tem que não tenha recebido?"
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Callar y escuchar", "Silence and listening", "Calar e escutar"),
            days = "8–14",
            focus = LocalizedText(
                "Aprender a no defenderse ni destacar, y buscar acompañamiento.",
                "Learn not to defend or stand out, and seek accompaniment.",
                "Aprender a não se defender nem se destacar, e buscar acompanhamento."
            ),
            practices = listOf(
                LocalizedText(
                    "Ante una crítica, responde solo con «gracias, lo pensaré», y reflexiona después a solas.",
                    "In the face of criticism, answer only \"thank you, I will think about it,\" and reflect later alone.",
                    "Diante de uma crítica, responda apenas \"obrigado, vou pensar\" e reflita depois a sós."
                ),
                LocalizedText(
                    "En cada conversación procura escuchar más de lo que hablas y preguntar por el otro.",
                    "In each conversation try to listen more than you speak and ask about the other.",
                    "Em cada conversa procure escutar mais do que fala e perguntar pelo outro."
                ),
                LocalizedText(
                    "Habla con un confesor o director y pídele que te señale tus puntos ciegos.",
                    "Speak with a confessor or director and ask him to point out your blind spots.",
                    "Fale com um confessor ou diretor e peça que lhe aponte seus pontos cegos."
                ),
                LocalizedText(
                    "Pide perdón al menos una vez en la semana por algo concreto.",
                    "Ask pardon at least once this week for something concrete.",
                    "Peça perdão pelo menos uma vez na semana por algo concreto."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Servir en lo escondido", "Hidden service", "Servir em segredo"),
            days = "15–21",
            focus = LocalizedText(
                "Practicar la virtud contraria mediante obras pequeñas y escondidas.",
                "Practise the opposite virtue through small hidden deeds.",
                "Praticar a virtude contrária por meio de obras pequenas e escondidas."
            ),
            practices = listOf(
                LocalizedText(
                    "Cada día una tarea humilde sin que nadie lo note (limpiar, ordenar, ceder el turno).",
                    "Each day one humble task that nobody notices (cleaning, tidying, giving up your turn).",
                    "Cada dia uma tarefa humilde sem que ninguém perceba (limpar, arrumar, ceder a vez)."
                ),
                LocalizedText(
                    "Elogia el trabajo de otros y atribuye a ellos el éxito de un proyecto común.",
                    "Praise others' work and credit them with the success of a shared project.",
                    "Elogie o trabalho dos outros e atribua a eles o sucesso de um projeto comum."
                ),
                LocalizedText(
                    "Deja pasar sin corregir un error ajeno que no importe de verdad.",
                    "Let pass, uncorrected, another's error that does not truly matter.",
                    "Deixe passar, sem corrigir, um erro alheio que não importe de verdade."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Aceptar la verdad y la dependencia", "Accepting truth and dependence", "Aceitar a verdade e a dependência"),
            days = "22–30",
            focus = LocalizedText(
                "Vivir la humildad como verdad: agradecer lo recibido y aceptar los propios límites.",
                "Live humility as truth: be thankful for what was received and accept your limits.",
                "Viver a humildade como verdade: agradecer o recebido e aceitar os próprios limites."
            ),
            practices = listOf(
                LocalizedText(
                    "Pide ayuda concreta a alguien al menos tres veces esta semana, aunque puedas hacerlo solo.",
                    "Ask someone for concrete help at least three times this week, even if you could do it alone.",
                    "Peça ajuda concreta a alguém pelo menos três vezes esta semana, mesmo que possa fazer sozinho."
                ),
                LocalizedText(
                    "Escribe una lista de tus límites y errores pasados y ofrécela a Dios en una confesión sincera, sin volver a confesar pecados ya perdonados.",
                    "Write a list of your limits and past mistakes and offer it to God in a sincere confession, without re-confessing sins already forgiven.",
                    "Escreva uma lista de seus limites e erros passados e ofereça-a a Deus numa confissão sincera, sem voltar a confessar pecados já perdoados."
                ),
                LocalizedText(
                    "Reza la letanía de la humildad lentamente y elige una petición para vivirla hoy.",
                    "Pray the litany of humility slowly and choose one petition to live today.",
                    "Reze a ladainha da humildade lentamente e escolha uma petição para viver hoje."
                ),
                LocalizedText(
                    "Acepta con paz una pequeña humillación sin explicarte ni quejarte.",
                    "Accept a small humiliation in peace without explaining yourself or complaining.",
                    "Aceite com paz uma pequena humilhação sem se explicar nem reclamar."
                )
            )
        ),
        GuidePhase(
            title = LocalizedText("Consolidar y vigilar", "Consolidating and watching", "Consolidar e vigiar"),
            days = "31–40",
            focus = LocalizedText(
                "Hacer de la humildad un estilo de vida y prever las recaídas.",
                "Make humility a way of life and anticipate relapses.",
                "Fazer da humildade um estilo de vida e prever as recaídas."
            ),
            practices = listOf(
                LocalizedText(
                    "Si recaes, no te desanimes: reconócelo, pide perdón y vuelve. Esa es la humildad en acto.",
                    "If you relapse, do not be discouraged: acknowledge it, ask pardon and return. That is humility in action.",
                    "Se recair, não desanime: reconheça, peça perdão e volte. Isso é humildade em ato."
                ),
                LocalizedText(
                    "Fija una regla de vida breve: oración de la mañana, examen, un servicio escondido a la semana, confesión periódica.",
                    "Set a brief rule of life: morning prayer, examen, one hidden service a week, regular confession.",
                    "Fixe uma regra de vida breve: oração da manhã, exame, um serviço escondido por semana, confissão periódica."
                ),
                LocalizedText(
                    "Mantén un compañero de rendición de cuentas: alguien a quien le des permiso de decirte la verdad.",
                    "Keep an accountability companion: someone you give permission to tell you the truth.",
                    "Mantenha um companheiro de prestação de contas: alguém a quem dê permissão de lhe dizer a verdade."
                ),
                LocalizedText(
                    "Da gracias a Dios en la Eucaristía por lo recibido y por las humillaciones que han sido gracia.",
                    "Thank God at Mass for what you received and for the humiliations that proved to be grace.",
                    "Agradeça a Deus na Eucaristia pelo recebido e pelas humilhações que foram graça."
                )
            )
        )
    ),
    scripture = listOf(
        GuideScripture(
            LocalizedText("Si 10,7-18", "Sir 10:7-18", "Eclo 10,7-18"),
            LocalizedText(
                "El Sirácida describe la soberbia como comienzo del pecado y raíz del apartamiento del Señor; Dios derriba tronos de soberbios.",
                "Sirach describes pride as the beginning of sin and the root of turning from the Lord; God overturns the thrones of the proud.",
                "O Eclesiástico descreve a soberba como começo do pecado e raiz do afastamento do Senhor; Deus derruba os tronos dos soberbos."
            )
        ),
        GuideScripture(
            LocalizedText("Lc 18,9-14", "Luke 18:9-14", "Lc 18,9-14"),
            LocalizedText(
                "El fariseo y el publicano: el soberbio reza mirándose a sí mismo y comparándose; el humilde se presenta a Dios tal cual es.",
                "The Pharisee and the tax collector: the proud man prays looking at himself and comparing; the humble one stands before God as he is.",
                "O fariseu e o publicano: o soberbo reza olhando para si e comparando-se; o humilde se apresenta a Deus como é."
            )
        ),
        GuideScripture(
            LocalizedText("Flp 2,5-11", "Phil 2:5-11", "Fl 2,5-11"),
            LocalizedText(
                "El himno de Cristo, que no se aferró a su categoría de Dios, nos da el modelo de la humildad y del servicio.",
                "The hymn of Christ, who did not cling to equality with God, gives the model of humility and service.",
                "O hino de Cristo, que não se apegou à sua condição divina, nos dá o modelo da humildade e do serviço."
            )
        ),
        GuideScripture(
            LocalizedText("1 Co 4,7", "1 Cor 4:7", "1Cor 4,7"),
            LocalizedText(
                "Pablo pregunta qué tenemos que no hayamos recibido: la humildad es memoria de que todo es don.",
                "Paul asks what we have that we have not received: humility is remembering that everything is gift.",
                "Paulo pergunta o que temos que não tenhamos recebido: a humildade é memória de que tudo é dom."
            )
        ),
        GuideScripture(
            LocalizedText("Jn 13,1-17", "John 13:1-17", "Jo 13,1-17"),
            LocalizedText(
                "El Señor lava los pies a sus discípulos: la grandeza en el Reino se mide por el servicio.",
                "The Lord washes his disciples' feet: greatness in the Kingdom is measured by service.",
                "O Senhor lava os pés dos discípulos: a grandeza no Reino se mede pelo serviço."
            )
        )
    ),
    saintsWisdom = listOf(
        GuideCitation(
            LocalizedText("San Gregorio Magno", "St Gregory the Great", "São Gregório Magno"),
            LocalizedText(
                "La soberbia es la reina de los vicios, que entrega el corazón vencido a siete vicios capitales como a sus generales, de los que nace un ejército de vicios.",
                "Pride is the queen of the vices, who hands the conquered heart over to seven capital vices as to her generals, from which an army of vices is born.",
                "A soberba é a rainha dos vícios, que entrega o coração vencido a sete vícios capitais como a seus generais, dos quais nasce um exército de vícios."
            ),
            LocalizedText("Moralia in Job XXXI, 45", "Moralia on Job XXXI, 45", "Moralia in Job XXXI, 45")
        ),
        GuideCitation(
            LocalizedText("San Agustín", "St Augustine", "Santo Agostinho"),
            LocalizedText(
                "El comienzo de toda mala voluntad es la soberbia: el alma se complace en sí misma y se aparta del Dios que es su origen.",
                "The beginning of every evil will is pride: the soul takes delight in itself and turns away from the God who is its source.",
                "O começo de toda má vontade é a soberba: a alma se compraz em si mesma e se afasta do Deus que é sua origem."
            ),
            LocalizedText("La ciudad de Dios XIV, 13", "City of God XIV, 13", "A Cidade de Deus XIV, 13")
        ),
        GuideCitation(
            LocalizedText("Santa Teresa de Jesús", "St Teresa of Ávila", "Santa Teresa de Ávila"),
            LocalizedText(
                "La humildad es andar en verdad: reconocer que todo bien viene de Dios y que de nosotros solo viene la miseria.",
                "Humility is walking in truth: recognising that all good comes from God and that from ourselves comes only wretchedness.",
                "A humildade é andar na verdade: reconhecer que todo bem vem de Deus e que de nós só vem a miséria."
            ),
            LocalizedText("Castillo interior, Moradas sextas 10,7", "Interior Castle, Sixth Mansions 10:7", "Castelo Interior, Sextas Moradas 10,7")
        ),
        GuideCitation(
            LocalizedText("San Juan Casiano", "St John Cassian", "São João Cassiano"),
            LocalizedText(
                "La soberbia es el vicio más sutil y último en ser vencido: ataca incluso a quien ha superado los demás vicios y pervierte las virtudes.",
                "Pride is the subtlest and last vice to be conquered: it attacks even one who has overcome the others and corrupts the virtues.",
                "A soberba é o vício mais sutil e o último a ser vencido: ataca até quem superou os demais e corrompe as virtudes."
            ),
            LocalizedText("Instituciones XI-XII", "Institutes XI–XII", "Instituições XI-XII")
        )
    ),
    sacramentalHelps = LocalizedText(
        "La confesión es escuela de humildad: acusarse con verdad y recibir el perdón sin merecerlo desarma la soberbia. La Eucaristía nos muestra a Dios hecho pan, el más humilde de los dones, y nos enseña a recibir en lugar de exigir. Ayudan la adoración ante el Santísimo, la dirección espiritual (dejarse guiar es un acto de humildad), el Rosario con María, la humilde sierva del Señor, la Letanía de la humildad (atribuida al cardenal Merry del Val) y las obras de misericordia vividas en lo escondido.",
        "Confession is a school of humility: accusing oneself in truth and receiving pardon undeserved disarms pride. The Eucharist shows us God made bread, the humblest of gifts, and teaches us to receive rather than demand. Adoration before the Blessed Sacrament, spiritual direction (letting oneself be guided is an act of humility), the Rosary with Mary, the humble handmaid of the Lord, the Litany of Humility (attributed to Cardinal Merry del Val) and works of mercy done in hiddenness all help.",
        "A confissão é escola de humildade: acusar-se com verdade e receber o perdão sem merecê-lo desarma a soberba. A Eucaristia nos mostra Deus feito pão, o mais humilde dos dons, e nos ensina a receber em vez de exigir. Ajudam a adoração diante do Santíssimo, a direção espiritual (deixar-se guiar é ato de humildade), o Rosário com Maria, a humilde serva do Senhor, a Ladainha da humildade (atribuída ao cardeal Merry del Val) e as obras de misericórdia vividas no escondido."
    ),
    whenToSeekHelp = LocalizedText(
        "Habla con un confesor o director espiritual si notas que la soberbia daña tus relaciones (control, desprecio, imposibilidad de pedir perdón) o si te cuesta mucho aceptar cualquier corrección. Un acompañante de confianza puede decirte con caridad lo que tú no ves. Si detrás de la necesidad de aplauso o de superioridad hay mucho sufrimiento, una autoestima muy frágil, o si el trato con los demás se vuelve rígido y dañino, conviene consultar también a un profesional cualificado; no se trata de un juicio sobre tu fe. Evita también el extremo contrario: la humildad no es despreciarse ni escrúpulo constante, sino verdad.",
        "Speak with a confessor or spiritual director if you notice pride damaging your relationships (control, contempt, inability to ask pardon) or if you find any correction very hard to accept. A trusted companion can tell you in charity what you cannot see. If behind the need for applause or superiority there is much suffering or very fragile self-esteem, or if dealings with others become rigid and harmful, it is good also to consult a qualified professional; this is no judgement on your faith. Also avoid the opposite extreme: humility is not self-contempt or constant scrupulosity, but truth.",
        "Fale com um confessor ou diretor espiritual se perceber que a soberba prejudica seus relacionamentos (controle, desprezo, incapacidade de pedir perdão) ou se tem muita dificuldade em aceitar qualquer correção. Um acompanhante de confiança pode lhe dizer com caridade o que você não vê. Se por trás da necessidade de aplauso ou de superioridade há muito sofrimento ou uma autoestima muito frágil, ou se o trato com os outros se torna rígido e danoso, convém consultar também um profissional qualificado; isso não é um julgamento sobre sua fé. Evite também o extremo oposto: humildade não é autodesprezo nem escrúpulo constante, mas verdade."
    ),
    sources = listOf(
        LocalizedText("Catecismo de la Iglesia Católica (CIC) 1866 (pecados capitales), 2094 (el odio a Dios nace de la soberbia), 405 y 2515 (concupiscencia).", "Catechism of the Catholic Church (CCC) 1866 (capital sins), 2094 (hatred of God springs from pride), 405 and 2515 (concupiscence).", "Catecismo da Igreja Católica (CIC) 1866 (pecados capitais), 2094 (o ódio a Deus nasce da soberba), 405 e 2515 (concupiscência)."),
        LocalizedText("Santo Tomás de Aquino, Suma Teológica II-II, q. 162 (soberbia) y q. 132 (vanagloria).", "St Thomas Aquinas, Summa Theologiae II-II, q. 162 (pride) and q. 132 (vainglory).", "Santo Tomás de Aquino, Suma Teológica II-II, q. 162 (soberba) e q. 132 (vanglória)."),
        LocalizedText("San Gregorio Magno, Moralia in Job XXXI, 45.", "St Gregory the Great, Moralia on Job XXXI, 45.", "São Gregório Magno, Moralia in Job XXXI, 45."),
        LocalizedText("San Agustín, La ciudad de Dios XIV, 13.", "St Augustine, City of God XIV, 13.", "Santo Agostinho, A Cidade de Deus XIV, 13."),
        LocalizedText("San Juan Casiano, Instituciones XI-XII (contra la soberbia).", "St John Cassian, Institutes XI–XII (against pride).", "São João Cassiano, Instituições XI-XII (contra a soberba)."),
        LocalizedText("Santa Teresa de Jesús, Castillo interior, Moradas sextas 10,7.", "St Teresa of Ávila, Interior Castle, Sixth Mansions 10:7.", "Santa Teresa de Ávila, Castelo Interior, Sextas Moradas 10,7.")
    )
)
