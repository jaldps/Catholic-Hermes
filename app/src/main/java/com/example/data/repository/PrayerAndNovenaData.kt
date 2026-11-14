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

    val officialNovenas: List<Novena> by lazy { CrawledNovenasData.allNovenas }

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
            id = "laudes",
            title = LocalizedText("Oración de la mañana · selección abreviada", "Morning prayer · abridged selection", "Oração da manhã · seleção abreviada"),
            latinTitle = null,
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText(
                "V/. Dios mío, ven en mi ayuda. R/. Señor, date prisa en socorrerme. Gloria al Padre, y al Hijo, y al Espíritu Santo. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén. Aleluya.\n\nHimno: Canta el gallo ya su aviso, la luz de la aurora viene; Jesús líbranos del sueño de la culpa y del pecado.\n\nSalmo 95: Venid, adoremos a Cristo, el Señor, porque es nuestro. Cantemos al Señor con alegría, aclamemos a la Roca que nos salva.\n\nLectura (Lc 1, 68-70): Bendito sea el Señor, Dios de Israel, porque ha visitado y redimido a su pueblo, suscitando una fuerza de salvación en la casa de David, su siervo, según lo había prometido desde antiguo por boca de sus santos profetas.",
                "V/. O God, come to my aid. R/. O Lord, make haste to help me. Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen. Alleluia.\n\nHymn: The cock has uttered loud and clear, the dawnlight breaks upon the air; O Lord, awake us from our sin and free us from the night within.\n\nPsalm 95: Come, let us worship Christ the Lord, for he is ours. Sing to the Lord with joy, acclaim the Rock who saves us.\n\nReading (Lk 1:68-70): Blessed be the Lord, the God of Israel, who has visited his people and redeemed them, raising up a power of salvation in the house of David his servant, as he spoke by the mouth of his holy prophets from of old.",
                "V/. Deus, vinde em nosso auxílio. R/. Senhor, apressai-vos em nos socorrer. Glória ao Pai, ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém. Aleluia.\n\nHino: Já o galo canta o seu aviso, e a luz da aurora vem; Senhor, livrai-nos do sono da culpa e do pecado.\n\nSalmo 95: Vinde, adoremos a Cristo, o Senhor, porque é nosso. Cantemos ao Senhor com alegria, aclamemos a Rocha que nos salva.\n\nLeitura (Lc 1, 68-70): Bendito seja o Senhor, Deus de Israel, que visitou e redimiu o seu povo, levantando uma força de salvação na casa de Davi, seu servo, como prometera desde os tempos antigos pela boca de seus santos profetas."
            ),
            responseText = LocalizedText(
                "Cántico (Lc 1, 79): Por la entrañable misericordia de nuestro Dios, nos visitará el sol que nace de lo alto, para iluminar a los que habitan en tinieblas y sombras de muerte, y para guiar nuestros pasos por el camino de la paz. Gloria al Padre, y al Hijo, y al Espíritu Santo. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.\n\nPadrenuestro: Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; y no nos dejes caer en la tentación, y líbranos del mal. Amén.\n\nOremos: Señor, que la gracia de tu Espíritu santo nos llene y nos guíe en este día, para que, caminando en tu luz, te alabemos con todo el corazón. Por Cristo, nuestro Señor. Amén.",
                "Canticle (Lk 1:79): Through the tender mercy of our God, the dawn from on high will visit us, to give light to those who sit in darkness and in the shadow of death, and to guide our feet into the way of peace. Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.\n\nOur Father: Our Father, who art in heaven, hallowed be thy name; thy kingdom come; thy will be done on earth, as it is in heaven. Give us this day our daily bread; and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.\n\nLet us pray: Lord, let the grace of your Holy Spirit fill us and guide us this day, that, walking in your light, we may praise you with all our hearts. Through Christ our Lord. Amen.",
                "Cântico (Lc 1, 79): Pela paternal misericórdia do nosso Deus, o sol que nasce do alto nos visitará, para iluminar os que jazem nas trevas e sombras da morte e guiar os nossos passos pelo caminho da paz. Glória ao Pai, ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.\n\nPai Nosso: Pai nosso, que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia dai-nos hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.\n\nOremos: Senhor, que a graça do Espírito Santo nos encha e nos guie neste dia, para que, caminhando na vossa luz, vos louvemos de todo o coração. Por Cristo, nosso Senhor. Amém."
            ),
            whenToPray = LocalizedText("Para la oración personal al amanecer. Selección devocional inspirada en Laudes; no es el oficio completo ni los textos propios del día de la Liturgia de las Horas.", "For personal prayer at daybreak. A devotional selection inspired by Lauds; this is not the complete office or the appointed texts for today in the Liturgy of the Hours.", "Para a oração pessoal ao amanhecer. Seleção devocional inspirada nas Laudes; não é o ofício completo nem os textos próprios do dia da Liturgia das Horas."),
            thematicTags = listOf("daily_routine", "morning", "office")
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
        ),
        VaticanPrayer(
            id = "advent_antiphons",
            title = LocalizedText("Ven, ven, Emmanuel (Antífonas del Adviento)", "O Come, O Come Emmanuel (Advent Antiphons)", "Vem, vem, Emmanuel (Antífonas do Advento)"),
            latinTitle = "Veni, veni Emmanuel! Captivum solve Israel, Qui gemit in exilio, Privatus Dei Filio.",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Ven, ven, Emmanuel, rescata a Israel cautivo, que gime en exilio solitario hasta que aparezca el Hijo de Dios. ¡Regocíjate! ¡Regocíjate! Emmanuel vendrá a ti, oh Israel. Ven, vara de Jesé, libera a los tuyos de la tiranía de Satanás; salva a tu pueblo de las profundidades del infierno y dale victoria sobre la tumba. ¡Regocíjate! ¡Regocíjate! Emmanuel vendrá a ti, oh Israel. Ven, ven, oh Oriens, ilumina nuestras almas con tu luz sanadora; disipa las tinieblas de la larga noche y atraviesa las sombras de la tumba. ¡Regocíjate! ¡Regocíjate! Emmanuel vendrá a ti, oh Israel. Ven, llave de David, abre de par en par nuestro hogar celestial; haz seguro el camino que lleva a lo alto y cierra el sendero de la miseria. ¡Regocíjate! ¡Regocíjate! Emmanuel vendrá a ti, oh Israel. Ven, Señor de poder, que a tus tribus en la altura del Sinaí diste la ley en nube, majestad y temor. ¡Regocíjate! ¡Regocíjate! Emmanuel vendrá a ti, oh Israel.",
                "O come, O come, Emmanuel, And ransom captive Israel, That mourns in lonely exile here Until the Son of God appear. Rejoice! Rejoice! Emmanuel Shall come to thee, O Israel. O come, thou Rod of Jesse, free Thine own from Satan's tyranny; From depths of hell thy people save, And give them victory o'er the grave. Rejoice! Rejoice! Emmanuel Shall come to thee, O Israel. O come, O come, thou Dayspring bright! Pour on our souls thy healing light; Dispel the long night's lingering gloom, And pierce the shadows of the tomb. Rejoice! Rejoice! Emmanuel Shall come to thee, O Israel. O come, thou Key of David, come, And open wide our heavenly home; Make safe the way that leads on high, And close the path to misery. Rejoice! Rejoice! Emmanuel Shall come to thee, O Israel. O come, O Lord of might, who to Thy tribes on Sinai's height In ancient times didst give the law In cloud and majesty and awe. Rejoice! Rejoice! Emmanuel Shall come to thee, O Israel.",
                "Vem, vem, Emmanuel, resgata Israel cativo, que geme em exílio solitário até que apareça o Filho de Deus. Alegra-te! Alegra-te! Emmanuel virá a ti, ó Israel. Vem, vara de Jessé, liberta os teus da tirania de Satanás; salva o teu povo das profundezas do inferno e dá-lhe vitória sobre o sepulcro. Alegra-te! Alegra-te! Emmanuel virá a ti, ó Israel. Vem, vem, ó Oriente, ilumina as nossas almas com a tua luz curadora; dissipa as trevas da longa noite e trespassa as sombras do sepulcro. Alegra-te! Alegra-te! Emmanuel virá a ti, ó Israel. Vem, chave de Davi, abre de par em par o nosso lar celestial; faz seguro o caminho que leva ao alto e fecha o caminho da miséria. Alegra-te! Alegra-te! Emmanuel virá a ti, ó Israel. Vem, Senhor de poder, que às tuas tribos no cume do Sinai deste a lei em nuvem, majestade e temor. Alegra-te! Alegra-te! Emmanuel virá a ti, ó Israel."
            ),
            whenToPray = LocalizedText("Se canta durante la temporada de Adviento, especialmente en la última semana antes de Navidad.", "Sung during Advent season, especially the last week before Christmas", "É cantado durante o tempo do Advento, especialmente na última semana antes do Natal."),
            thematicTags = listOf("advent", "liturgical")
        ),
        VaticanPrayer(
            id = "canticle_of_simeon",
            title = LocalizedText("Cántico de Simeón", "Canticle of Simeon", "Cântico de Simeão"),
            latinTitle = "Nunc dimittis servum tuum, Domine, secundum verbum tuum in pace, quia viderunt oculi mei",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText(
                "Ahora, Señor, despides a tu siervo en paz, conforme a tu palabra; porque han visto mis ojos tu salvación, la cual has preparado en presencia de todos los pueblos; luz para revelación a los gentiles, y gloria de tu pueblo Israel.",
                "Lord, now lettest thou thy servant depart in peace, according to thy word: For mine eyes have seen thy salvation, Which thou hast prepared before the face of all people; A light to lighten the Gentiles, and the glory of thy people Israel.",
                "Agora, Senhor, despede em paz o teu servo, segundo a tua palavra; pois os meus olhos viram a tua salvação, que preparaste diante de todos os povos; luz para iluminar as nações e glória do teu povo Israel."
            ),
            whenToPray = LocalizedText("Oración de la tarde en la Liturgia de las Horas y en otras celebraciones litúrgicas.", "Evening prayer in the Liturgy of the Hours, and other liturgical celebrations", "Oração vespertina na Liturgia das Horas e em outras celebrações litúrgicas."),
            thematicTags = listOf("daily_routine", "evening")
        ),
        VaticanPrayer(
            id = "canticle_of_zechariah",
            title = LocalizedText("Cántico de Zacarías", "Canticle of Zechariah", "Cântico de Zacarias"),
            latinTitle = "Benedictus Dominus Deus Israel, quia visitavit et fecit redemptionem plebi suae, et",
            category = PrayerCategory.MORNING_NIGHT,
            leaderText = LocalizedText(
                "Bendito el Señor Dios de Israel, porque ha visitado y redimido a su pueblo, y nos ha levantado una fuerza de salvación en la casa de David su siervo, según que habló por boca de sus santos profetas que fueron desde el principio, para salvarnos de nuestros enemigos y de la mano de todos los que nos aborrecen; para hacer misericordia con nuestros padres, y acordarse de su santo pacto, del juramento que hizo a Abraham nuestro padre, que nos había de conceder que, librados de la mano de nuestros enemigos, le sirviésemos sin temor, en santidad y en justicia delante de él, todos los días de nuestra vida. Y tú, niño, profeta del Altísimo serás llamado; porque irás delante de la faz del Señor, para preparar sus caminos; para dar conocimiento de salud a su pueblo, para remisión de sus pecados, por las entrañas de misericordia de nuestro Dios, con que nos visitó desde lo alto el oriente, para dar luz a los que habitan en tinieblas y en sombra de muerte; para encaminar nuestros pies por camino de paz.",
                "Blessed be the Lord God of Israel, for he has visited and redeemed his people and has raised up a horn of salvation for us in the house of his servant David, as he spoke by the mouth of his holy prophets from of old, that we should be saved from our enemies and from the hand of all who hate us; to show the mercy promised to our fathers and to remember his holy covenant, the oath that he swore to our father Abraham, to grant us that we, being delivered from the hand of our enemies, might serve him without fear, in holiness and righteousness before him all our days. And you, child, will be called the prophet of the Most High; for you will go before the Lord to prepare his ways, to give knowledge of salvation to his people in the forgiveness of their sins, because of the tender mercy of our God, whereby the sunrise shall visit us from on high to give light to those who sit in darkness and in the shadow of death, to guide our feet into the way of peace.",
                "Bendito seja o Senhor Deus de Israel, porque visitou e redimiu o seu povo; e nos suscitou uma salvação poderosa na casa de Davi, seu servo, conforme o que dissera pela boca dos seus santos profetas desde a antiguidade: para nos salvar dos nossos inimigos e das mãos de todos os que nos odeiam; para usar de misericórdia com nossos pais e lembrar-se do seu santo concerto; do juramento que jurou a Abraão nosso pai, de nos conceder que, libertados da mão dos nossos inimigos, o servíssemos sem temor, em santidade e justiça perante ele todos os dias da nossa vida. E tu, menino, serás chamado profeta do Altíssimo; porque irás diante da face do Senhor, a preparar os seus caminhos; para dar ao seu povo conhecimento da salvação, na remissão dos seus pecados; pelas entranhas da misericórdia do nosso Deus, com que nos visitou o Oriente do alto; para alumiar aos que estão assentados em trevas e sombra da morte; para guiar os nossos pés no caminho da paz."
            ),
            whenToPray = LocalizedText("Oración de la mañana en la Liturgia de las Horas y en otras celebraciones litúrgicas.", "Morning prayer in the Liturgy of the Hours, and other liturgical celebrations", "Oração da manhã na Liturgia das Horas e em outras celebrações litúrgicas."),
            thematicTags = listOf("daily_routine", "morning")
        ),
        VaticanPrayer(
            id = "chaplet_of_st_michael",
            title = LocalizedText("Corona de San Miguel Arcángel", "Chaplet of St. Michael", "Coroa de São Miguel Arcanjo"),
            latinTitle = "O Deus, veni in auxilium meum;",
            category = PrayerCategory.PROTECTION,
            leaderText = LocalizedText(
                "Oh Dios, ven en mi auxilio;\nSeñor, date prisa en socorrerme.\nGloria al Padre, al Hijo y al Espíritu Santo.\nComo era en el principio, ahora y siempre,\npor los siglos de los siglos. Amén.\n\nSan Miguel Arcángel, defiéndenos en la batalla;\nsé nuestro amparo contra la perversidad y asechanzas del diablo.\nQue Dios lo reprima, te lo rogamos suplicantes;\ny tú, Príncipe de la milicia celestial,\npor el poder de Dios, arroja al infierno a Satanás\ny a los demás espíritus malignos\nque andan dispersos por el mundo\npara la perdición de las almas. Amén.\n\nPor los nueve coros de Ángeles:\n\n1. Por la intercesión de San Miguel y el coro celestial de Ángeles, defiéndanos el Señor. Amén.\n\n2. Por la intercesión de San Miguel y el coro celestial de Arcángeles, defiéndanos el Señor. Amén.\n\n3. Por la intercesión de San Miguel y el coro celestial de Principados, defiéndanos el Señor. Amén.\n\n4. Por la intercesión de San Miguel y el coro celestial de Potestades, defiéndanos el Señor. Amén.\n\n5. Por la intercesión de San Miguel y el coro celestial de Virtudes, defiéndanos el Señor. Amén.\n\n6. Por la intercesión de San Miguel y el coro celestial de Dominaciones, defiéndanos el Señor. Amén.\n\n7. Por la intercesión de San Miguel y el coro celestial de Tronos, defiéndanos el Señor. Amén.\n\n8. Por la intercesión de San Miguel y el coro celestial de Querubines, defiéndanos el Señor. Amén.\n\n9. Por la intercesión de San Miguel y el coro celestial de Serafines, defiéndanos el Señor. Amén.\n\nOh gloriosísimo Príncipe de la milicia celestial,\nSan Miguel Arcángel,\ndefiéndenos en la batalla y en la lucha terrible\nque sostenemos contra los principados y potestades,\ncontra los gobernadores de este mundo de tinieblas,\ncontra los espíritus malignos en las regiones celestes.\nVen en auxilio de los hombres,\na quienes Dios creó inmortales,\ny a su imagen y semejanza los hizo,\ny del yugo del diablo los redimió a gran precio.\n\nPelea hoy la batalla del Señor,\ncon los bienaventurados Ángeles que te asisten,\ncomo peleaste contra Lucifer, príncipe de la soberbia,\ny sus ángeles apóstatas:\nno pudieron resistirte,\nni se encontró ya lugar para ellos en el cielo.\nPero fue precipitado aquel gran dragón, la serpiente antigua,\nque se llama diablo y Satanás,\nque seduce a todo el mundo;\ny fue precipitado a la tierra,\ny sus ángeles fueron arrojados con él.\n\n¡He aquí la Cruz del Señor! ¡Huid, poderes enemigos!\n¡Ha vencido el León de la tribu de Judá, el retoño de David!\n¡Aleluya!\n\nSan Miguel Arcángel, ruega por nosotros.\nAmén.",
                "O God, come to my assistance;\nO Lord, make haste to help me.\nGlory be to the Father, and to the Son, and to the Holy Spirit.\nAs it was in the beginning, is now, and ever shall be,\nworld without end. Amen.\n\nSaint Michael the Archangel, defend us in battle;\nbe our protection against the wickedness and snares of the devil.\nMay God rebuke him, we humbly pray:\nand do thou, O Prince of the heavenly host,\nby the power of God, thrust into hell Satan and all the evil spirits\nwho prowl about the world seeking the ruin of souls. Amen.\n\nFor the nine choirs of Angels:\n\n1. By the intercession of St. Michael and the celestial choir of Angels, may the Lord defend us. Amen.\n\n2. By the intercession of St. Michael and the celestial choir of Archangels, may the Lord defend us. Amen.\n\n3. By the intercession of St. Michael and the celestial choir of Principalities, may the Lord defend us. Amen.\n\n4. By the intercession of St. Michael and the celestial choir of Powers, may the Lord defend us. Amen.\n\n5. By the intercession of St. Michael and the celestial choir of Virtues, may the Lord defend us. Amen.\n\n6. By the intercession of St. Michael and the celestial choir of Dominations, may the Lord defend us. Amen.\n\n7. By the intercession of St. Michael and the celestial choir of Thrones, may the Lord defend us. Amen.\n\n8. By the intercession of St. Michael and the celestial choir of Cherubim, may the Lord defend us. Amen.\n\n9. By the intercession of St. Michael and the celestial choir of Seraphim, may the Lord defend us. Amen.\n\nO glorious Prince of the heavenly host, St. Michael the Archangel,\ndefend us in the battle and in the terrible warfare that we are waging\nagainst the principalities and powers,\nagainst the rulers of this world of darkness,\nagainst the evil spirits in the high places.\nCome to the rescue of men,\nwhom God has made in His own image and likeness,\nand whom He has redeemed at a great price from the tyranny of the devil.\n\nFight this day the battle of the Lord,\nwith the blessed Angels assisting thee,\nas thou didst fight against Lucifer, the prince of pride,\nand his apostate angels;\nwho were powerless to withstand thee,\nneither was their place found any more in heaven.\nBut that great dragon was cast out, the ancient serpent,\nwho is called the devil and Satan,\nwho seduces the whole world;\nand he was cast down to the earth,\nand his angels were cast down with him.\n\nBehold the Cross of the Lord! Begone, ye hostile powers!\nThe Lion of the tribe of Juda, the root of David, hath conquered!\nAlleluia!\n\nSaint Michael the Archangel, pray for us.\nAmen.",
                "Ó Deus, vem em meu auxílio;\nSenhor, apressa-Te a ajudar-me.\nGlória ao Pai, ao Filho e ao Espírito Santo.\nComo era no princípio, agora e sempre,\ne por todos os séculos dos séculos. Amém.\n\nSão Miguel Arcanjo, defendei-nos no combate;\nsede nossa proteção contra a perversidade e ciladas do demônio.\nMande-lhe Deus, nós vo-lo pedimos humildemente;\ne vós, Príncipe da milícia celeste,\npelo poder de Deus, precipitai no inferno a Satanás\ne aos outros espíritos malignos\nque andam pelo mundo\na procurar a perdição das almas. Amém.\n\nPelos nove coros dos Anjos:\n\n1. Pela intercessão de São Miguel e o coro celeste dos Anjos, defenda-nos o Senhor. Amém.\n\n2. Pela intercessão de São Miguel e o coro celeste dos Arcanjos, defenda-nos o Senhor. Amém.\n\n3. Pela intercessão de São Miguel e o coro celeste dos Principados, defenda-nos o Senhor. Amém.\n\n4. Pela intercessão de São Miguel e o coro celeste das Potestades, defenda-nos o Senhor. Amém.\n\n5. Pela intercessão de São Miguel e o coro celeste das Virtudes, defenda-nos o Senhor. Amém.\n\n6. Pela intercessão de São Miguel e o coro celeste das Dominações, defenda-nos o Senhor. Amém.\n\n7. Pela intercessão de São Miguel e o coro celeste dos Tronos, defenda-nos o Senhor. Amém.\n\n8. Pela intercessão de São Miguel e o coro celeste dos Querubins, defenda-nos o Senhor. Amém.\n\n9. Pela intercessão de São Miguel e o coro celeste dos Serafins, defenda-nos o Senhor. Amém.\n\nÓ gloriosíssimo Príncipe da milícia celeste,\nSão Miguel Arcanjo,\ndefendei-nos na batalha e na terrível peleja\nque temos contra os principados e potestades,\ncontra os príncipes deste mundo de trevas,\ncontra os espíritos malignos nas regiões celestes.\nVinde em socorro dos homens,\na quem Deus criou imortais,\ne fez à sua imagem e semelhança,\ne resgatou a grande preço da tirania do demônio.\n\nPelejai hoje a batalha do Senhor,\ncom os bem-aventurados Anjos que vos assistem,\ncomo pelejastes contra Lúcifer, príncipe da soberba,\ne os seus anjos apóstatas:\nnão puderam resistir-vos,\nnem se achou mais lugar para eles no céu.\nMas foi precipitado aquele grande dragão, a serpente antiga,\nque se chama diabo e Satanás,\nque seduz todo o mundo;\ne foi precipitado na terra,\ne os seus anjos com ele foram lançados.\n\nEis a Cruz do Senhor! Fugi, partes adversas!\nVenceu o Leão da tribo de Judá, a raiz de David!\nAleluia!\n\nSão Miguel Arcanjo, rogai por nós.\nAmém."
            ),
            whenToPray = LocalizedText("Se reza con Rosario, especialmente para protección contra el mal.", "Recited using rosary beads, especially for protection against evil", "É rezada com terço, especialmente para proteção contra o mal."),
            thematicTags = listOf("protection", "spiritual_warfare", "angels")
        ),
        VaticanPrayer(
            id = "christmas_prayer",
            title = LocalizedText("Oración de Navidad", "Christmas Prayer", "Oração de Natal"),
            latinTitle = "Deus, qui hanc sacratissimam noctem veri luminis fecisti illustratione clarescere: da,",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Oh Dios, que has hecho brillar esta noche sacratísima con la iluminación de la verdadera luz: concede, te rogamos, que nosotros, que conocemos en la tierra los misterios de su luz, gocemos también perfectamente de Él en el cielo; que vive y reina contigo en la unidad del Espíritu Santo, Dios, por los siglos de los siglos. Amén.",
                "O God, who hast made this most sacred night to shine with the illumination of the true Light: grant, we beseech Thee, that we who have known the mysteries of His light on earth, may also perfectly enjoy Him in heaven; who liveth and reigneth with Thee in the unity of the Holy Ghost, God, world without end. Amen.",
                "Ó Deus, que fizeste brilhar esta noite sacratíssima com a iluminação da verdadeira luz: concede-nos, nós te rogamos, que nós, que conhecemos na terra os mistérios da sua luz, gozemos também perfeitamente dele no céu; que vive e reina contigo na unidade do Espírito Santo, Deus, pelos séculos dos séculos. Amém."
            ),
            whenToPray = LocalizedText("Se reza durante la temporada de Navidad y en el día de Navidad.", "Prayed during Christmas season and on Christmas Day", "É rezada durante o tempo de Natal e no dia de Natal."),
            thematicTags = listOf("christmas", "liturgical")
        ),
        VaticanPrayer(
            id = "confiteor",
            title = LocalizedText("Yo pecador (Confiteor)", "Confiteor", "Confiteor"),
            latinTitle = "Confiteor Deo omnipotenti et vobis, fratres, quia peccavi nimis cogitatione, verbo, opere",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Me confieso a Dios todopoderoso y a vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión, por mi culpa, por mi culpa, por mi grandísima culpa. Por eso ruego a la bienaventurada siempre Virgen María, a los ángeles y santos, y a vosotros, hermanos, que intercedáis por mí ante Dios nuestro Señor.",
                "I confess to almighty God and to you, my brothers and sisters, that I have greatly sinned, in my thoughts and in my words, in what I have done and in what I have failed to do, through my fault, through my fault, through my most grievous fault; therefore I ask blessed Mary ever-Virgin, all the Angels and Saints, and you, my brothers and sisters, to pray for me to the Lord our God.",
                "Confesso a Deus todo-poderoso e a vós, irmãos, que pequei muito em pensamentos, palavras, obras e omissões, por minha culpa, por minha culpa, por minha máxima culpa. Por isso, peço à bem-aventurada sempre Virgem Maria, a todos os anjos e santos, e a vós, irmãos, que rogueis por mim a Deus nosso Senhor."
            ),
            whenToPray = LocalizedText("Se reza durante la Misa antes de la comunión y en otros contextos penitenciales.", "Recited during Mass before communion, and in other penitential contexts", "É rezada durante a Missa antes da comunhão e em outros contextos penitenciais."),
            thematicTags = listOf("forgiveness", "confession")
        ),
        VaticanPrayer(
            id = "divine_mercy_opening_prayer",
            title = LocalizedText("Oración de Apertura de la Divina Misericordia", "Divine Mercy Opening Prayer", "Oração de Abertura da Divina Misericórdia"),
            latinTitle = "Exspirasti, Iesu, sed vitae fons erupit animabus, et misericordiae oceanus aperuit toti",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Expiraste, Jesús, pero la fuente de vida brotó para las almas, y el océano de misericordia se abrió para todo el mundo. Oh fuente de vida, insondable Divina Misericordia, envuelve todo el mundo y derrámate sobre nosotros.",
                "You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.",
                "Expiraste, Jesus, mas a fonte da vida jorrou para as almas, e o oceano da misericórdia abriu-se para todo o mundo. Ó fonte da vida, insondável Divina Misericórdia, envolve todo o mundo e derrama-Te sobre nós."
            ),
            responseText = LocalizedText(
                "Oh Sangre y Agua, que brotasteis del Corazón de Jesús como fuente de misericordia para nosotros, en Ti confío.",
                "O Blood and Water, which gushed forth from the Heart of Jesus as a fountain of mercy for us, I trust in You.",
                "Ó Sangue e Água, que jorrastes do Coração de Jesus como fonte de misericórdia para nós, em Ti confio."
            ),
            whenToPray = LocalizedText("Oración opcional al comenzar la Corona de la Divina Misericordia.", "Optional prayer at the beginning of the Divine Mercy Chaplet", "Oração opcional no início da Coroa da Divina Misericórdia."),
            thematicTags = listOf("mercy", "trust")
        ),
        VaticanPrayer(
            id = "divine_mercy_closing_prayer",
            title = LocalizedText("Oración de Cierre de la Divina Misericordia", "Divine Mercy Closing Prayer", "Oração de Encerramento da Divina Misericórdia"),
            latinTitle = "Deus aeternus, in quo misericordia infinita et thesaurus compassionis inexhaustus,",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Dios eterno, en quien la misericordia es infinita y el tesoro de la compasión inagotable, míranos con bondad y aumenta tu misericordia en nosotros, para que en los momentos difíciles no desesperemos ni nos desanimemos, sino que con gran confianza nos sometamos a tu santa voluntad, que es Amor y Misericórdia misma.",
                "Eternal God, in whom mercy is endless and the treasury of compassion inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.",
                "Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai-nos com bondade e aumentai a vossa misericórdia em nós, para que nos momentos difíceis não desesperemos nem nos desanimemos, mas com grande confiança nos submetamos à vossa santa vontade, que é Amor e Misericórdia mesma."
            ),
            whenToPray = LocalizedText("Oración al final de la Corona de la Divina Misericordia.", "Prayer at the end of the Divine Mercy Chaplet", "Oração no final da Coroa da Divina Misericórdia."),
            thematicTags = listOf("mercy", "trust")
        ),
        VaticanPrayer(
            id = "easter_prayer",
            title = LocalizedText("Oración de Pascua", "Easter Prayer", "Oração Pascal"),
            latinTitle = "Deus, qui hodierna die per Unigenitum tuum, aeternitatis nobis aditum, devicta morte,",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Oh Dios, que en este día, por tu Unigénito, vencida la muerte, nos has abierto la puerta de la eternidad: lleva a cabo nuestros deseos, que previniendo los inspiras, ayudándolos también. Por el mismo Jesucristo, tu Hijo, nuestro Señor, que vive y reina contigo en la unidad del Espíritu Santo, Dios, por los siglos de los siglos. Amén.",
                "O God, who on this day, through Thine only-begotten Son, hast conquered death, and thrown open to us the gate of everlasting life, give effect by thine aid to our desires, which thou dost anticipate and inspire. Through the same Jesus Christ, thy Son, our Lord, who liveth and reigneth with thee in the unity of the Holy Ghost, God, world without end. Amen.",
                "Ó Deus, que neste dia, pelo teu Unigénito, vencida a morte, nos abriste a porta da eternidade: leva a cabo os nossos desejos, que prevenindo os inspiras, ajudando-os também. Pelo mesmo Jesus Cristo, teu Filho, nosso Senhor, que vive e reina contigo na unidade do Espírito Santo, Deus, pelos séculos dos séculos. Amém."
            ),
            whenToPray = LocalizedText("Se reza durante la temporada de Pascua y el Domingo de Resurrección.", "Prayed during Easter season and on Easter Sunday", "É rezada durante o tempo pascal e no Domingo de Páscoa."),
            thematicTags = listOf("easter", "liturgical")
        ),
        VaticanPrayer(
            id = "holy_god",
            title = LocalizedText("Dios Santo", "Holy God", "Deus Santo"),
            latinTitle = "Sancte Deus, Sancte Fortis, Sancte Immortalis, miserere nobis.",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Dios Santo, Dios fuerte, Dios inmortal, ten misericordia de nosotros y del mundo entero.",
                "Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.",
                "Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro."
            ),
            whenToPray = LocalizedText("Se reza tres veces al final de la Corona de la Divina Misericordia.", "Recited three times at the end of the Divine Mercy Chaplet", "É rezada três vezes ao final da Coroa da Divina Misericórdia."),
            thematicTags = listOf("mercy", "faith")
        ),
        VaticanPrayer(
            id = "lenten_prayer",
            title = LocalizedText("Oración de Cuaresma", "Lenten Prayer", "Oração da Quaresma"),
            latinTitle = "Omnipotens et misericors Deus, qui nos per observantiam Quadragesimae ad regnum tuum",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Dios omnipotente y misericordioso, que nos introduces en tu reino por la observancia de la Cuaresma, concédenos que por estos santos misterios de Pascua consigamos el perdón de nuestros pecados y nos adhieramos más firmemente a tu voluntad. Por Cristo nuestro Señor. Amén.",
                "Almighty and ever-living God, you invite us deeper into your kingdom through our Lenten observance. As we prepare to celebrate the Easter mysteries, help us to repent of our sins and renew our commitment to your will. We ask this through Christ our Lord. Amen.",
                "Deus omnipotente e misericordioso, que nos introduces no teu reino pela observância da Quaresma, concede-nos que por estes santos mistérios da Páscoa obtenhamos o perdão dos nossos pecados e nos apeguemos mais firmemente à tua vontade. Por Cristo nosso Senhor. Amém."
            ),
            whenToPray = LocalizedText("Se reza durante la Cuaresma para arrepentimiento y preparación para la Pascua.", "Prayed during Lent for repentance and preparation for Easter", "É rezada durante a Quaresma para arrependimento e preparação para a Páscoa."),
            thematicTags = listOf("lent", "forgiveness")
        ),
        VaticanPrayer(
            id = "litany_of_loreto",
            title = LocalizedText("Letanía de Loreto", "Litany of Loreto", "Ladainha de Loreto"),
            latinTitle = "Kyrie, eleison.",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText(
                "Señor, ten piedad.\nCristo, ten piedad.\nSeñor, ten piedad.\nCristo, óyenos.\nCristo, escúchanos benignamente.\nDios Padre celestial, ten piedad de nosotros.\nDios Hijo, Redentor del mundo, ten piedad de nosotros.\nDios Espíritu Santo, ten piedad de nosotros.\nSanta Trinidad, un solo Dios, ten piedad de nosotros.\nSanta María, ruega por nosotros.\nSanta Madre de Dios, ruega por nosotros.\nSanta Virgen de las vírgenes, ruega por nosotros.\nMadre de Cristo, ruega por nosotros.\nMadre de la divina gracia, ruega por nosotros.\nMadre purísima, ruega por nosotros.\nMadre castísima, ruega por nosotros.\nMadre inmaculada, ruega por nosotros.\nMadre inmaculada, ruega por nosotros.\nMadre amable, ruega por nosotros.\nMadre admirable, ruega por nosotros.\nMadre del buen consejo, ruega por nosotros.\nMadre del Creador, ruega por nosotros.\nMadre del Salvador, ruega por nosotros.\nVirgen prudentísima, ruega por nosotros.\nVirgen veneranda, ruega por nosotros.\nVirgen predicanda, ruega por nosotros.\nVirgen poderosa, ruega por nosotros.\nVirgen clemente, ruega por nosotros.\nVirgen fiel, ruega por nosotros.\nEspejo de justicia, ruega por nosotros.\nTrono de la sabiduría, ruega por nosotros.\nCausa de nuestra alegría, ruega por nosotros.\nVaso espiritual, ruega por nosotros.\nVaso honorable, ruega por nosotros.\nVaso insigne de devoción, ruega por nosotros.\nRosa mística, ruega por nosotros.\nTorre de David, ruega por nosotros.\nTorre de marfil, ruega por nosotros.\nCasa de oro, ruega por nosotros.\nArca de la alianza, ruega por nosotros.\nPuerta del cielo, ruega por nosotros.\nEstrella de la mañana, ruega por nosotros.\nSalud de los enfermos, ruega por nosotros.\nRefugio de los pecadores, ruega por nosotros.\nConsoladora de los afligidos, ruega por nosotros.\nAuxilio de los cristianos, ruega por nosotros.\nReina de los Ángeles, ruega por nosotros.\nReina de los Patriarcas, ruega por nosotros.\nReina de los Profetas, ruega por nosotros.\nReina de los Apóstoles, ruega por nosotros.\nReina de los Mártires, ruega por nosotros.\nReina de los Confesores, ruega por nosotros.\nReina de las Vírgenes, ruega por nosotros.\nReina de todos los Santos, ruega por nosotros.\nReina concebida sin pecado original, ruega por nosotros.\nReina asunta al cielo, ruega por nosotros.\nReina del santísimo Rosario, ruega por nosotros.\nReina de la familia, ruega por nosotros.\nReina de la paz, ruega por nosotros.\nCordero de Dios, que quitas los pecados del mundo, ten piedad de nosotros, Señor.\nCordero de Dios, que quitas los pecados del mundo, óyenos, Señor.\nCordero de Dios, que quitas los pecados del mundo, ten piedad de nosotros.\nRuega por nosotros, Santa Madre de Dios.\nPara que seamos dignos de las promesas de Cristo.\nOremos.\nOh Dios, cuyo Unigénito, por su vida, muerte y resurrección, nos ha merecido los premios de la eterna salvación, concédenos, te rogamos, que meditando estos misterios en el santísimo Rosario de la Bienaventurada Virgen María, imitemos lo que contienen y consigamos lo que prometen. Por el mismo Cristo nuestro Señor. Amén.",
                "Lord, have mercy.\nChrist, have mercy.\nLord, have mercy.\nChrist, hear us.\nChrist, graciously hear us.\nGod the Father of heaven, have mercy on us.\nGod the Son, Redeemer of the world, have mercy on us.\nGod the Holy Spirit, have mercy on us.\nHoly Trinity, one God, have mercy on us.\nHoly Mary, pray for us.\nHoly Mother of God, pray for us.\nHoly Virgin of virgins, pray for us.\nMother of Christ, pray for us.\nMother of divine grace, pray for us.\nMother most pure, pray for us.\nMother most chaste, pray for us.\nMother inviolate, pray for us.\nMother undefiled, pray for us.\nMother most amiable, pray for us.\nMother most admirable, pray for us.\nMother of good counsel, pray for us.\nMother of our Creator, pray for us.\nMother of our Savior, pray for us.\nVirgin most prudent, pray for us.\nVirgin most venerable, pray for us.\nVirgin most renowned, pray for us.\nVirgin most powerful, pray for us.\nVirgin most merciful, pray for us.\nVirgin most faithful, pray for us.\nMirror of justice, pray for us.\nSeat of wisdom, pray for us.\nCause of our joy, pray for us.\nSpiritual vessel, pray for us.\nVessel of honor, pray for us.\nSingular vessel of devotion, pray for us.\nMystical rose, pray for us.\nTower of David, pray for us.\nTower of ivory, pray for us.\nHouse of gold, pray for us.\nArk of the covenant, pray for us.\nGate of heaven, pray for us.\nMorning star, pray for us.\nHealth of the sick, pray for us.\nRefuge of sinners, pray for us.\nComforter of the afflicted, pray for us.\nHelp of Christians, pray for us.\nQueen of Angels, pray for us.\nQueen of Patriarchs, pray for us.\nQueen of Prophets, pray for us.\nQueen of Apostles, pray for us.\nQueen of Martyrs, pray for us.\nQueen of Confessors, pray for us.\nQueen of Virgins, pray for us.\nQueen of all Saints, pray for us.\nQueen conceived without original sin, pray for us.\nQueen assumed into heaven, pray for us.\nQueen of the most holy Rosary, pray for us.\nQueen of the family, pray for us.\nQueen of peace, pray for us.\nLamb of God, who takes away the sins of the world, spare us, O Lord.\nLamb of God, who takes away the sins of the world, graciously hear us, O Lord.\nLamb of God, who takes away the sins of the world, have mercy on us.\nPray for us, O holy Mother of God.\nThat we may be made worthy of the promises of Christ.\nLet us pray.\nO God, whose only begotten Son, by His life, death, and resurrection, has purchased for us the rewards of eternal salvation, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain and obtain what they promise, through the same Christ our Lord. Amen.",
                "Senhor, tende piedade.\nCristo, tende piedade.\nSenhor, tende piedade.\nCristo, ouvi-nos.\nCristo, atendei-nos.\nDeus Pai do céu, tende piedade de nós.\nDeus Filho, Redentor do mundo, tende piedade de nós.\nDeus Espírito Santo, tende piedade de nós.\nSantíssima Trindade, um só Deus, tende piedade de nós.\nSanta Maria, rogai por nós.\nSanta Mãe de Deus, rogai por nós.\nSanta Virgem das virgens, rogai por nós.\nMãe de Cristo, rogai por nós.\nMãe da divina graça, rogai por nós.\nMãe puríssima, rogai por nós.\nMãe castíssima, rogai por nós.\nMãe inviolada, rogai por nós.\nMãe imaculada, rogai por nós.\nMãe amável, rogai por nós.\nMãe admirável, rogai por nós.\nMãe do bom conselho, rogai por nós.\nMãe do Criador, rogai por nós.\nMãe do Salvador, rogai por nós.\nVirgem prudentíssima, rogai por nós.\nVirgem veneranda, rogai por nós.\nVirgem predicanda, rogai por nós.\nVirgem potente, rogai por nós.\nVirgem clemente, rogai por nós.\nVirgem fiel, rogai por nós.\nEspelho de justiça, rogai por nós.\nTrono da sabedoria, rogai por nós.\nCausa da nossa alegria, rogai por nós.\nVaso espiritual, rogai por nós.\nVaso honorável, rogai por nós.\nVaso insigne de devoção, rogai por nós.\nRosa mística, rogai por nós.\nTorre de Davi, rogai por nós.\nTorre de marfim, rogai por nós.\nCasa de ouro, rogai por nós.\nArca da aliança, rogai por nós.\nPorta do céu, rogai por nós.\nEstrela da manhã, rogai por nós.\nSaúde dos enfermos, rogai por nós.\nRefúgio dos pecadores, rogai por nós.\nConsoladora dos aflitos, rogai por nós.\nAuxílio dos cristãos, rogai por nós.\nRainha dos Anjos, rogai por nós.\nRainha dos Patriarcas, rogai por nós.\nRainha dos Profetas, rogai por nós.\nRainha dos Apóstolos, rogai por nós.\nRainha dos Mártires, rogai por nós.\nRainha dos Confessores, rogai por nós.\nRainha das Virgens, rogai por nós.\nRainha de todos os Santos, rogai por nós.\nRainha concebida sem pecado original, rogai por nós.\nRainha assumida ao céu, rogai por nós.\nRainha do santíssimo Rosário, rogai por nós.\nRainha da família, rogai por nós.\nRainha da paz, rogai por nós.\nCordeiro de Deus, que tirais os pecados do mundo, tende piedade de nós, Senhor.\nCordeiro de Deus, que tirais os pecados do mundo, ouvi-nos, Senhor.\nCordeiro de Deus, que tirais os pecados do mundo, tende piedade de nós.\nRogai por nós, Santa Mãe de Deus.\nPara que sejamos feitos dignos das promessas de Cristo.\nOremos.\nÓ Deus, cujo Unigênito, por sua vida, morte e ressurreição, nos adquiriu os prémios da salvação eterna, concedei-nos, Vos suplicamos, que meditando estes mistérios no santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que eles contêm e obtenhamos o que prometem. Pelo mesmo Cristo nosso Senhor. Amen."
            ),
            whenToPray = LocalizedText("Se reza en devociones marianas, especialmente durante mayo y octubre.", "Recited in Marian devotions, especially during May and October Marian months", "É rezada nas devoções marianas, especialmente em maio e outubro."),
            thematicTags = listOf("marian", "litany")
        ),
        VaticanPrayer(
            id = "litany_of_the_sacred_heart",
            title = LocalizedText("Letanía del Sagrado Corazón", "Litany of the Sacred Heart", "Ladainha do Sagrado Coração"),
            latinTitle = "Kyrie, eleison.",
            category = PrayerCategory.EUCHARISTIC,
            leaderText = LocalizedText(
                "Señor, ten piedad.\nCristo, ten piedad.\nSeñor, ten piedad.\nCristo, óyenos.\nCristo, escúchanos benignamente.\nDios Padre celestial, ten piedad de nosotros.\nDios Hijo, Redentor del mundo, ten piedad de nosotros.\nDios Espíritu Santo, ten piedad de nosotros.\nSantísima Trinidad, un solo Dios, ten piedad de nosotros.\nCorazón de Jesús, Hijo del Padre eterno, ten piedad de nosotros.\nCorazón de Jesús, formado por el Espíritu Santo en el seno de la Virgen Madre, ten piedad de nosotros.\nCorazón de Jesús, unido sustancialmente al Verbo de Dios, ten piedad de nosotros.\nCorazón de Jesús, de majestad infinita, ten piedad de nosotros.\nCorazón de Jesús, templo santo de Dios, ten piedad de nosotros.\nCorazón de Jesús, tabernáculo del Altísimo, ten piedad de nosotros.\nCorazón de Jesús, casa de Dios y puerta del cielo, ten piedad de nosotros.\nCorazón de Jesús, horno ardiente de caridad, ten piedad de nosotros.\nCorazón de Jesús, receptáculo de justicia y amor, ten piedad de nosotros.\nCorazón de Jesús, lleno de bondad y amor, ten piedad de nosotros.\nCorazón de Jesús, abismo de todas las virtudes, ten piedad de nosotros.\nCorazón de Jesús, digno de toda alabanza, ten piedad de nosotros.\nCorazón de Jesús, rey y centro de todos los corazones, ten piedad de nosotros.\nCorazón de Jesús, en el que están todos los tesoros de la sabiduría y del conocimiento, ten piedad de nosotros.\nCorazón de Jesús, en el que habita toda la plenitud de la divinidad, ten piedad de nosotros.\nCorazón de Jesús, cuya plenitud es nuestra alegría, ten piedad de nosotros.\nCorazón de Jesús, deseo de las colinas eternas, ten piedad de nosotros.\nCorazón de Jesús, paciente y de mucha misericordia, ten piedad de nosotros.\nCorazón de Jesús, rico para todos los que te invocan, ten piedad de nosotros.\nCorazón de Jesús, fuente de vida y santidad, ten piedad de nosotros.\nCorazón de Jesús, propiciación por nuestros pecados, ten piedad de nosotros.\nCorazón de Jesús, saturado de oprobios, ten piedad de nosotros.\nCorazón de Jesús, triturado por nuestras iniquidades, ten piedad de nosotros.\nCorazón de Jesús, hecho obediente hasta la muerte, ten piedad de nosotros.\nCorazón de Jesús, traspasado por una lanza, ten piedad de nosotros.\nCorazón de Jesús, fuente de toda consolación, ten piedad de nosotros.\nCorazón de Jesús, vida y resurrección nuestra, ten piedad de nosotros.\nCorazón de Jesús, paz y reconciliación nuestra, ten piedad de nosotros.\nCorazón de Jesús, víctima por nuestros pecados, ten piedad de nosotros.\nCorazón de Jesús, salvación de los que en ti esperan, ten piedad de nosotros.\nCorazón de Jesús, esperanza de los que mueren en ti, ten piedad de nosotros.\nCorazón de Jesús, delicia de todos los santos, ten piedad de nosotros.\nCordero de Dios, que quitas los pecados del mundo, ten piedad de nosotros, Señor.\nCordero de Dios, que quitas los pecados del mundo, óyenos, Señor.\nCordero de Dios, que quitas los pecados del mundo, ten piedad de nosotros.\nV. Jesús, manso y humilde de corazón.\nR. Haz nuestros corazones semejantes al tuyo.\nOremos.\nDios todopoderoso y eterno, mira el Corazón de tu Hijo amadísimo y las alabanzas y satisfacciones que te ofrece en nombre de los pecadores, y concede benignamente el perdón a los que piden tu misericordia en el nombre del mismo Hijo tuyo Jesucristo, que vive y reina contigo por los siglos de los siglos. Amén.",
                "Lord, have mercy.\nChrist, have mercy.\nLord, have mercy.\nChrist, hear us.\nChrist, graciously hear us.\nGod the Father of heaven, have mercy on us.\nGod the Son, Redeemer of the world, have mercy on us.\nGod the Holy Spirit, have mercy on us.\nHoly Trinity, one God, have mercy on us.\nHeart of Jesus, Son of the Eternal Father, have mercy on us.\nHeart of Jesus, formed by the Holy Spirit in the womb of the Virgin Mother, have mercy on us.\nHeart of Jesus, substantially united to the Word of God, have mercy on us.\nHeart of Jesus, of infinite majesty, have mercy on us.\nHeart of Jesus, holy temple of God, have mercy on us.\nHeart of Jesus, tabernacle of the Most High, have mercy on us.\nHeart of Jesus, house of God and gate of heaven, have mercy on us.\nHeart of Jesus, burning furnace of charity, have mercy on us.\nHeart of Jesus, vessel of justice and love, have mercy on us.\nHeart of Jesus, full of goodness and love, have mercy on us.\nHeart of Jesus, abyss of all virtues, have mercy on us.\nHeart of Jesus, most worthy of all praise, have mercy on us.\nHeart of Jesus, king and center of all hearts, have mercy on us.\nHeart of Jesus, in whom are all the treasures of wisdom and knowledge, have mercy on us.\nHeart of Jesus, in whom dwells the fullness of divinity, have mercy on us.\nHeart of Jesus, in whom the Father is well pleased, have mercy on us.\nHeart of Jesus, of whose fullness we have all received, have mercy on us.\nHeart of Jesus, desire of the eternal hills, have mercy on us.\nHeart of Jesus, patient and most merciful, have mercy on us.\nHeart of Jesus, enriching all who invoke Thee, have mercy on us.\nHeart of Jesus, fountain of life and holiness, have mercy on us.\nHeart of Jesus, propitiation for our sins, have mercy on us.\nHeart of Jesus, loaded with opprobrium, have mercy on us.\nHeart of Jesus, bruised for our offenses, have mercy on us.\nHeart of Jesus, obedient unto death, have mercy on us.\nHeart of Jesus, pierced with a lance, have mercy on us.\nHeart of Jesus, source of all consolation, have mercy on us.\nHeart of Jesus, our life and resurrection, have mercy on us.\nHeart of Jesus, our peace and reconciliation, have mercy on us.\nHeart of Jesus, victim for our sins, have mercy on us.\nHeart of Jesus, salvation of those who hope in Thee, have mercy on us.\nHeart of Jesus, hope of those who die in Thee, have mercy on us.\nHeart of Jesus, delight of all the saints, have mercy on us.\nLamb of God, who takes away the sins of the world, spare us, O Lord.\nLamb of God, who takes away the sins of the world, graciously hear us, O Lord.\nLamb of God, who takes away the sins of the world, have mercy on us.\nV. Jesus, meek and humble of heart.\nR. Make our hearts like unto Thine.\nLet us pray.\nAlmighty and eternal God, look upon the Heart of Thy most beloved Son and upon the praises and satisfaction which He offers Thee in the name of sinners; and do Thou, in Thy great goodness, grant pardon to them who seek Thy mercy, in the name of the same Thy Son, Jesus Christ, who lives and reigns with Thee, world without end. Amen.",
                "Senhor, tende piedade.\nCristo, tende piedade.\nSenhor, tende piedade.\nCristo, ouvi-nos.\nCristo, atendei-nos.\nDeus Pai do céu, tende piedade de nós.\nDeus Filho, Redentor do mundo, tende piedade de nós.\nDeus Espírito Santo, tende piedade de nós.\nSantíssima Trindade, um só Deus, tende piedade de nós.\nCoração de Jesus, Filho do Pai eterno, tende piedade de nós.\nCoração de Jesus, formado pelo Espírito Santo no seio da Virgem Mãe, tende piedade de nós.\nCoração de Jesus, unido substancialmente ao Verbo de Deus, tende piedade de nós.\nCoração de Jesus, de majestade infinita, tende piedade de nós.\nCoração de Jesus, templo santo de Deus, tende piedade de nós.\nCoração de Jesus, tabernáculo do Altíssimo, tende piedade de nós.\nCoração de Jesus, casa de Deus e porta do céu, tende piedade de nós.\nCoração de Jesus, forno ardente de caridade, tende piedade de nós.\nCoração de Jesus, vaso de justiça e amor, tende piedade de nós.\nCoração de Jesus, cheio de bondade e amor, tende piedade de nós.\nCoração de Jesus, abismo de todas as virtudes, tende piedade de nós.\nCoração de Jesus, digno de todo o louvor, tende piedade de nós.\nCoração de Jesus, rei e centro de todos os corações, tende piedade de nós.\nCoração de Jesus, no qual estão todos os tesouros da sabedoria e da ciência, tende piedade de nós.\nCoração de Jesus, no qual habita toda a plenitude da divindade, tende piedade de nós.\nCoração de Jesus, cuja plenitude é a nossa alegria, tende piedade de nós.\nCoração de Jesus, desejo das colinas eternas, tende piedade de nós.\nCoração de Jesus, paciente e de muita misericórdia, tende piedade de nós.\nCoração de Jesus, rico para todos os que vos invocam, tende piedade de nós.\nCoração de Jesus, fonte de vida e santidade, tende piedade de nós.\nCoração de Jesus, propiciação pelos nossos pecados, tende piedade de nós.\nCoração de Jesus, saturado de opróbrios, tende piedade de nós.\nCoração de Jesus, moído pelas nossas iniquidades, tende piedade de nós.\nCoração de Jesus, feito obediente até à morte, tende piedade de nós.\nCoração de Jesus, traspassado por uma lança, tende piedade de nós.\nCoração de Jesus, fonte de toda a consolação, tende piedade de nós.\nCoração de Jesus, vida e ressurreição nossa, tende piedade de nós.\nCoração de Jesus, paz e reconciliação nossa, tende piedade de nós.\nCoração de Jesus, vítima pelos nossos pecados, tende piedade de nós.\nCoração de Jesus, salvação dos que em vós esperam, tende piedade de nós.\nCoração de Jesus, esperança dos que morrem em vós, tende piedade de nós.\nCoração de Jesus, delícia de todos os santos, tende piedade de nós.\nCordeiro de Deus, que tirais os pecados do mundo, tende piedade de nós, Senhor.\nCordeiro de Deus, que tirais os pecados do mundo, ouvi-nos, Senhor.\nCordeiro de Deus, que tirais os pecados do mundo, tende piedade de nós.\nV. Jesus, manso e humilde de coração.\nR. Fazei o nosso coração semelhante ao vosso.\nOremos.\nDeus onipotente e eterno, olhai para o Coração do vosso diletíssimo Filho e para os louvores e satisfações que Ele vos oferece em nome dos pecadores, e concedei benignamente o perdão aos que imploram a vossa misericórdia no nome do mesmo vosso Filho Jesus Cristo, que vive e reina convosco pelos séculos dos séculos. Amen."
            ),
            whenToPray = LocalizedText("Se reza en las devociones al Sagrado Corazón, especialmente en su fiesta.", "Recited in devotions to the Sacred Heart, especially on the Feast of the Sacred Heart", "É rezada nas devoções ao Sagrado Coração, especialmente na sua festa."),
            thematicTags = listOf("eucharist", "faith")
        ),
        VaticanPrayer(
            id = "magnificat",
            title = LocalizedText("Magnificat", "Magnificat", "Magnificat"),
            latinTitle = "Magnificat anima mea Dominum,",
            category = PrayerCategory.MARIAN,
            leaderText = LocalizedText(
                "Proclama mi alma la grandeza del Señor,\nse regocija mi espíritu en Dios mi Salvador,\nporque ha mirado la humildad de su esclava.\nDesde ahora me felicitarán todas las generaciones,\nporque el Poderoso ha hecho obras grandes por mí:\nsu nombre es santo,\ny su misericordia llega a sus fieles\nde generación en generación.\nHa desplegado la fuerza de su brazo,\nha dispersado a los soberbios de corazón.\nHa derribado a los potentados de sus tronos\ny ha elevado a los humildes.\nA los hambrientos los ha colmado de bienes\ny a los ricos los ha despedido vacíos.\nHa auxiliado a Israel, su siervo,\nacordándose de la misericordia,\ncomo lo había prometido a nuestros padres,\na Abraham y a su descendencia por siempre.\n\nAmén.",
                "My soul doth magnify the Lord,\nand my spirit hath rejoiced in God my Saviour.\nFor he hath regarded the lowliness of his handmaiden.\nFor behold, from henceforth all generations shall call me blessed.\nFor he that is mighty hath magnified me,\nand holy is his Name.\nAnd his mercy is on them that fear him\nfrom generation unto generation.\nHe hath showed strength with his arm;\nhe hath scattered the proud in the imagination of their hearts.\nHe hath put down the mighty from their seat,\nand hath exalted the humble and meek.\nHe hath filled the hungry with good things,\nand the rich he hath sent empty away.\nHe remembering his mercy hath holpen his servant Israel,\nas he promised to our forefathers,\nAbraham and his seed for ever.\n\nAmen.",
                "A minha alma glorifica ao Senhor,\ne o meu espírito se regozija em Deus meu Salvador,\nporque olhou para a humildade da sua serva.\nPois eis que desde agora todas as gerações me chamarão bem-aventurada,\nporque o Poderoso me fez grandes coisas.\nSanto é o seu nome,\ne a sua misericórdia se estende de geração em geração\nsobre aqueles que o temem.\nManifestou o poder do seu braço,\ndispersou os soberbos no pensamento do seu coração;\ndepôs os poderosos dos seus tronos\ne exaltou os humildes;\naos famintos encheu de bens\ne aos ricos despediu vazios.\nAcolheu a Israel, seu servo,\nlembrando-se da sua misericórdia,\ncomo tinha falado a nossos pais,\na Abraão e à sua descendência para sempre.\n\nAmém."
            ),
            whenToPray = LocalizedText("Se canta o reza en Vísperas (Oración de la Tarde) en la Liturgia de las Horas.", "Sung or recited during Vespers (Evening Prayer) in the Liturgy of the Hours", "É cantado ou rezado nas Vésperas (Oração da Tarde) na Liturgia das Horas."),
            thematicTags = listOf("marian", "daily_routine")
        ),
        VaticanPrayer(
            id = "prayer_before_crucifix",
            title = LocalizedText("Oración ante el Crucifijo", "Prayer Before Crucifix", "Oração diante do Crucifixo"),
            latinTitle = "Ecce, o bone et dulcissime Iesu, ante conspectum tuum genibus me provolvo, ac maximo",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "He aquí, oh buen y dulcísimo Jesús, que me postro de rodillas ante tu presencia, y con el más ferviente deseo de mi alma te ruego y te suplico que imprimas en mi corazón vivos sentimientos de fe, esperanza y caridad, con verdadero dolor de mis pecados y firme propósito de enmienda, mientras con profundo afecto y dolor de alma considero dentro de mí mismo y contemplo mentalmente tus cinco preciosísimas llagas, teniendo ante mis ojos lo que ya hace tiempo profetizó David acerca de ti mismo, mi Jesús: 'Han taladrado mis manos y mis pies; han contado todos mis huesos.'",
                "Behold, O good and most sweet Jesus, I cast myself upon my knees in Thy sight, and with the most fervent desire of my soul I pray and beseech Thee that Thou wouldst impress upon my heart lively sentiments of faith, hope and charity, with true repentance for my sins, and a firm purpose of amendment, while with deep affection and grief of soul I consider within myself and mentally contemplate Thy five most precious wounds, having before my eyes that which the prophet David long ago spoke in Thine own person concerning Thee, my Jesus: 'They have pierced My hands and My feet; they have numbered all My bones.'",
                "Eis aqui, ó bom e dulcíssimo Jesus, que me prostro de joelhos diante do teu rosto, e com o mais fervoroso desejo da minha alma te rogo e te suplico que imprimas no meu coração vivos sentimentos de fé, esperança e caridade, com verdadeiro arrependimento dos meus pecados e firme propósito de emenda, enquanto com profundo afeto e dor de alma considero dentro de mim mesmo e mentalmente contemplo as tuas cinco preciosíssimas chagas, tendo diante dos olhos o que já há muito tempo profetizou Davi acerca de ti mesmo a teu respeito, meu Jesus: 'Traspassaram-me as mãos e os pés; contaram todos os meus ossos.'"
            ),
            whenToPray = LocalizedText("Se reza al orar ante un crucifijo, especialmente en Cuaresma y Semana Santa.", "Recited when praying before a crucifix, especially during Lent and Passiontide", "É rezada ao orar diante de um crucifixo, especialmente na Quaresma e na Semana Santa."),
            thematicTags = listOf("faith", "forgiveness")
        ),
        VaticanPrayer(
            id = "prayer_for_the_dead",
            title = LocalizedText("Oración por los Difuntos", "Prayer for the Dead", "Oração pelos Mortos"),
            latinTitle = "Deus, Creator et Redemptor omnium fidelium, animabus famulorum famularumque tuarum",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText(
                "Oh Dios, Creador y Redentor de todos los fieles, concede a las almas de tus siervos difuntos la remisión de todos sus pecados, para que por nuestras devotas súplicas obtengan el perdón que siempre desearon. Tú que vives y reinas por los siglos de los siglos. Amén.",
                "O God, the Creator and Redeemer of all the faithful, grant to the souls of Thy servants departed the remission of all their sins, that through our devout supplications they may obtain the pardon they have always desired. Who livest and reignest world without end. Amen.",
                "Ó Deus, Criador e Redentor de todos os fiéis, concede às almas dos teus servos falecidos a remissão de todos os seus pecados, para que pelas nossas devotas súplicas obtenham o perdão que sempre desejaram. Tu que vives e reinas pelos séculos dos séculos. Amém."
            ),
            whenToPray = LocalizedText("Se reza por los difuntos queridos, especialmente en el Día de los Fieles Difuntos.", "Prayed for deceased loved ones, especially during All Souls' Day", "É rezada pelos entes queridos falecidos, especialmente no Dia dos Fiéis Defuntos."),
            thematicTags = listOf("forgiveness", "peace")
        ),
        VaticanPrayer(
            id = "prayer_to_st_patrick",
            title = LocalizedText("Oración a San Patricio", "Prayer to St. Patrick", "Oração a São Patrício"),
            latinTitle = "Sancte Patricii, pastor animarum et apostole Hiberniae, ora pro nobis ut simus humiles,",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText(
                "San Patricio, glorioso y santo pastor, que trabajaste por la salvación de las almas en la tierra de Irlanda, obtén para nosotros la gracia de amar a Dios con todo nuestro corazón y amar a nuestro prójimo como a nosotros mismos. San Patricio, fuiste un humilde, piadoso y manso siervo de Dios. Fuiste pastor de almas y devoto siervo de la Iglesia. Fuiste hombre de oración y ayuno, y hombre de gran caridad. Fuiste hombre de gran fe, y hombre de gran esperanza. Fuiste hombre de gran amor. Ruega por nosotros, para que seamos humildes, piadosos y mansos. Ruega por nosotros, para que seamos pastores de almas. Ruega por nosotros, para que seamos devotos siervos de la Iglesia. Ruega por nosotros, para que seamos hombres y mujeres de oración y ayuno. Ruega por nosotros, para que seamos hombres y mujeres de gran caridad. Ruega por nosotros, para que seamos hombres y mujeres de gran fe. Ruega por nosotros, para que seamos hombres y mujeres de gran esperanza. Ruega por nosotros, para que seamos hombres y mujeres de gran amor. Amén.",
                "St. Patrick, glorious and holy shepherd, who didst labor for the salvation of souls in the land of Ireland, obtain for us the grace to love God with our whole heart, and to love our neighbor as ourselves. St. Patrick, you were a humble, pious, and meek servant of God. You were a shepherd of souls, and a devoted servant of the Church. You were a man of prayer and fasting, and you were a man of great charity. You were a man of great faith, and you were a man of great hope. You were a man of great love. Pray for us, that we may be humble, pious, and meek. Pray for us, that we may be shepherds of souls. Pray for us, that we may be devoted servants of the Church. Pray for us, that we may be men and women of prayer and fasting. Pray for us, that we may be men and women of great charity. Pray for us, that we may be men and women of great faith. Pray for us, that we may be men and women of great hope. Pray for us, that we may be men and women of great love. Amen.",
                "São Patrício, glorioso e santo pastor, que trabalhaste pela salvação das almas na terra da Irlanda, obtém para nós a graça de amar a Deus com todo o nosso coração e amar o nosso próximo como a nós mesmos. São Patrício, tu eras um humilde, piedoso e manso servo de Deus. Eras pastor de almas e devoto servo da Igreja. Eras homem de oração e jejum, e eras homem de grande caridade. Eras homem de grande fé, e eras homem de grande esperança. Eras homem de grande amor. Ora por nós, para que sejamos humildes, piedosos e mansos. Ora por nós, para que sejamos pastores de almas. Ora por nós, para que sejamos devotos servos da Igreja. Ora por nós, para que sejamos homens e mulheres de oração e jejum. Ora por nós, para que sejamos homens e mulheres de grande caridade. Ora por nós, para que sejamos homens e mulheres de grande fé. Ora por nós, para que sejamos homens e mulheres de grande esperança. Ora por nós, para que sejamos homens e mulheres de grande amor. Amém."
            ),
            whenToPray = LocalizedText("Se reza para protección, conversión e intercesión.", "Prayed for protection, conversion, and intercession", "É rezada para proteção, conversão e intercessão."),
            thematicTags = listOf("protection", "faith")
        ),
        VaticanPrayer(
            id = "prayer_to_st_rita",
            title = LocalizedText("Oración a Santa Rita", "Prayer to St. Rita", "Oração a Santa Rita"),
            latinTitle = "O Sancta Rita, advocata causarum desperatarum, quae pro tua liberalitate in concedendis",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText(
                "Oh Santa Patrona de los necesitados, Santa Rita, cuyas súplicas ante tu Señor Divino son casi irresistibles, que por tu liberalidad en conceder favores has sido llamada la Abogada de los Desesperados e incluso de los Imposibles; Santa Rita, tan humilde, tan pura, tan mortificada, tan paciente y de tan compasivo amor por tu Jesús Crucificado que pudiste obtener de Él todo lo que pedías, por lo cual todos confiadamente recurren a ti, esperando, si no siempre alivio, al menos consuelo; sé propicia a nuestra petición, mostrando tu poder con Dios en favor de tu suplicante; sé liberal con nosotros, como lo has sido en tantos casos maravillosos, para mayor gloria de Dios, para la propagación de tu propia devoción, y para el consuelo de los que confían en ti. Prometemos, si nuestra petición es concedida, glorificarte haciendo conocido tu favor, bendiciendo y cantando tus alabanzas para siempre. Confiando entonces en tus méritos y poder ante el Sagrado Corazón de Jesús, oramos: (aquí menciona tu petición). Obtén para nosotros nuestra petición por los singulares méritos de tu infancia, por tu perfecta unión con la Divina Voluntad, por tus heroicos sufrimientos durante tu vida matrimonial, por el consuelo que experimentaste en la conversión de tu esposo, por el sacrificio de tus hijos antes que verlos ofender gravemente a Dios, por tu milagrosa entrada al convento, por tus severas penitencias y azotamientos sangrientos tres veces al día, por el sufrimiento causado por la herida que recibiste de la espina de tu Salvador Crucificado, por el amor divino que consumió tu corazón, por esa notable devoción al Santísimo Sacramento, en el que solo exististe durante cuatro años, por la felicidad con que partiste de tus pruebas para unirte a tu Divino Esposo, por el perfecto ejemplo que diste a las personas de todo estado de vida. Ruega por nosotros, oh santa Rita, para que seamos hechos dignos de las promesas de Cristo. Amén.",
                "O Holy Patroness of those in need, St. Rita, whose pleadings before thy Divine Lord are almost irresistible, who for thy lavishness in granting favors hast been called the Advocate of the Hopeless and even of the Impossible; St. Rita, so humble, so pure, so mortified, so patient and of such compassionate love for thy Crucified Jesus that thou couldst obtain from Him whatsoever thou askest, on account of which all confidently have recourse to thee, expecting, if not always relief, at least comfort; be propitious to our petition, showing thy power with God on behalf of thy suppliant; be lavish to us, as thou hast been in so many wonderful cases, for the greater glory of God, for the spreading of thine own devotion, and for the consolation of those who trust in thee. We promise, if our petition is granted, to glorify thee by making known thy favor, to bless and sing thy praises forever. Relying then upon thy merits and power before the Sacred Heart of Jesus, we pray: (here mention your request). Obtain for us our request by the singular merits of thy childhood, by thy perfect union with the Divine Will, by thy heroic sufferings during thy married life, by the consolation thou didst experience at the conversion of thy husband, by the sacrifice of thy children rather than see them grievously offend God, by thy miraculous entrance into the convent, by thy severe penances and thrice daily bloody scourgings, by the suffering caused by the wound thou didst receive from the thorn of thy Crucified Savior, by the divine love which consumed thy heart, by that remarkable devotion to the Blessed Sacrament, on which alone thou didst exist for four years, by the happiness with which thou didst part from thy trials to join thy Divine Spouse, by the perfect example thou gavest to people of every state of life. Pray for us, O holy St. Rita, that we may be made worthy of the promises of Christ. Amen.",
                "Ó Santa Padroeira dos necessitados, Santa Rita, cujas súplicas diante do teu Senhor Divino são quase irresistíveis, que pela tua liberalidade em conceder favores foste chamada a Advogada dos Desesperados e até dos Impossíveis; Santa Rita, tão humilde, tão pura, tão mortificada, tão paciente e de tão compassivo amor pelo teu Jesus Crucificado que pudeste obter d'Ele tudo quanto pedias, pelo que todos confiadamente recorrem a ti, esperando, se não sempre alívio, pelo menos consolação; sê propícia à nossa petição, mostrando o teu poder com Deus em favor do teu suplicante; sê liberal connosco, como o foste em tantos casos maravilhosos, para maior glória de Deus, para a propagação da tua própria devoção, e para a consolação dos que em ti confiam. Prometemos, se a nossa petição for concedida, glorificar-te fazendo conhecido o teu favor, bendizendo e cantando os teus louvores para sempre. Confiando então nos teus méritos e poder diante do Sagrado Coração de Jesus, oramos: (aqui menciona o teu pedido). Obtém para nós o nosso pedido pelos singulares méritos da tua infância, pela tua perfeita união com a Divina Vontade, pelas tuas heroicas sofrimentos durante a tua vida matrimonial, pela consolação que experimentaste na conversão do teu esposo, pelo sacrifício dos teus filhos antes de os ver ofender gravemente a Deus, pela tua miraculosa entrada no convento, pelas tuas severas penitências e flagelações sangrentas três vezes ao dia, pela dor causada pela ferida que recebeste do espinho do teu Salvador Crucificado, pelo amor divino que consumiu o teu coração, por aquela notável devoção ao Santíssimo Sacramento, no qual só exististe durante quatro anos, pela felicidade com que partiste das tuas provações para te unires ao teu Divino Esposo, pelo perfeito exemplo que deste às pessoas de todo o estado de vida. Ora por nós, ó santa Rita, para que sejamos feitos dignos das promesas de Cristo. Amém."
            ),
            whenToPray = LocalizedText("Se reza en situaciones difíciles, causas imposibles e intercesión.", "Prayed for difficult situations, impossible causes, and intercession", "É rezada em situações difíceis, causas impossíveis e intercessão."),
            thematicTags = listOf("health", "family")
        ),
        VaticanPrayer(
            id = "prayer_to_st_therese",
            title = LocalizedText("Oración a Santa Teresita", "Prayer to St. Therese", "Oração a Santa Teresinha"),
            latinTitle = "O parva Teresia Infantiae Iesu, quaeso, elige mihi rosam ex hortis caelestibus et mitte",
            category = PrayerCategory.INTERCESSION,
            leaderText = LocalizedText(
                "Oh pequeña Teresa del Niño Jesús, por favor elige para mí una rosa de los jardines celestiales y envíamela como mensaje de amor. Oh pequeña Flor de Jesús, pide a Dios hoy que conceda los favores que ahora pongo con confianza en tus manos. (Menciona tu petición). Santa Teresa, ayúdame a creer siempre como tú creíste en el gran amor de Dios por mí, para que pueda imitar tu 'Pequeño Camino' cada día. Amén.",
                "O Little Therese of the Child Jesus, please pick for me a rose from the heavenly gardens and send it to me as a message of love. O Little Flower of Jesus, ask God today to grant the favors I now place with confidence in your hands. (Mention your request). St. Therese, help me to always believe as you did, in God's great love for me, so that I might imitate your 'Little Way' each day. Amen.",
                "Ó pequena Teresa da Criança Jesus, por favor escolhe para mim uma rosa dos jardins celestiais e envia-ma como mensagem de amor. Ó pequeno Flor de Jesus, pede a Deus hoje que conceda os favores que agora coloco com confiança nas tuas mãos. (Menciona o teu pedido). Santa Teresa, ajuda-me a acreditar sempre como tu acreditaste no grande amor de Deus por mim, para que eu possa imitar o teu 'Pequeno Caminho' cada dia. Amém."
            ),
            whenToPray = LocalizedText("Se reza para intercesión, confianza en el amor de Dios y seguir el Camino Pequeño.", "Prayed for intercession, confidence in God's love, and following the Little Way", "É rezada para intercessão, confiança no amor de Deus e seguir o Caminho Pequeno."),
            thematicTags = listOf("faith", "peace")
        ),
        VaticanPrayer(
            id = "sign_of_the_cross",
            title = LocalizedText("Señal de la Cruz", "Sign of the Cross", "Sinal da Cruz"),
            latinTitle = "In nomine Patris, et Filii, et Spiritus Sancti. Amen.",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén.",
                "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.",
                "Em nome do Pai, e do Filho, e do Espírito Santo. Amém."
            ),
            whenToPray = LocalizedText("Se usa al principio y al final de las oraciones, la Misa y las devociones diarias.", "Used at the beginning and end of prayers, Mass, and daily devotions", "É usado no início e no fim das orações, da Missa e das devoções diárias."),
            thematicTags = listOf("daily_routine", "faith", "protection")
        ),
        VaticanPrayer(
            id = "te_deum",
            title = LocalizedText("Te Deum", "Te Deum", "Te Deum"),
            latinTitle = "Te Deum laudamus: te Dominum confitemur.",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "A Ti, Dios, te alabamos; a Ti, Señor, te reconocemos.\nA Ti, eterno Padre, te venera toda la tierra.\nA Ti cantan todos los Ángeles; a Ti los cielos y todas las Potestades;\nA Ti los Querubines y Serafines proclaman sin cesar:\nSanto, Santo, Santo es el Señor Dios de los ejércitos.\nLlenos están los cielos y la tierra de la majestad de tu gloria.\nA Ti te alaba el coro glorioso de los Apóstoles;\nA Ti te ensalza el número laudable de los Profetas;\nA Ti te glorifica el ejército blanco de los Mártires.\nA Ti por todo el orbe la santa Iglesia te confiesa:\nPadre de inmensa majestad;\nTu adorable, verdadero y único Hijo;\nY también el Espíritu Santo Paráclito.\nTú eres el Rey de gloria, oh Cristo.\nTú eres el Hijo sempiterno del Padre.\nTú, para librar al hombre, no desdeñaste el seno de la Virgen.\nTú, vencido el aguijón de la muerte, abriste a los creyentes el reino de los cielos.\nTú estás sentado a la diestra de Dios, en la gloria del Padre.\nCreemos que has de venir como Juez.\nTe rogamos, pues, que socorras a tus siervos, a quienes redimiste con tu preciosa sangre.\nHaz que en la gloria eterna sean contados con tus santos.\n\nSalva a tu pueblo, Señor, y bendice a tu heredad.\nY gobiérnalos, y ensálzalos para siempre.\nCada día te bendecimos;\nY alabamos tu nombre por los siglos de los siglos.\nDígnate, Señor, guardarnos hoy sin pecado.\nTen misericordia de nosotros, Señor, ten misericordia de nosotros.\nDescienda tu misericordia sobre nosotros, Señor, según esperamos en Ti.\nEn Ti, Señor, he esperado; no sea yo confundido para siempre.\n\nAmén.",
                "We praise Thee, O God: we acknowledge Thee to be the Lord.\nAll the earth doth worship Thee, the Father everlasting.\nTo Thee all Angels cry aloud; the Heavens, and all the Powers therein;\nTo Thee Cherubim and Seraphim continually do cry:\nHoly, Holy, Holy, Lord God of Sabaoth;\nHeaven and earth are full of the Majesty of Thy glory.\nThe glorious company of the Apostles praise Thee.\nThe goodly fellowship of the Prophets praise Thee.\nThe noble army of Martyrs praise Thee.\nThe holy Church throughout all the world doth acknowledge Thee;\nThe Father of an infinite Majesty;\nThine adorable, true, and only Son;\nAlso the Holy Ghost, the Comforter.\nThou art the King of Glory, O Christ.\nThou art the everlasting Son of the Father.\nWhen Thou tookest upon Thee to deliver man, Thou didst not abhor the Virgin's womb.\nWhen Thou hadst overcome the sharpness of death, Thou didst open the Kingdom of Heaven to all believers.\nThou sittest at the right hand of God, in the glory of the Father.\nWe believe that Thou shalt come to be our Judge.\nWe therefore pray Thee, help Thy servants, whom Thou hast redeemed with Thy precious blood.\nMake them to be numbered with Thy Saints, in glory everlasting.\n\nO Lord, save Thy people, and bless Thine heritage.\nGovern them, and lift them up for ever.\nDay by day we magnify Thee;\nAnd we worship Thy Name ever, world without end.\nVouchsafe, O Lord, to keep us this day without sin.\nO Lord, have mercy upon us, have mercy upon us.\nO Lord, let Thy mercy lighten upon us, as our trust is in Thee.\nO Lord, in Thee have I trusted: let me never be confounded.\n\nAmen.",
                "A Ti, ó Deus, louvamos; a Ti, Senhor, confessamos.\nA Ti, Pai eterno, venera toda a terra.\nA Ti clamam todos os Anjos; a Ti os céus e todas as Potestades;\nA Ti os Querubins e Serafins proclamam sem cessar:\nSanto, Santo, Santo, Senhor Deus dos exércitos.\nCheios estão os céus e a terra da majestade da tua glória.\nA Ti louva o coro glorioso dos Apóstolos;\nA Ti o número louvável dos Profetas;\nA Ti o exército branco dos Mártires.\nA Ti por todo o orbe a santa Igreja confessa:\nPai de imensa majestade;\nO teu venerando, verdadeiro e único Filho;\nE também o Espírito Santo Paráclito.\nTu és o Rei da glória, ó Cristo.\nTu és o Filho sempiterno do Pai.\nTu, para livrar o homem, não horrorizaste o ventre da Virgem.\nTu, vencido o aguilhão da morte, abriste aos crentes os reinos dos céus.\nTu estás assentado à destra de Deus, na glória do Pai.\nCremos que hás de vir a julgar.\nRogamos-Te, pois, que socorras os teus servos, que resgataste com o teu precioso sangue.\nFaze que na glória eterna sejam contados com os teus santos.\n\nSalva o teu povo, Senhor, e abençoa a tua herança.\nE governa-os, e exalta-os para sempre.\nDia por dia Te bendizemos;\nE louvamos o teu nome pelos séculos dos séculos.\nDigna-Te, Senhor, guardar-nos hoje sem pecado.\nTem misericórdia de nós, Senhor, tem misericórdia de nós.\nDesça a tua misericórdia sobre nós, Senhor, como esperamos em Ti.\nEm Ti, Senhor, esperei; não seja eu confundido para sempre.\n\nAmém."
            ),
            whenToPray = LocalizedText("Se canta o reza en celebraciones litúrgicas, especialmente en ocasiones solemnes.", "Sung or recited in liturgical celebrations, especially on solemn occasions", "É cantado ou rezado em celebrações litúrgicas, especialmente em ocasiões solenes."),
            thematicTags = listOf("praise", "faith")
        ),
        VaticanPrayer(
            id = "armor_of_god",
            title = LocalizedText("Oración de la Armadura de Dios", "Armor of God Prayer", "Oração da Armadura de Deus"),
            latinTitle = "Deus omnipotens, per virtutem Spiritus Sancti, induc me armatura Dei, ut possim stare",
            category = PrayerCategory.PROTECTION,
            leaderText = LocalizedText(
                "Dios todopoderoso, por el poder del Espíritu Santo, vísteme con la armadura de Dios, para que pueda estar firme contra las asechanzas del diablo. Cíñeme con el cinturón de la verdad. Revísteme con la coraza de la justicia. Calza mis pies con el celo del evangelio de la paz. Toma el escudo de la fe, con el cual pueda apagar todos los dardos encendidos del maligno. Ponme el yelmo de la salvación y la espada del Espíritu, que es la palabra de Dios. Ayúdame a orar en el Espíritu en toda ocasión con toda clase de oraciones y súplicas. Ayúdame a estar alerta y perseverar siempre en oración por todos los santos. Dame fortaleza en el Señor y en su fuerza poderosa, para que pueda estar firme contra principados y potestades, contra los dominadores de este mundo tenebroso y contra las fuerzas espirituales del mal en las regiones celestiales. Por Cristo nuestro Señor. Amén.",
                "Almighty God, by the power of the Holy Spirit, clothe me with the armor of God, that I may be able to stand firm against the tactics of the devil. Gird me with the belt of truth. Clothe me with the breastplate of righteousness. Fit my feet with the readiness that comes from the gospel of peace. Take up the shield of faith, with which I can extinguish all the flaming arrows of the evil one. Put on me the helmet of salvation and the sword of the Spirit, which is the word of God. Help me to pray in the Spirit on all occasions with all kinds of prayers and requests. Help me to be alert and always keep on praying for all the Lord's people. Give me strength in the Lord and in his mighty power, that I may stand against the rulers, against the authorities, against the powers of this dark world and against the spiritual forces of evil in the heavenly realms. Through Christ our Lord. Amen.",
                "Deus Todo-Poderoso, pelo poder do Espírito Santo, reveste-me com a armadura de Deus, para que eu possa permanecer firme contra as ciladas do diabo. Cinge-me com o cinto da verdade. Reveste-me com a couraça da justiça. Calça os meus pés com a prontidão do evangelho da paz. Toma o escudo da fé, com o qual poderei apagar todos os dardos inflamados do maligno. Põe em mim o capacete da salvação e a espada do Espírito, que é a palavra de Deus. Ajuda-me a orar em todo o tempo no Espírito com toda oração e súplica. Ajuda-me a vigiar com toda perseverança e oração por todos os santos. Dá-me força no Senhor e na força do seu poder, para que eu possa permanecer firme contra os principados e potestades, contra os dominadores deste mundo tenebroso e contra as forças espirituais da maldade nas regiões celestiais. Por Cristo nosso Senhor. Amém."
            ),
            whenToPray = LocalizedText("Se reza para protección espiritual, en tiempos de guerra espiritual, tentación o pruebas; a menudo en la mañana o antes de situaciones difíciles.", "Recited for spiritual protection, in times of spiritual warfare, temptation, or when facing trials; often prayed in the morning or before challenging situations", "É rezada para proteção espiritual, em tempos de guerra espiritual, tentação ou provações; frequentemente pela manhã ou antes de situações desafiadoras."),
            thematicTags = listOf("protection", "spiritual_warfare", "daily_routine")
        ),
        VaticanPrayer(
            id = "eternal_father",
            title = LocalizedText("Padre Eterno", "Eternal Father", "Pai Eterno"),
            latinTitle = "Aeterne Pater, offero Tibi Corpus et Sanguinem, Animum et Divinitatem dilectissimi Filii",
            category = PrayerCategory.BASIC,
            leaderText = LocalizedText(
                "Padre Eterno, te ofrezco el Cuerpo y la Sangre, Alma y Divinidad de tu Hijo muy amado, nuestro Señor Jesucristo, en expiación de nuestros pecados y los del mundo entero.",
                "Eternal Father, I offer you the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ, in atonement for our sins and those of the whole world.",
                "Pai Eterno, ofereço-Te o Corpo e o Sangue, a Alma e a Divindade do Teu Filho muito amado, Senhor nosso Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro."
            ),
            whenToPray = LocalizedText("Se reza al comienzo de cada decena en la Corona de la Divina Misericordia.", "Recited at the start of each decade in the Divine Mercy Chaplet", "É rezada no início de cada dezena na Coroa da Divina Misericórdia."),
            thematicTags = listOf("mercy", "forgiveness")
        ),
    )
}
