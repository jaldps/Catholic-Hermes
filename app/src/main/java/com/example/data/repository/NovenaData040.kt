package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData040 {
    val novena: Novena = Novena(
        id = """novena-a-nuestra-senora-del-buen-consejo""",
        title = LocalizedText("""Novena a Nuestra Señora del Buen Consejo""", """Our Lady of Good Counsel Novena""", """Novena a Nossa Senhora do Bom Conselho"""),
        patron = LocalizedText("""Santo patrón de Albania, las Hermanas Misioneras de San Pedro Claver, la Provincia Agustiniana del Medio Oeste de Estados Unidos y la ciudad de Parañaque, Filipinas""", """Patron saint of Albania, Missionary Sisters of Saint Peter Claver, Augustinian Province of Midwest US, Parañaque City, Philippines.""", """Novena a Nossa Senhora do Bom Conselho"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza a Nuestra Señora del Buen Consejo, uno de los títulos de la Santísima Virgen María, tomado de una pintura rescatada milagrosamente de Albania durante la invasión turca.""", """Our Lady of Good Counsel is one of the titles of the Blessed Virgin Mary, named after a painting miraculously rescued from Albania during the Turkish invasion.""", """Santo padroeiro de Albânia, Irmãs Missionárias de São Pedro Claver, Província Agostiniana do Centro-Oeste dos Estados Unidos, cidade de Parañaque, nas Filipinas"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

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

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Virgen Santísima, a cuyos pies nos conduce nuestra ansiosa incertidumbre en la búsqueda y el logro de lo verdadero y lo bueno: invocándote con el dulce título de Madre del Buen Consejo, te suplicamos que vengas en nuestra ayuda cuando, en el camino de esta vida, las tinieblas del error y del mal conspiran para nuestra ruina, extraviando nuestras mentes y nuestros corazones.

Oh Sede de la Sabiduría y Estrella del Mar, ilumina a los que dudan y a los que yerran, para que no se dejen seducir por las falsas apariencias del bien; hazlos firmes ante las influencias hostiles y corruptoras de las pasiones y del pecado.

Oh Madre del Buen Consejo, alcánzanos de tu divino Hijo un gran amor a la virtud y, en la hora de la incertidumbre y de la prueba, la fuerza para abrazar el camino que conduce a nuestra salvación.

Si tu mano nos sostiene, caminaremos sin estorbo por la senda que nos señalan la vida y las palabras de Jesús, nuestro Redentor; y habiendo seguido con libertad y seguridad, aun en medio de las luchas de este mundo, al Sol de la Verdad y de la Justicia bajo tu Estrella maternal, llegaremos a gozar contigo de la paz plena y eterna en el puerto de la salvación.

Amén.

Te pedimos especialmente por la intención de esta novena:

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Nuestra Señora del Buen Consejo, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, to whose feet we are led by our anxious uncertainty in our search for and attainment of what is true and good, invoking you by the sweet title of Mother of Good Counsel, we beseech you to come to our assistance, when, along the road of this life, the darkness of error and of evil conspires towards our ruin by leading our minds and our hearts astray.

O Seat of Wisdom and Star of the Sea, enlighten the doubtful and the erring, that they be not seduced by the false appearances of good; render them steadfast in the face of the hostile and corrupting influences of passion and of sin.

O Mother of Good Counsel, obtain for us from your Divine Son a great love of virtue, and, in the hour of uncertainty and trial, the strength to embrace the way that leads to our salvation.

If your hand sustains us, we shall walk unmolested along the path indicated to us by the life and words of Jesus, our Redeemer; and having followed freely and securely, even in the midst of this world’s strife, the Sun of Truth and Justice under your maternal Star, we shall come to the enjoyment of full and eternal peace with you in the haven of salvation.

Amen.

We pray especially for the intention in this novena of

(mention request here…)

Our Father

Hail Mary

Glory Be

Our Lady of Good Counsel, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santíssima Virgem, a cujos pés nos conduz a nossa ansiosa incerteza na busca e na conquista do que é verdadeiro e bom, invocando-vos com o doce título de Mãe do Bom Conselho, nós vos suplicamos que venhais em nosso auxílio quando, ao longo do caminho desta vida, as trevas do erro e do mal conspiram para a nossa ruína, desviando as nossas mentes e os nossos corações.

Ó Sede da Sabedoria e Estrela do Mar, iluminai os que duvidam e os que erram, para que não sejam seduzidos pelas falsas aparências do bem; tornai-os firmes diante das influências hostis e corruptoras da paixão e do pecado.

Ó Mãe do Bom Conselho, alcançai-nos do vosso Divino Filho um grande amor à virtude e, na hora da incerteza e da provação, a força para abraçar o caminho que conduz à nossa salvação.

Se a vossa mão nos sustentar, caminharemos sem perturbação pela senda que nos é indicada pela vida e pelas palavras de Jesus, nosso Redentor; e, tendo seguido livre e seguramente, mesmo em meio às lutas deste mundo, o Sol da Verdade e da Justiça sob a vossa Estrela materna, chegaremos ao gozo da paz plena e eterna convosco no porto da salvação.

Amém.

Rezamos especialmente pela intenção desta novena:

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora do Bom Conselho, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-good-counsel-novena.png""",
        tags = listOf("love", "health", "peace")
    )
}