package com.example.data.repository

import com.example.data.models.ComplineOffice
import com.example.data.models.IgnatianExamenStep
import com.example.data.models.LocalizedText
import com.example.data.models.MarianAntiphonItem

object NightPrayerData {

    val complineOffice = ComplineOffice(
        title = LocalizedText(
            "Completas - Oración Oficial de la Noche de la Iglesia",
            "Compline - Official Night Prayer of the Catholic Church",
            "Completas - Oração Oficial da Noite da Igreja Católica"
        ),
        openingVerseLatin = "V/. Deus, in adiutórium meum inténde.\nR/. Dómine, ad adiuvándum me festína.\nGlória Patri, et Fílio, et Spirítui Sancto. Sicut erat in princípio, et nunc et semper, et in saécula saeculórum. Amen. Allelúia.",
        openingVerseTranslation = LocalizedText(
            "V/. Dios mío, ven en mi auxilio.\nR/. Señor, date prisa en socorrerme.\nGloria al Padre, y al Hijo, y al Espíritu Santo. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén. Aleluya.",
            "V/. O God, come to my assistance.\nR/. O Lord, make haste to help me.\nGlory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen. Alleluia.",
            "V/. Vinde, ó Deus, em meu auxílio.\nR/. Socorrei-me sem demora.\nGlória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre. Amém. Aleluia."
        ),
        confiteorTitle = LocalizedText(
            "Examen de Conciencia y Acto Penitencial (Confiteor)",
            "Examination of Conscience & Penitential Act",
            "Exame de Consciência e Ato Penitencial"
        ),
        confiteorText = LocalizedText(
            "Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión. Por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mí ante Dios, nuestro Señor. El Señor todopoderoso tenga misericordia de nosotros, perdone nuestros pecados y nos lleve a la vida eterna. Amén.",
            "I confess to almighty God and to you, my brothers and sisters, that I have greatly sinned, in my thoughts and in my words, in what I have done and in what I have failed to do, through my fault, through my fault, through my most grievous fault; therefore I ask blessed Mary ever-Virgin, all the Angels and Saints, and you, my brothers and sisters, to pray for me to the Lord our God. May almighty God have mercy on us, forgive us our sins, and bring us to everlasting life. Amen.",
            "Confesso a Deus todo-poderoso e a vós, irmãos, que pequei muitas vezes por pensamentos e palavras, atos e omissões, por minha culpa, minha tão grande culpa. E peço à Virgem Maria, aos Anjos e Santos, e a vós, irmãos, que rogueis por mim a Deus, nosso Senhor. Deus todo-poderoso tenha compaixão de nós, perdoe os nossos pecados e nos conduza à vida eterna. Amém."
        ),
        hymnLatinTitle = "Te lucis ante términum",
        hymnLatin = "Te lucis ante términum,\nrerum Creátor, póscimus,\nut sólita cleméntia\nsis præsul ad custódiam.\n\nProcul recédant sómnia\net nóctium phantásmata;\nhostémque nostrum cómprime,\nne polluántur córpora.\n\nPræsta, Pater omnípotens,\nper Iesum Christum Dóminum,\nqui tecum in perpétuum\nregnat cum Sancto Spíritu. Amen.",
        hymnTranslation = LocalizedText(
            "Antes que la luz se apague, oh Creador de todas las cosas, te suplicamos con tu bondad habitual que seas nuestro guardián y protector. Que se alejen de nosotros los malos sueños y los fantasmas de la noche; frena a nuestro enemigo, para que nuestros cuerpos no sean mancillados. Concédenos esto, Padre todopoderoso, por Jesucristo Señor nuestro, que reina contigo y el Espíritu Santo por los siglos. Amén.",
            "Before the ending of the day, Creator of the world, we pray that with Thy wonted favor Thou wouldst be our Guard and Keeper now. From evil dreams defend our sight, from fears and terrors of the night; tread underfoot our deadly foe, that we no sinful thought may know. O Father, that we ask be done, through Jesus Christ, Thine only Son; who, with the Holy Ghost and Thee, doth live and reign eternally. Amen.",
            "Antes que a luz se apague, ó Criador de tudo, Vos pedimos com Vossa habitual bondade que sejais nosso guarda e proteção. Afastai para longe os maus sonhos e as ilusões da noite. Concedei-nos isto, Pai onipotente, por Jesus Cristo Nosso Senhor. Amém."
        ),
        psalmTitle = LocalizedText("Salmodia: Salmo 91 (90) - A la sombra del Omnipotente", "Psalmody: Psalm 91 - Under God's Protection", "Salmodia: Salmo 91 - Sob a Proteção do Altíssimo"),
        psalmReference = "Salmo 91 (90), 1-16",
        psalmAntiphon = LocalizedText(
            "Antífona: Te cubrirá con sus plumas y bajo sus alas encontrarás refugio.",
            "Antiphon: He will conceal you with His pinions, and under His wings you will trust.",
            "Antífona: Ele te cobrirá com as Suas penas, e debaixo das Suas asas estarás seguro."
        ),
        psalmText = LocalizedText(
            "Tú que habitas al amparo del Altísimo y resides a la sombra del Todopoderoso, di al Señor: «Mi refugio y mi baluarte, mi Dios, en quien confío». Pues Él te librará de la red del cazador y de la peste funesta. Te cubrirá con sus plumas, bajo sus alas hallarás refugio; escudo y coraza es su fidelidad. No temerás el espanto nocturno, ni la flecha que vuela de día, ni la peste que avanza en las tinieblas, ni la plaga que azota a pleno mediodía. Caerán a tu lado mil, y diez mil a tu diestra; mas a ti no te alcanzará.",
            "He who dwells in the shelter of the Most High will abide in the shadow of the Almighty. I will say to the Lord, 'My refuge and my fortress, my God, in whom I trust.' For He will deliver you from the snare of the fowler and from the deadly pestilence. He will cover you with His pinions, and under His wings you will find refuge; His faithfulness is a shield and buckler. You will not fear the terror of the night, nor the arrow that flies by day, nor the pestilence that stalks in darkness, nor the destruction that wastes at noonday.",
            "Tu que habitas sob o amparo do Altíssimo e descansas à sombra do Onipotente, dize ao Senhor: 'Meu refúgio e minha fortaleza, meu Deus, em quem confio.' Porque Ele te livrará do laço do caçador e da peste perniciosa. Ele te cobrirá com as Suas penas, e sob as Suas asas encontrarás refúgio."
        ),
        readingReference = "Jeremías 14, 9",
        readingText = LocalizedText(
            "«Tú, Señor, estás en medio de nosotros, y tu santo Nombre ha sido invocado sobre nosotros; ¡no nos desampares, Señor, Dios nuestro!»",
            "“You are in our midst, O Lord, your name we bear: do not forsake us, O Lord, our God!”",
            "“Vós, Senhor, estais no meio de nós, e o Vosso santo Nome foi invocado sobre nós: não nos abandoneis, Senhor, nosso Deus!”"
        ),
        responsoryLatin = "V/. In manus tuas, Dómine, comméndo spíritum meum.\nR/. In manus tuas, Dómine, comméndo spíritum meum.\nV/. Redemísti nos, Dómine, Deus veritátis.\nR/. Comméndo spíritum meum.\nV/. Glória Patri, et Fílio, et Spirítui Sancto.\nR/. In manus tuas, Dómine, comméndo spíritum meum.",
        responsoryTranslation = LocalizedText(
            "V/. En tus manos, Señor, encomiendo mi espíritu.\nR/. En tus manos, Señor, encomiendo mi espíritu.\nV/. Tú nos has redimido, Señor, Dios de la verdad.\nR/. Encomiendo mi espíritu.\nV/. Gloria al Padre, y al Hijo, y al Espíritu Santo.\nR/. En tus manos, Señor, encomiendo mi espíritu.",
            "V/. Into your hands, Lord, I commend my spirit.\nR/. Into your hands, Lord, I commend my spirit.\nV/. You have redeemed us, Lord, God of truth.\nR/. Into your hands, Lord, I commend my spirit.\nV/. Glory to the Father, and to the Son, and to the Holy Spirit.\nR/. Into your hands, Lord, I commend my spirit.",
            "V/. Em Vossas mãos, Senhor, entrego o meu espírito.\nR/. Em Vossas mãos, Senhor, entrego o meu espírito.\nV/. Vós nos resgatastes, Senhor, Deus da verdade.\nR/. Entrego o meu espírito.\nV/. Glória ao Pai e ao Filho e ao Espírito Santo.\nR/. Em Vossas mãos, Senhor, entrego o meu espírito."
        ),
        canticleAntiphon = LocalizedText(
            "Antífona: Sálvanos, Señor, despiertos; custódianos dormidos, para que velemos con Cristo y descansemos en paz.",
            "Antiphon: Protect us, Lord, as we stay awake; watch over us as we sleep, that awake we may keep watch with Christ, and asleep, rest in His peace.",
            "Antífona: Salvai-nos, Senhor, quando velamos; guardai-nos quando dormimos, para que vigiemos com Cristo e descansemos em paz."
        ),
        canticleOfSimeonLatin = "Nunc dimíttis servum tuum, Dómine, secúndum verbum tuum in pace:\nQuia vidérunt óculi mei salutáre tuum,\nQuod parásti ante fáciem ómnium populórum:\nLumen ad revelatiónem géntium, et glóriam plebis tuæ Israël.\n\nGlória Patri, et Fílio, et Spirítui Sancto. Sicut erat in princípio, et nunc et semper, et in saécula saeculórum. Amen.",
        canticleOfSimeonTranslation = LocalizedText(
            "Ahora, Señor, según tu promesa, puedes dejar a tu siervo irse en paz.\nPorque mis ojos han visto a tu Salvador,\na quien has presentado ante todos los pueblos:\nluz para alumbrar a las naciones y gloria de tu pueblo Israel.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.",
            "Now, Master, you may let your servant go in peace, according to your word,\nfor my eyes have seen your salvation,\nwhich you prepared in sight of all the peoples:\na light for revelation to the Gentiles, and glory for your people Israel.\n\nGlory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.",
            "Agora, Senhor, segundo a Vossa palavra, podeis deixar o Vosso servo partir em paz.\nPorque os meus olhos viram a Vossa salvação,\nque preparastes diante de todos os povos:\nluz para iluminar as nações e glória do Vosso povo Israel.\n\nGlória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre. Amém."
        ),
        concludingPrayer = LocalizedText(
            "Oremos: Visita, Señor, esta habitación y aleja de ella todas las insidias del enemigo; que tus santos ángeles habiten en ella para guardarnos en paz, y que tu bendición esté siempre sobre nosotros. Por Jesucristo, nuestro Señor. Amén.",
            "Let us pray: Visit this house, we beg You, Lord, and drive far from it all snares of the enemy. May Your holy angels dwell here to keep us in peace, and may Your blessing be always upon us. Through Christ our Lord. Amen.",
            "Oremos: Visitai, Senhor, esta morada e afastai dela todas as ciladas do inimigo; habitem nela os Vossos santos anjos para nos guardar em paz, e a Vossa bênção esteja sempre conosco. Por Cristo, nosso Senhor. Amém."
        ),
        blessing = LocalizedText(
            "El Señor todopoderoso y misericordioso, Padre, Hijo y Espíritu Santo, nos conceda una noche tranquila y una muerte santa. Amén.",
            "May the all-powerful and merciful Lord, Father, Son, and Holy Spirit, grant us a peaceful night and a holy death. Amen.",
            "O Senhor todo-poderoso e misericordioso, Pai, Filho e Espírito Santo, nos conceda uma noite tranquila e uma santa morte. Amém."
        ),
        marianAntiphons = listOf(
            MarianAntiphonItem(
                id = "salve_regina",
                titleLatin = "Salve Regina",
                titleTranslation = LocalizedText("Dios te salve, Reina y Madre", "Hail, Holy Queen", "Salve Rainha"),
                seasonTag = LocalizedText("Tiempo Ordinario (Universal)", "Ordinary Time", "Tempo Comum"),
                latinText = "Salve, Regína, Mater misericórdiæ, vita, dulcédo, et spes nostra, salve. Ad te clamámus éxsules fílii Hevæ, ad te suspirámus, geméntes et flentes in hac lacrimárum valle. Eia, ergo, advocáta nostra, illos tuos misericórdes óculos ad nos convérte; et Iesum, benedíctum fructum ventris tui, nobis post hoc exsílium osténde. O clemens, o pia, o dulcis Virgo María.",
                translatedText = LocalizedText(
                    "Dios te salve, Reina y Madre de misericordia, vida, dulzura y esperanza nuestra; Dios te salve. A ti clamamos los desterrados hijos de Eva; a ti suspiramos, gimiendo y llorando, en este valle de lágrimas. ¡Ea, pues, Señora, abogada nuestra!, vuelve a nosotros esos tus ojos misericordiosos; y después de este destierro muéstranos a Jesús, fruto bendito de tu vientre. ¡Oh clemente, oh piadosa, oh dulce Virgen María! Ruega por nosotros, Santa Madre de Dios, para que seamos dignos de alcanzar las promesas de Nuestro Señor Jesucristo. Amén.",
                    "Hail, holy Queen, Mother of Mercy, our life, our sweetness and our hope. To thee do we cry, poor banished children of Eve; to thee do we send up our sighs, mourning and weeping in this valley of tears. Turn then, most gracious advocate, thine eyes of mercy toward us; and after this our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary. Pray for us, O Holy Mother of God, that we may be made worthy of the promises of Christ. Amen.",
                    "Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos os degredados filhos de Eva. A vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei, e depois deste desterro mostrai-nos Jesus, bendito fruto do vosso ventre. Ó clemente, ó piedosa, ó doce sempre Virgem Maria. Rogai por nós, Santa Mãe de Deus, para que sejamos dignos das promessas de Cristo. Amém."
                )
            ),
            MarianAntiphonItem(
                id = "sub_tuum",
                titleLatin = "Sub tuum præsídium",
                titleTranslation = LocalizedText("Bajo tu amparo nos acogemos", "Beneath Thy Protection", "À Vossa Proteção Recorremos"),
                seasonTag = LocalizedText("La oración mariana más antigua (siglo III)", "Oldest Marian prayer (3rd century)", "A mais antiga oração mariana (século III)"),
                latinText = "Sub tuum præsídium confúgimus, Sancta Dei Génetrix: nostras deprecatiónes ne despícias in necessitátibus, sed a perículis cunctis líbera nos semper, Virgo gloriósa et benedícta.",
                translatedText = LocalizedText(
                    "Bajo tu amparo nos acogemos, Santa Madre de Dios: no desprecies las súplicas que te dirigimos en nuestras necesidades; antes bien, líbranos siempre de todo peligro, ¡oh Virgen gloriosa y bendita!",
                    "We fly to thy protection, O Holy Mother of God; despise not our petitions in our necessities, but deliver us always from all dangers, O glorious and blessed Virgin.",
                    "À vossa proteção recorremos, Santa Mãe de Deus; não desprezeis as nossas súplicas em nossas necessidades, mas livrai-nos sempre de todos os perigos, ó Virgem gloriosa e bendita."
                )
            ),
            MarianAntiphonItem(
                id = "regina_caeli",
                titleLatin = "Regina Cæli",
                titleTranslation = LocalizedText("Reina del Cielo, alégrate", "Queen of Heaven, Rejoice", "Rainha do Céu, Alegrai-vos"),
                seasonTag = LocalizedText("Tiempo Pascual (desde Pascua hasta Pentecostés)", "Easter Season", "Tempo Pascal"),
                latinText = "Regína cæli, lætáre, allelúia:\nQuia quem meruísti portáre, allelúia,\nResurréxit, sicut dixit, allelúia.\nOra pro nobis Deum, allelúia.",
                translatedText = LocalizedText(
                    "Reina del Cielo, alégrate, aleluya.\nPorque el Señor, a quien mereciste llevar en tu seno, aleluya,\nha resucitado, según su palabra, aleluya.\nRuega por nosotros a Dios, aleluya.",
                    "Queen of Heaven, rejoice, alleluia.\nFor He whom thou didst merit to bear, alleluia,\nHas risen, as He said, alleluia.\nPray for us to God, alleluia.",
                    "Rainha do Céu, alegrai-vos, aleluia.\nPorque Aquele que merecestes trazer em vosso seio, aleluia,\nRessuscitou como disse, aleluia.\nRogai por nós a Deus, aleluia."
                )
            )
        )
    )

    val ignatianExamenSteps: List<IgnatianExamenStep> = listOf(
        IgnatianExamenStep(
            stepNumber = 1,
            title = LocalizedText("1. Gratitud: Dones de Dios hoy", "1. Gratitude: Recognizing God's Gifts", "1. Gratidão: Os Dons de Deus Hoje"),
            description = LocalizedText(
                "Ponte en la presencia amorosa de Dios y da gracias con sinceridad por las bendiciones, alimentos, conversaciones y gracias de este día.",
                "Place yourself in God's loving presence and give thanks for the blessings, meals, people, and graces received today.",
                "Coloque-se na presença de Deus e agradeça de coração pelas bênçãos e graças vivenciadas ao longo do dia."
            ),
            guidedPrayer = LocalizedText(
                "«Señor mío y Dios mío, te doy gracias de todo corazón por este día que me has regalado, por el don de la vida, por mi familia y por tu amor infinito».",
                "“My Lord and my God, I thank You with all my heart for this day, for the gift of life, and for Your unconditional love.”",
                "“Senhor meu e Deus meu, dou-Vos graças de todo o coração por este dia, pelo dom da vida e pela Vossa infinita providência.”"
            )
        ),
        IgnatianExamenStep(
            stepNumber = 2,
            title = LocalizedText("2. Petición de Luz al Espíritu Santo", "2. Petition for Light from the Holy Spirit", "2. Pedido de Luz ao Espírito Santo"),
            description = LocalizedText(
                "Pide al Espíritu Santo que ilumine tu mente y corazón para ver tu jornada no con ojos de culpa, sino con la mirada misericordiosa de Jesús.",
                "Ask the Holy Spirit for divine clarity to look upon your day not with scrupulous condemnation, but through Christ's eyes of truth and mercy.",
                "Peça a luz do Espírito Santo para enxergar com sinceridade e paz onde houve amor e onde faltou generosidade."
            ),
            guidedPrayer = LocalizedText(
                "«Ven, Espíritu Santo, llena los corazones de tus fieles y enciende en ellos el fuego de tu amor. Dame luz para discernir mis actos con verdad».",
                "“Come, Holy Spirit, fill the hearts of Your faithful. Grant me light to examine my thoughts, words, and actions in truth and love.”",
                "“Vinde, Espírito Santo, enchei os corações dos Vossos fiéis e acendei neles o fogo do Vosso amor. Iluminai o meu exame de consciência.”"
            )
        ),
        IgnatianExamenStep(
            stepNumber = 3,
            title = LocalizedText("3. Repaso Sincero del Día", "3. Review of the Day", "3. Revisão Sincera do Dia"),
            description = LocalizedText(
                "Recorre mentalmente las horas: mañana, tarde y noche. ¿Cómo respondiste a las inspiraciones de Dios? ¿En qué caíste (ira, soberbia, impureza, pereza, omisiones de caridad)?",
                "Walk through your day hour by hour. How did you respond to God's prompts? Where did you stumble in thoughts, words, actions, or omissions?",
                "Passe em revista o seu dia: desde o despertar até esta hora. Como tratou os outros? Cedeu à impaciência, ao orgulho ou à preguiça?"
            ),
            guidedPrayer = LocalizedText(
                "«Señor, Tú sondeas mi corazón y conoces todos mis caminos. Reconozco mis debilidades y las ocasiones en que me aparté de tu voluntad».",
                "“Lord, You search my heart and know all my ways. I acknowledge where I fell short and strayed from Your holy path.”",
                "“Senhor, Vós conheceis o mais íntimo de minha alma. Reconheço as minhas fragilidades diante da Vossa santidade.”"
            )
        ),
        IgnatianExamenStep(
            stepNumber = 4,
            title = LocalizedText("4. Dolor de Corazón y Perdón", "4. Contrition & Forgiveness", "4. Dor de Coração e Perdão"),
            description = LocalizedText(
                "Haz un sincero Acto de Contrición ante Jesús Crucificado, confiando plenamente en su infinita misericordia sanadora.",
                "Make a sincere Act of Contrition before Christ on the Cross, resting completely in His ocean of healing mercy.",
                "Faça um sincero Ato de Contrição, acolhendo o perdão amoroso que Jesus concede a todo pecador arrependido."
            ),
            guidedPrayer = LocalizedText(
                "«Jesús, Hijo de David, ten piedad de mí que soy un pecador. Me pesa de todo corazón haberte ofendido, porque eres infinitamente bueno».",
                "“Jesus, Son of David, have mercy on me, a sinner. I am truly sorry for offending You, my Lord and my God.”",
                "“Senhor Jesus, tende piedade de mim, que sou pecador. Arrependo-me de Vos ter ofendido e confio na Vossa infinita misericórdia.”"
            )
        ),
        IgnatianExamenStep(
            stepNumber = 5,
            title = LocalizedText("5. Propósito y Esperanza para Mañana", "5. Resolution for Tomorrow & Trust", "5. Propósito para o Amanhã e Confiança"),
            description = LocalizedText(
                "Toma una resolución concreta para el día de mañana y encomiéndate al descanso nocturno bajo el manto de la Santísima Virgen María.",
                "Formulate one practical spiritual resolution for tomorrow and rest peacefully under the mantle of Our Blessed Mother.",
                "Faça um propósito concreto de santidade para amanhã e entregue-se ao repouso protegido pelo manto da Mãe de Deus."
            ),
            guidedPrayer = LocalizedText(
                "«Bajo tu manto maternal pongo mi descanso esta noche, Santa Madre de Dios. Mañana, con tu ayuda, amaré y serviré mejor a tu Hijo Jesús. Amén».",
                "“Under your mantle I place my rest tonight, Holy Mother of God. Tomorrow, by God's grace and with your prayers, I will love and serve your Son better. Amen.”",
                "“Em Vossas mãos maternas entrego o meu sono e o meu amanhã, ó Mãe Santíssima. Guardai-me sempre no Coração de Jesus. Amém.”"
            )
        )
    )
}
