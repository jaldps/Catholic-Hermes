package com.example.data.repository

import com.example.data.models.CatholicPilgrimageSanctuary
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.models.LocalizedText
import com.example.data.models.SpiritualHabitItem

object PilgrimageAndHabitsData {

    val globalPilgrimages: List<CatholicPilgrimageSanctuary> = listOf(
        CatholicPilgrimageSanctuary(
            id = "rome_vatican",
            name = LocalizedText("Basílica Papal de San Pedro y Tumba de los Apóstoles", "Papal Basilica of Saint Peter & The Vatican", "Basílica Papal de São Pedro no Vaticano"),
            cityCountry = LocalizedText("Ciudad del Vaticano / Roma, Italia", "Vatican City / Rome, Italy", "Cidade do Vaticano / Roma, Itália"),
            historicalOrigin = LocalizedText(
                "Construida directamente sobre la colina vaticana sobre la tumba del Apóstol San Pedro, martirizado bajo Nerón en el año 64 d.C. Centro visible de la unidad de la Iglesia Católica.",
                "Erected over the tomb of Saint Peter the Apostle, crucified on Vatican Hill circa 64 AD. Visible center of Catholic unity.",
                "Erguida sobre o sepulcro do Apóstolo São Pedro, crucificado sob o imperador Nero. Centro da Sé de Pedro."
            ),
            spiritualSignificance = LocalizedText(
                "Sede del Sucesor de Pedro y Vicario de Cristo en la tierra. Alberga la Puerta Santa abierta en los Jubileos universales.",
                "Seat of the Successor of Peter, displaying universal apostolic communion and the Holy Door of Jubilee years.",
                "Sé do Sucessor de Pedro e morada da Porta Santa dos Anos Jubilares da Igreja."
            ),
            relicsAndPatron = LocalizedText(
                "Tumba de San Pedro, tumba de San Juan Pablo II, tumba de San Juan XXIII y reliquias de la Pasión.",
                "Tomb of St. Peter, St. John Paul II, St. John XXIII, and relic of the True Cross.",
                "Túmulo de São Pedro Apóstolo e dos Santos Papas João Paulo II e João XXIII."
            ),
            plenaryIndulgenceNote = LocalizedText(
                "Indulgencia plenaria concedida a los peregrinos que visitan la basílica, rezan el Credo y el Padre Nuestro por el Papa y confiesan.",
                "Plenary indulgence available under standard conditions (Confession, Communion, prayer for the Pope).",
                "Indulgência plenária aos peregrinos que rezam com devoção diante do túmulo do Apóstolo."
            ),
            practicalPilgrimAdvice = LocalizedText(
                "Asistir a la bendición dominical del Ángelus en la Plaza de San Pedro y reservar con antelación la visita a la Necrópolis Vaticana (Scavi).",
                "Attend the Sunday Angelus in St. Peter's Square and reserve the Scavi excavations tour in advance.",
                "Participe do Angelus dominical na Praça de São Pedro e visite a Cripta dos Papas."
            ),
            coordinateLatLong = Pair(41.9022, 12.4539)
        ),
        CatholicPilgrimageSanctuary(
            id = "holy_sepulchre",
            name = LocalizedText("Basílica del Santo Sepulcro y Calvario", "Church of the Holy Sepulchre & Calvary", "Basílica do Santo Sepulcro e Monte Calvário"),
            cityCountry = LocalizedText("Jerusalén, Tierra Santa", "Jerusalem, Holy Land", "Jerusalém, Terra Santa"),
            historicalOrigin = LocalizedText(
                "Identificada por Santa Elena en el año 326 d.C., engloba la roca viva del Monte Gólgota donde Cristo fue crucificado y la tumba vacía de donde resucitó al tercer día.",
                "Consecrated by St. Helena in 326 AD, enclosing the historical rock of Golgotha and the empty tomb of the Risen Christ.",
                "Consagrada no século IV por Santa Helena sobre o Calvário e o sepulcro de onde Cristo ressuscitou."
            ),
            spiritualSignificance = LocalizedText(
                "El lugar más sagrado de la cristiandad entera: donde la muerte fue vencida por la Resurrección de Nuestro Señor Jesucristo.",
                "The supreme holy place of Christianity where our Redemption was completed and eternal life opened.",
                "O local mais sagrado do cristianismo: onde ocorreu a Redenção da humanidade e a vitória sobre a morte."
            ),
            relicsAndPatron = LocalizedText("La Roca del Calvario, la Piedra de la Unción y el Edículo del Santo Sepulcro Vacío.", "The Rock of Calvary, Stone of Anointing, and the Edicule of the Empty Tomb.", "A Rocha do Gólgota e o Santo Sepulcro Vazio."),
            plenaryIndulgenceNote = LocalizedText("Indulgencia plenaria visitando devotamente el Santo Sepulcro en peregrinación santa.", "Plenary indulgence for holy pilgrims venerating the site of Christ's Passion and Resurrection.", "Indulgência plenária aos peregrinos na Terra Santa."),
            practicalPilgrimAdvice = LocalizedText("Rezar el Vía Crucis a lo largo de la Vía Dolorosa en la Ciudad Vieja culminando dentro del Sepulcro.", "Walk the Via Dolorosa early in the morning and attend the daily Eucharistic liturgy at the Edicule.", "Percorra a Via Dolorosa e viva a Santa Missa diante do sepulcro vazio."),
            coordinateLatLong = Pair(31.7785, 35.2296)
        ),
        CatholicPilgrimageSanctuary(
            id = "fatima_sanctuary",
            name = LocalizedText("Santuario de Nuestra Señora del Rosario de Fátima", "Sanctuary of Our Lady of Fatima", "Santuário de Nossa Senhora de Fátima"),
            cityCountry = LocalizedText("Cova da Iria / Fátima, Portugal", "Fatima, Portugal", "Fátima, Portugal"),
            historicalOrigin = LocalizedText(
                "Lugar de las apariciones de la Virgen María a los tres pastorcitos en 1917, con el milagro del sol del 13 de octubre.",
                "Site of the 1917 apparitions of the Mother of God, calling humanity to the daily Rosary and reparation.",
                "Palco das aparições de Nossa Senhora aos três pastorinhos em 1917."
            ),
            spiritualSignificance = LocalizedText(
                "Altar del mundo para la oración universal por la paz y el triunfo del Inmaculado Corazón de María.",
                "Worldwide altar of supplication for peace and the conversion of sinners through the Immaculate Heart.",
                "Altar do mundo para a oração contínua pela paz e conversão dos povos."
            ),
            relicsAndPatron = LocalizedText("Tumbas de San Francisco Marto, Santa Jacinta Marto y la Sierva de Dios Hermana Lucía.", "Tombs of St. Francisco, St. Jacinta, and Sister Lucia in the Basilica of Our Lady of the Rosary.", "Túmulos dos Santos Francisco e Jacinta Marto e da Irmã Lúcia."),
            plenaryIndulgenceNote = LocalizedText("Indulgencia plenaria en la Capilla de las Apariciones en las fiestas marianas solemnes.", "Plenary indulgence available for devout pilgrims at the Chapel of Apparitions.", "Indulgência plenária na Capelinha das Aparições."),
            practicalPilgrimAdvice = LocalizedText("Participar en la conmovedora Procesión de las Velas nocturna rezando el Santo Rosario en varios idiomas.", "Join the breathtaking nightly candlelight procession with thousands of pilgrims.", "Participe da Procissão das Velas à noite com a recitação do Santo Terço."),
            coordinateLatLong = Pair(39.6312, -8.6733)
        ),
        CatholicPilgrimageSanctuary(
            id = "lourdes_sanctuary",
            name = LocalizedText("Santuario de Nuestra Señora de Lourdes", "Sanctuary of Our Lady of Lourdes", "Santuário de Nossa Senhora de Lourdes"),
            cityCountry = LocalizedText("Lourdes, Altos Pirineos, Francia", "Lourdes, France", "Lourdes, França"),
            historicalOrigin = LocalizedText(
                "En 1858, María Santísima reveló a Santa Bernardita: 'Yo soy la Inmaculada Concepción', brotando la fuente de agua de sanación.",
                "1858 apparitions where Our Lady revealed her Immaculate Conception and gave the miraculous healing spring.",
                "Em 1858, a Virgem Imaculada apareceu a Santa Bernadete e fez brotar a fonte de água curadora."
            ),
            spiritualSignificance = LocalizedText(
                "Capital mundial de la esperanza para los enfermos, los pobres y los que buscan sanación corporal y espiritual.",
                "Sanctuary of comfort, reconciliation, and miraculous healing for the sick and afflicted.",
                "Capital espiritual da consolação e cura para todos os doentes do corpo e da alma."
            ),
            relicsAndPatron = LocalizedText("La Gruta milagrosa de Massabielle y reliquias de Santa Bernardita Soubirous.", "The miraculous Grotto of Massabielle and relics of St. Bernadette.", "A Gruta de Massabielle e o manancial de águas benditas."),
            plenaryIndulgenceNote = LocalizedText("Indulgencia plenaria en el santuario cumpliendo las condiciones de la Iglesia.", "Plenary indulgence granted to pilgrims who pray before the Grotto and receive sacraments.", "Indulgência plenária aos peregrinos que rezam na Gruta."),
            practicalPilgrimAdvice = LocalizedText("Asistir a la Bendición con el Santísimo Sacramento a los enfermos por la tarde.", "Participate in the afternoon Eucharistic Procession and blessing of the sick.", "Participe da Bênção dos Enfermos com o Santíssimo Sacramento."),
            coordinateLatLong = Pair(43.0975, -0.0583)
        ),
        CatholicPilgrimageSanctuary(
            id = "santiago_compostela",
            name = LocalizedText("Catedral y Tumba del Apóstol Santiago el Mayor", "Cathedral of Santiago de Compostela", "Catedral de Santiago de Compostela"),
            cityCountry = LocalizedText("Galicia, España", "Galicia, Spain", "Galícia, Espanha"),
            historicalOrigin = LocalizedText(
                "Descubrimiento del sepulcro del Apóstol Santiago en el siglo IX. Desde hace más de mil años, millones de peregrinos recorren el 'Camino de Santiago'.",
                "Ninth-century discovery of the tomb of St. James the Greater, inaugurating the historic Camino de Santiago.",
                "Túmulo do Apóstolo São Tiago Maior que deu origem ao lendário Caminho de Santiago."
            ),
            spiritualSignificance = LocalizedText(
                "Símbolo del camino cristiano de la vida como peregrinación hacia la santidad y encuentro fraterno.",
                "Symbol of the Christian pilgrimage on earth toward heavenly Jerusalem.",
                "Símbolo da peregrinação da fé e da comunhão fraterna entre os povos."
            ),
            relicsAndPatron = LocalizedText("Sepulcro del Apóstol Santiago el Mayor y el célebre incensario Botafumeiro.", "Tomb and relics of Saint James the Apostle.", "Relíquias veneráveis de São Tiago e o impressionante Botafumeiro."),
            plenaryIndulgenceNote = LocalizedText("Indulgencia plenaria plenísima en los Años Santos Compostelanos (cuando el 25 de julio cae en domingo).", "Jubilee plenary indulgence granted to pilgrims arriving via the Camino.", "Indulgência plenária concedida aos peregrinos que alcançam a Catedral."),
            practicalPilgrimAdvice = LocalizedText("Visitar la Misa del Peregrino a las 12:00 PM y dar el tradicional abrazo a la imagen del Apóstol en el camarín.", "Attend the daily 12:00 PM Pilgrim Mass and embrace the venerable statue of Saint James.", "Participe da Missa do Peregrino e faça a oração aos pés do Apóstolo."),
            coordinateLatLong = Pair(42.8806, -8.5447)
        )
    )

    val spiritualHabitsList: List<SpiritualHabitItem> = listOf(
        SpiritualHabitItem(
            id = "habit_morning",
            title = LocalizedText("Oración y Ofrecimiento de la Mañana", "Morning Prayer & Offering", "Oração e Oferecimento da Manhã"),
            category = LocalizedText("Oración", "Prayer", "Oração"),
            timeSuggestion = "07:00 AM",
            description = LocalizedText("Dedica tus primeras palabras al Señor consagrando todas tus obras del día.", "Offer your first thoughts to God, consecrating your day to His holy will.", "Consagre o seu dia nas mãos do Pai ao acordar."),
            iconName = "wb_sunny"
        ),
        SpiritualHabitItem(
            id = "habit_mass",
            title = LocalizedText("Santa Misa / Lecturas del Día", "Holy Mass / Daily Readings", "Santa Missa / Liturgia Diária"),
            category = LocalizedText("Eucaristía", "Eucharist", "Eucaristia"),
            timeSuggestion = "08:00 AM / Tarde",
            description = LocalizedText("Participar en la Eucaristía o meditar el Evangelio y lecturas de hoy.", "Participate in Holy Mass or prayerfully read today's Mass readings.", "Viver a Santa Missa ou ler atentamente a liturgia da Palavra."),
            iconName = "church"
        ),
        SpiritualHabitItem(
            id = "habit_rosary",
            title = LocalizedText("El Santo Rosario", "The Holy Rosary", "O Santo Terço"),
            category = LocalizedText("Mariana", "Marian", "Mariana"),
            timeSuggestion = "Cualquier hora",
            description = LocalizedText("Rezar los cinco misterios con amor a la Virgen María pidiendo por tu familia.", "Recite the 5 decades contemplating Christ through the eyes of Mary.", "Rezar os cinco mistérios em união com a Mãe de Deus."),
            iconName = "rosary"
        ),
        SpiritualHabitItem(
            id = "habit_angelus",
            title = LocalizedText("El Ángelus del Mediodía", "The Midday Angelus", "O Angelus do Meio-Dia"),
            category = LocalizedText("Encarnación", "Incarnation", "Encarnação"),
            timeSuggestion = "12:00 PM",
            description = LocalizedText("Pausa sagrada al mediodía para recordar que 'El Verbo se hizo carne'.", "Pause at noon to remember the divine miracle of Christ's Incarnation.", "Pausa bendita às 12h para honrar a Encarnação do Salvador."),
            iconName = "schedule"
        ),
        SpiritualHabitItem(
            id = "habit_examen",
            title = LocalizedText("Examen de Conciencia Nocturno", "Night Examination of Conscience", "Exame de Consciência Noturno"),
            category = LocalizedText("Conversión", "Conversion", "Conversão"),
            timeSuggestion = "09:30 PM",
            description = LocalizedText("Revisar con gratitud el día, pedir perdón por las faltas y rezar el Acto de Contrición.", "Review graces, ask forgiveness for sins, and pray the Act of Contrition before sleep.", "Agradecer a Deus, examinar o dia com humildade e rezar o ato de contrição."),
            iconName = "nights_stay"
        )
    )

    val upcomingLiturgicalDays: List<LiturgicalCalendarDay> = listOf(
        LiturgicalCalendarDay(
            dayOfMonth = 25,
            monthName = "Octubre",
            liturgicalColorHex = 0xFF2C6B3E, // Green
            seasonName = LocalizedText("Tiempo Ordinario", "Ordinary Time", "Tempo Comum"),
            celebrationTitle = LocalizedText("Domingo XXX del Tiempo Ordinario", "30th Sunday in Ordinary Time", "30º Domingo do Tempo Comum"),
            rank = LocalizedText("Solemnidad del Señor (Precepto)", "Lord's Solemnity (Obligation)", "Solenidade do Senhor (Preceito)"),
            fastingRule = LocalizedText("Precepto de Santa Misa Dominical (Sin ayuno penitencial)", "Sunday Mass Obligation (Festive, no fasting)", "Preceito da Santa Missa (Sem jejum penitencial)"),
            gospelVerseRef = "Marcos 10, 46-52",
            sundayCycle = LocalizedText("Ciclo B (San Marcos)", "Cycle B (St. Mark)", "Ciclo B (São Marcos)"),
            weekdayCycle = LocalizedText("Año II (Pares)", "Cycle II (Even Year)", "Ano II (Pares)"),
            isFastOrAbstinence = false,
            missalMeditation = LocalizedText(
                "«¡Hijo de David, ten compasión de mí! Tu fe te ha salvado». Jesús se detiene ante el clamor del ciego Bartimeo y nos invita a arrojar el manto de nuestras cegueras para seguirle en el camino.",
                "“Son of David, have pity on me! Your faith has saved you.” Jesus halts at Bartimaeus' humble cry, inviting us to throw off our blindness and follow Him along the road of the Cross.",
                "“Filho de Davi, tem piedade de mim! Tua fé te salvou”. Jesus para diante do clamor de Bartimeu e nos chama a ver a luz da Sua salvação."
            )
        ),
        LiturgicalCalendarDay(
            dayOfMonth = 1,
            monthName = "Noviembre",
            liturgicalColorHex = 0xFFD4AF37, // White/Gold
            seasonName = LocalizedText("Solemnidad Universal", "Solemnity", "Solenidade"),
            celebrationTitle = LocalizedText("Solemnidad de Todos los Santos", "Solemnity of All Saints", "Solenidade de Todos os Santos"),
            rank = LocalizedText("Solemnidad de Precepto", "Solemnity of Obligation", "Solenidade de Guarda"),
            fastingRule = LocalizedText("Día de fiesta y gozo pascual en la comunión de los santos", "Feast day rejoicing in the glory of the saints", "Dia de festa e júbilo pascal na comunhão dos santos"),
            gospelVerseRef = "Mateo 5, 1-12a",
            sundayCycle = LocalizedText("Ciclo B", "Cycle B", "Ciclo B"),
            weekdayCycle = LocalizedText("Año II", "Cycle II", "Ano II"),
            isFastOrAbstinence = false,
            missalMeditation = LocalizedText(
                "«Bienaventurados los limpios de corazón, porque ellos verán a Dios». La multitud innumerable de los santos nos muestra que el camino de las bienaventuranzas es la verdadera victoria sobre el mundo.",
                "“Blessed are the pure in heart, for they shall see God.” The countless multitude of saints proves that living the Beatitudes brings eternal triumph with the Lamb.",
                "“Bem-aventurados os puros de coração, porque verão a Deus”. A multidão celeste dos santos nos convida à santidade da vida diária."
            )
        ),
        LiturgicalCalendarDay(
            dayOfMonth = 2,
            monthName = "Noviembre",
            liturgicalColorHex = 0xFF5E2777, // Violet/Black
            seasonName = LocalizedText("Conmemoración de los Fieles Difuntos", "All Souls' Day", "Fiéis Defuntos"),
            celebrationTitle = LocalizedText("Conmemoración de Todos los Fieles Difuntos", "The Commemoration of All the Faithful Departed", "Comemoração de Todos os Fiéis Defuntos"),
            rank = LocalizedText("Conmemoración Litúrgica Universal", "Universal Liturgical Commemoration", "Comemoração Litúrgica Universal"),
            fastingRule = LocalizedText("Oración especial e indulgencia plenaria aplicable a las almas del Purgatorio", "Prayers & plenary indulgence applicable to Holy Souls in Purgatory", "Oração fervorosa e sufrágio pelas almas benditas do Purgatório"),
            gospelVerseRef = "Juan 6, 37-40",
            sundayCycle = LocalizedText("Ciclo B", "Cycle B", "Ciclo B"),
            weekdayCycle = LocalizedText("Año II", "Cycle II", "Ano II"),
            isFastOrAbstinence = true,
            missalMeditation = LocalizedText(
                "«Todo lo que el Padre me da vendrá a mí, y al que venga a mí no lo echaré fuera». Oramos con amor y esperanza por la purificación final de nuestros seres queridos en el Purgatorio.",
                "“Everything that the Father gives me will come to me, and anyone who comes to me I will never cast out.” We pray with loving hope for the holy souls in purgatory.",
                "“Todo aquele que o Pai me dá virá a mim”. Rezamos com piedade filial pelo repouso eterno das benditas almas."
            )
        )
    )

    val sinAvoiderCatalog: List<com.example.data.models.SinAvoiderOption> = listOf(
        com.example.data.models.SinAvoiderOption(
            sinKey = "LUST",
            title = LocalizedText("Lujuria / Impureza", "Lust / Impurity", "Luxúria / Impureza"),
            counterVirtue = LocalizedText("Castidad y Pureza de Corazón", "Chastity & Purity of Heart", "Castidade e Pureza de Coração"),
            advice = LocalizedText(
                "«¡Huye inmediatamente! Reza un Ave María poniendo la mirada en la Santísima Virgen, Templo del Espíritu Santo».",
                "“Flee the temptation at once! Pray a Hail Mary gazing upon the Blessed Virgin, Living Temple of the Holy Spirit.”",
                "“Fuja da ocasião de pecado! Reze uma Ave Maria consagrando os seus sentidos à Mãe de Deus.”"
            ),
            iconName = "favorite"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "ANGER",
            title = LocalizedText("Ira / Descontrol", "Anger / Wrath", "Ira / Rancor"),
            counterVirtue = LocalizedText("Paciencia y Mansedumbre", "Patience & Meekness", "Paciência e Mansidão"),
            advice = LocalizedText(
                "«Guarda sagrado silencio antes de hablar o escribir. Respira y pronuncia el Nombre de Jesús».",
                "“Keep holy silence before reacting or sending a harsh word. Breathe and invoke the Sacred Name of Jesus.”",
                "“Guarde silêncio interior antes de responder. Respire fundo e invoque o Santo Nome de Jesus.”"
            ),
            iconName = "self_improvement"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "GLUTTONY",
            title = LocalizedText("Gula / Apetito Desmedido", "Gluttony / Overeating", "Gula / Excesso"),
            counterVirtue = LocalizedText("Templanza y Ayuno Santo", "Temperance & Holy Fasting", "Temperança e Jejum"),
            advice = LocalizedText(
                "«Renuncia a un gusto superfluo y ofrécelo por las almas en recuerdo de la sed de Cristo en la Cruz».",
                "“Deny yourself a snack or luxury and offer it up for holy souls, recalling Christ's thirst on the Cross.”",
                "“Faça uma pequena renúncia voluntária e ofereça a Cristo crucificado que teve sede no Calvário.”"
            ),
            iconName = "restaurant"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "SLOTH",
            title = LocalizedText("Pereza / Acedia Espiritual", "Sloth / Spiritual Acedia", "Preguiça / Acídia"),
            counterVirtue = LocalizedText("Diligencia y Fervor Santo", "Diligence & Holy Fervor", "Diligência e Fervor"),
            advice = LocalizedText(
                "«Comienza tu deber u oración de inmediato sin esperar a sentir ganas; la fidelidad se forja en el sacrificio».",
                "“Begin your duty or prayer immediately without waiting to 'feel like it'; fidelity is proven in sacrifice.”",
                "“Levante-se e cumpra o seu dever agora mesmo; o amor a Deus se prova na fidelidade diária.”"
            ),
            iconName = "bolt"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "ENVY",
            title = LocalizedText("Envidia / Comparación", "Envy / Comparison", "Inveja / Descontentamento"),
            counterVirtue = LocalizedText("Caridad y Gratitud Fraterna", "Charity & Fraternal Joy", "Caridade e Benevolência"),
            advice = LocalizedText(
                "«Bendice secretamente a esa persona y pide a Dios que multiplique sus dones y santidad».",
                "“Silently bless that person right now and ask God to double their gifts, joy, and peace.”",
                "“Abençoe secretamente a pessoa que você inveja e agradeça a Deus pelas graças concedidas a ela.”"
            ),
            iconName = "volunteer_activism"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "PRIDE",
            title = LocalizedText("Soberbia / Vanagloria", "Pride / Vanity", "Soberba / Vaidade"),
            counterVirtue = LocalizedText("Humildad y Verdad", "Humility & Truth", "Humildade e Verdade"),
            advice = LocalizedText(
                "«¿Qué tienes que no hayas recibido de Dios? Acepta el segundo lugar y sirve a tu hermano».",
                "“What do you possess that you have not received? Take the lower place and wash the feet of your brother.”",
                "“Reconheça que tudo vem de Deus. Pratique a humildade e sirva aos outros sem buscar aplausos.”"
            ),
            iconName = "star"
        ),
        com.example.data.models.SinAvoiderOption(
            sinKey = "GREED",
            title = LocalizedText("Avaricia / Apego Material", "Greed / Materialism", "Avareza / Apego"),
            counterVirtue = LocalizedText("Generosidad y Desprendimiento", "Generosity & Detachment", "Generosidade e Partilha"),
            advice = LocalizedText(
                "«Haz un donativo o limosna escondida hoy; no acumules tesoros en la tierra donde la polilla carcome».",
                "“Give alms or a hidden generous gift today; store up eternal treasures in heaven with Christ.”",
                "“Partilhe o que você tem com quem precisa; a verdadeira riqueza está no Reino dos Céus.”"
            ),
            iconName = "account_balance_wallet"
        )
    )

    val defaultHabitsToSeed: List<com.example.data.local.SpiritualHabitEntity> = listOf(
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_morning_offering",
            titleEs = "Ofrecimiento de Obras de la Mañana",
            titleEn = "Morning Offering of Daily Works",
            titlePt = "Oferecimento do Dia ao Despertar",
            description = "Consagrar los pensamientos, palabras y obras a Dios al despertar.",
            category = "PRAYER",
            timeSuggestion = "07:00 AM",
            iconName = "wb_sunny",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_angelus",
            titleEs = "El Ángelus del Mediodía",
            titleEn = "The Midday Angelus",
            titlePt = "O Angelus do Meio-Dia",
            description = "Pausa mariana a las 12:00 PM recordando la Encarnación del Hijo de Dios.",
            category = "PRAYER",
            timeSuggestion = "12:00 PM",
            iconName = "schedule",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_holy_rosary",
            titleEs = "Santo Rosario Diario",
            titleEn = "Daily Holy Rosary",
            titlePt = "Santo Terço Diário",
            description = "Cinco misterios de contemplación de la vida de Cristo junto a la Virgen María.",
            category = "PRAYER",
            timeSuggestion = "Tarde / Noche",
            iconName = "rosary",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_daily_mass",
            titleEs = "Santa Misa o Liturgia del Día",
            titleEn = "Holy Mass or Daily Liturgy",
            titlePt = "Santa Missa ou Liturgia Diária",
            description = "Participación sacramental o lectura devota del Evangelio y lecturas de hoy.",
            category = "SACRAMENT",
            timeSuggestion = "08:00 AM / Tarde",
            iconName = "church",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_lectio_divina",
            titleEs = "Lectio divina",
            titleEn = "Lectio divina",
            titlePt = "Lectio divina",
            description = "Alimentar el alma con la Palabra Viva de Dios antes de comenzar las tareas.",
            category = "PRAYER",
            timeSuggestion = "Mañana",
            iconName = "menu_book",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_friday_fast",
            titleEs = "Ayuno o Abstinencia de los Viernes",
            titleEn = "Friday Fasting or Mortification",
            titlePt = "Jejum ou Abstinência de Sexta-feira",
            description = "Ofrecer una penitencia corporal en memoria de la Pasión salvadora de Jesús.",
            category = "FASTING",
            timeSuggestion = "Todo el día",
            iconName = "restaurant",
            isCustom = false
        ),
        com.example.data.local.SpiritualHabitEntity(
            id = "habit_night_examen",
            titleEs = "Examen de Conciencia Nocturno",
            titleEn = "Night Examination of Conscience",
            titlePt = "Exame de Consciência Noturno",
            description = "Agradecer las bendiciones, pedir perdón y rezar el Acto de Contrición.",
            category = "VIRTUE",
            timeSuggestion = "09:30 PM",
            iconName = "nights_stay",
            isCustom = false
        )
    )

    val defaultLiturgicalDaysToSeed: List<com.example.data.local.LiturgicalDayEntity> = listOf(
        com.example.data.local.LiturgicalDayEntity(
            date = "2026-10-25",
            celebrationTitle = "XXX Domingo del Tiempo Ordinario",
            seasonName = "Tiempo Ordinario",
            rank = "Solemnidad / Domingo",
            liturgicalColorHex = 0xFF2E7D32, // Verde Litúrgico
            sundayCycle = "Ciclo B",
            weekdayCycle = "Año II",
            isFastOrAbstinence = false,
            fastingRule = "Día del Señor: Precepto dominical y descanso cristiano",
            firstReadingRef = "Jeremías 31, 7-9",
            firstReadingText = "«Así dice el Señor: 'Griten de alegría por Jacob, aclamen a la primera de las naciones; hagan oír sus alabanzas y digan: ¡Salva, Señor, a tu pueblo, al resto de Israel! Miren que yo los traigo del país del norte... Guíalos entre consuelos'».",
            responsorialPsalmRef = "Salmo 125, 1-2ab. 2cd-3. 4-5. 6",
            responsorialPsalmText = "R/. El Señor ha estado grande con nosotros, y estamos alegres.\n\nCuando el Señor cambió la suerte de Sión, nos parecía soñar: la boca se nos llenaba de risas, la lengua de cantares.",
            secondReadingRef = "Hebreos 5, 1-6",
            secondReadingText = "«Todo sumo sacerdote, tomado de entre los hombres, está puesto para representar a los hombres en sus relaciones con Dios: para ofrecer dones y sacrificios por los pecados... Tú eres sacerdote para siempre, según el orden de Melquisedec».",
            gospelAcclamation = "«Aleluya, aleluya. Jesús curó a los enfermos y proclamó la Buena Nueva del Reino. Aleluya».",
            gospelRef = "Marcos 10, 46-52",
            gospelText = "«En aquel tiempo, al salir Jesús de Jericó con sus discípulos y bastante gente, el hijo de Timeo, Bartimeo, un mendigo ciego, estaba sentado junto al camino. Al enterarse de que era Jesús Nazareno, empezó a gritar: 'Hijo de David, Jesús, ten compasión de mí'... Jesús le dijo: '¿Qué quieres que haga por ti?'. El ciego le contestó: 'Rabbuní, que recobre la vista'. Jesús le dijo: 'Anda, tu fe te ha salvado'. Y al momento recobró la vista y lo seguía por el camino».",
            missalMeditation = "El grito de Bartimeo es la oración de toda alma que reconoce su ceguera espiritual y corre hacia Cristo. El Señor nos pregunta hoy: '¿Qué quieres que haga por ti?'. Respondamos con fe: '¡Señor, que vea!'.",
            saintOfFeast = "Santos Crispín y Crispiniano, mártires"
        ),
        com.example.data.local.LiturgicalDayEntity(
            date = "2026-11-01",
            celebrationTitle = "Solemnidad de Todos los Santos",
            seasonName = "Tiempo Ordinario",
            rank = "Solemnidad Universal",
            liturgicalColorHex = 0xFFFFFFFF, // Blanco Litúrgico
            sundayCycle = "Ciclo B",
            weekdayCycle = "Año II",
            isFastOrAbstinence = false,
            fastingRule = "Solemnidad de precepto: Comunión y alegría celestial",
            firstReadingRef = "Apocalipsis 7, 2-4. 9-14",
            firstReadingText = "«Vi una multitud inmensa, que nadie podía contar, de toda nación, razas, pueblos y lenguas, de pie delante del trono y del Cordero, vestidos con vestiduras blancas y con palmas en las manos... Éstos son los que vienen de la gran tribulación y han lavado sus vestiduras en la sangre del Cordero».",
            responsorialPsalmRef = "Salmo 23, 1-2. 3-4ab. 5-6",
            responsorialPsalmText = "R/. Éste es el grupo que viene al encuentro del Señor.\n\nDel Señor es la tierra y cuanto la llena, el orbe y todos sus habitantes: Él la fundó sobre los mares, Él la afianzó sobre los ríos.",
            secondReadingRef = "1 Juan 3, 1-3",
            secondReadingText = "«Miren qué amor nos ha tenido el Padre para llamarnos hijos de Dios, pues ¡lo somos!... Cuando se manifieste seremos semejantes a Él, porque lo veremos tal cual es».",
            gospelAcclamation = "«Aleluya. Vengan a mí los cansados y agobiados, y yo los aliviaré, dice el Señor. Aleluya».",
            gospelRef = "Mateo 5, 1-12a",
            gospelText = "«Al ver a la multitud, subió al monte y comenzó a enseñarles: 'Bienaventurados los pobres en el espíritu, porque de ellos es el Reino de los Cielos. Bienaventurados los mansos... Bienaventurados los limpios de corazón, porque ellos verán a Dios... Alégrense y regocíjense, porque su recompensa será grande en el cielo'».",
            missalMeditation = "La santidad no es un privilegio de pocos, sino la vocación universal de todo bautizado. Los santos son nuestros hermanos mayores que interceden por nosotros ante el trono de Dios.",
            saintOfFeast = "Todos los Santos del Cielo"
        )
    )
}
