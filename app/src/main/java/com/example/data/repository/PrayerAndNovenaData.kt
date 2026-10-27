package com.example.data.repository

import com.example.data.models.DivineMercyStep
import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay
import com.example.data.models.PrayerCategory
import com.example.data.models.RosaryDecade
import com.example.data.models.RosaryMystery
import com.example.data.models.RosaryMysteryType
import com.example.data.models.VaticanPrayer

object PrayerAndNovenaData {

    val joyfulMysteries = RosaryMystery(
        type = RosaryMysteryType.JOYFUL,
        decades = listOf(
            RosaryDecade(
                number = 1,
                title = LocalizedText("La Encarnación del Hijo de Dios", "The Annunciation of the Lord", "A Anunciação do Senhor"),
                scriptureRef = "Lucas 1, 26-38",
                fruitOfMystery = LocalizedText("Fruto: La Humildad y la Obediencia a Dios", "Fruit: Humility and Obedience to God", "Fruto: Humildade e Obediência a Deus"),
                meditation = LocalizedText(
                    "El Ángel Gabriel anuncia a la Virgen María que concebirá por obra del Espíritu Santo. María responde: 'He aquí la esclava del Señor, hágase en mí según tu palabra'.",
                    "The Archangel Gabriel announces to Mary that she will conceive by the power of the Holy Spirit. She answers with holy surrender: 'Behold the handmaid of the Lord.'",
                    "O Anjo Gabriel anuncia a Maria que ela conceberá o Salvador pelo Espírito Santo. Maria responde com entrega: 'Faça-se em mim segundo a tua palavra'."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María, llena eres de gracia, el Señor es contigo...", "Hail Mary, full of grace, the Lord is with thee...", "Ave Maria, cheia de graça, o Senhor é convosco..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.", "Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.", "Santa Maria, Mãe de Deus, rogai por nós pecadores, agora e na hora de nossa morte. Amém.")
            ),
            RosaryDecade(
                number = 2,
                title = LocalizedText("La Visitación de María a su prima Santa Isabel", "The Visitation of Mary to Elizabeth", "A Visitação de Nossa Senhora a Santa Isabel"),
                scriptureRef = "Lucas 1, 39-56",
                fruitOfMystery = LocalizedText("Fruto: La Caridad fraterna y el Amor al prójimo", "Fruit: Love of Neighbor and Fraternal Charity", "Fruto: Caridade fraterna e Serviço"),
                meditation = LocalizedText(
                    "María viaja presurosa a las montañas de Judea para servir a Isabel. San Juan Bautista salta de gozo en el seno materno.",
                    "Mary travels in haste to the hill country of Judea to serve Elizabeth. John the Baptist leaps for joy in his mother's womb.",
                    "Maria parte apressadamente para as montanhas da Judeia para servir sua prima Isabel. João Batista estremece de alegria."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María, llena eres de gracia...", "Hail Mary, full of grace...", "Ave Maria, cheia de graça..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios, ruega por nosotros...", "Holy Mary, Mother of God, pray for us...", "Santa Maria, Mãe de Deus, rogai por nós...")
            ),
            RosaryDecade(
                number = 3,
                title = LocalizedText("El Nacimiento de Jesús en Belén", "The Nativity of Jesus Christ", "O Nascimento de Jesus em Belém"),
                scriptureRef = "Lucas 2, 1-20",
                fruitOfMystery = LocalizedText("Fruto: El Desprendimiento de lo terreno y la Pobreza de espíritu", "Fruit: Poverty of Spirit and Detachment from the World", "Fruto: Desapego das coisas do mundo e Simplicidade"),
                meditation = LocalizedText(
                    "En la noche santa, en la pobreza de un pesebre, nace el Salvador del mundo. Los ángeles cantan: Gloria a Dios en las alturas.",
                    "In holy stillness and poverty of the manger, the Savior of the world is born. Angels sing: Glory to God in the highest.",
                    "Na santa noite, na pobreza da manjedoura, nasce o Redentor do mundo. Anjos cantam Glória a Deus nas alturas."
                ),
                leaderPrayer = LocalizedText("Padre nuestro, que estás en el cielo, santificado sea tu Nombre...", "Our Father, who art in heaven, hallowed be thy name...", "Pai nosso, que estais nos céus, santificado seja o vosso nome..."),
                congregationResponse = LocalizedText("Danos hoy nuestro pan de cada día; perdona nuestras ofensas...", "Give us this day our daily bread; and forgive us our trespasses...", "O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas...")
            ),
            RosaryDecade(
                number = 4,
                title = LocalizedText("La Presentación del Niño Jesús en el Templo", "The Presentation of the Child Jesus in the Temple", "A Apresentação do Menino Jesus no Templo"),
                scriptureRef = "Lucas 2, 22-38",
                fruitOfMystery = LocalizedText("Fruto: La Pureza de corazón y el Cumplimiento del deber", "Fruit: Purity of Heart and Filial Obedience", "Fruto: Pureza e Fidelidade à Lei de Deus"),
                meditation = LocalizedText(
                    "María y José presentan al Niño en Jerusalén. El anciano Simeón lo proclama: 'Luz para iluminar a las naciones'.",
                    "Mary and Joseph present the divine infant in Jerusalem. Simeon proclaims Him: 'A light of revelation to the Gentiles.'",
                    "Maria e José apresentam o Menino ao templo de Deus. Simeão exulta proclamando-O Luz das nações."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María, llena eres de gracia...", "Hail Mary, full of grace...", "Ave Maria, cheia de graça..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios, ruega por nosotros...", "Holy Mary, Mother of God, pray for us...", "Santa Maria, Mãe de Deus, rogai por nós...")
            ),
            RosaryDecade(
                number = 5,
                title = LocalizedText("El Niño Jesús perdido y hallado en el Templo", "The Finding of Jesus in the Temple", "A Perda e o Reencontro do Menino Jesus no Templo"),
                scriptureRef = "Lucas 2, 41-52",
                fruitOfMystery = LocalizedText("Fruto: La Búsqueda constante de Dios y la Piedad filial", "Fruit: Zeal for the Father's House and Holy Piety", "Fruto: Busca sincera da Vontade Divina"),
                meditation = LocalizedText(
                    "Tras tres días de angustiosa búsqueda, sus padres lo hallan entre los doctores: '¿No sabían que debo ocuparme de las cosas de mi Padre?'.",
                    "After three days of sorrowful searching, Mary and Joseph find Jesus teaching among the doctors in the temple.",
                    "Após três dias de busca, encontram Jesus entre os mestres da Lei, ensinando com sabedoria celestial."
                ),
                leaderPrayer = LocalizedText("Gloria al Padre, y al Hijo, y al Espíritu Santo...", "Glory be to the Father, and to the Son, and to the Holy Spirit...", "Glória ao Pai, ao Filho e ao Espírito Santo..."),
                congregationResponse = LocalizedText("Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.", "As it was in the beginning, is now, and ever shall be, world without end. Amen.", "Como era no princípio, agora e sempre. Amém.")
            )
        )
    )

    val sorrowfulMysteries = RosaryMystery(
        type = RosaryMysteryType.SORROWFUL,
        decades = listOf(
            RosaryDecade(
                number = 1,
                title = LocalizedText("La Agonía en el Huerto de Getsemaní", "The Agony in the Garden", "A Agonia no Horto das Oliveiras"),
                scriptureRef = "Mateo 26, 36-46",
                fruitOfMystery = LocalizedText("Fruto: El Dolor por nuestros pecados y la Conformidad con la Voluntad de Dios", "Fruit: Sorrow for Sin and Conformity to God's Will", "Fruto: Arrependimento dos pecados e Aceitação da vontade de Deus"),
                meditation = LocalizedText(
                    "Jesús suda sangre en Getsemaní: 'Padre, si es posible aleja de mí este cáliz; pero no se haga mi voluntad, sino la tuya'.",
                    "Jesus sweats blood in Gethsemane: 'Father, if it be possible, let this cup pass from me; yet not as I will, but as you will.'",
                    "Jesus sua gotas de sangue no Getsêmani: 'Pai, se é possível, afasta de mim este cálice; contudo, não seja como eu quero, mas como Tu queres'."
                ),
                leaderPrayer = LocalizedText("Padre nuestro...", "Our Father...", "Pai nosso..."),
                congregationResponse = LocalizedText("Danos hoy nuestro pan...", "Give us this day...", "O pão nosso...")
            ),
            RosaryDecade(
                number = 2,
                title = LocalizedText("La Flagelación de Nuestro Señor", "The Scourging at the Pillar", "A Flagelação de Jesus na Coluna"),
                scriptureRef = "Juan 19, 1",
                fruitOfMystery = LocalizedText("Fruto: La Mortificación de los sentidos y la Pureza", "Fruit: Mortification of the Flesh and Purity", "Fruto: Pureza e Domínio dos sentidos"),
                meditation = LocalizedText(
                    "Atado a la columna, el Cordero inocente es desgarrado por nuestros pecados de sensualidad y carne.",
                    "Tied to the pillar, the innocent Lamb suffers brutal lashes for our sins of the flesh.",
                    "Amarrado à coluna, o Cordeiro sem mancha é açoitado em reparação pelos nossos pecados."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 3,
                title = LocalizedText("La Coronación de Espinas", "The Crowning with Thorns", "A Coroação de Espinhos"),
                scriptureRef = "Mateo 27, 27-31",
                fruitOfMystery = LocalizedText("Fruto: La Humildad y la Victoria sobre el orgullo", "Fruit: Humility and Moral Courage", "Fruto: Humildade e Paciência nas ofensas"),
                meditation = LocalizedText(
                    "Los soldados clavan una corona de punzantes espinas en su cabeza y se burlan del Rey del Universo.",
                    "Soldiers press a crown of piercing thorns onto His sacred head, mocking the King of Kings.",
                    "Soldados zombam de Jesus cravando uma coroa de espinhos em Sua cabeça venerável."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 4,
                title = LocalizedText("Jesús con la Cruz a cuestas camino al Calvario", "The Carrying of the Cross", "Jesus Carregando a Cruz a Caminho do Calvário"),
                scriptureRef = "Lucas 23, 26-32",
                fruitOfMystery = LocalizedText("Fruto: La Paciencia para sobrellevar nuestras cruces diarias", "Fruit: Patience in Bearing Our Daily Cross", "Fruto: Perseverança para carregar nossa cruz diária"),
                meditation = LocalizedText(
                    "Jesús carga el madero de la Redención. Cae tres veces y se levanta por amor infinito a nosotros.",
                    "Jesus embraces the heavy wood of our Redemption. He falls three times and rises for love of mankind.",
                    "Jesus abraça a pesada cruz e caminha rumo ao Gólgota com amor incondicional."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 5,
                title = LocalizedText("La Crucifixión y Muerte de Jesús", "The Crucifixion and Death of Our Lord", "A Crucificação e Morte de Jesus"),
                scriptureRef = "Juan 19, 17-30",
                fruitOfMystery = LocalizedText("Fruto: La Salvación de las almas y el Amor al prójimo hasta el fin", "Fruit: Salvation of Souls and Holy Love", "Fruto: Amor a Deus até o fim e Salvação das almas"),
                meditation = LocalizedText(
                    "Elevado en la Cruz, Jesús nos da a María como Madre: 'Hijo, he ahí a tu Madre'. Y exclama: 'Todo está consumado'.",
                    "Elevated on the Cross, Jesus gives us Mary as Mother: 'Woman, behold your son.' He cries out: 'It is finished.'",
                    "Pregado no altar da Cruz, Jesus nos dá Maria como Mãe e entrega Seu Espírito ao Pai."
                ),
                leaderPrayer = LocalizedText("Gloria al Padre...", "Glory be to the Father...", "Glória ao Pai..."),
                congregationResponse = LocalizedText("Como era en el principio...", "As it was in the beginning...", "Como era no princípio...")
            )
        )
    )

    val gloriousMysteries = RosaryMystery(
        type = RosaryMysteryType.GLORIOUS,
        decades = listOf(
            RosaryDecade(
                number = 1,
                title = LocalizedText("La Resurrección del Señor", "The Resurrection of Jesus", "A Ressurreição do Senhor"),
                scriptureRef = "Mateo 28, 1-10",
                fruitOfMystery = LocalizedText("Fruto: La Fe inquebrantable", "Fruit: Faith in Christ's Victory", "Fruto: Fé inabalável na vitória de Cristo"),
                meditation = LocalizedText(
                    "¡Cristo ha resucitado verdaderamente! La muerte ha sido vencida y las puertas del cielo abiertas.",
                    "Christ is truly risen! Death is swallowed up in victory and eternal life opened to all who believe.",
                    "Cristo ressurgiu vitorioso do sepulcro! A morte foi vencida e a vida eterna inaugurada."
                ),
                leaderPrayer = LocalizedText("Padre nuestro...", "Our Father...", "Pai nosso..."),
                congregationResponse = LocalizedText("Danos hoy nuestro pan...", "Give us this day...", "O pão nosso...")
            ),
            RosaryDecade(
                number = 2,
                title = LocalizedText("La Ascensión del Señor a los Cielos", "The Ascension of Our Lord into Heaven", "A Ascensão de Jesus ao Céu"),
                scriptureRef = "Hechos 1, 6-11",
                fruitOfMystery = LocalizedText("Fruto: La Esperanza cristiana y el Deseo del Cielo", "Fruit: Christian Hope and Desire for Heaven", "Fruto: Esperança e anseio da Glória celeste"),
                meditation = LocalizedText(
                    "Jesús bendice a sus apóstoles y asciende al Padre para preparar un lugar para nosotros.",
                    "Jesus ascends to the right hand of the Father, promising to remain with us until the end of the age.",
                    "Jesus sobe aos Céus à vista dos Apóstolos, intercedendo perpetuamente por nós."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 3,
                title = LocalizedText("La Venida del Espíritu Santo en Pentecostés", "The Descent of the Holy Spirit at Pentecost", "A Descida do Espírito Santo em Pentecostes"),
                scriptureRef = "Hechos 2, 1-13",
                fruitOfMystery = LocalizedText("Fruto: El Celo apostólico y los Dones del Espíritu Santo", "Fruit: Zeal for the Gospel and the Seven Gifts", "Fruto: Amor a Deus e dons do Espírito Santo"),
                meditation = LocalizedText(
                    "En el Cenáculo, junto a María en oración, lenguas de fuego descienden sobre la Iglesia naciente.",
                    "In the Upper Room with Mary, tongues of fire descend upon the Apostles, igniting universal evangelization.",
                    "No Cenáculo com Maria Santíssima, o Espírito Santo desce com poder transformador."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 4,
                title = LocalizedText("La Asunción de la Virgen María al Cielo", "The Assumption of the Blessed Virgin Mary", "A Assunção de Nossa Senhora ao Céu"),
                scriptureRef = "Apocalipsis 12, 1",
                fruitOfMystery = LocalizedText("Fruto: La Gracia de una Santa Muerte y Devoción a María", "Fruit: Grace of a Happy Death and Devotion to Our Lady", "Fruto: Graça de uma santa morte e devoção filial a Maria"),
                meditation = LocalizedText(
                    "La Inmaculada Madre de Dios es asunta en cuerpo y alma a la gloria del Padre.",
                    "The Immaculate Virgin Mary is assumed body and soul into heavenly glory, anticipation of our resurrection.",
                    "A Virgem Imaculada é elevada em corpo e alma à presença gloriosa do Criador."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 5,
                title = LocalizedText("La Coronación de María como Reina del Universo", "The Coronation of Mary as Queen of Heaven and Earth", "A Coroação de Nossa Senhora como Rainha do Céu e da Terra"),
                scriptureRef = "Salmo 45 (44)",
                fruitOfMystery = LocalizedText("Fruto: La Perseverancia final y la Confianza filial", "Fruit: Final Perseverance and Trust in Mary's Intercession", "Fruto: Perseverança final e proteção materna"),
                meditation = LocalizedText(
                    "La Trinidad Santísima corona a María Santísima como Reina y Señora de todo lo creado.",
                    "The Most Holy Trinity crowns Mary as Queen of Heaven, our perpetual advocate and Mother of Grace.",
                    "A Santíssima Trindade coroa Maria como Rainha dos anjos e dos homens."
                ),
                leaderPrayer = LocalizedText("Gloria al Padre...", "Glory be to the Father...", "Glória ao Pai..."),
                congregationResponse = LocalizedText("Como era en el principio...", "As it was in the beginning...", "Como era no princípio...")
            )
        )
    )

    val luminousMysteries = RosaryMystery(
        type = RosaryMysteryType.LUMINOUS,
        decades = listOf(
            RosaryDecade(
                number = 1,
                title = LocalizedText("El Bautismo de Jesús en el Jordán", "The Baptism of Jesus in the Jordan", "O Batismo de Jesus no Rio Jordão"),
                scriptureRef = "Mateo 3, 13-17",
                fruitOfMystery = LocalizedText("Fruto: La Fidelidad a las promesas bautismales", "Fruit: Openness to the Holy Spirit and Baptismal Fidelity", "Fruto: Fidelidade às promessas batismais"),
                meditation = LocalizedText(
                    "Los cielos se abren y la voz del Padre proclama: 'Este es mi Hijo amado, en quien me complazco'.",
                    "The heavens open and the Father's voice resounds: 'This is my beloved Son, with whom I am well pleased.'",
                    "O Céu se abre e o Pai declara: 'Este é o meu Filho muito amado, ouvi-O'."
                ),
                leaderPrayer = LocalizedText("Padre nuestro...", "Our Father...", "Pai nosso..."),
                congregationResponse = LocalizedText("Danos hoy nuestro pan...", "Give us this day...", "O pão nosso...")
            ),
            RosaryDecade(
                number = 2,
                title = LocalizedText("La Autorrevelación en las Bodas de Caná", "The Wedding at Cana", "O Primeiro Milagre nas Bodas de Caná"),
                scriptureRef = "Juan 2, 1-11",
                fruitOfMystery = LocalizedText("Fruto: La Fe por la intercesión de María: 'Haced lo que Él os diga'", "Fruit: To Jesus through Mary and Matrimonial Holiness", "Fruto: Obediência à palavra de Jesus por Maria"),
                meditation = LocalizedText(
                    "Por la intercesión materna de María, Jesús transforma el agua en el mejor vino en Caná de Galilea.",
                    "Through Mary's maternal request, Jesus turns water into the finest wine, manifesting His glory.",
                    "A pedido de Maria Santíssima, Jesus manifesta sua glória transformando a água em vinho novo."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 3,
                title = LocalizedText("El Anuncio del Reino de Dios invitando a la Conversión", "The Proclamation of the Kingdom of God", "O Anúncio do Reino de Deus e Convite à Conversão"),
                scriptureRef = "Marcos 1, 14-15",
                fruitOfMystery = LocalizedText("Fruto: El Arrepentimiento y la Confianza en el Evangelio", "Fruit: Repentance and Desire for Holiness", "Fruto: Conversão do coração e acolhimento do Evangelho"),
                meditation = LocalizedText(
                    "Jesús proclama: 'El tiempo se ha cumplido y el Reino de Dios está cerca; conviértanse y crean en la Buena Nueva'.",
                    "Jesus proclaims: 'The kingdom of God is at hand; repent and believe in the gospel.'",
                    "Jesus anuncia a proximidade do Reino e nos convida à reconciliação com o Pai."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 4,
                title = LocalizedText("La Transfiguración en el Monte Tabor", "The Transfiguration of Our Lord", "A Transfiguração no Monte Tabor"),
                scriptureRef = "Lucas 9, 28-36",
                fruitOfMystery = LocalizedText("Fruto: El Deseo de la Santidad y la Escucha de Cristo", "Fruit: Contemplation and Listening to Christ", "Fruto: Escuta atenta da Palavra e contemplação"),
                meditation = LocalizedText(
                    "El rostro de Jesús resplandece como el sol y sus vestiduras se vuelven blancas como la nieve.",
                    "The face of Jesus shines like the sun, revealing the splendor of His eternal divinity.",
                    "No alto do Tabor, a glória divina de Cristo se revela aos apóstolos Pedro, Tiago e João."
                ),
                leaderPrayer = LocalizedText("Dios te salve, María...", "Hail Mary...", "Ave Maria..."),
                congregationResponse = LocalizedText("Santa María, Madre de Dios...", "Holy Mary, Mother of God...", "Santa Maria, Mãe de Deus...")
            ),
            RosaryDecade(
                number = 5,
                title = LocalizedText("La Institución de la Sagrada Eucaristía", "The Institution of the Holy Eucharist", "A Instituição da Santíssima Eucaristia"),
                scriptureRef = "Mateo 26, 26-29",
                fruitOfMystery = LocalizedText("Fruto: La Devoción eucarística y la Comunión sacramental", "Fruit: Eucharistic Adoration and Sacramental Love", "Fruto: Adoração ao Santíssimo Sacramento e comunhão"),
                meditation = LocalizedText(
                    "En la Última Cena, Jesús nos da su Cuerpo y Sangre como alimento para la vida eterna: 'Hagan esto en memoria mía'.",
                    "At the Last Supper, Jesus gives His Body and Blood in the bread and wine: 'Do this in memory of me.'",
                    "Na Última Ceia, Cristo se entrega inteiramente como pão dos anjos e sustento de nossas almas."
                ),
                leaderPrayer = LocalizedText("Gloria al Padre...", "Glory be to the Father...", "Glória ao Pai..."),
                congregationResponse = LocalizedText("Como era en el principio...", "As it was in the beginning...", "Como era no princípio...")
            )
        )
    )

    val divineMercySequence: List<DivineMercyStep> = listOf(
        DivineMercyStep(
            stepIndex = 1,
            title = LocalizedText("Oración de las 3:00 PM (Hora de la Misericordia)", "3:00 PM Prayer (The Hour of Mercy)", "Oração das Três Horas (Hora da Misericórdia)"),
            leaderText = LocalizedText(
                "Expiraste, Jesús, pero la fuente de vida brotó para las almas y el mar de misericordia se abrió para el mundo entero. ¡Oh fuente de vida, insondable Misericordia Divina, abarca el mundo entero y derrámate sobre nosotros!",
                "You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.",
                "Expirastes, Jesus, mas a fonte da vida brotou para as almas e o oceano da misericórdia abriu-se para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo todo e derramai-Vos sobre nós."
            ),
            responseText = LocalizedText(
                "¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío!",
                "O Blood and Water, which gushed forth from the Heart of Jesus as a fountain of Mercy for us, I trust in You!",
                "Ó Sangue e Água, que brotastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!"
            )
        ),
        DivineMercyStep(
            stepIndex = 2,
            title = LocalizedText("Padre Nuestro, Ave María y Credo", "Our Father, Hail Mary and Apostles' Creed", "Pai Nosso, Ave Maria e Credo"),
            leaderText = LocalizedText(
                "Creo en Dios, Padre Todopoderoso, Creador del cielo y de la tierra. Creo en Jesucristo, su único Hijo, Nuestro Señor...",
                "I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, Our Lord...",
                "Creio em Deus Pai Todo-Poderoso, Criador do céu e da terra. E em Jesus Cristo, seu único Filho, nosso Senhor..."
            ),
            responseText = LocalizedText(
                "...que fue concebido por obra y gracia del Espíritu Santo, nació de Santa María Virgen, padeció bajo el poder de Poncio Pilato, fue crucificado, muerto y sepultado.",
                "...who was conceived by the Holy Spirit, born of the Virgin Mary, suffered under Pontius Pilate, was crucified, died and was buried.",
                "...que foi concebido pelo poder do Espírito Santo, nasceu da Virgem Maria, padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado."
            )
        ),
        DivineMercyStep(
            stepIndex = 3,
            title = LocalizedText("Ofrecimiento (Cuenta Grande)", "Eternal Father Offering (Large Bead)", "Oferecimento ao Pai Eterno (Conta Grande)"),
            leaderText = LocalizedText(
                "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo...",
                "Eternal Father, I offer you the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ...",
                "Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade de Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo..."
            ),
            responseText = LocalizedText(
                "...en expiación de nuestros pecados y los del mundo entero.",
                "...in atonement for our sins and those of the whole world.",
                "...em expiação dos nossos pecados e dos do mundo inteiro."
            )
        ),
        DivineMercyStep(
            stepIndex = 4,
            title = LocalizedText("Por su dolorosa Pasión (10 Cuentas Pequeñas)", "For the Sake of His Sorrowful Passion (10 Small Beads)", "Pela Sua Dolorosa Paixão (10 Contas Pequenas)"),
            leaderText = LocalizedText(
                "Por su dolorosa Pasión...",
                "For the sake of His sorrowful Passion...",
                "Pela Sua dolorosa Paixão..."
            ),
            responseText = LocalizedText(
                "ten misericordia de nosotros y del mundo entero.",
                "have mercy on us and on the whole world.",
                "tende misericórdia de nós e do mundo inteiro."
            ),
            repeatCount = 10
        ),
        DivineMercyStep(
            stepIndex = 5,
            title = LocalizedText("Trisagio Santo (Se repite 3 veces)", "Holy God (Trisagion - repeated 3 times)", "Santo Deus (Triságio - repetido 3 vezes)"),
            leaderText = LocalizedText(
                "Santo Dios, Santo Fuerte, Santo Inmortal...",
                "Holy God, Holy Mighty One, Holy Immortal One...",
                "Deus Santo, Deus Forte, Deus Imortal..."
            ),
            responseText = LocalizedText(
                "ten piedad de nosotros y del mundo entero. Amén.",
                "have mercy on us and on the whole world. Amen.",
                "tende piedade de nós e do mundo inteiro. Amém."
            ),
            repeatCount = 3
        )
    )

    val officialNovenas: List<Novena> = CompleteNovenasData.allNovenas

    val vaticanOfficialPrayers: List<VaticanPrayer> = listOf(
        VaticanPrayer(
            id = "creed",
            title = LocalizedText("El Credo de los Apóstoles", "The Apostles' Creed", "O Credo dos Apóstolos"),
            latinTitle = "Symbolum Apostolicum",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Creo en Dios, Padre Todopoderoso, Creador del cielo y de la tierra. Creo en Jesucristo, su único Hijo, Nuestro Señor, que fue concebido por obra y gracia del Espíritu Santo, nació de Santa María Virgen, padeció bajo el poder de Poncio Pilato, fue crucificado, muerto y sepultado, descendió a los infiernos, al tercer día resucitó de entre los muertos, subió a los cielos y está sentado a la derecha de Dios, Padre Todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos.",
                "I believe in God, the Father Almighty, Creator of heaven and earth, and in Jesus Christ, His only Son, Our Lord, who was conceived by the Holy Spirit, born of the Virgin Mary, suffered under Pontius Pilate, was crucified, died and was buried; He descended into hell; on the third day He rose again from the dead; He ascended into heaven, and is seated at the right hand of God the Father Almighty; from there He will come to judge the living and the dead.",
                "Creio em Deus Pai Todo-Poderoso, Criador do céu e da terra. E em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo, nasceu da Virgem Maria, padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado; desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai Todo-Poderoso, donde há de vir a julgar os vivos e os mortos."
            ),
            responseText = LocalizedText(
                "Creo en el Espíritu Santo, la santa Iglesia católica, la comunión de los santos, el perdón de los pecados, la resurrección de la carne y la vida eterna. Amén.",
                "I believe in the Holy Spirit, the holy Catholic Church, the communion of saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.",
                "Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne e na vida eterna. Amém."
            ),
            whenToPray = LocalizedText("Al iniciar el Santo Rosario, en el Bautismo y en la Profesión de Fe.", "At the beginning of the Rosary, in Baptism, and daily profession of faith.", "Na abertura do Santo Terço e na profissão diária da fé católica."),
            indulgenceNote = LocalizedText("Indulgencia concedida por la Iglesia al rezarlo devotamente.", "Enriched with a partial indulgence when prayed devoutly.", "Indulgência parcial concedida pela Santa Sé."),
            thematicTags = listOf("faith", "protection", "doctrine")
        ),
        VaticanPrayer(
            id = "angelus",
            title = LocalizedText("El Ángelus del Señor", "The Angelus", "O Angelus"),
            latinTitle = "Angelus Domini",
            category = PrayerCategory.ANGELUS,
            leaderText = LocalizedText(
                "V/. El Ángel del Señor anunció a María.\nR/. Y concibió por obra del Espíritu Santo.\n(Dios te salve María...)\n\nV/. He aquí la esclava del Señor.\nR/. Hágase en mí según tu palabra.\n(Dios te salve María...)\n\nV/. Y el Verbo se hizo carne.\nR/. Y habitó entre nosotros.\n(Dios te salve María...)\n\nV/. Ruega por nosotros, Santa Madre de Dios.\nR/. Para que seamos dignos de alcanzar las promesas de Jesucristo.",
                "V/. The Angel of the Lord declared unto Mary.\nR/. And she conceived of the Holy Spirit.\n(Hail Mary...)\n\nV/. Behold the handmaid of the Lord.\nR/. Be it done unto me according to thy word.\n(Hail Mary...)\n\nV/. And the Word was made flesh.\nR/. And dwelt among us.\n(Hail Mary...)\n\nV/. Pray for us, O Holy Mother of God.\nR/. That we may be made worthy of the promises of Christ.",
                "V/. O Anjo do Senhor anunciou a Maria.\nR/. E ela concebeu do Espírito Santo.\n(Ave Maria...)\n\nV/. Eis aqui a serva do Senhor.\nR/. Faça-se em mim segundo a vossa palavra.\n(Ave Maria...)\n\nV/. E o Verbo se fez carne.\nR/. E habitou entre nós.\n(Ave Maria...)\n\nV/. Rogai por nós, Santa Mãe de Deus.\nR/. Para que sejamos dignos das promessas de Cristo."
            ),
            responseText = LocalizedText(
                "Oremos: Infunde, Señor, tu gracia en nuestras almas, para que habiendo conocido por el anuncio del Ángel la Encarnación de tu Hijo Jesucristo, seamos conducidos por su Pasión y Cruz a la gloria de la Resurrección. Por el mismo Jesucristo, Nuestro Señor. Amén.",
                "Let us pray: Pour forth, we beseech Thee, O Lord, Thy grace into our hearts, that we to whom the Incarnation of Christ Thy Son was made known by the message of an angel, may by His Passion and Cross be brought to the glory of His Resurrection. Through the same Christ our Lord. Amen.",
                "Oremos: Derramai, ó Deus, a vossa graça em nossos corações, para que, conhecendo pela mensagem do anjo a encarnação do vosso Filho, cheguemos, por sua paixão e cruz, à glória da ressurreição. Por Cristo, nosso Senhor. Amém."
            ),
            whenToPray = LocalizedText("A las 6:00 AM, 12:00 PM (mediodía) y 6:00 PM. En tiempo pascual se sustituye por el Regina Caeli.", "Tradition of the Church at 6:00 AM, 12:00 PM, and 6:00 PM. Replaced by Regina Caeli in Easter season.", "Às 6h, 12h e 18h. No tempo pascal é substituído pelo Regina Caeli."),
            thematicTags = listOf("marian", "incarnation", "daily_routine")
        ),
        VaticanPrayer(
            id = "st_michael",
            title = LocalizedText("Oración a San Miguel Arcángel", "Prayer to Saint Michael the Archangel", "Oração a São Miguel Arcanjo"),
            latinTitle = "Sancte Michael Archangele",
            category = PrayerCategory.PROTECTION,
            leaderText = LocalizedText(
                "San Miguel Arcángel, defiéndenos en la batalla. Sé nuestro amparo contra la perversidad y acechanzas del demonio.",
                "Saint Michael the Archangel, defend us in battle. Be our defense against the wickedness and snares of the Devil.",
                "São Miguel Arcanjo, defendei-nos no combate. Sede nosso refúgio contra as maldades e ciladas do demônio."
            ),
            responseText = LocalizedText(
                "Reprímale Dios, pedimos suplicantes, y tú, Príncipe de la Milicia Celestial, arroja al infierno con el divino poder a Satanás y a los otros espíritus malignos que andan dispersos por el mundo para la perdición de las almas. Amén.",
                "May God rebuke him, we humbly pray; and do thou, O Prince of the heavenly host, by the power of God, cast into hell Satan, and all the evil spirits, who prowl about the world seeking the ruin of souls. Amen.",
                "Ordene-lhe Deus, instantemente o pedimos, e vós, príncipe da milícia celeste, pela virtude divina, precipitai no inferno a Satanás e aos outros espíritos malignos que andam pelo mundo para perder as almas. Amém."
            ),
            whenToPray = LocalizedText("Compuesta por el Papa León XIII. Para protección espiritual y después de la Santa Misa.", "Written by Pope Leo XIII. Prayed for spiritual warfare and protection.", "Composta pelo Papa Leão XIII para proteção contra o mal."),
            thematicTags = listOf("protection", "spiritual_warfare", "peace")
        ),
        VaticanPrayer(
            id = "memorare",
            title = LocalizedText("El Acordaos de San Bernardo (Memorare)", "The Memorare of St. Bernard", "O Lembrai-vos (Memorare)"),
            latinTitle = "Memorare, O Piissima Virgo Maria",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText(
                "Acordaos, ¡oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que han acudido a vuestra protección, implorado vuestro auxilio o reclamado vuestro socorro, haya sido desamparado de vos.",
                "Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.",
                "Lembrai-vos, ó puríssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado."
            ),
            responseText = LocalizedText(
                "Animado por esta confianza, a vos acudo, ¡oh Madre, Virgen de las vírgenes!, y gimiendo bajo el peso de mis pecados, me atrevo a comparecer ante vuestra presencia soberana. ¡Oh Madre de Dios!, no desechéis mis súplicas, antes bien, escuchadlas y acogedlas favorablemente. Amén.",
                "Inspired by this confidence, I fly unto thee, O Virgin of virgins, my Mother; to thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me. Amen.",
                "Animado, pois, de igual confiança, a vós recorro, ó Virgem das virgens, minha Mãe; a vós me acolho e, gemendo sob o peso dos meus pecados, me prostro a vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Filho de Deus humanado, mas ouvi-as propícia e alcançai-me o que vos rogo. Amém."
            ),
            whenToPray = LocalizedText("Atribuida a San Bernardo de Claraval. Oración de máxima confianza para pedir milagros marianos.", "Great prayer of unreserved confidence in Mother Mary in desperate trials.", "Invocação de confiança irrestrita na intercessão da Mãe de Deus."),
            thematicTags = listOf("marian", "miracles", "health", "family")
        ),
        VaticanPrayer(
            id = "anima_christi",
            title = LocalizedText("Alma de Cristo (Anima Christi)", "Soul of Christ (Anima Christi)", "Alma de Cristo"),
            latinTitle = "Anima Christi, sanctifica me",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText(
                "Alma de Cristo, santifícame.\nCuerpo de Cristo, sálvame.\nSangre de Cristo, embriágame.\nAgua del costado de Cristo, lávame.\nPasión de Cristo, confórtame.",
                "Soul of Christ, sanctify me.\nBody of Christ, save me.\nBlood of Christ, inebriate me.\nWater from the side of Christ, wash me.\nPassion of Christ, strengthen me.",
                "Alma de Cristo, santificai-me.\nCorpo de Cristo, salvai-me.\nSangue de Cristo, inebriai-me.\nÁgua do lado de Cristo, lavai-me.\nPaixão de Cristo, confortai-me."
            ),
            responseText = LocalizedText(
                "¡Oh, buen Jesús!, óyeme.\nDentro de tus llagas, escóndeme.\nNo permitas que me aparte de ti.\nDel enemigo maligno, defiéndeme.\nEn la hora de mi muerte, llámame.\nY mándame ir a ti, para que con tus santos te alabe por los siglos de los siglos. Amén.",
                "O good Jesus, hear me.\nWithin Thy wounds hide me.\nSuffer me not to be separated from Thee.\nFrom the malicious enemy defend me.\nIn the hour of my death call me.\nAnd bid me come unto Thee, that with Thy saints I may praise Thee forever and ever. Amen.",
                "Ó bom Jesus, ouvi-me.\nDentro de vossas chagas, escondei-me.\nNão permitais que me separe de vós.\nDo inimigo maligno defendei-me.\nNa hora da minha morte chamai-me.\nE mandai-me ir para vós, para que vos louve com os vossos santos por todos os séculos dos séculos. Amém."
            ),
            whenToPray = LocalizedText("Especialmente recomendada por San Ignacio de Loyola después de recibir la Sagrada Comunión.", "Beloved post-communion prayer of Thanksgiving popularized by St. Ignatius of Loyola.", "Oração por excelência para depois da Sagrada Comunhão."),
            thematicTags = listOf("eucharist", "health", "forgiveness", "peace")
        ),
        VaticanPrayer(
            id = "morning_offering",
            title = LocalizedText("Ofrecimiento de las Obras del Día", "Morning Offering of Daily Life", "Oferecimento do Dia"),
            latinTitle = "Oblatio Matutina",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText(
                "¡Señor Dios Todopoderoso!, te agradezco por haberme permitido ver la luz de este nuevo día. Te ofrezco mis pensamientos, palabras, obras, alegrías y sufrimientos de esta jornada...",
                "O Jesus, through the Immaculate Heart of Mary, I offer You my prayers, works, joys, and sufferings of this day...",
                "Senhor Deus de infinita bondade, ofereço-Vos pelas mãos de Maria Santíssima todos os meus pensamentos, palavras e ações deste novo dia..."
            ),
            responseText = LocalizedText(
                "...en unión con el Santo Sacrificio de la Misa celebrado en todo el mundo, por las intenciones del Santo Padre, por la conversión de los pecadores y la paz en el mundo. Amén.",
                "...in union with the Holy Sacrifice of the Mass throughout the world, for all the intentions of Your Sacred Heart and the Holy Father. Amen.",
                "...em união com o Santo Sacrifício da Missa pelo Santo Padre e pela santificação de todas as famílias. Amém."
            ),
            whenToPray = LocalizedText("Al despertar en la mañana antes de comenzar cualquier actividad.", "Upon rising in the morning to dedicate the day to God.", "Ao acordar pela manhã para consagrar o dia a Deus."),
            thematicTags = listOf("daily_routine", "work", "peace")
        ),
        VaticanPrayer(
            id = "act_of_contrition",
            title = LocalizedText("Acto de Contrición (Examen de Conciencia)", "Act of Contrition", "Ato de Contrição"),
            latinTitle = "Actus Contritionis",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText(
                "¡Señor mío Jesucristo, Dios y Hombre verdadero, Creador, Padre y Redentor mío! Por ser Vos quien sois, Bondad infinita, y porque os amo sobre todas las cosas, me pesa de todo corazón haberos ofendido.",
                "O my God, I am heartily sorry for having offended Thee, and I detest all my sins because of thy just punishments, but most of all because they offend Thee, my God, who art all good and deserving of all my love.",
                "Meu Deus, eu me arrependo de todo o coração de vos ter ofendido, porque sois infinitamente bom e o pecado vos desagrada."
            ),
            responseText = LocalizedText(
                "También me pesa porque podéis castigarme con las penas del infierno. Propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta. Amén.",
                "I firmly resolve with the help of Thy grace to sin no more and to avoid the near occasion of sin. Amen.",
                "Prometo firmemente, com a ajuda de vossa graça, não mais pecar e evitar as ocasiões de queda. Amém."
            ),
            whenToPray = LocalizedText("Cada noche en el examen de conciencia y durante el Sacramento de la Reconciliación (Confesión).", "During night examination of conscience and the Sacrament of Confession.", "Na oração da noite e no Sacramento da Confissão."),
            thematicTags = listOf("forgiveness", "confession", "daily_routine")
        )
    )
}
