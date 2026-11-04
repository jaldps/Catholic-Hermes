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

    val officialNovenas: List<Novena> = CrawledNovenasData.allNovenas

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
            title = LocalizedText("El Acordaos (Memorare)", "The Memorare", "O Lembrai-vos (Memorare)"),
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
        ),
        VaticanPrayer(
            id = "our_father",
            title = LocalizedText("Padre Nuestro", "Our Father", "Pai Nosso"),
            latinTitle = "Pater Noster",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Padre nuestro, que estás en el cielo, santificado sea tu Nombre; venga a nosotros tu Reino; hágase tu voluntad en la tierra como en el cielo.", "Our Father, who art in heaven, hallowed be thy name; thy kingdom come; thy will be done on earth, as it is in heaven.", "Pai nosso, que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu."),
            responseText = LocalizedText("Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; y no nos dejes caer en la tentación, y líbranos del mal. Amén.", "Give us this day our daily bread; and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.", "Dai-nos hoje o pão nosso de cada dia; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém."),
            whenToPray = LocalizedText("La oración fundamental enseñada por Cristo a sus discípulos. Se reza en la Misa y diariamente.", "The foundational prayer taught by Christ to his disciples. Prayed at Mass and daily.", "A oração fundamental ensinada por Jesus aos discípulos. Rezada na Missa e diariamente."),
            thematicTags = listOf("daily_routine", "faith")
        ),
        VaticanPrayer(
            id = "hail_mary",
            title = LocalizedText("Ave María", "Hail Mary", "Ave Maria"),
            latinTitle = "Ave Maria",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Dios te salve, María, llena eres de gracia; el Señor es contigo; bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús.", "Hail Mary, full of grace, the Lord is with thee; blessed art thou among women, and blessed is the fruit of thy womb, Jesus.", "Ave Maria, cheia de graça, o Senhor é convosco; bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus."),
            responseText = LocalizedText("Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.", "Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.", "Santa Maria, Mãe de Deus, rogai por nós pecadores, agora e na hora de nossa morte. Amém."),
            whenToPray = LocalizedText("La oración mariana más venerada de la Iglesia, basada en las palabras del Ángel Gabriel y de Santa Isabel.", "The Church's most cherished Marian prayer, from the words of Gabriel and Elizabeth.", "A oração mariana mais venerada da Igreja, com palavras do Anjo Gabriel e de Santa Isabel."),
            thematicTags = listOf("marian", "daily_routine")
        ),
        VaticanPrayer(
            id = "glory_be",
            title = LocalizedText("Gloria al Padre", "Glory Be", "Glória ao Pai"),
            latinTitle = "Gloria Patri",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Gloria al Padre, y al Hijo, y al Espíritu Santo.", "Glory be to the Father, and to the Son, and to the Holy Spirit.", "Glória ao Pai, ao Filho e ao Espírito Santo."),
            responseText = LocalizedText("Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.", "As it was in the beginning, is now, and ever shall be, world without end. Amen.", "Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém."),
            whenToPray = LocalizedText("Al final de cada decena del Rosario y en la liturgia de las horas.", "At the end of each decade of the Rosary and in the Liturgy of the Hours.", "Ao final de cada dezena do Terço e na Liturgia das Horas."),
            thematicTags = listOf("daily_routine", "faith")
        ),
        VaticanPrayer(
            id = "fatima_prayer",
            title = LocalizedText("Oración de Fátima", "The Fatima Prayer", "A Oração de Fátima"),
            latinTitle = "Prece do Avé",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("¡Oh Jesús mío, perdona nuestras culpas! Líbranos del fuego del infierno. Lleva al cielo a todas las almas, especialmente a las más necesitadas de tu misericordia.", "O my Jesus, forgive us our sins, save us from the fires of hell. Lead all souls to Heaven, especially those who are most in need of thy mercy.", "Ó meu Jesus, perdoai-nos as nossas culpas, livrai-nos do fogo do inferno, e conduzi ao Céu todas as almas, principalmente as que mais precisam da vossa misericórdia."),
            responseText = LocalizedText("Santa María, Madre de Dios, ruega por nosotros.", "Holy Mary, Mother of God, pray for us.", "Santa Maria, Mãe de Deus, rogai por nós."),
            whenToPray = LocalizedText("Enseñada por la Virgen en Fátima (1917). Se añade tras cada decena del Rosario.", "Taught by Mary at Fatima in 1917. Added after each decade of the Rosary.", "Ensinada por Nossa Senhora em Fátima em 1917. Acrescenta-se após cada dezena do Terço."),
            thematicTags = listOf("marian", "forgiveness", "daily_routine")
        ),
        VaticanPrayer(
            id = "regina_caeli",
            title = LocalizedText("Reina del Cielo", "Regina Caeli", "Regina Caeli"),
            latinTitle = "Regina Caeli",
            category = PrayerCategory.ANGELUS,
            leaderText = LocalizedText("V. Reina del cielo, alégrate, aleluya. R. El que mereciste llevar en tu seno, aleluya.\nV. Porque resucitó según lo dicho, aleluya. R. Ruega al Señor por nosotros, aleluya.\nV. Alégrate, Virgen María, aleluya. R. Porque el Señor ha resucitado verdaderamente, aleluya.\nV. Oremos: Oh Dios, que por la resurrección de tu Hijo, nuestro Señor Jesucristo, te has dignado dar la alegría al mundo, concédenos, por lo tanto, por intercesión de la Virgen María, Madre de Dios, alcancemos los gozos de la vida eterna. Por el mismo Cristo nuestro Señor. Amén.", "V. Queen of Heaven, rejoice, alleluia. R. For He whom thou didst merit to bear, alleluia.\nV. He has risen, as He said, alleluia. R. Pray for us to God, alleluia.\nV. Rejoice also, O Virgin Mary, alleluia. R. For the Lord has truly risen, alleluia.\nV. Let us pray: O God, who, through the resurrection of thy Son, our Lord Jesus Christ, hast been pleased to give joy to the world; grant, we beseech thee, that by the intercession of the Virgin Mary, his Mother, we may obtain the joys of everlasting life. Through the same Christ our Lord. Amen.", "V. Rainha do céu, alegrai-vos, aleluia. R. Porque Aquele que merecestes trazer no vosso seio, aleluia.\nV. Ressuscitou como disse, aleluia. R. Rogai por nós a Deus, aleluia.\nV. Exultai, Virgem Maria, aleluia. R. Porque o Senhor ressuscitou verdadeiramente, aleluia.\nV. Oremos: Ó Deus, que pela ressurreição do vosso Filho, nosso Senhor Jesus Cristo, Vos dignastes dar alegria ao mundo, concedei-nos, por intercessão da Virgem Maria, sua Mãe, que alcancemos as alegrias da vida eterna. Por Cristo, nosso Senhor. Amém."),
            responseText = LocalizedText("Alégrate, Virgen María, aleluya.", "Rejoice, Virgin Mary, alleluia.", "Exultai, Virgem Maria, aleluia."),
            whenToPray = LocalizedText("De Domingo de Resurrección hasta Pentecostés, sustituye al Ángelus a las 12:00.", "From Easter Sunday to Pentecost, prayed at noon in place of the Angelus.", "Do Domingo da Ressurreição até Pentecostes, rezado ao meio-dia em lugar do Angelus."),
            thematicTags = listOf("marian", "easter", "daily_routine")
        ),
        VaticanPrayer(
            id = "salve_regina",
            title = LocalizedText("Salve, Reina del Cielo", "Salve Regina", "Salve Rainha"),
            latinTitle = "Salve Regina",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText("Salve, Reina del cielo, Madre de misericordia. Vida, dulzura y esperanza nuestra: Salve.\nA ti llamamos los desterrados hijos de Eva; a ti suspiramos, gimiendo y llorando en este valle de lágrimas.\nEa, pues, Señora, abogada nuestra, vuelve a nosotros esos tus ojos misericordiosos; y después de este destierro muéstranos a Jesús, fruto de tu vientre.\n¡Oh clementísima, oh piadosa, oh dulce y siempre Virgen María!", "Hail, Holy Queen, Mother of mercy, our life, our sweetness and our hope. To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears.\nTurn then, O most gracious advocate, thine eyes of mercy toward us; and after this our exile show unto us the blessed fruit of thy womb, Jesus.\nO clement, O loving, O sweet Virgin Mary.", "Salve, Rainha, Mãe de misericórdia, vida, doçura, esperança nossa, salve. A vós clamamos, os degredados filhos de Eva. A vós suspiramos, gemendo e chorando, neste vale de lágrimas.\nEia, pois, Advogada nossa, esses vossos olhos misericordiosos a nós volvei. E depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre.\nÓ clemente, ó piedosa, ó doce sempre Virgem Maria."),
            responseText = LocalizedText("Ruega por nosotros, Santa Madre de Dios, para que seamos dignos de alcanzar las promesas de nuestro Señor Jesucristo. Oremos: Omnipotente y sempiterno Dios, que quisiste que la bienaventurada Virgen María, Madre de tu Hijo, fuese Madre y Señora nuestra, concédenos, imploramos, que por su intercesión consigamos en el Reino de los cielos la vida, la alegría y la gloria que merecieron tus hijos. Por el mismo Cristo nuestro Señor. Amén.", "Pray for us, O Holy Mother of God, that we may be made worthy of the promises of Christ. Let us pray: Almighty and everlasting God, who by the Holy Spirit didst prepare the Blessed Virgin Mary to be the Mother of thy Son, grant that we may receive thy mercy through her intercession, and be led to everlasting life. Through the same Christ our Lord. Amen.", "Rogai por nós, Santa Mãe de Deus, para que sejamos dignos das promessas de Cristo. Oremos: Concedei, Todo-Poderoso Deus, que a vossa Igreja, pela intercessão da Virgem Maria, Mãe do vosso Filho, seja guardada e conduzida à vida eterna. Por Cristo, nosso Senhor. Amém."),
            whenToPray = LocalizedText("Al final del Santo Rosario, en la liturgia de las horas y como acción de gracias.", "At the end of the Rosary, in the Liturgy of the Hours, and as an act of thanksgiving.", "Ao final do Terço, na Liturgia das Horas e como ação de graças."),
            indulgenceNote = LocalizedText("Indulgencia parcial concedida por la Iglesia.", "Enriched with a partial indulgence when prayed devoutly.", "Indulgência parcial concedida pela Santa Sé quando rezada devotamente."),
            thematicTags = listOf("marian", "daily_routine")
        ),
        VaticanPrayer(
            id = "ave_regina_caelorum",
            title = LocalizedText("Ave, Reina de los Cielos", "Ave Regina Caelorum", "Ave Rainha dos Céus"),
            latinTitle = "Ave Regina Caelorum",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText("Ave, Reina de los cielos; Ave, Señora de los ángeles; Ave, raíz, Ave, puerta, por la que el mundo alcanzó la luz.", "Hail, O Queen of Heaven. Hail, O Lady of Angels. Hail, thou root, hail, thou gate, by which the world hath seen the light.", "Ave, Rainha dos céus; Ave, Senhora dos anjos; Ave, raiz, Ave, porta, pela qual o mundo alcançou a luz."),
            responseText = LocalizedText("Alégrate, Virgen gloriosa, digna de toda alabanza. ¡Oh Virgen María, ruega por nosotros a Cristo!", "Rejoice, O glorious Virgin, worthy of all praise. Intercede for us with Christ the Lord.", "Alegrai-vos, ó Virgem gloriosa, digna de todo louvor. Rogai por nós a Cristo."),
            whenToPray = LocalizedText("En la liturgia de las horas, especialmente en tiempo ordinario.", "In the Liturgy of the Hours, especially in Ordinary Time.", "Na Liturgia das Horas, especialmente no Tempo Comum."),
            thematicTags = listOf("marian", "daily_routine")
        ),
        VaticanPrayer(
            id = "alma_missionum",
            title = LocalizedText("Alma Misionera de la Madre del Redentor", "Alma Missionum Redemptoris Mater", "Alma Missionum Redemptoris Mater"),
            latinTitle = "Alma Missionum Redemptoris Mater",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText("Alma misionera de la Madre del Redentor, Reina del mundo, ruega por nosotros.\nCristo, escúchanos. Cristo, escúchanos. Cristo, escúchanos.\nDios Padre, ten piedad de nosotros. Dios Hijo, Redentor del mundo, ten piedad de nosotros. Dios Espíritu Santo, ten piedad de nosotros.", "Alma Missionum, Mother of the Redeemer, Queen of the world, pray for us.\nChrist, hear us. Christ, hear us. Christ, hear us.\nGod the Father in heaven, have mercy on us. God the Son, Redeemer of the world, have mercy on us. God the Holy Spirit, have mercy on us.", "Alma Missionum, Mãe do Redentor, Rainha do mundo, rogai por nós.\nCristo, ouvi-nos. Cristo, ouvi-nos. Cristo, ouvi-nos.\nDeus Pai que estais no céu, tende piedade de nós. Deus Filho, Redentor do mundo, tende piedade de nós. Deus Espírito Santo, tende piedade de nós."),
            responseText = LocalizedText("Santa María, Madre de la Iglesia, ruega por los misioneros, para que el Evangelio sea anunciado hasta los confines de la tierra.", "Holy Mary, Mother of the Church, pray for missionaries, that the Gospel may reach the ends of the earth.", "Santa Maria, Mãe da Igreja, rogai pelos missionários, para que o Evangelho chegue até os confins da terra."),
            whenToPray = LocalizedText("En el mes del Rosario y especialmente en el mes de octubre y en las intenciones misioneras.", "During the Rosary month and missions intentions, especially in October.", "No mês do Terço e nas intenções missionárias, especialmente em outubro."),
            thematicTags = listOf("marian", "mission", "protection")
        ),
        VaticanPrayer(
            id = "sub_tuum_praesidium",
            title = LocalizedText("Bajo tu amparo", "Sub Tuum Praesidium", "Sob a Vossa Proteção"),
            latinTitle = "Sub tuum praesidium",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText("Bajo tu amparo nos acogemos, Santa Madre de Dios; no desprecies las súplicas que te dirigimos en nuestras necesidades, antes bien líbranos de todo peligro, ¡oh siempre Virgen, gloriosa y bendita!", "We fly to thy protection, O Holy Mother of God; despise not our petitions in our necessities, but deliver us always from every danger, O glorious and blessed Virgin.", "A vós, ó santa Mãe de Deus, recorremos, em nossa proteção. Não desprezeis as nossas súplicas em nossas necessidades, mas livrai-nos sempre de todo perigo, ó Virgem gloriosíssima e bendita."),
            responseText = LocalizedText("Ruega por nosotros, Santa Madre de Dios, para que seamos dignos de alcanzar las promesas de Cristo. Amén.", "Pray for us, O Holy Mother of God, that we may be made worthy of the promises of Christ. Amen.", "Rogai por nós, Santa Mãe de Deus, para que sejamos dignos das promessas de Cristo. Amém."),
            whenToPray = LocalizedText("La oración mariana más antigua que se conserva, del siglo III.", "The oldest Marian prayer preserved, from the 3rd century.", "A oração mariana mais antiga conservada, do século III."),
            indulgenceNote = LocalizedText("Indulgencia parcial concedida por la Iglesia. San Pío IX la aprobó.", "Enriched with a partial indulgence.", "Indulgência parcial concedida pela Igreja."),
            thematicTags = listOf("marian", "protection", "daily_routine")
        ),
        VaticanPrayer(
            id = "act_of_faith",
            title = LocalizedText("Acto de Fe", "Act of Faith", "Ato de Fé"),
            latinTitle = "Actus Fidei",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Señor mío Jesucristo, creo firmemente todo lo que has revelado y enseñas por tu santa Iglesia, porque tú lo has dicho y no puedes engañarte ni engañarnos.", "O my God, I firmly believe all that thou hast revealed and that the holy Church proposes to our belief, because thou hast revealed it.", "Meu Senhor e meu Deus, creio firmemente tudo o que revelastes e a Santa Igreja nos propõe a crer, porque Vós o revelastes."),
            responseText = LocalizedText("Aumenta mi fe, Señor, hazla viva y operante por la caridad. Amén.", "Increase my faith, Lord, make it alive and working through love. Amen.", "Aumentai minha fé, Senhor, tornai-a viva por meio da caridade. Amém."),
            whenToPray = LocalizedText("Al despertar, en el examen de conciencia y cuando vacila la fe.", "On waking, in conscience examination, and when faith falters.", "Ao despertar, no exame de consciência e quando a fé vacila."),
            thematicTags = listOf("faith", "daily_routine")
        ),
        VaticanPrayer(
            id = "act_of_hope",
            title = LocalizedText("Acto de Esperanza", "Act of Hope", "Ato de Esperança"),
            latinTitle = "Actus Spei",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Señor mío Jesucristo, espero con certeza la vida eterna, porque tú, que eres infinitamente poderoso y misericordioso, lo has prometido.", "O my Jesus, I hope with certain hope to attain everlasting life, because thou, who art infinitely powerful and merciful, hast promised it.", "Senhor meu Jesus Cristo, espero com firme confiança alcançar a vida eterna, porque Vós, infinitamente poderoso e misericordioso, o prometestes."),
            responseText = LocalizedText("No sean defraudadas mis esperanzas. Amén.", "Let not my hopes be in vain. Amen.", "Não sejam frustradas as minhas esperanças. Amém."),
            whenToPray = LocalizedText("En la oración de la mañana, en la tentación y en el dolor.", "In morning prayer, in temptation and in sorrow.", "Na oração da manhã, na tentação e na dor."),
            thematicTags = listOf("faith", "protection")
        ),
        VaticanPrayer(
            id = "act_of_charity",
            title = LocalizedText("Acto de Caridad", "Act of Charity", "Ato de Caridade"),
            latinTitle = "Actus Caritatis",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Señor mío Jesucristo, te amo sobre todas las cosas y a mi prójimo como a mí mismo, por amor de ti.", "O my Jesus, I love thee above all things, and my neighbor as myself for the love of thee.", "Senhor meu Jesus, eu Vos amo sobre todas as coisas e amo o meu próximo como a mim mesmo por vosso amor."),
            responseText = LocalizedText("Concédeme amar a todos como tú nos amas. Amén.", "Grant that I may love all as thou lovest us. Amen.", "Concedei-me que eu ame a todos, como Vós nos amastes. Amém."),
            whenToPray = LocalizedText("Al despertar y cuando se reza por los demás.", "On waking and when praying for others.", "Ao despertar e quando se reza pelos outros."),
            thematicTags = listOf("peace", "family", "daily_routine")
        ),
        VaticanPrayer(
            id = "act_of_spiritual_communion",
            title = LocalizedText("Comunión Espiritual", "Spiritual Communion", "Comunhão Espiritual"),
            latinTitle = "Communio Spiritualis",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("Jesús mío, creo que estás realmente presente en el Santísimo Sacramento de la Eucaristía.", "My Jesus, I believe that thou art truly present in the Most Blessed Sacrament of the Eucharist.", "Meu Jesus, creio que estais realmente presente no Santíssimo Sacramento da Eucaristia."),
            responseText = LocalizedText("Te amo sobre todas las cosas y deseo recibirte en el alma. Ya que no puedo hacerlo sacramentalmente, ven al menos espiritualmente a mi corazón. Como si ya te hubiera recibido, me uno totalmente a ti. No permitas que me aparte de ti.", "I love thee above all things, and I desire to receive thee into my soul. Since I cannot at this moment receive thee sacramentally, come at least spiritually into my heart. As though I had already received thee, I embrace thee wholly. Never permit me to be separated from thee.", "Eu Vos amo sobre todas as coisas e desejo receber-Vos em minha alma. Já que não posso fazê-lo sacramentalmente, vinde ao menos espiritualmente ao meu coração. Como se já Vos tivesse recebido, abraço-Vos inteiramente. Nunca permitais que me separe de Vós."),
            whenToPray = LocalizedText("Cuando no se puede comulgar sacramentalmente, ante el Sagrario o en la adoración.", "When sacramental Communion is impossible, before the Tabernacle or in adoration.", "Quando não é possível comungar sacramentalmente, diante do Sacrário ou na adoração."),
            indulgenceNote = LocalizedText("Indulgencia parcial concedida por la Iglesia.", "Enriched with a partial indulgence.", "Indulgência parcial concedida pela Igreja."),
            thematicTags = listOf("eucharist", "daily_routine")
        ),
        VaticanPrayer(
            id = "peace_prayer",
            title = LocalizedText("Oración por la Paz de San Francisco", "Prayer of Saint Francis for Peace", "Oração de São Francisco pela Paz"),
            latinTitle = "Oratio Sancti Francisci",
            category = PrayerCategory.PROTECTION,
            leaderText = LocalizedText("Señor, hazme instrumento de tu paz. Donde haya odio, siembre yo amor; donde haya ofensa, perdón; donde haya duda, fe; donde haya desesperación, esperanza; donde haya tinieblas, luz; donde haya tristeza, alegría.", "Lord, make me an instrument of thy peace. Where there is hatred, let me sow love; where there is injury, pardon; where there is doubt, faith; where there is despair, hope; where there is darkness, light; where there is sadness, joy.", "Senhor, fazei-me instrumento de vossa paz. Onde houver ódio, que eu semeie o amor; onde houver ofensa, o perdão; onde houver dúvida, a fé; onde houver desespero, a esperança; onde houver trevas, a luz; onde houver tristeza, a alegria."),
            responseText = LocalizedText("Que no busque tanto ser amado cuanto amar; que no busque tanto ser comprendido cuanto comprender; que no busque tanto ser consolado cuanto consolar. Porque dando se recibe; perdonando se es perdonado; muriendo se resucita a la vida eterna.", "Grant that I seek not so much to be loved as to love; to be understood as to understand; to be consoled as to console. For it is in giving that we receive; it is in pardoning that we are pardoned; it is in dying that we are born to eternal life.", "Que eu não procure tanto ser amado quanto amar; ser compreendido quanto compreender; ser consolado quanto consolar. Porque é dando que se recebe, é perdoando que se é perdoado, é morrendo que se nasce para a vida eterna."),
            whenToPray = LocalizedText("Para pedir la paz en el mundo, en la familia y en el corazón.", "To pray for peace in the world, in the family, and in the heart.", "Para pedir a paz no mundo, na família e no coração."),
            thematicTags = listOf("peace", "forgiveness")
        ),
        VaticanPrayer(
            id = "come_holy_spirit",
            title = LocalizedText("Ven, Espíritu Santo", "Come, Holy Spirit", "Vinde, Espírito Santo"),
            latinTitle = "Veni Sancte Spiritus",
            category = PrayerCategory.HOLY_SPIRIT,
            leaderText = LocalizedText("Ven, Espíritu Santo, y envía desde el cielo un rayo de tu luz.\nVen, Padre de los pobres; ven, Don de Dios; ven, Luz de los corazones.\nConsolador perfecto, dulce huésped del alma, suavísimo descanso.\nEn el trabajo, descanso; en el ardor, templanza; en el llanto, consuelo.", "Come, Holy Spirit, come, and from thy bright heavenly throne send forth the rays of thy light.\nCome, thou Father of the poor, come, giver of gifts, come, thou light of hearts.\nGreat Consoler, sweet Guest of the soul, sweetest Relief.\nIn labor, rest; in heat, temperateness; in tears, consolation.", "Vinde, Espírito Santo, e dos céus enviai um raio da vossa luz.\nVinde, Pai dos pobres, vinde, Doador dos dons, vinde, Luz dos corações.\nConsolador perfeito, doce Hóspede da alma, suavíssimo Alívio.\nNo trabalho, descanso; no calor, temperança; no pranto, consolo."),
            responseText = LocalizedText("Oh Espíritu Santo, ven y renueva la faz de la tierra. Llena nuestro corazón de tus dones y haznos dóciles a tu inspiración.", "O Holy Spirit, come and renew the face of the earth. Fill our hearts with thy gifts and make us docile to thy inspiration.", "Ó Espírito Santo, vinde e renovai a face da terra. Enchei nossos corações com vossos dons e tornai-nos dóceis à vossa inspiração."),
            whenToPray = LocalizedText("En Pentecostés y al comenzar un camino, retiro o apostolado.", "At Pentecost and when beginning a retreat, journey, or apostolate.", "Em Pentecostes e ao iniciar um retiro, viagem ou apostolado."),
            indulgenceNote = LocalizedText("Indulgencia parcial concedida por la Iglesia.", "Enriched with a partial indulgence.", "Indulgência parcial concedida pela Igreja."),
            thematicTags = listOf("holy_spirit", "faith")
        ),
        VaticanPrayer(
            id = "veni_creator_spiritus",
            title = LocalizedText("Ven, Espíritu Creador", "Come, Creator Spirit", "Vinde, Espírito Criador"),
            latinTitle = "Veni Creator Spiritus",
            category = PrayerCategory.HOLY_SPIRIT,
            leaderText = LocalizedText("Ven, Espíritu Creador, visita las almas de tus fieles y enciende en sus corazones el fuego de tu amor.\nTú, que eres llamado Consolador, don de Dios Altísimo, fuente viva, fuego, caridad y unción espiritual.\nTú eres múltiple en tus dones, dedo de la diestra de Dios Padre, Espíritu de la promesa, lengua de fuego.\nEnciende la luz en nuestros sentidos, infunde el amor en nuestros corazones, fortalece nuestros cuerpos débiles con tu poder.\nAleja de nosotros al enemigo, danos pronto la paz, para que, guiados por ti, evitemos todo mal.\nPor el Padre, sepamos conocer al Hijo; y por ti, al Hijo y al Padre, creamos siempre. Amén.", "Come, Creator Spirit, visit the souls of thy faithful, and fill with thy heavenly grace the hearts which thou hast made.\nThou who art called the Paraclete, the gift of God most high, the living fountain, the fire, the love, and the anointing.\nThou art manifold in thy sevenfold gifts, thou art the finger of God's right hand, thou art the promised Spirit, thou increasest the word.\nKindle the light in our senses, pour love into our hearts, and with thy perpetual strength confirm our frail bodies.\nFar from us the foe, make haste to give us peace, so that with thee as guide we may avoid all that is harmful.\nThrough thee may we know the Father, and the Son, and in thee, the Spirit of both, may we ever believe. Amen.", "Vinde, Espírito Criador, vinde e visitai as almas dos fiéis e enchei de graça divina os corações que criastes.\nTu, que sois chamado Advogado, Dom de Deus Altíssimo, Fonte viva, Fogo, Caridade e Unção espiritual.\nVós sois sete dons em um, o Dedo da mão de Deus, o Espírito da promessa, a Língua de fogo.\nAcendei a luz em nossos sentidos, infundi o amor em nossos corações, fortalecei com vossa força os nossos corpos frágeis.\nLonge de nós o inimigo, dai-nos presto a paz, para que, guiados por Vós, evitemos todo mal.\nPor Vós conheçamos o Pai e o Filho, e em Vós creiamos sempre. Amém."),
            responseText = LocalizedText("Que podamos dar gloria al Padre, al Hijo resucitado y a ti, Espíritu Consolador, por los siglos de los siglos. Amén.", "To God the Father let us ever give praise, and to the Son, our Lord, risen from the grave; and to thee, O Holy Ghost, our Comforter, now and evermore. Amen.", "Glória ao Pai e ao Filho Ressuscitado e a Vós, Espírito Consolador, agora e sempre. Amém."),
            whenToPray = LocalizedText("En Pentecostés, en la Confirmación y al invocar la ayuda del Espíritu.", "At Pentecost, at Confirmation, and when invoking the Spirit's help.", "Em Pentecostes, na Crisma e ao invocar o auxílio do Espírito."),
            indulgenceNote = LocalizedText("Himno tradicional de la Iglesia en Pentecostés.", "Traditional hymn of the Church at Pentecost.", "Hino tradicional da Igreja em Pentecostes."),
            thematicTags = listOf("holy_spirit", "faith")
        ),
        VaticanPrayer(
            id = "guardian_angel",
            title = LocalizedText("Ángel de Dios", "Angel of God", "Santo Anjo do Senhor"),
            latinTitle = "Angele Dei qui custos es",
            category = PrayerCategory.PROTECTION,
            leaderText = LocalizedText("Ángel de Dios, que eres mi custodio, a quien la piedad divina me ha confiado, ilumíname, guárdame, rígeme y gobiérname. Amén.", "Angel of God, my guardian dear, to whom God's love commits me here, ever this day be at my side, to light and guard, to rule and guide. Amen.", "Santo Anjo do Senhor, meu zeloso e leal amigo, a quem Deus me confiou, eu vos venero e agradeço. Assistí-me em todos os momentos, para que eu cumpra fielmente a vontade de Deus. Amém."),
            responseText = LocalizedText("Dios te salve, María, llena eres de gracia.", "Hail Mary, full of grace.", "Ave Maria, cheia de graça."),
            whenToPray = LocalizedText("Para confiarse a la protección del Ángel Custodio, enseñada a los niños.", "For entrusting oneself to the Guardian Angel, taught to children.", "Para se entregar à proteção do Anjo da Guarda, ensinada às crianças."),
            thematicTags = listOf("protection", "family")
        ),
        VaticanPrayer(
            id = "grace_before_meals",
            title = LocalizedText("Acción de Gracias antes de las Comidas", "Grace Before Meals", "Bênção antes das Refeições"),
            latinTitle = "Benedictio Mensae",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText("Bendice, Señor, a nosotros y a estos alimentos que por tu generosidad vamos a recibir. Por Cristo nuestro Señor. Amén.", "Bless us, O Lord, and these thy gifts which we are about to receive from thy bounty through Christ our Lord. Amen.", "Abençoai, Senhor, a nós e a estes dons que por vossa bondade vamos receber. Por Cristo, nosso Senhor. Amém."),
            responseText = LocalizedText("Padre nuestro, que estás en el cielo.", "Our Father, who art in heaven.", "Pai nosso, que estais nos céus."),
            whenToPray = LocalizedText("Al comenzar la comida, en familia o en comunidad.", "Before meals, at home or in community.", "Antes das refeições, em casa ou em comunidade."),
            thematicTags = listOf("daily_routine", "family")
        ),
        VaticanPrayer(
            id = "grace_after_meals",
            title = LocalizedText("Acción de Gracias después de las Comidas", "Grace After Meals", "Ação de Graças após as Refeições"),
            latinTitle = "Gratias",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText("Te damos gracias, Dios todopoderoso, por todos tus beneficios, especialmente por esta comida. Por Cristo nuestro Señor. Amén.", "We give thee thanks, almighty God, for all thy benefits, especially for this meal. Through Christ our Lord. Amen.", "Nós Vos agradecemos, Deus Todo-Poderoso, por todos os vossos benefícios, especialmente por esta refeição. Por Cristo, nosso Senhor. Amém."),
            responseText = LocalizedText("El Señor os bendiga y os guarde en su paz.", "May the Lord bless you and keep you in his peace.", "O Senhor vos abençoe e vos guarde na paz."),
            whenToPray = LocalizedText("Al terminar la comida.", "After meals.", "Após as refeições."),
            thematicTags = listOf("daily_routine", "family")
        ),
        VaticanPrayer(
            id = "eternal_rest",
            title = LocalizedText("Descanso Eterno", "Eternal Rest", "Descanso Eterno"),
            latinTitle = "Requiem Aeternam",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText("Concédeles, Señor, el descanso eterno, y brille para ellos la luz perpetua. Descansen en paz. Amén.", "Eternal rest grant unto them, O Lord, and let perpetual light shine upon them. May they rest in peace. Amen.", "Concedei, Senhor, às almas dos fiéis defuntos o descanso eterno, e brilhe para eles a luz perpétua. Descansem em paz. Amém."),
            responseText = LocalizedText("Señor, por tu misericordia, que todas las almas de los fieles difuntos descansen en paz. Amén.", "Lord, by thy mercy, may all the faithful departed rest in peace. Amen.", "Senhor, por vossa misericórdia, que todas as almas dos fiéis defuntos descansem em paz. Amém."),
            whenToPray = LocalizedText("Al rezar por los difuntos y en el mes de noviembre.", "When praying for the dead and especially in November.", "Ao rezar pelos defuntos e especialmente no mês de novembro."),
            indulgenceNote = LocalizedText("Indulgencia parcial por las almas.", "Enriched with a partial indulgence for the holy souls.", "Indulgência parcial pelas almas."),
            thematicTags = listOf("forgiveness", "peace")
        ),
        VaticanPrayer(
            id = "adoro_te_devote",
            title = LocalizedText("Adoro te devote", "Adoro te devote", "Adoro te devote"),
            latinTitle = "Adoro te devote",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("Adoro te devote, latens Deitas, quae sub his figuris vere latitas: tibi se cor meum totum subicit, quia te contemplans totum deficit.\nVisus, tactus, gustus te fallitur, sed auditu solo creditur mente.\nCredo quidquid dixit Veracitas; nihil veritatis verbo verius.\nIn cruce latebas, hic latet Deitas; sed utrumque credens, te utrumque fateor.\nPlagas sicut Thomas volo vulnerum; Deus meus tu es, etsi te video.\nTe, pie Jesu, rogo, da mihi unum, quod te amo, teque spero, teque amplector.", "I adore thee devoutly, hiding thy deity, veiled here beneath these snows of white. To thee my heart doth bow down wholly, for in contemplating thee it faileth wholly.\nSight, touch, and taste are all deceived, but hearing only is our sure guide; I believe what the Son of God hath spoken; who doth speak a word more true than his?\nOn the cross thou veilest thy deity, here too thou veilest thy majesty; I believe and confess thee under both forms; may I see thee with unveiled face in glory.", "Adoro Te devote, latens Deitas, quae sub his figuris vere latitas: Te se cor meum totum subicit, quia Te contemplans totum deficit.\nVisus, tactus, gustus in Te fallitur, sed auditu solo creditur mente.\nCredo quidquid dixit Veracitas; nil veritatis verbo verius.\nIn cruce latebas, hic latet Deitas; sed utrumque credens, te utrumque fateor.\nPlagas sicut Thomas volo vulnerum; Deus meus Tu es, etsi Te video.\nTe pie Jesu, rogo, da mihi unum, quod te amo, teque spero, teque amplector."),
            responseText = LocalizedText("Jesús, acuérdate de mí. Dame un corazón que te ame siempre y purifícalo en tu Sangre. Amén.", "O Jesus, remember me. Give me a heart that loves thee ever and purify it in thy Blood. Amen.", "Senhor Jesus, lembrai-Vos de mim. Dai-me um coração que sempre Vos ame e purificai-o no vosso Sangue. Amém."),
            whenToPray = LocalizedText("Oración ante el Sagrario, especialmente el Jueves Santo y en Corpus Christi.", "Prayer before the Blessed Sacrament, especially on Holy Thursday and Corpus Christi.", "Oração diante do Santíssimo Sacramento, especialmente na Quinta-feira Santa e Corpus Christi."),
            thematicTags = listOf("eucharist", "faith")
        ),
        VaticanPrayer(
            id = "o_sacrum_convivium",
            title = LocalizedText("Oh sagrado convite", "O Sacred Banquet", "Ó Sagrado Banquete"),
            latinTitle = "O sacrum convivium",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("Oh sagrado convite, en el cual se recibe a Cristo. Se recuerda la memoria de su Pasión, el alma se colma de gracia, y se nos da prenda de la gloria futura. Aleluya.", "O sacred banquet, in which Christ is received. The memory of his Passion is recalled, the soul is filled with grace, and a pledge of future glory is given to us. Alleluia.", "Ó sagrado banquete em que Cristo se dá a nós. Faz-se memória de sua Paixão, a alma se enche de graça e se nos dá o penhor da futura glória. Aleluia."),
            responseText = LocalizedText("Tú nos diste el Pan del cielo, aleluya.", "Thou givest us the Bread of heaven, alleluia.", "Vós nos destes o Pão do Céu, aleluia."),
            whenToPray = LocalizedText("Oración de acción de gracias después de la Comunión.", "A prayer of thanksgiving after Communion.", "Oração de ação de graças após a Comunhão."),
            thematicTags = listOf("eucharist", "faith")
        ),
        VaticanPrayer(
            id = "tantum_ergo",
            title = LocalizedText("Tantum ergo", "Tantum ergo", "Tantum ergo"),
            latinTitle = "Tantum ergo",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("Tantum ergo Sacramentum veneremur cernui: et antiquum documentum novo cedat ritui: praestet fides supplementum sensuum defectui.\nGenitori Genitoque laus et iubilatio, salus, honor, virtus quoque sit et benedictio: procedenti ab utroque compar sit laudatio. Amén.", "Let us, therefore, worship the great Sacrament reverently, bow our heads: let the old law give place to the new, and let faith supply the failings of the senses.\nTo the Father and the Son, let there be glory, praise and honor; and to the Spirit whom both send forth, let there be the same praise. Amen.", "Adoremos, então, o grande Sacramento, reverentes, de cabeça inclinada: ceda a antiga lei à nova, e a fé supra a falta dos sentidos.\nAo Pai e ao Filho sejam glória, louvor e honra; e ao Espírito que de ambos procede, o mesmo louvor. Amém."),
            responseText = LocalizedText("Alabado sea para siempre el Santísimo Sacramento del Altar.", "Let the most Blessed Sacrament of the Altar be forever praised.", "Seja para sempre louvado o Santíssimo Sacramento do Altar."),
            whenToPray = LocalizedText("En la adoración eucarística y en Corpus Christi.", "In Eucharistic adoration and on Corpus Christi.", "Na adoração eucarística e em Corpus Christi."),
            thematicTags = listOf("eucharist", "faith")
        ),
        VaticanPrayer(
            id = "laudes_divinas",
            title = LocalizedText("Alabado sea Dios", "Blessed Be God", "Louvado Seja Deus"),
            latinTitle = "Laudes Divinae",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("Alabado sea Dios. Alabado sea Jesús Sacramentado.", "Blessed be God. Blessed be Jesus in the Most Blessed Sacrament.", "Louvado seja Deus. Louvado seja Jesus Sacramentado."),
            responseText = LocalizedText("Alabada sea la Santísima Trinidad. Alabada sea la Inmaculada Concepción de la Santísima Virgen María. Alabado sea San José, su casto esposo. Alabado sea Jesús, María y José. Alabada sea la Inmaculada Concepción. Alabado sea el Santísimo Sacramento.", "Blessed be the Most Holy Trinity. Blessed be the Immaculate Conception of the Blessed Virgin Mary. Blessed be St Joseph her most chaste spouse. Blessed be Jesus, Mary and Joseph. Blessed be the Immaculate Conception. Blessed be the Most Blessed Sacrament.", "Louvada seja a Santíssima Trindade. Louvada seja a Imaculada Conceição da Santíssima Virgem Maria. Louvado seja São José, seu casto esposo. Louvados sejam Jesus, Maria e José. Louvada seja a Imaculada Conceição. Louvado seja o Santíssimo Sacramento."),
            whenToPray = LocalizedText("Al adorar el Santísimo Sacramento, con jaculatorias repeated.", "When adoring the Blessed Sacrament, with loving ejaculations.", "Ao adorar o Santíssimo Sacramento, com jaculatórias devotas."),
            indulgenceNote = LocalizedText("Indulgencia parcial concedida por la Iglesia.", "Enriched with a partial indulgence.", "Indulgência parcial concedida pela Igreja."),
            thematicTags = listOf("eucharist", "faith")
        ),
        VaticanPrayer(
            id = "sacred_heart_consecration",
            title = LocalizedText("Consagración al Sagrado Corazón de Jesús", "Consecration to the Sacred Heart of Jesus", "Consagração ao Sagrado Coração de Jesus"),
            latinTitle = "Consecratio Cordi Jesu",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText("¡Oh Sagrado Corazón de Jesús! Yo, indigno pecador, me consagro a ti. Concede que de tu Corazón abiertos los caminos de la vida, aprenda a vivir cada día amando a Dios y al prójimo.", "O Sacred Heart of Jesus! I, an unworthy sinner, consecrate myself to thee. Grant that from thy open Heart I may learn to live each day loving God and neighbor.", "Ó Sagrado Coração de Jesus! Eu, pecador indigno, consagro-me a Vós. Concedei que do vosso Coração aberto eu aprenda a viver cada dia amando a Deus e ao próximo."),
            responseText = LocalizedText("Guárdame bajo tu protección y dame paz en la vida y en la muerte. Amén.", "Keep me under thy protection and give me peace in life and in death. Amen.", "Guardai-me sob vossa proteção e dai-me paz na vida e na morte. Amém."),
            whenToPray = LocalizedText("En la fiesta del Sagrado Corazón y en el mes de junio.", "On the feast of the Sacred Heart and in the month of June.", "Na festa do Sagrado Coração e no mês de junho."),
            thematicTags = listOf("eucharist", "protection")
        ),
        VaticanPrayer(
            id = "jesus_prayer",
            title = LocalizedText("Oración de Jesús", "The Jesus Prayer", "A Oração de Jesus"),
            latinTitle = "Prex Iesu",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Señor Jesucristo, Hijo de Dios, ten piedad de mí, pecador.", "Lord Jesus Christ, Son of God, have mercy on me a sinner.", "Senhor Jesus Cristo, Filho de Deus, tende piedade de mim, pecador."),
            responseText = LocalizedText("Gloria al Padre, y al Hijo, y al Espíritu Santo. Amén.", "Glory be to the Father, and to the Son, and to the Holy Spirit. Amen.", "Glória ao Pai, ao Filho e ao Espírito Santo. Amém."),
            whenToPray = LocalizedText("Oración continua del corazón, en el caminar, trabajar y descansar.", "A prayer of the heart without ceasing, while walking, working, and resting.", "Oração contínua do coração, ao andar, trabalhar e descansar."),
            thematicTags = listOf("faith", "daily_routine")
        ),
        VaticanPrayer(
            id = "st_joseph",
            title = LocalizedText("Oración a San José", "Prayer to Saint Joseph", "Oração a São José"),
            latinTitle = "Oremus ad Sanctum Ioseph",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText("Oh San José, Padre y Custodio de la Virgen y de Jesús, ruega por nosotros. Oh San José, protector de la Santa Iglesia, ruega por nosotros.", "O Saint Joseph, father and custodian of the Virgin and of Jesus, pray for us. O Saint Joseph, protector of the Holy Church, pray for us.", "Ó São José, Pai e Custódio da Virgem e de Jesus, rogai por nós. Ó São José, protetor da Santa Igreja, rogai por nós."),
            responseText = LocalizedText("Oh San José, esposo de la Madre de Dios y padre virginal de Jesús, ruega por nosotros. Amén.", "O Saint Joseph, spouse of the Mother of God and virgin father of Jesus, pray for us. Amen.", "Ó São José, esposo da Mãe de Deus e pai adotivo de Jesus, rogai por nós. Amém."),
            whenToPray = LocalizedText("Al invocar su protección para la familia, el trabajo y la Iglesia.", "To invoke his protection for family, work, and the Church.", "Para invocar sua proteção para a família, o trabalho e a Igreja."),
            thematicTags = listOf("family", "protection", "work")
        ),
        VaticanPrayer(
            id = "st_jude_thaddeus",
            title = LocalizedText("Oración a San Judas Tadeo", "Prayer to Saint Jude Thaddeus", "Oração a São Judas Tadeu"),
            latinTitle = "Oremus ad Sanctum Iudam Thaddaeum",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText("Oh santo apóstol San Judas, fiel siervo y amigo de Jesús, la Iglesia te invoca como patrón de los casos imposibles. Intercede por mí, que necesito tu ayuda. Ruega al Señor que escuche mi oración.", "O holy apostle St Jude, faithful servant and friend of Jesus, the Church invokes thee as patron of desperate causes. Intercede for me, who need thy help. Pray to the Lord to hear my prayer.", "Ó santo apóstolo São Judas, fiel servo e amigo de Jesus, a Igreja vos invoca como patrono das causas impossíveis. Intercedei por mim, que necessito de vosso auxílio. Rogai ao Senhor que ouça minha oração."),
            responseText = LocalizedText("Oh San Judas, muestra tu amor y socorreme en mi necesidad. Amén.", "O St Jude, show thy love and aid me in my need. Amen.", "Ó São Judas, mostrai vosso amor e socorrei-me na minha necessidade. Amém."),
            whenToPray = LocalizedText("En casos difíciles y desesperados.", "In difficult and desperate cases.", "Nos casos difíceis e desesperados."),
            thematicTags = listOf("health", "family")
        ),
        VaticanPrayer(
            id = "st_anthony",
            title = LocalizedText("Oración a San Antonio", "Prayer to Saint Anthony", "Oração de Santo Antônio"),
            latinTitle = "Oremus ad Sanctum Antonium",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText("Oh San Antonio de Padua, santo milagroso, intercede por nosotros. Ayúdanos a encontrar lo perdido y a recuperar la gracia de Dios.", "O St Anthony of Padua, glorious wonder-worker, intercede for us. Help us to find what is lost and to recover God's grace.", "Ó Santo Antônio de Padua, glorioso taumaturgo, intercedei por nós. Ajudai-nos a encontrar o que foi perdido e a recuperar a graça de Deus."),
            responseText = LocalizedText("Oh glorioso San Antonio, ruega por nosotros. Amén.", "O glorious St Anthony, pray for us. Amen.", "Ó glorioso Santo Antônio, rogai por nós. Amém."),
            whenToPray = LocalizedText("Para encontrar objetos perdidos y pedir milagros.", "To find lost objects and to ask for miracles.", "Para encontrar objetos perdidos e pedir milagres."),
            thematicTags = listOf("family", "faith")
        ),
        VaticanPrayer(
            id = "evening_prayer",
            title = LocalizedText("Oración de la Noche", "Evening Prayer", "Oração da Noite"),
            latinTitle = "Oratio Nocturna",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText("Señor, al terminar este día, te doy gracias por el bien que he hecho y te pido perdón por el mal y por lo bueno que dejé de hacer.", "Lord, at the close of this day I thank thee for the good I have done and ask forgiveness for the evil done and the good omitted.", "Senhor, ao terminar este dia, agradeço-Vos o bem que fiz e peço perdão pelo mal que fiz e pelo bem que deixei de fazer."),
            responseText = LocalizedText("Si no he amado lo suficiente o he faltado en caridad, dame tu gracia para reparar mañana. Guarda a los míos esta noche y a todos los que amo. Amén.", "If I have not loved enough or failed in charity, give me grace to make amends tomorrow. Keep my loved ones tonight and all whom I love. Amen.", "Se não amei bastante, dai-me a graça de reparar amanhã. Guardai os meus queridos esta noite e todos os que amo. Amém."),
            whenToPray = LocalizedText("Por la noche, tras el examen de conciencia.", "At night, after the examination of conscience.", "À noite, após o exame de consciência."),
            thematicTags = listOf("daily_routine", "forgiveness")
        ),
        VaticanPrayer(
            id = "suscipe_prayer",
            title = LocalizedText("Toma, Señor, y recibe (Suscipe)", "Take, Lord, and Receive (Suscipe)", "Tomai, Senhor, e Recebei (Suscipe)"),
            latinTitle = "Suscipe Domine",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Toma, Señor, y recibe toda mi libertad, mi memoria, mi inteligencia y toda mi voluntad. Todo lo que tengo y poseo, tú me lo has dado; a ti, Señor, todo lo devuelvo y lo pongo en tus manos para que lo gobiernes.", "Take, Lord, and receive all my liberty, my memory, my understanding, and my whole will. All that I have and hold I receive from thy bounty. To thee I return it all and surrender it wholly to thy governance.", "Tomai, Senhor, e recebei toda a minha liberdade, minha memória, meu entendimento e toda a minha vontade. Tudo o que tenho e possuo, vós me destes; a vós, Senhor, tudo devolvo e entrego para que o governeis."),
            responseText = LocalizedText("Haz de mí lo que quieras, con tal que me digas lo que quieres que haga. Amén.", "Dispose of me as thou wilt, provided thou tellest me what thou wilt me to do. Amen.", "Fazei de mim o que quiserdes, contanto que me digais o que devo fazer. Amém."),
            whenToPray = LocalizedText("Oración de San Ignacio de Loyola para la entrega de la vida.", "A prayer of St Ignatius of Loyola for total surrender.", "Oração de Santo Inácio de Loyola para entrega total."),
            thematicTags = listOf("faith", "daily_routine")
        ),
        VaticanPrayer(
            id = "immaculate_conception",
            title = LocalizedText("Oración a la Inmaculada Concepción", "Prayer to the Immaculate Conception", "Oração à Imaculada Conceição"),
            latinTitle = "O Maria sine peccato concepta",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText("¡Oh María, concebida sin pecado! Ruega por nosotros que recurrimos a ti.", "O Mary, conceived without sin, pray for us who have recourse to thee.", "Ó Maria, concebida sem pecado, rogai por nós que recorremos a vós."),
            responseText = LocalizedText("¡Oh María, Madre de nuestro Redentor, ruega por nosotros! Amén.", "O Mary, Mother of our Redeemer, pray for us! Amen.", "Ó Maria, Mãe do nosso Redentor, rogai por nós! Amém."),
            whenToPray = LocalizedText("En la fiesta de la Inmaculada Concepción, el 8 de diciembre.", "On the feast of the Immaculate Conception, December 8.", "Na festa da Imaculada Conceição, 8 de dezembro."),
            thematicTags = listOf("marian", "faith")
        ),
        VaticanPrayer(
            id = "stay_with_me_lord",
            title = LocalizedText("Quédate conmigo, Señor", "Stay with Me, Lord", "Ficai Comigo, Senhor"),
            latinTitle = "Mane nobiscum Domine",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText("Quédate conmigo, Señor, porque debo quedarme contigo. Ha caído la tarde y el día está declinando; yo soy débil, necesito tu fuerza. No me dejes solo.", "Stay with me, Lord, for it is necessary to abide with thee. It is evening, and the day is failing; I am weak, I need thy strength. Do not leave me.", "Ficai comigo, Senhor, porque devo ficar convosco. Caiu a tarde e o dia declina; sou fraco, necessito de vossa força. Não me deixeis."),
            responseText = LocalizedText("Quédate, Señor, conmigo. Amén.", "Stay, Lord, with me. Amen.", "Ficai, Senhor, comigo. Amém."),
            whenToPray = LocalizedText("En la oración de la noche y en la adoración eucarística.", "In night prayer and in Eucharistic adoration.", "Na oração da noite e na adoração eucarística."),
            thematicTags = listOf("daily_routine", "eucharist")
        ),
        VaticanPrayer(
            id = "signarse",
            title = LocalizedText("Signarse (Signo de la Cruz)", "Signing Oneself (Sign of the Cross)", "Fazer o Sinal da Cruz"),
            latinTitle = "Signum Crucis",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("En el nombre del Padre, del Hijo y del Espíritu Santo. Amén.", "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.", "Em nome do Pai, e do Filho, e do Espírito Santo. Amém."),
            whenToPray = LocalizedText("Al comienzo de cada oración y de la Misa: se toca la frente (Padre), los labios o el pecho (Hijo) y los hombros, de izquierda a derecha (Espíritu Santo).", "At the beginning of every prayer and of the Mass: touch the forehead (Father), the lips or the breast (Son), and the shoulders, from left to right (Holy Spirit).", "Ao início de cada oração e da Missa: toca-se a testa (Pai), os lábios ou o peito (Filho) e os ombros, da esquerda para a direita (Espírito Santo)."),
            thematicTags = listOf("daily_routine", "faith", "protection")
        ),
        VaticanPrayer(
            id = "persignarse",
            title = LocalizedText("Persignarse", "Making the Sign of the Cross", "Persignar-se"),
            latinTitle = "Per signum sanctae crucis",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Por la señal de la santa cruz, líbranos Señor Dios nuestro de nuestros enemigos. En el nombre del Padre, del Hijo y del Espíritu Santo. Amén.", "By the sign of the holy cross deliver us, Lord our God, from our enemies. In the name of the Father, and of the Son, and of the Holy Spirit. Amen.", "Pelo sinal da santa cruz, livrai-nos, Senhor nosso Deus, dos nossos inimigos. Em nome do Pai, e do Filho, e do Espírito Santo. Amém."),
            whenToPray = LocalizedText("Antes de comenzar la oración, el Rosario o al recibir una bendición: con el pulgar se dibuja una pequeña cruz en la frente, en los labios y en el pecho.", "Before beginning prayer, the Rosary, or on receiving a blessing: with the thumb draw a small cross on the forehead, on the lips, and on the breast.", "Antes de começar a oração, o Terço ou ao receber uma bênção: com o polegar, desenha-se uma pequena cruz na testa, nos lábios e no peito."),
            thematicTags = listOf("daily_routine", "faith", "protection")
        ),
        VaticanPrayer(
            id = "bendita_sea_tu_pureza",
            title = LocalizedText("Bendita sea tu pureza", "Blessed Be Your Purity", "Bendita seja a vossa pureza"),
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Bendita sea tu pureza y eternamente lo sea, pues todo un Dios se recrea en tan graciosa belleza. A ti, celestial princesa, Virgen sagrada María, te ofrezco en este día alma, vida y corazón. Mírame con compasión, no me dejes, Madre mía. Amén.", "Blessed be your purity, and blessed be it forever, since the whole God rejoices in such gracious beauty. To you, celestial princess, holy Virgin Mary, I offer on this day my soul, my life, and my heart. Look upon me with compassion, do not leave me, my Mother. Amen.", "Bendita seja a vossa pureza e eternamente o seja, pois todo um Deus se deleita com tal graciosa formosura. A vós, princesa celestial, Virgem sagrada Maria, eu vos ofereço neste dia alma, vida e coração. Olhai-me com compaixão, não me abandoneis, minha Mãe. Amém."),
            whenToPray = LocalizedText("Como oración mariana de entrega diaria, especialmente por la mañana y al terminar el Rosario.", "As a daily Marian act of offering, especially in the morning and at the close of the Rosary.", "Como oração mariana de entrega diária, especialmente pela manhã e ao final do Terço."),
            thematicTags = listOf("marian", "daily_routine")
        ),
        VaticanPrayer(
            id = "nicene_creed",
            title = LocalizedText("Credo Niceno-Constantinopolitano", "Nicene-Constantinopolitan Creed", "Credo Niceno-Constantinopolitano"),
            latinTitle = "Symbolum Nicaeno-Constantinopolitanum",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText("Creo en un solo Dios, Padre Todopoderoso, Creador del cielo y de la tierra, de todo lo visible e invisible. Creo en un solo Señor, Jesucristo, Hijo único de Dios, nacido del Padre antes de todos los siglos: Dios de Dios, Luz de Luz, Dios verdadero de Dios verdadero, engendrado, no creado, de la misma naturaleza del Padre, por quien todo fue hecho; que por nosotros, los hombres, y por nuestra salvación bajó del cielo, y por obra del Espíritu Santo se encarnó de María, la Virgen, y se hizo hombre; y por nuestra causa fue crucificado en tiempos de Poncio Pilato; padeció y fue sepultado, y resucitó al tercer día, según las Escrituras, y subió al cielo, y está sentado a la derecha del Padre; y de nuevo vendrá con gloria para juzgar a vivos y muertos, y su reino no tendrá fin.", "We believe in one God, the Father, the Almighty, maker of heaven and earth, of all that is, seen and unseen. We believe in one Lord, Jesus Christ, the only-begotten Son of God, born of the Father before all ages, God from God, Light from Light, true God from true God, begotten, not made, of the same essence as the Father; through Him all things were made. For us men and for our salvation He came down from heaven, and by the power of the Holy Spirit He became incarnate from the Virgin Mary, and was made man; for our sake He was crucified under Pontius Pilate, He suffered death and was buried, and rose again on the third day in accordance with the Scriptures; He ascended into heaven and is seated at the right hand of the Father. He will come again in glory to judge the living and the dead, and His kingdom will have no end.", "Cremos em um só Deus, Pai todo-poderoso, criador do céu e da terra, de todas as coisas visíveis e invisíveis. Cremos em um só Senhor, Jesus Cristo, Filho unigênito de Deus, nacido do Pai antes de todos os séculos: Deus de Deus, Luz da Luz, Deus verdadeiro de Deus verdadeiro, gerado, não criado, da mesma natureza do Pai, por quem tudo foi feito; por nós, homens, e por nossa salvação, desceu dos céus. E por obra do Espírito Santo se encarnou no seio da Virgem Maria e se fez homem. Foi crucificado por nós, sob Pôncio Pilatos; padecendo e morrendo, foi sepultado. Ressuscitou ao terceiro dia, segundo as Escrituras, e subiu ao céu; está sentado à direita do Pai. E de novo há de vir, em sua glória, para julgar os vivos e os mortos; e o seu reino não terá fim."),
            responseText = LocalizedText("Creo en el Espíritu Santo, Señor y dador de vida, que procede del Padre y del Hijo, que con el Padre y el Hijo recibe una misma adoración y gloria, y que habló por los profetas. Creo en la Iglesia, que es una, santa, católica y apostólica. Confieso un solo Bautismo para el perdón de los pecados. Espero la resurrección de los muertos y la vida del mundo futuro. Amén.", "We believe in the Holy Spirit, the Lord, the giver of life, who proceeds from the Father and the Son. With the Father and the Son He is worshipped and glorified. He has spoken through the Prophets. We believe in one, holy, catholic and apostolic Church. We acknowledge one baptism for the forgiveness of sins. We look for the resurrection of the dead, and the life of the world to come. Amen.", "Cremos no Espírito Santo, Senhor que dá a vida, e procede do Pai e do Filho. Com o Pai e o Filho é adorado e glorificado, e falou pelos profetas. Cremos na Igreja, uma, santa, católica e apostólica. Confessamos um só batismo para remissão dos pecados. Esperamos a ressurreição dos mortos e a vida do mundo futuro. Amém."),
            whenToPray = LocalizedText("En la Misa de los domingos y fiestas de solemnidad, después del Evangelio, como profesión de la fe de Nicea y de Constantinopla.", "At Mass on Sundays and solemnities, after the Gospel, as the profession of faith of Nicaea and Constantinople.", "Na Missa dos domingos e solenidades, após o Evangelho, como profissão de fé de Niceia e Constantinopla."),
            thematicTags = listOf("faith", "daily_routine")
        )
    )
}
