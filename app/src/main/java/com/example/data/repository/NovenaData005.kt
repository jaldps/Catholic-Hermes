package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData005 {
    val novena: Novena = Novena(
        id = """novena-a-la-natividad-de-la-virgen-maria""",
        title = LocalizedText("""Novena a la Natividad de la Virgen María""", """Nativity of Mary Novena""", """Novena da Natividade de Nossa Senhora"""),
        patron = LocalizedText("""Santo patrón de Malta, Cuba, Filipinas, Kerala (India) y una mayor devoción a Nuestra Señora""", """Patron saint of Malta, Cuba, Philippines, Kerala India, and for greater devotion to Our Lady""", """Novena da Natividade de Nossa Senhora"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a la Natividad de María, que celebra el cumpleaños de la Santísima Virgen María, por Malta, Cuba, Filipinas, Kerala (India) y por una mayor devoción a Nuestra Señora.""", """The Nativity of Mary celebrates the birthday of the Blessed Virgin Mary. Pray this novena for Malta, Cuba, Philippines, Kerala India, and for greater devotion to Our Lady.""", """Santo padroeiro de Malta, Cuba, Filipinas, Kerala (Índia) e por uma maior devoção a Nossa Senhora"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Niña celestial, amabilísima María, el Padre Eterno se complace en tu nacimiento, porque contempla en tu venida a este mundo a una de sus criaturas tan perfecta que llegará a ser la digna Madre de su divino Hijo. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Padre celestial el perdón de mis pecados y un dolor constante por ellos.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Heavenly Child, lovable Mary, the Eternal Father delights in your birth, for He beholds in your coming into this world one of His creatures who is so perfect that she will become the worthy Mother of His divine Son. May your birth give joy to my soul also, by obtaining for me from the heavenly Father, the pardon of my sins, and an abiding sorrow for them.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Celeste Menina, amável Maria, o Pai Eterno se alegra com o vosso nascimento, pois contempla na vossa vinda a este mundo uma de Suas criaturas, tão perfeita que se tornará a digna Mãe do Seu divino Filho. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Pai celeste o perdão dos meus pecados e uma dor constante por eles.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Niña celestial, amabilísima María, el Padre Eterno se complace en tu nacimiento, porque contempla en tu venida a este mundo a una de sus criaturas tan perfecta que llegará a ser la digna Madre de su divino Hijo. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Padre celestial el perdón de mis pecados y un dolor constante por ellos.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Heavenly Child, lovable Mary, the Eternal Father delights in your birth, for He beholds in your coming into this world one of His creatures who is so perfect that she will become the worthy Mother of His divine Son. May your birth give joy to my soul also, by obtaining for me from the heavenly Father, the pardon of my sins, and an abiding sorrow for them.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Celeste Menina, amável Maria, o Pai Eterno se alegra com o vosso nascimento, pois contempla na vossa vinda a este mundo uma de Suas criaturas, tão perfeita que se tornará a digna Mãe do Seu divino Filho. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Pai celeste o perdão dos meus pecados e uma dor constante por eles.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, admirable María, el Hijo de Dios se complace en tu nacimiento, porque contempla a la elegida para ser su Madre, y, ¡oh!, una Madre tan amada. Que tu nacimiento llene también de alegría mi alma, alcanzándome de tu divino Hijo la gracia de nacer de nuevo espiritualmente a una vida santa, perfectamente conforme a la tuya, para que merezca obtener la gloria eterna.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, admirable Mary, the Son of God delights in your birth, for He beholds the one chosen to be His Mother, and oh! such a beloved Mother. May your birth give joy to my soul also, by obtaining from your divine Son the grace to be born again spiritually to a holy life, perfectly conformed to yours, so that I may merit to obtain eternal glory.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, admirável Maria, o Filho de Deus se alegra com o vosso nascimento, pois contempla aquela que foi escolhida para ser Sua Mãe, e oh! uma Mãe tão amada. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do vosso divino Filho a graça de renascer espiritualmente para uma vida santa, perfeitamente conforme à vossa, para que eu mereça alcançar a glória eterna.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, admirable María, el Hijo de Dios se complace en tu nacimiento, porque contempla a la elegida para ser su Madre, y, ¡oh!, una Madre tan amada. Que tu nacimiento llene también de alegría mi alma, alcanzándome de tu divino Hijo la gracia de nacer de nuevo espiritualmente a una vida santa, perfectamente conforme a la tuya, para que merezca obtener la gloria eterna.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, admirable Mary, the Son of God delights in your birth, for He beholds the one chosen to be His Mother, and oh! such a beloved Mother. May your birth give joy to my soul also, by obtaining from your divine Son the grace to be born again spiritually to a holy life, perfectly conformed to yours, so that I may merit to obtain eternal glory.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, admirável Maria, o Filho de Deus se alegra com o vosso nascimento, pois contempla aquela que foi escolhida para ser Sua Mãe, e oh! uma Mãe tão amada. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do vosso divino Filho a graça de renascer espiritualmente para uma vida santa, perfeitamente conforme à vossa, para que eu mereça alcançar a glória eterna.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, amabilísima María, el Verbo Eterno se complace en tu nacimiento, porque ve en ti la fuente de su Sangre Inmaculada. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Verbo hecho carne la gracia de encontrar en esta Sangre divina la felicidad eterna de mi alma.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, lovable Mary, the Eternal Word takes delight in your birth, for He sees the source of His Immaculate Blood. May your birth give joy to my soul also, by obtaining for me from the Word made Flesh, the grace to find in this Divine Blood the eternal happiness of my soul.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, amável Maria, o Verbo Eterno se alegra com o vosso nascimento, pois vê nele a fonte do Seu Sangue Imaculado. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Verbo feito Carne a graça de encontrar neste Divino Sangue a felicidade eterna da minha alma.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, amabilísima María, el Verbo Eterno se complace en tu nacimiento, porque ve en ti la fuente de su Sangre Inmaculada. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Verbo hecho carne la gracia de encontrar en esta Sangre divina la felicidad eterna de mi alma.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, lovable Mary, the Eternal Word takes delight in your birth, for He sees the source of His Immaculate Blood. May your birth give joy to my soul also, by obtaining for me from the Word made Flesh, the grace to find in this Divine Blood the eternal happiness of my soul.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, amável Maria, o Verbo Eterno se alegra com o vosso nascimento, pois vê nele a fonte do Seu Sangue Imaculado. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Verbo feito Carne a graça de encontrar neste Divino Sangue a felicidade eterna da minha alma.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, admirable María: el Redentor, prometido a la humanidad desde el principio del mundo, se complace en tu nacimiento, porque ve a aquella que está destinada a ser la Corredentora de las almas, uniendo sus lágrimas a la Sangre derramada en la Cruz para salvar a la humanidad.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, admirable Mary: the Redeemer, promised to mankind from the beginning of the world, takes delight in your birth, for He sees the one who is destined to become the Co-redemptress of souls by uniting her tears to the Blood shed on the Cross to save mankind.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, admirável Maria: o Redentor, prometido à humanidade desde o princípio do mundo, se alegra com o vosso nascimento, pois vê aquela que está destinada a tornar-se a Corredentora das almas, unindo as suas lágrimas ao Sangue derramado na Cruz para salvar a humanidade.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Oh Elegida entre las hijas de Adán, admirable María: el Redentor, prometido a la humanidad desde el principio del mundo, se complace en tu nacimiento, porque ve a aquella que está destinada a ser la Corredentora de las almas, uniendo sus lágrimas a la Sangre derramada en la Cruz para salvar a la humanidad.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

O Chosen One among the daughters of Adam, admirable Mary: the Redeemer, promised to mankind from the beginning of the world, takes delight in your birth, for He sees the one who is destined to become the Co-redemptress of souls by uniting her tears to the Blood shed on the Cross to save mankind.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Ó Eleita entre as filhas de Adão, admirável Maria: o Redentor, prometido à humanidade desde o princípio do mundo, se alegra com o vosso nascimento, pois vê aquela que está destinada a tornar-se a Corredentora das almas, unindo as suas lágrimas ao Sangue derramado na Cruz para salvar a humanidade.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Resplandeciente Lirio del Paraíso, amabilísima María, el Espíritu Santo se complace en tu nacimiento, porque ve en ti el alma nunca manchada por el pecado, que sería para siempre su digno Templo. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Espíritu Santo su amor divino y la perseverancia final.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Resplendent Lily of Paradise, lovable Mary, the Holy Spirit takes delight in your birth for He sees in you the soul never stained by sin, which would forever be His worthy Temple. May your birth give joy to my soul also, by obtaining for me from the Holy Spirit His divine love and final perseverance.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Lírio resplandecente do Paraíso, amável Maria, o Espírito Santo se alegra com o vosso nascimento, pois vê em vós a alma jamais manchada pelo pecado, que seria para sempre o Seu digno Templo. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Espírito Santo o Seu divino amor e a perseverança final.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Resplandeciente Lirio del Paraíso, amabilísima María, el Espíritu Santo se complace en tu nacimiento, porque ve en ti el alma nunca manchada por el pecado, que sería para siempre su digno Templo. Que tu nacimiento llene también de alegría mi alma, alcanzándome del Espíritu Santo su amor divino y la perseverancia final.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Resplendent Lily of Paradise, lovable Mary, the Holy Spirit takes delight in your birth for He sees in you the soul never stained by sin, which would forever be His worthy Temple. May your birth give joy to my soul also, by obtaining for me from the Holy Spirit His divine love and final perseverance.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Lírio resplandecente do Paraíso, amável Maria, o Espírito Santo se alegra com o vosso nascimento, pois vê em vós a alma jamais manchada pelo pecado, que seria para sempre o Seu digno Templo. Que o vosso nascimento dê alegria também à minha alma, alcançando-me do Espírito Santo o Seu divino amor e a perseverança final.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Hermosísimo Retoño del árbol de David, admirabilísima María, cuyo nacimiento fue un gran consuelo para San Joaquín y Santa Ana, que se alegraron de ser los padres de una niña tan santa, que tu nacimiento sea el consuelo de mi alma, alcanzándome de Dios la victoria sobre el mundo y el desprendimiento de todas las cosas de esta tierra.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Most Beautiful Branch of the tree of David, most admirable Mary, whose birth was such a consolation to St. Joachim and St. Anne who were delighted to be the parents of so holy a child, may your birth be the consolation of my soul, by obtaining for me from God, victory over the world and detachment from all the things of this earth.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Belíssimo Rebento da árvore de Davi, admirabilíssima Maria, cujo nascimento foi tão grande consolação para São Joaquim e Sant’Ana, que se alegraram por serem os pais de tão santa criança, que o vosso nascimento seja a consolação da minha alma, alcançando-me de Deus a vitória sobre o mundo e o desapego de todas as coisas desta terra.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Hermosísimo Retoño del árbol de David, admirabilísima María, cuyo nacimiento fue un gran consuelo para San Joaquín y Santa Ana, que se alegraron de ser los padres de una niña tan santa, que tu nacimiento sea el consuelo de mi alma, alcanzándome de Dios la victoria sobre el mundo y el desprendimiento de todas las cosas de esta tierra.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Most Beautiful Branch of the tree of David, most admirable Mary, whose birth was such a consolation to St. Joachim and St. Anne who were delighted to be the parents of so holy a child, may your birth be the consolation of my soul, by obtaining for me from God, victory over the world and detachment from all the things of this earth.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Belíssimo Rebento da árvore de Davi, admirabilíssima Maria, cujo nascimento foi tão grande consolação para São Joaquim e Sant’Ana, que se alegraram por serem os pais de tão santa criança, que o vosso nascimento seja a consolação da minha alma, alcançando-me de Deus a vitória sobre o mundo e o desapego de todas as coisas desta terra.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Resplandeciente Estrella de la mañana, amabilísima María, tu nacimiento fue causa de gran alegría para los ángeles, porque vieron en ti a la Madre del Redentor del mundo, que con su Muerte repararía la pérdida causada por la caída de los ángeles rebeldes en el paraíso. Que tu nacimiento llene también de alegría mi alma, alcanzándome de Dios la victoria sobre el enemigo infernal, la liberación de todas sus trampas y la gracia de ser asociado a la gloria de los ángeles en el cielo.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Resplendent Morning Star, lovable Mary, your birth was the cause of great joy to the angels, for they beheld in you the Mother of the Redeemer of the world, who by His Death would repair the loss sustained by the fall of rebellious angels in paradise. May your birth give joy to my soul also, by obtaining for me from God, victory over the infernal enemy, deliverance from all his snares, and the grace to be associated in glory with the angels in heaven.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Resplandecente Estrela da Manhã, amável Maria, o vosso nascimento foi causa de grande alegria para os anjos, pois contemplaram em vós a Mãe do Redentor do mundo, que por Sua Morte repararia a perda causada pela queda dos anjos rebeldes no paraíso. Que o vosso nascimento dê alegria também à minha alma, alcançando-me de Deus a vitória sobre o inimigo infernal, a libertação de todas as suas ciladas e a graça de ser associado na glória aos anjos do céu.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Resplandeciente Estrella de la mañana, amabilísima María, tu nacimiento fue causa de gran alegría para los ángeles, porque vieron en ti a la Madre del Redentor del mundo, que con su Muerte repararía la pérdida causada por la caída de los ángeles rebeldes en el paraíso. Que tu nacimiento llene también de alegría mi alma, alcanzándome de Dios la victoria sobre el enemigo infernal, la liberación de todas sus trampas y la gracia de ser asociado a la gloria de los ángeles en el cielo.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Resplendent Morning Star, lovable Mary, your birth was the cause of great joy to the angels, for they beheld in you the Mother of the Redeemer of the world, who by His Death would repair the loss sustained by the fall of rebellious angels in paradise. May your birth give joy to my soul also, by obtaining for me from God, victory over the infernal enemy, deliverance from all his snares, and the grace to be associated in glory with the angels in heaven.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Resplandecente Estrela da Manhã, amável Maria, o vosso nascimento foi causa de grande alegria para os anjos, pois contemplaram em vós a Mãe do Redentor do mundo, que por Sua Morte repararia a perda causada pela queda dos anjos rebeldes no paraíso. Que o vosso nascimento dê alegria também à minha alma, alcançando-me de Deus a vitória sobre o inimigo infernal, a libertação de todas as suas ciladas e a graça de ser associado na glória aos anjos do céu.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Brillante Aurora del Cielo, amabilísima María, tu nacimiento trajo gran consuelo a las almas de los santos detenidas en el limbo, porque anunciaba la llegada del Sol de Justicia, Jesucristo, que iluminaría sus tinieblas y luego las conduciría al paraíso. Que tu nacimiento llene también de alegría mi alma y me alcance de Dios la paciencia en todas las adversidades y una perfecta y constante conformidad con su santísima voluntad.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Brilliant Aurora of Heaven, lovable Mary, your birth brought great consolation to the souls of the saints detained in limbo, for it announced the approach of the Sun of Justice, Jesus Christ, who would enlighten their darkness and then conduct them to paradise. May your birth give joy to my soul also, and obtain for me from God, patience in all adversities, a perfect and constant conformity to His most holy will.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Brilhante Aurora do Céu, amável Maria, o vosso nascimento trouxe grande consolação às almas dos santos retidas no limbo, pois anunciava a aproximação do Sol de Justiça, Jesus Cristo, que iluminaria as suas trevas e depois as conduziria ao paraíso. Que o vosso nascimento dê alegria também à minha alma e me alcance de Deus paciência em todas as adversidades e uma perfeita e constante conformidade com a Sua santíssima vontade.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Brillante Aurora del Cielo, amabilísima María, tu nacimiento trajo gran consuelo a las almas de los santos detenidas en el limbo, porque anunciaba la llegada del Sol de Justicia, Jesucristo, que iluminaría sus tinieblas y luego las conduciría al paraíso. Que tu nacimiento llene también de alegría mi alma y me alcance de Dios la paciencia en todas las adversidades y una perfecta y constante conformidad con su santísima voluntad.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Brilliant Aurora of Heaven, lovable Mary, your birth brought great consolation to the souls of the saints detained in limbo, for it announced the approach of the Sun of Justice, Jesus Christ, who would enlighten their darkness and then conduct them to paradise. May your birth give joy to my soul also, and obtain for me from God, patience in all adversities, a perfect and constant conformity to His most holy will.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Brilhante Aurora do Céu, amável Maria, o vosso nascimento trouxe grande consolação às almas dos santos retidas no limbo, pois anunciava a aproximação do Sol de Justiça, Jesus Cristo, que iluminaria as suas trevas e depois as conduziria ao paraíso. Que o vosso nascimento dê alegria também à minha alma e me alcance de Deus paciência em todas as adversidades e uma perfeita e constante conformidade com a Sua santíssima vontade.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

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

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Mediadora entre Dios y la humanidad, admirable María, con tu nacimiento completaste la alegría de todos los hijos de Adán, que por medio de ti han recibido al Autor de la Gracia, pues Él te ha hecho tesorera de todas las gracias que se nos conceden. Que tu nacimiento sea una causa especial de alegría para mi alma, alcanzándome de Dios la salvación eterna y todas las gracias necesarias para obtenerla.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Mediatrix between God and mankind, admirable Mary, by your birth you perfected the joy of all the children of Adam, who, through you, have received the Author of Grace, for He has made you the treasurer of all the graces which are imparted to us. May your birth be a special cause of joy to my soul by obtaining for me from God, eternal salvation and all the graces necessary to obtain it.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Medianeira entre Deus e a humanidade, admirável Maria, pelo vosso nascimento completastes a alegria de todos os filhos de Adão, que, por vós, receberam o Autor da Graça, pois Ele vos fez tesoureira de todas as graças que nos são concedidas. Que o vosso nascimento seja um motivo especial de alegria para a minha alma, alcançando-me de Deus a salvação eterna e todas as graças necessárias para alcançá-la.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

V. Dios mío, ven en mi auxilio.

R. Señor, date prisa en socorrerme.

V. Gloria al Padre, y al Hijo, y al Espíritu Santo.

R. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén.

Mediadora entre Dios y la humanidad, admirable María, con tu nacimiento completaste la alegría de todos los hijos de Adán, que por medio de ti han recibido al Autor de la Gracia, pues Él te ha hecho tesorera de todas las gracias que se nos conceden. Que tu nacimiento sea una causa especial de alegría para mi alma, alcanzándome de Dios la salvación eterna y todas las gracias necesarias para obtenerla.

Queridísima Madre, por favor ruega por mí y por estas mis intenciones.

(menciona tu petición aquí…)

Dios te salve, María. (1 vez)

Tu Natividad, oh Virgen Madre de Dios, anunció la alegría a todo el mundo; porque de ti nació el Sol de Justicia, Cristo nuestro Dios, que, destruyendo la maldición, nos dio la bendición, y confundiendo a la muerte, nos dio la vida eterna.

V. Celebremos con alegría el nacimiento de la Santísima Virgen María.

R. Para que interceda por nosotros ante nuestro Señor Jesucristo.

Concede a tus siervos, te suplicamos, Señor, el don de tu gracia celestial, para que, así como nuestra salvación comenzó con la maternidad de la Santísima Virgen, esta solemne fiesta de su Natividad nos obtenga un aumento de paz. Por nuestro Señor Jesucristo. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

V. O God, come to my assistance.

R. O Lord, make haste to help me.

V. Glory be to the Father, and to the Son, and to the Holy Spirit.

R. As it was in the beginning, is now and ever shall be, world without end. Amen

Mediatrix between God and mankind, admirable Mary, by your birth you perfected the joy of all the children of Adam, who, through you, have received the Author of Grace, for He has made you the treasurer of all the graces which are imparted to us. May your birth be a special cause of joy to my soul by obtaining for me from God, eternal salvation and all the graces necessary to obtain it.

Dearest Mother, please pray for me and for these my intentions.

(mention request here…)

Hail Mary x1

Your Nativity, O Virgin Mother of God, was the herald of joy to the whole world; since from you arose the Sun of Justice, Christ our God, who, destroying the curse, bestowed the blessing, and confounding death, rewarded us with life everlasting.

V. Let us celebrate with joy the birth of the Blessed Virgin Mary.

R. That she may intercede for us with Our Lord Jesus Christ.

Grant to us your servants, we beseech you, O Lord, the gift of Your heavenly grace, that as our salvation was begun in the child-bearing of the Blessed Virgin, so from this solemn festival of her Nativity may we obtain an increase of peace. Through Our Lord Jesus Christ. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

V. Vinde, ó Deus, em meu auxílio.

R. Socorrei-me sem demora.

V. Glória ao Pai e ao Filho e ao Espírito Santo.

R. Como era no princípio, agora e sempre, pelos séculos dos séculos. Amém.

Medianeira entre Deus e a humanidade, admirável Maria, pelo vosso nascimento completastes a alegria de todos os filhos de Adão, que, por vós, receberam o Autor da Graça, pois Ele vos fez tesoureira de todas as graças que nos são concedidas. Que o vosso nascimento seja um motivo especial de alegria para a minha alma, alcançando-me de Deus a salvação eterna e todas as graças necessárias para alcançá-la.

Querida Mãe, rogai por mim e por estas minhas intenções.

(mencione o pedido aqui…)

Ave-Maria.

O vosso nascimento, ó Virgem Mãe de Deus, anunciou a alegria ao mundo inteiro, pois de vós nasceu o Sol de Justiça, Cristo, nosso Deus, que, destruindo a maldição, nos trouxe a bênção e, confundindo a morte, nos deu a vida eterna.

V. Celebremos com alegria o nascimento da Bem-aventurada Virgem Maria.

R. Para que ela interceda por nós junto a Nosso Senhor Jesus Cristo.

Concedei aos Vossos servos, nós Vos suplicamos, Senhor, o dom da Vossa graça celeste, para que, assim como a nossa salvação começou com o parto da Bem-aventurada Virgem, desta solene festa da sua Natividade alcancemos um aumento de paz. Por Nosso Senhor Jesus Cristo. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/nativity-of-mary-novena.png""",
        tags = listOf("blessings", "love", "comfort", "peace")
    )
}