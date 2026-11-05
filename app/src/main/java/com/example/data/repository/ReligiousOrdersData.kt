package com.example.data.repository

import com.example.data.models.ChurchOffice
import com.example.data.models.LocalizedText
import com.example.data.models.ReligiousOrder

/**
 * Religious orders compendium (men + women) and Church ranks/offices guide,
 * in ES/EN/PT. Orders ordered men first, then women; offices in hierarchy order.
 */
object ReligiousOrdersData {
    val orders: List<ReligiousOrder> = listOf(
    ReligiousOrder(
        id = "franciscanos-ofm",
        name = LocalizedText("Orden de los Frailes Menores", "Order of Friars Minor", "Ordem dos Frades Menores"),
        abbreviation = "OFM",
        foundationYear = "1209",
        founder = LocalizedText("San Francisco de Asís", "St. Francis of Assisi", "São Francisco de Assis"),
        description = LocalizedText(
            "Son los frailes de la Orden fundada por San Francisco de Asís en 1209 para vivir el Evangelio en pobreza, fraternidad y minoridad. Su carisma es seguir a Cristo pobre y crucificado, predicando la paz, la reconciliación y la creación como casa común. Se distinguen por el voto de pobreza, la vida comunitaria sencilla y la evangelización itinerante. Hoy trabajan en parroquias, misiones, educación, justicia social y custodia de los lugares santos en Tierra Santa.",
            "They are the friars of the Order founded by Saint Francis of Assisi in 1209 to live the Gospel in poverty, fraternity and minor status. Their charism is to follow poor and crucified Christ, preaching peace, reconciliation and creation as a common home. They are marked by the vow of poverty, simple community life and itinerant evangelisation. Today they work in parishes, missions, education, social justice and the custody of the Holy Places in the Holy Land.",
            "São os frades da Ordem fundada por São Francisco de Assis em 1209 para viver o Evangelho na pobreza, na fraternidade e na menoridade. Seu carisma é seguir Cristo pobre e crucificado, pregando a paz, a reconciliação e a criação como casa comum. Caracterizam-se pelo voto de pobreza, a vida comunitária simples e a evangelização itinerante. Hoje atuam em paróquias, missões, educação, justiça social e custódia dos lugares santos na Terra Santa."
        ),
        charism = LocalizedText(
            "Pobreza, fraternidad y evangelización itinerante al modo de Francisco.",
            "Poverty, fraternity and itinerant evangelisation in the manner of Francis.",
            "Pobreza, fraternidade e evangelização itinerante à maneira de Francisco."
        )
    ),
    ReligiousOrder(
        id = "jesuitas-sj",
        name = LocalizedText("Compañía de Jesús", "Society of Jesus", "Companhia de Jesus"),
        abbreviation = "SJ",
        foundationYear = "1540",
        founder = LocalizedText("San Ignacio de Loyola", "St. Ignatius of Loyola", "Santo Inácio de Loyola"),
        description = LocalizedText(
            "Son sacerdotes y hermanos fundados por San Ignacio de Loyola, aprobado como orden en 1540 junto a sus compañeros de París. Su carisma es la espiritualidad de los Ejercicios Espirituales, el magis y la disponibilidad apostólica para buscar y hallar a Dios en todas las cosas. Se distinguen por el discernimiento, la obediencia al Papa en las misiones y el diálogo con la cultura. Hoy sirven en universidades, colegios, parroquias, centros de espiritualidad y misiones en todo el mundo.",
            "They are priests and brothers founded by Saint Ignatius of Loyola, approved as an order in 1540 together with his companions from Paris. Their charism is the spirituality of the Spiritual Exercises, the magis and apostolic availability to find God in all things. They are marked by discernment, obedience to the Pope in missions and dialogue with culture. Today they serve in universities, schools, parishes, spirituality centres and missions throughout the world.",
            "São sacerdotes e irmãos fundados por Santo Inácio de Loyola, aprovados como ordem em 1540 juntamente com seus companheiros de Paris. Seu carisma é a espiritualidade dos Exercícios Espirituais, o magis e a disponibilidade apostólica para buscar e encontrar Deus em todas as coisas. Caracterizam-se pelo discernimento, a obediência ao Papa nas missões e o diálogo com a cultura. Hoje servem em universidades, escolas, paróquias, centros de espiritualidade e missões em todo o mundo."
        ),
        charism = LocalizedText(
            "Ejercicios Espirituales, discernimiento y búsqueda de Dios en todas las cosas.",
            "Spiritual Exercises, discernment and finding God in all things.",
            "Exercícios Espirituais, discernimento e busca de Deus em todas as coisas."
        )
    ),
    ReligiousOrder(
        id = "opus-dei",
        name = LocalizedText("Opus Dei", "Opus Dei", "Opus Dei"),
        abbreviation = "—",
        foundationYear = "1928",
        founder = LocalizedText("San Josemaría Escrivá", "St. Josemaría Escrivá", "São Josemaría Escrivá"),
        description = LocalizedText(
            "Es una prelatura personal de la Iglesia Católica fundada por San Josemaría Escrivá en 1928 para promover la santidad en medio del trabajo y la vida ordinaria. Su mensaje es que toda actividad honesta puede ser camino de santidad y de servicio a los demás. Se distinguen por la santificación del trabajo, la vida interior, la libertad y la responsabilidad personal. Hoy sus fieles laicos y sacerdotes impulsan formación cristiana, universidades, escuelas, obras sociales y apostolado en la vida profesional.",
            "It is a personal prelature of the Catholic Church founded by Saint Josemaría Escrivá in 1928 to promote holiness amid work and ordinary life. Its message is that all honest activity can be a path to holiness and service to others. It is marked by the sanctification of work, interior life, freedom and personal responsibility. Today its lay and priest members promote Christian formation, universities, schools, charitable works and apostolate in professional life.",
            "É uma prelatura pessoal da Igreja Católica fundada por São Josemaría Escrivá em 1928 para promover a santidade no meio do trabalho e da vida ordinária. Sua mensagem é que toda atividade honesta pode ser caminho de santidade e serviço aos outros. Caracteriza-se pela santificação do trabalho, a vida interior, a liberdade e a responsabilidade pessoal. Hoje seus fiéis leigos e sacerdotes promovem formação cristã, universidades, escolas, obras sociais e apostolado na vida profissional."
        ),
        charism = LocalizedText(
            "Santidad en el trabajo y en la vida ordinaria.",
            "Holiness in work and ordinary life.",
            "Santidade no trabalho e na vida ordinária."
        )
    ),
    ReligiousOrder(
        id = "dominicos-op",
        name = LocalizedText("Orden de Predicadores", "Order of Preachers", "Ordem dos Pregadores"),
        abbreviation = "OP",
        foundationYear = "1216",
        founder = LocalizedText("Santo Domingo de Guzmán", "St. Dominic de Guzmán", "São Domingos de Gusmão"),
        description = LocalizedText(
            "Son los frailes y hermanas de la orden fundada por Santo Domingo de Guzmán en 1216 para predicar la Verdad del Evangelio contra los errores de su tiempo. Su carisma es la predicación, el estudio sagrado y la vida de oración contemplativa. Se distinguen por la pobreza, la vida común y la itinerancia apostólica. Hoy predican y enseñan en parroquias, universidades, misiones, medios de comunicación y en la formación del clero y los laicos.",
            "They are the friars and sisters of the order founded by Saint Dominic de Guzmán in 1216 to preach the Truth of the Gospel against the errors of his time. Their charism is preaching, sacred study and contemplative prayer. They are marked by poverty, common life and apostolic itinerancy. Today they preach and teach in parishes, universities, missions, media and the formation of clergy and laity.",
            "São os frades e irmãs da ordem fundada por São Domingos de Gusmão em 1216 para pregar a Verdade do Evangelho contra os erros de seu tempo. Seu carisma é a pregação, o estudo sagrado e a vida de oração contemplativa. Caracterizam-se pela pobreza, a vida comum e a itinerância apostólica. Hoje pregam e ensinam em paróquias, universidades, missões, meios de comunicação e na formação do clero e dos leigos."
        ),
        charism = LocalizedText(
            "Predicación, estudio y contemplación para dar el fruto de la oración.",
            "Preaching, study and contemplation to share the fruit of prayer.",
            "Pregação, estudo e contemplação para transmitir o fruto da oração."
        )
    ),
    ReligiousOrder(
        id = "benedictinos-osb",
        name = LocalizedText("Orden de San Benito", "Order of Saint Benedict", "Ordem de São Bento"),
        abbreviation = "OSB",
        foundationYear = "529",
        founder = LocalizedText("San Benito de Nursia", "St. Benedict of Nursia", "São Bento de Núrsia"),
        description = LocalizedText(
            "Son monjes y monjas de la tradición de San Benito de Nursia, que escribió la Regla en el siglo VI en Montecasino. Su carisma es la vida monástica de oración, trabajo, lectura y silencio bajo la Regla. Se distinguen por la humildad, la obediencia, la estabilidad y la liturgia de las Horas. Hoy mantienen abadías y prioratos con escuelas, hospedería, vida parroquial y renovación de la cultura y la oración en la Iglesia.",
            "They are monks and nuns in the tradition of Saint Benedict of Nursia, who wrote the Rule in the sixth century at Monte Cassino. Their charism is monastic life of prayer, work, reading and silence under the Rule. They are marked by humility, obedience, stability and the Liturgy of the Hours. Today they sustain abbeys and priories with schools, hospitality, parish life and the renewal of culture and prayer in the Church.",
            "São monges e monjas da tradição de São Bento de Núrsia, que escreveu a Regra no século VI em Montecassino. Seu carisma é a vida monástica de oração, trabalho, leitura e silêncio sob a Regra. Caracterizam-se pela humildade, a obediência, a estabilidade e a Liturgia das Horas. Hoje mantêm abadias e priorados com escolas, hospitalidade, vida paroquial e renovação da cultura e da oração na Igreja."
        ),
        charism = LocalizedText(
            "Ora et labora: oración, trabajo y estabilidad monástica.",
            "Ora et labora: prayer, work and monastic stability.",
            "Ora et labora: oração, trabalho e estabilidade monástica."
        )
    ),
    ReligiousOrder(
        id = "carmelitas-oc",
        name = LocalizedText("Orden de Nuestra Señora del Monte Carmelo", "Order of Our Lady of Mount Carmel", "Ordem de Nossa Senhora do Carmo"),
        abbreviation = "OC",
        foundationYear = "1247",
        founder = LocalizedText("Ermitaños del Monte Carmelo, formalizados bajo San Alberto de Jerusalén", "Hermits of Mount Carmel, formalised under St. Albert of Jerusalem", "Eremitas do Monte Carmelo, formalizados sob Santo Alberto de Jerusalém"),
        description = LocalizedText(
            "Son frailes y monjas de la orden nacida de los ermitaños del Monte Carmelo y formalizada en 1247 bajo la Regla de San Alberto de Jerusalén. Su carisma es la oración, la vida fraterna y la devoción a la Virgen del Carmen como modelo de escucha. Se distinguen por la clausura, la contemplación y la preparación para la misión. Hoy mantienen comunidades de vida contemplativa, parroquias, apostolado del escapulario y acompañamiento espiritual.",
            "They are friars and nuns of the order born from the hermits of Mount Carmel and formalised in 1247 under the Rule of Saint Albert of Jerusalem. Their charism is prayer, fraternal life and devotion to Our Lady of Mount Carmel as a model of listening. They are marked by enclosure, contemplation and preparation for mission. Today they sustain contemplative communities, parishes, the scapular apostolate and spiritual accompaniment.",
            "São frades e monjas da ordem nascida dos eremitas do Monte Carmelo e formalizada em 1247 sob a Regra de Santo Alberto de Jerusalém. Seu carisma é a oração, a vida fraterna e a devoção a Nossa Senhora do Carmo como modelo de escuta. Caracterizam-se pela clausura, a contemplação e a preparação para a missão. Hoje mantêm comunidades de vida contemplativa, paróquias, apostolado do escapulário e acompanhamento espiritual."
        ),
        charism = LocalizedText(
            "Oración, fraternidad y devoción mariana del Carmen.",
            "Prayer, fraternity and Marian devotion to Our Lady of Carmel.",
            "Oração, fraternidade e devoção mariana a Nossa Senhora do Carmo."
        )
    ),
    ReligiousOrder(
        id = "agustinos-osa",
        name = LocalizedText("Orden de San Agustín", "Order of Saint Augustine", "Ordem de Santo Agostinho"),
        abbreviation = "OSA",
        foundationYear = "1244",
        founder = LocalizedText("San Agustín de Hipona", "St. Augustine of Hippo", "Santo Agostinho de Hipona"),
        description = LocalizedText(
            "Son frailes y monjas agustinos unidos en 1244 a partir de comunidades que seguían la Regla de San Agustín, obispo de Hipona del siglo V. Su carisma es la vida en comunidad, la búsqueda de la Verdad y el corazón inquieto que descansa en Dios. Se distinguen por la fraternidad, el estudio teológico y la predicación. Hoy sirven en parroquias, universidades, misiones, educación y acción social en muchos países.",
            "They are Augustinian friars and nuns united in 1244 from communities that followed the Rule of Saint Augustine, fifth-century Bishop of Hippo. Their charism is life in community, the search for Truth and the restless heart that rests in God. They are marked by fraternity, theological study and preaching. Today they serve in parishes, universities, missions, education and social action in many countries.",
            "São frades e monjas agostinianos unidos em 1244 a partir de comunidades que seguiam a Regra de Santo Agostinho, bispo de Hipona do século V. Seu carisma é a vida em comunidade, a busca da Verdade e o coração inquieto que descansa em Deus. Caracterizam-se pela fraternidade, o estudo teológico e a pregação. Hoje servem em paróquias, universidades, missões, educação e ação social em muitos países."
        ),
        charism = LocalizedText(
            "Vida en comunidad, búsqueda de la Verdad y corazón en Dios.",
            "Life in community, search for Truth and a heart resting in God.",
            "Vida em comunidade, busca da Verdade e coração em Deus."
        )
    ),
    ReligiousOrder(
        id = "trapenses-ocso",
        name = LocalizedText("Orden Cisterciense de la Estricta Observancia", "Order of Cistercians of the Strict Observance", "Ordem Cisterciense da Estrita Observância"),
        abbreviation = "OCSO",
        foundationYear = "1664",
        founder = LocalizedText("Armand Jean de Rancé, abad de La Trappe", "Armand Jean de Rancé, Abbot of La Trappe", "Armand Jean de Rancé, abade de La Trappe"),
        description = LocalizedText(
            "Son monjes y monjas de la reforma trapense surgida en la abadía de La Trappe en 1664 bajo el abad Armand de Rancé, y constituida como orden propia en 1892. Su carisma es la vida contemplativa, el silencio, la clausura y el trabajo manual al modo cisterciense. Se distinguen por la austera observancia, la oración litúrgica y la vida de penitencia. Hoy mantienen monasterios con huertos, producción artesanal y acogida a quienes buscan silencio y conversión.",
            "They are monks and nuns of the Trappist reform that arose at the Abbey of La Trappe in 1664 under Abbot Armand de Rancé, and became a distinct order in 1892. Their charism is contemplative life, silence, enclosure and manual work in the Cistercian manner. They are marked by austere observance, liturgical prayer and a life of penance. Today they sustain monasteries with gardens, craft production and welcome for those seeking silence and conversion.",
            "São monges e monjas da reforma trapista surgida na abadia de La Trappe em 1664 sob o abade Armand de Rancé, e constituída como ordem própria em 1892. Seu carisma é a vida contemplativa, o silêncio, a clausura e o trabalho manual ao modo cisterciense. Caracterizam-se pela austeridade, a oração litúrgica e a vida de penitência. Hoje mantêm mosteiros com hortas, produção artesanal e acolhimento a quem busca silêncio e conversão."
        ),
        charism = LocalizedText(
            "Silencio, clausura, trabajo y austeridad contemplativa.",
            "Silence, enclosure, work and contemplative austerity.",
            "Silêncio, clausura, trabalho e austeridade contemplativa."
        )
    ),
    ReligiousOrder(
        id = "salesianos-sdb",
        name = LocalizedText("Sociedad Salesiana de San Juan Bosco", "Salesian Society of Saint John Bosco", "Sociedade Salesiana de São João Bosco"),
        abbreviation = "SDB",
        foundationYear = "1859",
        founder = LocalizedText("San Juan Bosco", "St. John Bosco", "São João Bosco"),
        description = LocalizedText(
            "Son sacerdotes, hermanos y laicos salesianos fundados por San Juan Bosco en 1859 para la educación y evangelización de los jóvenes. Su carisma es el sistema preventivo, la alegría, la razón, la religión y la amabilidad en favor de los más necesitados. Se distinguen por la opción preferencial por los jóvenes, la familia salesiana y la devoción a María Auxiliadora. Hoy trabajan en escuelas, oratorios, obras sociales, misiones y educación de niños y adolescentes.",
            "They are Salesian priests, brothers and lay associates founded by Saint John Bosco in 1859 for the education and evangelisation of young people. Their charism is the preventive system, joy, reason, religion and kindness in favour of those most in need. They are marked by a preferential option for the young, the Salesian Family and devotion to Mary Help of Christians. Today they work in schools, oratories, social works, missions and the education of children and adolescents.",
            "São sacerdotes, irmãos e leigos salesianos fundados por São João Bosco em 1859 para a educação e evangelização dos jovens. Seu carisma é o sistema preventivo, a alegria, a razão, a religião e a bondade em favor dos mais necessitados. Caracterizam-se pela opção preferencial pelos jovens, a Família Salesiana e a devoção a Maria Auxiliadora. Hoje atuam em escolas, oratórios, obras sociais, missões e educação de crianças e adolescentes."
        ),
        charism = LocalizedText(
            "Sistema preventivo, alegría y amor preferencial por los jóvenes.",
            "Preventive system, joy and preferential love for the young.",
            "Sistema preventivo, alegria e amor preferencial pelos jovens."
        )
    ),
    ReligiousOrder(
        id = "redentoristas-cssr",
        name = LocalizedText("Congregación del Santísimo Redentor", "Congregation of the Most Holy Redeemer", "Congregação do Santíssimo Redentor"),
        abbreviation = "CSsR",
        foundationYear = "1732",
        founder = LocalizedText("San Alfonso María de Ligorio", "St. Alphonsus Liguori", "Santo Afonso Maria de Ligório"),
        description = LocalizedText(
            "Son misioneros redentoristas fundados por San Alfonso María de Ligorio en 1732 para anunciar la copiosa redención de Cristo a los más abandonados. Su carisma es la moral pastoral misericordiosa, la evangelización popular y la devoción a Nuestra Señora del Perpetuo Socorro. Se distinguen por la predicación de misiones, la confesión y la teología moral accesible. Hoy animan parroquias, santuarios, misiones populares y la formación de predicadores del Evangelio.",
            "They are Redemptorist missionaries founded by Saint Alphonsus Liguori in 1732 to proclaim the abundant redemption of Christ to the most abandoned. Their charism is merciful pastoral moral theology, popular evangelisation and devotion to Our Lady of Perpetual Help. They are marked by mission preaching, confession and accessible moral theology. Today they sustain parishes, shrines, popular missions and the formation of preachers of the Gospel.",
            "São missionários redentoristas fundados por Santo Afonso Maria de Ligório em 1732 para anunciar a copiosa redenção de Cristo aos mais abandonados. Seu carisma é a moral pastoral misericordiosa, a evangelização popular e a devoção a Nossa Senhora do Perpétuo Socorro. Caracterizam-se pela pregação de missões, a confissão e a teologia moral acessível. Hoje animam paróquias, santuários, missões populares e a formação de pregadores do Evangelho."
        ),
        charism = LocalizedText(
            "Anunciar la redención copiosa a los abandonados con misericordia.",
            "Proclaiming abundant redemption to the abandoned with mercy.",
            "Anunciar a redenção copiosa aos abandonados com misericórdia."
        )
    ),
    ReligiousOrder(
        id = "capuchinos-ofmcap",
        name = LocalizedText("Orden de los Frailes Menores Capuchinos", "Order of Friars Minor Capuchin", "Ordem dos Frades Menores Capuchinhos"),
        abbreviation = "OFMCap",
        foundationYear = "1525",
        founder = LocalizedText("Venerable Mateo de Bascio", "Ven. Matteo di Bascio", "Ven. Mateus de Bascio"),
        description = LocalizedText(
            "Son frailes capuchinos surgidos en 1525 como reforma de la vida franciscana, iniciada por Mateo de Bascio y reconocidos como orden capuchina en 1619. Su carisma es la pobreza radical, la fraternidad menor y la predicación popular sencilla. Se distinguen por el hábito con capucha, la barba, el descalzamiento y la vida austera. Hoy sirven en parroquias, misiones, hospitales, formación y ministerio itinerante entre los pobres.",
            "They are Capuchin friars who arose in 1525 as a reform of Franciscan life, begun by Matteo di Bascio and recognised as a Capuchin order in 1619. Their charism is radical poverty, minor fraternity and simple popular preaching. They are marked by the hooded habit, the beard, the barefoot life and austerity. Today they serve in parishes, missions, hospitals, formation and itinerant ministry among the poor.",
            "São frades capuchinhos surgidos em 1525 como reforma da vida franciscana, iniciada por Mateus de Bascio e reconhecidos como ordem capuchinha em 1619. Seu carisma é a pobreza radical, a fraternidade menor e a pregação popular simples. Caracterizam-se pelo hábito com capuz, a barba, a vida descalça e a austeridade. Hoje servem em paróquias, missões, hospitais, formação e ministério itinerante entre os pobres."
        ),
        charism = LocalizedText(
            "Pobreza radical y predicación popular al modo de Francisco.",
            "Radical poverty and popular preaching in the manner of Francis.",
            "Pobreza radical e pregação popular à maneira de Francisco."
        )
    ),
    ReligiousOrder(
        id = "cistercienses-ocist",
        name = LocalizedText("Orden Cisterciense", "Order of Cistercians", "Ordem Cisterciense"),
        abbreviation = "OCist",
        foundationYear = "1098",
        founder = LocalizedText("San Roberto de Molesme", "St. Robert of Molesme", "São Roberto de Molesme"),
        description = LocalizedText(
            "Son monjes y monjas cistercienses fundados por San Roberto de Molesme en 1098 en Císter, Francia, como retorno a la vida monástica benedictina. Su carisma es la oración litúrgica, la vida común, el trabajo y el silencio en abadías. Se distinguen por la austeridad, la sobriedad de la arquitectura monástica y la hospitalidad. Hoy mantienen monasterios dedicados a la oración, la liturgia, la agricultura y la acogida de huéspedes.",
            "They are Cistercian monks and nuns founded by Saint Robert of Molesme in 1098 at Cîteaux, France, as a return to Benedictine monastic life. Their charism is liturgical prayer, common life, work and silence in abbeys. They are marked by austerity, the sobriety of monastic architecture and hospitality. Today they sustain monasteries devoted to prayer, liturgy, agriculture and the welcome of guests.",
            "São monges e monjas cistercienses fundados por São Roberto de Molesme em 1098 em Cister, França, como retorno à vida monástica beneditina. Seu carisma é a oração litúrgica, a vida comum, o trabalho e o silêncio nas abadias. Caracterizam-se pela austeridade, a sobriedade da arquitetura monástica e a hospitalidade. Hoje mantêm mosteiros dedicados à oração, à liturgia, à agricultura e ao acolhimento de hóspedes."
        ),
        charism = LocalizedText(
            "Liturgia, trabajo, silencio y retorno a la vida benedictina.",
            "Liturgy, work, silence and a return to Benedictine life.",
            "Liturgia, trabalho, silêncio e retorno à vida beneditina."
        )
    ),
    ReligiousOrder(
        id = "cartujos-ocart",
        name = LocalizedText("Orden de la Cartuja", "Order of Carthusians", "Ordem da Cartuxa"),
        abbreviation = "OCart",
        foundationYear = "1084",
        founder = LocalizedText("San Bruno de Colonia", "St. Bruno of Cologne", "São Bruno de Colônia"),
        description = LocalizedText(
            "Son monjes y monjas cartujos fundados por San Bruno de Colonia en 1084 en el desierto de la Cartuja, cerca de Grenoble. Su carisma es la vida eremítica y cenobítica de soledad, silencio, silencio y oración continua. Se distinguen por celdas individuales, clausura estricta y la vida de transcripción y contemplación. Hoy permanecen en cartujas del mundo dedicadas a la oración por la Iglesia y a la búsqueda de Dios en silencio.",
            "They are Carthusian monks and nuns founded by Saint Bruno of Cologne in 1084 in the desert of La Chartreuse, near Grenoble. Their charism is eremitical and cenobitical life of solitude, silence and continual prayer. They are marked by individual cells, strict enclosure and a life of copying and contemplation. Today they remain in charterhouses around the world dedicated to prayer for the Church and the search for God in silence.",
            "São monges e monjas cartusianos fundados por São Bruno de Colônia em 1084 no deserto da Grande Chartreuse, perto de Grenoble. Seu carisma é a vida eremítica e cenobítica de solidão, silêncio e oração contínua. Caracterizam-se por celas individuais, clausura estrita e vida de cópia e contemplação. Hoje permanecem em cartuxas pelo mundo dedicadas à oração pela Igreja e à busca de Deus no silêncio."
        ),
        charism = LocalizedText(
            "Soledad, silencio, clausura y oración continua.",
            "Solitude, silence, enclosure and continual prayer.",
            "Solidão, silêncio, clausura e oração contínua."
        )
    ),
    ReligiousOrder(
        id = "pasionistas-cp",
        name = LocalizedText("Congregación de la Pasión de Jesucristo", "Congregation of the Passion of Jesus Christ", "Congregação da Paixão de Jesus Cristo"),
        abbreviation = "CP",
        foundationYear = "1737",
        founder = LocalizedText("San Pablo de la Cruz", "St. Paul of the Cross", "São Paulo da Cruz"),
        description = LocalizedText(
            "Son misioneros pasionistas fundados por San Pablo de la Cruz en 1737 para anunciar la Pasión de Cristo como anuncio del amor de Dios. Su carisma es la memoria de la Pasión, la oración y la predicación de ejercicios y misiones. Se distinguen por el signo de la Pasión en el hábito, la vida de penitencia y la compasión por los que sufren. Hoy trabajan en parroquias, santuarios, retiros, apostolado con enfermos y acompañamiento del duelo.",
            "They are Passionist missionaries founded by Saint Paul of the Cross in 1737 to proclaim the Passion of Christ as the announcement of God's love. Their charism is remembrance of the Passion, prayer and the preaching of retreats and missions. They are marked by the sign of the Passion on the habit, a penitential life and compassion for those who suffer. Today they work in parishes, shrines, retreats, ministry to the sick and accompaniment of the grieving.",
            "São missionários passionistas fundados por São Paulo da Cruz em 1737 para anunciar a Paixão de Cristo como anúncio do amor de Deus. Seu carisma é a memória da Paixão, a oração e a pregação de retiros e missões. Caracterizam-se pelo sinal da Paixão no hábito, a vida de penitência e a compaixão pelos que sofrem. Hoje atuam em paróquias, santuários, retiros, apostolado com doentes e acompanhamento do luto."
        ),
        charism = LocalizedText(
            "Memoria y predicación de la Pasión de Cristo.",
            "Remembrance and preaching of the Passion of Christ.",
            "Memória e pregação da Paixão de Cristo."
        )
    ),
    ReligiousOrder(
        id = "camilianos-mi",
        name = LocalizedText("Religiosos Camilos", "Camillian Religious", "Religiosos Camilianos"),
        abbreviation = "MI",
        foundationYear = "1582",
        founder = LocalizedText("San Camilo de Lelis", "St. Camillus de Lellis", "São Camilo de Lellis"),
        description = LocalizedText(
            "Son religiosos camilianos, Ministros de los Enfermos, fundados por San Camilo de Lelis en 1582 para servir a los enfermos con corazón de madre. Su carisma es la atención corporal y espiritual a los enfermos, la pastoral de la salud y el acompañamiento en el sufrimiento. Se distinguen por el signo de la cruz roja y la entrega sacrificada. Hoy sirven en hospitales, hogares, cuidados paliativos, misión sanitaria y formación de agentes de pastoral de la salud.",
            "They are Camillian religious, Ministers of the Sick, founded by Saint Camillus de Lellis in 1582 to serve the sick with a mother's heart. Their charism is bodily and spiritual care of the sick, health pastoral care and accompaniment in suffering. They are marked by the red cross and selfless dedication. Today they serve in hospitals, homes, palliative care, health missions and the formation of health pastoral workers.",
            "São religiosos camilianos, Ministros dos Enfermos, fundados por São Camilo de Lellis em 1582 para servir aos doentes com coração de mãe. Seu carisma é o cuidado corporal e espiritual dos doentes, a pastoral da saúde e o acompanhamento no sofrimento. Caracterizam-se pelo sinal da cruz vermelha e a dedicação abnegada. Hoje servem em hospitais, lares, cuidados paliativos, missões de saúde e formação de agentes da pastoral da saúde."
        ),
        charism = LocalizedText(
            "Servir a los enfermos con corazón de madre.",
            "Serving the sick with a mother's heart.",
            "Servir aos doentes com coração de mãe."
        )
    ),
    ReligiousOrder(
        id = "maristas-fms",
        name = LocalizedText("Hermanitos de María", "Little Brothers of Mary", "Irmãos Maristas"),
        abbreviation = "FMS",
        foundationYear = "1817",
        founder = LocalizedText("San Marcelino Champagnat", "St. Marcellin Champagnat", "São Marcelino Champagnat"),
        description = LocalizedText(
            "Son hermanos maristas fundados por San Marcelino Champagnat en 1817 para educar y evangelizar a niños y jóvenes, especialmente a los más pobres. Su carisma es María como modelo de fe sencilla, la fraternidad y la educación integral. Se distinguen por la sencillez, la presencia entre los jóvenes y la dedicación apostólica. Hoy mantienen escuelas, colegios, universidades, hogares y obras sociales en muchos países.",
            "They are Marist Brothers founded by Saint Marcellin Champagnat in 1817 to educate and evangelise children and young people, especially the poorest. Their charism is Mary as a model of simple faith, fraternity and integral education. They are marked by simplicity, presence among the young and apostolic dedication. Today they sustain schools, colleges, universities, homes and charitable works in many countries.",
            "São irmãos maristas fundados por São Marcelino Champagnat em 1817 para educar e evangelizar crianças e jovens, especialmente os mais pobres. Seu carisma é Maria como modelo de fé simples, a fraternidade e a educação integral. Caracterizam-se pela simplicidade, a presença entre os jovens e a dedicação apostólica. Hoje mantêm escolas, colégios, universidades, lares e obras sociais em muitos países."
        ),
        charism = LocalizedText(
            "Educación mariana, fraternidad y presencia entre los jóvenes.",
            "Marian education, fraternity and presence among the young.",
            "Educação mariana, fraternidade e presença junto aos jovens."
        )
    ),
    ReligiousOrder(
        id = "oblato-maria-inmaculada-omi",
        name = LocalizedText("Misioneros Oblatos de María Inmaculada", "Missionary Oblates of Mary Immaculate", "Missionários Oblatos de Maria Imaculada"),
        abbreviation = "OMI",
        foundationYear = "1816",
        founder = LocalizedText("San Eugenio de Mazenod", "St. Eugène de Mazenod", "Santo Eugênio de Mazenod"),
        description = LocalizedText(
            "Son misioneros oblatos fundados por San Eugenio de Mazenod en 1816 como Misioneros de la Provenza, aprobados como Oblatos en 1826. Su carisma es la evangelización de los pobres abandonados, la predicación popular y la devoción a María Inmaculada. Se distinguen por la entrega misionera, la vida en comunidad y la cercanía a los más pobres. Hoy trabajan en misiones, parroquias populares, justicia social y evangelización en las periferias.",
            "They are Missionary Oblates founded by Saint Eugène de Mazenod in 1816 as the Missionaries of Provence, approved as Oblates in 1826. Their charism is evangelisation of the abandoned poor, popular preaching and devotion to Mary Immaculate. They are marked by missionary dedication, community life and closeness to the poorest. Today they work in missions, popular parishes, social justice and evangelisation on the peripheries.",
            "São missionários oblatos fundados por Santo Eugênio de Mazenod em 1816 como Missionários da Provença, aprovados como Oblatos em 1826. Seu carisma é a evangelização dos pobres abandonados, a pregação popular e a devoção a Maria Imaculada. Caracterizam-se pela entrega missionária, a vida em comunidade e a proximidade dos mais pobres. Hoje atuam em missões, paróquias populares, justiça social e evangelização nas periferias."
        ),
        charism = LocalizedText(
            "Evangelizar a los pobres abandonados junto a María Inmaculada.",
            "Evangelising the abandoned poor with Mary Immaculate.",
            "Evangelizar os pobres abandonados com Maria Imaculada."
        )
    ),
    ReligiousOrder(
        id = "verbitas-svd",
        name = LocalizedText("Sociedad del Verbo Divino", "Society of the Divine Word", "Sociedade do Verbo Divino"),
        abbreviation = "SVD",
        foundationYear = "1875",
        founder = LocalizedText("San Arnoldo Janssen", "St. Arnold Janssen", "Santo Arnoldo Janssen"),
        description = LocalizedText(
            "Son misioneros verbitas fundados por San Arnoldo Janssen en 1875 en Steyl, Países Bajos, para la misión universal de la Palabra de Dios. Su carisma es la animación bíblica, el diálogo intercultural y la misión ad gentes. Se distinguen por la cercanía a las culturas, el aprendizaje de lenguas y la comunicación del Evangelio. Hoy sirven en misiones, parroquias, educación, medios bíblicos y diálogo interreligioso en los cinco continentes.",
            "They are Divine Word Missionaries founded by Saint Arnold Janssen in 1875 at Steyl, Netherlands, for the universal mission of the Word of God. Their charism is biblical animation, intercultural dialogue and mission ad gentes. They are marked by closeness to cultures, learning languages and communicating the Gospel. Today they serve in missions, parishes, education, biblical media and interreligious dialogue on five continents.",
            "São missionários verbitas fundados por Santo Arnoldo Janssen em 1875 em Steyl, Países Baixos, para a missão universal da Palavra de Deus. Seu carisma é a animação bíblica, o diálogo intercultural e a missão ad gentes. Caracterizam-se pela proximidade às culturas, a aprendizagem de línguas e a comunicação do Evangelho. Hoje servem em missões, paróquias, educação, meios bíblicos e diálogo inter-religioso nos cinco continentes."
        ),
        charism = LocalizedText(
            "Palabra de Dios, diálogo intercultural y misión ad gentes.",
            "Word of God, intercultural dialogue and mission ad gentes.",
            "Palavra de Deus, diálogo intercultural e missão ad gentes."
        )
    ),
    ReligiousOrder(
        id = "claretianos-cmf",
        name = LocalizedText("Hijos del Corazón Inmaculado de la Bienaventurada Virgen María", "Missionary Sons of the Immaculate Heart of Mary", "Missionários Claretianos"),
        abbreviation = "CMF",
        foundationYear = "1849",
        founder = LocalizedText("San Antonio María Claret", "St. Anthony Mary Claret", "Santo Antônio Maria Claret"),
        description = LocalizedText(
            "Son misioneros claretianos fundados por San Antonio María Claret en 1849 en España para evangelizar con nuevos medios y arder en amor de Cristo. Su carisma es la misión, la predicación, la catequesis, la prensa católica y la renovación eclesial. Se distinguen por el celo apostólico, la pobreza y la unión con María. Hoy trabajan en parroquias, misiones, editoriales, medios de comunicación, educación y formación de misioneros.",
            "They are Claretian missionaries founded by Saint Anthony Mary Claret in 1849 in Spain to evangelise with new means and burn with the love of Christ. Their charism is mission, preaching, catechesis, the Catholic press and ecclesial renewal. They are marked by apostolic zeal, poverty and union with Mary. Today they work in parishes, missions, publishing houses, media, education and the formation of missionaries.",
            "São missionários claretianos fundados por Santo Antônio Maria Claret em 1849 na Espanha para evangelizar com novos meios e arder no amor de Cristo. Seu carisma é a missão, a pregação, a catequese, a imprensa católica e a renovação eclesial. Caracterizam-se pelo zelo apostólico, a pobreza e a união com Maria. Hoje atuam em paróquias, missões, editoras, meios de comunicação, educação e formação de missionários."
        ),
        charism = LocalizedText(
            "Misión, predicación y nuevos medios para evangelizar.",
            "Mission, preaching and new means for evangelisation.",
            "Missão, pregação e novos meios para evangelizar."
        )
    ),
    ReligiousOrder(
        id = "espiritanos-cssp",
        name = LocalizedText("Congregación del Espíritu Santo", "Congregation of the Holy Spirit", "Congregação do Espírito Santo"),
        abbreviation = "CSSp",
        foundationYear = "1703",
        founder = LocalizedText("Claude Le Begue de Poullart des Places", "Claude Le Begue de Poullart des Places", "Claude Le Begue de Poullart des Places"),
        description = LocalizedText(
            "Son misioneros espiritanos fundados por Claude Le Begue de Poullart des Places en 1703 en París, unidos en 1848 con la congregación del Corazón Inmaculado de María. Su carisma es la apertura al Espíritu Santo, la misión ad gentes y la atención a los pobres y abandonados. Se distinguen por la disponibilidad, la vida comunitaria y la inculturación. Hoy sirven en África, Europa, América y las Islas, en parroquias, educación, justicia y evangelización.",
            "They are Spiritan missionaries founded by Claude Le Begue de Poullart des Places in 1703 in Paris, united in 1848 with the Congregation of the Immaculate Heart of Mary. Their charism is openness to the Holy Spirit, mission ad gentes and care for the poor and abandoned. They are marked by availability, community life and inculturation. Today they serve in Africa, Europe, the Americas and the Islands, in parishes, education, justice and evangelisation.",
            "São missionários espiritanos fundados por Claude Le Begue de Poullart des Places em 1703 em Paris, unidos em 1848 à Congregação do Imaculado Coração de Maria. Seu carisma é a abertura ao Espírito Santo, a missão ad gentes e a atenção aos pobres e abandonados. Caracterizam-se pela disponibilidade, a vida comunitária e a inculturação. Hoje servem na África, Europa, Américas e Ilhas, em paróquias, educação, justiça e evangelização."
        ),
        charism = LocalizedText(
            "Apertura al Espíritu Santo y misión junto a los pobres.",
            "Openness to the Holy Spirit and mission with the poor.",
            "Abertura ao Espírito Santo e missão junto aos pobres."
        )
    ),
    ReligiousOrder(
        id = "paulinos-ssp",
        name = LocalizedText("Sociedad de San Pablo", "Society of St. Paul", "Sociedade de São Paulo"),
        abbreviation = "SSP",
        foundationYear = "1914",
        founder = LocalizedText("Venerable Jaime Alberione", "Ven. James Alberione", "Ven. Tiago Alberione"),
        description = LocalizedText(
            "Son religiosos paulinos fundados por el venerable Jaime Alberione en 1914 en Alba, Italia, para evangelizar a través de los modernos medios de comunicación. Su carisma es la comunicación del Evangelio mediante prensa, cine, radio, televisión, internet y libros. Se distinguen por la vida de redacción, estudio de las comunicaciones y oración por la misión. Hoy producen contenidos, editoriales, plataformas digitales y formación mediática para la Iglesia.",
            "They are Pauline religious founded by Ven. James Alberione in 1914 at Alba, Italy, to evangelise through modern means of communication. Their charism is communicating the Gospel through the press, cinema, radio, television, the internet and books. They are marked by a life of writing, study of communications and prayer for the mission. Today they produce content, publishing houses, digital platforms and media formation for the Church.",
            "São religiosos paulinos fundados pelo venerável Tiago Alberione em 1914 em Alba, Itália, para evangelizar através dos modernos meios de comunicação. Seu carisma é a comunicação do Evangelho por meio da imprensa, cinema, rádio, televisão, internet e livros. Caracterizam-se pela vida de redação, estudo da comunicação e oração pela missão. Hoje produzem conteúdos, editoras, plataformas digitais e formação midiática para a Igreja."
        ),
        charism = LocalizedText(
            "Evangelizar con los modernos medios de comunicación.",
            "Evangelising through modern means of communication.",
            "Evangelizar pelos modernos meios de comunicação."
        )
    ),
    ReligiousOrder(
        id = "hijas-caridad-dc",
        name = LocalizedText("Hijas de la Caridad de San Vicente de Paúl", "Daughters of Charity of Saint Vincent de Paul", "Filhas da Caridade de São Vicente de Paulo"),
        abbreviation = "DC",
        foundationYear = "1633",
        founder = LocalizedText("San Vicente de Paúl y Santa Luisa de Marillac", "St. Vincent de Paul and St. Louise de Marillac", "São Vicente de Paulo e Santa Luísa de Marillac"),
        description = LocalizedText(
            "Son la Compañía fundada en París el 29 de noviembre de 1633 por San Vicente de Paúl y Santa Luisa de Marillac para servir a los pobres con sencillez, humildad y caridad. Su carisma es vivir la vida consagrada en el servicio directo: visitar, alimentar, curar y acompañar a los más abandonados en hospitales, hogares y calles. Se distinguen por sus votos sencillos, sus cofias blancas y su presencia activa entre los pobres. Hoy trabajan en hospitales, escuelas, asilos, comedores, ayuda a inmigrantes y misiones en todo el mundo.",
            "They are the Company founded in Paris on 29 November 1633 by Saint Vincent de Paul and Saint Louise de Marillac to serve the poor with simplicity, humility and charity. Their charism is to live consecrated life in direct service: visiting, feeding, healing and accompanying the most abandoned in hospitals, homes and streets. They are marked by their simple vows, white winged caps and active presence among the poor. Today they work in hospitals, schools, residences, soup kitchens, help for migrants and missions throughout the world.",
            "São a Companhia fundada em Paris em 29 de novembro de 1633 por São Vicente de Paulo e Santa Luísa de Marillac para servir os pobres com simplicidade, humildade e caridade. Seu carisma é viver a vida consagrada no serviço direto: visitar, alimentar, curar e acompanhar os mais abandonados em hospitais, lares e ruas. Caracterizam-se pelos votos simples, pelas toucas brancas e pela presença ativa entre os pobres. Hoje atuam em hospitais, escolas, asilos, cozinhas populares, ajuda a migrantes e missões em todo o mundo."
        ),
        charism = LocalizedText(
            "Servicio humilde y sencillo a los pobres al modo de Vicente y Luisa.",
            "Humble and simple service to the poor in the manner of Vincent and Louise.",
            "Serviço humilde e simples aos pobres à maneira de Vicente e Luísa."
        )
    ),
    ReligiousOrder(
        id = "hijas-maria-auxiliadora-fma",
        name = LocalizedText("Hijas de María Auxiliadora", "Daughters of Mary Help of Christians", "Filhas de Maria Auxiliadora"),
        abbreviation = "FMA",
        foundationYear = "1872",
        founder = LocalizedText("San Juan Bosco y Santa María Doménica Mazzarello", "St. John Bosco and St. Mary Domenica Mazzarello", "São João Bosco e Santa Maria Domênica Mazzarello"),
        description = LocalizedText(
            "Son el Instituto fundado el 5 de agosto de 1872 en Mornese, Italia, por San Juan Bosco junto con Santa María Doménica Mazzarello como rama femenina de la Familia Salesiana. Su carisma es educar y evangelizar a las niñas y jóvenes, especialmente a las más pobres, con el Sistema Preventivo de Don Bosco: razón, religión y amor. Se distinguen por la alegría, la devoción a María Auxiliadora y la dedicación a colegios, oratorios y casas de acogida. Hoy trabajan en educación formal e informal, formación profesional, cultura y misiones en los cinco continentes.",
            "They are the Institute founded on 5 August 1872 in Mornese, Italy, by Saint John Bosco together with Saint Mary Domenica Mazzarello as the women's branch of the Salesian Family. Their charism is to educate and evangelise girls and young people, especially the poorest, with Don Bosco's Preventive System: reason, religion and loving-kindness. They are marked by joy, devotion to Mary Help of Christians and dedication to schools, oratories and homes for youth. Today they work in formal and informal education, vocational training, culture and missions on five continents.",
            "São o Instituto fundado em 5 de agosto de 1872 em Mornese, Itália, por São João Bosco juntamente com Santa Maria Domênica Mazzarello como ramo feminino da Família Salesiana. Seu carisma é educar e evangelizar meninas e jovens, especialmente os mais pobres, com o Sistema Preventivo de Dom Bosco: razão, religião e amor. Caracterizam-se pela alegria, devoção a Maria Auxiliadora e dedicação a escolas, oratórios e casas de acolhimento. Hoje atuam na educação formal e informal, na formação profissional, na cultura e nas missões nos cinco continentes."
        ),
        charism = LocalizedText(
            "Educación y evangelización de la juventud con el Sistema Preventivo.",
            "Education and evangelisation of youth with the Preventive System.",
            "Educação e evangelização da juventude com o Sistema Preventivo."
        )
    ),
    ReligiousOrder(
        id = "carmelitas-descalzas-ocd",
        name = LocalizedText("Carmelitas Descalzas", "Discalced Carmelite Sisters", "Carmelitas Descalças"),
        abbreviation = "OCD",
        foundationYear = "1562",
        founder = LocalizedText("Santa Teresa de Jesús", "St. Teresa of Jesus", "Santa Teresa de Jesus"),
        description = LocalizedText(
            "Son las monjas y hermanas de la reforma carmelitana iniciada por Santa Teresa de Jesús con la fundación del convento de San José en Ávila en 1562, junto a San Juan de la Cruz. Su carisma es la oración, el recogimiento y la unión con Dios en el estilo del Carmelo, con votos de clausura, pobreza y silencio para muchas comunidades. Se distinguen por la Liturgia de las Horas, la lectura espiritual y la vida de intercesión por la Iglesia. Hoy, además de la vida contemplativa, algunas comunidades apostólicas colaboran en parroquias, espiritualidad, educación y servicio a la Iglesia desde el Carmelo.",
            "They are the nuns and sisters of the Carmelite reform begun by Saint Teresa of Jesus with the foundation of the Convent of Saint Joseph in Avila in 1562, together with Saint John of the Cross. Their charism is prayer, recollection and union with God in the Carmelite tradition, with vows of enclosure, poverty and silence for many communities. They are marked by the Divine Office, spiritual reading and a life of intercession for the Church. Today, besides contemplative life, some apostolic communities serve in parishes, spirituality, education and service to the Church from Carmel.",
            "São as monjas e irmãs da reforma carmelitana iniciada por Santa Teresa de Jesus com a fundação do convento de São José em Ávila em 1562, junto com São João da Cruz. Seu carisma é a oração, o recolhimento e a união com Deus no estilo do Carmelo, com votos de clausura, pobreza e silêncio para muitas comunidades. Caracterizam-se pela liturgia das Horas, pela leitura espiritual e pela vida de intercessão pela Igreja. Hoje, além da vida contemplativa, algumas comunidades apostólicas atuam em paróquias, espiritualidade, educação e serviço à Igreja a partir do Carmelo."
        ),
        charism = LocalizedText(
            "Oración contemplativa, recogimiento y unión con Dios en el Carmelo.",
            "Contemplative prayer, recollection and union with God in Carmel.",
            "Oração contemplativa, recolhimento e união com Deus no Carmelo."
        )
    ),
    ReligiousOrder(
        id = "clarisas-osc",
        name = LocalizedText("Clarisas", "Poor Clares", "Clarissas"),
        abbreviation = "OSC",
        foundationYear = "1212",
        founder = LocalizedText("Santa Clara de Asís", "St. Clare of Assisi", "Santa Clara de Assis"),
        description = LocalizedText(
            "Son las hermanas de la Segunda Orden Franciscana, fundada por Santa Clara de Asís en San Damián, Asís, en 1212 para vivir el Evangelio en pobreza y contemplación. Su carisma es la vida de oración, clausura y pobreza radical siguiendo a Cristo pobre junto a Francisco de Asís. Se distinguen por el hábito marrón, el oficio divino, la adoración eucarística y la fraternidad en monasterios. Hoy sirven sobre todo con la oración y la intercesión, y muchas comunidades también acogen, forman y acompañan espiritualmente a quienes buscan a Dios.",
            "They are the sisters of the Second Franciscan Order, founded by Saint Clare of Assisi at San Damiano, Assisi, in 1212 to live the Gospel in poverty and contemplation. Their charism is a life of prayer, enclosure and radical poverty following poor Christ together with Francis of Assisi. They are marked by the brown habit, the Divine Office, Eucharistic adoration and fraternity in monasteries. Today they serve above all through prayer and intercession, and many communities also welcome, form and spiritually accompany those seeking God.",
            "São as irmãs da Segunda Ordem Franciscana, fundada por Santa Clara de Assis em São Damião, Assis, em 1212 para viver o Evangelho na pobreza e na contemplação. Seu carisma é a vida de oração, clausura e pobreza radical seguindo a Cristo pobre junto a Francisco de Assis. Caracterizam-se pelo hábito marrom, pelo ofício divino, pela adoração eucarística e pela fraternidade nos mosteiros. Hoje servem sobretudo com a oração e a intercessão, e muitas comunidades também acolhem, formam e acompanham espiritualmente quem busca Deus."
        ),
        charism = LocalizedText(
            "Pobreza radical, oración y contemplación al modo de Clara.",
            "Radical poverty, prayer and contemplation in the manner of Clare.",
            "Pobreza radical, oração e contemplação à maneira de Clara."
        )
    ),
    ReligiousOrder(
        id = "misioneras-caridad-mc",
        name = LocalizedText("Misioneras de la Caridad", "Missionaries of Charity", "Missionárias da Caridade"),
        abbreviation = "MC",
        foundationYear = "1950",
        founder = LocalizedText("Santa Teresa de Calcuta", "St. Teresa of Calcutta", "Santa Teresa de Calcutá"),
        description = LocalizedText(
            "Son la congregación fundada el 7 de octubre de 1950 en Calcuta, India, por la Madre Teresa de Calcuta para servir a los más pobres de los pobres. Su carisma es la caridad activa e incansable: tocar, curar y acompañar a los abandonados, enfermos, moribundos y leprosos con amor sin distinción. Se distinguen por el sari blanco con franjas azules, la vida de oración sencilla y el trabajo en casas de moribundos, hogares de niños, comedores y leproserías. Hoy sirven en todo el mundo con hermanos y centros de formación en muchos países.",
            "They are the congregation founded on 7 October 1950 in Calcutta, India, by Mother Teresa of Calcutta to serve the poorest of the poor. Their charism is active and tireless charity: touching, healing and accompanying the abandoned, sick, dying and lepers with love without distinction. They are marked by the white sari with blue stripes, simple prayer life and work in homes for the dying, children's homes, soup kitchens and leprosy centres. Today they serve throughout the world with brothers and formation centres in many countries.",
            "São a congregação fundada em 7 de outubro de 1950 em Calcutá, Índia, pela Madre Teresa de Calcutá para servir os mais pobres dos pobres. Seu carisma é a caridade ativa e incansável: tocar, curar e acompanhar os abandonados, doentes, moribundos e leprosos com amor sem distinção. Caracterizam-se pelo sári branco com listras azuis, pela vida de oração simples e pelo trabalho em casas de moribundos, lares de crianças, cozinhas populares e leprosários. Hoje servem em todo o mundo com irmãos e centros de formação em muitos países."
        ),
        charism = LocalizedText(
            "Servicio total y gratuito a los más pobres de los pobres.",
            "Wholehearted and free service to the poorest of the poor.",
            "Serviço total e gratuito aos mais pobres dos pobres."
        )
    ),
    ReligiousOrder(
        id = "franciscanas-misioneras-maria-fmm",
        name = LocalizedText("Franciscanas Misioneras de María", "Franciscan Missionaries of Mary", "Franciscanas Missionárias de Maria"),
        abbreviation = "FMM",
        foundationYear = "1877",
        founder = LocalizedText("Beata María de la Pasión", "Blessed Mary of the Passion", "Bem-aventurada Maria da Paixão"),
        description = LocalizedText(
            "Son el Instituto fundado el 6 de enero de 1877 en Ootacamund, India, por la Beata María de la Pasión, de nombre civil Hélène de Chappotin, para la misión universal al servicio de las mujeres. Su carisma es la vida franciscana de pobreza, fraternidad y adoración, vivida en misión entre los pueblos, especialmente atendiendo a las mujeres allí donde los médicos varones no podían entrar por el purdah. Se distinguen por la misión sanitaria, educativa y social, la vida comunitaria y la devoción mariana. Hoy trabajan en 73 países en salud, educación, catequesis y promoción humana.",
            "They are the Institute founded on 6 January 1877 in Ootacamund, India, by Blessed Mary of the Passion, civil name Helene de Chappotin, for universal mission in service of women. Their charism is Franciscan life of poverty, fraternity and adoration, lived in mission among peoples, especially caring for women where male doctors could not enter because of purdah. They are marked by health, educational and social mission, community life and Marian devotion. Today they work in 73 countries in health care, education, catechesis and human development.",
            "São o Instituto fundado em 6 de janeiro de 1877 em Ootacamund, Índia, pela Bem-aventurada Maria da Paixão, de nome civil Hélène de Chappotin, para a missão universal no serviço às mulheres. Seu carisma é a vida franciscana de pobreza, fraternidade e adoração, vivida em missão entre os povos, especialmente atendendo mulheres onde os médicos homens não podiam entrar por causa do purdah. Caracterizam-se pela missão sanitária, educativa e social, pela vida comunitária e pela devoção mariana. Hoje atuam em 73 países na saúde, educação, catequese e promoção humana."
        ),
        charism = LocalizedText(
            "Vida franciscana en misión, con especial servicio a las mujeres.",
            "Franciscan life on mission, with special service to women.",
            "Vida franciscana em missão, com serviço especial às mulheres."
        )
    ),
    ReligiousOrder(
        id = "hermanas-caridad-santa-ana-csa",
        name = LocalizedText("Hermanas de la Caridad de Santa Ana", "Sisters of Charity of Saint Anne", "Irmãs da Caridade de Santa Ana"),
        abbreviation = "CSA",
        foundationYear = "1804",
        founder = LocalizedText("P. Juan Bonal y María Rafols", "Fr. Juan Bonal and Mary Rafols", "P. Juan Bonal e Maria Rafols"),
        description = LocalizedText(
            "Son la Congregación surgida en Zaragoza el 28 de diciembre de 1804, cuando el P. Juan Bonal y María Rafols llegaron con once hermanas para atender a los enfermos del Hospital de Nuestra Señora de Gracia. Su carisma es la caridad samaritana: asistir a los enfermos, ancianos y pobres con entrega, humildad y espíritu de Santa Ana. Se distinguen por la vida comunitaria sencilla, la atención hospitalaria y la educación en instituciones propias. Hoy trabajan en hospitales, centros sanitarios y educativos, ayuda social y parroquias en España y América Latina.",
            "They are the Congregation that began in Zaragoza on 28 December 1804, when Fr. Juan Bonal and Mary Rafols arrived with eleven sisters to care for the sick in the Hospital of Our Lady of Grace. Their charism is Samaritan charity: assisting the sick, elderly and poor with dedication, humility and the spirit of Saint Anne. They are marked by simple community life, hospital care and education in their own centres. Today they work in hospitals, health and education centres, social help and parishes in Spain and Latin America.",
            "São a Congregação surgida em Saragoça em 28 de dezembro de 1804, quando o P. Juan Bonal e Maria Rafols chegaram com onze irmãs para atender os doentes do Hospital de Nossa Senhora da Graça. Seu carisma é a caridade samaritana: assistir doentes, idosos e pobres com dedicação, humildade e espírito de Santa Ana. Caracterizam-se pela vida comunitária simples, pelo atendimento hospitalar e pela educação em centros próprios. Hoje atuam em hospitais, centros de saúde e educação, ajuda social e paróquias na Espanha e na América Latina."
        ),
        charism = LocalizedText(
            "Caridad samaritana en el cuidado de enfermos y pobres.",
            "Samaritan charity in caring for the sick and the poor.",
            "Caridade samaritana no cuidado dos doentes e dos pobres."
        )
    ),
    ReligiousOrder(
        id = "orden-visitacion-vhm",
        name = LocalizedText("Orden de la Visitación de Santa María", "Order of the Visitation of Holy Mary", "Ordem da Visitação de Santa Maria"),
        abbreviation = "VHM",
        foundationYear = "1610",
        founder = LocalizedText("San Francisco de Sales y Santa Juana Francisca de Chantal", "St. Francis de Sales and St. Jane Frances de Chantal", "São Francisco de Sales e Santa Joana Francisca de Chantal"),
        description = LocalizedText(
            "Son las monjas de la Orden fundada el 6 de junio de 1610 en Annecy por San Francisco de Sales y Santa Juana Francisca de Chantal para dar la vida religiosa también a mujeres mayores o de constitución débil. Su carisma es la dulzura, la humildad y la mansedumbre del Corazón de Jesús, imitando a María en la Visitación a Isabel con caridad cordial. Se distinguen por la clausura, la Liturgia de las Horas y la vida interior de las pequeñas virtudes. Hoy interceden por la Iglesia y muchas comunidades acogen a huéspedes y acompañan en dirección espiritual y oración.",
            "They are the nuns of the Order founded on 6 June 1610 in Annecy by Saint Francis de Sales and Saint Jane Frances de Chantal to give religious life also to older women or those of weak constitution. Their charism is the gentleness, humility and meekness of the Heart of Jesus, imitating Mary in the Visitation to Elizabeth with heartfelt charity. They are marked by enclosure, the Divine Office and the interior life of the little virtues. Today they intercede for the Church and many communities welcome guests and accompany in spiritual direction and prayer.",
            "São as monjas da Ordem fundada em 6 de junho de 1610 em Annecy por São Francisco de Sales e Santa Joana Francisca de Chantal para dar a vida religiosa também a mulheres mais velhas ou de constituição frágil. Seu carisma é a doçura, a humildade e a mansidão do Coração de Jesus, imitando Maria na Visitação a Isabel com caridade cordial. Caracterizam-se pela clausura, pela liturgia das Horas e pela vida interior das pequenas virtudes. Hoje intercedem pela Igreja e muitas comunidades acolhem hóspedes e acompanham na direção espiritual e na oração."
        ),
        charism = LocalizedText(
            "Dulzura, humildad y caridad cordial al modo de la Visitación.",
            "Gentleness, humility and heartfelt charity in the manner of the Visitation.",
            "Doçura, humildade e caridade cordial à maneira da Visitação."
        )
    ),
    ReligiousOrder(
        id = "ursulinas-osu",
        name = LocalizedText("Ursulinas", "Ursuline Sisters", "Ursulinas"),
        abbreviation = "OSU",
        foundationYear = "1535",
        founder = LocalizedText("Santa Ángela de Merici", "St. Angela Merici", "Santa Ângela de Merici"),
        description = LocalizedText(
            "Son las hermanas de la Compañía de Santa Úrsula iniciada por Santa Ángela de Merici en Brescia, Italia, en 1535 para educar a las niñas en la fe y en la vida cristiana. Su carisma es la educación integral de la juventud, especialmente de las niñas pobres, con vida de oración y Apostolado activo en el mundo. Se distinguen por la vida común, la formación de maestras y la fundación de colegios y escuelas en muchos países. Hoy trabajan en educación, catequesis, orientación familiar, terapia y misiones vinculadas al legado de Santa Ángela.",
            "They are the sisters of the Company of Saint Ursula begun by Saint Angela Merici in Brescia, Italy, in 1535 to educate girls in the faith and Christian life. Their charism is the integral education of youth, especially poor girls, with a life of prayer and active apostolate in the world. They are marked by common life, the formation of teachers and the founding of schools and colleges in many countries. Today they work in education, catechesis, family guidance, therapy and missions linked to the legacy of Saint Angela.",
            "São as irmãs da Companhia de Santa Úrsula iniciada por Santa Ângela de Merici em Bréscia, Itália, em 1535 para educar meninas na fé e na vida cristã. Seu carisma é a educação integral da juventude, especialmente de meninas pobres, com vida de oração e apostolado ativo no mundo. Caracterizam-se pela vida comum, pela formação de professoras e pela fundação de escolas e colégios em muitos países. Hoje atuam na educação, catequese, orientação familiar, terapia e missões ligadas ao legado de Santa Ângela."
        ),
        charism = LocalizedText(
            "Educación cristiana de la juventud, especialmente de las niñas.",
            "Christian education of youth, especially of girls.",
            "Educação cristã da juventude, especialmente das meninas."
        )
    ),
    ReligiousOrder(
        id = "adoratrices-asc",
        name = LocalizedText("Adoratrices de la Sangre de Cristo", "Adorers of the Blood of Christ", "Adoradoras do Sangue de Cristo"),
        abbreviation = "ASC",
        foundationYear = "1808",
        founder = LocalizedText("Santa María De Mattias", "St. Maria De Mattias", "Santa Maria De Mattias"),
        description = LocalizedText(
            "Son la Congregación fundada en 1808 en Acuto, Italia, por Santa María De Mattias para adorar la Sangre de Cristo y evangelizar por la enseñanza. Su carisma es la adoración eucarística y la pasión por comunicar la Palabra, sobre todo a los pobres y a quienes están alejados de la Iglesia. Se distinguen por la predicación de misiones, la catequesis y la atención a niños y jóvenes en escuelas y esta Casa. Hoy trabajan en educación, misiones populares, catequesis, salud y pastoral caritativa en Europa, América, África y Asia.",
            "They are the Congregation founded in 1808 in Acuto, Italy, by Saint Maria De Mattias to adore the Blood of Christ and evangelise through teaching. Their charism is Eucharistic adoration and zeal to proclaim the Word, above all to the poor and those far from the Church. They are marked by mission preaching, catechesis and attention to children and young people in schools and homes. Today they work in education, popular missions, catechesis, health and charitable pastoral work in Europe, America, Africa and Asia.",
            "São a Congregação fundada em 1808 em Acuto, Itália, por Santa Maria De Mattias para adorar o Sangue de Cristo e evangelizar pela ensino. Seu carisma é a adoração eucarística e o zelo por anunciar a Palavra, sobretudo aos pobres e aos afastados da Igreja. Caracterizam-se pela pregação de missões, pela catequese e pelo atendimento a crianças e jovens em escolas e casas. Hoje atuam na educação, missões populares, catequese, saúde e pastoral caritativa na Europa, América, África e Ásia."
        ),
        charism = LocalizedText(
            "Adoración de la Sangre de Cristo y anuncio de la Palabra.",
            "Adoration of the Blood of Christ and proclamation of the Word.",
            "Adoração do Sangue de Cristo e anúncio da Palavra."
        )
    ),
    ReligiousOrder(
        id = "esclavas-sagrado-corazon-rshm",
        name = LocalizedText("Religiosas del Sagrado Corazón de María", "Religious of the Sacred Heart of Mary", "Religiosas do Sagrado Coração de Maria"),
        abbreviation = "RSHM",
        foundationYear = "1849",
        founder = LocalizedText("P. Jean Gailhac y Madre St. Jean Pelissier Cure", "Fr. Jean Gailhac and Mother St. Jean Pelissier Cure", "P. Jean Gailhac e Madre St. Jean Pelissier Cure"),
        description = LocalizedText(
            "Son el Instituto fundado el 24 de febrero de 1849 en Béziers, Francia, por el P. Jean Gailhac junto a la Madre St. Jean Pelissier Cure para servir a mujeres y niños marginados. Su carisma es hacer conocer y amar a Dios con corazón universal, educando y reparando herencias de exclusión con compasión. Se distinguen por la fundación del Refugio para mujeres, el orfanato y después colegios y centros de acogida. Hoy trabajan en educación, albergues, ayuda a mujeres, hospitales y proyectos sociales en varios continentes.",
            "They are the Institute founded on 24 February 1849 in Beziers, France, by Fr. Jean Gailhac together with Mother St. Jean Pelissier Cure to serve marginalised women and children. Their charism is to make God known and loved with a universal heart, educating and repairing the wounds of exclusion with compassion. They are marked by the Refuge for women, the orphanage and later schools and care centres. Today they work in education, shelters, help for women, hospitals and social projects on several continents.",
            "São o Instituto fundado em 24 de fevereiro de 1849 em Béziers, França, pelo P. Jean Gailhac juntamente com a Madre St. Jean Pelissier Cure para servir mulheres e crianças marginalizadas. Seu carisma é fazer Deus conhecido e amado com coração universal, educando e reparando feridas da exclusão com compaixão. Caracterizam-se pelo Refúgio para mulheres, pelo orfanato e depois por escolas e centros de acolhimento. Hoje atuam na educação, abrigos, ajuda a mulheres, hospitais e projetos sociais em vários continentes."
        ),
        charism = LocalizedText(
            "Hacer conocer y amar a Dios educando a los marginados.",
            "To make God known and loved by educating the marginalised.",
            "Fazer Deus conhecido e amado educando os marginalizados."
        )
    ),
    ReligiousOrder(
        id = "hermanas-providencia-sp",
        name = LocalizedText("Hermanas de la Providencia", "Sisters of Providence", "Irmãs da Providência"),
        abbreviation = "SP",
        foundationYear = "1843",
        founder = LocalizedText("Santa Emilie Gamelin", "St. Emilie Gamelin", "Santa Emilie Gamelin"),
        description = LocalizedText(
            "Son la Congregación fundada en 1843 en Montreal, Canadá, por Santa Emilie Gamelin para los pobres mediante la Providencia de Dios. Su carisma es el abandono confiado a la Providencia al servicio de enfermos, ancianos, huérfanos y encarcelados, especialmente mujeres y niños abandonados. Se distinguen por la hospitalidad, la atención a domicilio y los servicios sociales de calidad. Hoy trabajan en hospitales, residencias, albergues, escuelas, parroquias y centros comunitarios en Canadá, Estados Unidos y América Latina.",
            "They are the Congregation founded in 1843 in Montreal, Canada, by Saint Emilie Gamelin for the poor through the Providence of God. Their charism is trusting abandonment to Providence in service of the sick, elderly, orphans and prisoners, especially abandoned women and children. They are marked by hospitality, home care and quality social services. Today they work in hospitals, residences, shelters, schools, parishes and community centres in Canada, the United States and Latin America.",
            "São a Congregação fundada em 1843 em Montreal, Canadá, por Santa Emilie Gamelin para os pobres mediante a Providência de Deus. Seu carisma é o abandono confiante à Providência no serviço a doentes, idosos, órfãos e prisioneiros, especialmente mulheres e crianças abandonadas. Caracterizam-se pela hospitalidade, pelo cuidado domiciliar e pelos serviços sociais de qualidade. Hoje atuam em hospitais, residências, abrigos, escolas, paróquias e centros comunitários no Canadá, Estados Unidos e América Latina."
        ),
        charism = LocalizedText(
            "Abandono a la Providencia en el servicio a los pobres.",
            "Abandonment to Providence in service to the poor.",
            "Abandono à Providência no serviço aos pobres."
        )
    ),
    ReligiousOrder(
        id = "siervas-de-los-pobres-dss",
        name = LocalizedText("Siervas de los Pobres", "Servants of the Poor", "Servas dos Pobres"),
        abbreviation = "DSS",
        foundationYear = "1969",
        founder = LocalizedText("Petra Moennigmann", "Petra Moennigmann", "Petra Moennigmann"),
        description = LocalizedText(
            "Son la Congregación de las Siervas de los Pobres, Dinasevanasabha, fundada el 1 de junio de 1969 en Pattuvam, India, por la religiosa alemana Petra Moennigmann, de las Ursulinas de Werl. Su carisma es atender con preferencia a las castas más pobres de la sociedad india, llevando evangelio, salud y educación a aldeas marginadas. Se distinguen por la pobreza, la integración cultural y la vida de servicio junto a las comunidades más excluidas. Hoy trabajan en escuelas, dispensarios, ayuda a campesinos y promoción de la mujer en Kerala y otras zonas de India.",
            "They are the Congregation of the Servants of the Poor, Dinasevanasabha, founded on 1 June 1969 in Pattuvam, India, by the German religious Petra Moennigmann, of the Ursulines of Werl. Their charism is to serve preferentially the poorest castes of Indian society, bringing Gospel, health and education to marginalised villages. They are marked by poverty, cultural integration and a life of service alongside the most excluded communities. Today they work in schools, dispensaries, help for farmers and women's empowerment in Kerala and other areas of India.",
            "São a Congregação das Servas dos Pobres, Dinasevanasabha, fundada em 1 de junho de 1969 em Pattuvam, Índia, pela religiosa alemã Petra Moennigmann, das Ursulinas de Werl. Seu carisma é atender de preferência as castas mais pobres da sociedade indiana, levando evangelho, saúde e educação a aldeias marginalizadas. Caracterizam-se pela pobreza, pela integração cultural e pela vida de serviço junto às comunidades mais excluídas. Hoje atuam em escolas, dispensários, ajuda a agricultores e promoção da mulher em Kerala e outras regiões da Índia."
        ),
        charism = LocalizedText(
            "Servicio preferencial a los más pobres de la sociedad india.",
            "Preferential service to the poorest of Indian society.",
            "Serviço preferencial aos mais pobres da sociedade indiana."
        )
    ),
    ReligiousOrder(
        id = "franciscanas-de-los-angeles-fsma",
        name = LocalizedText("Franciscanas de Santa María de los Ángeles", "Franciscan Sisters of Saint Mary of the Angels", "Franciscanas de Santa Maria dos Anjos"),
        abbreviation = "FSMA",
        foundationYear = "1871",
        founder = LocalizedText("P. Crisóstomo Potton OFM y Sor M. Crisóstomo de la Cruz", "Fr. Chrysostom Potton OFM and Sr. M. Chrysostom of the Cross", "P. Crisóstomo Potton OFM e Ir. M. Crisóstomo da Cruz"),
        description = LocalizedText(
            "Son el Instituto de las Franciscanas de Santa María de los Ángeles, fundado el 2 de agosto de 1871 en Francia por el P. Crisóstomo Potton OFM y Sor M. Crisóstomo de la Cruz, a petición de Mons. Émile Freppel, obispo de Angers. Su carisma es la vida franciscana de pobreza y caridad, tomando a María bajo el título de Ángeles como quiso Francisco en la Porciúncula. Se distinguen por afiliación a la Tercera Orden Regular de San Francisco, la misión educativa y social y el trabajo con huérfanos. Hoy sirven en Francia, Inglaterra, India, Etiopía, Brasil y Tanzania en escuelas, costura, salud y promoción de la mujer.",
            "They are the Institute of the Franciscans of Saint Mary of the Angels, founded on 2 August 1871 in France by Fr. Chrysostom Potton OFM and Sr. M. Chrysostom of the Cross, at the request of Msgr. Emile Freppel, Bishop of Angers. Their charism is Franciscan life of poverty and charity, taking Mary under the title of the Angels as Francis did at the Portiuncula. They are marked by affiliation to the Third Order Regular of Saint Francis, educational and social mission and work with orphans. Today they serve in France, England, India, Ethiopia, Brazil and Tanzania in schools, needlework, health and women's promotion.",
            "São o Instituto das Franciscanas de Santa Maria dos Anjos, fundado em 2 de agosto de 1871 na França pelo P. Crisóstomo Potton OFM e pela Ir. M. Crisóstomo da Cruz, a pedido de D. Émile Freppel, bispo de Angers. Seu carisma é a vida franciscana de pobreza e caridade, tomando Maria sob o título de Anjos como Francisco quis na Porciúncula. Caracterizam-se pela afiliação à Terceira Ordem Regular de São Francisco, pela missão educativa e social e pelo trabalho com órfãos. Hoje servem na França, Inglaterra, Índia, Etiópia, Brasil e Tanzânia em escolas, costura, saúde e promoção da mulher."
        ),
        charism = LocalizedText(
            "Pobreza franciscana y caridad mariana en la misión.",
            "Franciscan poverty and Marian charity on mission.",
            "Pobreza franciscana e caridade mariana em missão."
        )
    ),
    ReligiousOrder(
        id = "religiosas-jesus-maria-rjm",
        name = LocalizedText("Religiosas de Jesús-María", "Religious of Jesus and Mary", "Religiosas de Jesus-Maria"),
        abbreviation = "RJM",
        foundationYear = "1821",
        founder = LocalizedText("Santa Claudine Thévenet", "St. Claudine Thevenet", "Santa Claudine Thévenet"),
        description = LocalizedText(
            "Son la Congregación fundada el 31 de octubre de 1821 en Lyon, Francia, por Santa Claudine Thévenet para la educación cristiana de las niñas. Su carisma es ser apóstoles de Jesús y María desde la escuela, formando personas con confianza, alegría y amor, especialmente a las más pobres. Se distinguen por el espíritu de familia, la dedicación a materiales y métodos sencillos y la vida de oración comunitaria. Hoy trabajan en colegios, centros pastorales, formación de educadores y proyectos de inclusión social en Europa, América, África y Asia.",
            "They are the Congregation founded on 31 October 1821 in Lyon, France, by Saint Claudine Thevenet for the Christian education of girls. Their charism is to be apostles of Jesus and Mary from the school, forming people with trust, joy and love, especially the poorest. They are marked by the spirit of family, dedication to simple materials and methods and a life of community prayer. Today they work in schools, pastoral centres, teacher formation and social inclusion projects in Europe, America, Africa and Asia.",
            "São a Congregação fundada em 31 de outubro de 1821 em Lyon, França, por Santa Claudine Thévenet para a educação cristã de meninas. Seu carisma é ser apóstolas de Jesus e Maria a partir da escola, formando pessoas com confiança, alegria e amor, especialmente as mais pobres. Caracterizam-se pelo espírito de família, pela dedicação a materiais e métodos simples e pela vida de oração comunitária. Hoje atuam em escolas, centros pastorais, formação de educadores e projetos de inclusão social na Europa, América, África e Ásia."
        ),
        charism = LocalizedText(
            "Educación cristiana con corazón de Jesús y María.",
            "Christian education with the heart of Jesus and Mary.",
            "Educação cristã com o coração de Jesus e Maria."
        )
    ),
    ReligiousOrder(
        id = "dominicanas-visitacion-dv",
        name = LocalizedText("Dominicanas de la Visitación", "Dominican Sisters of the Visitation", "Dominicanas da Visitação"),
        abbreviation = "DV",
        foundationYear = "1927",
        founder = LocalizedText("Venerable María Rosario de la Visitación", "Venerable Mary Rosario of the Visitation", "Venerável Maria Rosário da Visitação"),
        description = LocalizedText(
            "Son la comunidad dominica femenina agregada a la Orden de Predicadores, fundada el 18 de febrero de 1927 en Molo, Iloilo, Filipinas, por la Venerable María Rosario de la Visitación, María Beatriz del Rosario Arroyo. Su carisma es la vida dominica de contemplación, estudio y predicación, enriquecida por la visita amorosa de María a Isabel como modelo de salir al encuentro de los demás. Se distinguen por el hábito dominico, el rezo del Rosario y la dedicación escolar y apostólica. Hoy trabajan en escuelas, colegios, casas de retiro, catequesis y salud en Filipinas y en misiones en el extranjero.",
            "They are the Dominican women's community aggregated to the Order of Preachers, founded on 18 February 1927 in Molo, Iloilo, Philippines, by Venerable Mary Rosario of the Visitation, Maria Beatriz del Rosario Arroyo. Their charism is Dominican life of contemplation, study and preaching, enriched by Mary's loving visit to Elizabeth as a model of going out to meet others. They are marked by the Dominican habit, the Rosary and school and apostolic dedication. Today they work in schools, colleges, retreat houses, catechesis and health in the Philippines and in missions abroad.",
            "São a comunidade dominicana feminina agregada à Ordem dos Pregadores, fundada em 18 de fevereiro de 1927 em Molo, Iloilo, Filipinas, pela Venerável Maria Rosário da Visitação, Maria Beatriz del Rosario Arroyo. Seu carisma é a vida dominicana de contemplação, estudo e pregação, enriquecida pela visita amorosa de Maria a Isabel como modelo de ir ao encontro dos outros. Caracterizam-se pelo hábito dominicano, pela oração do Rosário e pela dedicação escolar e apostólica. Hoje atuam em escolas, colégios, casas de retiro, catequese e saúde nas Filipinas e em missões no exterior."
        ),
        charism = LocalizedText(
            "Contemplación, estudio y predicación al estilo dominico.",
            "Contemplation, study and preaching in the Dominican manner.",
            "Contemplação, estudo e pregação à maneira dominicana."
        )
    )
    )

    val offices: List<ChurchOffice> = listOf(
    ChurchOffice(
        id = "office_monaguillo",
        name = LocalizedText("Monaguillo", "Altar Server", "Coroinha"),
        description = LocalizedText(
            "Ayuda al sacerdote en la Misa, preparando el altar, la liturgia de la Palabra y la Eucaristía, y aprendiendo la oración y las rúbricas por la práctica. Se llega por invitación y formación en la propia parroquia o escuela, tras el Bautismo y normalmente tras la Primera Comunión o la catequesis. No se requiere ordenación sagrada, celibato ni formación de seminario, sino vida de gracia, piedad y puntualidad. Su servicio es una escuela de fe que a menudo despierta la vocación al sacerdocio.",
            "He assists the priest at Mass, preparing the altar and serving in the Liturgy of the Word and the Eucharist, while learning prayer and the rubrics through practice. He is chosen by invitation and training in his own parish or school, after Baptism and usually after First Communion or catechesis. No sacred ordination, celibacy, or seminary formation is required, but a life of grace, piety, and punctuality is. His service is a school of faith that often awakens a vocation to the priesthood.",
            "Auxilia o sacerdote na Missa, preparando o altar e servindo na Liturgia da Palavra e da Eucaristia, enquanto aprende a oração e as rúbricas na prática. É escolhido por convite e formação na própria paróquia ou escola, após o Batismo e normalmente após a Primeira Comunhão ou a catequese. Não se exige ordenação sagrada, celibato nem formação de seminário, mas sim vida de graça, piedade e pontualidade. Seu serviço é uma escola de fé que costuma despertar a vocação ao sacerdócio."
        )
    ),
    ChurchOffice(
        id = "office_lector",
        name = LocalizedText("Lector", "Lector", "Leitor"),
        description = LocalizedText(
            "Proclama la Palabra de Dios en la asamblea, presenta las intenciones de la oración universal y lee los textos de la liturgia con claridad y reverencia. En la tradición latina se llega tras una formación litúrgica y espiritual y, históricamente, mediante la institución en este ministerio tras el Acólito, hoy ejercido como ministerio laical estable. No requiere ordenación sagrada ni celibato, pero exige vida de oración, conocimiento de la Sagrada Escritura y preparación del texto. Es un servicio de la Palabra al servicio de la fe de la comunidad.",
            "He proclaims the Word of God to the assembly, presents the intercessions of the universal prayer, and reads the liturgical texts with clarity and reverence. In the Latin tradition he is formed in liturgy and spirituality and, historically, was instituted to this ministry after the Acolyte, today exercised as a stable lay ministry. It does not require sacred ordination or celibacy, but demands a life of prayer, knowledge of Scripture, and careful preparation of the text. It is a ministry of the Word at the service of the community's faith.",
            "Proclama a Palavra de Deus à assembleia, apresenta as intenções da oração universal e lê os textos litúrgicos com clareza e reverência. Na tradição latina, chega-se após formação litúrgica e espiritual e, historicamente, por instituição neste ministério após o Acólito, hoje exercido como ministério laical estável. Não exige ordenação sagrada nem celibato, mas pede vida de oração, conhecimento da Sagrada Escritura e preparo cuidadoso do texto. É um serviço da Palavra a serviço da fé da comunidade."
        )
    ),
    ChurchOffice(
        id = "office_acolito",
        name = LocalizedText("Acólito", "Acolyte", "Acólito"),
        description = LocalizedText(
            "Sirve en el altar, prepara el pan y el vino para la Eucaristía, ayuda al diácono y al sacerdote, y puede distribuir la Sagrada Comunión como ministro extraordinario. Se llega por formación litúrgica en la parroquia y, en la tradición latina, tradicionalmente tras ser lector y antes de la ordenación de subdiácono y diácono. No es orden sagrada ni exige formalmente celibato, pero requiere vida cristiana, piedad eucarística y preparación. Su misión es servir a la Eucaristía y llamar a una entrega más profunda.",
            "He serves at the altar, prepares the bread and wine for the Eucharist, assists the deacon and priest, and may distribute Holy Communion as an extraordinary minister. He is reached through liturgical formation in the parish and, in the Latin tradition, traditionally after serving as lector and before the ordination of subdeacon and deacon. It is not a sacred order and does not formally require celibacy, but it requires Christian life, Eucharistic piety, and preparation. His mission is to serve the Eucharist and to be called to a deeper dedication.",
            "Serve no altar, prepara o pão e o vinho para a Eucaristia, auxilia o diácono e o sacerdote e pode distribuir a Sagrada Comunhão como ministro extraordinário. Chega-se por formação litúrgica na paróquia e, na tradição latina, tradicionalmente após ser leitor e antes da ordenação de subdiácono e diácono. Não é uma ordem sagrada nem exige formalmente celibato, mas pede vida cristã, piedade eucarística e preparo. Sua missão é servir à Eucaristia e ser chamado a uma dedicação mais profunda."
        )
    ),
    ChurchOffice(
        id = "office_misionero",
        name = LocalizedText("Misionero", "Missionary", "Missionário"),
        description = LocalizedText(
            "Anuncia el Evangelio a quien aún no conoce a Cristo, funda comunidades, celebra los sacramentos según su ordenación y sirve a los pobres en tierras lejanas o en el propio país. Se llega por vocación, formación en un instituto o diócesis misionera, y envío por la autoridad eclesiástica, tras años de estudio y vida comunitaria. Si es sacerdote o religioso, vive también el celibato y la obediencia; si es laico, participa según su estado. La misión exige fe, idiomas, inculturación y entrega sostenida.",
            "He announces the Gospel to those who do not yet know Christ, founds communities, celebrates the sacraments according to his ordination, and serves the poor in distant lands or in his own country. He is called through vocation, formation in a missionary institute or diocese, and sending by the ecclesiastical authority, after years of study and community life. If he is a priest or religious, he also lives celibacy and obedience; if he is a layperson, he participates according to his state. Mission requires faith, languages, inculturation, and sustained dedication.",
            "Anuncia o Evangelho a quem ainda não conhece Cristo, funda comunidades, celebra os sacramentos conforme sua ordenação e serve os pobres em terras distantes ou no próprio país. Chega-se por vocação, formação em um instituto ou diocese missionária e envio pela autoridade eclesiástica, após anos de estudo e vida comunitária. Se for sacerdote ou religioso, vive também o celibato e a obediência; se for leigo, participa conforme seu estado. A missão pede fé, línguas, inculturação e dedicação perseverante."
        )
    ),
    ChurchOffice(
        id = "office_diacono",
        name = LocalizedText("Diácono", "Deacon", "Diácono"),
        description = LocalizedText(
            "Sirve en la caridad, la liturgia de la Palabra y el altar, proclama el Evangelio, predica la homilía, bautiza, asiste a matrimonios y preside funerales, sin celebrar la Eucaristía como presidente principal. Se llega por vocación, discernimiento, formación bíblica y teológica, y ordenación diaconal por el obispo. Según el derecho canónico, se requiere la edad mínima de 25 años; el diácono permanente puede estar casado, mientras el que aspira al sacerdocio asume el celibato. La ordenación lo configura a Cristo servidor.",
            "He serves in charity, in the Liturgy of the Word and at the altar, proclaims the Gospel, preaches the homily, baptizes, assists at marriages, and presides at funerals, without celebrating the Eucharist as the principal celebrant. He is called through vocation, discernment, biblical and theological formation, and diaconal ordination by the bishop. Canon law requires a minimum age of 25; a permanent deacon may be married, while one aspiring to the priesthood embraces celibacy. Ordination configures him to Christ the servant.",
            "Serve na caridade, na Liturgia da Palavra e no altar, proclama o Evangelho, prega a homilia, batiza, assiste casamentos e preside funerais, sem celebrar a Eucaristia como celebrante principal. Chega-se por vocação, discernimento, formação bíblica e teológica e ordenação diaconal pelo bispo. O direito canónico exige a idade mínima de 25 anos; o diácono permanente pode ser casado, enquanto o que aspira ao sacerdócio assume o celibato. A ordenação o configura a Cristo servo."
        )
    ),
    ChurchOffice(
        id = "office_subdiacono_historico",
        name = LocalizedText("Subdiácono (histórico)", "Subdeacon (historical)", "Subdiácono (histórico)"),
        description = LocalizedText(
            "En la tradición latina servía en la Misa cantando la Epístola, preparando las ofrendas y asistiendo al diácono, como grado intermedio entre el acólito y el diácono. Se llegaba por la ordenación menor y mayor según la disciplina antigua, tras el acólito y la formación en el seminario. En la Iglesia latina moderna quedó en desuso tras el Vaticano II y hoy solo se conserva en comunidades tradicionalistas y en iglesias orientales con funciones semejantes. Su memoria recuerda la progresión gradual hacia las órdenes sacras y el servicio litúrgico.",
            "In the Latin tradition he served at Mass by chanting the Epistle, preparing the offerings, and assisting the deacon, as an intermediate grade between acolyte and deacon. He was reached through minor and major ordination according to ancient discipline, after the acolyte and seminary formation. In the modern Latin Church it fell out of use after Vatican II and is now retained only in traditionalist communities and in Eastern churches with similar functions. Its memory recalls the gradual progression toward sacred orders and liturgical service.",
            "Na tradição latina, servia na Missa cantando a Epístola, preparando as oferendas e auxiliando o diácono, como grau intermediário entre acólito e diácono. Chegava-se pela ordenação menor e maior conforme a disciplina antiga, após o acólito e a formação em seminário. Na Igreja latina moderna caiu em desuso após o Vaticano II e hoje se conserva apenas em comunidades tradicionalistas e em igrejas orientais com funções semelhantes. Sua memória lembra a progressão gradual rumo às ordens sacras e ao serviço litúrgico."
        )
    ),
    ChurchOffice(
        id = "office_monje",
        name = LocalizedText("Monje", "Monk", "Monge"),
        description = LocalizedText(
            "Vive en monasterio bajo una Regla, consagrado a la oración, el trabajo y la vida comunitaria como separación del mundo por amor a Dios. Se llega por vocación, postulantado, noviciado, profesión temporal y profesión solemne de los votos de pobreza, castidad y obediencia. No requiere en sí la ordenación sacerdotal, aunque muchos monjes son sacerdotes, y el celibato se vive como parte de la consagración. Su testimonio de estabilidad y silencio es un apostolado de intercesión.",
            "He lives in a monastery under a Rule, consecrated to prayer, work, and community life as a separation from the world for love of God. He is called through vocation, postulancy, novitiate, temporary profession, and solemn profession of the vows of poverty, chastity, and obedience. Priesthood is not in itself required, although many monks are priests, and celibacy is lived as part of the consecration. His witness of stability and silence is an apostolate of intercession.",
            "Vive em mosteiro sob uma Regra, consagrado à oração, ao trabalho e à vida comunitária como separação do mundo por amor a Deus. Chega-se por vocação, postulantado, noviciado, profissão temporária e profissão solene dos votos de pobreza, castidade e obediência. O sacerdócio não é exigido em si, embora muitos monges sejam sacerdotes, e o celibato é vivido como parte da consagração. Seu testemunho de estabilidade e silêncio é um apostolado de intercessão."
        )
    ),
    ChurchOffice(
        id = "office_hermano_religioso",
        name = LocalizedText("Hermano religioso", "Religious Brother", "Irmão religioso"),
        description = LocalizedText(
            "Miembro consagrado de una orden o congregación que vive los votos de pobreza, castidad y obediencia sin ser sacerdote, dedicado a la oración y a obras diversas. Se llega por vocación, formación en la misma comunidad, noviciado, profesión temporal y profesión perpetua o solemne. No necesita ordenación sagrada y su estado es pleno en la vida religiosa, aportando en la enseñanza, la salud, la técnica o el acompañamiento. Su identidad es la fraternidad y la entrega sencilla.",
            "He is a consecrated member of an order or congregation who lives the vows of poverty, chastity, and obedience without being a priest, dedicated to prayer and to various works. He is called through vocation, formation in the same community, novitiate, temporary profession, and perpetual or solemn profession. He does not need sacred ordination and his state is full in religious life, contributing in teaching, health, technical work, or accompaniment. His identity is fraternity and simple dedication.",
            "É membro consagrado de uma ordem ou congregação que vive os votos de pobreza, castidade e obediência sem ser sacerdote, dedicado à oração e a obras diversas. Chega-se por vocação, formação na mesma comunidade, noviciado, profissão temporária e profissão perpétua ou solene. Não precisa de ordenação sagrada e seu estado é pleno na vida religiosa, contribuindo no ensino, na saúde, no trabalho técnico ou no acompanhamento. Sua identidade é a fraternidade e a entrega simples."
        )
    ),
    ChurchOffice(
        id = "office_novicio",
        name = LocalizedText("Novicio", "Novice", "Noviço"),
        description = LocalizedText(
            "Es quien discierne la vocación religiosa viviendo en un noviciado bajo la dirección de un maestro, dedicado a la oración, el estudio de la Regla y la vida común. Se llega tras el postulantado y el ingreso formal a la orden o congregación, con la aprobación de los superiores y en la edad canónica mínima. Durante el noviciado aún no se emiten los votos perpetuos y el celibato se asume como práctica de la vida religiosa. Es un tiempo de prueba, formación y búsqueda de la voluntad de Dios.",
            "He is one who discerns a religious vocation while living in a novitiate under a master, dedicated to prayer, study of the Rule, and common life. He is admitted after the postulancy and formal entry into the order or congregation, with the approval of superiors and at the canonical minimum age. During the novitiate the perpetual vows are not yet made and celibacy is assumed as part of religious life. It is a time of testing, formation, and seeking God's will.",
            "É quem discerne a vocação religiosa vivendo em noviciado sob a direção de um mestre, dedicado à oração, ao estudo da Regra e à vida comum. Entra após o postulantado e o ingresso formal na ordem ou congregação, com a aprovação dos superiores e na idade canónica mínima. Durante o noviciado ainda não se emitem os votos perpétuos e o celibato é assumido como prática da vida religiosa. É um tempo de prova, formação e busca da vontade de Deus."
        )
    ),
    ChurchOffice(
        id = "office_sacerdote",
        name = LocalizedText("Sacerdote", "Priest", "Sacerdote"),
        description = LocalizedText(
            "Preside la Eucaristía, confiere los sacramentos, predica, perdona los pecados y guía al pueblo de Dios como pastor y maestro. Se llega por vocación, admisión al seminario, formación filosófica y teológica de 6 a 8 años, y ordenación sacerdotal por el obispo. En la Iglesia latina se exige celibato, fe recta, santidad de vida y la edad mínima de 25 años. La ordenación lo marca con un carácter indeleble para actuar in persona Christi.",
            "He presides at the Eucharist, confers the sacraments, preaches, forgives sins, and guides the people of God as shepherd and teacher. He is called through vocation, admission to the seminary, philosophical and theological formation of 6 to 8 years, and priestly ordination by the bishop. In the Latin Church celibacy, right faith, holiness of life, and a minimum age of 25 are required. Ordination marks him with an indelible character to act in persona Christi.",
            "Preside a Eucaristia, confere os sacramentos, prega, perdoa os pecados e guia o povo de Deus como pastor e mestre. Chega-se por vocação, admissão ao seminário, formação filosófica e teológica de 6 a 8 anos e ordenação sacerdotal pelo bispo. Na Igreja latina exigem-se celibato, fé reta, santidade de vida e idade mínima de 25 anos. A ordenação o marca com um caráter indelével para agir in persona Christi."
        )
    ),
    ChurchOffice(
        id = "office_parroco",
        name = LocalizedText("Párroco", "Parish Priest", "Pároco"),
        description = LocalizedText(
            "Es el pastor propio de una parroquia, responsable de la liturgia, la predicación, la catequesis, la caridad y la administración de la comunidad confiada. Se llega por nombramiento del obispo diocesano, tras la ordenación sacerdotal, la formación de seminario y la experiencia pastoral como vicario. En el rito latino se exige celibato, edad mínima de 25 años para la ordenación sacerdotal y normalmente al menos 5 años de ministerio previo. Sigue a Cristo, buen pastor, que conoce y cuida a sus ovejas.",
            "He is the own pastor of a parish, responsible for worship, preaching, catechesis, charity, and the administration of the community entrusted to him. He is appointed by the diocesan bishop after priestly ordination, seminary formation, and pastoral experience as a vicar. In the Latin rite celibacy, a minimum age of 25 for priestly ordination, and usually at least 5 years of prior ministry are required. He follows Christ the Good Shepherd who knows and cares for his sheep.",
            "É o pastor próprio de uma paróquia, responsável pela liturgia, pregação, catequese, caridade e administração da comunidade confiada. É nomeado pelo bispo diocesano após a ordenação sacerdotal, a formação em seminário e a experiência pastoral como vigário. No rito latino exigem-se celibato, idade mínima de 25 anos para a ordenação sacerdotal e normalmente pelo menos 5 anos de ministério anterior. Segue a Cristo bom pastor que conhece e cuida de suas ovelhas."
        )
    ),
    ChurchOffice(
        id = "office_vicario_parroquial",
        name = LocalizedText("Vicario parroquial", "Parochial Vicar", "Vigário paroquial"),
        description = LocalizedText(
            "Ayuda al párroco en la cura pastoral de la parroquia, celebra la Misa, predica, catequiza, visita enfermos y atiende sacramentos y obras de caridad. Se llega por nombramiento del obispo tras la ordenación sacerdotal, con seminario de 6 a 8 años y la edad mínima de 25 años. En la Iglesia latina asume el celibato y la obediencia al obispo diocesano. Su ministerio es un aprendizaje de la paternidad espiritual y de la corresponsabilidad.",
            "He assists the parish priest in the pastoral care of the parish, celebrates Mass, preaches, teaches the catechism, visits the sick, and attends to sacraments and works of charity. He is appointed by the bishop after priestly ordination, a seminary of 6 to 8 years, and the minimum age of 25. In the Latin Church he undertakes celibacy and obedience to the diocesan bishop. His ministry is a learning of spiritual fatherhood and shared responsibility.",
            "Auxilia o pároco no cuidado pastoral da paróquia, celebra a Missa, prega, catequiza, visita os doentes e atende os sacramentos e as obras de caridade. É nomeado pelo bispo após a ordenação sacerdotal, o seminário de 6 a 8 anos e a idade mínima de 25 anos. Na Igreja latina assume o celibato e a obediência ao bispo diocesano. Seu ministério é um aprendizado da paternidade espiritual e da corresponsabilidade."
        )
    ),
    ChurchOffice(
        id = "office_canonigo",
        name = LocalizedText("Canónigo", "Canon", "Cônego"),
        description = LocalizedText(
            "Es un sacerdote distinguido en una catedral o colegiata, dedicado al culto litúrgico solemne, al consejo del obispo y a tareas pastorales y jurídicas especiales. Se llega por nombramiento o provisión canónica del obispo o capítulo, tras la ordenación sacerdotal y una trayectoria de servicio. No es un grado del sacramento del orden, sino una función de honor y responsabilidad. Se requiere celibato en el rito latino, formación sólida y buena reputación.",
            "He is a priest distinguished in a cathedral or collegiate church, dedicated to solemn liturgical worship, advising the bishop, and to special pastoral and juridical tasks. He is reached by canonical appointment or provision by the bishop or chapter, after priestly ordination and a record of service. It is not a grade of the sacrament of Order, but a function of honor and responsibility. Celibacy in the Latin rite, solid formation, and good reputation are required.",
            "É um sacerdote distinguido em catedral ou colegiada, dedicado ao culto litúrgico solene, ao conselho do bispo e a tarefas pastorais e jurídicas especiais. Chega-se por nomeação ou provisão canónica do bispo ou cabido, após a ordenação sacerdotal e uma trajetória de serviço. Não é um grau do sacramento da Ordem, mas uma função de honra e responsabilidade. Exigem-se celibato no rito latino, formação sólida e boa reputação."
        )
    ),
    ChurchOffice(
        id = "office_obispo",
        name = LocalizedText("Obispo", "Bishop", "Bispo"),
        description = LocalizedText(
            "Es el pastor de una diócesis, sucesor de los apóstoles, que enseña, santifica y gobierna, confiere la Confirmación y las órdenes y ordena el culto y la misión. Se llega por nombramiento del Papa, previa consulta y propuesta, tras haber sido sacerdote durante años, con formación de seminario de 6 a 8 años. El derecho canónico exige al menos 30 años de edad, celibato y haber recibido la ordenación sacerdotal al menos 5 años antes. Su lema suele expresar la fidelidad a su ministerio.",
            "He is the shepherd of a diocese, successor of the apostles, who teaches, sanctifies, and governs, confers Confirmation and Orders, and orders worship and mission. He is appointed by the Pope, after consultation and proposal, having been a priest for years with seminary formation of 6 to 8 years. Canon law requires at least 30 years of age, celibacy, and priestly ordination at least 5 years earlier. His motto usually expresses fidelity to his ministry.",
            "É o pastor de uma diocese, sucessor dos apóstolos, que ensina, santifica e governa, confere a Crisma e as ordens e ordena o culto e a missão. É nomeado pelo Papa, após consulta e proposta, tendo sido sacerdote por anos com formação de seminário de 6 a 8 anos. O direito canónico exige pelo menos 30 anos de idade, celibato e ordenação sacerdotal há pelo menos 5 anos. Seu lema costuma expressar a fidelidade ao seu ministério."
        )
    ),
    ChurchOffice(
        id = "office_arzobispo",
        name = LocalizedText("Arzobispo", "Archbishop", "Arcebispo"),
        description = LocalizedText(
            "Es un obispo que preside una sede archidiocesana de mayor importancia, con responsabilidades de coordinación y a veces de primacía regional. Se llega por nombramiento del Papa tras un episcopado previo o destacada trayectoria sacerdotal, tras el seminario y la ordenación con la edad mínima de 30 años. En el rito latino vive el celibato y ejerce la plenitud del episcopado. Su palio es signo de comunión con la Santa Sede y de autoridad metropolitana.",
            "He is a bishop who presides over a major archdiocesan see, with responsibilities of coordination and sometimes regional primacy. He is appointed by the Pope after previous episcopal ministry or a distinguished priestly record, following seminary and ordination at the minimum age of 30. In the Latin rite he lives celibacy and exercises the fullness of the episcopate. His pallium is a sign of communion with the Holy See and of metropolitan authority.",
            "É um bispo que preside uma sede arquidiocesana de maior importância, com responsabilidades de coordenação e às vezes de primazia regional. É nomeado pelo Papa após um episcopado prévio ou destacada trajetória sacerdotal, após seminário e ordenação na idade mínima de 30 anos. No rito latino vive o celibato e exerce a plenitude do episcopado. Seu pálio é sinal de comunhão com a Santa Sé e de autoridade metropolitana."
        )
    ),
    ChurchOffice(
        id = "office_metropolitano",
        name = LocalizedText("Metropolitano", "Metropolitan", "Metropolitano"),
        description = LocalizedText(
            "Es el arzobispo de una provincia eclesiástica que reúne varias diócesis, vela por la comunión y ejerce funciones de vigilancia y coordinación. Se llega por nombramiento papal entre los obispos y arzobispos de la provincia, con la edad mínima de 30 años y sacerdocio previo. No recibe un sacramento distinto, sino una jurisdicción y una precedencia de honor. Su papel incluye convocar el concilio provincial y ser consultado en la vida de las sed sufragáneas.",
            "He is the archbishop of an ecclesiastical province that gathers several dioceses, watches over communion, and exercises functions of oversight and coordination. He is appointed by the Pope from among the bishops and archbishops of the province, with a minimum age of 30 and prior priesthood. He does not receive a distinct sacrament, but a jurisdiction and a precedence of honor. His role includes convening the provincial council and being consulted in the life of the suffragan sees.",
            "É o arcebispo de uma província eclesiástica que reúne várias dioceses, vela pela comunhão e exerce funções de vigilância e coordenação. É nomeado pelo Papa entre os bispos e arcebispos da província, com idade mínima de 30 anos e sacerdócio prévio. Não recebe um sacramento distinto, mas uma jurisdição e uma precedência de honra. Suas funções incluem convocar o concílio provincial e ser consultado na vida das sedes sufragâneas."
        )
    ),
    ChurchOffice(
        id = "office_patriarca",
        name = LocalizedText("Patriarca", "Patriarch", "Patriarca"),
        description = LocalizedText(
            "Es el obispo de un antiguo patriarcado, padre y primado de una Iglesia particular oriental o título de honor en la Iglesia latina, con autoridad sobre su rito y territorio. Se llega por elección canónica o nombramiento papal según la disciplina de cada Iglesia, tras larga trayectoria episcopal. Se requiere la plenitud del episcopado, edad mínima de 30 años y, en el rito latino, celibato. Su ministerio guarda la tradición apostólica y la comunión entre las Iglesias.",
            "He is the bishop of an ancient patriarchate, father and primate of a particular Eastern Church or a title of honor in the Latin Church, with authority over his rite and territory. He is reached by canonical election or papal appointment according to the discipline of each Church, after a long episcopal record. Fullness of the episcopate, a minimum age of 30, and, in the Latin rite, celibacy are required. His ministry guards apostolic tradition and communion among the Churches.",
            "É o bispo de um antigo patriarcado, pai e primaz de uma Igreja particular oriental ou título de honra na Igreja latina, com autoridade sobre seu rito e território. Chega-se por eleição canónica ou nomeação papal conforme a disciplina de cada Igreja, após longa trajetória episcopal. Exigem-se a plenitude do episcopado, idade mínima de 30 anos e, no rito latino, celibato. Seu ministério guarda a tradição apostólica e a comunhão entre as Igrejas."
        )
    ),
    ChurchOffice(
        id = "office_cardenal",
        name = LocalizedText("Cardenal", "Cardinal", "Cardeal"),
        description = LocalizedText(
            "Es un eclesiástico elevado por el Papa para ayudarle en el gobierno de la Iglesia universal, aconsejarle y, si tiene menos de 80 años, elegir al Romano Pontífice. Se llega por creación del Papa, sin edad canónica fija, tras haber sido obispo o presbítero con años de servicio. Normalmente es obispo y, en el rito latino, célibe, aunque el presbítero cardenal también asume el celibato. Su púrpura simboliza la fidelidad hasta el martirio y su servicio a la unidad.",
            "He is an ecclesiastic raised by the Pope to assist him in governing the universal Church, to advise him, and, if under 80, to elect the Roman Pontiff. He is created by the Pope, with no fixed canonical age, after having been a bishop or priest with years of service. He is normally a bishop and, in the Latin rite, celibate, although a cardinal priest also embraces celibacy. His purple symbolizes fidelity even to martyrdom and service to unity.",
            "É um eclesiástico elevado pelo Papa para auxiliá-lo no governo da Igreja universal, aconselhá-lo e, se tiver menos de 80 anos, eleger o Romano Pontífice. É criado pelo Papa, sem idade canónica fixa, após ter sido bispo ou presbítero com anos de serviço. Normalmente é bispo e, no rito latino, célibe, embora o cardeal presbítero também assuma o celibato. Seu vermelho simboliza a fidelidade até o martírio e o serviço à unidade."
        )
    ),
    ChurchOffice(
        id = "office_papa",
        name = LocalizedText("Papa", "Pope", "Papa"),
        description = LocalizedText(
            "Es el Obispo de Roma y Sucesor de San Pedro, cabeza del Colegio Episcopal y pastor de la Iglesia universal, con potestad suprema de jurisdicción. Se llega por elección libre del Colegio de Cardenales reunido en cónclave, entre sus miembros o, raramente, entre otros obispos y sacerdotes. Se requiere la plenitud del orden sagrado, y si el elegido no es obispo, lo recibe de inmediato tras la elección. Aceptada la elección, asume de hecho el ministerio petrino de enseñar, santificar y gobernar.",
            "He is the Bishop of Rome and Successor of Saint Peter, head of the College of Bishops and pastor of the universal Church, with supreme power of jurisdiction. He is elected by the free vote of the College of Cardinals gathered in conclave, from among its members or, rarely, from among other bishops and priests. The fullness of sacred orders is required, and if the elected man is not a bishop he receives it immediately after the election. Having accepted the election, he at once assumes the Petrine ministry of teaching, sanctifying, and governing.",
            "É o Bispo de Roma e Sucessor de São Pedro, cabeça do Colégio Episcopal e pastor da Igreja universal, com suprema potestade de jurisdição. É eleito por voto livre do Colégio de Cardeais reunido em conclave, entre seus membros ou, raramente, entre outros bispos e sacerdotes. Exige-se a plenitude da ordem sagrada e, se o eleito não for bispo, recebe-a imediatamente após a eleição. Aceita a eleição, assume de imediato o ministério petrino de ensinar, santificar e governar."
        )
    ),
    ChurchOffice(
        id = "office_monja",
        name = LocalizedText("Monja", "Nun", "Freira"),
        description = LocalizedText(
            "Es una mujer consagrada que vive en clausura o vida activa bajo votos de pobreza, castidad y obediencia, dedicada a la oración y a la vida contemplativa o apostólica. Se llega por vocación, vida en comunidad, noviciado, profesión temporal y profesión solemne. En la tradición clásica, la monja de clausura observa un estilo de vida más retirado y el celibato como parte de la consagración. Su vida oculta es sustento espiritual de la Iglesia y de las almas.",
            "She is a consecrated woman who lives in enclosure or active life under vows of poverty, chastity, and obedience, dedicated to prayer and to contemplative or apostolic life. She is called through vocation, community life, novitiate, temporary profession, and solemn profession. In the classical tradition, an enclosed nun observes a more withdrawn way of life and celibacy as part of the consecration. Her hidden life sustains spiritually the Church and souls.",
            "É uma mulher consagrada que vive em clausura ou vida ativa sob voto de pobreza, castidade e obediência, dedicada à oração e à vida contemplativa ou apostólica. Chega-se por vocação, vida em comunidade, noviciado, profissão temporária e profissão solene. Na tradição clássica, a freira de clausura observa um estilo de vida mais recolhido e o celibato como parte da consagração. Sua vida escondida sustenta espiritualmente a Igreja e as almas."
        )
    ),
    ChurchOffice(
        id = "office_hermana_religiosa",
        name = LocalizedText("Hermana religiosa", "Religious Sister", "Irmã religiosa"),
        description = LocalizedText(
            "Es una mujer consagrada miembro de una congregación u orden femenina, que vive los votos de pobreza, castidad y obediencia y sirve en la educación, la salud, la caridad o la misión. Se llega por vocación, formación en comunidad, noviciado, profesión temporal y profesión perpetua o solemne. No requiere ordenación sagrada y su celibato es parte del testimonio evangélico. Su fraternidad sostiene obras y comunidades enteras.",
            "She is a consecrated woman, a member of a female congregation or order, who lives the vows of poverty, chastity, and obedience and serves in education, health, charity, or mission. She is called through vocation, formation in community, novitiate, temporary profession, and perpetual or solemn profession. She does not require sacred ordination and her celibacy is part of the evangelical witness. Her fraternity sustains entire works and communities.",
            "É uma mulher consagrada, membro de uma congregação ou ordem feminina, que vive os votos de pobreza, castidade e obediência e serve na educação, na saúde, na caridade ou na missão. Chega-se por vocação, formação em comunidade, noviciado, profissão temporária e profissão perpétua ou solene. Não exige ordenação sagrada e seu celibato faz parte do testemunho evangélico. Sua fraternidade sustenta obras e comunidades inteiras."
        )
    ),
    ChurchOffice(
        id = "office_madre_superiora",
        name = LocalizedText("Madre superiora", "Mother Superior", "Madre superiora"),
        description = LocalizedText(
            "Es la superiora elegida o nombrada para una comunidad o casa religiosa, que guía espiritualmente a sus hermanas, administra la vida común y representa a la comunidad. Se llega tras años de profesión religiosa, experiencia de vida en comunidad y elección por el capítulo o nombramiento por los superiores mayores. Emite los votos de pobreza, castidad y obediencia y asume el celibato. Su autoridad es servicio humilde y maternal al estilo de Cristo.",
            "She is the superior elected or appointed for a religious community or house, who guides her sisters spiritually, administers community life, and represents the community. She is reached after years of religious profession, experience of community life, and election by the chapter or appointment by higher superiors. She makes the vows of poverty, chastity, and obedience and embraces celibacy. Her authority is humble and maternal service after the likeness of Christ.",
            "É a superiora eleita ou nomeada para uma comunidade ou casa religiosa, que guia espiritualmente suas irmãs, administra a vida comum e representa a comunidade. Chega-se após anos de profissão religiosa, experiência de vida comunitária e eleição pelo capítulo ou nomeação pelos superiores maiores. Emite os votos de pobreza, castidade e obediência e assume o celibato. Sua autoridade é serviço humilde e maternal ao estilo de Cristo."
        )
    ),
    ChurchOffice(
        id = "office_abadesa",
        name = LocalizedText("Abadesa", "Abbess", "Abadessa"),
        description = LocalizedText(
            "Es la superiora de un monasterio femenino, elegida por las hermanas profesas y confirmada según la Regla, con autoridad de gobierno y cuidado espiritual. Se llega por profesión solemne de los votos, vida prolongada de oración y disciplina monástica, y elección canónica. Vive en celibato y obediencia bajo la Regla de la orden. Su figura conserva la tradición de las grandes abadesas que rigieron monasterios y escuelas.",
            "She is the superior of a women's monastery, elected by the professed sisters and confirmed according to the Rule, with authority of government and spiritual care. She is reached through solemn profession of the vows, prolonged life of prayer and monastic discipline, and canonical election. She lives in celibacy and obedience under the Rule of the order. Her figure preserves the tradition of great abbesses who governed monasteries and schools.",
            "É a superiora de um mosteiro feminino, eleita pelas irmãs professas e confirmada conforme a Regra, com autoridade de governo e cuidado espiritual. Chega-se pela profissão solene dos votos, vida prolongada de oração e disciplina monástica e eleição canónica. Vive em celibato e obediência sob a Regra da ordem. Sua figura preserva a tradição das grandes abadessas que regeram mosteiros e escolas."
        )
    ),
    ChurchOffice(
        id = "office_laico_consagrado",
        name = LocalizedText("Laico consagrado", "Consecrated Layperson", "Leigo consagrado"),
        description = LocalizedText(
            "Es un laico que, sin ser sacerdote ni religioso de votos clásicos, se consagra a Dios mediante una forma de vida eclesial aprobada, como instituto secular o asociación de vida apostólica. Se llega por vocación, formación específica, compromiso temporal y consagración definitiva según los estatutos del instituto. No requiere orden sagrada y puede permanecer en su profesión y vida familiar o célibe según la norma. Su testimonio lleva la santidad al corazón del mundo.",
            "He is a layperson who, without being a priest or a religious of classic vows, consecrates himself to God through an approved form of ecclesial life, such as a secular institute or a society of apostolic life. He is called through vocation, specific formation, temporary commitment, and definitive consecration according to the institute's statutes. He does not require sacred orders and may remain in his profession and family or celibate life according to the norm. His witness carries holiness into the heart of the world.",
            "É um leigo que, sem ser sacerdote nem religioso de votos clássicos, consagra-se a Deus por meio de uma forma de vida eclesial aprovada, como instituto secular ou sociedade de vida apostólica. Chega-se por vocação, formação específica, compromisso temporário e consagração definitiva segundo os estatutos do instituto. Não exige ordens sacras e pode permanecer em sua profissão e vida familiar ou celibatária conforme a norma. Seu testemunho leva a santidade ao coração do mundo."
        )
    ),
    ChurchOffice(
        id = "office_misionero_laico",
        name = LocalizedText("Misionero laico", "Lay Missionary", "Missionário leigo"),
        description = LocalizedText(
            "Es un bautizado que, sin orden sagrada, se dedica a evangelizar, enseñar el catecismo, servir a los pobres y animar comunidades en la misión ad gentes. Se llega por vocación, formación bíblica, teológica, lingüística y cultural, y envío por la Iglesia o una organización misionera. Puede vivir en familia o en celibato apostólico según su estado y compromiso. Su laicado es signo de una Iglesia en salida.",
            "He is a baptized person who, without sacred orders, dedicates himself to evangelizing, teaching the catechism, serving the poor, and fostering communities in the ad gentes mission. He is called through vocation, biblical, theological, linguistic, and cultural formation, and sending by the Church or a missionary organization. He may live in family life or apostolic celibacy according to his state and commitment. His lay character is a sign of a Church that goes forth.",
            "É um batizado que, sem ordens sacras, dedica-se a evangelizar, ensinar o catecismo, servir os pobres e animar comunidades na missão ad gentes. Chega-se por vocação, formação bíblica, teológica, linguística e cultural, e envio pela Igreja ou por uma organização missionária. Pode viver em família ou em celibato apostólico conforme seu estado e compromisso. Sua condição leiga é sinal de uma Igreja em saída."
        )
    ),
    ChurchOffice(
        id = "office_madre_general",
        name = LocalizedText("Madre general", "Mother General", "Madre geral"),
        description = LocalizedText(
            "Es la superiora mayor de toda una congregación u orden femenina, elegida por el capítulo general para gobernar, coordinar las provincias y custodiar el carisma. Se llega tras una larga vida de profesión religiosa, experiencia de gobierno y elección canónica del capítulo general. Emite los votos de pobreza, castidad y obediencia y vive el celibato consagrado. Su ministerio unifica y anima la vida de muchas comunidades y obras.",
            "She is the major superior of an entire female congregation or order, elected by the general chapter to govern, coordinate the provinces, and safeguard the charism. She is reached after a long life of religious profession, experience of government, and canonical election by the general chapter. She makes the vows of poverty, chastity, and obedience and lives consecrated celibacy. Her ministry unifies and enlivens the life of many communities and works.",
            "É a superiora maior de toda uma congregação ou ordem feminina, eleita pelo capítulo geral para governar, coordenar as províncias e guardar o carisma. Chega-se após uma longa vida de profissão religiosa, experiência de governo e eleição canónica do capítulo geral. Emite os votos de pobreza, castidade e obediência e vive o celibato consagrado. Seu ministério unifica e anima a vida de muitas comunidades e obras."
        )
    )
    )
}
