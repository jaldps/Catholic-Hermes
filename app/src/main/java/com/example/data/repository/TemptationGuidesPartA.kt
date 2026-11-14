package com.example.data.repository

import com.example.data.models.GuideCitation
import com.example.data.models.GuidePhase
import com.example.data.models.GuideScripture
import com.example.data.models.LocalizedText
import com.example.data.models.TemptationGuide

private fun t(es: String, en: String, pt: String) = LocalizedText(es, en, pt)

/** Long-form guides: LUST and ANGER (GLUTTONY and SLOTH live in [TemptationGuidesPartA2]). */
object TemptationGuidesPartA {
    val guides: List<TemptationGuide> = listOf(lust, anger) + TemptationGuidesPartA2.guides
}

private val lust = TemptationGuide(
    sinKey = "LUST",
    summary = t(
        "La lujuria es el deseo desordenado del placer sexual, separado del don de sí y del amor verdadero (CIC 2351). La virtud que la sana es la castidad: la integración de la sexualidad en la persona, que aprende a amar sin usar al otro (CIC 2337). No se vence solo a fuerza de voluntad, sino con gracia, custodia de los sentidos, amistad y un corazón ocupado en algo mayor.",
        "Lust is the disordered desire for sexual pleasure, cut off from self-gift and true love (CCC 2351). The virtue that heals it is chastity: the integration of sexuality into the whole person, who learns to love without using the other (CCC 2337). It is not overcome by willpower alone, but by grace, guarding the senses, friendship and a heart busy with something greater.",
        "A luxúria é o desejo desordenado do prazer sexual, separado do dom de si e do amor verdadeiro (CIC 2351). A virtude que a cura é a castidade: a integração da sexualidade na pessoa, que aprende a amar sem usar o outro (CIC 2337). Ela não se vence só com força de vontade, mas com graça, guarda dos sentidos, amizade e um coração ocupado com algo maior."
    ),
    howItWorks = t(
        "Casi nunca empieza con un acto, sino con una ocasión: soledad por la noche, cansancio, aburrimiento, una herida de rechazo, el estrés del trabajo, el móvil en la cama, una imagen que aparece sin buscarla. La imaginación y la mirada son la puerta; la pornografía y las redes explotan esa puerta con una oferta infinita, inmediata y sin rostro. Lo que se busca casi nunca es solo placer: es consuelo, sentirse deseado, huir de la ansiedad o del vacío.\n\nLa tradición describe el proceso en etapas: la sugestión (un pensamiento o imagen que llega, sin culpa mientras no se acoge), el deleite o complacencia (detenerse a saborearlo) y el consentimiento (la voluntad adhiere). Los Padres del desierto, Evagrio y Casiano, hablaban de los «logismoi»: pensamientos que se presentan y que conviene reconocer pronto, cuando aún son pequeños, antes de que echen raíces. Sentir la atracción no es pecar; pecar es consentirla deliberadamente. Pero cuanto antes se corta, más fácil es.\n\nEl engaño es sutil: «es inofensivo, no hago daño a nadie», «lo necesito para dormir o relajarme», «ya he caído, qué más da». Juan Pablo II, en su teología del cuerpo, señala el núcleo: la lujuria reduce a la persona a objeto de uso y al propio sujeto a un consumidor; Karol Wojtyła, en Amor y responsabilidad (cap. I), opone el amor al «uso» de la persona. Por eso, incluso a solas, la lujuria entrena el corazón a mirar y a amar de forma que luego perjudica a los demás.",
        "It almost never begins with an act, but with an occasion: loneliness at night, tiredness, boredom, a wound of rejection, work stress, the phone in bed, an image that appears unsought. The imagination and the eyes are the door; pornography and social media exploit that door with an endless, instant and faceless supply. What is sought is rarely just pleasure: it is comfort, feeling desired, escape from anxiety or emptiness.\n\nThe tradition describes the process in stages: suggestion (a thought or image arrives, no guilt while it is not welcomed), delight or complacency (lingering to savour it) and consent (the will adheres). The Desert Fathers, Evagrius and Cassian, spoke of the “logismoi”: thoughts that present themselves and are best recognised early, while still small, before they take root. Feeling an attraction is not sin; sin is deliberately consenting to it. But the sooner it is cut, the easier it is.\n\nThe deception is subtle: “it's harmless, it hurts nobody”, “I need it to sleep or unwind”, “I've already fallen, so what”. John Paul II, in his theology of the body, names the core: lust reduces the person to an object of use and the one looking to a consumer; Karol Wojtyła, in Love and Responsibility (ch. I), sets love against the “use” of a person. So even in private, lust trains the heart to see and love in ways that later harm others.",
        "Quase nunca começa por um ato, mas por uma ocasião: solidão à noite, cansaço, tédio, uma ferida de rejeição, o estresse do trabalho, o celular na cama, uma imagem que aparece sem ser procurada. A imaginação e o olhar são a porta; a pornografia e as redes exploram essa porta com uma oferta infinita, imediata e sem rosto. O que se busca raramente é só prazer: é consolo, sentir-se desejado, fugir da ansiedade ou do vazio.\n\nA tradição descreve o processo em etapas: a sugestão (um pensamento ou imagem que chega, sem culpa enquanto não é acolhido), o deleite ou complacência (demorar-se em saboreá-lo) e o consentimento (a vontade adere). Os Padres do deserto, Evágrio e Cassiano, falavam dos «logismoi»: pensamentos que se apresentam e convém reconhecer cedo, enquanto pequenos, antes de criarem raízes. Sentir a atração não é pecar; pecar é consenti-la deliberadamente. Mas quanto mais cedo se corta, mais fácil fica.\n\nO engano é sutil: «é inofensivo, não faço mal a ninguém», «preciso disso para dormir ou relaxar», «já caí, então tanto faz». João Paulo II, na teologia do corpo, aponta o núcleo: a luxúria reduz a pessoa a objeto de uso e quem olha a consumidor; Karol Wojtyła, em Amor e responsabilidade (cap. I), opõe o amor ao «uso» da pessoa. Por isso, mesmo a sós, a luxúria treina o coração a olhar e amar de um modo que depois prejudica os outros."
    ),
    whyItIsHard = t(
        "Es difícil porque toca una fuerza buena y profunda, el deseo de amar y ser amado, y porque el hábito se refuerza solo: cada vez que se busca el alivio inmediato, el cerebro asocia esa señal (aburrimiento, tristeza, una pantalla) con la recompensa, y la próxima vez el impulso llega más rápido y con más fuerza. Dicho sencillamente: lo que repetimos se vuelve camino. A ello se suma la concupiscencia, herencia del pecado original que no desaparece ni con el bautismo (CIC 405, 1264, 2515), y una cultura que presenta la impureza como normal y a la castidad como rareza.\n\nPor último, la vergüenza alimenta el ciclo: caer produce culpa, la culpa duele, y para aliviar el dolor se vuelve al mismo refugio. Se vive en secreto, y el secreto fortalece el vicio. Dios no responde a la caída con desprecio sino con misericordia; salir del ciclo exige aprender a levantarse pronto, sin desesperar, y a dejar de estar solo.",
        "It is hard because it touches a good and deep force, the desire to love and be loved, and because the habit reinforces itself: each time quick relief is sought, the brain links that cue (boredom, sadness, a screen) with the reward, and next time the urge arrives faster and stronger. Put simply: what we repeat becomes a path. Add concupiscence, the inheritance of original sin that remains even after baptism (CCC 405, 1264, 2515), and a culture that presents impurity as normal and chastity as odd.\n\nFinally, shame feeds the cycle: falling brings guilt, guilt hurts, and to ease the pain one returns to the same refuge. It is lived in secret, and secrecy strengthens the vice. God answers a fall not with contempt but with mercy; leaving the cycle requires learning to rise quickly, without despair, and to stop being alone.",
        "É difícil porque toca uma força boa e profunda, o desejo de amar e ser amado, e porque o hábito se reforça sozinho: cada vez que se busca o alívio imediato, o cérebro associa esse sinal (tédio, tristeza, uma tela) à recompensa, e da próxima vez o impulso chega mais rápido e mais forte. Dito de forma simples: o que repetimos vira caminho. Soma-se a concupiscência, herança do pecado original que permanece mesmo depois do batismo (CIC 405, 1264, 2515), e uma cultura que apresenta a impureza como normal e a castidade como estranheza.\n\nPor fim, a vergonha alimenta o ciclo: cair traz culpa, a culpa dói, e para aliviar a dor volta-se ao mesmo refúgio. Vive-se em segredo, e o segredo fortalece o vício. Deus não responde à queda com desprezo, mas com misericórdia; sair do ciclo exige aprender a se levantar logo, sem desesperar, e deixar de estar sozinho."
    ),
    warningSigns = listOf(
        t("Buscas pantallas o redes cuando estás cansado, solo o triste, y te cuesta cerrarlas.", "You reach for screens or social media when tired, alone or sad, and find it hard to close them.", "Você recorre a telas ou redes quando está cansado, sozinho ou triste, e custa fechá-las."),
        t("Te quedas a solas con el móvil por la noche o en lugares cerrados «solo un momento».", "You stay alone with your phone at night or behind closed doors “just for a moment”.", "Você fica a sós com o celular à noite ou em lugares fechados «só um instante»."),
        t("Miras a las personas como cuerpos o como posibles objetos de fantasía, también en la calle o en el trabajo.", "You look at people as bodies or as material for fantasy, even on the street or at work.", "Você olha as pessoas como corpos ou como material de fantasia, também na rua ou no trabalho."),
        t("Rezas con menos ganas, evitas la confesión o la comunión por vergüenza y empiezas a ocultar cosas.", "You pray with less desire, avoid confession or communion out of shame and begin hiding things.", "Você reza com menos vontade, evita a confissão ou a comunhão por vergonha e começa a esconder coisas."),
        t("Cada vez necesitas más intensidad o más tiempo, y sientes vacío o irritación después.", "You need ever more intensity or time, and feel emptiness or irritation afterwards.", "Você precisa de cada vez mais intensidade ou tempo, e sente vazio ou irritação depois."),
        t("Tus relaciones se enfrían: te cuesta la ternura real y la mirada limpia hacia tu cónyuge, novio o amigos.", "Your relationships cool: real tenderness and a clean gaze toward your spouse, partner or friends become harder.", "Seus relacionamentos esfriam: custa a ternura real e o olhar limpo para o cônjuge, namorado ou amigos.")
    ),
    inTheMoment = listOf(
        t("Huye de la ocasión: sal de la habitación, apaga la pantalla, deja el móvil lejos. José, en Egipto, no discutió: corrió (Gn 39,12).", "Flee the occasion: leave the room, switch off the screen, put the phone away. Joseph in Egypt did not argue, he ran (Gen 39:12).", "Fuja da ocasião: saia do cômodo, desligue a tela, deixe o celular longe. José do Egito não discutiu, correu (Gn 39,12)."),
        t("Nómbralo sin dramatizar: «esto es una tentación, no una orden». Así recuperas distancia.", "Name it without drama: “this is a temptation, not a command”. You regain distance.", "Nomeie sem dramatizar: «isto é uma tentação, não uma ordem». Assim você recupera distância."),
        t("Reza una oración breve: «Jesús, ayúdame», un Ave María mirando una imagen de la Virgen, o «Señor, crea en mí un corazón puro».", "Pray briefly: “Jesus, help me”, a Hail Mary looking at an image of Our Lady, or “Lord, create in me a clean heart”.", "Reze algo breve: «Jesus, ajuda-me», uma Ave-Maria olhando uma imagem de Nossa Senhora, ou «Senhor, cria em mim um coração puro»."),
        t("Cambia el cuerpo y el lugar: camina, lávate la cara con agua fría, sal a la calle o a un lugar con gente.", "Change body and place: walk, wash your face with cold water, go outside or where there are people.", "Mude o corpo e o lugar: caminhe, lave o rosto com água fria, saia para a rua ou para um lugar com gente."),
        t("Haz algo con las manos o la mente que ocupe de verdad: una tarea, un ejercicio, un salmo, una llamada.", "Do something that truly occupies hands or mind: a chore, exercise, a psalm, a call.", "Faça algo que ocupe de verdade as mãos ou a mente: uma tarefa, exercício, um salmo, um telefonema."),
        t("Escribe o llama a tu apoyo de confianza (amigo, director, grupo). Decir en voz alta la tentación le quita poder.", "Text or call your trusted support (friend, director, group). Saying the temptation aloud takes away its power.", "Escreva ou ligue para seu apoio de confiança (amigo, diretor, grupo). Dizer a tentação em voz alta tira-lhe poder."),
        t("Si caes, no esperes: rézale a Dios con sencillez, levántate, pide perdón y cuenta pronto la caída en confesión. Sin desesperar.", "If you fall, do not wait: speak simply to God, rise, ask forgiveness and tell it soon in confession. Without despair.", "Se cair, não espere: fale com Deus com simplicidade, levante-se, peça perdão e conte logo a queda na confissão. Sem desesperar.")
    ),
    dailyRoutine = listOf(
        t("Mañana: ofrece el día a Dios (breve oración de ofrecimiento) y encomienda tu pureza a la Virgen. Decide un propósito concreto para hoy.", "Morning: offer the day to God (a short offering prayer) and entrust your purity to Our Lady. Choose one concrete resolution for today.", "Manhã: ofereça o dia a Deus (breve oração de oferecimento) e confie sua pureza a Nossa Senhora. Escolha um propósito concreto para hoje."),
        t("Custodia de los sentidos: filtros o bloqueadores en dispositivos, redes sin notificaciones tentadoras, el móvil fuera del dormitorio por la noche.", "Guarding the senses: filters or blockers on devices, social media without tempting alerts, the phone out of the bedroom at night.", "Guarda dos sentidos: filtros ou bloqueadores nos dispositivos, redes sem notificações tentadoras, o celular fora do quarto à noite."),
        t("Plan para tus horas débiles: si sabes que el riesgo es a las 23:00 o los domingos por la tarde, ten ya preparada una actividad, compañía o un lugar.", "A plan for your weak hours: if you know the risk is at 11 pm or Sunday afternoons, have an activity, company or place ready.", "Plano para suas horas fracas: se sabe que o risco é às 23h ou nas tardes de domingo, deixe pronta uma atividade, companhia ou lugar."),
        t("Mediodía: una pausa de un minuto para preguntarte cómo estás (¿cansado?, ¿solo?, ¿ansioso?) y rezar un Ave María.", "Midday: a one-minute pause to ask how you are (tired? lonely? anxious?) and pray a Hail Mary.", "Meio-dia: uma pausa de um minuto para perguntar como você está (cansado? sozinho? ansioso?) e rezar uma Ave-Maria."),
        t("Noche: examen de conciencia breve al modo de San Ignacio: gracias, petición de luz, repaso del día, perdón, propósito para mañana. Anota el patrón, no solo las caídas.", "Evening: a brief examen in the manner of St Ignatius: thanks, ask for light, review the day, ask forgiveness, resolve for tomorrow. Note the pattern, not just the falls.", "Noite: exame de consciência breve ao modo de Santo Inácio: ação de graças, pedido de luz, revisão do dia, perdão, propósito para amanhã. Anote o padrão, não só as quedas."),
        t("Ritmo semanal: confesión regular (cada una o dos semanas, con el mismo confesor si es posible) y Misa dominical con comunión.", "Weekly rhythm: regular confession (every one or two weeks, with the same confessor if possible) and Sunday Mass with communion.", "Ritmo semanal: confissão regular (a cada uma ou duas semanas, com o mesmo confessor se possível) e Missa dominical com comunhão."),
        t("Cultiva el bien contrario: un rato de deporte, servicio, lectura, amistad sana o contemplación de la belleza (naturaleza, arte, música).", "Cultivate the opposite good: time for sport, service, reading, healthy friendship or contemplating beauty (nature, art, music).", "Cultive o bem contrário: um tempo de esporte, serviço, leitura, amizade sadia ou contemplação da beleza (natureza, arte, música).")
    ),
    plan = listOf(
        GuidePhase(
            title = t("Quitar ocasiones y pedir ayuda", "Remove occasions and ask for help", "Retirar ocasiões e pedir ajuda"),
            days = "1–7",
            focus = t("Reducir el acceso y romper el secreto. Nadie vence a oscuras.", "Reduce access and break the secrecy. No one wins in the dark.", "Reduzir o acesso e romper o segredo. Ninguém vence no escuro."),
            practices = listOf(
                t("Instala bloqueadores o filtros y pide a alguien de confianza que sea tu contraseña o apoyo.", "Install blockers or filters and ask a trusted person to hold the password or be your support.", "Instale bloqueadores ou filtros e peça a alguém de confiança que guarde a senha ou seja seu apoio."),
                t("Haz una buena confesión y cuéntale al confesor tu patrón; pídele orientación concreta.", "Make a good confession and tell the confessor your pattern; ask for concrete guidance.", "Faça uma boa confissão e conte ao confessor seu padrão; peça orientação concreta."),
                t("Saca el móvil y el portátil del dormitorio por la noche; consigue un despertador.", "Take the phone and laptop out of the bedroom at night; get an alarm clock.", "Tire celular e notebook do quarto à noite; arranje um despertador."),
                t("Anota cuándo, dónde y con qué emoción surgen los impulsos.", "Note when, where and with what emotion the urges arise.", "Anote quando, onde e com que emoção surgem os impulsos.")
            )
        ),
        GuidePhase(
            title = t("Disciplinar el día", "Give the day a shape", "Dar forma ao dia"),
            days = "8–14",
            focus = t("Construir una rutina que no deje huecos vacíos.", "Build a routine that leaves no empty gaps.", "Construir uma rotina que não deixe vazios."),
            practices = listOf(
                t("Fija horarios de sueño regulares; el cansancio debilita.", "Set regular sleep hours; tiredness weakens.", "Fixe horários de sono regulares; o cansaço enfraquece."),
                t("Vive la rutina diaria completa: ofrecimiento, plan para horas débiles, examen nocturno.", "Live the full daily routine: offering, plan for weak hours, evening examen.", "Viva a rotina diária completa: oferecimento, plano para horas fracas, exame noturno."),
                t("Añade una pequeña mortificación voluntaria (ducha fría, no picar, dejar un capricho) ofrecida por tu pureza.", "Add a small voluntary mortification (cold shower, no snacking, skipping a treat) offered for your purity.", "Acrescente uma pequena mortificação voluntária (banho frio, não beliscar, deixar um capricho) oferecida pela sua pureza."),
                t("Un ejercicio físico regular, sensato y adecuado a tu salud.", "A regular physical exercise, sensible and suited to your health.", "Um exercício físico regular, sensato e adequado à sua saúde.")
            )
        ),
        GuidePhase(
            title = t("Mirar de otro modo", "Learn to see differently", "Aprender a olhar de outro modo"),
            days = "15–21",
            focus = t("Educar la mirada y el corazón: ver personas, no objetos.", "Educate the gaze and the heart: see persons, not objects.", "Educar o olhar e o coração: ver pessoas, não objetos."),
            practices = listOf(
                t("Cada vez que te cruces con alguien, reza por esa persona en una palabra («Dios la bendiga»).", "Whenever you meet someone, pray for that person in a word (“God bless them”).", "Cada vez que cruzar com alguém, reze por essa pessoa numa palavra («Deus a abençoe»)."),
                t("Lee con calma la catequesis de Juan Pablo II sobre el amor humano o un libro sobre castidad para tu estado de vida.", "Read calmly John Paul II's catechesis on human love or a book on chastity for your state of life.", "Leia com calma a catequese de João Paulo II sobre o amor humano ou um livro sobre castidade para o seu estado de vida."),
                t("Dedica tiempo a la belleza sana (paseo, música, arte) y a la oración ante el Santísimo.", "Spend time on wholesome beauty (a walk, music, art) and prayer before the Blessed Sacrament.", "Dedique tempo à beleza sadia (passeio, música, arte) e à oração diante do Santíssimo."),
                t("Habla con tu apoyo semanalmente; cuenta victorias, no solo caídas.", "Talk with your support weekly; tell the victories, not only the falls.", "Converse com seu apoio semanalmente; conte vitórias, não só quedas.")
            )
        ),
        GuidePhase(
            title = t("Servir y amar de verdad", "Serve and love for real", "Servir e amar de verdade"),
            days = "22–30",
            focus = t("Sustituir la búsqueda de consuelo por el don de sí.", "Replace seeking comfort with self-gift.", "Substituir a busca de consolo pelo dom de si."),
            practices = listOf(
                t("Elige un servicio concreto y regular (visitar a un enfermo, ayudar en la parroquia, cuidar a alguien).", "Choose a concrete, regular service (visiting a sick person, helping at the parish, caring for someone).", "Escolha um serviço concreto e regular (visitar um doente, ajudar na paróquia, cuidar de alguém)."),
                t("Cultiva amistades sanas y presencia real: cena, llamada, conversación sin pantallas.", "Cultivate healthy friendships and real presence: a meal, a call, conversation without screens.", "Cultive amizades sadias e presença real: jantar, telefonema, conversa sem telas."),
                t("Protocolo de recaída: si caes, no esperes al sábado; reza, cuéntalo a tu apoyo, confiesa pronto y retoma el plan ese mismo día.", "Relapse protocol: if you fall, do not wait for the weekend; pray, tell your support, confess soon and resume the plan the same day.", "Protocolo de recaída: se cair, não espere o fim de semana; reze, conte ao seu apoio, confesse logo e retome o plano no mesmo dia."),
                t("Un ayuno o renuncia semanal ofrecido por la pureza (compatible con tu salud).", "A weekly fast or renunciation offered for purity (compatible with your health).", "Um jejum ou renúncia semanal oferecido pela pureza (compatível com sua saúde).")
            )
        ),
        GuidePhase(
            title = t("Consolidar y escribir una regla de vida", "Consolidate and write a rule of life", "Consolidar e escrever uma regra de vida"),
            days = "31–40",
            focus = t("Convertir lo aprendido en un modo de vivir que dure.", "Turn what you learned into a way of life that lasts.", "Transformar o aprendido num modo de viver que dure."),
            practices = listOf(
                t("Escribe tu regla de vida: oraciones, sacramentos, límites digitales, apoyos, horas de riesgo.", "Write your rule of life: prayers, sacraments, digital limits, supports, risk hours.", "Escreva sua regra de vida: orações, sacramentos, limites digitais, apoios, horas de risco."),
                t("Revísala con tu director espiritual o confesor y ajústala a tu realidad.", "Review it with your spiritual director or confessor and adjust it to your reality.", "Revise-a com seu diretor espiritual ou confessor e ajuste-a à sua realidade."),
                t("Da gracias por cada paso, aunque haya habido caídas; la castidad es un camino, no un examen.", "Give thanks for each step, even if there were falls; chastity is a journey, not an exam.", "Agradeça cada passo, ainda que tenha havido quedas; a castidade é um caminho, não uma prova."),
                t("Continúa con revisión mensual y pide ayuda si el patrón reaparece.", "Continue with a monthly review and ask for help if the pattern returns.", "Continue com revisão mensal e peça ajuda se o padrão reaparecer.")
            )
        )
    ),
    scripture = listOf(
        GuideScripture(
            t("1 Co 10,13", "1 Cor 10:13", "1Cor 10,13"),
            t("Dios no permite una prueba superior a tus fuerzas y siempre ofrece una salida: tu tarea es buscarla y tomarla pronto.", "God does not allow a trial beyond your strength and always provides a way out: your task is to look for it and take it early.", "Deus não permite uma prova acima das suas forças e sempre oferece uma saída: sua tarefa é procurá-la e tomá-la cedo.")
        ),
        GuideScripture(
            t("Mt 5,8", "Matt 5:8", "Mt 5,8"),
            t("La pureza de corazón no es solo prohibición: es una promesa, la de ver a Dios. Da sentido al esfuerzo.", "Purity of heart is not only prohibition: it carries a promise, to see God. It gives meaning to the effort.", "A pureza de coração não é só proibição: traz uma promessa, ver a Deus. Dá sentido ao esforço.")
        ),
        GuideScripture(
            t("1 Co 6,18-20", "1 Cor 6:18-20", "1Cor 6,18-20"),
            t("Pablo aconseja huir y recuerda que el cuerpo es templo del Espíritu y que fuimos comprados a gran precio: tu cuerpo tiene dignidad.", "Paul advises fleeing and reminds us the body is a temple of the Spirit and we were bought at a price: your body has dignity.", "Paulo aconselha fugir e lembra que o corpo é templo do Espírito e fomos comprados por alto preço: seu corpo tem dignidade.")
        ),
        GuideScripture(
            t("Gn 39,7-12", "Gen 39:7-12", "Gn 39,7-12"),
            t("José, acosado, no negocia ni se queda a pensarlo: sale corriendo. A veces la fortaleza es huida rápida.", "Joseph, pressed, neither bargains nor lingers: he runs out. Sometimes courage is a quick escape.", "José, assediado, não negocia nem fica pensando: sai correndo. Às vezes a fortaleza é fuga rápida.")
        ),
        GuideScripture(
            t("Sal 51,12", "Ps 51:10 (51:12 in NABRE)", "Sl 51,12"),
            t("Es la petición de quien se sabe débil: no «arréglame un poco», sino «crea en mí un corazón puro». La pureza es don que se pide.", "The plea of one who knows his weakness: not “tidy me up” but “create in me a clean heart”. Purity is a gift to be asked for.", "É o pedido de quem se sabe fraco: não «arruma-me um pouco», mas «cria em mim um coração puro». A pureza é dom que se pede.")
        )
    ),
    saintsWisdom = listOf(
        GuideCitation(
            t("San Agustín", "St Augustine", "Santo Agostinho"),
            t("Agustín cuenta que rezaba por la castidad pero temía ser escuchado demasiado pronto («dame castidad, pero todavía no»), y que solo la gracia lo liberó; confiesa que sin la ayuda de Dios la continencia no es posible y le pide: «da lo que mandas».", "Augustine tells how he prayed for chastity yet feared being heard too soon (“give me chastity, but not yet”), and that only grace freed him; he confesses continence is impossible without God's help and asks him: “give what you command”.", "Agostinho conta que rezava pela castidade mas temia ser ouvido cedo demais («dá-me castidade, mas ainda não»), e que só a graça o libertou; confessa que sem ajuda de Deus a continência é impossível e pede: «dá o que mandas»."),
            t("Confesiones VIII, 7 y X, 29", "Confessions VIII, 7 and X, 29", "Confissões VIII, 7 e X, 29"),
            false
        ),
        GuideCitation(
            t("San Juan Casiano", "St John Cassian", "São João Cassiano"),
            t("Casiano enseña que la pureza se guarda con una vigilancia que empieza en los pensamientos y en el cuerpo (sueño, comida, trabajo) y recuerda que no se logra solo con esfuerzo humano, sino con la gracia.", "Cassian teaches that purity is kept by a vigilance that begins with thoughts and with the body (sleep, food, work), and that it is won not by human effort alone but by grace.", "Cassiano ensina que a pureza se guarda com uma vigilância que começa nos pensamentos e no corpo (sono, comida, trabalho) e que não se alcança só com esforço humano, mas com a graça."),
            t("Instituciones VI (sobre la fornicación)", "Institutes, Book VI (on fornication)", "Instituições, livro VI (sobre a fornicação)"),
            false
        ),
        GuideCitation(
            t("Santo Tomás de Aquino", "St Thomas Aquinas", "Santo Tomás de Aquino"),
            t("Santo Tomás explica que la lujuria ofusca la razón y trae consecuencias en el juicio y la voluntad (ceguera de espíritu, precipitación, inconstancia), porque el placer desordenado domina la inteligencia; la remedia la castidad, virtud que ordena el deseo según la razón.", "Aquinas explains that lust clouds reason and brings consequences for judgement and will (blindness of mind, rashness, inconstancy), because disordered pleasure overpowers the intellect; chastity heals it, the virtue that orders desire according to reason.", "Santo Tomás explica que a luxúria ofusca a razão e traz consequências ao juízo e à vontade (cegueira de espírito, precipitação, inconstância), porque o prazer desordenado domina a inteligência; é curada pela castidade, virtude que ordena o desejo segundo a razão."),
            t("Suma Teológica II-II, q. 153 (la lujuria) y q. 151 (la castidad)", "Summa Theologiae II-II, q. 153 (lust) and q. 151 (chastity)", "Suma Teológica II-II, q. 153 (a luxúria) e q. 151 (a castidade)"),
            false
        ),
        GuideCitation(
            t("San Juan Pablo II", "St John Paul II", "São João Paulo II"),
            t("En su catequesis sobre el amor humano, Juan Pablo II enseña que la persona nunca debe ser tratada como un objeto de uso; la pureza no es desprecio del cuerpo sino reverencia ante su dignidad y vocación al don de sí.", "In his catechesis on human love John Paul II teaches that a person must never be treated as an object of use; purity is not contempt for the body but reverence for its dignity and its vocation to self-gift.", "Na sua catequese sobre o amor humano, João Paulo II ensina que a pessoa nunca deve ser tratada como objeto de uso; a pureza não é desprezo do corpo, mas reverência à sua dignidade e vocação ao dom de si."),
            t("Catequesis sobre el amor humano (Audiencias generales, 1979-1984), sobre Mt 5,27-28; y Amor y responsabilidad", "Catechesis on human love (General Audiences, 1979-1984), on Matt 5:27-28; and Love and Responsibility", "Catequese sobre o amor humano (Audiências gerais, 1979-1984), sobre Mt 5,27-28; e Amor e responsabilidade"),
            false
        )
    ),
    sacramentalHelps = t(
        "La confesión frecuente es el gran remedio: no solo perdona, también da gracia para el combate; conviene acudir con regularidad y con el mismo confesor si es posible, y no esperar a «estar bien» para ir. La Eucaristía alimenta la castidad y la comunión sin pecado grave es fuente de fortaleza. Ayudan también la devoción a la Virgen María (rosario, consagración), a san José y a san Luis Gonzaga, el ayuno y la mortificación moderados, la lectura espiritual, la adoración eucarística y la dirección espiritual, que acompaña con constancia. Los sacramentales (agua bendita, el escapulario, la señal de la cruz) fortalecen la confianza, siempre como apoyo y no como amuleto.",
        "Frequent confession is the great remedy: it forgives and gives grace for the struggle; go regularly and with the same confessor if possible, and do not wait to “be well” before going. The Eucharist nourishes chastity and communion, when free of grave sin, is a source of strength. Also helpful are devotion to Our Lady (rosary, consecration), to St Joseph and St Aloysius Gonzaga, moderate fasting and mortification, spiritual reading, Eucharistic adoration and spiritual direction, which accompanies steadily. Sacramentals (holy water, the scapular, the sign of the cross) strengthen trust, always as support and never as a charm.",
        "A confissão frequente é o grande remédio: não só perdoa, mas dá graça para o combate; convém ir com regularidade e com o mesmo confessor se possível, sem esperar «estar bem» para ir. A Eucaristia alimenta a castidade e a comunhão, sem pecado grave, é fonte de fortaleza. Ajudam também a devoção a Nossa Senhora (terço, consagração), a São José e a São Luís Gonzaga, o jejum e a mortificação moderados, a leitura espiritual, a adoração eucarística e a direção espiritual, que acompanha com constância. Os sacramentais (água benta, o escapulário, o sinal da cruz) fortalecem a confiança, sempre como apoio e nunca como amuleto."
    ),
    whenToSeekHelp = t(
        "Habla con un confesor o director espiritual en cuanto puedas: la vergüenza es la mayor aliada del vicio y la franqueza lo desarma. Recuerda que la responsabilidad moral depende del conocimiento y del consentimiento, y que los hábitos arraigados, la ansiedad o los traumas pueden disminuirla (CIC 1735, 2352); no es motivo para el escrúpulo ni para la desesperación. Si el consumo de pornografía o la conducta sexual se siente compulsivo, interfiere con tu trabajo o tus relaciones, o crece a pesar de tus intentos, busca también a un psicólogo o terapeuta cualificado y, si existen, grupos de apoyo; la ayuda profesional y la espiritual se complementan. Si hay ideas de hacerte daño, abuso o riesgo para otra persona, pide ayuda urgente a servicios de emergencia o de salud mental de tu país.",
        "Speak with a confessor or spiritual director as soon as you can: shame is the vice's greatest ally and candour disarms it. Remember that moral responsibility depends on knowledge and consent, and that entrenched habits, anxiety or trauma can lessen it (CCC 1735, 2352); this is no reason for scrupulosity or despair. If pornography use or sexual behaviour feels compulsive, interferes with work or relationships, or grows despite your efforts, also seek a qualified psychologist or therapist and, where they exist, support groups; professional and spiritual help complement each other. If there are thoughts of harming yourself, abuse, or risk to another person, seek urgent help from emergency or mental health services in your country.",
        "Fale com um confessor ou diretor espiritual assim que puder: a vergonha é a maior aliada do vício e a franqueza o desarma. Lembre que a responsabilidade moral depende do conhecimento e do consentimento, e que hábitos arraigados, ansiedade ou traumas podem diminuí-la (CIC 1735, 2352); isso não é motivo para escrúpulo nem para desespero. Se o consumo de pornografia ou o comportamento sexual parece compulsivo, atrapalha seu trabalho ou relacionamentos, ou cresce apesar dos seus esforços, procure também um psicólogo ou terapeuta qualificado e, onde existirem, grupos de apoio; a ajuda profissional e a espiritual se complementam. Se houver pensamentos de se ferir, abuso ou risco para outra pessoa, procure ajuda urgente em serviços de emergência ou de saúde mental do seu país."
    ),
    sources = listOf(
        t("Catecismo de la Iglesia Católica 2337-2359 (castidad y ofensas contra ella) y 2514-2533 (pureza del corazón, combate espiritual)", "Catechism of the Catholic Church 2337-2359 (chastity and offences against it) and 2514-2533 (purity of heart, spiritual combat)", "Catecismo da Igreja Católica 2337-2359 (castidade e ofensas contra ela) e 2514-2533 (pureza do coração, combate espiritual)"),
        t("CIC 405, 1264, 2515 (concupiscencia); CIC 1735 y 1857-1860 (imputabilidad)", "CCC 405, 1264, 2515 (concupiscence); CCC 1735 and 1857-1860 (imputability)", "CIC 405, 1264, 2515 (concupiscência); CIC 1735 e 1857-1860 (imputabilidade)"),
        t("Santo Tomás de Aquino, Suma Teológica II-II, qq. 151 y 153", "St Thomas Aquinas, Summa Theologiae II-II, qq. 151 and 153", "Santo Tomás de Aquino, Suma Teológica II-II, qq. 151 e 153"),
        t("San Juan Casiano, Instituciones, libro VI", "St John Cassian, Institutes, Book VI", "São João Cassiano, Instituições, livro VI"),
        t("San Agustín, Confesiones VIII y X", "St Augustine, Confessions VIII and X", "Santo Agostinho, Confissões VIII e X"),
        t("San Juan Pablo II, Catequesis sobre el amor humano (1979-1984); Amor y responsabilidad (1960)", "St John Paul II, Catechesis on human love (1979-1984); Love and Responsibility (1960)", "São João Paulo II, Catequese sobre o amor humano (1979-1984); Amor e responsabilidade (1960)")
    )
)

private val anger = TemptationGuide(
    sinKey = "ANGER",
    summary = t(
        "La ira es un deseo desordenado de venganza (CIC 2302) o de imponerse, que nace de una herida o de una injusticia vivida y busca hacer daño. La pasión de la ira no es pecaminosa en sí misma (CIC 1767). La virtud que la sana es la mansedumbre, que no es debilidad sino fuerza dominada por el amor. No se trata de no sentir enfado, sino de no dejar que gobierne tus palabras y actos.",
        "Anger is a disordered desire for revenge (CCC 2302) or to impose oneself, born of a wound or a perceived injustice, which seeks to harm. The passion of anger is not sinful in itself (CCC 1767). The virtue that heals it is meekness, which is not weakness but strength governed by love. The aim is not to feel no anger, but not to let it rule your words and actions.",
        "A ira é um desejo desordenado de vingança (CIC 2302) ou de se impor, que nasce de uma ferida ou de uma injustiça sentida e busca ferir. A paixão da ira não é pecaminosa em si mesma (CIC 1767). A virtude que a cura é a mansidão, que não é fraqueza, mas força governada pelo amor. Não se trata de não sentir raiva, mas de não deixá-la governar suas palavras e atos."
    ),
    howItWorks = t(
        "Las ocasiones suelen ser conocidas: el cansancio, el hambre, el tráfico, la sensación de no ser respetado, una crítica, una promesa rota, discusiones familiares o digitales (redes, mensajes). Debajo suele haber una herida: orgullo herido, miedo, impotencia, o una injusticia real o supuesta. La ira se presenta como protectora, pero en realidad defiende el yo.\n\nSegún la tradición, también aquí hay etapas: un primer movimiento involuntario (sentir el enfado), la rumiación (repetir mentalmente la ofensa, preparar respuestas, ensayar venganzas) y el consentimiento (la palabra hiriente, el portazo, el silencio castigador, la venganza). Evagrio y Casiano advertían de que el pensamiento de ira, alimentado, ciega el corazón y vuelve imposible la oración; por eso lo más eficaz es detenerlo en su inicio. La ira se disfraza de justicia («tengo razón»), de franqueza («solo digo la verdad») o de sarcasmo y humor.\n\nConviene distinguir: existe una indignación justa, que reacciona ante el mal y la injusticia con el deseo de que se corrija, sin odio y sin buscar el daño de la persona (CIC 2302; santo Tomás distingue la ira según la razón de la que se mueve por pasión desordenada). Es pecado cuando se busca la venganza, se hiere o se guarda rencor. Jesús mismo mostró indignación (Mc 3,5; Jn 2,15), pero sin perder el dominio ni el amor.",
        "The occasions are familiar: tiredness, hunger, traffic, feeling disrespected, criticism, a broken promise, family or online quarrels (social media, messages). Underneath there is usually a wound: wounded pride, fear, powerlessness, or a real or supposed injustice. Anger presents itself as protector, but really defends the self.\n\nThe tradition sees stages here too: a first involuntary movement (feeling the anger), rumination (replaying the offence, preparing replies, rehearsing revenge) and consent (the cutting word, the slammed door, the punishing silence, revenge). Evagrius and Cassian warned that anger-thoughts, once fed, blind the heart and make prayer impossible; so the most effective moment to stop them is at the start. Anger disguises itself as justice (“I'm right”), as candour (“I'm just telling the truth”) or as sarcasm and humour.\n\nA distinction matters: there is just indignation, which reacts to evil and injustice by wanting it corrected, without hatred and without seeking to harm the person (CCC 2302; Aquinas distinguishes anger according to reason from anger moved by disordered passion). It becomes sin when it seeks revenge, wounds or holds a grudge. Jesus himself showed indignation (Mark 3:5; John 2:15), yet without losing self-mastery or love.",
        "As ocasiões são conhecidas: cansaço, fome, trânsito, a sensação de não ser respeitado, uma crítica, uma promessa quebrada, discussões em família ou na internet (redes, mensagens). Por baixo costuma haver uma ferida: orgulho ferido, medo, impotência, ou uma injustiça real ou suposta. A ira se apresenta como protetora, mas na verdade defende o eu.\n\nSegundo a tradição, também aqui há etapas: um primeiro movimento involuntário (sentir a raiva), a ruminação (repetir mentalmente a ofensa, preparar respostas, ensaiar vinganças) e o consentimento (a palavra ferina, a porta batida, o silêncio punitivo, a vingança). Evágrio e Cassiano advertiam que o pensamento de ira, alimentado, cega o coração e torna impossível a oração; por isso o mais eficaz é detê-lo no começo. A ira se disfarça de justiça («eu tenho razão»), de franqueza («só estou dizendo a verdade») ou de sarcasmo e humor.\n\nConvém distinguir: existe indignação justa, que reage ao mal e à injustiça desejando que sejam corrigidos, sem ódio e sem buscar o dano da pessoa (CIC 2302; Santo Tomás distingue a ira segundo a razão da que se move por paixão desordenada). Torna-se pecado quando busca vingança, fere ou guarda rancor. O próprio Jesus mostrou indignação (Mc 3,5; Jo 2,15), mas sem perder o domínio de si nem o amor."
    ),
    whyItIsHard = t(
        "La ira es rápida: el cuerpo reacciona (corazón acelerado, tensión) antes de que la razón pueda intervenir, y cada vez que se la deja salir, se refuerza el camino por el que sale, hasta que parece automático. Además da una sensación inmediata de poder y alivio, aunque después deje arrepentimiento. Nace con frecuencia de heridas antiguas, de cómo se vivió el conflicto en la familia, del cansancio o del estrés, y se alimenta de la concupiscencia y del orgullo (CIC 405, 1264, 2515).\n\nLa cultura la premia (quien grita, gana; las redes recompensan la indignación), y el aislamiento y la falta de sueño la hacen más fácil. Y el ciclo de vergüenza también opera aquí: tras estallar, uno se justifica o se hunde, en lugar de reparar y aprender. Vencerla exige tiempo, humildad para pedir perdón y práctica repetida de pequeñas victorias.",
        "Anger is fast: the body reacts (racing heart, tension) before reason can step in, and each time it is let out, the path by which it exits is reinforced until it feels automatic. It also gives an immediate sense of power and relief, even if regret follows. It often springs from old wounds, from how conflict was lived in the family, from tiredness or stress, and is fed by concupiscence and pride (CCC 405, 1264, 2515).\n\nCulture rewards it (whoever shouts wins; social media pays for outrage), and isolation and lack of sleep make it easier. The shame cycle operates here too: after an outburst one justifies oneself or sinks, instead of repairing and learning. Overcoming it takes time, humility to ask forgiveness, and repeated practice of small victories.",
        "A ira é rápida: o corpo reage (coração acelerado, tensão) antes que a razão possa intervir, e cada vez que se deixa sair, reforça-se o caminho pelo qual sai, até parecer automático. Além disso dá uma sensação imediata de poder e alívio, ainda que depois deixe arrependimento. Nasce com frequência de feridas antigas, de como o conflito foi vivido na família, do cansaço ou do estresse, e se alimenta da concupiscência e do orgulho (CIC 405, 1264, 2515).\n\nA cultura a premia (quem grita, ganha; as redes recompensam a indignação), e o isolamento e a falta de sono a facilitam. O ciclo da vergonha também opera aqui: depois de explodir, a pessoa se justifica ou se afunda, em vez de reparar e aprender. Vencê-la exige tempo, humildade para pedir perdão e prática repetida de pequenas vitórias."
    ),
    warningSigns = listOf(
        t("Respondes con dureza o ironía a cosas pequeñas y luego te arrepientes.", "You answer small things with harshness or irony and then regret it.", "Você responde com dureza ou ironia a coisas pequenas e depois se arrepende."),
        t("Repites mentalmente discusiones o ensayas lo que dirás para «ganar» o herir.", "You replay arguments in your head or rehearse what you will say to “win” or wound.", "Você repete discussões na cabeça ou ensaia o que dirá para «ganhar» ou ferir."),
        t("Guardas rencor, evitas a alguien o le niegas el saludo; no soportas oír su nombre.", "You hold grudges, avoid someone or withhold greeting; you cannot bear to hear their name.", "Você guarda rancor, evita alguém ou nega o cumprimento; não suporta ouvir o nome da pessoa."),
        t("Escribes mensajes o comentarios en caliente y los envías sin esperar.", "You write messages or comments in the heat of the moment and send them without waiting.", "Você escreve mensagens ou comentários no calor do momento e os envia sem esperar."),
        t("Alzas la voz, golpeas objetos, o en casa se anda «con cuidado» a tu alrededor.", "You raise your voice, hit objects, or people at home walk on eggshells around you.", "Você levanta a voz, bate em objetos, ou em casa andam «com cuidado» perto de você."),
        t("Te cuesta rezar, perdonar o decir el Padrenuestro sin tropezar en «como nosotros perdonamos».", "It is hard to pray, to forgive, or to say the Our Father without stumbling at “as we forgive”.", "Custa rezar, perdoar ou dizer o Pai-Nosso sem tropeçar no «assim como nós perdoamos».")
    ),
    inTheMoment = listOf(
        t("Para antes de hablar o escribir: cuenta hasta diez, respira lento (inhala 4, exhala 6) y suelta los hombros.", "Stop before speaking or writing: count to ten, breathe slowly (in 4, out 6) and drop your shoulders.", "Pare antes de falar ou escrever: conte até dez, respire devagar (inspire 4, expire 6) e solte os ombros."),
        t("Di por dentro el Nombre de Jesús o «Señor, dame mansedumbre». Una oración breve interrumpe el guion.", "Say inwardly the Name of Jesus or “Lord, give me meekness”. A brief prayer breaks the script.", "Diga por dentro o Nome de Jesus ou «Senhor, dá-me mansidão». Uma oração breve interrompe o roteiro."),
        t("Aléjate si puedes: «necesito un momento, hablamos luego». Retirarse a tiempo no es huir, es prudencia.", "Step away if you can: “I need a moment, let's talk later”. Withdrawing in time is not running away, it is prudence.", "Afaste-se se puder: «preciso de um momento, falamos depois». Retirar-se a tempo não é fugir, é prudência."),
        t("Baja la voz y el ritmo; una respuesta suave desarma (Pr 15,1). No envíes nada escrito hasta pasadas unas horas.", "Lower your voice and pace; a gentle answer disarms (Prov 15:1). Send nothing in writing until some hours have passed.", "Baixe a voz e o ritmo; uma resposta suave desarma (Pr 15,1). Não envie nada escrito antes de passadas algumas horas."),
        t("Pregúntate: ¿qué herida o miedo hay debajo? ¿Qué necesito de verdad: respeto, descanso, ser escuchado?", "Ask yourself: what wound or fear lies beneath? What do I truly need: respect, rest, to be heard?", "Pergunte-se: que ferida ou medo há por baixo? De que preciso de verdade: respeito, descanso, ser ouvido?"),
        t("Ora por la persona que te enfadó, aunque sea con una frase: «Señor, bendícela».", "Pray for the person who angered you, even with one sentence: “Lord, bless them”.", "Reze pela pessoa que o irritou, mesmo com uma frase: «Senhor, abençoa-a»."),
        t("Si ya hiriste a alguien, no esperes a que se enfríe todo: pide perdón sencillo y concreto, sin «pero» ni excusas.", "If you have already hurt someone, do not wait for everything to cool: ask forgiveness simply and concretely, with no “but” or excuses.", "Se já feriu alguém, não espere esfriar tudo: peça perdão de modo simples e concreto, sem «mas» nem desculpas.")
    ),
    dailyRoutine = listOf(
        t("Mañana: ofrece el día y pide mansedumbre y paciencia; piensa en la situación más probable de conflicto y cómo quieres responder.", "Morning: offer the day and ask for meekness and patience; foresee the likeliest conflict and how you want to respond.", "Manhã: ofereça o dia e peça mansidão e paciência; preveja a situação mais provável de conflito e como quer responder."),
        t("Cuida lo básico: dormir lo suficiente, comer a horas, moverte; el cansancio y el hambre alimentan la ira.", "Look after the basics: enough sleep, regular meals, movement; tiredness and hunger feed anger.", "Cuide do básico: dormir o suficiente, comer em horários, mover-se; cansaço e fome alimentam a ira."),
        t("Regla del retraso: no respondas mensajes que te alteran hasta haber rezado o paseado un poco.", "The delay rule: do not answer messages that upset you until you have prayed or walked a little.", "Regra do adiamento: não responda mensagens que o alteram até ter rezado ou caminhado um pouco."),
        t("Mediodía: una pausa para soltar la tensión y recordar a quién tienes que perdonar hoy; reza un Padrenuestro despacio.", "Midday: a pause to release tension and recall whom you must forgive today; pray an Our Father slowly.", "Meio-dia: uma pausa para soltar a tensão e lembrar a quem precisa perdoar hoje; reze um Pai-Nosso devagar."),
        t("Noche: examen de conciencia al modo de San Ignacio: ¿cuándo me enfadé?, ¿qué lo desató?, ¿cómo respondí?, ¿a quién debo una disculpa? No te duermas con un rencor sin entregar a Dios (Ef 4,26).", "Evening: an examen in the manner of St Ignatius: when did I get angry, what set it off, how did I respond, whom do I owe an apology? Do not go to sleep with a grudge not handed over to God (Eph 4:26).", "Noite: exame de consciência ao modo de Santo Inácio: quando me irritei, o que desencadeou, como respondi, a quem devo um pedido de desculpas? Não durma com um rancor não entregue a Deus (Ef 4,26)."),
        t("Cada día, una acción de dulzura deliberada: un favor discreto, una palabra amable a quien te cuesta.", "Each day, one deliberate act of gentleness: a quiet favour, a kind word to someone who is difficult for you.", "Cada dia, uma ação deliberada de doçura: um favor discreto, uma palavra amável a quem lhe custa."),
        t("Semanal: confesión regular y Misa dominical; reza por quienes te hirieron.", "Weekly: regular confession and Sunday Mass; pray for those who have hurt you.", "Semanal: confissão regular e Missa dominical; reze por aqueles que o feriram.")
    ),
    plan = listOf(
        GuidePhase(
            title = t("Conocer el patrón", "Know the pattern", "Conhecer o padrão"),
            days = "1–7",
            focus = t("Observar sin juzgarte: qué te enfada, cuándo y cómo reaccionas.", "Observe without judging yourself: what angers you, when and how you react.", "Observar sem se julgar: o que o irrita, quando e como reage."),
            practices = listOf(
                t("Lleva una libreta: situación, emoción, reacción, lo que había debajo.", "Keep a notebook: situation, emotion, reaction, what lay beneath.", "Leve uma caderneta: situação, emoção, reação, o que havia por baixo."),
                t("Confiésate y pide la gracia de la mansedumbre.", "Go to confession and ask for the grace of meekness.", "Confesse-se e peça a graça da mansidão."),
                t("Identifica tus «horas rojas» (por ejemplo, al llegar a casa) y prepara un plan para ellas.", "Identify your “red hours” (for instance, arriving home) and prepare a plan for them.", "Identifique suas «horas vermelhas» (por exemplo, ao chegar em casa) e prepare um plano para elas."),
                t("Practica la respiración lenta y la pausa de diez segundos.", "Practise slow breathing and the ten-second pause.", "Pratique a respiração lenta e a pausa de dez segundos.")
            )
        ),
        GuidePhase(
            title = t("Frenar a tiempo", "Brake in time", "Frear a tempo"),
            days = "8–14",
            focus = t("Crear un espacio entre el estímulo y la respuesta.", "Create space between stimulus and response.", "Criar espaço entre o estímulo e a resposta."),
            practices = listOf(
                t("Aplica la regla del retraso a mensajes, redes y discusiones.", "Apply the delay rule to messages, social media and arguments.", "Aplique a regra do adiamento a mensagens, redes e discussões."),
                t("Prepara una frase de retirada sin ataque («prefiero hablarlo más tarde»).", "Prepare a withdrawal phrase without attack (“I'd rather talk about this later”).", "Prepare uma frase de retirada sem ataque («prefiro conversar mais tarde»)."),
                t("Ofrece una pequeña mortificación (callar un comentario, ceder en algo sin importancia) y reza el Padrenuestro despacio.", "Offer a small mortification (holding back a comment, yielding on something unimportant) and pray the Our Father slowly.", "Ofereça uma pequena mortificação (calar um comentário, ceder em algo sem importância) e reze o Pai-Nosso devagar."),
                t("Duerme y come con orden; cuida tu cuerpo.", "Sleep and eat in order; take care of your body.", "Durma e coma com ordem; cuide do seu corpo.")
            )
        ),
        GuidePhase(
            title = t("Sanar la raíz", "Heal the root", "Curar a raiz"),
            days = "15–21",
            focus = t("Trabajar las heridas y rencores que alimentan la ira.", "Work on the wounds and grudges that feed anger.", "Trabalhar as feridas e rancores que alimentam a ira."),
            practices = listOf(
                t("Haz una lista de personas con las que guardas rencor y reza cada día por una de ellas.", "List the people toward whom you hold resentment and pray each day for one of them.", "Faça uma lista de pessoas com quem guarda rancor e reze cada dia por uma delas."),
                t("Habla con tu confesor o director sobre las heridas más hondas; pregunta si te conviene ayuda profesional.", "Talk with your confessor or director about the deeper wounds; ask whether professional help would be good for you.", "Converse com seu confessor ou diretor sobre as feridas mais fundas; pergunte se convém ajuda profissional."),
                t("Medita la actitud de Jesús ante la injusticia: Mt 11,29 y la Pasión.", "Meditate on Jesus' attitude to injustice: Matt 11:29 and the Passion.", "Medite a atitude de Jesus diante da injustiça: Mt 11,29 e a Paixão."),
                t("Pide perdón a quien hayas herido, con sencillez y sin justificarte.", "Ask forgiveness of anyone you have hurt, simply and without justifying yourself.", "Peça perdão a quem tenha ferido, com simplicidade e sem se justificar.")
            )
        ),
        GuidePhase(
            title = t("Cultivar la mansedumbre", "Cultivate meekness", "Cultivar a mansidão"),
            days = "22–30",
            focus = t("Poner en el lugar de la ira la dulzura, la escucha y el servicio.", "Put gentleness, listening and service where anger was.", "Pôr no lugar da ira a doçura, a escuta e o serviço."),
            practices = listOf(
                t("Escucha de verdad a una persona al día sin interrumpir ni preparar respuesta.", "Truly listen to one person a day without interrupting or preparing a reply.", "Escute de verdade uma pessoa por dia sem interromper nem preparar resposta."),
                t("Haz un servicio concreto a quien te cuesta (una tarea, un detalle, una oración).", "Do a concrete service for someone difficult for you (a chore, a gesture, a prayer).", "Faça um serviço concreto a quem lhe custa (uma tarefa, um detalhe, uma oração)."),
                t("Lee el capítulo de san Francisco de Sales sobre la dulzura y repasa un propósito semanal.", "Read St Francis de Sales on meekness and review a weekly resolution.", "Leia o capítulo de São Francisco de Sales sobre a mansidão e reveja um propósito semanal."),
                t("Protocolo de recaída: si explotas, reconoce, pide perdón pronto, confiesa y retoma el plan sin hundirte.", "Relapse protocol: if you blow up, acknowledge it, ask forgiveness soon, confess and resume the plan without sinking.", "Protocolo de recaída: se explodir, reconheça, peça perdão logo, confesse e retome o plano sem se afundar.")
            )
        ),
        GuidePhase(
            title = t("Consolidar y escribir una regla", "Consolidate and write a rule", "Consolidar e escrever uma regra"),
            days = "31–40",
            focus = t("Convertir lo conseguido en hábitos estables y en una regla de vida.", "Turn gains into stable habits and a rule of life.", "Transformar os ganhos em hábitos estáveis e numa regra de vida."),
            practices = listOf(
                t("Escribe tu regla: horas rojas, frases de retirada, oración breve, práctica de reparación, apoyo.", "Write your rule: red hours, withdrawal phrases, short prayer, repair practice, support.", "Escreva sua regra: horas vermelhas, frases de retirada, oração breve, prática de reparação, apoio."),
                t("Revísala con tu director y ajústala; deja un rato mensual para repasarla.", "Review it with your director and adjust it; set aside a monthly time to revisit it.", "Revise-a com seu diretor e ajuste-a; reserve um tempo mensal para revê-la."),
                t("Agradece cada avance y mide el progreso por la reparación y la paz, no por no sentir enfado.", "Give thanks for each advance and measure progress by repair and peace, not by feeling no anger.", "Agradeça cada avanço e meça o progresso pela reparação e pela paz, não por não sentir raiva."),
                t("Sigue rezando por quienes te cuesta querer, de forma estable.", "Keep praying steadily for those you find hard to love.", "Continue rezando de forma estável por aqueles que lhe custa amar.")
            )
        )
    ),
    scripture = listOf(
        GuideScripture(
            t("Ef 4,26-27", "Eph 4:26-27", "Ef 4,26-27"),
            t("Se puede sentir enojo, pero no hay que dejarlo crecer ni dormir con él: no le des lugar al diablo. Es un consejo práctico para resolver el mismo día.", "Anger may be felt, but it must not be allowed to grow or be slept on: do not give the devil a foothold. A practical counsel to resolve things the same day.", "Pode-se sentir raiva, mas não se deve deixá-la crescer nem dormir com ela: não dê lugar ao diabo. Um conselho prático para resolver no mesmo dia.")
        ),
        GuideScripture(
            t("St 1,19-20", "James 1:19-20", "Tg 1,19-20"),
            t("Rápido para escuchar, lento para hablar, lento para airarse: la ira humana no produce la justicia de Dios.", "Quick to listen, slow to speak, slow to anger: human anger does not produce God's justice.", "Pronto para ouvir, lento para falar, lento para se irar: a ira humana não produz a justiça de Deus.")
        ),
        GuideScripture(
            t("Pr 15,1", "Prov 15:1", "Pr 15,1"),
            t("Una respuesta suave calma el enojo; la palabra dura lo enciende. Enseña que el tono puede cambiar la escena.", "A gentle answer calms wrath; a harsh word stirs it. It teaches that tone can change the scene.", "A resposta branda acalma o furor; a palavra dura o acende. Ensina que o tom pode mudar a cena.")
        ),
        GuideScripture(
            t("Rm 12,17-21", "Rom 12:17-21", "Rm 12,17-21"),
            t("No pagar mal con mal, no vengarse, dejar el juicio a Dios y vencer el mal con el bien: el programa cristiano ante la ofensa.", "Do not repay evil with evil, do not take revenge, leave judgement to God and overcome evil with good: the Christian programme before offence.", "Não pagar o mal com o mal, não se vingar, deixar o julgamento a Deus e vencer o mal com o bem: o programa cristão diante da ofensa.")
        ),
        GuideScripture(
            t("Mt 11,29", "Matt 11:29", "Mt 11,29"),
            t("Jesús se ofrece como maestro manso y humilde de corazón: la mansedumbre se aprende junto a Él y da descanso al alma.", "Jesus offers himself as a teacher gentle and humble of heart: meekness is learned beside him and gives rest to the soul.", "Jesus se oferece como mestre manso e humilde de coração: a mansidão se aprende junto dele e dá descanso à alma.")
        )
    ),
    saintsWisdom = listOf(
        GuideCitation(
            t("San Francisco de Sales", "St Francis de Sales", "São Francisco de Sales"),
            t("Aconseja no entregarse a la ira ni siquiera con pretextos de celo; propone rechazarla apenas se advierte, con calma, invocando la ayuda de Dios, y practicar la dulzura incluso en cosas pequeñas. Recuerda también la dulzura hacia uno mismo: corregirse con paz, sin irritarse por la propia debilidad.", "He advises not yielding to anger even under pretexts of zeal; he proposes rejecting it as soon as it is noticed, calmly, calling on God's help, and practising gentleness in small things. He also stresses gentleness toward oneself: correcting oneself in peace, without irritation at one's own weakness.", "Aconselha não ceder à ira nem sob pretexto de zelo; propõe rechaçá-la assim que se percebe, com calma, invocando a ajuda de Deus, e praticar a doçura mesmo nas pequenas coisas. Lembra também a doçura consigo mesmo: corrigir-se em paz, sem irritar-se com a própria fraqueza."),
            t("Introducción a la vida devota, parte III, cap. 8 (dulzura con el prójimo y remedio contra la ira) y cap. 9", "Introduction to the Devout Life, Part III, ch. 8 (meekness toward neighbour and remedy for anger) and ch. 9", "Introdução à vida devota, parte III, cap. 8 (doçura com o próximo e remédio contra a ira) e cap. 9"),
            false
        ),
        GuideCitation(
            t("Santo Tomás de Aquino", "St Thomas Aquinas", "Santo Tomás de Aquino"),
            t("Distingue la ira que obedece a la razón, que desea un castigo justo y proporcionado, de la ira desordenada, que busca el mal por sí mismo o más de lo debido. La mansedumbre modera la ira sin suprimirla; la falta de enojo ante lo injusto también puede ser un defecto.", "He distinguishes anger that obeys reason, desiring a just and proportionate correction, from disordered anger, which seeks harm for its own sake or beyond what is due. Meekness moderates anger without suppressing it; lacking all anger before injustice can itself be a defect.", "Distingue a ira que obedece à razão, que deseja uma correção justa e proporcional, da ira desordenada, que busca o mal por si ou além do devido. A mansidão modera a ira sem suprimi-la; a falta de ira diante do injusto também pode ser um defeito."),
            t("Suma Teológica II-II, q. 158 (la ira) y q. 157 (la mansedumbre)", "Summa Theologiae II-II, q. 158 (anger) and q. 157 (meekness)", "Suma Teológica II-II, q. 158 (a ira) e q. 157 (a mansidão)"),
            false
        ),
        GuideCitation(
            t("San Juan Casiano", "St John Cassian", "São João Cassiano"),
            t("Casiano enseña que la ira, por «justa» que parezca, ciega el ojo del corazón e impide la oración y el conocimiento de Dios; propone cortarla de raíz y no dejar que el sol se ponga sobre el enojo, buscando la reconciliación pronta.", "Cassian teaches that anger, however “just” it may seem, blinds the eye of the heart and hinders prayer and the knowledge of God; he proposes cutting it at the root and not letting the sun set on one's wrath, seeking quick reconciliation.", "Cassiano ensina que a ira, por mais «justa» que pareça, cega o olho do coração e impede a oração e o conhecimento de Deus; propõe cortá-la pela raiz e não deixar o sol se pôr sobre a ira, buscando a reconciliação pronta."),
            t("Instituciones VIII (sobre la ira)", "Institutes, Book VIII (on anger)", "Instituições, livro VIII (sobre a ira)"),
            false
        ),
        GuideCitation(
            t("San Gregorio Magno", "St Gregory the Great", "São Gregório Magno"),
            t("Gregorio enumera la ira entre los vicios capitales y describe cómo de ella nacen riñas, hinchazón de la mente, injurias, gritería, indignación y blasfemia (Moralia XXXI,45); enseña a distinguir el celo bueno del furor desordenado (Moralia V,45).", "Gregory lists anger among the capital vices and describes how quarrels, swelling of mind, contumely, clamour, indignation and blasphemy are born from it (Moralia XXXI,45); he teaches how to tell good zeal from disordered fury (Moralia V,45).", "Gregório enumera a ira entre os vícios capitais e descreve como dela nascem rixas, inchaço da mente, injúrias, gritaria, indignação e blasfêmia (Moralia XXXI,45); ensina a distinguir o zelo bom do furor desordenado (Moralia V,45)."),
            t("Moralia in Job XXXI, 45", "Moralia in Job XXXI, 45", "Moralia in Job XXXI, 45"),
            false
        )
    ),
    sacramentalHelps = t(
        "La confesión sana la ira y el rencor: obliga a reconocer, perdona y da gracia para reparar. La Eucaristía es el sacramento de la reconciliación y de la paz; el Padrenuestro, con su «como nosotros perdonamos», prepara para recibirla. Ayudan el rosario, la oración al Corazón manso y humilde de Jesús, la dirección espiritual, el ayuno moderado (que enseña dominio) y las obras de misericordia, sobre todo con quienes te cuesta. Si la ira nace de una herida profunda, la oración de perdón, repetida con paciencia, va ablandando el corazón.",
        "Confession heals anger and resentment: it requires acknowledgement, forgives and gives grace to make repair. The Eucharist is the sacrament of reconciliation and peace; the Our Father, with its “as we forgive”, prepares for receiving it. Helpful are the rosary, prayer to the meek and humble Heart of Jesus, spiritual direction, moderate fasting (which teaches mastery) and works of mercy, especially toward those who are difficult. If the anger springs from a deep wound, the prayer of forgiveness, repeated patiently, gradually softens the heart.",
        "A confissão cura a ira e o rancor: obriga a reconhecer, perdoa e dá graça para reparar. A Eucaristia é o sacramento da reconciliação e da paz; o Pai-Nosso, com seu «assim como nós perdoamos», prepara para recebê-la. Ajudam o terço, a oração ao Coração manso e humilde de Jesus, a direção espiritual, o jejum moderado (que ensina domínio) e as obras de misericórdia, sobretudo com quem lhe custa. Se a ira nasce de uma ferida profunda, a oração de perdão, repetida com paciência, vai amolecendo o coração."
    ),
    whenToSeekHelp = t(
        "Habla con tu confesor o director espiritual si la ira te domina, si guardas rencores que no logras soltar o si sientes que el perdón es imposible. Si hay arrebatos que dan miedo, golpes, amenazas o daño a personas o a animales, o miedo a ti mismo, busca ayuda profesional de inmediato (un psicólogo o servicio de salud mental) y aléjate de la situación; la seguridad de todos va primero y la violencia nunca se justifica. Si eres víctima de maltrato, pide ayuda a servicios de protección o emergencia de tu país. Pedir ayuda no es falta de fe: la gracia y los buenos profesionales trabajan juntos. Y no te llenes de escrúpulos: sentir enojo no es pecado; lo que importa es lo que haces con él.",
        "Speak with your confessor or spiritual director if anger dominates you, if you hold grudges you cannot release, or if forgiveness seems impossible. If there are frightening outbursts, blows, threats or harm to people or animals, or fear of yourself, seek professional help at once (a psychologist or mental health service) and leave the situation; everyone's safety comes first and violence is never justified. If you are a victim of abuse, ask for help from protection or emergency services in your country. Asking for help is not a lack of faith: grace and good professionals work together. And do not burden yourself with scruples: feeling anger is not a sin; what matters is what you do with it.",
        "Converse com seu confessor ou diretor espiritual se a ira o domina, se guarda rancores que não consegue soltar ou se sente que o perdão é impossível. Se há explosões que assustam, golpes, ameaças ou dano a pessoas ou animais, ou medo de si mesmo, procure ajuda profissional de imediato (um psicólogo ou serviço de saúde mental) e afaste-se da situação; a segurança de todos vem primeiro e a violência nunca se justifica. Se você é vítima de maus-tratos, peça ajuda a serviços de proteção ou emergência do seu país. Pedir ajuda não é falta de fé: a graça e bons profissionais trabalham juntos. E não se encha de escrúpulos: sentir raiva não é pecado; o que importa é o que você faz com ela."
    ),
    sources = listOf(
        t("Catecismo de la Iglesia Católica 2302-2303 (la ira y la venganza), 1866 (vicios capitales), 2842-2845 (perdón)", "Catechism of the Catholic Church 2302-2303 (anger and vengeance), 1866 (capital vices), 2842-2845 (forgiveness)", "Catecismo da Igreja Católica 2302-2303 (a ira e a vingança), 1866 (vícios capitais), 2842-2845 (perdão)"),
        t("Santo Tomás de Aquino, Suma Teológica II-II, qq. 157-158", "St Thomas Aquinas, Summa Theologiae II-II, qq. 157-158", "Santo Tomás de Aquino, Suma Teológica II-II, qq. 157-158"),
        t("San Juan Casiano, Instituciones, libro VIII", "St John Cassian, Institutes, Book VIII", "São João Cassiano, Instituições, livro VIII"),
        t("San Gregorio Magno, Moralia in Job XXXI, 45 y V, 45", "St Gregory the Great, Moralia in Job XXXI, 45 and V, 45", "São Gregório Magno, Moralia in Job XXXI, 45 e V, 45"),
        t("San Francisco de Sales, Introducción a la vida devota, parte III, caps. 8-9", "St Francis de Sales, Introduction to the Devout Life, Part III, chs. 8-9", "São Francisco de Sales, Introdução à vida devota, parte III, caps. 8-9")
    )
)
