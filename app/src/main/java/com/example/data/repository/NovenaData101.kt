package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData101 {
    val novena: Novena = Novena(
        id = """novena-a-san-juan-maria-vianney""",
        title = LocalizedText("""Novena a San Juan María Vianney, Cura de Ars""", """St. John Vianney Novena""", """Novena a São João Maria Vianney, o Santo Cura d'Ars"""),
        patron = LocalizedText("""Santo patrón de los párrocos, los confesores, la Administración Apostólica Personal San Juan María Vianney, la Arquidiócesis de Dubuque, la Arquidiócesis de Kansas City""", """Patron saint of Parish priests, confessors, Personal Apostolic Administration of Saint John Mary Vianney, Archdiocese of Dubuque, Archdiocese of Kansas City""", """Novena a São João Maria Vianney, o Santo Cura d'Ars"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Recordatorios diarios + intenciones + oraciones grupales""", """Daily reminders + prayer intentions + group prayers""", """Santo padroeiro de os párocos, os confessores, a Administração Apostólica Pessoal São João Maria Vianney, a Arquidiocese de Dubuque, a Arquidiocese de Kansas City"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Dios, Tú suscitaste al humilde san Juan María Vianney para mostrar lo que puede hacer un solo sacerdote. Derrama Tus gracias sobre mi párroco y sobre todos los sacerdotes, para que sigan el ejemplo del santo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

God, you raised up humble St. John Vianney to show what one priest can do. Pour out your graces upon my parish priest, and all priests, that they follow the saint’s example.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Ó Deus, que suscitastes o humilde São João Maria Vianney para mostrar o que um só sacerdote pode fazer, derramai as Vossas graças sobre o meu pároco e sobre todos os sacerdotes, para que sigam o exemplo do santo.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Dios, Tú suscitaste al humilde san Juan María Vianney para mostrar lo que puede hacer un solo sacerdote. Derrama Tus gracias sobre mi párroco y sobre todos los sacerdotes, para que sigan el ejemplo del santo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

God, you raised up humble St. John Vianney to show what one priest can do. Pour out your graces upon my parish priest, and all priests, that they follow the saint’s example.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Ó Deus, que suscitastes o humilde São João Maria Vianney para mostrar o que um só sacerdote pode fazer, derramai as Vossas graças sobre o meu pároco e sobre todos os sacerdotes, para que sigam o exemplo do santo.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Jesús, san Juan María Vianney fue un sacerdote según Tu propio sacerdocio. Dame la valentía de anunciar y vivir el Evangelio con audacia, como él lo hizo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Jesus, St. John Vianney was a priest after your own priesthood. Give me the courage to proclaim and live the Gospel boldly, as he did.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Jesus, São João Maria Vianney foi um sacerdote segundo o Vosso próprio sacerdócio. Dai-me a coragem de anunciar e viver o Evangelho com ousadia, como ele fez.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Jesús, san Juan María Vianney fue un sacerdote según Tu propio sacerdocio. Dame la valentía de anunciar y vivir el Evangelio con audacia, como él lo hizo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Jesus, St. John Vianney was a priest after your own priesthood. Give me the courage to proclaim and live the Gospel boldly, as he did.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Jesus, São João Maria Vianney foi um sacerdote segundo o Vosso próprio sacerdócio. Dai-me a coragem de anunciar e viver o Evangelho com ousadia, como ele fez.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Señor, san Juan María Vianney pasaba horas y horas en el confesonario. Ayúdame a confesarme con frecuencia y a hacer buenas confesiones, para que venza mis defectos y crezca en santidad.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Lord, St. John Vianney sat in the confessional for hours on end. Help me go to Confession frequently and make good confessions, so that I might overcome my faults and grow in holiness.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Senhor, São João Maria Vianney ficava horas e horas no confessionário. Ajudai-me a confessar-me com frequência e a fazer boas confissões, para que eu vença os meus defeitos e cresça em santidade.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Señor, san Juan María Vianney pasaba horas y horas en el confesonario. Ayúdame a confesarme con frecuencia y a hacer buenas confesiones, para que venza mis defectos y crezca en santidad.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Lord, St. John Vianney sat in the confessional for hours on end. Help me go to Confession frequently and make good confessions, so that I might overcome my faults and grow in holiness.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Senhor, São João Maria Vianney ficava horas e horas no confessionário. Ajudai-me a confessar-me com frequência e a fazer boas confissões, para que eu vença os meus defeitos e cresça em santidade.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, fuiste bondadoso con los pecadores que acudían a ti buscando el perdón de Dios. Ruega para que yo sea misericordioso con quienes pecan contra mí y los perdone, recordando cuánto me ha perdonado nuestro Señor.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you were gentle with sinners who came to you, seeking the forgiveness of God. Pray that I may be merciful to those who sin against me, and forgive them, remembering how much our Lord has forgiven me of.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, fostes manso com os pecadores que vinham a vós buscando o perdão de Deus. Rogai para que eu seja misericordioso com os que pecam contra mim e os perdoe, lembrando-me de quanto nosso Senhor me perdoou.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, fuiste bondadoso con los pecadores que acudían a ti buscando el perdón de Dios. Ruega para que yo sea misericordioso con quienes pecan contra mí y los perdone, recordando cuánto me ha perdonado nuestro Señor.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you were gentle with sinners who came to you, seeking the forgiveness of God. Pray that I may be merciful to those who sin against me, and forgive them, remembering how much our Lord has forgiven me of.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, fostes manso com os pecadores que vinham a vós buscando o perdão de Deus. Rogai para que eu seja misericordioso com os que pecam contra mim e os perdoe, lembrando-me de quanto nosso Senhor me perdoou.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, pasabas horas en oración, en contemplación silenciosa, y así fuiste moldeado a imagen de Jesucristo. Ayúdame a aprender a rezar mejor y a pasar tiempo en silencio con Dios.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you spent hours in prayer, in quiet contemplation, and so were molded into the shape of Jesus Christ. Help me to learn to pray better and spend time in silence with God.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, passáveis horas em oração, em silenciosa contemplação, e assim fostes moldado à forma de Jesus Cristo. Ajudai-me a aprender a rezar melhor e a passar tempo em silêncio com Deus.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, pasabas horas en oración, en contemplación silenciosa, y así fuiste moldeado a imagen de Jesucristo. Ayúdame a aprender a rezar mejor y a pasar tiempo en silencio con Dios.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you spent hours in prayer, in quiet contemplation, and so were molded into the shape of Jesus Christ. Help me to learn to pray better and spend time in silence with God.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, passáveis horas em oração, em silenciosa contemplação, e assim fostes moldado à forma de Jesus Cristo. Ajudai-me a aprender a rezar melhor e a passar tempo em silêncio com Deus.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, apreciabas mucho la pureza de corazón y llevaste a otros a la pureza. Obtenme las gracias que necesito para vencer todos los pecados contra la santa pureza y resistir las tentaciones contra esta gran virtud.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you prized purity of heart highly and led others to purity. Obtain for me the graces I need to conquer all sins against holy purity and resist temptations against this great virtue.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, estimáveis muito a pureza de coração e conduzíeis os outros à pureza. Alcançai-me as graças de que preciso para vencer todos os pecados contra a santa pureza e resistir às tentações contra esta grande virtude.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, apreciabas mucho la pureza de corazón y llevaste a otros a la pureza. Obtenme las gracias que necesito para vencer todos los pecados contra la santa pureza y resistir las tentaciones contra esta gran virtud.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you prized purity of heart highly and led others to purity. Obtain for me the graces I need to conquer all sins against holy purity and resist temptations against this great virtue.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, estimáveis muito a pureza de coração e conduzíeis os outros à pureza. Alcançai-me as graças de que preciso para vencer todos os pecados contra a santa pureza e resistir às tentações contra esta grande virtude.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, viviste para el Cielo y para la Visión Beatífica de la Santísima Trinidad, y animaste a tu rebaño a hacer lo mismo. Ruega por mí, para que no me ahoguen los afanes y preocupaciones de este mundo, sino que mantenga mis ojos firmemente puestos en el gozo sin fin del Cielo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you lived for Heaven and the Beatific Vision with the Holy Trinity, encouraging your flock to do the same. Pray for me, that I won’t be choked by the cares and worries of this world, but will keep my eyes firmly set on Heaven’s unending joy.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, vivestes para o Céu e para a Visão Beatífica da Santíssima Trindade, animando o vosso rebanho a fazer o mesmo. Rogai por mim, para que eu não seja sufocado pelos cuidados e preocupações deste mundo, mas mantenha os olhos firmemente fixos na alegria sem fim do Céu.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, viviste para el Cielo y para la Visión Beatífica de la Santísima Trinidad, y animaste a tu rebaño a hacer lo mismo. Ruega por mí, para que no me ahoguen los afanes y preocupaciones de este mundo, sino que mantenga mis ojos firmemente puestos en el gozo sin fin del Cielo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you lived for Heaven and the Beatific Vision with the Holy Trinity, encouraging your flock to do the same. Pray for me, that I won’t be choked by the cares and worries of this world, but will keep my eyes firmly set on Heaven’s unending joy.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, vivestes para o Céu e para a Visão Beatífica da Santíssima Trindade, animando o vosso rebanho a fazer o mesmo. Rogai por mim, para que eu não seja sufocado pelos cuidados e preocupações deste mundo, mas mantenha os olhos firmemente fixos na alegria sem fim do Céu.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, recibías con fervor a Jesús en la Eucaristía en cada Misa que celebrabas. Ruega para que me mantenga libre de pecado mortal y obtenme una parte de la devoción con que tú recibías a Jesús en el Sacramento.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you fervently received Jesus in the Eucharist at each Mass you celebrated. Pray that I am kept free from mortal sin and obtain for me a share in the devotion with which you received Jesus in the Sacrament.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, recebíeis Jesus com fervor na Eucaristia em cada Missa que celebráveis. Rogai para que eu seja guardado do pecado mortal e alcançai-me uma parte da devoção com que recebíeis Jesus no Sacramento.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

San Juan María Vianney, recibías con fervor a Jesús en la Eucaristía en cada Misa que celebrabas. Ruega para que me mantenga libre de pecado mortal y obtenme una parte de la devoción con que tú recibías a Jesús en el Sacramento.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

St. John Vianney, you fervently received Jesus in the Eucharist at each Mass you celebrated. Pray that I am kept free from mortal sin and obtain for me a share in the devotion with which you received Jesus in the Sacrament.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

São João Maria Vianney, recebíeis Jesus com fervor na Eucaristia em cada Missa que celebráveis. Rogai para que eu seja guardado do pecado mortal e alcançai-me uma parte da devoção com que recebíeis Jesus no Sacramento.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

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

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Santo Cura de Ars, tu llegada a aquel pueblo fue como una avalancha espiritual en un tiempo de gran persecución y oscuridad. Ruega por nosotros para que, en nuestro tiempo oscuro, la luz de Cristo brille desde nosotros y muestre a todos el camino del Cielo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Holy priest of Ars, your coming to that town was like a spiritual avalanche in a time of great persecution and darkness. Pray for us that in our dark time, the light of Christ shines forth from us and shows the way to Heaven for all to see.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Santo sacerdote de Ars, a vossa chegada àquela aldeia foi como uma avalanche espiritual num tempo de grande perseguição e trevas. Rogai por nós, para que no nosso tempo de trevas a luz de Cristo brilhe a partir de nós e mostre a todos o caminho do Céu.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Juan María Vianney, tú fuiste ejemplo de lo que debe ser un santo sacerdote de Cristo. Tu paciencia y tu fortaleza en el confesonario mostraron la misericordia de Dios a los penitentes que buscaban el perdón de sus pecados.

Como Cristo, fuiste bondadoso y misericordioso, pero también llamaste a tu rebaño al arrepentimiento y a la enmienda de vida. Ayúdame con tu poderosa intercesión en el camino de santidad que nuestro Señor ha preparado para mí.

En particular, te pido que me obtengas las gracias de

(menciona tu petición aquí…)

Amén.

Santo Cura de Ars, tu llegada a aquel pueblo fue como una avalancha espiritual en un tiempo de gran persecución y oscuridad. Ruega por nosotros para que, en nuestro tiempo oscuro, la luz de Cristo brille desde nosotros y muestre a todos el camino del Cielo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St. John Vianney, you exemplified what a holy priest of Christ should be. Your patience and fortitude in the confessional demonstrated the mercy of God to the penitents who sought forgiveness for their sins.

Like Christ, you were gracious and merciful, yet also you called your flock to repentance and amendment of life. Help me through your powerful intercession along the path to sanctity that our Lord has prepared for me.

In particular, I ask that you obtain for me the graces of

(mention request here…)

Amen.

Holy priest of Ars, your coming to that town was like a spiritual avalanche in a time of great persecution and darkness. Pray for us that in our dark time, the light of Christ shines forth from us and shows the way to Heaven for all to see.

Our Father
 Hail Mary
 Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São João Maria Vianney, fostes o exemplo do que deve ser um santo sacerdote de Cristo. A vossa paciência e fortaleza no confessionário mostraram a misericórdia de Deus aos penitentes que buscavam o perdão dos seus pecados.

Como Cristo, fostes bondoso e misericordioso, mas também chamastes o vosso rebanho ao arrependimento e à emenda de vida. Ajudai-me, pela vossa poderosa intercessão, no caminho da santidade que nosso Senhor preparou para mim.

Em particular, peço que me alcanceis as graças de

(mencione o pedido aqui…)

Amém.

Santo sacerdote de Ars, a vossa chegada àquela aldeia foi como uma avalanche espiritual num tempo de grande perseguição e trevas. Rogai por nós, para que no nosso tempo de trevas a luz de Cristo brilhe a partir de nós e mostre a todos o caminho do Céu.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-john-vianney-teaser.png""",
        tags = listOf("health")
    )
}