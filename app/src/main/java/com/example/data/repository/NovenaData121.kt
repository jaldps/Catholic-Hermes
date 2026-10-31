package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData121 {
    val novena: Novena = Novena(
        id = """novena-a-san-patricio""",
        title = LocalizedText("""Novena a San Patricio, Apóstol de Irlanda""", """St. Patrick Novena""", """Novena a São Patrício, Apóstolo da Irlanda"""),
        patron = LocalizedText("""Santo patrón de Irlanda, Nigeria, Montserrat, la Arquidiócesis de Nueva York, la Arquidiócesis de Newark, la Arquidiócesis de Los Ángeles, Boston, Rolla (Misuri), Loíza (Puerto Rico), Murcia (España), el clan Giolla Phádraig, los ingenieros, los asistentes jurídicos, la Arquidiócesis de Melbourne; se le invoca contra las serpientes y los pecados""", """Patron saint of Ireland, Nigeria, Montserrat, Archdiocese of New York, Archdiocese of Newark, Archdiocese of Los Angeles, Boston, Rolla, Missouri, Loíza, Puerto Rico, Murcia Spain, Clann Giolla Phádraig, engineers, paralegals, Archdiocese of Melbourne, invoked against snakes, sins""", """Novena a São Patrício, Apóstolo da Irlanda"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""San Patricio fue esclavizado en su adolescencia, pero escapó y llegó a ser misionero en Irlanda. Su valentía y su fe convirtieron a los irlandeses del paganismo celta al catolicismo.""", """St. Patrick was enslaved as a teenager but escaped and became a missionary to Ireland. His courage and faith converted the Irish from Celtic paganism to Catholicism.""", """Santo padroeiro de a Irlanda, a Nigéria, Montserrat, a Arquidiocese de Nova York, a Arquidiocese de Newark, a Arquidiocese de Los Angeles, Boston, Rolla (Missouri), Loíza (Porto Rico), Múrcia (Espanha), o clã Giolla Phádraig, os engenheiros, os assistentes jurídicos, a Arquidiocese de Melbourne; invocado contra as serpentes e os pecados"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por medio de una fuerza poderosa, la invocación de la Trinidad, por medio de la fe en las Tres Personas, por medio de la confesión de la Unidad del Creador de la creación.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today; Through a mighty strength, the invocation of the Trinity, Through a belief in the Threeness, Through confession of the Oneness Of the Creator of creation.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje por meio de uma força poderosa, a invocação da Trindade, pela fé nas Três Pessoas, pela confissão da Unidade do Criador da criação.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por medio de una fuerza poderosa, la invocación de la Trinidad, por medio de la fe en las Tres Personas, por medio de la confesión de la Unidad del Creador de la creación.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today; Through a mighty strength, the invocation of the Trinity, Through a belief in the Threeness, Through confession of the Oneness Of the Creator of creation.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje por meio de uma força poderosa, a invocação da Trindade, pela fé nas Três Pessoas, pela confissão da Unidade do Criador da criação.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy
 por la fuerza del nacimiento de Cristo y de Su bautismo,
 por la fuerza de Su crucifixión y de Su sepultura,
 por la fuerza de Su resurrección y de Su ascensión,
 por la fuerza de Su descenso para el Juicio Final.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today
 Through the strength of Christ’s birth and His baptism,
 Through the strength of His crucifixion and His burial,
 Through the strength of His resurrection and His ascension, Through the strength of His descent for the judgment of doom.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje
 pela força do nascimento de Cristo e do Seu batismo,
 pela força da Sua crucificação e da Sua sepultura,
 pela força da Sua ressurreição e da Sua ascensão,
 pela força da Sua vinda no dia do juízo.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy
 por la fuerza del nacimiento de Cristo y de Su bautismo,
 por la fuerza de Su crucifixión y de Su sepultura,
 por la fuerza de Su resurrección y de Su ascensión,
 por la fuerza de Su descenso para el Juicio Final.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today
 Through the strength of Christ’s birth and His baptism,
 Through the strength of His crucifixion and His burial,
 Through the strength of His resurrection and His ascension, Through the strength of His descent for the judgment of doom.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje
 pela força do nascimento de Cristo e do Seu batismo,
 pela força da Sua crucificação e da Sua sepultura,
 pela força da Sua ressurreição e da Sua ascensão,
 pela força da Sua vinda no dia do juízo.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza del amor de los querubines, en la obediencia de los ángeles, en el servicio de los arcángeles, en la esperanza de la resurrección para recibir la recompensa, en las oraciones de los patriarcas,
 en las predicaciones de los apóstoles, en la fe de los confesores, en la inocencia de las vírgenes, en las obras de los justos.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through the strength of the love of cherubim, In obedience of angels, In service of archangels, In the hope of resurrection to meet with reward, In the prayers of patriarchs,
 In preachings of the apostles, In faiths of confessors, In innocence of virgins, In deeds of righteous men.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força do amor dos querubins, na obediência dos anjos, no serviço dos arcanjos, na esperança da ressurreição para receber a recompensa, nas orações dos patriarcas,
 nas pregações dos apóstolos, na fé dos confessores, na inocência das virgens, nas obras dos justos.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza del amor de los querubines, en la obediencia de los ángeles, en el servicio de los arcángeles, en la esperanza de la resurrección para recibir la recompensa, en las oraciones de los patriarcas,
 en las predicaciones de los apóstoles, en la fe de los confesores, en la inocencia de las vírgenes, en las obras de los justos.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through the strength of the love of cherubim, In obedience of angels, In service of archangels, In the hope of resurrection to meet with reward, In the prayers of patriarchs,
 In preachings of the apostles, In faiths of confessors, In innocence of virgins, In deeds of righteous men.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força do amor dos querubins, na obediência dos anjos, no serviço dos arcanjos, na esperança da ressurreição para receber a recompensa, nas orações dos patriarcas,
 nas pregações dos apóstolos, na fé dos confessores, na inocência das virgens, nas obras dos justos.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza del cielo:
 luz del sol, resplandor del fuego, rapidez del rayo, velocidad del viento, profundidad del mar,
 estabilidad de la tierra, firmeza de la roca.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through the strength of heaven;
 Light of the sun, Splendor of fire, Speed of lightning, Swiftness of the wind, Depth of the sea,
 Stability of the earth, Firmness of the rock.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força do céu:
 luz do sol, esplendor do fogo, rapidez do relâmpago, velocidade do vento, profundidade do mar,
 estabilidade da terra, firmeza da rocha.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza del cielo:
 luz del sol, resplandor del fuego, rapidez del rayo, velocidad del viento, profundidad del mar,
 estabilidad de la tierra, firmeza de la roca.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through the strength of heaven;
 Light of the sun, Splendor of fire, Speed of lightning, Swiftness of the wind, Depth of the sea,
 Stability of the earth, Firmness of the rock.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força do céu:
 luz do sol, esplendor do fogo, rapidez do relâmpago, velocidade do vento, profundidade do mar,
 estabilidade da terra, firmeza da rocha.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza de Dios que me guía:
 el poder de Dios para sostenerme,
 la sabiduría de Dios para conducirme,
 el ojo de Dios para mirar delante de mí,
 el oído de Dios para escucharme,
 la palabra de Dios para hablar por mí,
 la mano de Dios para guardarme,
 el camino de Dios para abrirse ante mí,
 el escudo de Dios para protegerme,
 los ejércitos de Dios para salvarme
 de las trampas del demonio,
 de las tentaciones de los vicios,
 de todos los que me desean mal,
 lejos y cerca,
 solo o en multitud.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through God’s strength to pilot me; God’s might to uphold me,
 God’s wisdom to guide me,
 God’s eye to look before me,
 God’s ear to hear me,
 God’s word to speak for me,
 God’s hand to guard me,
 God’s way to lie before me,
 God’s shield to protect me,
 God’s hosts to save me
 From snares of the devil,
 From temptations of vices,
 From every one who desires me ill,
 Afar and anear,
 Alone or in a mulitude.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força de Deus que me guia;
 o poder de Deus para me sustentar,
 a sabedoria de Deus para me conduzir,
 o olho de Deus para olhar diante de mim,
 o ouvido de Deus para me ouvir,
 a palavra de Deus para falar por mim,
 a mão de Deus para me guardar,
 o caminho de Deus para se abrir diante de mim,
 o escudo de Deus para me proteger,
 os exércitos de Deus para me salvar
 das ciladas do demônio,
 das tentações dos vícios,
 de todos os que me desejam mal,
 longe e perto,
 sozinho ou na multidão.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por la fuerza de Dios que me guía:
 el poder de Dios para sostenerme,
 la sabiduría de Dios para conducirme,
 el ojo de Dios para mirar delante de mí,
 el oído de Dios para escucharme,
 la palabra de Dios para hablar por mí,
 la mano de Dios para guardarme,
 el camino de Dios para abrirse ante mí,
 el escudo de Dios para protegerme,
 los ejércitos de Dios para salvarme
 de las trampas del demonio,
 de las tentaciones de los vicios,
 de todos los que me desean mal,
 lejos y cerca,
 solo o en multitud.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through God’s strength to pilot me; God’s might to uphold me,
 God’s wisdom to guide me,
 God’s eye to look before me,
 God’s ear to hear me,
 God’s word to speak for me,
 God’s hand to guard me,
 God’s way to lie before me,
 God’s shield to protect me,
 God’s hosts to save me
 From snares of the devil,
 From temptations of vices,
 From every one who desires me ill,
 Afar and anear,
 Alone or in a mulitude.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje pela força de Deus que me guia;
 o poder de Deus para me sustentar,
 a sabedoria de Deus para me conduzir,
 o olho de Deus para olhar diante de mim,
 o ouvido de Deus para me ouvir,
 a palavra de Deus para falar por mim,
 a mão de Deus para me guardar,
 o caminho de Deus para se abrir diante de mim,
 o escudo de Deus para me proteger,
 os exércitos de Deus para me salvar
 das ciladas do demônio,
 das tentações dos vícios,
 de todos os que me desejam mal,
 longe e perto,
 sozinho ou na multidão.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Convoco hoy todos estos poderes entre mí y el mal, contra todo poder cruel y despiadado que se oponga a mi cuerpo y a mi alma,
 contra los conjuros de los falsos profetas, contra las negras leyes del paganismo, contra las falsas leyes de los herejes, contra las artes de la idolatría,
 contra los hechizos de brujas, herreros y magos, contra todo conocimiento que corrompe el cuerpo y el alma del hombre.
 Cristo, protégeme hoy
 contra el veneno, contra el fuego,
 contra el ahogamiento, contra las heridas, para que me llegue recompensa en abundancia.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I summon today all these powers between me and evil, Against every cruel merciless power that opposes my body and soul,
 Against incantations of false prophets, Against black laws of pagandom, Against false laws of heretics, Against craft of idolatry,
 Against spells of witches and smiths and wizards, Against every knowledge that corrupts man’s body and soul.
 Christ shield me today
 Against poison, against burning,
 Against drowning, against wounding, So that reward may come to me in abundance.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Convoco hoje todos estes poderes entre mim e o mal, contra todo poder cruel e impiedoso que se oponha ao meu corpo e à minha alma,
 contra os encantamentos dos falsos profetas, contra as leis negras do paganismo, contra as falsas leis dos hereges, contra as artes da idolatria,
 contra os feitiços de bruxas, ferreiros e magos, contra todo saber que corrompe o corpo e a alma do homem.
 Cristo me proteja hoje
 contra o veneno, contra o fogo,
 contra o afogamento, contra as feridas, para que me venha recompensa em abundância.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Convoco hoy todos estos poderes entre mí y el mal, contra todo poder cruel y despiadado que se oponga a mi cuerpo y a mi alma,
 contra los conjuros de los falsos profetas, contra las negras leyes del paganismo, contra las falsas leyes de los herejes, contra las artes de la idolatría,
 contra los hechizos de brujas, herreros y magos, contra todo conocimiento que corrompe el cuerpo y el alma del hombre.
 Cristo, protégeme hoy
 contra el veneno, contra el fuego,
 contra el ahogamiento, contra las heridas, para que me llegue recompensa en abundancia.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I summon today all these powers between me and evil, Against every cruel merciless power that opposes my body and soul,
 Against incantations of false prophets, Against black laws of pagandom, Against false laws of heretics, Against craft of idolatry,
 Against spells of witches and smiths and wizards, Against every knowledge that corrupts man’s body and soul.
 Christ shield me today
 Against poison, against burning,
 Against drowning, against wounding, So that reward may come to me in abundance.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Convoco hoje todos estes poderes entre mim e o mal, contra todo poder cruel e impiedoso que se oponha ao meu corpo e à minha alma,
 contra os encantamentos dos falsos profetas, contra as leis negras do paganismo, contra as falsas leis dos hereges, contra as artes da idolatria,
 contra os feitiços de bruxas, ferreiros e magos, contra todo saber que corrompe o corpo e a alma do homem.
 Cristo me proteja hoje
 contra o veneno, contra o fogo,
 contra o afogamento, contra as feridas, para que me venha recompensa em abundância.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Cristo conmigo, Cristo delante de mí, Cristo detrás de mí, Cristo en mí, Cristo debajo de mí, Cristo encima de mí,
 Cristo a mi derecha, Cristo a mi izquierda,
 Cristo cuando me acuesto, Cristo cuando me siento,
 Cristo en el corazón de todo el que piensa en mí,
 Cristo en la boca de todo el que habla de mí,
 Cristo en el ojo que me ve,
 Cristo en el oído que me escucha.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

Christ with me, Christ before me, Christ behind me, Christ in me, Christ beneath me, Christ above me,
 Christ on my right, Christ on my left,
 Christ when I lie down, Christ when I sit down,
 Christ in the heart of every man who thinks of me,
 Christ in the mouth of every man who speaks of me,
 Christ in the eye that sees me,
 Christ in the ear that hears me.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Cristo comigo, Cristo diante de mim, Cristo atrás de mim, Cristo em mim, Cristo abaixo de mim, Cristo acima de mim,
 Cristo à minha direita, Cristo à minha esquerda,
 Cristo quando me deito, Cristo quando me sento,
 Cristo no coração de todo aquele que pensa em mim,
 Cristo na boca de todo aquele que fala de mim,
 Cristo no olho que me vê,
 Cristo no ouvido que me escuta.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Cristo conmigo, Cristo delante de mí, Cristo detrás de mí, Cristo en mí, Cristo debajo de mí, Cristo encima de mí,
 Cristo a mi derecha, Cristo a mi izquierda,
 Cristo cuando me acuesto, Cristo cuando me siento,
 Cristo en el corazón de todo el que piensa en mí,
 Cristo en la boca de todo el que habla de mí,
 Cristo en el ojo que me ve,
 Cristo en el oído que me escucha.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

Christ with me, Christ before me, Christ behind me, Christ in me, Christ beneath me, Christ above me,
 Christ on my right, Christ on my left,
 Christ when I lie down, Christ when I sit down,
 Christ in the heart of every man who thinks of me,
 Christ in the mouth of every man who speaks of me,
 Christ in the eye that sees me,
 Christ in the ear that hears me.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Cristo comigo, Cristo diante de mim, Cristo atrás de mim, Cristo em mim, Cristo abaixo de mim, Cristo acima de mim,
 Cristo à minha direita, Cristo à minha esquerda,
 Cristo quando me deito, Cristo quando me sento,
 Cristo no coração de todo aquele que pensa em mim,
 Cristo na boca de todo aquele que fala de mim,
 Cristo no olho que me vê,
 Cristo no ouvido que me escuta.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por medio de una fuerza poderosa, la invocación de la Trinidad, por medio de la fe en las Tres Personas, por medio de la confesión de la Unidad del Creador de la creación.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through a mighty strength, the invocation of the Trinity, Through a belief in the Threeness, Through a confession of the Oneness Of the Creator of creation

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje por meio de uma força poderosa, a invocação da Trindade, pela fé nas Três Pessoas, pela confissão da Unidade do Criador da criação.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

Me levanto hoy por medio de una fuerza poderosa, la invocación de la Trinidad, por medio de la fe en las Tres Personas, por medio de la confesión de la Unidad del Creador de la creación.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

I arise today Through a mighty strength, the invocation of the Trinity, Through a belief in the Threeness, Through a confession of the Oneness Of the Creator of creation

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

Levanto-me hoje por meio de uma força poderosa, a invocação da Trindade, pela fé nas Três Pessoas, pela confissão da Unidade do Criador da criação.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

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

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

San Patricio, gracias por tus oraciones por mi intención en esta novena. Confío en que nuestro Señor las escuchará por tu intercesión.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

St. Patrick, thank you for your prayers for my intention in this novena. I trust that our Lord will answer them through your intercession.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

São Patrício, obrigado pelas vossas orações pela minha intenção nesta novena. Confio que nosso Senhor as atenderá pela vossa intercessão.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Patricio, obtenme la gracia de amar a Dios con todo mi corazón, de servirle con todas mis fuerzas y de perseverar en los buenos propósitos hasta el fin.

Oh fiel pastor del rebaño irlandés, que habrías dado tu vida mil veces para salvar una sola alma, toma bajo tu cuidado especial mi alma y las almas de mis compatriotas.

Concede que todos los corazones participen de los benditos frutos de aquel Evangelio que tú plantaste y regaste.

(menciona tu petición aquí…)

San Patricio, gracias por tus oraciones por mi intención en esta novena. Confío en que nuestro Señor las escuchará por tu intercesión.

V. Ruega por nosotros, glorioso san Patricio.
 R. Y obtennos la intención de esta novena.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. Patrick, obtain for me grace to love God with my whole heart, to serve him with my whole strength, and to persevere in good purposes to the end.

O faithful shepherd of the Irish flock, who would have laid down his life a thousand times to save one soul, take my soul, and the souls of my countrymen, under your special care.

Grant that all hearts may share the blessed fruits of that Gospel that you planted and watered.

(mention request here…)

St. Patrick, thank you for your prayers for my intention in this novena. I trust that our Lord will answer them through your intercession.

V. Pray for us, O glorious saint Patrick.
 R. And obtain for us the intention of this Novena.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Patrício, alcançai-me a graça de amar a Deus de todo o coração, de servi-Lo com todas as minhas forças e de perseverar nos bons propósitos até o fim.

Ó fiel pastor do rebanho irlandês, que teríeis dado mil vezes a vida para salvar uma só alma, tomai sob o vosso cuidado especial a minha alma e as almas dos meus compatriotas.

Concedei que todos os corações participem dos frutos benditos daquele Evangelho que vós plantastes e regastes.

(mencione o pedido aqui…)

São Patrício, obrigado pelas vossas orações pela minha intenção nesta novena. Confio que nosso Senhor as atenderá pela vossa intercessão.

V. Rogai por nós, glorioso São Patrício.
 R. E alcançai-nos a intenção desta novena.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-patrick-novena.png""",
        tags = listOf("love")
    )
}