package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.ScriptureCourseTrack
import com.example.data.models.ScriptureLesson

object ScriptureStudyData {

    val tracks: List<ScriptureCourseTrack> = listOf(
        ScriptureCourseTrack(
            id = "track_sacraments",
            title = LocalizedText(
                "Los Sacramentos de Gracia en la Escritura",
                "The Sacraments of Grace in Scripture",
                "Os Sacramentos da Graça na Escritura"
            ),
            description = LocalizedText(
                "Curso editorial de 6 lecciones sobre las raíces bíblicas de los siete sacramentos instituidos por Jesucristo.",
                "6-lesson editorial course exploring the biblical roots of the seven Sacraments instituted by Jesus Christ.",
                "Curso editorial de 6 lições sobre as raízes bíblicas dos sete sacramentos instituídos por Jesus Cristo."
            ),
            iconName = "Church",
            lessons = listOf(
                ScriptureLesson(
                    id = "sac_1_baptism",
                    trackId = "track_sacraments",
                    lessonNumber = 1,
                    title = LocalizedText("El Bautismo y el Nuevo Nacimiento", "Baptism & New Birth", "O Batismo e o Novo Nascimento"),
                    biblicalReference = "Juan 3, 1-8 y Mateo 28, 19-20",
                    biblicalPassageText = LocalizedText(
                        "«Jesús le respondió: 'En verdad, en verdad te digo: el que no nazca de agua y del Espíritu no puede entrar en el Reino de Dios. Lo nacido de la carne, carne es; lo nacido del Espíritu, espíritu es'... 'Vayan, pues, y hagan discípulos a todas las gentes bautizándolas en el nombre del Padre y del Hijo y del Espíritu Santo'».",
                        "“Jesus answered, 'Amen, amen, I say to you, no one can enter the kingdom of God without being born of water and Spirit. What is born of flesh is flesh and what is born of spirit is spirit'... 'Go, therefore, and make disciples of all nations, baptizing them in the name of the Father, and of the Son, and of the Holy Spirit.'”",
                        "“Jesus respondeu: 'Em verdade, em verdade te digo: quem não nascer da água e do Espírito não pode entrar no Reino de Deus'... 'Ide, pois, e fazei discípulos de todas as nações, batizando-os em nome do Pai, e do Filho, e do Espírito Santo'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Concilio Vaticano II (Lumen Gentium 11) y el Catecismo (§1213) enseñan que el Santo Bautismo es el fundamento de toda la vida cristiana, la puerta de entrada a la vida en el Espíritu (vitae spiritualis ianua) que borra el pecado original y nos incorpora a la Iglesia como hijos adoptivos de Dios.",
                        "Vatican II (Lumen Gentium 11) and the Catechism (CCC 1213) affirm Holy Baptism as the foundation of the entire Christian life, through which we are freed from sin and reborn as children of God.",
                        "O Vaticano II e o Catecismo (CIC 1213) ensinam que o Batismo é a porta da vida no Espírito e o fundamento de toda a vocação cristã."
                    ),
                    lectioStep = LocalizedText(
                        "Jesús recalca la necesidad absoluta del agua y del Espíritu Santo para nacer de nuevo y ser coherederos del Reino.",
                        "Notice Christ's solemn dialogue with Nicodemus on supernatural rebirth through water and the Holy Spirit.",
                        "Jesus revela a Nicodemos a necessidade do renascimento pela água e pelo Espírito Santo."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Recuerdo con frecuencia la dignidad suprema de haber sido sellado como hijo de Dios en mi bautismo?",
                        "How consciously do I live each day in the grace and promises of my baptismal consecration?",
                        "Como vivo cotidianamente a graça infinita de ser filho amado de Deus pelo Batismo?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, renueva en mí la gracia viva del bautismo. Purifica mis pensamientos y hazme testimonio fiel de tu luz».",
                        "“Lord Jesus, revive the indelible seal of my baptism. Keep me unspotted from the world and faithful to Your Church.”",
                        "“Senhor Jesus, renovai em mim a graça batismal e dai-me a fidelidade aos Vossos mandamentos.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Toma hoy agua bendita, persígnate despacio y agradece a Dios por haberte llamado por tu nombre.",
                        "Bless yourself with holy water today with reverent recollection of your baptismal calling.",
                        "Faça o sinal da Cruz com água benta recordando o dia bendito do seu Batismo."
                    ),
                    catechismRefs = listOf("CIC 1213-1284", "Lumen Gentium 11", "Dei Verbum 2")
                ),
                ScriptureLesson(
                    id = "sac_2_confirmation",
                    trackId = "track_sacraments",
                    lessonNumber = 2,
                    title = LocalizedText("La Confirmación y el Fuego de Pentecostés", "Confirmation & the Fire of Pentecost", "A Confirmação e o Fogo de Pentecostes"),
                    biblicalReference = "Hechos 2, 1-4 y Hechos 8, 14-17",
                    biblicalPassageText = LocalizedText(
                        "«Al llegar el día de Pentecostés, estaban todos reunidos... y se les aparecieron unas lenguas como de fuego... y quedaron todos llenos del Espíritu Santo... Los apóstoles en Jerusalén les enviaron a Pedro y a Juan... les imponían las manos y recibían el Espíritu Santo».",
                        "“When the time for Pentecost was fulfilled, they were all in one place together... Then there appeared to them tongues as of fire... and they were all filled with the holy Spirit... Then they laid hands on them and they received the holy Spirit.”",
                        "“Chegando o dia de Pentecostes, estavam todos reunidos... apareceram línguas como de fogo e todos ficaram cheios do Espírito Santo.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Catecismo (CIC §1285) declara que la Confirmación consuma la gracia bautismal; por este sacramento los fieles se vinculan más estrechamente a la Iglesia y se enriquecen con una fortaleza especial del Espíritu Santo para difundir y defender la fe como verdaderos testigos de Cristo.",
                        "The Catechism (CCC 1285) highlights Confirmation as completing baptismal grace, imparting a special outpouring of the Spirit for apostolic mission.",
                        "O sacramento da Confirmação confere a unção e os sete dons do Espírito Santo para sermos apóstolos intrépidos de Cristo."
                    ),
                    lectioStep = LocalizedText(
                        "Los Apóstoles pasaron del temor a la valentía evangelizadora tras recibir el Paráclito.",
                        "The Apostles were transformed from hidden fear into courageous public proclaimers of Christ.",
                        "Os apóstolos foram revestidos de poder do alto para testemunhar a ressurreição."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Permito que los 7 dones del Espíritu Santo (sabiduría, entendimiento, consejo, fortaleza, ciencia, piedad y temor de Dios) guíen mis decisiones?",
                        "Which of the Seven Gifts of the Holy Spirit do I need to invoke most actively in my present circumstances?",
                        "Deixo que o Espírito Santo dirija os meus passos ou ajo apenas por impulsos humanos?"
                    ),
                    oratioStep = LocalizedText(
                        "«Espíritu Santo, dulce huésped del alma, enciende en mí el fuego de tu santo celo y dame valentía para dar testimonio».",
                        "“Holy Spirit, divine Consoler, stir within my heart Your sevenfold gifts and strengthen me to confess Christ boldly.”",
                        "“Vinde, Espírito Santo, enchei os corações dos Vossos fiéis com o fogo do Vosso amor.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Invoca al Espíritu Santo antes de empezar cualquier trabajo o conversación difícil hoy.",
                        "Pray a short ejaculation to the Holy Spirit before embarking on any major task today.",
                        "Invoque o Espírito Santo antes de iniciar qualquer trabalho importante hoje."
                    ),
                    catechismRefs = listOf("CIC 1285-1321", "Ad Gentes 3-5")
                ),
                ScriptureLesson(
                    id = "sac_3_eucharist",
                    trackId = "track_sacraments",
                    lessonNumber = 3,
                    title = LocalizedText("La Eucaristía: Cuerpo y Sangre Real de Cristo", "The Holy Eucharist: Real Presence of Christ", "A Eucaristia: Presença Real de Cristo"),
                    biblicalReference = "Juan 6, 51-58 y 1 Corintios 11, 23-26",
                    biblicalPassageText = LocalizedText(
                        "«'Yo soy el pan vivo bajado del cielo. Si uno come de este pan, vivirá para siempre; y el pan que yo le daré es mi carne, para la vida del mundo'... 'Porque yo recibí del Señor lo que les he transmitido: que el Señor Jesús, la noche en que fue entregado, tomó pan, dio gracias, lo partió y dijo: Esto es mi cuerpo que se entrega por ustedes'».",
                        "“'I am the living bread that came down from heaven; whoever eats this bread will live forever; and the bread that I will give is my flesh for the life of the world'... 'For I received from the Lord what I also handed on to you, that the Lord Jesus, on the night he was handed over, took bread, and, after he had given thanks, broke it and said, This is my body that is for you.'”",
                        "“'Eu sou o pão vivo que desceu do céu; se alguém comer deste pão, viverá eternamente; e o pão que eu darei pela vida do mundo é a minha carne'... 'Isto é o meu corpo que é entregue por vós'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "San Juan Pablo II (Ecclesia de Eucharistia) y el Concilio de Trento dogmatizan la Presencia Real, sustancial e íntegra de Cristo bajo las especies de pan y vino por transustanciación. Es 'fuente y cumbre de toda la vida cristiana' (Lumen Gentium 11).",
                        "Vatican II terms the Holy Eucharist 'the source and summit of the Christian life' (LG 11). Through transubstantiation, Christ is truly, really, and substantially present.",
                        "A Eucaristia é a fonte e o ápice de toda a vida da Igreja (LG 11). Sob as espécies consagradas está Cristo vivo em Corpo, Sangue, Alma e Divindade."
                    ),
                    lectioStep = LocalizedText(
                        "Jesús no habla en sentido simbólico o metafórico; insiste ante la incredulidad de los oyentes: 'Mi carne es verdadera comida y mi sangre verdadera bebida'.",
                        "Jesus speaks with literal realism, emphasizing that eternal life hinges upon communion with His Eucharistic flesh.",
                        "Jesus afirma categoricamente a realidade do Seu Corpo entregue e Sangue derramado."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Cómo me preparo espiritualmente (estado de gracia, ayuno eucarístico, devoción profunda) antes de acercarme a comulgar?",
                        "Do I approach Holy Communion with profound reverence, Eucharistic fast, and purity of heart in the state of grace?",
                        "Aproximo-me da Santa Comunhão em estado de graça e com profunda adoração?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, creo firmemente que estás real y verdaderamente presente en el Santísimo Sacramento del Altar. Te adoro con todo mi ser».",
                        "“O Sacrament Most Holy, O Sacrament Divine, all praise and all thanksgiving be every moment Thine.”",
                        "“Graças e louvores se deem a todo o momento ao Santíssimo e Diviníssimo Sacramento!”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Visita hoy una iglesia o capilla del Santísimo Sacramento durante al menos 10 minutos de adoración silenciosa.",
                        "Make a 10-minute visit to the Blessed Sacrament in a nearby Catholic church today.",
                        "Faça uma visita de adoração ao Santíssimo Sacramento hoje."
                    ),
                    catechismRefs = listOf("CIC 1322-1419", "Ecclesia de Eucharistia", "Mysterium Fidei")
                ),
                ScriptureLesson(
                    id = "sac_4_confession",
                    trackId = "track_sacraments",
                    lessonNumber = 4,
                    title = LocalizedText("Reconciliación: El Abrazo del Padre Misericordioso", "Penance & Reconciliation: The Father's Embrace", "Reconciliação: O Abraço do Pai Misericordioso"),
                    biblicalReference = "Juan 20, 21-23 y Lucas 15, 11-24",
                    biblicalPassageText = LocalizedText(
                        "«Jesús les dijo otra vez: 'La paz esté con ustedes. Como el Padre me envió, también yo los envío'. Dicho esto, sopló sobre ellos y les dijo: 'Reciban el Espíritu Santo. A quienes les perdonen los pecados, les quedan perdonados; a quienes se los retengan, les quedan retenidos'».",
                        "“Jesus said to them again, 'Peace be with you. As the Father has sent me, so I send you.' And when he had said this, he breathed on them and said to them, 'Receive the holy Spirit. Whose sins you forgive are forgiven them, and whose sins you retain are retained.'”",
                        "“Jesus soprou sobre eles e disse: 'Recebei o Espírito Santo. Àqueles a quem perdoardes os pecados, ser-lhes-ão perdoados'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Santo Padre y el Catecismo (CIC §1441) recuerdan que sólo Dios perdona los pecados; sin embargo, Cristo encomendó a los Apóstoles y a sus sucesores en el sacerdocio el ministerio de la reconciliación sacramental (Reconciliatio et Paenitentia).",
                        "Pope St. John Paul II (Reconciliatio et Paenitentia) expounded the divine authority entrusted to ordained priests to reconcile sinners through sacramental absolution.",
                        "Cristo confiou aos sacerdotes o ministério da reconciliação para absolver os pecados em Seu Nome (CIC 1441)."
                    ),
                    lectioStep = LocalizedText(
                        "El Señor resucitado otorga el poder de atar y desatar pecados el mismo domingo de Resurrección.",
                        "On the very evening of Easter, Christ institutes the Sacrament of Penance as the first fruit of His victory over death.",
                        "Jesus ressuscitado institui a Confissão como primeiro presente pascal para a humanidade."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Experimento la confesión como un tribunal de miedo o como un encuentro sanador con el amor infinito del Corazón de Jesús?",
                        "Do I view Confession as an ordeal of shame, or as a liberating bath in Christ's redeeming blood?",
                        "Vejo a Confissão como um abraço de misericórdia ou tenho receio de abrir o coração ao sacerdote?"
                    ),
                    oratioStep = LocalizedText(
                        "«Jesús, médico de las almas, ten compasión de mis fragilidades y concédeme la gracia de una confesión sincera y contrita».",
                        "“Jesus, gentle physician of souls, soften my heart to repent of every hidden fault and embrace Your absolution.”",
                        "“Senhor Jesus, dai-me a coragem de me confessar com sinceridade e concedei-me o Vosso perdão restaurador.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Revisa las horas de confesión de tu parroquia y programa tu próxima confesión sacramental esta semana.",
                        "Check confession schedules at your local parish and set a date to receive absolution.",
                        "Programe a sua próxima Confissão sacramental para esta semana."
                    ),
                    catechismRefs = listOf("CIC 1422-1498", "Reconciliatio et Paenitentia", "Misericordia Dei")
                ),
                ScriptureLesson(
                    id = "sac_5_anointing",
                    trackId = "track_sacraments",
                    lessonNumber = 5,
                    title = LocalizedText("Unción de los Enfermos: Sanación y Fortaleza", "Anointing of the Sick: Comfort and Healing", "Unção dos Enfermos: Conforto e Cura"),
                    biblicalReference = "Santiago 5, 14-15 y Marcos 6, 12-13",
                    biblicalPassageText = LocalizedText(
                        "«¿Está enfermo alguno de ustedes? Llame a los presbíteros de la Iglesia, y que oren sobre él, ungiéndole con óleo en el nombre del Señor. Y la oración de la fe salvará al enfermo, y el Señor lo aliviará; y si hubiera cometido pecados, le serán perdonados».",
                        "“Is anyone among you sick? He should summon the presbyters of the church, and they should pray over him and anoint him with oil in the name of the Lord, and the prayer of faith will save the sick person, and the Lord will raise him up. If he has committed any sins, he will be forgiven.”",
                        "“Está alguém enfermo entre vós? Chame os presbíteros da Igreja, e orem sobre ele, ungindo-o com óleo em nome do Senhor.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Concilio Vaticano II (Sacrosanctum Concilium 73) aclaró que la Unción de los enfermos no es un sacramento sólo para los que están en las últimas horas de vida, sino para todo fiel que comienza a encontrarse en peligro por enfermedad grave o vejez.",
                        "Vatican II (Sacrosanctum Concilium 73) recovered the pastoral scope of Anointing: not merely for the immediate point of death, but for all facing serious illness or advancing age.",
                        "O Vaticano II reafirmou que a Unção dos Enfermos confere alívio, serenidade e perdão dos pecados aos que sofrem de doenças graves."
                    ),
                    lectioStep = LocalizedText(
                        "La carta de Santiago manda llamar expresamente a los presbíteros para orar y ungir con óleo consagrado.",
                        "Notice the explicit biblical mandate: the sick summon the presbyters, receiving prayer, holy anointing, and remission of sins.",
                        "A Carta de São Tiago descreve o rito apostólico da unção com óleo pelos presbíteros."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Ofrezco mis dolores y malestares físicos en unión a la Cruz redentora de Jesucristo por la salvación de las almas?",
                        "How do I bear physical sufferings: with bitter complaint, or united to the redemptive Passion of Christ?",
                        "Uno meus sofrimentos cotidianos à Cruz de Cristo para a redenção dos pecadores?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, que pasaste haciendo el bien y sanando a los enfermos: acompaña a todos los que hoy sufren en hospitales y hogares».",
                        "“Divine Healer, pour Your comforting grace upon the bedridden, the elderly, and all facing severe illness.”",
                        "“Senhor Jesus, Médico divino, consolai todos os enfermos e dai-lhes a paz de Vosso Espírito Santo.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Reza un misterio del Santo Rosario hoy por una persona enferma o anciana que conozcas.",
                        "Offer a decade of the Holy Rosary specifically for a sick or suffering relative today.",
                        "Reze um Terço hoje pela saúde física e espiritual de um enfermo conhecido."
                    ),
                    catechismRefs = listOf("CIC 1499-1532", "Sacrosanctum Concilium 73")
                ),
                ScriptureLesson(
                    id = "sac_6_vocation",
                    trackId = "track_sacraments",
                    lessonNumber = 6,
                    title = LocalizedText("Orden Sagrado y Matrimonio: Sacramentos de Comunión", "Holy Orders & Matrimony: Sacraments of Service", "Ordem e Matrimônio: Sacramentos da Comunhão"),
                    biblicalReference = "Efesios 5, 25-32 y Hebreos 5, 1-6",
                    biblicalPassageText = LocalizedText(
                        "«Maridos, amen a sus mujeres como Cristo amó a la Iglesia y se entregó a sí mismo por ella... Gran misterio es éste, lo digo respecto a Cristo y a la Iglesia... Pues todo sumo sacerdote tomado de entre los hombres es constituido en favor de los hombres en lo que a Dios se refiere».",
                        "“Husbands, love your wives, even as Christ loved the church and handed himself over for her... This is a great mystery, but I speak in reference to Christ and the church... Every high priest is taken from among men and made their representative before God.”",
                        "“Maridos, amai as vossas mulheres, como Cristo amou a Igreja e a si mesmo se entregou por ela... Grande é este mistério; digo-o, porém, a respeito de Cristo e da Igreja.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Catecismo (CIC §1534) los denomina 'Sacramentos al servicio de la comunidad'. Están ordenados a la salvación de los demás, configurando a los esposos al amor esponsal de Cristo y a los ministros al sacerdocio de Cristo Cabeza (Pastores Dabo Vobis, Familiaris Consortio).",
                        "The Catechism classifies Holy Orders and Matrimony as Sacraments at the Service of Communion (CCC 1534), conferring graces for self-sacrificing love and service.",
                        "Ordem e Matrimônio são dons do amor de Deus destinados à edificação do Corpo místico da Igreja e santificação das famílias."
                    ),
                    lectioStep = LocalizedText(
                        "San Pablo compara la alianza nupcial con el misterio del amor sacrificial indisoluble entre Cristo y su Esposa la Iglesia.",
                        "Saint Paul elevates Christian marriage into an icon of Christ's unbreakable covenant with His Bride, the Church.",
                        "O matrimônio cristão é elevado à dignidade de sacramento que reflete o amor esponsal de Cristo."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Valoro y rezo diariamente por la fidelidad de los sacerdotes y la santidad de los matrimonios cristianos?",
                        "Do I regularly pray for holy priests and for Christian marriages to remain faithful against worldly pressures?",
                        "Rezo diariamente pelas vocações sacerdotais e pela santidade dos lares católicos?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor, danos santos sacerdotes y santas familias que reflejen la pureza de tu Evangelio. Guarda a nuestros pastores y esposos».",
                        "“Lord, bless our priests with apostolic holiness, and defend Christian marriages with enduring fidelity.”",
                        "“Senhor, dai à Vossa Igreja santos sacerdotes e fortalecei os matrimônios cristãos na caridade e na paz.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Ora una jaculatoria por el párroco de tu comunidad y por los matrimonios en crisis.",
                        "Offer a prayer for your local parish pastor and for couples experiencing trials today.",
                        "Ofereça uma oração sincera pelo seu pároco e pelas famílias de sua comunidade."
                    ),
                    catechismRefs = listOf("CIC 1533-1666", "Pastores Dabo Vobis", "Familiaris Consortio")
                )
            )
        ),
        ScriptureCourseTrack(
            id = "track_parables",
            title = LocalizedText(
                "Las Parábolas del Reino de Dios",
                "The Parables of the Kingdom of God",
                "As Parábolas do Reino de Deus"
            ),
            description = LocalizedText(
                "Estudio contemplativo de las historias y alegorías con las que Nuestro Señor Jesucristo reveló los misterios del Cielo.",
                "Contemplative study of the divine stories and allegories through which Christ unveiled the mysteries of the Kingdom.",
                "Estudo das alegorias e ensinamentos através dos quais Jesus revelou os segredos do Reino celeste."
            ),
            iconName = "AutoStories",
            lessons = listOf(
                ScriptureLesson(
                    id = "par_1_sower",
                    trackId = "track_parables",
                    lessonNumber = 1,
                    title = LocalizedText("El Sembrador y las Tierras del Alma", "The Sower & the Soil of the Soul", "O Semeador e as Terras da Alma"),
                    biblicalReference = "Mateo 13, 1-23",
                    biblicalPassageText = LocalizedText(
                        "«Salió el sembrador a sembrar... Parte cayó junto al camino, otra en pedregal, otra entre zarzas, y otra en tierra buena y dio fruto, una ciento, otra sesenta, otra treinta. El que tenga oídos, que oiga... La semilla sembrada en tierra buena es el que oye la Palabra y la comprende».",
                        "“A sower went out to sow... And some seed fell on the path, some on rocky ground, some among thorns, and some on good soil, where it produced fruit, a hundred or sixty or thirtyfold. Whoever has ears ought to hear!”",
                        "“Eis que o semeador saiu a semear... Outra semente caiu em boa terra e deu fruto: um a cem, outro a sessenta e outro a trinta.”"
                    ),
                    vaticanContext = LocalizedText(
                        "La Constitución Dei Verbum del Vaticano II (§21) enseña que la Iglesia venera la Palabra divina igual que el Cuerpo de Cristo. Las cuatro tierras representan los estados del corazón humano ante la gracia divina.",
                        "Vatican II (Dei Verbum 21) teaches that the Church venerates the divine Scriptures just as she venerates the Body of the Lord. The soils reveal our openness to grace.",
                        "Dei Verbum 21 afirma que a Palavra de Deus alimenta a vida da Igreja. Os quatro solos revelam a disposição interior da alma."
                    ),
                    lectioStep = LocalizedText(
                        "La semilla es la misma en todos los casos; la diferencia radica en la acogida y perseverancia del terreno.",
                        "Observe that the seed is identical in every scenario; the outcome is governed exclusively by the condition of the soil.",
                        "A semente divina é sempre perfeita; a fertilidade depende da resposta do coração humano."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Qué zarzas o distracciones del mundo (afán de riquezas, ruidos del móvil) ahogan hoy la Palabra de Dios en mi vida?",
                        "Which worldly thorns—material worries, screens, vanity—threaten to suffocate God's voice in my schedule?",
                        "Quais espinhos de ansiedade ou vaidade estão sufocando a oração no meu dia a dia?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, limpia las piedras y zarzas de mi alma; haz de mi corazón tierra fértil donde florezcan tus virtudes».",
                        "“Lord, remove the rocks of pride and the thorns of anxiety from my soul, that Your Word may take deep root.”",
                        "“Senhor Jesus, arrancai de minha alma as ervas daninhas do pecado e fazei frutificar a Vossa Palavra.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Dedica 15 minutos de silencio sin celular ni pantallas para meditar la Escritura con recogimiento.",
                        "Set aside 15 uninterrupted minutes of silence away from screens to read Scripture today.",
                        "Desligue as notificações por 15 minutos para fazer uma leitura bíblica em silêncio."
                    ),
                    catechismRefs = listOf("CIC 546", "Dei Verbum 21-26")
                ),
                ScriptureLesson(
                    id = "par_2_samaritan",
                    trackId = "track_parables",
                    lessonNumber = 2,
                    title = LocalizedText("El Buen Samaritano: El Amor sin Fronteras", "The Good Samaritan: Boundless Charity", "O Bom Samaritano: Amor Sem Fronteiras"),
                    biblicalReference = "Lucas 10, 25-37",
                    biblicalPassageText = LocalizedText(
                        "«Un samaritano que iba de camino llegó cerca de él y, al verle, tuvo compasión; se acercó, vendó sus heridas, echando en ellas aceite y vino; le montó sobre su propia cabalgadura, le llevó a la posada y cuidó de él... 'Ve y haz tú lo mismo'».",
                        "“A Samaritan traveler who came upon him was moved with compassion at the sight. He approached the victim, poured oil and wine over his wounds and bandaged them. Then he lifted him up on his own animal, took him to an inn and cared for him... 'Go and do likewise.'”",
                        "“Um samaritano, que ia de viagem, aproximou-se dele e, vendo-o, encheu-se de compaixão; cuidou de suas chagas... 'Vai e faze tu o mesmo'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "Los Padres de la Iglesia y la encíclica Deus Caritas Est de Benedicto XVI ven en el Buen Samaritano a Jesucristo mismo, quien baja a curar a la humanidad herida por el pecado con el óleo y vino de los sacramentos.",
                        "Pope Benedict XVI (Deus Caritas Est) highlighted the Samaritan as Christ Himself, who bends down to heal fallen humanity through the medicinal balm of the Sacraments.",
                        "Bento XVI em Deus Caritas Est ensina que Cristo é o verdadeiro Samaritano que cura as feridas de nossa alma."
                    ),
                    lectioStep = LocalizedText(
                        "El sacerdote y el levita dan un rodeo; sólo el samaritano se detiene movido a compasión y paga de su propio bolsillo.",
                        "The religious figures avoid inconvenience; only the despised Samaritan halts, spends his own resources, and touches the wounded.",
                        "O samaritano não apenas sente pena, mas investe tempo, recursos e cuidados diretos."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Quién es hoy mi prójimo vulnerable al que suelo pasar de largo para no complicarme la vida?",
                        "Who is the inconvenient neighbor in my life whom I am tempted to walk past without engaging?",
                        "Quem é a pessoa próxima necessitada de ajuda que eu costumo ignorar por comodismo?"
                    ),
                    oratioStep = LocalizedText(
                        "«Jesús compasivo, dame entrañas de misericordia. No permitas que mi religión sea fría o indiferente ante el dolor ajeno».",
                        "“Lord Jesus, give me eyes that see suffering and hands that serve with Your selfless charity.”",
                        "“Jesus misericordioso, livrai-me da indiferença e ensinai-me a amar o próximo com atos concretos.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Realiza hoy una obra de misericordia corporal o espiritual concreta: ayuda a alguien necesitado o visita a un enfermo.",
                        "Perform an intentional corporal or spiritual work of mercy today for someone who cannot repay you.",
                        "Realize um ato concreto de caridade por alguém que precise de amparo hoje."
                    ),
                    catechismRefs = listOf("CIC 1825", "Deus Caritas Est 19-39", "Fratelli Tutti 56-86")
                ),
                ScriptureLesson(
                    id = "par_3_prodigal_son",
                    trackId = "track_parables",
                    lessonNumber = 3,
                    title = LocalizedText("El Padre Misericordioso y el Hijo Pródigo", "The Merciful Father & the Prodigal Son", "O Pai Misericordioso e o Filho Pródigo"),
                    biblicalReference = "Lucas 15, 11-32",
                    biblicalPassageText = LocalizedText(
                        "«Estando él todavía lejos, le vio su padre y, conmovido, corrió, se echó a su cuello y le besó efusivamente... 'Hijo, tú siempre estás conmigo, y todo lo mío es tuyo; pero convenía celebrar una fiesta y alegrarse, porque este hermano tuyo estaba muerto y ha vuelto a la vida'».",
                        "“While he was still a long way off, his father caught sight of him, and was filled with compassion. He ran to his son, embraced him and kissed him... 'My son, you are here with me always; everything I have is yours. But now we must celebrate, because your brother was dead and has come to life.'”",
                        "“Quando ainda estava longe, seu pai o avistou e, movido de compaixão, correu, lançou-se-lhe ao pescoço e o beijou... 'Meu filho estava morto e reviveu'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "San Juan Pablo II (Dives in Misericordia) describe esta parábola como el corazón del Evangelio. Revela que el amor de Dios es mayor que cualquier pecado y que la misericordia restaura la dignidad filial del pecador.",
                        "Pope St. John Paul II devoted his encyclical Dives in Misericordia to this passage, proving that God's fidelity to His paternity surpasses all human betrayal.",
                        "Dives in Misericordia de São João Paulo II demonstra que a misericórdia do Pai devolve ao pecador a dignidade de filho de Deus."
                    ),
                    lectioStep = LocalizedText(
                        "El padre no espera sentado ni recrimina; corre al encuentro de su hijo andrajoso y ordena el mejor vestido, anillo y banquete.",
                        "The father runs outward to meet his returning child, cutting off rehearsed excuses with royal restoration.",
                        "O pai corre ao encontro do filho arrependido, restituindo-lhe imediatamente a herança e a honra."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Tengo la actitud del hijo menor que necesita volver, o la amargura del hermano mayor que juzga a los demás?",
                        "Do I identify with the repentant wanderer, or do I harbor the elder brother's resentment and self-righteousness?",
                        "Tenho a humildade do filho que retorna, ou a frieza do irmão mais velho que critica os outros?"
                    ),
                    oratioStep = LocalizedText(
                        "«Padre Santo, he pecado contra el cielo y contra Ti; no soy digno de llamarme tu hijo. Abrázame en tu infinita misericordia».",
                        "“Abba Father, I have sinned against heaven and before You. Welcome me home in the tender embrace of Your mercy.”",
                        "“Pai Santo, pequei contra o céu e contra Vós. Acolhei-me em Vossos braços de amor e restaurai a minha paz.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Perdona de corazón hoy a una persona con la que guardabas rencor o distancia.",
                        "Extend unconditional forgiveness to someone who hurt you, releasing all bitter resentment.",
                        "Perdoe de coração hoje a alguém que tenha ofendido você."
                    ),
                    catechismRefs = listOf("CIC 1439", "Dives in Misericordia", "Misericordiae Vultus")
                ),
                ScriptureLesson(
                    id = "par_4_wheat_tares",
                    trackId = "track_parables",
                    lessonNumber = 4,
                    title = LocalizedText("El Trigo y la Cizaña: La Paciencia de Dios", "The Wheat & the Weeds: God's Patience", "O Trigo e o Joio: A Paciência de Deus"),
                    biblicalReference = "Mateo 13, 24-30. 36-43",
                    biblicalPassageText = LocalizedText(
                        "«'Dejen que ambos crezcan juntos hasta la siega; y al tiempo de la siega diré a los segadores: Recojan primero la cizaña y átenla en gavillas para quemarla, y el trigo recójanlo en mi granero'... Entonces los justos brillarán como el sol en el Reino de su Padre».",
                        "“'Let them grow together until harvest; then at harvest time I will say to the harvesters, First collect the weeds and tie them in bundles for burning; but gather the wheat into my barn.'... Then the righteous will shine like the sun in the kingdom of their Father.”",
                        "“'Deixai crescer ambos juntos até à ceifa; e, por ocasião da ceifa, direi aos ceifeiros: Colhei primeiro o joio e atai-o em feixes para queimar; mas o trigo, ajuntai-o no meu celeiro'.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Papa Francisco y el Catecismo (CIC §827) enseñan que la Iglesia en la tierra es a la vez santa y siempre necesitada de purificación, pues en ella coexisten pecadores y santos hasta el Juicio Final.",
                        "The Catechism (CCC 827) explains the coexistence of sanctity and sin within the pilgrim Church, cautioning against hasty puritanical fanaticism.",
                        "A Igreja é santa e ao mesmo tempo acolhe os pecadores, chamando todos à conversão antes do Juízo Final."
                    ),
                    lectioStep = LocalizedText(
                        "Los siervos quieren arrancar la cizaña de inmediato; el amo prohíbe la violencia para no arrancar con ella el trigo tierno.",
                        "The servants impulsively demand immediate destruction; the Master counsels patient prudence so tender wheat is not lost.",
                        "O dono do campo ensina a paciência divina para permitir que a cizaña possa se converter em bom trigo."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Soy paciente con las debilidades de los hermanos en la Iglesia o me apresuro a condenar con soberbia?",
                        "Am I patient with the human imperfections of others, remembering that God has been infinitely patient with me?",
                        "Sei ser paciente com os erros alheios, lembrando quanta misericórdia Deus já teve comigo?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor, enséñame tu divina paciencia. Dame ojos para buscar el bien en todos y esperanza en la conversión de los pecadores».",
                        "“Lord of the harvest, grant me patience and charity. Help me to bear good fruit and trust Your divine providence.”",
                        "“Senhor, dai-me um coração manso e paciente, confiando sempre na Vossa justiça e misericórdia.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Pide a Dios por la santificación y purificación de la Iglesia Católica y sus ministros.",
                        "Offer a decade of the Rosary for the renewal and holiness of the clergy and Catholic faithful.",
                        "Reze hoje pela santificação dos padres e pela pureza da Santa Igreja."
                    ),
                    catechismRefs = listOf("CIC 827", "Lumen Gentium 8")
                ),
                ScriptureLesson(
                    id = "par_5_pearl",
                    trackId = "track_parables",
                    lessonNumber = 5,
                    title = LocalizedText("El Tesoro Oculto y la Perla Preciosa", "The Hidden Treasure & the Pearl of Great Price", "O Tesouro Escondido e a Pérola Preciosa"),
                    biblicalReference = "Mateo 13, 44-46",
                    biblicalPassageText = LocalizedText(
                        "«El Reino de los Cielos es semejante a un tesoro escondido en un campo que, al encontrarlo un hombre, vuelve a esconderlo y, por la alegría que le da, va, vende todo lo que tiene y compra el campo aquel. También es semejante a un mercader que busca perlas finas... fue, vendió todo lo que tenía y la compró».",
                        "“The kingdom of heaven is like a treasure buried in a field, which a person finds and hides again, and out of joy goes and sells all that he has and buys that field. Again, the kingdom of heaven is like a merchant searching for fine pearls. When he finds a pearl of great price, he goes and sells all that he has and buys it.”",
                        "“O Reino dos Céus é semelhante a um tesouro escondido no campo... e pela alegria que dele tem, vai, vende tudo quanto tem e compra aquele campo.”"
                    ),
                    vaticanContext = LocalizedText(
                        "San Agustín y el Magisterio de la Iglesia recalcan que Cristo mismo es la Perla inestimable. Conocer a Jesús no es una carga moral, sino una alegría que relativiza todas las posesiones terrenas.",
                        "St. Augustine and the Magisterium identify Christ as the supreme Pearl; encountering Him reorders every priority with transcendent joy.",
                        "Encontrar a Cristo é o maior tesouro da existência humana, pelo qual vale a pena entregar toda a nossa vida."
                    ),
                    lectioStep = LocalizedText(
                        "El comprador no vende todo con tristeza o desgano, sino 'lleno de alegría' por el valor infinito de lo descubierto.",
                        "The transaction is driven not by reluctant duty, but by overflowing joy at possessing what is infinitely more valuable.",
                        "A renúncia aos bens transitórios é feita com profunda alegria por causa da grandeza do Reino."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Es Jesús verdaderamente el centro y tesoro supremo de mi existencia, o sigo apegado a comodidades mundanas?",
                        "Is Jesus the supreme treasure of my heart, or do secondary idols still hold first place in my affections?",
                        "Jesus Cristo é verdadeiramente o tesouro mais valioso do meu coração?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, Tú eres mi único y verdadero tesoro. Que nada en este mundo me aparte jamás de tu santo amor».",
                        "“Jesus, Pearl of boundless price, be the supreme love of my soul. Let nothing in this world sever me from You.”",
                        "“Senhor Jesus, Vós sois o meu tesouro e a minha herança. Nada quero fora de Vós!”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Identifica hoy un apego desordenado (dinero, vanidad, aprobación de otros) y ofrécelo al Señor.",
                        "Renounce one unnecessary material indulgence today as a token of devotion to Christ your Treasure.",
                        "Abra mão de um apego material hoje em sinal de amor exclusivo a Cristo."
                    ),
                    catechismRefs = listOf("CIC 546", "Gaudium et Spes 22")
                ),
                ScriptureLesson(
                    id = "par_6_pharisee_tax_collector",
                    trackId = "track_parables",
                    lessonNumber = 6,
                    title = LocalizedText("El Fariseo y el Publicano: La Verdadera Humildad", "The Pharisee & the Publican: Authentic Humility", "O Fariseu e o Publicano: A Verdadeira Humildade"),
                    biblicalReference = "Lucas 18, 9-14",
                    biblicalPassageText = LocalizedText(
                        "«Dos hombres subieron al templo a orar: uno fariseo y otro publicano... El publicano, quedándose a distancia, ni siquiera se atrevía a levantar los ojos al cielo, sino que se golpeaba el pecho diciendo: '¡Oh Dios!, ten compasión de mí, que soy un pecador'. Les digo que éste bajó a su casa justificado y aquél no; porque todo el que se ensalce será humillado y el que se humille será ensalzado».",
                        "“Two people went up to the temple area to pray; one was a Pharisee and the other a tax collector... But the tax collector stood off at a distance and would not even raise his eyes to heaven but beat his breast and prayed, 'O God, be merciful to me a sinner.' I tell you, the latter went home justified, not the former; for everyone who exalts himself will be humbled, and the one who humbles himself will be exalted.”",
                        "“O publicano, porém, estando em pé, de longe, nem ainda queria levantar os olhos ao céu, mas batia no peito, dizendo: 'Ó Deus, tem misericórdia de mim, pecador!'”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Catecismo (CIC §2559) abre el tratado sobre la oración con la máxima de Santa Teresa de Jesús: 'La humildad es la base de la oración'. Si oramos con autosuficiencia, Dios no puede entrar.",
                        "The Catechism introduces prayer with this scripture (CCC 2559): 'Humility is the foundation of prayer.' We receive grace only with open, contrite hands.",
                        "O Catecismo (CIC 2559) ensina que a humildade é o fundamento da oração. Quem se justifica a si mesmo impede a ação da graça."
                    ),
                    lectioStep = LocalizedText(
                        "El fariseo reza 'consigo mismo' enumerando sus méritos y despreciando al prójimo; el publicano confía únicamente en la misericordia divina.",
                        "The Pharisee congratulates himself while disparaging others; the tax collector casts himself wholly upon unmerited divine mercy.",
                        "O fariseu ostenta suas virtudes com orgulho; o publicano reconhece sua pequenez e alcança o perdão."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Me siento a veces moralmente superior a otros que no practican la fe con la misma frecuencia que yo?",
                        "Do I ever secretly look down on others whose spiritual practice appears less devout than mine?",
                        "Caio na tentação de me achar melhor do que os outros pelas minhas práticas religiosas?"
                    ),
                    oratioStep = LocalizedText(
                        "«Jesús, manso y humilde de corazón, haz mi corazón semejante al tuyo. ¡Ten compasión de mí, Señor, que soy pecador!»",
                        "“Lord Jesus Christ, Son of the Living God, have mercy on me, a sinner. Teach me true evangelical lowliness.”",
                        "“Jesus, manso e humilde de coração, fazei o meu coração semelhante ao Vosso!”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Reza la Oración de Jesús («Señor Jesucristo, Hijo de Dios, ten piedad de mí, pecador») 10 veces en silencio.",
                        "Pray the Jesus Prayer ten times in reverent, contrite adoration today.",
                        "Reze a oração do publicano dez vezes com profunda devoção ao longo do dia."
                    ),
                    catechismRefs = listOf("CIC 2559", "CIC 2613")
                )
            )
        ),
        ScriptureCourseTrack(
            id = "track_apostolic",
            title = LocalizedText(
                "Enseñanzas Apostólicas y Cartas Paulinas",
                "Apostolic Teachings & Pauline Epistles",
                "Ensinamentos Apostólicos e Cartas Paulinas"
            ),
            description = LocalizedText(
                "Profundización en las epístolas del Nuevo Testamento sobre la justificación, la caridad cristiana, la armadura espiritual y la esperanza de la resurrección.",
                "Editorial study of apostolic theology: justification, the hymn of charity, the armor of God, and resurrection hope.",
                "Estudo teológico e pastoral das cartas de São Paulo sobre a fé, a caridade e a vida em Cristo."
            ),
            iconName = "MenuBook",
            lessons = listOf(
                ScriptureLesson(
                    id = "apo_1_justification",
                    trackId = "track_apostolic",
                    lessonNumber = 1,
                    title = LocalizedText("Paz con Dios y la Esperanza que no Defrauda", "Peace with God & the Hope that Never Fails", "Paz com Deus e a Esperança que Não Decepciona"),
                    biblicalReference = "Romanos 5, 1-11 y Romanos 8, 35-39",
                    biblicalPassageText = LocalizedText(
                        "«Habiendo, pues, recibido la justificación por la fe, estamos en paz con Dios por medio de nuestro Señor Jesucristo... y la esperanza no defrauda, porque el amor de Dios ha sido derramado en nuestros corazones por el Espíritu Santo que nos ha sido dado... ¿Quién nos separará del amor de Cristo? ¿La tribulación, la angustia, la persecución...? En todo esto vencemos con creces gracias a Aquel que nos amó».",
                        "“Therefore, since we have been justified by faith, we have peace with God through our Lord Jesus Christ... and hope does not disappoint, because the love of God has been poured out into our hearts through the holy Spirit that has been given to us... What will separate us from the love of Christ? Will anguish, or distress, or persecution...? No, in all these things we conquer overwhelmingly through him who loved us.”",
                        "“Justificados, pois, pela fé, temos paz com Deus, por nosso Senhor Jesus Cristo... e a esperança não nos decepciona, porque o amor de Deus foi derramado em nossos corações pelo Espírito Santo.”"
                    ),
                    vaticanContext = LocalizedText(
                        "La Declaración Conjunta sobre la Doctrina de la Justificación del Vaticano y el Catecismo (CIC §1987-2005) enseñan que la justificación es la gracia gratuita de Dios que no sólo perdona los pecados, sino que renueva interiormente al creyente haciéndole partícipe de la naturaleza divina.",
                        "Vatican teaching on justification emphasizes that divine grace gratuitously forgives sin and interiorly sanctifies the soul through infused theological virtues.",
                        "A justificação é dom gratuito de Deus que nos liberta do pecado e nos transforma em templos vivos da Santíssima Trindade (CIC 1987)."
                    ),
                    lectioStep = LocalizedText(
                        "San Pablo proclama con ardor que nada en toda la creación —ni la muerte ni los poderes celestes ni las pruebas terrenas— podrá arrancarnos del amor de Dios manifestado en Cristo.",
                        "Notice the unshakable certainty: neither tribulation nor death itself can sever the believer from Christ's sovereign love.",
                        "São Paulo atesta que nenhuma criatura ou tribulação poderá nos separar do amor de Cristo."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Vivo con la paz y seguridad de saberme amado incondicionalmente por Dios, o permito que las dificultades nublen mi esperanza?",
                        "Do I live in the peaceful certainty of divine sonship, or do temporary worries shake my supernatural confidence?",
                        "Conservo a paz da fé mesmo em meio às tempestades e provações da vida?"
                    ),
                    oratioStep = LocalizedText(
                        "«Señor Jesús, concédeme la gracia de anclar mi vida en tu amor inconmovible. Que ninguna tribulación me separe de Ti jamás».",
                        "“Lord Jesus, anchor my soul in Your boundless fidelity. Let nothing in heaven or earth separate me from Your sacred heart.”",
                        "“Senhor Jesus, aumentai a minha fé e dai-me a certeza de que nada me separará do Vosso amor!”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Repite hoy en momentos de tensión: «Si Dios está conmigo, ¿quién contra mí?» (Rm 8, 31).",
                        "Memorize and repeat Romans 8:31 today: 'If God is for us, who can be against us?'",
                        "Memorize e proclame hoje com fé: 'Se Deus é por nós, quem será contra nós?'"
                    ),
                    catechismRefs = listOf("CIC 1987-2005", "Dei Verbum 5")
                ),
                ScriptureLesson(
                    id = "apo_2_charity",
                    trackId = "track_apostolic",
                    lessonNumber = 2,
                    title = LocalizedText("El Himno a la Caridad Cristiana", "The Hymn of Christian Charity", "O Hino à Caridade Cristã"),
                    biblicalReference = "1 Corintios 13, 1-13",
                    biblicalPassageText = LocalizedText(
                        "«Aunque hablara las lenguas de los hombres y de los ángeles, si no tengo caridad, soy como bronce que suena o címbalo que retiñe... La caridad es paciente, es servicial; la caridad no es envidiosa, no es jactanciosa, no se engríe; es decorosa; no busca su interés; no se irrita; no toma en cuenta el mal... Todo lo disculpa, todo lo cree, todo lo espera, todo lo soporta. La caridad no acaba nunca».",
                        "“If I speak in human and angelic tongues but do not have love, I am a resounding gong or a clashing cymbal... Love is patient, love is kind. It is not jealous, love is not pompous, it is not inflated, it is not rude, it does not seek its own interests, it is not quick-tempered, it does not brood over injury... It bears all things, believes all things, hopes all things, endures all things. Love never fails.”",
                        "“Ainda que eu fale as línguas dos homens e dos anjos, se não tiver amor, serei como o bronze que soa... O amor é paciente, é benigno; não é invejoso, não se ensoberbece... O amor jamais acaba.”"
                    ),
                    vaticanContext = LocalizedText(
                        "Benedicto XVI (Deus Caritas Est) enseña que el ágape es el amor sacrificial de Dios derramado en la Cruz. Sin la virtud teologal de la caridad, los carismas más extraordinarios quedan vacíos y estériles (CIC §1822).",
                        "Pope Benedict XVI demonstrated in Deus Caritas Est that agape is the supreme revelation of God's essence; without charity, external works are spiritually barren.",
                        "Bento XVI recorda que Deus é Amor e que a caridade fraterna é a forma e a alma de todas as virtudes cristãs."
                    ),
                    lectioStep = LocalizedText(
                        "San Pablo define la caridad no con adjetivos románticos, sino con verbos de paciencia, perdón, servicio y renuncia al ego.",
                        "Saint Paul portrays love through rigorous action: patient endurance, benevolence, refusing self-seeking entitlement.",
                        "A caridade verdadeira é descrita através de atitudes concretas de renúncia e paciência."
                    ),
                    meditatioStep = LocalizedText(
                        "Al comparar mi conducta con 1 Corintios 13: ¿soy paciente con quien me impacienta? ¿Guardo rencor cuando me contradicen?",
                        "Testing my life against 1 Cor 13: Am I patient with family members? Do I harbor scoreboards of past offenses?",
                        "Como trato as pessoas em casa e no trabalho: com a paciência e doçura da caridade de Cristo?"
                    ),
                    oratioStep = LocalizedText(
                        "«Jesús, derrama en mi corazón tu caridad divina. Purifícame del egoísmo y enséñame a amar como Tú me has amado».",
                        "“Lord Jesus, pour Your pure charity into my veins. Forgive my coldness and make me an instrument of Your patient love.”",
                        "“Senhor Jesus, inflamai o meu coração no Vosso amor para que eu ame a todos com paciência e ternura.”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Responde con una palabra amable o un gesto silencioso de paciencia ante la primera provocación que recibas hoy.",
                        "Respond to any irritation or slight today with deliberate, quiet kindness and prayer.",
                        "Responda com mansidão e silêncio diante de qualquer contrariedade hoje."
                    ),
                    catechismRefs = listOf("CIC 1822-1829", "Deus Caritas Est")
                ),
                ScriptureLesson(
                    id = "apo_3_spiritual_armor",
                    trackId = "track_apostolic",
                    lessonNumber = 3,
                    title = LocalizedText("La Armadura de Dios para el Combate Espiritual", "The Armor of God in Spiritual Warfare", "A Armadura de Deus para o Combate Espiritual"),
                    biblicalReference = "Efesios 6, 10-18",
                    biblicalPassageText = LocalizedText(
                        "«Por lo demás, fortalézcanse en el Señor y en la fuerza de su poder. Revístanse de las armas de Dios para poder resistir a las acechanzas del diablo... Tomen el escudo de la fe, con el que podrán apagar todas las flechas incendiarias del Maligno. Tomen también el casco de la salvación y la espada del Espíritu, que es la palabra de Dios; siempre en oración y súplica».",
                        "“Draw your strength from the Lord and from his mighty power. Put on the armor of God so that you may be able to stand firm against the tactics of the devil... In all circumstances, hold faith as a shield, to quench all the flaming arrows of the evil one. And take the helmet of salvation and the sword of the Spirit, which is the word of God. With all prayer and supplication, pray at every opportunity in the Spirit.”",
                        "“Revesti-vos de toda a armadura de Deus, para que possais estar firmes contra as astutas ciladas do diabo... tomando sobretudo o escudo da fé, com o qual podereis apagar todos os dardos inflamados do maligno.”"
                    ),
                    vaticanContext = LocalizedText(
                        "El Papa Francisco (Gaudete et Exsultate §158-165) recuerda con firmeza que la vida cristiana es un combate espiritual permanente contra el príncipe de este mundo, y que la oración constante, los sacramentos y la Palabra de Dios son nuestras armas victoriosas.",
                        "Pope Francis (Gaudete et Exsultate 158-165) reminds believers that spiritual combat against demonic deception is real, requiring vigilant prayer and sacramental defenses.",
                        "Gaudete et Exsultate alerta que o combate espiritual exige vigilância contínua, fé firme e recurso assíduo aos sacramentos."
                    ),
                    lectioStep = LocalizedText(
                        "Nuestra lucha fundamental no es contra carne ni sangre humana, sino contra las fuerzas invisibles del pecado y la tentación.",
                        "The primary battlefield is spiritual; worldly armor fails where the shield of faith and sword of the Spirit triumph.",
                        "As armas do cristão são espirituais: a verdade, a justiça, a fé, a Palavra de Deus e a oração ininterrupta."
                    ),
                    meditatioStep = LocalizedText(
                        "¿Recurro de inmediato a la oración y a la intercesión de la Virgen y San Miguel Arcángel ante la primera tentación?",
                        "Do I proactively guard my senses, thoughts, and habits before temptation gains a foothold in my soul?",
                        "Reconheço as armadilhas do inimigo nas pequenas concessões ao pecado?"
                    ),
                    oratioStep = LocalizedText(
                        "«San Miguel Arcángel, defiéndenos en la batalla. Sé nuestro amparo contra la perversidad y acechanzas del demonio. Amén».",
                        "“Saint Michael the Archangel, defend us in battle. Be our protection against the malice and snares of the devil. Amen.”",
                        "“São Miguel Arcanjo, defendei-nos no combate, sede o nosso refúgio contra as ciladas do demônio!”"
                    ),
                    contemplatioStep = LocalizedText(
                        "Reza la oración a San Miguel Arcángel al salir de casa o al comenzar tu jornada laboral.",
                        "Recite the Saint Michael prayer with reverent fervor at the start of your day.",
                        "Reze a oração a São Miguel Arcanjo pedindo proteção para sua família hoje."
                    ),
                    catechismRefs = listOf("CIC 2850-2854", "Gaudete et Exsultate 158-165")
                )
            )
        )
    )
}
