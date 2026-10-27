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
        reference = "Filipenses 4, 6-7",
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
            feastDay = "8 de Septiembre",
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
            feastDay = "16 de Julio",
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
            feastDay = "12 de Diciembre",
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
            feastDay = "8 de Septiembre",
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
            feastDay = "12 de Octubre",
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
            feastDay = "8 de Septiembre y 11 de Septiembre",
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
            feastDay = "8 de Mayo",
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
            feastDay = "9 de Julio y 18 de Noviembre",
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
            feastDay = "13 de Mayo",
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
            feastDay = "11 de Febrero",
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
            feastDay = "12 de Octubre",
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
            feastDay = "27 de Noviembre",
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
