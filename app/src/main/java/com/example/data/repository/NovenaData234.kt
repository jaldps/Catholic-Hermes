package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData234 {
    val novena: Novena = Novena(
        id = """novena-de-los-siete-dolores-y-gozos-de-san-jose""",
        title = LocalizedText("""Novena de los Siete Dolores y Gozos de San José""", """Seven Sorrows and Joys of St Joseph Novena""", """Novena das Sete Dores e Alegrias de São José"""),
        patron = LocalizedText("""Santo patrón de la devoción a San José, a la Virgen y a Jesús, y la superación de toda ansiedad y todo temor""", """Patron saint of devotion to St. Joseph, our Lady, and Jesus and to overcome all anxiety and fear""", """Novena das Sete Dores e Alegrias de São José"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta antigua devoción que medita los sufrimientos de San José y los gozos correspondientes de su vida. Reza esta novena para crecer en la devoción a San José, a la Virgen y a Jesús, y para vencer toda ansiedad y todo temor.""", """The seven sorrows and joys of St. Joseph are an old devotion that meditates on the sufferings of St. Joseph and the corresponding joys of his life. Pray this novena to grow in devotion to St. Joseph, our Lady, and Jesus and to overcome all anxiety and fear.""", """Santo padroeiro de devoção a São José, a Nossa Senhora e a Jesus, e para vencer toda ansiedade e medo"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 2,
                theme = LocalizedText("""Día 2""", """Day 2""", """Dia 2"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 3,
                theme = LocalizedText("""Día 3""", """Day 3""", """Dia 3"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 4,
                theme = LocalizedText("""Día 4""", """Day 4""", """Dia 4"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 5,
                theme = LocalizedText("""Día 5""", """Day 5""", """Dia 5"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 6,
                theme = LocalizedText("""Día 6""", """Day 6""", """Dia 6"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 7,
                theme = LocalizedText("""Día 7""", """Day 7""", """Dia 7"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 8,
                theme = LocalizedText("""Día 8""", """Day 8""", """Dia 8"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 9,
                theme = LocalizedText("""Día 9""", """Day 9""", """Dia 9"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Primer dolor y gozo

San José, casto esposo de la Santa Madre de Dios, por el dolor con que fue traspasado tu corazón al pensar en una cruel separación de María, y por el profundo gozo que sentiste cuando el ángel te reveló el inefable misterio de la Encarnación, alcánzanos de Jesús y de María la gracia de superar toda ansiedad. Obtén para nosotros del adorable Corazón de Jesús la paz indecible de la que él es la Fuente eterna.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Segundo dolor y gozo

San José, padre adoptivo de Jesús, por el amargo dolor que experimentó tu corazón al ver al Niño Jesús recostado en un pesebre, y por el gozo que sentiste al ver a los Magos reconocerlo y adorarlo como su Dios, alcánzanos con tus oraciones que nuestro corazón, purificado por tu protección, llegue a ser un pesebre vivo donde el Salvador del mundo reciba y bendiga nuestro homenaje.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Tercer dolor y gozo

San José, por el dolor con que fue traspasado tu corazón al ver la Sangre que brotó del Niño Jesús en la Circuncisión, y por el gozo que inundó tu alma por el privilegio de imponerle el sagrado y misterioso Nombre de Jesús, alcánzanos que los méritos de esta preciosa Sangre se apliquen a nuestras almas y que el divino Nombre de Jesús quede grabado para siempre en nuestros corazones.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Cuarto dolor y gozo

San José, por el dolor que sentiste cuando el Señor declaró que el alma de María sería traspasada por una espada de dolor, y por tu gozo cuando el santo Simeón añadió que el divino Niño sería resurrección de muchos, alcánzanos la gracia de compadecernos de los dolores de María y de participar en la salvación que Jesús trajo a la tierra.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Quinto dolor y gozo

San José, por tu dolor cuando se te ordenó huir a Egipto, y por tu gozo al ver derribados los ídolos a la llegada del Dios vivo, concédenos que ningún ídolo de afecto terreno ocupe ya nuestro corazón, sino que, entregados por completo como tú al servicio de Jesús y de María, vivamos y muramos felizmente solo por ellos.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Sexto dolor y gozo

San José, por el dolor de tu corazón causado por el temor al tirano Arquelao, y por el gozo de compartir la compañía de Jesús y de María en Nazaret, alcánzanos que, libres de todo temor, gocemos de la paz de una buena conciencia y vivamos seguros, en unión con Jesús y María, experimentando el efecto de tu saludable asistencia en la hora de nuestra muerte.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Séptimo dolor y gozo

San José, por el amargo dolor con que la pérdida del Niño Jesús oprimió tu corazón, y por el santo gozo que inundó tu alma al recobrar a tu Tesoro al entrar en el Templo, te suplicamos que no permitas que perdamos a nuestro Salvador Jesús por el pecado. Pero si nos sobreviniera esta desgracia, concédenos compartir tu afán por buscarlo y alcánzanos la gracia de volver a encontrarlo, dispuesto a mostrarnos su gran misericordia, especialmente en la hora de la muerte, para que pasemos de esta vida a gozar de su presencia en el cielo y cantar allí contigo sus divinas misericordias para siempre.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oremos

Oh Dios, que con inefable providencia te dignaste elegir al bienaventurado José por esposo de tu santísima Madre, concédenos que merezcamos tener por intercesor en el cielo a quien en la tierra veneramos como nuestro santo protector. Tú que vives y reinas por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

First Sorrow and Joy

St. Joseph, Chaste Spouse of the Holy Mother of God, by the sorrow with which thy heart was pierced at the thought of a cruel separation from Mary, and by the deep joy that thou didst feel when the angel revealed to thee the ineffable mystery of the Incarnation, obtain for us from Jesus and Mary, the grace of surmounting all anxiety. Win for us from the Adorable Heart of Jesus the unspeakable peace of which He is the Eternal Source.

Our Father

Hail Mary

Glory Be

Second Sorrow and Joy

St. Joseph, Foster-Father of Jesus, by the bitter sorrow which thy heart experienced in seeing the Child Jesus lying in a manger, and by the joy which thou didst feel in seeing the Wise men recognize and adore Him as their God, obtain by thy prayers that our heart, purified by thy protection, may become a living crib, where the Savior of the world may receive and bless our homage.

Our Father

Hail Mary

Glory Be

Third Sorrow and Joy

St. Joseph, by the sorrow with which thy heart was pierced at the sight of the Blood which flowed from the Infant Jesus in the Circumcision, and by the joy that inundated thy soul at thy privilege of imposing the sacred and mysterious Name of Jesus, obtain for us that the merits of this Precious Blood may be applied to our souls, and that the Divine Name of Jesus may be engraved forever in our hearts.

Our Father

Hail Mary

Glory Be

Fourth Sorrow and Joy

St. Joseph, by the sorrow when the Lord declared that the soul of Mary would be pierced with a sword of sorrow, and by thy joy when holy Simeon added that the Divine Infant was to be the resurrection of many, obtain for us the grace to have compassion on the sorrows of Mary, and share in the salvation which Jesus brought to the earth.

Our Father

Hail Mary

Glory Be

Pray all seven sorrows and joys of St. Joseph.

Fifth Sorrow and Joy

St. Joseph, by thy sorrow when told to fly into Egypt, and by thy joy in seeing the idols overthrown at the arrival of the living God, grant that no idol of earthly affection may any longer occupy our hearts, but being like thee entirely devoted to the service of Jesus and Mary, we may live and happily die for them alone.

Our Father

Hail Mary

Glory Be

Sixth Sorrow and Joy

St. Joseph, by the sorrow of thy heart caused by the fear of the tyrant Archelaus and by the joy in sharing the company of Jesus and Mary at Nazareth, obtain for us, that disengaged from all fear, we may enjoy the peace of a good conscience and may live in security, in union with Jesus and Mary, experiencing the effect of thy salutary assistance at the hour of our death.

Our Father

Hail Mary

Glory Be

Seventh Sorrow and Joy

St. Joseph, by the bitter sorrow with which the loss of the Child Jesus crushed thy heart, and by the holy joy which inundated thy soul in recovering thy Treasure on entering the Temple, we supplicate thee not to permit us to lose our Saviour Jesus by sin. Yet, should this misfortune befall us, grant that we may share thy eagerness in seeking Him, and obtain for us the grace to find Him again, ready to show us His great mercy, especially at the hour of death; so that we may pass from this life to enjoy His presence in heaven, there to sing with thee His divine mercies forever.

Our Father

Hail Mary

Glory Be

Let Us Pray

O God, Who in Thine ineffable Providence has vouchsafed to choose Blessed Joseph to be the Spouse of Thy most holy Mother, grant, we beseech Thee, that we may deserve to have him for our intercessor in heaven whom on earth we venerate as our holy protector: Who lives and reigns forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Primeira Dor e Alegria

São José, castíssimo Esposo da Santa Mãe de Deus, pela dor com que o vosso coração foi traspassado ao pensar numa cruel separação de Maria, e pela profunda alegria que sentistes quando o anjo vos revelou o inefável mistério da Encarnação, alcançai-nos de Jesus e Maria a graça de superar toda ansiedade. Obtende-nos do Adorável Coração de Jesus a inefável paz da qual Ele é a Fonte Eterna.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Segunda Dor e Alegria

São José, Pai adotivo de Jesus, pela amarga dor que o vosso coração experimentou ao ver o Menino Jesus deitado numa manjedoura, e pela alegria que sentistes ao ver os Magos reconhecê-Lo e adorá-Lo como seu Deus, alcançai-nos por vossas orações que o nosso coração, purificado por vossa proteção, se torne um presépio vivo, onde o Salvador do mundo possa receber e abençoar as nossas homenagens.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Terceira Dor e Alegria

São José, pela dor com que o vosso coração foi traspassado à vista do Sangue que correu do Menino Jesus na Circuncisão, e pela alegria que inundou a vossa alma pelo privilégio de impor o sagrado e misterioso Nome de Jesus, alcançai-nos que os méritos deste Precioso Sangue sejam aplicados às nossas almas e que o Divino Nome de Jesus fique gravado para sempre em nossos corações.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quarta Dor e Alegria

São José, pela dor quando o Senhor declarou que a alma de Maria seria traspassada por uma espada de dor, e pela vossa alegria quando o santo Simeão acrescentou que o Divino Menino seria a ressurreição de muitos, alcançai-nos a graça de nos compadecermos das dores de Maria e de participarmos da salvação que Jesus trouxe à terra.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Quinta Dor e Alegria

São José, pela vossa dor quando vos foi ordenado fugir para o Egito, e pela vossa alegria ao ver os ídolos derrubados à chegada do Deus vivo, concedei que nenhum ídolo de afeição terrena ocupe mais os nossos corações, mas que, inteiramente dedicados como vós ao serviço de Jesus e Maria, vivamos e morramos felizes somente para eles.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sexta Dor e Alegria

São José, pela dor do vosso coração causada pelo medo do tirano Arquelau, e pela alegria de partilhar a companhia de Jesus e Maria em Nazaré, alcançai-nos que, livres de todo temor, gozemos da paz de uma boa consciência e vivamos em segurança, em união com Jesus e Maria, experimentando o efeito da vossa salutar assistência na hora da nossa morte.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Sétima Dor e Alegria

São José, pela amarga dor com que a perda do Menino Jesus esmagou o vosso coração, e pela santa alegria que inundou a vossa alma ao reencontrar o vosso Tesouro ao entrar no Templo, nós vos suplicamos que não permitais que percamos o nosso Salvador Jesus pelo pecado. Mas, se essa desgraça nos acontecer, concedei que partilhemos o vosso empenho em procurá-Lo e alcançai-nos a graça de encontrá-Lo novamente, pronto a mostrar-nos a Sua grande misericórdia, especialmente na hora da morte, para que passemos desta vida a gozar da Sua presença no céu, para ali cantar convosco as Suas divinas misericórdias para sempre.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Oremos

Ó Deus, que por inefável providência Vos dignastes escolher o bem-aventurado José para Esposo da Vossa Santíssima Mãe, concedei-nos, Vos pedimos, que mereçamos ter como intercessor no céu aquele que na terra veneramos como nosso santo protetor. Vós que viveis e reinais para sempre. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/seven-sorrows-joys-st-joseph-novena.png""",
        tags = listOf("health", "peace")
    )
}