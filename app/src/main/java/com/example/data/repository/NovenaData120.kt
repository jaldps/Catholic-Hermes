package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData120 {
    val novena: Novena = Novena(
        id = """novena-a-san-pablo-de-la-cruz""",
        title = LocalizedText("""Novena a San Pablo de la Cruz""", """St Paul of the Cross Novena""", """Novena a São Paulo da Cruz"""),
        patron = LocalizedText("""Santo patrón de una devoción más profunda a la Pasión de Cristo""", """Patron saint of deeper devotion to Christ's Passion""", """Novena a São Paulo da Cruz"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a San Pablo de la Cruz, místico y predicador italiano, fundador de la Congregación religiosa de los Pasionistas. Reza por una devoción más profunda a la Pasión de Cristo.""", """St. Paul of the Cross was an Italian mystic, preacher, and founder of the Passionists religious Congregation. Pray this novena for deeper devotion to Christ’s Passion.""", """Santo padroeiro de uma devoção mais profunda à Paixão de Cristo"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

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

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh buen San Pablo de la Cruz, tú revelaste las maravillas del poder de Dios proclamando la Pasión del Hijo único de Dios.

Con tus palabras y tus obras poderosas, llegaste a ser guía espiritual y predicador del Evangelio para un mundo que se había enfriado en el amor a Jesucristo.

Vuelve nuestros corazones y nuestras mentes hacia la cruz misericordiosa de Jesús.

Ayúdanos a perseverar en la fe y en el amor, y asístenos en toda necesidad, especialmente:

(menciona tu petición aquí…)

Que, compartiendo la Pasión de Jesús en esta vida, lleguemos a compartir la gloria que Él ha prometido.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡San Pablo de la Cruz, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O good St. Paul of the Cross, you revealed the wonders of God’s power by proclaiming the Passion of God’s only Son.

By your words and mighty deeds, you became a spiritual guide and preacher of the Gospel to a world grown cold to the love of Jesus Christ.

Turn our hearts and minds to the merciful cross of Jesus.

Help us to persevere in faith and love, and assist us in every need, especially:

(mention request here…)

By sharing the Passion of Jesus in this life, may we come to share in the glory He has promised.

Amen

Our Father

Hail Mary

Glory Be

Saint Paul of the Cross, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó bom São Paulo da Cruz, revelastes as maravilhas do poder de Deus, proclamando a Paixão do Filho único de Deus.

Pelas vossas palavras e obras poderosas, vós vos tornastes guia espiritual e pregador do Evangelho para um mundo que havia esfriado no amor a Jesus Cristo.

Voltai os nossos corações e as nossas mentes para a misericordiosa cruz de Jesus.

Ajudai-nos a perseverar na fé e no amor, e assisti-nos em todas as necessidades, especialmente:

(mencione o pedido aqui…)

Participando da Paixão de Jesus nesta vida, possamos chegar a participar da glória que Ele prometeu.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Paulo da Cruz, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-paul-of-the-cross-novena.png""",
        tags = listOf("love")
    )
}