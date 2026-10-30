package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData091 {
    val novena: Novena = Novena(
        id = """novena-a-san-jose-obrero""",
        title = LocalizedText("""Novena a San José Obrero""", """St Joseph the Worker Novena""", """Novena a São José Operário"""),
        patron = LocalizedText("""Santo patrón de los desempleados, encontrar trabajo, el subempleo, el trabajo""", """Patron saint of the unemployed, finding a job, underemployment, labor""", """Novena a São José Operário"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""San José representa el verdadero modelo de trabajo para los católicos y para todas las personas, pues tuvo el supremo honor de trabajar con Jesucristo en el taller de carpintería de Nazaret. Reza esta novena para encontrar trabajo y pedir ayuda en tu trabajo.""", """St Joseph represents the true model of work for Catholics and all people, since he had the supreme honor of working with Jesus Christ in the carpenter shop at Nazareth. Pray this novena for finding a job and for help with your work.""", """Santo padroeiro de os desempregados, os que procuram emprego, o subemprego, o trabalho"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

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

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Querido san José, doy gracias a Dios por tu privilegio de haber podido trabajar codo con codo con Jesús en el taller de carpintería de Nazaret.

Como prenda de tu propia gratitud a Dios, obtenme la gracia de respetar la dignidad del trabajo y de estar siempre contento con la posición en la vida, por humilde que sea, en la que la Divina Providencia quiera ponerme.

Enséñame a trabajar para Dios y con Dios, con espíritu de humildad y de oración, como tú lo hiciste, para que ofrezca mi esfuerzo unido al sacrificio de Jesús en la Santa Misa en reparación de mis pecados y gane abundantes méritos para el cielo.

San José, yo, tu hijo indigno, te saludo. Tú eres el fiel protector e intercesor de todos los que te aman y veneran.

Tú sabes que tengo especial confianza en ti y que, después de nuestro Señor y de Nuestra Señora, pongo en ti toda mi esperanza de salvación, porque eres especialmente poderoso ante Dios y nunca abandonarás a tus fieles servidores.

Por eso te invoco humildemente y me encomiendo a tu intercesión, con todos mis seres queridos y todo lo que me pertenece.

Te suplico, por tu amor a nuestro Señor y a Nuestra Señora, que no me abandones durante la vida y que me asistas en la hora de mi muerte. Glorioso san José, esposo de la Virgen Inmaculada, obtenme un alma pura, humilde y caritativa y una perfecta conformidad con la Voluntad divina. Sé mi guía, mi padre y mi modelo durante la vida, para que merezca morir como tú, en los brazos de nuestro Señor y de Nuestra Señora.

Amable san José, fiel seguidor de Jesucristo, elevo hacia ti mi corazón para implorar tu poderosa intercesión y obtener del Divino Corazón de Jesús todas las gracias necesarias para mi bien espiritual y temporal, en particular la gracia de una buena muerte y la gracia especial que ahora imploro:

(menciona tu petición aquí…)

Custodio del Verbo Encarnado, confío en que tus oraciones en mi favor serán escuchadas benignamente ante el trono de Dios. Amén.

Acordaos, oh castísimo esposo de la Virgen María, mi amable protector, san José, que jamás se ha oído decir que nadie haya invocado tu protección o pedido tu auxilio sin haber sido consolado. Animado con esta confianza, vengo a ti y a ti me encomiendo humildemente. No desprecies mis súplicas, oh padre adoptivo del Redentor, antes bien acógelas benignamente. Amén.

Oración del papa León XIII a san José (de la encíclica Quamquam Pluries)

A ti, bienaventurado san José, acudimos en nuestra tribulación, y después de implorar el auxilio de tu santísima Esposa, solicitamos también confiadamente tu patrocinio.

Por aquella caridad que te tuvo unido con la Inmaculada Virgen Madre de Dios, y por el paterno amor con que abrazaste al Niño Jesús, te suplicamos humildemente que vuelvas benigno los ojos a la heredad que con Su Sangre adquirió Jesucristo, y con tu poder y auxilio socorras nuestras necesidades.

Protege, oh providentísimo custodio de la Sagrada Familia, a la escogida descendencia de Jesucristo; aparta de nosotros, oh padre amantísimo, toda mancha de error y de corrupción; asístenos propicio desde el cielo, oh fortísimo libertador nuestro, en esta lucha contra el poder de las tinieblas.

Y así como en otro tiempo libraste al Niño Jesús de inminente peligro de la vida, así ahora defiende a la santa Iglesia de Dios de las asechanzas de sus enemigos y de toda adversidad. Protégenos siempre a cada uno de nosotros con tu patrocinio, para que, a ejemplo tuyo y sostenidos por tu auxilio, podamos vivir y morir santamente y alcanzar en el cielo la eterna bienaventuranza. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Dear Saint Joseph, I thank God for thy privilege of being able to work side-by-side with Jesus in the carpenter shop of Nazareth.

As a token of thy own gratitude to God, obtain for me the grace to respect the dignity of labor and ever to be content with the position in life, however lowly, in which it may please Divine Providence to place me.

Teach me to work for God and with God in the spirit of humility and prayer, as thou did, so that I may offer my toil in union with the sacrifice of Jesus in the Holy Mass as a reparation for my sins, and gain rich merit for heaven.

St Joseph, I, thy unworthy child, greet thee. Thou art the faithful protector and intercessor of all who love and venerate thee.

Thou knowest that I have special confidence in thee and that, after Our Lord and Our Lady, I place all my hope of salvation in thee, for thou art especially powerful with God and will never abandon thy faithful servants.

Therefore I humbly invoke thee and commend myself, with all who are dear to me and all that belongs to me, to thy intercession.

I beg thee, by thy love for Our Lord and Our Lady, not to abandon me during life and to assist me at the hour of my death. Glorious Saint Joseph, spouse of the Immaculate Virgin, obtain for me a pure, humble, charitable mind and perfect resignation to the divine Will. Be my guide, my father and my model through life that I may merit to die as Thou didst in the arms of Our Lord and Our Lady.

Loving Saint Joseph, faithful follower of Jesus Christ, I raise my heart to thee to implore thy powerful intercession in obtaining from the Divine Heart of Jesus all the graces necessary for my spiritual and temporal welfare, particularly the grace of a happy death and the special grace I now implore:

(mention request here…)

Guardian of the Word Incarnate, I feel confident that thy prayers on my behalf will be graciously heard before the throne of God. Amen.

Remember, most pure spouse of Mary, ever Virgin, my loving protector, Saint Joseph, that no one ever had recourse to thy protection or asked for thy aid without obtaining relief. Confiding, therefore, in thy goodness, I come before thee and humbly implore thee. Despise not my petitions, foster-father of the Redeemer, but graciously receive them. Amen.

Pope Leo XIII’s Prayer to St Joseph (from Quamquam Pluries)

To thee, O blessed Joseph, we have recourse in our affliction, and having implored the help of thy thrice holy Spouse, we now, with hearts filled with confidence, earnestly beg thee also to take us under thy protection.

By that charity wherewith thou wast united to the Immaculate Virgin Mother of God, and by that fatherly love with which thou didst cherish the Child Jesus, we beseech thee and we humbly pray that thou wilt look down with gracious eye upon that inheritance which Jesus Christ purchased by His blood, and wilt succor us in our need by thy power and strength.

Defend, O most watchful guardian of the Holy Family, the chosen off-spring of Jesus Christ. Keep from us, O most loving Father, all blight of error and corruption. Aid us from on high, most valiant defender, in this conflict with the powers of darkness.

And even as of old thou didst rescue the Child Jesus from the peril of His life, so now defend God’s Holy Church from the snares of the enemy and from all adversity. Shield us ever under thy patronage, that, following thine example and strengthened by thy help, we may live a holy life, die a happy death, and attain to everlasting bliss in Heaven. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Querido São José, agradeço a Deus o vosso privilégio de poder trabalhar lado a lado com Jesus na carpintaria de Nazaré.

Como sinal da vossa própria gratidão a Deus, alcançai-me a graça de respeitar a dignidade do trabalho e de estar sempre contente com a posição na vida, por mais humilde que seja, em que aprouver à Divina Providência colocar-me.

Ensinai-me a trabalhar para Deus e com Deus, no espírito de humildade e de oração, como vós fizestes, para que eu ofereça o meu esforço em união com o sacrifício de Jesus na Santa Missa, em reparação dos meus pecados, e alcance ricos méritos para o céu.

São José, eu, vosso filho indigno, vos saúdo. Vós sois o fiel protetor e intercessor de todos os que vos amam e veneram.

Sabeis que tenho especial confiança em vós e que, depois de Nosso Senhor e de Nossa Senhora, ponho em vós toda a minha esperança de salvação, pois sois especialmente poderoso junto de Deus e nunca abandonareis os vossos servos fiéis.

Por isso vos invoco humildemente e me recomendo à vossa intercessão, com todos os que me são queridos e tudo o que me pertence.

Suplico-vos, pelo vosso amor a Nosso Senhor e a Nossa Senhora, que não me abandoneis durante a vida e me assistais na hora da minha morte. Glorioso São José, esposo da Virgem Imaculada, alcançai-me uma alma pura, humilde e caridosa e uma perfeita conformidade com a vontade divina. Sede o meu guia, o meu pai e o meu modelo durante a vida, para que eu mereça morrer como vós morrestes, nos braços de Nosso Senhor e de Nossa Senhora.

Amável São José, fiel seguidor de Jesus Cristo, elevo a vós o meu coração para implorar a vossa poderosa intercessão, a fim de alcançar do Divino Coração de Jesus todas as graças necessárias ao meu bem espiritual e temporal, particularmente a graça de uma boa morte e a graça especial que agora imploro:

(mencione o pedido aqui…)

Guardião do Verbo Encarnado, tenho confiança de que as vossas orações por mim serão benignamente atendidas diante do trono de Deus. Amém.

Lembrai-vos, ó puríssimo esposo de Maria sempre Virgem, meu amoroso protetor São José, que jamais se ouviu dizer que alguém tenha recorrido à vossa proteção ou pedido o vosso auxílio sem ter sido consolado. Com esta confiança, venho à vossa presença e humildemente vos suplico. Não desprezeis as minhas súplicas, ó pai adotivo do Redentor, mas acolhei-as com bondade. Amém.

Oração do Papa Leão XIII a São José (da encíclica Quamquam Pluries)

A vós, São José, recorremos na nossa tribulação e, depois de ter implorado o auxílio da vossa santíssima Esposa, cheios de confiança solicitamos também o vosso patrocínio.

Por esse laço sagrado de caridade que vos uniu à Virgem Imaculada, Mãe de Deus, e pelo amor paternal que tivestes ao Menino Jesus, humildemente vos suplicamos que lanceis um olhar benigno sobre a herança que Jesus Cristo conquistou com o Seu sangue e nos socorrais nas nossas necessidades com o vosso auxílio e poder.

Protegei, ó guarda providente da Divina Família, a raça eleita de Jesus Cristo. Afastai para longe de nós, ó pai amantíssimo, a peste do erro e do vício. Assisti-nos do alto do céu, ó nosso fortíssimo sustentáculo, na luta contra o poder das trevas.

E, assim como outrora salvastes da morte a vida ameaçada do Menino Jesus, assim também defendei agora a santa Igreja de Deus das ciladas dos seus inimigos e de toda adversidade. Amparai a cada um de nós com o vosso constante patrocínio, a fim de que, a vosso exemplo e sustentados pelo vosso auxílio, possamos viver virtuosamente, morrer piedosamente e alcançar no céu a eterna bem-aventurança. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-joseph-the-worker-novena.png""",
        tags = listOf("love", "money", "health", "family")
    )
}