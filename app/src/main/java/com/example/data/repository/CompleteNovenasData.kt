package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

/**
 * Complete and authentic Catholic Novenas with 9 distinct, rich daily prayers,
 * meditations, and intentions approved by the Catholic Church.
 */
object CompleteNovenasData {

    val sacredHeartNovena = Novena(
        id = "sacred_heart",
        title = LocalizedText("Novena Oficial al Sagrado Corazón de Jesús", "Official Novena to the Sacred Heart of Jesus", "Novena Oficial ao Sagrado Coração de Jesus"),
        patron = LocalizedText("El Amor Divino y la Reparación", "Divine Love & Reparation", "O Amor Divino e a Reparação"),
        category = LocalizedText("Amor Divino y Gracia", "Divine Love & Grace", "Amor Divino e Graça"),
        description = LocalizedText(
            "Novena completa de 9 días basada en las revelaciones de Nuestro Señor Jesucristo a Santa Margarita María de Alacoque en Paray-le-Monial y aprobada por la Santa Sede.",
            "Complete 9-day Novena revealed by Jesus Christ to Saint Margaret Mary Alacoque in Paray-le-Monial and authorized by the Vatican.",
            "Novena completa de 9 dias revelada por Jesus a Santa Margarida Maria Alacoque com todas as orações autênticas."
        ),
        miraculousHistory = LocalizedText(
            "Jesús mostró su Corazón ardiente de amor coronado de espinas diciendo: 'He aquí el Corazón que tanto ha amado a los hombres...'",
            "Christ displayed His Sacred Heart aflame with unquenchable charity, promising infinite graces to those who venerate it.",
            "Jesus mostrou Seu Coração inflamado de amor, prometendo copiosas bênçãos a quem O honrar."
        ),
        promises = LocalizedText(
            "Paz en las familias, consuelo en las aflicciones, bendición en las empresas y la gracia de la salvación eterna.",
            "Peace in families, solace in tribulations, blessings on temporal tasks, and the supreme grace of final perseverance.",
            "Paz nos lares, consolo nas aflições e a salvação eterna."
        ),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("Día 1: El Amor Infinito del Corazón de Jesús", "Day 1: The Infinite Love of Christ's Heart", "Dia 1: O Amor Infinito do Coração de Jesus"),
                scripture = "1 Juan 4, 9-10",
                meditation = LocalizedText(
                    "Considera cómo el Corazón de Jesús nos amó primero, entregándose en la Cruz para rescatarnos del pecado y de la muerte eterna.",
                    "Contemplate how the Sacred Heart loved us first, submitting to the Cross to redeem us from sin and despair.",
                    "Medite sobre o amor imenso de Jesus, que Se entregou inteiramente na Cruz por nossa redenção."
                ),
                prayer = LocalizedText(
                    "¡Oh Corazón amabilísimo de Jesús, horno ardiente de divina caridad!: enciende mi alma en el fuego de tu amor. Haz que reconozca tu infinita bondad y concédeme la gracia que hoy te pido en esta novena... Sagrado Corazón de Jesús, en Ti confío.",
                    "O most lovable Heart of Jesus, burning furnace of charity: inflame my cold soul with the fire of Your divine love. Hear my earnest petition... Sacred Heart of Jesus, I place my trust in You.",
                    "Ó Coração amabilíssimo de Jesus, fornalha ardente de amor: inflamai o meu coração no Vosso santo amor. Sagrado Coração de Jesus, eu confio em Vós!"
                ),
                intentionGuidance = LocalizedText("Pon en el Corazón de Jesús tu petición principal y haz un acto de gratitud por su sacrificio en la Cruz.", "Place your primary intention into Christ's Heart and offer thanksgiving for His Passion.", "Apresente sua intenção e agradeça a Jesus pelo dom da vida.")
            ),
            NovenaDay(
                dayNumber = 2,
                theme = LocalizedText("Día 2: Jesús Manso y Humilde de Corazón", "Day 2: Meek & Humble of Heart", "Dia 2: Jesus Manso e Humilde de Coração"),
                scripture = "Mateo 11, 28-30",
                meditation = LocalizedText(
                    "«Aprendan de mí, que soy manso y humilde de corazón, y hallarán descanso para sus almas». La humildad de Cristo vence la soberbia del mundo.",
                    "“Learn from me, for I am meek and humble of heart; and you will find rest for yourselves.” Humility disarms the worldly spirit.",
                    "A mansidão de Jesus nos ensina a silenciar o orgulho e a acolher o descanso na Divina Providência."
                ),
                prayer = LocalizedText(
                    "¡Oh Jesús manso y humilde de corazón!: arranca de mi pecho todo resentimiento, ira y orgullo. Dame un corazón semejante al tuyo para amar a mis hermanos con paciencia evangélica. Amén.",
                    "O Jesus, meek and humble of heart: eradicate pride and anger from my soul. Grant me a heart molded to Yours in patient charity. Amen.",
                    "Jesus, manso e humilde de coração, fazei o meu coração semelhante ao Vosso e dai-me a virtude da santa paciência. Amém."
                ),
                intentionGuidance = LocalizedText("Pide la gracia de perdonar a quien te haya ofendido recientemente.", "Forgive sincerely anyone who has wounded you recently.", "Perdoe de coração a quem tenha ofendido você.")
            ),
            NovenaDay(
                dayNumber = 3,
                theme = LocalizedText("Día 3: El Traspasado por la Lanza: Fuente de Sacramentos", "Day 3: Pierced by the Lance", "Dia 3: Transpassado pela Lança"),
                scripture = "Juan 19, 34",
                meditation = LocalizedText(
                    "Uno de los soldados le abrió el costado con una lanza, y al instante salió sangre y agua: el Bautismo y la Eucaristía, vida de la Iglesia.",
                    "One soldier thrust his lance into His side, and immediately blood and water flowed forth: the sacraments of life.",
                    "Do Coração aberto de Jesus nascem os sacramentos da salvação da Santa Igreja."
                ),
                prayer = LocalizedText(
                    "¡Oh Fuente inagotable de vida!: purifícame con el agua viva de tu costado y aliméntame con la Sangre preciosísima de la Santa Eucaristía. Amén.",
                    "O Fount of everlasting life: cleanse me with the living water from Your pierced side and nourish me with Your precious Blood. Amen.",
                    "Ó Fonte inesgotável de graça: purificai-me e alimentai a minha alma com a Vossa Santa Eucaristia. Amém."
                ),
                intentionGuidance = LocalizedText("Ofrece un acto de adoración al Santísimo Sacramento en el sagrario.", "Offer an act of adoration before the Blessed Sacrament.", "Faça uma comunhão espiritual reparadora.")
            ),
            NovenaDay(
                dayNumber = 4,
                theme = LocalizedText("Día 4: Refugio en las Tribulaciones", "Day 4: Refuge in Tribulation", "Dia 4: Refúgio nas Tribulações"),
                scripture = "Salmo 46, 2-3",
                meditation = LocalizedText(
                    "En medio de las tormentas de la vida, el Corazón de Jesús es la roca inexpugnable donde el alma encuentra serenidad y protección sobrenatural.",
                    "Amidst tempests and tribulations, Christ's Sacred Heart remains the unshakeable refuge of peace.",
                    "Nas tempestades do mundo, o Coração de Cristo é o abrigo seguro onde encontramos consolação."
                ),
                prayer = LocalizedText(
                    "¡Señor mío y Dios mío!: cuando me sienta desfallecer bajo el peso del sufrimiento, atráeme a tu dulce Corazón y sé mi fortaleza. Amén.",
                    "My Lord and my God: when my spirit wavers under heavy burdens, draw me near to Your Heart and be my divine strength. Amen.",
                    "Senhor Jesus: sede meu refúgio e dai-me a fortaleza para carregar minha cruz com amor. Amém."
                ),
                intentionGuidance = LocalizedText("Entrega a Jesús tus angustias financieras, de salud o familiares con total abandono.", "Surrender your anxieties regarding work, health, or family to Jesus.", "Consagre suas dores a Jesus na certeza de Seu auxílio.")
            ),
            NovenaDay(
                dayNumber = 5,
                theme = LocalizedText("Día 5: Reparación por las Ingratitudes Humanas", "Day 5: Reparation for Ingratitude", "Dia 5: Reparação pelas Ingratidões"),
                scripture = "Salmo 69, 21",
                meditation = LocalizedText(
                    "«Esperé compasión y no la hubo; consoladores, y a ninguno hallé». Consuela hoy al Corazón de Cristo que sufre el desprecio y la indiferencia.",
                    "“I looked for sympathy, but there was none; for comforters, but none did I find.” Console the wounded Heart of Jesus today.",
                    "Reparar as ofensas e a frieza dos homens contra o amor do Santíssimo Coração de Jesus."
                ),
                prayer = LocalizedText(
                    "¡Oh Corazón amantísimo!: te pido perdón por mis propias ingratitudes y por todos los que olvidan tu inmenso amor. Quiero desagraviarte con una vida fiel. Amén.",
                    "O most tender Heart: forgive my past neglect and the coldness of this world. Accept my love in reparation. Amen.",
                    "Perdão, Senhor, por tantas ingratidões! Desejo amar-Vos com ardor redobrado por aqueles que não Vos amam. Amém."
                ),
                intentionGuidance = LocalizedText("Reza una oración de desagravio y ofrece un sacrificio voluntario por amor a Dios.", "Offer a silent act of mortification in reparation for blasphemies.", "Ofereça uma oração reparadora diante de uma imagem de Jesus.")
            ),
            NovenaDay(
                dayNumber = 6,
                theme = LocalizedText("Día 6: La Paz en las Familias y Hogares", "Day 6: Peace in Families", "Dia 6: Paz nas Famílias"),
                scripture = "Colosenses 3, 12-15",
                meditation = LocalizedText(
                    "Jesús prometió: «Pondré la paz en sus familias y bendeciré los hogares donde la imagen de mi Sagrado Corazón sea expuesta y honrada».",
                    "Jesus promised: “I will bring peace to their families and bless every home where an image of My Heart is exposed and honored.”",
                    "A bênção do Sagrado Coração derrama santidade, compreensão e concórdia sobre os lares."
                ),
                prayer = LocalizedText(
                    "¡Rey y centro de todos los corazones!: reina en mi familia. Aleja de nosotros toda discordia, egoísmo y desunión, y haz de nuestro hogar un santuario de amor. Amén.",
                    "King and center of all hearts: reign in my family. Expel division and conflict, and transform our home into a haven of charity. Amen.",
                    "Reinai, Jesus, em nosso lar! Afastai qualquer discórdia e abençoai todos os membros de nossa família. Amém."
                ),
                intentionGuidance = LocalizedText("Ora por la conversión y reconciliación de tus familiares más alejados de la fe.", "Pray for relatives who have drifted away from the sacraments.", "Reze pela união e santificação de seus familiares.")
            ),
            NovenaDay(
                dayNumber = 7,
                theme = LocalizedText("Día 7: La Comunión Reparadora de los Primeros Viernes", "Day 7: The First Friday Communion", "Dia 7: A Comunhão dos Primeiros Sextas-Feiras"),
                scripture = "Juan 6, 54-56",
                meditation = LocalizedText(
                    "La Gran Promesa: «A todos los que comulguen nueve primeros viernes de mes seguidos, concederé la gracia de la penitencia final; no morirán en desgracia mía».",
                    "The Great Promise: “To all who receive Holy Communion on nine consecutive First Fridays, I will grant final perseverance; they shall not die in My disfavor.”",
                    "A grande promessa da perseverança final para aqueles que comungam nas nove primeiras sextas-feiras."
                ),
                prayer = LocalizedText(
                    "¡Oh Jesús, Pan vivo bajado del cielo!: hazme fiel a la Santa Eucaristía y a la devoción de los Primeros Viernes, para que alcance la bienaventuranza eterna. Amén.",
                    "O Living Bread come down from heaven: make me a faithful adorer of the Eucharist, that I may receive the grace of final perseverance. Amen.",
                    "Concedei-me, Jesus, a graça de comungar dignamente e a santidade da perseverança final. Amém."
                ),
                intentionGuidance = LocalizedText("Comprométete a vivir la devoción de los Nueve Primeros Viernes de mes.", "Resolve to observe the Nine First Fridays devotion.", "Renove o propósito de se confessar e comungar na primeira sexta-feira do mês.")
            ),
            NovenaDay(
                dayNumber = 8,
                theme = LocalizedText("Día 8: Consuelo para los Moribundos y Almas del Purgatorio", "Day 8: Solace for the Dying and Holy Souls", "Dia 8: Consolo para os Moribundos e Almas"),
                scripture = "Lucas 23, 42-43",
                meditation = LocalizedText(
                    "«Jesús, acuérdate de mí cuando vengas en tu Reino». La misericordia de Cristo abraza al moribundo y derrama rocío de alivio sobre las almas del Purgatorio.",
                    "“Jesus, remember me when You come into Your kingdom.” The Sacred Heart is the supreme comforter in death.",
                    "O amor de Jesus acolhe o arrependimento sincero até o último instante da vida."
                ),
                prayer = LocalizedText(
                    "¡Oh Corazón misericordioso de Jesús!: asiste a todos los que hoy están en agonía. Concédeles la contrición perfecta y abre las puertas del cielo a las benditas almas del Purgatorio. Amén.",
                    "O Merciful Heart: accompany all who are dying today. Grant them perfect contrition and receive the souls in Purgatory into eternal bliss. Amen.",
                    "Protegei os agonizantes nesta hora e dai descanso eterno às benditas almas do Purgatório. Amém."
                ),
                intentionGuidance = LocalizedText("Reza por los difuntos más olvidados y por los moribundos de este día.", "Pray for forgotten souls in Purgatory and those dying without sacraments.", "Reze pelas almas que mais precisam da misericórdia divina.")
            ),
            NovenaDay(
                dayNumber = 9,
                theme = LocalizedText("Día 9: La Consagración Total al Sagrado Corazón", "Day 9: Total Consecration", "Dia 9: A Consagração Total ao Sagrado Coração"),
                scripture = "Romanos 8, 38-39",
                meditation = LocalizedText(
                    "«Nada podrá separarnos del amor de Dios manifestado en Cristo Jesús». Consagrarse es entregar la vida entera para vivir bajo el imperio del amor divino.",
                    "“Nothing can separate us from the love of God in Christ Jesus.” Consecration is an irrevocable self-offering to His love.",
                    "A consagração é a entrega total da alma para viver inteiramente por Jesus e para Sua glória."
                ),
                prayer = LocalizedText(
                    "¡Oh dulcísimo Jesús!: hoy me consagro enteramente a tu Sagrado Corazón: mi cuerpo, mi alma, mi vida y cuanto soy y tengo. Sé mi Rey y mi Dios para siempre. Amén.",
                    "O most sweet Jesus: today I consecrate myself entirely to Your Sacred Heart: my soul, my body, my past, and my future. Be my all forever. Amen.",
                    "Consagro-me inteiramente ao Vosso Coração, ó Jesus! Tomai posse de toda a minha vida para sempre. Amém."
                ),
                intentionGuidance = LocalizedText("Recita con devoción la oración solemne de consagración y da gracias por las gracias recibidas.", "Pronounce your act of total consecration with firm faith and gratitude.", "Faça uma entrega confiante e agradeça a Deus pelas graças alcançadas.")
            )
        )
    )

    val divineMercyNovena = Novena(
        id = "divine_mercy",
        title = LocalizedText("Novena Oficial de la Divina Misericordia", "Official Divine Mercy Novena", "Novena Oficial da Divina Misericórdia"),
        patron = LocalizedText("Jesús Misericordioso", "Jesus the Divine Mercy", "Jesus Misericordioso"),
        category = LocalizedText("Misericordia y Salvación", "Mercy & Salvation", "Misericórdia e Salvação"),
        description = LocalizedText(
            "Novena dictada por Nuestro Señor Jesucristo a Santa Faustina Kowalska para rezarse preferentemente desde el Viernes Santo hasta la Fiesta de la Divina Misericordia.",
            "Nine-day Novena dictated personally by Jesus to Saint Faustina Kowalska, concluding on the Feast of Divine Mercy.",
            "Novena ditada por Jesus a Santa Faustina, trazendo cada dia um grupo diferente de almas ao Seu Coração misericordioso."
        ),
        miraculousHistory = LocalizedText(
            "Jesús prometió: 'Por medio de esta novena concederé a las almas toda clase de gracias...'",
            "Christ promised to Saint Faustina: 'During this novena I will grant to souls all kinds of graces...'",
            "Jesus prometeu derramar um oceano de graças sobre as almas que rezarem esta novena com confiança."
        ),
        promises = LocalizedText(
            "Remisión total de las culpas y penas en la Fiesta de la Misericordia, y protección en la hora de la muerte.",
            "Total remission of sins and punishments on Mercy Sunday, and angelic defense at the hour of death.",
            "Perdão total das culpas e penas e salvação eterna."
        ),
        days = listOf(
            NovenaDay(1, LocalizedText("Día 1: Por toda la humanidad y especialmente los pecadores", "Day 1: All Mankind, especially Sinners", "Dia 1: Toda a humanidade, especialmente os pecadores"), "Lucas 15, 10",
                LocalizedText("Jesús dice: «Hoy tráeme a toda la humanidad y especialmente a todos los pecadores, y sumérgelos en el mar de mi misericordia».", "Jesus said: “Today bring to Me all mankind, especially all sinners, and immerse them in the ocean of My mercy.”", "Jesus pede que mergulhemos em Sua misericórdia toda a humanidade."),
                LocalizedText("Jesús misericordioso, cuya naturaleza es tener compasión de nosotros: mira no nuestros pecados sino la confianza que depositamos en tu bondad infinita. Amén.", "Most Merciful Jesus: look not upon our sins but upon our trust which we place in Your infinite goodness. Amen.", "Misericordioso Jesus: acolhei toda a humanidade no Vosso Coração de amor. Amém."),
                LocalizedText("Reza la Coronilla de la Divina Misericordia por los pecadores del mundo entero.", "Pray the Divine Mercy Chaplet for the salvation of all souls.", "Reze o Terço da Misericórdia pelos pecadores.")
            ),
            NovenaDay(2, LocalizedText("Día 2: Por las almas de los sacerdotes y religiosos", "Day 2: The Souls of Priests and Religious", "Dia 2: As almas dos sacerdotes e religiosos"), "Hebreos 5, 1-4",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas de los sacerdotes y las almas de los religiosos, y sumérgelas en mi misericordia insondable».", "Jesus said: “Today bring to Me the souls of priests and religious, and immerse them in My unfathomable mercy.”", "Jesus nos convida a rezar com ardor pela santificação dos padres e religiosos."),
                LocalizedText("Jesús misericordioso, de quien procede todo bien: multiplica la gracia en las almas consagradas a tu servicio para que realicen dignas obras de misericordia. Amén.", "Most Merciful Jesus: increase grace in men and women consecrated to Your service. Amen.", "Jesus misericordioso: dai aos Vossos sacerdotes e religiosos a força de guiar o Vosso rebanho com santidade. Amém."),
                LocalizedText("Ofrece tu oración por el Santo Padre y los sacerdotes de tu diócesis.", "Offer this day for the Pope, your bishop, and parish priests.", "Reze pela perseverança e santidade dos sacerdotes.")
            ),
            NovenaDay(3, LocalizedText("Día 3: Por todas las almas devotas y fieles", "Day 3: All Devout and Faithful Souls", "Dia 3: Todas as almas devotas e fiéis"), "Juan 19, 25-27",
                LocalizedText("Jesús dice: «Hoy tráeme a todas las almas devotas y fieles, y sumérgelas en el mar de mi misericordia. Estas almas me consolaron en el camino del Calvario».", "Jesus said: “Today bring to Me all devout and faithful souls, and immerse them in the ocean of My mercy.”", "As almas fiéis são as gotas de consolo que aliviaram a agonia de Cristo na Paixão."),
                LocalizedText("Jesús misericordioso, que derramas tus gracias con tanta abundancia: acoge a todas las almas fieles y no permitas que jamás se aparten de tu amor. Amén.", "Most Merciful Jesus: receive all faithful souls into the abode of Your most compassionate Heart. Amen.", "Jesus compassivo: guardai os Vossos fiéis para que perseverem firmes na fé até o último suspiro. Amém."),
                LocalizedText("Pide por la perseverancia final de los miembros de tu comunidad parroquial.", "Pray for the perseverance of all devout Catholic believers.", "Reze pelos fiéis de sua paróquia e pelos que sofrem perseguição.")
            ),
            NovenaDay(4, LocalizedText("Día 4: Por los que no creen en Dios y los que no conocen a Jesús", "Day 4: Those who do not Believe in God", "Dia 4: Os que não creem em Deus e não conhecem Jesus"), "1 Timoteo 2, 3-4",
                LocalizedText("Jesús dice: «Hoy tráeme a los que no creen en Dios y a aquellos que todavía no me conocen. Los rayos de mi corazón iluminen las tinieblas de su mente».", "Jesus said: “Today bring to Me those who do not believe in God and those who do not yet know Me.”", "Deus quer que todos os homens se salvem e cheguem ao conhecimento da verdade."),
                LocalizedText("Jesús compasivísimo, que eres la Luz del mundo entero: acoge en tu Corazón a los que aún no te conocen para que los rayos de tu gracia los iluminen. Amén.", "Most compassionate Jesus: draw into Your Heart the souls of those who do not yet know You. Amen.", "Senhor Jesus, Luz das nações: iluminai aqueles que vivem no ateísmo ou ignorância religiosa. Amém."),
                LocalizedText("Ora por los pueblos y personas que jamás han escuchado el Evangelio.", "Pray for missionary efforts and people living in secular materialism.", "Reze pela evangelização dos povos que ainda não conhecem a Cristo.")
            ),
            NovenaDay(5, LocalizedText("Día 5: Por las almas de los separados de la Iglesia", "Day 5: The Souls of Separated Brethren", "Dia 5: As almas dos irmãos separados"), "Juan 17, 20-21",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas de los que se han separado de mi Iglesia, y sumérgelas en el mar de mi misericordia. Durante mi amarga Pasión desgarraron mi Cuerpo».", "Jesus said: “Today bring to Me the souls of those who have separated themselves from My Church.”", "Jesus rezou na Última Ceia: 'Pai, que todos sejam um, como Tu e Eu somos um'."),
                LocalizedText("Jesús infinitamente misericordioso: reúne en el redil de la única Iglesia que Tú fundaste a todos los hermanos separados para que haya un solo rebaño y un solo Pastor. Amén.", "Most Merciful Jesus: look with mercy upon our separated brethren. Draw them back to apostolic unity. Amen.", "Jesus, Bom Pastor: guiai todos os cristãos à plenitude da unidade católica sob a verdade do Evangelho. Amém."),
                LocalizedText("Ofrece esta jornada por la reconciliación y unidad de los cristianos en la Iglesia.", "Pray for authentic Christian unity rooted in apostolic truth.", "Reze pela unidade da Igreja e pelo diálogo fraterno.")
            ),
            NovenaDay(6, LocalizedText("Día 6: Por las almas mansas y humildes y los niños pequeños", "Day 6: The Meek, Humble & Children", "Dia 6: As almas mansas, humildes e as criancinhas"), "Mateo 18, 1-4",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas mansas y humildes y las almas de los niños pequeños, y sumérgelas en mi misericordia. Estas almas son las más semejantes a mi Corazón».", "Jesus said: “Today bring to Me the Meek and Humble Souls and the Souls of Little Children.”", "As almas puras e humildes sobem ao trono de Deus como incenso aromático."),
                LocalizedText("Jesús benignísimo, que dijiste: 'Dejen que los niños vengan a mí': guarda la inocencia de los niños, defiende a los no nacidos y llena a los humildes de tu paz. Amén.", "Most Merciful Jesus: preserve the purity of the young, protect the unborn, and bestow peace on the humble. Amen.", "Jesus amigo das crianças: protegei a pureza dos pequeninos e dai-nos um coração simples e humilde. Amém."),
                LocalizedText("Pide la defensa de los niños no nacidos y la pureza de la juventud.", "Pray for the protection of infants and children in danger.", "Reze pela proteção dos nascituros e crianças necessitadas.")
            ),
            NovenaDay(7, LocalizedText("Día 7: Por las almas que veneran la Divina Misericordia", "Day 7: Souls who Venerate My Mercy", "Dia 7: As almas que veneram a Divina Misericórdia"), "Salmo 89, 2",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas que veneran y ensalzan de modo especial mi misericordia. Estas almas son copias vivas de mi Corazón compasivo».", "Jesus said: “Today bring to Me the Souls who especially venerate and glorify My Mercy.”", "Quem difunde a misericórdia de Jesus experimentará a Sua proteção especial."),
                LocalizedText("Jesús compasivísimo: protege a las almas que proclaman la grandeza de tu misericordia. Defiéndelas en la hora de la muerte como tu propia gloria. Amén.", "Most Merciful Jesus: shield all who proclaim the greatness of Your Mercy. Be their refuge in life and victory in death. Amen.", "Senhor Jesus: abençoai todos os apóstolos da Vossa divina misericórdia e sustentai a sua missão com graça. Amém."),
                LocalizedText("Promete dar a conocer la devoción a la Divina Misericordia a otra persona.", "Share the Divine Mercy devotion with someone going through darkness.", "Ensine a oração do Terço da Misericórdia a alguém.")
            ),
            NovenaDay(8, LocalizedText("Día 8: Por las benditas almas del Purgatorio", "Day 8: The Holy Souls in Purgatory", "Dia 8: As almas benditas do Purgatório"), "2 Macabeos 12, 43-46",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas que están en la cárcel del Purgatorio y sumérgelas en el abismo de mi misericordia. Que los torrentes de mi Sangre apaguen sus llamas».", "Jesus said: “Today bring to Me the Souls who are in the prison of Purgatory, and immerse them in the abyss of My mercy.”", "As almas do Purgatório esperam com santa paciência a nossa oração e o Santo Sacrifício da Missa."),
                LocalizedText("Jesús misericordioso: mira con clemencia a las benditas almas del Purgatorio y ábreles las puertas de la gloria celestial por los méritos de tu Santa Pasión. Amén.", "Most Merciful Jesus: relieve the holy souls suffering purification and admit them swiftly into the joy of Your presence. Amen.", "Jesus compassivo: aliviai os sofrimentos das almas do Purgatório e concedei-lhes o descanso eterno no Paraíso. Amém."),
                LocalizedText("Ofrece una indulgencia o el rezo del Rosario por los difuntos de tu familia.", "Offer a Rosary or obtain an indulgence for departed souls.", "Reze pelas almas dos falecidos de sua família e amigos.")
            ),
            NovenaDay(9, LocalizedText("Día 9: Por las almas tibias", "Day 9: Lukewarm & Indifferent Souls", "Dia 9: As almas tíbias"), "Apocalipsis 3, 15-16",
                LocalizedText("Jesús dice: «Hoy tráeme a las almas tibias y sumérgelas en el abismo de mi misericordia. Estas almas son las que más dolorosamente hieren mi Corazón».", "Jesus said: “Today bring to Me Souls who have become Lukewarm, and immerse them in the abyss of My mercy.”", "A tibieza apaga o fervor do Espírito Santo. Jesus deseja acender nelas o fogo vivo de Seu amor."),
                LocalizedText("Jesús compasivísimo: sumerge en el fuego de tu amor divino a las almas tibias. Que el ardor de tu Corazón vuelva a encender en ellas la llama viva de la caridad. Amén.", "Most compassionate Jesus: immerse lukewarm souls in the fire of Your divine charity. Revive their deadened hearts with supernatural fervor. Amen.", "Senhor Jesus: inflamai as almas frias e tíbias no ardor do Vosso Santo Espírito para que Vos amem com entusiasmo. Amém."),
                LocalizedText("Haz un examen de conciencia sobre la tibieza y renueva tu fervor espiritual.", "Examine your own life for spiritual complacency and pray for zeal.", "Renove o fervor de sua oração e afaste toda preguiça espiritual.")
            )
        )
    )

    val stJudeNovena = Novena(
        id = "st_jude",
        title = LocalizedText("Novena Oficial a San Judas Tadeo", "Official Novena to Saint Jude Thaddeus", "Novena Oficial a São Judas Tadeu"),
        patron = LocalizedText("Apóstol y Patrón de las Causas Difíciles y Desesperadas", "Apostle and Patron of Difficult & Desperate Causes", "Apóstolo e Padroeiro das Causas Impossíveis"),
        category = LocalizedText("Peticiones Imposibles y Familia", "Impossible Causes & Family", "Causas Urgentes e Família"),
        description = LocalizedText(
            "Novena con los 9 textos tradicionales y oraciones aprobadas por la Iglesia. Millones de fieles dan testimonio de la milagrosa intercesión de San Judas en momentos de tribulación extrema.",
            "Complete 9-day novena to the Patron of Desperate Causes with traditional prayers, scripture meditations, and daily intentions.",
            "Novena completa de 9 dias ao Apóstolo e Padroeiro dos aflitos, com todas as orações autênticas da tradição católica."
        ),
        miraculousHistory = LocalizedText(
            "Santa Brígida de Suecia y San Bernardo experimentaron cómo Jesús concedía favores inmediatos a quienes invocaran a San Judas en sus mayores aprietos.",
            "St. Bridget of Sweden and St. Bernard received divine revelations to invoke St. Jude with absolute confidence in extremity.",
            "Jesus revelou a Santa Brígida que atenderia prontamente as preces dirigidas a São Judas Tadeu nos casos desesperados."
        ),
        promises = LocalizedText(
            "Auxilio en causas sin esperanza humana, salud en enfermedades graves, trabajo y paz en el hogar.",
            "Assistance in hopeless human difficulties, healing in severe sickness, employment, and familial reconciliation.",
            "Socorro nas causas difíceis, emprego, cura nas enfermidades e restauração da paz familiar."
        ),
        days = listOf(
            NovenaDay(1, LocalizedText("Día 1: La Vocación de San Judas al Apostolado", "Day 1: The Calling of St. Jude", "Dia 1: A Vocação de São Judas ao Apostolado"), "Lucas 6, 12-16",
                LocalizedText("Jesús pasó la noche en oración y al amanecer eligió a doce, a quienes llamó Apóstoles, entre ellos a Judas Tadeo. San Judas respondió con prontitud y fidelidad total.", "Jesus spent the night in prayer and chose twelve apostles, among them Jude Thaddeus, who followed Him faithfully.", "Jesus escolheu São Judas como coluna da Igreja; o apóstolo deixou tudo para seguir os passos do Mestre."),
                LocalizedText("¡Oh glorioso Apóstol San Judas Tadeo!: por la prontitud con que respondiste a la llamada de Jesús, alcánzame la gracia de responder con generosidad a la voluntad divina en mi vida... (mencionar aquí la petición). Amén.", "O glorious Apostle St. Jude: by your prompt obedience to Christ's call, obtain for me grace to embrace God's holy will. Amen.", "Glorioso São Judas Tadeu: alcançai-me a graça de viver fiel aos mandamentos de Cristo. Amém."),
                LocalizedText("Pide la gracia de escuchar con docilidad la voz de Dios en tu conciencia.", "Pray for prompt obedience to God's guidance in your daily vocation.", "Peça a Deus a graça da fidelidade à Sua santa vontade.")
            ),
            NovenaDay(2, LocalizedText("Día 2: El Amor y Fidelidad a Jesucristo", "Day 2: Love and Fidelity to Christ", "Dia 2: O Amor e Fidelidade a Jesus Cristo"), "Juan 14, 21-23",
                LocalizedText("En la Última Cena, San Judas preguntó a Jesús: «Señor, ¿por qué te has de manifestar a nosotros y no al mundo?». Jesús le reveló el misterio del amor íntimo de Dios al alma fiel.", "At the Last Supper, St. Jude asked: 'Lord, how is it that You will manifest Yourself to us, and not to the world?' Jesus revealed the glory of indwelling charity.", "Na Última Ceia, São Judas acolheu a promessa de Jesus de fazer morada no coração dos que O amam."),
                LocalizedText("¡San Judas compasivo, amigo entrañable del Señor!: enséñame a amar a Jesús con pureza de corazón para que el Padre y el Hijo hagan su morada en mi alma. Amén.", "St. Jude, faithful friend of the Savior: teach me to love Christ above all temporal treasures. Amen.", "São Judas, amigo fiel de Jesus: inflamai minha alma no amor divino e atendei minha aflição. Amém."),
                LocalizedText("Haz un acto de agradecimiento sincero por el sacramento del Bautismo.", "Thank God sincerely for your Catholic faith and baptismal grace.", "Agradeça a Deus pelo dom da fé católica.")
            ),
            NovenaDay(3, LocalizedText("Día 3: El Celo Apostólico por las Almas", "Day 3: Apostolic Zeal for Souls", "Dia 3: O Zelo Apostólico pelas Almas"), "Judas 1, 3",
                LocalizedText("En su Carta canónica, San Judas exhorta: «Luchen ardorosamente por la fe que de una vez para siempre ha sido entregada a los santos».", "St. Jude wrote: 'Contend earnestly for the faith which was once for all delivered to the saints.'", "São Judas exorta os fiéis a defender com coragem a integridade da fé católica."),
                LocalizedText("¡Apóstol intrépido!: concédeme un celo ardiente para defender la santa fe católica en mi ambiente y llevar a otros con mi buen ejemplo hacia el camino de salvación. Amén.", "Fearless Apostle: grant me apostolic courage to defend Catholic truth and lead souls to Christ. Amen.", "São Judas, coluna da fé: dai-me coragem para testemunhar o Evangelho sem medo do mundo. Amém."),
                LocalizedText("Reza por la conversión de alguien que haya caído en el ateísmo o la indiferencia.", "Pray for someone close to you who has lost the practice of the faith.", "Reze por uma pessoa que esteja afastada de Deus.")
            ),
            NovenaDay(4, LocalizedText("Día 4: La Fe Inquebrantable en las Pruebas", "Day 4: Unshakeable Faith in Trials", "Dia 4: A Fé Viva em Meio às Tribulações"), "Hebreos 11, 1",
                LocalizedText("San Judas predicó el Evangelio en Mesopotamia y Persia en medio de peligros, persecuciones y fatigas indecibles, sin retroceder jamás en su confianza divina.", "St. Jude preached the Gospel through hardship and persecution in Mesopotamia and Persia, trusting unreservedly in God.", "São Judas enfrentou perseguições na Mesopotâmia e na Pérsia, mantendo inabalável a esperança cristã."),
                LocalizedText("¡Oh glorioso protector mío!: cuando las dudas, la desesperanza y las dificultades materiales amenacen mi paz, sostenme con tu poderosa intercesión ante el trono celestial. Amén.", "Glorious protector: when doubt, desolation, and financial distress surround me, hold me steadfast in hope. Amen.", "Glorioso protetor: sustentai minha fé nas horas mais difíceis e sede meu amparo. Amém."),
                LocalizedText("Haz una confesión de fe sincera rezando el Credo con atención.", "Pray the Apostles' Creed slowly, renewing your total trust in God.", "Reze o Credo com devoção, renovando sua entrega a Deus.")
            ),
            NovenaDay(5, LocalizedText("Día 5: La Virtud de la Esperanza Cristiana", "Day 5: The Virtue of Christian Hope", "Dia 5: A Virtude da Esperança Cristã"), "Romanos 5, 3-5",
                LocalizedText("«La tribulación produce paciencia; la paciencia, virtud probada; y la virtud probada, esperanza. Y la esperanza no defrauda». San Judas es el apóstol de la santa esperanza.", "“Tribulation produces perseverance; character, hope. And hope does not disappoint.” St. Jude is the patron of divine hope.", "A esperança em Deus jamais engana: Ele cumpre Suas promessas no tempo oportuno."),
                LocalizedText("¡San Judas bendito!: aleja de mi corazón la tentación del desánimo y del desaliento. Haz que espere siempre con paz el socorro paternal de Dios en esta necesidad... Amén.", "Blessed St. Jude: banish discouragement from my heart. Fill me with patient expectation of divine assistance. Amen.", "São Judas bendito: afastai de mim o desânimo e renovai minha esperança na misericórdia divina. Amém."),
                LocalizedText("Lee el Salmo 91 y descansa bajo las alas de la Divina Providencia.", "Meditate on Psalm 91, entrusting your future to God's providence.", "Medite no Salmo 91 e confie na proteção dos Santos Anjos.")
            ),
            NovenaDay(6, LocalizedText("Día 6: La Caridad Fraterna y la Misericordia", "Day 6: Fraternal Charity & Mercy", "Dia 6: A Caridade Fraterna e o Perdão"), "Judas 1, 21-23",
                LocalizedText("«Consérvense en el amor de Dios... y tengan compasión de los que dudan». San Judas nos enseña que el camino más corto hacia Dios es la misericordia entrañable.", "“Keep yourselves in the love of God... have mercy on some who are doubting.” Mercy is the shortest path to heaven.", "São Judas nos convida a permanecer no amor de Deus e a socorrer com ternura os mais fracos."),
                LocalizedText("¡Amadísimo San Judas!: enséñame a perdonar a quienes me han hecho sufrir y a socorrer con generosidad a los pobres y necesitados de cuerpo y alma. Amén.", "Beloved St. Jude: grant me a forgiving spirit and a compassionate heart toward the poor and suffering. Amen.", "São Judas, apóstolo do perdão: ensinai-me a perdoar de coração a quem me ofendeu. Amém."),
                LocalizedText("Haz una obra de caridad: da una limosna o ayuda a alguien necesitado hoy.", "Perform a concrete corporal or spiritual work of mercy today.", "Faça uma obra de caridade ou dê uma ajuda a quem precisa.")
            ),
            NovenaDay(7, LocalizedText("Día 7: La Humildad y la Oración Constante", "Day 7: Humility and Constant Prayer", "Dia 7: A Humildade e a Oração Constante"), "1 Pedro 5, 6-7",
                LocalizedText("«Humíllense bajo la poderosa mano de Dios, para que Él los exalte a su debido tiempo; depositando en Él toda su ansiedad». La oración humilde traspasa las nubes.", "“Humble yourselves under the mighty hand of God, casting all your anxieties upon Him, because He cares for you.”", "A oração humilde e perseverante chega ao coração de Deus e obtém os maiores milagres."),
                LocalizedText("¡Santo Apóstol Judas Tadeo!: alcánzame un espíritu verdaderamente humilde, despojado de orgullo, que sepa orar con perseverancia sin desfallecer jamás. Amén.", "Holy Apostle: obtain for me genuine humility of heart, that my prayers may rise pleasing before God. Amen.", "São Judas: dai-me um coração humilde e perseverante na oração diária. Amém."),
                LocalizedText("Dedica 10 minutos de silencio contemplativo adorando a Dios.", "Spend 10 minutes in silent contemplative adoration before the crucifix.", "Faça dez minutos de oração silenciosa aos pés da Cruz.")
            ),
            NovenaDay(8, LocalizedText("Día 8: El Martirio: Fidelidad hasta la Sangre", "Day 8: Martyrdom: Faithful unto Death", "Dia 8: O Martírio e o Testemunho de Fé"), "Apocalipsis 2, 10",
                LocalizedText("San Judas coronó su vida apostólica derramando su sangre como mártir de Jesucristo en Beirut (Suanir), prefiriendo la muerte antes que negar a su divino Maestro.", "St. Jude sealed his preaching with his life's blood, martyred for Christ rather than compromising the truth of the Gospel.", "São Judas derramou seu sangue por amor a Cristo, selando seu ministério com a coroa do martírio."),
                LocalizedText("¡Mártir glorioso de Cristo!: por la corona inmarcesible que ganaste con tu sangre, dame fortaleza para vencer las tentaciones del pecado y vivir en gracia hasta el fin. Amén.", "Glorious Martyr: by the palm of victory you won, give me strength to overcome temptation and persevere unto death. Amen.", "Mártir invicto de Cristo: dai-me fortaleza para vencer o pecado e perseverar na santidade. Amém."),
                LocalizedText("Pide la gracia de evitar todo pecado mortal y vivir en estado de gracia.", "Pray for fortitude against mortal sin and the gift of final perseverance.", "Peça a graça de viver sempre em estado de graça santificante.")
            ),
            NovenaDay(9, LocalizedText("Día 9: La Gloria Eterna y la Coronación Celestial", "Day 9: Eternal Glory and Intercession", "Dia 9: A Glória Celeste e a Coroação Eterna"), "2 Timoteo 4, 7-8",
                LocalizedText("«He combatido el buen combate, he terminado la carrera, he guardado la fe. Por lo demás, me está guardada la corona de justicia». San Judas intercede hoy ante el trono del Cordero.", "“I have fought the good fight, I have finished the race, I have kept the faith.” St. Jude reigns with Christ, interceding for us.", "São Judas contempla eternamente a face de Deus no Céu e intercede sem cessar por nossas preces."),
                LocalizedText("¡Oh celestial protector San Judas Tadeo!: te doy gracias por tu amparo paternal. Presenta mi súplica en las llagas gloriosas de Jesús y acompáñame hasta que pueda alabarte con los santos en el Cielo. Amén.", "O heavenly patron St. Jude: accept my thanksgiving. Present my petition at the feet of the Lord and lead me to eternal glory. Amen.", "São Judas Tadeu, patrono amado: apresentai minha súplica a Jesus e levai-me um dia à glória do Paraíso. Amém."),
                LocalizedText("Da gracias a Dios por la intercesión de San Judas y promete vivir cristianamente.", "Offer thanksgiving to the Holy Trinity for St. Jude's powerful intercession.", "Agradeça a Deus pelas bênçãos recebidas e renove sua vida cristã.")
            )
        )
    )

    val undoerKnotsNovena = Novena(
        id = "undoer_knots",
        title = LocalizedText("Novena Oficial a Nuestra Señora Desatanudos", "Official Novena to Our Lady Undoer of Knots", "Novena Oficial a Nossa Senhora Desatadora dos Nós"),
        patron = LocalizedText("Madre de Misericordia que Desata los Nudos del Alma", "Mother of Mercy Who Unties the Knots of Life", "Mãe que Desata os Nós da Vida"),
        category = LocalizedText("Matrimonios, Familias y Sanación", "Marriage, Family & Healing", "Famílias e Reconciliação"),
        description = LocalizedText(
            "Novena con las 9 oraciones e intenciones auténticas difundidas por el Papa Francisco y basada en la teología de San Ireneo: 'El nudo de la desobediencia de Eva fue desatado por la obediencia de María'.",
            "Complete 9-day novena approved by the Church and beloved by Pope Francis, invoking Mary's maternal hands to untangle the agonizing knots of sin, conflict, and despair.",
            "Novena completa de 9 dias à Virgem Desatadora dos Nós, com as 9 orações autênticas para desatar os nós do pecado, da discórdia e da doença."
        ),
        miraculousHistory = LocalizedText(
            "En 1700, en la iglesia de San Pedro am Perlach (Augsburgo, Alemania), una pintura milagrosa representó a la Virgen María desatando una cinta con nudos de un matrimonio que iba a divorciarse, restaurando su amor para siempre.",
            "Painted in Augsburg in 1700, the miraculous image showed the Virgin untying the knotted wedding ribbon of a couple facing divorce, reconciling them completely.",
            "Pintada em Augsburgo no século XVIII, a imagem milagrosa salvou um matrimônio prestes a se romper ao desatar a fita dos nós nupciais."
        ),
        promises = LocalizedText(
            "Paz conyugal, desatamiento de heridas generacionales, perdón mutuo, liberación de vicios y paz interior.",
            "Marital reconciliation, healing of generational resentments, freedom from crippling addictions, and peace of mind.",
            "Restauração dos lares, desatamento de mágoas antigas, cura interior e reconciliação matrimonial."
        ),
        days = listOf(
            NovenaDay(1, LocalizedText("Día 1: Desatando el Nudo del Pecado y la Culpa", "Day 1: Untying the Knot of Sin & Guilt", "Dia 1: Desatando o Nó do Pecado e da Culpa"), "Génesis 3, 15",
                LocalizedText("Santa Madre mía amadísima: tú eres la mujer que aplasta la cabeza de la serpiente infernal. Hoy te presento el nudo del pecado y del remordimiento que me separa de la gracia.", "Dearest Mother: you crush the serpent's head. Today I bring you the knot of sin and guilt that separates my soul from divine peace.", "Santa Mãe de Deus: apresento-vos hoje o nó dos meus pecados que me privam da paz do coração."),
                LocalizedText("Virgen Santísima, por tu obediencia perfecta: toma en tus manos benditas este nudo que asfixia mi alma... (mencionar el nudo). Desátalo, Madre, por amor a tu divino Hijo. Amén.", "Holy Virgin, by your holy obedience: take into your hands this knot that suffocates my heart... (name it). Untie it for love of Christ. Amen.", "Virgem Imaculada: tomai em vossas mãos este nó que me aflige e desatai-o pelo poder de vosso Filho Jesus. Amém."),
                LocalizedText("Haz un propósito de acudir al Sacramento de la Confesión.", "Resolve to make a good confession and receive sacramental absolution.", "Faça um firme propósito de se confessar.")
            ),
            NovenaDay(2, LocalizedText("Día 2: Desatando el Nudo del Resentimiento y la Amargura", "Day 2: Untying the Knot of Resentment", "Dia 2: Desatando o Nó do Rancor e da Falta de Perdão"), "Efesios 4, 31-32",
                LocalizedText("«Desaparezca de entre ustedes toda amargura, ira, enojo, gritería y maledicencia... sean comprensivos y perdónense unos a otros como Dios los perdonó en Cristo».", "“Let all bitterness, wrath, anger, clamor, and evil speaking be put away from you... be kind and forgiving to one another.”", "A falta de perdão endurece o coração e ata a alma a correntes de tristeza."),
                LocalizedText("Madre de Misericordia: desata en mí el nudo del rencor hacia quienes me han herido. Dame un corazón capaz de perdonar sin límites como Jesús en la Cruz. Amén.", "Mother of Mercy: untie the stubborn knot of resentment in my wounded heart. Grant me grace to forgive unconditionally. Amen.", "Mãe amada: desatai em mim todo rancor e dai-me a graça de perdoar sinceramente a quem me magoou. Amém."),
                LocalizedText("Perdona de corazón a una persona concreta que te haya causado daño.", "Forgive consciously and by name someone who injured you.", "Perdoe em seu coração a pessoa que mais o ofendeu.")
            ),
            NovenaDay(3, LocalizedText("Día 3: Desatando el Nudo de la Ansiedad y el Miedo al Futuro", "Day 3: Untying the Knot of Anxiety", "Dia 3: Desatando o Nó da Angústia e do Medo"), "Filipenses 4, 6-7",
                LocalizedText("«Por nada se angustien, sino que en toda ocasión presenten sus peticiones a Dios en oración y acción de gracias. Y la paz de Dios custodiará sus corazones».", "“Do not be anxious about anything, but in every situation, by prayer and thanksgiving, present your requests to God.”", "A ansiedade e o medo roubam a paz que Cristo nos conquistou."),
                LocalizedText("María, Reina de la Paz: toma entre tus dedos purísimos los nudos del temor, la incertidumbre económica y la preocupación por mis seres queridos. Regálame tu serenidad. Amén.", "Mary, Queen of Peace: hold in your fingers the knots of financial dread, terror, and sleepless nights. Shower me with peace. Amen.", "Rainha da Paz: desatai os nós da angústia e do desespero e enchei meu coração com vossa santa confiança. Amém."),
                LocalizedText("Reza el Ave María lentamente contemplando la sonrisa maternal de la Virgen.", "Recite the Hail Mary slowly, resting in Mary's motherly protection.", "Reze a Ave Maria contemplando o amor materno de Nossa Senhora.")
            ),
            NovenaDay(4, LocalizedText("Día 4: Desatando los Nudos en el Matrimonio y la Familia", "Day 4: Untying Knots in Marriage & Family", "Dia 4: Desatando os Nós no Matrimônio e Família"), "1 Corintios 13, 4-7",
                LocalizedText("El amor es paciente y servicial; no busca lo suyo, todo lo disculpa, todo lo cree, todo lo espera. María es la guardiana sagrada de los hogares cristianos.", "Love is patient, love is kind; it keeps no record of wrongs. Mary restored the Augsburg marriage and protects every Christian family.", "Nossa Senhora é a protetora fiel das famílias e reconstrói os matrimônios em crise."),
                LocalizedText("Virgen Desatanudos: mira las desavenencias, silencios dolorosos y divisiones en mi hogar. Desata los nudos del orgullo y restablece la ternura y la comprensión mutua. Amén.", "Virgin Undoer of Knots: look upon strife and misunderstanding in my family. Dissolve pride and revive domestic love. Amen.", "Virgem Desatadora dos Nós: curai as divisões de nossa família e desatai todo desentendimento conjugal. Amém."),
                LocalizedText("Ten hoy un gesto de ternura y reconciliación con alguien de tu familia.", "Make a conscious gesture of appreciation and warmth toward a family member.", "Tenha um gesto de carinho e paciência em sua casa hoje.")
            ),
            NovenaDay(5, LocalizedText("Día 5: Desatando los Nudos Laborales y Económicos", "Day 5: Untying Knots of Work & Finances", "Dia 5: Desatando os Nós Financeiros e de Trabalho"), "Mateo 6, 31-33",
                LocalizedText("«Busquen primero el Reino de Dios y su justicia, y todas estas cosas se les darán por añadidura». María proveyó en las bodas de Caná cuando faltó el vino.", "“Seek first the kingdom of God and his righteousness, and all these things will be added to you.” Mary noticed the missing wine at Cana.", "Nas Bodas de Caná, Maria intercedeu quando faltou o vinho; ela socorre nossas necessidades materiais."),
                LocalizedText("Madre solícita: tú que intercediste en Caná para que no faltara el vino de la fiesta, desata los nudos de mis deudas, desempleo y carencias materiales. Confío en tu auxilio. Amén.", "Attentive Mother: as you pleaded at Cana, untie the knots of debt, joblessness, and material distress. I trust in you. Amen.", "Mãe bendita: desatai os nós das dificuldades financeiras e concedei-me um trabalho digno. Amém."),
                LocalizedText("Pide la intercesión de San José Obrero junto con María.", "Invoke St. Joseph alongside Our Lady for honest employment and provision.", "Peça o auxílio de São José Operário para suas necessidades temporais.")
            ),
            NovenaDay(6, LocalizedText("Día 6: Desatando el Nudo de la Enfermedad y el Dolor Físico", "Day 6: Untying Knots of Sickness & Pain", "Dia 6: Desatando os Nós da Doença e Sofrimento"), "Lucas 8, 43-48",
                LocalizedText("La mujer que tocó el manto de Jesús fue sanada al instante. María estuvo de pie junto a la Cruz, consolando a su Hijo sufriente y convirtiéndose en Salud de los enfermos.", "Standing by the Cross, Mary comforted the suffering Savior and became the Health of the Sick.", "Aos pés da Cruz, Maria acolheu todo o sofrimento humano, tornando-se Saúde dos Enfermos."),
                LocalizedText("Salud de los enfermos: pongo en tus manos la salud de mi cuerpo y de mi mente. Desata los nudos de la enfermedad que me aflige o aflige a los míos, según la santa voluntad de Dios. Amén.", "Health of the Sick: I surrender my ailments to your healing hands. Untie the knots of sickness and restore wholeness. Amen.", "Saúde dos Enfermos: desatai os nós das enfermidades e concedei-me alívio e fortaleza. Amém."),
                LocalizedText("Ofrece tus molestias o dolores físicos en unión con la Pasión de Cristo.", "Offer small physical discomforts in union with Christ's cross for souls.", "Ofereça seus sofrimentos corporais pela conversão dos pecadores.")
            ),
            NovenaDay(7, LocalizedText("Día 7: Desatando el Nudo de la Confusión Espiritual y Vicios", "Day 7: Untying Knots of Addictions & Darkness", "Dia 7: Desatando os Nós dos Vícios e Escuridão"), "Juan 8, 12",
                LocalizedText("«Yo soy la luz del mundo; el que me sigue no andará en tinieblas, sino que tendrá la luz de la vida». María es la Estrella del Mar que guía en la noche oscura.", "“I am the light of the world. Whoever follows me will never walk in darkness.” Mary is the Star of the Sea guiding the lost.", "Maria é a Estrela do Mar que nos guia em meio às trevas das tentações e dos vícios."),
                LocalizedText("Madre Purísima: desata las cadenas de los malos hábitos, dependencias y ataduras espirituales que me esclavizan. Libera mi alma para volar hacia Dios. Amén.", "Most Pure Mother: untie every addiction, dark habit, and moral compromise that binds my freedom. Restore my dignity in Christ. Amen.", "Mãe Santíssima: desatai os nós das más inclinações e vícios que aprisionam minha alma. Amém."),
                LocalizedText("Renuncia conscientemente a un hábito o vicio que te aparte de la santidad.", "Firmly renounce a destructive habit or temptation in prayer.", "Renuncie a um mau hábito e busque a pureza de vida.")
            ),
            NovenaDay(8, LocalizedText("Día 8: Desatando el Nudo de la Frialdad y Tibieza Espiritual", "Day 8: Untying Knots of Spiritual Sloth", "Dia 8: Desatando os Nós da Tibieza Espiritual"), "Apocalipsis 3, 20",
                LocalizedText("«Mira que estoy a la puerta y llamo. Si alguno oye mi voz y abre la puerta, entraré a él». María nos ayuda a reabrir la puerta del corazón a la oración.", "“Behold, I stand at the door and knock.” Mary helps us reopen cold hearts to passionate prayer.", "Jesus bate à porta de nossa alma; Maria nos ensina a acolhê-Lo com fervor renovado."),
                LocalizedText("Madre del Amor Hermoso: desata el nudo de la pereza espiritual, del desgano para orar y de la rutina estéril. Enciende en mi pecho el celo por las cosas santas. Amén.", "Mother of Fair Love: untie the knot of spiritual dryness, apathy, and routine. Fan into flame the spark of divine love. Amen.", "Mãe do Amor Divino: desatai a frieza de minha oração e dai-me santo fervor pelo Reino de Deus. Amém."),
                LocalizedText("Reza el Santo Rosario con profunda devoción y meditación de los misterios.", "Pray the Holy Rosary with fresh attention, contemplating each mystery.", "Reze o Santo Terço com atenção e amor filial.")
            ),
            NovenaDay(9, LocalizedText("Día 9: Acción de Gracias y Consagración a Nuestra Señora", "Day 9: Thanksgiving & Consecration", "Dia 9: Ação de Graças e Consagração a Maria"), "Lucas 1, 46-55",
                LocalizedText("«Proclama mi alma la grandeza del Señor, y se alegra mi espíritu en Dios mi Salvador, porque ha mirado la humillación de su sierva». María desata los nudos y nos llena de gozo.", "“My soul magnifies the Lord, and my spirit rejoices in God my Savior.” Mary dissolves our knots into triumphant songs of joy.", "O Magnificat é o canto de vitória dos humildes cujos nós foram desatados por Deus."),
                LocalizedText("¡Oh María, Madre mía dulcísima, Desatanudos de mi vida!: te doy gracias de todo corazón porque has escuchado mi plegaria. Te consagro mi vida entera: sé mi abogada y consuelo hasta la eternidad. Amén.", "O Mary, sweetest Mother and Undoer of Knots: receive my profound thanksgiving. Keep my soul safe in your mantle until eternal bliss. Amen.", "Ó Maria, Desatadora dos Nós de minha vida: agradeço-vos por vosso amparo maternal e consagro-me a vós para sempre. Amém."),
                LocalizedText("Da gracias a Dios por María y promete difundir esta bendita devoción.", "Give praise to God for Mary's intercession and share this novena with others.", "Agradeça a Nossa Senhora e ensine esta devoção a quem sofre.")
            )
        )
    )

    val allNovenas: List<Novena> = listOf(
        sacredHeartNovena,
        divineMercyNovena,
        stJudeNovena,
        undoerKnotsNovena
    )
}
