package com.example.data.repository

import java.util.Calendar
import com.example.data.models.ChristManifestation
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchSchismHistory
import com.example.data.models.LocalizedText
import com.example.data.models.MarianApparition
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay

object DevotionsAndHistoryData {

    fun getTodaySaint(cal: Calendar = Calendar.getInstance()): SaintOfTheDay {
        val month = cal.get(Calendar.MONTH) // 0-based: 0 = Jan, 9 = Oct, 10 = Nov
        val day = cal.get(Calendar.DAY_OF_MONTH)

        return when {
            month == Calendar.OCTOBER && day == 27 -> SaintOfTheDay(
                id = "st_frumentius",
                name = LocalizedText("San Frumencio de Etiopía y Beatos Mártires", "Saint Frumentius (Apostle of Ethiopia)", "São Frumêncio de Axum"),
                feastDate = "27 de Octubre",
                title = LocalizedText("Obispo y Apóstol de Etiopía / Mártires de la Fe", "Bishop and Apostle of Ethiopia", "Bispo e Apóstolo da Etiópia"),
                patronages = LocalizedText("Etiopía, misiones en África, catequistas y diplomáticos cristianos.", "Ethiopia, African missions, catechists.", "Etiópia e evangelização dos povos."),
                biography = LocalizedText(
                    "Nacido en Tiro en el siglo IV, tras naufragar en el Mar Rojo fue llevado cautivo a la corte del Reino de Axum (Etiopía). Por su virtud y sabiduría llegó a ser consejero real y preceptor del príncipe Ezana, a quien instruyó en la fe católica. San Atanasio de Alejandría lo consagró primer Obispo de Etiopía ('Abba Salama', Padre de la Paz). Evangelizó incansablemente el cuerno de África bautizando al Rey y a su pueblo.",
                    "Captured as a youth after a shipwreck in the Red Sea, Frumentius gained high esteem at the court of Axum. He introduced Christianity to Prince Ezana and was consecrated first Bishop of Ethiopia by St. Athanasius of Alexandria.",
                    "Após naufrágio, levou a fé cristã ao Reino de Axum. Foi consagrado primeiro bispo da Etiópia por Santo Atanásio e batizou a família real etíope."
                ),
                iconicQuote = LocalizedText(
                    "«Dios saca bienes infinitos aun de los mayores naufragios humanos».",
                    "“God draws infinite good even from the greatest human shipwrecks.”",
                    "“Deus manifesta Sua glória salvadora até mesmo nas provações do exílio.”"
                ),
                intercessionPrayer = LocalizedText(
                    "Dios todopoderoso, que por el celo apostólico de San Frumencio encendiste la luz del Evangelio en tierras lejanas, concédenos proclamar a Cristo con valentía en todo lugar. Por Jesucristo nuestro Señor. Amén.",
                    "Almighty God, who through Saint Frumentius brought the light of the Gospel to distant nations, grant us zealous faith. Through Christ our Lord. Amen.",
                    "Ó Deus, que fizestes de São Frumêncio luz para os povos, concedei-nos proclamar a Cristo com fervor apostólico. Amém."
                )
            )

            month == Calendar.OCTOBER && day == 28 -> SaintOfTheDay(
                id = "sts_simon_jude",
                name = LocalizedText("Santos Simón y Judas Tadeo (Apóstoles y Mártires)", "Saints Simon and Jude (Apostles & Martyrs)", "Santos Simão e Judas Tadeu (Apóstolos)"),
                feastDate = "28 de Octubre",
                title = LocalizedText("Fiesta Litúrgica de los Santos Apóstoles / Patrono de los Casos Difíciles", "Feast of the Apostles / Patron of Impossible Causes", "Apóstolos de Cristo e Mártires da Fé"),
                patronages = LocalizedText("Causas desesperadas, casos imposibles, trabajadores de la salud y hospitales.", "Lost causes, hopeless situations, desperate cases.", "Causas desesperadas e impossíveis, famílias e enfermos."),
                biography = LocalizedText(
                    "San Simón el Cananeo y San Judas Tadeo fueron llamados personalmente por Jesucristo para formar parte de los Doce Apóstoles (Lc 6, 15-16). En la Última Cena, San Judas preguntó al Señor: '¿Por qué te has de manifestar a nosotros y no al mundo?' (Jn 14, 22). Ambos predicaron el Evangelio en Mesopotamia y Persia sufriendo el martirio por negarse a adorar dioses paganos. San Judas es venerado universalmente como el gran intercesor de los casos desesperados e imposibles.",
                    "Saints Simon and Jude were chosen by Christ among the Twelve Apostles. St. Jude questioned Jesus at the Last Supper and authored the Epistle of Jude. Both preached throughout Persia and suffered glorious martyrdom.",
                    "Chamados por Jesus entre os Doze Apóstolos. Pregaram corajosamente o Evangelho e derramaram seu sangue como mártires em testemunho da fé católica."
                ),
                iconicQuote = LocalizedText(
                    "«Manténganse en el amor de Dios, esperando la misericordia de nuestro Señor Jesucristo para la vida eterna». (Judas 1, 21)",
                    "“Keep yourselves in the love of God, waiting for the mercy of our Lord Jesus Christ unto life everlasting.” (Jude 1:21)",
                    "“Conservai-vos no amor de Deus, esperando a misericórdia de nosso Senhor Jesus Cristo para a vida eterna”. (Jd 1, 21)"
                ),
                intercessionPrayer = LocalizedText(
                    "¡Gloriosísimo Apóstol San Judas Tadeo, fiel siervo y amigo de Jesús!, socórreme en esta gran necesidad para que reciba el consuelo y el auxilio del cielo en todas mis tribulaciones. Amén.",
                    "Glorious Apostle St. Jude Thaddeus, faithful servant and friend of Jesus, come to my assistance in this great need and obtain for me heavenly comfort. Amen.",
                    "Glorioso São Judas Tadeu, apóstolo e mártir, rogai por nós nas causas mais difíceis e alcançai-nos a graça que vos pedimos. Amém."
                )
            )

            month == Calendar.OCTOBER && day == 29 -> SaintOfTheDay(
                id = "st_narcissus",
                name = LocalizedText("San Narciso de Jerusalén (Obispo y Confesor)", "Saint Narcissus of Jerusalem (Bishop)", "São Narciso de Jerusalém"),
                feastDate = "29 de Octubre",
                title = LocalizedText("Patriarca y Obispo Centenario de Jerusalén", "Bishop of Jerusalem", "Bispo e Confessor da Fé"),
                patronages = LocalizedText("Ancianos, víctimas de calumnias y sacerdotes mayores.", "Elderly, victims of false accusations.", "Idosos e pessoas injustamente caluniadas."),
                biography = LocalizedText(
                    "Obispo de Jerusalén a finales del siglo II, presidió concilios defendiendo la celebración de la Pascua según la tradición apostólica. Sobrellevó con paciencia heroica falsas calumnias retirándose al desierto en profunda oración antes de ser restaurado a su sede hasta los 116 años.",
                    "St. Narcissus governed the Church in Jerusalem into great old age, presiding over synods on Easter and enduring slander with serene charity.",
                    "Bispo venerável de Jerusalém, governou a diocese até idade avançada com oração e paciência heróica diante de calúnias."
                ),
                iconicQuote = LocalizedText("«El Señor es la defensa del inocente y su luz resplandecerá a su debido tiempo».", "“The Lord is the defender of the innocent.”", "“O Senhor é o escudo dos justos.”"),
                intercessionPrayer = LocalizedText("Señor Jesús, danos la paciencia y santidad de San Narciso ante las pruebas de la vida. Amén.", "Lord Jesus, grant us patient trust like St. Narcissus. Amen.", "Senhor, dai-nos paciência e pureza de coração. Amém.")
            )

            month == Calendar.OCTOBER && day == 31 -> SaintOfTheDay(
                id = "st_alphonsus_rodriguez",
                name = LocalizedText("San Alfonso Rodríguez y Víspera de Todos los Santos", "Saint Alphonsus Rodriguez (All Hallows' Eve)", "Santo Afonso Rodrigues"),
                feastDate = "31 de Octubre",
                title = LocalizedText("Hermano Jesuita y Místico del Servicio Humilde", "Jesuit Mystic and Porter", "Religioso e Místico do Serviço"),
                patronages = LocalizedText("Porteros, religiosos legos y servidores del prójimo.", "Porters, lay brothers, hospitality workers.", "Porteiros e servidores da Igreja."),
                biography = LocalizedText(
                    "Ejerció como portero del colegio de Palma de Mallorca durante 46 años. Al abrir la puerta decía: '¡Ya voy, Señor!', viendo a Cristo en cada visitante. Fue el gran director espiritual de San Pedro Claver, a quien inspiró a misionar en América.",
                    "Jesuit lay brother who served 46 years as a humble porter, seeing Christ in every visitor. He inspired St. Peter Claver's mission to Colombia.",
                    "Serviu como humilde porteiro por 46 anos, vendo Cristo em cada pessoa que chegava à porta do convento."
                ),
                iconicQuote = LocalizedText("«¡Ya voy, Señor!: ver a Cristo en cada hermano que llama a nuestra puerta».", "“I am coming, Lord!: seeing Christ in every visitor.”", "“Eis-me aqui, Senhor!: servir a Deus nos irmãos.”"),
                intercessionPrayer = LocalizedText("Haznos, Señor, siervos humildes que acojan a Cristo en los hermanos más necesitados. Amén.", "Make us humble servants of Christ in our neighbor. Amen.", "Fazei-nos mansos e humildes de coração. Amém.")
            )

            month == Calendar.NOVEMBER && day == 1 -> SaintOfTheDay(
                id = "all_saints",
                name = LocalizedText("Solemnidad de Todos los Santos", "Solemnity of All Saints", "Solenidade de Todos os Santos"),
                feastDate = "1 de Noviembre",
                title = LocalizedText("Celebración de la Iglesia Triunfante en el Cielo", "Solemnity of the Church Triumphant", "Festa dos Santos no Céu"),
                patronages = LocalizedText("Toda la Iglesia Católica, vocación universal a la santidad.", "The universal Church, call to holiness.", "A Igreja universal e todos os cristãos."),
                biography = LocalizedText(
                    "Celebramos a la inmensa multitud de bienaventurados que nadie puede contar (Apoc 7, 9) que ya contemplan a Dios cara a cara. La Iglesia nos recuerda que la santidad es la vocación de todo bautizado.",
                    "Solemn feast celebrating the countless multitude of saints standing before the throne of the Lamb in celestial glory.",
                    "Celebração solene de todos os santos conhecidos e desconhecidos que triunfam na glória celeste com Cristo."
                ),
                iconicQuote = LocalizedText("«Alégrense y regocíjense, porque su recompensa será grande en los cielos». (Mt 5, 12)", "“Rejoice and be glad, for your reward will be great in heaven.”", "“Alegrai-vos e exultai, porque é grande a vossa recompensa nos céus.”"),
                intercessionPrayer = LocalizedText("¡Santos y Santas de Dios, intercedan por nosotros peregrinos en la tierra! Amén.", "All holy saints of God, pray for us on our earthly pilgrimage! Amen.", "Santos e Santas de Deus, rogai por nós! Amém.")
            )

            month == Calendar.NOVEMBER && day == 2 -> SaintOfTheDay(
                id = "all_souls",
                name = LocalizedText("Conmemoración de Todos los Fieles Difuntos", "Commemoration of All the Faithful Departed (All Souls)", "Fiéis Defuntos"),
                feastDate = "2 de Noviembre",
                title = LocalizedText("Oración y Sufragio por las Almas del Purgatorio", "Prayers for the Holy Souls in Purgatory", "Oração pelas Almas do Purgatório"),
                patronages = LocalizedText("Almas del purgatorio y duelo cristiano.", "Holy souls in purgatory, grieving families.", "Almas do purgatório e sufrágios dos fiéis."),
                biography = LocalizedText(
                    "Día de solemne sufragio, limosna e indulgencias por las almas del Purgatorio que aguardan su purificación final para contemplar a Dios. Como enseña 2 Macabeos 12, es obra santa orar por los difuntos.",
                    "Solemn commemoration offering Holy Masses and prayers to speed the purification of the holy souls in purgatory.",
                    "Dia santo de oração e sufrágio da Igreja pelas almas que se purificam no Purgatório."
                ),
                iconicQuote = LocalizedText("«Dales, Señor, el descanso eterno, y brille para ellas la luz perpetua».", "“Eternal rest grant unto them, O Lord, and let perpetual light shine upon them.”", "“Dai-lhes, Senhor, o descanso eterno, e brilhe para elas a luz perpétua.”"),
                intercessionPrayer = LocalizedText("Descansen en paz las almas de todos los fieles difuntos por la misericordia de Dios. Amén.", "May the souls of the faithful departed through the mercy of God rest in peace. Amen.", "Descanso eterno dai-lhes, Senhor. Amém.")
            )

            else -> {
                // Dynamically constructed feast day for today's date
                val dayStr = "$day de ${when (month) {
                    Calendar.JANUARY -> "Enero"
                    Calendar.FEBRUARY -> "Febrero"
                    Calendar.MARCH -> "Marzo"
                    Calendar.APRIL -> "Abril"
                    Calendar.MAY -> "Mayo"
                    Calendar.JUNE -> "Junio"
                    Calendar.JULY -> "Julio"
                    Calendar.AUGUST -> "Agosto"
                    Calendar.SEPTEMBER -> "Septiembre"
                    Calendar.OCTOBER -> "Octubre"
                    Calendar.NOVEMBER -> "Noviembre"
                    else -> "Diciembre"
                }}"

                SaintOfTheDay(
                    id = "saint_day_${month}_$day",
                    name = LocalizedText("Santo del Día de Hoy ($dayStr)", "Saint of Today ($dayStr)", "Santo do Dia de Hoje ($dayStr)"),
                    feastDate = dayStr,
                    title = LocalizedText("Conmemoración del Santoral Católico", "Catholic Sanctoral Feast", "Comemoração do Santoral Católico"),
                    patronages = LocalizedText("La Iglesia universal y los devotos de hoy.", "The universal Church and faithful.", "A Igreja universal e os fiéis."),
                    biography = LocalizedText(
                        "En este día, la Iglesia Católica conmemora a los santos, mártires y confesores de la fe inscritos en el Martirologio Romano que dieron testimonio supremo de amor a Cristo y a los hermanos. Su ejemplo nos inspira a vivir con fidelidad la vocación a la santidad cotidiana.",
                        "On this day, the Catholic Church celebrates the holy witnesses and martyrs commemorated in the Roman Martyrology who sanctified the world through heroic charity.",
                        "Neste dia santo, a Igreja celebra os mártires e santos que viveram heroicamente as virtudes cristãs em união com o Coração de Jesus."
                    ),
                    iconicQuote = LocalizedText("«Sean santos, porque Yo, el Señor su Dios, soy santo». (Lev 19, 2)", "“Be holy, because I, the Lord your God, am holy.” (Lev 19:2)", "“Sede santos, porque Eu, o Senhor vosso Deus, sou santo”. (Lv 19, 2)"),
                    intercessionPrayer = LocalizedText("Señor Dios todopoderoso, concédenos la gracia de seguir el ejemplo de tus santos y alcanzar un día la gloria eterna. Amén.", "Almighty God, grant us the grace to follow the holy example of Your saints. Amen.", "Deus todo-poderoso, guiai-nos pelos caminhos da santidade. Amém.")
                )
            }
        }
    }

    val saintOfTheDay: SaintOfTheDay get() = getTodaySaint()

    val verseOfTheDay = VerseOfTheDay(
        // Canonical citation; the repository renders it in the reader's language.
        reference = "phi 4:6-7",
        text = LocalizedText(
            "«No se angustien por nada; más bien, en toda ocasión, con oración y súplica y acción de gracias, presenten sus peticiones a Dios. Y la paz de Dios, que sobrepasa todo entendimiento, cuidará sus corazones y sus pensamientos en Cristo Jesús».",
            "“Have no anxiety at all, but in everything, by prayer and petition, with thanksgiving, make your requests known to God. Then the peace of God that surpasses all understanding will guard your hearts and minds in Christ Jesus.”",
            "“Não vos inquieteis com coisa alguma; antes, as vossas petições sejam em tudo conhecidas diante de Deus pela oração e súplica, com ação de graças. E a paz de Deus, que excede todo o entendimento, guardará os vossos corações e as vossas mentes em Cristo Jesus.”"
        ),
        meditation = LocalizedText(
            "San Pablo escribe estas palabras preso en Roma. La paz cristiana no es la ausencia de dificultades, sino la certeza inquebrantable de la presencia salvadora de Cristo que camina con nosotros.",
            "St. Paul penned these words from imprisonment. Christian peace is not circumstantial comfort, but the supernatural tranquility born of total surrender to God's providence.",
            "São Paulo nos exorta a transformar a ansiedade terrena em oração confiante e ação de graças."
        ),
        spiritualChallenge = LocalizedText(
            "Hoy, en cuanto sientas una punzada de preocupación o miedo, detente, haz la señal de la Cruz y di: 'Jesús, en Ti confío'.",
            "Whenever worry surfaces today, pause, trace the Sign of the Cross upon yourself, and say: 'Jesus, I trust in You.'",
            "Hoje, ao primeiro sinal de ansiedade, faça o sinal da Cruz e reze: 'Jesus, manso e humilde de coração, fazei o meu coração semelhante ao Vosso'."
        )
    )

    val marianApparitions: List<MarianApparition> = listOf(
        MarianApparition(
            id = "virgen_del_valle",
            title = LocalizedText("Nuestra Señora del Valle (Virgen del Valle)", "Our Lady of the Valley", "Nossa Senhora do Vale"),
            feastDay = LocalizedText("8 de Septiembre", "8 September", "8 de Setembro"),
            year = "1530",
            location = LocalizedText("El Valle del Espíritu Santo, Isla de Margarita, Venezuela", "Margarita Island, Venezuela", "Ilha de Margarita, Venezuela"),
            primarySanctuary = LocalizedText("Basílica Menor de Nuestra Señora del Valle", "Minor Basilica of Our Lady of the Valley", "Basílica Menor de Nossa Senhora do Vale"),
            historyAndApparition = LocalizedText(
                "Llegada a la isla de Cubagua hacia 1530 como la Purísima Concepción. Tras el huracán y maremoto de 1541 que destruyó Nueva Cádiz, la imagen sagrada fue trasladada intacta a la Isla de Margarita en el frondoso Valle del Espíritu Santo. Es venerada con inmenso fervor por los pescadores, marineros y todo el pueblo venezolano.",
                "Arrived on Cubagua island around 1530. After a destructive hurricane and tsunami leveled the settlement in 1541, the holy image was brought intact to Margarita Island into the Valley of the Holy Spirit, becoming the celestial protectress of sailors.",
                "Chegou por volta de 1530 à Venezuela. Após a destruição de Cubagua por um maremoto, a imagem foi levada intacta para a Ilha de Margarita, onde é venerada pelos pescadores e marinheiros."
            ),
            promises = LocalizedText(
                "Protección maternal a los navegantes en tempestades marinas, auxilio a los enfermos y consuelo a los pescadores en sus faenas.",
                "Maternal safeguarding for mariners during ocean tempests, physical cures, and abundant harvests of peace.",
                "Proteção aos homens do mar contra tormentas e amparo às famílias necessitadas."
            ),
            recordedMiracles = LocalizedText(
                "El milagro de la perla en forma de pierna encontrada por el pescador Domingo (curado milagrosamente de una herida de raya marina), la lluvia milagrosa de 1608 tras una gran sequía y la protección de los patriotas en la batalla de Matasiete.",
                "The miraculous pearl in the shape of a leg found by the diver Domingo whose gangrenous limb was restored, and the rain miracle of 1608 ending famine.",
                "O milagre da pérola em formato de perna pescada em agradecimento pela cura de um mergulhador, e o fim da grande seca em 1608."
            ),
            venerationPrayer = LocalizedText(
                "¡Oh Madre Santísima del Valle, tierna protectora de los mares y de nuestros hogares!: extiende tu manto virginal sobre nuestras familias, guía a los que navegan en las aguas de la vida y llévanos siempre a tu Hijo Jesucristo. Amén.",
                "O Blessed Mother of the Valley, sweet protectress of the seas and of our homes: shelter our families beneath your mantle, steer us through life's storms, and bring us to Jesus. Amen.",
                "Ó Virgem Santíssima do Vale, doce padroeira dos mares: protegei as nossas famílias e guiai os nossos corações rumo ao Céu. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Venezuela", "Venezuela", "Venezuela"),
            patronageOf = LocalizedText("Patrona del Oriente Venezolano, de los pescadores y de la Armada Bolivariana.", "Patroness of Eastern Venezuela, fishermen, and mariners.", "Padroeira do Oriente Venezuelano e dos pescadores.")
        ),
        MarianApparition(
            id = "virgen_del_carmen",
            title = LocalizedText("Nuestra Señora del Carmen (Virgen del Monte Carmelo)", "Our Lady of Mount Carmel", "Nossa Senhora do Carmo"),
            feastDay = LocalizedText("16 de Julio", "16 July", "16 de Julho"),
            year = "1251",
            location = LocalizedText("Aylesford, Inglaterra / Monte Carmelo, Tierra Santa", "Mount Carmel, Holy Land / England", "Monte Carmelo / Inglaterra"),
            primarySanctuary = LocalizedText("Santuario Stella Maris (Monte Carmelo, Israel) y Templo Votivo de Maipú (Chile)", "Stella Maris Monastery & National Votive Shrine of Maipú", "Santuário Stella Maris e Basílica de Maipú"),
            historyAndApparition = LocalizedText(
                "Heredera de la espiritualidad del profeta Elías en el Monte Carmelo. El 16 de julio de 1251, la Santísima Virgen se apareció a San Simón Stock, Superior General de los Carmelitas, entregándole el Santo Escapulario café como signo de protección maternal y salvación eterna.",
                "Rooted in Elijah's prayer on Mount Carmel. On July 16, 1251, Our Lady appeared to Saint Simon Stock, handing him the brown Scapular as a sacred livery of maternal protection and eternal salvation.",
                "Herdeira dos eremitas do Monte Carmelo. Em 1251, Nossa Senhora apareceu a São Simão Stock entregando-lhe o Santo Escapulário como sinal de salvação."
            ),
            promises = LocalizedText(
                "«Quien muera revestido de este Santo Escapulario no padecerá el fuego eterno» (Privilegio Sabatino: liberación del Purgatorio el primer sábado tras la muerte).",
                "“Whoever dies wearing this Scapular shall not suffer eternal fire” (Sabbatine Privilege: swift deliverance from Purgatory).",
                "“Aquele que morrer vestindo o Santo Escapulário não padecerá o fogo eterno” (Privilégio Sabatino)."
            ),
            recordedMiracles = LocalizedText(
                "Conversiones milagrosas a la hora de la muerte, protección contra incendios y naufragios, y la victoria en la gesta de la Independencia de América del Sur encomendada al Escapulario.",
                "Incorrupt preservation of Scapulars, rescue of stranded sailors, and heroic protection across battlefields.",
                "Inúmeros livramentos de perigos mortais, proteção contra incêndios e a libertação de almas do Purgatório."
            ),
            venerationPrayer = LocalizedText(
                "¡Flor del Carmelo, vid florida, esplendor del cielo, Virgen fecunda y singular!: a los carmelitas da tus favores, Estrella del mar. Bajo tu escapulario amparamos nuestra salvación. Amén.",
                "Flower of Carmel, blossoming vine, splendor of heaven, childbearing yet maiden divine: bestow thy gifts upon us, Star of the Sea! Amen.",
                "Flor do Carmelo, vide florida, esplendor do céu, Virgem fecunda e singular: dai-nos a vossa bênção maternal, ó Estrela do Mar. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal / Chile / Colombia / España", "Universal / Chile / Spain", "Universal / Brasil / Chile"),
            patronageOf = LocalizedText("Patrona y Reina de Chile, de los Carmelitas, de las almas del Purgatorio, de los transportistas y bomberos.", "Patroness of Chile, Carmelites, souls in Purgatory, and mariners.", "Padroeira da Ordem do Carmo, do Chile e libertadora das almas do Purgatório.")
        ),
        MarianApparition(
            id = "guadalupe",
            title = LocalizedText("Nuestra Señora de Guadalupe", "Our Lady of Guadalupe", "Nossa Senhora de Guadalupe"),
            feastDay = LocalizedText("12 de Diciembre", "12 December", "12 de Dezembro"),
            year = "1531",
            location = LocalizedText("Cerro del Tepeyac, Ciudad de México", "Tepeyac Hill, Mexico City", "Monte Tepeyac, Cidade do México"),
            primarySanctuary = LocalizedText("Insigne y Nacional Basílica de Santa María de Guadalupe", "Basilica of Our Lady of Guadalupe", "Basílica de Nossa Senhora de Guadalupe"),
            historyAndApparition = LocalizedText(
                "La Madre de Dios se apareció al humilde indígena San Juan Diego Cuauhtlatoatzin en el cerro del Tepeyac. Pidió la construcción de un templo para mostrar a su amado Hijo. Como prueba ante el obispo Fray Juan de Zumárraga, la Virgen envió rosas de Castilla frescas que florecieron en pleno invierno. Al abrir Juan Diego su tilma de fibra de maguey, quedó milagrosamente plasmada la imagen viva de la Morenita del Tepeyac.",
                "The Blessed Virgin Mary appeared to Saint Juan Diego on Tepeyac Hill. She asked for a church to make known the compassion of Christ. As a celestial sign, she arranged out-of-season Castilian roses inside his tilma. When Juan Diego unfurled his cloak before Bishop Zumárraga, the miraculous image of Our Lady of Guadalupe was emblazoned upon the cactus-fiber fabric.",
                "Nossa Senhora apareceu ao indígena São Juan Diego no México, deixando impressa milagrosamente sua santa imagem no manto (tilma) de fibra vegetal, que permanece intacto até os dias de hoje."
            ),
            promises = LocalizedText(
                "«¿No estoy yo aquí, que tengo el honor de ser tu madre? ¿No estás bajo mi sombra y amparo? No temas ninguna enfermedad ni angustia».",
                "“Am I not here, who have the honor to be your Mother? Are you not under my shadow and protection? Do not let anything grieve you.”",
                "“Não estou eu aqui, que sou tua Mãe? Não estás sob o meu amparo? Nada temas.”"
            ),
            recordedMiracles = LocalizedText(
                "La conversión de más de 9 millones de indígenas en menos de una década, la conservación inexplicable de la tilma de ayate por más de 500 años y los descubrimientos oftalmológicos en los ojos de la imagen.",
                "Mass conversion of millions within years, perpetual preservation of fragile maguey fiber, and microscopic figures discovered reflected in her pupils.",
                "Conversão em massa do povo das Américas, preservação física inexplicável da fibra vegetal por cinco séculos e incontáveis milagres documentados."
            ),
            venerationPrayer = LocalizedText(
                "Santa María de Guadalupe, Mística Rosa de México y Emperatriz de América: intercede por nuestras familias, defiende la vida humana desde la concepción y guíanos siempre a tu Hijo Jesucristo. Amén.",
                "Holy Virgin of Guadalupe, Queen of the Americas: watch over all nations, defend the unborn, and lead our hearts to your divine Son. Amen.",
                "Santa Maria de Guadalupe, Mãe das Américas: protegei as nossas famílias, defendei os inocentes e fazei reinar a paz de Cristo em nossos lares. Amém."
            ),
            region = "Americas",
            country = LocalizedText("México / América Entera", "Mexico / The Americas", "México / Américas"),
            patronageOf = LocalizedText("Emperatriz de América, Patrona de México y Defensora de la Vida por nacer.", "Empress of the Americas, Patroness of the Unborn.", "Imperatriz das Américas e Protetora dos Nascituros.")
        ),
        MarianApparition(
            id = "caridad_del_cobre",
            title = LocalizedText("Nuestra Señora de la Caridad del Cobre (Cachita)", "Our Lady of Charity of El Cobre", "Nossa Senhora da Caridade do Cobre"),
            feastDay = LocalizedText("8 de Septiembre", "8 September", "8 de Setembro"),
            year = "1612",
            location = LocalizedText("Bahía de Nipe y El Cobre, Santiago de Cuba", "El Cobre, Santiago de Cuba", "El Cobre, Santiago de Cuba"),
            primarySanctuary = LocalizedText("Basílica Santuario Nacional de Nuestra Señora de la Caridad del Cobre", "National Shrine of Our Lady of Charity", "Basílica Santuário Nacional da Caridade do Cobre"),
            historyAndApparition = LocalizedText(
                "Hallada flotando en el mar en la bahía de Nipe en 1612 por 'los tres Juanes' (dos indígenas y un niño esclavo de origen africano) tras una tempestad. La imagen de madera dorada flotaba sobre una tabla con la inscripción: 'Yo soy la Virgen de la Caridad', completamente seca a pesar de las olas.",
                "Found floating in Nipe Bay in 1612 by three young salt-gatherers. The statue rested upon a wooden board engraved with 'I am the Virgin of Charity', with her garments dry amidst the waves.",
                "Encontrada em 1612 na Baía de Nipe por três humildes trabalhadores sobre uma tábua com a inscrição: 'Eu sou a Virgem da Caridade', inteiramente seca sobre o mar revolto."
            ),
            promises = LocalizedText(
                "Libertad del alma frente al pecado, reconciliación fraterna de las familias y consuelo en el destierro y el dolor.",
                "Freedom from tyranny and sin, domestic concord, and comforting shelter for the exile and the oppressed.",
                "Reconciliação dos corações, liberdade interior e fortaleza na provação."
            ),
            recordedMiracles = LocalizedText(
                "La imagen intacta y seca en medio del mar encrespado, curación de los mineros de cobre de graves derrumbes y la unión de todo un pueblo bajo la fe católica.",
                "The miraculous preservation dry in rough waters, preservation of copper miners during cave-ins, and reconciliation of exiles.",
                "Preservação inexplicável no mar revolto e inúmeras libertações espirituais e corporais."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen de la Caridad del Cobre, Madre amadísima!: mira con compasión a tus hijos, derrama tu dulzura en los corazones heridos, sana a nuestros enfermos y danos la libertad verdadera de los hijos de Dios. Amén.",
                "Virgin of Charity of El Cobre, beloved Mother: look with pity upon our afflictions, heal our sick, and preserve our faith undimmed. Amen.",
                "Virgem da Caridade do Cobre, Mãe bondosa: olhai com amor para o vosso povo e concedei-nos a paz de Vosso Divino Filho. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Cuba", "Cuba", "Cuba"),
            patronageOf = LocalizedText("Patrona excelsa de Cuba, de los mineros y protectora de las familias en la diáspora.", "Patroness of Cuba, copper miners, and exiles.", "Padroeira de Cuba e refúgio dos necessitados.")
        ),
        MarianApparition(
            id = "aparecida",
            title = LocalizedText("Nuestra Señora de la Concepción Aparecida", "Our Lady of Aparecida", "Nossa Senhora da Conceição Aparecida"),
            feastDay = LocalizedText("12 de Octubre", "12 October", "12 de Outubro"),
            year = "1717",
            location = LocalizedText("Río Paraíba do Sul, São Paulo, Brasil", "Paraíba River, São Paulo, Brazil", "Rio Paraíba do Sul, São Paulo, Brasil"),
            primarySanctuary = LocalizedText("Santuario Nacional de Nuestra Señora de la Concepción Aparecida", "National Shrine of Our Lady of Aparecida", "Santuário Nacional de Nossa Senhora Aparecida"),
            historyAndApparition = LocalizedText(
                "Tres humildes pescadores (Domingos, Felipe y João) que no habían pescado nada tras horas de faena, sacaron en sus redes primero el cuerpo y luego la cabeza de una pequeña estatua de terracota oscura de la Virgen Inmaculada. Al reunirla, sus barcas se llenaron milagrosamente de abundantes peces.",
                "Three poor fishermen, catching nothing in the Paraiba River, drew up in their nets the broken clay statue of the Immaculate Conception. Upon joining the head and body, their nets miraculously overflowed with fish.",
                "Três pescadores resgataram das águas do Rio Paraíba a imagem de terracota da Virgem Imaculada. Em seguida, ocorreu o milagre da pesca abundante. É a amada Padroeira do Brasil."
            ),
            promises = LocalizedText(
                "Protección maternal a los humildes, libertad a los oprimidos y bendición en el trabajo honesto.",
                "Maternal protection of the vulnerable, freedom of conscience, and blessings upon honest labor.",
                "Proteção aos trabalhadores, libertação das amarras do pecado e amparo aos necessitados."
            ),
            recordedMiracles = LocalizedText(
                "El milagro de las velas que se encendieron solas, la liberación del esclavo Zacarías cuando cayeron sus cadenas y el ciego que recobró la vista.",
                "The unassisted lighting of altar candles, the sudden breaking of chains from the slave Zacarias, and miraculous healings.",
                "O milagre das velas acesas sozinhas, o rompimento das correntes do escravo Zacarias e inúmeras graças de saúde."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen Aparecida, Reina y Patrona de los pueblos!, bendice nuestras naciones, fortalece nuestra fe católica y guarda a nuestros jóvenes en la pureza. Amén.",
                "O Lady Aparecida, blessed Mother of all souls: wrap our nations in your mantle of charity and peace. Amen.",
                "Querida Mãe Aparecida, abençoai o nosso povo, protegei as nossas famílias e dai-nos a graça de amar a Jesus com todo o nosso coração. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Brasil", "Brazil", "Brasil"),
            patronageOf = LocalizedText("Reina y Patrona principal de Brasil y amparo de los pescadores y trabajadores.", "Principal Patroness and Queen of Brazil.", "Rainha e Padroeira do Brasil.")
        ),
        MarianApparition(
            id = "coromoto",
            title = LocalizedText("Nuestra Señora de Coromoto", "Our Lady of Coromoto", "Nossa Senhora de Coromoto"),
            feastDay = LocalizedText("8 de Septiembre y 11 de Septiembre", "8 September and 11 September", "8 de Setembro e 11 de Setembro"),
            year = "1652",
            location = LocalizedText("Guanare, Estado Portuguesa, Venezuela", "Guanare, Venezuela", "Guanare, Venezuela"),
            primarySanctuary = LocalizedText("Basílica Menor Santuario Nacional de Nuestra Señora de Coromoto", "National Shrine of Our Lady of Coromoto", "Santuário Nacional de Nossa Senhora de Coromoto"),
            historyAndApparition = LocalizedText(
                "Se apareció al cacique Coromoto y su mujer de la tribu de los Cospes en 1652, diciéndoles en su lengua nativa: 'Vayan a casa de los blancos para que les echen agua en la cabeza y puedan ir al cielo'. El 8 de septiembre de 1652, la Señora reapareció en la choza del cacique y le dejó en su puño una diminuta reliquia milagrosa de pergamino con su imagen santa.",
                "Appeared to Chief Coromoto of the Cospes tribe in 1652, inviting them to be baptized in Christ. On September 8, 1652, she reappeared inside his hut and pressed into his clenched fist a microscopic parchment relic containing her sacred likeness.",
                "Apareceu ao cacique Coromoto exortando sua tribo a receber o Santo Batismo. Deixou milagrosamente em sua mão uma minúscula relíquia em pergaminho com sua sagrada efígie."
            ),
            promises = LocalizedText(
                "Gracia bautismal para todos los pueblos originarios y perseverancia en la verdadera fe ante la rebeldía del corazón.",
                "Baptismal regeneration for indigenous peoples and perseverance in divine truth.",
                "Graça da fé católica para todas as nações e renovação espiritual."
            ),
            recordedMiracles = LocalizedText(
                "La reliquia original de la estampa coromotana que no se degrada y cuya restauración científica microscópica en 2009 reveló el iris y los ojos de la Virgen con reflejos vivos.",
                "The unpainted, self-formed micro-relic preserved over centuries; forensic restoration in 2009 discovered anatomical eye details.",
                "A preservação da relíquia sagrada e a conversão de toda a tribo dos índios Cospes."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen de Coromoto, Patrona de Venezuela!: renueva la fe en nuestra patria, une a nuestras familias en el perdón y condúcenos por los senderos de la justicia y la paz. Amén.",
                "Virgin of Coromoto, Queen of Venezuela: renew the faith across our nations, heal divisions, and guide us in truth. Amen.",
                "Virgem de Coromoto, Padroeira da Venezuela: abençoai a nossa terra e fazei triunfar o amor de Cristo em nossos lares. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Venezuela", "Venezuela", "Venezuela"),
            patronageOf = LocalizedText("Patrona Principal de Venezuela y protectora de la evangelización de los pueblos indígenas.", "Patroness of Venezuela.", "Padroeira da Venezuela.")
        ),
        MarianApparition(
            id = "lujan",
            title = LocalizedText("Nuestra Señora de Luján", "Our Lady of Luján", "Nossa Senhora de Luján"),
            feastDay = LocalizedText("8 de Mayo", "8 May", "8 de Maio"),
            year = "1630",
            location = LocalizedText("Río Luján, Buenos Aires, Argentina", "Luján, Argentina", "Luján, Argentina"),
            primarySanctuary = LocalizedText("Basílica Nacional de Nuestra Señora de Luján", "National Basilica of Our Lady of Luján", "Basílica Nacional de Nossa Senhora de Luján"),
            historyAndApparition = LocalizedText(
                "Una caravana que transportaba dos pequeñas imágenes de barro cocido de la Virgen hacia Sumampa se detuvo en las orillas del río Luján. Los bueyes se negaron a avanzar a pesar de todos los intentos, hasta que bajaron el cajón que contenía la imagen de la Inmaculada Concepción. La Virgen eligió quedarse allí para siempre, custodiada por el fiel esclavo 'Negro Manuel'.",
                "A caravan transporting terracotta statues stopped along the Luján riverbank in 1630. The oxen refused to budge until the crate containing the image of the Immaculate Conception was unloaded. The Virgin chose to remain there, cared for by the faithful slave Manuel.",
                "Em 1630, a carreta que transportava a imagem da Virgem parou junto ao rio Luján e os bois se recusaram a andar até que a caixa com a imagem fosse retirada, indicando o desejo de Maria de ali permanecer."
            ),
            promises = LocalizedText(
                "Protección maternal a los peregrinos, fidelidad inquebrantable a la Iglesia y consuelo a los desamparados.",
                "Unfailing protection to pilgrims and preservation of Catholic unity across the Southern Cone.",
                "Amparo materno aos peregrinos e consolo aos aflitos."
            ),
            recordedMiracles = LocalizedText(
                "El milagro de las carretas detenidas por designio divino, sanaciones en el río y la incorrupción del humilde Manuel de Luján.",
                "The oxen immovability miracle, cures through the lamp oil, and miraculous rescues from military invasions.",
                "O milagre dos bois imóveis, curas pelo óleo da lâmpada da Virgem e acolhimento aos peregrinos."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen de Luján, Madre de la Esperanza!: mira a tus hijos que caminan en la noche, abre nuestros ojos a la luz de Cristo y enséñanos a amar como Tú amaste. Amén.",
                "Virgin of Luján, Mother of Hope: watch over our pilgrimage on earth and draw our souls closer to the Sacred Heart of Jesus. Amen.",
                "Nossa Senhora de Luján, Mãe de esperança e caridade: intercedei pelo povo do Prata e guardai a nossa fé católica. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Argentina / Uruguay / Paraguay", "Argentina / Uruguay / Paraguay", "Argentina / Uruguai"),
            patronageOf = LocalizedText("Patrona de Argentina, Uruguay y Paraguay, y de los transportistas terrestres.", "Patroness of Argentina, Uruguay, and Paraguay.", "Padroeira da Argentina e protetora dos viajantes.")
        ),
        MarianApparition(
            id = "chiquinquira",
            title = LocalizedText("Nuestra Señora del Rosario de Chiquinquirá (La Chinita)", "Our Lady of the Rosary of Chiquinquirá", "Nossa Senhora de Chiquinquirá"),
            feastDay = LocalizedText("9 de Julio y 18 de Noviembre", "9 July and 18 November", "9 de Julho e 18 de Novembro"),
            year = "1586",
            location = LocalizedText("Chiquinquirá, Boyacá, Colombia / Maracaibo, Venezuela", "Chiquinquirá, Colombia / Maracaibo", "Chiquinquirá, Colômbia"),
            primarySanctuary = LocalizedText("Basílica de Nuestra Señora del Rosario de Chiquinquirá (Colombia y Zulia)", "Basilica of Our Lady of the Rosary of Chiquinquirá", "Basílica de Nossa Senhora de Chiquinquirá"),
            historyAndApparition = LocalizedText(
                "Pintada sobre tela de algodón por Alonso de Narváez hacia 1560 con la Virgen en el centro y a los lados San Antonio de Padua y San Andrés. Con el tiempo se borró casi por completo y fue usada para secar trigo. El 26 de diciembre de 1586, la humilde mujer María Ramos oraba ante el lienzo cuando éste comenzó a emitir rayos luminosos y se renovó milagrosamente en vivos colores que perduran intactos.",
                "Painted on coarse cotton canvas around 1560, the painting decayed until the image was virtually invisible. On December 26, 1586, while the pious María Ramos knelt in prayer, the canvas suddenly erupted in brilliant light and restored itself in vivid radiant color.",
                "O quadro pintado em algodão estava quase apagado. Em 1586, diante das orações fervorosas de Maria Ramos, a imagem renovou-se milagrosamente com cores vivas e resplendor celeste."
            ),
            promises = LocalizedText(
                "Renovación espiritual del alma manchada por el pecado, paz en los hogares y el don de la oración perseverante.",
                "Spiritual restoration for weary souls, healing of division, and defense of the Rosary.",
                "Renovação da alma ferida pelo pecado e florescimento da paz."
            ),
            recordedMiracles = LocalizedText(
                "La milagrosa autorrenovación luminosa de la pintura que sigue desconcertando a la ciencia, y la cesación de la peste en 1587.",
                "The scientifically unexplainable self-restoration of pigments on cotton, and miraculous halting of epidemics.",
                "A restauração luminosa instantânea das cores e incontáveis curas atestadas ao longo dos séculos."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen de Chiquinquirá, Reina y Patrona!: así como renovaste milagrosamente tu lienzo apagado, renueva hoy mi corazón tibio con el fuego purificador del amor de Cristo. Amén.",
                "Our Lady of Chiquinquirá: as you radiantly restored your faded canvas, renew our dull hearts with the fervent fire of Christ's Gospel. Amen.",
                "Nossa Senhora de Chiquinquirá: renovai a nossa alma e fazei brilhar em nossas vidas a luz de Cristo Jesus. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Colombia / Venezuela (Zulia)", "Colombia / Venezuela", "Colômbia / Venezuela"),
            patronageOf = LocalizedText("Reina y Patrona de Colombia y de la región zuliana en Venezuela (La Chinita).", "Patroness and Queen of Colombia.", "Padroeira da Colômbia e amparo dos enfermos.")
        ),
        MarianApparition(
            id = "fatima",
            title = LocalizedText("Nuestra Señora del Rosario de Fátima", "Our Lady of the Rosary of Fatima", "Nossa Senhora de Fátima"),
            feastDay = LocalizedText("13 de Mayo", "13 May", "13 de Maio"),
            year = "1917",
            location = LocalizedText("Cova da Iria, Fátima, Portugal", "Cova da Iria, Fatima, Portugal", "Cova da Iria, Fátima, Portugal"),
            primarySanctuary = LocalizedText("Santuario de Nuestra Señora del Rosario de Fátima", "Sanctuary of Our Lady of Fatima", "Santuário de Fátima"),
            historyAndApparition = LocalizedText(
                "María Santísima se apareció seis veces a tres pastorcitos: Lucía dos Santos y los santos Francisco y Jacinta Marto. En plena Primera Guerra Mundial, la Virgen trajo un mensaje urgente de oración, penitencia, consagración al Inmaculado Corazón y el rezo diario del Santo Rosario para alcanzar la paz mundial. El 13 de octubre de 1917, más de 70.000 testigos presenciaron el asombroso 'Milagro del Sol'.",
                "Between May and October 1917, Our Lady appeared to three shepherd children: Lucia, Francisco, and Jacinta. She requested the daily Rosary for world peace and devotion to her Immaculate Heart. On October 13, 1917, over 70,000 people witnessed the famous 'Miracle of the Sun'.",
                "Nossa Senhora apareceu a três pastorinhos pedindo oração do terço todos os dias, penitência e conversão. Em 13 de outubro de 1917 realizou-se o grandioso Milagre do Sol diante de mais de 70 mil pessoas."
            ),
            promises = LocalizedText(
                "«Al final, mi Inmaculado Corazón triunfará. El Santo Padre me consagrará a Rusia, que se convertirá, y será concedido al mundo algún tiempo de paz».",
                "“In the end, my Immaculate Heart will triumph. Russia will be consecrated to me, will be converted, and a period of peace will be granted to the world.”",
                "“Por fim, o meu Imaculado Coração triunfará. O Santo Padre consagrará a Rússia e será concedido ao mundo algum tempo de paz.”"
            ),
            recordedMiracles = LocalizedText(
                "El Milagro del Sol visto por periodistas laicos y creyentes, la curación de miles de enfermos en la Cova da Iria y la salvación providencial de San Juan Pablo II del atentado del 13 de mayo de 1981.",
                "The solar phenomenon verified by secular newspapers, thousands of medical healings, and the survival of Pope St. John Paul II from assassination in 1981.",
                "O Milagre do Sol comprovado pela imprensa da época, curas físicas extraordinárias no Santuário e a salvação do Papa São João Paulo II."
            ),
            venerationPrayer = LocalizedText(
                "¡Oh Santísima Virgen del Rosario de Fátima!, haz que no nos cansemos nunca de rezar el Rosario por la paz del mundo y la conversión de los pecadores. Inmaculado Corazón de María, sé la salvación de mi alma. Amén.",
                "O Most Holy Virgin of the Rosary of Fatima, inspire us to pray the Rosary daily for the conversion of sinners and the peace of the world. Immaculate Heart of Mary, be our refuge. Amen.",
                "Ó Doce Coração de Maria, sede a nossa salvação! Ensinai-nos a rezar o Santo Terço todos os dias com amor e devoção. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Portugal / Universal", "Portugal / Universal", "Portugal / Universal"),
            patronageOf = LocalizedText("Reina de la Paz, protectora de los perseguidos por la fe y Patrona de Portugal.", "Queen of the Rosary and Peace.", "Padroeira da Paz e do Santo Rosário.")
        ),
        MarianApparition(
            id = "lourdes",
            title = LocalizedText("Nuestra Señora de Lourdes", "Our Lady of Lourdes", "Nossa Senhora de Lourdes"),
            feastDay = LocalizedText("11 de Febrero", "11 February", "11 de Fevereiro"),
            year = "1858",
            location = LocalizedText("Gruta de Massabielle, Lourdes, Francia", "Grotto of Massabielle, Lourdes, France", "Gruta de Massabielle, Lourdes, França"),
            primarySanctuary = LocalizedText("Santuario de Nuestra Señora de Lourdes", "Sanctuary of Our Lady of Lourdes", "Santuário de Nossa Senhora de Lourdes"),
            historyAndApparition = LocalizedText(
                "La Virgen María se apareció en 18 ocasiones a la humilde campesina Santa Bernardita Soubirous. Vestida de blanco con una cinta azul y una rosa dorada en cada pie, la Señora confirmó el dogma recién proclamado por el Beato Pío IX diciendo en dialecto occitano: 'Yo soy la Inmaculada Concepción'. Hizo brotar una fuente milagrosa que continúa manando agua sanadora.",
                "Our Lady appeared 18 times to 14-year-old Bernadette Soubirous at the grotto of Massabielle. Revealing her identity as 'The Immaculate Conception', she unsealed a miraculous spring of water that has been the instrument of thousands of medically documented cures.",
                "Nossa Senhora apareceu a Santa Bernadete na gruta de Massabielle, revelando: 'Eu sou a Imaculada Conceição'. Fez jorrar uma fonte de água onde ocorrem milagres de cura física e espiritual até hoje."
            ),
            promises = LocalizedText(
                "«No te prometo hacerte feliz en este mundo, sino en el otro». Promesa de sanación física y consuelo a los enfermos.",
                "“I do not promise to make you happy in this life, but in the next.” Comfort for all suffering in body and spirit.",
                "Consolo imenso aos enfermos e a promessa de vida eterna aos que perseverarem na fé."
            ),
            recordedMiracles = LocalizedText(
                "Más de 70 curaciones declaradas milagrosas e inexplicables por el riguroso Comité Médico Internacional de Lourdes (CMIL).",
                "Over 70 medically verified, scientifically inexplicable physical cures certified by the International Medical Bureau of Lourdes.",
                "Mais de 70 milagres de curas físicas comprovados cientificamente pelo rigoroso Comitê Médico de Lourdes."
            ),
            venerationPrayer = LocalizedText(
                "Nuestra Señora de Lourdes, consuelo de los afligidos y salud de los enfermos: toca con tu amor maternal a quienes hoy sufren en el cuerpo o en el espíritu. Amén.",
                "Our Lady of Lourdes, Health of the Sick and Comforter of the Afflicted: intercede for all burdened with disease, that they may experience Christ's restorative grace. Amen.",
                "Nossa Senhora de Lourdes, Saúde dos Enfermos e Consoladora dos Aflitos: rogai por nós e dai alívio aos nossos irmãos que sofrem nos hospitais e lares. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Francia / Universal", "France / Universal", "França / Universal"),
            patronageOf = LocalizedText("Salud de los Enfermos y patrona universal de los hospitales y cuidadores.", "Patroness of the Sick and healthcare workers.", "Saúde dos Enfermos e protetora dos hospitais.")
        ),
        MarianApparition(
            id = "virgen_del_pilar",
            title = LocalizedText("Nuestra Señora del Pilar", "Our Lady of the Pillar", "Nossa Senhora do Pilar"),
            feastDay = LocalizedText("12 de Octubre", "12 October", "12 de Outubro"),
            year = "40 d.C.",
            location = LocalizedText("Zaragoza, España", "Zaragoza, Spain", "Saragoça, Espanha"),
            primarySanctuary = LocalizedText("Catedral-Basílica de Nuestra Señora del Pilar", "Cathedral-Basilica of Our Lady of the Pillar", "Catedral-Basílica de Nossa Senhora do Pilar"),
            historyAndApparition = LocalizedText(
                "Considerada la primera aparición mariana de la historia. Ocurrió en carne mortal (antes de su Asunción): la Virgen vino en cuerpo y alma a las orillas del río Ebro para confortar al Apóstol Santiago el Mayor, que se hallaba desalentado en su misión en Hispania. Le entregó una columna de jaspe diciendo: 'Este pilar permanecerá aquí hasta el fin del mundo'.",
                "The earliest recorded Marian apparition in Christian history, occurring while Mary was still living on earth. She appeared atop a jasper pillar along the Ebro River to encourage Saint James the Apostle in his preaching.",
                "A mais antiga aparição mariana, ocorrida ainda durante a vida terrena da Virgem. Maria apareceu sobre um pilar de jaspe junto ao rio Ebro para consolar o Apóstolo São Tiago Maior."
            ),
            promises = LocalizedText(
                "Permanencia inconmovible de la fe católica en los pueblos de lengua hispana hasta el fin de los tiempos.",
                "Unwavering endurance of the Catholic faith among Spanish-speaking nations until the consummation of the world.",
                "Perseverança inabalável na fé cristã e amparo apostólico."
            ),
            recordedMiracles = LocalizedText(
                "El milagro de Calanda en 1640 (la restitución física instantánea de la pierna amputada a Miguel Pellicer), y la caída de tres bombas durante la Guerra Civil española sobre la basílica sin que ninguna explotara.",
                "The Miracle of Calanda in 1640 (re-creation of an amputated leg), and unexploded bombs piercing the basilica roof during the 1936 war.",
                "O milagre de Calanda (restituição de uma perna amputada) e as três bombas de guerra que caíram sobre a basílica sem detonar."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen del Pilar, columna firme de nuestra fe!: ruega por la Iglesia católica, guarda a nuestras naciones en la unidad de Cristo y sé nuestro amparo en el combate espiritual. Amén.",
                "Our Lady of the Pillar, steadfast rock of our faith: guard our nations, preserve the unity of the Church, and strengthen us in our apostolic labors. Amen.",
                "Nossa Senhora do Pilar, coluna viva de nossa fé: guardai as nossas famílias e dai-nos perseverança até o fim. Amém."
            ),
            region = "Europe",
            country = LocalizedText("España / Hispanidad", "Spain / Hispanic World", "Espanha / Hispanidade"),
            patronageOf = LocalizedText("Reina de la Hispanidad y Patrona de Zaragoza y de la Guardia Civil.", "Patroness of the Hispanic Peoples.", "Padroeira dos povos de língua espanhola.")
        ),
        MarianApparition(
            id = "medalla_milagrosa",
            title = LocalizedText("Nuestra Señora de la Medalla Milagrosa", "Our Lady of the Miraculous Medal", "Nossa Senhora das Graças da Medalha Milagrosa"),
            feastDay = LocalizedText("27 de Noviembre", "27 November", "27 de Novembro"),
            year = "1830",
            location = LocalizedText("Rue du Bac, París, Francia", "Rue du Bac, Paris, France", "Rue du Bac, Paris, França"),
            primarySanctuary = LocalizedText("Capilla de Nuestra Señora de la Medalla Milagrosa (París)", "Chapel of Our Lady of the Miraculous Medal", "Capela da Medalha Milagrosa em Paris"),
            historyAndApparition = LocalizedText(
                "La Virgen María se manifestó a la novicia Santa Catalina Labouré de las Hijas de la Caridad. Mostró sus manos derramando rayos resplandecientes sobre el globo terráqueo, simbolizando las gracias concedidas a quienes las piden. Ordenó acuñar una medalla con la inscripción: '¡Oh María, sin pecado concebida, ruega por nosotros que recurrimos a ti!'.",
                "Our Lady appeared to Saint Catherine Labouré in the chapel of the Daughters of Charity. Radiating beams of celestial light from her fingertips, she instructed the casting of the Medal with the prayer: 'O Mary, conceived without sin, pray for us who have recourse to thee.'",
                "Apareceu a Santa Catarina Labouré com as mãos estendidas derramando raios de graças. Ensinou a confecção da Medalha Milagrosa prometendo abundantes bênçãos a quem a portar com devoção."
            ),
            promises = LocalizedText(
                "«Todas las personas que lleven la Medalla bendecida al cuello con confianza recibirán grandes gracias; abundantes serán las gracias para quienes la porten con fe».",
                "“All who wear this medal blessed around their neck will receive great graces; abundantly will graces be lavished upon those who wear it with confidence.”",
                "“Todas as pessoas que usarem a medalha com fé receberão grandes graças e especial proteção maternal.”"
            ),
            recordedMiracles = LocalizedText(
                "La fulminante conversión del célebre ateo judío Alphonse Ratisbonne en Roma en 1842, curaciones durante la epidemia de cólera en París y millones de conversiones documentadas.",
                "The instant conversion of Alphonse Ratisbonne in Rome, halts to cholera outbreaks in Paris, and global miracles.",
                "A conversão instantânea de Alphonse Ratisbonne e inúmeras curas durante a peste de cólera em Paris."
            ),
            venerationPrayer = LocalizedText(
                "¡Oh María, sin pecado concebida!: derrama los rayos de tu misericordia sobre nuestras almas y ruega por nosotros que recurrimos a ti. Amén.",
                "O Mary, conceived without sin: pray for us who have recourse to thee. Pour the rays of Christ's grace upon our hearts. Amen.",
                "Ó Maria concebida sem pecado: rogai por nós que recorremos a vós. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Francia / Universal", "France / Universal", "França / Universal"),
            patronageOf = LocalizedText("Dispensadora universal de las gracias divinas y amparo de los enfermos y conversos.", "Mediatrix of Graces and haven for converts.", "Dispensadora das graças celestes e refúgio dos aflitos.")
        ),
        MarianApparition(
            id = "rosa_mistica",
            title = LocalizedText("María Rosa Mística (María, Rosa Mística)", "Mary Mystical Rose", "Maria Rosa Mística"),
            feastDay = LocalizedText("13 de Julio", "13 July", "13 de Julho"),
            year = "1947",
            location = LocalizedText("Montichiello, Brescia, Italia", "Montichiello, Brescia, Italy", "Montichiello, Itália"),
            primarySanctuary = LocalizedText("Santuario de Rosa Mística (Fontanelle, Brescia)", "Shrine of Rosa Mystica (Fontanelle, Brescia)", "Santuário Rosa Mística em Fontanelle"),
            historyAndApparition = LocalizedText(
                "La Virgen se apareció desde 1947 a la enfermera Pierina Gilli en el hospital de Montichiello vestida de blanco con tres rosas (blanca, roja y dorada). Pidió oración, penitencia y reparación ante Jesús por el abandono de tabernáculos y la angustia de los sacerdotes.",
                "Mary appeared from 1947 to nurse Pierina Gilli in Montichiello, dressed in white with three roses (white, red and golden), asking prayer and reparation for the abandoned tabernacles of the world.",
                "A Virgem apareceu a partir de 1947 à enfermeira Pierina Gilli pedindo oração, penitência e reparação pela indiferença diante do Santíssimo Sacramento."
            ),
            promises = LocalizedText(
                "Conversión de sacerdotes y religiosos, auxilio a los enfermos y consuelo a las almas en horas de prueba.",
                "Conversion of priests, aid to the sick, and consolation in times of trial.",
                "Conversão de sacerdotes, amparo aos enfermos e consolo nas provações."
            ),
            recordedMiracles = LocalizedText(
                "Numerosos enfermos curados tras la oración en Fontanelle, conversiones atestiguadas y la expansión de la devoción en todo el mundo.",
                "Many sick healed after prayer at Fontanelle, with documented conversions.",
                "Muitas curas e conversões atestadas após orações no santuário."
            ),
            venerationPrayer = LocalizedText(
                "María, Rosa Mística, consigue para nosotros la gracia de reparar con amor los ultrajes contra Jesús y de vivir en santa humildad. Amén.",
                "Mary, Mystical Rose, obtain for us the grace to repair with love the offenses against Jesus and to live in holy humility. Amen.",
                "Maria, Rosa Mística, alcançai-nos a graça de reparar com amor os ultrajes contra Jesus. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Italia / Universal", "Italy / Universal", "Itália / Universal"),
            patronageOf = LocalizedText("Auxilio de los enfermos y reparación eucarística.", "Help of the sick and Eucharistic reparation.", "Auxílio dos enfermos e reparação eucarística.")
        ),
        MarianApparition(
            id = "salus_populi_romani",
            title = LocalizedText("Nuestra Señora Salus Populi Romani (Salvación del Pueblo Romano)", "Our Lady Salus Populi Romani", "Nossa Senhora Salus Populi Romani"),
            feastDay = LocalizedText("Domingo después de la Asunción", "Sunday after the Assumption", "Domingo após a Assunção"),
            year = "Siglo IX (tradición)",
            location = LocalizedText("Capilla Paulina, Basílica de Santa María la Mayor, Roma", "Borghese Chapel, St. Mary Major, Rome", "Capela Borghese, Basílica Santa Maria Maior, Roma"),
            primarySanctuary = LocalizedText("Basílica de Santa María la Mayor (Roma)", "Basilica of St. Mary Major, Rome", "Basílica de Santa Maria Maior em Roma"),
            historyAndApparition = LocalizedText(
                "Icono bizantino según la tradición pintado por San Lucas. En 590 el Papa San Gregorio Magno lo llevó en procesión durante la peste en Roma. Es la advocación protectora de la ciudad; el Papa Francisco la veneraba con especial devoción y la visita antes y después de cada viaje apostólico.",
                "Byzantine icon traditionally attributed to St. Luke. In 590 St. Gregory the Great carried it in procession during a plague in Rome. It is the protector of the city; Pope Francis visited it before and after every apostolic journey.",
                "Ícone bizantino do século IX venerado em Roma. Em 590 foi levado em procissão durante a peste e continua sendo a proteção de Roma e dos viajantes."
            ),
            promises = LocalizedText(
                "Protección de la ciudad, auxilio en epidemias y guía espiritual en los viajes y decisiones del Pontífice.",
                "Protection of the city, aid in epidemics, and guidance on journeys.",
                "Proteção da cidade, auxílio nas epidemias e proteção dos viajantes."
            ),
            recordedMiracles = LocalizedText(
                "El cese de la peste en Roma tras la procesión de 590 y múltiples indulgencias concedidas a su devoción.",
                "The abatement of the plague in Rome after the 590 procession.",
                "O fim da peste em Roma após a procissão de 590."
            ),
            venerationPrayer = LocalizedText(
                "¡Salvación del pueblo romano, Virgen santísima!: protege a tu ciudad y a todos los viajeros, y guárdanos siempre bajo tu manto. Amén.",
                "Salvation of the Roman people, Most Holy Virgin: protect your city and all travelers, keeping us always under your mantle. Amen.",
                "Salvação do povo romano, Virgem Santíssima: protegei a vossa cidade e todos os viajantes. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Italia / Universal", "Italy / Universal", "Itália / Universal"),
            patronageOf = LocalizedText("Protectora de Roma y de los viajeros.", "Protector of Rome and travelers.", "Protetora de Roma e dos viajantes.")
        ),
        MarianApparition(
            id = "virgen_dolores",
            title = LocalizedText("Nuestra Señora de los Dolores (Piedad)", "Our Lady of Sorrows", "Nossa Senhora das Dores"),
            feastDay = LocalizedText("15 de Septiembre", "15 September", "15 de Setembro"),
            year = "Tradición medieval",
            location = LocalizedText("Universal / Florencia, Italia", "Universal / Florence, Italy", "Universal / Florença, Itália"),
            primarySanctuary = LocalizedText("Basílica de la Santísima Anunciada (Florencia)", "Basilica of the Most Holy Annunciation, Florence", "Basílica da Santíssima Anunciação em Florença"),
            historyAndApparition = LocalizedText(
                "Contempla a María al pie de la Cruz, con el corazón traspasado por siete espadas en memoria de sus dolores: la profecía de Simeón, la huida a Egipto, la pérdida de Jesús, el camino al Calvario, la crucifixión, la deposición y la sepultura.",
                "Mary at the foot of the Cross, her heart pierced by seven swords recalling her sorrows: the prophecy of Simeon, the flight into Egypt, the loss of Jesus, the way to Calvary, the crucifixion, the deposition and burial.",
                "Contempla Maria ao pé da Cruz, com o coração transpassado por sete espadas em memória de suas dores na vida de Jesus."
            ),
            promises = LocalizedText(
                "Consuelo en el sufrimiento, conversión de los pecadores y perseverancia en la fe junto a la Cruz.",
                "Consolation in suffering, conversion of sinners, and perseverance beside the Cross.",
                "Consolo no sofrimento, conversão dos pecadores e perseverança na fé."
            ),
            recordedMiracles = LocalizedText(
                "Innumrables gracias a los devotos del Rosario de los Dolores y la conversión de corazones endurecidos.",
                "Countless graces through the Rosary of Sorrows and conversions of hardened hearts.",
                "Inúmeras graças alcançadas pela devoção à Nossa Senhora das Dores."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de los Dolores, que estuviste al pie de la Cruz: concédenos el don de compadecernos de los que sufren y de unir nuestros dolores a los de tu Hijo. Amén.",
                "Our Lady of Sorrows, who stood beside the Cross: grant us compassion for the suffering and union of our sorrows with those of your Son. Amen.",
                "Mãe Dolorosa: dai-nos compaixão pelos que sofrem e união de nossas dores à Cruz de vosso Filho. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            patronageOf = LocalizedText("Consuelo de los afligidos y conversión de pecadores.", "Consolation of the afflicted and conversion of sinners.", "Consolação dos aflitos e conversão dos pecadores.")
        ),
        MarianApparition(
            id = "inmaculada_concepcion",
            title = LocalizedText("Nuestra Señora de la Inmaculada Concepción", "Our Lady of the Immaculate Conception", "Nossa Senhora da Imaculada Conceição"),
            feastDay = LocalizedText("8 de Diciembre", "8 December", "8 de Dezembro"),
            year = "1854 (dogma)",
            location = LocalizedText("Universal / Roma, Italia", "Universal / Rome, Italy", "Universal / Roma, Itália"),
            primarySanctuary = LocalizedText("Basílica de la Inmaculada Concepción (Roma)", "Basilica of the Immaculate Conception, Rome", "Basílica da Imaculada Conceição em Roma"),
            historyAndApparition = LocalizedText(
                "Celebra a María concebida sin pecado original por singular gracia de Dios. El Papa Pío IX proclamó el dogma en 1854 (Ineffabilis Deus); en Lourdes (1858) la Virgen confirmó: 'Yo soy la Inmaculada Concepción'.",
                "Mary conceived without original sin by a singular grace of God. Proclaimed dogma by Pius IX in 1854; at Lourdes in 1858 she confirmed: 'I am the Immaculate Conception.'",
                "Celebra Maria concebida sem pecado original. Dogma proclamado em 1854 e confirmado pela Virgem em Lourdes: 'Eu sou a Imaculada Conceição'."
            ),
            promises = LocalizedText(
                "Pureza de corazón, alegría al confesarse y protección contra el pecado en la hora de la tentación.",
                "Purity of heart, joy in confession, and protection against sin in temptation.",
                "Pureza de coração, alegria na confissão e proteção contra o pecado."
            ),
            recordedMiracles = LocalizedText(
                "La medalla milagrosa de 1830 y las curaciones de Lourdes unidas a su advocación.",
                "The Miraculous Medal of 1830 and the healings of Lourdes tied to her advocation.",
                "A Medalha Milagrosa de 1830 e as curas de Lourdes ligadas à sua invocação."
            ),
            venerationPrayer = LocalizedText(
                "¡Oh María, sin pecado concebida!: rugado por nosotros que recurrimos a ti. Refugio de pecadores, sálvanos. Amén.",
                "O Mary, conceived without sin: pray for us who have recourse to you. Refuge of sinners, save us. Amen.",
                "Ó Maria concebida sem pecado: rogai por nós que recorremos a vós. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            patronageOf = LocalizedText("Patrona de las naciones de lengua española y de los pecadores.", "Patroness of Spanish-speaking nations and sinners.", "Padroeira das nações de língua espanhola e dos pecadores.")
        ),
        MarianApparition(
            id = "asuncion",
            title = LocalizedText("Nuestra Señora de la Asunción (Dormición y Asunción)", "Our Lady of the Assumption", "Nossa Senhora da Assunção"),
            feastDay = LocalizedText("15 de Agosto", "15 August", "15 de Agosto"),
            year = "1950 (dogma)",
            location = LocalizedText("Universal / Jerusalén y Roma", "Universal / Jerusalem and Rome", "Universal / Jerusalém e Roma"),
            primarySanctuary = LocalizedText("Basílica de la Dormición (Jerusalén) y Basílica de Santa María la Mayor (Roma)", "Dormition Abbey, Jerusalem & St. Mary Major, Rome", "Abadia da Dormição em Jerusalém e Basílica Santa Maria Maior em Roma"),
            historyAndApparition = LocalizedText(
                "La Iglesia celebra que María, al término de su vida terrena, fue elevada en cuerpo y alma a la gloria de Dios. Pío XII proclamó el dogma en 1950 (Munificentissimus Deus); la Dormición se guarda en Jerusalén.",
                "Mary, at the end of her earthly life, was taken body and soul into the glory of God. Proclaimed dogma by Pius XII in 1950; tradition places her Dormition in Jerusalem.",
                "A Igreja celebra que Maria foi elevada em corpo e alma à glória de Deus. Dogma proclamado em 1950."
            ),
            promises = LocalizedText(
                "Esperanza en la resurrección, buena muerte y certeza de la vida eterna junto a Cristo.",
                "Hope in the resurrection, holy death, and certainty of eternal life in Christ.",
                "Esperança na ressurreição e na vida eterna junto a Cristo."
            ),
            recordedMiracles = LocalizedText(
                "Veneración milenaria en la tumba de la Dormición y fiesta universal de Asunción en todos los continentes.",
                "Millennial veneration at the Dormition tomb and the universal Assumption feast.",
                "Veneração milenar no sepulcro da Dormição e festa universal da Assunção."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen Asunta al Cielo, Reina de los ángeles!: intercede por nosotros para que, purificados en esta vida, merezcamos un día acompañarte en la gloria. Amén.",
                "Virgin assumed into Heaven, Queen of angels: intercede for us so that, purified in this life, we may one day share your glory. Amen.",
                "Virgem Assunta aos céus, Rainha dos anjos: intercedei por nós nesta vida terrena. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            patronageOf = LocalizedText("Patrona de la buena muerte y de los moribundos.", "Patroness of holy death and the dying.", "Padroeira da boa morte e dos moribundos.")
        ),
        MarianApparition(
            id = "estrella_del_mar",
            title = LocalizedText("Nuestra Señora Estrella del Mar (Stella Maris)", "Our Lady Star of the Sea", "Nossa Senhora Estrela do Mar"),
            feastDay = LocalizedText("Último domingo de Septiembre", "Last Sunday of September", "Último domingo de setembro"),
            year = "Tradición medieval",
            location = LocalizedText("Universal / puertos y costas", "Universal / ports and coasts", "Universal / portos e costas"),
            primarySanctuary = LocalizedText("Santuarios portuarios de Stella Maris", "Harbor shrines of Stella Maris", "Santuários portuários de Stella Maris"),
            historyAndApparition = LocalizedText(
                "Ancient title from the Fathers: as the polestar guided sailors at night, Mary guides souls through the darkness of earth toward Christ, harbor of salvation. Carmen songs invoke her as Stella Maris.",
                "Ancient title: as the evening star guided sailors home, Mary guides souls through the darkness of life toward Christ. The Carmelite hymn Flos Carmeli salutes her as Stella Maris.",
                "Título antigo: como a estrela guiava os navegantes, Maria guia as almas através das trevas da vida até Cristo."
            ),
            promises = LocalizedText(
                "Protección en la navegación, socorro en tempestades y puerto seguro en la hora de la muerte.",
                "Protection at sea, help in storms, and a safe harbor in the hour of death.",
                "Proteção no mar, socorro nas tempestades e porto seguro na hora da morte."
            ),
            recordedMiracles = LocalizedText(
                "Navegantes librados de náufragios tras su invocación; campanas y faros de Stella Maris en todos los puertos.",
                "Sailors rescued from wrecks after invoking her; Stella Maris shrines in ports worldwide.",
                "Marinheiros livrados de naufrágios após invocá-la."
            ),
            venerationPrayer = LocalizedText(
                "Estrella del Mar, Madre de Dios, en este valle de lágrimas entona tu canto: salvación y guía a navegación. Amén.",
                "Star of the ocean, Mother of God: through this vale of tears lead us safely home. Amen.",
                "Estrela do Mar, Mãe de Deus: guia-nos com segurança pela noite da vida. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal / Chile / España", "Universal", "Universal"),
            patronageOf = LocalizedText("Patrona de marineros, pescadores y puertos.", "Patroness of sailors, fishermen, and ports.", "Padroeira dos marinheiros e pescadores.")
        ),
        MarianApparition(
            id = "rosario_lepanto",
            title = LocalizedText("Nuestra Señora del Rosario (Lepanto / Victoria)", "Our Lady of the Rosary (Lepanto/Victory)", "Nossa Senhora do Rosário (Lepanto)"),
            feastDay = LocalizedText("7 de Octubre", "7 October", "7 de Outubro"),
            year = "1571",
            location = LocalizedText("Golfo de Lepanto, Grecia", "Gulf of Lepanto, Greece", "Golfo de Lepanto, Grécia"),
            primarySanctuary = LocalizedText("Basílica de Santa María sobre Minerva (Roma)", "St. Mary over Minerva, Rome", "Santa Maria sobre Minerva em Roma"),
            historyAndApparition = LocalizedText(
                "El 7 de octubre de 1571 la flota cristiana venció en Lepanto a la flota otomana invocarndo el auxilio de María. San Pío V instituyó la fiesta; los dominicos propagaron universalmente el rezo del Santo Rosario.",
                "On 7 October 1571 the Christian fleet defeated the Ottoman navy at Lepanto invoking Mary's aid. St. Pius V instituted the feast; Dominicans spread the Rosary worldwide.",
                "Em 7 de outubro de 1571 a frota cristã venceu em Lepanto invocando Maria. São Pio V instituiu a festa e os dominicanos difundiram o Santo Rosário."
            ),
            promises = LocalizedText(
                "«Cien mil avemarías han ganado la batalla». Victoria sobre el mal por el Rosario: paz, conversión y protección de los hogares.",
                "The Rosary's victories: peace, conversion, and protection of homes.",
                "Vitórias mediante o Santo Rosário: paz, conversão e proteção das famílias."
            ),
            recordedMiracles = LocalizedText(
                "La victoria naval de Lepanto, salvación de provincias y ciudades por el Rosario en los siglos siguientes.",
                "The naval victory at Lepanto and the Rosary's protection of cities and provinces thereafter.",
                "A vitória de Lepanto e a proteção de cidades e nações pelo Santo Rosário."
            ),
            venerationPrayer = LocalizedText(
                "Reina del Santo Rosario, auxilio de los cristianos: apiádate de nosotros, alcance la paz y concédenos la perseverancia. Amén.",
                "Queen of the Holy Rosary, help of Christians: obtain peace for us and perseverance in prayer. Amen.",
                "Rainha do Santo Rosário, auxílio dos cristãos: alcançai-nos a paz e a perseverança. Amém."
            ),
            region = "Europe",
            country = LocalizedText("Universal", "Universal", "Universal"),
            patronageOf = LocalizedText("Patrona del Rosario y Victory over adversaries of the faith.", "Patroness of the Rosary.", "Padroeira do Santo Rosário.")
        ),
        MarianApparition(
            id = "candelaria",
            title = LocalizedText("Nuestra Señora de la Candelaria", "Our Lady of Candelaria", "Nossa Senhora da Candelária"),
            feastDay = LocalizedText("2 de Febrero", "2 February", "2 de Fevereiro"),
            year = "1392 (Canarias)",
            location = LocalizedText("Candelaria, Tenerife, España", "Candelaria, Tenerife, Spain", "Candelaria, Tenerife, Espanha"),
            primarySanctuary = LocalizedText("Basílica de Nuestra Señora de la Candelaria (Tenerife)", "Basilica of Candelaria, Tenerife", "Basílica da Candelária em Tenerife"),
            historyAndApparition = LocalizedText(
                "Imagen hallada en 1392 por dos pescadores guanches en una playa de Tenerife; resguardada en una cueva y luego llevada a la iglesia. La advocación de la Presentación del Señor (Candelaria) se extendió a toda América Latina, especialmente a Bolivia.",
                "Image found in 1392 by Guanches on a Tenerife beach, enshrined in a cave then church. The Presentation advocation spread across Latin America, above all to Bolivia.",
                "Imagem encontrada em 1392 em Tenerife. A devoção da Candelária se espalhou por toda a América Latina, sobretudo na Bolívia."
            ),
            promises = LocalizedText(
                "Luz para caminar en verdad, protección de los pueblos originarios y de los peregrinos.",
                "Light to walk in truth, protection of indigenous peoples and pilgrims.",
                "Luz para caminhar na verdade e proteção dos fiéis."
            ),
            recordedMiracles = LocalizedText(
                "Prodigios de la versión tinerfeña y fiestas patronales multitudinarias en Copacabana y en el Altiplano.",
                "Miracles of the Tenerife image and massive patronal feasts across the Andes.",
                "Milagres da imagem de Tenerife e festas patronais nos Andes."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de la Candelaria, luz que disipa las sombras: alumbra nuestros caminos, aviva la fe y llévanos a Cristo. Amén.",
                "Our Lady of Candelaria, light of the world: illumine our paths and lead us to Christ. Amen.",
                "Nossa Senhora da Candelária, luz que dissipa as trevas: iluminai os nossos caminhos. Amém."
            ),
            region = "Americas",
            country = LocalizedText("España / Bolivia / América Latina", "Spain / Bolivia / Latin America", "Espanha / Bolívia / América Latina"),
            patronageOf = LocalizedText("Patrona de las Canarias y de los pueblos del Altiplano.", "Patroness of the Canaries and Andean peoples.", "Padroeira das Canárias e dos povos andinos.")
        ),
        MarianApparition(
            id = "mercedes",
            title = LocalizedText("Nuestra Señora de la Merced (Redentora de Cautivos)", "Our Lady of Mercy (Redeemer of Captives)", "Nossa Senhora da Merced (Resgate dos Cativos)"),
            feastDay = LocalizedText("24 de Septiembre", "24 September", "24 de Setembro"),
            year = "1218",
            location = LocalizedText("Barcelona, España", "Barcelona, Spain", "Barcelona, Espanha"),
            primarySanctuary = LocalizedText("Basílica de la Merced (Barcelona)", "Basilica of La Merced, Barcelona", "Basílica da Merced em Barcelona"),
            historyAndApparition = LocalizedText(
                "La Virgen se apareció a San Pedro Nolasco, San Raimundo de Peñafort y el rey Jaime I de Aragón entregándole el hábito blanco y las llaves para redimir cautivos. Fundó la Orden de los Mercedarios dedicada a rescatar prisioneros.",
                "Mary appeared to St. Peter Nolasco, St. Raymond of Penafort and King James I handing over the white habit to ransom captives. She founded the Mercedarian order devoted to freeing prisoners.",
                "A Virgem entregou a São Pedro Nolasco o hábito branco para fundar a Ordem de Nossa Senhora da Merced dedicada ao resgate dos cativos."
            ),
            promises = LocalizedText(
                "Redención de las cautividades del alma, libertad de conciencia y consuelo a presos y perseguidos.",
                "Ransom from spiritual captivity, freedom of conscience, and consolation of prisoners.",
                "Libertação das prisões do pecado e consolo aos perseguidos."
            ),
            recordedMiracles = LocalizedText(
                "Cadenas que se abren solas ante su imagen y liberación de cautivos documentada por los Mercedarios.",
                "Chains that opened by themselves before her image and prisoners ransomed by the Mercedarians.",
                "Calabouços que se abriram ante sua imagem e libertações documentadas."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de la Merced, Madre y Redentora: rompe las cadenas de nuestros pecados y líbranos del cautiverio del mal. Amén.",
                "Our Lady of Mercy, Mother and Redeemer: break the chains of our sins and free us from evil. Amen.",
                "Nossa Senhora da Merced, Mãe e Redentora: quebrai as correntes de nossos pecados. Amém."
            ),
            region = "Americas",
            country = LocalizedText("España / América Latina (Chile, Perú)", "Spain / Latin America", "Espanha / América Latina"),
            patronageOf = LocalizedText("Patrona de los cautivos, presos y de la Orden Mercedaria.", "Patroness of captives, prisoners, and the Mercedarians.", "Padroeira dos cativos e da Ordem da Merced.")
        ),
        MarianApparition(
            id = "caacupe",
            title = LocalizedText("Nuestra Señora de los Milagros de Caacupé", "Our Lady of Caacupé", "Nossa Senhora de Caacupé"),
            feastDay = LocalizedText("8 de Diciembre", "8 December", "8 de Dezembro"),
            year = "1760",
            location = LocalizedText("Caacupé, Cordillera, Paraguay", "Caacupé, Paraguay", "Caacupé, Paraguai"),
            primarySanctuary = LocalizedText("Catedral-Basílica de Caacupé", "Cathedral-Basilica of Caacupé", "Catedral-Basílica de Caacupé"),
            historyAndApparition = LocalizedText(
                "El tallador José Félix Zarza talló en madera dos imágenes: una grande para la ciudad y una pequeña para el río. Tras una inundación, la pequeña indígena 'yvyra' fue hallada intacta y condujo al santuario de Caacupé, patrona de Paraguay.",
                "Carver José Félix Zarza carved two images; after a flood the small one was found intact and set at Caacupé, becoming patroness of Paraguay.",
                "A imagem de Caacupé foi encontrada intacta após uma inundação e se tornou a Padroeira do Paraguai."
            ),
            promises = LocalizedText(
                "Protección de los labradores, salud de los enfermos y amparo de las familias paraguayas.",
                "Protection of farmers, health of the sick, and shelter of Paraguayan families.",
                "Proteção dos trabalhadores, saúde dos enfermos e amparo das famílias paraguayas."
            ),
            recordedMiracles = LocalizedText(
                "Inundaciones detenidas, cosechas salvadas y devución nacional cada 8 de diciembre.",
                "Floods halted, harvests saved, and the national pilgrimage every 8 December.",
                "Cheias detidas, colheitas salvas e peregrinação nacional anual."
            ),
            venerationPrayer = LocalizedText(
                "Madre de Caacupé, Señora de los Milagros: cubre con tu manto a Paraguay, sana a nuestros enfermos y fortalece la fe de tu pueblo. Amén.",
                "Mother of Caacupé: cover Paraguay with your mantle, heal our sick, and strengthen our faith. Amen.",
                "Mãe de Caacupé: protegei o Paraguai, curai os nossos enfermos e fortalecei a fé do vosso povo. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Paraguay", "Paraguay", "Paraguai"),
            patronageOf = LocalizedText("Patrona principal de Paraguay.", "Principal patroness of Paraguay.", "Padroeira principal do Paraguai.")
        ),
        MarianApparition(
            id = "altagracia",
            title = LocalizedText("Nuestra Señora de la Altagracia", "Our Lady of Altagracia", "Nossa Senhora da Altagracia"),
            feastDay = LocalizedText("15 de Agosto", "15 August", "15 de Agosto"),
            year = "1515",
            location = LocalizedText("Higüey, República Dominicana", "Higüey, Dominican Republic", "Higüey, República Dominicana"),
            primarySanctuary = LocalizedText("Basílica de Nuestra Señora de la Altagracia (Higüey)", "Basilica of Altagracia, Higüey", "Basílica da Altagracia em Higüey"),
            historyAndApparition = LocalizedText(
                "Cuadro traído de España en 1515 y colocado en Higüey. Francisco y Alberto de Astacio desaparecieron una noche y reaparecieron ilesos invocándola; de ahí 'Nuestra Señora de la Altagracia'. Protectora del pueblo dominicano.",
                "Painting brought from Spain in 1515 to Higüey. Two brothers missing one night reappeared unharmed invoking her, giving 'Our Lady of High Grace'. Protector of the Dominican people.",
                "Quadro trazido em 1515 para Higüey. Protege o povo dominicano como Mãe da Altagracia."
            ),
            promises = LocalizedText(
                "Gracia alta para los que se pierden, hallazgo de lo perdido y amparo en la noche de la angustia.",
                "Strength for the lost, finding of what is lost, and shelter in the night of anguish.",
                "Graça para os extraviados, encontro dos perdidos e amparo nas angústias."
            ),
            recordedMiracles = LocalizedText(
                "El hallazgo milagroso de los Astacio en 1515 y peregrinaciones nacionales al santuario de Higüey.",
                "The miraculous finding of the Astacio brothers in 1515 and national pilgrimages to Higüey.",
                "O encontro milagroso dos irmãos Astacio em 1515."
            ),
            venerationPrayer = LocalizedText(
                "¡Virgen de la Altagracia, Madre y Reina!: ampara a tu pueblo, guárdanos en la gracia y llévanos a tu Hijo Jesucristo. Amén.",
                "Virgin of Altagracia: shelter your people, keep us in grace, and bring us to Jesus. Amen.",
                "Virgem da Altagracia: amparai o vosso povo e guardai-nos na graça. Amém."
            ),
            region = "Americas",
            country = LocalizedText("República Dominicana", "Dominican Republic", "República Dominicana"),
            patronageOf = LocalizedText("Protectora y Reina del pueblo dominicano.", "Protectress and Queen of the Dominican people.", "Protetora do povo dominicano.")
        ),
        MarianApparition(
            id = "perpetuo_socorro",
            title = LocalizedText("Nuestra Señora del Perpetuo Socorro", "Our Lady of Perpetual Help", "Nossa Senhora do Perpétuo Socorro"),
            feastDay = LocalizedText("27 de Junio", "27 June", "27 de Junho"),
            year = "1499 (icono milenario)",
            location = LocalizedText("Iglesia de San Alfonso (Roma)", "Church of St. Alphonsus, Rome", "Igreja de Santo Afonso em Roma"),
            primarySanctuary = LocalizedText("Iglesia de San Alfonso María de Ligorio (Roma)", "Church of St. Alphonsus, Rome", "Igreja de Santo Afonso em Roma"),
            historyAndApparition = LocalizedText(
                "Icono cretense de los siglos XIV-XV de la Virgen con el Niño Jesús y los arcángeles Miguel y Gabriel, rescatado de la iglesia de San Mateo tras el saqueo de Roma en 1527. Los Redentoristas lo guardan por orden de Pío IX desde 1866.",
                "Cretan icon of the 14th-15th centuries of Mary and the Child with Michael and Gabriel, saved after the 1527 sack of Rome. Redemptorists guard it by order of Pius IX since 1866.",
                "Ícone dos séculos XIV-XV venerado em Roma. Salvo no saqueio de 1527, guardado pelos Redentoristas desde 1866."
            ),
            promises = LocalizedText(
                "«El que visita esta imagen y la honra obtendrá siempre y en todo lugar socorro perpetuo».",
                "Devotion to this icon obtains perpetual help in life and in death.",
                "A devoção a este ícone obtém perpétuo socorro na vida e na morte."
            ),
            recordedMiracles = LocalizedText(
                "Incontables gracias ante el icono; la imagen del Niño que se aparta temeroso y la Madre que lo sostiene.",
                "Countless graces before the icon; the Child turning in fear while the Mother holds Him.",
                "Inúmeras graças diante do ícone milagroso."
            ),
            venerationPrayer = LocalizedText(
                "Madre del Perpetuo Socorro: acude a nosotros en toda tentación, cura a nuestros enfermos y condúcenos al puerto de la salvación. Amén.",
                "Mother of Perpetual Help: come to us in every temptation, heal our sick, and guide us to salvation. Amen.",
                "Mãe do Perpétuo Socorro: socorrei-nos nas tentações e curai os nossos enfermos. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal / Italia", "Universal / Italy", "Universal / Itália"),
            patronageOf = LocalizedText("Auxilio perpetuo de enfermos y moribundos.", "Perpetual help of the sick and dying.", "Auxílio perpétuo dos enfermos e moribundos.")
        ),
        MarianApparition(
            id = "akita",
            title = LocalizedText("Nuestra Señora de Akita", "Our Lady of Akita", "Nossa Senhora de Akita"),
            feastDay = LocalizedText("15 de Agosto", "15 August", "15 de Agosto"),
            year = "1973",
            location = LocalizedText("Akita, Japón", "Akita, Japan", "Akita, Japão"),
            primarySanctuary = LocalizedText("Santuarios de Akita (Japón)", "Shrines at Akita, Japan", "Santuários de Akita no Japão"),
            historyAndApparition = LocalizedText(
                "La Hermana Agnes Sasagawaha oyó mensajes en la capilla (1973) y una estatua de madera lloró lágrimas y sangre. La Virgen advirtió sobre la necesidad de oración, penitencia y sacrificio ante la apostasía y la confusión del mundo.",
                "Sister Agnes Sasagawaha received messages in the chapel (1973) and a wooden statue wept tears and blood. Mary warned of prayer, penance and sacrifice amid apostasy.",
                "Irmã Inês Sasagawaha recebeu mensagens em 1973 e a estátua lacrimou sangue. Maria exorta à oração e penitência."
            ),
            promises = LocalizedText(
                "Conversión de pecadores, reconciliación de los pueblos y consuelo en medio de la confusión moderna.",
                "Conversion of sinners, reconciliation of peoples, and consolation amid modern confusion.",
                "Conversão dos pecadores e consolo em meio à confusão."
            ),
            recordedMiracles = LocalizedText(
                "La estatua que lloró 101 veces, el cáncer de la hermana curado y la sordera de un sordo sanada tras la oración.",
                "The statue weeping 101 times, the sister's cancer healed, and a deaf man's hearing restored.",
                "A estátua que chorou 101 vezes e curas atestadas após a oração."
            ),
            venerationPrayer = LocalizedText(
                "María de Akita, Madre fiel: ruega por la conversión de los pecadores, por la paz del mundo y por los sacerdotes perseguidos. Amén.",
                "Mary of Akita: pray for the conversion of sinners, peace in the world, and persecuted priests. Amen.",
                "Maria de Akita: rogai pela conversão dos pecadores e pela paz no mundo. Amém."
            ),
            region = "Asia_Africa",
            country = LocalizedText("Japón", "Japan", "Japão"),
            patronageOf = LocalizedText("Patrona de los enfermos y de los sordos.", "Patroness of the sick and deaf.", "Padroeira dos enfermos e dos surdos.")
        ),
        MarianApparition(
            id = "kibeho",
            title = LocalizedText("Nuestra Señora de Kibeho (Madre del Verbo)", "Our Lady of Kibeho", "Nossa Senhora de Kibeho"),
            feastDay = LocalizedText("28 de Noviembre", "28 November", "28 de Novembro"),
            year = "1981",
            location = LocalizedText("Kibeho, Ruanda", "Kibeho, Rwanda", "Kibeho, Ruanda"),
            primarySanctuary = LocalizedText("Santuarios de Kibeho (Ruanda)", "Shrines at Kibeho, Rwanda", "Santuários de Kibeho em Ruanda"),
            historyAndApparition = LocalizedText(
                "Entre 1981 y 1989 la Virgen se apareció a tres jóvenes ruandesas profetizando la guerra civil de 1994. Pidió oración, conversión y reconciliación; su mensaje: 'No vendan su conciencia por dinero'.",
                "Between 1981 and 1989 Mary appeared to three Rwandan girls foretelling the 1994 civil war. She asked prayer, conversion and reconciliation; 'Do not sell your conscience for money.'",
                "Entre 1981 e 1989 a Virgem apareceu a três jovens ruandesas, profetizando a guerra de 1994 e chamando à oração."
            ),
            promises = LocalizedText(
                "Reconciliación entre pueblos, consuelo a las viudas y huérfanos, fidelidad a la conciencia.",
                "Reconciliation of peoples, consolation of widows and orphans, fidelity of conscience.",
                "Reconciliação dos povos e consolo às viúvas e órfãos."
            ),
            recordedMiracles = LocalizedText(
                "Apariciones verificadas por la comisión diocesana y la paz incansable de los aldeanos tras la guerra.",
                "Apparitions approved by the diocesan commission and the enduring peace of the villages.",
                "Aparições reconhecidas pela comissão diocesana."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de Kibeho, Madre del Verbo: reconcilia a nuestros pueblos, sana las heridas de la guerra y denuestos la paz de Cristo. Amén.",
                "Virgin of Kibeho: reconcile our peoples, heal the wounds of war, and give us Christ's peace. Amen.",
                "Virgem de Kibeho: reconciliai os nossos povos e curai as feridas da guerra. Amém."
            ),
            region = "Asia_Africa",
            country = LocalizedText("Ruanda", "Rwanda", "Ruanda"),
            patronageOf = LocalizedText("Patrona de la reconciliación en África.", "Patroness of reconciliation in Africa.", "Padroeira da reconciliação na África.")
        ),
        MarianApparition(
            id = "remedios_cholula",
            title = LocalizedText("Nuestra Señora de los Remedios (Cholula)", "Our Lady of Remedies (Cholula)", "Nossa Senhora dos Remédios (Cholula)"),
            feastDay = LocalizedText("8 de Septiembre", "8 September", "8 de Setembro"),
            year = "1519 (templo)",
            location = LocalizedText("Santuario de Cholula, Puebla, México", "Cholula Shrine, Puebla, Mexico", "Santuário de Cholula, Puebla, México"),
            primarySanctuary = LocalizedText("Santuario de la Virgen de los Remedios (Cholula)", "Shrine of Our Lady of Remedies, Cholula", "Santuário da Virgem dos Remédios em Cholula"),
            historyAndApparition = LocalizedText(
                "Tras la evangelización de Cholula se levantó el santuario sobre la gran pirámide dedicada a Quetzalcóatl. La Virgen de los Remedios protege a los pueblos del Valle de México y de Puebla contra pestes y calamidades.",
                "After the evangelization of Cholula the shrine rose above the great pyramid of Quetzalcoatl. Our Lady of Remedies protects the towns of Puebla from plagues.",
                "Após a evangelização de Cholula ergueu-se o santuário sobre a pirâmide de Quetzalcóatl. Protege os povos de Puebla."
            ),
            promises = LocalizedText(
                "Remedio celestial para las epidemias, amparo a los agricultores y salud de los niños.",
                "Heavenly remedy in epidemics, protection of farmers, and health of children.",
                "Auxílio celeste nas epidemias e proteção dos agricultores."
            ),
            recordedMiracles = LocalizedText(
                "El cese de epidemias tras peregrinaciones a la pirámide-santuario y feria patronal de septiembre.",
                "Epidemics ceasing after pilgrimages to the pyramid-shrine.",
                "Epidemias que cessaram após peregrinações ao santuário."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de los Remedios, refugio de los enfermos: cúbrenos con tu manto, sáname las heridas del alma y del cuerpo. Amén.",
                "Our Lady of Remedies: cover us with your mantle and heal our wounds of soul and body. Amen.",
                "Virgem dos Remédios: cobri-nos o vosso manto e curai nossas feridas. Amém."
            ),
            region = "Americas",
            country = LocalizedText("México", "Mexico", "México"),
            patronageOf = LocalizedText("Patrona del Valle de Puebla y protectora contra epidemias.", "Patroness of the Puebla Valley.", "Padroeira do Vale de Puebla.")
        ),
        MarianApparition(
            id = "copacabana",
            title = LocalizedText("Nuestra Señora de Copacabana", "Our Lady of Copacabana", "Nossa Senhora de Copacabana"),
            feastDay = LocalizedText("5 de Agosto", "5 August", "5 de Agosto"),
            year = "1583",
            location = LocalizedText("Copacabana, lago Titicaca, Bolivia", "Copacabana, Lake Titicaca, Bolivia", "Copacabana, Lago Titicaca, Bolívia"),
            primarySanctuary = LocalizedText("Basílica de Nuestra Señora de Copacabana", "Basilica of Our Lady of Copacabana", "Basílica de Nossa Senhora de Copacabana"),
            historyAndApparition = LocalizedText(
                "Tallas de maguey realizadas por Francisco Tito Yupanqui (1583) tras un naufragio en el Titicaca en el que se invocó a la Virgen. Su imagen de candelaria (Virgen Morena) preside el santuario más grande del Altiplano.",
                "Carved by Francisco Tito Yupanqui (1583) after a wreck on Lake Titicaca survived invoking the Virgin. Her dark image presides over the greatest Andean shrine.",
                "Esculpida por Francisco Tito Yupanqui após um naufrágio no Titicaca, tornou-se a maior devoção do Altiplano."
            ),
            promises = LocalizedText(
                "Protección de navegantes del Titicaca, abundancia en la pesca y fe de los pueblos aymaras.",
                "Protection of Titicaca sailors, abundance of the catch, and faith of the Aymara peoples.",
                "Proteção dos navegantes do Titicaca e dos povos andinos."
            ),
            recordedMiracles = LocalizedText(
                "Naufragios salvados en el Titicaca y peregrinación nacional de agosto a Copacabana.",
                "Wrecks survived on Lake Titicaca and the national August pilgrimage.",
                "Naufrágios salvos no Titicaca e peregrinação nacional de agosto."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de Copacabana, Reina del lago: guía las barcas de nuestra vida y arrímanos dulces a puerto seguro. Amén.",
                "Virgin of Copacabana: guide the boats of our life to safe harbor. Amen.",
                "Virgem de Copacabana: guia-nos à porto seguro na travessia da vida. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Bolivia", "Bolivia", "Bolívia"),
            patronageOf = LocalizedText("Patrona de los navegantes del Altiplano.", "Patroness of Andean sailors.", "Padroeira dos navegantes do Altiplano.")
        ),
        MarianApparition(
            id = "san_juan_lagos",
            title = LocalizedText("Nuestra Señora de San Juan de los Lagos", "Our Lady of San Juan de los Lagos", "Nossa Senhora de San Juan de los Lagos"),
            feastDay = LocalizedText("2 de Febrero", "2 February", "2 de Fevereiro"),
            year = "1623",
            location = LocalizedText("San Juan de los Lagos, Jalisco, México", "San Juan de los Lagos, Jalisco, Mexico", "San Juan de los Lagos, Jalisco, México"),
            primarySanctuary = LocalizedText("Basílica-Catedral de San Juan de los Lagos", "Cathedral-Basilica of San Juan de los Lagos", "Catedral-Basílica de San Juan de los Lagos"),
            historyAndApparition = LocalizedText(
                "En 1623 la imagen de la Inmaculada salvó por prodigio a una niña aplastada por un circo de acróbatas. La noticia corrió por todo Nueva Galicia y cada año millones de peregrinos visitan la basílica de San Juan.",
                "In 1623 the Immaculate image instantly healed a girl crushed by acrobats. News spread through New Galicia; millions pilgrimage yearly to San Juan.",
                "Em 1623 a imagem salvou milagrosamente uma menina. Milhões de peregrinos visitam anualmente a basílica."
            ),
            promises = LocalizedText(
                "Curación instantánea de los enfermos, amparo de los niños y luz para los peregrinos.",
                "Instant healing of the sick, protection of children, and light for pilgrims.",
                "Cura dos enfermos e proteção das crianças."
            ),
            recordedMiracles = LocalizedText(
                "La niña curada en 1623 y miles de exvotos que cubren las paredes de la basílica.",
                "The healed girl of 1623 and thousands of ex-votos covering the basilica walls.",
                "A menina curada em 1623 e milhares de ex-votos."
            ),
            venerationPrayer = LocalizedText(
                "Madre de San Juan de los Lagos: sáname, Señor, por tu Madre; cúbreme con su gracia y guíame a Cristo. Amén.",
                "Mother of San Juan: heal me through your Mother; cover me with grace and lead me to Christ. Amen.",
                "Mãe de San Juan: curai-nos e guia-nos a Cristo. Amém."
            ),
            region = "Americas",
            country = LocalizedText("México", "Mexico", "México"),
            patronageOf = LocalizedText("Reina de los Altos de Jalisco y de los peregrinos.", "Queen of the Jalisco Highlands.", "Rainha dos Altos de Jalisco.")
        ),
        MarianApparition(
            id = "angeles_costa_rica",
            title = LocalizedText("Nuestra Señora de los Ángeles (Patrona de Costa Rica)", "Our Lady of the Angels (Costa Rica)", "Nossa Senhora dos Anjos (Costa Rica)"),
            feastDay = LocalizedText("2 de Agosto", "2 August", "2 de Agosto"),
            year = "1635",
            location = LocalizedText("Cartago, Costa Rica (Piedra de los Ángeles)", "Cartago, Costa Rica", "Cartago, Costa Rica"),
            primarySanctuary = LocalizedText("Basílica de los Ángeles (Cartago)", "Basilica of Our Lady of the Angels, Cartago", "Basílica dos Anjos em Cartago"),
            historyAndApparition = LocalizedText(
                "Imagen hallada en 1635 en la Piedra de los Ángeles por una india llamada Juana Pereira. La Romería nacional reúne millones; patrona proclamada por el Congreso en 1824 y por la Iglesia en el siglo XX.",
                "Image found in 1635 at the Stone of the Angels by an indigenous woman. National pilgrimage draws millions; proclaimed patroness in 1824.",
                "Imagem encontrada em 1635 na Pedra dos Anjos. Padroeira da Costa Rica e peregrinação nacional anual."
            ),
            promises = LocalizedText(
                "Paz para Costa Rica, compañía de los ángeles en las pruebas y luz para los peregrinos de la Romería.",
                "Peace for Costa Rica, angelic company in trials, and light for Romería pilgrims.",
                "Paz para a Costa Rica e proteção angelical aos peregrinos."
            ),
            recordedMiracles = LocalizedText(
                "La imagen que retornaba siempre a la Piedra de los Ángeles y la preservación de la paz costarricense.",
                "The image always returning to the Stone of the Angels and Costa Rica's enduring peace.",
                "A imagem que retornava sempre à Pedra dos Anjos."
            ),
            venerationPrayer = LocalizedText(
                "Virgen de los Ángeles, Madre y Patrona: cubre a Costa Rica con tu manto, libra al mundo de la violencia y dame a conocer a Jesús. Amén.",
                "Virgin of the Angels: cover Costa Rica with your mantle and bring us to Jesus. Amen.",
                "Virgem dos Anjos: protegei a Costa Rica e guia-nos a Jesus. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Costa Rica", "Costa Rica", "Costa Rica"),
            patronageOf = LocalizedText("Patrona principal de Costa Rica.", "Principal patroness of Costa Rica.", "Padroeira principal da Costa Rica.")
        )
    )

    val christManifestations: List<ChristManifestation> = listOf(
        ChristManifestation(
            id = "sacred_heart_jesus",
            title = LocalizedText("El Sagrado Corazón de Jesús", "The Most Sacred Heart of Jesus", "O Sagrado Coração de Jesus"),
            feastDay = LocalizedText("Viernes posterior a la Solemnidad de Corpus Christi", "Friday after Corpus Christi", "Sexta-feira após o Corpus Christi"),
            originHistory = LocalizedText(
                "Revelado formalmente a Santa Margarita María de Alacoque en Paray-le-Monial (Francia) entre 1673 y 1675. Jesús mostró su corazón vivo, coronado de espinas, con una cruz clavada en llamas de amor, exclamando: 'He aquí el Corazón que tanto ha amado a los hombres y que de ellos sólo recibe ingratitudes'.",
                "Formally revealed to Saint Margaret Mary Alacoque at the convent of the Visitation in Paray-le-Monial. Christ unveiled His divine heart encircled with thorns, aflame with unquenchable charity for mankind.",
                "Revelado a Santa Margarida Maria Alacoque na França. Jesus mostrou Seu Coração cercado de espinhos e em chamas de amor por toda a humanidade."
            ),
            promises = LocalizedText(
                "12 Promesas Solemnes, entre ellas: paz en los hogares, consuelo en las aflicciones, bendición en todas las empresas, y la Gran Promesa de la salvación eterna a quienes comulguen nueve primeros viernes de mes consecutivos.",
                "The 12 Great Promises: peace in families, comfort in all afflictions, abundant blessing on temporal undertakings, and the grace of final repentance for the Nine First Fridays.",
                "As doze promessas de Jesus, incluindo paz nos lares, consolo nas aflições e a salvação eterna àqueles que comungarem nas primeiras sextas-feiras do mês."
            ),
            theologicalMeaning = LocalizedText(
                "El Corazón de Jesús es el símbolo supremo y la fuente real del amor redentor con que el Hijo de Dios amó a su Padre y a todos los hombres hasta el extremo de la Cruz.",
                "The Sacred Heart is the theological and physical center of Christ's infinite charity and the sacrificial source of the Sacraments.",
                "O Coração de Jesus é o símbolo vivo da caridade redentora de Cristo e fonte dos sacramentos da Igreja."
            ),
            officialPrayer = LocalizedText(
                "Jesús, manso y humilde de Corazón, haz mi corazón semejante al tuyo. Sagrado Corazón de Jesús, en Ti confío.",
                "Jesus, meek and humble of heart, make my heart like unto Thine. Most Sacred Heart of Jesus, I place my trust in Thee.",
                "Jesus, manso e humilde de Coração, fazei o meu coração semelhante ao Vosso. Sagrado Coração de Jesus, eu confio em Vós!"
            ),
            region = "Universal",
            country = LocalizedText("Universal / Francia", "Universal / France", "Universal / França"),
            primarySanctuary = LocalizedText("Basílica del Sagrado Corazón de Montmartre (París) y Paray-le-Monial", "Sacré-Cœur Basilica (Paris) & Paray-le-Monial", "Basílica de Sacré-Cœur em Paris e Paray-le-Monial"),
            recordedMiracles = LocalizedText(
                "La consagración de familias que superaron crisis insolubles, la protección de Marsella de la peste en 1720 tras la consagración episcopal, e incontables conversiones en el lecho de muerte.",
                "The deliverance of Marseille from the plague in 1720, preservation of consecrated religious houses, and miracles of deathbed repentance.",
                "A proteção de Marselha da grande peste após a consagração da diocese ao Sagrado Coração e incontáveis conversões."
            )
        ),
        ChristManifestation(
            id = "divina_misericordia",
            title = LocalizedText("Señor de la Divina Misericordia (Jesús Misericordioso)", "The Divine Mercy of Jesus", "Jesus Misericordioso (Divina Misericórdia)"),
            feastDay = LocalizedText("Segundo Domingo de Pascua (Fiesta de la Divina Misericordia)", "Second Sunday of Easter", "Segundo Domingo da Páscoa"),
            originHistory = LocalizedText(
                "Apariciones a Santa Faustina Kowalska en Plock y Vilna (1931-1938). Jesús se presentó vestido de blanco con su mano derecha bendiciendo y la izquierda tocando su túnica en el pecho, de donde brotan dos grandes rayos: uno rojo (sangre que es vida de las almas) y otro pálido (agua que las justifica). Pidió pintar este cuadro con la firma: 'Jesús, en Ti confío'.",
                "Revealed to Saint Faustina Kowalska in Poland. Christ appeared with rays of red (Blood, the life of souls) and pale (Water, which justifies souls) emanating from His pierced side, establishing the Chaplet and Feast of Divine Mercy.",
                "Revelado a Santa Faustina Kowalska. Jesus apareceu irradiando dois raios de luz de Seu peito: o vermelho (Sangue) e o branco (Água), pedindo a pintura do quadro com a inscrição: 'Jesus, eu confio em Vós'."
            ),
            promises = LocalizedText(
                "«El alma que venere esta imagen no perecerá. Prometo también la victoria sobre los enemigos aquí en la tierra y sobre todo en la hora de la muerte. Defenderé a esa alma como mi propia gloria».",
                "“The soul that venerates this image will not perish. I also promise victory over its enemies already here on earth, especially at the hour of death. I Myself will defend it as My own glory.”",
                "“A alma que venerar esta imagem não perecerá. Prometo a salvação e a vitória sobre o pecado na hora da morte.”"
            ),
            theologicalMeaning = LocalizedText(
                "Es el límite que Dios ha puesto al mal en la historia humana: no hay miseria humana ni pecado que pueda agotar el abismo insondable de la misericordia de Cristo.",
                "Divine Mercy is God's supreme attribute toward fallen creation: Christ's blood and water gushing forth from the Cross as a fountain of regeneration.",
                "A misericórdia é o atributo supremo de Deus que acolhe e restaura todo pecador que se aproxima com confiança."
            ),
            officialPrayer = LocalizedText(
                "«¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío! Jesús, en Ti confío».",
                "“O Blood and Water, which gushed forth from the Heart of Jesus as a fount of Mercy for us, I trust in You! Jesus, I trust in You.”",
                "“Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!”"
            ),
            region = "Universal",
            country = LocalizedText("Polonia / Lituania / Universal", "Poland / Lithuania / Universal", "Polônia / Universal"),
            primarySanctuary = LocalizedText("Santuario de la Divina Misericordia (Lagiewniki, Cracovia, Polonia)", "Sanctuary of Divine Mercy, Krakow, Poland", "Santuário da Divina Misericórdia em Cracóvia"),
            recordedMiracles = LocalizedText(
                "La curación milagrosa de Maureen Digan certificada para la beatificación de Santa Faustina, la salvación de pecadores desahuciados al rezar la Coronilla y la expansión universal del culto por San Juan Pablo II.",
                "The miraculous instantaneous cure of Maureen Digan, thousands of certified medical cures, and deathbed conversions.",
                "A cura milagrosa de Maureen Digan e a rápida expansão mundial do culto da Divina Misericórdia."
            )
        ),
        ChristManifestation(
            id = "divino_nino",
            title = LocalizedText("El Divino Niño Jesús (20 de Julio)", "The Divine Child Jesus", "O Divino Menino Jesus"),
            feastDay = LocalizedText("Primer domingo de Septiembre y 20 de Julio", "First Sunday of September", "Primeiro domingo de setembro"),
            originHistory = LocalizedText(
                "Devoción centrada en la infancia divina de Jesús según la profecía de Isaías 9: 'Un niño nos ha nacido'. Se consolidó mundialmente a través del Padre salesiano Juan del Rizzo en el barrio 20 de Julio de Bogotá (Colombia), extendiéndose por todo el mundo hispanohablante.",
                "Devotion celebrating the divine mystery of Christ's childhood. Popularized globally through Father Juan del Rizzo in Bogotá, inspired by the Infant Jesus of Prague.",
                "Devoção à infância santa de Cristo inspirada no Menino Jesus de Praga e amplamente divulgada a partir do Santuário do Divino Menino na Colômbia."
            ),
            promises = LocalizedText(
                "«Todo lo que quieras pedir, pídelo por los méritos de mi infancia y nada te será negado».",
                "“Whatever you wish to obtain, ask for it through the merits of My holy childhood, and your prayer will not go unheeded.”",
                "“Tudo o que pedirdes pelos méritos da minha santa infância ser-vos-á concedido.”"
            ),
            theologicalMeaning = LocalizedText(
                "Nos invita a abrazar la sencillez evangélica: 'Si no se hacen como niños, no entrarán en el Reino de los Cielos' (Mt 18, 3).",
                "Calling us to evangelical simplicity: 'Unless you turn and become like children, you will never enter the kingdom of heaven.'",
                "Ensina a humildade, pureza e confiança total na Divina Providência."
            ),
            officialPrayer = LocalizedText(
                "Divino Niño Jesús, dueño de mi corazón y mi vida: en tus manitas inocentes pongo todas mis necesidades y esperanzas. Te amo y en Ti confío.",
                "Divine Infant Jesus, Sovereign of my heart: into Your open hands I place all my worries and dreams. I love You and trust in You.",
                "Divino Menino Jesus, abençoai a nossa vida, guardai as nossas famílias e guiai os nossos passos no amor. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Colombia / América Latina", "Colombia / Latin America", "Colômbia / América Latina"),
            primarySanctuary = LocalizedText("Santuario del Divino Niño Jesús (Barrio 20 de Julio, Bogotá, Colombia)", "Shrine of the Divine Child Jesus, Bogotá", "Santuário do Divino Menino Jesus em Bogotá"),
            recordedMiracles = LocalizedText(
                "Curación de miles de niños desahuciados, providencia milagrosa para comedores infantiles y hogares de caridad.",
                "Miraculous provisions of food for orphanages and thousands of verified pediatric cures.",
                "Inúmeras graças de cura física de crianças e bênçãos em projetos de caridade."
            )
        ),
        ChristManifestation(
            id = "nino_de_atocha",
            title = LocalizedText("El Santo Niño de Atocha", "The Holy Infant of Atocha", "O Santo Menino de Atocha"),
            feastDay = LocalizedText("25 de Diciembre y 8 de Septiembre", "December 25 & September 8", "25 de Dezembro"),
            originHistory = LocalizedText(
                "Originado en Atocha (Madrid, España) durante la ocupación musulmana, cuando sólo se permitía a niños pequeños llevar comida a los prisioneros cristianos. Los presos comenzaron a ver llegar a un niño desconocido vestido de peregrino con sombrero, cesta de pan y cantimplora que nunca se agotaban. La devoción floreció con fulgor milagroso en Plateros (Fresnillo, Zacatecas, México).",
                "Originated in Atocha, Spain, during Muslim occupation when only children could visit imprisoned Christians. An unknown boy with a basket of bread and gourd of water that never emptied fed the captives. The devotion flourished in Plateros, Zacatecas, Mexico.",
                "Originou-se em Atocha (Espanha) quando prisioneiros cristãos recebiam comida de um menino vestido de peregrino cuja cesta de pão nunca se esgotava. Grande santuário em Fresnillo, México."
            ),
            promises = LocalizedText(
                "Auxilio a los cautivos, liberación de los injustamente encarcelados y amparo en los caminos y a los mineros atrapados.",
                "Liberation for captives, guidance for travelers, and protection of miners deep underground.",
                "Libertação aos presos injustamente e proteção aos viajantes e mineiros."
            ),
            theologicalMeaning = LocalizedText(
                "Manifiesta a Cristo Peregrino que acompaña a los cautivos del pecado: 'Estuve en la cárcel y viniste a verme' (Mt 25, 36).",
                "Embodying Christ the Divine Pilgrim who visits those imprisoned in body or spirit: 'I was in prison and you came to visit me.'",
                "Representa a caridade viva de Cristo que liberta os homens do cativeiro espiritual e temporal."
            ),
            officialPrayer = LocalizedText(
                "¡Sapientísimo y Santo Niño de Atocha, amparo de los desvalidos y consuelo de los afligidos!: visita mi alma encarcelada por el temor, aliméntame con el pan de tu gracia y camina a mi lado en las horas difíciles. Amén.",
                "O Most Wise and Holy Infant of Atocha, Protector of the helpless: visit my troubled heart, nourish me with Your divine solace, and deliver me from adversity. Amen.",
                "Santo Menino de Atocha, refúgio dos necessitados: quebrai as correntes de nossas angústias e guiai os nossos passos no bem. Amém."
            ),
            region = "Americas",
            country = LocalizedText("México / España", "Mexico / Spain", "México / Espanha"),
            primarySanctuary = LocalizedText("Santuario del Santo Niño de Atocha (Plateros, Fresnillo, Zacatecas, México)", "Sanctuary of Plateros, Zacatecas, Mexico", "Santuário de Plateros em Zacatecas"),
            recordedMiracles = LocalizedText(
                "El rescate de mineros atrapados en minas de plata derrumbadas tras encomendarse al Niño, y huellas de polvo en los zapatitos de la imagen por caminar socorriendo peregrinos.",
                "Rescue of miners trapped deep beneath collapsed shafts, and recurring dust on the statue's sandals.",
                "O resgate de operários presos em minas desabadas e sapatos da estátua desgastados por caminhar consolando aflitos."
            )
        ),
        ChristManifestation(
            id = "nino_jesus_de_praga",
            title = LocalizedText("El Santo Niño Jesús de Praga", "The Infant Jesus of Prague", "O Menino Jesus de Praga"),
            feastDay = LocalizedText("Primer domingo de Mayo y 25 de Diciembre", "First Sunday of May", "Primeiro domingo de maio"),
            originHistory = LocalizedText(
                "Estatua de cera del siglo XVI traída de España a Bohemia por Doña María Manrique de Lara y donada en 1628 al convento de los Carmelitas Descalzos en Praga. Tras el saqueo de los sajones, la estatua fue rescatada de los escombros por el Padre Cirilo de la Madre de Dios, oyendo al Niño decirle: 'Ten compasión de mí y yo la tendré de ti; devuélveme mis manos y yo te daré la paz'.",
                "Wax statue brought from Spain to Prague in the 16th century, gifted to the Discalced Carmelites. Discovered in rubble after enemy invasion, Father Cyril heard the Infant say: 'Have pity on Me, and I will have pity on you. Give Me My hands, and I will give you peace.'",
                "Escultura de cera doada aos Carmelitas de Praga em 1628. Após a guerra, a imagem foi restaurada e revelou ao Padre Cirilo: 'Tende compaixão de Mim e Eu terei compaixão de vós; restituí-Me as mãos e Eu vos darei a paz'."
            ),
            promises = LocalizedText(
                "«Cuanto más me honréis, tanto más os favoreceré». Paz en los pueblos, bendición económica en la penuria y sanación de enfermos.",
                "“The more you honor Me, the more I will favor you.” Peace, family concord, and rescue from financial despair.",
                "“Quanto mais me honrardes, mais vos favorecerei.” Paz nas aflições e abundância de bênçãos espirituais."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo Rey que se despoja de su gloria para gobernar con el cetro de la ternura y la debilidad de un niño en brazos.",
                "The sovereign Majesty of the King of Kings manifest in the vulnerability and gentleness of a newborn child.",
                "Cristo Rei do universo revestido da mansidão de uma criança divina."
            ),
            officialPrayer = LocalizedText(
                "¡Oh Divino Niño Jesús de Praga!, por tu santa infancia te suplico que me libres de esta necesidad y me concedas la gracia de amarte y servirte siempre con corazón puro. Amén.",
                "O Infant Jesus of Prague, by the merits of Your divine childhood, deliver me from all anguish and grant me the grace to adore You eternally. Amen.",
                "Menino Jesus de Praga, rico em misericórdia: abençoai a nossa vida e concedei-nos a graça que Vos pedimos com confiança. Amém."
            ),
            region = "Europe",
            country = LocalizedText("República Checa / Universal", "Czech Republic / Universal", "República Tcheca / Universal"),
            primarySanctuary = LocalizedText("Iglesia de Nuestra Señora de la Victoria y San Antonio (Praga, República Checa)", "Church of Our Lady Victorious, Prague", "Igreja de Nossa Senhora Vitoriosa em Praga"),
            recordedMiracles = LocalizedText(
                "La salvación de Praga de la peste en 1639, sanaciones instantáneas de nobles y campesinos desahuciados e incontables testimonios.",
                "Preservation of Prague from invading forces in 1639 and thousands of certified medical cures.",
                "A preservação de Praga da peste e incontáveis testemunhos de cura e auxílio providencial."
            )
        ),
        ChristManifestation(
            id = "senor_milagros",
            title = LocalizedText("El Señor de los Milagros (Cristo Moreno)", "The Lord of Miracles (El Señor de los Milagros)", "O Senhor dos Milagres (Cristo Moreno)"),
            feastDay = LocalizedText("Mes de Octubre (Mes Morado)", "Month of October", "Mês de Outubro"),
            originHistory = LocalizedText(
                "Pintado en 1651 por un esclavo angoleño en una rústica pared de adobe en Pachacamilla (Lima, Perú). El devastador terremoto de 1655 derrumbó templos y palacios, pero la humilde pared del Cristo Crucificado quedó absolutamente intacta. Igual prodigio ocurrió en el terremoto de 1687.",
                "Painted in 1651 by an enslaved Angolan artisan on an adobe wall in Lima, Peru. Violent earthquakes in 1655 and 1687 leveled the city, but the humble wall depicting the crucified Christ remained completely unharmed.",
                "Pintado no século XVII em Lima por um escravo angolano, resistiu milagrosamente a violentos terremotos que devastaram a cidade, tornando-se a maior procissão católica da América do Sul."
            ),
            promises = LocalizedText(
                "Amparo ante catástrofes naturales, sanación de enfermos desahuciados y reconciliación de los pueblos.",
                "Protection in perils, preservation of the faithful, and restoration of physical and moral health.",
                "Proteção contra os perigos, união fraterna e alívio aos enfermos."
            ),
            theologicalMeaning = LocalizedText(
                "Muestra a Cristo Crucificado con el Padre Eterno y el Espíritu Santo, y a sus pies la Virgen María y María Magdalena.",
                "Depicts the full Trinitarian mystery: God the Father above, the Holy Spirit descending, and Christ giving His life with Mary at the foot of the Cross.",
                "Representa o sacrifício pascal de Cristo na Cruz com a presença do Pai e do Espírito Santo."
            ),
            officialPrayer = LocalizedText(
                "Señor de los Milagros, con paso firme de buen cristiano, hagamos grande nuestro Perú y el mundo entero. Guíanos con tu luz y bendícenos.",
                "Lord of Miracles, divine Savior upon the Cross: grant us perseverance in faith, forgive our transgressions, and bestow Your peace upon the world.",
                "Senhor dos Milagres, olhai com misericórdia para nós, curai os nossos males e dai-nos a graça de seguir Vossos passos até a vida eterna. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Perú / América del Sur", "Peru / South America", "Peru / América do Sul"),
            primarySanctuary = LocalizedText("Santuario y Monasterio de las Nazarenas (Lima, Perú)", "Sanctuary of Las Nazarenas, Lima, Peru", "Santuário das Nazarenas em Lima"),
            recordedMiracles = LocalizedText(
                "La preservación intacta del muro de adobe en terremotos que arrasaron Lima, la curación de cánceres terminales y la procesión más multitudinaria de América.",
                "The wall's survival through catastrophic earthquakes, documented cures of terminally ill, and millions gathered annually.",
                "A preservação do muro de barro em sucessivos terremotos devastadores e a maior procissão pública de fé do continente."
            )
        ),
        ChristManifestation(
            id = "nazareno_de_san_pablo",
            title = LocalizedText("El Nazareno de San Pablo", "The Nazarene of Saint Paul", "O Nazareno de São Paulo"),
            feastDay = LocalizedText("Miércoles Santo", "Holy Wednesday", "Quarta-feira Santa"),
            originHistory = LocalizedText(
                "Talla de madera policromada del siglo XVII esculpida en Sevilla o Caracas. En 1696, una epidemia de peste azotaba Caracas. Durante la procesión de rogativa, la cruz del Nazareno tropezó con una rama de limonero en la esquina de Miracielos; los limones cayeron y se preparó una infusión con la que los enfermos sanaron de inmediato ('El milagro del limonero').",
                "17th-century statue of Christ carrying the Cross. In 1696, during a deadly yellow fever epidemic in Caracas, the statue was carried in procession. Its cross brushed against a lemon tree at the Miracielos corner; the fallen lemons were pressed into tea and instantly cured the dying.",
                "Escultura do século XVII em Caracas. Em 1696, durante uma peste, a cruz tocou os galhos de um limoeiro; os limões colhidos curaram milagrosamente os enfermos que bebiam de seu sumo."
            ),
            promises = LocalizedText(
                "Fortaleza ante el sufrimiento personal, alivio en epidemias y enfermedades, y esperanza ante las cruces cotidianas.",
                "Fortitude beneath life's heavy crosses, recovery from virulent diseases, and deliverance from despair.",
                "Conforto nas aflições da vida e cura de enfermidades graves."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo caído que camina hacia el Calvario cargando los pecados del mundo, recordándonos el mérito redentor de la Cruz.",
                "The patient Savior enduring the Via Dolorosa, assuring all who suffer that their cross is borne with Christ.",
                "Jesus a caminho do Calvário ensinando a redimir os sofrimentos pelo amor de Deus."
            ),
            officialPrayer = LocalizedText(
                "¡Oh dulcísimo Jesús Nazareno, que con infinito amor llevas la Cruz de mis pecados!: concédeme la gracia de abrazar mi cruz diaria con paciencia y acompáñame siempre en la hora de la prueba. Amén.",
                "O sweet Jesus the Nazarene, who carried the Cross for my salvation: grant me grace to bear my daily burdens with perseverance, and never forsake me. Amen.",
                "Senhor Bom Jesus Nazareno, que levastes a pesada Cruz por amor a nós: dai-nos paciência e fortaleza nas tribulações. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Venezuela", "Venezuela", "Venezuela"),
            primarySanctuary = LocalizedText("Basílica de Santa Teresa y Santa Ana (Caracas, Venezuela)", "Basilica of Saint Teresa, Caracas", "Basílica de Santa Teresa em Caracas"),
            recordedMiracles = LocalizedText(
                "El milagro del limonero de Miracielos que erradicó la peste en Caracas, y millones de devotos vestidos de morado en Miércoles Santo.",
                "The Miracle of the Lemon Tree ending the 1696 epidemic, and ongoing healing miracles.",
                "O milagre do limoeiro que cessou a epidemia em Caracas em 1696."
            )
        ),
        ChristManifestation(
            id = "cristo_negro_esquipulas",
            title = LocalizedText("El Cristo Negro de Esquipulas", "The Black Christ of Esquipulas", "O Cristo Negro de Esquipulas"),
            feastDay = LocalizedText("15 de Enero y 9 de Marzo", "January 15", "15 de Janeiro"),
            originHistory = LocalizedText(
                "Esculpido en 1595 en madera oscura de naranjo por el escultor Quirio Cataño en Antigua Guatemala a petición de los indígenas chortís. Colocado en Esquipulas, el Cristo crucificado adoptó un tono oscuro similar a la tez de sus hijos indígenas, convirtiéndose en el gran centro de peregrinación de toda Centroamérica.",
                "Carved in 1595 from dark orange wood by Quirio Cataño in Guatemala for the Chorti indigenous people. Its dark color embraced the indigenous believers, establishing Esquipulas as Central America's spiritual heart.",
                "Esculpido em 1595 na Guatemala para os povos indígenas. A cor escura da imagem aproximou os fiéis, transformando Esquipulas no maior santuário centro-americano."
            ),
            promises = LocalizedText(
                "Paz y reconciliación entre pueblos en conflicto, sanación corporal y amparo a los migrantes y campesinos.",
                "Reconciliation across warring factions, bodily healings, and shelter for migrants and laborers.",
                "Reconciliação e fraternidade entre os povos e amparo aos peregrinos."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo Crucificado que asume la identidad, el dolor y la esperanza de los más marginados y sufrientes.",
                "Christ on the Cross who empties Himself to unite with the oppressed, radiating universal redemption.",
                "A Cruz redentora de Cristo acolhendo as dores e lágrimas dos humildes."
            ),
            officialPrayer = LocalizedText(
                "¡Señor de Esquipulas, Cristo crucificado y bendito!: mira a nuestros pueblos sedientos de paz. Sana las heridas de nuestras discordias y llena nuestros corazones con tu santo perdón. Amén.",
                "O Black Christ of Esquipulas, blessed Redeemer upon the Cross: bestow Your heavenly peace upon our families, heal our sick, and preserve our faith. Amen.",
                "Senhor de Esquipulas, Cristo Crucificado: dai-nos a paz, curai os nossos enfermos e abençoai a nossa América. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Guatemala / Centroamérica", "Guatemala / Central America", "Guatemala / América Central"),
            primarySanctuary = LocalizedText("Basílica del Señor de Esquipulas (Chiquimula, Guatemala)", "Basilica of Esquipulas, Guatemala", "Basílica de Esquipulas na Guatemala"),
            recordedMiracles = LocalizedText(
                "La curación del Arzobispo de Guatemala Fray Pedro Pardo de Figueroa que motivó la construcción del templo monumental en 1759, y el cese de guerras fratricidas.",
                "The miraculous recovery of Archbishop Pardo de Figueroa in 1737 and historical peace agreements blessed at the shrine.",
                "A cura do Arcebispo da Guatemala em 1737 e incontáveis graças alcançadas por peregrinos de todo o continente."
            )
        ),
        ChristManifestation(
            id = "cristo_rey",
            title = LocalizedText("Cristo Rey del Universo (Cristo Rey)", "Christ the King of the Universe", "Cristo Rei do Universo"),
            feastDay = LocalizedText("Último Domingo del Tiempo Ordinario", "Last Sunday in Ordinary Time", "Último domingo do Tempo Comum"),
            originHistory = LocalizedText(
                "Fiesta instituida por Pío XI en 1925 (Quas Primas) ante el laicismo militante. Cristo es Rey no por poder terreno sino por la verdad y la Cruz: 'Mi Reino no es de este mundo'.",
                "Feast instituted by Pius XI in 1925 (Quas Primas). Christ is King not by earthly power but by truth and the Cross: 'My Kingdom is not of this world.'",
                "Festa instituída por Pio XI em 1925. Cristo é Rei pela verdade e pela Cruz: 'O meu Reino não é deste mundo'."
            ),
            promises = LocalizedText(
                "Reinar con Cristo en la gloria, integridad moral en la vida pública y esperanza en medio de las ideologías.",
                "To reign with Christ in glory, moral integrity in public life, and hope amid hostile ideologies.",
                "Reinar com Cristo na glória e integridade moral na vida pública."
            ),
            theologicalMeaning = LocalizedText(
                "Jesucristo es soberano de la creación y de la historia; su realeza es servicio, verdad y amor hasta la Cruz.",
                "Christ is sovereign of creation and history; His kingship is truth, service, and love to the Cross.",
                "Cristo é soberano da criação e da história; sua realeza é verdade e amor até a Cruz."
            ),
            officialPrayer = LocalizedText(
                "Ven, Señor, a reinar: reina en mi vida, en mi hogar y en el mundo entero. Cristo Rey, vengo a Ti.",
                "Come, Lord, and reign: reign in my life, in my home, and in the whole world. Christ the King, I come to You.",
                "Vinde, Senhor, e reinai: reinai na minha vida, no meu lar e no mundo inteiro. Cristo Rei, eu venho a Vós."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            primarySanctuary = LocalizedText("Santuarios y Basílicas de Cristo Rey", "Shrines and Basilicas of Christ the King", "Santuários e Basílicas de Cristo Rei"),
            recordedMiracles = LocalizedText(
                "La consagración de naciones (España 1919, Polonia 2016) y la paz preservada en medio de guerras.",
                "Consecrations of nations (Spain 1919, Poland 2016) and peace preserved amid wars.",
                "As consagrações de nações a Cristo Rei e a paz preservada em meio às guerras."
            )
        ),
        ChristManifestation(
            id = "cristo_milagros",
            title = LocalizedText("Cristo de los Milagros (Señor de los Milagros de Chile)", "Christ of the Miracles (Chile)", "Cristo dos Milagres (Chile)"),
            feastDay = LocalizedText("Segundo Domingo de Noviembre", "Second Sunday of November", "Segundo domingo de novembro"),
            originHistory = LocalizedText(
                "Imagen pintada en el lienzo del siglo XVIII del templo de San Agustín en Santiago, salvada por prodigio del terremoto de 1647 que arruinó la ciudad. El muro y el lienzo permanecieron intactos.",
                "Painting from the 18th century of St. Augustine's church in Santiago, saved miraculously from the 1647 earthquake that ruined the city; wall and canvas stood intact.",
                "Quadro do século XVIII em Santiago, salvo milagrosamente do terremoto de 1647 que destruiu a cidade."
            ),
            promises = LocalizedText(
                "Amparo ante terremotos, curación de enfermos y reconstrucción espiritual de las naciones.",
                "Protection in earthquakes, healing of the sick, and spiritual rebuilding of nations.",
                "Proteção nos terremotos, cura dos enfermos e reconstrução espiritual das nações."
            ),
            theologicalMeaning = LocalizedText(
                "La Cruz como trono de misericordia: Dios es más grande que la naturaleza y la muerte; en ella, los milagros.",
                "The Cross as throne of mercy: God is greater than nature and death; upon it, the miracles.",
                "A Cruz como trono de misericórdia: Deus é maior que a natureza e a morte."
            ),
            officialPrayer = LocalizedText(
                "Señor de los Milagros, mira a tu pueblo: protégenos de todo mal, perdona nuestros pecados y levántanos. Amén.",
                "Lord of Miracles, look upon your people: protect us from all evil, forgive our sins, and lift us up. Amen.",
                "Senhor dos Milagres, olhai para o vosso povo: protegei-nos e perdoai nossos pecados. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Chile", "Chile", "Chile"),
            primarySanctuary = LocalizedText("Templo de San Agustín (Santiago, Chile)", "St. Augustine's Church, Santiago, Chile", "Igreja de Santo Agostinho em Santiago"),
            recordedMiracles = LocalizedText(
                "El lienzo intacto tras el terremoto de 1647 y la reconstrucción de Santiago tras la obra de la Virgen.",
                "The canvas intact after the 1647 earthquake and the rebuilding of Santiago.",
                "O quadro intacto após o terremoto de 1647."
            )
        ),
        ChristManifestation(
            id = "santo_rostro",
            title = LocalizedText("El Santo Rostro de Jesús (de Verónica)", "The Holy Face of Jesus", "A Santa Face de Jesus"),
            feastDay = LocalizedText("Martes de Carnaval", "Shrove Tuesday", "Terça-feira de Carnaval"),
            originHistory = LocalizedText(
                "La Verónica enjuga el rostro de Jesús en el Vía Crucis y queda impresa su faz sangrienta. La devoción fue promovida en el siglo XIX; la 'obrita' y la reparación piden consolar la faz agredida.",
                "Veronica wipes Jesus' face on the Way of the Cross, and His bloodied features are imprinted. The 19th-century devotion asks reparation for the outraged Face.",
                "Verônica enxugou o rosto de Jesus no caminho da Cruz. A devoção pede reparação pela face agredida."
            ),
            promises = LocalizedText(
                "«Carguen conmigo el rostro»: quien ofrece reparación recibe luz en la noche, paz en la agonía y el Rostro sin velo en el cielo.",
                "Light in the night, peace in agony, and the unveiled Face in heaven for those who make reparation.",
                "Luz na noite, paz na agonia e a Face desvelada no céu para quem promove a reparação."
            ),
            theologicalMeaning = LocalizedText(
                "Contemplar la faz humillada de Cristo enseña a honrar el rostro de cada persona y a reparar los agravios.",
                "To contemplate Christ's humiliated face teaches honor for every human face and reparation for offenses.",
                "Contemplar a face humilhada de Cristo ensina a honrar cada rosto humano."
            ),
            officialPrayer = LocalizedText(
                "Rostro adorable de mi Jesús, consuela tu Faz ultrajeada, enjuga tu Sangre y mira con amor a los que te contemplan. Amén.",
                "Adorable Face of Jesus, console Your outraged Face, wipe Your Blood, and look with love on those who contemplate You. Amen.",
                "Rosto adorável de Jesus, consolai a vossa Face ultrajeada e olhai com amor os que vos contemplam. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal / Francia", "Universal / France", "Universal / França"),
            primarySanctuary = LocalizedText("Catedral de Manoppello (Italia) y Tours (Francia)", "Cathedral of Manoppello, Italy", "Catedral de Manoppello na Itália"),
            recordedMiracles = LocalizedText(
                "El lienzo de Manoppello conservado sin corrosión y conversiones de incrédulos tras contemplarlo.",
                "The Manoppello cloth preserved undecayed and conversions of skeptics who contemplated it.",
                "O pano de Manoppello preservado e conversões de incrédulos."
            )
        ),
        ChristManifestation(
            id = "cristo_redentor",
            title = LocalizedText("Cristo Redentor (Corcovado)", "Christ the Redeemer (Corcovado)", "Cristo Redentor (Corcovado)"),
            feastDay = LocalizedText("Domingo de Ramos (Cardozo)", "Palm Sunday", "Domingo de Ramos"),
            originHistory = LocalizedText(
                "Estatua de 38 metros sobre el Cerro del Corcovado en Río de Janeiro, bendecida en 1931. Símbolo de la fe y de la acogida: brazos abiertos sobre la bahía de Guanabara.",
                "38-metre statue atop Corcovado in Rio de Janeiro, blessed in 1931. Symbol of faith and welcome: open arms over Guanabara Bay.",
                "Estátua de 38 metros sobre o Corcovado no Rio, abençoada em 1931, símbolo de fé e acolhimento."
            ),
            promises = LocalizedText(
                "Brazo del Redentor sobre los que buscan trabajo, barco y hogar; paz para la ciudad y para Brasil.",
                "The Redeemer's arm over seekers of work and home; peace for the city of Rio and for Brazil.",
                "O braço do Redentor sobre os que buscam trabalho e lar; paz para o Rio e para o Brasil."
            ),
            theologicalMeaning = LocalizedText(
                "La Redención con los brazos de la Cruz: Cristo abraza a todos sin excepción, de norte a sur y de oriente a occidente.",
                "Redemption with the arms of the Cross: Christ embraces all without exception.",
                "A Redenção de braços abertos: Cristo acolhe a todos sem exceção."
            ),
            officialPrayer = LocalizedText(
                "Cristo Redentor, que de la cruz con los brazos extendidos llamáis a vuestros hijos a la reconciliación: abre vuestro corazón a mi oración. Amén.",
                "Christ the Redeemer, who from the Cross with arms outstretched call your children to reconciliation: open your heart to my prayer. Amen.",
                "Cristo Redentor, que da Cruz de braços abertos chama os vossos filhos: abri vosso coração à minha oração. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Brasil", "Brazil", "Brasil"),
            primarySanctuary = LocalizedText("Santuario del Cristo Redentor (Corcovado, Río)", "Sanctuary of Christ the Redeemer, Rio", "Santuário do Cristo Redentor no Rio"),
            recordedMiracles = LocalizedText(
                "La estatua intacta tras tormentas y caída de rayos; las misa de apertura del santuario cada domingo.",
                "The statue unharmed by storms and lightning; weekly Sunday Masses at the summit.",
                "A estátua intacta após tempestades e raios."
            )
        ),
        ChristManifestation(
            id = "buen_pastor",
            title = LocalizedText("Cristo Buen Pastor (Buen Pastor de Ovejas)", "Christ the Good Shepherd", "Cristo Bom Pastor"),
            feastDay = LocalizedText("IV Domingo de Pascua", "4th Sunday of Easter", "IV Domingo da Páscoa"),
            originHistory = LocalizedText(
                "En el arte de las catacumbas (siglos II-III) Jesús aparece como pastor joven con la oveja rescatada sobre los hombros. El Salmo 22 y Juan 10 fundan esta iconografía milenaria.",
                "In catacomb art (2nd-3rd centuries) Jesus appears as a youthful shepherd bearing the rescued sheep. Psalm 22 and John 10 ground this ancient iconography.",
                "Na arte das catacumbas Jesus aparece como pastor que carrega a ovelha resgatada nos ombros."
            ),
            promises = LocalizedText(
                "«El Buen Pastor conoce a sus ovejas y sus ovejas le conocen». Guía en el discernimiento y búsqueda de los descarriados.",
                "The Good Shepherd knows His own and His own know Him. Guidance for the lost.",
                "O Bom Pastor conhece as suas ovelhas e as conduz à vida plena."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo nos busca, nos conoce por nombre y da la vida por nosotros; nadie es demasiado pequeño para Él.",
                "Christ seeks us, knows us by name, and lays down His life for us; no one is too small for Him.",
                "Cristo nos busca, nos conhece pelo nome e dá a vida por nós."
            ),
            officialPrayer = LocalizedText(
                "Buen Pastor, busca a la oveja perdida que soy: halla mi nombre, llévame en tus hombros y devuélveme al redil. Amén.",
                "Good Shepherd, seek the lost sheep that I am: find my name, bear me on Your shoulders, and bring me home. Amen.",
                "Bom Pastor, buscai a ovelha perdida que sou: encontrai meu nome e conduzi-me de volta. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            primarySanctuary = LocalizedText("Santuarios del Buen Pastor", "Shrines of the Good Shepherd", "Santuários do Bom Pastor"),
            recordedMiracles = LocalizedText(
                "Vocaciones y conversiones de extraviados tras la devoción al Buen Pastor.",
                "Vocations and conversions of the strayed following devotion to the Good Shepherd.",
                "Vocações e conversões após a devoção ao Bom Pastor."
            )
        ),
        ChristManifestation(
            id = "cristo_veracruz",
            title = LocalizedText("Santo Cristo de Veracruz (Cristo de la Veracruz)", "Holy Christ of Vera Cruz", "Santo Cristo da Veracruz"),
            feastDay = LocalizedText("Viernes Santo", "Good Friday", "Sexta-feira Santa"),
            originHistory = LocalizedText(
                "Crucifijo de marfil con coral, llamado 'Cristo de la Luz', venerado en la ermita de la Vera Cruz de La Laguna (Tenerife). Devoción antigua de los isleños y marineros canarios.",
                "Ivory crucifix with coral, known as the Christ of Light, venerated in the hermitage of Vera Cruz in La Laguna, Tenerife. Ancient devotion of Canarian sailors.",
                "Crucifixo de marfim venerado em La Laguna, Tenerife, devoção antiga dos marinheiros canários."
            ),
            promises = LocalizedText(
                "Amparo de los navegantes, luz en la oscuridad y fidelidad en la hora de la tempestad.",
                "Protection of sailors, light in darkness, and faithfulness in the storm.",
                "Proteção dos navegantes e luz nas trevas."
            ),
            theologicalMeaning = LocalizedText(
                "La Veracruz del Señor es la cruz que nos salva; Cristo, luz del mundo, ilumina también la navegación.",
                "Christ, Light of the world, illumines the seafarer as He illumines the soul.",
                "Cristo, Luz do mundo, ilumina o navegante como ilumina a alma."
            ),
            officialPrayer = LocalizedText(
                "Santo Cristo de la Veracruz, luz que en las tinieblas brilla: ilumina mi camino y lleva a buen puerto la nave de mi vida. Amén.",
                "Holy Christ of Vera Cruz, Light shining in darkness: illumine my path and bring my life's ship to safe harbor. Amen.",
                "Santo Cristo da Veracruz, luz que brilha nas trevas: iluminai meu caminho. Amém."
            ),
            region = "Europe",
            country = LocalizedText("España / Canarias", "Spain / Canary Islands", "Espanha / Canárias"),
            primarySanctuary = LocalizedText("Ermita de la Vera Cruz (La Laguna, Tenerife)", "Hermitage of Vera Cruz, La Laguna", "Ermida da Vera Cruz em La Laguna"),
            recordedMiracles = LocalizedText(
                "Náufragos librados en las costas canarias invocando al Cristo de la Luz.",
                "Shipwrecked sailors rescued on Canarian coasts invoking the Christ of Light.",
                "Náufragos salvos invocando o Cristo da Luz."
            )
        ),
        ChristManifestation(
            id = "senor_columna",
            title = LocalizedText("El Señor de la Columna (Cristo de la Columna)", "Lord of the Column (Christ of the Column)", "Senhor da Coluna"),
            feastDay = LocalizedText("Lunes Santo", "Holy Monday", "Segunda-feira Santa"),
            originHistory = LocalizedText(
                "Escultura de la Flagelación venerada en muchas ciudades latinoamericanas. El Señor atado a la columna soporta la corona de espinas y el azote para redimir el maltrato del hombre.",
                "Devotion to the Flagellation venerated in many cities. The Lord bound to the column endures scourging and crowning with thorns.",
                "Devoção à Flagelação venerada em muitas cidades. O Senhor atado à coluna sofre por amor a nós."
            ),
            promises = LocalizedText(
                "Sanación de las heridas del maltrato, fortaleza en la humillación y paciencia ante la injusticia.",
                "Healing of wounds of abuse, strength in humiliation, and patience under injustice.",
                "Cura das feridas do maltrato e paciência ante a injustiça."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo asume la humillación para dignificar a la víctima: nada de lo que te han hecho es ajeno a Él.",
                "Christ assumes humiliation to dignify the victim: nothing done to you is alien to Him.",
                "Cristo assume a humilhação para dignificar a vítima."
            ),
            officialPrayer = LocalizedText(
                "Señor de la Columna, que hiciste mías mis heridas: sáname, Señor, por la sangre que de Ti derramas y dame paz en la humillación. Amén.",
                "Lord of the Column, who made my wounds Your own: heal me, Lord, through the blood You shed, and give me peace in humiliation. Amen.",
                "Senhor da Coluna: curai-me por vosso sangue derramado e dai-me paz na humilhação. Amém."
            ),
            region = "Americas",
            country = LocalizedText("Latinoamérica", "Latin America", "América Latina"),
            primarySanctuary = LocalizedText("Iglesias del Señor de la Columna", "Churches of the Lord of the Column", "Igrejas do Senhor da Coluna"),
            recordedMiracles = LocalizedText(
                "Curaciones de enfermos sometidos a humillaciones y reconciliaciones familiares atestiguadas.",
                "Cures of the humiliated and family reconciliations attested.",
                "Curas de pessoas humilhadas e reconciliações familiares."
            )
        ),
        ChristManifestation(
            id = "cristo_yacente",
            title = LocalizedText("El Santo Cristo Yacente (Cristo Muerto)", "The Holy Recumbent Christ", "O Santo Cristo Jacente"),
            feastDay = LocalizedText("Viernes Santo (Santo Entierro)", "Good Friday (Entombment)", "Sexta-feira Santa (Santo Enterro)"),
            originHistory = LocalizedText(
                "Imagen del Cristo muerto en el regazo de la Madre o en el sepulcro, venerada en la liturgia de la Semana Santa. Recuerda el descanso del cuerpo tras la Cruz antes de la Resurrección.",
                "Image of the dead Christ in the Mother's lap or in the tomb, venerated in Holy Week liturgy. Recalling the rest of the body after the Cross before the Resurrection.",
                "Imagem do Cristo morto venerada na liturgia da Semana Santa, lembrando o descanso após a Cruz."
            ),
            promises = LocalizedText(
                "Consuelo a los que lloran a sus difuntos, esperanza en la muerte y confianza para no temer el sepulcro.",
                "Consolation for mourners, hope in death, and trust not to fear the tomb.",
                "Consolo aos que choram seus mortos e esperança diante da morte."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo verdaderamente muerto y verdaderamente resucitado: la muerte no es la última palabra, la vida eterna lo es.",
                "Christ truly dead and truly risen: death is not the last word, eternal life is.",
                "Cristo verdadeiramente morto e ressuscitado: a morte não é a última palavra."
            ),
            officialPrayer = LocalizedText(
                "Cristo Yacente, que en la tumba esperaste la gloria de la Resurrección: en Ti reposamos y en Ti resucitaremos. Amén.",
                "Recumbent Christ, who in the tomb awaited the glory of the Resurrection: in You we rest, and in You we shall rise. Amen.",
                "Cristo Jacente, que no túmulo aguardastes a glória da Ressurreição: em Vós descansamos. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            primarySanctuary = LocalizedText("Capillas del Santo Entierro", "Chapels of the Entombment", "Capelas do Santo Enterro"),
            recordedMiracles = LocalizedText(
                "Consuelo a fieles en duelo y conversiones junto al sepulcro en la Semana Santa.",
                "Consolation to mourners and conversions beside the tomb in Holy Week.",
                "Consolo aos enlutados e conversões na Semana Santa."
            )
        ),
        ChristManifestation(
            id = "cristo_camino",
            title = LocalizedText("Cristo del Camino (Cristo Peregrino)", "Christ of the Way (Wayfarer Christ)", "Cristo do Caminho"),
            feastDay = LocalizedText("V Domingo de Cuaresma", "5th Sunday of Lent", "V Domingo da Quaresma"),
            originHistory = LocalizedText(
                "Cristo caminante de Emaús (Lc 24) que se hace presente en el camino. Devoción de caminantes, emigrantes y peregrinos que reconocen al Señor en la fracción del pan.",
                "The wayfaring Christ of Emmaus (Lk 24) present on the road. Devotion of walkers, migrants and pilgrims who recognize the Lord in the breaking of bread.",
                "Cristo caminhante de Emaús presente no caminho. Devoção de peregrinos e migrantes."
            ),
            promises = LocalizedText(
                "Compañía en el camino, pan en el desierto y reconocimiento del Señor en la Eucaristía.",
                "Companionship on the road, bread in the desert, and recognition of the Lord in the Eucharist.",
                "Companhia no caminho e reconhecimento do Senhor na Eucaristia."
            ),
            theologicalMeaning = LocalizedText(
                "El Resucitado camina con nosotros; no somos abandonados en el camino, Él es el Camino.",
                "The Risen One walks with us; we are not abandoned on the road, He is the Way.",
                "O Ressuscitado caminha conosco; Ele é o Caminho."
            ),
            officialPrayer = LocalizedText(
                "Cristo del Camino, que a Emaús caminaste con dos discípulos: camina conmigo, quédate con nosotros y parte el pan. Amén.",
                "Christ of the Way, who walked to Emmaus: walk with me, stay with us, and break the bread. Amen.",
                "Cristo do Caminho, caminhai conosco e permanecei conosco. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            primarySanctuary = LocalizedText("Santuarios de Emaús y del Camino", "Shrines of Emmaus and the Way", "Santuários de Emaús"),
            recordedMiracles = LocalizedText(
                "Peregrinos librados de peligros en los caminos y encuentros eucarísticos transformantes.",
                "Pilgrims delivered from perils on the road and transforming Eucharistic encounters.",
                "Peregrinos livrados de perigos e encontros transformadores."
            )
        ),
        ChristManifestation(
            id = "cristo_paciencia",
            title = LocalizedText("El Cristo de la Paciencia (Señor de la Paciencia)", "Christ of Patience (Lord of Patience)", "Cristo da Paciência"),
            feastDay = LocalizedText("Jueves Santo", "Holy Thursday", "Quinta-feira Santa"),
            originHistory = LocalizedText(
                "Cristo paciente en la Pasión que no responde con violencia a los que le ofenden. Devoción que exalta la mansedumbre del Corazón frente a la ira.",
                "The patient Christ of the Passion who does not answer violence with violence. Devotion extolling the meekness of the Heart before anger.",
                "Cristo paciente da Paixão que não responde com violência. Devoção à mansidão do Coração."
            ),
            promises = LocalizedText(
                "Paciencia en las pruebas, mansedumbre ante la ofensa y serenidad en las dificultades.",
                "Patience in trials, meekness before offense, and serenity in difficulties.",
                "Paciência nas provações, mansidão ante a ofensa e serenidade nas dificuldades."
            ),
            theologicalMeaning = LocalizedText(
                "Frente a la ira del mundo, Cristo venció por la paciencia; esa es la fuerza que salva.",
                "Before the world's anger, Christ conquered through patience; that is the saving strength.",
                "Diante da ira do mundo, Cristo venceu pela paciência."
            ),
            officialPrayer = LocalizedText(
                "Señor de la Paciencia, que callaste ante tus acusadores: formame a tu imagen, dame tu serenidad y tu perdón. Amén.",
                "Lord of Patience, who kept silence before Your accusers: form me in Your image, give me Your serenity and Your forgiveness. Amen.",
                "Senhor da Paciência: formai-me à vossa imagem e dai-me vossa serenidade. Amém."
            ),
            region = "Universal",
            country = LocalizedText("Universal", "Universal", "Universal"),
            primarySanctuary = LocalizedText("Iglesias de la Paciencia de Cristo", "Churches of the Patience of Christ", "Igrejas da Paciência de Cristo"),
            recordedMiracles = LocalizedText(
                "Reconciliaciones tras años de rencor y paciencia heroica en enfermos atestiguada.",
                "Reconciliations after years of resentment and heroic patience in the sick attested.",
                "Reconciliações após anos de rancor."
            )
        ),
        ChristManifestation(
            id = "cristo_humildad",
            title = LocalizedText("Cristo de la Humildad y Paciencia (Cristo Humilde)", "Christ of Humility (Humble Christ)", "Cristo da Humildade"),
            feastDay = LocalizedText("II Domingo de Adviento", "Second Sunday of Advent", "II Domingo do Advento"),
            originHistory = LocalizedText(
                "Advoción de Cristo humilde, abogado de los pobres; popularizada en México y en la iconografía del Niño pobre de Belén. El Rey de reyes nace en un pesebre.",
                "Advocation of the humble Christ, advocate of the poor; popular in Mexico and in the iconography of the Bethlehem crib. The King of kings is born in a stable.",
                "Advocação de Cristo humilde, advogado dos pobres. O Rei dos reis nasce em um presépio."
            ),
            promises = LocalizedText(
                "Dignidad para los pequeños, pan para los pobres y riqueza verdadera en la humildad de espíritu.",
                "Dignity for the lowly, bread for the poor, and true riches in humility of spirit.",
                "Dignidade para os pequenos e riqueza na humildade."
            ),
            theologicalMeaning = LocalizedText(
                "Dios se hace pequeño para que el pequeño no tema acercarse; la humildad es el camino de la grandeza.",
                "God becomes small so the small may not fear to approach; humility is the way of greatness.",
                "Deus se faz pequeno para que o pequeno não tema aproximar-se."
            ),
            officialPrayer = LocalizedText(
                "Cristo humilde, Rey pobre de Belén: enséñame a despojarme de todo orgullo y a reconocer tu grandeza en la sencillez. Amén.",
                "Humble Christ, poor King of Bethlehem: teach me to strip off all pride and to see Your greatness in simplicity. Amen.",
                "Cristo humilde, Rei pobre de Belém: ensinai-me a despojar-me do orgulho. Amém."
            ),
            region = "Americas",
            country = LocalizedText("México / Universal", "Mexico / Universal", "México / Universal"),
            primarySanctuary = LocalizedText("Capillas del Niño Pobre de Belén", "Chapels of the Poor Child of Bethlehem", "Capelas do Menino Pobre de Belém"),
            recordedMiracles = LocalizedText(
                "Limosnas que se multiplican y dignificación de los olvidados por la sociedad.",
                "Alms multiplied and the forgotten of society dignified.",
                "Esmolas multiplicadas e dignificação dos esquecidos."
            )
        ),
        ChristManifestation(
            id = "cristo_sentenciado",
            title = LocalizedText("Cristo Sentenciado (Padre Jesús Sentenciado)", "Christ Sentenced (Christ the Condemned)", "Cristo Sentenciado"),
            feastDay = LocalizedText("Viernes de Dolores", "Friday of Sorrows", "Sexta-feira das Dores"),
            originHistory = LocalizedText(
                "Cristo condenado por Pilatos, atado y con la cruz a cuestas, venerado como Padre Jesús Sentenciado en Tecla (El Salvador) y en La Plata (Bolivia).",
                "Christ condemned by Pilate, bound and carrying the Cross, venerated as Padre Jesús Sentenciado in Tecla (El Salvador) and La Plata (Bolivia).",
                "Cristo condenado por Pilatos, atado e com a cruz às costas, venerado em El Salvador e na Bolívia."
            ),
            promises = LocalizedText(
                "Fortaleza ante sentencias injustas, absolución de la conciencia y paciencia al cargar la cruz diaria.",
                "Strength before unjust judgments, absolution of conscience, and patience to carry the daily cross.",
                "Fortaleza ante sentenças injustas e paciência para carregar a cruz diária."
            ),
            theologicalMeaning = LocalizedText(
                "Cristo condenado por nosotros para absolvernos; su sentencia es nuestra justificación.",
                "Christ condemned for us to absolve us; His sentence is our justification.",
                "Cristo condenado por nós para nos absolver; sua sentença é nossa justificação."
            ),
            officialPrayer = LocalizedText(
                "Padre Jesús Sentenciado: carguaste la sentencia del mundo para librarme: por tu Sangre me absolverás. Amén.",
                "Father Jesus Sentenced: You carried the world's sentence to free me; by Your Blood absolve me. Amen.",
                "Pai Jesus Sentenciado: carregastes a sentença do mundo para me livrar. Amém."
            ),
            region = "Americas",
            country = LocalizedText("El Salvador / Bolivia", "El Salvador / Bolivia", "El Salvador / Bolívia"),
            primarySanctuary = LocalizedText("Templo de Padre Jesús Sentenciado (Tecla, El Salvador)", "Church of Padre Jesús Sentenciado, Tecla", "Igreja de Padre Jesús Sentenciado em Tecla"),
            recordedMiracles = LocalizedText(
                "Presos absueltos y librados tras su intercesión; procesiones de penitencia en Semana Santa.",
                "Prisoners acquitted and delivered through his intercession; penitential processions in Holy Week.",
                "Prisioneiros absolvidos e procissões penitenciais na Semana Santa."
            )
        )
    )


    val churchHistoryEpochs: List<ChurchHistoryEpoch> = listOf(
        ChurchHistoryEpoch(
            epochId = "apostolic",
            title = LocalizedText("La Iglesia Apostólica y las Persecuciones", "The Apostolic Church & Persecutions", "A Igreja Apostólica e as Perseguições"),
            centuryRange = "Siglos I - IV (Años 33 - 313 d.C.)",
            narrative = LocalizedText(
                "Desde el derramamiento del Espíritu Santo en Pentecostés, los Doce Apóstoles predicaron a Cristo resucitado por todo el Imperio Romano. Durante casi 300 años, la Iglesia creció en medio del martirio bajo emperadores como Nerón, Decio y Diocleciano. Tertuliano exclamó: 'La sangre de los mártires es semilla de nuevos cristianos'.",
                "From the outpouring of Pentecost, the Apostles carried the Gospel across the known world. Under Roman emperors (Nero, Decius, Diocletian), thousands preferred death to apostasy, proving the divinity of their faith.",
                "Do Cenáculo de Pentecostes ao martírio dos primeiros cristãos em Roma. A Igreja se expande heroicamente sustentada pelo sangue dos mártires."
            ),
            keyPopes = LocalizedText("San Pedro (primer Papa, crucificado boca abajo), San Lino, San Cleto, San Clemente Romano.", "St. Peter (Crucified upside down), St. Linus, St. Cletus, St. Clement I.", "São Pedro (primeiro Papa), São Lino, São Cleto e São Clemente."),
            ecumenicalCouncils = LocalizedText("Concilio Apostólico de Jerusalén (año 50, Hechos 15): salvación por la gracia de Cristo.", "Council of Jerusalem (c. 50 AD): Gentiles admitted without Jewish ceremonial law.", "Concílio de Jerusalém (ano 50): abertura aos povos gentios."),
            historicalSaints = LocalizedText("San Ignacio de Antioquía, San Policarpo, San Justino Mártir, Santa Inés, San Lorenzo.", "St. Ignatius of Antioch, St. Polycarp, St. Justin Martyr, St. Agnes.", "Santo Inácio de Antioquia, São Policarpo, Santa Inês e São Lourenço.")
        ),
        ChurchHistoryEpoch(
            epochId = "councils",
            title = LocalizedText("La Era de los Grandes Concilios Ecuménicos", "The Golden Age of Fathers & Councils", "A Era dos Grandes Concílios e Padres"),
            centuryRange = "Siglos IV - VIII (Años 313 - 787 d.C.)",
            narrative = LocalizedText(
                "Con el Edicto de Milán (313), cesan las persecuciones. La Iglesia define dogmáticamente la divinidad de Jesucristo (Nicea 325), la divinidad del Espíritu Santo (Constantinopla 381), a María como Verdadera Madre de Dios (Éfeso 431) y la unión de las dos naturalezas en una sola persona divina (Calcedonia 451). Florece la Patrística y el monacato.",
                "With freedom of religion granted by Constantine, great ecumenical councils rigorously defined orthodox Christology against Arianism, Nestorianism, and Monophysitism.",
                "A liberdade religiosa permite a formulação solene dos dogmas da Santíssima Trindade e da maternidade divina de Maria Santíssima."
            ),
            keyPopes = LocalizedText("San Silvestre I, San Dámaso I (encargó la Vulgata a San Jerónimo), San León Magno, San Gregorio Magno.", "St. Sylvester I, St. Damasus I, St. Leo the Great, St. Gregory the Great.", "São Silvestre I, São Dâmaso I, São Leão Magno e São Gregório Magno."),
            ecumenicalCouncils = LocalizedText("Nicea I (325), Constantinopla I (381), Éfeso (431), Calcedonia (451), Nicea II (787, veneración de iconos).", "Nicaea I (325), Constantinople I (381), Ephesus (431), Chalcedon (451), Nicaea II (787).", "Niceia I (325), Constantinopla I (381), Éfeso (431), Calcedônia (451)."),
            historicalSaints = LocalizedText("San Atanasio, San Basilio, San Juan Crisóstomo, San Agustín, San Jerónimo, San Benito de Nursia.", "St. Athanasius, St. Augustine, St. Jerome, St. John Chrysostom, St. Benedict.", "Santo Atanásio, Santo Agostinho, São Jerônimo e São Bento de Núrsia.")
        ),
        ChurchHistoryEpoch(
            epochId = "modern",
            title = LocalizedText("De Trento al Concilio Vaticano II y la Era Contemporánea", "Council of Trent to Vatican II & Modern Era", "Do Concílio de Trento ao Vaticano II"),
            centuryRange = "Siglos XVI - XXI",
            narrative = LocalizedText(
                "El Concilio de Trento (1545-1563) fijó con precisión el canon de las Escrituras de 73 libros, los siete Sacramentos y la doctrina de la justificación. Los misioneros jesuitas, franciscanos y dominicos llevaron la fe a América, África y Asia. En el siglo XX, el Concilio Vaticano II (1962-1965) renovó la liturgia y la misión pastoral en el mundo moderno.",
                "The Counter-Reformation reaffirmed sacramental theology and initiated worldwide evangelization. In the 20th century, the Second Vatican Council (1962-1965) promoted universal call to holiness and liturgical renewal.",
                "A consolidação dogmática em Trento, a grande expansão missionária nas Américas e a renovação litúrgica e pastoral do Concílio Vaticano II."
            ),
            keyPopes = LocalizedText("San Pío V, San Pío X, San Juan XXIII, San Pablo VI, San Juan Pablo II, Benedicto XVI, Papa Francisco.", "St. Pius V, St. Pius X, St. John XXIII, St. Paul VI, St. John Paul II, Pope Francis.", "São Pio V, São Pio X, São João XXIII, São João Paulo II e Papa Francisco."),
            ecumenicalCouncils = LocalizedText("Concilio de Trento (1545-1563), Concilio Vaticano I (1869-1870), Concilio Vaticano II (1962-1965).", "Council of Trent (1545-1563), Vatican I (1869-1870), Vatican II (1962-1965).", "Concílio de Trento (1545-1563), Vaticano I (1870) e Vaticano II (1962-1965)."),
            historicalSaints = LocalizedText("San Ignacio de Loyola, Santa Teresa de Jesús, San Juan Bosco, San Pío de Pietrelcina, Santa Teresa de Calcuta.", "St. Ignatius of Loyola, St. Teresa of Avila, St. John Bosco, St. Padre Pio, St. Teresa of Calcutta.", "Santo Inácio de Loyola, Santa Teresa d'Ávila, São João Bosco e São Padre Pio.")
        ),
        ChurchHistoryEpoch(
            epochId = "papal_states_medieval",
            title = LocalizedText("Los Estados Pontificios y la Edad Media Central", "The Papal States & the Central Middle Ages", "Os Estados Pontifícios e a Idade Média Central"),
            centuryRange = "Siglos VIII - XI (754 - 1073)",
            narrative = LocalizedText(
                "El Papa Esteban II recibe en 754 la Donación de Pipino y nacen los Estados Pontificios, base temporal del papado. Carlomagno es coronado emperador en Roma (800) por León III, fundando la cristiandad latina. Catedral, monasterio y universidad estructuran la vida de Europa.",
                "Pope Stephen II receives the Donation of Pepin in 754, founding the Papal States. Charlemagne is crowned emperor in Rome (800), founding Latin Christendom. Cathedrals, monasteries and universities shape medieval Europe.",
                "O Papa Estevão II recebe a Doação de Pipino (754) e nascem os Estados Pontifícios. Carlos Magno é coroado imperador em Roma (800)."
            ),
            keyPopes = LocalizedText("San Gregorio Magno, San León III (corona a Carlomagno), San Nicolás I, San Gregorio VII (inicio de la reforma).", "St. Gregory the Great, Leo III (crowns Charlemagne), Nicholas I, Gregory VII.", "São Gregório Magno, Leão III, Nicolau I e Gregório VII."),
            ecumenicalCouncils = LocalizedText("Concilio de Constantinopla IV (869-870, contra Focio); concilios de reforma del siglo IX.", "Fourth Council of Constantinople (869-870); Carolingian reform councils.", "IV Concílio de Constantinopla (869) e concílios carolíngios de reforma."),
            historicalSaints = LocalizedText("San Benito de Nursia, San Beda, San Cirilo y Metodio, Santa Edith Steinline, San Ansgario.", "St. Benedict of Nursia, St. Bede, St. Cyril and Methodius, St. Ansgar.", "São Bento de Núrsia, São Beda, São Cirilo e São Metódio.")
        ),
        ChurchHistoryEpoch(
            epochId = "gregorian_reform",
            title = LocalizedText("La Reforma Gregoriana y la Libertad de la Iglesia", "The Gregorian Reform & the Freedom of the Church", "A Reforma Gregoriana e a Liberdade da Igreja"),
            centuryRange = "Siglos XI - XII (1073 - 1122)",
            narrative = LocalizedText(
                "Gregorio VII excomulga en 1076 al emperador Enrique IV y el Dictatus Papae afirma la independencia espiritual del papado. La Querella de las Investiduras culmina en Worms (1122) con la libertad canónica de la Iglesia frente al poder laico.",
                "Gregory VII excommunicates Emperor Henry IV in 1076 and the Dictatus Papae affirms papal spiritual independence. The Investiture Contest ends at Worms (1122) with canonical liberty from lay power.",
                "Gregório VII excomungou o imperador em 1076. A Querela das Investiduras terminou em Worms (1122) com a liberdade da Igreja."
            ),
            keyPopes = LocalizedText("San Gregorio VII, Pascual II, Gelasio II, Calixto II.", "St. Gregory VII, Paschal II, Gelasius II, Callixtus II.", "São Gregório VII, Pascual II, Gelásio II e Calisto II."),
            ecumenicalCouncils = LocalizedText("Concilio de Letrán I (1123) consagra el Concordato de Worms; sin concilios ecuménicos plenos del período.", "First Lateran Council (1123) promulgates the Worms Concordat; no full ecumenical council of the period.", "I Concílio de Latrão (1123) promulga o Concordato de Worms."),
            historicalSaints = LocalizedText("San Bruno de Colonia, San Anselmo de Canterbury, San Norberto de Xanten, San Bernardo de Claraval.", "St. Bruno of Cologne, St. Anselm of Canterbury, St. Norbert, St. Bernard of Clairvaux.", "São Bruno de Colônia, Santo Anselmo, São Norberto e São Bernardo de Claraval.")
        ),
        ChurchHistoryEpoch(
            epochId = "mendicant_orders",
            title = LocalizedText("Órdenes Mendicantes y la Escolástica", "Mendicant Orders & Scholasticism", "Ordens Mendicantes e a Escolástica"),
            centuryRange = "Siglos XIII - XIV (1209 - 1378)",
            narrative = LocalizedText(
                "San Francisco de Asís (1209) y Santo Domingo (1216) fundan órdenes mendicantes que viven de la mendicidad y predican a los pobres y a las ciudades. La escolástica de Alberto Magno y Tomás de Aquino dialoga con Aristóteles y funda la universidad.",
                "St. Francis of Assisi (1209) and St. Dominic (1216) found mendicant orders living by begging and preaching to the poor and cities. Scholasticism of Albert the Great and Thomas Aquinas opens the university to Aristotle.",
                "São Francisco (1209) e São Domingos (1216) fundam as ordens mendicantes. A escolástica de Tomás de Aquino floresce nas universidades."
            ),
            keyPopes = LocalizedText("Inocencio III, Gregorio IX, Inocencio IV, Gregorio X, Nicolás III, Martín IV, Bonifacio VIII.", "Innocent III, Gregory IX, Innocent IV, Gregory X, Nicholas III, Martin IV, Boniface VIII.", "Inocêncio III, Gregório IX, Inocêncio IV e Bonifácio VIII."),
            ecumenicalCouncils = LocalizedText("Letrán IV (1215, confesión anual y Eucaristía), Lyon I (1245), Lyon II (1274), Vienne (1311-1312).", "Lateran IV (1215), Lyon I (1245), Lyon II (1274), Vienne (1311-1312).", "Latrão IV (1215), Lyon I e II, Vienne (1312)."),
            historicalSaints = LocalizedText("San Francisco de Asís, Santo Domingo, Santa Clara, San Antonio de Padua, San Buenaventura, Santo Tomás de Aquino.", "St. Francis of Assisi, St. Dominic, St. Clare, St. Anthony of Padua, St. Bonaventure, St. Thomas Aquinas.", "São Francisco de Assis, São Domingos, Santa Clara, Santo Antônio de Pádua, São Tomás de Aquino.")
        ),
        ChurchHistoryEpoch(
            epochId = "reformation_trent",
            title = LocalizedText("La Reforma Protestante y el Concilio de Trento", "The Protestant Reformation & the Council of Trent", "A Reforma Protestante e o Concílio de Trento"),
            centuryRange = "Siglos XVI (1517 - 1563)",
            narrative = LocalizedText(
                "Lutero publica las 95 tesis en 1517; la ruptura se extiende y nacen luteranos, anglicanos y calvinistas. La Iglesia responde con el Concilio de Trento (1545-1563), que fija el canon bíblico, los siete sacramentos, la justificación y la renovación del clero y de las órdenes.",
                "Luther posts the 95 Theses in 1517; the rupture spreads and Lutheran, Anglican and Calvinist churches arise. The Church answers with the Council of Trent (1545-1563), fixing the biblical canon, the seven sacraments, justification and renewal.",
                "Lutero publica as 95 teses em 1517. A Igreja responde com o Concílio de Trento (1545-1563), reafirmando o cânon bíblico e os sete sacramentos."
            ),
            keyPopes = LocalizedText("León X, Adriano VI, Clemente VII, Pablo III, Julio III, Marcelo II, Pablo IV, Pío IV.", "Leo X, Adrian VI, Clement VII, Paul III, Julius III, Marcellus II, Paul IV, Pius IV.", "Leão X, Adriano VI, Clemente VII, Paulo III, Júlio III, Pio IV."),
            ecumenicalCouncils = LocalizedText("Concilio de Trento (1545-1563): Escritura y Tradición, pecado original, justificación, sacramentos, Misa y purgatorio.", "Council of Trent (1545-1563): Scripture and Tradition, original sin, justification, sacraments, Mass, purgatory.", "Concílio de Trento: Escritura e Tradição, pecado original, justificação e sacramentos."),
            historicalSaints = LocalizedText("San Ignacio de Loyola, San Francisco Javier, Santa Teresa de Jesús, San Felipe Neri, San Juan de la Cruz.", "St. Ignatius of Loyola, St. Francis Xavier, St. Teresa of Avila, St. Philip Neri, St. John of the Cross.", "Santo Inácio de Loyola, São Francisco Xavier, Santa Teresa d'Ávila, São Filipe Néri.")
        ),
        ChurchHistoryEpoch(
            epochId = "baroque_missions",
            title = LocalizedText("Misiones Barrocas y la Contrarreforma", "Baroque Missions & the Counter-Reformation", "Missões Barrocas e a Contrarreforma"),
            centuryRange = "Siglos XVII - XVIII (1600 - 1750)",
            narrative = LocalizedText(
                "La Companía de Jesús y las órdenes mendicantes llevan el Evangelio a América, África y Asia con colegios, reducciones y barroco. Propaganda Fide (1622) coordina las misiones; las congregaciones reformadas y la devoción al Sagrado Corazón renuevan la vida interior.",
                "The Society of Jesus and mendicant orders carry the Gospel to America, Africa and Asia with schools, reductions and baroque art. Propaganda Fide (1622) coordinates missions; the Sacred Heart devotion renews interior life.",
                "Jesuítas e mendicantes levam o Evangelho à América, África e Ásia. Propaganda Fide (1622) organiza as missões."
            ),
            keyPopes = LocalizedText("Pablo V, Urbano VIII, Alejandro VII, Clemente XI, Clemente XII, Benedicto XIV.", "Paul V, Urban VIII, Alexander VII, Clement XI, Clement XII, Benedict XIV.", "Paulo V, Urbano VIII, Alexandre VII, Clemente XI e Bento XIV."),
            ecumenicalCouncils = LocalizedText("Sin concilios ecuménicos; Catecismo Romano (1566) y reformas post-tridentinas.", "No ecumenical council; the Roman Catechism (1566) and post-Tridentine reforms.", "Sem concílios ecumênicos; Catecismo Romano (1566) e reformas pós-tridentinas."),
            historicalSaints = LocalizedText("San Vicente de Paúl, San Luis de Montfort, Santa Margarita María de Alacoque, San Pedro Claver, San Francisco de Sales.", "St. Vincent de Paul, St. Louis de Montfort, St. Margaret Mary Alacoque, St. Peter Claver, St. Francis de Sales.", "São Vicente de Paulo, São Luís de Montfort, Santa Margarida Maria, São Pedro Claver.")
        ),
        ChurchHistoryEpoch(
            epochId = "enlightenment_suppression",
            title = LocalizedText("La Ilustración y la Supresión de la Compañía", "The Enlightenment & the Suppression of the Jesuits", "O Iluminismo e a Supressão da Companhia"),
            centuryRange = "Siglos XVIII - XIX (1700 - 1830)",
            narrative = LocalizedText(
                "La Ilustración critica el poder de la Iglesia; José II y los déspotas ilustrados someten órdenes y bienes eclesiásticos. Clemente XIV suprime la Compañía de Jesús (1773); Pío VII la restaura en 1814. La Iglesia pierde Estados Pontificios en 1870.",
                "Enlightenment thought criticizes Church power; Joseph II and enlightened despots subject orders and goods. Clement XIV suppresses the Jesuits (1773); Pius VII restores them in 1814. The Church loses the Papal States in 1870.",
                "O Iluminismo critica o poder da Igreja. Clemente XIV suprime os Jesuítas (1773); Pio VII os restaura (1814)."
            ),
            keyPopes = LocalizedText("Clemente XI, Clemente XII, Benedicto XIV, Clemente XIII, Clemente XIV, Pío VI, Pío VII, Gregorio XVI.", "Clement XI, Clement XII, Benedict XIV, Clement XIII, Clement XIV, Pius VI, Pius VII, Gregory XVI.", "Clemente XI, Bento XIV, Clemente XIV, Pio VI, Pio VII e Gregório XVI."),
            ecumenicalCouncils = LocalizedText("Sin concilios ecuménicos; encíclicas contra el liberalismo y la masonería.", "No ecumenical council; encyclicals against liberalism and freemasonry.", "Sem concílios ecumênicos; encíclicas contra o liberalismo."),
            historicalSaints = LocalizedText("San Alfonso María de Ligorio, San Gerardo Mayela, Santa Teresita de Lisieux (precursora), Santa Faustina Kowalska.", "St. Alphonsus Maria de Liguori, St. Gerard Majella, St. Thérèse of Lisieux, St. Faustina Kowalska.", "Santo Afonso de Ligório, São Geraldo Majella, Santa Faustina Kowalska.")
        ),
        ChurchHistoryEpoch(
            epochId = "vatican_i_modern",
            title = LocalizedText("El Vaticano I y la Iglesia Moderna", "Vatican I & the Modern Church", "O Vaticano I e a Igreja Moderna"),
            centuryRange = "Siglos XIX - XX (1870 - 1960)",
            narrative = LocalizedText(
                "El Vaticano I (1869-1870) define la infalibilidad papal y la divinidad de Cristo. Los papas sociales construyen el magisterio moderno: la Inmaculada (1854), la Asunción (1950), las apariciones de Lourdes, Fátima y la consagración al Sagrado Corazón marcan el ciclo.",
                "Vatican I (1869-1870) defines papal infallibility and the divinity of Christ. Social popes build modern teaching: the Immaculate Conception (1854), the Assumption (1950), Lourdes, Fatima and the Sacred Heart consecration mark the period.",
                "O Vaticano I (1869-1870) define a infalibilidade papal. Imaculada (1854) e Assunção (1950) são proclamadas."
            ),
            keyPopes = LocalizedText("Pío IX, León XIII, Pío X, Benedicto XV, Pío XI, Pío XII.", "Pius IX, Leo XIII, Pius X, Benedict XV, Pius XI, Pius XII.", "Pio IX, Leão XIII, Pio X, Bento XV, Pio XI e Pio XII."),
            ecumenicalCouncils = LocalizedText("Concilio Vaticano I (1869-1870): Dei Filius e Pastor Aeternus.", "First Vatican Council (1869-1870): Dei Filius and Pastor Aeternus.", "I Concílio do Vaticano: Dei Filius e Pastor Aeternus."),
            historicalSaints = LocalizedText("San Juan Bosco, Santa Teresa de Lisieux, San Pío de Pietrelcina, Santa Bernadette, San Maximiliano Kolbe.", "St. John Bosco, St. Therese of Lisieux, St. Padre Pio, St. Bernadette, St. Maximilian Kolbe.", "São João Bosco, Santa Teresa de Lisieux, São Padre Pio, Santa Bernadette, São Maximiliano Kolbe.")
        )
    )

    val churchSchisms: List<ChurchSchismHistory> = listOf(
        ChurchSchismHistory(
            id = "east_west_1054",
            title = LocalizedText("El Gran Cisma de Oriente (Iglesia Ortodoxa)", "The Great East-West Schism of 1054", "O Grande Cisma do Oriente (1054)"),
            historicalYear = "1054 d.C.",
            context = LocalizedText(
                "Separación formal entre la Iglesia Católica en Roma (Occidente) y las Iglesias de rito bizantino encabezadas por el Patriarcado de Constantinopla (Oriente). Acontecimientos de distanciamiento cultural, político (Imperio Bizantino vs Sacro Imperio) y eclesial culminaron en la ruptura de comunión en 1054.",
                "The separation between the Western Catholic Church led by Rome and the Eastern Byzantine Churches led by Constantinople. Cultural, linguistic, and geopolitical rivalries aggravated theological disputes.",
                "A separação histórica entre a Igreja Católica Romana no Ocidente e as Igrejas Ortodoxas Bizantinas no Oriente."
            ),
            theologicalDifferences = LocalizedText(
                "1. La cláusula 'Filioque' en el Credo (los latinos afirman que el Espíritu Santo procede del Padre 'y del Hijo', mientras los orientales preservan 'que procede del Padre a través del Hijo').\n2. El Primado Papal (Oriente reconoce al Obispo de Roma un 'primado de honor', mientras la Iglesia Católica enseña el primado universal de jurisdicción confiado por Cristo a San Pedro en Mt 16, 18).\n3. Uso de pan ácimo (Occidente) vs pan leudado (Oriente) para la Eucaristía.",
                "1. The 'Filioque' clause in the Nicene-Constantinopolitan Creed.\n2. Papal universal primacy and jurisdiction vs primacy of honor.\n3. Unleavened bread (Western) vs leavened bread (Eastern) for the Eucharist.",
                "1. A questão do Filioque no Credo Niceno.\n2. O primado de jurisdição do Papa sobre a Igreja universal.\n3. Tradições litúrgicas e uso do pão ázimo ou fermentado."
            ),
            catholicPerspective = LocalizedText(
                "La Iglesia Católica reconoce que las Iglesias Ortodoxas poseen verdadera Sucesión Apostólica, sacerdocio válido y los siete Sacramentos. Se les considera 'Iglesias hermanas' con las cuales falta solo la plena comunión con la Cátedra de Pedro.",
                "The Catholic Church recognizes that Eastern Orthodox Churches possess valid Apostolic Succession, valid priesthood, and all seven Sacraments, considering them beloved sister Churches.",
                "A Igreja Católica reconhece que as Igrejas Ortodoxas possuem sucessão apostólica legítima e verdadeiros sacramentos, sendo chamadas Igrejas irmãs."
            ),
            dialogueAndReconciliation = LocalizedText(
                "En 1965, el Papa San Pablo VI y el Patriarca Ecuménico Atenágoras I levantaron solemnemente las mutuas excomuniones de 1054. San Juan Pablo II pidió a la Iglesia 'respirar con sus dos pulmones' (Oriente y Occidente).",
                "In 1965, Pope St. Paul VI and Patriarch Athenagoras I formally revoked the 1054 excommunications. St. John Paul II urged the Church to 'breathe with both lungs'.",
                "Em 1965 foram revogadas as excomunhões mútuas e intensificou-se o diálogo de oração pela plena unidade visible dos cristãos."
            )
        ),
        ChurchSchismHistory(
            id = "reformation_1517",
            title = LocalizedText("La Reforma Protestante del Siglo XVI", "The Protestant Reformation (1517)", "A Reforma Protestante (1517)"),
            historicalYear = "1517 d.C.",
            context = LocalizedText(
                "Iniciada con la publicación de las 95 tesis de Martín Lutero en Wittenberg, derivó en una fractura masiva de la cristiandad occidental y en el nacimiento de diversas denominaciones (Luteranos, Calvinistas, Anglicanos, Anabaptistas).",
                "Triggered by Martin Luther's 95 Theses in 1517, leading to theological division across Western Europe and the formation of Protestant denominations.",
                "Iniciada por Martinho Lutero em 1517, dividiu a cristandade ocidental e deu origem às confissões evangélicas e protestantes."
            ),
            theologicalDifferences = LocalizedText(
                "1. 'Sola Scriptura': El protestantismo postula que solo la Biblia es norma de fe, excluyendo la Sagrada Tradición apostólica y el Magisterio de la Iglesia.\n2. 'Sola Fide': Sostienen que el hombre se justifica solo por la fe sin necesidad de obras, mientras la doctrina católica (Santiago 2, 24) enseña que la fe obra por la caridad.\n3. Canon Bíblico: Lutero retiró 7 libros del Antiguo Testamento (Deuterocanónicos), dejando 66 libros en vez de los 73 católicos.\n4. Sacramentos: Redujeron los siete Sacramentos a solo dos (Bautismo y Cena), negando la Transustanciación real y el sacerdocio ministerial ministerial católico.\n5. Rechazo a la mediación de la Virgen María, devoción a los santos y el Purgatorio.",
                "1. Sola Scriptura vs Sacred Scripture, Sacred Tradition & Magisterium.\n2. Sola Fide vs Faith working through Charity (James 2:24).\n3. Bible Canon: Omission of 7 Old Testament books.\n4. Reduction from Seven Sacraments to two, denying ministerial priesthood.\n5. Rejection of Purgatory, intercession of Saints, and Marian dogmas.",
                "1. Sola Scriptura vs Escritura, Tradição Apostólica e Magistério.\n2. Sola Fide vs Fé que atua pela caridade.\n3. Exclusão de 7 livros do Antigo Testamento.\n4. Rejeição de 5 sacramentos e do sacerdócio ministerial.\n5. Negação do Purgatório e da intercessão dos Santos e de Maria Santíssima."
            ),
            catholicPerspective = LocalizedText(
                "El Concilio de Trento (1545-1563) clarificó exhaustivamente que la Revelación Divina nos llega tanto por la Sagrada Escritura como por la Sagrada Tradición apostólica guiada por el Magisterio de la Iglesia.",
                "The Council of Trent definitively reaffirmed that Divine Revelation is preserved through both Sacred Scripture and Apostolic Tradition, guided by the living Magisterium.",
                "O Concílio de Trento reafirmou a integridade da fé católica, a beleza dos sete sacramentos e o valor da Sagrada Tradição."
            ),
            dialogueAndReconciliation = LocalizedText(
                "El decreto 'Unitatis Redintegratio' del Vaticano II promueve el ecumenismo sincero, orando juntos al Padre por la unidad en la verdad que Cristo pidió en Juan 17: 'Que todos sean uno'.",
                "The Second Vatican Council encourages dialogue in truth and fraternal charity, fulfilling Christ's priestly prayer: 'That they may all be one' (John 17:21).",
                "A Igreja Católica promove a oração constante pela unidade de todos os cristãos na verdade do Evangelho de Cristo."
            )
        )
    )
}
