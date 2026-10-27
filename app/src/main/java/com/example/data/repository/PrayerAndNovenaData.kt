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

    val officialNovenas: List<Novena> = listOf(
        Novena(
            id = "st_jude",
            title = LocalizedText("Novena a San Judas Tadeo", "Novena to Saint Jude Thaddeus", "Novena a São Judas Tadeu"),
            patron = LocalizedText("Apóstol y Patrón de las Causas Desesperadas e Imposibles", "Apostle and Patron of Desperate and Impossible Causes", "Apóstolo e Padroeiro das Causas Desesperadas"),
            category = LocalizedText("Peticiones Imposibles", "Impossible Petitions", "Causas Urgentes"),
            description = LocalizedText(
                "Una de las novenas más milagrosas de la Iglesia Católica. Incontables testimonios dan fe del auxilio rápido del apóstol en las situaciones de mayor aflicción económica, de salud y familiar.",
                "Celebrated for centuries as the helper of those in dire extremity, Saint Jude is invoked when all human hope seems exhausted.",
                "Uma das novenas mais amadas e milagrosas da Igreja. Inúmeros fiéis alcançam socorro nas causas mais difíceis."
            ),
            miraculousHistory = LocalizedText(
                "Santa Brígida de Suecia relató en sus revelaciones que Nuestro Señor le indicó que cuando necesitara favores especiales recurriera a San Judas Tadeo con gran confianza.",
                "Our Lord appeared to Saint Bridget of Sweden, directing her to invoke Saint Jude with great confidence for desperate matters.",
                "Jesus revelou a Santa Brígida que concederia graças especiais a quem recorresse à intercessão de São Judas Tadeu."
            ),
            promises = LocalizedText(
                "Promesa de auxilio en el abandono, alivio en las penas del corazón y solución en causas sin salida humana.",
                "Comfort in desolation, renewal of divine hope, and spiritual/material aid in urgent trials.",
                "Alívio nas angústias, renovação da esperança e socorro nas necessidades urgentes."
            ),
            days = listOf(
                NovenaDay(
                    dayNumber = 1,
                    theme = LocalizedText("Día 1: San Judas, pariente del Señor", "Day 1: St. Jude, Kinsman of the Lord", "Dia 1: São Judas, parente do Senhor"),
                    scripture = "Judas 1, 1-2",
                    meditation = LocalizedText("San Judas Tadeo, pariente de Jesús y apóstol fiel, nos recuerda que la cercanía al Señor nace del amor y de la fidelidad. En los momentos de mayor desamparo, Dios no nos abandona.", "St. Jude Thaddeus, kinsman of Jesus and faithful apostle, reminds us that nearness to the Lord is born of love and fidelity. In moments of utter desolation, God does not abandon us.", "São Judas Tadeu, parente de Jesus e apóstolo fiel, nos recorda que a proximidade do Senhor nasce do amor e da fidelidade. Nos momentos de maior desamparo, Deus não nos abandona."),
                    prayer = LocalizedText("¡Oh glorioso Apóstol San Judas Tadeo, fiel servidor y amigo de Jesús! Te suplico que intercedas por mí en esta causa desesperada... (mencionar aquí la petición). Alcánzame del Señor la gracia de permanecer fiel hasta el final. Amén.", "O glorious Apostle St. Jude, faithful servant and friend of Jesus! Intercede for me in this desperate cause... (mention your intention). Obtain for me the grace of final perseverance. Amen.", "Ó glorioso Apóstolo São Judas Tadeu, servo fiel e amigo de Jesus! Rogai por mim nesta causa desesperada... (mencionar a intenção). Alcançai-me a graça da perseverança final. Amém."),
                    intentionGuidance = LocalizedText("Presenta con honestidad tu causa más difícil y haz un acto de perdón hacia quien te haya ofendido.", "Place your most agonizing worry before God and make an act of forgiveness toward anyone who hurt you.", "Coloque sua intenção no altar do Senhor e faça um ato de perdão fraterno.")
                ),
                NovenaDay(
                    dayNumber = 2,
                    theme = LocalizedText("Día 2: Fe firme en la tribulación", "Day 2: Steadfast Faith in Trial", "Dia 2: Fé firme na tribulação"),
                    scripture = "Judas 1, 3",
                    meditation = LocalizedText("La fe es el escudo que nos sostiene cuando todo parece perdido. San Judas nos enseña a combatir por la fe transmitida a los santos, confiando en el poder de Dios.", "Faith is the shield that sustains us when all seems lost. St. Jude teaches us to contend for the faith once delivered to the saints, trusting in God's power.", "A fé é o escudo que nos sustenta quando tudo parece perdido. São Judas nos ensina a combater pela fé transmitida aos santos, confiando no poder de Deus."),
                    prayer = LocalizedText("San Judas Tadeo, apóstol de la fe inquebrantable, fortalece mi esperanza en medio de la prueba. Intercede por mi intención... Amén.", "St. Jude, apostle of unshakable faith, strengthen my hope in the midst of trial. Intercede for my intention... Amen.", "São Judas Tadeu, apóstolo da fé inabalável, fortalecei minha esperança em meio à provação. Rogai por minha intenção... Amém."),
                    intentionGuidance = LocalizedText("Renueva hoy tu confianza en la Palabra de Dios y lee un pasaje del Evangelio con devoción.", "Renew your trust in God's Word today and read a Gospel passage with devotion.", "Renove hoje sua confiança na Palavra de Deus e leia um trecho do Evangelho com devoção.")
                ),
                NovenaDay(
                    dayNumber = 3,
                    theme = LocalizedText("Día 3: Esperanza en las causas imposibles", "Day 3: Hope in Impossible Causes", "Dia 3: Esperança nas causas impossíveis"),
                    scripture = "Judas 1, 20-21",
                    meditation = LocalizedText("Dios es especialista en lo imposible. San Judas, patrón de las causas desesperadas, nos invita a no desfallecer, porque el Señor escucha el clamor del corazón humilde.", "God specializes in the impossible. St. Jude, patron of desperate causes, invites us not to faint, for the Lord hears the cry of the humble heart.", "Deus é especialista no impossível. São Judas, padroeiro das causas desesperadas, nos convida a não desfalecer, pois o Senhor ouve o clamor do coração humilde."),
                    prayer = LocalizedText("Glorioso San Judas, abogado de los casos sin salida, presenta ante Dios mi súplica... y concédeme la gracia que necesito. Amén.", "Glorious St. Jude, advocate of hopeless cases, present my petition before God... and grant me the grace I need. Amen.", "Glorioso São Judas, advogado das causas sem saída, apresentai diante de Deus minha súplica... e concedei-me a graça que necessito. Amém."),
                    intentionGuidance = LocalizedText("Escribe tu petición en un papel y colócala ante una imagen de San Judas, confiándola a su intercesión.", "Write your petition on paper and place it before an image of St. Jude, entrusting it to his intercession.", "Escreva sua petição em um papel e coloque-a diante de uma imagem de São Judas, confiando-a à sua intercessão.")
                ),
                NovenaDay(
                    dayNumber = 4,
                    theme = LocalizedText("Día 4: Caridad fraterna y misericordia", "Day 4: Fraternal Charity and Mercy", "Dia 4: Caridade fraterna e misericórdia"),
                    scripture = "Judas 1, 22-23",
                    meditation = LocalizedText("San Judas nos exhorta a tener misericordia de los que dudan y a salvar a otros, arrancándolos del fuego. La caridad hacia el prójimo es el fruto de la verdadera devoción.", "St. Jude exhorts us to have mercy on those who doubt and to save others by snatching them from the fire. Charity toward neighbor is the fruit of true devotion.", "São Judas nos exorta a ter misericórdia dos que duvidam e a salvar outros, arrebatando-os do fogo. A caridade para com o próximo é o fruto da verdadeira devoção."),
                    prayer = LocalizedText("Apóstol San Judas, enséñame a amar con el corazón de Cristo y a ser instrumento de tu misericordia. Intercede por mi necesidad... Amén.", "Apostle St. Jude, teach me to love with the heart of Christ and to be an instrument of your mercy. Intercede for my need... Amen.", "Apóstolo São Judas, ensinai-me a amar com o coração de Cristo e a ser instrumento de vossa misericórdia. Rogai por minha necessidade... Amém."),
                    intentionGuidance = LocalizedText("Haz un gesto concreto de caridad por alguien que sufra hoy.", "Perform a concrete act of charity for someone who is suffering today.", "Pratique hoje um gesto concreto de caridade por alguém que sofre.")
                ),
                NovenaDay(
                    dayNumber = 5,
                    theme = LocalizedText("Día 5: Perseverancia en la oración", "Day 5: Perseverance in Prayer", "Dia 5: Perseverança na oração"),
                    scripture = "Lucas 18, 1-8",
                    meditation = LocalizedText("Jesús nos enseñó a orar siempre sin desfallecer. San Judas, amigo de Jesús, nos sostiene en la perseverancia, recordándonos que el Señor responde a su tiempo.", "Jesus taught us to pray always without losing heart. St. Jude, friend of Jesus, sustains us in perseverance, reminding us that the Lord answers in His time.", "Jesus nos ensinou a orar sempre sem desfalecer. São Judas, amigo de Jesus, nos sustenta na perseverança, recordando-nos que o Senhor responde a seu tempo."),
                    prayer = LocalizedText("San Judas Tadeo, compañero del Señor, dame constancia en la oración y paciencia en la espera. Alcánzame la gracia que hoy imploro... Amén.", "St. Jude Thaddeus, companion of the Lord, grant me constancy in prayer and patience in waiting. Obtain for me the grace I implore today... Amen.", "São Judas Tadeu, companheiro do Senhor, dai-me constância na oração e paciência na espera. Alcançai-me a graça que hoje imploro... Amém."),
                    intentionGuidance = LocalizedText("Dedica quince minutos a la oración silenciosa por tu intención, sin pedir nada más que la voluntad de Dios.", "Spend fifteen minutes in silent prayer for your intention, asking only for God's will.", "Dedique quinze minutos à oração silenciosa por sua intenção, pedindo apenas a vontade de Deus.")
                ),
                NovenaDay(
                    dayNumber = 6,
                    theme = LocalizedText("Día 6: Confianza en la Providencia", "Day 6: Trust in Providence", "Dia 6: Confiança na Providência"),
                    scripture = "Mateo 6, 25-34",
                    meditation = LocalizedText("El Señor cuida de cada detalle de nuestra vida. San Judas nos invita a confiar en la Providencia divina, dejando en sus manos nuestras angustias y necesidades.", "The Lord cares for every detail of our life. St. Jude invites us to trust in divine Providence, placing our anxieties and needs in His hands.", "O Senhor cuida de cada detalhe de nossa vida. São Judas nos convida a confiar na Providência divina, colocando em Suas mãos nossas angústias e necessidades."),
                    prayer = LocalizedText("San Judas, intercesor poderoso, ayúdame a confiar en Dios en medio de la incertidumbre. Presenta mi petición... Amén.", "St. Jude, powerful intercessor, help me to trust in God amid uncertainty. Present my petition... Amen.", "São Judas, intercessor poderoso, ajudai-me a confiar em Deus em meio à incerteza. Apresentai minha petição... Amém."),
                    intentionGuidance = LocalizedText("Haz un acto de abandono en la Providencia de Dios, repitiendo: 'Jesús, en Ti confío'.", "Make an act of abandonment to God's Providence, repeating: 'Jesus, I trust in You'.", "Faça um ato de abandono à Providência de Deus, repetindo: 'Jesus, eu confio em Vós'.")
                ),
                NovenaDay(
                    dayNumber = 7,
                    theme = LocalizedText("Día 7: Sanación de heridas y recuerdos", "Day 7: Healing of Wounds and Memories", "Dia 7: Cura das feridas e memórias"),
                    scripture = "Salmo 147, 3",
                    meditation = LocalizedText("Dios sana a los quebrantados de corazón y venda sus heridas. San Judas, consolador de los afligidos, nos acompaña en el proceso de sanación interior.", "God heals the brokenhearted and binds up their wounds. St. Jude, comforter of the afflicted, accompanies us in the process of inner healing.", "Deus cura os quebrantados de coração e enfaixa suas feridas. São Judas, consolador dos aflitos, nos acompanha no processo de cura interior."),
                    prayer = LocalizedText("Apóstol San Judas, consuela mi corazón herido y sana los recuerdos dolorosos. Intercede por mi intención... Amén.", "Apostle St. Jude, comfort my wounded heart and heal painful memories. Intercede for my intention... Amen.", "Apóstolo São Judas, consolai meu coração ferido e curai as memórias dolorosas. Rogai por minha intenção... Amém."),
                    intentionGuidance = LocalizedText("Perdona a alguien que te haya herido y pide al Señor que sane tu memoria.", "Forgive someone who has wounded you and ask the Lord to heal your memory.", "Perdoe alguém que o tenha ferido e peça ao Senhor que cure sua memória.")
                ),
                NovenaDay(
                    dayNumber = 8,
                    theme = LocalizedText("Día 8: Fortaleza ante el desánimo", "Day 8: Strength against Discouragement", "Dia 8: Força contra o desânimo"),
                    scripture = "Filipenses 4, 13",
                    meditation = LocalizedText("Todo lo puedo en Cristo que me fortalece. San Judas nos anima a no perder el ánimo, porque el Señor es nuestra fortaleza en la debilidad.", "I can do all things in Christ who strengthens me. St. Jude encourages us not to lose heart, for the Lord is our strength in weakness.", "Tudo posso naquele que me fortalece. São Judas nos anima a não perder o ânimo, pois o Senhor é nossa força na fraqueza."),
                    prayer = LocalizedText("San Judas Tadeo, escudo de los desanimados, levanta mi espíritu y dame valor para seguir. Presenta mi petición... Amén.", "St. Jude Thaddeus, shield of the discouraged, lift my spirit and give me courage to go on. Present my petition... Amen.", "São Judas Tadeu, escudo dos desanimados, levantai meu espírito e dai-me coragem para prosseguir. Apresentai minha petição... Amém."),
                    intentionGuidance = LocalizedText("Haz una obra de misericordia espiritual: anima a alguien que esté triste o desanimado.", "Perform a spiritual work of mercy: encourage someone who is sad or discouraged.", "Pratique uma obra de misericórdia espiritual: anime alguém que esteja triste ou desanimado.")
                ),
                NovenaDay(
                    dayNumber = 9,
                    theme = LocalizedText("Día 9: Acción de gracias y consagración", "Day 9: Thanksgiving and Consecration", "Dia 9: Ação de graças e consagração"),
                    scripture = "1 Tesalonicenses 5, 16-18",
                    meditation = LocalizedText("Al concluir la novena, damos gracias a Dios por la intercesión de San Judas. Consagramos nuestra vida y nuestra intención a su patrocinio, confiando en la promesa de auxilio.", "As the novena concludes, we give thanks to God for St. Jude's intercession. We consecrate our life and our intention to his patronage, trusting in the promise of aid.", "Ao concluir a novena, damos graças a Deus pela intercessão de São Judas. Consagramos nossa vida e nossa intenção ao seu patrocínio, confiando na promessa de socorro."),
                    prayer = LocalizedText("¡Oh glorioso San Judas Tadeo! Te consagro mi vida y mi intención... Dame la gracia de vivir en la fe y de glorificar a Dios en todo. Amén.", "O glorious St. Jude! I consecrate my life and my intention... Grant me the grace to live in faith and to glorify God in all things. Amen.", "Ó glorioso São Judas Tadeu! Consagro a vós minha vida e minha intenção... Concedei-me a graça de viver na fé e glorificar a Deus em tudo. Amém."),
                    intentionGuidance = LocalizedText("Da gracias a Dios por la intercesión de San Judas y comprométete a testimoniar tu fe.", "Give thanks to God for St. Jude's intercession and commit to witnessing your faith.", "Dê graças a Deus pela intercessão de São Judas e comprometa-se a testemunhar sua fé.")
                )
            )
        ),
        Novena(
            id = "undoer_knots",
            title = LocalizedText("Novena a Nuestra Señora Desatanudos", "Novena to Our Lady Undoer of Knots", "Novena a Nossa Senhora Desatadora dos Nós"),
            patron = LocalizedText("Madre de Misericordia que Desata los Nudos del Alma", "Mother of Mercy Who Unties the Knots of Life", "Mãe que Desata os Nós da Vida"),
            category = LocalizedText("Matrimonios, Familias y Sanación", "Marriage, Family and Healing", "Famílias e Reconciliação"),
            description = LocalizedText(
                "Devoción predilecta del Papa Francisco. Inspirada en San Ireneo: 'El nudo de la desobediencia de Eva fue desatado por la obediencia de María'.",
                "Deeply promoted by Pope Francis, this devotion invokes Mary to untie the agonizing knots in our hearts, relationships, and health.",
                "Especialmente difundida pelo Papa Francisco. Maria desata os nós da intriga, da discórdia e da doença."
            ),
            miraculousHistory = LocalizedText(
                "Surgió en Augsburgo (Alemania) en 1700, cuando una pintura conmovió a un matrimonio a punto de romperse y restauró milagrosamente su unidad.",
                "Originated in Germany in 1700 when an oil painting miraculously healed an irreconcilable marriage.",
                "Surgiu na Alemanha no século XVIII, reconciliando milagrosamente um matrimônio prestes a se dissolver."
            ),
            promises = LocalizedText(
                "Desata nudos de resentimiento, heridas del pasado, problemas laborales y de salud.",
                "Untangling confusion, healing generational wounds, and restoring domestic peace.",
                "Paz nos lares, reconciliação entre parentes e cura das feridas da alma."
            ),
            days = listOf(
                NovenaDay(
                    dayNumber = 1,
                    theme = LocalizedText("Día 1: El nudo de la confusión y la falta de paz", "Day 1: The Knot of Confusion and Lack of Peace", "Dia 1: O nó da confusão e da falta de paz"),
                    scripture = "Lucas 1, 26-38",
                    meditation = LocalizedText("María, Desatadora de los Nudos, recibe la confusión que enturbia mi mente y mi corazón. Ella, llena de gracia, desata el nudo de la ansiedad que me impide ver la voluntad de Dios.", "Mary, Undoer of Knots, receives the confusion that clouds my mind and heart. Full of grace, she unties the knot of anxiety that prevents me from seeing God's will.", "Maria, Desatadora dos Nós, recebe a confusão que turva minha mente e meu coração. Cheia de graça, ela desata o nó da ansiedade que me impede de ver a vontade de Deus."),
                    prayer = LocalizedText("Virgen María, Madre del amor hermoso, desata el nudo de la confusión que entorpece mi camino. Por tu santa intercesión, concédeme la paz que hoy te suplico... Amén.", "Holy Mary, Mother of fair love, untie the knot of confusion that obstructs my path. Intercede for me before your Divine Son. Amen.", "Virgem Maria, Mãe que nunca desampara um filho, desatai o nó da confusão e trazei-me a paz de Cristo. Amém."),
                    intentionGuidance = LocalizedText("Identifica el problema que te quita la paz y entrégalo a María con confianza.", "Name the worry that steals your peace and entrust it to Mary with confidence.", "Identifique o problema que lhe tira a paz e entregue-o a Maria com confiança.")
                ),
                NovenaDay(
                    dayNumber = 2,
                    theme = LocalizedText("Día 2: El nudo de la soledad y el abandono", "Day 2: The Knot of Loneliness and Abandonment", "Dia 2: O nó da solidão e do abandono"),
                    scripture = "Juan 19, 25-27",
                    meditation = LocalizedText("Al pie de la Cruz, María recibió a Juan como hijo. En la soledad y el abandono, ella nos acoge como madre y desata el nudo del aislamiento.", "At the foot of the Cross, Mary received John as her son. In loneliness and abandonment, she welcomes us as mother and unties the knot of isolation.", "Ao pé da Cruz, Maria recebeu João como filho. Na solidão e no abandono, ela nos acolhe como mãe e desata o nó do isolamento."),
                    prayer = LocalizedText("Madre de Misericordia, desata el nudo de la soledad que oprime mi corazón y dame la certeza de tu compañía maternal. Amén.", "Mother of Mercy, untie the knot of loneliness that oppresses my heart and give me the certainty of your maternal companionship. Amen.", "Mãe de Misericórdia, desatai o nó da solidão que oprime meu coração e dai-me a certeza de vossa companhia maternal. Amém."),
                    intentionGuidance = LocalizedText("Visita a alguien que esté solo o enfermo y ofrece tu compañía como oración.", "Visit someone who is alone or sick and offer your companionship as prayer.", "Visite alguém que esteja só ou doente e ofereça sua companhia como oração.")
                ),
                NovenaDay(
                    dayNumber = 3,
                    theme = LocalizedText("Día 3: El nudo del resentimiento y el perdón", "Day 3: The Knot of Resentment and Forgiveness", "Dia 3: O nó do ressentimento e do perdão"),
                    scripture = "Mateo 18, 21-22",
                    meditation = LocalizedText("El resentimiento encadena el alma. María, obediente y libre de amargura, nos enseña a perdonar setenta veces siete, desatando el nudo del rencor.", "Resentment chains the soul. Mary, obedient and free of bitterness, teaches us to forgive seventy times seven, untying the knot of rancor.", "O ressentimento acorrenta a alma. Maria, obediente e livre de amargura, nos ensina a perdoar setenta vezes sete, desatando o nó do rancor."),
                    prayer = LocalizedText("Desatadora de Nudos, libera mi corazón del resentimiento y dame la gracia de perdonar de todo corazón. Amén.", "Undoer of Knots, free my heart from resentment and grant me the grace to forgive wholeheartedly. Amen.", "Desatadora dos Nós, libertai meu coração do ressentimento e concedei-me a graça de perdoar de todo o coração. Amém."),
                    intentionGuidance = LocalizedText("Haz un acto de perdón hacia quien te haya ofendido y pide a María que sane la herida.", "Make an act of forgiveness toward anyone who has hurt you and ask Mary to heal the wound.", "Faça um ato de perdão para quem o tenha ofendido e peça a Maria que sane a ferida.")
                ),
                NovenaDay(
                    dayNumber = 4,
                    theme = LocalizedText("Día 4: El nudo de la familia y el matrimonio", "Day 4: The Knot of Family and Marriage", "Dia 4: O nó da família e do matrimônio"),
                    scripture = "Efesios 5, 25-33",
                    meditation = LocalizedText("María es la Madre de la Sagrada Familia. Ella intercede por los hogares divididos y desata los nudos de la discordia, restaurando la unidad y el amor.", "Mary is the Mother of the Holy Family. She intercedes for divided homes and unties the knots of discord, restoring unity and love.", "Maria é a Mãe da Sagrada Família. Ela intercede pelos lares divididos e desata os nós da discórdia, restaurando a unidade e o amor."),
                    prayer = LocalizedText("Madre de la Sagrada Familia, desata los nudos que amenazan mi hogar y reina en mi familia con tu paz. Amén.", "Mother of the Holy Family, untie the knots that threaten my home and reign in my family with your peace. Amen.", "Mãe da Sagrada Família, desatai os nós que ameaçam meu lar e reinai em minha família com vossa paz. Amém."),
                    intentionGuidance = LocalizedText("Reza el Rosario en familia o por la reconciliación de un ser querido.", "Pray the Rosary as a family or for the reconciliation of a loved one.", "Reze o Rosário em família ou pela reconciliação de um ente querido.")
                ),
                NovenaDay(
                    dayNumber = 5,
                    theme = LocalizedText("Día 5: El nudo de la salud y el sufrimiento", "Day 5: The Knot of Health and Suffering", "Dia 5: O nó da saúde e do sofrimento"),
                    scripture = "Marcos 5, 25-34",
                    meditation = LocalizedText("La mujer que tocó el manto de Jesús fue sanada. María, salud de los enfermos, intercede por quienes sufren en el cuerpo y desata el nudo del dolor.", "The woman who touched Jesus' cloak was healed. Mary, health of the sick, intercedes for those who suffer in body and unties the knot of pain.", "A mulher que tocou o manto de Jesus foi curada. Maria, saúde dos enfermos, intercede por quem sofre no corpo e desata o nó da dor."),
                    prayer = LocalizedText("María, salud de los enfermos, desata el nudo de mi enfermedad y dame paciencia redentora. Amén.", "Mary, health of the sick, untie the knot of my illness and grant me redemptive patience. Amen.", "Maria, saúde dos enfermos, desatai o nó de minha doença e concedei-me paciência redentora. Amém."),
                    intentionGuidance = LocalizedText("Ofrece tu sufrimiento por la conversión de un pecador y pide la intercesión de María.", "Offer your suffering for the conversion of a sinner and ask for Mary's intercession.", "Ofereça seu sofrimento pela conversão de um pecador e peça a intercessão de Maria.")
                ),
                NovenaDay(
                    dayNumber = 6,
                    theme = LocalizedText("Día 6: El nudo de las necesidades económicas", "Day 6: The Knot of Financial Need", "Dia 6: O nó das necessidades econômicas"),
                    scripture = "Mateo 6, 26-33",
                    meditation = LocalizedText("Dios viste los lirios del campo y alimenta las aves. María, proveedora de Caná, intercede por nuestras necesidades materiales y desata el nudo de la escasez.", "God clothes the lilies of the field and feeds the birds. Mary, provider at Cana, intercedes for our material needs and unties the knot of scarcity.", "Deus veste os lírios do campo e alimenta as aves. Maria, provedora em Caná, intercede por nossas necessidades materiais e desata o nó da escassez."),
                    prayer = LocalizedText("Virgen María, desata el nudo de mis necesidades económicas y dame confianza en la Providencia de Dios. Amén.", "Virgin Mary, untie the knot of my financial needs and give me trust in God's Providence. Amen.", "Virgem Maria, desatai o nó de minhas necessidades econômicas e dai-me confiança na Providência de Deus. Amém."),
                    intentionGuidance = LocalizedText("Haz un acto de generosidad con alguien necesitado y confía en la Providencia.", "Perform an act of generosity toward someone in need and trust in Providence.", "Pratique um ato de generosidade com alguém necessitado e confie na Providência.")
                ),
                NovenaDay(
                    dayNumber = 7,
                    theme = LocalizedText("Día 7: El nudo de las adicciones y los malos hábitos", "Day 7: The Knot of Addictions and Bad Habits", "Dia 7: O nó dos vícios e maus hábitos"),
                    scripture = "Romanos 7, 15-25",
                    meditation = LocalizedText("El bien que quiero hacer no lo hago. María, llena de gracia, intercede por la libertad interior y desata el nudo de las adicciones que esclavizan el alma.", "The good I want to do I do not do. Mary, full of grace, intercedes for inner freedom and unties the knot of addictions that enslave the soul.", "O bem que quero fazer não faço. Maria, cheia de graça, intercede pela liberdade interior e desata o nó dos vícios que escravizam a alma."),
                    prayer = LocalizedText("Desatadora de Nudos, libera mi voluntad de toda esclavitud y dame la fuerza para vencer mis malos hábitos. Amén.", "Undoer of Knots, free my will from all slavery and give me strength to overcome my bad habits. Amen.", "Desatadora dos Nós, libertai minha vontade de toda escravidão e dai-me força para vencer meus maus hábitos. Amém."),
                    intentionGuidance = LocalizedText("Haz una renuncia concreta hoy y pide a María la gracia de la libertad interior.", "Make a concrete renunciation today and ask Mary for the grace of inner freedom.", "Faça hoje uma renúncia concreta e peça a Maria a graça da liberdade interior.")
                ),
                NovenaDay(
                    dayNumber = 8,
                    theme = LocalizedText("Día 8: El nudo de la desesperación y la falta de fe", "Day 8: The Knot of Despair and Lack of Faith", "Dia 8: O nó do desespero e da falta de fé"),
                    scripture = "Marcos 9, 24",
                    meditation = LocalizedText("Señor, creo, ayuda a mi incredulidad. María, estrella de la mañana, intercede por las almas que han perdido la fe y desata el nudo de la desesperación.", "Lord, I believe, help my unbelief. Mary, morning star, intercedes for souls who have lost faith and unties the knot of despair.", "Senhor, creio, ajudai minha incredulidade. Maria, estrela da manhã, intercede pelas almas que perderam a fé e desata o nó do desespero."),
                    prayer = LocalizedText("Madre de la fe, desata el nudo de mi desesperación y renueva en mí la esperanza en Cristo. Amén.", "Mother of faith, untie the knot of my despair and renew in me hope in Christ. Amen.", "Mãe da fé, desatai o nó de meu desespero e renovai em mim a esperança em Cristo. Amém."),
                    intentionGuidance = LocalizedText("Reza por alguien que haya perdido la fe o esté en desesperación.", "Pray for someone who has lost faith or is in despair.", "Reze por alguém que tenha perdido a fé ou esteja em desespero.")
                ),
                NovenaDay(
                    dayNumber = 9,
                    theme = LocalizedText("Día 9: Consagración y acción de gracias", "Day 9: Consecration and Thanksgiving", "Dia 9: Consagração e ação de graças"),
                    scripture = "Lucas 1, 46-55",
                    meditation = LocalizedText("María proclama la grandeza del Señor. Al finalizar la novena, consagramos nuestra vida a la Desatadora de Nudos, agradeciendo por los favores recibidos y confiando en su intercesión.", "Mary proclaims the greatness of the Lord. At the end of the novena, we consecrate our life to the Undoer of Knots, thanking for favors received and trusting in her intercession.", "Maria proclama a grandeza do Senhor. Ao final da novena, consagramos nossa vida à Desatadora dos Nós, agradecendo pelos favores recebidos e confiando em sua intercessão."),
                    prayer = LocalizedText("Virgen María, Desatadora de los Nudos, consagro mi vida y mi intención a tu cuidado maternal. Desata todos los nudos que me separan de tu Hijo. Amén.", "Virgin Mary, Undoer of Knots, I consecrate my life and my intention to your maternal care. Untie all the knots that separate me from your Son. Amen.", "Virgem Maria, Desatadora dos Nós, consagro minha vida e minha intenção ao vosso cuidado maternal. Desatai todos os nós que me separam de vosso Filho. Amém."),
                    intentionGuidance = LocalizedText("Da gracias a Dios y a María por los nudos desatados y renueva tu consagración.", "Give thanks to God and Mary for the knots untied and renew your consecration.", "Dê graças a Deus e a Maria pelos nós desatados e renove sua consagração.")
                )
            )
        ),
        CompleteNovenasData.sacredHeartNovena,
        CompleteNovenasData.divineMercyNovena
    )

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
