package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData192 {
    val novena: Novena = Novena(
        id = """novena-a-santa-teresa-de-avila""",
        title = LocalizedText("""Novena a Santa Teresa de Ávila""", """St. Teresa of Avila Novena""", """Novena a Santa Teresa de Ávila"""),
        patron = LocalizedText("""Santo patrón de personas con dolores de cabeza, escritores católicos españoles, España, personas enfermas, personas en órdenes religiosas, personas ridiculizadas por su piedad, encajeras""", """Patron saint of Spain, sick people, people in religious orders, chess, people ridiculed for their piety, lacemakers, Požega, Croatia, Talisay City, Cebu, Philippines, Malalag, Davao del Sur, Philippines""", """Novena a Santa Teresa de Ávila"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Santa Teresa fue una monja carmelita española que reformó la Orden y experimentó gracias místicas a través de la oración.""", """St. Teresa was a Spanish Carmelite nun who reformed the Order and experienced mystical grace through prayer""", """Santo padroeiro de a Espanha, os doentes, os religiosos, o xadrez, os que são ridicularizados pela sua piedade, os rendeiros, Požega (Croácia), Talisay City (Cebu, Filipinas), Malalag (Davao del Sur, Filipinas)"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! Amada en Jesucristo y de su querida Madre. ¡Gloriosísima Santa Teresa de Jesús!, inspirada por Dios para reformar la Sagrada orden del Carmen, fuisteis espejo de castidad y pureza abrasada en el amor de Dios, ¿por qué no me habéis de conseguir la gracia de imitaros hasta la muerte?

Aquí me tenéis postrado a vuestras divinas plantas deseando obréis en mí esa celestial sabiduría que os hizo predilecta para Jesucristo y su divina Madre, María Santísima. Yo os dedico esta novena para honra y gloria vuestra y bien de mi alma. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most amiable Lord Jesus Christ! We thank Thee for the great gift of faith and of devotion to the Holy Sacrament, which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits and by those of Thy faithful spouse, to grant us the gift of a lively faith, and of a fervent devotion toward the most Holy Sacrament of the altar; where Thou, O infinite Majesty! hast obliged Thyself to abide with us even to the end of the world, and wherein Thou didst so lovingly give Thy whole Self to us.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amabilíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da fé e da devoção ao Santíssimo Sacramento que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa fiel esposa, que nos concedais o dom de uma fé viva e de uma devoção fervorosa ao Santíssimo Sacramento do altar, onde Vós, ó Majestade infinita, Vos comprometestes a permanecer conosco até o fim do mundo, e no qual tão amorosamente Vos destes inteiramente a nós.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! Amada en Jesucristo y de su querida Madre. ¡Gloriosísima Santa Teresa de Jesús!, inspirada por Dios para reformar la Sagrada orden del Carmen, fuisteis espejo de castidad y pureza abrasada en el amor de Dios, ¿por qué no me habéis de conseguir la gracia de imitaros hasta la muerte?

Aquí me tenéis postrado a vuestras divinas plantas deseando obréis en mí esa celestial sabiduría que os hizo predilecta para Jesucristo y su divina Madre, María Santísima. Yo os dedico esta novena para honra y gloria vuestra y bien de mi alma. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most amiable Lord Jesus Christ! We thank Thee for the great gift of faith and of devotion to the Holy Sacrament, which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits and by those of Thy faithful spouse, to grant us the gift of a lively faith, and of a fervent devotion toward the most Holy Sacrament of the altar; where Thou, O infinite Majesty! hast obliged Thyself to abide with us even to the end of the world, and wherein Thou didst so lovingly give Thy whole Self to us.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amabilíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da fé e da devoção ao Santíssimo Sacramento que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa fiel esposa, que nos concedais o dom de uma fé viva e de uma devoção fervorosa ao Santíssimo Sacramento do altar, onde Vós, ó Majestade infinita, Vos comprometestes a permanecer conosco até o fim do mundo, e no qual tão amorosamente Vos destes inteiramente a nós.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Aquí me tenéis ¡oh Dios mío! a mí, vil gusanillo de la tierra, ciego por el camino de la perdición, que no teniendo en cuenta lo que padecisteis por toda las criaturas, sufriendo vuestra sacratísima pasión y muerte por todos los pecadores, dadme, Señor, esa luz divina que me inspire en vuestra bondad y misericordia infinita, para que sea digno de alcanzar la bienaventuranza, como la alcanzó la seráfica y gloriosa virgen Santa Teresa de Jesús. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most merciful Lord Jesus Christ! we thank Thee for the great gift of hope which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy holy spouse, to give us a great confidence in Thy goodness, by reason of Thy Precious Blood, which Thou hast shed to its last drop for our salvation.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó misericordiosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da esperança que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa santa esposa, que nos deis uma grande confiança na Vossa bondade, em virtude do Vosso Preciosíssimo Sangue, que derramastes até a última gota pela nossa salvação.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Aquí me tenéis ¡oh Dios mío! a mí, vil gusanillo de la tierra, ciego por el camino de la perdición, que no teniendo en cuenta lo que padecisteis por toda las criaturas, sufriendo vuestra sacratísima pasión y muerte por todos los pecadores, dadme, Señor, esa luz divina que me inspire en vuestra bondad y misericordia infinita, para que sea digno de alcanzar la bienaventuranza, como la alcanzó la seráfica y gloriosa virgen Santa Teresa de Jesús. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most merciful Lord Jesus Christ! we thank Thee for the great gift of hope which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy holy spouse, to give us a great confidence in Thy goodness, by reason of Thy Precious Blood, which Thou hast shed to its last drop for our salvation.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó misericordiosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da esperança que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa santa esposa, que nos deis uma grande confiança na Vossa bondade, em virtude do Vosso Preciosíssimo Sangue, que derramastes até a última gota pela nossa salvação.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! mi buen Jesús, Dios y Señor de todo lo criado; yo os adoro y os amo de todo corazón con arrepentimiento sincero de todas mis culpas y pecados. ¿Por qué mi Dios y Señor, no habéis de abrasar mi corazón como a vuestra amada esposa Santa Teresa de Jesús? Comunicadme, Señor, esa llama celestial que limpie mi alma de toda culpa, para que sea digno de alcanzar la gloria que tanto deseo. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most loving Lord Jesus Christ! we thank Thee for the great gift of love which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most loving spouse, to give us the great, the crowning gift of Thy perfect love.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amorosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom do amor que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa amantíssima esposa, que nos deis o grande dom, o dom supremo, do Vosso perfeito amor.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! mi buen Jesús, Dios y Señor de todo lo criado; yo os adoro y os amo de todo corazón con arrepentimiento sincero de todas mis culpas y pecados. ¿Por qué mi Dios y Señor, no habéis de abrasar mi corazón como a vuestra amada esposa Santa Teresa de Jesús? Comunicadme, Señor, esa llama celestial que limpie mi alma de toda culpa, para que sea digno de alcanzar la gloria que tanto deseo. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most loving Lord Jesus Christ! we thank Thee for the great gift of love which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most loving spouse, to give us the great, the crowning gift of Thy perfect love.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amorosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom do amor que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa amantíssima esposa, que nos deis o grande dom, o dom supremo, do Vosso perfeito amor.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Te saludo resplandeciente lirio de la hermosura y sosegada primavera, Virgen Sagrada, divina esposa de Jesucristo que merecisteis los dones celestiales y la predilección de María Santísima dando al mundo ejemplos de virtud y de ternura, por lo que merecisteis ser colmada de los dones celestiales.

Te suplico ¡oh Santa Teresa de Jesús! me comuniquéis la gracia del Eterno que vos merecisteis para ser admitida en el seno de los cielos, para tener la dicha de acompañaros en la mansión de los justos. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most sweet Lord Jesus Christ! we thank Thee for the gift of great desire and resolution which Thou didst grant to Thy beloved Teresa, that she might love Thee perfectly; we pray Thee, by Thy merits, and by those of Thy most generous spouse, to give us a true desire, and a true resolution of pleasing Thee the utmost of our power. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó dulcíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom do grande desejo e da firme resolução que concedestes à Vossa amada Teresa, para que Vos amasse perfeitamente; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa generosíssima esposa, que nos deis um verdadeiro desejo e uma verdadeira resolução de Vos agradar com todas as nossas forças. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Te saludo resplandeciente lirio de la hermosura y sosegada primavera, Virgen Sagrada, divina esposa de Jesucristo que merecisteis los dones celestiales y la predilección de María Santísima dando al mundo ejemplos de virtud y de ternura, por lo que merecisteis ser colmada de los dones celestiales.

Te suplico ¡oh Santa Teresa de Jesús! me comuniquéis la gracia del Eterno que vos merecisteis para ser admitida en el seno de los cielos, para tener la dicha de acompañaros en la mansión de los justos. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most sweet Lord Jesus Christ! we thank Thee for the gift of great desire and resolution which Thou didst grant to Thy beloved Teresa, that she might love Thee perfectly; we pray Thee, by Thy merits, and by those of Thy most generous spouse, to give us a true desire, and a true resolution of pleasing Thee the utmost of our power. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó dulcíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom do grande desejo e da firme resolução que concedestes à Vossa amada Teresa, para que Vos amasse perfeitamente; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa generosíssima esposa, que nos deis um verdadeiro desejo e uma verdadeira resolução de Vos agradar com todas as nossas forças. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! mi amada Santa Teresa de Jesús; yo os escojo como ayuda piadosa, Madre de mi flaqueza, de mis atenciones y necesidades; yo me entrego en vuestros brazos, lleno de arrepentimiento de todos mis pecados, para que me cubráis con vuestro hábito seráfico como a hijo vuestro, para que me consoléis con vuestra gracia pidiendo a vuestro Esposo, Jesucristo, y a su purísima Madre por este mortal que desea de todo corazón participar de la gloria reservada a las almas justas. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most kind Lord Jesus Christ! we thank Thee for the great gift of humility which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most humble spouse, to grant us the grace of a true humility, which may make us ever find our joy in humiliation, and prefer contempt before every honor.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó bondosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da humildade que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa humílima esposa, que nos concedais a graça de uma verdadeira humildade, que nos faça encontrar sempre a nossa alegria na humilhação e preferir o desprezo a toda honra.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh! mi amada Santa Teresa de Jesús; yo os escojo como ayuda piadosa, Madre de mi flaqueza, de mis atenciones y necesidades; yo me entrego en vuestros brazos, lleno de arrepentimiento de todos mis pecados, para que me cubráis con vuestro hábito seráfico como a hijo vuestro, para que me consoléis con vuestra gracia pidiendo a vuestro Esposo, Jesucristo, y a su purísima Madre por este mortal que desea de todo corazón participar de la gloria reservada a las almas justas. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most kind Lord Jesus Christ! we thank Thee for the great gift of humility which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most humble spouse, to grant us the grace of a true humility, which may make us ever find our joy in humiliation, and prefer contempt before every honor.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó bondosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo grande dom da humildade que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa humílima esposa, que nos concedais a graça de uma verdadeira humildade, que nos faça encontrar sempre a nossa alegria na humilhação e preferir o desprezo a toda honra.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Divina Santa Teresa de Jesús; aquí me tenéis sumisa y arrepentida de todos mis pecados, pidiéndoos la protección de Jesús y de su divina Madre, María Santísima, para que por su intercesión me concedáis tres cosas: la primera, que tengáis misericordia de mí por mis muchos pecados cometidos; segunda, gracia para serviros cumpliendo los Mandamientos de Dios y no caer en pecado mortal, y la tercera, que me socorráis en el trance crítico de la muerte para morir en vuestra santa gracia, que rogaréis por mí os pido a Nuestro Señor Jesucristo y a la Santísima Virgen María. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most bountiful Lord Jesus Christ! we thank Thee for the gift of devotion towards Thy sweet mother, Mary and her holy spouse, Joseph, which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most dear spouse, to give us the grace of a special and tender devotion towards Thy most holy mother, Mary, and towards Thy beloved foster-father, Joseph. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó generosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom da devoção à Vossa doce Mãe, Maria, e ao seu santo esposo, José, que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa caríssima esposa, que nos deis a graça de uma devoção especial e terna à Vossa santíssima Mãe, Maria, e ao Vosso amado pai adotivo, José. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Divina Santa Teresa de Jesús; aquí me tenéis sumisa y arrepentida de todos mis pecados, pidiéndoos la protección de Jesús y de su divina Madre, María Santísima, para que por su intercesión me concedáis tres cosas: la primera, que tengáis misericordia de mí por mis muchos pecados cometidos; segunda, gracia para serviros cumpliendo los Mandamientos de Dios y no caer en pecado mortal, y la tercera, que me socorráis en el trance crítico de la muerte para morir en vuestra santa gracia, que rogaréis por mí os pido a Nuestro Señor Jesucristo y a la Santísima Virgen María. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most bountiful Lord Jesus Christ! we thank Thee for the gift of devotion towards Thy sweet mother, Mary and her holy spouse, Joseph, which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most dear spouse, to give us the grace of a special and tender devotion towards Thy most holy mother, Mary, and towards Thy beloved foster-father, Joseph. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó generosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom da devoção à Vossa doce Mãe, Maria, e ao seu santo esposo, José, que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa caríssima esposa, que nos deis a graça de uma devoção especial e terna à Vossa santíssima Mãe, Maria, e ao Vosso amado pai adotivo, José. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh Dios misericordioso y clemente! cuyo espíritu de bondad manifestáis a los mortales cuanto más pecadores se os manifiestan. Sois, pues, pastor de las ovejas descarriadas que con vuestra infinita bondad y misericordia las dirigís al redil de la bienaventuranza.

Os pido, mi buen Jesús, infiltréis en mi ese espíritu divino para que yo me arrepienta de todos los pecados cometidos durante mi vida; sea yo tan feliz mereciendo la gracia que prodigasteis a Santa Teresa de Jesús, haciéndola esposa vuestra y que merezca los dones que recibió de vuestra querida Madre para alabaros, bendeciros y gozaros en la gloria por siempre jamás. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most loving Lord Jesus Christ! we thank Thee for the wonderful gift of the wound in the heart which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy seraphic spouse, to grant us also a like wound of love, that, henceforth, we may love Thee and give our mind to the love of nothing but Thee. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amorosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo admirável dom da ferida no coração que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa seráfica esposa, que nos concedais também uma semelhante ferida de amor, para que de agora em diante Vos amemos e não ponhamos o nosso pensamento no amor de nada senão de Vós. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

¡Oh Dios misericordioso y clemente! cuyo espíritu de bondad manifestáis a los mortales cuanto más pecadores se os manifiestan. Sois, pues, pastor de las ovejas descarriadas que con vuestra infinita bondad y misericordia las dirigís al redil de la bienaventuranza.

Os pido, mi buen Jesús, infiltréis en mi ese espíritu divino para que yo me arrepienta de todos los pecados cometidos durante mi vida; sea yo tan feliz mereciendo la gracia que prodigasteis a Santa Teresa de Jesús, haciéndola esposa vuestra y que merezca los dones que recibió de vuestra querida Madre para alabaros, bendeciros y gozaros en la gloria por siempre jamás. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most loving Lord Jesus Christ! we thank Thee for the wonderful gift of the wound in the heart which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy seraphic spouse, to grant us also a like wound of love, that, henceforth, we may love Thee and give our mind to the love of nothing but Thee. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amorosíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo admirável dom da ferida no coração que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa seráfica esposa, que nos concedais também uma semelhante ferida de amor, para que de agora em diante Vos amemos e não ponhamos o nosso pensamento no amor de nada senão de Vós. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Señor mío Jesucristo: yo os adoro y os amo de todo corazón y quisiera participar de los dolores de vuestra Pasión y muerte que sufristeis por nosotros los pecadores: pase por mí también el amargo cáliz de tantos padecimientos, ya que marchasteis al sacrificio de vuestra pasión y muerte, lleve yo esa cruz al calvario de mis culpas como os dignasteis favorecer a Santa Teresa de Jesús, para que mi alma quede purificada y pueda alcanzar la gloria que tenéis reservada a los que os sirven. Amen.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most beloved Lord Jesus Christ! we thank Thee for the eminent gift of the desire for a happy death in God’s sanctifying grace which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most constant spouse, to grant us the grace of desiring such a happy death, in order to go and possess Thee eternally in the country of the blessed. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amadíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo insigne dom do desejo de uma morte feliz na graça santificante de Deus, que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa constantíssima esposa, que nos concedais a graça de desejar uma morte tão feliz, para irmos possuir-Vos eternamente na pátria dos bem-aventurados. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Señor mío Jesucristo: yo os adoro y os amo de todo corazón y quisiera participar de los dolores de vuestra Pasión y muerte que sufristeis por nosotros los pecadores: pase por mí también el amargo cáliz de tantos padecimientos, ya que marchasteis al sacrificio de vuestra pasión y muerte, lleve yo esa cruz al calvario de mis culpas como os dignasteis favorecer a Santa Teresa de Jesús, para que mi alma quede purificada y pueda alcanzar la gloria que tenéis reservada a los que os sirven. Amen.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

O most beloved Lord Jesus Christ! we thank Thee for the eminent gift of the desire for a happy death in God’s sanctifying grace which Thou didst grant to Thy beloved Teresa; we pray Thee, by Thy merits, and by those of Thy most constant spouse, to grant us the grace of desiring such a happy death, in order to go and possess Thee eternally in the country of the blessed. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Ó amadíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo insigne dom do desejo de uma morte feliz na graça santificante de Deus, que concedestes à Vossa amada Teresa; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa constantíssima esposa, que nos concedais a graça de desejar uma morte tão feliz, para irmos possuir-Vos eternamente na pátria dos bem-aventurados. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

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

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Dios y Señor de todo lo criado: ¡cuán grata es a vuestra divinísima misericordia la muerte de los que abandonan en gracia esta miserable vida y van a ser partícipes de la eterna bienaventuranza! concededme, Señor, a mí esa virtud con que favorecisteis a Santa Teresa de Jesús por sus muchas virtudes dándola una muerte feliz, cuya alma voló al cielo en forma de blanca paloma donde la esperaban gozosos para celebrar su triunfo millares de coros de vírgenes, ángeles, arcángeles y serafines.

Te suplico, oh Santa Teresa de Jesús, que pidáis por mi a vuestro esposo, Nuestro Señor Jesucristo y a su bendita Madre, María Santísima, me conceda una buena muerte y que vaya después a gozar la eterna gloria. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

Lastly, O dearest Lord Jesus Christ! we thank Thee for the gift of the precious death which Thou didst grant to Thy beloved Teresa, making her sweetly to die of love; we pray Thee, by Thy merits, and by those of Thy most affectionate spouse, to grant us a good death; and if we do not die of love, yet, that we may at least die burning of love for Thee, that so dying, we may be able to go and love Thee for evermore with a more perfect love in heaven. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Por fim, ó queridíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom da preciosa morte que concedestes à Vossa amada Teresa, fazendo-a morrer docemente de amor; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa afetuosíssima esposa, que nos concedais uma boa morte; e, se não morrermos de amor, que ao menos morramos ardendo de amor por Vós, para que, morrendo assim, possamos ir amar-Vos para sempre com um amor mais perfeito no céu. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh! dulcísimo, amantísimo y reverendísimo Jesucristo, padre de todas las misericordias: aquí me tenéis rendido a vuestros pies para manifestaros que os amo sobre todas las cosas y con el pesar de haberos ofendido, y os suplico de todo corazón me perdonéis mis culpas y pecados; os suplico también pidáis a vuestra queridísima Madre, María Santísima, me conceda la gracia para asistir a esta Novena en honra de vuestra seráfica y predilecta hija Santa Teresa

(menciona tu petición aquí…)

Amén.

Dios y Señor de todo lo criado: ¡cuán grata es a vuestra divinísima misericordia la muerte de los que abandonan en gracia esta miserable vida y van a ser partícipes de la eterna bienaventuranza! concededme, Señor, a mí esa virtud con que favorecisteis a Santa Teresa de Jesús por sus muchas virtudes dándola una muerte feliz, cuya alma voló al cielo en forma de blanca paloma donde la esperaban gozosos para celebrar su triunfo millares de coros de vírgenes, ángeles, arcángeles y serafines.

Te suplico, oh Santa Teresa de Jesús, que pidáis por mi a vuestro esposo, Nuestro Señor Jesucristo y a su bendita Madre, María Santísima, me conceda una buena muerte y que vaya después a gozar la eterna gloria. Amén.

Terminar con tres Padrenuestros, Avemarías y Glorias a la Santísima Trinidad.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(This novena was written by St. Alphonsus Liguori)

Lastly, O dearest Lord Jesus Christ! we thank Thee for the gift of the precious death which Thou didst grant to Thy beloved Teresa, making her sweetly to die of love; we pray Thee, by Thy merits, and by those of Thy most affectionate spouse, to grant us a good death; and if we do not die of love, yet, that we may at least die burning of love for Thee, that so dying, we may be able to go and love Thee for evermore with a more perfect love in heaven. Amen.

Say one Our Father, Hail Mary and Glory be.

V. St. Teresa, pray for us:

R. That we may become worthy of the promises of Jesus Christ.

Let us pray: Graciously hear us, O God of our salvation! that as we rejoice in the commemoration of the blessed virgin Teresa, so we may be nourished by her heavenly doctrine, and draw from thence the fervour of a tender devotion; through our Lord Jesus Christ, Thy Son, Who liveth and reigneth with Thee in the unity of the Holy Spirit, God for ever and ever.

(mention request here…)

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(Esta novena foi escrita por Santo Afonso Maria de Ligório)

Por fim, ó queridíssimo Senhor Jesus Cristo! Nós Vos damos graças pelo dom da preciosa morte que concedestes à Vossa amada Teresa, fazendo-a morrer docemente de amor; nós Vos pedimos, pelos Vossos méritos e pelos da Vossa afetuosíssima esposa, que nos concedais uma boa morte; e, se não morrermos de amor, que ao menos morramos ardendo de amor por Vós, para que, morrendo assim, possamos ir amar-Vos para sempre com um amor mais perfeito no céu. Amém.

Reze um Pai-Nosso, uma Ave-Maria e um Glória ao Pai.

V. Santa Teresa, rogai por nós.

R. Para que sejamos dignos das promessas de Jesus Cristo.

Oremos: Ouvi-nos com bondade, ó Deus da nossa salvação, para que, assim como nos alegramos com a memória da bem-aventurada virgem Teresa, sejamos alimentados pela sua doutrina celeste e dela tiremos o fervor de uma terna devoção. Por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, Deus, por todos os séculos dos séculos.

(mencione o pedido aqui…)

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-teresa-of-avila.png""",
        tags = listOf("blessings", "love", "health")
    )
}