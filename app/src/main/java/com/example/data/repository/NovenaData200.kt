package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData200 {
    val novena: Novena = Novena(
        id = """novena-a-santo-tomas-becket""",
        title = LocalizedText("""Novena a Santo Tomás Becket, arzobispo y mártir""", """St Thomas Becket Novena""", """Novena a São Tomás Becket"""),
        patron = LocalizedText("""Santo patrón de el Exeter College de Oxford, Portsmouth, la abadía de Arbroath, el clero secular, la City de Londres y la catedral de Canterbury""", """Patron saint of Exeter college, Oxford, Portsmouth, Arbroath Abbey, secular clergy, City of London, and Canterbury Cathedral""", """Novena a São Tomás Becket"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Recordatorios diarios + intenciones + oraciones grupales""", """Daily reminders + prayer intentions + group prayers""", """Santo padroeiro de Exeter College de Oxford, Portsmouth, Abadia de Arbroath, clero secular, City de Londres e Catedral de Cantuária"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

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

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran Santo Tomás, obispo, sacerdote, mártir y cordero sacrificado por la fe, ruega por nosotros.

Tú, que fuiste un pastor intrépido del pueblo de Dios, ruega por nosotros para que tengamos valentía en todas las circunstancias de nuestra vida y vivamos según la luz que tu ejemplo da a nuestra conciencia.

Que seamos fieles hasta la muerte, como lo fuiste tú. Que busquemos siempre la santa voluntad de Dios en nuestra vida.

Santo Tomás, muchos fueron llevados ante ti para ser sanados, tanto durante tu vida como, sobre todo, después de tu martirio; te ruego que escuches y acojas mis oraciones por

(menciona tu petición aquí…)

Santo Tomás, ruega por nosotros para que en Él, y con Él, vivamos, nos movamos y existamos.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santo Tomás Becket, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Saint Thomas, Bishop, Priest, Martyr, and sacrificial lamb for the Faith, pray for us.

You who were a fearless Shepherd of the people of God, pray for us to have courage in all the circumstances of our lives, to living according to the light that your example gives to our consciences.

May we be faithful unto death, as you were. May we always seek God’s Holy will in our lives.

Saint Thomas, many were brought to you for healing both in your lifetime and especially after your martyrdom, please hear and accept my prayers for

(mention request here…)

St Thomas pray for us that in Him, and with Him, we may live and move and have our being.

Amen.

Our Father

Hail Mary

Glory Be

St Thomas Becket, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande São Tomás, bispo, sacerdote, mártir e cordeiro imolado pela fé, rogai por nós.

Vós, que fostes um pastor destemido do povo de Deus, rogai por nós, para que tenhamos coragem em todas as circunstâncias da nossa vida e vivamos de acordo com a luz que o vosso exemplo dá à nossa consciência.

Que sejamos fiéis até a morte, como vós fostes. Que busquemos sempre a santa vontade de Deus na nossa vida.

São Tomás, muitos foram levados a vós em busca de cura, tanto durante a vossa vida como sobretudo depois do vosso martírio; por favor, ouvi e acolhei as minhas orações por

(mencione o pedido aqui…)

São Tomás, rogai por nós, para que n’Ele, e com Ele, vivamos, nos movamos e existamos.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Tomás Becket, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-thomas-becket-novena.png""",
        tags = emptyList()
    )
}