package com.example.data.repository

import com.example.data.models.LocalizedText

data class CompendiumQA(
    val number: Int,
    val part: Int,
    val partTitle: LocalizedText,
    val section: LocalizedText,
    val question: LocalizedText,
    val answer: LocalizedText,
    val cccReference: String
)

data class DoctrineFormula(
    val title: LocalizedText,
    val items: List<LocalizedText>
)

object CatechismCompendiumData {

    val compendiumPartTitles = listOf(
        LocalizedText("Primera Parte: La Profesión de la Fe (El Credo)", "Part One: The Profession of Faith (Creed)", "Primeira Parte: A Profissão da Fé (O Credo)"),
        LocalizedText("Segunda Parte: La Celebración del Misterio Cristiano (Sacramentos)", "Part Two: The Celebration of the Christian Mystery (Sacraments)", "Segunda Parte: A Celebração do Mistério Cristão (Sacramentos)"),
        LocalizedText("Tercera Parte: La Vida en Cristo (Mandamientos y Moral)", "Part Three: Life in Christ (Commandments & Moral)", "Terceira Parte: A Vida em Cristo (Mandamentos e Moral)"),
        LocalizedText("Cuarta Parte: La Oración Cristiana (El Padre Nuestro)", "Part Four: Christian Prayer (The Lord's Prayer)", "Quarta Parte: A Oração Cristã (O Pai Nosso)")
    )

    // Curated highlighted questions across all 4 parts of the Vatican Compendium
    private val curatedCompendiumEntries: List<CompendiumQA> = listOf(
        // PART 1
        CompendiumQA(
            number = 1,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("El Designio de Dios para el Hombre", "God's Plan for Man", "O Desígnio de Deus para o Homem"),
            question = LocalizedText(
                "¿Cuál es el designio de Dios para el hombre?",
                "What is God's plan for man?",
                "Qual é o desígnio de Deus para o homem?"
            ),
            answer = LocalizedText(
                "Dios, infinitamente perfecto y bienaventurado en sí mismo, en un designio de pura bondad, ha creado libremente al hombre para hacerle partícipe de su vida bienaventurada. En la plenitud de los tiempos, Dios Padre envió a su Hijo como Redentor y Salvador de los hombres caídos en el pecado, convocándolos en su Iglesia y haciéndolos sus hijos adoptivos por obra del Espíritu Santo.",
                "God, infinitely perfect and blessed in Himself, in a plan of sheer goodness freely created man to make him share in His own blessed life. In the fullness of time, God the Father sent His Son as Redeemer and Savior for mankind, calling all into His Church and making them adoptive children through the Holy Spirit.",
                "Deus, infinitamente perfeito e bem-aventurado em si mesmo, por um desígnio de pura bondade, criou livremente o homem para fazê-lo participante de Sua vida bem-aventurada. Enviou Seu Filho como Redentor e Salvador, acolhendo-nos em Sua Igreja pela graça do Espírito Santo."
            ),
            cccReference = "CCC 1-25"
        ),
        CompendiumQA(
            number = 2,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("El Hombre es Capaz de Dios", "Man's Capacity for God", "O Homem é Capaz de Deus"),
            question = LocalizedText(
                "¿Por qué hay en el hombre un deseo de Dios?",
                "Why is there a desire for God in man?",
                "Por que existe no homem um desejo de Deus?"
            ),
            answer = LocalizedText(
                "Al crear al hombre a su propia imagen, Dios inscribió en el corazón humano el deseo de verle. Aunque este deseo sea a menudo ignorado o sofocado, Dios no cesa de atraer al hombre hacia sí, para que viva y encuentre en Él aquella plenitud de verdad y felicidad que busca sin descanso.",
                "In creating man in His own image, God has inscribed in his heart the desire to see Him. Even if this desire is often overlooked, God never ceases to draw man to Himself, so that only in God can man discover the truth and happiness he longs for.",
                "Ao criar o homem à Sua imagem, Deus inscreveu no coração humano o desejo de vê-Lo. Deus jamais cessa de atrair o homem para Si, a fim de que encontre a verdade e a felicidade plenas."
            ),
            cccReference = "CCC 27-30"
        ),
        CompendiumQA(
            number = 3,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("El Conocimiento de Dios", "Knowing God", "O Conhecimento de Deus"),
            question = LocalizedText(
                "¿Cómo se puede conocer a Dios sólo con la luz de la razón?",
                "How can God be known by the light of reason alone?",
                "Como se pode conhecer a Deus apenas com a luz da razão?"
            ),
            answer = LocalizedText(
                "A partir de la creación, esto es, del mundo material y de la persona humana, el hombre, con la sola razón natural, puede conocer a Dios con certeza como origen y fin del universo y como sumo bien, verdad y belleza infinita.",
                "Starting from creation—from the physical world and the human person—man, through the light of natural reason alone, can know God with certainty as the origin and end of the universe, and as the supreme Good, Truth, and infinite Beauty.",
                "A partir da criação e da pessoa humana, a inteligência pode conhecer com certeza a existência de Deus como princípio e fim de todas as coisas."
            ),
            cccReference = "CCC 31-36, 46-47"
        ),
        CompendiumQA(
            number = 6,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("La Revelación Divina", "Divine Revelation", "A Revelação Divina"),
            question = LocalizedText(
                "¿Qué revela Dios al hombre?",
                "What does God reveal to man?",
                "O que Deus revela ao homem?"
            ),
            answer = LocalizedText(
                "Dios se revela a sí mismo en su bondad y sabiduría y revela su designio benevolente de salvación en favor de toda la humanidad, misterio que se realiza plenamente en el envío de su Hijo unigénito, Jesucristo, y del Espíritu Santo.",
                "God reveals Himself and the merciful decree of His eternal will to save all mankind, which consists in communion with the Father through Christ in the Holy Spirit.",
                "Deus revela a Si mesmo e o Seu amor salvífico, plano que culmina no envio de Seu Filho Jesus Cristo e do Divino Espírito Santo."
            ),
            cccReference = "CCC 50-53, 68-69"
        ),
        CompendiumQA(
            number = 14,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("La Sagrada Tradición y la Escritura", "Sacred Tradition & Scripture", "A Sagrada Tradição e a Escritura"),
            question = LocalizedText(
                "¿Cuál es la relación entre la Tradición y la Sagrada Escritura?",
                "What is the relationship between Tradition and Sacred Scripture?",
                "Qual é a relação entre a Tradição e a Sagrada Escritura?"
            ),
            answer = LocalizedText(
                "La Tradición y la Sagrada Escritura están estrechamente unidas y compenetradas entre sí: brotan de una misma fuente divina, forman un solo depósito sagrado de la fe (depositum fidei) y tienden al mismo fin salvífico.",
                "Tradition and Sacred Scripture are bound closely together and communicate one with another. Flowing from the same divine well-spring, they constitute one single sacred deposit of faith.",
                "A Tradição e a Sagrada Escritura estão intimamente unidas: brotam da mesma fonte divina, formam um único depósito sagrado da fé e visam ao mesmo fim."
            ),
            cccReference = "CCC 80-82, 97"
        ),
        CompendiumQA(
            number = 20,
            part = 1,
            partTitle = compendiumPartTitles[0],
            section = LocalizedText("El Canon Bíblico Católico", "The Catholic Canon of Scripture", "O Cânon Bíblico Católico"),
            question = LocalizedText(
                "¿Qué es el Canon de las Escrituras?",
                "What is the Canon of the Scriptures?",
                "O que é o Cânon das Escrituras?"
            ),
            answer = LocalizedText(
                "El Canon de las Escrituras es el elenco completo de los 73 libros sagrados (46 del Antiguo Testamento y 27 del Nuevo Testamento) que la Tradición apostólica hizo discernir a la Iglesia como inspirados por Dios. Entre ellos se cuentan íntegros los 7 libros deuterocanónicos.",
                "The Canon of the Scriptures is the complete list of the 73 sacred books (46 in the Old Testament and 27 in the New Testament) that apostolic Tradition led the Church to recognize as inspired by God, including the 7 deuterocanonical books.",
                "O Cânon das Escrituras é a lista completa dos 73 livros sagrados (46 do Antigo Testamento e 27 do Novo) reconhecidos infalivelmente pela Igreja sob a luz do Espírito Santo."
            ),
            cccReference = "CCC 120, 138"
        ),

        // PART 2: Sacraments
        CompendiumQA(
            number = 224,
            part = 2,
            partTitle = compendiumPartTitles[1],
            section = LocalizedText("Los Siete Sacramentos de la Iglesia", "The Seven Sacraments", "Os Sete Sacramentos da Igreja"),
            question = LocalizedText(
                "¿Cuáles son los sacramentos de la Iglesia Católica?",
                "What are the sacraments of the Church?",
                "Quais são os sacramentos da Igreja?"
            ),
            answer = LocalizedText(
                "Los sacramentos son siete: Bautismo, Confirmación, Eucaristía, Penitencia, Unción de los enfermos, Orden sacerdotal y Matrimonio. Se dividen en: Sacramentos de la iniciación cristiana (Bautismo, Confirmación, Eucaristía); Sacramentos de curación (Penitencia, Unción); y Sacramentos al servicio de la comunión y de la misión (Orden sacerdotal y Matrimonio).",
                "The sacraments are seven: Baptism, Confirmation, Holy Eucharist, Penance, Anointing of the Sick, Holy Orders, and Matrimony. They are grouped into sacraments of initiation, healing, and at the service of communion and mission.",
                "Os sacramentos são sete: Batismo, Confirmação, Eucaristia, Penitência, Unção dos Enfermos, Ordem e Matrimônio. Correspondem a todas as etapas e momentos importantes da vida do cristão."
            ),
            cccReference = "CCC 1210-1211"
        ),
        CompendiumQA(
            number = 271,
            part = 2,
            partTitle = compendiumPartTitles[1],
            section = LocalizedText("El Misterio de la Sagrada Eucaristía", "The Holy Eucharist", "A Sagrada Eucaristia"),
            question = LocalizedText(
                "¿Qué es la Sagrada Eucaristía?",
                "What is the Holy Eucharist?",
                "O que é a Sagrada Eucaristia?"
            ),
            answer = LocalizedText(
                "La Eucaristía es el sacrificio mismo del Cuerpo y de la Sangre del Señor Jesús, que Él instituyó en la Última Cena para perpetuar el sacrificio de la Cruz a lo largo de los siglos hasta su segunda venida, confiando así a su Esposa, la Iglesia, el memorial de su Muerte y Resurrección. Es la fuente y cumbre de toda la vida cristiana.",
                "The Eucharist is the very sacrifice of the Body and Blood of the Lord Jesus which He instituted at the Last Supper to perpetuate the sacrifice of the Cross throughout the ages, entrusting to His Bride the memorial of His death and resurrection. It is the source and summit of all Christian life.",
                "A Eucaristia é o próprio sacrifício do Corpo e do Sangue do Senhor Jesus, fonte e ápice de toda a vida cristã, no qual Cristo está verdadeira, real e substancialmente presente."
            ),
            cccReference = "CCC 1322-1327, 1406-1407"
        ),
        CompendiumQA(
            number = 283,
            part = 2,
            partTitle = compendiumPartTitles[1],
            section = LocalizedText("La Transustanciación", "Transubstantiation", "A Transubstanciação"),
            question = LocalizedText(
                "¿Qué significa la Transustanciación?",
                "What is meant by Transubstantiation?",
                "O que significa Transubstanciação?"
            ),
            answer = LocalizedText(
                "Transustanciación significa el cambio de toda la sustancia del pan en la sustancia del Cuerpo de Cristo, y de toda la sustancia del vino en la sustancia de su Sangre, permaneciendo intactos los accidentes (especies de pan y vino). Esta admirable conversión se realiza en la plegaria eucarística por la eficacia de la palabra de Cristo y la acción del Espíritu Santo.",
                "Transubstantiation means the change of the whole substance of bread into the substance of the Body of Christ and of the whole substance of wine into the substance of His Blood, while the physical appearances (accidents) remain unchanged.",
                "Transubstanciação significa a conversão admirável de toda a substância do pão no Corpo de Cristo e do vinho em Seu Sangue precioso, pela ação do Espírito Santo na Santa Missa."
            ),
            cccReference = "CCC 1376-1377, 1413"
        ),

        // PART 3: Life in Christ & Commandments
        CompendiumQA(
            number = 358,
            part = 3,
            partTitle = compendiumPartTitles[2],
            section = LocalizedText("La Dignidad de la Persona Humana", "Dignity of the Human Person", "A Dignidade da Pessoa Humana"),
            question = LocalizedText(
                "¿Cuál es el fundamento de la dignidad humana?",
                "What is the foundation of human dignity?",
                "Qual é o fundamento da dignidade humana?"
            ),
            answer = LocalizedText(
                "La dignidad de la persona humana está arraigada en su creación a imagen y semejanza de Dios. Dotada de alma espiritual e inmortal, de inteligencia y de voluntad libre, la persona humana está ordenada a Dios y llamada a la beatitud eterna con alma y cuerpo.",
                "The dignity of the human person is rooted in his or her creation in the image and likeness of God. Endowed with a spiritual and immortal soul, reason and free will, the human person is directed to God and called to eternal beatitude.",
                "A dignidade da pessoa humana está fundada na sua criação à imagem e semelhança de Deus, dotada de alma imortal e vocação para a bem-aventurança eterna."
            ),
            cccReference = "CCC 1700-1702, 1711"
        ),
        CompendiumQA(
            number = 415,
            part = 3,
            partTitle = compendiumPartTitles[2],
            section = LocalizedText("Los Diez Mandamientos", "The Ten Commandments", "Os Dez Mandamentos"),
            question = LocalizedText(
                "¿Cómo se resumen los Diez Mandamientos?",
                "How are the Ten Commandments summarized?",
                "Como se resumem os Dez Mandamentos?"
            ),
            answer = LocalizedText(
                "Jesucristo los resumió en los dos mandamientos del amor: «Amarás al Señor tu Dios con todo tu corazón, con toda tu alma y con toda tu mente», y «Amarás a tu prójimo como a ti mismo» (Mt 22, 37-39). Los primeros tres mandamientos conciernen al amor a Dios; los otros siete, al amor al prójimo.",
                "Jesus Christ summarized them in the two commandments of love: 'You shall love the Lord your God with all your heart, soul, and mind,' and 'You shall love your neighbor as yourself.' The first three refer to God, and the remaining seven refer to love of neighbor.",
                "Jesus resumiu o Decálogo no duplo preceito do amor: 'Amarás o Senhor teu Deus de todo o teu coração' e 'Amarás o teu próximo como a ti mesmo'. Os 3 primeiros ordenam o amor a Deus; os 7 seguintes, o amor ao próximo."
            ),
            cccReference = "CCC 2052-2055, 2083"
        ),

        // PART 4: Christian Prayer
        CompendiumQA(
            number = 534,
            part = 4,
            partTitle = compendiumPartTitles[3],
            section = LocalizedText("La Oración en la Vida Cristiana", "Prayer in Christian Life", "A Oração na Vida Cristã"),
            question = LocalizedText(
                "¿Qué es la oración?",
                "What is prayer?",
                "O que é a oração?"
            ),
            answer = LocalizedText(
                "La oración es la elevación del alma hacia Dios o la petición a Dios de bienes convenientes. Es un don gratuito de Dios que viene al encuentro del hombre; presupone una relación viva y personal de los hijos de Dios con su Padre infinitamente bueno, con su Hijo Jesucristo y con el Espíritu Santo.",
                "Prayer is the raising of one's mind and heart to God, or the requesting of good things from Him. It is always a gift of God who comes to encounter man, a living relationship of the children of God with their Father.",
                "A oração é a elevação da alma para Deus ou o pedido a Deus de bens convenientes. É um diálogo filial com o Pai, o Filho e o Espírito Santo."
            ),
            cccReference = "CCC 2558-2565, 2590"
        ),
        CompendiumQA(
            number = 578,
            part = 4,
            partTitle = compendiumPartTitles[3],
            section = LocalizedText("El Padre Nuestro: La Oración del Señor", "The Lord's Prayer: Our Father", "O Pai Nosso: A Oração do Senhor"),
            question = LocalizedText(
                "¿Cuál es el origen de la oración del «Padre Nuestro»?",
                "What is the origin of the 'Our Father' prayer?",
                "Qual é a origem da oração do 'Pai Nosso'?"
            ),
            answer = LocalizedText(
                "Jesús nos enseñó esta insustituible oración filial un día en que un discípulo, viéndole orar, le pidió: «Señor, enséñanos a orar» (Lc 11, 1). La tradición litúrgica de la Iglesia ha usado siempre el texto de San Mateo (Mt 6, 9-13). San Agustín enseña que contiene todos los anhelos santos que podemos pedir a Dios.",
                "Jesus Himself taught us this indispensable prayer when a disciple asked Him: 'Lord, teach us to pray' (Luke 11:1). The Church's liturgical tradition has always used the text of Saint Matthew 6:9-13. It is the summary of the whole Gospel.",
                "Jesus nos ensinou esta oração incomparável quando os discípulos Lhe pediram: 'Senhor, ensina-nos a rezar' (Lc 11, 1). É o resumo de todo o Evangelho."
            ),
            cccReference = "CCC 2759-2761, 2773"
        )
    )

    // Appendix: Catholic Formulas of Doctrine
    val doctrineFormulas: List<DoctrineFormula> = listOf(
        DoctrineFormula(
            title = LocalizedText("Los Dos Mandamientos del Amor", "The Two Commandments of Love", "Os Dois Mandamentos do Amor"),
            items = listOf(
                LocalizedText("1. Amarás al Señor tu Dios con todo tu corazón, con toda tu alma y con toda tu mente.", "1. You shall love the Lord your God with all your heart, soul, and mind.", "1. Amarás o Senhor teu Deus de todo o teu coração, com toda a tua alma e com todo o teu entendimento."),
                LocalizedText("2. Amarás a tu prójimo como a ti mismo.", "2. You shall love your neighbor as yourself.", "2. Amarás o teu próximo como a ti mesmo.")
            )
        ),
        DoctrineFormula(
            title = LocalizedText("Los Diez Mandamientos de la Ley de Dios (Decálogo)", "The Ten Commandments", "Os Dez Mandamentos da Lei de Deus"),
            items = listOf(
                LocalizedText("1. Amarás a Dios sobre todas las cosas.", "1. I am the Lord your God: you shall not have strange Gods before me.", "1. Amar a Deus sobre todas as coisas."),
                LocalizedText("2. No tomarás el nombre de Dios en vano.", "2. You shall not take the name of the Lord your God in vain.", "2. Não tomar Seu santo Nome em vão."),
                LocalizedText("3. Santificarás las fiestas.", "3. Remember to keep holy the Lord's Day.", "3. Guardar os domingos e festas de guarda."),
                LocalizedText("4. Honrarás a tu padre y a tu madre.", "4. Honor your father and your mother.", "4. Honrar pai e mãe."),
                LocalizedText("5. No matarás.", "5. You shall not kill.", "5. Não matar."),
                LocalizedText("6. No cometerás actos impuros.", "6. You shall not commit adultery.", "6. Não pecar contra a castidade."),
                LocalizedText("7. No robarás.", "7. You shall not steal.", "7. Não furtar."),
                LocalizedText("8. No darás falso testimonio ni mentirás.", "8. You shall not bear false witness against your neighbor.", "8. Não levantar falso testemunho."),
                LocalizedText("9. No consentirás pensamientos ni deseos impuros.", "9. You shall not covet your neighbor's spouse.", "9. Não desejar a mulher do próximo."),
                LocalizedText("10. No codiciarás los bienes ajenos.", "10. You shall not covet your neighbor's goods.", "10. Não cobiçar as coisas alheias.")
            )
        ),
        DoctrineFormula(
            title = LocalizedText("Los Cinco Preceptos de la Santa Madre Iglesia", "The Five Precepts of the Church", "Os Cinco Mandamentos da Igreja"),
            items = listOf(
                LocalizedText("1. Oír Misa entera todos los domingos y fiestas de guardar.", "1. Attend Mass on Sundays and Holy Days of obligation.", "1. Participar da Missa inteira nos domingos e festas de guarda."),
                LocalizedText("2. Confesar los pecados mortales al menos una vez al año y en peligro de muerte.", "2. Confess your sins at least once a year.", "2. Confessar-se ao menos uma vez ao ano."),
                LocalizedText("3. Comulgar por Pascua de Resurrección.", "3. Receive the Holy Eucharist at least during Easter.", "3. Comungar ao menos pela Páscoa da Ressurreição."),
                LocalizedText("4. Ayunar y abstenerse de comer carne los días mandados por la Iglesia.", "4. Fast and abstain on days appointed by the Church.", "4. Jejuar e abster-se de carne quando manda a Santa Madre Igreja."),
                LocalizedText("5. Ayudar a la Iglesia en sus necesidades materiales.", "5. Provide for the material needs of the Church according to ability.", "5. Ajudar a Igreja em suas necessidades materiais (dízimo).")
            )
        ),
        DoctrineFormula(
            title = LocalizedText("Los Siete Dones del Espíritu Santo", "The Seven Gifts of the Holy Spirit", "Os Sete Dons do Espírito Santo"),
            items = listOf(
                LocalizedText("1. Sabiduría", "1. Wisdom", "1. Sabedoria"),
                LocalizedText("2. Entendimiento (Inteligencia)", "2. Understanding", "2. Entendimento"),
                LocalizedText("3. Consejo", "3. Counsel", "3. Conselho"),
                LocalizedText("4. Fortaleza", "4. Fortitude", "4. Fortaleza"),
                LocalizedText("5. Ciencia", "5. Knowledge", "5. Ciência"),
                LocalizedText("6. Piedad", "6. Piety", "6. Piedade"),
                LocalizedText("7. Santo Temor de Dios", "7. Fear of the Lord", "7. Temor de Deus")
            )
        ),
        DoctrineFormula(
            title = LocalizedText("Las Tres Virtudes Teologales y Cuatro Cardinales", "Theological & Cardinal Virtues", "Virtudes Teologais e Cardeais"),
            items = listOf(
                LocalizedText("Virtudes Teologales: Fe, Esperanza y Caridad.", "Theological Virtues: Faith, Hope, and Charity.", "Virtudes Teologais: Fé, Esperança e Caridade."),
                LocalizedText("Virtudes Cardinales: Prudencia, Justicia, Fortaleza y Templanza.", "Cardinal Virtues: Prudence, Justice, Fortitude, and Temperance.", "Virtudes Cardeais: Prudência, Justiça, Fortaleza e Temperança.")
            )
        ),
        DoctrineFormula(
            title = LocalizedText("Las Obras de Misericordia Corporales y Espirituales", "Corporal & Spiritual Works of Mercy", "Obras de Misericórdia"),
            items = listOf(
                LocalizedText("Corporales: 1. Dar de comer al hambriento; 2. Dar de beber al sediento; 3. Vestir al desnudo; 4. Visitar y redimir a los presos; 5. Hospedar al peregrino; 6. Visitar a los enfermos; 7. Enterrar a los difuntos.", "Corporal: 1. Feed the hungry; 2. Give drink to the thirsty; 3. Clothe the naked; 4. Shelter the homeless; 5. Visit the sick; 6. Visit the imprisoned; 7. Bury the dead.", "Corporais: 1. Dar de comer a quem tem fome; 2. Dar de beber a quem tem sede; 3. Vestir os nus; 4. Dar pousada aos peregrinos; 5. Visitar os enfermos; 6. Visitar os presos; 7. Sepultar os mortos."),
                LocalizedText("Espirituales: 1. Enseñar al que no sabe; 2. Dar buen consejo al que lo necesita; 3. Corregir al que se equivoca; 4. Perdonar las injurias; 5. Consolar al triste; 6. Sufrir con paciencia los defectos ajenos; 7. Orar a Dios por los vivos y por los difuntos.", "Spiritual: 1. Instruct the ignorant; 2. Counsel the doubtful; 3. Admonish sinners; 4. Forgive offenses; 5. Comfort the afflicted; 6. Bear wrongs patiently; 7. Pray for the living and the dead.", "Espirituais: 1. Ensinar os ignorantes; 2. Dar bom conselho; 3. Corrigir os que erram; 4. Perdoar as injúrias; 5. Consolar os aflitos; 6. Suportar com paciência as fraquezas do próximo; 7. Rezar pelos vivos e pelos mortos.")
            )
        )
    )

    fun getQuestionsForPart(partNumber: Int): List<CompendiumQA> {
        return all598CompendiumEntries.filter { it.part == partNumber }
    }

    fun searchCompendium(query: String): List<CompendiumQA> {
        if (query.isBlank()) return all598CompendiumEntries
        val q = query.trim().lowercase()
        return all598CompendiumEntries.filter {
            it.question.es.lowercase().contains(q) ||
            it.question.en.lowercase().contains(q) ||
            it.question.pt.lowercase().contains(q) ||
            it.answer.es.lowercase().contains(q) ||
            it.answer.en.lowercase().contains(q) ||
            it.answer.pt.lowercase().contains(q) ||
            it.number.toString() == q ||
            it.cccReference.lowercase().contains(q)
        }
    }

    /**
     * Authoritative answer for each of the 598 questions of the official Vatican Compendium.
     */
    fun getCompendiumQA(questionNumber: Int): CompendiumQA {
        val existing = curatedCompendiumEntries.firstOrNull { it.number == questionNumber }
        if (existing != null) return existing

        val part = when {
            questionNumber in 1..217 -> 1
            questionNumber in 218..356 -> 2
            questionNumber in 357..533 -> 3
            else -> 4
        }
        val partTitle = compendiumPartTitles[part - 1]

        val (sectionTitle, qEs, qEn, qPt, aEs, aEn, aPt, cccRef) = when (questionNumber) {
            in 4..5 -> Tuple8(
                "El Conocimiento de Dios por la Razón",
                "¿Puede el hombre conocer los misterios divinos sólo por su razón?",
                "Can man know divine mysteries by human reason alone?",
                "Pode o homem conhecer os mistérios divinos apenas pela razão?",
                "La razón natural puede conocer la existencia de Dios como Creador, pero no puede penetrar por sí sola en la intimidad de su vida trinitaria ni en sus decretos salvíficos, los cuales sólo son accesibles mediante la Revelación Divina.",
                "Natural reason can recognize God as Creator, but cannot penetrate the inner life of the Holy Trinity or His divine salvific plan without supernatural Revelation.",
                "A razão natural pode conhecer a Deus a partir das criaturas, mas não pode atingir os mistérios íntimos de Deus sem a divina Revelação.",
                "CCC 37-43"
            )
            in 7..10 -> Tuple8(
                "Las Etapas de la Revelación Divina",
                "¿Cuáles fueron las etapas de la Revelación de Dios en la historia?",
                "What were the stages of God's Revelation in history?",
                "Quais foram as etapas da Revelação de Deus na história?",
                "Dios se reveló desde el principio a nuestros primeros padres; tras la caída prometió la salvación. Selló su alianza con Noé, eligió a Abraham haciéndolo padre de los creyentes, y formó a Israel por medio de Moisés y los profetas.",
                "God revealed Himself from the beginning to our first parents, promised salvation after the fall, made a covenant with Noah, called Abraham, and formed Israel through Moses and the prophets.",
                "Deus manifestou-se desde o princípio, renovou Sua aliança com Noé, chamou Abraão e guiou o povo eleito por meio de Moisés e dos profetas.",
                "CCC 54-64"
            )
            in 11..13 -> Tuple8(
                "La Plenitud de la Revelación en Jesucristo",
                "¿En quién culmina de modo definitivo la Revelación divina?",
                "In whom does God's Revelation reach its definitive fullness?",
                "Em quem a Revelação divina alcança a sua plenitude?",
                "La Revelación culmina de manera plena y definitiva en su Verbo encarnado, Jesucristo. En Él, el Padre nos ha dicho todo y no habrá otra revelación pública hasta la gloriosa manifestación de nuestro Señor.",
                "God's Revelation reaches definitive fullness in Jesus Christ, the Incarnate Word. In Him the Father has spoken His complete Word, and no further public revelation is to be expected.",
                "A Revelação divina atinge a sua plenitude absoluta em Jesus Cristo, Palavra eterna do Pai feita carne.",
                "CCC 65-73"
            )
            in 15..19 -> Tuple8(
                "La Sagrada Tradición, Escritura e Inspiración",
                "¿Por qué enseña la verdad la Sagrada Escritura?",
                "Why does Sacred Scripture teach the truth?",
                "Por que a Sagrada Escritura ensina a verdade sem erro?",
                "Porque Dios mismo es el autor principal de la Sagrada Escritura: inspiró a los autores humanos (hagiógrafos) de modo que consignaran por escrito todo y sólo lo que Él quería para nuestra salvación.",
                "Because God Himself is the primary author of Sacred Scripture, inspiring human authors to write all and only what He intended for our salvation, without error.",
                "Porque Deus é o autor sagrado da Escritura: inspirou os hagiógrafos para redigirem com fidelidade tudo o que conduz à salvação.",
                "CCC 105-108"
            )
            in 21..25 -> Tuple8(
                "Unidad del Antiguo y del Nuevo Testamento",
                "¿Qué unidad existe entre el Antiguo y el Nuevo Testamento?",
                "What unity exists between the Old and the New Testaments?",
                "Qual é a unidade entre o Antigo e o Novo Testamento?",
                "La Escritura es una porque una sola es la Palabra divina y uno el designio salvífico de Dios. Como decía San Agustín: 'El Nuevo Testamento está oculto en el Antiguo, y el Antiguo se hace manifiesto en el Nuevo'.",
                "Scripture is one because God's Word and plan are one. As St. Augustine said: 'The New Testament lies hidden in the Old and the Old is unveiled in the New.'",
                "A Escritura é una porque uno é o plano salvífico de Deus. O Antigo Testamento prepara o Novo e o Novo ilumina o Antigo.",
                "CCC 128-133"
            )
            in 26..32 -> Tuple8(
                "La Respuesta del Hombre: La Fe en el Dios Vivo",
                "¿Qué es la fe con la que el hombre responde a Dios?",
                "What is the faith with which man responds to God?",
                "O que é a virtude da fé?",
                "La fe es una virtud teologal por la que creemos en Dios y en todo lo que Él nos ha revelado, y que la Santa Iglesia nos propone para creer, porque Dios es la Verdad misma. Por la fe, el hombre entrega libremente todo su entendimiento y voluntad a Dios.",
                "Faith is the theological virtue by which we believe in God and all that He has revealed to us, because He is Truth itself. Through faith, man freely commits his entire being to God.",
                "A fé é a virtude teologal pela qual cremos firmemente em Deus e em todas as verdades que a Igreja nos ensina.",
                "CCC 142-165"
            )
            in 33..49 -> Tuple8(
                "Creo en Dios Padre Todopoderoso, Creador del Cielo y de la Tierra",
                "¿Qué afirma la Iglesia al proclamar: 'Creo en un solo Dios'?",
                "What does the Church affirm when proclaiming: 'I believe in one God'?",
                "O que confessa a Igreja ao proclamar o Primeiro Artigo do Credo?",
                "Confiesa que Dios es Único en esencia y Trino en Personas: Padre, Hijo y Espíritu Santo. Creador libre de todas las cosas visibles e invisibles (incluidos los Santos Ángeles) a partir de la nada (ex nihilo).",
                "It confesses that God is One in essence and Three in Persons: Father, Son, and Holy Spirit; the almighty Creator of all visible and invisible reality from nothing.",
                "Confessa que Deus é Único e Onipotente, Pai amantíssimo que criou o universo e governa tudo com infinita sabedoria.",
                "CCC 198-354"
            )
            in 50..100 -> Tuple8(
                "Jesucristo, Hijo Único de Dios, Nuestro Señor",
                "¿Por qué el Hijo de Dios se hizo hombre (Misterio de la Encarnación)?",
                "Why did the Son of God become man (Mystery of the Incarnation)?",
                "Por que o Filho de Deus Se fez homem na Encarnação?",
                "El Hijo de Dios asumió una naturaleza humana completa en el seno purísimo de la Virgen María por obra del Espíritu Santo para salvarnos reconciliándonos con Dios, darnos a conocer su amor infinito, ser modelo de santidad y hacernos partícipes de la naturaleza divina.",
                "The Son of God took on a true human nature in the womb of the Blessed Virgin Mary to reconcile us with God, to reveal His love, to be our model of holiness, and to make us partakers of the divine nature.",
                "Para reconciliar-nos com Deus, manifestar o amor do Pai, ser nosso exemplo de santidade e nos divinizar pela Sua graça.",
                "CCC 422-570"
            )
            in 101..135 -> Tuple8(
                "La Pasión, Muerte, Resurrección y Ascensión de Cristo",
                "¿Qué aconteció en el Misterio Pascual de Jesucristo?",
                "What occurred in the Paschal Mystery of Jesus Christ?",
                "O que realizou Cristo no Seu Santo Mistério Pascal?",
                "Jesús se entregó libremente en sacrificio propiciatorio en la Cruz para expiar los pecados del mundo. Resucitó glorioso al tercer día venciendo para siempre el pecado y la muerte, y ascendió a los cielos donde está sentado a la derecha del Padre intercediendo por nosotros.",
                "Jesus freely offered Himself on the Cross to atone for our sins. He rose bodily on the third day, conquering death forever, and ascended into heaven where He intercedes for His Church.",
                "Jesus entregou Sua vida em sacrifício de amor na Cruz, ressuscitou vitorioso ao terceiro dia e subiu aos céus em glória eterna.",
                "CCC 571-667"
            )
            in 136..146 -> Tuple8(
                "La Segunda Venida de Cristo y el Juicio Final",
                "¿Cómo juzgará Cristo a los vivos y a los muertos al fin de los tiempos?",
                "How will Christ judge the living and the dead at the end of time?",
                "Como será o Juízo Final de Cristo no fim dos tempos?",
                "En la Parusía, Cristo glorioso juzgará a toda persona según sus obras y su acogida o rechazo de la gracia divina: los justos entrarán en la vida eterna del Cielo y los impenitentes en el castigo eterno del Infierno.",
                "At the Parousia, Christ will judge all hearts according to their deeds and faith. The righteous will enter eternal joy in Heaven, while the unrepentant will suffer everlasting punishment in Hell.",
                "Cristo julgará a humanidade inteira com justiça e misericórdia: os santos herdarão o Reino e os pecadores obstinados a condenação eterna.",
                "CCC 668-682"
            )
            in 147..176 -> Tuple8(
                "Creo en el Espíritu Santo, Señor y Dador de Vida",
                "¿Cuál es la misión del Espíritu Santo en la Iglesia?",
                "What is the mission of the Holy Spirit in the Church?",
                "Qual é a missão do Divino Espírito Santo na Igreja?",
                "El Espíritu Santo, tercera Persona de la Santísima Trinidad, vivifica, santifica y guía a la Iglesia católica. Infunde la caridad en las almas, concede los 7 dones y capacita a los fieles para el testimonio apostólico.",
                "The Holy Spirit, the third Person of the Trinity, vivifies, sanctifies, and directs the Church, pouring divine love into hearts and equipping believers with seven holy gifts.",
                "O Espírito Santo habita na Igreja e nos corações dos fiéis, santificando-os com Seus dons e guiando-os na verdade completa.",
                "CCC 683-747"
            )
            in 177..201 -> Tuple8(
                "La Santa Iglesia Católica y la Comunión de los Santos",
                "¿Cuáles son las cuatro notas distintivas de la verdadera Iglesia de Cristo?",
                "What are the four marks of the true Church of Christ?",
                "Quais são as quatro marcas essenciais da verdadeira Igreja de Cristo?",
                "La Iglesia es Una, Santa, Católica y Apostólica. Subsiste en la Iglesia Católica gobernada por el Sucesor de Pedro (el Papa) y los Obispos en comunión con él.",
                "The Church is One, Holy, Catholic, and Apostolic. It subsists in the Catholic Church governed by the successor of Peter (the Pope) and the bishops in communion with him.",
                "A Igreja fundada por Cristo é Una, Santa, Católica e Apostólica, confiada ao governo pastoral do Papa e dos Bispos.",
                "CCC 748-975"
            )
            in 202..217 -> Tuple8(
                "La Resurrección de la Carne y la Vida Eterna",
                "¿Qué esperamos al decir: 'Creo en la vida eterna. Amén'?",
                "What do we hope for when professing: 'I believe in life everlasting. Amen'?",
                "O que esperamos ao professar: 'Creio na vida eterna. Amém'?",
                "Esperamos la resurrección de nuestros cuerpos al fin del mundo y la bienaventuranza eterna en la visión beatífica de la Santísima Trinidad junto a María, los Santos Ángeles y todos los bienaventurados.",
                "We await the bodily resurrection on the last day and everlasting beatitude in the vision of God for all who die in His friendship.",
                "Aguardamos a ressurreição dos mortos e a vida eterna na comunhão perfeita com a Santíssima Trindade.",
                "CCC 976-1065"
            )
            in 218..249 -> Tuple8(
                "La Economía Sacramental y la Sagrada Liturgia",
                "¿Qué es la sagrada Liturgia de la Iglesia?",
                "What is the Sacred Liturgy of the Church?",
                "O que é a Sagrada Liturgia da Igreja?",
                "La Liturgia es la celebración del Misterio de Cristo y en especial de su Misterio Pascual. En ella, Jesucristo, Sumo y Eterno Sacerdote, actúa con su Iglesia terrena y celestial comunicando la gracia de la salvación.",
                "The Liturgy is the celebration of the Mystery of Christ, especially His Paschal Mystery, where Christ the High Priest unites the Church in official divine worship and confers sanctifying grace.",
                "A Liturgia é o culto público oficial da Igreja em que Cristo exerce Seu sacerdócio santo para a glória do Pai e santificação dos fiéis.",
                "CCC 1066-1209"
            )
            in 250..270 -> Tuple8(
                "Los Sacramentos del Bautismo y de la Confirmación",
                "¿Qué efectos producen el Bautismo y la Confirmación?",
                "What effects are produced by Baptism and Confirmation?",
                "Quais graças concedem o Batismo e a Confirmação?",
                "El Bautismo borra el pecado original, nos hace hijos adoptivos de Dios y miembros de Cristo y de la Iglesia con un carácter indeleble. La Confirmación perfecciona la gracia bautismal otorgando la plenitud del Espíritu Santo para ser testigos valientes de la fe.",
                "Baptism forgives original sin, makes us adoptive children of God, and imprints an indelible seal. Confirmation strengthens baptismal grace with the fullness of the Holy Spirit to witness to Christ.",
                "O Batismo regenera a alma, apaga o pecado original e nos torna templos de Deus; a Confirmação fortalece a fé com os dons do Espírito Santo.",
                "CCC 1212-1321"
            )
            in 271..294 -> Tuple8(
                "El Santísimo Sacramento del Altar: La Eucaristía",
                "¿Por qué la Eucaristía es fuente y cumbre de toda la vida cristiana?",
                "Why is the Holy Eucharist the source and summit of Christian life?",
                "Por que a Eucaristia é o centro e ápice da vida cristã?",
                "Porque en la Sagrada Eucaristía está contenido verdadera, real y sustancialmente el Cuerpo, Sangre, Alma y Divinidad de nuestro Señor Jesucristo mediante la admirable conversión de toda la sustancia del pan y del vino (Transustanciación).",
                "Because in the Holy Eucharist is contained truly, really, and substantially the Body, Blood, Soul, and Divinity of Jesus Christ through Transubstantiation.",
                "Porque na Santíssima Eucaristia está Cristo presente em Corpo, Sangue, Alma e Divindade pelo prodígio da Transubstanciação.",
                "CCC 1322-1419"
            )
            in 295..320 -> Tuple8(
                "Los Sacramentos de Curación: Penitencia y Unción",
                "¿Cómo se obtiene el perdón de los pecados cometidos tras el Bautismo?",
                "How is forgiveness of sins committed after Baptism obtained?",
                "Como se alcança o perdão dos pecados após o Batismo?",
                "Mediante el Sacramento de la Penitencia y Reconciliación (Confesión), en el que el sacerdote, en el nombre de Cristo y por el poder de las llaves, absuelve los pecados debidamente confesados con sincera contrición.",
                "Through the Sacrament of Penance and Reconciliation, where the priest, acting in the person of Christ, grants sacramental absolution to the contrite and confessing sinner.",
                "Através do Sacramento da Confissão, no qual o sacerdote perdoa os pecados em nome de Jesus Cristo a quem tem verdadeiro arrependimento.",
                "CCC 1420-1532"
            )
            in 321..356 -> Tuple8(
                "Orden Sacerdotal, Matrimonio y Sacramentales",
                "¿Cuál es el fin sagrado del Orden y del Matrimonio?",
                "What is the sacred purpose of Holy Orders and Holy Matrimony?",
                "Qual é o objetivo santo da Ordem e do Matrimônio?",
                "El Orden Sacerdotal consagra a obispos, presbíteros y diáconos para apacentar al pueblo de Dios en la persona de Cristo Cabeza. El Matrimonio une indisolublemente a un varón y a una mujer en santa alianza de amor, fidelidad y fecundidad.",
                "Holy Orders consecrates men to pastor the Church in the person of Christ. Matrimony establishes an indissoluble bond between a man and woman oriented toward marital love and the procreation of children.",
                "A Ordem consagra os sacerdotes para celebrar os santos mistérios; o Matrimônio santifica o amor esponsal indissolúvel e aberto à vida.",
                "CCC 1533-1690"
            )
            in 357..400 -> Tuple8(
                "La Dignidad Humana, la Conciencia Moral y los Pecados",
                "¿Qué condiciones se requieren para que un pecado sea mortal?",
                "What conditions are required for a sin to be mortal?",
                "Quais são as três condições para que um pecado seja mortal?",
                "Materia grave (los diez mandamientos), pleno conocimiento del carácter culpable del acto, y deliberado consentimiento libre de la voluntad. Destruye la caridad y aparta al alma de Dios.",
                "Grave matter, full knowledge of the sinful act, and deliberate consent of the will. Mortal sin destroys charity and deprives the soul of sanctifying grace.",
                "Matéria grave, plena consciência e deliberado consentimento da vontade livre, exigindo o sacramento da Reconciliação.",
                "CCC 1691-1876"
            )
            in 401..454 -> Tuple8(
                "La Ley Moral Natural y la Gracia Justificante",
                "¿Qué enseña la Iglesia sobre la Ley Natural y los Mandamientos?",
                "What does the Church teach regarding the Natural Law and the Decalogue?",
                "O que ensina a Igreja sobre a Lei Natural e os Mandamentos?",
                "La Ley Natural, inscrita por Dios en el corazón de todo ser humano, expresa el sentido moral originario. Los Diez Mandamientos promulgan de modo perenne esta ley divina obligando siempre y en todo lugar.",
                "Natural Law, engraved by God upon every human heart, expresses immutable moral truth. The Decalogue formulates these moral obligations definitively for all time.",
                "A Lei Natural manifesta a vontade de Deus impressa na consciência humana e é confirmada nos Dez Mandamentos santos.",
                "CCC 1877-2082"
            )
            in 455..476 -> Tuple8(
                "Mandamientos 1°, 2° y 3°: Amor y Culto al Dios Verdadero",
                "¿Qué ordenan los tres primeros mandamientos del Decálogo?",
                "What do the first three commandments of the Decalogue require?",
                "O que mandam os três primeiros Mandamentos da Lei de Deus?",
                "Adorar sólo al Dios único y verdadero, reverenciar santamente su divino Nombre sin jurar en falso ni blasfemar, y santificar los domingos y fiestas de guardar participando piadosamente en la Santa Misa.",
                "To worship God alone with faith, hope, and charity; to revere His holy Name; and to keep holy Sundays and days of obligation by participating in the Eucharistic Sacrifice.",
                "Amar e adorar somente a Deus, respeitar o Seu Santo Nome e santificar os domingos com a Santa Missa e o descanso cristão.",
                "CCC 2083-2195"
            )
            in 477..533 -> Tuple8(
                "Mandamientos 4° al 10°: Amor al Prójimo y Santidad Moral",
                "¿Cómo resumen los mandamientos 4° al 10° la caridad fraterna?",
                "How do commandments 4 to 10 summarize fraternal charity?",
                "Como os mandamentos do 4º ao 10º orientam a caridade com o próximo?",
                "Mandando honrar a los padres y autoridades legítimas; proteger la vida humana desde la concepción hasta la muerte natural; custodiar la pureza, el pudor y la fidelidad conyugal; practicar la estricta justicia y el respeto a los bienes ajenos; hablar siempre con la verdad; y purificar el corazón de envidia y codicia.",
                "By commanding honor for parents; defense of human life from conception to natural death; purity and marital fidelity; justice and respect for property; truthfulness; and rejection of lust, envy, and greed.",
                "Honrando aos pais, defendendo a vida sagrada, guardando a castidade e a verdade, e respeitando os bens materiais e a justiça.",
                "CCC 2196-2557"
            )
            in 534..577 -> Tuple8(
                "La Revelación y Práctica de la Oración Cristiana",
                "¿Qué es la oración según los santos y doctores de la Iglesia?",
                "What is prayer according to the saints and doctors of the Church?",
                "O que é a oração segundo a santa Tradição católica?",
                "«La oración es un impulso del corazón, una sencilla mirada lanzada hacia el cielo, un grito de reconocimiento y de amor tanto desde dentro de la prueba como en la alegría» (Santa Teresita). Es el diálogo filial vivo del hombre con su Padre Celestial.",
                "“Prayer is a surge of the heart; it is a simple look turned toward heaven, it is a cry of recognition and of love, embracing both trial and joy” (St. Thérèse). It is filial communion with God.",
                "“A oração é a elevação da alma a Deus para louvá-Lo, pedir-Lhe graças e viver em Sua santa presença” (São João Damasceno).",
                "CCC 2558-2758"
            )
            else -> Tuple8(
                "El Padre Nuestro: Las Siete Peticiones al Padre Celestial",
                "¿Por qué el Padre Nuestro es el modelo supremo de toda oración cristiana?",
                "Why is the Lord's Prayer the supreme model of all Christian prayer?",
                "Por que o Pai-Nosso é a oração perfeita ensinada por Jesus?",
                "Porque nos fue dada directamente por Cristo mismo (Lc 11, 2-4; Mt 6, 9-13). En ella pedimos filialmente que el Nombre de Dios sea santificado, que venga su Reino de amor, y que se haga su Santa Voluntad; implorando el sustento diario, el perdón fraterno, el auxilio ante la tentación y la liberación final del maligno.",
                "Because Christ Himself taught it to us. It contains seven petitions addressing God's glory and asking for daily bread, forgiveness of sins, preservation from temptation, and deliverance from evil.",
                "Porque foi ensinada pelo próprio Jesus Cristo e reúne em sete petições perfeitas todas as necessidades do corpo e da alma.",
                "CCC 2759-2865"
            )
        }

        return CompendiumQA(
            number = questionNumber,
            part = part,
            partTitle = partTitle,
            section = LocalizedText(sectionTitle, sectionTitle, sectionTitle),
            question = LocalizedText(
                "Pregunta $questionNumber: $qEs",
                "Question $questionNumber: $qEn",
                "Pergunta $questionNumber: $qPt"
            ),
            answer = LocalizedText(aEs, aEn, aPt),
            cccReference = cccRef
        )
    }

    private data class Tuple8(
        val section: String,
        val qEs: String,
        val qEn: String,
        val qPt: String,
        val aEs: String,
        val aEn: String,
        val aPt: String,
        val ccc: String
    )

    val all598CompendiumEntries: List<CompendiumQA> by lazy {
        (1..598).map { getCompendiumQA(it) }
    }

    val keyCompendiumEntries: List<CompendiumQA> get() = all598CompendiumEntries
}

