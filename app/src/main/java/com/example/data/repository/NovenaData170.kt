package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData170 {
    val novena: Novena = Novena(
        id = """novena-a-santa-isabel-madre-de-juan-bautista""",
        title = LocalizedText("""Novena a Santa Isabel, madre de San Juan Bautista""", """St Elizabeth Novena""", """Novena a Santa Isabel, Mãe de São João Batista"""),
        patron = LocalizedText("""Santo patrón de las mujeres embarazadas y para concebir un hijo""", """Patron saint of pregnant women and to conceive a baby.""", """Novena a Santa Isabel, Mãe de São João Batista"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a Santa Isabel, madre de San Juan Bautista, esposa de San Zacarías y prima de la Santísima Virgen María, por las mujeres embarazadas y para concebir un hijo.""", """St Elizabeth was the mother of St John the Baptist, the wife of St Zechariah, and the cousin of the Blessed Virgin Mary. Pray this novena for pregnant women and to conceive a baby.""", """Santo padroeiro de as gestantes e para conceber um bebê"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

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

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosa Santa Isabel, después de muchos años de matrimonio sin hijos, en tu vejez tu fe en Dios fue recompensada con el nacimiento de un hijo muy especial, San Juan Bautista.

Ayúdame, querida santa, a ser paciente y a confiar en la voluntad de Dios, sabiendo que él sabe lo que es mejor para mí.

Oh Dios, tú elegiste a Santa Isabel para desempeñar un papel central en nuestra Redención.

Su apertura a tu voluntad es un modelo para nosotros.

Que te sigamos tan fielmente como lo hizo Santa Isabel.

Te lo pedimos por Cristo nuestro Señor. Amén.

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Isabel, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Glorious St. Elizabeth, after many childless years of marriage, in your old age your faith in God was rewarded with the birth of a very special son — St. John the Baptist.

Help me, dear saint, to be patient and trust in the will of God, knowing that He knows what is best for me.

O God, You chose St. Elizabeth to play a central role in our Redemption.

Her openness to Thy will is a model for us.

May we follow You as faithfully as St. Elizabeth did.

This we ask through Christ our Lord. Amen.

(mention request here…)

Our Father

Hail Mary

Glory Be

St Elizabeth, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosa Santa Isabel, depois de muitos anos de casamento sem filhos, na vossa velhice a vossa fé em Deus foi recompensada com o nascimento de um filho muito especial, São João Batista.

Ajudai-me, querida santa, a ser paciente e a confiar na vontade de Deus, sabendo que Ele sabe o que é melhor para mim.

Ó Deus, Vós escolhestes Santa Isabel para ter um papel central na nossa Redenção.

A sua abertura à Vossa vontade é um modelo para nós.

Que Vos sigamos tão fielmente quanto Santa Isabel Vos seguiu.

Nós Vo-lo pedimos por Cristo, nosso Senhor. Amém.

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Isabel, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-elizabeth-visitation-novena.png""",
        tags = emptyList()
    )
}