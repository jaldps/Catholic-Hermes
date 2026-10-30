package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData022 {
    val novena: Novena = Novena(
        id = """novena-a-los-santos-inocentes""",
        title = LocalizedText("""Novena a los Santos Inocentes""", """Holy Innocents Novena""", """Novena aos Santos Inocentes"""),
        patron = LocalizedText("""Santo patrón de los niños expósitos, los bebés y los coros infantiles""", """Patron saint of foundlings, babies, and children's choirs""", """Novena aos Santos Inocentes"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a los Santos Inocentes, los niños de dos años para abajo de Belén que el rey Herodes mandó matar, por los niños expósitos, los bebés y los coros infantiles.""", """The Holy Innocents were the male children two years old and younger that lived near Bethlehem whom King Herod ordered to be slaughtered. Pray this novena for foundlings, babies, and children’s choirs.""", """Santo padroeiro de crianças abandonadas, bebês e coros infantis"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

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

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Padre, por su profesión de fe sin palabras en tu Hijo, los Santos Inocentes fueron coronados con la vida en su nacimiento.

Escucha sus oraciones por nosotros en esta novena, especialmente por

(menciona tu petición aquí…)

Santos Inocentes, que moristeis antes de tener edad para saber lo que significa la vida, rogad por todos los niños que mueren pequeños, para que Dios los acoja en sus brazos amorosos.

Santos Inocentes, que fuisteis asesinados porque un hombre estaba lleno de odio, rogad por los que odian, para que Dios toque su corazón y lo llene de amor.

Santos Inocentes, que sufristeis una muerte violenta, rogad por todos los que sufren la violencia, para que encuentren paz y amor.

Santos Inocentes, vuestros padres os lloraron con un dolor profundo y duradero; rogad por todos los padres que han perdido hijos pequeños, para que Dios los envuelva en un cálido manto de consuelo.

Santos Inocentes, quienes os rodeaban sin duda se sintieron impotentes para impedir vuestra muerte; rogad por todos los que se sienten impotentes en sus circunstancias, para que se aferren a Dios en busca de valor y esperanza.

Santos Inocentes, que ahora estáis en el cielo, rogad por todos nosotros, para que un día nos unamos a vosotros allí y gocemos para siempre del amor de Dios.

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Inocentes, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Father, by their wordless profession of faith in Thy Son, the Holy Innocents were crowned with life at His birth.

Hear their prayers for us in this novena, especially for

(mention request here…)

Holy Innocents, you died before you were old enough to know what life means, pray for all children who die young that God may gather them into His loving arms.

Holy Innocents, you were killed because one man was filled with hatred, pray for those who hate that God may touch their hearts and fill them with love.

Holy Innocents, you experienced a violent death, pray for all who are affected by violence that they may find peace and love.

Holy Innocents, your parents grieved for you with deep and lasting sorrow, pray for all parents who have lost young children that God may wrap a warm blanket of comfort around them.

Holy Innocents, those around you certainly felt helpless to prevent your deaths, pray for all who feel helpless in their circumstances that they may cling to God for courage and hope.

Holy Innocents, you who are now in Heaven, pray for all of us that one day we may join you there to bask in God’s love forever.

Amen.

Our Father

Hail Mary

Glory Be

Holy Innocents, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pai, por sua profissão de fé sem palavras em Vosso Filho, os Santos Inocentes foram coroados com a vida por ocasião do nascimento d’Ele.

Ouvi as orações que eles fazem por nós nesta novena, especialmente por

(mencione o pedido aqui…)

Santos Inocentes, vós que morrestes antes de terdes idade para saber o que é a vida, rogai por todas as crianças que morrem pequenas, para que Deus as acolha em Seus braços amorosos.

Santos Inocentes, vós que fostes mortos porque um homem estava cheio de ódio, rogai pelos que odeiam, para que Deus toque os seus corações e os encha de amor.

Santos Inocentes, vós que sofrestes uma morte violenta, rogai por todos os que são atingidos pela violência, para que encontrem paz e amor.

Santos Inocentes, vossos pais choraram por vós com uma dor profunda e duradoura; rogai por todos os pais que perderam filhos pequenos, para que Deus os envolva num cálido manto de consolação.

Santos Inocentes, os que estavam ao vosso redor certamente se sentiram impotentes para impedir a vossa morte; rogai por todos os que se sentem impotentes em suas circunstâncias, para que se apeguem a Deus em busca de coragem e esperança.

Santos Inocentes, vós que agora estais no Céu, rogai por todos nós, para que um dia possamos juntar-nos a vós ali e deleitar-nos no amor de Deus para sempre.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Inocentes, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/holy-innocents-novena.png""",
        tags = listOf("love", "comfort", "peace")
    )
}