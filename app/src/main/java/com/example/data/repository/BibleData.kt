package com.example.data.repository

import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import com.example.data.models.BibleCategory
import com.example.data.models.BibleChapter
import com.example.data.models.BibleVerse
import com.example.data.models.BibleVersionInfo
import com.example.data.models.DailyMassReading
import com.example.data.models.LocalizedText
import com.example.data.models.Testament

object BibleData {

    val catholicCanonBooks: List<BibleBook> = listOf(
        // Pentateuch
        BibleBook("gen", LocalizedText("Génesis", "Genesis", "Gênesis"), "Gn", Testament.OLD, BibleCategory.PENTATEUCH, false, 50,
            LocalizedText("El relato de la creación, la caída, los patriarcas y la promesa divina.", "The origin of creation, the patriarchs, and the covenant.", "A criação, patriarcas e a aliança com Deus.")),
        BibleBook("exo", LocalizedText("Éxodo", "Exodus", "Êxodo"), "Ex", Testament.OLD, BibleCategory.PENTATEUCH, false, 40,
            LocalizedText("La liberación de Israel de Egipto, la Pascua y la Alianza del Sinaí.", "The liberation of Israel, the Passover, and the Law at Sinai.", "A libertação do Egito, a Páscoa e a Lei do Sinai.")),
        BibleBook("lev", LocalizedText("Levítico", "Leviticus", "Levítico"), "Lv", Testament.OLD, BibleCategory.PENTATEUCH, false, 27,
            LocalizedText("Leyes sagradas, santidad sacerdotal y el culto al Dios Vivo.", "Holiness code, priesthood, and sacrificial offerings.", "Santidade do povo de Deus e leis sacerdotais.")),
        BibleBook("num", LocalizedText("Números", "Numbers", "Números"), "Nm", Testament.OLD, BibleCategory.PENTATEUCH, false, 36,
            LocalizedText("La peregrinación del pueblo en el desierto hacia la Tierra Prometida.", "The desert wandering toward the Promised Land.", "A peregrinação pelo deserto rumo à Terra Prometida.")),
        BibleBook("deu", LocalizedText("Deuteronomio", "Deuteronomy", "Deuteronômio"), "Dt", Testament.OLD, BibleCategory.PENTATEUCH, false, 34,
            LocalizedText("El gran discurso de Moisés renovando la Alianza: Escucha, oh Israel.", "Moses' farewell discourse and covenant renewal.", "O discurso de despedida de Moisés e renovação da aliança.")),

        // Historical & Deuterocanonical
        BibleBook("jos", LocalizedText("Josué", "Joshua", "Josué"), "Jos", Testament.OLD, BibleCategory.HISTORICAL, false, 24,
            LocalizedText("La entrada y establecimiento en la Tierra Prometida.", "Entering the Promised Land.", "A conquista e assentamento na Terra Prometida.")),
        BibleBook("jud", LocalizedText("Jueces", "Judges", "Juízes"), "Jue", Testament.OLD, BibleCategory.HISTORICAL, false, 21,
            LocalizedText("Los líderes providenciales que Dios envió para rescatar a su pueblo.", "Israel's deliverers in times of crisis.", "Os líderes que Deus enviou para libertar Seu povo.")),
        BibleBook("rut", LocalizedText("Rut", "Ruth", "Rute"), "Rut", Testament.OLD, BibleCategory.HISTORICAL, false, 4,
            LocalizedText("La fiel moabita que se convirtió en antepasada del Rey David y de Cristo.", "The faithful Gentile ancestress of Christ.", "A jovem fiel e bisavó do Rei Davi.")),
        BibleBook("1sa", LocalizedText("1 Samuel", "1 Samuel", "1 Samuel"), "1S", Testament.OLD, BibleCategory.HISTORICAL, false, 31,
            LocalizedText("El profeta Samuel, la unción del rey Saúl y el joven David.", "Samuel, King Saul, and the rise of David.", "Samuel, a unção de Saul e a juventude de Davi.")),
        BibleBook("2sa", LocalizedText("2 Samuel", "2 Samuel", "2 Samuel"), "2S", Testament.OLD, BibleCategory.HISTORICAL, false, 24,
            LocalizedText("El reinado davídico y la promesa del trono eterno que cumplirá el Mesías.", "David's reign and the eternal covenant promise.", "O reinado de Davi e a promessa do Reino eterno.")),
        BibleBook("1ki", LocalizedText("1 Reyes", "1 Kings", "1 Reis"), "1R", Testament.OLD, BibleCategory.HISTORICAL, false, 22,
            LocalizedText("La gloria del templo de Salomón y la división del reino.", "Solomon's temple and kingdom division.", "O templo de Salomão e a divisão do reino.")),
        BibleBook("2ki", LocalizedText("2 Reyes", "2 Kings", "2 Reis"), "2R", Testament.OLD, BibleCategory.HISTORICAL, false, 25,
            LocalizedText("Los profetas Elías y Eliseo, el exilio en Babilonia.", "The prophets Elijah and Elisha and the exile.", "Elias, Eliseu e a queda de Jerusalém.")),
        BibleBook("1ch", LocalizedText("1 Crónicas", "1 Chronicles", "1 Crônicas"), "1Cr", Testament.OLD, BibleCategory.HISTORICAL, false, 29,
            LocalizedText("Genealogías sagradas, el reinado de David y la preparación del Santo Templo.", "Genealogies of Israel and the reign of David.", "Genealogias sagradas e a preparação do Templo por Davi.")),
        BibleBook("2ch", LocalizedText("2 Crónicas", "2 Chronicles", "2 Crônicas"), "2Cr", Testament.OLD, BibleCategory.HISTORICAL, false, 36,
            LocalizedText("El Templo de Salomón, los reyes de Judá y el edicto de Ciro para el retorno.", "Solomon's temple, kings of Judah, and the return from exile.", "A história dos reis de Judá e o retorno do exílio.")),
        BibleBook("ezr", LocalizedText("Esdras", "Ezra", "Esdras"), "Esd", Testament.OLD, BibleCategory.HISTORICAL, false, 10,
            LocalizedText("El retorno del exilio babilónico y la reconstrucción del Templo de Dios.", "The return from Babylon and rebuilding of the Temple.", "A reconstrução do Templo sob Esdras.")),
        BibleBook("neh", LocalizedText("Nehemías", "Nehemiah", "Neemias"), "Neh", Testament.OLD, BibleCategory.HISTORICAL, false, 13,
            LocalizedText("La reconstrucción de las murallas de Jerusalén y renovación de la Alianza.", "Rebuilding Jerusalem's walls and covenant renewal.", "A reconstrução das muralhas de Jerusalém.")),
        BibleBook("tob", LocalizedText("Tobías", "Tobit", "Tobias"), "Tob", Testament.OLD, BibleCategory.HISTORICAL, true, 14,
            LocalizedText("Libro Deuterocanónico. San Rafael Arcángel guía al joven Tobías; la providencia y la oración.", "Deuterocanonical. St. Raphael the Archangel, fidelity in trials, and filial piety.", "Deuterocanônico. O Arcanjo São Rafael e a piedade familiar.")),
        BibleBook("jdt", LocalizedText("Judit", "Judith", "Judite"), "Jdt", Testament.OLD, BibleCategory.HISTORICAL, true, 16,
            LocalizedText("Libro Deuterocanónico. La heroína que salvó a Israel de Holofernes; prefiguración de María.", "Deuterocanonical. Judith saves her nation; Marian prefiguration.", "Deuterocanônico. A corajosa mulher que libertou seu povo.")),
        BibleBook("est", LocalizedText("Ester", "Esther", "Ester"), "Est", Testament.OLD, BibleCategory.HISTORICAL, false, 10,
            LocalizedText("Con adiciones deuterocanónicas en griego. La providencia salvando a los judíos.", "Includes Catholic Greek additions with rich prayers of Mordecai and Esther.", "Com acréscimos gregos deuterocanônicos de grande beleza.")),
        BibleBook("1ma", LocalizedText("1 Macabeos", "1 Maccabees", "1 Macabeus"), "1M", Testament.OLD, BibleCategory.HISTORICAL, true, 16,
            LocalizedText("Libro Deuterocanónico. La resistencia de los fieles ante Antíoco; la purificación del Templo.", "Deuterocanonical. The heroic defense of the Temple and holy faith.", "Deuterocanônico. A heroica luta pela fé e liberdade religiosa.")),
        BibleBook("2ma", LocalizedText("2 Macabeos", "2 Maccabees", "2 Macabeus"), "2M", Testament.OLD, BibleCategory.HISTORICAL, true, 15,
            LocalizedText("Libro Deuterocanónico. Testimonio explícito de la resurrección y la oración por los difuntos (Purgatorio).", "Deuterocanonical. Classic biblical foundation for resurrection and prayers for the holy souls.", "Deuterocanônico. Testemunho bíblico da oração pelos defuntos.")),

        // Wisdom & Poetry
        BibleBook("job", LocalizedText("Job", "Job", "Jó"), "Job", Testament.OLD, BibleCategory.WISDOM, false, 42,
            LocalizedText("El misterio del sufrimiento del inocente y la soberanía de Dios.", "The mystery of suffering and divine wisdom.", "O mistério do sofrimento do justo e a glória de Deus.")),
        BibleBook("psa", LocalizedText("Salmos", "Psalms", "Salmos"), "Sal", Testament.OLD, BibleCategory.WISDOM, false, 150,
            LocalizedText("El gran libro de oración de la Iglesia y de Cristo; 150 cánticos divinamente inspirados.", "The great prayer book of the Church: praise, repentance, and messianic hope.", "O livro de oração por excelência do Povo de Deus.")),
        BibleBook("pro", LocalizedText("Proverbios", "Proverbs", "Provérbios"), "Pr", Testament.OLD, BibleCategory.WISDOM, false, 31,
            LocalizedText("Sentencias morales prácticas para una vida recta guiada por el temor del Señor.", "Practical wisdom and moral instruction in the fear of the Lord.", "Instruções morais para o caminho da retidão.")),
        BibleBook("ecc", LocalizedText("Eclesiastés (Qohélet)", "Ecclesiastes", "Eclesiastes"), "Ecl", Testament.OLD, BibleCategory.WISDOM, false, 12,
            LocalizedText("Todo es vanidad bajo el sol si falta Dios; llamado a recordar al Creador.", "Life without God is vanity; remember your Creator in your youth.", "A transitoriedade das coisas terrenas e a busca de Deus.")),
        BibleBook("sng", LocalizedText("Cantar de los Cantares", "Song of Songs", "Cântico dos Cânticos"), "Cnt", Testament.OLD, BibleCategory.WISDOM, false, 8,
            LocalizedText("El amor esponsalicio entre Dios y el alma, y de Cristo con su Santa Iglesia.", "Allegory of mystical love between Christ and His Bride, the Church.", "O amor místico entre Deus e a alma fiel.")),
        BibleBook("wis", LocalizedText("Sabiduría", "Wisdom of Solomon", "Sabedoria"), "Sab", Testament.OLD, BibleCategory.WISDOM, true, 19,
            LocalizedText("Libro Deuterocanónico. La Sabiduría increada, inmortalidad del alma y justicia.", "Deuterocanonical. The beauty of divine wisdom and eternal destiny of the soul.", "Deuterocanônico. A glória da Sabedoria divina e vida eterna.")),
        BibleBook("sir", LocalizedText("Sirácida (Eclesiástico)", "Sirach (Ecclesiasticus)", "Eclesiástico (Sirácida)"), "Eclo", Testament.OLD, BibleCategory.WISDOM, true, 51,
            LocalizedText("Libro Deuterocanónico. Consejos de vida, amistad santa, deberes familiares y alabanza a los antepasados.", "Deuterocanonical. Vast moral instruction used in catechumenal instruction.", "Deuterocanônico. Grande manual de conduta moral e amor à Lei.")),

        // Prophets
        BibleBook("isa", LocalizedText("Isaías", "Isaiah", "Isaías"), "Is", Testament.OLD, BibleCategory.PROPHETS, false, 66,
            LocalizedText("El quinto evangelista del AT: profecías de la Virgen encinta (7:14) y el Siervo Doliente (53).", "The Prince of Prophets: prophecies of the Virgin birth and Suffering Servant.", "O grande profeta messiânico e os cantos do Servo de Deus.")),
        BibleBook("jer", LocalizedText("Jeremías", "Jeremiah", "Jeremias"), "Jer", Testament.OLD, BibleCategory.PROPHETS, false, 52,
            LocalizedText("La vocación profética, el dolor por el pueblo y la promesa de la Nueva Alianza.", "The call to repentance and the promise of the New Covenant.", "O profeta que anunciou a Nova Aliança escrita no coração.")),
        BibleBook("lam", LocalizedText("Lamentaciones", "Lamentations", "Lamentações"), "Lam", Testament.OLD, BibleCategory.PROPHETS, false, 5,
            LocalizedText("Cantos elegíacos sobre la destrucción de Jerusalén; la fidelidad de Dios nunca decae.", "Elegies over Jerusalem's fall; God's mercies are new every morning.", "Poemas de lamento e esperança na misericórdia de Deus.")),
        BibleBook("bar", LocalizedText("Baruc", "Baruch", "Baruc"), "Bar", Testament.OLD, BibleCategory.PROPHETS, true, 6,
            LocalizedText("Libro Deuterocanónico. La oración de los exiliados, la Sabiduría divina y la Carta de Jeremías.", "Deuterocanonical. Exilic prayer, divine wisdom manifest in Israel.", "Deuterocanônico. A oração de penitência e consolo do profeta.")),
        BibleBook("eze", LocalizedText("Ezequiel", "Ezekiel", "Ezequiel"), "Ez", Testament.OLD, BibleCategory.PROPHETS, false, 48,
            LocalizedText("Visiones celestiales, el corazón nuevo de carne y el Templo renovado.", "Visions of divine glory, the valley of dry bones, and new heart.", "A glória de Deus, a visão dos ossos secos e o coração novo.")),
        BibleBook("dan", LocalizedText("Daniel", "Daniel", "Daniel"), "Dan", Testament.OLD, BibleCategory.PROPHETS, false, 14,
            LocalizedText("Con adiciones deuterocanónicas (Cántico de los Tres Jóvenes y Susana). El Hijo del Hombre.", "Includes Catholic deuterocanonical chapters 13 (Susanna) and 14 (Bel and Dragon).", "Com os capítulos deuterocanônicos de Susana e o hino dos três jovens.")),
        BibleBook("hos", LocalizedText("Oseas", "Hosea", "Oseias"), "Os", Testament.OLD, BibleCategory.PROPHETS, false, 14,
            LocalizedText("El amor fiel e incondicional de Dios hacia un pueblo infiel.", "God's steadfast, redeeming love for His covenant people.", "O amor fiel e eterno de Deus por Seu povo.")),
        BibleBook("joe", LocalizedText("Joel", "Joel", "Joel"), "Jl", Testament.OLD, BibleCategory.PROPHETS, false, 3,
            LocalizedText("El derramamiento del Espíritu Santo cumplido en Pentecostés (Hch 2).", "The outpouring of the Holy Spirit fulfilled at Pentecost.", "A promessa do Espírito Santo sobre toda a humanidade.")),
        BibleBook("amo", LocalizedText("Amós", "Amos", "Amós"), "Am", Testament.OLD, BibleCategory.PROPHETS, false, 9,
            LocalizedText("El clamor de Dios por la justicia social y el culto sincero del corazón.", "God's call for justice and righteousness flowing like a river.", "O clamor pela justiça social e misericórdia.")),
        BibleBook("oba", LocalizedText("Abdías", "Obadiah", "Obadias"), "Abd", Testament.OLD, BibleCategory.PROPHETS, false, 1,
            LocalizedText("La caída de la soberbia terrena y el triunfo del Monte Sión.", "The fall of worldly pride and the triumph of the Lord's kingdom.", "A vitória da justiça divina contra o orgulho.")),
        BibleBook("jon", LocalizedText("Jonás", "Jonah", "Jonas"), "Jon", Testament.OLD, BibleCategory.PROPHETS, false, 4,
            LocalizedText("El signo de Jonás: tres días en el vientre del pez prefiguran la Resurrección de Cristo.", "The sign of Jonah prefiguring Christ's Resurrection on the third day.", "O sinal dos três dias prefigurando a Ressurreição.")),
        BibleBook("mic", LocalizedText("Miqueas", "Micah", "Miqueias"), "Miq", Testament.OLD, BibleCategory.PROPHETS, false, 7,
            LocalizedText("Profecía del nacimiento del Mesías en Belén de Judá (5:2).", "Prophecy of the Messiah's birth in Bethlehem (5:2).", "A profecia do nascimento do Messias em Belém.")),
        BibleBook("nah", LocalizedText("Nahúm", "Nahum", "Naum"), "Nah", Testament.OLD, BibleCategory.PROPHETS, false, 3,
            LocalizedText("El juicio de Dios sobre Nínive y la consolación de los oprimidos.", "Divine justice against tyranny and comfort for God's faithful.", "A consolação dos aflitos e o julgamento divino.")),
        BibleBook("hab", LocalizedText("Habacuc", "Habakkuk", "Habacuc"), "Hab", Testament.OLD, BibleCategory.PROPHETS, false, 3,
            LocalizedText("El justo vivirá por su fe: esperanza inquebrantable en la soberanía divina.", "The righteous shall live by faith; patient trust in divine providence.", "O justo viverá da sua fé e fidelidade.")),
        BibleBook("zep", LocalizedText("Sofonías", "Zephaniah", "Sofonias"), "Sof", Testament.OLD, BibleCategory.PROPHETS, false, 3,
            LocalizedText("El Día del Señor y la promesa de un resto humilde y fiel.", "The Day of the Lord and the promise of a humble remnant.", "A promessa da restauração do pequeno resto fiel.")),
        BibleBook("hag", LocalizedText("Ageo", "Haggai", "Ageu"), "Ag", Testament.OLD, BibleCategory.PROPHETS, false, 2,
            LocalizedText("Llamado a reedificar el Templo del Señor con celo sagrado.", "Exhortation to rebuild the Lord's house with holy zeal.", "A exortação à reedificação da Casa de Deus.")),
        BibleBook("zec", LocalizedText("Zacarías", "Zechariah", "Zacarias"), "Zac", Testament.OLD, BibleCategory.PROPHETS, false, 14,
            LocalizedText("El Rey humilde que entra en Jerusalén montado en un asno (9:9) y el traspasado (12:10).", "Messianic visions: Christ entering on a donkey and the One pierced.", "O Messias Rei da paz e as trinta moedas de prata.")),
        BibleBook("mal", LocalizedText("Malaquías", "Malachi", "Malaquias"), "Mal", Testament.OLD, BibleCategory.PROPHETS, false, 4,
            LocalizedText("La profecía del sacrificio puro ofrecido en todo lugar: la Santa Misa (1:11).", "Prophecy of the pure offering among all nations: the Holy Mass (1:11).", "A profecia da oblação pura em todas as nações: a Eucaristia.")),

        // Gospels and Acts
        BibleBook("mat", LocalizedText("San Mateo", "Matthew", "São Mateus"), "Mt", Testament.NEW, BibleCategory.GOSPELS, false, 28,
            LocalizedText("El Evangelio eclesial: Jesús el nuevo Moisés, Sermón del Monte y primado de Pedro (16:18).", "Gospel of the Kingdom: Sermon on the Mount and Peter's commission.", "O Evangelho do Reino e a instituição da Igreja de Cristo.")),
        BibleBook("mar", LocalizedText("San Marcos", "Mark", "São Marcos"), "Mc", Testament.NEW, BibleCategory.GOSPELS, false, 16,
            LocalizedText("El Evangelio dinámico: Jesús el Siervo Conquistador, Hijo de Dios y Redentor.", "The active Gospel: Jesus as the Son of God and Suffering Healer.", "O Evangelho conciso que proclama Jesus como Filho de Deus.")),
        BibleBook("luk", LocalizedText("San Lucas", "Luke", "São Lucas"), "Lc", Testament.NEW, BibleCategory.GOSPELS, false, 24,
            LocalizedText("El Evangelio de la misericordia, la Virgen María (Magnificat) y los pobres.", "Gospel of mercy, the Holy Spirit, and the Blessed Mother.", "O Evangelho da ternura de Deus e da bem-aventurada Virgem Maria.")),
        BibleBook("joh", LocalizedText("San Juan", "John", "São João"), "Jn", Testament.NEW, BibleCategory.GOSPELS, false, 21,
            LocalizedText("El Evangelio teológico: El Verbo hecho carne, la Eucaristía (Jn 6) y la Madre al pie de la Cruz.", "The spiritual Gospel: The Incarnate Word, Eucharistic discourse, and high theology.", "O Evangelho espiritual do Verbo de Deus e do Bom Pastor.")),
        BibleBook("act", LocalizedText("Hechos de los Apóstoles", "Acts of the Apostles", "Atos dos Apóstolos"), "Hch", Testament.NEW, BibleCategory.GOSPELS, false, 28,
            LocalizedText("El nacimiento y expansión de la Iglesia Católica impulsada por el Espíritu Santo.", "The birth and mission of the universal Church led by the Apostles.", "A expansão da Igreja primitiva pelo poder do Espírito Santo.")),

        // Epistles & Revelation
        BibleBook("rom", LocalizedText("Romanos", "Romans", "Romanos"), "Rom", Testament.NEW, BibleCategory.EPISTLES, false, 16,
            LocalizedText("La gran carta de San Pablo sobre la justificación, la gracia y la vida en el Espíritu.", "St. Paul's theological masterwork on grace, faith, and salvation.", "A grande epístola de São Paulo sobre a fé e a graça salvadora.")),
        BibleBook("1co", LocalizedText("1 Corintios", "1 Corinthians", "1 Coríntios"), "1Co", Testament.NEW, BibleCategory.EPISTLES, false, 16,
            LocalizedText("La Eucaristía (11:23-26), el Cuerpo Místico de Cristo y el himno al Amor (13).", "Eucharistic liturgy, unity of the Church, and the hymn to divine charity.", "A instituição da Eucaristia e o hino à caridade cristã.")),
        BibleBook("2co", LocalizedText("2 Corintios", "2 Corinthians", "2 Coríntios"), "2Co", Testament.NEW, BibleCategory.EPISTLES, false, 13,
            LocalizedText("El ministerio apostólico de reconciliación; fortaleza divina en la debilidad humana.", "The apostolic ministry of reconciliation and strength in weakness.", "O ministério da reconciliação e a consolação de Deus.")),
        BibleBook("gal", LocalizedText("Gálatas", "Galatians", "Gálatas"), "Gál", Testament.NEW, BibleCategory.EPISTLES, false, 6,
            LocalizedText("La libertad de los hijos de Dios, la gracia de Cristo y los frutos del Espíritu Santo.", "Freedom in Christ and the nine fruits of the Holy Spirit.", "A liberdade cristã e os frutos do Espírito Santo.")),
        BibleBook("eph", LocalizedText("Efesios", "Ephesians", "Efésios"), "Ef", Testament.NEW, BibleCategory.EPISTLES, false, 6,
            LocalizedText("El misterio de la Iglesia como Cuerpo y Esposa de Cristo; la armadura de Dios.", "The Church as the Bride of Christ and the armor of God.", "O mistério da Igreja, Corpo de Cristo, e a armadura espiritual.")),
        BibleBook("phi", LocalizedText("Filipenses", "Philippians", "Filipenses"), "Flp", Testament.NEW, BibleCategory.EPISTLES, false, 4,
            LocalizedText("La alegría cristiana en Cristo; el himno de la Kénosis (2:5-11).", "Rejoicing always in the Lord; the great Christological hymn of self-emptying.", "A alegria no Senhor e o hino à humildade de Cristo.")),
        BibleBook("col", LocalizedText("Colosenses", "Colossians", "Colossenses"), "Col", Testament.NEW, BibleCategory.EPISTLES, false, 4,
            LocalizedText("La absoluta primacía de Cristo Cabeza sobre toda la creación.", "The supremacy and cosmic preeminence of Christ.", "A supremacia de Cristo sobre toda a criação.")),
        BibleBook("1th", LocalizedText("1 Tesalonicenses", "1 Thessalonians", "1 Tessalonicenses"), "1Ts", Testament.NEW, BibleCategory.EPISTLES, false, 5,
            LocalizedText("La esperanza en la Parusía, la resurrección de los muertos y la santidad.", "Hope in the Second Coming of Christ and call to holiness.", "A esperança na vinda do Senhor e a santificação.")),
        BibleBook("2th", LocalizedText("2 Tesalonicenses", "2 Thessalonians", "2 Tessalonicenses"), "2Ts", Testament.NEW, BibleCategory.EPISTLES, false, 3,
            LocalizedText("Firmeza en la tradición apostólica y perseverancia ante las pruebas.", "Steadfastness in apostolic traditions and endurance.", "A perseverança na fé e fidelidade às tradições apostólicas.")),
        BibleBook("1ti", LocalizedText("1 Timoteo", "1 Timothy", "1 Timóteo"), "1Tim", Testament.NEW, BibleCategory.EPISTLES, false, 6,
            LocalizedText("El orden sagrado de la Iglesia, el sacerdocio ministerial y la sana doctrina.", "Pastoral order, priesthood, and defending orthodox doctrine.", "O ministério pastoral e a guarda da sã doutrina.")),
        BibleBook("2ti", LocalizedText("2 Timoteo", "2 Timothy", "2 Timóteo"), "2Tim", Testament.NEW, BibleCategory.EPISTLES, false, 4,
            LocalizedText("El testamento espiritual de San Pablo: he combatido el buen combate.", "Paul's moving spiritual testament: fighting the good fight.", "O testamento espiritual de Paulo: combati o bom combate.")),
        BibleBook("tit", LocalizedText("Tito", "Titus", "Tito"), "Tit", Testament.NEW, BibleCategory.EPISTLES, false, 3,
            LocalizedText("La piedad cristiana, el bautismo de regeneración y las buenas obras.", "Pastoral leadership, grace, and regeneration in Baptism.", "A organização eclesial e a vida na graça de Deus.")),
        BibleBook("phm", LocalizedText("Filemón", "Philemon", "Filemom"), "Flm", Testament.NEW, BibleCategory.EPISTLES, false, 1,
            LocalizedText("Fraternidad evangélica que trasciende toda servidumbre humana en Cristo.", "Christian brotherhood and love transforming social bonds.", "A fraternidade e perdão entre irmãos em Cristo.")),
        BibleBook("heb", LocalizedText("Hebreos", "Hebrews", "Hebreus"), "Heb", Testament.NEW, BibleCategory.EPISTLES, false, 13,
            LocalizedText("Jesucristo Sumo Sacerdote eterno según el orden de Melquisedec; el altar de la Nueva Alianza.", "Christ our supreme High Priest and the eternal Eucharistic sacrifice.", "Cristo, Sumo Sacerdote eterno e mediador da Nova Aliança.")),
        BibleBook("jam", LocalizedText("Santiago", "James", "São Tiago"), "Sant", Testament.NEW, BibleCategory.EPISTLES, false, 5,
            LocalizedText("La fe viva mostrada por las obras; la Unción de los Enfermos (5:14).", "Faith without works is dead; the Sacrament of the Anointing of the Sick.", "A fé manifestada pelas boas obras e a Unção dos enfermos.")),
        BibleBook("1pe", LocalizedText("1 Pedro", "1 Peter", "1 São Pedro"), "1P", Testament.NEW, BibleCategory.EPISTLES, false, 5,
            LocalizedText("Pueblo sacerdotal consagrado a Dios; esperanza cristiana ante el sufrimiento.", "The royal priesthood of believers and holy endurance under trials.", "O sacerdócio régio e a esperança cristã viva.")),
        BibleBook("2pe", LocalizedText("2 Pedro", "2 Peter", "2 São Pedro"), "2P", Testament.NEW, BibleCategory.EPISTLES, false, 3,
            LocalizedText("Partícipes de la naturaleza divina; vigilancia ante los falsos maestros.", "Partakers of the divine nature and vigilance until the Day of the Lord.", "A participação na natureza divina e a vigilância cristã.")),
        BibleBook("1jo", LocalizedText("1 Juan", "1 John", "1 São João"), "1Jn", Testament.NEW, BibleCategory.EPISTLES, false, 5,
            LocalizedText("Dios es Amor; comunión filial con el Padre por medio de Jesucristo.", "God is love; walk in the light as children of the Father.", "Deus é Amor; a comunhão fraterna e filiação divina.")),
        BibleBook("2jo", LocalizedText("2 Juan", "2 John", "2 São João"), "2Jn", Testament.NEW, BibleCategory.EPISTLES, false, 1,
            LocalizedText("Permanecer en la verdad y el mandamiento del amor fraterno.", "Abiding in truth and the commandment of divine love.", "A perseverança na verdade e no amor fraterno.")),
        BibleBook("3jo", LocalizedText("3 Juan", "3 John", "3 São João"), "3Jn", Testament.NEW, BibleCategory.EPISTLES, false, 1,
            LocalizedText("La hospitalidad y comunión eclesial con los predicadores de la Verdad.", "Christian hospitality and support for missionaries of the Gospel.", "A hospitalidade e o testemunho da verdade.")),
        BibleBook("jde", LocalizedText("Judas", "Jude", "São Judas"), "Jud", Testament.NEW, BibleCategory.EPISTLES, false, 1,
            LocalizedText("Combatir por la fe transmitida a los santos de una vez para siempre.", "Contending earnestly for the faith once delivered to the saints.", "A guarda fiel da fé transmitida aos santos.")),
        BibleBook("rev", LocalizedText("Apocalipsis", "Revelation", "Apocalipse"), "Ap", Testament.NEW, BibleCategory.REVELATION, false, 22,
            LocalizedText("La liturgia celestial del Cordero degollado, la Mujer vestida de sol y la Jerusalén celestial.", "The heavenly liturgy, Christ's definitive victory, and the Holy City.", "A vitória final do Cordeiro e o banquete celeste da Igreja."))
    )

    val sampleVersesWithCommentaries: List<BibleVerse> = listOf(
        BibleVerse(
            number = 1,
            text = LocalizedText(
                "En el principio era el Verbo, y el Verbo estaba con Dios, y el Verbo era Dios.",
                "In the beginning was the Word, and the Word was with God, and the Word was God.",
                "No princípio era o Verbo, e o Verbo estava com Deus, e o Verbo era Deus."
            ),
            commentary = LocalizedText(
                "San Agustín (Tratado sobre San Juan): El Verbo no fue hecho criatura, sino engendrado desde la eternidad por el Padre. Es la Sabiduría increada que se nos reveló plenamente.",
                "St. Augustine (Tractate on John): The Word was not made a creature, but eternally begotten of the Father. He is uncreated Wisdom made manifest.",
                "Santo Agostinho: O Verbo não foi feito criatura, mas gerado eternamente pelo Pai, luz da luz e Deus verdadeiro."
            ),
            commentaryAuthor = "San Agustín de Hipona"
        ),
        BibleVerse(
            number = 14,
            text = LocalizedText(
                "Y el Verbo se hizo carne, y habitó entre nosotros, y vimos su gloria.",
                "And the Word became flesh, and dwelt among us, and we saw His glory.",
                "E o Verbo se fez carne e habitou entre nós, e vimos a sua glória."
            ),
            commentary = LocalizedText(
                "Santo Tomás de Aquino (Summa Theologiae III): El Hijo unigénito de Dios, queriendo hacernos partícipes de su divinidad, asumió nuestra naturaleza humana.",
                "St. Thomas Aquinas (Summa Theologiae III): The only-begotten Son of God, desiring to make us partakers of His divinity, took our human nature upon Himself.",
                "São Tomás de Aquino: Para nos fazer participantes de Sua divindade, o Filho de Deus assumiu a nossa fragilidade humana."
            ),
            commentaryAuthor = "Santo Tomás de Aquino"
        ),
        BibleVerse(
            number = 18,
            text = LocalizedText(
                "Y yo te digo que tú eres Pedro, y sobre esta piedra edificaré mi Iglesia, y las puertas del infierno no prevalecerán contra ella.",
                "And I say to you, that you are Peter, and upon this rock I will build my Church; and the gates of hell shall not prevail against it.",
                "E eu te declaro: tu és Pedro, e sobre esta pedra edificarei a minha Igreja; e as portas do inferno não prevalecerão contra ela."
            ),
            commentary = LocalizedText(
                "San Juan Crisóstomo (Homilía 54 sobre Mateo): Confió el Señor a un pescador el cuidado de la Iglesia universal y le dio las llaves del Reino celestial.",
                "St. John Chrysostom (Homily 54 on Matthew): The Lord entrusted the care of the universal Church to a fisherman, bestowing upon him the keys of the Kingdom of Heaven.",
                "São João Crisóstomo: O Senhor confiou ao Apóstolo a custódia da fé para confirmação perpétua dos irmãos."
            ),
            commentaryAuthor = "San Juan Crisóstomo"
        ),
        BibleVerse(
            number = 28,
            text = LocalizedText(
                "Alégrate, llena de gracia, el Señor está contigo.",
                "Hail, full of grace, the Lord is with you.",
                "Alegra-te, cheia de graça, o Senhor está contigo."
            ),
            commentary = LocalizedText(
                "San Jerónimo: El título griego 'Kecharitomene' indica una plenitud perenne de gracia recibida, preludio del dogma de la Inmaculada Concepción.",
                "St. Jerome: The Greek title 'Kecharitomene' signifies a unique, complete fullness of grace, bearing witness to her immaculate sanctity.",
                "São Jerônimo: O termo 'cheia de graça' revela a pureza singular e a preservação perfeitíssima da Mãe de Deus."
            ),
            commentaryAuthor = "San Jerónimo de Estridón"
        )
    )

    fun getVersesForBookAndChapter(bookId: String, chapterNumber: Int): List<BibleVerse> {
        val book = catholicCanonBooks.firstOrNull { it.id == bookId } ?: catholicCanonBooks.first()
        return CatholicBibleContentEngine.getVersesForChapter(book, chapterNumber)
    }


    data class DeuterocanonicalBookDetail(
        val name: LocalizedText,
        val abbreviation: String,
        val chapters: Int,
        val doctrinalSignificance: LocalizedText,
        val keyVerse: LocalizedText,
        val keyReference: String
    )

    data class CanonHistoricalMilestone(
        val year: String,
        val councilOrEvent: LocalizedText,
        val description: LocalizedText
    )

    val deuterocanonicalBooksDetail: List<DeuterocanonicalBookDetail> = listOf(
        DeuterocanonicalBookDetail(
            name = LocalizedText("Tobías", "Tobit", "Tobias"),
            abbreviation = "Tob",
            chapters = 14,
            doctrinalSignificance = LocalizedText(
                "Enseña la intercesión de los Santos Ángeles (San Rafael Arcángel presentando oraciones ante el trono de Dios), el valor de la limosna y la santidad del matrimonio cristiano.",
                "Affirms angelic intercession before God's throne (St. Raphael), the redemptive value of almsgiving, and the sanctity of holy matrimony.",
                "Ensina a intercessão dos Santos Anjos (São Rafael), a santidade do matrimônio e o valor meritório da esmola."
            ),
            keyVerse = LocalizedText(
                "«Cuando tú orabas con lágrimas y enterrabas a los muertos, yo presentaba tu oración ante la presencia gloriosa del Santo». (Tob 12, 12)",
                "“When you prayed with tears and buried the dead, I offered your prayer to the Lord.” (Tobit 12:12)",
                "“Quando tu oravas com lágrimas e sepultavas os mortos, eu apresentava a tua oração ao Senhor”. (Tob 12, 12)"
            ),
            keyReference = "Tob 12, 12"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("Judit", "Judith", "Judite"),
            abbreviation = "Jdt",
            chapters = 16,
            doctrinalSignificance = LocalizedText(
                "Heroína que libra a su pueblo cortando la cabeza de Holofernes; prefiguración mariana de la mujer que aplasta la cabeza de la serpiente (Gén 3, 15).",
                "A courageous heroine who delivers Israel by beheading Holofernes; an ancient Marian type crushing the serpent's head.",
                "Heroína da fé que liberta o povo de Deus; figura profética de Nossa Senhora esmagando o dragão infernal."
            ),
            keyVerse = LocalizedText(
                "«¡Bendita seas entre todas las mujeres, hija del Dios Altísimo, y bendito sea el Señor Dios, creador del cielo y de la tierra!». (Jdt 13, 18)",
                "“Blessed are you, daughter, by the Most High God, above all the women on earth; and blessed be the Lord God.” (Judith 13:18)",
                "“Bendita és tu, ó filha, pelo Deus Altíssimo, mais do que todas as mulheres da terra”. (Jdt 13, 18)"
            ),
            keyReference = "Jdt 13, 18"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("1 Macabeos", "1 Maccabees", "1 Macabeus"),
            abbreviation = "1 Mac",
            chapters = 16,
            doctrinalSignificance = LocalizedText(
                "Defensa heroica de la fe y de la Ley ante la apostasía pagana helenista; purificación y dedicación del Santo Templo (fiesta de Hanukkah mencionada en Juan 10, 22).",
                "Historical record of Jewish martyrdom and defense of the Covenant against Antiochus Epiphanes; the Hanukkah rededication attended by Jesus in John 10:22.",
                "História da resistência dos mártires e purificação do Templo de Jerusalém (festa mencionada em João 10, 22)."
            ),
            keyVerse = LocalizedText(
                "«Aunque todas las naciones obedezcan al rey, yo, mis hijos y mis hermanos nos mantendremos fieles a la Alianza de nuestros padres». (1 Mac 2, 19-20)",
                "“Even if all nations obey the king, I and my sons and my brothers will keep the covenant of our ancestors.” (1 Macc 2:19-20)",
                "“Ainda que todas as nações obedeçam ao rei, eu, meus filhos e meus irmãos guardaremos a aliança”. (1 Mac 2, 19-20)"
            ),
            keyReference = "1 Mac 2, 19-20"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("2 Macabeos", "2 Maccabees", "2 Macabeus"),
            abbreviation = "2 Mac",
            chapters = 15,
            doctrinalSignificance = LocalizedText(
                "Fundamento bíblico explícito de la oración por los difuntos y el Purgatorio (cap. 12), la resurrección de la carne, el martirio de los siete hermanos y la intercesión de los santos (Onías y Jeremías en cap. 15).",
                "Definitive Old Testament foundation for prayer for the departed (Purgatory, ch. 12), resurrection of the flesh, and heavenly intercession of the saints (ch. 15).",
                "Fundamento bíblico direto da oração pelos defuntos (Purgatório), da ressurreição dos corpos e da intercessão celeste dos santos."
            ),
            keyVerse = LocalizedText(
                "«Santo y piadoso pensamiento es orar por los difuntos, para que sean liberados de sus pecados». (2 Mac 12, 45)",
                "“It is therefore a holy and wholesome thought to pray for the dead, that they may be loosed from sins.” (2 Macc 12:46)",
                "“É um pensamento santo e salutar rezar pelos mortos, para que sejam libertos de seus pecados”. (2 Mac 12, 45)"
            ),
            keyReference = "2 Mac 12, 45"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("Sabiduría de Salomón", "Wisdom of Solomon", "Sabedoria"),
            abbreviation = "Sab",
            chapters = 19,
            doctrinalSignificance = LocalizedText(
                "Inmortalidad del alma humana, justicia celestial; contiene la asombrosa profecía de la Pasión de Cristo (Sab 2, 12-20) leída cada Viernes Santo.",
                "Immortality of the soul, divine justice, and the stunning messianic prophecy of the Passion and Crucifixion of Christ in chapter 2.",
                "Imortalidade da alma e a profecia da Paixão e Morte de Cristo ultrajado pelos ímpios (cap. 2)."
            ),
            keyVerse = LocalizedText(
                "«Las almas de los justos están en la mano de Dios y no los tocará el tormento... aunque a juicio de los hombres hayan sufrido, su esperanza está llena de inmortalidad». (Sab 3, 1.4)",
                "“The souls of the righteous are in the hand of God, and no torment shall touch them... their hope is full of immortality.” (Wisdom 3:1,4)",
                "“As almas dos justos estão na mão de Deus e nenhum tormento as tocará... sua esperança está repleta de imortalidade”. (Sab 3, 1.4)"
            ),
            keyReference = "Sab 3, 1-4"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("Sirácida (Eclesiástico)", "Sirach (Ecclesiasticus)", "Eclesiástico (Sirácida)"),
            abbreviation = "Sir",
            chapters = 51,
            doctrinalSignificance = LocalizedText(
                "Tratado monumental de sabiduría moral, veneración a los padres, temor de Dios, caridad fraternal y la personificación de la Divina Sabiduría que inspiró la liturgia de la Iglesia.",
                "Comprehensive manual of Catholic moral life, filial devotion, fear of the Lord, friendship, and eternal Wisdom.",
                "Monumental tesouro de sabedoria moral, honra aos pais, virtudes cristãs e a Sabedoria divina."
            ),
            keyVerse = LocalizedText(
                "«El que honra a su padre expía sus pecados, y el que respeta a su madre es como quien acumula tesoros». (Sir 3, 3-4)",
                "“Whoever honors his father atones for sins, and whoever glorifies his mother is like one who lays up treasure.” (Sirach 3:3-4)",
                "“Quem honra seu pai alcança o perdão dos pecados; quem respeita sua mãe é como quem ajunta tesouros”. (Eclo 3, 3-4)"
            ),
            keyReference = "Sir 3, 3-4"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("Baruc (con Carta de Jeremías)", "Baruch", "Baruc"),
            abbreviation = "Bar",
            chapters = 6,
            doctrinalSignificance = LocalizedText(
                "Escrito por el secretario del profeta Jeremías. Profetiza la Encarnación del Verbo viviendo entre los hombres (Bar 3, 38) y el arrepentimiento del Pueblo de Dios.",
                "Written by Jeremiah's secretary. Explicitly prophecies the Incarnation of God walking upon earth among men (Baruch 3:37).",
                "Profetiza a Encarnação do Filho de Deus convivendo na terra com os homens (Bar 3, 38) e a consolação de Jerusalém."
            ),
            keyVerse = LocalizedText(
                "«Después de esto, apareció la Sabiduría en la tierra y conversó con los hombres». (Bar 3, 38)",
                "“Afterward she appeared upon earth, and held converse with men.” (Baruch 3:37)",
                "“Depois disso, apareceu sobre a terra e viveu entre os homens”. (Bar 3, 38)"
            ),
            keyReference = "Bar 3, 38"
        ),
        DeuterocanonicalBookDetail(
            name = LocalizedText("Adiciones a Daniel y Ester", "Additions to Daniel & Esther", "Acréscimos a Daniel e Ester"),
            abbreviation = "Dan/Est",
            chapters = 0,
            doctrinalSignificance = LocalizedText(
                "En Daniel: La historia de Susana y los ancianos, Bel y el Dragón, y el Cántico de los tres jóvenes (Benedicite en el Breviario). En Ester: Oraciones solemnes de Ester y Mardoqueo reconociendo la soberanía de Dios.",
                "In Daniel: Susanna and the elders, Bel and the Dragon, and the Song of the Three Youths. In Esther: Sacred prayers of Mordecai and Esther.",
                "Em Daniel: Susana e os anciãos, o hino dos três jovens na fornalha. Em Ester: As orações de Ester e Mardoqueu."
            ),
            keyVerse = LocalizedText(
                "«¡Bendito eres, Señor, Dios de nuestros padres: alabado y ensalzado por los siglos!». (Dan 3, 52)",
                "“Blessed are you, O Lord, the God of our ancestors, praiseworthy and exalted above all forever!” (Daniel 3:52)",
                "“Bendito sejais, Senhor, Deus de nossos pais, digno de louvor e de glória para sempre!” (Dan 3, 52)"
            ),
            keyReference = "Dan 3, 52"
        )
    )

    val canonHistoricalMilestones: List<CanonHistoricalMilestone> = listOf(
        CanonHistoricalMilestone(
            year = "250 a.C.",
            councilOrEvent = LocalizedText("Traducción de los Setenta (Septuaginta / LXX)", "Septuagint Translation (LXX)", "Tradução dos Setenta (Septuaginta)"),
            description = LocalizedText(
                "En Alejandría, 72 sabios judíos traducen las Sagradas Escrituras al griego para la diáspora. Incluye los 46 libros del Antiguo Testamento. Esta fue la Biblia citada por los Apóstoles y evangelistas en el Nuevo Testamento (más del 80% de las citas del AT provienen de la Septuaginta).",
                "Jewish scholars translated the Hebrew and Aramaic Scriptures into Greek in Alexandria. It contained all 46 OT books and was the standard Bible quoted by Jesus' Apostles and the Early Church.",
                "Em Alexandria, as Escrituras são traduzidas ao grego incluindo os 46 livros. Esta foi a Bíblia usada pelos Apóstolos e citada no Novo Testamento."
            )
        ),
        CanonHistoricalMilestone(
            year = "382 d.C.",
            councilOrEvent = LocalizedText("Concilio de Roma (Papa San Dámaso I)", "Council of Rome (Pope St. Damasus I)", "Concílio de Roma (Papa São Dâmaso I)"),
            description = LocalizedText(
                "El Papa San Dámaso I promulga el 'Decretum Damasi' fijando formalmente el canon definitivo de 73 libros (46 AT + 27 NT) y encomienda a San Jerónimo la traducción de la Vulgata Latina oficial.",
                "Pope Damasus I promulgated the definitive 73-book canon (46 OT + 27 NT) and commissioned St. Jerome to translate the Latin Vulgate.",
                "O Papa São Dâmaso I publica a lista definitiva dos 73 livros e encarrega São Jerônimo de traduzir a Vulgata Latina."
            )
        ),
        CanonHistoricalMilestone(
            year = "393 & 397 d.C.",
            councilOrEvent = LocalizedText("Concilios de Hipona y Cartago (San Agustín)", "Councils of Hippo & Carthage (St. Augustine)", "Concílios de Hipona e Cartago (Santo Agostinho)"),
            description = LocalizedText(
                "Bajo el liderazgo teológico de San Agustín, los concilios africanos confirman unánimemente la lista exacta de los 73 libros inspirados, confirmada por el Papa San Inocencio I en el 405 d.C.",
                "Guided by St. Augustine, the North African councils affirmed the 73 books, later confirmed by Pope Innocent I in 405 AD.",
                "Guiados por Santo Agostinho, confirmam unanimemente os 73 livros sagrados, confirmados pelo Papa Inocêncio I."
            )
        ),
        CanonHistoricalMilestone(
            year = "1442 d.C.",
            councilOrEvent = LocalizedText("Concilio Ecuménico de Florencia", "Council of Florence", "Concílio de Florença"),
            description = LocalizedText(
                "En la bula 'Cantate Domino' (Papa Eugenio IV), la Iglesia proclama solemnemente el canon de los 73 libros ante las Iglesias Orientales.",
                "Pope Eugene IV solemnly reaffirmed the 73 books in the papal bull 'Cantate Domino'.",
                "O Papa Eugênio IV reafirma solenemente a lista canônica dos 73 livros sagrados."
            )
        ),
        CanonHistoricalMilestone(
            year = "1534 d.C.",
            councilOrEvent = LocalizedText("Reforma Protestante (Martín Lutero)", "Protestant Reformation (Martin Luther)", "Reforma Protestante (Martinho Lutero)"),
            description = LocalizedText(
                "Martín Lutero decide relegar los 7 libros deuterocanónicos a un apéndice calificándolos de 'apócrifos' porque contradecían sus tesis personales (especialmente 2 Macabeos 12 sobre el Purgatorio y las obras en Santiago). En siglos posteriores, las sociedades bíblicas protestantes los suprimieron por completo, reduciendo la Biblia a 66 libros.",
                "Luther removed the 7 deuterocanonical books from the OT canon due to doctrinal conflicts (e.g., purgatory in 2 Maccabees). Later Protestant societies omitted them entirely, producing a 66-book volume.",
                "Lutero retirou os 7 livros deuterocanônicos por contrariarem suas teses (como o Purgatório). Mais tarde, bíblias protestantes os removeram por completo, restando 66 livros."
            )
        ),
        CanonHistoricalMilestone(
            year = "1546 d.C.",
            councilOrEvent = LocalizedText("Concilio Ecuménico de Trento (Sesión IV)", "Council of Trent (Fourth Session)", "Concílio de Trento (Sessão IV)"),
            description = LocalizedText(
                "Frente a la mutilación de las Escrituras, la Iglesia Católica define dogmáticamente de forma infalible el Canon Sagrado de los 73 libros bajo anatema, preservando intacto el depósito apostólico.",
                "In response to Protestant omissions, the Catholic Church dogmatically defined the 73-book canon with solemn anathema, protecting apostolic integrity.",
                "Em resposta às exclusões, o Concílio define dogmaticamente os 73 livros sob anátema, garantindo a integridade da Palavra de Deus."
            )
        )
    )

    val bibleVersions: List<BibleVersionInfo> = listOf(
        BibleVersionInfo(
            id = "jerusalem",
            name = "Biblia de Jerusalén / Jerusalem Bible",
            abbreviation = "BJ",
            language = "Multilingual (FR, ES, EN, PT)",
            isCatholicCanon = true,
            description = LocalizedText(
                "La edición académica y litúrgica de mayor prestigio en el orbe católico, traducida por la Escuela Bíblica de Jerusalén.",
                "The pinnacle of Catholic biblical scholarship, prized worldwide for rich textual fidelity and theological footnotes.",
                "A mais respeitada tradução católica para estudo bíblico e liturgia, fruto da Escola Bíblica e Arqueológica de Jerusalém."
            ),
            canonicalDifferenceNote = LocalizedText(
                "Contiene los 73 libros canónicos completos de la Iglesia Católica.",
                "Contains the full 73 canonical books approved at Hippo (393), Carthage (397), and Trent (1546).",
                "Contém os 73 livros completos aprovados pelos Concílios da Igreja Católica."
            )
        ),
        BibleVersionInfo(
            id = "latinoamericana",
            name = "Biblia Latinoamericana",
            abbreviation = "BLA",
            language = "Español / Português",
            isCatholicCanon = true,
            description = LocalizedText(
                "Traducción pastoral con vocabulario accesible y notas explicativas profundas para la vida diaria del creyente.",
                "Pastoral edition celebrated for accessible vocabulary and vivid practical catechetical annotations.",
                "Edição pastoral amplamente utilizada na América Latina por sua linguagem acessível e notas de reflexão."
            ),
            canonicalDifferenceNote = LocalizedText(
                "Contiene los 73 libros canónicos católicos íntegros.",
                "Includes all 73 Catholic canonical books in proper liturgical sequence.",
                "Possui todos os 73 livros sagrados da tradição católica."
            )
        ),
        BibleVersionInfo(
            id = "douay_rheims",
            name = "Douay-Rheims Bible / Nova Vulgata",
            abbreviation = "DRB",
            language = "English / Latin",
            isCatholicCanon = true,
            description = LocalizedText(
                "Traducción tradicional histórica católica de la Vulgata Latina de San Jerónimo declarada auténtica por el Concilio de Trento.",
                "The venerable traditional Catholic translation of the Latin Vulgate, faithful to St. Jerome's fourth-century rendering.",
                "Tradução tradicional a partir da Vulgata Latina de São Jerônimo, de grande valor litúrgico e teológico."
            ),
            canonicalDifferenceNote = LocalizedText(
                "Conserva inalterados los 73 libros canónicos de la tradición apostólica.",
                "Maintains all 73 books without any omission of the deuterocanonicals.",
                "Conserva inalterados todos os 73 livros canônicos bíblicos."
            )
        ),
        BibleVersionInfo(
            id = "reina_valera",
            name = "Reina-Valera / King James / João Ferreira",
            abbreviation = "PROT",
            language = "ES / EN / PT",
            isCatholicCanon = false,
            description = LocalizedText(
                "Versiones bíblicas surgidas a partir de la Reforma protestante del siglo XVI.",
                "Reformation-era translations used primarily in Protestant and Evangelical traditions.",
                "Traduções históricas de tradição protestante surgidas após o século XVI."
            ),
            canonicalDifferenceNote = LocalizedText(
                "AVISO CANÓNICO: Estas ediciones contienen solo 66 libros, habiendo excluido 7 libros inspirados del Antiguo Testamento (Tobías, Judit, 1 y 2 Macabeos, Sabiduría, Sirácida, Baruc y adiciones de Ester y Daniel).",
                "CANONICAL NOTICE: These editions omit 7 inspired Old Testament books (Tobit, Judith, 1 & 2 Maccabees, Wisdom, Sirach, Baruch, and Catholic additions to Esther and Daniel).",
                "AVISO CANÔNICO: Estas edições possuem apenas 66 livros, tendo excluído os 7 livros deuterocanônicos conservados pela Igreja Católica."
            )
        )
    )

    val todayMassReading = DailyMassReading(
        dateDisplay = "Domingo Ordinario",
        liturgicalSeason = LocalizedText("Tiempo Ordinario", "Ordinary Time", "Tempo Comum"),
        liturgicalColorHex = 0xFF2C6B3E, // Liturgical Green
        feastTitle = LocalizedText("Celebración del Misterio Pascual", "Sunday of the Divine Mercy & Word", "Domingo da Palavra e da Misericórdia"),
        firstReadingRef = "Isaías 53, 10-11",
        firstReadingText = LocalizedText(
            "El Señor quiso triturarlo con el sufrimiento; si entrega su vida en expiación, verá su descendencia, prolongará sus días, y por su mano prosperará la voluntad del Señor. Gracias al sufrimiento de su alma verá la luz y quedará saciado.",
            "The Lord was pleased to crush him in infirmity. If he gives his life as an offering for sin, he shall see his descendants in a long life, and the will of the Lord shall be accomplished through him. Because of his affliction he shall see the light in fullness of days.",
            "O Senhor quis feri-lo com o sofrimento; se ele oferecer sua vida em expiação, verá uma descendência duradoura e a vontade do Senhor se cumprirá por suas mãos."
        ),
        responsorialPsalmRef = "Salmo 32 (33)",
        responsorialPsalmText = LocalizedText(
            "R/. Que tu misericordia, Señor, venga sobre nosotros, como lo esperamos de ti.\n\nLa palabra del Señor es sincera, y todas sus acciones son leales; él ama la justicia y el derecho, y su misericordia llena la tierra.",
            "R/. Lord, let your mercy be on us, as we place our trust in you.\n\nUpright is the word of the Lord, and all his works are trustworthy. He loves justice and right; of the kindness of the Lord the earth is full.",
            "R/. Sobre nós venha, Senhor, a vossa graça, da mesma forma que em vós esperamos.\n\nPois reta é a palavra do Senhor e tudo o que Ele faz é digno de fé. Ele ama a justiça e o direito; da misericórdia do Senhor a terra está cheia."
        ),
        secondReadingRef = "Hebreos 4, 14-16",
        secondReadingText = LocalizedText(
            "Hermanos: Ya que tenemos un sumo sacerdote eminente, que ha atravesado los cielos, Jesús, Hijo de Dios, mantengamos firme nuestra fe. Pues no tenemos un sumo sacerdote incapaz de compadecerse de nuestras debilidades, sino que ha sido probado en todo igual que nosotros, excepto en el pecado. Acerquémonos, por tanto, confiadamente al trono de la gracia.",
            "Brothers and sisters: Since we have a great high priest who has passed through the heavens, Jesus, the Son of God, let us hold fast to our confession. For we do not have a high priest who is unable to sympathize with our weaknesses, but one who has similarly been tested in every way, yet without sin. So let us confidently approach the throne of grace.",
            "Irmãos: Visto que temos um grande sumo sacerdote que atravessou os céus, Jesus, o Filho de Deus, guardemos firmemente a nossa fé. Pois não temos um sumo sacerdote incapaz de se compadecer de nossas fraquezas, mas um que foi tentado em tudo como nós, com exceção do pecado."
        ),
        gospelRef = "San Marcos 10, 42-45",
        gospelText = LocalizedText(
            "En aquel tiempo, Jesús reunió a los doce y les dijo: «Saben que los que son tenidos como jefes de las naciones las gobiernan como señores absolutos y los grandes las oprimen con su poder. Pero entre ustedes no debe ser así: al contrario, el que quiera llegar a ser grande entre ustedes, que sea su servidor, y el que quiera ser el primero, que sea el esclavo de todos. Porque el Hijo del Hombre no ha venido a ser servido, sino a servir y a dar su vida en rescate por muchos».",
            "Jesus summoned the Twelve and said to them: 'You know that those who are recognized as rulers over the Gentiles lord it over them, and their great ones make their authority over them felt. But it shall not be so among you. Rather, whoever wishes to be great among you will be your servant; whoever wishes to be first among you will be the slave of all. For the Son of Man did not come to be served but to serve and to give his life as a ransom for many.'",
            "Naquele tempo, Jesus chamou os Doze e disse-lhes: 'Sabeis que os chefes das nações as dominam e os grandes as tiranizam. Entre vós não deve ser assim: quem quiser ser o maior, seja o vosso servidor; e quem quiser ser o primeiro, seja o escravo de todos. Porque o Filho do Homem não veio para ser servido, mas para servir e dar a sua vida em resgate por muitos'."
        ),
        homilyReflection = LocalizedText(
            "Cristo invierte la lógica del poder humano. La verdadera grandeza en el Reino de Dios reside en el servicio humilde y en la entrega de amor hacia el prójimo.",
            "Christ transforms worldly power through divine charity. In the Kingdom of God, royal authority is manifested in humble, sacrificial self-giving.",
            "Cristo transforma o poder temporal em serviço de amor. A verdadeira dignidade dos discípulos está em servir com coração humilde."
        ),
        saintOfFeast = LocalizedText("San Juan Pablo II / Beatos Mártires", "Pope St. John Paul II", "São João Paulo II")
    )
}
