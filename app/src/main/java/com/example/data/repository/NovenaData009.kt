package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData009 {
    val novena: Novena = Novena(
        id = """novena-a-la-virgen-de-fatima""",
        title = LocalizedText("""Novena a la Virgen de Fátima""", """Our Lady of Fatima Novena""", """Novena a Nossa Senhora de Fátima - Paz e Conversão"""),
        patron = LocalizedText("""Santo patrón de Diocese of Leiria-Fátima""", """Patron saint of Diocese of Leiria-Fátima""", """Novena a Nossa Senhora de Fátima - Paz e Conversão"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""La Santísima Virgen María se apareció en 1917 a tres niños en el pequeño pueblo de Fátima, Portugal. Les pidió que rezaran el Rosario por la paz, por los pecadores, por el fin de la Primera Guerra Mundial y por la conversión de Rusia.""", """The Blessed Virgin Mary appeared in 1917 to three children in the little village of Fatima, Portugal. She told them to pray the Rosary for peace, for sinners, for World War I to end, and for the conversion of Russia.""", """Santo padroeiro de Diocese of Leiria-Fátima"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre de los pobres pecadores!, que apareciendo en Fátima, dejaste transparentar en vuestro rostro celestial una leve sombra de tristeza para indicar el dolor que os causan los pecados de los hombres y que con maternal compasión exhortaste a no afligir más a vuestro Hijo con la culpa y a reparar los pecados con la mortificación y la penitencia. Dadnos la gracia de un sincero dolor de los pecados cometidos y la resolución generosa de reparar con obras de penitencia y mortificación todas las ofensas que se infieren a vuestro Divino Hijo y a vuestro Corazón Inmaculado.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe dos pobres pecadores, que, aparecendo em Fátima, deixastes transparecer em vosso rosto celestial uma leve sombra de tristeza, para indicar a dor que causam os pecados dos homens, os quais, com maternal compaixão, exortastes a não afligir mais a vosso Filho com a culpa e a reparar os pecados com a mortificação e a penitência, dai-nos a graça de uma sincera dor dos pecados cometidos e a resolução generosa de reparar com obras de penitência e mortificação todas as ofensas que se inferem a vosso Divino Filho e o vosso Coração Imaculado.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre de los pobres pecadores!, que apareciendo en Fátima, dejaste transparentar en vuestro rostro celestial una leve sombra de tristeza para indicar el dolor que os causan los pecados de los hombres y que con maternal compasión exhortaste a no afligir más a vuestro Hijo con la culpa y a reparar los pecados con la mortificación y la penitencia. Dadnos la gracia de un sincero dolor de los pecados cometidos y la resolución generosa de reparar con obras de penitencia y mortificación todas las ofensas que se infieren a vuestro Divino Hijo y a vuestro Corazón Inmaculado.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe dos pobres pecadores, que, aparecendo em Fátima, deixastes transparecer em vosso rosto celestial uma leve sombra de tristeza, para indicar a dor que causam os pecados dos homens, os quais, com maternal compaixão, exortastes a não afligir mais a vosso Filho com a culpa e a reparar os pecados com a mortificação e a penitência, dai-nos a graça de uma sincera dor dos pecados cometidos e a resolução generosa de reparar com obras de penitência e mortificação todas as ofensas que se inferem a vosso Divino Filho e o vosso Coração Imaculado.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre de la divina gracia, que vestida de nívea blancura te apareciste a unos pastorcitos sencillos e inocentes, enseñándonos así cuánto debemos amar y procurar la inocencia del alma, y que pediste por medio de ellos la enmienda de las costumbres y la santidad de una vida cristiana perfecta. Concédenos misericordiosamente la gracia de saber apreciar la dignidad de nuestra condición de cristianos y de llevar una vida en todo conforme a las promesas bautismales.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe da Divina Graça, que vestida de nívea brancura aparecestes aos pastorinhos singelos e inocentes, ensinando-os, assim, o quanto devemos amar e procurar a inocência da alma, e que pedistes, por meio deles, a emenda dos costumes e a santidade de uma vida cristã perfeita, concedei-nos misericordiosamente a graça de saber apreciar a dignidade de nossa condição de cristãos e levar uma vida conforme as promessas batismais.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre de la divina gracia, que vestida de nívea blancura te apareciste a unos pastorcitos sencillos e inocentes, enseñándonos así cuánto debemos amar y procurar la inocencia del alma, y que pediste por medio de ellos la enmienda de las costumbres y la santidad de una vida cristiana perfecta. Concédenos misericordiosamente la gracia de saber apreciar la dignidad de nuestra condición de cristianos y de llevar una vida en todo conforme a las promesas bautismales.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe da Divina Graça, que vestida de nívea brancura aparecestes aos pastorinhos singelos e inocentes, ensinando-os, assim, o quanto devemos amar e procurar a inocência da alma, e que pedistes, por meio deles, a emenda dos costumes e a santidade de uma vida cristã perfeita, concedei-nos misericordiosamente a graça de saber apreciar a dignidade de nossa condição de cristãos e levar uma vida conforme as promessas batismais.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, vaso insigne de devoción!, que te apareciste en Fátima teniendo pendiente de vuestras manos el santo Rosario, y que insistentemente repetías: «Orad, orad mucho», para alejar por medio de la oración los males que nos amenazan. Concédenos el don y el espíritu de oración, la gracia de ser fieles en el cumplimiento del gran precepto de orar, haciéndolo todos los días, para así poder observar bien los santos mandamientos, vencer las tentaciones y llegar al conocimiento y amor de Jesucristo en esta vida y a la unión feliz con Él en la otra.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, vaso insigne de devoção, que aparecestes em Fátima tendo pendente de vossas mãos o Santo Rosário, e que insistentemente repetias: “Orai, orai muito” para conseguir findar, por meio da oração, os males que nos ameaçam. Concedei-nos o dom e o espírito de oração, a graça de sermos fiéis no cumprimento do grande preceito de orar, fazendo-o, todos os dias, para assim observar bem os santos mandamentos, vencer as tentações e chegar ao conhecimento e amor de Jesus Cristo, nesta vida, e à união feliz com Ele na outra.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, vaso insigne de devoción!, que te apareciste en Fátima teniendo pendiente de vuestras manos el santo Rosario, y que insistentemente repetías: «Orad, orad mucho», para alejar por medio de la oración los males que nos amenazan. Concédenos el don y el espíritu de oración, la gracia de ser fieles en el cumplimiento del gran precepto de orar, haciéndolo todos los días, para así poder observar bien los santos mandamientos, vencer las tentaciones y llegar al conocimiento y amor de Jesucristo en esta vida y a la unión feliz con Él en la otra.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, vaso insigne de devoção, que aparecestes em Fátima tendo pendente de vossas mãos o Santo Rosário, e que insistentemente repetias: “Orai, orai muito” para conseguir findar, por meio da oração, os males que nos ameaçam. Concedei-nos o dom e o espírito de oração, a graça de sermos fiéis no cumprimento do grande preceito de orar, fazendo-o, todos os dias, para assim observar bem os santos mandamentos, vencer as tentações e chegar ao conhecimento e amor de Jesus Cristo, nesta vida, e à união feliz com Ele na outra.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

Oh santísima Virgen María, Reina de la Iglesia!, que exhortaste a los pastorcitos de Fátima a rogar por el Papa, e infundiste en sus almas sencillas una gran veneración y amor hacia él, como Vicario de vuestro Hijo y su representante en la tierra. Infunde también a nosotros el espíritu de veneración y docilidad hacia la autoridad del Romano Pontífice, de adhesión inquebrantable a sus enseñanzas, y en él y con él un gran amor y respeto a todos los ministros de la santa Iglesia, por medio de los cuales participamos la vida de la gracia en los sacramentos.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, rainha da Igreja, que exortastes aos pastorinhos de Fátima a rogar pelo Papa e infundistes em suas almas sinceras uma grande veneração e amor por ele, como vigário de vosso Filho e Seu representante na Terra, infunde também a nós o espírito de veneração e docilidade à autoridade do Romano Pontífice, de adesão inquebrantável a seus ensinamentos, e nele e com ele um grande amor e respeito a todos os ministros da Santa Igreja, por meio dos quais participamos da vida da graça nos sacramentos.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

Oh santísima Virgen María, Reina de la Iglesia!, que exhortaste a los pastorcitos de Fátima a rogar por el Papa, e infundiste en sus almas sencillas una gran veneración y amor hacia él, como Vicario de vuestro Hijo y su representante en la tierra. Infunde también a nosotros el espíritu de veneración y docilidad hacia la autoridad del Romano Pontífice, de adhesión inquebrantable a sus enseñanzas, y en él y con él un gran amor y respeto a todos los ministros de la santa Iglesia, por medio de los cuales participamos la vida de la gracia en los sacramentos.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, rainha da Igreja, que exortastes aos pastorinhos de Fátima a rogar pelo Papa e infundistes em suas almas sinceras uma grande veneração e amor por ele, como vigário de vosso Filho e Seu representante na Terra, infunde também a nós o espírito de veneração e docilidade à autoridade do Romano Pontífice, de adesão inquebrantável a seus ensinamentos, e nele e com ele um grande amor e respeito a todos os ministros da Santa Igreja, por meio dos quais participamos da vida da graça nos sacramentos.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, salud de los enfermos y consoladora de los afligidos!, que movida por el ruego de los pastorcitos, obraste ya curaciones en vuestras apariciones en Fátima, y habéis convertido este lugar, santificado por vuestra presencia, en oficina de vuestras misericordias maternales en favor de todos los afligidos. A vuestro Corazón maternal acudimos llenos de filial confianza, mostrando las enfermedades de nuestras almas y las aflicciones y dolencias todas de nuestra vida. Echad sobre ellas una mirada de compasión y remediadlas con la ternura de vuestras manos, para que así podamos serviros y amaros con todo nuestro corazón y con todo nuestro ser.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, saúde dos enfermos e amparo dos aflitos, que movida pelo rogo dos pastorinhos, fizestes já curas em vossas aparições em Fátima, e haveis convertido este lugar, santificado por vossa presença, em oficina de vossas misericórdias maternais em favor de todos os aflitos; ao vosso coração maternal acudimos cheios de filial confiança, mostrando as enfermidades de nossas almas e as aflições e doenças todas de nossa vida. Deixai sobre elas um olhar de compaixão e as remedíeis com a ternura de vossas mãos, para que assim possamos servir-vos e amar-vos com todo nosso coração e com todo nosso ser.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, salud de los enfermos y consoladora de los afligidos!, que movida por el ruego de los pastorcitos, obraste ya curaciones en vuestras apariciones en Fátima, y habéis convertido este lugar, santificado por vuestra presencia, en oficina de vuestras misericordias maternales en favor de todos los afligidos. A vuestro Corazón maternal acudimos llenos de filial confianza, mostrando las enfermedades de nuestras almas y las aflicciones y dolencias todas de nuestra vida. Echad sobre ellas una mirada de compasión y remediadlas con la ternura de vuestras manos, para que así podamos serviros y amaros con todo nuestro corazón y con todo nuestro ser.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, saúde dos enfermos e amparo dos aflitos, que movida pelo rogo dos pastorinhos, fizestes já curas em vossas aparições em Fátima, e haveis convertido este lugar, santificado por vossa presença, em oficina de vossas misericórdias maternais em favor de todos os aflitos; ao vosso coração maternal acudimos cheios de filial confiança, mostrando as enfermidades de nossas almas e as aflições e doenças todas de nossa vida. Deixai sobre elas um olhar de compaixão e as remedíeis com a ternura de vossas mãos, para que assim possamos servir-vos e amar-vos com todo nosso coração e com todo nosso ser.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, refugio de los pecadores!, que enseñaste a los pastorcitos de Fátima a rogar incesantemente al Señor para que esos desgraciados no caigan en las penas eternas del infierno, y que manifestaste a uno de los tres que los pecados de la carne son los que más almas arrastran a aquellas terribles llamas. Infundid en nuestras almas un gran horror al pecado y el temor santo de la justicia divina, y al mismo tiempo despertad en ellas la compasión por la suerte de los pobres pecadores y un santo celo para trabajar con nuestras oraciones, ejemplos y palabras por su conversión.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, refúgio dos pecadores, que ensinastes aos pastorinhos de Fátima a rogar incessantemente ao Senhor, para que os desgraçados não caiam nas penas eternas do inferno, e que manifestastes a um dos três que os pecados da carne são os que mais almas arrastam àquelas terríveis chamas, colocai em nossas almas um grande horror ao pecado e o temor santo da justiça divina; ao mesmo tempo, despertai nelas a compaixão pelos pobres pecadores e um santo zelo para trabalhar com nossas orações, exemplos e palavras por sua conversão.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, refugio de los pecadores!, que enseñaste a los pastorcitos de Fátima a rogar incesantemente al Señor para que esos desgraciados no caigan en las penas eternas del infierno, y que manifestaste a uno de los tres que los pecados de la carne son los que más almas arrastran a aquellas terribles llamas. Infundid en nuestras almas un gran horror al pecado y el temor santo de la justicia divina, y al mismo tiempo despertad en ellas la compasión por la suerte de los pobres pecadores y un santo celo para trabajar con nuestras oraciones, ejemplos y palabras por su conversión.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, refúgio dos pecadores, que ensinastes aos pastorinhos de Fátima a rogar incessantemente ao Senhor, para que os desgraçados não caiam nas penas eternas do inferno, e que manifestastes a um dos três que os pecados da carne são os que mais almas arrastam àquelas terríveis chamas, colocai em nossas almas um grande horror ao pecado e o temor santo da justiça divina; ao mesmo tempo, despertai nelas a compaixão pelos pobres pecadores e um santo zelo para trabalhar com nossas orações, exemplos e palavras por sua conversão.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Reina del purgatorio!, que enseñaste a los pastorcitos de Fátima a rogar a Dios por las almas del purgatorio, especialmente por las más abandonadas. Encomendamos a la inagotable ternura de vuestro maternal Corazón todas las almas que padecen en aquel lugar de purificación, en particular las de todos nuestros allegados y familiares y las más abandonadas y necesitadas; alíviales sus penas y llévalas pronto a la región de la luz y de la paz, para cantar allí perpetuamente vuestras misericordias.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, rainha do Purgatório, que ensinastes aos pastorinhos de Fátima a rogar a Deus pelas almas do Purgatório, especialmente pelas mais abandonadas, encomendamos à inesgotável ternura de vosso maternal coração todas as almas que padecem naquele lugar de purificação, em particular as de todos os nossos chegados e familiares e as mais abandonadas e necessitadas; aliviais suas penas e levai-as prontas à região da luz e da paz, para ali cantar perpetuamente vossas misericórdias.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Reina del purgatorio!, que enseñaste a los pastorcitos de Fátima a rogar a Dios por las almas del purgatorio, especialmente por las más abandonadas. Encomendamos a la inagotable ternura de vuestro maternal Corazón todas las almas que padecen en aquel lugar de purificación, en particular las de todos nuestros allegados y familiares y las más abandonadas y necesitadas; alíviales sus penas y llévalas pronto a la región de la luz y de la paz, para cantar allí perpetuamente vuestras misericordias.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, rainha do Purgatório, que ensinastes aos pastorinhos de Fátima a rogar a Deus pelas almas do Purgatório, especialmente pelas mais abandonadas, encomendamos à inesgotável ternura de vosso maternal coração todas as almas que padecem naquele lugar de purificação, em particular as de todos os nossos chegados e familiares e as mais abandonadas e necessitadas; aliviais suas penas e levai-as prontas à região da luz e da paz, para ali cantar perpetuamente vossas misericórdias.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María!, que en vuestra última aparición te diste a conocer como la Reina del Santísimo Rosario, y en todas ellas recomendaste el rezo de esta devoción como el remedio más seguro y eficaz para todos los males y calamidades que nos afligen, tanto del alma como del cuerpo, así públicas como privadas. Infundid en nuestras almas una profunda estima de los misterios de nuestra Redención que se conmemoran en el rezo del Rosario, para así vivir siempre de sus frutos. Concédenos la gracia de ser siempre fieles a la práctica de rezarlo diariamente para honraros a Vos, acompañando vuestros gozos, dolores y glorias, y así merecer vuestra maternal protección y asistencia en todos los momentos de la vida, pero especialmente en la hora de la muerte.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, que em vossa última aparição vos destes a conhecer como a Rainha do Santíssimo Rosário, e em todas as aparições recomendastes a reza dessa devoção como o remédio mais seguro e eficaz para todos os males e calamidades que nos afligem, tanto da alma quanto do corpo, tanto públicas quanto privadas, colocai em nossas almas uma profunda estima pelos mistérios de nossa Redenção, que se comemoram na reza do Rosário, para assim viverem sempre de seus frutos. Concedei-nos a graça de ser sempre fiéis à prática de rezá-lo diariamente para honrarmos a vós, acompanhando vossas alegrias, dores e glórias, e assim merecer vossa maternal proteção e assistência em todos os momentos da vida, mais especialmente na hora da morte.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María!, que en vuestra última aparición te diste a conocer como la Reina del Santísimo Rosario, y en todas ellas recomendaste el rezo de esta devoción como el remedio más seguro y eficaz para todos los males y calamidades que nos afligen, tanto del alma como del cuerpo, así públicas como privadas. Infundid en nuestras almas una profunda estima de los misterios de nuestra Redención que se conmemoran en el rezo del Rosario, para así vivir siempre de sus frutos. Concédenos la gracia de ser siempre fieles a la práctica de rezarlo diariamente para honraros a Vos, acompañando vuestros gozos, dolores y glorias, y así merecer vuestra maternal protección y asistencia en todos los momentos de la vida, pero especialmente en la hora de la muerte.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, que em vossa última aparição vos destes a conhecer como a Rainha do Santíssimo Rosário, e em todas as aparições recomendastes a reza dessa devoção como o remédio mais seguro e eficaz para todos os males e calamidades que nos afligem, tanto da alma quanto do corpo, tanto públicas quanto privadas, colocai em nossas almas uma profunda estima pelos mistérios de nossa Redenção, que se comemoram na reza do Rosário, para assim viverem sempre de seus frutos. Concedei-nos a graça de ser sempre fiéis à prática de rezá-lo diariamente para honrarmos a vós, acompanhando vossas alegrias, dores e glórias, e assim merecer vossa maternal proteção e assistência em todos os momentos da vida, mais especialmente na hora da morte.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

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

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre nuestra dulcísima!, que escogiste a los pastorcitos de Fátima para mostrar al mundo las ternuras de vuestro Corazón misericordioso, y les propusiste la devoción al mismo como el medio con el cual Dios quiere dar la paz al mundo, como el camino para llevar las almas a Dios, y como una prenda suprema de salvación. Haced, ¡oh Corazón de la más tierna de las madres!, que sepamos comprender vuestro mensaje de amor y de misericordia, que lo abracemos con filial adhesión y que lo practiquemos siempre con fervor; y así sea vuestro Corazón nuestro refugio, nuestro consuelo y el camino que nos conduzca al amor y a la unión con vuestro Hijo Jesús.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe nossa dulcíssima, que escolhestes aos pastorinhos de Fátima para mostrar ao mundo as ternuras de vosso coração misericordioso e lhes propusestes a devoção ao mesmo como o meio com o qual Deus quer dar a paz ao mundo, como o caminho para levar as almas a Ele, e como uma prenda suprema de salvação, fazei, ó coração da mais terna das mães, que possamos compreender vossa mensagem de amor e misericórdia, que a abracemos com filial adesão e que a pratiquemos sempre com fervor. Assim seja vosso coração nosso refúgio, nossa esperança e o caminho que nos conduz ao amor e à união com vosso Filho Jesus.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

OFRECIMIENTO PARA TODOS LOS DÍAS

¡Oh Dios mío! Yo creo, adoro, espero y os amo. Os pido perdón por los que no creen, no adoran, no esperan y no os aman.

¡Oh santísima Trinidad, Padre, Hijo y Espíritu Santo! Yo os adoro profundamente y os ofrezco el preciosísimo cuerpo, sangre, alma y divinidad de Nuestro Señor Jesucristo, presente en todos los tabernáculos del mundo, en reparación de los ultrajes con que Él es ofendido; y por los méritos infinitos de su Santísimo Corazón e intercesión del Inmaculado Corazón de María, os pido la conversión de los pecadores.

ORACIÓN PREPARATORIA

Oh santísima Virgen María, Reina del Rosario y Madre de misericordia, que te dignaste manifestar en Fátima la ternura de vuestro Inmaculado Corazón trayéndonos mensajes de salvación y de paz. Confiados en vuestra misericordia maternal y agradecidos a las bondades de vuestro amantísimo Corazón, venimos a vuestras plantas para rendiros el tributo de nuestra veneración y amor. Concédenos las gracias que necesitamos para cumplir fielmente vuestro mensaje de amor, y la que os pedimos en esta Novena, si ha de ser para mayor gloria de Dios, honra vuestra y provecho de nuestras almas. Así sea.

(menciona tu petición aquí…)

¡Oh santísima Virgen María, Madre nuestra dulcísima!, que escogiste a los pastorcitos de Fátima para mostrar al mundo las ternuras de vuestro Corazón misericordioso, y les propusiste la devoción al mismo como el medio con el cual Dios quiere dar la paz al mundo, como el camino para llevar las almas a Dios, y como una prenda suprema de salvación. Haced, ¡oh Corazón de la más tierna de las madres!, que sepamos comprender vuestro mensaje de amor y de misericordia, que lo abracemos con filial adhesión y que lo practiquemos siempre con fervor; y así sea vuestro Corazón nuestro refugio, nuestro consuelo y el camino que nos conduzca al amor y a la unión con vuestro Hijo Jesús.

ORACIÓN FINAL

¡Oh Dios, cuyo Unigénito, con su vida, muerte y resurrección, nos mereció el premio de la salvación eterna! Os suplicamos nos concedas que, meditando los misterios del santísimo rosario de la bienaventurada Virgen María, imitemos los ejemplos que nos enseñan y alcancemos el premio que prometen. Por el mismo Jesucristo nuestro Señor. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Most Holy Virgin, who deigned to come to Fatima to reveal to the three little shepherds the treasures of graces hidden in the recitation of the Rosary, inspire our hearts with a sincere love of this devotion, so that by meditating on the mysteries of our redemption that are recalled in it, we may gather the fruits and obtain the conversion of sinners, the conversion of Russia and this favor which I so earnestly seek,

(mention request here…)

which I ask of thee in this novena, for the greater Glory of God, for thine own honor and for the good of all people.

Amen.

My God, I believe, I adore, I hope and I love You! I ask pardon of You for those who do not believe, do not adore, do not hope and do not love You. Most Holy Trinity, Father, Son and Holy Spirit, I adore You profoundly, and I offer You the most precious Body, Blood, Soul and Divinity of Jesus Christ, present in all the tabernacles of the world, in reparation for the outrages, sacrileges and indifference with which He Himself is offended. And, through the infinite merits of His most Sacred Heart, and the Immaculate Heart of Mary, I beg of You the conversion of poor sinners.

Our Father,

Hail Mary

Glory Be

(three times each)

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Santíssima Virgem, que nos montes de Fátima vos dignastes revelar aos três pastorinhos os tesouros das graças que podemos alcançar, rezando o Santo Rosário, ajudai-nos a apreciar sempre mais essa santa oração, a fim de que, meditando os mistérios da nossa redenção, alcancemos as graças que, insistentemente, vos pedimos

(mencione o pedido aqui…)

Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno, levai as almas todas para o céu e socorrei principalmente as que mais precisarem.

Nossa Senhora de Fátima, rogai por nós.

(Reza-se uma dezena de Ave-Marias em saudação a Nossa Senhora de Fátima)

Oração preparatória

Ó Santíssima Virgem Maria, Rainha do Rosário e Mãe de Misericórdia, que vos dignastes manifestar, em Fátima, a ternura de vosso Imaculado Coração, trazendo-nos mensagens de salvação e paz, confiados em vossa misericórdia maternal e agradecidos das bondades de vosso amantíssimo coração, viemos a vossos pés para render-vos o tributo de nossa veneração e amor.

Concedei-nos as graças de que necessitamos para cumprir fielmente vossa mensagem de amor, e a que vos pedimos nessa novena, se forem elas para maior glória de Deus, honra vossa e proveito de nossas almas. Assim seja.

Ó Santíssima Virgem Maria, Mãe nossa dulcíssima, que escolhestes aos pastorinhos de Fátima para mostrar ao mundo as ternuras de vosso coração misericordioso e lhes propusestes a devoção ao mesmo como o meio com o qual Deus quer dar a paz ao mundo, como o caminho para levar as almas a Ele, e como uma prenda suprema de salvação, fazei, ó coração da mais terna das mães, que possamos compreender vossa mensagem de amor e misericórdia, que a abracemos com filial adesão e que a pratiquemos sempre com fervor. Assim seja vosso coração nosso refúgio, nossa esperança e o caminho que nos conduz ao amor e à união com vosso Filho Jesus.

Ó Deus, cujo Unigênito, com Sua vida, Morte e Ressurreição mereceu-nos o prêmio da salvação eterna, suplicamo-Vos: concedas-nos que, meditando os mistérios do Santíssimo Rosário da bem-aventurada Virgem Maria, imitemos os exemplos que nos ensinam e alcancemos o prêmio que prometem. Pelo mesmo Jesus Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-fatima.png""",
        tags = listOf("love", "comfort", "health", "family", "peace")
    )
}