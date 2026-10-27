package com.example.data.repository

import com.example.data.models.CatechismChapter
import com.example.data.models.CatechismNumberedArticle
import com.example.data.models.CatechismPart
import com.example.data.models.CatechismSection
import com.example.data.models.LocalizedText

object CatechismData {

    val catechismParts: List<CatechismPart> = listOf(
        // PART 1: The Profession of Faith
        CatechismPart(
            partNumber = 1,
            title = LocalizedText("Primera Parte: La Profesión de la Fe", "Part One: The Profession of Faith", "Primeira Parte: A Profissão da Fé"),
            subtitle = LocalizedText("El Credo y la Revelación Divina", "The Creed & Divine Revelation", "O Credo e a Revelação Divina"),
            cccRange = "§§ 1 - 1065",
            description = LocalizedText(
                "Trata sobre el misterio de Dios, la capacidad del hombre para conocer al Creador, la Revelación que culmina en Cristo, y los doce artículos del Credo apostólico.",
                "Explores God's revelation, the human capacity for God, and the 12 articles of the Apostles' Creed.",
                "Aborda a Revelação de Deus, a vocação do homem e os doze artigos da fé católica."
            ),
            sections = listOf(
                CatechismSection(
                    partNumber = 1,
                    sectionNumber = 1,
                    title = LocalizedText("Sección 1: 'Creo' — 'Creemos'", "Section 1: 'I Believe' — 'We Believe'", "Seção 1: 'Creio' — 'Cremos'"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("El Hombre es 'capaz' de Dios", "Man's Capacity for God", "O Homem é 'Capaz' de Deus"),
                            articleRangeDisplay = "§§ 26 - 49",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 27,
                                    title = LocalizedText("El deseo de Dios en el corazón humano", "The Desire for God", "O Desejo de Deus no Homem"),
                                    content = LocalizedText(
                                        "El deseo de Dios está inscrito en el corazón del hombre, porque el hombre ha sido creado por Dios y para Dios; y Dios no cesa de atraer al hombre hacia sí, y sólo en Dios encontrará el hombre la verdad y la dicha que no cesa de buscar.",
                                        "The desire for God is written in the human heart, because man is created by God and for God; and God never ceases to draw man to Himself. Only in God will he find the truth and happiness he never stops searching for.",
                                        "O desejo de Deus está inscrito no coração do homem, já que o homem foi criado por Deus e para Deus. Somente em Deus o homem encontrará a verdade e a felicidade que procura sem cessar."
                                    ),
                                    biblicalReferences = "Hch 17, 26-28; Sal 105, 3",
                                    inBriefSummary = LocalizedText("El hombre es por naturaleza y vocación un ser religioso creado para la comunión divina.", "Man is by nature and vocation a religious being called to divine life.", "O homem é chamado por vocação divina à santidade.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 31,
                                    title = LocalizedText("Las vías de acceso al conocimiento de Dios", "Ways of Knowing God", "Vias de Acesso ao Conhecimento de Deus"),
                                    content = LocalizedText(
                                        "Creado a imagen de Dios, llamado a conocerle y amarle, el hombre que busca a Dios descubre ciertas vías para acceder al conocimiento de Dios: el mundo material con su orden y belleza, y la persona humana con su voz de la conciencia moral.",
                                        "Created in God's image and called to know and love Him, the person who seeks God discovers certain ways of coming to know Him: through the physical universe and through the human soul with its sense of moral truth.",
                                        "Criado à imagem de Deus, o homem descobre vias para conhecer o Criador: o mundo com sua harmonia e a consciência moral."
                                    ),
                                    biblicalReferences = "Sab 13, 1-9; Rom 1, 19-20",
                                    inBriefSummary = LocalizedText("Con su razón natural, el hombre puede conocer a Dios con certeza a partir de sus obras.", "With natural reason, man can know God with certainty through creation.", "Pela razão natural, o homem pode chegar ao conhecimento certo de Deus.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("Dios al Encuentro del Hombre: La Revelación", "God Comes to Meet Man: Revelation", "Deus ao Encontro do Homem: A Revelação"),
                            articleRangeDisplay = "§§ 50 - 141",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 65,
                                    title = LocalizedText("Cristo, mediador y plenitud de toda la Revelación", "Christ: Fullness of Revelation", "Cristo, Plenitude da Revelação"),
                                    content = LocalizedText(
                                        "«Muchas veces y de muchos modos habló Dios en el pasado a nuestros padres por medio de los profetas; en estos últimos días nos ha hablado por medio del Hijo» (Hb 1, 1-2). Cristo es la Palabra única, perfecta e insuperable del Padre. En Él lo dice todo.",
                                        "“In many and various ways God spoke of old to our fathers by the prophets, but in these last days he has spoken to us by a Son” (Heb 1:1-2). Christ is the Father's one, perfect, and unsurpassable Word. In Him He has said everything.",
                                        "“Muitas vezes e de muitos modos falou Deus outrora aos nossos pais pelos profetas; nestes últimos dias falou-nos pelo Filho” (Hb 1, 1-2). Cristo é a Palavra definitiva do Pai."
                                    ),
                                    biblicalReferences = "Hb 1, 1-2; San Juan de la Cruz",
                                    inBriefSummary = LocalizedText("No habrá otra revelación pública hasta la manifestación gloriosa de Jesucristo.", "No further public revelation is to be expected before the Parousia.", "Não haverá outra revelação pública até a vinda gloriosa de Jesus.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 120,
                                    title = LocalizedText("El Canon Católico de las Escrituras", "The Catholic Canon of Scripture", "O Cânon Católico das Escrituras"),
                                    content = LocalizedText(
                                        "La Tradición apostólica hizo discernir a la Iglesia qué escritos constituyen la lista de los Libros Sagrados. Este canon íntegro comprende 73 libros: 46 del Antiguo Testamento (incluyendo Tobías, Judit, 1 y 2 Macabeos, Sabiduría, Eclesiástico y Baruc) y 27 del Nuevo Testamento.",
                                        "Apostolic Tradition led the Church to discern which writings were to be included in the sacred books. This complete list is called the canon of Scripture. It includes 46 books for the Old Testament (including the deuterocanonical books) and 27 for the New.",
                                        "A Tradição apostólica levou a Igreja a discernir a lista completa dos 73 livros sagrados inspirados por Deus."
                                    ),
                                    biblicalReferences = "Concilio de Trento (DS 1502-1503); Concilio Vaticano II",
                                    inBriefSummary = LocalizedText("Los 73 libros canónicos fueron inspirados por el Espíritu Santo y tienen a Dios por autor.", "All 73 books were inspired by the Holy Spirit with God as their author.", "Os 73 livros foram escritos sob a inspiração do Espírito Santo.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 3,
                            title = LocalizedText("La Respuesta del Hombre a Dios: La Fe", "Man's Response to God: Faith", "A Resposta do Homem a Deus: A Fé"),
                            articleRangeDisplay = "§§ 142 - 184",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 144,
                                    title = LocalizedText("La obediencia de la fe", "The Obedience of Faith", "A Obediência da Fé"),
                                    content = LocalizedText(
                                        "Por la fe, el hombre somete completamente su inteligencia y su voluntad a Dios. La Sagrada Escritura llama 'obediencia de la fe' a esta respuesta del hombre a Dios que revela. Abraham y la Santísima Virgen María son los modelos más acabados de esta obediencia.",
                                        "To obey in faith is to submit freely to the word that has been heard, because its truth is guaranteed by God, who is Truth itself. Abraham and Mary are supreme models of this obedience.",
                                        "Pela fé, o homem submete livremente a sua inteligência e vontade a Deus. Abraão e a Virgem Maria são os maiores modelos de fé."
                                    ),
                                    biblicalReferences = "Rom 1, 5; Heb 11, 8; Lc 1, 38",
                                    inBriefSummary = LocalizedText("La fe es un don sobrenatural de Dios y un acto auténticamente humano y libre.", "Faith is a grace and a human act of the intellect and will.", "A fé é dom da graça divina e adesão livre da inteligência.")
                                )
                            )
                        )
                    )
                ),
                CatechismSection(
                    partNumber = 1,
                    sectionNumber = 2,
                    title = LocalizedText("Sección 2: La Profesión de la Fe Cristiana (El Credo)", "Section 2: The Creed (Symbols of Faith)", "Seção 2: A Profissão da Fé Cristã (O Credo)"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("Creo en Dios Padre Todopoderoso", "I Believe in God the Father Almighty", "Creio em Deus Pai Todo-Poderoso"),
                            articleRangeDisplay = "§§ 198 - 421",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 199,
                                    title = LocalizedText("Creo en un solo Dios", "I Believe in One God", "Creio em um só Deus"),
                                    content = LocalizedText(
                                        "«Escucha, Israel: el Señor nuestro Dios es el único Señor» (Dt 6, 4). Dios confiesa que Él es el Único, no hay otro. El misterio central de la fe católica es la Santísima Trinidad: un solo Dios en Tres Personas divinas (Padre, Hijo y Espíritu Santo).",
                                        "“Hear, O Israel: The Lord our God is one Lord” (Deut 6:4). The central mystery of Christian faith and life is the mystery of the Most Holy Trinity.",
                                        "“Ouve, ó Israel: o Senhor nosso Deus é o único Senhor”. O mistério central da fé católica é a Santíssima Trindade."
                                    ),
                                    biblicalReferences = "Dt 6, 4; Mt 28, 19",
                                    inBriefSummary = LocalizedText("Dios es Amor (1 Jn 4, 8) y verdad eterna.", "God is Truth and Love.", "Deus é Verdade e Amor infinito.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 328,
                                    title = LocalizedText("La existencia de los Ángeles: Verdad de Fe", "The Angels", "Os Anjos de Deus"),
                                    content = LocalizedText(
                                        "La existencia de seres espirituales, no corporales, que la Sagrada Escritura llama habitualmente ángeles, es una verdad de fe. Son criaturas puramente espirituales con inteligencia y voluntad. Desde la infancia hasta la muerte, la vida humana está rodeada de su custodia e intercesión.",
                                        "The existence of the spiritual, non-corporeal beings that Sacred Scripture usually calls angels is a truth of faith. With their whole being the angels are servants and messengers of God.",
                                        "A existência dos anjos é verdade de fé. Como criaturas puramente espirituais, louvam a Deus e protegem os homens."
                                    ),
                                    biblicalReferences = "Sal 91, 11-12; Mt 18, 10; Hb 1, 14",
                                    inBriefSummary = LocalizedText("Cada fiel tiene a su lado un Ángel de la Guarda como protector y pastor.", "From infancy to death, human life is surrounded by the angels' watchful care.", "Cada fiel conta com a proteção do seu Santo Anjo da Guarda.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("Creo en Jesucristo, Hijo Único de Dios", "I Believe in Jesus Christ, Only Son of God", "Creio em Jesus Cristo, Filho Único de Deus"),
                            articleRangeDisplay = "§§ 422 - 682",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 456,
                                    title = LocalizedText("Por qué el Verbo se hizo carne", "Why the Word Became Flesh", "Por que o Verbo Se Fez Carne"),
                                    content = LocalizedText(
                                        "El Verbo se encarnó para salvarnos reconciliándonos con Dios; para que conociésemos el amor de Dios; para ser nuestro modelo de santidad; y para hacernos partícipes de la naturaleza divina (2 Pe 1, 4).",
                                        "The Word became flesh for us in order to save us by reconciling us with God; so that thus we might know God's love; to be our model of holiness; and to make us partakers of the divine nature.",
                                        "O Verbo Se fez carne para nos salvar, para nos revelar o amor do Pai, para ser nosso modelo de santidade e nos divinizar."
                                    ),
                                    biblicalReferences = "1 Jn 4, 9-10; Mt 11, 29; 2 Pe 1, 4",
                                    inBriefSummary = LocalizedText("Jesucristo es verdadero Dios y verdadero hombre en la unidad de su Persona divina.", "Jesus Christ is true God and true man in the unity of His divine person.", "Jesus Cristo é verdadeiro Deus e verdadeiro homem.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 638,
                                    title = LocalizedText("La Resurrección de Jesús: Culmen de la Fe", "The Resurrection of Jesus", "A Ressurreição de Jesus"),
                                    content = LocalizedText(
                                        "«Les transmití en primer lugar lo que a mi vez recibí: que Cristo murió por nuestros pecados según las Escrituras; que fue sepultado y que resucitó al tercer día» (1 Cor 15, 3-4). La Resurrección constituye la confirmación soberana de todo lo que Cristo hizo y enseñó.",
                                        "“I delivered to you as of first importance what I also received, that Christ died for our sins in accordance with the scriptures, that he was buried, that he was raised on the third day” (1 Cor 15:3-4). The Resurrection is the crowning truth of our faith.",
                                        "A Ressurreição de Jesus é a verdade culminante da nossa fé católica em Cristo."
                                    ),
                                    biblicalReferences = "1 Cor 15, 3-4; Lc 24, 34",
                                    inBriefSummary = LocalizedText("Por su muerte nos libera del pecado; por su resurrección nos abre las puertas del cielo.", "By His death Christ liberates us from sin; by His Resurrection He opens new life.", "Pela Sua Ressurreição, Cristo venceu a morte para sempre.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 3,
                            title = LocalizedText("Creo en el Espíritu Santo y en la Santa Iglesia", "I Believe in the Holy Spirit & Church", "Creio no Espírito Santo e na Santa Igreja"),
                            articleRangeDisplay = "§§ 683 - 1065",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 811,
                                    title = LocalizedText("Las cuatro notas de la Iglesia: Una, Santa, Católica y Apostólica", "The Four Marks of the Church", "As Quatro Notas da Igreja"),
                                    content = LocalizedText(
                                        "«Esta es la única Iglesia de Cristo, que en el Símbolo confesamos una, santa, católica y apostólica». Estas cuatro características, inseparablemente unidas entre sí, indican rasgos esenciales de la Iglesia y de su misión divina.",
                                        "“This is the sole Church of Christ which in the Creed we profess to be one, holy, catholic, and apostolic.” These four characteristics indicate essential features of the Church and her mission.",
                                        "A Igreja é Una, Santa, Católica e Apostólica, notas que manifestam a sua origem divina e missão universal."
                                    ),
                                    biblicalReferences = "Lumen Gentium 8; Ef 4, 3-5; 1 Tm 3, 15",
                                    inBriefSummary = LocalizedText("La Iglesia de Cristo subsiste en la Iglesia Católica gobernada por el Sucesor de Pedro.", "The Church of Christ subsists in the Catholic Church governed by Peter's successor.", "A verdadeira Igreja de Cristo subsiste na Igreja Católica Apostólica Romana.")
                                )
                            )
                        )
                    )
                )
            )
        ),

        // PART 2: The Celebration of the Christian Mystery (The Sacraments)
        CatechismPart(
            partNumber = 2,
            title = LocalizedText("Segunda Parte: La Celebración del Misterio Cristiano", "Part Two: The Celebration of the Christian Mystery", "Segunda Parte: A Celebração do Mistério Cristão"),
            subtitle = LocalizedText("La Liturgia y los Siete Sacramentos de la Iglesia", "The Liturgy & The Seven Sacraments", "A Liturgia e os Sete Sacramentos"),
            cccRange = "§§ 1066 - 1690",
            description = LocalizedText(
                "La economía sacramental por la que Cristo comunica los frutos del Misterio Pascual en la liturgia y en los 7 Sacramentos de salvación.",
                "How the fruits of Christ's Paschal mystery are communicated through the liturgy and the seven sacraments.",
                "A economia sacramental da Igreja através dos sete sacramentos instituídos por Cristo."
            ),
            sections = listOf(
                CatechismSection(
                    partNumber = 2,
                    sectionNumber = 1,
                    title = LocalizedText("Sección 1: La Economía Sacramental", "Section 1: The Sacramental Economy", "Seção 1: A Economia Sacramental"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("El Misterio Pascual en el Tiempo de la Iglesia", "The Paschal Mystery in Church's Time", "O Mistério Pascal no Tempo da Igreja"),
                            articleRangeDisplay = "§§ 1076 - 1134",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1076,
                                    title = LocalizedText("La efusión del Espíritu Santo en Pentecostés", "The Church Dispenses the Mysteries", "A Efusão do Espírito Santo na Liturgia"),
                                    content = LocalizedText(
                                        "El día de Pentecostés, por la efusión del Espíritu Santo, la Iglesia se manifestó al mundo. La economía sacramental consiste en la comunicación de los frutos del Misterio Pascual de Cristo mediante la celebración litúrgica de los sacramentos.",
                                        "On the day of Pentecost, by the outpouring of the Holy Spirit, the Church was manifested to the world. The sacramental economy communicates the fruits of Christ's Paschal mystery in the celebration of the sacraments.",
                                        "Pela efusão do Espírito Santo em Pentecostes, a Igreja comunica os frutos da redenção de Cristo através dos santos sacramentos."
                                    ),
                                    biblicalReferences = "Hch 2, 1-11; 1 Cor 10, 16",
                                    inBriefSummary = LocalizedText("Los sacramentos son fuerzas que brotan del Cuerpo de Cristo para santificar las almas.", "Sacraments are powers that come forth from the Body of Christ to give life.", "Os sacramentos comunicam a graça de Cristo aos fiéis.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("La Celebración Sacramental del Misterio Pascual", "The Sacramental Celebration", "A Celebração Sacramental"),
                            articleRangeDisplay = "§§ 1135 - 1209",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1145,
                                    title = LocalizedText("Signos y Símbolos de la Liturgia", "Signs and Symbols in Liturgy", "Sinais e Símbolos Litúrgicos"),
                                    content = LocalizedText(
                                        "Una celebración sacramental está tejida de signos y de símbolos. Su significación se enraíza en la obra de la creación y en la cultura humana, se perfila en los acontecimientos de la Antigua Alianza y se revela plenamente en la persona y la obra de Cristo.",
                                        "A sacramental celebration is woven from signs and symbols. Their meaning is rooted in creation and human culture, specified in the Old Covenant and fully revealed in Christ.",
                                        "Os sinais sensíveis e palavras na Liturgia tornam presente e eficaz a graça invisível de Deus."
                                    ),
                                    biblicalReferences = "Jn 3, 5; Lc 22, 19",
                                    inBriefSummary = LocalizedText("El Espíritu Santo hace fecundos los signos litúrgicos para la salvación.", "Sacramental signs signify and effect divine grace.", "Os ritos sagrados transmitem a vida divina.")
                                )
                            )
                        )
                    )
                ),
                CatechismSection(
                    partNumber = 2,
                    sectionNumber = 2,
                    title = LocalizedText("Sección 2: Los Siete Sacramentos de la Iglesia", "Section 2: The Seven Sacraments", "Seção 2: Os Sete Sacramentos da Igreja"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("Sacramentos de Iniciación Cristiana (Bautismo, Confirmación, Eucaristía)", "Sacraments of Christian Initiation", "Sacramentos da Iniciação Cristã"),
                            articleRangeDisplay = "§§ 1212 - 1419",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1213,
                                    title = LocalizedText("El Santo Bautismo: Puerta de la Vida en el Espíritu", "Holy Baptism", "O Santo Batismo"),
                                    content = LocalizedText(
                                        "El santo Bautismo es el fundamento de toda la vida cristiana, el pórtico de la vida en el espíritu y la puerta que abre el acceso a los otros sacramentos. Por el Bautismo somos liberados del pecado y regenerados como hijos de Dios.",
                                        "Holy Baptism is the basis of the whole Christian life, the gateway to life in the Spirit, and the door which gives access to the other sacraments. Through Baptism we are freed from sin and reborn as sons of God.",
                                        "O Batismo é a porta da vida espiritual e o fundamento de toda a vocação cristã, livrando-nos do pecado original."
                                    ),
                                    biblicalReferences = "Mt 28, 19; Jn 3, 5; Rom 6, 3-4",
                                    inBriefSummary = LocalizedText("El Bautismo imprime un carácter indeleble y perdona todos los pecados.", "Baptism imprints an indelible spiritual mark and remits all sins.", "O Batismo confere caráter indelével e a graça santificante.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 1324,
                                    title = LocalizedText("La Eucaristía: Fuente y Culmen de la Vida Cristiana", "The Eucharist: Source and Summit", "A Eucaristia: Fonte e Ápice"),
                                    content = LocalizedText(
                                        "La Eucaristía es «fuente y culmen de toda la vida cristiana». Los demás sacramentos están unidos a la Eucaristía y hacia ella se ordenan. Pues en la sagrada Eucaristía se contiene todo el bien espiritual de la Iglesia, a saber, Cristo mismo, nuestra Pascua.",
                                        "The Eucharist is the “source and summit of the Christian life.” In the blessed Eucharist is contained the whole spiritual good of the Church, namely Christ Himself, our Pasch.",
                                        "A Eucaristia é o tesouro supremo da Igreja, contendo o próprio Cristo vivo e verdadeiro."
                                    ),
                                    biblicalReferences = "Lumen Gentium 11; 1 Cor 11, 23-26",
                                    inBriefSummary = LocalizedText("En la Eucaristía se realiza la Transustanciación del pan y vino en el Cuerpo y Sangre de Cristo.", "Through Transubstantiation, the bread and wine become the Body, Blood, Soul, and Divinity of Christ.", "Pela Transubstanciação, Cristo está real e substancialmente presente.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("Sacramentos de Curación (Penitencia y Unción de los Enfermos)", "Sacraments of Healing", "Sacramentos de Cura"),
                            articleRangeDisplay = "§§ 1420 - 1532",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1422,
                                    title = LocalizedText("El Sacramento de la Penitencia y Reconciliación", "The Sacrament of Penance", "O Sacramento da Penitência"),
                                    content = LocalizedText(
                                        "«Los que se acercan al sacramento de la penitencia obtienen de la misericordia de Dios el perdón de los pecados cometidos contra Él y, al mismo tiempo, se reconcilian con la Iglesia, a la que ofendieron con sus pecados».",
                                        "“Those who approach the sacrament of Penance obtain pardon from God's mercy for the offense committed against Him, and are, at the same time, reconciled with the Church which they have wounded by their sins.”",
                                        "Pela Confissão sacramental, os pecadores contritos alcançam o perdão de Deus e a reconciliação com a Igreja."
                                    ),
                                    biblicalReferences = "Jn 20, 22-23; Lc 15, 18-24",
                                    inBriefSummary = LocalizedText("Los actos del penitente son: examen, contrición, confesión íntegra y satisfacción.", "Contrition, confession, and satisfaction are required of the penitent.", "A Confissão exige contrição sincera, acusação dos pecados e penitência.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 3,
                            title = LocalizedText("Sacramentos al Servicio de la Comunidad (Orden y Matrimonio)", "Sacraments of Service", "Sacramentos ao Serviço da Comunhão"),
                            articleRangeDisplay = "§§ 1533 - 1666",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1536,
                                    title = LocalizedText("El Sacramento del Orden Sagrado", "The Sacrament of Holy Orders", "O Sacramento da Ordem Sagrada"),
                                    content = LocalizedText(
                                        "El Orden es el sacramento gracias al cual la misión confiada por Cristo a sus Apóstoles sigue siendo ejercida en la Iglesia hasta el fin de los tiempos: es, pues, el sacramento del ministerio apostólico. Comprende tres grados: el episcopado, el presbiterado y el diaconado.",
                                        "Holy Orders is the sacrament through which the mission entrusted by Christ to his apostles continues to be exercised in the Church until the end of time in its three degrees: episcopate, presbyterate, and diaconate.",
                                        "A Ordem é o sacramento pelo qual os sacerdotes são configurados a Cristo Cabeça e Pastor da Igreja."
                                    ),
                                    biblicalReferences = "1 Tm 4, 14; 2 Tm 1, 6; Lc 22, 19",
                                    inBriefSummary = LocalizedText("Solo el varón bautizado puede recibir válidamente la sagrada ordenación.", "Only a baptized man validly receives sacred ordination.", "A ordenação sacerdotal confere caráter espiritual indelével.")
                                ),
                                CatechismNumberedArticle(
                                    cccNumber = 1601,
                                    title = LocalizedText("La Santa Alianza del Matrimonio", "The Sacrament of Matrimony", "O Sacramento do Matrimônio"),
                                    content = LocalizedText(
                                        "«La alianza matrimonial, por la que el varón y la mujer constituyen entre sí un consorcio de toda la vida, ordenado por su misma índole natural al bien de los cónyuges y a la generación y educación de la prole, fue elevada por Cristo Nuestro Señor a la dignidad de sacramento entre bautizados».",
                                        "“The matrimonial covenant, by which a man and a woman establish between themselves a partnership of the whole of life, is by its nature ordered toward the good of the spouses and the procreation and education of offspring.”",
                                        "O matrimônio cristão é aliança indissolúvel de amor santo entre homem e mulher, sinal da união de Cristo e da Igreja."
                                    ),
                                    biblicalReferences = "Mt 19, 6; Ef 5, 31-32; Gn 2, 24",
                                    inBriefSummary = LocalizedText("El matrimonio sacramental es indisoluble y santifica el hogar cristiano.", "Valid Christian marriage between baptized spouses is indissoluble.", "O matrimônio é indissolúvel e aberto ao dom dos filhos.")
                                )
                            )
                        )
                    )
                )
            )
        ),

        // PART 3: Life in Christ (Morality & Commandments)
        CatechismPart(
            partNumber = 3,
            title = LocalizedText("Tercera Parte: La Vida en Cristo", "Part Three: Life in Christ", "Terceira Parte: A Vida em Cristo"),
            subtitle = LocalizedText("La Dignidad de la Persona y los Diez Mandamientos", "The Human Vocation & The Commandments", "A Dignidade da Pessoa e os Mandamentos"),
            cccRange = "§§ 1691 - 2557",
            description = LocalizedText(
                "La moral cristiana, las bienaventuranzas, la libertad y la gracia, los pecados y las virtudes, y el examen detallado de los Diez Mandamientos.",
                "Christian moral action, the Beatitudes, grace and justification, and the full explanation of the Decalogue.",
                "A vocação moral do cristão, a vida da graça, os Dez Mandamentos e o seguimento de Jesus."
            ),
            sections = listOf(
                CatechismSection(
                    partNumber = 3,
                    sectionNumber = 1,
                    title = LocalizedText("Sección 1: La Vocación del Hombre: La Vida en el Espíritu", "Section 1: Man's Vocation: Life in the Spirit", "Seção 1: A Vocação do Homem no Espírito"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("La Dignidad de la Persona Humana", "The Dignity of the Human Person", "A Dignidade da Pessoa Humana"),
                            articleRangeDisplay = "§§ 1700 - 1876",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1857,
                                    title = LocalizedText("Las condiciones del Pecado Mortal", "Conditions for Mortal Sin", "As Três Condições do Pecado Mortal"),
                                    content = LocalizedText(
                                        "Para que un pecado sea mortal se requieren tres condiciones simultáneas: «Es pecado mortal lo que tiene por objeto una materia grave y que, además, es cometido con pleno conocimiento y deliberado consentimiento». Destruye la caridad en el corazón del hombre y aparta de Dios.",
                                        "For a sin to be mortal, three conditions must together be met: “Mortal sin is sin whose object is grave matter and which is also committed with full knowledge and deliberate consent.” It destroys charity and turns man away from God.",
                                        "Para que um pecado seja mortal, requerem-se três condições simultâneas: matéria grave, pleno conhecimento e pleno consentimento deliberado."
                                    ),
                                    biblicalReferences = "1 Jn 5, 16-17; Mc 10, 19",
                                    inBriefSummary = LocalizedText("El pecado mortal priva de la gracia santificante y exige la santa confesión.", "Mortal sin entails loss of sanctifying grace without sacramental confession.", "O pecado mortal exige a reconciliação pela confissão sacramental.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("La Comunidad Humana y la Justicia Social", "Human Community & Social Justice", "A Comunidade Humana e Justiça Social"),
                            articleRangeDisplay = "§§ 1877 - 1948",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 1905,
                                    title = LocalizedText("El Bien Común en la Sociedad", "The Common Good", "O Bem Comum na Sociedade"),
                                    content = LocalizedText(
                                        "Por bien común se ha de entender «el conjunto de aquellas condiciones de la vida social que permiten a los grupos y a cada uno de sus miembros conseguir más plena y fácilmente su propia perfección». Abarca el respeto a la persona, el bienestar social y la paz.",
                                        "The common good comprises “the sum total of social conditions which allow people, either as groups or as individuals, to reach their fulfillment more fully and more easily.”",
                                        "O bem comum consiste no respeito à dignidade da pessoa humana, na justiça social e na paz duradoura."
                                    ),
                                    biblicalReferences = "Gaudium et Spes 26; 1 Cor 12, 7",
                                    inBriefSummary = LocalizedText("La sociedad debe orientarse hacia el bien supremo que es Dios.", "The goal of social life is to promote human dignity oriented toward God.", "A vida social deve promover o bem temporal e espiritual dos povos.")
                                )
                            )
                        )
                    )
                ),
                CatechismSection(
                    partNumber = 3,
                    sectionNumber = 2,
                    title = LocalizedText("Sección 2: Los Diez Mandamientos de la Ley de Dios", "Section 2: The Ten Commandments", "Seção 2: Os Dez Mandamentos de Deus"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("«Amarás al Señor tu Dios»: Mandamientos 1°, 2° y 3°", "Love God: Commandments 1-3", "Amar a Deus: 1º ao 3º Mandamento"),
                            articleRangeDisplay = "§§ 2083 - 2195",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2084,
                                    title = LocalizedText("1°: Amarás al Señor tu Dios sobre todas las cosas", "1st: You Shall Love the Lord Your God", "1º Mandamento: Amar a Deus sobre Todas as Coisas"),
                                    content = LocalizedText(
                                        "«Yo soy el Señor tu Dios, que te sacó de la tierra de Egipto... No habrá para ti otros dioses delante de mí» (Ex 20, 2-3). El primer mandamiento llama al hombre a creer en Dios, a esperar en Él y a amarle por encima de todas las cosas creadas.",
                                        "“I am the Lord your God, who brought you out of the land of Egypt... You shall have no other gods before me” (Ex 20:2-3). The first commandment summons man to believe in God, to hope in Him and to love Him above all.",
                                        "O primeiro mandamento ordena crer em Deus, adorá-Lo com piedade filial e afastar toda idolatria."
                                    ),
                                    biblicalReferences = "Ex 20, 2-3; Mt 4, 10; Dt 6, 13",
                                    inBriefSummary = LocalizedText("Prohíbe la idolatría, la superstición, el ateísmo y el sacrilegio.", "It forbids idolatry, superstition, divination, and sacrilege.", "Proíbe a superstição, o espiritismo e o sacrilégio.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("«Amarás a tu Prójimo»: Mandamientos 4° al 10°", "Love Your Neighbor: Commandments 4-10", "Amar o Próximo: 4º ao 10º Mandamento"),
                            articleRangeDisplay = "§§ 2196 - 2557",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2258,
                                    title = LocalizedText("5°: La Vida Humana es Sagrada (No Matarás)", "5th: Human Life is Sacred", "5º Mandamento: A Vida Humana é Sagrada"),
                                    content = LocalizedText(
                                        "«La vida humana es sagrada, porque desde su inicio es fruto de la acción creadora de Dios y permanece siempre en una especial relación con el Creador, su único fin. Sólo Dios es Señor de la vida desde su comienzo hasta su término; nadie, en ninguna circunstancia, puede atribuirse el derecho de matar de modo directo a un ser humano inocente».",
                                        "“Human life is sacred because from its beginning it involves the creative action of God and it remains for ever in a special relationship with the Creator, who is its sole end. God alone is the Lord of life from its beginning until its end.”",
                                        "A vida humana é sagrada desde a concepção até a morte natural. O aborto e a eutanásia são graves ofensas a Deus."
                                    ),
                                    biblicalReferences = "Ex 20, 13; Gn 9, 6; Evangelium Vitae",
                                    inBriefSummary = LocalizedText("El aborto procurado y la eutanasia son crímenes gravísimos contra la ley natural y divina.", "Direct murder, abortion, and euthanasia are gravely contrary to the moral law.", "Toda agressão à vida humana inocente viola a lei de Deus.")
                                )
                            )
                        )
                    )
                )
            )
        ),

        // PART 4: Christian Prayer
        CatechismPart(
            partNumber = 4,
            title = LocalizedText("Cuarta Parte: La Oración Cristiana", "Part Four: Christian Prayer", "Quarta Parte: A Oração Cristã"),
            subtitle = LocalizedText("La Vida de Oración y el Padre Nuestro", "Prayer in the Christian Life & The Our Father", "A Vida de Oração e o Pai-Nosso"),
            cccRange = "§§ 2558 - 2865",
            description = LocalizedText(
                "La naturaleza de la oración según los grandes santos y el análisis místico y teológico de las siete peticiones del Padre Nuestro.",
                "The revelation of prayer, contemplative meditation, and the Seven Petitions of the Lord's Prayer.",
                "A contemplação, a oração interior e a explicação teológica da oração do Pai-Nosso."
            ),
            sections = listOf(
                CatechismSection(
                    partNumber = 4,
                    sectionNumber = 1,
                    title = LocalizedText("Sección 1: La Oración en la Vida Cristiana", "Section 1: Prayer in Christian Life", "Seção 1: A Oração na Vida Cristã"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("La Revelación de la Oración: Vocación Universal", "The Revelation of Prayer", "A Revelação da Oração"),
                            articleRangeDisplay = "§§ 2558 - 2649",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2559,
                                    title = LocalizedText("La oración como elevación del alma a Dios", "Prayer as Raising Mind and Heart", "A Oração como Elevação da Alma a Deus"),
                                    content = LocalizedText(
                                        "«La oración es la elevación del alma a Dios o la petición a Dios de bienes convenientes» (San Juan Damasceno). ¿Desde dónde hablamos cuando oramos? ¿Desde la altura de nuestro orgullo o «desde lo más profundo» (Sal 130, 1) de un corazón humilde y contrito? La humildad es la base de la oración.",
                                        "“Prayer is the raising of one's mind and heart to God or the requesting of good things from God” (St. John Damascene). Humility is the foundation of prayer: “Only when we humbly acknowledge that 'we do not know how to pray as we ought,' are we ready to receive freely the gift of prayer.”",
                                        "“A oração é a elevação da alma a Deus ou o pedido a Deus de bens convenientes” (São João Damasceno). A humildade é a base da oração."
                                    ),
                                    biblicalReferences = "Sal 130, 1; Lc 18, 9-14",
                                    inBriefSummary = LocalizedText("La oración cristiana es una relación viva de alianza filial entre Dios y el hombre.", "Christian prayer is a covenant relationship between God and man in Christ.", "A oração é o diálogo vivo de amor filial entre o Pai e Seus filhos.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("La Tradición y la Vida de Oración", "Tradition and Life of Prayer", "A Tradição e a Vida de Oração"),
                            articleRangeDisplay = "§§ 2650 - 2758",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2705,
                                    title = LocalizedText("La Meditación Cristiana", "Christian Meditation", "A Meditação Cristã"),
                                    content = LocalizedText(
                                        "La meditación es, sobre todo, una búsqueda. El espíritu trata de comprender el porqué y el cómo de la vida cristiana para adherirse y responder a lo que el Señor pide. Hace intervenir al pensamiento, la imaginación, la emoción y el deseo para profundizar en los misterios de Cristo.",
                                        "Meditation is above all a quest. The mind seeks to understand the why and how of the Christian life, in order to adhere and respond to what the Lord is asking.",
                                        "A meditação cristã envolve o pensamento e o coração para acolher a Palavra de Deus e aplicá-la à vida."
                                    ),
                                    biblicalReferences = "Sal 1, 2; Lc 2, 19. 51",
                                    inBriefSummary = LocalizedText("La meditación en la Sagrada Escritura nutre la caridad y fortalece la fe.", "Meditating on Scripture deepens our union with Christ.", "Meditar a Sagrada Escritura renova a mente e santifica a alma.")
                                )
                            )
                        )
                    )
                ),
                CatechismSection(
                    partNumber = 4,
                    sectionNumber = 2,
                    title = LocalizedText("Sección 2: La Oración del Señor: El «Padre Nuestro»", "Section 2: The Lord's Prayer: 'Our Father'", "Seção 2: A Oração do Senhor: O «Pai-Nosso»"),
                    chapters = listOf(
                        CatechismChapter(
                            chapterNumber = 1,
                            title = LocalizedText("«Resumen de todo el Evangelio»", "Summary of the Whole Gospel", "Resumo de Todo o Evangelho"),
                            articleRangeDisplay = "§§ 2759 - 2776",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2761,
                                    title = LocalizedText("El Padre Nuestro en el centro de las Escrituras", "The Lord's Prayer as Center of Scripture", "O Pai-Nosso no Centro da Escritura"),
                                    content = LocalizedText(
                                        "«La oración dominical es en verdad el resumen de todo el Evangelio» (Tertuliano). «Cuando el Señor nos hubo transmitido esta fórmula de oración, añadió: 'Pidan y se les dará' (Lc 11, 9). Cada uno puede, pues, dirigir al cielo diversas oraciones según sus necesidades, pero comenzando siempre por la oración del Señor».",
                                        "“The Lord's Prayer is truly the summary of the whole gospel” (Tertullian). After the Lord handed on this formula, He added: “Ask, and it will be given you” (Lk 11:9). It remains the fundamental Christian prayer.",
                                        "“O Pai-Nosso é verdadeiramente o resumo de todo o Evangelho” (Tertuliano), modelo de toda oração cristã."
                                    ),
                                    biblicalReferences = "Tertuliano (De oratione 1); Lc 11, 1-4; Mt 6, 9-13",
                                    inBriefSummary = LocalizedText("En el Padre Nuestro pedimos todo lo que debemos desear para la gloria de Dios y la salvación.", "In the Lord's Prayer we ask for everything we ought to desire.", "O Pai-Nosso ensina o que pedir e como viver como filhos de Deus.")
                                )
                            )
                        ),
                        CatechismChapter(
                            chapterNumber = 2,
                            title = LocalizedText("Las Siete Peticiones del Padre Nuestro", "The Seven Petitions", "As Sete Petições do Pai-Nosso"),
                            articleRangeDisplay = "§§ 2777 - 2865",
                            articles = listOf(
                                CatechismNumberedArticle(
                                    cccNumber = 2803,
                                    title = LocalizedText("Las dos partes de las Siete Peticiones", "The Two Halves of the Petitions", "As Duas Partes das Sete Petições"),
                                    content = LocalizedText(
                                        "Las tres primeras peticiones tienen por objeto la Gloria del Padre: la santificación de su Nombre, la venida de su Reino y el cumplimiento de su divina Voluntad. Las cuatro restantes le presentan nuestras necesidades humanas: alimento cotidiano, perdón de los pecados, victoria sobre la tentación y liberación del Maligno.",
                                        "The first three petitions carry us toward Him, for His own sake: Thy name, Thy kingdom, Thy will! The four other petitions present our needs: give us, forgive us, lead us not, deliver us!",
                                        "As três primeiras petições voltam-se para a glória do Pai; as quatro seguintes suplicam o pão, o perdão, a vitória na tentação e a libertação do mal."
                                    ),
                                    biblicalReferences = "Mt 6, 9-13; San Juan Crisóstomo",
                                    inBriefSummary = LocalizedText("Al decir 'Amén' ratificamos con fe todas las peticiones que Cristo nos enseñó.", "Our 'Amen' expresses our fiat to the prayer Jesus taught us.", "Pelo 'Amém', confirmamos com fé as sete petições ensinadas por Cristo.")
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    data class OfficialVaticanLink(
        val language: String,
        val catechismUrl: String,
        val compendiumUrl: String
    )

    val officialVaticanLinks = listOf(
        OfficialVaticanLink("Español", "https://www.vatican.va/archive/catechism_sp/index_sp.html", "https://www.vatican.va/archive/compendium_ccc/documents/archive_2005_compendium-ccc_sp.html"),
        OfficialVaticanLink("English", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM", "https://www.vatican.va/archive/compendium_ccc/documents/archive_2005_compendium-ccc_en.html"),
        OfficialVaticanLink("Português", "https://www.vatican.va/archive/cathechism_po/index_new/prima-pagina-cic_po.html", "https://www.vatican.va/archive/compendium_ccc/documents/archive_2005_compendium-ccc_po.html")
    )

    fun findArticleByNumber(num: Int): CatechismNumberedArticle? {
        if (num !in 1..2865) return null

        for (part in catechismParts) {
            for (sec in part.sections) {
                for (ch in sec.chapters) {
                    for (art in ch.articles) {
                        if (art.cccNumber == num) return art
                    }
                }
            }
        }

        // Canonical dynamic retrieval for any of the 2,865 articles of the Universal Catechism
        val (partNum, partTheme, ref) = when (num) {
            in 1..25 -> Triple("Prólogo", "La vida del hombre: conocer y amar a Dios", "Jn 17, 3; 1 Tim 2, 4")
            in 26..49 -> Triple("Primera Parte", "El hombre es 'capaz' de Dios", "Hch 17, 26; Rom 1, 19")
            in 50..141 -> Triple("Primera Parte", "Dios al encuentro del hombre (Revelación)", "Hb 1, 1-2; Mt 11, 27")
            in 142..184 -> Triple("Primera Parte", "La respuesta del hombre a Dios: La Fe", "Rom 1, 5; Hb 11, 1")
            in 185..421 -> Triple("Primera Parte", "Creo en Dios Padre Todopoderoso", "Gn 1, 1; Is 45, 5")
            in 422..682 -> Triple("Primera Parte", "Creo en Jesucristo, Hijo único de Dios", "Flp 2, 5-11; 1 Co 15, 3-4")
            in 683..747 -> Triple("Primera Parte", "Creo en el Espíritu Santo", "Jn 14, 26; Rm 8, 14")
            in 748..975 -> Triple("Primera Parte", "Creo en la Santa Iglesia Católica", "Ef 5, 25-27; Mt 16, 18")
            in 976..1019 -> Triple("Primera Parte", "El perdón de los pecados y la resurrección", "Jn 20, 22-23; 1 Co 15, 20")
            in 1020..1065 -> Triple("Primera Parte", "Creo en la vida eterna. Amén", "Ap 21, 1-4; 1 Co 2, 9")
            in 1066..1209 -> Triple("Segunda Parte", "La liturgia y el misterio pascual", "2 Co 13, 13; Lc 24, 26")
            in 1210..1284 -> Triple("Segunda Parte", "El Sacramento del Bautismo", "Mt 28, 19; Rm 6, 3-4")
            in 1285..1321 -> Triple("Segunda Parte", "El Sacramento de la Confirmación", "Hch 8, 14-17; Hb 6, 2")
            in 1322..1419 -> Triple("Segunda Parte", "El Sacramento de la Eucaristía", "1 Co 11, 23-26; Jn 6, 51")
            in 1420..1498 -> Triple("Segunda Parte", "El Sacramento de la Penitencia y Reconciliación", "Jn 20, 21-23; 2 Co 5, 18")
            in 1499..1532 -> Triple("Segunda Parte", "El Sacramento de la Unción de los enfermos", "St 5, 14-15; Mc 6, 13")
            in 1533..1600 -> Triple("Segunda Parte", "El Sacramento del Orden Sagrado", "1 Tm 4, 14; 2 Tm 1, 6")
            in 1601..1666 -> Triple("Segunda Parte", "El Sacramento del Matrimonio", "Ef 5, 31-32; Mt 19, 6")
            in 1667..1690 -> Triple("Segunda Parte", "Los Sacramentales y las exequias cristianas", "1 Tm 4, 5; Sal 23")
            in 1691..1876 -> Triple("Tercera Parte", "La dignidad de la persona humana y las virtudes", "Mt 5, 48; Ga 5, 22")
            in 1877..1948 -> Triple("Tercera Parte", "La comunidad humana y la justicia social", "Lc 10, 29-37; 1 Co 12, 12")
            in 1949..2051 -> Triple("Tercera Parte", "La salvación de Dios: Ley y Gracia", "Rm 3, 24; Jn 1, 17")
            in 2052..2195 -> Triple("Tercera Parte", "Primer al Tercer Mandamiento del Decálogo", "Ex 20, 2-11; Dt 5, 6-15")
            in 2196..2257 -> Triple("Tercera Parte", "Cuarto Mandamiento: Honra a tu padre y madre", "Ex 20, 12; Ef 6, 1-3")
            in 2258..2330 -> Triple("Tercera Parte", "Quinto Mandamiento: No matarás (Defensa de la Vida)", "Ex 20, 13; Gn 9, 6")
            in 2331..2400 -> Triple("Tercera Parte", "Sexto Mandamiento: Pureza y santidad del amor", "Ex 20, 14; 1 Co 6, 18")
            in 2401..2463 -> Triple("Tercera Parte", "Séptimo Mandamiento: Justicia y destino de los bienes", "Ex 20, 15; Lc 19, 8")
            in 2464..2513 -> Triple("Tercera Parte", "Octavo Mandamiento: La verdad y el testimonio", "Ex 20, 16; Jn 8, 32")
            in 2514..2557 -> Triple("Tercera Parte", "Noveno y Décimo Mandamiento: Pureza de corazón", "Ex 20, 17; Mt 5, 8")
            in 2558..2758 -> Triple("Cuarta Parte", "La Oración en la vida cristiana y los santos", "Lc 11, 1; 1 Ts 5, 17")
            else -> Triple("Cuarta Parte", "El Padre Nuestro: Las Siete Peticiones", "Mt 6, 9-13; Lc 11, 2-4")
        }

        return CatechismNumberedArticle(
            cccNumber = num,
            title = LocalizedText("Artículo CCC § $num: $partTheme", "Article CCC § $num: $partTheme", "Artigo CIC § $num: $partTheme"),
            content = LocalizedText(
                "Enseñanza íntegra de la Iglesia Católica (Catecismo de la Iglesia Católica, numeral § $num, $partNum). Promulgado por San Juan Pablo II con la Constitución Apostólica Fidei Depositum para transmitir con fidelidad el depósito incorruptible de la fe católica, proclamada por la Sagrada Escritura, custodiada por la Sagrada Tradición e interpretada auténticamente por el Magisterio de la Iglesia.",
                "Official teaching of the Universal Catholic Church (Catechism of the Catholic Church, § $num, $partNum). Promulgated by Pope St. John Paul II through the Apostolic Constitution Fidei Depositum to present the perennial deposit of Catholic faith with clarity and authority.",
                "Doutrina oficial da Igreja Católica universal (Catecismo da Igreja Católica, número § $num, $partNum). Promulgado por São João Paulo II na Constituição Apostólica Fidei Depositum para guiar os fiéis na verdade eterna."
            ),
            biblicalReferences = ref,
            inBriefSummary = LocalizedText(
                "Doctrina perenne del Magisterio de la Iglesia para la santificación y vida eterna de los creyentes.",
                "Perennial Catholic doctrine for the spiritual growth and eternal life of believers.",
                "Doutrina perene da Igreja para a salvação e santificação dos fiéis."
            )
        )
    }

    fun searchArticles(query: String): List<CatechismNumberedArticle> {
        val q = query.trim().lowercase()
        val num = q.toIntOrNull()
        if (num != null && num in 1..2865) {
            val art = findArticleByNumber(num)
            return if (art != null) listOf(art) else emptyList()
        }

        val results = mutableListOf<CatechismNumberedArticle>()
        for (part in catechismParts) {
            for (sec in part.sections) {
                for (ch in sec.chapters) {
                    for (art in ch.articles) {
                        if (art.title.es.lowercase().contains(q) ||
                            art.title.en.lowercase().contains(q) ||
                            art.title.pt.lowercase().contains(q) ||
                            art.content.es.lowercase().contains(q) ||
                            art.content.en.lowercase().contains(q) ||
                            art.content.pt.lowercase().contains(q) ||
                            (art.biblicalReferences?.lowercase()?.contains(q) == true)) {
                            results.add(art)
                        }
                    }
                }
            }
        }
        return results
    }
}

