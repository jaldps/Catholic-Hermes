package com.example.data.repository

import com.example.data.models.GuideCitation
import com.example.data.models.GuidePhase
import com.example.data.models.GuideScripture
import com.example.data.models.LocalizedText
import com.example.data.models.TemptationGuide

/** Long-form guide for GREED (avarice); part of [TemptationGuidesPartB]. */
internal object TemptationGuidesPartB2 {
    val greed: TemptationGuide = TemptationGuide(
        sinKey = "GREED",
        summary = LocalizedText(
            "La avaricia es el amor desordenado a las riquezas y a la seguridad que prometen: querer tener, acumular o controlar más de lo necesario. Endurece el corazón ante Dios y ante los pobres. Se sana con el desprendimiento y la generosidad, que nacen de confiar en la providencia del Padre.",
            "Greed is the disordered love of riches and of the security they promise: wanting to have, hoard or control more than is needed. It hardens the heart toward God and the poor. It is healed by detachment and generosity, which spring from trusting the Father's providence.",
            "A avareza é o amor desordenado pelas riquezas e pela segurança que prometem: querer ter, acumular ou controlar mais do que é necessário. Endurece o coração diante de Deus e dos pobres. Cura-se com o desapego e a generosidade, que nascem da confiança na providência do Pai."
        ),
        howItWorks = LocalizedText(
            "El Catecismo enseña que el décimo mandamiento prohíbe la avidez y el deseo desordenado de bienes y de poder (CIC 2535-2536), y que Jesús pide un corazón desprendido: «no podéis servir a Dios y al dinero» (Mt 6,24; CIC 2544-2547). No es pecado tener bienes ni trabajar para vivir con dignidad; lo es poner en ellos el corazón (cf. 1 Tm 6,10, que habla del amor al dinero como raíz de todos los males). La parábola del rico insensato (Lc 12,15-21) muestra la trampa: acumular para asegurar la vida, olvidando que la vida es de Dios.\n\n" +
                "Sus ocasiones son tanto la escasez como la abundancia: el miedo al futuro, las ofertas y el consumismo, la comparación con quien tiene más, la herencia, los negocios, el juego, la deuda. Sigue las etapas clásicas: una sugerencia («necesito esto», «por si acaso»), la complacencia (imaginar la posesión, la seguridad o el estatus que daría) y el consentimiento (comprar, ahorrar sin medida, retener lo debido, no dar). Evagrio y Casiano la contaban entre los logismoi, pensamientos que prometen seguridad ante la vejez, la enfermedad o la pobreza, y que se disfrazan de prudencia (Casiano, Instituciones VII).\n\n" +
                "Santo Tomás enumera sus frutos: traición, fraude, falacia, perjurio, inquietud, violencia y dureza de corazón, contra la misericordia (Suma Teológica II-II, q. 118, a. 8). Tiene también una dimensión social: salarios injustos, deudas no pagadas, indiferencia ante quien pasa necesidad, que la Iglesia enseña como obligación de justicia y caridad (CIC 2443-2449). Se disfraza de previsión, de «lo merezco», de «es mío, hago con ello lo que quiero», o de ahorro que nunca es suficiente.",
            "The Catechism teaches that the tenth commandment forbids avarice and the disordered desire for goods and power (CCC 2535-2536), and that Jesus asks for a detached heart: \"you cannot serve God and mammon\" (Matt 6:24; CCC 2544-2547). Owning goods or working to live with dignity is no sin; setting the heart on them is (cf. 1 Tim 6:10, which speaks of the love of money as a root of all evils). The parable of the rich fool (Luke 12:15-21) shows the trap: hoarding to secure life while forgetting that life belongs to God.\n\n" +
                "Its occasions are scarcity and abundance alike: fear of the future, sales and consumerism, comparison with those who have more, inheritances, business, gambling, debt. It follows the classic stages: a suggestion (\"I need this,\" \"just in case\"), delight (imagining the possession, the security or status it would bring) and consent (buying, saving without measure, withholding what is owed, not giving). Evagrius and Cassian counted it among the logismoi, thoughts that promise security against old age, illness or poverty and disguise themselves as prudence (Cassian, Institutes VII).\n\n" +
                "St Thomas lists its fruits: treachery, fraud, falsehood, perjury, restlessness, violence and insensibility to mercy (Summa Theologiae II-II, q. 118, a. 8). It also has a social dimension: unjust wages, unpaid debts, indifference to those in need, which the Church teaches as an obligation of justice and charity (CCC 2443-2449). It disguises itself as foresight, as \"I deserve it,\" as \"it's mine, I do what I want with it,\" or as savings that are never enough.",
            "O Catecismo ensina que o décimo mandamento proíbe a avidez e o desejo desordenado de bens e de poder (CIC 2535-2536) e que Jesus pede um coração desapegado: \"não podeis servir a Deus e ao dinheiro\" (Mt 6,24; CIC 2544-2547). Não é pecado ter bens nem trabalhar para viver com dignidade; é pecado pôr neles o coração (cf. 1Tm 6,10, que fala do amor ao dinheiro como raiz de todos os males). A parábola do rico insensato (Lc 12,15-21) mostra a armadilha: acumular para assegurar a vida, esquecendo que a vida é de Deus.\n\n" +
                "Suas ocasiões são tanto a escassez quanto a abundância: o medo do futuro, as promoções e o consumismo, a comparação com quem tem mais, a herança, os negócios, o jogo, a dívida. Segue as etapas clássicas: a sugestão (\"preciso disto\", \"por precaução\"), a complacência (imaginar a posse, a segurança ou o status que daria) e o consentimento (comprar, poupar sem medida, reter o que é devido, não dar). Evágrio e Cassiano a contavam entre os logismoi, pensamentos que prometem segurança diante da velhice, da doença ou da pobreza e se disfarçam de prudência (Cassiano, Instituições VII).\n\n" +
                "Santo Tomás enumera seus frutos: traição, fraude, falácia, perjúrio, inquietação, violência e dureza de coração, contra a misericórdia (Suma Teológica II-II, q. 118, a. 8). Tem também uma dimensão social: salários injustos, dívidas não pagas, indiferença diante de quem passa necessidade, o que a Igreja ensina como obrigação de justiça e caridade (CIC 2443-2449). Disfarça-se de previdência, de \"eu mereço\", de \"é meu, faço com isso o que quiser\" ou de poupança que nunca é suficiente."
        ),
        whyItIsHard = LocalizedText(
            "Es difícil porque casi nadie se siente avaro: siempre hay alguien que tiene más, y siempre un motivo aparentemente sensato para guardar o comprar. Detrás suele haber miedo e inseguridad (a la pobreza, a la enfermedad, a no ser nadie), y el dinero parece ofrecer control. La concupiscencia nos inclina a buscar seguridad fuera de Dios (CIC 405, 2515), y una cultura de consumo, publicidad y crédito fácil hace de cada deseo una «necesidad».\n\n" +
                "Por eso no basta con propósitos de austeridad: hay que trabajar la confianza. Cada acto concreto de dar, de esperar o de renunciar, aunque sea pequeño, educa el corazón a vivir de lo recibido y no de lo acumulado. Y conviene distinguir la verdadera prudencia (administrar bien, ahorrar para la familia, pagar lo debido) de la avidez, que nunca dice «basta».",
            "It is hard because almost nobody feels greedy: there is always someone who has more and always a plausible reason to keep or to buy. Behind it there is often fear and insecurity (of poverty, illness, being nobody), and money seems to offer control. Concupiscence inclines us to seek security apart from God (CCC 405, 2515), and a culture of consumption, advertising and easy credit turns every wish into a \"need.\"\n\n" +
                "So resolutions of austerity are not enough: trust must be trained. Each concrete act of giving, waiting or renouncing, however small, educates the heart to live on what is received rather than on what is stored. And it helps to distinguish true prudence (managing well, saving for the family, paying what is owed) from avarice, which never says \"enough.\"",
            "É difícil porque quase ninguém se sente avarento: sempre há alguém que tem mais e sempre um motivo aparentemente sensato para guardar ou comprar. Por trás costuma haver medo e insegurança (da pobreza, da doença, de não ser ninguém), e o dinheiro parece oferecer controle. A concupiscência nos inclina a buscar segurança fora de Deus (CIC 405, 2515), e uma cultura de consumo, publicidade e crédito fácil faz de cada desejo uma \"necessidade\".\n\n" +
                "Por isso não bastam propósitos de austeridade: é preciso trabalhar a confiança. Cada ato concreto de dar, esperar ou renunciar, por menor que seja, educa o coração a viver do que recebeu e não do que acumulou. E convém distinguir a verdadeira prudência (administrar bem, poupar para a família, pagar o que é devido) da avidez, que nunca diz \"basta\"."
        ),
        warningSigns = listOf(
            LocalizedText(
                "Piensas en dinero o en lo que quieres comprar con más frecuencia de lo que piensas en las personas.",
                "You think about money or what you want to buy more often than you think about people.",
                "Você pensa em dinheiro ou no que quer comprar com mais frequência do que pensa nas pessoas."
            ),
            LocalizedText(
                "Compras por impulso, por ansiedad o para sentirte mejor, y luego te arrepientes o lo ocultas.",
                "You buy on impulse, out of anxiety or to feel better, and then regret it or hide it.",
                "Você compra por impulso, por ansiedade ou para se sentir melhor, e depois se arrepende ou esconde."
            ),
            LocalizedText(
                "Te cuesta dar limosna, regalar, compartir o pagar a tiempo lo que debes, aunque puedas.",
                "You find it hard to give alms, make gifts, share or pay on time what you owe, even though you could.",
                "Você tem dificuldade em dar esmola, presentear, partilhar ou pagar em dia o que deve, mesmo podendo."
            ),
            LocalizedText(
                "Sientes una inquietud constante por el futuro económico, o nunca te parece suficiente lo que tienes.",
                "You feel constant unease about your financial future, or what you have never seems enough.",
                "Você sente uma inquietação constante com o futuro financeiro, ou nunca lhe parece suficiente o que tem."
            ),
            LocalizedText(
                "Tus decisiones (trabajo, tiempo, familia, domingo) se ordenan a ganar más aunque perjudiquen a otros.",
                "Your decisions (work, time, family, Sunday) are ordered to earning more even when others suffer.",
                "Suas decisões (trabalho, tempo, família, domingo) se ordenam a ganhar mais ainda que prejudiquem outros."
            ),
            LocalizedText(
                "Evitas mirar a los pobres o justificas tu falta de ayuda con críticas a quien pide.",
                "You avoid looking at the poor or justify not helping by criticising the one who asks.",
                "Você evita olhar para os pobres ou justifica sua falta de ajuda criticando quem pede."
            )
        ),
        inTheMoment = listOf(
            LocalizedText(
                "Detente y nómbralo: «esto es deseo desordenado de tener». Pide la gracia de la confianza.",
                "Stop and name it: \"this is a disordered wish to have.\" Ask for the grace of trust.",
                "Pare e nomeie: \"isto é desejo desordenado de ter\". Peça a graça da confiança."
            ),
            LocalizedText(
                "Aplaza cualquier compra no necesaria 48 horas; si después aún la necesitas, decide con calma.",
                "Postpone any unneeded purchase for 48 hours; if you still need it afterwards, decide calmly.",
                "Adie qualquer compra desnecessária por 48 horas; se depois ainda precisar, decida com calma."
            ),
            LocalizedText(
                "Da algo inmediatamente, aunque sea pequeño: una limosna, un objeto, una propina generosa.",
                "Give something right away, however small: an alms, an object, a generous tip.",
                "Dê algo imediatamente, ainda que pequeno: uma esmola, um objeto, uma gorjeta generosa."
            ),
            LocalizedText(
                "Pregúntate: ¿lo necesito, lo quiero, o busco calmar un miedo? ¿Cabe en mi presupuesto y en mi deber de ayudar?",
                "Ask yourself: do I need it, want it, or am I trying to soothe a fear? Does it fit my budget and my duty to help?",
                "Pergunte-se: preciso, quero ou busco acalmar um medo? Cabe no meu orçamento e no meu dever de ajudar?"
            ),
            LocalizedText(
                "Cierra la tienda o la aplicación, borra el carrito, sal del lugar.",
                "Close the shop or app, clear the cart, leave the place.",
                "Feche a loja ou o aplicativo, limpe o carrinho, saia do lugar."
            ),
            LocalizedText(
                "Reza: «Señor, tú eres mi herencia» (cf. Sal 16,5) y repite lentamente «Padre, el pan de cada día, dánoslo hoy».",
                "Pray: \"Lord, you are my portion\" (cf. Ps 16:5) and repeat slowly \"Father, give us today our daily bread.\"",
                "Reze: \"Senhor, tu és minha herança\" (cf. Sl 16,5) e repita lentamente \"Pai, o pão nosso de cada dia dai-nos hoje\"."
            )
        ),
        dailyRoutine = listOf(
            LocalizedText(
                "Mañana: ofrecimiento del día y petición de un corazón libre; una vez al día reza el Padre Nuestro pensando en «el pan de cada día».",
                "Morning: offer the day and ask for a free heart; once a day pray the Our Father thinking of \"our daily bread.\"",
                "Manhã: oferecimento do dia e pedido de um coração livre; uma vez por dia reze o Pai-Nosso pensando no \"pão de cada dia\"."
            ),
            LocalizedText(
                "Custodia del corazón: define qué compras o gastos están previstos hoy y qué evitarás (redes comerciales, ofertas, tiendas en línea).",
                "Guarding the heart: define which purchases or expenses are planned today and what you will avoid (commercial feeds, sales, online shops).",
                "Guarda do coração: defina que compras ou gastos estão previstos hoje e o que evitará (redes comerciais, promoções, lojas online)."
            ),
            LocalizedText(
                "Mediodía: pausa breve. ¿He gastado o he retenido por miedo? ¿A quién puedo dar algo esta tarde?",
                "Midday: a short pause. Have I spent or withheld out of fear? To whom can I give something this afternoon?",
                "Meio-dia: pausa breve. Gastei ou retive por medo? A quem posso dar algo esta tarde?"
            ),
            LocalizedText(
                "Examen de la noche (San Ignacio): gracias por lo recibido; repaso de gastos, retenciones y ocasiones de generosidad; perdón; propósito.",
                "Evening examen (St Ignatius): thanks for what was received; review of spending, withholding and chances to be generous; pardon; resolve.",
                "Exame da noite (Santo Inácio): ação de graças pelo recebido; revisão de gastos, retenções e ocasiões de generosidade; perdão; propósito."
            ),
            LocalizedText(
                "Limosna regular y planificada: separa una cantidad fija (por ejemplo un porcentaje de tus ingresos que decidas con tu director) para los pobres y la Iglesia, el diezmo como referencia; no es una cuota obligatoria: el precepto es ayudar a las necesidades de la Iglesia según las propias posibilidades (CIC 2043).",
                "Regular, planned almsgiving: set aside a fixed amount (for example a percentage of your income decided with your director) for the poor and the Church, with the tithe as a reference; it is not a mandatory quota: the precept is to help provide for the Church's needs according to one's means (CCC 2043).",
                "Esmola regular e planejada: separe uma quantia fixa (por exemplo, uma porcentagem da renda decidida com seu diretor) para os pobres e a Igreja, tendo o dízimo como referência; não é uma cota obrigatória: o preceito é ajudar nas necessidades da Igreja segundo as próprias possibilidades (CIC 2043)."
            ),
            LocalizedText(
                "Semanal: orden de presupuesto (ingresos, gastos, deudas, donativo) y confesión o dirección con ritmo estable.",
                "Weekly: tidy the budget (income, expenses, debts, giving) and keep a steady rhythm of confession or direction.",
                "Semanal: organização do orçamento (receitas, gastos, dívidas, doação) e confissão ou direção com ritmo estável."
            ),
            LocalizedText(
                "Una obra de misericordia material cada semana (visita, comida, ropa, ayuda concreta) y un objeto regalado o donado al mes.",
                "One corporal work of mercy each week (visit, food, clothing, concrete help) and one object given away each month.",
                "Uma obra de misericórdia corporal por semana (visita, comida, roupa, ajuda concreta) e um objeto presenteado ou doado por mês."
            )
        ),
        plan = listOf(
            GuidePhase(
                title = LocalizedText("Mirar con verdad", "Looking honestly", "Olhar com verdade"),
                days = "1–7",
                focus = LocalizedText(
                    "Ver cómo se relaciona tu corazón con el dinero y las cosas, sin juzgarte.",
                    "See how your heart relates to money and things, without judging yourself.",
                    "Ver como seu coração se relaciona com o dinheiro e as coisas, sem se julgar."
                ),
                practices = listOf(
                    LocalizedText(
                        "Anota cada gasto y cada deseo de compra durante la semana; subraya los que nacieron de la ansiedad o la comparación.",
                        "Note every expense and every wish to buy this week; underline those that sprang from anxiety or comparison.",
                        "Anote cada gasto e cada desejo de compra durante a semana; sublinhe os que nasceram da ansiedade ou da comparação."
                    ),
                    LocalizedText(
                        "Haz una lista de lo que de verdad necesitas, y de lo que tienes de sobra.",
                        "List what you truly need and what you have in excess.",
                        "Faça uma lista do que realmente precisa e do que tem de sobra."
                    ),
                    LocalizedText(
                        "Medita Lc 12,15-21: ¿en qué asegura mi vida? ¿qué miedo hay detrás?",
                        "Meditate on Luke 12:15-21: where do I secure my life? What fear lies behind it?",
                        "Medite Lc 12,15-21: em que asseguro minha vida? Que medo há por trás?"
                    )
                )
            ),
            GuidePhase(
                title = LocalizedText("Poner orden y quitar ocasiones", "Order and removing occasions", "Pôr ordem e retirar as ocasiões"),
                days = "8–14",
                focus = LocalizedText(
                    "Reducir lo que alimenta el consumo y establecer un orden sencillo.",
                    "Reduce what feeds consumption and set up a simple order.",
                    "Reduzir o que alimenta o consumo e estabelecer uma ordem simples."
                ),
                practices = listOf(
                    LocalizedText(
                        "Da de baja suscripciones y notificaciones comerciales, borra tarjetas guardadas en tiendas en línea.",
                        "Cancel commercial subscriptions and notifications; delete cards saved in online shops.",
                        "Cancele assinaturas e notificações comerciais, apague cartões salvos em lojas online."
                    ),
                    LocalizedText(
                        "Establece un presupuesto sencillo y la regla de las 48 horas para toda compra no necesaria.",
                        "Set a simple budget and the 48-hour rule for every non-essential purchase.",
                        "Estabeleça um orçamento simples e a regra das 48 horas para toda compra não necessária."
                    ),
                    LocalizedText(
                        "Cuéntale a alguien de confianza (cónyuge, amigo, director) tu propósito y pídele que te pregunte.",
                        "Tell someone trustworthy (spouse, friend, director) your resolution and ask them to check in.",
                        "Conte a alguém de confiança (cônjuge, amigo, diretor) seu propósito e peça que lhe pergunte."
                    ),
                    LocalizedText(
                        "Confiésate de lo concreto (ansiedad por tener, retención de lo debido, dureza ante el pobre).",
                        "Confess the concrete (anxiety about having, withholding what is owed, hardness toward the poor).",
                        "Confesse o concreto (ansiedade por ter, retenção do devido, dureza diante do pobre)."
                    )
                )
            ),
            GuidePhase(
                title = LocalizedText("Aprender a dar", "Learning to give", "Aprender a dar"),
                days = "15–21",
                focus = LocalizedText(
                    "Practicar la virtud contraria con actos pequeños y regulares.",
                    "Practise the opposite virtue with small, regular acts.",
                    "Praticar a virtude contrária com atos pequenos e regulares."
                ),
                practices = listOf(
                    LocalizedText(
                        "Da cada día algo concreto: dinero, tiempo, un objeto. Empieza pequeño y constante.",
                        "Give something concrete each day: money, time, an object. Start small and steady.",
                        "Dê algo concreto todos os dias: dinheiro, tempo, um objeto. Comece pequeno e constante."
                    ),
                    LocalizedText(
                        "Elige un objeto que te guste mucho y regálalo a quien lo necesite.",
                        "Choose an object you like a lot and give it to someone who needs it.",
                        "Escolha um objeto de que gosta muito e presenteie quem precise."
                    ),
                    LocalizedText(
                        "Haz una mortificación pequeña de comodidad o gasto (un café, una compra) y entrega lo ahorrado a los pobres.",
                        "Make a small mortification of comfort or spending (a coffee, a purchase) and give the saving to the poor.",
                        "Faça uma pequena mortificação de conforto ou gasto (um café, uma compra) e entregue o poupado aos pobres."
                    )
                )
            ),
            GuidePhase(
                title = LocalizedText("Justicia y confianza", "Justice and trust", "Justiça e confiança"),
                days = "22–30",
                focus = LocalizedText(
                    "Ordenar lo debido en justicia y crecer en confianza en la Providencia.",
                    "Set right what is owed in justice and grow in trust in Providence.",
                    "Ordenar o que é devido em justiça e crescer na confiança na Providência."
                ),
                practices = listOf(
                    LocalizedText(
                        "Revisa si debes algo (salarios, deudas, restituciones) y planifica con tu confesor cómo repararlo.",
                        "Check whether you owe anything (wages, debts, restitution) and plan with your confessor how to repair it.",
                        "Verifique se deve algo (salários, dívidas, restituições) e planeje com seu confessor como reparar."
                    ),
                    LocalizedText(
                        "Fija tu donativo regular a los pobres y a la Iglesia como porcentaje, y cúmplelo antes de gastar en lo demás.",
                        "Set your regular gift to the poor and the Church as a percentage and give it before spending on the rest.",
                        "Fixe sua doação regular aos pobres e à Igreja como porcentagem e cumpra-a antes de gastar no resto."
                    ),
                    LocalizedText(
                        "Dedica un tiempo a servir en persona a los pobres (comedor, visita a enfermos, parroquia).",
                        "Spend time serving the poor in person (soup kitchen, visiting the sick, parish).",
                        "Dedique um tempo a servir pessoalmente os pobres (refeitório, visita a doentes, paróquia)."
                    ),
                    LocalizedText(
                        "Medita Mt 6,19-34 cada mañana un versículo y rézalo: «el Padre sabe lo que necesitáis».",
                        "Meditate on one verse of Matt 6:19-34 each morning and pray it: \"your Father knows what you need.\"",
                        "Medite um versículo de Mt 6,19-34 a cada manhã e reze-o: \"vosso Pai sabe do que necessitais\"."
                    )
                )
            ),
            GuidePhase(
                title = LocalizedText("Consolidar una vida sencilla", "Consolidating a simple life", "Consolidar uma vida simples"),
                days = "31–40",
                focus = LocalizedText(
                    "Convertir lo aprendido en una regla de vida estable y prever las recaídas.",
                    "Turn what has been learnt into a steady rule of life and anticipate relapses.",
                    "Transformar o aprendido numa regra de vida estável e prever as recaídas."
                ),
                practices = listOf(
                    LocalizedText(
                        "Si recaes (una compra impulsiva, una retención), no te desesperes: reconócelo, repara si hace falta y retoma la rutina al día siguiente.",
                        "If you relapse (an impulsive purchase, a withholding), do not despair: acknowledge it, repair if needed and resume the routine next day.",
                        "Se recair (uma compra impulsiva, uma retenção), não se desespere: reconheça, repare se necessário e retome a rotina no dia seguinte."
                    ),
                    LocalizedText(
                        "Escribe una regla de vida: presupuesto, donativo fijo, regla de 48 horas, un día sin compras por semana.",
                        "Write a rule of life: budget, fixed gift, the 48-hour rule, one no-spend day a week.",
                        "Escreva uma regra de vida: orçamento, doação fixa, regra das 48 horas, um dia sem compras por semana."
                    ),
                    LocalizedText(
                        "Revisa con tu director o confesor el camino y las ocasiones que siguen siendo frágiles.",
                        "Review the journey with your director or confessor, and the occasions that remain fragile.",
                        "Reveja o caminho com seu diretor ou confessor e as ocasiões que continuam frágeis."
                    ),
                    LocalizedText(
                        "Da gracias a Dios en la Eucaristía por lo recibido y por la libertad ganada; mantén el servicio a los pobres.",
                        "Give thanks to God at Mass for what you have received and the freedom gained; keep serving the poor.",
                        "Agradeça a Deus na Eucaristia pelo recebido e pela liberdade conquistada; mantenha o serviço aos pobres."
                    )
                )
            )
        ),
        scripture = listOf(
            GuideScripture(
                LocalizedText("Lc 12,15-21", "Luke 12:15-21", "Lc 12,15-21"),
                LocalizedText(
                    "El rico insensato acumula para sí y no es rico ante Dios. Jesús advierte: la vida de uno no depende de sus bienes.",
                    "The rich fool stores up for himself and is not rich toward God. Jesus warns that one's life does not depend on one's possessions.",
                    "O rico insensato acumula para si e não é rico diante de Deus. Jesus adverte: a vida de alguém não depende de seus bens."
                )
            ),
            GuideScripture(
                LocalizedText("1 Tm 6,6-10", "1 Tim 6:6-10", "1Tm 6,6-10"),
                LocalizedText(
                    "La piedad con contentamiento es gran ganancia; el amor al dinero es raíz de todos los males y extravía de la fe.",
                    "Godliness with contentment is great gain; the love of money is a root of all evils and leads astray from the faith.",
                    "A piedade com contentamento é grande ganho; o amor ao dinheiro é raiz de todos os males e desvia da fé."
                )
            ),
            GuideScripture(
                LocalizedText("Mt 6,19-24", "Matt 6:19-24", "Mt 6,19-24"),
                LocalizedText(
                    "Jesús invita a acumular tesoros en el cielo: donde está tu tesoro, allí está tu corazón. No se puede servir a Dios y al dinero.",
                    "Jesus invites us to store treasures in heaven: where your treasure is, there is your heart. You cannot serve God and mammon.",
                    "Jesus convida a acumular tesouros no céu: onde está o teu tesouro, aí está o teu coração. Não se pode servir a Deus e ao dinheiro."
                )
            ),
            GuideScripture(
                LocalizedText("Hch 4,32-35", "Acts 4:32-35", "At 4,32-35"),
                LocalizedText(
                    "La primera comunidad tenía todo en común y nadie pasaba necesidad: la generosidad como estilo de Iglesia.",
                    "The first community held everything in common and none was in need: generosity as the Church's way of life.",
                    "A primeira comunidade tinha tudo em comum e ninguém passava necessidade: a generosidade como estilo de Igreja."
                )
            ),
            GuideScripture(
                LocalizedText("Mc 10,17-27", "Mark 10:17-27", "Mc 10,17-27"),
                LocalizedText(
                    "El joven rico se marcha triste porque tenía muchos bienes: Jesús lo miró con amor y lo invitó a una libertad mayor.",
                    "The rich young man goes away sad because he had many possessions: Jesus looked on him with love and invited him to a greater freedom.",
                    "O jovem rico vai embora triste porque tinha muitos bens: Jesus o olhou com amor e o convidou a uma liberdade maior."
                )
            )
        ),
        saintsWisdom = listOf(
            GuideCitation(
                LocalizedText("San Basilio Magno", "St Basil the Great", "São Basílio Magno"),
                LocalizedText(
                    "Comentando la parábola del rico insensato, enseña que el pan que se guarda pertenece al hambriento y el manto en el arca, al desnudo: retener lo superfluo es una forma de injusticia.",
                    "Commenting on the parable of the rich fool, he teaches that the bread kept belongs to the hungry and the cloak in the chest to the naked: withholding the superfluous is a form of injustice.",
                    "Comentando a parábola do rico insensato, ensina que o pão guardado pertence ao faminto e o manto na arca ao nu: reter o supérfluo é uma forma de injustiça."
                ),
                LocalizedText("Homilía 6 (Destruam horrea mea); PG 31, 261-277", "Homily 6 (Destruam horrea mea); PG 31, 261–277", "Homilia 6 (Destruam horrea mea); PG 31, 261-277")
            ),
            GuideCitation(
                LocalizedText("Santo Tomás de Aquino", "St Thomas Aquinas", "Santo Tomás de Aquino"),
                LocalizedText(
                    "La avaricia es amor desordenado por poseer riquezas; es pecado contra la justicia y la caridad cuando se aferra y no ayuda al prójimo. Sus hijas son la traición, el fraude, la falacia, el perjurio, la inquietud, la violencia y la dureza de corazón (contra la misericordia) (a. 8).",
                    "Avarice is the disordered love of possessing riches; it sins against justice and charity when it clings and does not help the neighbour. Its daughters are treachery, fraud, falsehood, perjury, restlessness, violence and insensibility to mercy (a. 8).",
                    "A avareza é o amor desordenado de possuir riquezas; peca contra a justiça e a caridade quando se agarra e não ajuda o próximo. Suas filhas são a traição, a fraude, a falácia, o perjúrio, a inquietação, a violência e a dureza de coração (contra a misericórdia) (a. 8)."
                ),
                LocalizedText("Suma Teológica II-II, q. 118", "Summa Theologiae II-II, q. 118", "Suma Teológica II-II, q. 118")
            ),
            GuideCitation(
                LocalizedText("San Juan Casiano", "St John Cassian", "São João Cassiano"),
                LocalizedText(
                    "Describe la avaricia como un vicio que nunca se sacia y que se vence con una vida sencilla y confiada, renunciando a poseer más de lo necesario.",
                    "He describes avarice as a vice never satisfied, overcome by a simple, trusting life that renounces owning more than is needed.",
                    "Descreve a avareza como um vício que nunca se sacia, vencido por uma vida simples e confiante, renunciando a possuir mais do que o necessário."
                ),
                LocalizedText("Instituciones VII", "Institutes VII", "Instituições VII")
            ),
            GuideCitation(
                LocalizedText("San Francisco de Sales", "St Francis de Sales", "São Francisco de Sales"),
                LocalizedText(
                    "Enseña a vivir la pobreza de espíritu incluso entre riquezas: cuidar con orden los bienes, sin apegar el corazón, y ser generoso con los pobres.",
                    "He teaches how to live poverty of spirit even amid riches: care for goods in an orderly way without attaching the heart, and be generous with the poor.",
                    "Ensina a viver a pobreza de espírito mesmo entre riquezas: cuidar dos bens com ordem, sem apegar o coração, e ser generoso com os pobres."
                ),
                LocalizedText("Introducción a la vida devota, parte III, cap. 14-15", "Introduction to the Devout Life, Part III, ch. 14-15", "Introdução à vida devota, parte III, cap. 14-15")
            )
        ),
        sacramentalHelps = LocalizedText(
            "La confesión libera de la avidez y de la dureza ante el pobre, y ayuda a ordenar lo que se debe en justicia (restitución, deudas). La limosna es penitencia que repara el apego a los bienes (CIC 1434, 2447). La Eucaristía, don total de Cristo partido y repartido, enseña a recibirlo todo como don y libera de la posesividad: quien comulga aprende a darse. Ayuda la práctica de una ayuda fija y regular a los pobres, decidida de antemano y con consejo, que impide que el miedo o la costumbre la posterguen; también las obras de misericordia, el ayuno, el Padre Nuestro («el pan de cada día»), la oración de confianza en la Providencia y la dirección espiritual para ordenar un presupuesto.",
            "Confession frees us from avidity and from hardness toward the poor, and helps to set right what is owed in justice (restitution, debts). Almsgiving is a penance that repairs attachment to goods (CCC 1434, 2447). The Eucharist, Christ's total gift broken and shared, teaches us to receive everything as gift and frees us from possessiveness: whoever receives Communion learns to give oneself. The practice of regular, fixed giving to the poor, decided beforehand and with counsel, keeps fear or habit from putting it off; works of mercy, fasting, the Our Father (\"our daily bread\"), prayer of trust in Providence and spiritual direction to arrange a budget also help.",
            "A confissão liberta da avidez e da dureza diante do pobre e ajuda a ordenar o que se deve em justiça (restituição, dívidas). A esmola é penitência que repara o apego aos bens (CIC 1434, 2447). A Eucaristia, dom total de Cristo partido e repartido, ensina a receber tudo como dom e liberta da possessividade: quem comunga aprende a se doar. Ajuda a prática de uma contribuição fixa e regular aos pobres, decidida de antemão e com aconselhamento, que impede que o medo ou o hábito a adiem; também as obras de misericórdia, o jejum, o Pai-Nosso (\"o pão de cada dia\"), a oração de confiança na Providência e a direção espiritual para organizar um orçamento."
        ),
        whenToSeekHelp = LocalizedText(
            "Habla con tu confesor o director espiritual si la avaricia te quita la paz, daña a tu familia o te lleva a retener lo debido. Si notas compras compulsivas, juego, deudas que crecen sin control, o una angustia económica desproporcionada, consulta también a un profesional cualificado (de salud mental, o un asesor financiero o servicio social); pedir ayuda es prudencia y no signo de falta de fe. Evita la culpa paralizante: no se trata de despreciar los bienes ni de vivir en miseria, sino de ordenar el corazón con calma y de modo gradual.",
            "Speak to your confessor or spiritual director if greed takes away your peace, harms your family or leads you to withhold what is owed. If you notice compulsive buying, gambling, debts growing out of control, or disproportionate financial anguish, also consult a qualified professional (mental health, or a financial adviser or social service); asking for help is prudence, not a lack of faith. Avoid paralysing guilt: the goal is not to despise goods or live in misery but to order the heart calmly and gradually.",
            "Fale com seu confessor ou diretor espiritual se a avareza lhe tira a paz, prejudica sua família ou o leva a reter o que é devido. Se notar compras compulsivas, jogo, dívidas que crescem sem controle ou uma angústia financeira desproporcional, consulte também um profissional qualificado (de saúde mental, ou um consultor financeiro ou serviço social); pedir ajuda é prudência e não falta de fé. Evite a culpa paralisante: não se trata de desprezar os bens nem de viver na miséria, mas de ordenar o coração com calma e de modo gradual."
        ),
        sources = listOf(
            LocalizedText("Catecismo de la Iglesia Católica (CIC) 2535-2536 (avidez), 2544-2547 (pobreza de corazón), 2443-2449 (amor a los pobres), 405 y 2515 (concupiscencia).", "Catechism of the Catholic Church (CCC) 2535-2536 (avarice), 2544-2547 (poverty of heart), 2443-2449 (love for the poor), 405 and 2515 (concupiscence).", "Catecismo da Igreja Católica (CIC) 2535-2536 (avidez), 2544-2547 (pobreza de coração), 2443-2449 (amor aos pobres), 405 e 2515 (concupiscência)."),
            LocalizedText("Santo Tomás de Aquino, Suma Teológica II-II, q. 118 (la avaricia).", "St Thomas Aquinas, Summa Theologiae II-II, q. 118 (avarice).", "Santo Tomás de Aquino, Suma Teológica II-II, q. 118 (a avareza)."),
            LocalizedText("San Basilio Magno, Homilía 6 (Destruam horrea mea); PG 31, 261-277.", "St Basil the Great, Homily 6 (Destruam horrea mea); PG 31, 261–277.", "São Basílio Magno, Homilia 6 (Destruam horrea mea); PG 31, 261-277."),
            LocalizedText("San Juan Casiano, Instituciones VII (contra la avaricia).", "St John Cassian, Institutes VII (against avarice).", "São João Cassiano, Instituições VII (contra a avareza)."),
            LocalizedText("San Francisco de Sales, Introducción a la vida devota, parte III, cap. 14-15.", "St Francis de Sales, Introduction to the Devout Life, Part III, ch. 14-15.", "São Francisco de Sales, Introdução à vida devota, parte III, cap. 14-15.")
        )
    )
}
