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
        )
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
