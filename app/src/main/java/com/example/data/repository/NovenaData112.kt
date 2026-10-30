package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData112 {
    val novena: Novena = Novena(
        id = """novena-a-san-luis-y-santa-celia-martin""",
        title = LocalizedText("""Novena a San Luis y Santa Celia Martin""", """Saints Louis and Zelie Martin Novena""", """Novena a São Luís e Santa Zélia Martin"""),
        patron = LocalizedText("""Santo patrón de los enfermos, la enfermedad mental, el matrimonio, la crianza de los hijos y los viudos""", """Patron saint of illness, mental illness, marriage, parenting, and widowers""", """Novena a São Luís e Santa Zélia Martin"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza a San Luis y Santa Celia Martin, matrimonio católico francés y padres de cinco religiosas, entre ellas Santa Teresita de Lisieux. Reza esta novena por los enfermos, la enfermedad mental, el matrimonio, la crianza de los hijos y los viudos.""", """Saints Louis and Zelie Martin were a French Catholic couple and the parents of five nuns, including St Thérèse of Lisieux. Pray this novena for illness, mental illness, marriage, parenting, and widowers.""", """Santo padroeiro de doenças, doenças mentais, matrimônio, pais e mães, e viúvos"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

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

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Luis Martin, tú soportaste grandes sufrimientos en tu vida, en medio de las alegrías aún mayores de servir a Dios en tu bendita familia.

Instruiste a tus hijas en la fe y luego las entregaste generosamente a Dios en la vida religiosa, sin guardarte nada para ti, y él te recompensó con la corona de la santidad.

Santa Celia Guérin Martin, tú sentiste el tremendo sufrimiento de ver morir a cuatro hijos pequeños, y luego tú misma enfrentaste la muerte cuando aún tenías hijas pequeñas.

Sin embargo, perseveraste hasta el final con fe y constancia, uniendo tus sufrimientos a los de Nuestro Señor en la Cruz, y obtuviste muchas gracias para tu familia y, después, para nosotros.

Rogad por nosotros, San Luis y Santa Celia Martin, especialmente por la intención que tenemos en esta novena:

(menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis y Santa Celia Martin, rogad por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Louis Martin, you endured great sufferings in your life, amid the even greater joys of serving God in your blessed family.

You instructed your daughters in the Faith and then generously gave them to God in religious life, holding nothing back from Him, and He rewarded you with the crown of sanctity.

St Zelie Guerin Martin, you felt the tremendous suffering of having four children die at a young age, and then yourself faced death while still having young children.

Yet you endured to the end with faith and perseverance, uniting your sufferings with those of Our Lord on the Cross, obtaining for your family, and subsequently for us, many graces.

Pray for us, Saints Louis and Zelie Martin, especially in the intention we have for this novena

(mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

Sts Louis and Zelie Martin, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Luís Martin, suportastes grandes sofrimentos em vossa vida, em meio às alegrias ainda maiores de servir a Deus em vossa abençoada família.

Instruístes as vossas filhas na Fé e depois as destes generosamente a Deus na vida religiosa, sem nada Lhe negar, e Ele vos recompensou com a coroa da santidade.

Santa Zélia Guérin Martin, sentistes o imenso sofrimento de ver quatro filhos morrerem pequenos e depois enfrentastes vós mesma a morte quando ainda tínheis filhas pequenas.

Contudo, perseverastes até o fim com fé e constância, unindo os vossos sofrimentos aos de Nosso Senhor na Cruz, alcançando para a vossa família, e depois para nós, muitas graças.

Rogai por nós, São Luís e Santa Zélia Martin, especialmente pela intenção que temos nesta novena

(mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís e Santa Zélia Martin, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/saints-louis-and-zelie-martin-novena.png""",
        tags = listOf("health", "family")
    )
}