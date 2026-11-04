package com.example.data.repository

import com.example.data.models.CapitalSinVsVirtue
import com.example.data.models.CatholicNewsArticle
import com.example.data.models.ChurchPrecept
import com.example.data.models.CommandmentExploration
import com.example.data.models.FaithCourse
import com.example.data.models.FaithCourseDay
import com.example.data.models.GregorianChantItem
import com.example.data.models.LocalizedText
import com.example.data.models.NearbyParish
import com.example.data.models.SinTheologyExplanation
import com.example.data.models.TriviaQuestion
import com.example.data.models.ViaCrucisStation

object DoctrineAndSpiritualData {

    val theTenCommandments: List<CommandmentExploration> = listOf(
        CommandmentExploration(
            number = 1,
            title = LocalizedText("1°: Amarás a Dios sobre todas las cosas", "1st: You shall love the Lord your God above all things", "1º: Amar a Deus sobre todas as coisas"),
            biblicalWording = LocalizedText("«Yo soy el Señor tu Dios... No tendrás otros dioses fuera de mí» (Éxodo 20, 2-3)", "“I am the Lord your God... You shall have no other gods before me” (Exodus 20:2-3)", "“Eu sou o Senhor teu Deus... Não terás outros deuses diante de Mim” (Êxodo 20, 2-3)"),
            moralExplanation = LocalizedText(
                "Manda adorar solo al Dios Único y Verdadero. Pone a Dios como centro soberano de nuestra vida, tiempo, afectos y decisiones.",
                "Commands adoration of the one true God, placing Him above money, power, superstition, and worldly ambition.",
                "Ordena adorar ao único Deus verdadeiro, colocando-O em primeiro lugar no coração e na vida."
            ),
            sinsForbidden = LocalizedText("Idolatría, superstición, brujería, horóscopos, ateísmo, desconfianza en Dios y sacrilegio.", "Idolatry, occultism, divination, horoscopes, practical atheism, and sacrilege.", "Idolatria, superstição, ocultismo, espiritismo, descrença e sacrilégio."),
            virtuesCommanded = LocalizedText("Fe viva, Esperanza filial, Caridad ardiente, Oración y Virtud de la Religión.", "Theological virtues of Faith, Hope, Charity, and the virtue of divine worship.", "Fé, Esperança, Caridade e adoração piedosa.")
        ),
        CommandmentExploration(
            number = 2,
            title = LocalizedText("2°: No tomarás el Nombre de Dios en vano", "2nd: You shall not take the name of the Lord in vain", "2º: Não tomar seu Santo Nome em vão"),
            biblicalWording = LocalizedText("«No tomarás en falso el nombre del Señor tu Dios» (Éxodo 20, 7)", "“You shall not take the name of the Lord your God in vain” (Exodus 20:7)", "“Não pronunciarás em vão o nome do Senhor teu Deus” (Êxodo 20, 7)"),
            moralExplanation = LocalizedText(
                "Manda respetar la santidad del Nombre del Altísimo, de Jesucristo, de la Virgen María y de todos los santos.",
                "Commands reverence for God's holy name, oaths, and sacred vows made before God.",
                "Ordena o respeito absoluto ao Santo Nome de Deus, de Jesus, de Nossa Senhora e dos santos."
            ),
            sinsForbidden = LocalizedText("Blasfemia, jurar en falso o sin necesidad, imprecaciones y uso frívolo o burlón de cosas sagradas.", "Blasphemy, perjury, false swearing, profanity, and cursing.", "Blasfêmia, falso juramento e uso desrespeitoso das coisas sagradas."),
            virtuesCommanded = LocalizedText("Reverencia, bendición, alabanza y testimonio fiel de Cristo.", "Reverence for the holy name, praise, and keeping sacred promises.", "Reverência filial e testemunho santo.")
        ),
        CommandmentExploration(
            number = 3,
            title = LocalizedText("3°: Santificarás las fiestas y el Día del Señor", "3rd: Remember to keep holy the Lord's Day", "3º: Guardar domingos e festas de guarda"),
            biblicalWording = LocalizedText("«Recuerda el día del sábado para santificarlo» (Éxodo 20, 8)", "“Remember the sabbath day, to keep it holy” (Exodus 20:8)", "“Lembra-te do dia de sábado, para o santificar” (Êxodo 20, 8)"),
            moralExplanation = LocalizedText(
                "Para los católicos el domingo es el Día del Señor en memoria de la Resurrección de Cristo. Obliga a participar en la Santa Misa y al descanso que nutre el alma y la familia.",
                "Fulfilling the Sunday Eucharistic obligation, resting from servile labor, and fostering prayer with family.",
                "Participação na Santa Missa dominical e dedicação do dia ao repouso e à convivência fraterna."
            ),
            sinsForbidden = LocalizedText("Faltar deliberadamente a Misa dominical sin causa grave y realizar trabajos que impidan el culto debido a Dios.", "Missing Sunday Mass deliberately (grave sin without just reason) and unnecessary servile work.", "Faltar à Santa Missa por negligência e trabalhar sem necessidade real."),
            virtuesCommanded = LocalizedText("Piedad litúrgica, comunión fraterna, caridad y descanso espiritual.", "Liturgical worship, joy in the Resurrection, and works of mercy.", "Comunhão com Deus e caridade com os pobres.")
        ),
        CommandmentExploration(
            number = 4,
            title = LocalizedText("4°: Honrarás a tu padre y a tu madre", "4th: Honor your father and your mother", "4º: Honrar pai e mãe"),
            biblicalWording = LocalizedText("«Honra a tu padre y a tu madre, para que se prolonguen tus días» (Éxodo 20, 12)", "“Honor your father and your mother, that your days may be long” (Exodus 20:12)", "“Honra teu pai e tua mãe, para que se prolonguem os teus dias” (Êxodo 20, 12)"),
            moralExplanation = LocalizedText(
                "Exige amor, gratitud, respeto y cuidado filial hacia los padres, abuelos y educadores, y de los padres hacia la formación cristiana de los hijos.",
                "Calls for filial gratitude, obedience, assistance in their old age, and respect for legitimate authorities.",
                "Obriga a amar, respeitar e amparar os pais na velhice e educar os filhos na fé católica."
            ),
            sinsForbidden = LocalizedText("Desobediencia injusta, desprecio, abandono de los padres ancianos o enfermos y faltas de respeto en el hogar.", "Disrespect, abandonment of elderly parents, family strife, and neglect of children's spiritual education.", "Desobediência, desprezo aos pais e descuido da família."),
            virtuesCommanded = LocalizedText("Piedad familiar, concordia doméstica, respeto intergeneracional.", "Filial love, domestic harmony, and responsible citizenship.", "Amor familiar e honra aos idosos.")
        ),
        CommandmentExploration(
            number = 5,
            title = LocalizedText("5°: No matarás", "5th: You shall not kill", "5º: Não matar"),
            biblicalWording = LocalizedText("«No matarás» (Éxodo 20, 13)", "“You shall not kill” (Exodus 20:13)", "“Não matarás” (Êxodo 20, 13)"),
            moralExplanation = LocalizedText(
                "La vida humana es sagrada desde la concepción hasta la muerte natural porque viene de Dios. Manda custodiar la salud y la paz.",
                "Human life is sacred from conception to natural death. Also forbids hatred, violence, and self-harm.",
                "A vida humana é sagrada desde a concepção até o fim natural. Proíbe qualquer atentado à vida e ao corpo."
            ),
            sinsForbidden = LocalizedText("Homicidio, aborto procurado, eutanasia, suicidio, odio rencoroso, venganza y escándalo que destruye almas.", "Murder, abortion, euthanasia, suicide, hatred, rage, and scandal that leads others into sin.", "Homicídio, aborto, eutanásia, ódio e agressões."),
            virtuesCommanded = LocalizedText("Respeto por la vida, mansedumbre, perdón a los enemigos y cuidado de la salud.", "Meekness, forgiveness, peacemaking, and care for vulnerable lives.", "Mansidão, perdão e promoção da paz.")
        ),
        CommandmentExploration(
            number = 6,
            title = LocalizedText("6°: No cometerás actos impuros", "6th: You shall not commit adultery", "6º: Não cometer atos impuros"),
            biblicalWording = LocalizedText("«No cometerás adulterio» (Éxodo 20, 14)", "“You shall not commit adultery” (Exodus 20:14)", "“Não cometerás adultério” (Êxodo 20, 14)"),
            moralExplanation = LocalizedText(
                "Protege la santidad del amor conyugal, la pureza y la castidad según el estado de vida de cada persona (soltero, consagrado o casado).",
                "Guards marital fidelity, bodily modesty, and the virtue of chastity according to one's state of life.",
                "Guarda a santidade do matrimônio e a pureza do coração e do corpo segundo o estado de vida."
            ),
            sinsForbidden = LocalizedText("Adulterio, fornicación, pornografía, masturbación, relaciones fuera del matrimonio y vestimenta provocadora.", "Adultery, fornication, pornography, lustful actions, and impure relations outside sacramental marriage.", "Adultério, pornografia e fornicação."),
            virtuesCommanded = LocalizedText("Castidad cristiana, pudor, fidelidad conyugal y autodominio.", "Chastity, modesty, self-mastery, and true spousal love.", "Castidade, modéstia e fidelidade.")
        ),
        CommandmentExploration(
            number = 7,
            title = LocalizedText("7°: No robarás", "7th: You shall not steal", "7º: Não furtar"),
            biblicalWording = LocalizedText("«No robarás» (Éxodo 20, 15)", "“You shall not steal” (Exodus 20:15)", "“Não furtarás” (Êxodo 20, 15)"),
            moralExplanation = LocalizedText(
                "Manda la justicia y la caridad en el uso de los bienes materiales y el respeto a la propiedad del prójimo.",
                "Demands justice, honesty in business, fair wages, and stewardship of God's creation.",
                "Exige honestidade, justiça social, salários dignos e respeito aos bens alheios."
            ),
            sinsForbidden = LocalizedText("Robo, fraude, corrupción, estafa, no pagar salarios justos y dañar bienes ajenos o públicos.", "Theft, fraud, business deceit, unjust wages, corruption, and vandalism.", "Roubo, corrupção, fraudes e retenção de salários."),
            virtuesCommanded = LocalizedText("Honestidad, justicia conmutativa, restitución del daño y generosidad con los pobres.", "Honesty, restitution of stolen goods, and generous almsgiving.", "Honestidade, esmola e desapego.")
        ),
        CommandmentExploration(
            number = 8,
            title = LocalizedText("8°: No darás falso testimonio ni mentirás", "8th: You shall not bear false witness against your neighbor", "8º: Não levantar falso testemunho"),
            biblicalWording = LocalizedText("«No darás falso testimonio contra tu prójimo» (Éxodo 20, 16)", "“You shall not bear false witness against your neighbor” (Exodus 20:16)", "“Não dirás falso testemunho contra o teu próximo” (Êxodo 20, 16)"),
            moralExplanation = LocalizedText(
                "Exige hablar siempre con la verdad y defender la buena fama del prójimo.",
                "Commands truthfulness in word and deed, guarding our neighbor's good reputation.",
                "Ordena viver na verdade e defender a boa fama de todos os irmãos."
            ),
            sinsForbidden = LocalizedText("Mentira, calumnia, difamación, murmuración, juicio temerario y revelación indebida de secretos.", "Lying, calumny, defamation, gossip, rash judgment, and breach of trust.", "Mentira, fofoca, calúnia e difamação."),
            virtuesCommanded = LocalizedText("Veracidad, sinceridad, discreción y caridad en la palabra.", "Veracity, sincerity, discretion, and restitution of honor.", "Veracidade e prudência nas palavras.")
        ),
        CommandmentExploration(
            number = 9,
            title = LocalizedText("9°: No consentirás pensamientos ni deseos impuros", "9th: You shall not covet your neighbor's spouse", "9º: Não desejar a mulher do próximo"),
            biblicalWording = LocalizedText("«No codiciarás la mujer de tu prójimo» (Éxodo 20, 17)", "“You shall not covet your neighbor's wife” (Exodus 20:17)", "“Não cobiçarás a mulher do teu próximo” (Êxodo 20, 17)"),
            moralExplanation = LocalizedText(
                "Manda la pureza de intención en la mente y el corazón: 'Bienaventurados los limpios de corazón, porque ellos verán a Dios'.",
                "Commands custody of the eyes and purity of thoughts and interior desires.",
                "Ordena a pureza interior de pensamentos e desejos da alma."
            ),
            sinsForbidden = LocalizedText("Deleitarse voluntariamente en fantasías impuras y codiciar la pareja del prójimo.", "Deliberate lustful daydreaming, concupiscence of the eyes, and interior infidelity.", "Pensamentos impuros consentidos e fantasias pecaminosas."),
            virtuesCommanded = LocalizedText("Pureza de corazón, custodia de la mirada y oración vigilante.", "Custody of the heart, modesty of sight, and holy thoughts.", "Pureza interior e oração constante.")
        ),
        CommandmentExploration(
            number = 10,
            title = LocalizedText("10°: No codiciarás los bienes ajenos", "10th: You shall not covet your neighbor's goods", "10º: Não cobiçar as coisas alheias"),
            biblicalWording = LocalizedText("«No codiciarás la casa ni nada que sea de tu prójimo» (Éxodo 20, 17)", "“You shall not covet anything that belongs to your neighbor” (Exodus 20:17)", "“Não cobiçarás coisa alguma que pertença ao teu próximo” (Êxodo 20, 17)"),
            moralExplanation = LocalizedText(
                "Prohíbe la avaricia y la envidia, que es la tristeza ante el bien ajeno.",
                "Forbids greed and envy, directing our hearts toward spiritual riches in Christ.",
                "Proíbe a ganância, a avareza e a inveja dos bens dos outros."
            ),
            sinsForbidden = LocalizedText("Envidia, avaricia desenfrenada, deseo de que a otros les vaya mal y apego desmedido a las riquezas.", "Envy, greed, sorrow at another's fortune, and worship of money.", "Inveja, cobiça e ambição desmedida."),
            virtuesCommanded = LocalizedText("Gratitud, generosidad, alegría por el bien del prójimo y pobreza evangélica.", "Contentment, rejoicing in another's blessings, and reliance on God.", "Gratidão, generosidade e espírito de partilha.")
        )
    )

    val churchPrecepts: List<ChurchPrecept> = listOf(
        ChurchPrecept(
            number = 1,
            title = LocalizedText("1°: Oír Misa entera todos los domingos y fiestas de guardar", "1st: Attend Mass on Sundays and Holy Days of Obligation", "1º: Participar da Santa Missa nos domingos e festas"),
            explanation = LocalizedText("Santificar el Día del Señor asistiendo a la Santa Eucaristía.", "Participating fully in the Eucharistic sacrifice on all obligatory days.", "Santificar os domingos com a presença viva na Eucaristia."),
            applicationInLife = LocalizedText("Obliga bajo pecado grave salvo impedimento de enfermedad o fuerza mayor.", "Binds under grave matter unless excused by illness or emergency.", "Compromisso moral de fidelidade à comunidade e a Cristo.")
        ),
        ChurchPrecept(
            number = 2,
            title = LocalizedText("2°: Confesar los pecados mortales al menos una vez al año", "2nd: Confess serious sins at least once a year", "2º: Confessar-se ao menos uma vez ao ano"),
            explanation = LocalizedText("Acudir al Sacramento de la Penitencia con verdadero arrepentimiento.", "Receiving sacramental absolution when in mortal sin.", "Buscar a misericórdia de Deus no sacramento da Reconciliação."),
            applicationInLife = LocalizedText("Y siempre que se esté en peligro de muerte o antes de comulgar si hay pecado grave.", "Also whenever preparing to receive Holy Communion in mortal sin.", "Fundamental para manter a alma em estado de graça santificante.")
        ),
        ChurchPrecept(
            number = 3,
            title = LocalizedText("3°: Comulgar al menos por Pascua de Resurrección", "3rd: Receive the Eucharist at least during Easter season", "3º: Comungar ao menos pela Páscoa da Ressurreição"),
            explanation = LocalizedText("Unirse a Cristo en la Sagrada Comunión en el tiempo pascual.", "Nourishing our souls with Christ's Body and Blood during Eastertide.", "Unir-se ao Salvador no mistério de Sua ressurreição pascal."),
            applicationInLife = LocalizedText("La Iglesia recomienda encarecidamente la comunión frecuente si se está en gracia de Dios.", "Daily or weekly communion is passionately recommended in state of grace.", "Recomenda-se a comunhão em cada Santa Missa com a alma limpa.")
        ),
        ChurchPrecept(
            number = 4,
            title = LocalizedText("4°: Ayunar y abstenerse de comer carne cuando lo manda la Iglesia", "4th: Observe days of fasting and abstinence", "4º: Jejuar e abster-se de carne quando prescrito"),
            explanation = LocalizedText("Práctica de penitencia: ayuno y abstinencia el Miércoles de Ceniza y Viernes Santo, y abstinencia los viernes de Cuaresma.", "Penitential fasting on Ash Wednesday and Good Friday; abstinence from meat on Fridays.", "Mortificação voluntária para nos unirmos à Paixão de Cristo."),
            applicationInLife = LocalizedText("El ayuno educa la voluntad y despierta la solidaridad con los que tienen hambre.", "Trains self-control and opens our hearts to the poor.", "Educa o espírito e purifica o coração.")
        ),
        ChurchPrecept(
            number = 5,
            title = LocalizedText("5°: Ayudar a la Iglesia en sus necesidades materiales", "5th: Provide for the material needs of the Church", "5º: Ajudar a Igreja em suas necessidades"),
            explanation = LocalizedText("Contribuir según las posibilidades al sostenimiento del culto, clero y obras de caridad.", "Supporting pastoral ministries, parishes, missions, and the poor according to ability.", "Sustento do culto divino e assistência aos mais necessitados."),
            applicationInLife = LocalizedText("Mediante el diezmo, limosnas y colaboración voluntaria en la parroquia.", "Through tithes, donations, and personal service.", "Participação generosa e responsável na vida comunitária.")
        )
    )

    val capitalSinsVsVirtues: List<CapitalSinVsVirtue> = listOf(
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Soberbia / Orgullo", "Pride / Arrogance", "Soberba / Orgulho"),
            sinMeaning = LocalizedText("Amor desordenado de la propia excelencia y raíz de todos los demás pecados.", "Inordinate self-love and claiming for oneself what belongs to God.", "Amor desordenado de si mesmo e autossuficiência diante de Deus."),
            opposingVirtue = LocalizedText("Humildad", "Humility", "Humildade"),
            virtueRemedy = LocalizedText("Reconocer que todo don viene de Dios y ponerse al servicio sincero de los demás.", "Recognizing that every gift comes from God; imitating the humble Christ.", "Reconhecer nossa pequenez e acolher a graça do Senhor."),
            practicalAdvice = LocalizedText("Acepta con serenidad las correcciones y cede el primer lugar a tu hermano.", "Gladly accept constructive corrections without self-defense.", "Pratique o silêncio e evite se vangloriar dos próprios méritos.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Avaricia", "Greed / Avarice", "Avareza"),
            sinMeaning = LocalizedText("Apego idolátrico al dinero y a las posesiones materiales.", "Disordered desire for wealth, possessions, and worldly power.", "Apego egoísta ao dinheiro e às coisas materiais."),
            opposingVirtue = LocalizedText("Generosidad / Desprendimiento", "Generosity / Liberality", "Generosidade / Partilha"),
            virtueRemedy = LocalizedText("Recordar que somos administradores temporales de los bienes de Dios.", "Remembering that we are temporary stewards of God's earth.", "Praticar a esmola e o desprendimento do coração."),
            practicalAdvice = LocalizedText("Dona periódicamente a los pobres y comparte lo que tienes con alegría.", "Give alms quietly to someone in genuine necessity.", "Faça doações aos pobres e ajude quem passa necessidade.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Lujuria", "Lust", "Luxúria"),
            sinMeaning = LocalizedText("Deseo desordenado o disfrute egoísta del placer sexual fuera del matrimonio.", "Inordinate desire for sexual pleasure detached from sacramental love and procreation.", "Busca desordenada do prazer da carne fora do plano de Deus."),
            opposingVirtue = LocalizedText("Castidad / Pureza", "Chastity / Purity", "Castidade / Pureza"),
            virtueRemedy = LocalizedText("Tratar el cuerpo propio y ajeno como templo vivo del Espíritu Santo (1 Cor 6).", "Viewing the human body as a living temple of the Holy Trinity.", "Guardar os sentidos e consagrar a alma à Virgem Maria."),
            practicalAdvice = LocalizedText("Custodia la mirada en internet y acude a María ante las tentaciones.", "Guard your eyes, avoid near occasions, and pray the Hail Mary instantly.", "Evite ocasiões de pecado e reze a Ave Maria nas tentações.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Ira", "Wrath / Anger", "Ira"),
            sinMeaning = LocalizedText("Deseo desordenado de venganza o violencia destructiva en el corazón.", "Disordered passion for revenge, hostility, and bitter resentment.", "Rancor amargo e desejo descontrolado de vingança."),
            opposingVirtue = LocalizedText("Paciencia / Mansedumbre", "Patience / Meekness", "Paciência / Mansidão"),
            virtueRemedy = LocalizedText("Contemplar a Cristo crucificado perdonando a sus verdugos.", "Contemplating Christ on the Cross praying: 'Father, forgive them.'", "Olhar para Jesus manso e humilde de coração."),
            practicalAdvice = LocalizedText("Antes de responder cuando estés enojado, reza un Padre Nuestro.", "Pause, take three breaths, and pray for the person irritating you.", "Respire fundo e faça uma oração antes de responder na raiva.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Gula", "Gluttony", "Gula"),
            sinMeaning = LocalizedText("Deseo desordenado en el comer o beber por encima de la templanza.", "Inordinate appetite for food or drink exceeding bodily reason.", "Excesso e apego desordenado à comida e à bebida."),
            opposingVirtue = LocalizedText("Templanza / Sobriedad", "Temperance / Sobriety", "Temperança / Sobriedade"),
            virtueRemedy = LocalizedText("Comer para vivir y glorificar a Dios, practicando el ayuno cristiano.", "Eating to sustain life and glorifying God through fasting.", "Praticar o jejum e a moderação nas refeições."),
            practicalAdvice = LocalizedText("Renuncia a un gusto voluntario en cada comida en señal de penitencia.", "Fast from sweets or snacks once a week as an act of prayer.", "Faça pequenas renúncias voluntárias nas refeições.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Envidia", "Envy", "Inveja"),
            sinMeaning = LocalizedText("Tristeza por el bien o éxito ajeno y alegría ante su caída.", "Sorrow over another's spiritual or material well-being.", "Tristeza pelo sucesso do irmão e desgosto com a graça alheia."),
            opposingVirtue = LocalizedText("Caridad / Amor fraterno", "Charity / Kindness", "Caridade / Benevolência"),
            virtueRemedy = LocalizedText("Alegrarse sinceramente de las bendiciones de los demás como miembros del mismo Cuerpo.", "Rejoicing in our brother's joy as fellow limbs of Christ's Body.", "Abençoar o próximo e alegrar-se com as conquistas dos irmãos."),
            practicalAdvice = LocalizedText("Felicita de corazón a quien tenga éxito y reza por él en silencio.", "Offer a sincere compliment and pray for that person's holiness.", "Elogie com sinceridade e reze pelo bem de quem você inveja.")
        ),
        CapitalSinVsVirtue(
            capitalSin = LocalizedText("Pereza / Acedia", "Sloth / Acedia", "Preguiça / Acídia"),
            sinMeaning = LocalizedText("Desgana o apatía culpable ante las exigencias del amor a Dios y al prójimo.", "Spiritual lethargy and resistance to the divine demands of charity.", "Frieza espiritual e desânimo culpável no serviço de Deus."),
            opposingVirtue = LocalizedText("Diligencia / Celo por Dios", "Diligence / Holy Fervor", "Diligência / Fervor"),
            virtueRemedy = LocalizedText("Cumplir con amor y puntualidad los deberes diarios y la oración personal.", "Faithfulness in daily routine and persevering prayer.", "Perseverança na vida de oração e nos deveres cotidianos."),
            practicalAdvice = LocalizedText("Comienza tu oración a una hora fija sin postergarla jamás.", "Set fixed times for prayer and stick to them faithfully.", "Estabeleça horários certos para rezar e não os adie.")
        )
    )

    val sinTheology = SinTheologyExplanation(
        venialExplanation = LocalizedText(
            "El pecado venial no destruye la gracia santificante en el alma, pero debilita la caridad, traba el progreso espiritual y dispone al pecado mortal si se descuida. Se borra con el acto sincero de contrición, la oración, el agua bendita y la comunión sacramental.",
            "Venial sin offends God and wounds charity, but does not rupture our covenant of sanctifying grace. It is cleansed by contrition, the Confiteor, and holy communion.",
            "O pecado venial fere a caridade sem destruir a graça santificante. É perdoado pela contrição sincera, oração e Santa Eucaristia."
        ),
        mortalExplanation = LocalizedText(
            "El pecado mortal destruye totalmente la caridad en el corazón del hombre por una infracción grave de la ley de Dios. Separa de la comunión con Dios y priva del Cielo eterno si no hay arrepentimiento. Requiere imperativamente el Sacramento de la Confesión con un sacerdote.",
            "Mortal sin destroys sanctifying grace by a grave violation of God's law. It cuts the soul off from heaven until restored through sacramental confession.",
            "O pecado mortal destrói a graça santificante e priva da comunhão com Deus. Exige a confissão sacramental individual com um sacerdote."
        ),
        mortalThreeConditions = listOf(
            LocalizedText("1. Materia Grave: Que la acción sea intrínsecamente grave (ej. homicidio, adulterio, blasfemia, robo importante).", "1. Grave Matter: The act itself is a serious transgression of the Ten Commandments.", "1. Matéria Grave: A falta deve ser grave segundo a Lei de Deus."),
            LocalizedText("2. Plena Conciencia (Conocimiento): Que la persona sepa con certeza que lo que va a hacer es pecado grave.", "2. Full Knowledge: Knowing clearly that the act is sinful and opposed to God's will.", "2. Pleno Conhecimento: Saber conscientemente que a ação é pecado grave."),
            LocalizedText("3. Deliberado Consentimiento: Que la voluntad sea libre y decida cometerlo a pesar de todo.", "3. Deliberate Consent: A sufficiently deliberate choice of the will to commit the act.", "3. Pleno Consentimento: Vontade livre e decidida em realizar o ato.")
        ),
        confessionExamenGuide = LocalizedText(
            "Pasos para una Santa Confesión:\n1. Examen de conciencia detallado.\n2. Dolor de los pecados (arrepentimiento sincero).\n3. Propósito firme de no volver a pecar.\n4. Decir los pecados al sacerdote sin callar nada por vergüenza.\n5. Cumplir la penitencia impuesta.",
            "Five Steps to a Fruitful Confession:\n1. Examination of conscience.\n2. Sorrow for sins (contrition).\n3. Firm purpose of amendment.\n4. Sincere confession to the priest.\n5. Fulfilling the penance given.",
            "Cinco Passos para uma Boa Confissão:\n1. Exame de consciência sincero.\n2. Arrependimento de coração.\n3. Firme propósito de emenda.\n4. Confissão íntegra dos pecados ao sacerdote.\n5. Cumprimento da penitência."
        )
    )

    val gregorianChants: List<GregorianChantItem> = listOf(
        GregorianChantItem(
            id = "salve_regina",
            title = "Salve Regina (Canto Gregoriano Simple)",
            liturgicalSeason = LocalizedText("Tiempo Ordinario / Todo el año", "Ordinary Time / Year-round", "Tempo Comum"),
            description = LocalizedText("La más venerable antífona mariana de la liturgia latina.", "The timeless Marian chant sung at Compline.", "A mais sublime antífona mariana entoada no fim do dia."),
            latinLyrics = "Salve, Regína, Mater misericórdiae;\nvita, dulcédo, et spes nostra, salve.\nAd te clamámus, éxsules fílii Hevae;\nad te suspirámus, geméntes et flentes\nin hac lacrimárum valle.\nEia ergo, advocáta nostra,\nillos tuos misericórdes óculos ad nos convérte;\net Jesum, benedíctum fructum ventris tui,\nnobis post hoc exsílium osténde.\nO clemens, o pia, o dulcis Virgo María.",
            translatedLyrics = LocalizedText(
                "Dios te salve, Reina y Madre de misericordia, vida, dulzura y esperanza nuestra, Dios te salve. A ti clamamos los desterrados hijos de Eva; a ti suspiramos, gimiendo y llorando en este valle de lágrimas. Ea, pues, Señora, abogada nuestra, vuelve a nosotros esos tus ojos misericordiosos; y después de este destierro muéstranos a Jesús, fruto bendito de tu vientre. ¡Oh clementísima, oh piadosa, oh dulce Virgen María!",
                "Hail, holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve; to thee do we send up our sighs, mourning and weeping in this valley of tears. Turn then, most gracious advocate, thine eyes of mercy toward us; and after this our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary!",
                "Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos os degredados filhos de Eva. A vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei, e depois deste desterro mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria!"
            )
        ),
        GregorianChantItem(
            id = "tantum_ergo",
            title = "Tantum Ergo Sacramentum",
            liturgicalSeason = LocalizedText("Adoración Eucarística y Bendición", "Eucharistic Adoration and Benediction", "Adoração ao Santíssimo"),
            description = LocalizedText("Compuesto por Santo Tomás de Aquino para la fiesta de Corpus Christi.", "Hymn by St. Thomas Aquinas sung before Eucharistic Benediction.", "Hino eucarístico de São Tomás de Aquino para a Bênção solene."),
            latinLyrics = "Tantum ergo Sacraméntum\nvenerémur cérnui:\net antíquum documéntum\nnovo cedat rítui:\npræstet fides suppleméntum\nsénsuum deféctui.\n\nGenitóri, Genitóque\nlaus et iubilátio,\nsalus, honor, virtus quoque\nsit et benedíctio:\nprocedénti ab utróque\ncompar sit laudátio. Amen.",
            translatedLyrics = LocalizedText(
                "Adoremos reverentes tan grande Sacramento, y la antigua alianza ceda el paso al nuevo rito; supla la fe lo que los sentidos no alcanzan a percibir. Al Padre y al Hijo sean la alabanza, el honor, la gloria, la bendición y el poder; y al que procede de ambos sea dada igual gloria. Amén.",
                "Down in adoration falling, this great Sacrament we hail; over ancient forms departing, newer rites of grace prevail; faith for all defects supplying, where the feeble senses fail. To the everlasting Father, and the Son who reigns on high, with the Holy Spirit proceeding, be all honor, praise and might. Amen.",
                "Tão sublime sacramento adoremos nós aqui; pois o Antigo Testamento deu ao Novo o seu lugar. Venha a fé por suplemento os sentidos completar. Ao eterno Pai cantemos e a Jesus o Salvador; ao Espírito exaltemos, na Trindade eterno amor. Amém."
            )
        )
    )

    val viaCrucisStations: List<ViaCrucisStation> = listOf(
        ViaCrucisStation(
            stationNumber = 1,
            title = LocalizedText("I Estación: Jesús es condenado a muerte", "1st Station: Jesus is Condemned to Death", "1ª Estação: Jesus é condenado à morte"),
            scripture = LocalizedText("Mateo 27, 26: «Pilato les soltó a Barrabás y a Jesús, después de azotarlo, lo entregó para que fuera crucificado».", "Matthew 27:26: “Pilate released Barabbas to them; but after having Jesus scourged, he handed Him over to be crucified.”", "Mateus 27, 26: “Pilatos soltou-lhes Barrabás e entregou Jesus para ser crucificado.”"),
            meditation = LocalizedText(
                "Considera cómo Jesús, inocente y sin culpa, comparece manso ante Pilato y acepta la sentencia de muerte por nuestros pecados.",
                "Consider how Jesus, all innocence and holiness, quietly submits to unjust human condemnation to redeem our souls.",
                "Considera como Jesus, puro e santo, aceita a morte injusta por amor a ti."
            ),
            prayer = LocalizedText(
                "Te adoramos, ¡oh Cristo!, y te bendecimos, que por tu santa Cruz redimiste al mundo. Jesús mío, perdóname.",
                "We adore Thee, O Christ, and we praise Thee, because by Thy Holy Cross Thou hast redeemed the world.",
                "Nós vos adoramos, ó Cristo, e vos bendizemos, porque pela vossa santa cruz remistes o mundo."
            )
        ),
        ViaCrucisStation(
            stationNumber = 2,
            title = LocalizedText("II Estación: Jesús carga con la Cruz a cuestas", "2nd Station: Jesus Takes Up His Cross", "2ª Estação: Jesus carrega a Cruz"),
            scripture = LocalizedText("Juan 19, 17: «Jesús, cargando con su cruz, salió hacia el lugar llamado Calvario».", "John 19:17: “Carrying the cross himself, He went out to what is called the Place of the Skull.”", "João 19, 17: “Jesus, levando a sua cruz, saiu para o Calvário.”"),
            meditation = LocalizedText(
                "Jesús abraza con ternura el madero de la Cruz, cargando con el peso inmenso de todas nuestras infidelidades.",
                "Jesus embraces the heavy wood with divine love, willingly carrying the weight of all human guilt.",
                "Jesus abraça a santa cruz que os nossos pecados tornaram tão pesada."
            ),
            prayer = LocalizedText(
                "¡Oh Jesús mío!, ayúdame a llevar con paciencia y amor las cruces de cada día.",
                "O my Jesus, give me grace to shoulder the trials of daily life united to Thy Passion.",
                "Senhor Jesus, dai-me forças para carregar a minha cruz de cada dia."
            )
        ),
        ViaCrucisStation(
            stationNumber = 3,
            title = LocalizedText("III Estación: Jesús cae por primera vez bajo la Cruz", "3rd Station: Jesus Falls the First Time", "3ª Estação: Jesus cai pela primeira vez"),
            scripture = LocalizedText("Isaías 53, 4: «Él soportó nuestros sufrimientos y aguantó nuestros dolores».", "Isaiah 53:4: “Yet it was our infirmities that He bore, our sufferings that He endured.”", "Isaías 53, 4: “Ele tomou sobre si as nossas dores e suportou as nossas enfermidades.”"),
            meditation = LocalizedText(
                "La pérdida de sangre por los azotes y las espinas hace que el Salvador caiga al suelo. Sin embargo, se levanta de nuevo.",
                "Weakened by torture and agonizing loss of blood, the Savior collapses, yet rises out of burning love.",
                "Debilitado pelas dores, o Salvador cai por terra, mas ergue-se para completar o sacrifício."
            ),
            prayer = LocalizedText(
                "Señor, no permitas que me quede postrado en mis caídas; alcánzame la gracia de levantarme siempre.",
                "Lord, never let me despair after falling into sin; grant me the grace to rise promptly in repentance.",
                "Senhor, levantai-me de minhas quedas e livrai-me do pecado."
            )
        )
    )

    val triviaQuestions: List<TriviaQuestion> = listOf(
        TriviaQuestion(
            id = "t1",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText(
                "¿Cuántos libros componen el canon completo de la Biblia Católica?",
                "How many books comprise the complete Catholic Bible Canon?",
                "Quantos livros compõem o cânon completo da Bíblia Católica?"
            ),
            options = listOf(
                LocalizedText("73 libros (46 en el AT y 27 en el NT)", "73 books (46 in the OT and 27 in the NT)", "73 livros (46 no AT e 27 no NT)"),
                LocalizedText("66 libros", "66 books", "66 livros"),
                LocalizedText("70 libros", "70 books", "70 livros"),
                LocalizedText("80 libros", "80 books", "80 livros")
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText(
                "La Iglesia Católica proclamó solemnemente el canon de 73 libros en los Concilios de Hipona (393), Cartago (397) y Trento (1546), conservando intactos los 7 libros deuterocanónicos.",
                "The Catholic Canon consists of 73 inspired books confirmed at Hippo, Carthage, and Trent, preserving the 7 Old Testament deuterocanonical books.",
                "A Igreja Católica preservou os 73 livros sagrados inspirados por Deus."
            )
        ),
        TriviaQuestion(
            id = "t2",
            category = LocalizedText("Sacramentos", "The Sacraments", "Sacramentos"),
            question = LocalizedText(
                "¿Cuál es el Sacramento que la Iglesia llama 'Fuente y Culmen de toda la vida cristiana'?",
                "Which Sacrament does the Catholic Church proclaim as the 'Source and Summit of Christian Life'?",
                "Qual Sacramento é a 'Fonte e Ápice de toda a vida cristã'?"
            ),
            options = listOf(
                LocalizedText("El Santo Bautismo", "Holy Baptism", "O Santo Batismo"),
                LocalizedText("La Sagrada Eucaristía", "The Holy Eucharist", "A Santíssima Eucaristia"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
                LocalizedText("El Orden Sacerdotal", "Holy Orders", "A Ordem Sacerdotal")
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText(
                "El Concilio Vaticano II (Lumen Gentium 11) y el Catecismo (§1324) enseñan que la Eucaristía es fuente y cumbre porque en ella está realmente presente Cristo en Persona: Cuerpo, Sangre, Alma y Divinidad.",
                "The Eucharist contains the whole spiritual good of the Church, namely Christ Himself, our Pasch.",
                "Na Eucaristia está presente o próprio Jesus Cristo em Corpo, Sangue, Alma e Divindade."
            )
        ),
        TriviaQuestion(
            id = "t3",
            category = LocalizedText("Historia y Papado", "Church History & Papacy", "História da Igreja e Papado"),
            question = LocalizedText(
                "¿Quién fue el primer Papa de la Iglesia Católica elegido directamente por Jesucristo?",
                "Who was the very first Pope of the Catholic Church commissioned by Jesus Christ?",
                "Quem foi o primeiro Papa da Igreja Católica escolhido por Cristo?"
            ),
            options = listOf(
                LocalizedText("San Pablo Apóstol", "Saint Paul the Apostle", "São Paulo Apóstolo"),
                LocalizedText("San Pedro Apóstol", "Saint Peter the Apostle", "São Pedro Apóstolo"),
                LocalizedText("San Juan Evangelista", "Saint John the Evangelist", "São João Evangelista"),
                LocalizedText("San Lino", "Saint Linus", "São Lino")
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText(
                "En Mateo 16, 18, Jesús declara: 'Tú eres Pedro, y sobre esta piedra edificaré mi Iglesia'. Pedro fue el primer obispo de Roma.",
                "Christ gave Simon the name Peter (Rock) and conferred upon him the keys of the Kingdom of Heaven.",
                "Jesus confiou as chaves do Reino a Pedro para apascentar o rebanho universal."
            )
        ),
        TriviaQuestion(
            id = "t4",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Cuál es el primer libro de la Biblia?", "What is the first book of the Bible?", "Qual é o primeiro livro da Bíblia?"),
            options = listOf(
                LocalizedText("Éxodo", "Exodus", "Êxodo"),
                LocalizedText("Salmos", "Psalms", "Salmos"),
                LocalizedText("Isaías", "Isaiah", "Isaías"),
                LocalizedText("Génesis", "Genesis", "Gênesis"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El Génesis narra la creación del mundo y del ser humano, y la historia de los patriarcas.", "Genesis tells the creation of the world and humanity and the story of the patriarchs.", "O Gênesis relata a criação do mundo e do homem e a história dos patriarcas.")
        ),
        TriviaQuestion(
            id = "t5",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Cuál es el último libro de la Biblia Católica?", "What is the last book of the Catholic Bible?", "Qual é o último livro da Bíblia Católica?"),
            options = listOf(
                LocalizedText("Romanos", "Romans", "Romanos"),
                LocalizedText("Judas", "Jude", "Judas"),
                LocalizedText("Apocalipsis", "Revelation", "Apocalipse"),
                LocalizedText("Hechos de los Apóstoles", "Acts of the Apostles", "Atos dos Apóstolos"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El Apocalipsis, de San Juan, cierra la Escritura con la visión de la Jerusalén celestial.", "Revelation, by Saint John, closes Scripture with the vision of the heavenly Jerusalem.", "O Apocalipse, de São João, fecha a Escritura com a visão da Jerusalém celestial.")
        ),
        TriviaQuestion(
            id = "t6",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Cuántos libros del Antiguo Testamento hay en la Biblia Católica?", "How many Old Testament books are in the Catholic Bible?", "Quantos livros do Antigo Testamento há na Bíblia Católica?"),
            options = listOf(
                LocalizedText("43 libros", "43 books", "43 livros"),
                LocalizedText("39 libros", "39 books", "39 livros"),
                LocalizedText("46 libros", "46 books", "46 livros"),
                LocalizedText("50 libros", "50 books", "50 livros"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("La Biblia Católica conserva 46 libros del Antiguo Testamento, incluidos los deuterocanónicos.", "The Catholic Bible preserves 46 Old Testament books, including the deuterocanonical books.", "A Bíblia Católica preserva 46 livros do Antigo Testamento, incluindo os deuterocanônicos.")
        ),
        TriviaQuestion(
            id = "t7",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Quién construyó el arca para salvar a su familia del diluvio?", "Who built the ark to save his family from the flood?", "Quem construiu a arca para salvar sua família do dilúvio?"),
            options = listOf(
                LocalizedText("Noé", "Noah", "Noé"),
                LocalizedText("Jacob", "Jacob", "Jacó"),
                LocalizedText("Abraham", "Abraham", "Abraão"),
                LocalizedText("Moisés", "Moses", "Moisés"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Dios mandó a Noé construir el arca; él y su familia se salvaron con los animales.", "God commanded Noah to build the ark; he, his family, and the animals were saved.", "Deus mandou Noé construir a arca; ele, sua família e os animais foram salvos.")
        ),
        TriviaQuestion(
            id = "t8",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Dónde recibió Moisés los Diez Mandamientos?", "Where did Moses receive the Ten Commandments?", "Onde Moisés recebeu os Dez Mandamentos?"),
            options = listOf(
                LocalizedText("Monte de los Olivos", "Mount of Olives", "Monte das Oliveiras"),
                LocalizedText("Monte Tabor", "Mount Tabor", "Monte Tabor"),
                LocalizedText("Monte Carmelo", "Mount Carmel", "Monte Carmelo"),
                LocalizedText("Monte Sinaí", "Mount Sinai", "Monte Sinai"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Moisés subió al Sinaí y recibió las tablas de la Ley de Dios.", "Moses ascended Sinai and received God's tablets of the Law.", "Moisés subiu ao Sinai e recebeu as tábuas da Lei de Deus.")
        ),
        TriviaQuestion(
            id = "t9",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Cómo venció David al gigante Goliat?", "How did David defeat the giant Goliath?", "Como Davi venceu o gigante Golias?"),
            options = listOf(
                LocalizedText("Con una honda y una piedra", "With a sling and a stone", "Com uma funda e uma pedra"),
                LocalizedText("Con una lanza", "With a spear", "Com uma lança"),
                LocalizedText("Con una flecha", "With an arrow", "Com uma flecha"),
                LocalizedText("Con una espada", "With a sword", "Com uma espada"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("David, joven pastor, venció a Goliat con una honda y una piedra, confiando en Dios.", "David, a young shepherd, defeated Goliath with a sling and a stone, trusting in God.", "Davi, jovem pastor, venceu Golias com uma funda e uma pedra, confiando em Deus.")
        ),
        TriviaQuestion(
            id = "t10",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué rey pidió sabiduría a Dios y construyó el primer Templo de Jerusalén?", "Which king asked God for wisdom and built the first Temple in Jerusalem?", "Qual rei pediu sabedoria a Deus e construiu o primeiro Templo de Jerusalém?"),
            options = listOf(
                LocalizedText("David", "David", "Davi"),
                LocalizedText("Salomón", "Solomon", "Salomão"),
                LocalizedText("Saúl", "Saul", "Saul"),
                LocalizedText("Roboam", "Rehoboam", "Roboão"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Salomón pidió sabiduría y erigió el Templo de Jerusalén.", "Solomon asked for wisdom and erected the Temple of Jerusalem.", "Salomão pediu sabedoria e ergueu o Templo de Jerusalém.")
        ),
        TriviaQuestion(
            id = "t11",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué profeta fue tragado por un gran pez por desobedecer a Dios?", "Which prophet was swallowed by a great fish for disobeying God?", "Qual profeta foi engolido por um grande peixe por desobedecer a Deus?"),
            options = listOf(
                LocalizedText("Jeremías", "Jeremiah", "Jeremias"),
                LocalizedText("Jonás", "Jonah", "Jonas"),
                LocalizedText("Elías", "Elijah", "Elias"),
                LocalizedText("Isaías", "Isaiah", "Isaías"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Jonás huyó del llamado divino, fue tragado por el pez y luego predicó en Nínive.", "Jonah fled God's call, was swallowed by the fish, and later preached in Nineveh.", "Jonas fugiu do chamado divino, foi engolido pelo peixe e depois pregou em Nínive.")
        ),
        TriviaQuestion(
            id = "t12",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Quién fue echado al foso de los leones por orar a Dios?", "Who was thrown into the lions' den for praying to God?", "Quem foi lançado na cova dos leões por orar a Deus?"),
            options = listOf(
                LocalizedText("David", "David", "Davi"),
                LocalizedText("Daniel", "Daniel", "Daniel"),
                LocalizedText("José", "Joseph", "José"),
                LocalizedText("Moisés", "Moses", "Moisés"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Daniel oró a pesar de la prohibición del rey y Dios cerró la boca de los leones.", "Daniel prayed despite the king's ban and God shut the lions' mouths.", "Daniel orou apesar da proibição do rei e Deus fechou a boca dos leões.")
        ),
        TriviaQuestion(
            id = "t13",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué reina judía salvó a su pueblo ante el rey Asuero?", "Which Jewish queen saved her people before King Ahasuerus?", "Qual rainha judia salvou seu povo diante do rei Assuero?"),
            options = listOf(
                LocalizedText("Rut", "Ruth", "Rute"),
                LocalizedText("Débora", "Deborah", "Débora"),
                LocalizedText("Ester", "Esther", "Ester"),
                LocalizedText("Judith", "Judith", "Judite"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Ester intercedió ante el rey y salvó a los judíos de la destrucción.", "Esther interceded with the king and saved the Jews from destruction.", "Ester intercedeu junto ao rei e salvou os judeus da destruição.")
        ),
        TriviaQuestion(
            id = "t14",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué mujer fue leal a su suegra Noemí y es antepasada de Jesús?", "Which woman was loyal to her mother-in-law Naomi and is an ancestor of Jesus?", "Qual mulher foi fiel à sua sogra Noemi e é antepassada de Jesus?"),
            options = listOf(
                LocalizedText("Rut", "Ruth", "Rute"),
                LocalizedText("Ana", "Hannah", "Ana"),
                LocalizedText("Sara", "Sarah", "Sara"),
                LocalizedText("Ester", "Esther", "Ester"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Rut dijo: «Tu pueblo será mi pueblo, tu Dios será mi Dios».", "Ruth said: 'Your people shall be my people, your God my God.'", "Rute disse: 'Teu povo será meu povo, teu Deus será meu Deus.'")
        ),
        TriviaQuestion(
            id = "t15",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿A quién se atribuyen tradicionalmente los Salmos?", "To whom are the Psalms traditionally attributed?", "A quem são tradicionalmente atribuídos os Salmos?"),
            options = listOf(
                LocalizedText("Moisés", "Moses", "Moisés"),
                LocalizedText("Salomón", "Solomon", "Salomão"),
                LocalizedText("David", "David", "Davi"),
                LocalizedText("Elías", "Elijah", "Elias"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Muchos Salmos se atribuyen al rey David, músico y poeta sagrado.", "Many Psalms are attributed to King David, musician and sacred poet.", "Muitos Salmos são atribuídos ao rei Davi, músico e poeta sagrado.")
        ),
        TriviaQuestion(
            id = "t16",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿En qué libro leemos el Sermón de la Montaña y las Bienaventuranzas?", "In which book do we read the Sermon on the Mount and the Beatitudes?", "Em qual livro lemos o Sermão da Montanha e as Bem-aventuranças?"),
            options = listOf(
                LocalizedText("Marcos", "Mark", "Marcos"),
                LocalizedText("Lucas", "Luke", "Lucas"),
                LocalizedText("Juan", "John", "João"),
                LocalizedText("Mateo", "Matthew", "Mateus"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Mateo recoge las Bienaventuranzas: «Bienaventurados los pobres de espíritu…».", "Matthew records the Beatitudes: 'Blessed are the poor in spirit…'", "Mateus registra as Bem-aventuranças: 'Bem-aventurados os pobres em espírito…'")
        ),
        TriviaQuestion(
            id = "t17",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Cuál fue el primer milagro de Jesús, en las bodas de Caná?", "What was Jesus' first miracle, at the wedding of Cana?", "Qual foi o primeiro milagre de Jesus, nas bodas de Caná?"),
            options = listOf(
                LocalizedText("Calmar la tormenta", "Calming the storm", "Acalmar a tempestade"),
                LocalizedText("Curar a un enfermo", "Healing a sick man", "Curar um enfermo"),
                LocalizedText("Multiplicar los panes", "Multiplying the loaves", "Multiplicar os pães"),
                LocalizedText("Convertir el agua en vino", "Turning water into wine", "Transformar água em vinho"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Jesús convirtió el agua en vino, el primer signo de su gloria.", "Jesus turned water into wine, the first sign of His glory.", "Jesus transformou água em vinho, o primeiro sinal de sua glória.")
        ),
        TriviaQuestion(
            id = "t18",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿En qué ciudad nació Jesús?", "In which city was Jesus born?", "Em qual cidade Jesus nasceu?"),
            options = listOf(
                LocalizedText("Jerusalén", "Jerusalem", "Jerusalém"),
                LocalizedText("Caná", "Cana", "Caná"),
                LocalizedText("Nazaret", "Nazareth", "Nazaré"),
                LocalizedText("Belén", "Bethlehem", "Belém"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Jesús nació en Belén de Judea, como anunciaron los profetas.", "Jesus was born in Bethlehem of Judea, as the prophets foretold.", "Jesus nasceu em Belém da Judeia, como profetizaram os profetas.")
        ),
        TriviaQuestion(
            id = "t19",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿A qué apóstoles llamó Jesús pescando junto al mar de Galilea?", "Which apostles did Jesus call while fishing by the Sea of Galilee?", "Quais apóstolos Jesus chamou pescando junto ao mar da Galileia?"),
            options = listOf(
                LocalizedText("Pablo y Bernabé", "Paul and Barnabas", "Paulo e Barnabé"),
                LocalizedText("Santiago y Juan", "James and John", "Santiago e João"),
                LocalizedText("Andrés y Tomás", "Andrew and Thomas", "André e Tomé"),
                LocalizedText("Pedro y Juan", "Peter and John", "Pedro e João"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Jesús llamó a Santiago y a Juan, hijos de Zebedeo, para ser pescadores de hombres.", "Jesus called James and John, sons of Zebedee, to be fishers of men.", "Jesus chamou Santiago e João, filhos de Zebedeu, para serem pescadores de homens.")
        ),
        TriviaQuestion(
            id = "t20",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué instituyó Jesús en la Última Cena?", "What did Jesus institute at the Last Supper?", "O que Jesus instituiu na Última Ceia?"),
            options = listOf(
                LocalizedText("La Eucaristía", "The Eucharist", "A Eucaristia"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
                LocalizedText("La Unción de los enfermos", "Anointing of the Sick", "A Unção dos Enfermos"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Jesús tomó el pan y el vino y los convirtió en su Cuerpo y su Sangre.", "Jesus took bread and wine and made them His Body and Blood.", "Jesus tomou o pão e o vinho e os tornou seu Corpo e seu Sangue.")
        ),
        TriviaQuestion(
            id = "t21",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Dónde se convirtió San Pablo, camino de Damasco?", "Where was Saint Paul converted, on the road to Damascus?", "Onde São Paulo se converteu, no caminho de Damasco?"),
            options = listOf(
                LocalizedText("Una nube luminosa", "He saw a bright cloud", "Uma nuvem luminosa"),
                LocalizedText("Un terremoto", "An earthquake", "Um terremoto"),
                LocalizedText("Un rayo del cielo y una voz", "Lightning from heaven and a voice", "Um relâmpago do céu e uma voz"),
                LocalizedText("Un ángel", "An angel", "Um anjo"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Pablo cayó a tierra y oyó: «Saulo, Saulo, ¿por qué me persigues?».", "Paul fell to the ground and heard: 'Saul, Saul, why do you persecute Me?'", "Paulo caiu por terra e ouviu: 'Saulo, Saulo, por que me persegues?'")
        ),
        TriviaQuestion(
            id = "t22",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Quién escribió el libro de los Hechos de los Apóstoles?", "Who wrote the Book of Acts of the Apostles?", "Quem escreveu o livro dos Atos dos Apóstolos?"),
            options = listOf(
                LocalizedText("San Juan", "Saint John", "São João"),
                LocalizedText("San Pedro", "Saint Peter", "São Pedro"),
                LocalizedText("San Pablo", "Saint Paul", "São Paulo"),
                LocalizedText("San Lucas", "Saint Luke", "São Lucas"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("San Lucas, compañero de Pablo, escribió los Hechos y el Evangelio que lleva su nombre.", "Saint Luke, Paul's companion, wrote Acts and the Gospel bearing his name.", "São Lucas, companheiro de Paulo, escreveu os Atos e o Evangelho que leva seu nome.")
        ),
        TriviaQuestion(
            id = "t23",
            category = LocalizedText("Sagrada Escritura", "Sacred Scripture", "Sagrada Escritura"),
            question = LocalizedText("¿Qué acontecimiento narra el capítulo 2 de los Hechos?", "What event does Acts chapter 2 recount?", "Qual evento narra o capítulo 2 dos Atos?"),
            options = listOf(
                LocalizedText("La conversión de Pablo", "Paul's conversion", "A conversão de Paulo"),
                LocalizedText("El bautismo de Jesús", "Jesus' baptism", "O batismo de Jesus"),
                LocalizedText("La resurrección", "The Resurrection", "A ressurreição"),
                LocalizedText("Pentecostés", "Pentecost", "Pentecostes"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("En Pentecostés descendió el Espíritu Santo sobre los apóstoles en Jerusalén.", "At Pentecost the Holy Spirit descended on the apostles in Jerusalem.", "No Pentecostes o Espírito Santo desceu sobre os apóstolos em Jerusalém.")
        ),
        TriviaQuestion(
            id = "t24",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo de Asís, amante de la pobreza, es patrono de los animales?", "Which saint of Assisi, lover of poverty, is patron of animals?", "Qual santo de Assis, amante da pobreza, é padroeiro dos animais?"),
            options = listOf(
                LocalizedText("San Martín", "Saint Martin", "São Martinho"),
                LocalizedText("San Roque", "Saint Roch", "São Roque"),
                LocalizedText("San Antonio", "Saint Anthony", "São Antônio"),
                LocalizedText("San Francisco de Asís", "Saint Francis of Assisi", "São Francisco de Assis"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("San Francisco predicó a los animales y vivió en extrema pobreza.", "Saint Francis preached to animals and lived in extreme poverty.", "São Francisco pregou aos animais e viveu em extrema pobreza.")
        ),
        TriviaQuestion(
            id = "t25",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santa española, mística y doctora de la Iglesia, reformó el Carmelo?", "Which Spanish saint, mystic and Doctor of the Church, reformed Carmel?", "Qual santa espanhola, mística e doutora da Igreja, reformou o Carmelo?"),
            options = listOf(
                LocalizedText("Santa Teresita", "Saint Thérèse", "Santa Teresinha"),
                LocalizedText("Santa Clara", "Saint Clare", "Santa Clara"),
                LocalizedText("Santa Rosa", "Saint Rose", "Santa Rosa"),
                LocalizedText("Santa Teresa de Jesús", "Saint Teresa of Jesus", "Santa Teresa de Jesus"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Santa Teresa fundó conventos renovados y escribió «Camino de perfección».", "Saint Teresa founded reformed convents and wrote 'The Way of Perfection.'", "Santa Teresa fundou conventos reformados e escreveu 'Caminho de perfeição.'")
        ),
        TriviaQuestion(
            id = "t26",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santa de Lisieux enseñó el «camino de la infancia espiritual»?", "Which saint of Lisieux taught the 'Little Way' of spiritual childhood?", "Qual santa de Lisieux ensinou o 'caminho da infância espiritual'?"),
            options = listOf(
                LocalizedText("Santa Teresa de Ávila", "Saint Teresa of Avila", "Santa Teresa de Ávila"),
                LocalizedText("Santa Clara", "Saint Clare", "Santa Clara"),
                LocalizedText("Santa Teresita de Lisieux", "Saint Thérèse of Lisieux", "Santa Teresinha de Lisieux"),
                LocalizedText("Santa Catalina", "Saint Catherine", "Santa Catarina"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Santa Teresita vivió la santidad en las cosas pequeñas con gran confianza.", "Saint Thérèse lived holiness in small things with great trust.", "Santa Teresinha viveu a santidade nas coisas pequenas com grande confiança.")
        ),
        TriviaQuestion(
            id = "t27",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo español fundó la Compañía de Jesús (jesuitas)?", "Which Spanish saint founded the Society of Jesus (Jesuits)?", "Qual santo espanhol fundou a Companhia de Jesus (jesuítas)?"),
            options = listOf(
                LocalizedText("San José", "Saint Joseph", "São José"),
                LocalizedText("San Ignacio de Loyola", "Saint Ignatius of Loyola", "São Inácio de Loyola"),
                LocalizedText("San Domingo", "Saint Dominic", "São Domingos"),
                LocalizedText("San Francisco Javier", "Saint Francis Xavier", "São Francisco Xavier"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("San Ignacio escribió los Ejercicios Espirituales y fundó los jesuitas.", "Saint Ignatius wrote the Spiritual Exercises and founded the Jesuits.", "São Inácio escreveu os Exercícios Espirituais e fundou os jesuítas.")
        ),
        TriviaQuestion(
            id = "t28",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo fundó la Orden de Predicadores (dominicos)?", "Which saint founded the Order of Preachers (Dominicans)?", "Qual santo fundou a Ordem dos Pregadores (dominicanos)?"),
            options = listOf(
                LocalizedText("San Agustín", "Saint Augustine", "São Agostinho"),
                LocalizedText("San Francisco", "Saint Francis", "São Francisco"),
                LocalizedText("San Benito", "Saint Benedict", "São Bento"),
                LocalizedText("San Domingo de Guzmán", "Saint Dominic de Guzmán", "São Domingos de Gusmão"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Santo Domingo fundó los dominicos para predicar la verdad de Jesucristo.", "Saint Dominic founded the Dominicans to preach the truth of Christ.", "São Domingos fundou os dominicanos para pregar a verdade de Cristo.")
        ),
        TriviaQuestion(
            id = "t29",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santa de Asís fundó las Hermanas Clarisas?", "Which saint of Assisi founded the Poor Clares?", "Qual santa de Assis fundou as Clarissas?"),
            options = listOf(
                LocalizedText("Santa Inés", "Saint Agnes", "Santa Inês"),
                LocalizedText("Santa Marta", "Saint Martha", "Santa Marta"),
                LocalizedText("Santa Clara", "Saint Clare", "Santa Clara"),
                LocalizedText("Santa Isabel", "Saint Elizabeth", "Santa Isabel"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Santa Clara vivió en clausura en San Damián, junto a San Francisco.", "Saint Clare lived in enclosure at San Damiano, beside Saint Francis.", "Santa Clara viveu em clausura em São Damião, junto a São Francisco.")
        ),
        TriviaQuestion(
            id = "t30",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo franciscano es invocado para hallar objetos perdidos?", "Which Franciscan saint is invoked to find lost objects?", "Qual santo franciscano é invocado para encontrar objetos perdidos?"),
            options = listOf(
                LocalizedText("San Francisco", "Saint Francis", "São Francisco"),
                LocalizedText("San Buenaventura", "Saint Bonaventure", "São Boaventura"),
                LocalizedText("San Elías", "Saint Elijah", "São Elias"),
                LocalizedText("San Antonio de Padua", "Saint Anthony of Padua", "São Antônio de Pádua"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("San Antonio, gran predicador, es patrono de los objetos perdidos.", "Saint Anthony, a great preacher, is patron of lost objects.", "São Antônio, grande pregador, é padroeiro dos objetos perdidos.")
        ),
        TriviaQuestion(
            id = "t31",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Quién fue el esposo de María y padre adoptivo de Jesús?", "Who was Mary's husband and Jesus' adoptive father?", "Quem foi o esposo de Maria e pai adotivo de Jesus?"),
            options = listOf(
                LocalizedText("San José", "Saint Joseph", "São José"),
                LocalizedText("San Joaquín", "Saint Joachim", "São Joaquim"),
                LocalizedText("Zacarías", "Zechariah", "Zacarias"),
                LocalizedText("Simón", "Simon", "Simão"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("San José, carpintero, custodio de la Sagrada Familia.", "Saint Joseph, carpenter, guardian of the Holy Family.", "São José, carpinteiro, guardião da Sagrada Família.")
        ),
        TriviaQuestion(
            id = "t32",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué papa polaco, de largo pontificado, convocó las Jornadas Mundiales de la Juventud?", "Which Polish pope, with a long pontificate, called the World Youth Days?", "Qual papa polonês, de longo pontificado, convocou as Jornadas Mundiais da Juventude?"),
            options = listOf(
                LocalizedText("San Pablo VI", "Saint Paul VI", "São Paulo VI"),
                LocalizedText("San Juan Pablo II", "Saint John Paul II", "São João Paulo II"),
                LocalizedText("Pío XII", "Pius XII", "Pio XII"),
                LocalizedText("Juan XXIII", "John XXIII", "João XXIII"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Juan Pablo II, papa de 1978 a 2005, reunió a millones de jóvenes.", "John Paul II, pope from 1978 to 2005, gathered millions of young people.", "João Paulo II, papa de 1978 a 2005, reuniu milhões de jovens.")
        ),
        TriviaQuestion(
            id = "t33",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santa de Calcuta fundó las Misioneras de la Caridad?", "Which saint of Calcutta founded the Missionaries of Charity?", "Qual santa de Calcutá fundou as Missionárias da Caridade?"),
            options = listOf(
                LocalizedText("Santa Catalina", "Saint Catherine", "Santa Catarina"),
                LocalizedText("Santa Teresa de Calcuta", "Saint Teresa of Calcutta", "Santa Teresa de Calcutá"),
                LocalizedText("Santa Faustina", "Saint Faustina", "Santa Faustina"),
                LocalizedText("Santa Isabel", "Saint Elizabeth", "Santa Isabel"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("La Madre Teresa dedicó su vida a los más pobres de los pobres en la India.", "Mother Teresa devoted her life to the poorest of the poor in India.", "A Madre Teresa dedicou sua vida aos mais pobres dos pobres na Índia.")
        ),
        TriviaQuestion(
            id = "t34",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo escribió la Regla benedictina, base del monacato occidental?", "Which saint wrote the Benedictine Rule, basis of Western monasticism?", "Qual santo escreveu a Regra beneditina, base do monacato ocidental?"),
            options = listOf(
                LocalizedText("San Roberto", "Saint Robert", "São Roberto"),
                LocalizedText("San Bernardo", "Saint Bernard", "São Bernardo"),
                LocalizedText("San Benito", "Saint Benedict", "São Bento"),
                LocalizedText("San Bruno", "Saint Bruno", "São Bruno"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("San Benito: «Ora y trabaja», lema de los monasterios benedictinos.", "Saint Benedict: 'Pray and work,' motto of Benedictine monasteries.", "São Bento: 'Ora e trabalha', lema dos mosteiros beneditinos.")
        ),
        TriviaQuestion(
            id = "t35",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué obispo de Hipona escribió «Confesiones»?", "Which Bishop of Hippo wrote the 'Confessions'?", "Qual bispo de Hipona escreveu as 'Confissões'?"),
            options = listOf(
                LocalizedText("San Agustín", "Saint Augustine", "São Agostinho"),
                LocalizedText("San Jerónimo", "Saint Jerome", "São Jerônimo"),
                LocalizedText("San Gregorio", "Saint Gregory", "São Gregório"),
                LocalizedText("San Ambrosio", "Saint Ambrose", "São Ambrósio"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("San Agustín, doctor de la Iglesia, escribió Confesiones y La ciudad de Dios.", "Saint Augustine, Doctor of the Church, wrote the Confessions and The City of God.", "São Agostinho, doutor da Igreja, escreveu Confissões e A Cidade de Deus.")
        ),
        TriviaQuestion(
            id = "t36",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo dominico escribió la «Suma Teológica»?", "Which Dominican saint wrote the 'Summa Theologiae'?", "Qual santo dominicano escreveu a 'Suma Teológica'?"),
            options = listOf(
                LocalizedText("Santo Tomás de Aquino", "Saint Thomas Aquinas", "São Tomás de Aquino"),
                LocalizedText("San Domingo", "Saint Dominic", "São Domingos"),
                LocalizedText("San Vicente Ferrer", "Saint Vincent Ferrer", "São Vicente Ferrer"),
                LocalizedText("San Alberto Magno", "Saint Albert the Great", "São Alberto Magno"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Santo Tomás, Doctor Angélico, armonizó fe y razón.", "Saint Thomas, the Angelic Doctor, harmonized faith and reason.", "São Tomás, Doutor Angélico, harmonizou fé e razão.")
        ),
        TriviaQuestion(
            id = "t37",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santo franciscano murió en Auschwitz tras ofrecerse por un prisionero?", "Which Franciscan saint died at Auschwitz after offering himself for a prisoner?", "Qual santo franciscano morreu em Auschwitz após oferecer-se por um prisioneiro?"),
            options = listOf(
                LocalizedText("San Maximiliano Kolbe", "Saint Maximilian Kolbe", "São Maximiliano Kolbe"),
                LocalizedText("San Padre Pío", "Saint Padre Pio", "São Padre Pio"),
                LocalizedText("San Antonio", "Saint Anthony", "São Antônio"),
                LocalizedText("San Roque", "Saint Roch", "São Roque"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("San Maximiliano Kolbe entregó su vida por un padre de familia condenado a morir.", "Saint Maximilian Kolbe gave his life for a father condemned to die.", "São Maximiliano Kolbe entregou sua vida por um pai de família condenado a morrer.")
        ),
        TriviaQuestion(
            id = "t38",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué sacerdote capuchino italiano recibió los estigmas de Cristo?", "Which Italian Capuchin priest received the stigmata of Christ?", "Qual sacerdote capuchinho italiano recebeu os estigmas de Cristo?"),
            options = listOf(
                LocalizedText("San Félix", "Saint Felix", "São Félix"),
                LocalizedText("San Pío de Pietrelcina (Padre Pío)", "Saint Pio of Pietrelcina (Padre Pio)", "São Pio de Pietrelcina (Padre Pio)"),
                LocalizedText("San Juan Bosco", "Saint John Bosco", "São João Bosco"),
                LocalizedText("San Leopoldo", "Saint Leopold", "São Leopoldo"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El Padre Pío llevó las llagas de Cristo durante 50 años.", "Padre Pio bore Christ's wounds for 50 years.", "O Padre Pio carregou as chagas de Cristo por 50 anos.")
        ),
        TriviaQuestion(
            id = "t39",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué santa polaca recibió las revelaciones de la Divina Misericordia?", "Which Polish saint received the revelations of Divine Mercy?", "Qual santa polonesa recebeu as revelações da Divina Misericórdia?"),
            options = listOf(
                LocalizedText("Santa Kinga", "Saint Kinga", "Santa Kinga"),
                LocalizedText("Santa Jadwiga", "Saint Jadwiga", "Santa Jadwiga"),
                LocalizedText("Santa Faustina Kowalska", "Saint Faustina Kowalska", "Santa Faustina Kowalska"),
                LocalizedText("Santa Edwiges", "Saint Hedwig", "Santa Edwiges"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Santa Faustina oyó a Jesús: «Dile a la humanidad que no puede haber paz hasta que se vuelva a Mi Misericordia».", "Saint Faustina heard Jesus say: 'Tell mankind there can be no peace until it turns to My Mercy.'", "Santa Faustina ouviu Jesus dizer: 'Dize à humanidade que não haverá paz até que volte à Minha Misericórdia.'")
        ),
        TriviaQuestion(
            id = "t40",
            category = LocalizedText("Santos y Santas", "Saints", "Santos e Santas"),
            question = LocalizedText("¿Qué mártir romano fue asaetado con flechas?", "Which Roman martyr was shot with arrows?", "Qual mártir romano foi morto por flechas?"),
            options = listOf(
                LocalizedText("San Lorenzo", "Saint Lawrence", "São Lourenço"),
                LocalizedText("San Esteban", "Saint Stephen", "São Estêvão"),
                LocalizedText("San Sebastián", "Saint Sebastian", "São Sebastião"),
                LocalizedText("San Jorge", "Saint George", "São Jorge"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("San Sebastián, soldado, fue atado a un árbol y asaetado por su fe.", "Saint Sebastian, a soldier, was tied to a tree and shot with arrows for his faith.", "São Sebastião, soldado, foi amarrado a uma árvore e morto por flechas pela fé.")
        ),
        TriviaQuestion(
            id = "t41",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Cuántos sacramentos hay en la Iglesia Católica?", "How many sacraments are there in the Catholic Church?", "Quantos sacramentos há na Igreja Católica?"),
            options = listOf(
                LocalizedText("Seis", "Six", "Seis"),
                LocalizedText("Siete", "Seven", "Sete"),
                LocalizedText("Diez", "Ten", "Dez"),
                LocalizedText("Cinco", "Five", "Cinco"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Hay siete sacramentos: Bautismo, Confirmación, Eucaristía, Penitencia, Unción, Matrimonio y Orden.", "There are seven sacraments: Baptism, Confirmation, Eucharist, Penance, Anointing, Matrimony and Orders.", "São sete sacramentos: Batismo, Crisma, Eucaristia, Penitência, Unção, Matrimônio e Ordem.")
        ),
        TriviaQuestion(
            id = "t42",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué sacramento borra el pecado original?", "Which sacrament washes away original sin?", "Qual sacramento apaga o pecado original?"),
            options = listOf(
                LocalizedText("La Eucaristía", "The Eucharist", "A Eucaristia"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
                LocalizedText("El Matrimonio", "Marriage", "O Matrimônio"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("En el Bautismo el niño es lavado del pecado original y nace a la vida divina.", "In Baptism the child is washed from original sin and born to divine life.", "No Batismo a criança é lavada do pecado original e nasce para a vida divina.")
        ),
        TriviaQuestion(
            id = "t43",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué sacramento imprime el don del Espíritu Santo con el crisma?", "Which sacrament impresses the gift of the Holy Spirit with chrism?", "Qual sacramento imprime o dom do Espírito Santo com o crisma?"),
            options = listOf(
                LocalizedText("La Penitencia", "Penance", "A Penitência"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
                LocalizedText("El Matrimonio", "Matrimony", "O Matrimônio"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("La Confirmación fortalece la gracia bautismal y nos hace testigos de Cristo.", "Confirmation strengthens baptismal grace and makes us witnesses of Christ.", "A Confirmação fortalece a graça batismal e nos faz testemunhas de Cristo.")
        ),
        TriviaQuestion(
            id = "t44",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué ocurre en la consagración eucarística según la fe católica?", "What happens at the Eucharistic consecration according to Catholic faith?", "O que acontece na consagração eucarística segundo a fé católica?"),
            options = listOf(
                LocalizedText("Solo cambia el vino", "Only the wine changes", "Só o vinho muda"),
                LocalizedText("El pan y el vino se convierten en Cuerpo y Sangre de Cristo", "Bread and wine become Christ's Body and Blood", "O pão e o vinho se tornam Corpo e Sangue de Cristo"),
                LocalizedText("El pan y el vino son solo símbolos", "Bread and wine are only symbols", "O pão e o vinho são apenas símbolos"),
                LocalizedText("Solo cambia el pan", "Only the bread changes", "Só o pão muda"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("La Iglesia enseña la transubstanciación: permanece Cristo entero bajo las especies.", "The Church teaches transubstantiation: the whole Christ remains under the species.", "A Igreja ensina a transubstanciação: Cristo inteiro permanece sob as espécies.")
        ),
        TriviaQuestion(
            id = "t45",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Quién puede absolver los pecados en el sacramento de la Reconciliación?", "Who can absolve sins in the Sacrament of Reconciliation?", "Quem pode absolver os pecados no sacramento da Reconciliação?"),
            options = listOf(
                LocalizedText("Un catequista", "Catechist", "Um catequista"),
                LocalizedText("La persona misma", "The person oneself", "A própria pessoa"),
                LocalizedText("Cualquier diácono", "Any deacon", "Qualquer diácono"),
                LocalizedText("Un sacerdote u obispo", "Priest or bishop", "Um sacerdote ou bispo"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Solo el sacerdote, ministro ordenado, puede absolver en nombre de Cristo.", "Only a priest, an ordained minister, can absolve in Christ's name.", "Somente o sacerdote, ministro ordenado, pode absolver em nome de Cristo.")
        ),
        TriviaQuestion(
            id = "t46",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué sacramento administra la unción con óleo a los enfermos graves?", "Which sacrament anoints seriously ill persons with oil?", "Qual sacramento unge os doentes graves com óleo?"),
            options = listOf(
                LocalizedText("La Unción de los Enfermos", "Anointing of the Sick", "Unção dos Enfermos"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
                LocalizedText("El Orden", "Orders", "A Ordem"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Santiago 5,14 exhorta a ungir a los enfermos y orar por ellos.", "James 5:14 urges to anoint the sick and pray for them.", "Tiago 5,14 exorta a ungir os enfermos e orar por eles.")
        ),
        TriviaQuestion(
            id = "t47",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué sacramento une a un hombre y una mujer en alianza de amor?", "Which sacrament unites a man and woman in covenant of love?", "Qual sacramento une um homem e uma mulher em aliança de amor?"),
            options = listOf(
                LocalizedText("El Matrimonio", "Matrimony", "O Matrimônio"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
                LocalizedText("La Penitencia", "Penance", "A Penitência"),
                LocalizedText("La Eucaristía", "Eucharist", "A Eucaristia"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("El Matrimonio es un sacramento: los dos serán una sola carne.", "Matrimony is a sacrament: the two become one flesh.", "O Matrimônio é um sacramento: os dois serão uma só carne.")
        ),
        TriviaQuestion(
            id = "t48",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué sacramento ordena obispos, sacerdotes y diáconos?", "Which sacrament ordains bishops, priests, and deacons?", "Qual sacramento ordena bispos, sacerdotes e diáconos?"),
            options = listOf(
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
                LocalizedText("El Matrimonio", "Matrimony", "O Matrimônio"),
                LocalizedText("El Orden Sacerdotal", "Holy Orders", "A Ordem Sacerdotal"),
                LocalizedText("La Unción", "Anointing", "A Unção"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El Orden confiere el ministerio apostólico al servicio de la Iglesia.", "Holy Orders confers apostolic ministry at the service of the Church.", "A Ordem confere o ministério apostólico a serviço da Igreja.")
        ),
        TriviaQuestion(
            id = "t49",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Cómo se llama la Eucaristía recibida por un moribundo?", "What is the Eucharist received by a dying person called?", "Como se chama a Eucaristia recebida por um moribundo?"),
            options = listOf(
                LocalizedText("Sacrificio", "Sacrifice", "Sacrifício"),
                LocalizedText("Cena", "Supper", "Ceia"),
                LocalizedText("Viático", "Viaticum", "Viático"),
                LocalizedText("Maná", "Manna", "Maná"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El viático es el alimento para el camino hacia la vida eterna.", "Viaticum is food for the journey to eternal life.", "O viático é o alimento para a jornada à vida eterna.")
        ),
        TriviaQuestion(
            id = "t50",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué palabras se pronuncian al bautizar?", "What words are spoken when baptizing?", "Quais palavras se pronunciam ao batizar?"),
            options = listOf(
                LocalizedText("«Yo te perdono»", "'I forgive you'", "'Eu te perdoo'"),
                LocalizedText("«Paz a ti»", "'Peace be with you'", "'Paz a ti'"),
                LocalizedText("«En el nombre del Padre, y del Hijo, y del Espíritu Santo»", "'In the name of the Father, and of the Son, and of the Holy Spirit'", "'Em nome do Pai, e do Filho, e do Espírito Santo'"),
                LocalizedText("«En el nombre de Jesús»", "'In the name of Jesus'", "'Em nome de Jesus'"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("La fórmula trinitaria del Bautismo está en Mateo 28,19.", "Baptism's Trinitarian formula is in Matthew 28:19.", "A fórmula trinitária do Batismo está em Mateus 28,19.")
        ),
        TriviaQuestion(
            id = "t51",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿A qué edad aproximada se suele recibir la Primera Comunión?", "At about what age is First Communion usually received?", "Com que idade aproximadamente se costuma receber a Primeira Comunhão?"),
            options = listOf(
                LocalizedText("A los tres años", "At three", "Com três anos"),
                LocalizedText("Hacia los siete años", "About age seven", "Perto dos sete anos"),
                LocalizedText("A los dieciséis", "At sixteen", "Com dezesseis anos"),
                LocalizedText("Al nacer", "At birth", "Ao nascer"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("La Iglesia pide que el niño tenga uso de razón, hacia los siete años.", "The Church asks the child to have the use of reason, around age seven.", "A Igreja pede que a criança tenha uso de razão, por volta dos sete anos.")
        ),
        TriviaQuestion(
            id = "t52",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Con qué se unge la frente en la Confirmación?", "With what is the forehead anointed at Confirmation?", "Com o que se unge a testa na Confirmação?"),
            options = listOf(
                LocalizedText("Aceite común", "Common oil", "Azeite comum"),
                LocalizedText("Santo Crisma", "Sacred Chrism", "Crisma sagrado"),
                LocalizedText("Vino", "Wine", "Vinho"),
                LocalizedText("Agua bendita", "Holy water", "Água benta"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El obispo unge con Santo Crisma en forma de cruz.", "The bishop anoints with sacred Chrism in the form of a cross.", "O bispo unge com Crisma sagrado em forma de cruz.")
        ),
        TriviaQuestion(
            id = "t53",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué garantiza el «sello» de la confesión?", "What does the 'seal' of confession guarantee?", "O que garante o 'sigilo' da confissão?"),
            options = listOf(
                LocalizedText("Que el sacerdote puede contar el pecado", "The priest may tell the sin", "Que o sacerdote pode contar o pecado"),
                LocalizedText("Inviolabilidad absoluta de lo confesado", "Absolute inviolability of what is confessed", "Inviolabilidade absoluta do que foi confessado"),
                LocalizedText("Que la confesión es pública", "Confession is public", "A confissão é pública"),
                LocalizedText("Que no hay perdón", "There is no forgiveness", "Não há perdão"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El sigilo sacramental obliga al sacerdote a guardar silencio absoluto.", "The sacramental seal binds the priest to absolute silence.", "O sigilo sacramental obriga o sacerdote ao silêncio absoluto.")
        ),
        TriviaQuestion(
            id = "t54",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Qué es esencial en el Matrimonio cristiano?", "What is essential in Christian Matrimony?", "O que é essencial no Matrimônio cristão?"),
            options = listOf(
                LocalizedText("El vestido blanco", "The white dress", "O vestido branco"),
                LocalizedText("El anillo", "The ring", "A aliança"),
                LocalizedText("El consentimiento libre de los esposos", "The free consent of the spouses", "O consentimento livre dos esposos"),
                LocalizedText("La fiesta", "The party", "A festa"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Los esposos se dan libremente el consentimiento ante Dios y la Iglesia.", "The spouses freely give consent before God and the Church.", "Os esposos dão livremente o consentimento diante de Deus e da Igreja.")
        ),
        TriviaQuestion(
            id = "t55",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Dónde se unge con óleo en la Unción de los Enfermos?", "Where is oil applied in the Anointing of the Sick?", "Onde se aplica o óleo na Unção dos Enfermos?"),
            options = listOf(
                LocalizedText("El pecho", "The chest", "O peito"),
                LocalizedText("El corazón", "The heart", "O coração"),
                LocalizedText("Los pies", "The feet", "Os pés"),
                LocalizedText("Frente y manos", "Forehead and hands", "Testa e mãos"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Se unge la frente y las manos, partes de la persona que actúa y siente.", "The forehead and hands are anointed, parts by which a person acts and feels.", "Ungem-se a testa e as mãos, partes com que a pessoa age e sente.")
        ),
        TriviaQuestion(
            id = "t56",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Cuáles son los tres sacramentos de iniciación cristiana?", "Which are the three sacraments of Christian initiation?", "Quais são os três sacramentos da iniciação cristã?"),
            options = listOf(
                LocalizedText("Matrimonio, Orden y Unción", "Matrimony, Orders and Anointing", "Matrimônio, Ordem e Unção"),
                LocalizedText("Eucaristía, Penitencia y Bautismo", "Eucharist, Penance and Baptism", "Eucaristia, Penitência e Batismo"),
                LocalizedText("Penitencia, Unción y Matrimonio", "Penance, Anointing and Matrimony", "Penitência, Unção e Matrimônio"),
                LocalizedText("Bautismo, Confirmación y Eucaristía", "Baptism, Confirmation and Eucharist", "Batismo, Confirmação e Eucaristia"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Estos tres forman la base de la vida cristiana del bautizado.", "These three form the foundation of the baptized person's Christian life.", "Estes três formam a base da vida cristã do batizado.")
        ),
        TriviaQuestion(
            id = "t57",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Cuáles son los dos sacramentos al servicio de la comunión?", "Which are the two sacraments at the service of communion?", "Quais são os dois sacramentos a serviço da comunhão?"),
            options = listOf(
                LocalizedText("Matrimonio y Orden Sacerdotal", "Matrimony and Holy Orders", "Matrimônio e Ordem Sacerdotal"),
                LocalizedText("Eucaristía y Unción", "Eucharist and Anointing", "Eucaristia e Unção"),
                LocalizedText("Penitencia y Bautismo", "Penance and Baptism", "Penitência e Batismo"),
                LocalizedText("Bautismo y Confirmación", "Baptism and Confirmation", "Batismo e Confirmação"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Matrimonio y Orden sirven a la edificación de la comunidad eclesial.", "Matrimony and Orders serve to build up the ecclesial community.", "Matrimônio e Ordem servem à edificação da comunidade eclesial.")
        ),
        TriviaQuestion(
            id = "t58",
            category = LocalizedText("Sacramentos", "Sacraments", "Sacramentos"),
            question = LocalizedText("¿Cuál es la materia del Bautismo?", "What is the matter of Baptism?", "Qual é a matéria do Batismo?"),
            options = listOf(
                LocalizedText("El aceite", "Oil", "O óleo"),
                LocalizedText("El agua", "Water", "A água"),
                LocalizedText("El pan", "Bread", "O pão"),
                LocalizedText("La sal", "Salt", "O sal"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El agua natural, derramada o vertida, es la materia del Bautismo.", "Natural water, poured or immersed, is the matter of Baptism.", "A água natural, derramada ou por imersão, é a matéria do Batismo.")
        ),
        TriviaQuestion(
            id = "t59",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color litúrgico se usa en Adviento?", "Which liturgical color is used in Advent?", "Qual cor litúrgica se usa no Advento?"),
            options = listOf(
                LocalizedText("Blanco", "White", "Branco"),
                LocalizedText("Verde", "Green", "Verde"),
                LocalizedText("Rojo", "Red", "Vermelho"),
                LocalizedText("Morado", "Purple", "Roxo"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El morado es color de penitencia, espera y preparación.", "Purple is the color of penance, expectation, and preparation.", "O roxo é cor de penitência, espera e preparação.")
        ),
        TriviaQuestion(
            id = "t60",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color se usa en la fiesta de Navidad?", "Which color is used on Christmas?", "Qual cor se usa na festa de Natal?"),
            options = listOf(
                LocalizedText("Negro", "Black", "Preto"),
                LocalizedText("Blanco o dorado", "White or gold", "Branco ou dourado"),
                LocalizedText("Morado", "Purple", "Roxo"),
                LocalizedText("Verde", "Green", "Verde"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El blanco manifiesta la alegría y la gloria de Cristo.", "White expresses the joy and glory of Christ.", "O branco expressa a alegria e a glória de Cristo.")
        ),
        TriviaQuestion(
            id = "t61",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color se usa el Domingo de Ramos y el Viernes Santo?", "Which color is used on Palm Sunday and Good Friday?", "Qual cor se usa no Domingo de Ramos e na Sexta-feira Santa?"),
            options = listOf(
                LocalizedText("Blanco", "White", "Branco"),
                LocalizedText("Azul", "Blue", "Azul"),
                LocalizedText("Verde", "Green", "Verde"),
                LocalizedText("Rojo", "Red", "Vermelho"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El rojo recuerda la Pasión y la sangre de Cristo.", "Red recalls Christ's Passion and Blood.", "O vermelho lembra a Paixão e o Sangue de Cristo.")
        ),
        TriviaQuestion(
            id = "t62",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color se usa en Tiempo Ordinario?", "Which color is used in Ordinary Time?", "Qual cor se usa no Tempo Comum?"),
            options = listOf(
                LocalizedText("Morado", "Purple", "Roxo"),
                LocalizedText("Rojo", "Red", "Vermelho"),
                LocalizedText("Negro", "Black", "Preto"),
                LocalizedText("Verde", "Green", "Verde"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El verde es el color del crecimiento y de la esperanza.", "Green is the color of growth and hope.", "O verde é a cor do crescimento e da esperança.")
        ),
        TriviaQuestion(
            id = "t63",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color se usa en las fiestas de los mártires?", "Which color is used on martyrs' feasts?", "Qual cor se usa nas festas dos mártires?"),
            options = listOf(
                LocalizedText("Blanco", "White", "Branco"),
                LocalizedText("Verde", "Green", "Verde"),
                LocalizedText("Rojo", "Red", "Vermelho"),
                LocalizedText("Morado", "Purple", "Roxo"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El rojo simboliza la sangre derramada por la fe.", "Red symbolizes blood shed for the faith.", "O vermelho simboliza o sangue derramado pela fé.")
        ),
        TriviaQuestion(
            id = "t64",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué color se usa en las solemnidades de la Virgen María?", "Which color is used on Marian solemnities?", "Qual cor se usa nas solenidades da Virgem Maria?"),
            options = listOf(
                LocalizedText("Blanco", "White", "Branco"),
                LocalizedText("Rojo", "Red", "Vermelho"),
                LocalizedText("Verde", "Green", "Verde"),
                LocalizedText("Morado", "Purple", "Roxo"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Las fiestas de María, no mártir, se celebran en blanco.", "Mary's feasts, as non-martyr, are celebrated in white.", "As festas de Maria, não mártir, são celebradas em branco.")
        ),
        TriviaQuestion(
            id = "t65",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuántas semanas dura el Adviento?", "How many weeks does Advent last?", "Quantas semanas dura o Advento?"),
            options = listOf(
                LocalizedText("Seis", "Six", "Seis"),
                LocalizedText("Dos", "Two", "Duas"),
                LocalizedText("Tres", "Three", "Três"),
                LocalizedText("Cuatro", "Four", "Quatro"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El Adviento dura cuatro semanas de preparación para Navidad.", "Advent lasts four weeks of preparation for Christmas.", "O Advento dura quatro semanas de preparação para o Natal.")
        ),
        TriviaQuestion(
            id = "t66",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuántos días dura la Cuaresma de penitencia?", "How many days does Lent of penance last?", "Quantos dias dura a Quaresma de penitência?"),
            options = listOf(
                LocalizedText("Diez", "Ten", "Dez"),
                LocalizedText("Cincuenta", "Fifty", "Cinquenta"),
                LocalizedText("Treinta", "Thirty", "Trinta"),
                LocalizedText("Cuarenta", "Forty", "Quarenta"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("La Cuaresma imita los cuarenta días de Jesús en el desierto.", "Lent imitates Jesus' forty days in the desert.", "A Quaresma imita os quarenta dias de Jesus no deserto.")
        ),
        TriviaQuestion(
            id = "t67",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué semana comienza con el Domingo de Ramos?", "Which week begins with Palm Sunday?", "Qual semana começa com o Domingo de Ramos?"),
            options = listOf(
                LocalizedText("Semana de Pascua", "Easter Week", "Semana da Páscoa"),
                LocalizedText("Semana de Adviento", "Advent Week", "Semana do Advento"),
                LocalizedText("Semana de Pentecostés", "Pentecost Week", "Semana de Pentecostes"),
                LocalizedText("Semana Santa", "Holy Week", "Semana Santa"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("La Semana Santa celebra la Pasión y Resurrección del Señor.", "Holy Week celebrates the Lord's Passion and Resurrection.", "A Semana Santa celebra a Paixão e Ressurreição do Senhor.")
        ),
        TriviaQuestion(
            id = "t68",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué fiesta celebra la presencia eucarística de Cristo?", "Which feast celebrates Christ's Eucharistic presence?", "Qual festa celebra a presença eucarística de Cristo?"),
            options = listOf(
                LocalizedText("San Valentín", "Saint Valentine's Day", "São Valentim"),
                LocalizedText("Carnaval", "Carnival", "Carnaval"),
                LocalizedText("Fiesta de la Cosecha", "Harvest Festival", "Festa da Colheita"),
                LocalizedText("Corpus Christi", "Corpus Christi", "Corpus Christi"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El Corpus Christi honra el Cuerpo y Sangre de Cristo en la Eucaristía.", "Corpus Christi honors Christ's Body and Blood in the Eucharist.", "Corpus Christi honra o Corpo e Sangue de Cristo na Eucaristia.")
        ),
        TriviaQuestion(
            id = "t69",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿En qué fecha se celebra la Asunción de María?", "On what date is the Assumption of Mary celebrated?", "Em que data se celebra a Assunção de Maria?"),
            options = listOf(
                LocalizedText("15 de agosto", "August 15", "15 de agosto"),
                LocalizedText("8 de diciembre", "December 8", "8 de dezembro"),
                LocalizedText("1 de noviembre", "November 1", "1 de novembro"),
                LocalizedText("25 de marzo", "March 25", "25 de março"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("La Asunción, el 15 de agosto, celebra que María fue llevada al cielo en cuerpo y alma.", "The Assumption, on August 15, celebrates that Mary was taken to heaven body and soul.", "A Assunção, em 15 de agosto, celebra que Maria foi levada ao céu de corpo e alma.")
        ),
        TriviaQuestion(
            id = "t70",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuándo se celebra la fiesta de Todos los Santos?", "When is the Feast of All Saints celebrated?", "Quando se celebra a festa de Todos os Santos?"),
            options = listOf(
                LocalizedText("6 de enero", "January 6", "6 de janeiro"),
                LocalizedText("1 de noviembre", "November 1", "1 de novembro"),
                LocalizedText("2 de noviembre", "November 2", "2 de novembro"),
                LocalizedText("24 de diciembre", "December 24", "24 de dezembro"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El 1 de noviembre honramos a todos los santos del cielo.", "On November 1 we honor all the saints in heaven.", "Em 1 de novembro honramos todos os santos do céu.")
        ),
        TriviaQuestion(
            id = "t71",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuándo se celebra la Conmemoración de los Fieles Difuntos?", "When is the Commemoration of All the Faithful Departed celebrated?", "Quando se celebra a Comemoração de Todos os Fiéis Defuntos?"),
            options = listOf(
                LocalizedText("15 de agosto", "August 15", "15 de agosto"),
                LocalizedText("31 de octubre", "October 31", "31 de outubro"),
                LocalizedText("1 de noviembre", "November 1", "1 de novembro"),
                LocalizedText("2 de noviembre", "November 2", "2 de novembro"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El 2 de noviembre rezamos por las almas del purgatorio.", "On November 2 we pray for the souls in purgatory.", "Em 2 de novembro rezamos pelas almas do purgatório.")
        ),
        TriviaQuestion(
            id = "t72",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuándo se celebra la Inmaculada Concepción de María?", "When is the Immaculate Conception of Mary celebrated?", "Quando se celebra a Imaculada Conceição de Maria?"),
            options = listOf(
                LocalizedText("12 de octubre", "October 12", "12 de outubro"),
                LocalizedText("7 de diciembre", "December 7", "7 de dezembro"),
                LocalizedText("8 de diciembre", "December 8", "8 de dezembro"),
                LocalizedText("15 de agosto", "August 15", "15 de agosto"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El 8 de diciembre celebra a María concebida sin pecado original.", "December 8 celebrates Mary conceived without original sin.", "Em 8 de dezembro celebramos Maria concebida sem pecado original.")
        ),
        TriviaQuestion(
            id = "t73",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Cuántos días después de la Pascua se celebra Pentecostés?", "How many days after Easter is Pentecost celebrated?", "Quantos dias depois da Páscoa se celebra Pentecostes?"),
            options = listOf(
                LocalizedText("Cuarenta", "Forty", "Quarenta"),
                LocalizedText("Cincuenta", "Fifty", "Cinquenta"),
                LocalizedText("Sesenta", "Sixty", "Sessenta"),
                LocalizedText("Treinta", "Thirty", "Trinta"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Pentecostés cae cincuenta días después de la Resurrección.", "Pentecost falls fifty days after the Resurrection.", "Pentecostes cai cinquenta dias depois da Ressurreição.")
        ),
        TriviaQuestion(
            id = "t74",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Con qué símbolo se marca la frente el Miércoles de Ceniza?", "With what symbol is the forehead marked on Ash Wednesday?", "Com qual símbolo se marca a testa na Quarta-feira de Cinzas?"),
            options = listOf(
                LocalizedText("Ceniza en forma de cruz", "Ashes in form of a cross", "Cinzas em forma de cruz"),
                LocalizedText("Agua", "Water", "Água"),
                LocalizedText("Aceite", "Oil", "Óleo"),
                LocalizedText("Tinta", "Ink", "Tinta"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("La ceniza recuerda: «Polvo eres y al polvo volverás».", "Ashes recall: 'Dust you are and to dust you shall return.'", "As cinzas lembram: 'Pó és e em pó te tornarás.'")
        ),
        TriviaQuestion(
            id = "t75",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué día del Señor es el domingo para los cristianos?", "Which is the Lord's Day for Christians?", "Qual é o Dia do Senhor para os cristãos?"),
            options = listOf(
                LocalizedText("El lunes", "Monday", "Segunda-feira"),
                LocalizedText("El sábado", "Saturday", "Sábado"),
                LocalizedText("El domingo", "Sunday", "Domingo"),
                LocalizedText("El viernes", "Friday", "Sexta-feira"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El domingo recuerda la Resurrección y es día de descanso y Misa.", "Sunday recalls the Resurrection and is a day of rest and Mass.", "O domingo lembra a Ressurreição e é dia de descanso e Missa.")
        ),
        TriviaQuestion(
            id = "t76",
            category = LocalizedText("Liturgia", "Liturgy", "Liturgia"),
            question = LocalizedText("¿Qué oración se reza antes de la Comunión en la Misa?", "Which prayer is said before Communion at Mass?", "Qual oração se reza antes da Comunhão na Missa?"),
            options = listOf(
                LocalizedText("El Credo", "The Creed", "O Credo"),
                LocalizedText("El Padrenuestro", "The Our Father", "O Pai-Nosso"),
                LocalizedText("El Avemaría", "The Hail Mary", "A Ave-Maria"),
                LocalizedText("El Ángelus", "The Angelus", "O Angelus"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("La Misa incluye el Padrenuestro antes de la Comunión.", "Mass includes the Our Father before Communion.", "A Missa inclui o Pai-Nosso antes da Comunhão.")
        ),
        TriviaQuestion(
            id = "t77",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Dónde descendió el Espíritu Santo en Pentecostés?", "Where did the Holy Spirit descend at Pentecost?", "Onde o Espírito Santo desceu no Pentecostes?"),
            options = listOf(
                LocalizedText("Roma", "Rome", "Roma"),
                LocalizedText("Atenas", "Athens", "Atenas"),
                LocalizedText("Belén", "Bethlehem", "Belém"),
                LocalizedText("Jerusalén", "Jerusalem", "Jerusalém"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Los apóstoles estaban reunidos en Jerusalén cuando vino el Espíritu Santo.", "The apostles were gathered in Jerusalem when the Holy Spirit came.", "Os apóstolos estavam reunidos em Jerusalém quando veio o Espírito Santo.")
        ),
        TriviaQuestion(
            id = "t78",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué concilio definió la consustancialidad del Hijo con el Padre?", "Which council defined the consubstantiality of the Son with the Father?", "Qual concílio definiu a consubstancialidade do Filho com o Pai?"),
            options = listOf(
                LocalizedText("Calcedonia", "Chalcedon", "Calcedônia"),
                LocalizedText("Cartago", "Carthage", "Cartago"),
                LocalizedText("Éfeso", "Ephesus", "Éfeso"),
                LocalizedText("Nicea (325)", "Nicaea (325)", "Niceia (325)"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El Concilio de Nicea condenó el arrianismo y proclamó el Credo.", "The Council of Nicaea condemned Arianism and proclaimed the Creed.", "O Concílio de Niceia condenou o arianismo e proclamou o Credo.")
        ),
        TriviaQuestion(
            id = "t79",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué edicto legalizó el cristianismo en el Imperio romano?", "Which edict legalized Christianity in the Roman Empire?", "Qual edito legalizou o cristianismo no Império Romano?"),
            options = listOf(
                LocalizedText("Edicto de Milán (313)", "Edict of Milan (313)", "Edito de Milão (313)"),
                LocalizedText("Edicto de Tesalónica", "Edict of Thessalonica", "Edito de Tessalônica"),
                LocalizedText("Edicto de Diocleciano", "Edict of Diocletian", "Edito de Diocleciano"),
                LocalizedText("Edicto de Claudius", "Edict of Claudius", "Edito de Cláudio"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Constantino promulgó el Edicto de Milán en 313.", "Constantine issued the Edict of Milan in 313.", "Constantino promulgou o Edito de Milão em 313.")
        ),
        TriviaQuestion(
            id = "t80",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿En qué año ocurrió el Gran Cisma entre Oriente y Occidente?", "In what year did the Great Schism between East and West occur?", "Em que ano ocorreu o Grande Cisma entre Oriente e Ocidente?"),
            options = listOf(
                LocalizedText("1054", "1054", "1054"),
                LocalizedText("1492", "1492", "1492"),
                LocalizedText("1517", "1517", "1517"),
                LocalizedText("1215", "1215", "1215"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("En 1054 se separaron la Iglesia de Oriente y la de Occidente.", "In 1054 the Churches of East and West separated.", "Em 1054 separaram-se as Igrejas do Oriente e do Ocidente.")
        ),
        TriviaQuestion(
            id = "t81",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué concilio definió la infalibilidad del Papa?", "Which council defined papal infallibility?", "Qual concílio definiu a infalibilidade do Papa?"),
            options = listOf(
                LocalizedText("Nicea", "Nicaea", "Niceia"),
                LocalizedText("Vaticano I", "Vatican I", "Vaticano I"),
                LocalizedText("Vaticano II", "Vatican II", "Vaticano II"),
                LocalizedText("Trento", "Trent", "Trento"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("El Vaticano I (1869-1870) definió la infalibilidad papal.", "Vatican I (1869-1870) defined papal infallibility.", "O Vaticano I (1869-1870) definiu a infalibilidade papal.")
        ),
        TriviaQuestion(
            id = "t82",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué papa convocó el Concilio Vaticano II?", "Which pope convoked Vatican II?", "Qual papa convocou o Concílio Vaticano II?"),
            options = listOf(
                LocalizedText("Pablo VI", "Paul VI", "Paulo VI"),
                LocalizedText("Juan Pablo I", "John Paul I", "João Paulo I"),
                LocalizedText("Pío XII", "Pius XII", "Pio XII"),
                LocalizedText("Juan XXIII", "John XXIII", "João XXIII"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Juan XXIII abrió el Vaticano II en 1962.", "John XXIII opened Vatican II in 1962.", "João XXIII abriu o Vaticano II em 1962.")
        ),
        TriviaQuestion(
            id = "t83",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Quién es el primer papa jesuita y americano?", "Who is the first Jesuit and American pope?", "Quem é o primeiro papa jesuíta e americano?"),
            options = listOf(
                LocalizedText("Benedicto XVI", "Benedict XVI", "Bento XVI"),
                LocalizedText("Juan Pablo II", "John Paul II", "João Paulo II"),
                LocalizedText("Pablo VI", "Paul VI", "Paulo VI"),
                LocalizedText("Papa Francisco", "Pope Francis", "Papa Francisco"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Francisco, argentino, fue el primer papa de América.", "Francis, from Argentina, was the first pope from the Americas.", "Francisco, argentino, foi o primeiro papa das Américas.")
        ),
        TriviaQuestion(
            id = "t84",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué papa renunció en 2013, hecho inusual en siglos?", "Which pope resigned in 2013, unusual for centuries?", "Qual papa renunciou em 2013, fato incomum há séculos?"),
            options = listOf(
                LocalizedText("Benedicto XVI", "Benedict XVI", "Bento XVI"),
                LocalizedText("Juan Pablo II", "John Paul II", "João Paulo II"),
                LocalizedText("Pío XI", "Pius XI", "Pio XI"),
                LocalizedText("Pablo VI", "Paul VI", "Paulo VI"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Benedicto XVI renunció en 2013, el primer papa en renunciar en casi 600 años.", "Benedict XVI resigned in 2013, the first pope to resign in nearly 600 years.", "Bento XVI renunciou em 2013, o primeiro papa a renunciar em quase 600 anos.")
        ),
        TriviaQuestion(
            id = "t85",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué papa instituyó las Jornadas Mundiales de la Juventud?", "Which pope instituted World Youth Day?", "Qual papa instituiu as Jornadas Mundiais da Juventude?"),
            options = listOf(
                LocalizedText("Juan Pablo II", "John Paul II", "João Paulo II"),
                LocalizedText("Pablo VI", "Paul VI", "Paulo VI"),
                LocalizedText("Benedicto XVI", "Benedict XVI", "Bento XVI"),
                LocalizedText("Francisco", "Francis", "Francisco"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Juan Pablo II instituyó la JMJ en 1984.", "John Paul II instituted World Youth Day in 1984.", "João Paulo II instituiu a JMJ em 1984.")
        ),
        TriviaQuestion(
            id = "t86",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué papa, llamado «el Grande», renovó Roma y el canto gregoriano?", "Which pope, called 'the Great,' renewed Rome and Gregorian chant?", "Qual papa, chamado 'o Grande', renovou Roma e o canto gregoriano?"),
            options = listOf(
                LocalizedText("San Pío", "Saint Pius", "São Pio"),
                LocalizedText("San Gregorio Magno", "Saint Gregory the Great", "São Gregório Magno"),
                LocalizedText("San León", "Saint Leo", "São Leão"),
                LocalizedText("San Inocencio", "Saint Innocent", "São Inocêncio"),
            ),
            correctIndex = 1,
            doctrinalExplanation = LocalizedText("Gregorio Magno (590-604) dio nombre al canto gregoriano.", "Gregory the Great (590-604) gave his name to Gregorian chant.", "Gregório Magno (590-604) deu nome ao canto gregoriano.")
        ),
        TriviaQuestion(
            id = "t87",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué misionero es llamado Apóstol de Alemania?", "Which missionary is called the Apostle of Germany?", "Qual missionário é chamado Apóstolo da Alemanha?"),
            options = listOf(
                LocalizedText("San Agustín", "Saint Augustine", "São Agostinho"),
                LocalizedText("San Francisco", "Saint Francis", "São Francisco"),
                LocalizedText("San Bonifacio", "Saint Boniface", "São Bonifácio"),
                LocalizedText("San Patricio", "Saint Patrick", "São Patrício"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("San Bonifacio evangelizó a los pueblos germánicos en el siglo VIII.", "Saint Boniface evangelized the Germanic peoples in the 8th century.", "São Bonifácio evangelizou os povos germânicos no século VIII.")
        ),
        TriviaQuestion(
            id = "t88",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué abadía, fundada en 910, impulsó la reforma monástica de Cluny?", "Which abbey, founded in 910, drove the Cluniac monastic reform?", "Qual abadia, fundada em 910, impulsionou a reforma monástica de Cluny?"),
            options = listOf(
                LocalizedText("San Galo", "St. Gall", "São Galo"),
                LocalizedText("Fontenay", "Fontenay", "Fontenay"),
                LocalizedText("Cluny", "Cluny", "Cluny"),
                LocalizedText("Montecasino", "Monte Cassino", "Monte Cassino"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("La abadía de Cluny renovó la vida monástica en la Europa medieval.", "The abbey of Cluny renewed monastic life in medieval Europe.", "A abadia de Cluny renovou a vida monástica na Europa medieval.")
        ),
        TriviaQuestion(
            id = "t89",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Quién imprimió la Biblia de Gutenberg hacia 1450?", "Who printed the Gutenberg Bible around 1450?", "Quem imprimiu a Bíblia de Gutenberg por volta de 1450?"),
            options = listOf(
                LocalizedText("Johannes Gutenberg", "Johannes Gutenberg", "Johannes Gutenberg"),
                LocalizedText("Isaac Newton", "Isaac Newton", "Isaac Newton"),
                LocalizedText("Galileo Galilei", "Galileo Galilei", "Galileo Galilei"),
                LocalizedText("Leonardo da Vinci", "Leonardo da Vinci", "Leonardo da Vinci"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Gutenberg inventó la imprenta de tipos móviles en Europa.", "Gutenberg invented the movable-type printing press in Europe.", "Gutenberg inventou a imprensa de tipos móveis na Europa.")
        ),
        TriviaQuestion(
            id = "t90",
            category = LocalizedText("Historia de la Iglesia", "Church History", "História da Igreja"),
            question = LocalizedText("¿Qué concilio aprobó la Vulgata como texto oficial de la Biblia latina?", "Which council approved the Vulgate as the official Latin Bible?", "Qual concílio aprovou a Vulgata como texto oficial da Bíblia latina?"),
            options = listOf(
                LocalizedText("Trento", "Trent", "Trento"),
                LocalizedText("Vaticano II", "Vatican II", "Vaticano II"),
                LocalizedText("Nicea", "Nicaea", "Niceia"),
                LocalizedText("Calcedonia", "Chalcedon", "Calcedônia"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("El Concilio de Trento (1546) confirmó la Vulgata de San Jerónimo.", "The Council of Trent (1546) confirmed Saint Jerome's Vulgate.", "O Concílio de Trento (1546) confirmou a Vulgata de São Jerônimo.")
        ),
        TriviaQuestion(
            id = "t91",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿En cuántas Personas es un solo Dios según la fe católica?", "Into how many Persons is the one God?", "Em quantas Pessoas é um só Deus segundo a fé católica?"),
            options = listOf(
                LocalizedText("Diez", "Ten", "Dez"),
                LocalizedText("Una", "One", "Uma"),
                LocalizedText("Dos", "Two", "Duas"),
                LocalizedText("Tres: Padre, Hijo y Espíritu Santo", "Three: Father, Son and Holy Spirit", "Três: Pai, Filho e Espírito Santo"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Dios es uno en tres Personas, misterio central del cristianismo.", "God is one in three Persons, the central mystery of Christianity.", "Deus é um em três Pessoas, mistério central do cristianismo.")
        ),
        TriviaQuestion(
            id = "t92",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cuántos Mandamientos dio Dios a Moisés?", "How many Commandments did God give to Moses?", "Quantos Mandamentos Deus deu a Moisés?"),
            options = listOf(
                LocalizedText("Cinco", "Five", "Cinco"),
                LocalizedText("Siete", "Seven", "Sete"),
                LocalizedText("Diez", "Ten", "Dez"),
                LocalizedText("Doce", "Twelve", "Doze"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Los Diez Mandamientos resumen el amor a Dios y al prójimo.", "The Ten Commandments summarize love of God and neighbor.", "Os Dez Mandamentos resumem o amor a Deus e ao próximo.")
        ),
        TriviaQuestion(
            id = "t93",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cuántas son las Bienaventuranzas?", "How many are the Beatitudes?", "Quantas são as Bem-aventuranças?"),
            options = listOf(
                LocalizedText("Diez", "Ten", "Dez"),
                LocalizedText("Cuatro", "Four", "Quatro"),
                LocalizedText("Siete", "Seven", "Sete"),
                LocalizedText("Ocho", "Eight", "Oito"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Jesús proclamó ocho Bienaventuranzas en el Sermón de la Montaña.", "Jesus proclaimed eight Beatitudes in the Sermon on the Mount.", "Jesus proclamou oito Bem-aventuranças no Sermão da Montanha.")
        ),
        TriviaQuestion(
            id = "t94",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cómo comienza la oración que Jesús enseñó a sus discípulos?", "How does the prayer Jesus taught His disciples begin?", "Como começa a oração que Jesus ensinou a seus discípulos?"),
            options = listOf(
                LocalizedText("«Ave María»", "'Hail Mary'", "'Ave-Maria'"),
                LocalizedText("«Señor, ten piedad»", "'Lord, have mercy'", "'Senhor, tende piedade'"),
                LocalizedText("«Amén»", "'Amen'", "'Amém'"),
                LocalizedText("«Padre nuestro»", "'Our Father'", "'Pai-nosso'"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("El Padrenuestro es la oración perfecta enseñada por Cristo.", "The Our Father is the perfect prayer taught by Christ.", "O Pai-Nosso é a oração perfeita ensinada por Cristo.")
        ),
        TriviaQuestion(
            id = "t95",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué oración comienza así: «Dios te salve, María…»?", "Which prayer begins: 'Hail, Mary…'", "Qual oração começa: 'Ave, Maria…??'"),
            options = listOf(
                LocalizedText("El Avemaría", "The Hail Mary", "A Ave-Maria"),
                LocalizedText("El Padrenuestro", "The Our Father", "O Pai-Nosso"),
                LocalizedText("El Credo", "The Creed", "O Credo"),
                LocalizedText("La Salve", "The Salve", "A Salve"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("El Avemaría saluda a María con las palabras del ángel Gabriel.", "The Hail Mary greets Mary with the angel Gabriel's words.", "A Ave-Maria saúda Maria com as palavras do anjo Gabriel.")
        ),
        TriviaQuestion(
            id = "t96",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué cuatro notas caracterizan a la Iglesia?", "Which four marks characterize the Church?", "Quais quatro marcas caracterizam a Igreja?"),
            options = listOf(
                LocalizedText("Una, Santa, Católica y Apostólica", "One, Holy, Catholic and Apostolic", "Una, Santa, Católica e Apostólica"),
                LocalizedText("Grande, fuerte, rica y nueva", "Great, strong, rich and new", "Grande, forte, rica e nova"),
                LocalizedText("Antigua, joven, popular y moderna", "Ancient, young, popular and modern", "Antiga, jovem, popular e moderna"),
                LocalizedText("Silenciosa, solemne, pública y antigua", "Silent, solemn, public and ancient", "Silenciosa, solene, pública e antiga"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Una, Santa, Católica y Apostólica: las marcas del Credo.", "One, Holy, Catholic and Apostolic: the marks of the Creed.", "Uma, Santa, Católica e Apostólica: as marcas do Credo.")
        ),
        TriviaQuestion(
            id = "t97",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cuántos son los dones del Espíritu Santo?", "How many gifts of the Holy Spirit are there?", "Quantos são os dons do Espírito Santo?"),
            options = listOf(
                LocalizedText("Tres", "Three", "Três"),
                LocalizedText("Cinco", "Five", "Cinco"),
                LocalizedText("Siete", "Siete", "Sete"),
                LocalizedText("Diez", "Ten", "Dez"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Los siete dones: sabiduría, ciencia, consejo, fortaleza, piedad, temor de Dios y entendimiento.", "The seven gifts: wisdom, knowledge, counsel, fortitude, piety, fear of God and understanding.", "Os sete dons: sabedoria, ciência, conselho, fortaleza, piedade, temor de Deus e entendimento.")
        ),
        TriviaQuestion(
            id = "t98",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cuáles son las tres virtudes teologales?", "Which are the three theological virtues?", "Quais são as três virtudes teologais?"),
            options = listOf(
                LocalizedText("Pobreza, Castidad y Obediencia", "Poverty, Chastity and Obedience", "Pobreza, Castidade e Obediência"),
                LocalizedText("Fe, Obediencia y Humildad", "Faith, Obedience and Humility", "Fé, Obediência e Humildade"),
                LocalizedText("Fe, Esperanza y Caridad", "Faith, Hope and Charity", "Fé, Esperança e Caridade"),
                LocalizedText("Prudencia, Justicia y Templanza", "Prudence, Justice and Temperance", "Prudência, Justiça e Temperança"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("Fe, Esperanza y Caridad nos unen a Dios.", "Faith, Hope and Charity unite us to God.", "Fé, Esperança e Caridade nos unem a Deus.")
        ),
        TriviaQuestion(
            id = "t99",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Cuáles son las cuatro virtudes cardinales?", "Which are the four cardinal virtues?", "Quais são as quatro virtudes cardeais?"),
            options = listOf(
                LocalizedText("Prudencia, Justicia, Templanza y Fortaleza", "Prudence, Justice, Temperance and Fortitude", "Prudência, Justiça, Temperança e Fortaleza"),
                LocalizedText("Sabiduría, Ciencia, Piedad y Temor", "Wisdom, Knowledge, Piety and Fear", "Sabedoria, Ciência, Piedade e Temor"),
                LocalizedText("Paciencia, Humildad, Obediencia y Silencio", "Patience, Humility, Obedience and Silence", "Paciência, Humildade, Obediência e Silêncio"),
                LocalizedText("Fe, Esperanza, Caridad y Pureza", "Faith, Hope, Charity and Purity", "Fé, Esperança, Caridade e Pureza"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Las cardinales son eje de la vida moral cristiana.", "The cardinal virtues are the hinges of Christian moral life.", "As cardeais são o eixo da vida moral cristã.")
        ),
        TriviaQuestion(
            id = "t100",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Quiénes cometieron el pecado original?", "Who committed original sin?", "Quem cometeu o pecado original?"),
            options = listOf(
                LocalizedText("Caín y Abel", "Cain and Abel", "Caim e Abel"),
                LocalizedText("Noé y su familia", "Noah and his family", "Noé e sua família"),
                LocalizedText("David y Betsabé", "David and Bathsheba", "Davi e Betsabé"),
                LocalizedText("Adán y Eva", "Adam and Eve", "Adão e Eva"),
            ),
            correctIndex = 3,
            doctrinalExplanation = LocalizedText("Adán y Eva desobedecieron a Dios en el paraíso.", "Adam and Eve disobeyed God in paradise.", "Adão e Eva desobedeceram a Deus no paraíso.")
        ),
        TriviaQuestion(
            id = "t101",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué profesamos al final del Credo de los Apóstoles?", "What do we profess at the end of the Apostles' Creed?", "O que professamos no final do Creio dos Apóstolos?"),
            options = listOf(
                LocalizedText("La vida eterna", "Life everlasting", "A vida eterna"),
                LocalizedText("La pobreza", "Poverty", "A pobreza"),
                LocalizedText("El silencio", "Silence", "O silêncio"),
                LocalizedText("La obediencia", "Obedience", "A obediência"),
            ),
            correctIndex = 0,
            doctrinalExplanation = LocalizedText("Creemos en «la resurrección de la carne y la vida eterna».", "We believe in 'the resurrection of the body and life everlasting.'", "Cremos na 'ressurreição da carne e na vida eterna.'")
        ),
        TriviaQuestion(
            id = "t102",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué es el purgatorio según el Catecismo?", "What is purgatory according to the Catechism?", "O que é o purgatório segundo o Catecismo?"),
            options = listOf(
                LocalizedText("Un estado sin Dios", "A state without God", "Um estado sem Deus"),
                LocalizedText("Un lugar de gozo eterno", "A place of eternal joy", "Um lugar de gozo eterno"),
                LocalizedText("Purificación de las almas antes del cielo", "Purification of souls before heaven", "Purificação das almas antes do céu"),
                LocalizedText("El infierno", "Hell", "O inferno"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El purgatorio purifica a quienes mueren en gracia antes del cielo.", "Purgatory purifies those who die in grace before heaven.", "O purgatório purifica os que morrem em graça antes do céu.")
        ),
        TriviaQuestion(
            id = "t103",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué sacramento perdona los pecados y restaura la gracia?", "Which sacrament forgives sins and restores grace?", "Qual sacramento perdoa os pecados e restaura a graça?"),
            options = listOf(
                LocalizedText("La Eucaristía", "Eucharist", "A Eucaristia"),
                LocalizedText("La Confirmación", "Confirmation", "A Confirmação"),
                LocalizedText("La Penitencia (Confesión)", "Penance (Confession)", "A Penitência (Confissão)"),
                LocalizedText("El Bautismo", "Baptism", "O Batismo"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("En la Confesión, el pecador arrepentido recibe la absolución.", "In Confession, the repentant sinner receives absolution.", "Na Confissão, o pecador arrependido recebe a absolvição.")
        ),
        TriviaQuestion(
            id = "t104",
            category = LocalizedText("Catecismo", "Catechism", "Catecismo"),
            question = LocalizedText("¿Qué misterio celebramos al rezar el rosario?", "What mystery do we celebrate when praying the rosary?", "Qual mistério celebramos ao rezar o rosário?"),
            options = listOf(
                LocalizedText("La historia de Roma", "The history of Rome", "A história de Roma"),
                LocalizedText("La naturaleza", "Nature", "A natureza"),
                LocalizedText("La vida de Jesús y María", "The life of Jesus and Mary", "A vida de Jesus e Maria"),
                LocalizedText("Solo la Creación", "Only Creation", "Somente a Criação"),
            ),
            correctIndex = 2,
            doctrinalExplanation = LocalizedText("El rosario medita los misterios gozosos, dolorosos, gloriosos y luminosos.", "The rosary meditates on the joyful, sorrowful, glorious and luminous mysteries.", "O rosário medita os mistérios gozosos, dolorosos, gloriosos e luminosos.")
        ),
    )

    val nearbyParishes: List<NearbyParish> = listOf(
        // España - Madrid
        NearbyParish(
            id = "es_mad_1",
            name = "Santa Iglesia Catedral Basílica de Santa María la Real de la Almudena",
            diocese = "Archidiócesis de Madrid",
            address = "Calle de Bailén, 10, Centro, 28013 Madrid",
            country = "España",
            stateOrRegion = "Comunidad de Madrid",
            city = "Madrid",
            latitude = 40.4154,
            longitude = -3.7144,
            distanceDisplay = "Centro",
            sundayMasses = "10:30, 12:00 (Misa Mayor), 13:30, 18:00, 19:00, 20:00",
            weekdayMasses = "12:00, 18:00, 19:00",
            confessionTimes = "Lunes a Domingo: 10:30 - 13:00 y 17:30 - 20:00",
            adorationTimes = "Capilla del Santísimo: 09:00 - 20:00",
            contactPhone = "+34 91 542 22 01",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "es_mad_2",
            name = "Parroquia Basílica de la Concepción de Nuestra Señora",
            diocese = "Archidiócesis de Madrid",
            address = "Calle de Goya, 26, Salamanca, 28001 Madrid",
            country = "España",
            stateOrRegion = "Comunidad de Madrid",
            city = "Madrid",
            latitude = 40.4253,
            longitude = -3.6853,
            distanceDisplay = "Salamanca",
            sundayMasses = "09:00, 10:00, 11:00, 12:00, 13:00, 19:00, 20:00, 21:00",
            weekdayMasses = "08:00, 09:00, 10:00, 12:00, 19:00, 20:00",
            confessionTimes = "Permanente durante todas las Misas y mañanas de 10:00 a 13:00",
            adorationTimes = "Capilla de Adoración Perpetua (24 Horas / 365 Días)",
            contactPhone = "+34 91 577 35 15",
            isPerpetualAdoration = true
        ),
        // España - Castilla-La Mancha
        NearbyParish(
            id = "es_tol_1",
            name = "Catedral Primada de Santa María de Toledo",
            diocese = "Archidiócesis Primada de Toledo",
            address = "Calle Cardenal Cisneros, 1, 45002 Toledo",
            country = "España",
            stateOrRegion = "Castilla-La Mancha",
            city = "Toledo",
            latitude = 39.8571,
            longitude = -4.0244,
            distanceDisplay = "Centro Histórico",
            sundayMasses = "09:45 (Rito Mozárabe), 12:00, 18:30",
            weekdayMasses = "09:00 (Rito Mozárabe), 18:30",
            confessionTimes = "De 10:00 a 12:30 y de 16:30 a 18:30",
            adorationTimes = "Jueves Eucarísticos: 10:00 - 18:00",
            contactPhone = "+34 925 22 22 41",
            isPerpetualAdoration = false
        ),
        // España - Cataluña
        NearbyParish(
            id = "es_bcn_1",
            name = "Basílica de la Sagrada Familia",
            diocese = "Archidiócesis de Barcelona",
            address = "Carrer de Mallorca, 401, 08013 Barcelona",
            country = "España",
            stateOrRegion = "Cataluña",
            city = "Barcelona",
            latitude = 41.4036,
            longitude = 2.1744,
            distanceDisplay = "Eixample",
            sundayMasses = "09:00 (Misa Internacional en Nave Central)",
            weekdayMasses = "09:00 (Cripta de la Sagrada Familia)",
            confessionTimes = "30 min antes de cada Santa Misa",
            adorationTimes = "Capilla del Santísimo en la Cripta: 09:30 - 19:00",
            contactPhone = "+34 932 080 414",
            isPerpetualAdoration = false
        ),
        // España - Andalucía
        NearbyParish(
            id = "es_sev_1",
            name = "Catedral de Santa María de la Sede y Giralda",
            diocese = "Archidiócesis de Sevilla",
            address = "Av. de la Constitución, s/n, Casco Antiguo, 41004 Sevilla",
            country = "España",
            stateOrRegion = "Andalucía",
            city = "Sevilla",
            latitude = 37.3862,
            longitude = -5.9926,
            distanceDisplay = "Casco Antiguo",
            sundayMasses = "08:30, 10:00, 11:30, 12:30, 18:00, 19:00, 20:00",
            weekdayMasses = "08:30, 10:00, 12:30, 19:30",
            confessionTimes = "Durante las celebraciones matutinas y vespertinas",
            adorationTimes = "Capilla Real: 09:00 - 19:00",
            contactPhone = "+34 954 21 49 71",
            isPerpetualAdoration = false
        ),
        // España - Galicia
        NearbyParish(
            id = "es_gal_1",
            name = "Santa Apostólica y Metropolitana Iglesia Catedral de Santiago de Compostela",
            diocese = "Archidiócesis de Santiago de Compostela",
            address = "Praza do Obradoiro, s/n, 15704 Santiago de Compostela",
            country = "España",
            stateOrRegion = "Galicia",
            city = "Santiago de Compostela",
            latitude = 42.8806,
            longitude = -8.5456,
            distanceDisplay = "Plaza del Obradoiro",
            sundayMasses = "07:30, 09:30, 12:00 (Misa del Peregrino con Botafumeiro), 19:30",
            weekdayMasses = "07:30, 09:30, 12:00, 19:30",
            confessionTimes = "Capilla de la Soledad: Continuo de 08:30 a 19:30",
            adorationTimes = "Capilla de la Comunión: Adoración Perpetua",
            contactPhone = "+34 981 58 35 48",
            isPerpetualAdoration = true
        ),
        // España - Valencia
        NearbyParish(
            id = "es_val_1",
            name = "Catedral Basílica Metropolitana de Santa María (Santo Cáliz)",
            diocese = "Archidiócesis de Valencia",
            address = "Plaza de la Reina, s/n, 46003 Valencia",
            country = "España",
            stateOrRegion = "Comunidad Valenciana",
            city = "Valencia",
            latitude = 39.4753,
            longitude = -0.3752,
            distanceDisplay = "Plaza de la Reina",
            sundayMasses = "09:00, 10:30, 12:00 (Misa Conventual), 18:00, 19:00",
            weekdayMasses = "08:30, 09:30, 11:00, 19:00",
            confessionTimes = "Lunes a Sábado: 10:00 - 12:30 y 17:30 - 19:30",
            adorationTimes = "Capilla del Santo Cáliz: 10:00 - 18:00",
            contactPhone = "+34 963 91 81 27",
            isPerpetualAdoration = false
        ),
        // México - Ciudad de México
        NearbyParish(
            id = "mx_cdmx_1",
            name = "Insigne y Nacional Basílica de Santa María de Guadalupe",
            diocese = "Arquidiócesis Primada de México",
            address = "Fray Juan de Zumárraga No. 2, Villa de Guadalupe, 07050 Ciudad de México",
            country = "México",
            stateOrRegion = "Ciudad de México",
            city = "Ciudad de México",
            latitude = 19.4846,
            longitude = -99.1176,
            distanceDisplay = "Norte CDMX",
            sundayMasses = "Cada hora de 06:00 a 20:00 (Altar Mayor)",
            weekdayMasses = "07:00, 08:00, 09:00, 10:00, 12:00, 16:00, 18:00, 19:00",
            confessionTimes = "Confesionario Central: Continuo de 07:00 a 19:30",
            adorationTimes = "Capilla del Santísimo y Adoración Nocturna Permanente",
            contactPhone = "+52 55 5118 0500",
            isPerpetualAdoration = true
        ),
        NearbyParish(
            id = "mx_cdmx_2",
            name = "Catedral Metropolitana de la Asunción de la Santísima Virgen María",
            diocese = "Arquidiócesis Primada de México",
            address = "Plaza de la Constitución S/N, Centro Histórico, 06000 Ciudad de México",
            country = "México",
            stateOrRegion = "Ciudad de México",
            city = "Ciudad de México",
            latitude = 19.4343,
            longitude = -99.1331,
            distanceDisplay = "Zócalo",
            sundayMasses = "08:30, 10:30, 12:00 (Misa Capitular), 13:30, 17:00, 18:00",
            weekdayMasses = "09:30, 12:00, 17:00",
            confessionTimes = "Lunes a Viernes de 10:00 a 14:00 y de 16:00 a 18:00",
            adorationTimes = "Jueves de 10:00 a 17:00",
            contactPhone = "+52 55 4165 4013",
            isPerpetualAdoration = false
        ),
        // México - Jalisco
        NearbyParish(
            id = "mx_jal_1",
            name = "Catedral Basílica de la Asunción de María Santísima",
            diocese = "Arquidiócesis de Guadalajara",
            address = "Av. Fray Antonio Alcalde 10, Centro, 44100 Guadalajara, Jal.",
            country = "México",
            stateOrRegion = "Jalisco",
            city = "Guadalajara",
            latitude = 20.6770,
            longitude = -103.3469,
            distanceDisplay = "Centro Histórico",
            sundayMasses = "08:00, 09:30, 10:30, 12:00, 13:30, 17:00, 18:30, 20:00",
            weekdayMasses = "08:00, 09:30, 12:00, 18:00, 19:30",
            confessionTimes = "Durante todas las misas y de 10:00 a 13:00",
            adorationTimes = "Capilla del Santísimo: 08:00 a 19:00",
            contactPhone = "+52 33 3614 5504",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "mx_jal_2",
            name = "Basílica de Nuestra Señora de Zapopan",
            diocese = "Arquidiócesis de Guadalajara",
            address = "Calle Eva Briseño 152, Zapopan Centro, 45100 Zapopan, Jal.",
            country = "México",
            stateOrRegion = "Jalisco",
            city = "Zapopan",
            latitude = 20.7214,
            longitude = -103.3920,
            distanceDisplay = "Plaza de las Américas",
            sundayMasses = "07:00, 08:00, 09:00, 10:00, 11:00, 12:00, 13:00, 17:00, 18:00, 19:00, 20:00",
            weekdayMasses = "07:00, 08:00, 09:00, 10:00, 12:00, 18:00, 19:00, 20:00",
            confessionTimes = "Lunes a Domingo: 08:00 - 13:00 y 17:00 - 20:00",
            adorationTimes = "Capilla de Adoración Franciscana Continua",
            contactPhone = "+52 33 3633 0141",
            isPerpetualAdoration = true
        ),
        // México - Nuevo León
        NearbyParish(
            id = "mx_nl_1",
            name = "Catedral Metropolitana de Nuestra Señora de Monterrey",
            diocese = "Arquidiócesis de Monterrey",
            address = "Juan Zuazua 1100 Sur, Centro, 64000 Monterrey, N.L.",
            country = "México",
            stateOrRegion = "Nuevo León",
            city = "Monterrey",
            latitude = 25.6667,
            longitude = -100.3105,
            distanceDisplay = "Macroplaza",
            sundayMasses = "08:30, 10:00, 11:30, 13:00, 17:00, 18:30, 20:00",
            weekdayMasses = "08:00, 11:30, 18:30",
            confessionTimes = "Lunes a Viernes 11:00 - 13:00 y 17:00 - 19:00",
            adorationTimes = "Jueves Eucarísticos: 09:00 - 18:00",
            contactPhone = "+52 81 8342 4863",
            isPerpetualAdoration = false
        ),
        // México - Puebla
        NearbyParish(
            id = "mx_pue_1",
            name = "Catedral Basílica de la Inmaculada Concepción",
            diocese = "Arquidiócesis de Puebla de los Ángeles",
            address = "C. 16 de Septiembre s/n, Centro Histórico, 72000 Puebla, Pue.",
            country = "México",
            stateOrRegion = "Puebla",
            city = "Puebla",
            latitude = 19.0428,
            longitude = -98.1983,
            distanceDisplay = "Zócalo de Puebla",
            sundayMasses = "07:00, 08:00, 09:00, 10:00, 12:00, 13:00, 18:00, 19:00",
            weekdayMasses = "08:00, 09:00, 12:00, 19:00",
            confessionTimes = "De 09:00 a 13:00 y de 17:00 a 19:00",
            adorationTimes = "Capilla del Santísimo: 09:00 - 18:00",
            contactPhone = "+52 222 232 3801",
            isPerpetualAdoration = false
        ),
        // Colombia - Bogotá D.C.
        NearbyParish(
            id = "co_bog_1",
            name = "Catedral Primada de Colombia (Basílica de la Inmaculada Concepción)",
            diocese = "Arquidiócesis de Bogotá",
            address = "Plaza de Bolívar, Cra. 7 #10-70, La Candelaria, Bogotá",
            country = "Colombia",
            stateOrRegion = "Bogotá D.C.",
            city = "Bogotá",
            latitude = 4.5981,
            longitude = -74.0758,
            distanceDisplay = "Plaza Bolívar",
            sundayMasses = "08:00, 10:00, 12:00, 13:30, 17:00",
            weekdayMasses = "12:00, 17:00",
            confessionTimes = "Durante todas las Misas y previa cita en despacho",
            adorationTimes = "Capilla del Sagrario: 09:00 - 18:00",
            contactPhone = "+57 601 341 1954",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "co_bog_2",
            name = "Santuario del Señor Caído de Monserrate",
            diocese = "Arquidiócesis de Bogotá",
            address = "Cerro de Monserrate, Santa Fe, Bogotá",
            country = "Colombia",
            stateOrRegion = "Bogotá D.C.",
            city = "Bogotá",
            latitude = 4.6056,
            longitude = -74.0555,
            distanceDisplay = "Cerro de Monserrate",
            sundayMasses = "06:30, 08:00, 09:30, 11:00, 12:30, 14:00, 15:30",
            weekdayMasses = "08:00, 10:00, 12:00",
            confessionTimes = "Durante las misas dominicales",
            adorationTimes = "Adoración permanente ante el Santísimo",
            contactPhone = "+57 601 284 7677",
            isPerpetualAdoration = true
        ),
        // Colombia - Antioquia
        NearbyParish(
            id = "co_ant_1",
            name = "Catedral Basílica Metropolitana de la Inmaculada Concepción",
            diocese = "Arquidiócesis de Medellín",
            address = "Carrera 48 #56-81, Parque de Bolívar, Medellín",
            country = "Colombia",
            stateOrRegion = "Antioquia",
            city = "Medellín",
            latitude = 6.2541,
            longitude = -75.5658,
            distanceDisplay = "Parque Bolívar",
            sundayMasses = "07:00, 08:30, 10:00, 12:00, 18:00",
            weekdayMasses = "08:00, 12:00, 18:00",
            confessionTimes = "Lunes a Viernes: 10:00 - 12:00 y 16:00 - 18:00",
            adorationTimes = "Jueves: 09:00 - 18:00",
            contactPhone = "+57 604 513 1863",
            isPerpetualAdoration = false
        ),
        // Colombia - Valle del Cauca
        NearbyParish(
            id = "co_val_1",
            name = "Catedral Metropolitana de San Pedro Apóstol",
            diocese = "Arquidiócesis de Cali",
            address = "Calle 11 #5-53, Plaza de Cayzedo, Cali",
            country = "Colombia",
            stateOrRegion = "Valle del Cauca",
            city = "Cali",
            latitude = 3.4516,
            longitude = -76.5320,
            distanceDisplay = "Plaza de Cayzedo",
            sundayMasses = "08:00, 10:00, 12:00, 17:00, 18:30",
            weekdayMasses = "07:30, 12:00, 18:00",
            confessionTimes = "Martes y Jueves de 10:00 a 12:00",
            adorationTimes = "Capilla del Santísimo: 08:00 - 17:00",
            contactPhone = "+57 602 882 1740",
            isPerpetualAdoration = false
        ),
        // Argentina - Buenos Aires
        NearbyParish(
            id = "ar_bue_1",
            name = "Catedral Metropolitana de la Santísima Trinidad",
            diocese = "Arquidiócesis de Buenos Aires",
            address = "San Martín 27, San Nicolás, C1004 Buenos Aires",
            country = "Argentina",
            stateOrRegion = "Ciudad Autónoma de Buenos Aires",
            city = "Buenos Aires",
            latitude = -34.6076,
            longitude = -58.3732,
            distanceDisplay = "Plaza de Mayo",
            sundayMasses = "09:00, 10:30, 11:30 (Misa Mayor), 17:30, 19:00",
            weekdayMasses = "08:30, 12:30, 18:00",
            confessionTimes = "Lunes a Viernes: 10:00 - 12:30 y 16:30 - 18:00",
            adorationTimes = "Lunes a Viernes: 13:00 - 17:45",
            contactPhone = "+54 11 4343 0812",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "ar_bue_2",
            name = "Basílica Nacional de Nuestra Señora de Luján",
            diocese = "Arquidiócesis de Mercedes-Luján",
            address = "San Martín 51, B6700 Luján, Provincia de Buenos Aires",
            country = "Argentina",
            stateOrRegion = "Provincia de Buenos Aires",
            city = "Luján",
            latitude = -34.5644,
            longitude = -59.1217,
            distanceDisplay = "Plaza Belgrano",
            sundayMasses = "08:00, 09:30, 11:00, 12:30, 15:00, 17:00, 19:00",
            weekdayMasses = "08:00, 11:00, 17:00, 19:00",
            confessionTimes = "Continuo en el Claustro de la Reconciliación",
            adorationTimes = "Capilla del Santísimo: Adoración Perpetua",
            contactPhone = "+54 2323 42 0262",
            isPerpetualAdoration = true
        ),
        // Argentina - Córdoba
        NearbyParish(
            id = "ar_cba_1",
            name = "Catedral de Nuestra Señora de la Asunción de Córdoba",
            diocese = "Arquidiócesis de Córdoba",
            address = "Independencia 80, X5000 Córdoba",
            country = "Argentina",
            stateOrRegion = "Córdoba",
            city = "Córdoba",
            latitude = -31.4168,
            longitude = -64.1843,
            distanceDisplay = "Plaza San Martín",
            sundayMasses = "09:00, 11:00, 19:00, 20:30",
            weekdayMasses = "09:00, 19:00",
            confessionTimes = "Lunes a Sábado de 10:00 a 12:30 y 18:00 a 19:00",
            adorationTimes = "Jueves Eucarísticos: 10:00 - 18:30",
            contactPhone = "+54 351 422 3446",
            isPerpetualAdoration = false
        ),
        // Brasil - São Paulo / Aparecida
        NearbyParish(
            id = "br_sp_1",
            name = "Catedral Metropolitana de São Paulo (Catedral da Sé)",
            diocese = "Arquidiocese de São Paulo",
            address = "Praça da Sé - Sé, São Paulo - SP, 01001-000",
            country = "Brasil",
            stateOrRegion = "São Paulo",
            city = "São Paulo",
            latitude = -23.5505,
            longitude = -46.6333,
            distanceDisplay = "Praça da Sé",
            sundayMasses = "09:00, 11:00, 15:00, 17:00",
            weekdayMasses = "07:30, 12:00, 17:00",
            confessionTimes = "Segunda a Sexta: 09:00 às 11:30 e 14:00 às 16:30",
            adorationTimes = "Capela do Santíssimo: 08:00 às 18:00",
            contactPhone = "+55 11 3107 6832",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "br_apa_1",
            name = "Santuário Nacional de Nossa Senhora da Conceição Aparecida",
            diocese = "Arquidiocese de Aparecida",
            address = "Av. Dr. Júlio Prestes - Ponte Alta, Aparecida - SP, 12570-000",
            country = "Brasil",
            stateOrRegion = "São Paulo",
            city = "Aparecida",
            latitude = -22.8499,
            longitude = -45.2341,
            distanceDisplay = "Santuário Central",
            sundayMasses = "05:30, 08:00, 10:00, 12:00, 14:00, 16:00, 18:00",
            weekdayMasses = "06:45, 09:00, 12:00, 16:00, 18:00",
            confessionTimes = "Capela da Reconciliação: 06:00 às 18:00 contínuo",
            adorationTimes = "Capela do Santíssimo Sacramento: Adoração Perpétua",
            contactPhone = "+55 12 3104 1000",
            isPerpetualAdoration = true
        ),
        // Brasil - Rio de Janeiro
        NearbyParish(
            id = "br_rio_1",
            name = "Catedral Metropolitana de São Sebastião do Rio de Janeiro",
            diocese = "Arquidiocese de São Sebastião do Rio de Janeiro",
            address = "Av. Chile, 245 - Centro, Rio de Janeiro - RJ, 20031-170",
            country = "Brasil",
            stateOrRegion = "Rio de Janeiro",
            city = "Rio de Janeiro",
            latitude = -22.9108,
            longitude = -43.1808,
            distanceDisplay = "Centro / Lapa",
            sundayMasses = "08:00, 10:00, 12:00, 18:00",
            weekdayMasses = "12:00, 18:00",
            confessionTimes = "Durante as Missas de meio-dia",
            adorationTimes = "Segunda a Sexta: 08:00 às 17:00",
            contactPhone = "+55 21 2240 2669",
            isPerpetualAdoration = false
        ),
        // Brasil - Minas Gerais
        NearbyParish(
            id = "br_mg_1",
            name = "Santuário Arquidiocesano São Judas Tadeu",
            diocese = "Arquidiocese de Belo Horizonte",
            address = "Rua Restinga, 265 - Graça, Belo Horizonte - MG, 31140-410",
            country = "Brasil",
            stateOrRegion = "Minas Gerais",
            city = "Belo Horizonte",
            latitude = -19.9077,
            longitude = -43.9261,
            distanceDisplay = "Bairro Graça",
            sundayMasses = "07:00, 09:00, 11:00, 18:00, 20:00",
            weekdayMasses = "07:00, 19:00",
            confessionTimes = "Terça a Sexta: 14:30 às 17:00",
            adorationTimes = "Adoração Perpétua ao Santíssimo Sacramento",
            contactPhone = "+55 31 2526 4648",
            isPerpetualAdoration = true
        ),
        // Estados Unidos - New York & Miami & California
        NearbyParish(
            id = "us_ny_1",
            name = "St. Patrick's Cathedral",
            diocese = "Archdiocese of New York",
            address = "5th Ave, New York, NY 10022",
            country = "Estados Unidos",
            stateOrRegion = "New York",
            city = "New York",
            latitude = 40.7585,
            longitude = -73.9760,
            distanceDisplay = "Midtown Manhattan",
            sundayMasses = "07:00, 08:00, 09:00, 10:15 (High Mass), 12:00, 13:00 (Spanish), 17:30",
            weekdayMasses = "07:00, 07:30, 08:00, 12:00, 12:30, 13:00, 17:30",
            confessionTimes = "Monday - Friday: 11:30 AM - 12:00 PM; Saturdays 3:30 - 5:30 PM",
            adorationTimes = "Lady Chapel: 08:00 AM - 06:00 PM",
            contactPhone = "+1 212 753 2261",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "us_mia_1",
            name = "St. Mary's Cathedral / Ermita de la Caridad",
            diocese = "Archdiocese of Miami",
            address = "7525 NW 2nd Ave, Miami, FL 33150",
            country = "Estados Unidos",
            stateOrRegion = "Florida",
            city = "Miami",
            latitude = 25.8441,
            longitude = -80.1983,
            distanceDisplay = "Miami Metro",
            sundayMasses = "08:00, 10:00 (English), 12:00 (Spanish), 17:00",
            weekdayMasses = "07:30, 12:00 (Spanish/English)",
            confessionTimes = "Saturdays 4:00 PM - 5:00 PM or by appointment",
            adorationTimes = "Perpetual Adoration Chapel (24/7 with Keycode)",
            contactPhone = "+1 305 759 4531",
            isPerpetualAdoration = true
        ),
        NearbyParish(
            id = "us_cal_1",
            name = "Cathedral of Our Lady of the Angels",
            diocese = "Archdiocese of Los Angeles",
            address = "555 W Temple St, Los Angeles, CA 90012",
            country = "Estados Unidos",
            stateOrRegion = "California",
            city = "Los Angeles",
            latitude = 34.0583,
            longitude = -118.2467,
            distanceDisplay = "Downtown LA",
            sundayMasses = "08:00, 10:00 (English), 12:30 (Spanish), 17:00",
            weekdayMasses = "07:00, 12:10",
            confessionTimes = "Monday - Friday: 11:00 AM - 12:00 PM",
            adorationTimes = "Daily Adoration in Blessed Sacrament Chapel",
            contactPhone = "+1 213 680 5200",
            isPerpetualAdoration = false
        ),
        // Italia - Roma & Vaticano
        NearbyParish(
            id = "it_rom_1",
            name = "Basilica Papale di San Pietro in Vaticano",
            diocese = "Diocesi di Roma / Santa Sede",
            address = "Piazza San Pietro, 00120 Città del Vaticano",
            country = "Italia",
            stateOrRegion = "Lazio / Vaticano",
            city = "Roma",
            latitude = 41.9022,
            longitude = 12.4539,
            distanceDisplay = "Piazza San Pietro",
            sundayMasses = "09:00, 10:30 (Latino), 11:30, 12:15, 16:00, 17:00, 18:00",
            weekdayMasses = "07:00, 08:30, 09:00, 10:00, 11:00, 12:00, 17:00",
            confessionTimes = "Continuo nel braccio confessionale in 10 lingue",
            adorationTimes = "Cappella del Santissimo Sacramento: 08:30 - 18:45",
            contactPhone = "+39 06 6988 3731",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "it_rom_2",
            name = "Arcibasilica Papale di San Giovanni in Laterano (Cattedrale di Roma)",
            diocese = "Diocesi di Roma",
            address = "Piazza di S. Giovanni in Laterano, 4, 00184 Roma",
            country = "Italia",
            stateOrRegion = "Lazio / Vaticano",
            city = "Roma",
            latitude = 41.8859,
            longitude = 12.5057,
            distanceDisplay = "San Giovanni",
            sundayMasses = "07:30, 09:00, 10:30, 12:00, 17:00, 18:30",
            weekdayMasses = "07:30, 09:00, 10:00, 18:00",
            confessionTimes = "Tutti i giorni dalle 07:30 alle 12:30 e dalle 16:00 alle 18:30",
            adorationTimes = "Cappella Corsini: 09:00 - 18:00",
            contactPhone = "+39 06 6988 6433",
            isPerpetualAdoration = false
        ),
        // Perú - Lima & Cusco
        NearbyParish(
            id = "pe_lim_1",
            name = "Basílica Catedral Metropolitana de Lima y Primada del Perú",
            diocese = "Arquidiócesis de Lima",
            address = "Jirón Carabaya s/n, Plaza Mayor, Lima 15001",
            country = "Perú",
            stateOrRegion = "Lima",
            city = "Lima",
            latitude = -12.0467,
            longitude = -77.0297,
            distanceDisplay = "Plaza Mayor",
            sundayMasses = "11:00 (Misa Solemne del Cabildo), 18:00",
            weekdayMasses = "09:00, 18:00",
            confessionTimes = "De 10:00 a 12:00 y antes de cada Misa",
            adorationTimes = "Capilla del Sagrario: 09:00 - 17:00",
            contactPhone = "+51 1 427 9647",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "pe_cus_1",
            name = "Catedral Basílica de la Virgen de la Asunción de Cusco",
            diocese = "Arquidiócesis de Cusco",
            address = "Plaza de Armas s/n, Cusco 08002",
            country = "Perú",
            stateOrRegion = "Cusco",
            city = "Cusco",
            latitude = -13.5167,
            longitude = -71.9788,
            distanceDisplay = "Plaza de Armas",
            sundayMasses = "06:30, 08:30, 10:00, 11:30, 18:00",
            weekdayMasses = "07:00, 08:00",
            confessionTimes = "Durante las misas matutinas",
            adorationTimes = "Capilla del Señor de los Temblores",
            contactPhone = "+51 84 223 245",
            isPerpetualAdoration = false
        ),
        // Venezuela - Caracas & Maracaibo & Lara
        NearbyParish(
            id = "ve_ccs_1",
            name = "Catedral Metropolitana de Santa Ana de Caracas",
            diocese = "Arquidiócesis de Caracas",
            address = "Plaza Bolívar, Esquina de Torre a Gradillas, Caracas 1010",
            country = "Venezuela",
            stateOrRegion = "Distrito Capital",
            city = "Caracas",
            latitude = 10.5061,
            longitude = -66.9142,
            distanceDisplay = "Plaza Bolívar",
            sundayMasses = "08:00, 10:00, 12:00, 16:30",
            weekdayMasses = "07:30, 12:00, 16:30",
            confessionTimes = "Martes a Viernes de 09:00 a 11:30",
            adorationTimes = "Jueves Eucarísticos: 08:00 - 16:00",
            contactPhone = "+58 212 862 4963",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "ve_lar_1",
            name = "Catedral Metropolitana de Nuestra Señora del Carmen de Barquisimeto",
            diocese = "Arquidiócesis de Barquisimeto",
            address = "Av. Venezuela con Calle 30, Barquisimeto 3001, Lara",
            country = "Venezuela",
            stateOrRegion = "Lara",
            city = "Barquisimeto",
            latitude = 10.0684,
            longitude = -69.3242,
            distanceDisplay = "Av. Venezuela",
            sundayMasses = "07:00, 08:30, 10:30, 17:00, 18:30",
            weekdayMasses = "07:00, 17:30",
            confessionTimes = "Sábados y Domingos antes de la Misa",
            adorationTimes = "Capilla del Santísimo: 08:00 - 17:00",
            contactPhone = "+58 251 251 1568",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "ve_mar_1",
            name = "Basílica Nuestra Señora de Chiquinquirá (La Chinita)",
            diocese = "Arquidiócesis de Maracaibo",
            address = "Calle 96 (Ciencias), Casco Central, Maracaibo, Zulia",
            country = "Venezuela",
            stateOrRegion = "Zulia",
            city = "Maracaibo",
            latitude = 10.6436,
            longitude = -71.6148,
            distanceDisplay = "Plazoleta de la Basílica",
            sundayMasses = "07:00, 08:30, 10:00, 11:30, 17:00, 18:30",
            weekdayMasses = "07:00, 17:30",
            confessionTimes = "Lunes a Domingo: 08:00 - 11:00 y 16:00 - 18:00",
            adorationTimes = "Adoración Perpetua al Santísimo en Capilla de San Juan de Dios",
            contactPhone = "+58 261 722 8415",
            isPerpetualAdoration = true
        ),
        // Chile - Santiago & Valparaíso
        NearbyParish(
            id = "cl_scl_1",
            name = "Catedral Metropolitana de Santiago",
            diocese = "Arquidiócesis de Santiago",
            address = "Plaza de Armas 444, Santiago Centro, Región Metropolitana",
            country = "Chile",
            stateOrRegion = "Región Metropolitana",
            city = "Santiago",
            latitude = -33.4378,
            longitude = -70.6514,
            distanceDisplay = "Plaza de Armas",
            sundayMasses = "09:30, 11:00, 12:30 (Misa Mayor), 19:00",
            weekdayMasses = "12:30, 19:00",
            confessionTimes = "Lunes a Viernes de 10:00 a 13:00 y 16:30 a 18:30",
            adorationTimes = "Capilla del Santísimo: 09:30 - 19:00",
            contactPhone = "+56 2 2787 5600",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "cl_val_1",
            name = "Iglesia Catedral de Valparaíso (Espíritu Santo)",
            diocese = "Diócesis de Valparaíso",
            address = "Chacabuco 1778, Plaza Victoria, Valparaíso",
            country = "Chile",
            stateOrRegion = "Valparaíso",
            city = "Valparaíso",
            latitude = -33.0483,
            longitude = -71.6186,
            distanceDisplay = "Plaza Victoria",
            sundayMasses = "09:00, 11:00, 12:30, 19:00",
            weekdayMasses = "12:00, 19:00",
            confessionTimes = "Martes y Jueves de 16:00 a 18:00",
            adorationTimes = "Jueves: 10:00 - 18:00",
            contactPhone = "+56 32 221 2784",
            isPerpetualAdoration = false
        ),
        // Portugal - Lisboa & Fátima
        NearbyParish(
            id = "pt_lis_1",
            name = "Sé Patriarcal de Lisboa (Catedral de Santa Maria Maior)",
            diocese = "Patriarcado de Lisboa",
            address = "Largo da Sé, 1100-585 Lisboa",
            country = "Portugal",
            stateOrRegion = "Lisboa",
            city = "Lisboa",
            latitude = 38.7099,
            longitude = -9.1326,
            distanceDisplay = "Alfama / Baixa",
            sundayMasses = "11:30 (Missa Capitular), 17:00",
            weekdayMasses = "10:00, 17:00",
            confessionTimes = "Segunda a Sábado das 10:00 às 12:30 e 15:00 às 17:00",
            adorationTimes = "Capela do Santíssimo Sacramento: 10:00 - 17:30",
            contactPhone = "+351 218 876 628",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "pt_fat_1",
            name = "Santuário de Nossa Senhora do Rosário de Fátima",
            diocese = "Diocese de Leiria-Fátima",
            address = "Rua de Santa Isabel 360, 2495-424 Fátima",
            country = "Portugal",
            stateOrRegion = "Santarém",
            city = "Fátima",
            latitude = 39.6318,
            longitude = -8.6732,
            distanceDisplay = "Recinto de Oração",
            sundayMasses = "07:30, 09:00, 11:00 (Missa Internacional), 12:30, 15:00, 16:30, 18:30",
            weekdayMasses = "07:30, 09:00, 11:00, 15:00, 16:30, 18:30",
            confessionTimes = "Capela da Reconciliação: 07:30 às 19:30 contínuo",
            adorationTimes = "Capela do Santíssimo Sacramento: Adoração Perpétua (24h)",
            contactPhone = "+351 249 539 600",
            isPerpetualAdoration = true
        ),
        // Francia - París & Lourdes
        NearbyParish(
            id = "fr_par_1",
            name = "Cathédrale Notre-Dame de Paris",
            diocese = "Archidiocèse de Paris",
            address = "6 Parvis Notre-Dame - Pl. Jean-Paul II, 75004 Paris",
            country = "Francia",
            stateOrRegion = "Île-de-France",
            city = "París",
            latitude = 48.8530,
            longitude = 2.3499,
            distanceDisplay = "Île de la Cité",
            sundayMasses = "08:30, 10:00 (Grégorien), 11:30 (Messe Internationale), 18:30",
            weekdayMasses = "08:00, 09:00, 12:00, 18:15",
            confessionTimes = "Tous les jours de 10:00 à 12:30 et de 15:00 à 18:30",
            adorationTimes = "Chapelle du Très Saint-Sacrement: 09:00 - 18:00",
            contactPhone = "+33 1 42 34 56 10",
            isPerpetualAdoration = false
        ),
        NearbyParish(
            id = "fr_lou_1",
            name = "Sanctuaire Notre-Dame de Lourdes (Grotte de Massabielle)",
            diocese = "Diocèse de Tarbes et Lourdes",
            address = "1 Avenue Mgr Théas, 65108 Lourdes",
            country = "Francia",
            stateOrRegion = "Hautes-Pyrénées",
            city = "Lourdes",
            latitude = 43.0975,
            longitude = -0.0583,
            distanceDisplay = "Grotte de Massabielle",
            sundayMasses = "08:30, 10:00 (Messe Internationale), 11:15, 17:00, 18:30",
            weekdayMasses = "08:30, 10:00, 11:15, 17:00",
            confessionTimes = "Chapelle de la Réconciliation: 08:00 à 19:00",
            adorationTimes = "Tente de l'Adoration: Adoration Perpétua 24h/24",
            contactPhone = "+33 5 62 42 78 78",
            isPerpetualAdoration = true
        )
    )

    val catholicNews: List<CatholicNewsArticle> = listOf(
        CatholicNewsArticle(
            id = "n1",
            title = LocalizedText(
                "El Santo Padre llama a los jóvenes a ser apóstoles de la esperanza y la paz",
                "The Holy Father Calls Youth to Be Apostles of Hope and Reconciliation",
                "O Santo Padre convoca a juventude a ser apóstola da esperança e da paz"
            ),
            source = "Vatican News",
            date = "Octubre 2026",
            category = LocalizedText("Magisterio Pontificio", "Papal Magisterium", "Magistério Papal"),
            summary = LocalizedText(
                "Durante la audiencia general en la Plaza de San Pedro, el Papa exhortó a redescubrir el valor del silencio, la confesión frecuente y el servicio fraterno a los enfermos.",
                "In his general audience at St. Peter's Square, the Pope encouraged frequent confession, Eucharistic adoration, and active charity towards the lonely.",
                "Na Praça de São Pedro, o Papa destacou o poder transformador do terço em família e da adoração eucarística."
            ),
            readTimeMinutes = 3
        ),
        CatholicNewsArticle(
            id = "n2",
            title = LocalizedText(
                "Anuncian nueva causa de beatificación de misioneros mártires de la caridad",
                "Dicastery for Causes of Saints Advances Beatification of Modern Martyrs",
                "Dicastério avança com beatificação de missionários mártires da fé"
            ),
            source = "L'Osservatore Romano",
            date = "Octubre 2026",
            category = LocalizedText("Santidad y Misión", "Holiness & Mission", "Santidade e Missão"),
            summary = LocalizedText(
                "Se reconoce el martirio 'en odio a la fe' de sacerdotes y religiosas que dieron la vida asistiendo a comunidades olvidadas.",
                "Recognizing supreme evangelical witness of religious sisters who gave their lives serving impoverished communities.",
                "Testemunho heroico de religiosas que derramaram seu sangue servindo aos pobres e anunciando o Evangelho."
            ),
            readTimeMinutes = 2
        )
    )
}
